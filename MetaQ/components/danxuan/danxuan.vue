<template>
	<view class="SingleChoice">
		<view class="xuanxiang_text">选项</view>
		
		<view v-for="(item, index) in text" :key="item" class="single_choice_option">
		  <view class="delete_option" @click="delete_options(index)" id="option_0">
		    <img src="/static/question_component/delete.png" alt="">
		  </view>
		  <view class="write_option">
			  <!-- 这里在小程序里面可能要使用v-model  但是现在使用v-model会卡顿 -->
		    <input type="text" name="" id="" v-model="text_copy[index]" placeholder="输入选项">
		  </view>
		</view>
		
		<!-- 一条直线 -->
		<view class="separate_line"></view>
		
		<!-- 添加选项的操作 -->
		<view class="add_options" @click="add_options" >
			<img src="/static/question_component/add.png" alt="">
			<view class="add_word">
				添加选项
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		 name: 'SingleChoice',
		data() {
			return {
				text:[...this.text_copy]
			};
		},
		props:{
			text_copy:{
				type: Array,  
				required: false,
				default: () => (['',''])  
			}
		},
		methods:{
			delete_options(item){
				  this.text.splice(item,1)
				  this.text_copy.splice(item,1)
				  this.text=[...this.text_copy];
			},
			add_options(){
				this.text_copy.push('');
				this.text.push('');
			}
		}
	}
</script>

<style lang="less">

.SingleChoice{
	width: 100vw;
	// height: 30vh;
	// border: 2px solid red;
}	
.xuanxiang_text{
	height: 4vh;
	 font-size:18px;
	background-color: #dabff0;
	 text-indent: 2vh;
}
.single_choice_option{
	margin: 1vh;
	display: flex;
	height: 5vh;
	// border: 2px solid gray;
	align-items: center;
}

.write_option{
	// margin: 1vh;
	margin-left: 10px;
}

.SingleChoice img{
	height: 5vh;
}

.add_options{
	margin: 1vh;
	// margin-top: 2vh;
	display: flex;
	height: 5vh;
	// border-top: 2px solid lightgray;
	// border: 2px solid gray;
	align-items: center;
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
