<template>  
  <view class="echarts" @click="clickChart">  
    <canvas :id="'echarts-' + chartId" :style="'width:' + width + 'px;height:' + height + 'px;'"></canvas>  
  </view>  
</template>  
  
<script>  

  
export default {  
  props: {  
    width: {  
      type: Number,  
      default: 375  
    },  
    height: {  
      type: Number,  
      default: 375  
    },  
    options: {  
      type: Object,  
      default: () => {}  
    }  
  },  
  data() {  
    return {  
      chartId: new Date().getTime(),  
      chartInstance: null  
    };  
  },  
 mounted() {  
     this.initChart();  
   },  
   methods: {  
     initChart() {  
       const element = document.getElementById('echarts-' + this.chartId);  
       this.chartInstance = window.echarts.init(element);  
       this.chartInstance.setOption(this.options);  
     }, 
    clickChart() {  
      this.$emit('click-chart');  
    }  
  },  
  watch: {  
    options: {  
      deep: true,  
      handler(newVal) {  
        this.chartInstance && this.chartInstance.setOption(newVal);  
      }  
    }  
  }  
};  
</script>  
  
<style scoped>  
.echarts {  
  position: relative;  
}  
</style>  
