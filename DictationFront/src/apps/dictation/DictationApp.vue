<template>
  <div class="d-flex flex-grow-1 flex-row mt-2">
    <v-navigation-drawer
      :app="$vuetify.breakpoint.mdAndDown"
      :permanent="$vuetify.breakpoint.lgAndUp"
      floating
      class="elevation-1 rounded flex-shrink-0"
      :class="[$vuetify.rtl ? 'ml-3' : 'mr-3']"
      width="240"
    >
      <Menu class="pa-2" />
    </v-navigation-drawer>

    <div class="d-flex flex-grow-1 flex-column">
      <transition name="fade" mode="out-in">
        <router-view :key="$route.fullPath" class="flex-grow-1"></router-view>
      </transition>
    </div>
  </div>
</template>

<script>
import Menu from './components/Menu'

/*
|---------------------------------------------------------------------
| 받아쓰기 메인 컴포넌트
|---------------------------------------------------------------------
*/
export default {
  components: {
    Menu
  }
}
</script>

<style scoped>
/**
 * Transition animation between pages
 */
.fade-enter-active,
.fade-leave-active {
  transition-duration: 0.2s;
  transition-property: opacity;
  transition-timing-function: ease;
}

.fade-enter,
.fade-leave-active {
  opacity: 0;
}
</style>
