<template>
	<view v-if="Type==1" class="saveContainer">
		<view class="save">
			<view class="navBar">
				<image src="../../static/save/back.png" mode="aspectFit" class="back" @tap="goBack"></image>
			</view>
			<image :src="isQrcode?qrcode:checkImg" mode="aspectFit" class="checkImg"></image>
			<view class="button" @tap="save">
				保存
			</view>
			<view class="button2" @tap="push">
				发布
			</view>
		</view>	
	</view>
	<view v-else class="saveContainer">
		<view class="save">
			<view class="navBar">
				<image src="../../static/save/back.png" mode="aspectFit" class="back" @tap="goBack"></image>
			</view>
			<image :src="isQrcode?qrcode:checkImg" mode="aspectFit" class="checkImg"></image>
			<view class="button" @tap="copyText">
				{{button1Text}}
			</view>
			<view class="button2" @tap="saveQRCode">
				{{button2Text}}
			</view>
		</view>	
	</view>

</template>

<script>
	import axios from 'axios'
	import store from '/store/index.js'
	export default {
		name:"save",
		data() {
			return {
				b1:0,
				isQrcode:false,
				checkImg:'/static/save/checkImg.png',
				qrcode:'/static/save/qrcode.png'//TODO
			};
		},
		
		computed:{
			
			button1Text(){
				if(this.b1==0)
				{
					return'分享填写链接'
				}
				if(this.b1==1)
				{
					return '已复制到剪贴板'
				}
				if(this.b1==2)
				{
					return '更换二维码背景'
				}
			},
			button2Text(){
				if(this.isQrcode)
				{
					return'保存到相册'
				}
				else{
					return'更换二维码背景'
				}
			}
		},
		watch: {
		    isShow(newVal, oldVal) {  
		      if(!oldVal&&newVal)
			  {
				this.b1=0
				this.isQrcode=false
			  }  
		    }
		},
		props: {
			questionNirePorps:{
				type:Object
			},
			isShow: {
				type: Boolean,
				default: false,
			},
			Type:{
				type:Number,
				default:0
			},
			id:{
				type:Number,
				default:-1
			}
		},
		methods:{
			save(){
				this.questionNirePorps['commit'] = false;
				console.log("save",this.questionNirePorps);
				//axios.defaults.headers.common['token'] = localStorage.getItem('token');
				console.log("this.questionNirePorps",this.questionNirePorps);
				if(this.$store.state.qnid==-1){
					console.log("创建并保存问卷");
					uni.request({
						url: this.$store.state.host + '/create',
						method: 'POST',
						header:{
							'Content-Type' : 'application/json',
							token : uni.getStorageSync("token")
						},
						data: this.questionNirePorps,
						success: res => {
							console.log(res.data);
						},
						fail: () => {},
						complete: () => {}
					});
					// axios.post('https://metaq.scutbot.icu/create'
					// 			/*'http://localhost:8080/create'*/, 
					// 		this.questionNirePorps
					// 	)
					// 	.then(response => {
					// 	  console.log(response.data);
					// 	})
					// 	.catch(error => {
					// 	  console.log(error);
					// 	});
					// uni.navigateTo({
					// 	url:"/pages/myQ/myQ"
					// })
				}else{
					console.log("保存已创建问卷");
					this.questionNirePorps['id'] = this.$store.state.qnid;
					this.$store.state.qnid = -1;
					uni.request({
						url: this.$store.state.host + '/save',
						method: 'POST',
						header:{
							'Content-Type' : 'application/json',
							token : uni.getStorageSync("token")
						},
						data: this.questionNirePorps,
						success: res => {
							console.log(res.data);
						},
						fail: () => {},
						complete: () => {}
					});
					// axios.post('https://metaq.scutbot.icu/save'
					// 			/*'http://localhost:8080/save'*/, 
					// 		this.questionNirePorps,
					// 	)
					// 	.then(response => {
					// 	  console.log(response.data);
					// 	})
					// 	.catch(error => {
					// 	  console.log(error);
					// 	});
					uni.navigateTo({
						url:"/pages/myQ/myQ"
					})
				}
			},
			push(){
				console.log("push",this.questionNirePorps);
				this.questionNirePorps['commit'] = true;
				//axios.defaults.headers.common['token'] = localStorage.getItem('token');
				console.log("this.questionNirePorps",this.questionNirePorps);
				if(this.$store.state.qnid==-1){
					console.log("创建并发布问卷");
					uni.request({
						url: this.$store.state.host + '/create',
						method: 'POST',
						header:{
							'Content-Type' : 'application/json',
							token : uni.getStorageSync("token")
						},
						data: this.questionNirePorps,
						success: res => {
							console.log(res.data);
						},
						fail: () => {},
						complete: () => {}
					});
					// axios.post('https://metaq.scutbot.icu/create'
					// 			/*'http://localhost:8080/create'*/, 
					// 		this.questionNirePorps
					// 	)
					// 	.then(response => {
					// 	  console.log(response.data);
					// 	})
					// 	.catch(error => {
					// 	  console.log(error);
					// 	});
					uni.navigateTo({
						url:"/pages/myQ/myQ"
					})
				}else{
					console.log("发布已创建问卷");
					this.questionNirePorps['id'] = this.$store.state.qnid;
					this.$store.state.qnid = -1;
					uni.request({
						url: this.$store.state.host + '/save',
						method: 'POST',
						header:{
							'Content-Type' : 'application/json',
							token : uni.getStorageSync("token")
						},
						data: this.questionNirePorps,
						success: res => {
							console.log(res.data);
						},
						fail: () => {},
						complete: () => {}
					});
					// axios.post('https://metaq.scutbot.icu/save'
					// 			/*'http://localhost:8080/save'*/, 
					// 		this.questionNirePorps,
					// 	)
					// 	.then(response => {
					// 	  console.log(response.data);
					// 	})
					// 	.catch(error => {
					// 	  console.log(error);
					// 	});
					uni.navigateTo({
						url:"/pages/myQ/myQ"
					})
				}
				uni.navigateTo({
					url:"/pages/myQ/myQ"
				})
			},
			goBack(){
				if(this.isQrcode)
				{
					this.isQrcode=!this.isQrcode
					this.b1=0
				}
				else{
					this.$emit('updateSave')
				}
			},
			copyText() {
			if(this.b1==0)
			{
				//axios.defaults.headers.common['token'] = localStorage.getItem('token');
				//console.log("token",localStorage.getItem('token'));
				uni.request({
					url: this.$store.state.host + '/share',
					method: 'POST',
					header:{
						'Content-Type' : 'application/json',
						token : uni.getStorageSync("token")
					},
					data: {
						id:this.$store.state.qnid,
						flag:false
					},
					success: res => {
						const textToCopy = res.data;
						uni.setClipboardData({
						  data: textToCopy,  
						  success: () => {  
						    uni.showToast({  
						      title: '已复制到剪贴板',  
						      icon: 'success',  
						    });
								this.b1=1
						  },  
						  fail: (err) => {  
						    console.error('Error copying text: ', err);  
						    uni.showToast({  
						      title: '复制失败',  
						      icon: 'none',  
						    });  
								this.b1=0
						  },  
						});
					},
					fail: () => {},
					complete: () => {}
				});
				// axios.post('https://metaq.scutbot.icu/share'
				// 			/*'http://localhost:8080/share'*/,{
				// 				id:this.$store.state.qnid,
				// 				flag:false
				// 			})
				//     .then(response => {
				//       const textToCopy = response.data;
				// 	  uni.setClipboardData({
				// 	    data: textToCopy,  
				// 	    success: () => {  
				// 	      uni.showToast({  
				// 	        title: '已复制到剪贴板',  
				// 	        icon: 'success',  
				// 	      });
				// 	  		this.b1=1
				// 	    },  
				// 	    fail: (err) => {  
				// 	      console.error('Error copying text: ', err);  
				// 	      uni.showToast({  
				// 	        title: '复制失败',  
				// 	        icon: 'none',  
				// 	      });  
				// 	  		this.b1=0
				// 	    },  
				// 	  });  
				//     })
				//     .catch(error => {
				//       console.log(error);
				//     });
				
			}
			},
			isMobileDevice() {
				// navigator=/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
				// if (typeof navigator == 'undefined' || navigator) {
				//   // 在移动端环境中运行的代码
				//   return true;  
				// } else {
				//   // 在非移动端环境中运行的代码
				//   return false;  
				// }
				return true;
			    
			  },
			saveQRCode() {  
			  if (!this.isQrcode) {  
				  uni.request({
				  	url: this.$store.state.host + '/share',
				  	method: 'POST',
				  	header:{
				  		'Content-Type' : 'application/json',
				  		token : uni.getStorageSync("token")
				  	},
				  	data: {
				  		id:this.$store.state.qnid,
				  		flag:true
				  	},
				  	success: res => {
				  		console.log(res);
				  		console.log(res.data);
				  		this.qrcode = res.data;
				  	},
				  	fail: () => {},
				  	complete: () => {}
				});
				// axios.defaults.headers.common['token'] = localStorage.getItem('token');
				// console.log("token",localStorage.getItem('token'));
				// axios.post('https://metaq.scutbot.icu/share'
				// 			/*'http://localhost:8080/share'*/,{
				// 				id:this.$store.state.qnid,
				// 				flag:true
				// 			})
				//     .then(response => {
				// 		console.log(response.data);
				//       this.qrcode = response.data;
					  
				//     })
				//     .catch(error => {
				//       console.log(error);
				//     });
				this.isQrcode = !this.isQrcode ;  
				this.b1 = 2;  
			  } else {  
				if (this.isMobileDevice()) {
					console.log(111)
					uni.downloadFile({  
					  url: this.qrcode,  
					  success: (res) => {  
						if (res.statusCode === 200) {  
						  uni.saveImageToPhotosAlbum({  
							filePath: res.tempFilePath,  
							success: () => {  
							  uni.showToast({  
								title: '二维码已保存',  
								icon: 'success',  
							  });  
							},  
							fail: (err) => {  
							  console.error('Error saving image: ', err);  
							  uni.showToast({  
								title: '保存失败',  
								icon: 'none',  
							  });  
							},  
						  });  
						}  
					  },  
					  fail: (err) => {  
						console.error('Error downloading image: ', err);  
						uni.showToast({  
						  title: '保存失败',  
						  icon: 'none',  
						});  
					  },  
					});
				} else {  
					console.log(222)
				  const link = document.createElement('a');  
				  link.href = this.qrcode;  
				  link.download = 'mataQ.png';  
				  link.style.display = 'none';  
				  document.body.appendChild(link);  
				  link.click();  
				  document.body.removeChild(link);  
				}  
			  }  
			}  
 

		}
	}
</script>

<style lang="less">
		
	*{
		margin: 0%;
		box-sizing: border-box;
	}
	.saveContainer{
		z-index: 999;
		height: 100vh;
		width: 100vw;
		position: fixed;
		bottom: -100%;
		left: 0;
		right: 0;	
		transition:bottom 0.5s ease-in-out;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		background: rgba(0, 0, 0, 0.2);
		backdrop-filter: blur(5px);
		.save{
			height: 50vh;
			width: 80vw;
			border-radius: 42px;
			background: linear-gradient(90deg, rgba(255, 255, 255, 1) 0%, rgba(255, 242, 255, 1) 100%);
			box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
			display: flex;
			flex-direction: column;
			align-items: center;
			padding: 3%;
		}
		.button{
			border-radius: 33.5px;
			background: rgba(255, 255, 255, 1);
			box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
			line-height: 32.93px;
			color: rgba(179, 157, 194, 1);
			font-size: 15pt;
			width: 80%;
			height: 10%;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			margin: 5% 0 5% 0;
		}
		.button2{
			border-radius: 33.5px;
			background: rgba(223, 188, 245, 1);
			box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
			line-height: 32.93px;
			color: rgba(0, 0, 0, 1);
			font-size: 15pt;
			width: 80%;
			height: 10%;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
		}
		.checkImg{
			width: 80%;
			height: 50%;
		}
		.navBar{
			height: 10%;
			width: 100%;
			image{
				height: 100%;
				width: 20%;
			}
		}
	}

	.saveContainer.show{
		bottom: 0%;
	}
</style>