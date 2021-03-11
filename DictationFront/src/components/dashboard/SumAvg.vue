<template>
  <v-card class="d-flex flex-column flex-grow-1">
    <!-- information -->
    <div>
      <v-card-title>
        <div>1학년 2반 받아쓰기 평균 점수</div>
      </v-card-title>
      <div v-if="showChart">
        <apexchart 
          type="line" 
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
        name: '점수',
        data: [10, 40, 30, 50, 70, 60, 60]
      }],
      chartOptions: {
        chart: {
          height: 350,
          type: 'line',
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: 'smooth'
        },

        grid: {
          row: {
            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
            opacity: 0.5
          }
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
