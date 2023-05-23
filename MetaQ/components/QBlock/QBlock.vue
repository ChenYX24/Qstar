<template>
	<view class="Block">
		<view class="topBox">
			<view class="title">
				{{title}}
			</view>
		   <view class="number" :style="{fontSize:fontSize}">
			{{number}}
		   </view>
		</view>
		<view class="bottomBox">
		   <view class="pushBox">
			<view :class="{ 'active-dot': isPush, 'dot': !isPush }"></view>
			<view class="push">{{pushText}}</view>
		   </view>
		   <view class="text">
			答卷数量
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
		   onumber:{
		    type:Number,
		    default:1
		   },
		   isPush:{
		    type:Boolean,
		    default:false
		   }
		  },
		data() {
			return {
				pushText:"已发布",
			};
		},
		computed: {
		      number(){
		          let len = this.onumber.toString().length;
		          if(len == 4) {
		              return Math.floor(this.onumber / 1000)+'K'
		          } else if(len == 5) {
		              return Math.floor(this.onumber / 10000)+'W'
		          } else if(len>5)
		    {
		     return '9W+'
		    }
		    else {
		              return this.onumber
		          }
		      },
		      fontSize() {
		          let len = this.number.toString().length;
		          if (len <= 1) {
		              return '40pt';
		          } else if (len == 2) {
		              return '30pt';
		          } else if (len >= 3) {
		              return '24pt';
		          }
		      }
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
    height: 35vw;
	min-height: 35vw;
    justify-content: space-between;
    box-sizing: border-box;
    padding: 3% 5% 8% 3%;
    align-items: stretch;
}
.topBox{
	display: flex;
	justify-content: space-between;
	align-items: center;
	align-content: center;
	flex-direction: row;
	height: 70%;
	width: 100%;
	.title{
		font-size: 20px;
		font-weight: 400;
		width:80%;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.number{
		font-weight: 900;
		color: rgba(204, 139, 247, 0.36);
		width: 20%;
		text-align: center;
	}
}
.bottomBox{
	display: flex;
	justify-content: space-between;
	align-items: center;
	align-content: center;
	flex-direction: row;
	.pushBox{
		display: flex;
		flex-direction: row;
		align-items: center;
		.dot{
			border-radius: 50%;
			width: 12px;
			height: 12px;
			opacity: 1;
			background: rgba(187, 187, 199, 1);
			filter: blur(0.6px);
			position: relative;
		}
		.active-dot{
			border-radius: 50%;
			width: 12px;
			height: 12px;
			opacity: 1;
			background: rgba(221, 183, 247, 1);
			filter: blur(0.6px);
			position: relative;
		}
		.push{
			color: rgba(187, 187, 199, 1);
			margin-left: 5px;
		}
	}
	.text{
		font-size: 12px;
		font-weight: 400;
		color: rgba(187, 187, 199, 1);
		width: 20%;
		text-align: center;
	}
}
</style>