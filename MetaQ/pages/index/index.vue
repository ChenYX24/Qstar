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
				<view class="button2" @tap="findPassword">
					找回密码
				</view>
			</view>
		</view>
	</view>
	
</template>

<script>
	import axios from 'axios';
	export default {
		data() {
			return {
				flag:false
			}
		},
		onLoad(options) {
			//console.log(options);
			if(options){this.flag=options.flag}
		},
		mounted(options){
			if(!this.flag){
				//自动登录
				const token = localStorage.getItem('token');
				console.log(token);
				if (token) {
				  // 发送请求进行自动登录
				  axios.post(/*'https://metaq.scutbot.icu/login'*/
				  			'http://localhost:8080/autologin', {token})
				      .then(response => {
				        var response = response.data;
						console.log(response);
				  	  if(response == "fail")
				  	  {
				  		  uni.showToast({
				  		    title: '登录失败',  
				  		    icon: 'none'
				  		  }); 
				  	  }else{
						  console.log(response);
						  console.log(localStorage.getItem('token'));
						  console.log("自动登录成功！");
						  uni.reLaunch({
						  		url:"/pages/myQ/myQ"
						  	})
						}
					  })
				      .catch(error=> {
				        console.log(error);
				      });
				}
			}

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
			},
			findPassword(){
				uni.navigateTo({
					url:"/pages/findPasswd/findPasswd"
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
		margin-top: 3rem;
		display: grid;
		justify-content: center;
		justify-items: center;
		gap: 2rem;
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
	.button2{
		width: 80vw;
		height: 13vw;
		opacity: 1;
		border-radius: 12px;
		background: rgba(230, 230, 240, 1);
		box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
		backdrop-blur: 4px;
		font-size: 20px;
		font-weight: 400;
		display: flex;
		justify-content: center;
		align-items: center;
		color: rgba(128, 128, 128, 0.75);
	}
</style>
