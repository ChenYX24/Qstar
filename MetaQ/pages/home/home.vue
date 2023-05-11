<template>
	<view class="background" :class="{ 'background-blur': isExpanded }" @click="confirmInput($event)">
		<view class="title">
			我的
		</view>
		<view class="img">
			<image :src="profilePhoto" mode="aspectFit"></image>
		</view>
		<input type="text" id="name" :class="{ 'name': true, 'expanded': isExpanded }" v-model="name" @click="expandInput">
		<view class="infoBox">
			<view class="info">
			  <view class="item">
				<view class="label">手机：</view>
				<view class="value">{{phone}}</view>
			  </view>
			  <view class="item">
				<view class="label">邮箱：</view>
				<view class="value">{{email}}</view>
			  </view>
			  <view class="item">
				<view class="label">地区：</view>
				<view class="value">{{location}}</view>
			  </view>
			</view>
			<image class="icon" src="/static/home/edit.png" mode="aspectFit"></image>
		</view>
		<view class="button">
			切换账号
		</view>
	</view>
	<view class="tabbar" @click="confirmInput($event)">
		<tab-bar :tab="tab" :isExpanded="isExpanded"></tab-bar>
	</view>
</template>

<script>
import TabBar from '/components/tabbar/tabbar.vue';
export default {
  inheritAttrs:false,
  components: {
    TabBar,
  },
  onLoad: function (options) {
	this.tab=options.tab
  },
  data() {
  	return {
  		tab: '',
		profilePhoto:'/static/home/profilePhoto.png',
		name:'尘',
		isExpanded:false,
		flag:false,
		phone: "xxx",
        email: "xxx",
        location: "xxx",
  	}
  },
  methods: {
      expandInput() {
        this.isExpanded = true;
      },
      confirmInput(event) {
        // 恢复样式
		if(this.isExpanded)
		{
			if(this.flag)
			{
				if (event.target.id != 'name') {
				    this.isExpanded = false;
					this.flag=false;
				}
			}
			else
			{
				this.flag=true
			}
		}
      },
	}

};
</script>

<style scoped lang="less">


.background{
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100vw;
	height: calc(100vh - 15vh);
	position: relative;
	top: 0;
	left:0;
	background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
	.title{
		font-size: 16pt;
		font-weight: 400;
		letter-spacing: 0px;
		line-height: 32.93px;
		margin-top: 5vw;
		margin-bottom: 3vw;
	}
}
.img{
	width: 35vw;
	height: 35vw;
	border: 2px solid rgba(221, 183, 247, 1);
	border-radius: 50%;
    overflow: hidden;
	image{
		width: 100%;
		height: 100%;
	}
}

.name.expanded {
  width: 80vw;
  height: 7vh;
  transform: scale(1.2);
  background-color: rgba(255,255,255,1);
}

.name{
	width: 80vw;
	height: 7vh;
	border-radius: 20px;
	background: rgba(255, 255, 255, 0.45);
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	text-align: center;
	font-size: 18pt;
	font-weight: 400;
	color: rgba(0, 0, 0, 1);
	margin: 3vw;
	transition: all 0.3s ease;
}
.background-blur>*:not(.name):not(.confirm){
	filter: blur(5px);
}

.confirm{
	width: 80vw;
	height: 10vw;
	text-align: center;
}

.infoBox {
	display: flex;
	align-items: flex-start;
	box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.1);
	width: 80vw;
	height: 13vh;
	flex-direction: column;
	border-radius: 10px;
	background: rgba(255, 255, 255, 0.45);
	position: relative;
	    padding-left: 5%;
	    box-sizing: border-box;

}

.info {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-start;
	flex: 1;
}

.item {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
}

.label {
	font-size: 14pt;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 24.7px;
	color: rgba(187, 187, 199, 1);
}
.value{
	font-size: 14pt;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 24.7px;
	color: rgba(187, 187, 199, 1);
}
.icon {
    width: 8vw;
    height: 8vw;
    position: absolute;
    bottom: 10%;
	right: 2%;
}
.button{
	border-radius: 12px;
	background: rgba(224, 224, 224, 0.25);
	box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
	font-size: 18pt;
	font-weight: 400;
	color: rgba(179, 157, 194, 1);
	text-align: center;
	width: 80vw;
	height: 7vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	position: absolute;
	bottom: 10%;
	
}
</style>