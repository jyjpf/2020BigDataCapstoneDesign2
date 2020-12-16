<template>
  <Board :board-type="boardType" />
</template>

<script>
import Board from '../components/Board'

export default {
  components: {
    Board
  },
  data() {
    return {
      boardType: {
        text: '공지사항',
        value: '006001'
      }
    }
  }
}
</script>