<template>  
  <view class="container">  
    <text class="title">当前计数：{{ count }}</text>  
    <button @click="increment">增加</button>  
    <button @click="decrement">减少</button>  
    <button @click="incrementAsync">异步增加</button>  
    <text class="title">双倍计数：{{ doubleCount }}</text>  
  </view>  
</template>  
  
<script>  
export default {  
  computed: {  
    count() {  
      return this.$store.state.count;  
    },  
    doubleCount() {  
      return this.$store.getters.doubleCount;  
    },  
  },  
  methods: {  
    increment() {  
      this.$store.commit('increment');  
    },  
    decrement() {  
      this.$store.commit('decrement');  
    },  
    incrementAsync() {  
      this.$store.dispatch('incrementAsync');  
    },  
  },  
};  
</script>  
  
<style scoped>  
.title {  
  font-size: 32rpx;  
  text-align: center;  
  margin-bottom: 16rpx;  
}  
  
button {  
  display: block;  
  width: 300rpx;  
  height: 60rpx;  
  line-height: 60rpx;  
  font-size: 30rpx;  
  text-align: center;  
  margin: 0 auto 16rpx auto;  
  background-color: #007aff;  
  color: white;  
}  
</style>  
