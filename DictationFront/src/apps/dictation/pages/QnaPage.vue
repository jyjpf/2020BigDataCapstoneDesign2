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
        text: '묻고 답하기',
        value: '006002'
      }
    }
  }
}
</script>
