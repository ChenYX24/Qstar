<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox">
				<view class="title">
					{{num}}.{{content.title}}<text>(多选题)</text>
				</view>
					<checkbox-group name="" @change="getValue">
						<view class="duoxuan-checkbox" v-for="(item,index) in content.choice" :key="index">
							<label>
								<checkbox :value="(index+1).toString()" color="#c695ff" class="checkBox"/>
								<text class="duoxuantext">{{item}}</text>
							</label>
						</view>
					</checkbox-group>
			</view>
		</view>
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				answer:''
			};
		},
		props:{
			//是预览模式还是填写模式
			mode:{
				default:0
			},
			num:{
				type:String,
				default:'1'
			},
			content:{
				type:Object,
				default:{
					title:"5555",
					type:'blank',
					choice:['1','2','3','4'],
				}
			}

		},
		methods:{
			// getOffsetTop: function() {
			// 	let viewElements = document.querySelectorAll("#test");  
			// 	if(viewElements)
			// 	{
			// 		let target_view = viewElements[(this.num-1)];
			// 		// target_view={'targetView_height':target_view.offsetHeight,'targetView_offset_top':target_view.offsetTop)}
			// 		target_view={'targetView_height':target_view.offsetHeight,'targetView_offset_top':target_view.offsetTop}
			// 		this.$store.commit('setTargetView',target_view);
			// 	}
			// },
			getValue(e){
				this.answer=e.detail
				// console.log(e.detail)
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

.duoxuan-checkbox{
	display: flex;
	// border: 2px solid red;
	height: 5vh;
	align-items: center;
	margin-left: 8px;
}


.duoxuan-text{
	margin-left: 10px;
	
}

.title text{
	margin-left: 5px;
	color: red;
	font-size: 14px;
}

/deep/.uni-checkbox-input {
    background-color: #ffffff;;
	border-radius: 50%
  }


</style>
