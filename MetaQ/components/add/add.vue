<template>
	<view class="addContainer" :class="{show:isShow}">
		<view class="questionnire_page" >
			<view class="input_class2">
				
				<view class="textarea_border2">
					<textarea maxlength="900"
							  id="title2"
							  placeholder="请输入问卷标题"
							  @linechange="autoChange"
							  v-model="questionNire.title"
							  disabled
						></textarea>
				</view>
				<view class="textarea_border2">
							  <textarea maxlength="900"
							  id="descripition2"
							  placeholder="请输入问卷简介"
							  @linechange="autoChange"
							  v-model="questionNire.description"
							  disabled
							  ></textarea>
				</view>
				
			</view>
			<view class="question-all" v-for="(item,index) in questionNire.content" :key="index">
				<component
				:num="(index+1).toString()"
				:content="item"
				:is="chooseComponent(index)">
				</component>
			</view>

		</view>
		
	</view>
	
	
</template>

<script>
	import store from '/store/index.js'
	import danxuanAnswer from '/components/answerQuestion/danxuanAnswer/danxuanAnswer.vue'
	import tiankongAnswer from '/components/answerQuestion/tiankongAnswer/tiankongAnswer.vue'
	import huadongtiaoAnswer from '/components/answerQuestion//huadongtiaoAnswer/huadongtiaoAnswer.vue'
	import duoxuanAnswer from '/components/answerQuestion/duoxuanAnswer/duoxuanAnswer.vue'
	import riqiAnswer from '/components/answerQuestion/calendarSeleteAnswer/calendarSeleteAnswer.vue'
	
	export default {
		components: {
			danxuanAnswer,
			tiankongAnswer,
			huadongtiaoAnswer,
			duoxuanAnswer,
			riqiAnswer
		},
		props:{
			questionNireProps:{
				type:Object,
				default:{
					
				}
			},
			isShow:{
				type:Boolean,
				default:false
			}
		},
		mounted(){
			// console.log(this.questionNire)
		},
		data() {
			return {
				answer:[],
				mode:0,
				componentName:['danxuanAnswer','duoxuanAnswer',
				'tiankongAnswer','huadongtiaoAnswer','','riqiAnswer'],
				title:'关于c10居住学生学校住宿感受的的调研',
				description:'请c10的同学填写，谢谢配合！',
				questionNire:this.questionNireProps,
				// content:[
				// 		{
				// 			question:"标题1",
				// 			type:'SINGLE',
				// 			choice:['a','b','c'],
				// 			setting:[]
				// 		},
				// 		{
				// 			question:"填空题",
				// 			type:'BLANK',
				// 			choice:[],
				// 			setting:[]
				// 		},
				// 		{
				// 			question:"滑动条",
				// 			type:'SLIDE',
				// 			choice:[10,'非常差',1000,'非常好',0],
				// 			setting:[]
				// 		},
				// 		{
				// 			question:"标题2",
				// 			type:'MULTIPLE',
				// 			choice:['m','n','b'],
				// 			setting:[]
				// 	    },
				// 		{
				// 			question:"标题2",
				// 			type:'SINGLE',
				// 			choice:['m','n','b'],
				// 			setting:[]
				// 		}
				// 		],
			}
		},
		methods:{
			autoChange(e){
					var node=document.getElementById(e.currentTarget.id);
					node.style.height=`${e.detail.height}px`
			},
			chooseComponent(index){
				switch(this.questionNire.content[index].type){
					case 'SINGLE':
						return this.componentName[0];
					case 'MULTIPLE':
						return this.componentName[1];
					case 'BLANK':
						return this.componentName[2];
					case 'SLIDE':
						return this.componentName[3];
					case  'PHONE':
						return this.componentName[4];
					case 'DATE':
						return this.componentName[5];
				};
				// var temp=parseInt(this.all_content[index].type);
				// this.componentName[temp]
			},
			submitSuccess(){
				this.$refs.questionAnswerRef.forEach(childComponent => {
						this.answer.push(childComponent.answer);
				});
				console.log(this.answer)
				
			}
		}

	};
</script>

<style scoped lang="less">
	
*{
	margin: 0;
	box-sizing: border-box;
}
.addContainer{
	z-index: 998;
	position: fixed;
	bottom: -100%;
	left: 0;
	right: 0;
	height: 100%;	
	transition: bottom 0.5s ease-in-out;
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
	background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
}
.addContainer.show{
	bottom: 0;
}
.questionnire_page{
		display: flex;
		align-content: center;
		overflow-y: scroll;
		height: 85%;
		flex-direction: column;
		align-items: center;
		padding-bottom: 10%;
}


.textarea_border2{
	border: 3px solid rgba(255, 255, 255, 1);
	display: flex;
}
.input_class2 .textarea_border2:first-child {
	margin-top: 4vh;
	width: 80vw;
	display: flex;
	align-items: center;
	padding: 2% 5% 2% 5%;
}
.input_class2 .textarea_border2:nth-child(2){
		margin-top: 2vh;
		width: 80vw;
		display: flex;
	padding: 2% 5% 2% 5%;
}

.textarea_border2{
	display: flex;
	flex-direction: column;
	justify-content: center;
	
}


.textarea_border2:first-child textarea {
	height: 3vh;
	font-size: 24px;
	color: rgba(0, 0, 0, 1);
	line-height: 32.93px;
	// line-height: 12vh;
	display: flex;
	overflow-wrap: break-word;
	white-space: normal;
	text-align: center;
	width: 100%;
}

.textarea_border2:last-child textarea {
	// margin-top: 2vh;
	height: 3vh;
	line-height: 27.44px;
	color: rgba(0, 0, 0, 0.36);
	font-size: 20px;
	width: 100%;
	// line-height: 8vh;
}
</style>