<template>
	<view class="background">
		<view>
			<!-- <Title ref="title" :content="content"></Title> -->
			
			<view class="num-show">
				<view class="box">
					第{{num}}题
				</view>
			</view>
			
			<view class="" v-if=" content.type=='SINGLE' || content.type=='MULTIPLE'" >
				<SingleChoice ref="danxuan" 
				:text_copy="content.choice"
				:num="num"
				:type="content.type"
				:questionProp="content.question">
				</SingleChoice>
			</view>
<!-- 			
			<view class="" v-else-if="content.type == 'MULTIPLE'">
				<SingleChoice ref="duoxuan" 
				:text_copy="content.choice"
				:num="num"
				:type="content.type"
				:questionProp="content.question"></SingleChoice>
			</view> -->
			
			<view class="" v-else-if="content.type == 'BLANK'">
				<Title
				:num="num"
				ref="tiankong"
				:questionProp="content.question"></Title>
			</view>
			
			<view class="" v-else-if="content.type == 'SLIDE'">
				<slider-setting 
				ref="sliderSet"
				:num="num"
				:questionProp="content.question"></slider-setting>
			</view>
			
			<view class="" v-else-if="content.type == 'DATE'">
				<calendarSetting 
				ref="calendarSetting"
				:num="num"
				:questionProp="content.question"
				:indexProps='content.choice[0]'></calendarSetting>
			</view>
			
			
			<setting></setting>
			
			
			<view class="ok_or_no">
				<view class="button_ok_or_no" @click="toEditQuestion">
					确认
				</view>
				<view class="button_ok_or_no"  @click="bcakEdit">
					返回
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import Title from '/components/questionEdit/title/title.vue'
	import SingleChoice from "/components/questionEdit/danxuan/danxuan.vue"
	import setting from "/components/questionEdit/setting/setting.vue"
	import sliderSetting from "/components/questionEdit/sliderSetting/sliderSetting.vue"
	import store from '/store/index.js'
	import calendarSetting from "/components/questionEdit/calendarSetting/calendarSetting.vue"
	
	export default {
		components:{
			// danxuansetting,
			Title,
			SingleChoice,
			setting,
			sliderSetting,
			calendarSetting
		},
		onLoad(options) {
			// console.log(content.type=='SINGLE')
			if(options.length){
				let temp=JSON.parse(options.content);
				let length=options.length;
				this.content=temp;
				this.$store.commit('setNowOperate',100000);
			}
			else if(options.content){//这里是对已经存在的问题进行编辑
				this.content=JSON.parse(options.content)
				// console.log(this.content)
			}
		},
		data() {
			return {
				num:this.$store.state.now_operate+1,
				content:{
						question:"日期",
						type:'SINGLE',
						choice:[2,3],
						setting:[]
				}
			};
		},
		methods:{
			toEditQuestion(){
				console.log(this.content.type)
				 switch(this.content.type){
					 case 'MULTIPLE':
					 case 'SINGLE' :
							this.content.choice=this.$refs.danxuan.copy;
							//去除空值
							this.content.question=this.$refs.danxuan.question;
							this.content.choice=this.content.choice.filter(item => item !== null && item !== undefined && item !== "");  // 过滤空值
							// console.log(this.content.choice);
							break;
					 // case 'MULTIPLE':
						// 	this.content.choice=this.$refs.duoxuan.copy;
						// 	this.content.question=this.$refs.duoxuan.question;
						// 	this.content.choice=this.content.choice.filter(item => item !== null && item !== undefined && item !== "");  // 过滤空值
						// 	break;
					 case 'BLANK':
							this.content.question=this.$refs.tiankong.question;
							break;
					 case 'SLIDE':
							this.content.question=this.$refs.sliderSet.question;
							this.content.choice=this.$refs.sliderSet.setting;
							break;
					case 'DATE':
							this.content.question=this.$refs.calendarSetting.question;
							this.content.choice=this.$refs.calendarSetting.index;
							break;
					 default:
							break;
							
					};
				this.generateQuestion();
				 },
				 generateQuestion(){
					 uni.reLaunch({
					 	url: '/pages/editQuestionnire/editQuestionnire?content='+JSON.stringify(this.content)
					 })
				 },
				bcakEdit(){
					uni.reLaunch({
						url: '/pages/editQuestionnire/editQuestionnire?flag='+'1'
					})
				} 
			
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
    overflow-y: scroll;
	background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
}
.num-show{
	margin-top: 10px;
	width: 100%;
	height: 15vh;
	// border: 2px solid red;
	display: flex;
	align-items: center;
	justify-content: center;
	
}
.box{
	width: 80vw;
	height: 10vh;
	border: 2px dashed rgba(255, 255, 255, 1);
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 30px;
	color: #940ece;
}

.ok_or_no{
	margin-top: 4vh;
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
	margin-bottom: 15vh;
	background-color: aquamarine;
	background-color: rgba(230,230,240,1);
}

</style>
