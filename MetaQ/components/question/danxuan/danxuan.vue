<template>
	<view>
		<p>选项</p>
		<!-- 默认两个选项 -->
		<view class="single_choice_option">
			<view class="delete_option">
				<img src="/static/question_component/delete.png" alt="">
			</view>
			<view class="write_option">
				<input type="text" name="" id="" placeholder="输入选项">
			</view>
		</view>
		
		<view class="add_options">
			<img src="/static/question_component/add.png" alt="">
			<view class="add_word">
				添加选项
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		 name: 'SingleChoice',
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="less">




</style>
