<template>
	<view class="Title">
		<view class="box">
		<view class="title">
			<view class="xing">*</view>
			<view class="title-text">
				标题
			</view>
		</view>
		<textarea class="content" maxlength="900" v-model="content.title" placeholder="请输入标题">
		
		</textarea>
	</view>
	</view>
</template>

<script>
	export default {
		name:"Title",
		props:{
			content:{
				type:Object,
				default:{
					title:"3333",
					type:'blank',
					choice:[],
				}
				
			}
		},
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="less">
	.content{
		// background-color: rgba(255, 255, 255,1);
		height: 10vh;
		border: 2px solid red;
		width: 86vw;
	}
	
	.Title{
		// border: 2px solid red;
		// background-color: rgba(255, 255, 255,1);
		height: 18vh;
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.title{
		// width: 100vw;
		display: flex;
	}
	.xing{
		color: red;
		// margin-left: 10px;
	}
	.title-text{
		color:red;
		opacity: 0.5;
		margin-left: 3px;
	}
// .box{
// 	// border: 2px solid green;
// }
	
</style>