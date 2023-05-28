<template>
	<view>
		<view class="bg">
			<input type="text" v-model="testdata">
			<button @tap="test">tests</button>
		</view>
	</view>
</template>

<script>
import axios from 'axios';
	export default {
		data() {
			return {
				testdata:""
			};
		},
		methods: {
			test() {
				axios.defaults.headers.common['token'] = this.testdata;
				axios.get('http://localhost:8080/getCreated')
					.then(response => {
						console.log("response",response.data);
					})
					.catch(error => {
					    console.log(error);
					});
				console.log("testdata",this.testdata);
			}
		}
	}
</script>

<style lang="less">
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
	.bg input {
		border: 1px solid rgba(0,0,0,0.6);
	}
</style>
