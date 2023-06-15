<template>
	<view class="operateEdit">
		<view class="operate-box">
			<view class="operate" @click="emitUp">
				<!-- <img src="/static/operationEditQuestion/up.png" alt=""> -->
				<image src="/static/operationEditQuestion/up.png" mode="" class="big-image"></image>
			</view>
			<view class="operate" @click="emitDown">
				<!-- <img src="/static/operationEditQuestion/down.png" alt=""> -->
				<image src="/static/operationEditQuestion/down.png" mode="" class="big-image"></image>
			</view>
			<view class="operate" @click="emitDelete">
				<!-- <img src="/static/question_component/delete.png" alt=""> -->
				<image src="/static/operationEditQuestion/delete.png" mode="" class="big-image"></image>
			</view>
			<view class="operate">
				<!-- <img src="/static/operationEditQuestion/attach.png" alt=""> -->
				<image src="/static/operationEditQuestion/attach.png" mode="" class="big-image"></image>
			</view>
		</view>
		
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		},
		methods:{
			emitDelete(){
				this.$emit('deleteOption');
			},
			emitUp(){
				this.$emit('upOption');
			},
			emitDown(){
				this.$emit('downOption');
			}
		}
	}
</script>

<style lang="less">
	
.operateEdit{
	border-radius: 5px;
	width: 100%;
	height: 7vh;
	background: rgba(255, 255, 255, 0.8);
	border: 1px solid rgba(225, 225, 235, 1);
	// border: 1px solid red;
}

.operate-box{
	height: 100%;
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	// border: 2px solid red;
	margin: 0 5px 0 5px;
}

.operate{
	display: flex;
	height: 100%;
	// width: 75%;
	
}
.big-image{
    height: 6.5vh;
    width: 6.5vh;
}
// .operate img{
// 	// border: 1px solid green;
// 	height:100%;
// }

// .operate:nth-child(3){
// 	height: 80%;
// }

// .operate:nth-child(4){
// 	height: 80%;
// }



</style>
