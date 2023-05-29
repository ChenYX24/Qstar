<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox">
				<view class="title">
					{{num}}.{{content.title}}<text>[多选题]</text>
				</view>
					<checkbox-group name="" @change="getValue" class="checkboxGroup">
						<view class="duoxuan-checkbox" v-for="(item,index) in content.choice" :key="index">
							<label class="checkbox">
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
			getValue(e){
				this.answer=e.detail.value
				// console.log(e.detail.value)
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
}
.inbox{
	// border: 1px dashed green;
}
.title{
	margin:10px 0px 5px 20px;
	font-size: 20px;
}

.checkboxGroup{
	margin: 10px 20px 35px 20px;
}

.duoxuan-checkbox{
	border-radius: 3px;
	border: 1px solid rgba(225, 225, 235, 1);
	align-items: center;
	
}

.checkbox{
	height: 8vh;
	background: rgba(255, 255, 255, 0.8);
	margin-left: 5px;
	width: 100%;
	display: flex;
	align-items: center;
}

.duoxuan-text{
	margin-left: 10px;
	
}

.title text{
	margin-left: 5px;
	color: rgb(143 68 238);
	font-size: 14px;
}

/deep/.uni-checkbox-input {
    background-color: #ffffff;;
	border-radius: 50%
  }


</style>
