<template>
	<view class="saveContainer">
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
	export default {
		name:"save",
		data() {
			return {
				b1:0,
				isQrcode:false,
				checkImg:'/static/save/checkImg.png',
				qrcode:'/static/save/qrcode.png'
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
			isShow: {
				type: Boolean,
				default: false,
			},
		},
		methods:{
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
				const textToCopy = 'This is the text to be copied to the clipboard.';
				uni.setClipboardData({  
				  data: textToCopy,  
				  success: () => {  
				    uni.showToast({  
				      title: 'Text copied to clipboard',  
				      icon: 'success',  
				    });
						this.b1=1
				  },  
				  fail: (err) => {  
				    console.error('Error copying text: ', err);  
				    uni.showToast({  
				      title: 'Failed to copy text',  
				      icon: 'none',  
				    });  
						this.b1=0
				  },  
				});  
			}
			},
			saveQRCode(){
				this.isQrcode=true
				this.b1=2
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
			font-size: 20pt;
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
			font-size: 20pt;
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