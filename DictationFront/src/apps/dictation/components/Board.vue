<template>
  <div class="d-flex flex-column flex-grow-1">
    <v-card>
      <v-toolbar color="primary lighten-1" dark flat>
        <v-toolbar-title>{{ lectureName }}</v-toolbar-title>
      </v-toolbar>
      <v-card>
        <v-card class="mx-auto">
          <v-data-table
            :headers="headers"
            :items="contents"
            :loading="isLoading"
          >
            <template v-slot:top>
              <v-toolbar flat>
                <v-toolbar-title>{{ boardType.text }}</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn
                  v-if="role == 'TEACHER' && boardType.value == '006001'"
                  color="primary"
                  class="mb-2"
                  @click="openDialog()"
                >
                  공지사항 작성
                </v-btn>
                <v-btn
                  v-if="role !='TEACHER' && boardType.value == '006002'"
                  color="primary"
                  class="mb-2"
                  @click="openDialog()"
                >
                  질문 작성
                </v-btn>
                <Editor
                  v-if="showEditor"
                  :show-editor="showEditor"
                  :board-type="boardType"
                  :board-data="boardData"
                  @submit-dialog="submitDialog()"
                  @close-dialog="closeDialog()"
                />
              </v-toolbar>
            </template>
            <template v-slot:[`item.no`]="{ item }">
              <div class="font-weight-bold"># {{ item.no }} </div>
            </template>
            <template v-slot:[`item.title`]="{ item }">
              <a href="#" @click="openDialog2(item)">{{ item.title }} </a>
            </template>
            <template v-slot:[`item.update_date`]="{ item }">
              <div>{{ item.update_date.split('T')[0] }}</div>
            </template>
          </v-data-table>
        </v-card>
      </v-card>
    </v-card>
  </div>
</template>

<script>
import Editor from './Editor'
import axios from 'axios'
import { mapState } from 'vuex'

/*
|---------------------------------------------------------------------
| 공지사항 Component
|---------------------------------------------------------------------
*/
export default {
  components: {
    Editor
  },
  props: {
    boardType: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      showEditor: false,
      isLoading: false,
      contents: [],
      boardData: null,
      isCreate: null,
      lectureName: '',
      headers: [{
        text: '번호',
        value: 'no'
      }, {
        text: '제목',
        value: 'title'
      }, {
        text: '작성자',
        value: 'auth_nm'
      }, {
        text: '작성/수정일',
        value: 'update_date'
      }, {
        text: '조회수',
        value: 'read_cnt'
      }]
    }
  },
  computed: {
    ...mapState('dictation-app', ['role'])
  },
  created () {
    this.init()
  },
  methods: {
    init() {
      this.isLoading = true
      this.contents = this.$options.data().contents

      axios.get(`/api/lecture/${this.$route.params.lecture}/board?board_cd=${this.boardType.value}`)
        .then((res) => this.contents = res.data)
        .catch((err) => console.log(err))
        .then(() => this.isLoading = false)
    },
    closeDialog() {
      this.showEditor = false
      this.showDetail = false
    },
    submitDialog() {
      this.init()
      this.showEditor = false
    },
    openDialog(content) {
      this.boardData = content
      this.showEditor = true
    },
    //제목 클릭시
    openDialog2(content) {
      this.boardData = content
      this.showEditor = true
    }
  }
}
</script>

<style lang="scss" scoped>
.email-app-top {
  height: 82px;
}

</style>

<style scoped>
a {
  text-decoration: none;
}

</style>