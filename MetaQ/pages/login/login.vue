<template>
	<view>
		<view class="bg">
			<view class="title">
				Login
			</view>
			<form @submit.prevent="submitForm" class="Form">
			    <div>
			      <label class="Label">邮箱：</label>
			      <input type="text"  class="Input" v-model="formData.email" required>
			    </div>
			    <div>
			      <label class="Label">密码：</label>
			      <input type="password" class="Input" v-model="formData.password" required>
			    </div>
				<div class="buttonGroup">
			    <button :class="{'active': isFill,'notlogin': !isFill}" :disabled="!isFill" type="submit" @tap="submitForm">登录</button>
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
				        email: '',
				        password: ''
				      },
				token:''
			};
		},
		computed: {
			isFill(){
				return this.formData.email !== '' && this.formData.password !== '';
			}
		},
		methods: {
		    submitForm() {
				var self = this;
				axios.post('http://localhost:8080/login', {
		            email: self.formData.email,
					password: self.formData.password
					})
		            .then(response => {
		              self.token = response.data;
					  console.log(self.token);
		            })
		            .catch(error => {
		              console.log(error);
		            });
				
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
		margin-top: 20px;
		transition: transform 0.5s;
	}
	.buttonGroup :hover {
		/* 悬浮效果 */
		transform: translateY(-2px);
		/*盒子阴影*/ 
		box-shadow: 0px 5px 10px 4px rgba(200, 196, 218, 50); 
	}
	.buttonGroup .notlogin {
		color: #000;
		background-color: rgba(216, 214, 219, 100);
	}
	form {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	form div {
		margin-top: 20px;
	}
	form .link {
		margin-top: 5px;
		font-size: 14px;
	}
	
	.Input {
		display: inline-block;
		border-bottom: 1px solid #000;
		border-top: 0px;
		border-left: 0px;
		border-right: 0px;
		vertical-align: top;
	}
	.Input :focus{
		border-bottom: 5px solid rgba(200, 196, 218, 50);
	}
	.Label {
		display: inline-block;
		width: 50px;
		text-align: right;
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
