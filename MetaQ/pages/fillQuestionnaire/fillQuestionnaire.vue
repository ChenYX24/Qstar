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
							  v-model="brief"
							  disabled
							  ></textarea>
				</view>
				
			</view>
			
<!-- 			<view class=""  v-for="(item,index) in all_content" :key=index>
				<questions :content="all_content[index]"  
				:num="(index+1).toString()"
				></questions>
			</view> -->
<!-- 			<components :is="danxun"></components> -->
			

		</view>
		
	</view>
	
	
</template>

<script>
	import store from '/store/index.js'
	// import questions from '/components/questions/questions.vue'
	import single_ans from '/components/ans/single_ans/single_ans.vue'
	export default {
		components: {
			// questions,
			single_ans
		},
		onLoad: function(options) {
		},
		data() {
			return {
				title:'关于c10居住学生学校住宿感受的的调研',
				brief:'请c10的同学填写，谢谢配合！',
				all_content:[
						{
							title:"标题1",
							type:'0',
							choice:['a','b','c'],
						},
						{
							title:"标题2",
							type:'0',
							choice:['m','n','b'],
					    },
						{
							title:"标题2",
							type:'0',
							choice:['m','n','b'],
						}],
			}
		},
		// watch: {
		//   '$store.state.IsJump': function(newVal, oldVal) {
		// 		this.toEdit()
		//   }
		// },
 	    mounted() {
		  // this.textareaDom = this.$refs.textareaDom;
	    },
		methods:{
			autoChange(e){
					var node=document.getElementById(e.currentTarget.id);
					node.style.height=`${e.detail.height}px`
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
	
	.input_class{
		 //  margin: 2vh;
		 //  display: flex;
		 //  justify-content: center;
		 //  align-items: center;
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