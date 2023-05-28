<template>
	<view class="slider-setting">
		
		<view class="line">
			
		</view>
		<view class="box">
			最小值
			<view class="input-area">
				<input type="number" placeholder="0" maxlength="3" v-model="setting[0]">
			</view>
		</view>
		<view class="box">
			最小值显示文本
			<view class="input-area">
				<input type="text" placeholder="非常差"  v-model="setting[1]">
			</view>
		</view>
		<view class="box">
			最大值
			<view class="input-area">
				<input type="number" placeholder="100" maxlength="3" v-model="setting[2]">
			</view>
		</view>
		<view class="box">
			最大值显示文本
			<view class="input-area">
				<input type="text" placeholder="非常好" v-model="setting[3]">
			</view>
		</view>
		<view class="box">
			步数 
			<view class="input-area">
				<input type="number" placeholder="0" maxlength="2" v-model="setting[4]">
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				setting:[0,'非常差',100,'非常好',0]
				// min_value:0,
				// min_text:'',
				// max_value:100,
				// max_text:'',
				// setp:0
			};
		}
	}
</script>



<style lang="less">

.line{
	// margin-top: 10px;
	height: 5px;
	    background: linear-gradient(233deg, #ff43f8 0%, #9a86b5 38.89%, #b092be 67.78%, #613dd8 100%);
}
	
.box{
	margin-top: 2px;
	display: flex;
	align-items: center;
	width: 100vw;
	height: 5vh;
	// border: 2px solid red;
}
.input-area{
	display: flex;
	justify-content: center;
	align-items: center;
	position: absolute;
	height: 5vh;
	width: 15vw;
	// border: 2px solid gray;
	// box-shadow: 0px 2px 4px 0px rgba(136,63,143,0.25);
	left: 80vw;
}

.input-area input{
	text-align: right;
}

</style>
