<template>
	<view class="save">
		
	</view>
</template>

<script>
	export default {
		name:"save",
		data() {
			return {
				
			};
		},
		props: {
			isShow: {
				type: Boolean,
				default: false,
			},
		},
	}
</script>

<style lang="less">
	.save{
		z-index: 9999;
		height: 45vh;
		width: 80vw;
		border-radius: 42px;
		background: linear-gradient(90deg, rgba(255, 255, 255, 1) 0%, rgba(255, 242, 255, 1) 100%);
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
		position: fixed;
		bottom: -100%;
		left: 0;
		right: 0;	
		transition: right,left,bottom 0.5s ease-in-out;
	}
	.save.show{
		bottom: 50%;
	}
</style>