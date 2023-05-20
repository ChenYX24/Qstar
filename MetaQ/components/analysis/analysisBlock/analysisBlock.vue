<template>
	<view class="Block">
		<view class="topBox">
			<view class="title">
				{{title}}

			</view>
			<view class="type">
				[{{CType}}]
			</view>

		</view>
		  <swiper
			class="middleBox"
			@change="onSwiperChange"
			:current="currentSwiper"
		  >
		  	<swiper-item class="chart" v-for="(item,index) in SData" :key="index">{{SData[index]}}</swiper-item>
		  </swiper>
		  <view class="bottomBox" >
				<view v-for="(item,index) in SData" :key="index" @tap="onBottomItemClick(index)">
					{{SData[index]}}
				</view>
		  </view>
	</view>
</template>

<script>
	export default {
		name:"QBlock",
		props: {
			title: {
				type: String,
				default:''
			},
			type:{
				type:String,
				default:''
			},
		},
		
		data() {
			return {
				pushText:"已发布",
				CType:'',
				SData:["列表","饼状","环状","柱状","条形","折线"],
				displayText: "列表",
				currentSwiper: 0,
			};
		},
		mounted() {
			switch (this.type){
				case 'single':
					this.CType="单选题"
					break;
				case 'multiple':
					this.CType="多选题"
					break;
				case 'blank':
					this.CType="填空题"
					break;
				default:
					break;
			}
		},
		computed: {
		    end(){
		        if(this.isEnd)
				{
					return '已截止'
				}
				else{
					return '修改'
				}
		    },
		},
		methods:{
			onSwiperChange(e) {
			    this.currentSwiper=e.detail.current 
			  },
			
			  onBottomItemClick(index) {
				  console.log(index)
			    this.currentSwiper=index
			  },
		}
	}
</script>

<style lang="less">
.Block{
    border-radius: 20px;
    background: rgba(255, 255, 255, 0.8);
    box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
    display: flex;
    flex-direction: column;
    width: 80vw;
	min-height: 30vh;
    justify-content: space-between;
    box-sizing: border-box;
    padding: 3% 5% 8% 3%;
    align-items: stretch;
}
.topBox{
	display: flex;
	justify-content: flex-start;
	align-items: center;
	align-content: center;
	flex-direction: row;
	height: fit-content;
	width: 100%;
	.title{
		font-size: 20px;
		font-weight: 400;
		color: black;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.type{
		font-size: 11pt;
		font-weight: 400;
		color: #bbbbc7;
		white-space: nowrap;
		display: inline-block;
		margin-left: 3%;
	}
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.middleBox {
  width: 100%;
  height: 100%;
  margin-top: 5%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.displayText {
  font-size: 24px;
  font-weight: bold;
}

.bottomBox {
      width: 100%;
	  height: fit-content;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      align-content: center;
}

.bottomItem {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 18px;
  
}

.ellipse {
  border-radius: 999px;
  background: rgba(248, 241, 246, 1);
  padding: 2px 4px;
  margin-left: 4px;
}
</style>