<template>
	<view class="background">
		<view class="">
		   <!-- 题型选择 -->
		  <view id="tixing">
			  <view class="titleBox" id="tixing_title">
			  	<view class="dot">
			  	</view>
			  	<h2>题型</h2>
			  </view>
		    
		    <view class="square">
		      <view class="square-part" @click="generateQuestion" id="0">
				  <img src="/static/editQuestion/danxuan.png" alt="">
				  <!-- <view class="square-part-word">单选题</view> -->
				  <p>单选题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="1">
				  <img src="/static/editQuestion/duoxuan.png" alt="">
				  <!-- <view class="square-part-word">单选题</view> -->
				  <p>多选题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="2">
				  <img src="/static/editQuestion/tiankong.png" alt="">
				  <p>填空题</p>
			  </view>
		      <view class="square-part" @click="generateQuestion" id="3">
				  <img src="/static/editQuestion/huadongtiao.png" alt="">
				  <p>滑动条</p>
			  </view>
		    </view>
		  </view>
		  <!-- 模板 -->
		  <view id="moban">
			  <view class="titleBox">
				<view class="dot">
				</view>
			  	<h2>模板</h2>
			  </view>
			  
			  <view class="rectangle">
				  <view class="rectangle_square">
					  <img src="/static/editQuestion/shouji.png" alt="">
					  <p>手机</p>
				  </view>
				  <view class="rectangle_square">
					  <img src="/static/editQuestion/riqi.png" alt="">
					  <p>日期</p>
				  </view>
				  <view class="rectangle_square">
					  <img src="/static/editQuestion/didian.png" alt="">
					  <p>地点</p>
				  </view>
			  </view>
		  </view>
		</view>
		<tab-bar :activeTab="tab"></tab-bar>
	</view>
</template>

<script>
import TabBar from '/components/tabbar/tabbar.vue';
export default {
	  components: {
		TabBar
	  },
	  onLoad: function (options) {
		this.tab=options.tab
	  },
	  data() {
		return {
			tab: '',
		}
	  },
	  methods:{
		  generateQuestion(e){
			  var type_num=parseInt(e.currentTarget.id)
			  // console.log(type_num)
			  
		  }
	  }
  

};




</script>

<style scoped lang="less">
.background{
	width: 100vw;
	height: 100vh;
	position: relative;
	top: 0;
	left:0;
	background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
}

#tixing{
	width: 100vw;
	height: 50vh;
}
.dot{
	position: absolute;
	border-radius: 50%;
	width: 12px;
	height: 12px;
	opacity: 1;
	background: rgba(221, 183, 247, 1);
	filter: blur(0.6px);
	position: relative;
	margin-left: 5px;
	margin-right:5px ;
}
.titleBox{
	display: flex;
	flex-direction: row;
	align-items: center;
}

// 调整titile"题型"的位置
#tixing_title{
	position: relative;
	top: 5%;
}
.square {
  // display: flex;
  // flex-wrap: wrap;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  gap: 2px;
  width: 70vw;
  height: 70vw;
  position: absolute;
  top:15vw;
  left: 15vw;
}
.square-part {
  // display: flex; /* 使用 flex 布局 */
  // align-items: center; /* 垂直居中对齐 */
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  border: 1px solid rgb(237,217,252);
  background-color: rgba(255, 255, 255, 0.5); /* 红色，透明度为 50% */
  //定义圆角变量
  --border_radius:25%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}


.square-part:first-child{
  	  border-top-left-radius:var(--border_radius);
}
.square-part:nth-child(2) {
  border-top-right-radius: var(--border_radius); 
}
.square-part:nth-child(3) {
  border-bottom-left-radius: var(--border_radius); 
}
.square-part:last-child {
  border-bottom-right-radius: var(--border_radius); 
}

.square-part img{
	width: 40%;
	height: 40%;
	position: relative;
	// top: 30%;
	// left: 30%;
	// opacity:0.2;
}
.square-part p{
	position: relative;
	// top:25%;
	// left: 30%;
}

.rectangle{
	display: flex;
    flex-wrap: wrap;
    width: 80vw;
    height: 20vw;
    position: relative;
    top: 5vw;
    left: 10vw;
  
}
.rectangle_square{
	height: 120%;
	width: 32%;
	border: 1px solid rgb(237,217,252);
	background-color: rgba(255, 255, 255, 0.5); 
	--border_radius:20%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.rectangle_square img{
	width: 50%;
	height: 50%;
	position: relative;
	// top: 21%;
	// left: 30%;
}

.rectangle_square p{
	position: relative;
	// top:15%;
	// left: 39%;
}
.rectangle_square:first-child{
	border-top-left-radius: var(--border_radius);
	border-bottom-left-radius: var(--border_radius);
}
.rectangle_square:last-child{
	border-top-right-radius: var(--border_radius);
	border-bottom-right-radius: var(--border_radius);
}



</style>