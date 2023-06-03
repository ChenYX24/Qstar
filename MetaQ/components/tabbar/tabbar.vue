<template>
<view class="tabContainer" :class="{'blur':isExpanded}">
  <view class="tab-bar">
	  
    <view v-if="Type==0" class="tab-item"  @click="switchTab('question')">
      <image class="imgGroup notebIcon leftIcon" :src="activeTab === 'question'?'/static/tabbar/Q2.png':'/static/tabbar/Q.png'"></image>
	  	<view class="dot" :class="{active: activeTab === 'question'}"></view>
    </view>
	
    <view v-if="Type==1" class="tab-item"  @click="switchTab('setting')">
      <image class="imgGroup notebIcon leftIcon" :src="activeTab === 'setting'?'/static/tabbar/setting2.png':'/static/tabbar/setting.png'"></image>
	  	<view class="dot" :class="{active: activeTab === 'setting'}"></view>
    </view>
	
    <view class="tab-item add" @click="switchTab('add')"  v-if="Type==0" >
      <image class="imgGroup addImg" src="/static/tabbar/add.png" ></image>
    </view>
	
	<view class="tab-item add" @click="switchTab('add')"  v-if="Type==1" >
	  <image class="imgGroup addImg" src="/static/tabbar/add.png" ></image>
	</view>
	
	<view v-if="Type==1" class="tab-item"  @click="switchTab('save')">
	  <image class="imgGroup notebIcon leftIcon" :src="activeTab === 'save'?'/static/tabbar/save2.png':'/static/tabbar/save.png'"></image>
	  	<view class="dot" :class="{active: activeTab === 'save'}"></view>
	</view>
	
    <view v-if="Type==0" class="tab-item"  @click="switchTab('home')">
      <image class="imgGroup"  :src="activeTab === 'home'?'/static/tabbar/home2.png':'/static/tabbar/home.png'"></image>
	  	<view class="dot" :class="{active: activeTab === 'home'}"></view>
    </view>
	<!-- 底部橘色的小圆点 -->
<!-- 	<add v-if="isAdd"></add> -->
  </view>
  <add :class="{show:isAdd}" :isShow="isAdd" @updateAdd="changeAdd"
  :questionNireProps='questionNire'
  ></add>
  <save v-if="Type==1" :class="{show:isSave}" :isShow="isSave" @updateSave="changeSave" :Type="Type" :questionNirePorps="questionNire"></save>
  <save v-else :class="{show:isSave}" :isShow="isSave" @updateSave="changeSave"></save>
  </view>
  
</template>

<script>
import add from "/components/add/add.vue"
import save from "/components/save/save.vue"
export default {
  components: {
    add,
	save
  },
  data() {
    return {
		activeTab: this.tab,
		isAdd:false,
		isSaveFlag:false,
		questionNire:this.questionNireProps
	};
  },
  computed:{
	isSave(){
		// console.log(111,this.questionNire)
		if(this.isSaveFlag||this.outIsSave)
		{
			this.isSaveFlag=true
			console.log(this.isSaveFlag,this.outIsSave)
			return true
		}
		else{
			return false
		}  
	}  
  },
   props: {
	  questionNireProps:{
		type:Object,
		default:{
				
		}
	  },
	  outIsSave:{
		  type:Boolean,
		  default:false
	  },
      tab: {
        type: String,
        default: 'question'
      },
	  isExpanded:{
		  type:Boolean,
		  default:false
	  },
	  Type:{
		  type:Number,
		  default:0
	  }
    },
	methods: {
		
		
		
	  changeAdd(){
		  this.isAdd=!this.isAdd;
	  },
	 changeSave(){
		 this.$emit('changeSave')
		 this.isSaveFlag=!this.isSaveFlag;
		 if(!this.isSaveFlag)
		 {
		 	this.activeTab=''
		 }
	 },
	//#ifdef MP-WEIXIN
	switchTab(tab) {
	if(!this.isExpanded)
	{
		if (tab === 'add'&&this.Type==0) {
		  // 点击加号按钮跳转到相应页面
		  uni.navigateTo({
		    url: '/pages/editQuestionnire/editQuestionnire',
		  });
		} 
		else if(tab === 'add'&&this.Type==1){
			// this.isAdd=!this.isAdd
			uni.navigateTo({
			  url: '/pages/fillQuestionnaire/fillQuestionnaire',
			});
		}
		 else {
		  // 切换选中的tab
		  this.activeTab = tab;
		  // 根据选中的tab跳转到相应页面
		  switch (tab) {
		    case 'question':
		      wx.reLaunch({
		        url: `/pages/myQ/myQ?tab=${tab}`,
		      });
		      break;
		    case 'home':
		      wx.reLaunch({
		        url: `/pages/home/home?tab=${tab}`,
		      });
		      break;
			case 'setting':
				wx.navigateTo({
					url:`/pages/setting/setting?tab=${tab}`
				})
				break;
			case 'save':
				this.isSaveFlag=!this.isSaveFlag
				if(!this.isSaveFlag)
				{
					this.activeTab=''
				}
				break;
		  }
		}
	}
	 
	},
	//#endif
	//#ifndef MP-WEIXIN
	switchTab(tab) {
	if(!this.isExpanded)
	{
		if (tab === 'add'&&this.Type==0) {
		  // 点击加号按钮跳转到相应页面
		  uni.navigateTo({
		    url: '/pages/editQuestionnire/editQuestionnire',
		  });
		} 
		else if(tab === 'add'&&this.Type==1){
			this.isAdd=!this.isAdd
			// uni.navigateTo({
			//   url: '/pages/fillQuestionnaire/fillQuestionnaire',
			// });
		}
		else {
		  // 切换选中的tab
		  this.activeTab = tab;
		  // 根据选中的tab跳转到相应页面
		  switch (tab) {
		    case 'question':
		      uni.reLaunch({
		        url: `/pages/myQ/myQ?tab=${tab}`,
		      });
		      break;
		    case 'home':
		      uni.reLaunch({
		        url: `/pages/home/home?tab=${tab}`,
		      });
		      break;
		  case 'setting':
			uni.navigateTo({
			  url: `/pages/setting/setting?tab=${tab}`,
			});
			break;
		  case 'save':
		  	this.isSaveFlag=!this.isSaveFlag
			if(!this.isSaveFlag)
			{
				this.activeTab=''
			}
			break;
		  }
		}
	}

	},
	//#endif

  },
};
</script>

<style scoped>
.tab-bar {
  z-index: 999;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 15vh;
  background-color: #fff;
  display: flex;
  justify-content: space-around;
  align-items: center;
  border-top-left-radius: 30px!important;
  border-top-right-radius: 30px!important;
  box-shadow: 0px -12px 10px -4px rgb(136, 63, 143, 0.15) !important;
  background: rgba(252, 251, 255, 1);

}

.tab-item {
  z-index: 99;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 26rpx;
  color: #888;
  text-align: center;
}
//#ifdef MP-WEIXIN
.tab-item:first-child{
	padding-top: 0.3rem;
}
//#endif
.dot.active {
  background: rgba(221, 183, 247, 1);
}
.tab-item.add {
  position: relative;
}

.dot {
  position: absolute;
  bottom: 1rem;
  border-radius: 50%;
  width: 12px;
  height: 12px;
  opacity: 1;
  background: rgba(221, 183, 247, 0);
  filter: blur(0.6px);
  position: relative;
  top: 3px;
}

 
 .tab-item.add image {
 width: 18vw;
 height: 18vw;
 }
 
 .tab-item.add text {
 font-size: 0;
 line-height: 0;
 height: 0;
 overflow: hidden;
 }
 
 
 .imgGroup{
	 width: 2.5rem;
	 height: 2.5rem;
 }
 
/* .notebIcon{
	 width: 1.8rem;
	 height: 1.8rem;
	 top: 0.08rem !important;
 } */
 
 .addImg{
	 transform:scale(1.08);
 }
 
/* .leftIcon{
	 bottom: 0.1rem;
 } */
 .blur :not(.blur){
	filter: blur(5px);
 }
.tabContainer::after {
  content: "";
  display: block;
  width: 100%;
  height: 100px; /* 占位元素的高度 */
}
.placeholder {
  display: block;
  width: 100%;
  height: 100px;
}
 </style>
 
