<template>  
  <view class="contentBox">  
    <view class="select" @tap="toggleSelect">  
      <image :src="selected[0] ? '/static/setting/selected.png' : '/static/setting/noSelected.png'" class="icon" />  
      <view class="text">记录用户昵称</view>  
    </view>  
    <view class="select" @tap="toggleSelect(1)">  
      <image :src="selected[1] ? '/static/setting/selected.png' : '/static/setting/noSelected.png'" class="icon" />  
      <view class="text">每个用户只能回答一次</view>  
    </view>  
    <view class="select" @tap="toggleSelect(2)">  
      <image :src="selected[2] ? '/static/setting/selected.png' : '/static/setting/noSelected.png'" class="icon" />  
      <view class="text">积分奖励（待开发）</view>  
    </view>  
  </view>  
</template>  
  
<script>  
export default {  
  data() {  
    return {  
      selected: [false, false, false],  
    };  
  },  
  methods: {  
    toggleSelect(index) {  
      this.selected.splice(index, 1, !this.selected[index]);  
    },  
  },  
};  
</script>  
  
<style>  
.contentBox {  
  display: flex;  
  flex-direction: column;  
  width: 100%;  
}  
.select {  
  display: flex;  
  align-items: center;  
  margin-bottom: 10px;  
}  
.text {  
  margin-left: 10px;  
}  
</style>  
