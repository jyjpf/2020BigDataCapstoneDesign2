<template>
  <div class="d-flex flex-grow-1 flex-column">
    <v-row class="flex-grow-0" dense>

      <v-col cols="12" md="6">
        <activity-card class="h-full" />
      </v-col>

      <v-col v-if="role === 'TEACHER'" cols="12" md="6">
        <sum-avg class="h-full" />
      </v-col>

      <v-col v-if="role === 'STUDENT'" cols="12" md="6">
        <distribution-map class="h-full" />
      </v-col>
    </v-row>

    <v-row class="flex-grow-0" dense>
      <v-col cols="12">
        <program-Time class="h-full" />
      </v-col>
    </v-row>

  </div>
</template>

<script>
// DEMO Cards for dashboard
import ActivityCard from '../../components/dashboard/ActivityCard'
import ProgramTime from '../../components/dashboard/ProgramTime' // 프로그램 사용 시간
import DistributionMap from '../../components/dashboard/DistributionMap' //내능력치
import SumAvg from '../../components/dashboard/SumAvg' // 이번주 총 평균점수
import { mapState } from 'vuex'

export default {
  components: {
    ActivityCard,
    DistributionMap,
    ProgramTime,
    SumAvg
  },
  data() {
    return {
      loadingInterval: null,

      isLoading1: true,
      isLoading2: true,
      isLoading3: true,
      isLoading4: true,

      ordersSeries: [{
        name: 'Orders',
        data: [
          ['2020-02-02', 34],
          ['2020-02-03', 43],
          ['2020-02-04', 40],
          ['2020-02-05', 43]
        ]
      }],

      customersSeries: [{
        name: 'Customers',
        data: [
          ['2020-02-02', 13],
          ['2020-02-03', 11],
          ['2020-02-04', 13],
          ['2020-02-05', 12]
        ]
      }]
    }
  },
  computed: {
    ...mapState('dictation-app', ['role']),
    theme() {
      return this.$vuetify.theme.isDark
        ? this.$vuetify.theme.defaults.dark
        : this.$vuetify.theme.defaults.light
    }
  },
  mounted() {
    let count = 0

    // DEMO delay for loading graphics
    this.loadingInterval = setInterval(() => {
      this[`isLoading${count++}`] = false
      if (count === 4) this.clear()
    }, 400)
  },
  beforeDestroy() {
    this.clear()
  },
  methods: {
    clear() {
      clearInterval(this.loadingInterval)
    }
  }
}
</script>
