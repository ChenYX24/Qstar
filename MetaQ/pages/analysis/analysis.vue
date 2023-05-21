<template>
<view class="background">
		<view class="title">
			{{title}}
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
					<AnalysisBlock v-for="(block, index) in blocks" :key="block.id" :title="block.title" :type="block.type"></AnalysisBlock>
				</view>
		      </swiper-item>
		      <swiper-item>
				<view class="page2">
					222
				</view>
		      </swiper-item>
		    </swiper>
		  </view>
	</view>
</template>

<script>
import TabSwiper from "/components/tabSwiper/tabSwiper.vue";
import AnalysisBlock from "/components/analysis/analysisBlock/analysisBlock.vue"
	export default {
		components: {
			TabSwiper,
			AnalysisBlock
		},
		data() {
			return {
				title:'关于c10居住学生学校住宿感受的的调研',
				currentTab: 0,
				text1:'分析',
				text2:"编辑",
				blocks:[
					{ id: 1, title: '1.你住在几楼',type:'single'},
				  { id: 2, title: '2.你住在几楼', type:'multiple'},
				  { id: 3, title: '3.你住在几楼', type:'blank'},
				],
			};
		},
		methods: {
		  swiperChange(e) {
		    this.currentTab = e.detail.current;
		  },
		  handleTabChange(index) {
			this.currentTab = index;
		  },
		}
	}
</script>

<style lang="less">
.background{
	.title{
		font-size: 16pt;
		font-weight: 400;
		letter-spacing: 0px;
		line-height: 32.93px;
		margin-top: 10vw;
		margin-bottom: 3vw;
		max-width: 70vw;
		text-align: center;
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
	.container {
	  height: 100%;
	  position: relative;
	  width: 100vw;
	}
	
	.swiper {
    height: calc(100% - 100px);
    position: relative;
    bottom: -65px;
	}
	
	.page1,
	.page2 {
	  height: 100%;
	  width: 100vw;
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	  gap: 3%;
	  overflow-y: scroll;
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
