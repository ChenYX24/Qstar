<template>
	<view class="background">
		<view class="title">
			我的问卷
		</view>
		<view class="inputBox">
			<image src="/static/inputSearch/search.png" mode="aspectFill"></image>
			<input type="text" placeholder="输入关键词" class="input" placeholder-style="color: rgba(187, 187, 199, 1);;" v-model="inputValue"> <!--v-model是双向绑定，将文本框的值与inputvalue绑定-->
		</view>
		<view class="container">
			<tab-swiper class="tabSwiper" @tab-change="handleTabChange" :current-tab="currentTab" :text1="text1" :text2="text2"/>
		    <swiper
		      :current="currentTab"
		      @change="swiperChange"
		      :duration="300"
		      class="swiper"
		    >
		      <swiper-item>
		        <view class="page1">
					<QBlock :number="number" :text="text" :title="title"></QBlock>
				</view>
		      </swiper-item>
		      <swiper-item>
		        <view class="page2">页面2</view>
		      </swiper-item>
		    </swiper>
		  </view>
		  
		<tab-bar :activeTab="tab"></tab-bar>
	</view>
</template>

<script>
import TabBar from '/components/tabbar/tabbar.vue';
import TabSwiper from "/components/tabSwiper/tabSwiper.vue";
import QBlock from '/components/QBlock/QBlock.vue';
export default {
  components: {
    TabBar,
	TabSwiper,
	QBlock
  },
  onLoad: function (options) {
	this.tab=options.tab
  },
  data() {
  	return {
  		tab: '',
		inputValue: '',
		currentTab: 0,
		text1:'我创建的',
		text2:"我填写的",
		title:"关于早八是否会被饿死调查",
		text:"答卷数量"
		
  	}
  },
    methods: {
      swiperChange(e) {
        this.currentTab = e.detail.current;
      },
	  handleTabChange(index) {
		this.currentTab = index;
	  }
    }

};
</script>

<style scoped lang="less">
.background{
	.title{
		font-size: 16pt;
		font-weight: 400;
		letter-spacing: 0px;
		line-height: 32.93px;
		margin-top: 5vw;
		margin-bottom: 3vw;
	}
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100vw;
	height: 100vh;
	position: relative;
	top: 0;
	left:0;
	background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
}
	.inputBox{
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-content: center;
		align-items: center;
		width: 90vw;
		height: 7vh;
		border-radius: 10px;
		background: rgba(255, 255, 255, 0.45);
		margin-top: 2vw;
		margin-bottom: 3vw;
		.input{
			width: 80%;
			height: 80%;
			color: rgba(187, 187, 199, 1);
		}

		image{
			width: 2rem;
			height: 2rem;
			z-index: 99;
			margin-right: 3%;
		}
	}
	.container {
	  height: 100%;
	  position: relative;
	  width: 100vw;
	}
	
	.swiper {
	  height: calc(100% - 50px);
	  position: relative;
	  bottom: -50px;
	}
	
	.page1,
	.page2 {
	  height: 100%;
	  width: 100vw;
	  display: flex;
	  justify-content: center;
	  align-items: center;
	}
	
	.tabSwiper {
	  position: absolute;
	  top: 0;
	  left: 0;
	  right: 0;
	  margin-top: 2vw;
	  margin-bottom: 3vw;
	}
</style>