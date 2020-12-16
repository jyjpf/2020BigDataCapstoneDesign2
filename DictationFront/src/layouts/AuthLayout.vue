<template>
  <div class="d-flex text-center flex-column flex-md-row flex-grow-1">
    <v-sheet class="layout-side mx-auto mx-md-1 d-none d-md-flex flex-md-column justify-space-between px-2">
      <div class="mt-3 mt-md-10 pa-2">
        <div class="display-2 font-weight-bold primary--text">
          {{ product.name }}
        </div>
        <br>
        <div class="title my-2">어서오세요!</div>
        <div class="title my-2">원격수업을 위한 받아쓰기 프로그램</div>
      </div>
    </v-sheet>

    <div class="pa-2 pa-md-4 flex-grow-1 align-center justify-center d-flex flex-column">
      <div class="layout-content ma-auto w-full">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState('app', ['product'])
  }
}
</script>

<style scoped>
.layout-side {
  width: 420px;
  background-image: url('/images/background/2.jpg');
  background-position: 80%;
}

.layout-content {
  max-width: 480px;
}
</style>
