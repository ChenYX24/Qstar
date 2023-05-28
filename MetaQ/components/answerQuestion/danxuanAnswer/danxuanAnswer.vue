<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox" @click="showOperation">
				<view class="title">
					{{num}}.{{content.title}}
				</view>
				<view class="options" v-for="(item,index) in content.choice" :key=index>
					<view class="circle"></view>
					{{item}}
				</view>
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
}
.inbox{
	border: 1px dashed green;
}
.title{
	font-size: 20px;
	// color: aqua;
	// border: 2px solid red;
}

.options{
	height: 5vh;
	// border: 2px solid green;
	display: flex;
	align-items: center
}
.circle{
  width: 15px;
  height: 15px; 
  // background-color: red;
  border: 2px solid gray;
  border-radius: 50%;
  margin-left: 5px;
  margin-right: 5px;
}




</style>
