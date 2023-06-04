<template>
	<view class="danxuanDisplay" id="test">
		<view class="box">
			<view class="inbox">
				<view class="title" @click="changeShow">
					{{num}}.{{content.question}}
				</view>
				
				<view class="calendar-range">
					<view class="input-div" id='inputDiv' @click="changeShow">
									<view v-if="showRange">
										{{range.start.toLocaleDateString()}}-{{range.end.toLocaleDateString()}}
									</view>
									<view v-else-if="isRange">
										{{range.start.toLocaleDateString()}}
									</view>
									<view v-else>
										{{date.toLocaleDateString()}}
									</view>
					</view>
					<view class="date">
						<DatePicker v-if="isRange" v-model.range="range" 
						:style="{display: isShow ? 'flex' : 'none'}" 
						color="purple"/>
						<DatePicker v-else v-model="date"
						:style="{display: isShow ? 'flex' : 'none'}" 
						color="purple"/>
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
				date: new Date(2023, 0, 6),
				answer:'',
				isShow:false
			};
		},
		  watch: {
			range(newVal,oldVal) {
			  // 在这里处理值变化的逻辑
			  // console.log(this.range.start.toLocaleDateString()==this.range.end.toLocaleDateString())
			  if((this.content.choice[0]==2)&&newVal.start.toLocaleDateString()==newVal.end.toLocaleDateString()){
				  // this.range=oldVal
				this.range=oldVal
				  uni.showToast({
				    title: '请选择一段日期',  
				    icon:'none',  
				  });  
			  }else{
				  this.isShow=!this.isShow
			  }
			  console.log(333)
			},
			date(newVal,oldVal) {
				this.isShow=!this.isShow 
			}
		  },
		  computed: {
		  	isRange() {
				if(this.content.choice[0]==1)//只能single
				{
					this.answer=this.date.toLocaleDateString().replace(/\//g, '-')
					return false
				}
				else if(this.content.choice[0]==2)//只能range
				{	
					var start=this.range.start.toLocaleDateString().replace(/\//g, '-');
					var end=this.range.end.toLocaleDateString().replace(/\//g, '-')
					this.answer=start+','+end
					return true
				}
				else{
					 return true
				}

		  	},
			showRange(){
				if(this.isRange)
				{
					if(this.range.start.toLocaleDateString()==this.range.end.toLocaleDateString())
					{
						this.answer=this.range.start.toLocaleDateString().replace(/\//g, '-');
						return false
					}
					else
					{
						var start=this.range.start.toLocaleDateString().replace(/\//g, '-');
						var end=this.range.end.toLocaleDateString().replace(/\//g, '-')
						this.answer=start+','+end	
						return true
					}
				}
				else{
					return false
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
					choice:[0],
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
			test(){
				console.log(this.answer);
			}
			// ttt(e){
			// 	this.range=({
			// 		start: this.range.start,
			// 		  end: this.range.end
			// 	}),
			// 	console.log(this.range)
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
