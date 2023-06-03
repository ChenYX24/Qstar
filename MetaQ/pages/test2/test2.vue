<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox">
				<view class="title">
					{{num}}.{{content.question}}
				</view>
				<view class="">
					<view class="input-div" id='inputDiv' @click="changeShow">
									<view v-if="isRange">
										{{range.start.toLocaleDateString()}}-{{range.end.toLocaleDateString()}}
									</view>
									<view v-else>
										{{range.start.toLocaleDateString()}}
									</view>
							</view>
					<view class="date">
						<DatePicker v-if="isRange" v-model.range="range" 
						:style="{display: isShow ? 'flex' : 'none'}" />
					</view>
						
				</view>
			</view>

		</view>
		
		
	</view>
</template>

<script>
	import {DatePicker} from 'v-calendar';
	import 'v-calendar/style.css';
	
	export default {
		data() {
			return {
				range:({
					start: new Date(2023, 0, 6),
					  end: new Date(2023, 0, 10)
				}),
				date:null,
				answer:'',
				isShow:false
			};
		},
		  watch: {
			range(newVal) {
			  // 在这里处理值变化的逻辑
			  // console.log(this.range.start.toLocaleDateString()==this.range.end.toLocaleDateString())
			  this.isShow=!this.isShow
			}
		  },
		  computed: {
		  	isRange() {
				if(test==1)
				{
					return false
				}
				else if(test==2)
				{
					return true
				}
				else{
					if(this.range.start.toLocaleDateString()==this.range.end.toLocaleDateString())
					{
						return false
					}
					else
					{
						return true
					}
				}

		  	}
		  },
		props:{
			num:{
				type:String,
				default:'2'
			},
			content:{
				type:Object,
				default:{
					question:"3333",
					type:'blank',
					choice:['1','2','3','4'],
				}
			},

		},
		components:{
			DatePicker
		},
		methods:{
			setHeight(e){
					var node=document.getElementById('textArea');
					var parent=document.getElementById('inputDiv')
					node.style.height=`${e.detail.height}px`
					parent.style.height=`${e.detail.height}px`
			},
			changeShow(e){
				this.isShow=!this.isShow
			},
			

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




.input-div{
	display: flex;
	margin: 10px 20px 20px 20px;
	border: 1px solid rgba(225, 225, 235, 1);
	border-radius: 3px;
	background-color: #ffffff;
	justify-content: center;
	font-size: 20px;
}

.text-input{
	display: flex;
	width: 100%;
	height: 100%;
}
.date{
	display: flex;
	margin: 10px 20px 20px 20px;
	// border: 1px solid rgba(225, 225, 235, 1);
	border-radius: 3px;
	background-color: #ffffff;
	justify-content: center
}

</style>
