<template>
	<view class="Block">
		<view class="topBox">
			<view class="title">
				{{title}}
			</view>
			<view class="check">
				查看
			</view>
		</view>
		<view class="bottomBox">
			<view class="endBox">
				<view :class="{ 'active-dot': !isEnd, 'dot': isEnd }"></view>
				<view class="name">{{name}}</view>
			</view>
			<view :class="{ 'end': isEnd, 'noEnd': !isEnd }">
				{{end}}
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
			isEnd:{
				type:Boolean,
				default:true
			},
			name:{
				type:String,
				default:''
			}
		},
		data() {
			return {
				pushText:"已发布",
			};
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
	.check{
		font-size: 20pt;
		font-weight: 900;
		color: rgba(204, 139, 247, 0.36);
	}
}
.bottomBox{
	display: flex;
	justify-content: space-between;
	align-items: center;
	align-content: center;
	flex-direction: row;
	.endBox{
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
		.name{
			color: rgba(187, 187, 199, 1);
			margin-left: 5px;
		}
	}
	.end{
		font-size: 12px;
		font-weight: 400;
		color: rgba(187, 187, 199, 1);
		width: 20%;
		text-align: center;
	}
	.noEnd{
		font-size: 12px;
		font-weight: 400;
		color: rgba(187, 187, 199, 1);
		background: rgba(248, 241, 246, 1);
		width: 20%;
		border-radius: 999px;
		text-align: center;

	}
}
</style>