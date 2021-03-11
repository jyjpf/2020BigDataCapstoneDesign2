<template>
  <div class="d-flex flex-column flex-grow-1">

    <v-card>
      <v-toolbar color="primary lighten-1" dark flat>
        <v-toolbar-title>{{ lectureName }}</v-toolbar-title>
      </v-toolbar>

      <v-data-table
        :headers="headers"
        :items="students"
        :search="searchQuery"
        class="flex-grow-1"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>신청현황</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-text-field
              v-model="searchQuery"
              append-icon="mdi-magnify"
              class="flex-grow-1 mr-md-2"
              solo
              hide-details
              dense
              clearable
              placeholder="e.g. filter for id, email, name, etc"
            />
            <v-btn
              :loading="isLoading"
              icon
              small
              class="ml-2"
            >
              <v-icon>mdi-refresh</v-icon>
            </v-btn>
          </v-toolbar>
        </template>
        <template v-slot:[`item.state`]="{ item }">
          <v-select
            v-model="item.state"
            :items="selects"
            :background-color="item.class"
            solo
            dense
            hide-details
            @change="apply(item)"
          />
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
/*
|---------------------------------------------------------------------
| 강좌 학생 승인 Component
|---------------------------------------------------------------------
*/
import axios from 'axios'
import { mapState } from 'vuex'

// TODO: class - switch 문 리팩토링
export default {
  data() {
    return {
      isLoading: false,
      lectureName: '',
      searchQuery: '',
      selects: [
        { text: '승인', value: '승인' },
        { text: '대기중', value: '대기중' },
        { text: '거절', value: '거절' }
      ],
      headers: [
        { text: '이름', value: 'kor_nm' },
        { text: '학년', value: 'grade' },
        { text: '반', value: 'ban' },
        { text: '번호', value: 'bunho' },
        { text: '상태', align: 'center', value: 'state', width: '150px' }
      ],
      students: []
    }
  },
  computed: {
    ...mapState('dictation-app', ['role'])
  },
  created() {

    axios.get(`/api/enroll/${this.$route.params.lecture}`)
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
          }

          return this.students.push({
            user_id: item.user_id,
            kor_nm: item.kor_nm,
            grade: item.grade,
            ban: item.ban,
            bunho: item.bunho,
            state: item.approval_cd,
            class: color
          })
        })
      })
      .catch((err) => console.log(err))

  },
  methods: {
    apply(student) {
    
      switch (student.state) {
      case '승인':
        axios.put('/api/enroll', { 
          lecture_no: this.$route.params.lecture,
          user_id: student.user_id,
          approval_cd: '004001'
        })
          .then(() => {
            student.class = 'light-green accent-3'
          })
          .catch(() => alert('신청업데이트오류'))
        break
      case '대기중':
        axios.put('/api/enroll', { 
          lecture_no: this.$route.params.lecture,
          user_id: student.user_id,
          approval_cd: '004003'
        })
          .then(() => {
            student.class = 'yellow accent-3'
          })
          .catch(() => alert('신청업데이트오류'))
        break
      case '거절':
        axios.put('/api/enroll', { 
          lecture_no: this.$route.params.lecture,
          user_id: student.user_id,
          approval_cd: '004002'
        })
          .then(() => {
            student.class = 'red accent-3'
          })
          .catch(() => alert('신청업데이트오류'))
        break
      }

    }
  }
}
</script>

<style lang="scss" scoped>
.email-app-top {
  height: 82px;
}
</style>
