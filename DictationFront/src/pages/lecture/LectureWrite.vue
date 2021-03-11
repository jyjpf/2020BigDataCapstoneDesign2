<template>
  <div class="ma-auto">
    <v-card class="pa-3" width="500px">

      <v-form
        ref="form"
        v-model="valid"
        lazy-validation
      >
        <v-select
          v-model="lecture.lecture_type_cd" 
          :items="lectureType"
          outlined
          color="blue-grey lighten-2"
          label="공개 여부"
          :rules="[v => !!v || '공개여부를 선택하세요.']"
        />

        <v-text-field
          v-model="lecture.lecture_nm"
          outlined
          color="blue-grey lighten-2"
          label="강좌이름"
          :rules="[v => !!v || '강좌이름을 입력하세요!']"
        />
        <div class="d-flex flex-row">
          <v-text-field
            v-model="lecture.year"
            color="blue-grey lighten-2"
            label="년도"
            outlined
            :rules="[v => !!v || '년도를 입력하세요!']"
          />
        
          <v-select
            v-model="lecture.term"
            :items="enroll"
            outlined
            color="blue-grey lighten-2"
            label="학기"
            :rules="[v => !!v || '학기를 선택하세요!']"
          />
        </div>
        <v-select 
          v-model="lecture.grade"
          :items="grade"  
          color="blue-grey lighten-2"
          label="학년"
          outlined
          :rules="[v => !!v || '학년을 선택하세요.']"
        />

        <v-text-field
          v-model="lecture.ban"
          color="blue-grey lighten-2"
          label="반"
          outlined
          :rules="[v => !!v || '반을 입력하세요.']"
        />

        <v-menu
          ref="startDatePicker"
          v-model="startDatePicker"
          :close-on-content-click="false"
          :return-value.sync="lecture.enroll_st_dt"
          transition="scale-transition"
          offset-y
          min-width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="lecture.enroll_st_dt"
              label="수강신청 시작일"
              prepend-icon="mdi-calendar"
              readonly
              :rules="[v => !!v || '수강신청일을 선택하세요!']"
              v-bind="attrs"
              v-on="on"
            />
          </template>
          <v-date-picker
            v-model="lecture.enroll_st_dt"
            scrollable
            locale="ko-kr"
          >
            <v-spacer/>
            <v-btn
              text
              color="primary"
              @click="startDatePicker = false"
            >Cancel</v-btn>

            <v-btn
              text
              color="primary"
              @click="$refs.startDatePicker.save(lecture.enroll_st_dt)"
            >OK</v-btn>
          </v-date-picker>
        </v-menu>

        <v-menu
          ref="endDatePicker"
          v-model="endDatePicker"
          :close-on-content-click="false"
          :return-value.sync="lecture.enroll_ed_dt"
          transition="scale-transition"
          offset-y
          min-width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="lecture.enroll_ed_dt"
              label="수강신청 종료일"
              prepend-icon="mdi-calendar"
              readonly
              :rules="[v => !!v || '수강신청종료일을 선택하세요!']"
              v-bind="attrs"
              v-on="on"
            />
          </template>
          <v-date-picker
            v-model="lecture.enroll_ed_dt"
            scrollable
            locale="ko-kr"
          >
            <v-spacer/>
            <v-btn
              text
              color="primary"
              @click="endDatePicker = false"
            >
              Cancel
            </v-btn>
            <v-btn
              text
              color="primary"
              @click="$refs.endDatePicker.save(lecture.enroll_ed_dt)"
            >
              OK
            </v-btn>
          </v-date-picker>
        </v-menu>

        <v-text-field 
          v-model="lecture.teacher_nm"
          label="강좌등록 선생님 이름"
          outlined
          :rules="[v => !!v || '선생님 이름을 입력하세요.']"
        />
      
        <v-card-actions>
          <v-btn
            color="primary base"
            width="300px"
            class="ma-auto"
            @click="sumbit()"
          >강좌 등록하기</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {

    return {
      startDatePicker: null,
      endDatePicker: null,
      name: '강좌 등록',
      valid: true,
      menu: false,
      tabs: null,
      text: '',
      grade: ['1','2','3','4','5','6'],
      enroll: ['1','2'],
      lectureType: [{
        text: '모두승인',
        value: '005001'
      }, {
        text: '선택승인',
        value: '005002'
      }, {
        text: '비공개',
        value: '005003'
      }],
      lecture: {
        year: '',
        term: '',
        lecture_nm: '',
        lecture_type_cd:'',
        grade: '',
        ban: '',
        enroll_st_dt: '',
        enroll_ed_dt: '',
        teacher_nm: ''
      }
    }
  },
  methods: {
    sumbit() {
      if (this.$refs.form.validate()) {
        axios.post('/api/lecture', this.lecture)
          .then((res) => this.$router.push('/lecture'))
          .catch((err) => console.error(err))
      }

    }
  }
}
</script>
