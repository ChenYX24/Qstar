<template>
	<view>
		<view class="bg">
			<view class="title">
				Register
			</view>
			<form class="Form">
				<div class="Input-margin">
					<input type="text" placeholder="请输入用户名" placeholder-style="color: rgba(187, 187, 199, 1);text-align: center;" class="Input" v-model="formData.username" required>
				</div>
				<div class="Input-margin">
					<input type="email" placeholder="请输入邮箱" placeholder-style="color: rgba(187, 187, 199, 1);text-align: center;" :class="{'Input':trueEmail,'errorWrite':!trueEmail}" v-model="formData.email" @input="checkEmail" required>
				</div>
			    <div class="Input-margin">
					<input type="password" placeholder="请输入密码" placeholder-style="color: rgba(187, 187, 199, 1);text-align: center;" :class="{'Input':isSame,'errorWrite':!isSame}" v-model="formData.password" required>
				</div>
			    <div class="Input-margin">
					<input type="password" placeholder="请再次输入密码" placeholder-style="color: rgba(187, 187, 199, 1);text-align: center;" :class="{'Input':isSame,'errorWrite':!isSame}" v-model="formData.confirm" required>
				</div>
				<div id="gbutton">
					<div id="getcode">
						<input type="text" placeholder="请输入验证码" placeholder-style="color: rgba(187, 187, 199, 1);text-align: center;" class="Input" v-model="formData.verification" required>
					</div>
					<view @tap="startTime">{{ getCodeText }}</view>
				</div>
				<div class="buttonGroup">
			    <button :class="{'active': isFill,'inactive': !isFill}" :disabled="!isFill" type="submit" @tap="submitForm">注册</button>
				<button @tap="Login" class="link">已有账户？立即登录</button>
				</div>
			</form>
		</view>
	</view>
</template>

<script>
import axios from 'axios';
	export default {
		data() {
			return {
				formData: {
						email:"",
				        username: '',
				        password: '',
						 confirm: '',
					verification: ''
				      },
				active:true,
				getCodeText:"获取验证码",
				trueEmail:true,
				code:"123456",
				check:false
			};
		},
		computed: {
			isFill(){
				return this.trueEmail && this.formData.username !== '' && this.formData.password !== '' 
					   && this.formData.confirm !== '' && this.formData.verification !== '';
			},
			enableGetcode(){
				return this.trueEmail && this.active;
			},
			isSame(){
				return this.formData.password == this.formData.confirm;
			}
		},
		methods: {
		    submitForm() {
				if(this.code == this.formData.verification){
					axios.post('http://localhost:8080/register', {
					  username:this.formData.username,
					  email: this.formData.email,
					password: this.formData.password
					})
					.then(response => {
						this.token = response.data;
						console.log(this.token);
					})
					.catch(error => {
					    console.log(error);
					});
				}else{
					alert("验证码错误!");
				}
		    },
			Login(){
				uni.navigateTo({
					url:"/pages/login/login"
				})
			},
			startTime(){
				if(this.enableGetcode && this.check){
					axios.post('http://localhost:8080/getcode', {
					  email: this.formData.email
					})
					.then(response => {
						this.code = response.data;
						console.log(this.code);
					})
					.catch(error => {
					    console.log(error);
					});
					var time = 30;
					this.active = false;
					this.getCodeText = time;
					const intervalId = setInterval(() => {
					        time--;
					        if (time === 0) {
					          clearInterval(intervalId);
					          this.active = true;
					          this.getCodeText = "获取验证码";
					        } else {
					          this.getCodeText = time;
					        }
					      }, 1000);
				}
			},
			checkEmail(){
				this.check = true;
				const emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				this.trueEmail = emailPattern.test(this.formData.email);
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
	button {
		background-color: red;
	}
	.buttonGroup {
		margin-top: 3vh;
		width: 65vw;
	}
	.buttonGroup .inactive {
		color: #000;
		background-color: rgba(216, 214, 219, 100);
	}
	form {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	#getcode {
		display: inline-block;
	}
	#getcode input{
		display: inline-block;
		text-align: center;
		vertical-align: top;
		border: 1px solid rgba(0,0,0,0.6);
		border-radius: 10px;
		width: 35vw;
		height: 3vw;
	}
	#getcode input:focus-within {
		border: 1px solid rgba(200, 196, 218, 50);
	}
	#gbutton{
		margin-top:3vh;
		display: flex;
	}
	#gbutton view{
		text-align: center;
		background-color: rgba(216, 214, 219, 100);
		width: 30vw;
		height: 6vw;
	}
	.Input {
		display: inline-block;
		text-align: center;
		vertical-align: top;
		border: 1px solid rgba(0,0,0,0.6);
		border-radius: 10px;
		width: 65vw;
		height: 3vh;
	}
	.Input:focus-within{
		border: 1px solid rgba(200, 196, 218, 50);
		border-radius: 10px;
	}
	.errorWrite {
		display: inline-block;
		text-align: center;
		vertical-align: top;
		border: 1px solid red;
		border-radius: 10px;
		width: 65vw;
		height: 3vh;
	}
	.errorWrite:focus-within {
		border: 2px solid rgba(200, 196, 218, 50);
		border-radius: 10px;
	}
	.Input-margin {
		margin-top: 3vh;
	}
	
	.link {
		margin-top: 5px;
		font-size: 14px;
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
