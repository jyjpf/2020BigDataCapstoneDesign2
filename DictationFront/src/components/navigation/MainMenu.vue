<template>
  <v-list nav dense>
    <div v-for="(item, index) in menu" :key="index">
      <div v-if="item.key || item.text" class="pa-1 mt-2 overline">{{ item.key ? $t(item.key) : item.text }}</div>
      <nav-menu :menu="item.items" />
    </div>
  </v-list>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
import NavMenu from './NavMenu'

export default {
  components: {
    NavMenu
  },
  props: {
    menu: {
      type: Array,
      default: () => []
    }
  },
  computed: {
    ...mapState('dictation-app', ['role'])
  },
  created() {
    this.menu[1].items[1].items = []
    // TODO: vuex로 메뉴데이터 관리
    axios.get('/api/lecture?type=menu')
      .then((res) => { 
        for (const item of res.data) {
          this.menu[1].items[1].items.push({
            link: '/apps/lecture/' + item.lecture_no,
            text: item.lecture_nm
          })
        }
      })
    if (this.role === 'STUDENT')
      this.menu[1].items[0].text = '수업 신청'
  }
}
</script>
