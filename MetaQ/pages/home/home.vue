<template>
	<view class="background">
		222
		<tab-bar :activeTab="tab"></tab-bar>
	</view>
</template>

<script>
import TabBar from '/components/tabbar/tabbar.vue';
export default {
  components: {
    TabBar
  },
  onLoad: function (options) {
	this.tab=options.tab
  },
  data() {
  	return {
  		tab: '',
  	}
  },

};
</script>

<style scoped lang="less">
.background{
	width: 100vw;
	height: 100vh;
	position: relative;
	top: 0;
	left:0;
	background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
}
</style>