<template>
  <v-menu offset-y left transition="slide-y-transition">
    <template v-slot:activator="{ on }">
      <v-badge
        bordered
        content="2"
        offset-x="22"
        offset-y="22"
      >
        <v-btn icon v-on="on">
          <v-icon>mdi-bell-outline</v-icon>
        </v-btn>
      </v-badge>
    </template>

    <!-- dropdown card -->
    <v-card>
      <v-list three-line dense max-width="400">
        <v-subheader class="pa-2 font-weight-bold">Notifications</v-subheader>
        <div v-for="(item, index) in items" :key="index">
          <v-divider v-if="index > 0 && index < items.length" inset></v-divider>

          <v-list-item>
            <v-list-item-avatar size="32" :color="item.color">
              <v-icon dark small>{{ item.icon }}</v-icon>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title v-text="item.title"></v-list-item-title>
              <v-list-item-subtitle class="caption" v-text="item.subtitle"></v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action class="align-self-center">
              <v-list-item-action-text v-text="item.time"></v-list-item-action-text>
            </v-list-item-action>
          </v-list-item>
        </div>
      </v-list>

      <div class="text-center py-2">
        <v-btn small>See all</v-btn>
      </div>
    </v-card>
  </v-menu>
</template>

<script>
/*
|---------------------------------------------------------------------
| Toolbar Notifications Component
|---------------------------------------------------------------------
|
| Quickmenu to check out notifications
|
*/
export default {
  data() {
    return {
      items: [
        {
          title: '남산 초등학교 2학기 받아쓰기 과제 알림',
          color: 'success',
          icon: 'mdi-email-outline',
          subtitle: '1학년 2반 이영재 1주차 김영자선생님 1단계,2단계 과제 알림',
          time: '하루 전'
        },
        {
          title: '남산 초등학교 2학기 받아쓰기 결과 알림',
          color: 'success',
          icon: 'mdi-email-outline',
          subtitle: '1학년 2반 이영재 1주차 김영자선생님 1단계,2단계 과제 결과 알림',
          time: '오늘'
        }
      ]
    }
  }
}
</script>
