<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox" @click="showOperation">
				<view class="title">
					{{num}}.{{content.title}}
				</view>
				<view class="input-div">
					
				</view>
<!-- 				<view class="options" v-for="(item,index) in content.choice" :key=index>
					<view class="circle"></view>
					{{item}}
				</view> -->
			</view>

			<operationEditQuestion 
			:style="{display: operation_show ? 'flex' : 'none'}"
			:num="num"
			@clickSon="getOffsetTop"
			 >
			</operationEditQuestion>
			
		</view>
		
		
	</view>
</template>

<script>
	import operationEditQuestion from "/components/questionShow/operationEditQuestion/operationEditQuestion.vue"
	export default {
		data() {
			return {
				operation_show:false
			};
		},
		props:{
			num:{
				type:String,
				default:'1'
			},
			content:{
				type:Object,
				default:{
					title:"3333",
					type:'blank',
					choice:['1','2','3','4'],
				}
			},

		},
		// created() {
		//   console.log('---------------------------------')
		//   console.log(this.content)
		// },
		// computed:{
		// 	console.log(content)
		// },
		components:{
			operationEditQuestion
		},
		methods:{
			getOffsetTop: function() {
				let viewElements = document.querySelectorAll("#test");  
				if(viewElements)
				{
					let target_view = viewElements[(this.num-1)];
					// target_view={'targetView_height':target_view.offsetHeight,'targetView_offset_top':target_view.offsetTop)}
					target_view={'targetView_height':target_view.offsetHeight,'targetView_offset_top':target_view.offsetTop}
					this.$store.commit('setTargetView',target_view);
				}

			},
			showOperation(){
					this.operation_show=!this.operation_show
			},
			// getTop()
			// {
			// 	console.log(111)
			// 	this.$emit('clickSon')
			// }
		}
	}
</script>

<style lang="less">
.danxuanDisplay{

	margin-top: 10px;
	display: flex;
	width: 100vw;
	// height: 100vh;
	justify-content: center;
}
.box{	
	width: 90vw;
	border-radius: 20px;
	background: rgba(255, 255, 255, 0.8);
	box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
}

.title{
	margin:10px 0px 5px 20px;
	font-size: 20px;
}
.input-div{
	display: flex;
	margin: 10px 20px 20px 20px;
	border: 1px solid rgba(225, 225, 235, 1);
	border-radius: 3px;
	background-color: #ffffff;
	height: 5vh
}
.options{
	height: 5vh;
	// border: 2px solid green;
	display: flex;
	align-items: center
}



</style>
