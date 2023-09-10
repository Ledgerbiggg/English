<template>
  <div style="width: 100%; height: 600px;">
    <div ref="echarts" style="height: 600px;width: 900px"/>
  </div>
</template>
<script>


import http from "@/js/http";

export default {
  name: "EChartsView",
  components: {},
  data() {
    return {
      Timer: null,
      dateList: [],
      currentCount: [],
      totalDuration: [],
    };
  },
  mounted() {
    this.initView();
    this.Timer = setInterval(() => {
      this.countTime();
    }, 1000)
    this.getRecentDays();
  },
  methods: {
    getRecentDays() {
      http.get('/time/getRecentTime', {days: 7}).then(res => {
        console.log("getRecentTime", res.data.data)
        for (let i = 0; i < res.data.data.length; i++) {
          this.dateList.push(res.data.data[i]['date']);
          this.currentCount.push(res.data.data[i]['countTime']);
          let count = 0;
          for (let j = 0; j <= i; j++) {
            count += res.data.data[j]['countTime']
          }
          this.totalDuration.push(count);
        }
        console.log("this.currentCount", this.currentCount)
        console.log("this.totalDuration", this.totalDuration)
        this.initView()
      })
    },
    countTime() {
      http.post('/time/postTimeToCount').then(res => {
      })
    },
    getChartOptions() {
      return {
        title: {
          text: '最近七天的背英语单词的情况'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {},
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.dateList
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} min'
          }
        },
        series: [
          {
            name: '累计时长(min)',
            type: 'line',
            data: this.totalDuration,
            markPoint: {
              data: [
                {type: 'max', name: 'Max'},
              ],
            },
          },
          {
            name: '当天时长(min)',
            type: 'line',
            data: this.currentCount,
            markPoint: {
              data: [
                // {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5},
                {type: 'max', name: 'Max'},
                {type: 'min', name: 'Min'}
              ],
            },
            markLine: {
              data: [
                {type: 'average', name: 'Avg'},
                [
                  {
                    symbol: 'none',
                    x: '90%',
                    yAxis: 'max'
                  },
                  {
                    symbol: 'circle',
                    label: {
                      position: 'start',
                      formatter: 'Max'
                    },
                    type: 'max',
                    name: '最高点'
                  }
                ]
              ]
            }
          }
        ]
      }
    },
    initView() {
      this.$nextTick(() => {
        const chartDom = this.$refs.echarts;

        // 初始化ECharts图表
        const chart = this.$echarts.init(chartDom);

        // 设置图表配置
        chart.setOption(this.getChartOptions());
      })

    }
  }
}
</script>


<style scoped>

</style>