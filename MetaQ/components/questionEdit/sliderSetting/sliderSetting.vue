<template>
	<view class="slider-setting">
		
<!-- 		<view class="line">
		</view> -->
		<view class="slider-box">
			<view class="group">
				
				<view class="xuanxiang_text">
					{{num}}.
					<input type="text"  placeholder="点击编辑问题" class='question-input' v-model="question">
					<view class="danxuanti-label">
						[滑动条]
					</view>

				</view>    
				
				
				<view class="box">
					<view class="text">
						最小值
					</view>
					<view class="input-area">
						<input type="number" placeholder="0" maxlength="3" v-model="setting[0]">
					</view>
				</view>
				<view class="box">
					<view class="text">
						最小值显示文本
					</view>
					
					<view class="input-area">
						<input type="text" placeholder=""  v-model="setting[1]">
					</view>
				</view>
				<view class="box">
					<view class="text">
						最大值
					</view>
					
					<view class="input-area">
						<input type="number" placeholder="100" maxlength="3" v-model="setting[2]">
					</view>
				</view>
				<view class="box">
					<view class="text">
						最大值显示文本
					</view>
					
					<view class="input-area">
						<input type="text" placeholder="" v-model="setting[3]">
					</view>
				</view>
				<view class="box">
					<view class="text">
						步数 
					</view>
					
					<view class="input-area">
						<input type="number" placeholder="0" maxlength="2" v-model="setting[4]">
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				setting:[0,'',100,'',0],
				// question:''
				question:this.questionProp,
				
			};
		},
		props:{
			num:{
				type:Number,
				default:-1
			},
			questionProp:{
				type:String,
				default:""
			}
			
		}
	}
</script>



<style lang="less">

// .line{
// 	// margin-top: 10px;
// 	height: 5px;
// 	    background: linear-gradient(233deg, #ff43f8 0%, #9a86b5 38.89%, #b092be 67.78%, #613dd8 100%);
// }

.slider-setting{  
	display: flex;
	justify-content: center;
	width: 100vw;     
}

.slider-box{
	background-color: rgba(255, 255, 255, 0.8);
	border-radius: 10px;
	width: 85vw;
	border: 2px solid rgba(225, 225, 235, 1);
}

.group{
	margin: 25px 20px 25px 20px;
}

.xuanxiang_text{    
	height: 4vh;    
	font-size:18px;       
	color: #991cff;
	margin-bottom: 10px;
    display: flex;
	align-items: center;
}   
.question-input{
	font-size: 18px;
	margin-left: 8px;
	color: black;
	// border: 2px solid red;
}

.danxuanti-label{
	
	display: flex;
	align-items: center;
	flex-shrink: 0;
	color: rgb(237 64 225);
	font-size: 14px;
}



.box{
	border-radius: 3px;
	display: flex;
	align-items: center;
	background: rgba(255, 255, 255, 0.8);
	border: 1px solid rgba(225, 225, 235, 1);
	height: 6vh;
	justify-content: space-between;
	// border: 2px solid red;
}
.text{
	margin-left: 10px;
}
.input-area{
	
	display: flex;
	justify-content: center;
	align-items: center;
	height: 5vh;
	// width: 15vw;
	// border: 2px solid gray;
	// box-shadow: 0px 2px 4px 0px rgba(136,63,143,0.25);
	// left: 80vw;
}

.input-area input{
	margin-right: 10px;
	width: 15vw;
	text-align: right;
	// border: 2px solid red;
}


</style>
