<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox">
				<view class="title">
					{{num}}.{{content.question}}
					<view class="type">
						<text>[滑动条]</text>
					</view>
				</view>
				<view class="slider-box">
					<sliderBar
					:value='answer'
					:min="content.choice[0]"
					:max="content.choice[2]"
					@changeVal="changeValue"
					:disabled="true"
					></sliderBar>
				</view>
			</view>
			<operationEditQuestion
			:style="{display: operate_show ? 'flex' : 'none'}"
			:num="num"
			@clickSon="getOffsetTop"
			 >
			</operationEditQuestion>
		</view>
	</view>
</template>

<script>
import sliderBar from '/components/sliderBar/sliderBar.vue'
import operationEditQuestion from "/components/questionShow/operationEditQuestion/operationEditQuestion.vue"
export default {
		components: {
			sliderBar,
			operationEditQuestion
		},
		data() {
			return {
				answer:Math.floor(this.content.choice[2]*0.8),
				// operation_show:false
			};
		},
		props:{
			operate_show:{
				type:Boolean,
				default:false
			},
			num:{
				type:String,
				default:'1'
			},
			content:{
				type:Object,
				default:{
					question:"3333",
					type:'3',
					choice:[10,'非常差',1000,'非常好',0],
				}
			},

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
			// showOperation(){
			// 	this.operation_show=!this.operation_show
			// },
			changeValue(val){
				this.answer=val
			}
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
	padding-bottom: 5%;
}
.inbox{
	// border: 1px dashed green;
}
.title{
	margin:10px 0px 5px 20px;
	font-size: 20px;
	display: flex;
	align-items: center;
	.type{
		margin-left: 5px;
		color: rgb(143 68 238);
		font-size: 14px;
	}
}

.slider-box{
	margin: 20px 20px 0px 20px;
}

/deep/.uni-slider-value{
	color: #7902ac;
	font-size: 17px;
}
/deep/.uni-slider-handle-wrapper{
	height:5px;
}

</style>
