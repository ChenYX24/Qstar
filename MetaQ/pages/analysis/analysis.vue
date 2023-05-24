<template>
<view class="background" :class="{'edit':currentTab}">
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
					<view class="editContainer">
						<view class="editBox" :class="{'active':isCopy}" @tap="switchCopy">
							<image :src="isCopy?'/static/analysis/copy.png':'/static/analysis/copy1.png'" mode="aspectFit"></image>
							<view class="editRight">
								<view class="rT">
									复制-编辑
								</view>
								<view class="rB">
									原始问卷及答卷不受影响
									修改不受限制
								</view>
							</view>
						</view>
						<view class="editBox" :class="{'active':isSave}" @tap="switchSave">
							<image :src="isSave?'/static/analysis/save.png':'/static/analysis/save1.png'" mode="aspectFit"></image>
							<view class="editRight">
								<view class="rT">
									保留-编辑
								</view>
								<view class="rB">
									直接更改原始问卷内容
									修改受限制
								</view>
							</view>
						</view>
						<view class="editBox" :class="{'active':isDelete}" @tap="switchDelete">
							<image :src="isDelete?'/static/analysis/delete.png':'/static/analysis/delete1.png'" mode="aspectFit"></image>
							<view class="editRight">
								<view class="rT">
									删除-编辑
								</view>
								<view class="rB">
									删除原始问卷与答卷
									修改不受限制
								</view>
							</view>
						</view>
						<view class="next" :class="{'active':isDelete||isSave||isCopy}">
							下一步
						</view>
					</view>
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
				isCopy:false,
				isSave:false,
				isDelete:false
			};
		},
		methods: {
		  swiperChange(e) {
		    this.currentTab = e.detail.current;
		  },
		  handleTabChange(index) {
			this.currentTab = index;
		  },
		  switchCopy(){
		  	this.isCopy=!this.isCopy
		  	this.isSave=false
		  	this.isDelete=false
		  },
		  switchSave(){
			this.isCopy=false
			this.isSave=!this.isSave
			this.isDelete=false
		  },
		  switchDelete(){
			this.isCopy=false
			this.isSave=false
			this.isDelete=!this.isDelete  			
		  }
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
	.edit{
		background: linear-gradient(225deg, rgba(230, 247, 255, 1) 0%, rgba(255, 254, 247, 1) 38.89%, rgba(233, 229, 254, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
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
	.editContainer{
		display: flex;
		flex-direction: column;
		align-items: center;
		gap: 7%;
		height: 100vh;
		margin-top: 5%;
	}
	.editBox{
		width: 80vw;
		height: 14vh;
		padding: 5%;
		box-sizing: border-box;
		border-radius: 20px;
		background: rgba(255, 255, 255, 0.8);
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		image{
			width: 20%;
			height: 60%;
			margin-right: 5%;
		}
		.editRight{
			display: flex;
			flex-direction: column;
			align-items: flex-start;
			justify-content: space-between;
			height: 85%;
			.rT{
				font-size: 14pt;
				color: rgba(0, 0, 0, 1);
			}
			.rB{
				font-size: 12pt;
				color: rgba(187, 187, 199, 1);
			}
		}
	}
	.editBox.active{
		border: 2px solid rgba(221, 183, 247, 1);
	}
	.next{
		width: 80vw;
		height: 10vh;
		display: flex;
		align-items: center;
		text-align: center;
		flex-direction: row;
		justify-content: center;
		border-radius: 12px;
		box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
		font-size: 16pt;
		background: rgba(230, 230, 240, 1);
		color: rgba(128, 128, 128, 1);
		margin-top: 8%;
	}
	.next.active{
		background: linear-gradient(90deg, rgba(255, 255, 255, 0.8) 0%, rgba(255, 247, 255, 0.8) 100%);
		color: rgba(179, 157, 194, 1);
	}
</style>
