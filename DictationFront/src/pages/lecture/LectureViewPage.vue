<template>
  <div class="d-flex flex-column flex-grow-1">
    <div class="d-flex align-center py-3">
      <div>
        <div class="display-1">전체 수업</div>
      </div>
      <v-spacer></v-spacer>
      <v-btn
        v-if="role === 'TEACHER'" 
        color="primary"
        @click="gowrite()"
      >강좌 개설하기</v-btn>
    </div>

    <v-card>
      <!-- users list -->
      <v-row dense class="pa-2 align-center">
        <v-col cols="6">

        </v-col>
        <v-col cols="6" class="d-flex text-right align-center">
          <v-text-field
            v-model="searchQuery"
            append-icon="mdi-magnify"
            class="flex-grow-1 mr-md-2"
            solo
            hide-details
            dense
            clearable
            placeholder="강좌코드, 강좌명, 학년, 선생님을 입력하세요"
          ></v-text-field>
          <v-btn
            :loading="isLoading"
            icon
            small
            class="ml-2"
          >
            <v-icon>mdi-refresh</v-icon>
          </v-btn>
        </v-col>
      </v-row>

      <v-data-table
        :headers="headers"
        :items="lectures"
        :search="searchQuery"
        class="flex-grow-1"
      >
        <template v-slot:[`item.lecture_no`]="{ item }">
          <div class="font-weight-bold"># {{ item.lecture_no }} </div>
        </template>

        <template v-if="role === 'TEACHER'" v-slot:[`item.state`]="{ }">
          <div class="actions">
            <v-btn icon @click="open()">
              <v-icon>mdi-page-last</v-icon>
            </v-btn>
          </div>
        </template>
        <template v-else v-slot:[`item.state`]="{ item }">
          <div class="actions">
            <v-btn 
              :class="item.class"
              width="80px"
              @click="enroll(item)"
            >{{ item.state }}</v-btn>
          </div>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
import LectureWrite from './LectureWrite'

// TODO: class - switch 문 리팩토링
export default {
  data() {
    return {
      isLoading: false,
      searchQuery: '',
      headers: [
        { text: '강좌코드', align: 'left', value: 'lecture_no' },
        { text: '강좌명', value: 'lecture_nm' },
        { text: '학년', value: 'grade' },
        { text: '선생님', align: 'left', value: 'teacher_nm' },
        { text: '상태', align: 'left', value: 'state' }
      ],
      lectures: []
    }
  },
  computed: {
    ...mapState('dictation-app', ['role'])
  },
  created() {

    if (this.role === 'TEACHER') {
      axios.get('/api/lecture')
        .then((res) => {
          res.data.map((item) => this.lectures.push({
            lecture_no: item.lecture_no,
            lecture_nm: item.lecture_nm,
            grade: item.grade,
            teacher_nm: item.teacher_nm
          }))
        })
        .catch((err) => {
          console.log(err)
        })
    } else {
      axios.get('/api/enroll')
        .then((res) => {
          
          res.data.map((item) => {
            let color = null

            switch (item.approval_cd) {
            case '승인':
              color = 'light-green accent-3'
              break
            case '대기중':
              color = 'yellow accent-3'
              break
            case '거절':
              color = 'red accent-3'
              break
            default:
              color = 'grey lighten-2'
            }

            return this.lectures.push({
              lecture_no: item.lecture_no,
              lecture_nm: item.lecture_nm,
              grade: item.grade,
              teacher_nm: item.teacher_nm,
              state: item.approval_cd || '신청안함',
              class: color
            })
          })
        })
        .catch((err) => {
          console.log(err)
        })
    }

  },
  methods: {
    enroll(lecture) {

      switch (lecture.state) {
      case '승인':
        alert('승인된 수업은 취소할 수 없습니다.')
        break
      case '대기중':
        axios.delete(`/api/enroll/${lecture.lecture_no}`)
          .then(() => {
            lecture.state = '신청안함'
            lecture.class = 'grey lighten-2'
          })
          .catch(() => alert('수강신청오류'))
        break
      case '거절':
        alert('거절된 수업은 신청할 수 없습니다.')
        break
      default:
        axios.post('/api/enroll', lecture)
          .then(() => {
            lecture.state = '대기중'
            lecture.class = 'yellow accent-3'
          })
          .catch(() => alert('수강신청오류'))
      }

    },
    gowrite() {
      this.$router.push('/lecture/write')
    },
    open() {
      alert('전체강좌의 학습자료실 이동은 개발중인 기능입니다.')
    }
  }
}
</script>

<style lang="scss" scoped>
.slide-fade-enter-active {
  transition: all 0.3s ease;
}
.slide-fade-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateX(10px);
  opacity: 0;
}
</style>
