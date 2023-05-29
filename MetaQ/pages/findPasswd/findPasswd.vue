+<template>
	<view>
		<view class="bg">
			<view class="title">
				FindPasswd
			</view>
			<form class="Form">
			    <div :class="{'Input-margin':trueEmail,'errorWrite':!trueEmail}">
			    	<label class="label">邮箱：</label>
			    	<input type="email" placeholder="请输入邮箱" maxlength="20" class="Input" v-model="formData.email" @input="checkEmail" required>
			    </div>
			    <div :class="{'Input-margin':isSame,'errorWrite':!isSame}">
			    	<label class="label">密码：</label>
			    	<input v-bind:type="this.visible1 ? 'text' : 'password'" maxlength="16" placeholder="请输入密码" class="Input" v-model="formData.password" required>
			    	<image class="img"
			    	v-bind:src="this.visible1 ? '../../static/login/avisible.png' : '../../static/login/invisible.png'"
			    	 @click="changeView1"></image>
			    </div>
			    <div :class="{'Input-margin':isSame,'errorWrite':!isSame}">
			    	<label class="confirm_label">确认密码：</label>
			    	<input class="confirm_input" v-bind:type="this.visible2 ? 'text' : 'password'" maxlength="16" placeholder="请再次输入密码" v-model="formData.confirm" required>
			    	<image class="img"
			    	v-bind:src="this.visible2 ? '../../static/login/avisible.png' : '../../static/login/invisible.png'"
			    	 @click="changeView2"></image>
			    </div>
			    <div id="gbutton">
			    	<div id="getcode">
			    		<label class="label">验证码：</label>
			    		<input type="text" placeholder="请输入验证码" class="Input" v-model="formData.verification" required>
			    	</div>
			    	<view :style="{'background-color': active ? 'rgba(255,255,255,0.8)' : 'rgba(208, 202, 217, 1)'}" @tap="startTime">{{ getCodeText }}</view>
			    </div>
				<div class="buttonGroup">
					<button :class="{'active': isFill,'inactive': !isFill}" :disabled="!isFill" type="submit" @tap="changepasswd">修改密码</button>
					<button @click="Login" class="link">立即登录</button>
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
				        password: '',
						 confirm: '',
					verification: ''
				      },
				active:true,
				getCodeText:"获取验证码",
				trueEmail:true,
				code:"123456",
				success:"",
				check:false,
				visible1:false,
				visible2:false
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
		    changepasswd() {
				axios.post('http://localhost:8080/checkcode', {
				  email: this.formData.email,
				  code:  this.formData.verification
				})
				.then(response => {
					if(response.data == true){
						axios.post('http://localhost:8080/changepasswd', {
							email:    this.formData.email,
							password: this.formData.password
						})
						.then(response => {
							console.log("响应值",response.data);
							if(response.data == true){
								uni.showToast({
								  title: '修改密码成功',  
								  icon: 'none'
								}); 
								uni.navigateTo({
									url:"/pages/login/login"
								})
							}
							else{
								uni.showToast({
								  title: '修改密码失败',  
								  icon: 'none'
								}); 
							}
						})
						.catch(error => {
						    console.log(error);
						});
					}
					else{
						uni.showToast({
						  title: '验证码错误',  
						  icon: 'none'
						}); 
					}
				})
				.catch(error => {
				    console.log(error);
				});
		    },
			Login(){
				uni.navigateTo({
					url:"/pages/login/login"
				})
			},
			startTime(){
				if(this.enableGetcode && this.check){
					axios.post('http://localhost:8080/sendcode', {
					  email: this.formData.email
					})
					.then(response => {
						if(response.data == false){
							uni.showToast({
							  title: '获取验证码失败',  
							  icon: 'none'
							}); 
						}
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
			changeView1(){
				this.visible1 = !this.visible1;
			},
			changeView2(){
				this.visible2 = !this.visible2;
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
	#getcode {
		display: inline-block;
		display: flex;
		align-items: center;
		/* border: 1px solid rgba(0,0,0,0.6); */
		border-radius: 20px;
		width: 47.7vw;
		height: 6.2vh;
		background-color: rgba(255,255,255,0.8);
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
	}
	#getcode:focus-within {
		border: 1px solid #8431de;
		border-radius: 20px;
	}
	#getcode input{
		display: inline-block;
		text-align: left;
		vertical-align: top;
	}
	#getcode .label {
		width: 29vw;
	}
	#gbutton{
		margin-top:3vh;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	#gbutton view{
		display: flex;
		align-items: center;
		justify-content: center;
		text-align: center;
		/* border: 1px solid rgba(0,0,0,0.6); */
		border-radius: 20px;
		margin-left: 1vw;
		width: 28.3vw;
		height: 6.2vh;
		/* background-color: rgba(255,255,255,0.8); */
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
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
	.confirm_input {
		display: inline-block;
		text-align: left;
		vertical-align: top;
		width: 37vw;
	}
	.confirm_label {
		display: inline-block;
		margin-left: 5vw;
		text-align: left;
		width: 21vw;
	}
	.img {
		display: inline-block;
		width: 9vw;
		height: 4vh;
		margin-right: 2vw;
	}
	.label {
		display: inline-block;
		margin-left: 5vw;
		text-align: left;
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