<template>
	<view class="background">
		<view class="title">
			我的问卷
		</view>
		<view class="inputBox">
			<image src="/static/inputSearch/search.png" mode="aspectFill"></image>
			<input type="text" placeholder="输入关键词" class="input" placeholder-style="color: rgba(187, 187, 199, 1);;" v-model="inputValue"> <!--v-model是双向绑定，将文本框的值与inputvalue绑定-->
		</view>
		<view class="container">
			<tab-swiper class="tabSwiper" @tab-change="handleTabChange" :current-tab="currentTab" :text1="text1" :text2="text2"/>
		    <swiper
		      :current="currentTab"
		      @change="swiperChange"
		      :duration="300"
		      class="swiper"
		    >
		      <swiper-item>
				<view class="page1">
				  <QBlock v-for="(block, index) in myCreateds" :key="block.id" :onumber="block.filled" :title="block.title" :isPush="block.commit" @changeSave="emitSave(index)"></QBlock>
				</view>
		      </swiper-item>
		      <swiper-item>
				<view class="page2">
				  <QBlock2 v-for="item in myFilleds" :key="item.id" :title="item.title" :isEnd="item.commited" :name="item.name"></QBlock2>
				</view>
		      </swiper-item>
		    </swiper>
		  </view>
		  
		<tab-bar :tab="tab" :outIsSave="isSave" @changeSave="emitSave"></tab-bar>
	</view>
</template>

<script>
import TabBar from '/components/tabbar/tabbar.vue';
import TabSwiper from "/components/tabSwiper/tabSwiper.vue";
import QBlock from '/components/myQ/QBlock/QBlock.vue';
import QBlock2 from '/components/myQ/QBlock2/QBlock2.vue';
import axios from 'axios';
export default {
  components: {
    TabBar,
	TabSwiper,
	QBlock,
	QBlock2
  },
  onLoad: function (options) {
	this.tab=options.tab
  },
  mounted() {
  	this.fetchData();
  },
  data() {
  	return {
  		tab: '',
		inputValue: '',
		currentTab: 0,
		text1:'我创建的',
		text2:"我填写的",
		isEnd:true,
		name:'尘',
		//要传的值
		blocks:[],
		//要传的值
		blocks1: [],
		isSave:false
  	}
  },
    methods: {
		emitSave(index){
			//TODO
			if(index==0||index)
			{
			  console.log(this.blocks1[index].id)
			}
			this.isSave=!this.isSave
		},
		async fetchData() {
			//获取已经创建的问卷
			axios.defaults.headers.common['token'] = localStorage.getItem('token');
			axios.get(/*'https://metaq.scutbot.icu/login'*/
						'http://localhost:8080/getCreated')
			    .then(response => {
			      console.log(response.data);
				  this.blocks1 = response.data.data;
				  console.log("blocks1",this.blocks1);
			    })
			    .catch(error => {
			      console.log(error);
			    });
			try {  
			  // const response = await axios.get('/static/test2.json'); // TODO
			  // this.blocks = response.data.data.blocks;
			  // this.blocks1=response.data.data.blocks1
			} catch (error) {  
			  console.error('Error fetching data:', error);  
			}
			//获取已经填写的问卷
			axios.get(/*'https://metaq.scutbot.icu/login'*/
						'http://localhost:8080/fillRecord')
			    .then(response => {
			      console.log(response.data);
			      this.blocks = response.data.data;
			      console.log("blocks",this.blocks);
			    })
			    .catch(error => {
			      console.log(error);
			    });
		  },
      swiperChange(e) {
        this.currentTab = e.detail.current;
		this.inputValue=''
      },
	  handleTabChange(index) {
		this.currentTab = index;
	  },


    },
	computed: {
		myCreateds() {
		  if(this.inputValue&&this.currentTab===0)
		  {
			return this.blocks1.filter(post =>
						post.title.includes(this.inputValue)
						);  
		  }
		  else{
			  return this.blocks1
		  }
		},
		myFilleds(){
			if(this.inputValue&&this.currentTab==1)
			{
				return this.blocks.filter(post =>
							post.title.includes(this.inputValue)||post.name.includes(this.inputValue)
							);  
			}
			else{
				return this.blocks
			}
		}
	},

};
</script>

<style scoped lang="less">
.background{
	.title{
		font-size: 16pt;
		font-weight: 400;
		letter-spacing: 0px;
		line-height: 32.93px;
		margin-top: 5vw;
		margin-bottom: 3vw;
	}
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100vw;
	height: 100vh;
	position: relative;
	top: 0;
	left:0;
	background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
}
	.inputBox{
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-content: center;
		align-items: center;
		width: 90vw;
		height: 7vh;
		border-radius: 10px;
		background: rgba(255, 255, 255, 0.45);
		margin-top: 2vw;
		margin-bottom: 3vw;
		.input{
			width: 80%;
			height: 80%;
			color: rgba(187, 187, 199, 1);
		}

		image{
			width: 2rem;
			height: 2rem;
			z-index: 99;
			margin-right: 3%;
		}
	}
	.container {
	  height: 100%;
	  position: relative;
	  width: 100vw;
	}
	
	.swiper {
    height: calc(100% - 100px);
    position: relative;
    bottom: -65px;
	}
	
	.page1,
	.page2 {
	  height: 100%;
	  width: 100vw;
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	  gap: 3%;
	  overflow-y: scroll;
	}
	
	.tabSwiper {
	  position: absolute;
	  top: 0;
	  left: 0;
	  right: 0;
	  margin-top: 2vw;
	  margin-bottom: 3vw;
	}
</style>