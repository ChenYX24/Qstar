<template>
	<view>
		<view class="bg">
			<view class="title">
				Login
			</view>
			<form @submit.prevent="submitForm" class="Form">
				<div :class="{'Input-margin':trueEmail,'errorWrite':!trueEmail}">
					<label class="label">邮箱：</label>
					<input type="email" placeholder="请输入邮箱" class="Input" maxlength="20" v-model="formData.email" @input="checkEmail" required>
				</div>
			    <div class="Input-margin">
					<label class="label">密码：</label>
					<input v-bind:type="this.visible ? 'text' : 'password'" maxlength="16" placeholder="请输入密码" class="Input" v-model="formData.password" required>
					<image class="img"
					v-bind:src="this.visible ? '../../static/login/avisible.png' : '../../static/login/invisible.png'"
					 @click="changeView"></image>
					 <!-- :src="{'../../static/login/visible.png':this.visible,'../../static/login/invisible.png':!this.visible}" -->
				</div>
				<div class="buttonGroup">
					<view :class="{'active': isFill,'inactive': !isFill}" :disabled="!isFill" type="submit" @tap="submitForm">登录</view>
					<button @click="Register" class="link">没有账户？点此注册</button>
					<button @click="findPasswd" class="link">忘记密码？点此找回</button>
				</div>
			  </form>
		</view>
	</view>
</template>

<script>
import axios from 'axios';
	export default {
		onLoad(option){
			console.log("option_flag:",option.flag)
			this.flag = option.flag;
		},
		data() {
			return {
				formData: {
				        email: "",
				        password: ""
				      },
				token:"",
				trueEmail:true,
				check:false,
				visible:false,
				flag:false
			};
		},
		computed: {
			isFill(){
				return this.trueEmail !== false && this.formData.password !== '' && this.check;
			}
		},
		methods: {
		  //   submitForm() {
				// axios.post('https://metaq.scutbot.icu/login'
				// 			/*'http://localhost:8080/login'*/, {
		  //           email: this.formData.email,
				// 	password: this.formData.password
				// 	})
		  //           .then(response => {
		  //             this.token = response.data;
				// 	  if(this.token == " ")
				// 	  {
				// 		  uni.showToast({
				// 		    title: '登录失败',  
				// 		    icon: 'none'
				// 		  }) 
				// 	  }else{
				// 		  console.log("token",this.token);
				// 		  localStorage.setItem('token', JSON.stringify(this.token));
				// 		  console.log("localStorage",localStorage.getItem('token'));
				// 		  uni.reLaunch({
				// 		  		url:"/pages/myQ/myQ"
				// 		  	})
				// 	  }
		  //           })
		  //           .catch(error => {
		  //             console.log(error);
		  //           });
		  //   },
		  submitForm(){
			  console.log(this.$store.state.host + '/login');
			uni.request({
				url: this.$store.state.host + '/login',
				//url:'http://localhost:8080/login',
				method: 'POST',
				header:{
					'Content-Type' : 'application/json',
					//token : uni.getStorageSync("token")
				},
				data: {
					email: this.formData.email,
					password: this.formData.password
				},
				success: res => {
					console.log(res);
					this.token = res.data;
					if(this.token == " ")
					{
					  uni.showToast({
					    title: '登录失败',  
					    icon: 'none'
					  }) 
					}else{
					  const Token = JSON.stringify(this.token);
					  console.log("token",Token);
					  uni.setStorageSync('token',Token)
					  console.log("存在缓存中的:",uni.getStorageSync('token'))
					  //localStorage.setItem('token', JSON.stringify(this.token));
					  //console.log("localStorage",localStorage.getItem('token'));
					  console.log("flag：",this.flag)
					  if(this.flag){
						  console.log("返回填写页面")
						  uni.navigateBack();
					  }else{
						  uni.reLaunch({
						  		url:"/pages/myQ/myQ"
						  	})
					  }
					  
					}
				},
				fail: () => {},
				complete: () => {}
			});
  
		  },
			checkEmail(){
				this.check = true;
				const emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				this.trueEmail = emailPattern.test(this.formData.email);
			},
			changeView(){
				this.visible = !this.visible;
			},
			Register(){
				uni.navigateTo({
					url:"/pages/register/register"
				})
			},
			findPasswd(){
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
	.buttonGroup {
		margin-top: 3vh;
		width: 79vw;
		
	}
	.buttonGroup button {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 1.5vh;
		background-color: rgba(230, 230, 240, 1);
		box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
		height: 6.2vh;
	}
	.buttonGroup .active {
		display: flex;
		justify-content: center;
		align-items: center;
		height: 10vh;
		font-size: 24px;
		font-weight: 400;
		color: rgba(0, 0, 0, 0.75);
		text-align: center;
		vertical-align: top;
		border-radius: 12px;
		background: linear-gradient(90deg, rgba(224, 222, 250, 0.75) 0%, rgba(255, 235, 244, 0.75) 100%);
		box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
		/* filter: blur(4px); */
	}
	.buttonGroup .inactive {
		display: flex;
		justify-content: center;
		align-items: center;
		height: 10vh;
		font-size: 24px;
		font-weight: 400;
		color: rgba(128, 128, 128, 1);
		text-align: center;
		vertical-align: top;
		border-radius: 12px;
		background-color: rgba(230, 230, 240, 1);
		box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
		/* filter: blur(4px); */
	}
	form {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.Input {
		display: inline-block;
		text-align: left;
		vertical-align: top;
		/* width: 45vw; */
	}
	/* .Input:focus-within{
		border: 1px solid rgba(200, 196, 218, 50);
		border-radius: 10px;
	} */
	.errorWrite {
		margin-top: 3vh;
		display: flex;
		align-items: center;
		text-align: left;
		vertical-align: top;
		border: 1px solid red;
		border-radius: 20px;
		width: 77vw;
		height: 6.2vh;
		background-color: rgba(255,255,255,0.8);
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
	}
	.errorWrite:focus-within {
		border: 1px solid #8431de;
		border-radius: 20px;
	}
	.Input-margin {
		margin-top: 3vh;
		display: flex;
		align-items: center;
		/* border: 1px solid rgba(0,0,0,0.6); */
		border-radius: 20px;
		width: 77vw;
		height: 6.2vh;
		background-color: rgba(255,255,255,0.8);
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
	}
	.Input-margin:focus-within{
		border: 1px solid #8431de;
		border-radius: 20px;
	}
	.img {
		display: inline-block;
		width: 9vw;
		height: 4vh;
		margin-right: 2vw;
	}
	.label {
		display: inline-block;
		width: 20vw;
		text-align: right;
	}
	.link {
		margin-top: 5px;
		font-size: 14px;
		color: rgba(128, 128, 128, 1);
		border-radius: 12px;
		background: rgba(230, 230, 240, 1);
		box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
	}
	.title {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		font-size: 48px;
		font-weight: 400;
		letter-spacing: 0px;
		line-height: 57.6px;
		color: rgba(0, 0, 0, 1);
		text-align: center;
		vertical-align: top;
		font-family: 'ali', sans-serif;
	}
</style>
