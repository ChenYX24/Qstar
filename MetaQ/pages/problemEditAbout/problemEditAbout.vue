<template>
	<view>
		<view class="title">
			<p>*</p>
			<textarea name="" id="" cols="30" rows="10" placeholder="请输入标题"></textarea>
			<SingleChoice></SingleChoice>
		</view>
	</view>
</template>

<script>
	import SingleChoice from '/components/question/danxuan.vue';
	export default {
		components:{
			SingleChoice
		},
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="less">




</style>
