<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox">
				<view class="title">
					{{num}}.{{content.title}}
				</view>
				<view class="input-div" id='inputDiv'>
					<textarea name="" id="textArea" cols="30" rows="10" 
					class="text-input"
					@linechange="setHeight"
					v-model="choice"></textarea>
				</view>
			</view>

		</view>
		
		
	</view>
</template>

<script>
	
	
	export default {
		data() {
			return {
				choice:''
			};
		},
		props:{
			num:{
				type:String,
				default:'2'
			},
			content:{
				type:Object,
				default:{
					title:"3333",
					type:'blank',
					choice:['1','2','3','4'],
				}
			},

		},

		methods:{
			setHeight(e){
					var node=document.getElementById('textArea');
					var parent=document.getElementById('inputDiv')
					node.style.height=`${e.detail.height}px`
					parent.style.height=`${e.detail.height}px`
			},
			

		}
	}
</script>

<style lang="less">
.danxuanDisplay{

	margin-top: 10px;
	display: flex;
	width: 100vw;
	// height: 100vh;
	justify-content: center;
}
.box{
	width: 90vw;
}
.inbox{
	border: 1px dashed green;
}
.title{
	font-size: 20px;
}




.input-div{
	display: flex;
	margin: 5px 5px 5px 5px;
	border: 2px solid pink;
	background-color: #f7faf6;
}

.text-input{
	display: flex;
	width: 100%;
	height: 100%;
}

</style>
