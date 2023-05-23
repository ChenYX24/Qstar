<template>  
  <view>  
    <view class="table-row first">  
      <text class="table-cell">选项</text>  
      <text class="table-cell">比例</text>  
      <text class="table-cell">数量</text>  
    </view>  
    <view class="table-row" v-for="(item, index) in pieData" :key="index">  
      <text class="table-cell">{{ item.name }}</text>  
      <text class="table-cell">{{ calcPercentage(item.value) }}</text>  
      <text class="table-cell">{{ item.value }}</text>  
    </view>  
    <view class="table-row last">  
      <text class="table-cell">总人数</text>  
      <text class="table-cell"></text>  
      <text class="table-cell">{{ totalValue }}</text>  
    </view>  
  </view>  
</template>  
  
<script>  
export default {  
  data() {  
    return {  
      pieData: [  
        { value: 1048, name: 'Search Engine'},  
        { value: 735, name: 'Direct'},  
        { value: 580, name: 'Email'},  
        { value: 484, name: 'Union Ads'},  
        { value: 300, name: 'Video Ads'},  
        { value: 300, name: 'Vdeo'},  
        { value: 300, name: 'Vieo'},  
        { value: 300, name: 'Vido'},  
        { value: 300, name: 'Vide'},  
      ],  
    };  
  },  
  computed: {  
    totalValue() {  
      return this.pieData.reduce((acc, item) => acc + item.value, 0);  
    },  
  },  
  methods: {  
    calcPercentage(value) {  
      return ((value / this.totalValue) * 100).toFixed(2) + '%';  
    },  
  },  
};  
</script>  
  
<style>  
.table-row {  
  display: flex;  
  justify-content: space-between;  
  padding: 10px 0;  
  border-bottom: 1px solid #eee;  
  background: rgba(250, 251, 255, 1);
}
.first,.last{
	background: rgba(240, 244, 253, 1);
}
.table-cell {  
  flex: 1;  
  text-align: center;  
}  
</style>  
