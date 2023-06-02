<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox">
				<view class="title">
					{{num}}.{{content.question}}<text>[单选题]</text>
				</view>
				<radio-group name="" @change="getValue" class="radioGroup">
					<view class="danxuan_radio" v-for="(item,index) in content.choice" :key="index" :class="{'active':index==answer&&answer!=''}">
						<label class="radio" >
							<radio :value="index.toString()" color="#c695ff"/><text>{{item}}</text>
						</label>
					</view>
				</radio-group>
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
			num:{
				type:String,
				default:'1'
			},
			content:{
				type:Object,
				default:{
					question:"5555",
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
				this.answer=e.detail.value
				// console.log(this.answer)
			}
		}
	}
</script>

<style lang="less">
.danxuanDisplay{

	margin-top: 10px;
	display: flex;
	width: 100vw;
	justify-content: center;
}
.box{
	width: 90vw;
	border-radius: 20px;
	background: rgba(255, 255, 255, 0.8);
	box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
}
.inbox{
	// border: 1px dashed green;
}
.title{
	margin:10px 0px 5px 20px;
	font-size: 20px;
}
.title text{
	margin-left: 5px;
	color: rgb(143 68 238);
	font-size: 14px;
}
.radioGroup{
	margin: 10px 20px 35px 20px;
}
.danxuan_radio{

	border-radius: 3px;
	align-items: center;
	background: rgba(255, 255, 255, 0.8);
	border: 2px solid rgba(225, 225, 235, 1);
}
  .danxuan_radio + .danxuan_radio {  
    margin-top: -2px; /* 使边框重叠 */  
  } 
  .danxuan_radio.active{
	  border: 2px solid rgba(221, 183, 247, 1);
	  z-index: 2;
	  box-shadow: 1px 1px 10px 0px rgba(136, 63, 143, 0.15);
	  position: relative;
  }
.radio{
	height: 8vh;
	margin-left: 5px;
	width: 100%;
	display: flex;
	align-items: center;
}

</style>
