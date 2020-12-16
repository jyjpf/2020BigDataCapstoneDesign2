<template>
  <div class="d-flex flex-column flex-grow-1">

    <v-card>
      <v-toolbar color="primary lighten-1" dark flat>
        <v-toolbar-title>{{ lectureName }}</v-toolbar-title>
      </v-toolbar>
      <!-- users list -->
      
      <v-data-table
        :headers="headers"
        :items="levels"
        :search="searchQuery"
        :loading="isLoading"
        disable-sort
        class="flex-grow-1"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>학습현황</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
        </template>
        <template v-slot:[`header.score_avg`]="{ header }">
          {{ header.text }}
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-icon small v-bind="attrs" v-on="on">mdi-help-circle</v-icon>
            </template>
            <span>학생들이 시행한 받아쓰기 중 가장 마지막 회차에 받은 점수들의 평균값입니다.</span>
          </v-tooltip>
        </template>
        <template v-slot:[`item.state`]="{ item }">
          <div class="actions">
            <v-btn icon @click="open(item.course_no)">
              <v-icon>mdi-page-last</v-icon>
            </v-btn>
          </div>
        </template>
      </v-data-table>
      <v-dialog v-model="dialog" width="600px">
        <v-card>
          <v-card-title class="headline grey lighten-2">
            {{ level }} 단계 통계
            <v-spacer/>
            <v-btn icon @click="close()">
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-card-title>
          <v-divider/>
          <v-data-table
            :headers="detailHeaders"
            :items="details"
            :loading="isDialogLoading"
            disable-sort
            class="flex-grow-1"
          />
        </v-card>
      </v-dialog>
    </v-card>
  </div>
</template>

<script>
/*
|---------------------------------------------------------------------
| 학습 현황 Component
|---------------------------------------------------------------------
*/
import axios from 'axios'
export default {
  data() {
    return {
      dialog: false,
      isLoading: false,
      isDialogLoading: false,
      lectureName: '',
      searchQuery: '',
      headers: [
        { text: '단계', value: 'course_no', align: 'center' },
        { text: '통과 점수 평균', value: 'score_avg', align: 'center' },
        { text: '완료한 학생수', value: 'complete_student', align: 'center' },
        { text: '학생별 통계', value: 'state', align: 'center' }
      ],
      detailHeaders: [
        { text: '학생 이름', value: 'kor_nm', align: 'center' },
        { text: '시도 회차', value: 'num_of_trial', align: 'center' },
        { text: '점수', value: 'score', align: 'center' },
        { text: '제출 날짜', value: 'update_date', align: 'center' }
      ],
      levels: [],
      level: 0,
      details: []
    }
  },
  created() {
    this.isLoading = true
    axios.get(`/api/lecture/${this.$route.params.lecture}/stats`)
      .then((res) => {
        console.log(res.data)
        this.levels = res.data
      })
      .catch(() => {
        alert('통계를 불러오지 못했습니다.')
      })
      .then(() => {
        this.isLoading = false
      })
  },
  methods: {
    open(course_no) {
      this.details = []
      this.dialog = true
      this.isDialogLoading = true
      this.level = course_no
      axios.get(`/api/lecture/${this.$route.params.lecture}/stats/${course_no}`)
        .then((res) => {
          this.details = res.data
        })
        .catch(() => {
          alert('통계를 불러오지 못했습니다.')
        })
        .then(() => {
          this.isDialogLoading = false
        })
    },
    close() {
      this.dialog = false
    }
  }
}
</script>

<style lang="scss" scoped>
.email-app-top {
  height: 82px;
}
</style>