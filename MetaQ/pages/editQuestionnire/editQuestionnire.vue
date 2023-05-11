<template>
	<view class="background">
		<view class="input_class">
			<view class="textarea_border">
				<textarea name="title" placeholder="请输入问卷标题"></textarea>
			</view>
			<view class="textarea_border">
				<textarea name="description" placeholder="请输入问卷简介"></textarea>
			</view>
			
		</view>

		<view class="add_question">
			<p>+</p>

		</view>

		<tab-bar :activeTab="tab"></tab-bar>
	</view>
</template>

<script>
	import TabBar from '/components/tabbar/tabbar.vue';
	export default {
		components: {
			TabBar
		},
		onLoad: function(options) {
			this.tab = options.tab
		},
		data() {
			return {
				tab: '',
			}
		},

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
	.input{
		  display: flex;
		  justify-content: center;
		  align-items: center;
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
		position: relative;
		top:4.5vh;
		height: 3vh;
		width: 80vw;
		// line-height: 12vh;
	}

	.textarea_border:last-child textarea {
		// margin-top: 2vh;
		position: relative;
		top:2vh;
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
</style>