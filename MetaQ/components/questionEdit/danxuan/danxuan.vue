<template>    
	<view class="SingleChoice"> 
	   <view class="box">
	   	
	   <view class="group">
	   	
	   
		<view class="xuanxiang_text">
			{{num}}.
			<input type="text"  placeholder="点击编辑问题" class='question-input' v-model="question">
			<view class="danxuanti-label"  v-if="type=='SINGLE'">
				[单选题]
			</view>
			<view class="danxuanti-label"  v-if="type=='MULTIPLE'">
				[多选题]
			</view>
		</view>    
		    
		<view v-for="(item, index) in text" :key="index" class="single_choice_option">    
			<view class="choice-box">
				<view class="write_option" @click="showOperation(index)">    
				  <input type="text" v-model="copy[index]" placeholder="点击编辑选项">    
				</view> 
			</view>
		  <operateEdit 
		  @deleteOption="deleteOptions(index)"
		  @upOption="upOptions(index)"
		  @downOption="downOptions(index)"
		  :style="{display: operation_show[index] ? 'flex' : 'none'}"></operateEdit>
		
		
		</view>    
		
		
		
		<!-- 一条直线 -->    
		<!-- <view class="separate_line"></view>    -->
		    
		<!-- 添加选项的操作 -->    
		<view class="add_options" @click="add_options" >    
			<img src="/static/question_component/add.png" alt="">    
			<view class="add_word">    
				添加选项    
			</view>    
		</view>    
	</view>
	</view>
	</view>    
</template>    
    
<script>
	import { List, number } from 'echarts';    
	import operateEdit from "/components/questionEdit/operateEdit/operateEdit.vue"
	export default {    
		 name: 'SingleChoice',  
	    components:{
		   operateEdit
	    },
		data() {    
			return {    
				text:[...this.text_copy],
				copy:[],
				operation_show:Array.from({ length: this.text_copy.length }, () => false),
				beforeIndex:-1,
				// question:''
				question:this.questionProp
			};    
		},    
		props:{
			type:{
				type:String,
				default:""
			},
			num:{
				type:Number,
				default:-1
			},
			questionProp:{
				type:String,
				default:""
			},
			text_copy:{    
				type: Array,      
				required: false,    
				default: ['','']
			}
			
		},
		mounted(){
			// console.log(this.text_copy)
			this.copy=[...this.text_copy]
			this.text=[...this.text_copy]
		},

		methods:{    
			deleteOptions(item){
				  this.copy.splice(item,1)     
				  this.text=[...this.copy];     
			},    
			add_options(){    
				this.copy.push('');    
				this.text=[...this.copy];
				this.showOperation(this.copy.length-1);		
			},
			showOperation(index){
				if(this.beforeIndex==-1){
					this.operation_show[index]=true;
					this.beforeIndex=index;
				}
				else if(this.beforeIndex==index){
					this.operation_show[index]=false;
					this.beforeIndex=-1;
				}
				else{
					this.operation_show[this.beforeIndex]=false;
					this.operation_show[index]=true;
					this.beforeIndex=index;
				}
			},
			upOptions(index){
				//第一个
				if(index==0)
					return;
				this.copy.splice(index-1, 2, this.copy[index], this.copy[index-1]);
				this.text=[...this.copy];
				this.showOperation(index-1);
			},
			downOptions(index){
				if(index==this.copy.length-1)
					return;
				[this.copy[index], this.copy[index+1]] = [this.copy[index+1], this.copy[index]];
				this.text=[...this.copy];
				this.showOperation(index+1);
			}
		}    
	}    
</script>    
    
<style lang="less">    
    
.SingleChoice{  
	display: flex;
	justify-content: center;
	width: 100vw;    
	// height: 30vh;    
	// border: 2px solid red;    
}
.box{
	background-color: rgba(255, 255, 255, 0.8);
	border-radius: 10px;
	width: 85vw;
	border: 2px solid rgba(225, 225, 235, 1);
}   

.group{
	margin: 10px 20px 35px 20px;
}
.single_choice_option{      
	display: flex;
	// align-items: center;
	flex-direction: column;
} 

.choice-box{
	display: flex;
	align-items: center;
	height: 7vh;
	border-radius: 3px;
	background: rgba(255, 255, 255, 0.8);
	border: 1px solid rgba(225, 225, 235, 1);
}

.delete_option{
	display: flex;
	// border: 2px solid red;
	align-items: center;
	
}
.xuanxiang_text{    
	height: 4vh;    
	font-size:18px;       
	color: #991cff;
	margin-bottom: 10px;
    display: flex;
	align-items: center;
}   
.question-input{
	font-size: 18px;
	margin-left: 8px;
	color: black;
	// border: 2px solid red;
}

.danxuanti-label{
	
	display: flex;
	align-items: center;
	flex-shrink: 0;
	color: rgb(237 64 225);
	font-size: 14px;
}

   
    
.write_option{    
	margin-left: 10px;    
}    
    
.SingleChoice img{    
	height: 5vh;    
}    
    
.add_options{    
	display: flex;    
	height: 7vh;    
	align-items: center; 
	justify-content: center;
	border-radius: 3px;
	background: rgba(255, 255, 255, 0.8);
	border: 1px solid rgba(225, 225, 235, 1);
}    
    
.add_word{    
	margin-left: 10px ;    
	color: #b30481;    
}    
    
.separate_line{    
	// height: 1vh;    
	border-bottom: 1px solid lightgray;    
}    
    
    
    
    
</style>
