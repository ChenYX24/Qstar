<template>
	<view>
		<danxuanDisplay></danxuanDisplay>
		<tiankongDisplay></tiankongDisplay>
		<sliderDisplay :content='content'></sliderDisplay>
		<danxuanAnswer></danxuanAnswer>
	</view>
</template>

<script>
import danxuanDisplay from '/components/questionShow/danxuanDisplay/danxuanDisplay.vue'
import tiankongDisplay from '/components/questionShow/tiankongDisplay/tiankongDisplay.vue'
import sliderDisplay from '/components/questionShow/sliderDisplay/sliderDisplay.vue'
import danxuanAnswer from '/components/answerQuestion/danxuanAnswer/danxuanAnswer.vue'
	export default {
		components:{
			danxuanDisplay,
			tiankongDisplay,
			sliderDisplay,
			danxuanAnswer
		},
		data() {
			return {
				content:{
						title:"3333",
						type:'3',
						choice:[10,'非常差',1000,'非常好',0],
					}
			};
		}
	}
</script>

<style lang="less">

</style>
