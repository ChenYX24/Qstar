<template>
	<view>
		<danxuanAnswer></danxuanAnswer>
	</view>
</template>

<script>
	import axios from 'axios';
	import danxuanAnswer from '/components//answerQuestion/danxuanAnswer/danxuanAnswer.vue'
	export default {
		components:{
			danxuanAnswer
		},
		data() {
			return {
				
			};
		},
		mounted(){
			axios.post(/*'https://metaq.scutbot.icu/login'*/
						'http://localhost:8080/testss', {
			    email: "123456",
				password: "753951"
				})
			    .then(response => {
					console.log("aaaa");
			    })
			    .catch(error => {
			      console.log(error);
			    });
		}
	}
</script>

<style lang="less">

</style>
