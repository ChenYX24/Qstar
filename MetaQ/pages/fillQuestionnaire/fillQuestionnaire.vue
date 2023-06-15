<template>
	<view class="background">
		<view class="questionnire_page" id='questionnire_page'>
			<view class="input_class" id='input_class_block'>
				<view class="textarea_border">
					<textarea maxlength="900"
							  id="title"
							  placeholder="请输入问卷标题"
							  @linechange="autoChange"
							  v-model="questionNaire.title"
							  disabled
						></textarea>
				</view>
				<view class="textarea_border">
							  <textarea maxlength="900"
							  id="descripition"
							  placeholder="请输入问卷简介"
							  @linechange="autoChange"
							  v-model="questionNaire.description"
							  disabled
							  ></textarea>
				</view>		
			</view>
			<view class="question-all" v-for="(item,index) in questionNaire.content" :key="index">
				<component
				ref="questionAnswerRef"
				:num="(index+1).toString()"
				:content="item"
				:is="chooseComponent(index)"
				:answerProps="answerTemp[index]">
				</component>
			</view>
			
			<view class="">
				<view class="block">
				</view>
			</view>
			<view class="">
				<view class="submit" @click="submitSuccess(false)">
					保存
				</view>
				<view class="submit" @click="submitSuccess(true)">
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
	import riqiAnswer from '/components/answerQuestion/calendarSeleteAnswer/calendarSeleteAnswer.vue'
	
	export default {
		components: {
			danxuanAnswer,
			tiankongAnswer,
			huadongtiaoAnswer,
			duoxuanAnswer,
			riqiAnswer
		},
		onLoad: function(options) {
			console.log("options.id:",options.id);
			console.log("options.check:",options.check);
			if(options.id){
				uni.request({
					url:this.$store.state.host + '/isLogin',
					method:'POST',
					header:{
						'Content-Type' : 'application/json',
						token : uni.getStorageSync("token")
					},
					success: res => {
						console.log("isLogin:",res.data);
						
						if(res.data.msg == "false"){
							uni.navigateTo({
								url:'/pages/login/login?flag=true'
							})
						}
					},
					fail: () => {},
					complete: () => {}
				})
				var info = {
					id:options.id,
					commit:false,
				}
				uni.request({
					url: this.$store.state.host + '/fill',
					method: 'POST',
					header:{
						'Content-Type' : 'application/json',
						token : uni.getStorageSync("token")
					},
					data: info,
					success: res => {
						//console.log(res);
						console.log("fill:",res.data);
						this.questionNaire = res.data.data;
						this.ID = res.data.data.id;
					},
					fail: () => {},
					complete: () => {}
				});
				//const token = localStorage.getItem('token')
				//axios.defaults.headers.common['token'] = token;
				//console.log("token",localStorage.getItem('token'));
				this.id = options.id;
				// var info = {
				// 	id:options.id,
				// 	commit:false,
				// }
				// console.log(info);
				// axios.post('https://metaq.scutbot.icu/fill'
				// 			/*'http://localhost:8080/fill'*/,info)
				//     .then(response => {
				//       console.log(response.data);
				// 	  this.questionNaire = response.data.data;
				// 	  this.ID = response.data.data.id;
				//     })
				//     .catch(error => {
				//       console.log(error);
				//     });
				// console.log(this.answer);
				this.answerTemp=Array.from({length:this.questionNaire.content.length}, () => '');
			}
			else if(options.check){
				//这里是answer的id\
				console.log("asdjaklsjdlkasjdlk");
				this.ID= this.$store.state.qnid;
				//axios.defaults.headers.common['token'] = localStorage.getItem('token');
				//console.log("token",localStorage.getItem('token'));
				console.log("ID:",this.ID);
				uni.request({
					url: this.$store.state.host + '/checkFill',
					method: 'POST',
					header:{
						'Content-Type' : 'application/json',
						token : uni.getStorageSync("token")
					},
					data: {
						id:this.ID
					},
					success: res => {
						//console.log(res);
						console.log("checkfill:",res.data);
						this.answerTemp=res.data.data.filled;
						this.questionNaire=res.data.data.check;//这个是问卷内容
						this.id = res.data.data.id;
					},
					fail: () => {},
					complete: () => {}
				});
				// axios.post('https://metaq.scutbot.icu/checkFill'
				// 			/*'http://localhost:8080/checkFill'*/,{id:this.ID})
				//     .then(response => {
				//       console.log(response.data);
				// 	  this.answerTemp=response.data.data.filled;
				// 	  this.questionNaire=response.data.data.check;//这个是问卷内容
				// 	  this.id = response.data.data.id;
				//     })
				//     .catch(error => {
				//       console.log(error);
				//     });
			}
		},
		// data() {
		// 	return {
		// 		// A:['1','ttt','500','0,1','0'],
		// 		answerTemp:['0','012','太好了，没有建议','10',''],
		// 		answer:[],
		// 		ID:-1,//问卷的一份答案的id
		// 		id:-1,//问卷id
		// 		componentName:['danxuanAnswer','duoxuanAnswer',
		// 		'tiankongAnswer','huadongtiaoAnswer','','riqiAnswer'],
		// 		questionNaire:{    
		// 		},
		// 	}
		// },
		data() {
		   return {
		    // A:['1','ttt','500','0,1','0'],
		    answerTemp:['0','012','太好了，没有建议','10',''],
		    answer:[],
		    ID:-1,//问卷的一份答案的id
		    id:-1,//问卷id
		    componentName:['danxuanAnswer','duoxuanAnswer',
		    'tiankongAnswer','huadongtiaoAnswer','','riqiAnswer'],
		    questionNaire:{
		         title:'关于生活中常用的问卷系统的使用情况以及喜爱程度的调查',
		         description:'请大家认真填写，谢谢大家！',
		         content:[
		          {
		           "choice": ["MetaQ", "问卷星", "星问卷", "QStar"],
		           "question": "你最喜欢哪个问卷系统",
		           "type": "SINGLE"
		          }, {
		           "choice": ["外观", "功能", "便捷"],
		           "question": "你喜欢什么方面",
		           "type": "MULTIPLE"
		          }, {
		           "choice": [],
		           "question": "有什么建议吗",
		           "type": "BLANK"
		          }, {
		           "choice": [0, "", "10", "", 0],
		           "question": "评个分吧",
		           "type": "SLIDE"
		          }, {
		           "choice": 0,
		           "question": "什么时候使用最频繁",
		           "type": "DATE"
		          }]
		    },
		   }},
		

		methods:{
			getT(){
				console.log(this.answerTemp)
			},
			autoChange(e){
					var node=document.getElementById(e.currentTarget.id);
					node.style.height=`${e.detail.height}px`
			},
			chooseComponent(index){
				switch(this.questionNaire.content[index].type){
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
				// return this.componentName[temp];
			},
			submitSuccess(commitValue){
				this.$refs.questionAnswerRef.forEach(childComponent => {
							this.answer.push(childComponent.answer);
				});
				const token = localStorage.getItem('token')
				//axios.defaults.headers.common['token'] = token;
				//console.log("token",localStorage.getItem('token'));
				var info = {
					filledID:this.ID,
					data:this.answer,
					commit:commitValue,
					id:this.id
				}
				console.log(info);
				console.log(this.answer);
				uni.request({
					url: this.$store.state.host + '/saveFill',
					method: 'POST',
					header:{
						'Content-Type' : 'application/json',
						token : uni.getStorageSync("token")
					},
					data: info,
					success: res => {
						console.log(res.data);
						uni.reLaunch({
							url:'/pages/myQ/myQ'
						})
					},
					fail: () => {},
					complete: () => {}
				});
				// axios.post('https://metaq.scutbot.icu/saveFill'
				// 			/*'http://localhost:8080/saveFill'*/,info)
				//     .then(response => {
				//       console.log(response.data);
				// 	  uni.reLaunch({
				// 	  	url:'/pages/myQ/myQ'
				// 	  })
				// 	  //this.questionNaire = response.data.data;
				//     })
				//     .catch(error => {
				//       console.log(error);
				//     });
				

				
			},
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
	margin-bottom: 30px;
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