<template>
	<view class="background">
		<view>
			<Title></Title>
			
			<!-- <SingleChoice></SingleChoice> -->
			<view class="" v-if="content.typeNum == 0">
			<SingleChoice></SingleChoice>
			</view>
			
			<view class="" v-else-if="content.typeNum == 1">
			<SingleChoice></SingleChoice>
			</view>
			
			<view class="" v-else-if="content.typeNum == 2">
			<!-- <SingleChoice></SingleChoice> -->
			</view>
			
			<view class="" v-else-if="content.typeNum == 3">
			<!-- <SingleChoice></SingleChoice> -->
			</view>
			
			<setting></setting>
			
			
			<view class="ok_or_no">
				<view class="button_ok_or_no">
					确认
				</view>
				<view class="button_ok_or_no">
					返回
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import Title from '/components/title/title.vue';
	import SingleChoice from "/components/danxuan/danxuan.vue"
	import setting from "/components/setting/setting.vue"
	
	export default {
		components:{
			// danxuansetting,
			Title,
			SingleChoice,
			setting
		},
		// onLoad(options) {
		//   this.content.typeNum=options.typenum
		//   console.log(this.content.typeNum)
		// },
		data() {
			return {
				content:{
					type:Object,
					default:{
						title:"",
						typeNum:null,
						choice:[],
					}
					
				}
			};
		},
		
	}
</script>



<style lang="less">
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

.ok_or_no{
	margin-top: 5vh;
	width: 100vw;
	display: flex;
	flex-direction: column;
	align-items: center;
}
.button_ok_or_no{
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	width: 80vw;
	height: 5vh;
	border-radius: 10px;
	border: 0.5px solid black;
}
// 确认按钮的设置
.button_ok_or_no:first-child{
	margin-bottom: 2vh;
	background:linear-gradient(90deg,#b3aefd 0%,rgba(255,235,244,0.75)100%);
	box-shadow: 0px 2px 4px 0px rgba(136,63,143,0.25);
}

.button_ok_or_no:last-child{
	background-color: aquamarine;
	background-color: rgba(230,230,240,1);
}

</style>
