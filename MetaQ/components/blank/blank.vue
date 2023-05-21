<template>
	<view class="Blank">
		<view class="title">
			{{content.title}}
		</view>
		<textarea class="content" maxlength="900">
			
		</textarea>
	</view>
</template>

<script>
	export default {
		name:"blank",
		props:{
			content:{
				type:Object,
				default:{
					title:"22",
					type:'blank',
					choice:[],
				}
				
			}
		},
		data() {
			return {

			};
		}
	}
</script>

<style lang="less">
	.content{
		background-color: rgba(255, 255, 255,1);
		height: 10vh;
	}
</style>