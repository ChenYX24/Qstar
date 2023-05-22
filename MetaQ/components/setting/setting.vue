<template>
	<view class="setting">
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
}	

.setting_text{
		height: 4vh;
		 font-size:18px;
		background-color: #dabff0;
		 text-indent: 2vh;
}
.setting_class{
	padding-left: 10px;
	height: 5vh;
	// border: 2px solid red;
	display: flex;
	align-items: center
}



.buttton_{
	position: relative;
	display: flex;
	height: 100%;

	// border: 2px solid green;
	left: 48vw;
	align-items: center
}

.condition_show_jump_setting{
	display: flex;
	align-items: center;
	position: relative;
	height: 100%;

	// border: 2px solid green;
	left: 60vw;
}


.condition_show_jump_setting{
	color:  rgb(250 98 228);
}





</style>
