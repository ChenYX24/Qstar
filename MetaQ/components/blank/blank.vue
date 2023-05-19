<template>
	<view class="Blank">
		<view class="title">
			{{title}}
		</view>
		<textarea class="content" maxlength="900"></textarea>
	</view>
</template>

<script>
	export default {
		name:"blank",
		props:{
		},
		data() {
			return {
				title:'this is a title'
			};
		}
	}
</script>

<style lang="less">
	.content{
		background-color: rgba(255, 255, 255,1);
	}
</style>