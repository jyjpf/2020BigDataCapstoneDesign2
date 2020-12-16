<template> 
  <div>

    <v-list dense nav class="mt-2 pa-0">
      <v-list-item
        v-for="(item, index) in (menu.filter(x => x.roles.includes(role)))"
        :key="index"
        :to="item.link"
        active-class="primary--text"
        link
      >
        <v-list-item-content>
          <v-list-item-title>{{ item.label }}</v-list-item-title>
        </v-list-item-content>

        <v-list-item-action v-if="item.count > 0">
          <v-badge
            inline
            color="primary"
            class="font-weight-bold"
            :content="item.count"
          >
          </v-badge>
        </v-list-item-action>

      </v-list-item>

      <v-list-item v-if="role === 'TEACHER'" to="#">
        <v-list-item-content @click="dialog = true">
          <v-list-item-title>수업 삭제</v-list-item-title>
          <v-dialog
            v-model="dialog"
            width="500"
          >
            <v-card>
              <v-alert type="error">수업 삭제하기</v-alert>

              <v-card-text>
                수업을 삭제하시면 공지사항, 묻고 답하기, 받아쓰기 음성 파일 및 답안, 학생들의 수강기록, 받아쓰기 기록 및 통계가 함께 삭제됩니다.<br>
                수업을 삭제하시려면 <span style="color:Tomato">수업명</span>을 입력하시고 <span style="color:DodgerBlue">동의합니다</span>를 눌러주세요
              </v-card-text>

              <v-text-field
                class="px-3"
                label="수업명을 입력하세요"
                outlined
              />

              <v-card-actions>
                <v-spacer/>
                <v-btn
                  color="primary"
                  text
                  @click="dialog = false"
                >동의합니다</v-btn>
                <v-btn
                  color="primary"
                  text
                  @click="dialog = false"
                >취소</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-list-item-content>

      </v-list-item>
    </v-list>

  </div>
</template>

<script>
import { mapState } from 'vuex'
/*
|---------------------------------------------------------------------
| 학습자료실 메뉴 컴포넌트
|---------------------------------------------------------------------
*/
export default {
  data() {
    return {
      dialog: false
    }
  },
  computed: {
    ...mapState('dictation-app', ['role']),
    menu() {
      return [{
        label: '공지사항',
        icon: 'bx bxs-inbox',
        link: 'notice',
        count: 3,
        roles: ['STUDENT', 'TEACHER']
      }, {
        label: '받아쓰기 등록',
        icon: 'bx-send',
        link: 'dictation',
        roles: ['TEACHER']
      }, {
        label: '학습현황',
        icon: 'bx-send',
        link: 'stats',
        roles: ['TEACHER']
      }, {
        label: '신청현황',
        icon: 'bx-send',
        link: 'apply',
        roles: ['TEACHER']
      }, {
        label: '받아쓰기 하기',
        icon: 'bx-send',
        link: 'dictation',
        roles: ['STUDENT']
      }, {
        label: '묻고 답하기',
        icon: 'mdi-delete-outline',
        link: 'qna',
        roles: ['STUDENT', 'TEACHER']
      }, {
        label: '수업 수정',
        icon: 'mdi-delete-outline',
        link: 'modify',
        roles: ['TEACHER']
      }]
    }
  }
}
</script>
