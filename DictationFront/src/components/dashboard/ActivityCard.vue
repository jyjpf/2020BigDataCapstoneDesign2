<template>
  <v-card>
    <v-card-title>
      <div>받아쓰기 일정</div>
      <v-spacer></v-spacer>
      <v-menu offset-y left transition="slide-y-transition">
        <template v-slot:activator="{ on }">
          <v-btn icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list dense>
          <v-list-item
            v-for="(item, index) in menu"
            :key="index"
            :to="item.link"
            :exact="item.exact"
            :disabled="item.disabled"
            link
          >
            <v-list-item-icon>
              <v-icon small :class="{ 'grey--text': item.disabled }">{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ item.text }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-card-title>

    <v-card-text>
      <v-timeline class="pa-0" dense align-top>
        <v-timeline-item v-for="(item, index) in activity" :key="index" :color="item.color" small>
          <strong>{{ item.what }}</strong>
          <div class="caption">
            <div class="grey--text">{{ item.when }}</div>
          </div>
        </v-timeline-item>
      </v-timeline>
    </v-card-text>
  </v-card>
</template>

<script>
/*
|---------------------------------------------------------------------
| DEMO Dashboard Card Component
|---------------------------------------------------------------------
|
| Demo card component to be used to gather some ideas on how to build
| your own dashboard component
|
*/
export default {
  data() {
    return {
      menu: [
        { icon: 'mdi-refresh', text: 'Refresh' },
        { icon: 'mdi-delete-outline', text: 'Clear' }
      ],
      activity: [{
        what: '받아쓰기 1단계 작성',
        when: '1학년 2반',
        color: 'green accent-3'
      }, {
        what: '받아쓰기 4단계 작성',
        when: '2학년 7반',
        color: 'red lighten-3'
      }, {
        what: '받아쓰기 5단계 작성',
        when: '2학년 7반',
        color: 'red lighten-3'
      }, {
        what: '받아쓰기 2단계 작성',
        when: '1학년 2반',
        color: 'green accent-3'
      }, {
        what: '받아쓰기 6단계 작성',
        when: '2학년 7반',
        color: 'red lighten-3'
      }, {
        what: '받아쓰기 3단계 작성',
        when: '1학년 2반',
        color: 'green accent-3'
      }]
    }
  }
}
</script>
