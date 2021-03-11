<template>
  <div class="d-flex flex-column flex-grow-1">
    <v-card>
      <v-toolbar
        color="primary lighten-1"
        dark
        flat
      >
        <!--밑에 강좌이름도 받아오는걸로 바꾸기 -->
        <v-toolbar-title>{{ lectureName }}</v-toolbar-title>
      </v-toolbar>
      <v-card>

        <!--받아쓰기하기 화면 --> 
        <v-container align-center>
          <div class="mt-5">
            <v-slider
              v-model="level"
              readonly
              label="단계"
              step="1"
              thumb-label="always"
              :min="1"
              :max="40"
              ticks="always"
              tick-size="5"
            >
            </v-slider>
            <v-btn 
              v-if="!(level < 2) && role === 'TEACHER'"
              :loading="isLoading"
              :disabled="isLoading"
              class="ma-1"
              width-right="60"
              color="primary"
              @click="getLevel(--level)"
            >1단계-</v-btn>
            <v-btn
              v-if="!(level > 39) && role === 'TEACHER'"
              :loading="isLoading"
              :disabled="isLoading"
              class="ma-1"
              width-left="60"
              color="primary"
              @click="getLevel(++level)"
            >1단계+</v-btn>
          </div>
        
          <v-form v-if="modified || role == 'TEACHER'" class="pt-4 pr-4 pl-4">
            <v-card
              v-for="item in questions"
              :key="item.question_no"
              class="pa-1 mt-3"
              :color="item.isCorrect == null ? '' : (item.isCorrect ? 'blue lighten-4' : 'red lighten-4')"
              elevation="1"
            > 
              
              <v-file-input
                v-if="role === 'TEACHER'"
                v-model="item.file"
                :disabled="maxlevel > level"
                class="my-1"
                accept="audio/*"
                dense
                hide-details
                @change="createUrl(item)"
              />
              <!-- TODO: style -> class -->
              <audio ref="audio" :src="item.fileUrl" style="height: 30px" controls />
              
              <v-text-field 
                v-model="item.answer"
                label="정답을 적으세요."
                :disabled="maxlevel > level || practice"
                outlined
                hide-details
                dense
              >
                <template v-slot:prepend>
                  <div class="text-h6">
                    {{ item.question_no + "." }}
                  </div>
                </template>
              </v-text-field>

              <div v-if="practice && !item.isCorrect">
                <h1>정답 : {{ item.question }}</h1>
                <v-text-field
                  v-model="item.answer"
                  label="여기에 연습하세요."
                  outlined
                  hide-details
                  dense
                />
              </div>
            </v-card>

            <v-flex class="text-center">

              <v-btn
                v-if="maxlevel <= level && role === 'TEACHER'"
                :loading="isLoading"
                :disabled="isLoading"
                class="ma-1"
                width="270" 
                color="primary"
                x-large 
                @click="submit('save')"
              >저장</v-btn>

              <v-btn
                v-if="maxlevel <= level && !practice"
                :loading="isLoading"
                :disabled="isLoading"
                class="ma-1"
                width="270" 
                color="primary"
                x-large 
                @click="submit('submit')"
              >제출</v-btn>

              <v-btn
                v-if="practice"
                :loading="isLoading"
                :disabled="isLoading"
                class="ma-1"
                width="270" 
                color="primary"
                x-large 
                @click="nextLevel()"
              >연습 문제 제출하기</v-btn>

            </v-flex>
          </v-form>

          <v-dialog v-model="dialog" persistent max-width="290">
            <v-card>
              <v-card-title class="headline">당신의 점수는?</v-card-title>
              <v-card-text class="pa-3"><h1>{{ score }} 점입니다!</h1></v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn v-if="score != 100" color="green darken-1" text @click="startPractice()">틀린문제 연습하기</v-btn>
                <v-btn v-if="score == 100" color="green darken-1" text @click="nextLevel2()">다음단계로</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="dialog2" persistent max-width="290">
            <v-card>
              <v-card-title class="headline">당신의 연습 문제 점수는?</v-card-title>
              <v-card-text class="pa-3"><h1>{{ praticescore }} 점입니다!</h1></v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="again()">다시하기</v-btn>
                <v-btn color="green darken-1" text @click="nextLevel3()">다음단계로</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-alert
            v-if="maxlevel > level"
            type="info"
            class="my-1"
          >제출이 완료된 받아쓰기입니다.</v-alert>
        </v-container>
      </v-card> 
    </v-card>
    <v-alert
      v-if="!modified && role == 'STUDENT'"
      type="error"
      class="my-1"
    >등록된 받아쓰기가 없습니다</v-alert>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
/*
|---------------------------------------------------------------------
| 받아쓰기 화면
|---------------------------------------------------------------------
*/
// TODO: Refactoring 필요 (학생, 선생님 컴포넌트 분리, 로직 따로 작성)
export default {
  data() {
    return {
      dialog2: false,
      modified: false,
      practice: false,
      dialog: false,
      score: 0,
      praticescore: 0,
      praticestext:[
        { answer:'' },
        { answer:'' },
        { answer:'' }
      ],
      isLoading: false,
      lectureName: '',
      level: 0,
      maxlevel: 0,
      questions: [
        { question_no: 1, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 2, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 3, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 4, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 5, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 6, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 7, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 8, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 9, answer: '', file: null, fileUrl: null, isCorrect: null },
        { question_no: 10, answer: '', file: null, fileUrl: null, isCorrect: null }
      ]
    }
  },
  computed: {
    ...mapState('dictation-app', ['role'])
  },
  async created() {
    
    this.isLoading = true

    let dictation = null
    
    this.maxlevel = this.level = await this.getMaxLevel()
    dictation = await axios.get(`/api/lecture/${this.$route.params.lecture}/course/${this.maxlevel}`)

    this.modified = this.isModified(dictation.data)

    for await (const item of dictation.data) {
      const { save_file_nm, file_nm, question_no, question } = item
      const ii = question_no - 1

      this.questions[ii].answer = question

      if (save_file_nm !== null) {

        this.questions[ii].file = { name: file_nm }
        this.questions[ii].fileUrl = await this.getAudioURL(save_file_nm)

      }

    }

    this.isLoading = false

  },
  methods: {
    createUrl(row) {
      const url = URL.createObjectURL(row.file)

      row.fileUrl = url
    },
    startPractice() {
      this.dialog = false
      this.practice = true
    },
    again () {
      this.dialog2 = false
      this.practice = true
      this.praticescore = 0
    },
    //연습문제에서 다음단계로 넘어가기
    nextLevel2() {
      axios.put('/api/enroll', {
        lecture_no: this.$route.params.lecture,
        pass_course_no: this.level
      })
      this.maxlevel = this.level = this.level + 1
      this.getLevel(this.level)
      this.dialog = false
    },
    nextLevel3() {
      axios.put('/api/enroll', {
        lecture_no: this.$route.params.lecture,
        pass_course_no: this.level
      })
      this.maxlevel = this.level = this.level + 1
      this.getLevel(this.level)
      this.dialog2 = false
    },
    //연습문제 계산하기
    async nextLevel() {

      if (confirm('연습한 받아쓰기를 제출하시겠습니까?')) {

        const seq = await axios.get(`/api/lecture/${this.$route.params.lecture}/study/${this.level}/seq`)

        for await (const question of this.questions) {
        
          const res = await axios.post(`/api/lecture/${this.$route.params.lecture}/study/${this.level}`, {
            question_no: question.question_no,
            seq_no: seq.data,
            answer: question.answer
          })

          question.question = res.data.answer
          question.isCorrect = res.data.correct_yn
          this.praticescore += res.data.correct_yn ? 10 : 0 

          this.dialog2 = true
          this.practice = false
        }
      }
    },
    //받아쓰기 제출
    async submit(mode) {
      this.isLoading = true
      
      // TODO: save 정삭적으로 마치면 알람(snackbar) 출력
      if (this.role === 'TEACHER') {

        if (mode === 'submit') {
          // TODO: confirm dialog로 변경
          if (confirm('제출하신 받아쓰기는 바꾸실 수 없습니다.')) {

            for await (const question of this.questions) {
              const formData = new FormData()
      
              formData.append('course_no', this.level)
              formData.append('question_no', question.question_no)
              formData.append('question', question.answer)
              formData.append('file', question.file)

              if (this.modified === true) 
                await axios.put(`/api/lecture/${this.$route.params.lecture}/course`, formData)
              else
                await axios.post(`/api/lecture/${this.$route.params.lecture}/course`, formData)
            }

            this.maxlevel = this.level = this.level + 1

            await axios.put(`/api/lecture/${this.$route.params.lecture}`, { 
              level: this.level
            })

            this.getLevel(this.level)

          } 
        } else {

          for await (const question of this.questions) {
            const formData = new FormData()
    
            formData.append('course_no', this.level)
            formData.append('question_no', question.question_no)
            formData.append('question', question.answer)
            formData.append('file', question.file)

            if (this.modified === true) 
              await axios.put(`/api/lecture/${this.$route.params.lecture}/course`, formData)
            else
              await axios.post(`/api/lecture/${this.$route.params.lecture}/course`, formData)

          }
        }

      } else {
        
        if (confirm('받아쓰기를 제출하시겠습니까?')) {

          const seq = await axios.get(`/api/lecture/${this.$route.params.lecture}/study/${this.level}/seq`)

          for await (const question of this.questions) {
            const res = await axios.post(`/api/lecture/${this.$route.params.lecture}/study/${this.level}`, {
              question_no: question.question_no,
              seq_no: seq.data,
              answer: question.answer
            })

            //console.log('test' + res.data.answer)
            question.question = res.data.answer
            question.isCorrect = res.data.correct_yn
            this.score += res.data.correct_yn ? 10 : 0
            //console.log(res.data.answer)
          }

          this.dialog = true

        }

      }

      this.isLoading = false

    },
    getLevel(level) {
   
      this.isLoading = true
      this.score = 0

      if (this.questions[0].fileUrl) {
        let idx = 0

        for (const item of this.questions) {
          const temp =  item.fileUrl

          item.fileUrl = null
          URL.revokeObjectURL(temp)
          this.$refs.audio[idx++].load()
        }
      }

      this.questions = this.$options.data().questions
      
      const url = `/api/lecture/${this.$route.params.lecture}/course/${level}`

      axios.get(url)
        .then(async (res) => {
          this.modified = this.isModified(res.data)

          if (res.data.length > 0) {
      
            for await (const item of res.data) {
              const { save_file_nm, file_nm, question_no, question } = item
              const question_no_idx = question_no - 1

              this.questions[question_no_idx].answer = question

              if (save_file_nm !== null) {

                this.questions[question_no_idx].file = { name: file_nm }
                this.questions[question_no_idx].fileUrl = await this.getAudioURL(save_file_nm)

              }

            }

          }

        })
        .catch((err) => console.log(err))
        .then(() => {
          this.isLoading = false
        })
     
    },
    async getMaxLevel() {
      let res = null

      if (this.role === 'TEACHER') {
        res = await axios.get(`/api/lecture/${this.$route.params.lecture}`)

        return res.data.level + 1
      } else { 
        res = await axios.get(`/api/enroll/${this.$route.params.lecture}`)

        return res.data.pass_course_no + 1
      }
    },
    async getAudioURL(fileName) {
      const audio = await axios.get(`/api/download/audio/${fileName}`, { responseType: 'blob' })
      const contentType = audio.headers['content-type']
      const blob = new Blob([audio.data], { contentType })

      return URL.createObjectURL(blob)
    },
    isModified(data) {
      return data.length > 0 ? true : false
    }
  }
}
</script>

<style lang="scss" scoped>
.email-app-top {
  height: 82px;
}
</style>
