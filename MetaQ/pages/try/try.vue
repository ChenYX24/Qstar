<template>
	<view class="background">
		<view>
			<Title ref="title" :content="content"></Title>
			
			<view class="" v-if="content.type == 0">
			<SingleChoice ref="danxuan" :text_copy="content.choice">
			</SingleChoice>
			</view>
			
			<view class="" v-else-if="content.type == 1">
			<SingleChoice ref="duoxuan"></SingleChoice>
			</view>
			
			<view class="" v-else-if="content.type == 2">
			<!-- <SingleChoice></SingleChoice> -->
			</view>
			
			<view class="" v-else-if="content.type == 3">
			<!-- <SingleChoice></SingleChoice> -->
			<slider-setting ref="slider_set"></slider-setting>
			</view>
			
			<setting></setting>
			
			
			<view class="ok_or_no">
				<view class="button_ok_or_no" @click="toEditQuestion">
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
	import Title from '/components/title/title.vue'
	import SingleChoice from "/components/danxuan/danxuan.vue"
	import setting from "/components/setting/setting.vue"
	import sliderSetting from "/components/sliderSetting/sliderSetting.vue"
	export default {
		components:{
			// danxuansetting,
			Title,
			SingleChoice,
			setting,
			sliderSetting
		},
		onLoad(options) {
			if(options.type){
				this.content.type=options.type
			}
			if(options.content){
				let temp=JSON.parse(options.content)
				this.content=temp
			}
		},
		data() {
			return {
				content:{
					title:"444",
					type:'0',
					choice:['3','5']
					
				}
			};
		},
		methods:{
			toEditQuestion(){
				 // const titleComponent = this.$refs.title // 获取标题组件实例
				 // console.log(this.content.type);
				 this.content.title= this.$refs.title.content.title; // 获取标题数据
				 // console.log(this.content.title)
				 // console.log('-------------------')
				 // console.log(this.content.type)
				 switch(parseInt(this.content.type)){
					 case 0:
							this.content.choice=this.$refs.danxuan.copy;
							// console.log(this.content.choice);
							break;
					 case 1:
							this.content.choice=this.$refs.duoxuan.copy;
							console.log(this.content.choice);
							break;
					 case 2:
							break;
					 case 3:
							this.content.choice=this.$refs.slider_set.setting;
							console.log(this.content.choice);
							break;
					 default:
							break;
							
					};
				this.generateQuestion();
				 },
				 generateQuestion(){
					 uni.navigateTo({
					 	url: '/pages/editQuestionnire/editQuestionnire?content='+JSON.stringify(this.content)
					 })
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
