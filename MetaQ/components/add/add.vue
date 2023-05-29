<!-- <template>
	<view class="addContainer" :class="{show:isShow}">
		<view class="imgAndText">
			<image src="https://tuanpet-cyx.oss-cn-guangzhou.aliyuncs.com/static/add/addNotebook.png" mode="widthFix" class="addImg imgNote" @tap="addNote"></image>
			<text class="Text notbookT">新建备忘事件</text>
		</view>
		<view class="imgAndText">
			<image src="https://tuanpet-cyx.oss-cn-guangzhou.aliyuncs.com/static/add/addActivityNote.png" mode="widthFix" class="addImg"  @tap="ActivityThoughtCreate"></image>
			<text class="Text notbookT">新建活动笔记</text>
		</view>
		<view class="imgAndText">
			<image src="https://tuanpet-cyx.oss-cn-guangzhou.aliyuncs.com/static/add/addPetIDCard.png" mode="widthFix" class="addImg" @tap="IDCreate"></image>
			<text class="Text">新建宠物身份证</text>
		</view>
		<view class="imgAndText">
			<image src="https://tuanpet-cyx.oss-cn-guangzhou.aliyuncs.com/static/add/addPetIcon.png" mode="widthFix" class="addImg face imgNote"></image>
			<text class="Text notbookT">新建萌宠表情</text>
		</view>
	</view>
</template>

<script>
	export default {
		name:"add",
		data() {
			return {
				
			};
		},
		props: {
			isShow: {
				type: Boolean,
				default: false,
			},
		},
		methods: {
			IDCreate() {
				this.$emit('updateAdd');
				uni.navigateTo({
					 url: `/pages/IDCreate/IDCreate/IDCreate`,
				})
			},
			addNote(){
				
				const item = ['', '', '', '', '']; // 初始化为五个空字符串的数组
				const index = -1; // 初始化为 -1
				
				wx.setStorageSync('myData', { item, index });
				this.$emit('updateAdd');
				uni.navigateTo({
					 url: `/pages/notebook/notebookForm`,
				})
			},
			ActivityThoughtCreate() {
				this.$emit('updateAdd');
				uni.navigateTo({
					 url: `/pages/activity/ActivityThoughtCreate`,
				})
			}
		},
	}
</script>

<style lang="less">
	.addContainer{
	z-index: 998;
	background-color: rgba(255, 255, 255, 0.3);
	position: fixed;
	display: grid;
	grid-template-columns: repeat(2, 200px);
	grid-template-rows: repeat(2, 200px);

	bottom: -100%;
	left: 0;
	right: 0;
	height: 100%;		
	// animation: slide-up 0.5s ease-in-out forwards;
	transition: bottom 0.5s ease-in-out;
	align-items: center;
	justify-items: center;
	justify-content: center;
	align-content: center;
		.imgAndText{
			box-shadow: 5px 7px 8px -1px rgba(239,219,208,0.5);
			height: 40vw;
			width: 40vw;
			background-color: #fcceb5;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			margin: 3vw;
			border-radius: 10%;
			.addImg{
				height: 15vw;
				width: 15vw;
			}
			.addImg.imgNote{
				margin-bottom: 0.5rem;
			}
			.addImg.face{
				height: 13vw;
				width: 13vw;
			}
			.Text
			{
				color: #ffffff;
				font-family: SimHei;
			}
		}
	}
	.addContainer.show{
		bottom: 0;
	}
</style> -->
<template>
	<view class="addContainer" :class="{show:isShow}">
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
			<view class="question-all" v-for="(item,index) in all_content" :key="index">
				<component
				:num="(index+1).toString()"
				:content="item"
				:is="chooseComponent(index)">
				</component>
			</view>
			

<!-- 			<view class="">
				<view class="submit" @click="submitSuccess">
					提交
				</view>
			</view> -->
			

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
		props: {
			isShow: {
				type: Boolean,
				default: false,
			},
		},
		data() {
			return {
				answer:[],
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
							choice:['m','n','b'],
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
			// submitSuccess(){
				
				
			// }
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
	
.submit{
	margin-top: 20px;
	margin-bottom: 50px;
	display: flex;
	align-items: center;
	justify-content: center;
	width: 70vw;
	height:10vh;
	border: 1px solid #9c3ced;
	border-radius: 10px;
	background: linear-gradient(58deg, #b094f8 0%, #e4daf1 38.89%, #f2f2f2 67.78%, #e4b8f5 100%);
	
}
</style>