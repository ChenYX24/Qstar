<template>
	<view>
		<view class="bg">
			<view class="title">
				Register
			</view>
			<form @submit.prevent="submitForm" class="Form">
				<div id="username">
				  <label class="Label">用户名：</label>
				  <input type="text"  class="Input" v-model="formData.username" required>
				</div>
			    <div>
			      <label class="Label">邮箱：</label>
			      <input type="email" :class="{'Input':trueEmail,'errorWrite':!trueEmail}" v-model="formData.email" @input="checkEmail" required>
			    </div>
			    <div>
			      <label class="Label">密码：</label>
			      <input type="password" :class="{'Input':isSame,'errorWrite':!isSame}" v-model="formData.password" required>
			    </div>
				<div id="confirmDiv">
					<label class="Label" id="Confirm">确认密码：</label>
					<input type="password" :class="{'Input':isSame,'errorWrite':!isSame}" v-model="formData.confirm" required>
				</div>
				<div id="getcode">
					<label class="Label">验证码:</label>
					<input type="text" class="Input" v-model="formData.verification" required>
					<button :disabled="!enableGetcode" @tap="startTime">{{ getCodeText }}</button>
				</div>
				<div class="buttonGroup">
			    <button :class="{'active': isFill,'notlogin': !isFill}" :disabled="!isFill" type="submit" @tap="submitForm">注册</button>
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
				trueEmail:false,
				code:" "
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
		        
		        //console.log(this.formData);
		    },
			Login(){
				uni.navigateTo({
					url:"/pages/login/login"
				})
			},
			startTime(){
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
				var time = 3;
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
			},
			checkEmail(){
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
	#confirmDiv input{
		width: 180px;
	}
	#Confirm{
		width: 80px;
		margin-left: 0px;
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
	#getcode {
		display: table;
		width: 100%;
	}
	#getcode button{
		display: table-cell;
		font-size: 5px;
		width: 100px;
		background-color: rgba(216, 214, 219, 20);
	}
	#getcode label{
		width: 60px;
		margin-left: 0px;
		vertical-align: top;
	}
	#getcode input {
		width: 100px;
	}
	.Input {
		display: inline-block;
		border-bottom: 2px solid #000;
		border-top: 0px;
		border-left: 0px;
		border-right: 0px;
		vertical-align: top;
		width: 212px;
	}
	.Input:focus-within{
		border-bottom: 2px solid rgba(200, 196, 218, 50)!important;
	}
	.errorWrite {
		display: inline-block;
		border-bottom: 2px solid rgba(255, 0, 0, 0.3);
		border-top: 0px;
		border-left: 0px;
		border-right: 0px;
		vertical-align: top;
		width: 212px;
	}
	.errorWrite:focus-within {
		border-bottom: 2px solid rgba(200, 196, 218, 50);
	}
	.Label {
		display: inline-block;
		width: 48px;
		text-align: left;
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
	#username label{
		width: 64px;
		margin-left: 0px;
		vertical-align: top;
	}
	#username input{
		width: 196px;
		margin-left: 0px;
		vertical-align: top;
	}
</style>
