<template>
	<view class="background" id="bg" ref="background">
		<view class="questionnire_page" id='questionnire_page' :style="{display: questionnire_page_show ? 'none' : 'flex'}">		
		
			<view class="input_class" id='input_class_block'>
				<view class="textarea_border">
					<textarea maxlength="900"
							  ref="textareaDom"
							  id="title"
							  placeholder="请输入问卷标题"
							  @linechange="test"
							  v-model="questionNire.title"
							  ></textarea>
				</view>
				<view class="textarea_border">
							  <textarea maxlength="900"
							  ref="textareaDom"
							  id="descripition"
							  placeholder="请输入问卷简介"
							  @linechange="test"
							  v-model="questionNire.description"
							  ></textarea>
				</view>
				
			</view>
			
			<view class="" id='question-all' v-for="(item,index) in questionNire.content" :key=index>
				<!-- 单选组件 -->
				<view class="" v-if="item.type=='SINGLE'">
					<danxuanDisplay :content="questionNire.content[index]"
					:num="(index+1).toString()"
					></danxuanDisplay>
				</view>
				<!-- 多选 -->
				<view class="" v-if="item.type=='MULTIPLE'">
					<danxuanDisplay :content="questionNire.content[index]"
					:num="(index+1).toString()"
					></danxuanDisplay>
				</view>
				<!-- 填空 -->
				<view class="" v-else-if="item.type=='BLANK'">
					<tiankongDisplay :content="questionNire.content[index]"
					:num="(index+1).toString()"
					></tiankongDisplay>
				</view>
				<!-- 滑动条 -->
				<view class="" v-else-if="item.type=='SLIDE'">
					<sliderDisplay :content="questionNire.content[index]"
					:num="(index+1).toString()"
					></sliderDisplay>
				</view>
			</view>
			
			
			
			<view class="add_question" @click="showQuestionChoose">
				<p>+</p>
			</view>

		</view>
		<tab-bar :activeTab="tab" :Type="1" :questionNireProps='questionNire'></tab-bar>
		
		<!-- 下面是点击加号弹出选择题目 -->
		<view class="question" id='question_page'   :style="{top: question_page_show ? '0' : '100%'}">
		   <view class="navBar">
		   	<image src="/static/editQuestion/back.png" mode="aspectFit" class="back" @tap="goBack"></image>
		   </view>
		   <!-- 题型选择 -->
		  <view id="tixing">
			  <view class="titleBox" id="tixing_title">
			  	<view class="dot">
			  	</view>
			  	<h2>题型</h2>
			  </view>
		    
		    <view class="square">
		      <view class="square-part" @click="generateQuestion" id="SINGLE">
				  <image src="/static/editQuestion/danxuan.png" mode="aspectFit"></image>
				  <!-- <view class="square-part-word">单选题</view> -->
				  <p>单选题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="MULTIPLE">
				  <image src="/static/editQuestion/duoxuan.png" mode="aspectFit"></image>
				  <!-- <view class="square-part-word">单选题</view> -->
				  <p>多选题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="BLANK">
				  <image src="/static/editQuestion/tiankong.png" mode="aspectFit"></image>
				  <p>填空题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="SLIDE">
				  <image src="/static/editQuestion/huadongtiao.png" mode="aspectFit"></image>
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
					  <image src="/static/editQuestion/shouji.png" mode="aspectFit"></image>
					  <p>手机</p>
				  </view>
				  <view class="rectangle_square">
					  <image src="/static/editQuestion/riqi.png" mode="aspectFit"></image>
					  <p>日期</p>
				  </view>
				  <view class="rectangle_square">
					  <image src="/static/editQuestion/didian.png" mode="aspectFit"></image>
					  <p>地点</p>
				  </view>
			  </view>
		  </view>
		</view>
		
	</view>
	
	
</template>

<script>
	import TabBar from '/components/tabbar/tabbar.vue';
	import danxuanDisplay from '/components/questionShow/danxuanDisplay/danxuanDisplay.vue';
	import store from '/store/index.js'
	import tiankongDisplay from '/components/questionShow/tiankongDisplay/tiankongDisplay.vue'
	import sliderDisplay from '/components/questionShow/sliderDisplay/sliderDisplay.vue'
	
	// import Blank from '/components/blank/blank.vue';
	export default {
		components: {
			TabBar,
			danxuanDisplay,
			tiankongDisplay,
			sliderDisplay
		},
		onLoad: function(options) {
			this.tab = options.tab
			if(options.content){
				// console.log(this.$store.state.questionNire.content)
				//重新获取content
				this.questionNire.content=this.$store.state.questionNire.content
				const temp=JSON.parse(options.content);
				//如果content的长度比当前正在操作的选择的序号大
				// console.log(this.questionNire.content.length,this.$store.state.now_operate)
				if(this.questionNire.content.length>this.$store.state.now_operate){
					this.questionNire.content[this.$store.state.now_operate]=temp;
				}
				else{
					this.questionNire.content.push(temp);
				}
				//重置这个值
				// this.$store.commit('setNowOperate',-1);
				// this.$store.commit('setQuestionNire',this.questionNire);
			}
			else if(options.flag){
				//重新获取content
				this.questionNire.content=this.$store.state.questionNire.content
			}
			
		},
		mounted(){
			this.toButton()
		},
		data() {
			return {
				tab: '',
				questionNire:{
					title:'早八大学生会不会饿死',
					description:'大学士会饿死',
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
							]
				},
				
				
				//下面是决定两个页面互相切换的变量
				questionnire_page_show:0,
				question_page_show:0,
				isAdd:0

			}
		},
		watch: {
		  '$store.state.IsJump': function(newVal, oldVal) {
				this.toEdit()
		  }
		},
		methods:{

			test(e){
					var node=document.getElementById(e.currentTarget.id);
					node.style.height=`${e.detail.height}px`
			},
			showQuestionChoose(){
					this.question_page_show=1;
					this.questionnire_page_show=1;
					// this.show=0;
			},
			goBack(){
				this.question_page_show=0;
				this.questionnire_page_show=0;
			},
			generateQuestion(e){
				this.question_page_show=0;
				this.questionnire_page_show=0;
				this.creat(e.currentTarget.id);
			},
			creat(type_num){
				var length=this.questionNire.content.length
				var content_temp={
					title:'',
					type:type_num,
					choice:[],
					setting:[]
				}
				//每次跳转前都要把信息转存
				this.$store.commit('setQuestionNire',this.questionNire);
				this.$store.commit('setNowOperate',length);
				uni.navigateTo({
					url: '/pages/try/try?content='+JSON.stringify(content_temp)+'&length='+length  
				})
			},
			toEdit(){
				// console.log(this.questionNire.content[this.$store.state.now_operate])
				//每次跳转前都要把信息转存
				this.$store.commit('setQuestionNire',this.questionNire);
				uni.navigateTo({
					url: '/pages/try/try?content='+JSON.stringify(this.questionNire.content[this.$store.state.now_operate])
							
				})
			},
			//更改页面当前显示位置的函数,为了使添加了新题目，滑动到最下面
			toButton(){
				this.$nextTick(()=>{
					const questionnire_page = document.getElementById("questionnire_page");
					this.isAdd=this.questionNire.content.length>this.$store.state.now_operate?0:1
					console.log(this.isAdd==1)
					if(this.isAdd==1){
					// const backgroundElement = this.$refs.background;
					// console.log(backgroundElement.scrollHeight)
					console.log(111)
					var scrollHeight=questionnire_page.scrollHeight;
					var offsetHeight=questionnire_page.offsetHeight;
					questionnire_page.scrollTop=scrollHeight>0?scrollHeight-offsetHeight:0;
					}
					else if(this.isAdd==-2)
					{
						
					}
					else{
						var question_all = document.getElementById('question-all');  
						var targetView = this.$store.state.targetView;
						var editHeight = this.$store.state.editHeight;
						var question_all_height = questionnire_page.clientHeight;
						var targetView_height = targetView.targetView_height;  
						var targetView_offset_top = targetView.targetView_offset_top;  
						var scroll_position = targetView_offset_top-(question_all_height- (targetView_height-editHeight)) / 2;  
						// console.log(question_all_height , targetView_height,editHeight)
						questionnire_page.scrollTo(0, scroll_position);  
					}
					
					
				})


			}
			
		}

	};
</script>

<style scoped lang="less">
	*{
		margin: 0%;
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
			height: 85%;
			flex-direction: column;
			align-items: center;
			display: flex;
			flex-direction: column;
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
		align-items: center;
		padding: 2% 5% 2% 5%;
	}
	.input_class .textarea_border:nth-child(2){
			margin-top: 2vh;
			height: 8vh;
			width: 80vw;
			size: size;
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
		display: flex;
		overflow-wrap: break-word;
		white-space: normal;
		text-align: center;
		width: 100%;
		font-size: 24px;
		color: rgba(0, 0, 0, 1);
		line-height: 32.93px;
		// line-height: 12vh;
		::placeholder{
			font-size: 24px;
			line-height: 32.93px;
		}
	}

	.textarea_border:last-child textarea {
		// margin-top: 2vh;
		height: 3vh;
		line-height: 27.44px;
		color: rgba(0, 0, 0, 0.36);
		font-size: 20px;
		width: 100%;
		
		::placeholder{
			font-size: 20px;
			line-height: 27.44px;
			display: flex;
			text-align: center;
			width: 100%;
		}
		// line-height: 8vh;
	}

	.add_question {
		margin: 2vh;
		height: 15vh;
		min-height: 15vh;
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
		  height: 85vh;
		  width: 100vw;
		  .navBar{
			margin-top: 5%;

		  	height: 5%;
		  	width: 100%;
			display: flex;
			align-items: center;
			flex-direction: row;
		  	image{
		  		height: 100%;
		  		width: 10%;
		  	}
		  }
	}
	
	
	#tixing{
		width: 100vw;
		height: 40vh;
		display: flex;
		flex-direction: column;
		justify-content: center;
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
	  position: relative;
	  left: 50%;
	  transform: translate(-50%);
	  border-radius: 20px;
	  box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
	  overflow: hidden;
	}
	.square-part {
	  // display: flex; /* 使用 flex 布局 */
	  // align-items: center; /* 垂直居中对齐 */
	  width: 100%;
	  height: 100%;
	  box-sizing: border-box;
	  background: rgba(255, 255, 255, 0.8);
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
	
	.square-part image{
		width: 30%;
		height: 30%;
		position: relative;
		// top: 30%;
		// left: 30%;
		// opacity:0.2;
	}
	.square-part p{
		position: relative;
		line-height: 27.44px;
		color: rgba(0, 0, 0, 0.36);
		font-size: 12pt;
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
		background: rgba(255, 255, 255, 0.8);
		--border_radius:20%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		background: rgba(255, 255, 255, 0.8);
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
	}
	
	.rectangle_square image{
		width: 40%;
		height: 40%;
		position: relative;
		// top: 21%;
		// left: 30%;
	}
	
	.rectangle_square p{
		position: relative;
		line-height: 27.44px;
		color: rgba(0, 0, 0, 0.36);
		font-size: 12pt;
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