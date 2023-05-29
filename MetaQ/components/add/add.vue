<template>
	<view class="addContainer" :class="{show:isShow}">
		<view class="questionnire_page" id='questionnire_page'>
			<view class="input_class2" id='input_class2_block'>
				
				<view class="textarea_border2">
					<textarea maxlength="900"
							  id="title"
							  placeholder="请输入问卷标题"
							  @linechange="autoChange"
							  v-model="title"
							  disabled
						></textarea>
				</view>
				<view class="textarea_border2">
							  <textarea maxlength="900"
							  id="descripition"
							  placeholder="请输入问卷简介"
							  @linechange="autoChange"
							  v-model="description"
							  disabled
							  ></textarea>
				</view>
				
			</view>
			<view class="question-all" v-for="(item,index) in all_content" :key="index">
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
	export default {
		components: {
			danxuanAnswer,
			tiankongAnswer,
			huadongtiaoAnswer,
			duoxuanAnswer
		},
		onLoad: function(options) {
			if(options.mode){
				this.mode=options.mode
			}
		},
		props:{
			all_content:{
				type:Array,
				default:[]
			},
			isShow:{
				type:Boolean,
				default:false
			}
		},
		data() {
			return {
				answer:[],
				mode:0,
				componentName:['danxuanAnswer','duoxuanAnswer',
				'tiankongAnswer','huadongtiaoAnswer'],
				title:'关于c10居住学生学校住宿感受的的调研',
				description:'请c10的同学填写，谢谢配合！',
				all_content:[
						{
							title:"标题1",
							type:'0',
							choice:['a','b','c'],
						},
						{
							title:"标题2",
							type:'1',
							choice:['m','n','b'],
					    },
						{
							title:"标题2",
							type:'0',
							choice:['m','n','b'],
						},
						{
							title:"标题2",
							type:'2',
							choice:['m','n','b'],
						},
						{
							title:"滑动条",
							type:'3',
							choice:[10,'非常差',1000,'非常好',0],
						}],
			}
		},
		methods:{
			autoChange(e){
					var node=document.getElementById(e.currentTarget.id);
					node.style.height=`${e.detail.height}px`
			},
			chooseComponent(index){
				var temp=parseInt(this.all_content[index].type);
				return this.componentName[temp];
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