<template>
	<view>
		<view class="bg">
			<view class="title">
				Login
			</view>
			<form @submit.prevent="submitForm" class="Form">
				<div class="Input-margin">
					<input type="email" placeholder="请输入邮箱" :class="{'Input':trueEmail,'errorWrite':!trueEmail}" v-model="formData.email" @input="checkEmail" required>
				</div>
			    <div class="Input-margin">
					<input type="password" placeholder="请输入密码" class="Input" v-model="formData.password" required>
				</div>
				<div class="buttonGroup">
			    <button :class="{'active': isFill,'inactive': !isFill}" :disabled="!isFill" type="submit" @tap="submitForm">登录</button>
				<button @click="Register" class="link">没有账户？点此注册</button>
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
				        email: "",
				        password: ""
				      },
				token:"",
				trueEmail:true,
			};
		},
		computed: {
			isFill(){
				return this.trueEmail !== '' && this.formData.password !== '';
			}
		},
		methods: {
		    submitForm() {
				axios.post('http://localhost:8080/login', {
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
				
		    },
			checkEmail(){
				const emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				this.trueEmail = emailPattern.test(this.formData.email);
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
