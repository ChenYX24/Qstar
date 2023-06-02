<template>
	<view class="Title">
		<view class="box">
			<view class="group">
				<view class="title">
					<view class="title-text">
						{{num}}
					</view>
					<view class="tiankong-label">
						[填空题]
					</view>
				</view>
				<view class="textarea-box">
					<textarea class="content" maxlength="900" v-model="question" placeholder="请输入问题">
					</textarea>
				</view>
		</view>
	</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// question:''
				question:this.questionProp
			};
		},
		props:{
			num:{
				type:Number,
				default:100
			},
			questionProp:{
				type:String,
				default:'333'
				}
				
			}
		}

	
</script>

<style lang="less">

	
	.Title{
		// border: 2px solid red;
		// background-color: rgba(255, 255, 255,1);
		height: 18vh;
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-bottom: 30px;
	}
	
	

	.box{
		display: flex;
		flex-direction: column;
		background-color: rgba(255, 255, 255, 0.8);
		border-radius: 10px;
		width: 85vw;
		border: 2px solid rgba(225, 225, 235, 1);
	} 
	.group{
		margin: 10px 20px 20px 20px;
	}
	
	.title{
		display: flex;
		margin-bottom: 5px;
	}
	.title-text{
		width: 80vw;
		font-size:18px;   
		color:#991cff;
	}
	.tiankong-label{
		margin-left: 2px;
		display: flex;
		align-items: center;
		color: rgb(237 64 225);
		font-size: 14px;
		flex-shrink: 0;
	}
	
	
	
	
	.textarea-box{
		display: flex;
		justify-content: center;
	}
	.content{
		height: 10vh;
		background: rgba(255, 255, 255, 0.8);
		border: 1px solid rgba(225, 225, 235, 1);
		
	}
</style>