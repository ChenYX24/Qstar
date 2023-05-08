<template>
	<view class="bgimg">
		<view class="bg">
			<view class="title">
				Meta Q
			</view>
			<view class="buttonGroup">
				<view class="button" @tap="Login">
					登陆
				</view>
				<view class="button" @tap="Register">
					注册
				</view>
			</view>
		</view>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
			}
		},
		onLoad() {
			
		},
		methods: {
			Login(){
				uni.navigateTo({
					url:"/pages/login/login"
				})
			},
			Register(){
				uni.navigateTo({
					url:"/pages/register/register"
				})
			}
		}
	}
</script>

<style>
	*{
		margin: 0%;
	}
	@font-face {
	  font-family: 'ali';
	  src: url('/font/ali/Alimama_ShuHeiTi_Bold.ttf') format('truetype');
	}
	.bg {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
        position: fixed;
	    top: 0;
	    left: 0;
	    right: 0;
	    bottom: 0;
		width: 100vw;
		height: 100vh;
		opacity: 1;
		background: url('/static/index/bg.png') center center / 225% 130% no-repeat,  linear-gradient(225deg, rgba(230, 247, 255, 1) 0%, rgba(255, 254, 247, 1) 38.89%, rgba(233, 229, 254, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
	}
	.title {
		font-size: 48px;
		font-weight: 400;
		letter-spacing: 0px;
		line-height: 57.6px;
		color: rgba(0, 0, 0, 1);
		text-align: left;
		vertical-align: top;
		font-family: 'ali', sans-serif;
	}
	.buttonGroup{
		margin-top: 5.9375rem;
		display: grid;
		justify-content: center;
		justify-items: center;
		gap: 5rem;
	}
	.button{
	  width: 80vw;
	  height: 22vw;
	  opacity: 1;
	  border-radius: 12px;
	  background: linear-gradient(90deg, rgba(224, 222, 250, 0.75) 0%, rgba(255, 235, 244, 0.75) 100%);
	  box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
	  backdrop-blur: 4px;
	  font-size: 24px;
	  font-weight: 400;
	  display: flex;
	  justify-content: center;
	  align-items: center;
	}
</style>
