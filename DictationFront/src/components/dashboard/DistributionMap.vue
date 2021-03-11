<template>
  <v-card class="d-flex flex-column flex-grow-1">
    <!-- information -->
    <div>
      <v-card-title>나의 강점</v-card-title>

      <e-charts
        ref="radar"
        style="width: 100%"
        autoresize
        :options="radar"
        auto-resize
      />
      
    </div>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      radar:{
        tooltip: {},
        radar: {
          indicator: [
            { name: '집중력', max: 20 },
            { name: '듣기', max: 20 },
            { name: '언어 쓰기', max: 20 },
            { name: '성적', max: 20 }
          ]
        },
        series: [{
          name: '이강인',
          type: 'radar',
          data: [{ value: [13,16,10,5] }]
        }]
      }
    }
  }
}
</script>
