<template>
	<view class="background">
		<view class="questionnire_page" id='questionnire_page' :style="{display: questionnire_page_show ? 'none' : 'flex'}">
			
		
			<view class="input_class" id='input_class_block'>
				<view class="textarea_border">
					<textarea maxlength="900"
							  ref="textareaDom"
							  id="title"
							  placeholder="请输入问卷标题"
							  @linechange="test"
							  ></textarea>
				</view>
				
				<view class="textarea_border">
							  <textarea maxlength="900"
							  ref="textareaDom"
							  id="descripition"
							  placeholder="请输入问卷简介"
							  @linechange="test"
							  ></textarea>
				</view>
				
			</view>
<view class="danxuan" id="duanxuan0"></view>
			<view class="add_question" @click="showQuestionChoose">
				<p>+</p>

			</view>
		<Blank></Blank>
		</view>
		<tab-bar :activeTab="tab"></tab-bar>
		
		<!-- 下面是点击加号弹出选择题目 -->
		<view class="question" id='question_page'   :style="{top: question_page_show ? '0' : '100%'}">
		   <!-- 题型选择 -->
		  <view id="tixing">
			  <view class="titleBox" id="tixing_title">
			  	<view class="dot">
			  	</view>
			  	<h2>题型</h2>
			  </view>
		    
		    <view class="square">
		      <view class="square-part" @click="generateQuestion" id="0">
				  <img src="/static/editQuestion/danxuan.png" alt="">
				  <!-- <view class="square-part-word">单选题</view> -->
				  <p>单选题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="1">
				  <img src="/static/editQuestion/duoxuan.png" alt="">
				  <!-- <view class="square-part-word">单选题</view> -->
				  <p>多选题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="2">
				  <img src="/static/editQuestion/tiankong.png" alt="">
				  <p>填空题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="3">
				  <img src="/static/editQuestion/huadongtiao.png" alt="">
				  <p>滑动条</p>
			  </view>
		    </view>
		  </view>
		  <!-- 模板 -->
		  <view id="moban">
			  <view class="titleBox">
				<view class="dot">
				</view>
			  	<h2>模板</h2>
			  </view>
			  
			  <view class="rectangle">
				  <view class="rectangle_square">
					  <img src="/static/editQuestion/shouji.png" alt="">
					  <p>手机</p>
				  </view>
				  <view class="rectangle_square">
					  <img src="/static/editQuestion/riqi.png" alt="">
					  <p>日期</p>
				  </view>
				  <view class="rectangle_square">
					  <img src="/static/editQuestion/didian.png" alt="">
					  <p>地点</p>
				  </view>
			  </view>
		  </view>
		</view>
		
	</view>
	
	
</template>

<script>
	import TabBar from '/components/tabbar/tabbar.vue';
	import Blank from '/components/blank/blank.vue';
	export default {
		components: {
			TabBar,
			Blank
		},
		onLoad: function(options) {
			this.tab = options.tab
		},
		data() {
			return {
				tab: '',
				//用来记录四种问题的数量
				num_q:[0,0,0,0],
				questionnire_page_show:0,
				question_page_show:0,
			}
		},
 	    mounted() {
		  // this.textareaDom = this.$refs.textareaDom;
	    },
		methods:{
			test(e){
					var node=document.getElementById(e.currentTarget.id);
					node.style.height=`${e.detail.height}px`

			},
			showQuestionChoose(){
				// var question_page=document.getElementById('question_page');
					// question_page.style.top = 0;
					this.question_page_show=1;
					this.questionnire_page_show=1;
					// this.show=0;
			},
			generateQuestion(e){
				// var type_num=parseInt(e.currentTarget.id);
				// var questionnire_page=document.getElementById('questionnire_page');
				this.question_page_show=0;
				this.questionnire_page_show=0;
				// this.show=1;
				this.creat(parseInt(e.currentTarget.id));
			},
			creat(type_num){
				var input_class_block=document.getElementById('input_class_block')
				switch(type_num){
					//单选题
					case 0:
						var temp=document.createElement('view')
						temp.setAttribute('class', 'danxuan');
						temp.setAttribute('id', `duanxuan${this.num_q[0]}`);
						this.setQuestionStyle(temp)
						input_class_block.appendChild(temp);
						break;
					case 1:
						break;
					case 2:
						var temp=document.createElement('view')
						temp.setAttribute('class', 'blank');
						temp.setAttribute('id', `blank${this.num_q[0]}`);
						this.setQuestionStyle(temp)
						input_class_block.appendChild(temp);
						break;
					case 3:
						break;
					default:
					    break;
				}
				this.num_q[type_num]++;
			},
			setQuestionStyle(temp){
				
				temp.style.marginTop='3vh'
				temp.style.display='flex';
				temp.style.height= ' 15vh';
				temp.style.width='80vw';
				temp.style.border='2px solid red';
				
				// temp.style.cssText =`
				// 	marginTop:'3vh';
				// 	display:'flex';
				// 	width:'80vw';
				// 	border:'2px solid red';
				// 	height: ' 15vh';
				// `

				// console.log(temp.style.csscssText)
				// temp.style.cssText = 'height: 100px;width:80vw;temp.style.border=2px solid red';
				// temp.setStyle({
				// 	'marginTop':'3vh',
				// 	'display':'flex',
				// 	'height':'15vh',
				// 	'width':'80vw',
				// 	'border':'2px solid red'
				// })
				
			},
		}

	};
</script>

<style scoped lang="less">
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
		    flex-wrap: wrap;
		    align-content: center;
		    justify-content: center;
	}
	
	.input_class{
		 //  margin: 2vh;
		 //  display: flex;
		 //  justify-content: center;
		 //  align-items: center;
	}
	
	.textarea_border{
		border: 2px dashed #fff;
		display: flex;
	}
	.input_class .textarea_border:first-child {
		margin-top: 4vh;
		height: 12vh;
		width: 80vw;
		display: flex;
	}
	.input_class .textarea_border:nth-child(2){
			margin-top: 2vh;
			height: 8vh;
			width: 80vw;
			size: size;
			display: flex;
	}
	
	.textarea_border{
		display: flex;
		flex-direction: column;
		justify-content: center;
		textarea{
			border: 2px solid red;
			display: flex;
			overflow-wrap: break-word;
			white-space: normal;
			text-align: center;
			overflow-y: auto;
		}
		
	}
	

	.textarea_border:first-child textarea {
		height: 3vh;
		width: 80vw;
		// line-height: 12vh;
	}

	.textarea_border:last-child textarea {
		// margin-top: 2vh;
		height: 3vh;
		width: 80vw;
		// line-height: 8vh;
	}

	.add_question {
		margin: 2vh;
		height: 15vh;
		width: 80vw;
		border: 2px solid #fff;
		border-radius: 5vw;
		background-color: #fff;
		display: flex;
		align-items: center;
		justify-content: center;

	}

	.add_question p {
		margin: 0;
		text-align: center;
		font-size: 15vw;
		color: #E4C2FF;
	}
	
	
	// //为生成的新的div设置样式
	// .danxuan{
	// 	display: flex;
	// 	border: 2px solid green;
	// 	height: 15vh;
	// 	width: 80vw;
	// }
	
	

////////////////////////////////////////////////////////////////////////////////////////
///////////////////////
	// 以下是题目选择
	.question{
		  position: fixed;
		  top: 100%;
		  left: 0;
		  transition: top 0.5s ease-in-out;
		  
	}
	
	
	#tixing{
		width: 100vw;
		height: 50vh;
	}
	.dot{
		position: absolute;
		border-radius: 50%;
		width: 12px;
		height: 12px;
		opacity: 1;
		background: rgba(221, 183, 247, 1);
		filter: blur(0.6px);
		position: relative;
		margin-left: 5px;
		margin-right:5px ;
	}
	.titleBox{
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	
	// 调整titile"题型"的位置
	#tixing_title{
		position: relative;
		top: 5%;
	}
	.square {
	  // display: flex;
	  // flex-wrap: wrap;
	  display: grid;
	  grid-template-columns: 1fr 1fr;
	  grid-template-rows: 1fr 1fr;
	  gap: 2px;
	  width: 70vw;
	  height: 70vw;
	  position: absolute;
	  top:15vw;
	  left: 15vw;
	}
	.square-part {
	  // display: flex; /* 使用 flex 布局 */
	  // align-items: center; /* 垂直居中对齐 */
	  width: 100%;
	  height: 100%;
	  box-sizing: border-box;
	  border: 1px solid rgb(237,217,252);
	  background-color: rgba(255, 255, 255, 0.5); /* 红色，透明度为 50% */
	  //定义圆角变量
	  --border_radius:25%;
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	  justify-content: center;
	}
	
	
	.square-part:first-child{
	  	  border-top-left-radius:var(--border_radius);
	}
	.square-part:nth-child(2) {
	  border-top-right-radius: var(--border_radius); 
	}
	.square-part:nth-child(3) {
	  border-bottom-left-radius: var(--border_radius); 
	}
	.square-part:last-child {
	  border-bottom-right-radius: var(--border_radius); 
	}
	
	.square-part img{
		width: 40%;
		height: 40%;
		position: relative;
		// top: 30%;
		// left: 30%;
		// opacity:0.2;
	}
	.square-part p{
		position: relative;
		// top:25%;
		// left: 30%;
	}
	
	.rectangle{
		display: flex;
	    flex-wrap: wrap;
	    width: 80vw;
	    height: 20vw;
	    position: relative;
	    top: 5vw;
	    left: 10vw;
	  
	}
	.rectangle_square{
		height: 120%;
		width: 32%;
		border: 1px solid rgb(237,217,252);
		background-color: rgba(255, 255, 255, 0.5); 
		--border_radius:20%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	
	.rectangle_square img{
		width: 50%;
		height: 50%;
		position: relative;
		// top: 21%;
		// left: 30%;
	}
	
	.rectangle_square p{
		position: relative;
		// top:15%;
		// left: 39%;
	}
	.rectangle_square:first-child{
		border-top-left-radius: var(--border_radius);
		border-bottom-left-radius: var(--border_radius);
	}
	.rectangle_square:last-child{
		border-top-right-radius: var(--border_radius);
		border-bottom-right-radius: var(--border_radius);
	}
	
	
	

	
</style>