<template>
  <v-card class="d-flex flex-column flex-grow-1">
    <!-- information -->
    <div>
      <v-card-title>
        <div>1학년 2반 받아쓰기 공부 시간</div>
      </v-card-title>
      
      <div v-if="showChart">
        <apexchart 
          type="area" 
          height="350" 
          :options="chartOptions" 
          :series="series"
        />
      </div>
      
    </div>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      showChart: false,
      series: [{
        name: '저번주',
        data: [31, 40, 28, 51, 42]
      }, {
        name: '이번주',
        data: [22, 32, 45, 32, 34]
      }],
      chartOptions: {
        chart: {
          height: 350,
          type: 'area'
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: 'smooth'
        },
        xaxis: {
          categories: ['월', '화', '수', '목', '금', '토', '일']
        }
      }
    }
  },
  mounted() {
    this.showChart = true
  }
}
</script>
