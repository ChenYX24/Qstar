<template>
	<view class="background">
		<view class="questionnire_page" id='questionnire_page'>
			<view class="input_class" id='input_class_block'>
				<view class="textarea_border">
					<textarea maxlength="900"
							  id="title"
							  placeholder="请输入问卷标题"
							  @linechange="autoChange"
							  v-model="title"
							  disabled
						></textarea>
				</view>
				<view class="textarea_border">
							  <textarea maxlength="900"
							  id="descripition"
							  placeholder="请输入问卷简介"
							  @linechange="autoChange"
							  v-model="description"
							  disabled
							  ></textarea>
				</view>		
			</view>
			<view class="question-all" v-for="(item,index) in content" :key="index">
				<component
				ref="questionAnswerRef"
				:num="(index+1).toString()"
				:content="item"
				:is="chooseComponent(index)">
				</component>
			</view>
			
			<view class="">
				<view class="block">
				</view>
			</view>

			<view class="" v-if="mode==0">
				<view class="submit" @click="submitSuccess">
					提交
				</view>
			</view>
			

		</view>
		
	</view>
	
	
</template>

<script>
	import axios from 'axios'
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
			// if(options.mode){
			// 	this.mode=options.mode
			// }
			// if(options.id){
			// 	this.$store.commit('setIsCreate',options.id)
			// 	const token = localStorage.getItem('token')
				
			// 	if(!token){
			// 		uni.navigateTo({
			// 			url:'/pages/login/login?id='+options.id
			// 		})
			// 	}
			// }
			const token = localStorage.getItem('token')
			axios.defaults.headers.common['token'] = token;
			//console.log("token",localStorage.getItem('token'));
			var info = {
				id:options.id,
				commit:false
			}
			console.log(info);
			axios.post(/*'https://metaq.scutbot.icu/login'*/
						'http://localhost:8080/fill',info)
			    .then(response => {
			      console.log(response.data);
			    })
			    .catch(error => {
			      console.log(error);
			    });
			console.log(this.answer);
		},
		data() {
			return {
				answer:[],
				mode:0,
				componentName:['danxuanAnswer','duoxuanAnswer',
				'tiankongAnswer','huadongtiaoAnswer'],
				title:'关于c10居住学生学校住宿感受的的调研',
				description:'请c10的同学填写，谢谢配合！',
				content:[
						{
							question:"标题1",
							type:'SINGLE',
							choice:['a','b','c'],
							setting:[]
						},
						{
							question:"填空题",
							type:'BLANK',
							choice:[],
							setting:[]
						},
						{
							question:"滑动条",
							type:'SLIDE',
							choice:[10,'非常差',1000,'非常好',0],
							setting:[]
						},
						{
							question:"标题2",
							type:'MULTIPLE',
							choice:['m','n','b'],
							setting:[]
					    },
						{
							question:"标题2",
							type:'SINGLE',
							choice:['m','n','b'],
							setting:[]
						}
						],
			}
		},
		methods:{
			autoChange(e){
					var node=document.getElementById(e.currentTarget.id);
					node.style.height=`${e.detail.height}px`
			},
			chooseComponent(index){
				switch(this.content[index].type){
					case 'SINGLE':
						return this.componentName[0];
					case 'MULTIPLE':
						return this.componentName[1];
					case 'BLANK':
						return this.componentName[2];
					case 'SLIDE':
						return this.componentName[3];
				};
				
				
				// var temp=parseInt(this.all_content[index].type);
				// return this.componentName[temp];
			},
			submitSuccess(){
				this.$refs.questionAnswerRef.forEach(childComponent => {
						if(childComponent.$options.name === 'duoxuanAnswer'){
							// console.log('duoxuan',Array.from(childComponent.answer))
							this.answer.push(Array.from(childComponent.answer).join(''));
						}
						else
							this.answer.push(childComponent.answer);
				});
				
				
			}
		}

	};
</script>

<style scoped lang="less">
*{
	margin: 0;
	box-sizing: border-box;
}
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

.questionnire_page{
		display: flex;
		align-content: center;
		overflow-y: scroll;
		height: 100%;
		flex-direction: column;
		align-items: center;
}


.textarea_border{
	border: 3px solid rgba(255, 255, 255, 1);
	display: flex;
}
.input_class .textarea_border:first-child {
	margin-top: 4vh;
	width: 80vw;
	display: flex;
	align-items: center;
	padding: 2% 5% 2% 5%;
}
.input_class .textarea_border:nth-child(2){
		margin-top: 2vh;
		width: 80vw;
		display: flex;
	padding: 2% 5% 2% 5%;
}

.textarea_border{
	display: flex;
	flex-direction: column;
	justify-content: center;
	
}


.textarea_border:first-child textarea {
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

.textarea_border:last-child textarea {
	// margin-top: 2vh;
	height: 3vh;
	line-height: 27.44px;
	color: rgba(0, 0, 0, 0.36);
	font-size: 20px;
	width: 100%;
	// line-height: 8vh;
}

.block{
	display: flex;
	width: 100%;
	height: 50px;
}

.submit{
	// margin-top: 20px;
	margin-bottom: 50px;
	display: flex;
	align-items: center;
	justify-content: center;
	width: 70vw;
	height:10vh;
	border: 1px solid #9c3ced;
	border-radius: 10px;
	background: linear-gradient(58deg, #b094f8 0%, #e4daf1 38.89%, #f2f2f2 67.78%, #e4b8f5 100%);
	// background: inear-gradient(90deg,#b3aefd 0%,rgba(255,235,244,0.75)100%);;
}


	
	
	
	
	

	
	

	

	


	
	
	

	
</style>