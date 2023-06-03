<template>  
  <view v-if="Type=='blank'">
	  	<view class="table-row first">
	  	  <text class="table-cell">id</text>  
	  	  <text class="table-cell">内容</text>   
	  	</view>  
	  	<view class="table-row" v-for="(item, index) in Data" :key="index">  
	  	  <text class="table-cell">{{ index+1 }}</text>  
	  	  <text class="table-cell">{{ item.name }}</text>  
	  	</view>  
  </view>
  <view v-else-if="Type=='slider'">
  	  	<view class="table-row first">
  	  	  <text class="table-cell">id</text>  
  	  	  <text class="table-cell">数值</text>   
  	  	</view>  
  	  	<view class="table-row" v-for="(item, index) in Data" :key="index">  
  	  	  <text class="table-cell">{{ index+1 }}</text>  
  	  	  <text class="table-cell">{{ item.name }}</text>  
  	  	</view>  
  </view>
  <view v-else>
	  
    <view class="table-row first">  
      <text class="table-cell">选项</text>  
      <text class="table-cell">比例</text>  
      <text class="table-cell">数量</text>  
    </view>  
    <view class="table-row" v-for="(item, index) in Data" :key="index">  
      <text class="table-cell">{{ item.name }}</text>  
      <text class="table-cell">{{ calcPercentage(item.value) }}</text>  
      <text class="table-cell">{{ item.value }}</text>  
    </view>  
    <view class="table-row last">  
      <text class="table-cell">总数</text>  
      <text class="table-cell"></text>  
      <text class="table-cell">{{ totalValue }}</text>  
    </view>  
  </view>  
</template>  
  
<script>  
export default {  
	props:{
		Data:{
			type: Array,  
			default: [] 
	  },
	  Type:{
		  type:String,
		  default:''
	  }
	},
  data() {  
    return {  
    };  
  },  
  computed: {  
    totalValue() {  
      return this.Data.reduce((acc, item) => acc + item.value, 0);  
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
  color: rgba(144, 144, 153, 1);
  align-items: center;

}
.first,.last{
	background: rgba(240, 244, 253, 1);
}
.table-cell {  
  flex: 1;  
  text-align: center;  
  color: rgba(144, 144, 153, 1);
	
}  
</style>  
