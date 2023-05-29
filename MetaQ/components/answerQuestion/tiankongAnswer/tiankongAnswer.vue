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
					v-model="answer"></textarea>
				</view>
			</view>

		</view>
		
		
	</view>
</template>

<script>
	
	
	export default {
		data() {
			return {
				answer:''
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
	border-radius: 20px;
	background: rgba(255, 255, 255, 0.8);
	box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
}
.inbox{
	// border: 1px dashed green;
}
.title{
	margin:10px 0px 5px 20px;
	font-size: 20px;
}




.input-div{
	display: flex;
	margin: 10px 20px 20px 20px;
	border: 1px solid rgba(225, 225, 235, 1);
	border-radius: 3px;
	background-color: #ffffff;
}

.text-input{
	display: flex;
	width: 100%;
	height: 100%;
}

textarea{
	font-size: 22px;
}
</style>
