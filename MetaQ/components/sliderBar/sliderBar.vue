<template>
	<view class="bar">
		<view class="Hcontrol">
			<view class="barContainer" :style="{'width': percentage + '%'}"></view>  

			<slider 
			:value="value"
			:min="min"
			:max="max"
			color="rgba(0,0,0,0)" 
			selected-color="rgba(0,0,0,0)" 
			@changing="updateValue"
			@change="changeValue"
			:disabled="disabled"
			/>
			
		
		</view>
		<view class="text">
			{{value}}
		</view>
	</view>

</template>

<script>
	export default {
		name:"sliderBar",
		props:{
			min:{
				type:Number,
				default:0
			},
			max:{
				type:Number,
				default:100
			},
			value:{
				type:Number,
				default:0
			},
			disabled:{
				type:Boolean,
				default:false
			}
		},
		computed:{
			percentage(){
				if(this.val)
				{
					return 100-this.val/(this.max-this.min)*100
				}
				else{
					return 100-this.value/(this.max-this.min)*100
				}
				
			}
		},
		data() {
			return {
				val:null
			};
		},
		methods:{
			updateValue(e){
				this.val=e.detail.value
				this.$emit("changeVal",e.detail.value)
			},
			changeValue(e){
				this.val=e.detail.value
				this.$emit("changeVal",e.detail.value)
			}
		}
	}
</script>

<style lang="less">
	.bar{
		width: 95%;
		display: flex;
		align-items: center;
		.text{
			    color: #7902ac;
			    font-size: 14pt;
		}
	}
		.Hcontrol{
			.barContainer{
				height: 5vw;
				opacity: 1;
				border-radius: 9999px;
				background: rgba(219, 219, 219, 1);  
			}
			position: relative; 
			display: flex;
			flex-direction: row-reverse;
			margin: 5%;
		  width: 100%;
			height: 5vw;
			opacity: 1;
			border-radius: 9999px;
			background: linear-gradient(90deg, rgba(248, 237, 255, 1) 0%, rgba(203, 145, 242, 1) 100%);  
		}
  slider{
		  margin: 0;
		  width: 100%;
		  flex-shrink: 0; /* 防止 barContainer 被压缩 */  
		  position: absolute;
  }
</style>