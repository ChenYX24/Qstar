<template>
	<view class="Block">
		<view class="leftBox">
			<view class="title">
				{{title}}
			</view>
			<view class="pushBox">
				<view class="dot"></view>
				<view class="push">{{pushText}}</view>
			</view>
		</view>
		<view class="rightBox">
			<view class="number">
				{{number}}
			</view>
			<view class="text">
				{{text}}
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
			number:{
				type:Number,
				default:1
			},
			text:{
				type:String,
				default:''
			}
		},
		data() {
			return {
				pushText:"已发布"
			};
		}
	}
</script>

<style lang="less">
.Block{
    border-radius: 20px;
    background: rgba(255, 255, 255, 0.8);
    box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
    display: flex;
    flex-direction: row;
    align-items: center;
    width: 80vw;
    height: 35vw;
    justify-content: space-between;
}
.leftBox{
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: flex-start;
	height: 40%;
	.title{
		font-size: 20px;
		font-weight: 400;
	}
}
.rightBox{
	display: flex;
	flex-direction: column;
	align-items: center;
	height: 40%;
	.number{
		font-size: 54pt;
		font-weight: 900;
		color: rgba(204, 139, 247, 0.25);
	}
	.text{
		font-size: 12px;
		font-weight: 400;
		color: rgba(187, 187, 199, 1);
	}
}
</style>