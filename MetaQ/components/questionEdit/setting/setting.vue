<template>
	<view class="setting">
		<view class="box">
			<view class="group">
				<view class="setting_text">设置</view>
				<view class="setting_content">
					<view class="setting_class">
						此题目必须回答
						<view class="buttton_">
								<switch class="red-switch"  @change="" color="#8461dd"
								:checked="isChecked" style="transform:scale(0.9)"/>
						</view>
					</view>
					<view class="setting_class">
						显示条件
						<view class="condition_show_jump_setting">
							未设置>
						</view>
					</view>
					<view class="setting_class">
						跳题设置
						<view class="condition_show_jump_setting">
							未设置>
						</view>
					</view>
					
				</view>
		</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'setting',
		data() {
			return {
				isChecked:false  
			};
		}
	}
</script>

<style lang="less">
.setting{
	width: 100vw;
	display: flex;
	justify-content: center;
	margin-top: 10px;
}	

.setting_text{
		height: 4vh;
		 font-size:20px;
		 color: #991cff;
		 margin-bottom: 10px;
		// background-color: #dabff0;
		
		 // text-indent: 2vh;
}
.setting_class{
	padding-left: 10px;
	height: 7vh;
	background: rgba(255, 255, 255, 0.8);
	border: 1px solid rgba(225, 225, 235, 1);
	display: flex;
	align-items: center;
	justify-content: space-between
}
.box{
	background-color: rgba(255, 255, 255, 0.8);
	border-radius: 10px;
	width: 85vw;
}   

.group{
	margin: 20px 20px 20px 20px;
}


.buttton_{
	position: relative;
	display: flex;
	height: 100%;
	align-items: center;
	margin-right: 10px;
}

.condition_show_jump_setting{
	display: flex;
	align-items: center;
	position: relative;
	height: 100%;
	margin-right: 10px;
}


.condition_show_jump_setting{
	color:  rgb(250 98 228);
}





</style>
