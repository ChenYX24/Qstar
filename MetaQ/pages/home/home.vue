<template>
	<view class="background" :class="{ 'background-blur': isExpanded,'blur2':isInfoExpanded }" @click="confirmInput($event)">
		<view class="title">
			我的
		</view>
		<view class="img" @tap="selectImage">
			<image :src="profilePhoto" mode="aspectFill" ></image>
		</view>
		<input type="text" id="name" :class="{ 'name': true, 'expanded': isExpanded }" v-model="name" @click="expandInput" :disabled="!isExpanded">
		<view id="infoBox" :class="{ 'infoBox': true, 'infoExpanded': isInfoExpanded }">
			<view class="info" id="infoBox2">
			  <view class="item" id="infoBox3">
				<view class="label" id="infoBox4">手机：</view>
				<input type="text" v-model="phone" class="value" id="infoBox5" :disabled="!isInfoExpanded">
			  </view>
			  <view class="item" id="infoBox6">
				<view class="label" id="infoBox7">邮箱：</view>
				<input type="text" v-model="email" class="value" id="infoBox8" :disabled="!isInfoExpanded">
			  </view>
			  <view class="item" id="infoBox9">
				<view class="label" id="infoBox10">地区：</view>
				<picker mode="selector" :range="provinces" @change="onProvinceChange" class="picker" id="infoBox11" :disabled="!isInfoExpanded">
				  <view class="picker-text" id="infoBox12">{{ currentProvince }}</view>
				</picker>
			  </view>
			</view>
			<image class="icon" src="/static/home/edit.png" mode="aspectFit"  @click="expandInfoBox"></image>
		</view>
		<view class="button" @tap="goToIndex">
			切换账号
		</view>
	</view>
	<view class="tabbar" @click="confirmInput($event)">
		<tab-bar :tab="tab" :isExpanded="isExpanded||isInfoExpanded"></tab-bar>
	</view>
</template>

<script>
import axios from 'axios';
import TabBar from '/components/tabbar/tabbar.vue';
export default {
  inheritAttrs:false,
  components: {
    TabBar,
  },
  onLoad: function (options) {
	this.tab=options.tab
	const Token = JSON.parse(localStorage.getItem('token'));
	this.email = Token.email;
	axios.post(/*'https://metaq.scutbot.icu/login'*/
				'http://localhost:8080/getPage', {
			email:this.email
		})
	    .then(response => {
			console.log(response.data);
	    })
	    .catch(error => {
	      console.log(error);
	    });
	
  },
  data() {
  	return {
  		tab: '',
		profilePhoto:'/static/home/profilePhoto.png',
		name:'尘',
		isExpanded:false,
		isInfoExpanded:false,
		flag:false,
		phone: "13306715109",
        email: "",
		provinces: ['未知','北京市', '天津市', '上海市', '重庆市', '河北省', '山西省', '辽宁省', '吉林省', '黑龙江省', '江苏省', '浙江省', '安徽省', '福建省', '江西省', '山东省', '河南省', '湖北省', '湖南省', '广东省', '海南省', '四川省', '贵州省', '云南省', '陕西省', '甘肃省', '青海省', '台湾省', '内蒙古自治区', '广西壮族自治区', '西藏自治区', '宁夏回族自治区', '新疆维吾尔自治区', '香港特别行政区', '澳门特别行政区'],
		currentProvince: '未知',
		origin_phone:"13306715109",
		origin_provinces:"未知",
		token:""
	}
  },
  methods: {
      expandInput() {
		if(!this.isInfoExpanded)
			this.isExpanded = true;
      },
      confirmInput(event) {
		//告诉后端数据修改
		if(this.origin_phone != this.phone || this.origin_provinces != this.currentProvince){
			this.origin_phone = this.phone;
			this.origin_provinces = this.currentProvince;
			axios.defaults.headers.common['token'] = this.token;
			axios.post(/*'https://metaq.scutbot.icu/login'*/
						'http://localhost:8080/test', {
					phoneNumber:this.phone,
					location:this.currentProvince
				})
			    .then(response => {
					console.log(response.data);
			    })
			    .catch(error => {
			      console.log(error);
			    });
		}
        // 恢复样式
		if(this.isExpanded)
		{
			if(this.flag)
			{
				if (event.target.id != 'name') {
				    this.isExpanded = false;
					this.flag=false;
				}
			}
			else
			{
				this.flag=true
			}
		}
		if(this.isInfoExpanded)
		{
			if(this.flag2)
			{
				console.log(event.target.id)
				if (!event.target.id.startsWith('infoBox')) {
				    this.isInfoExpanded = false;
					this.flag2=false;
				}
			}
			else
			{
				this.flag2=true
			}
		}
      },
	  expandInfoBox(){
		if(!this.isExpanded)
		  this.isInfoExpanded = true;
	  },
	  onProvinceChange(event) {
	    const provinceIndex = event.detail.value
	    this.currentProvince = this.provinces[provinceIndex]
	    if (this.currentProvince !== '请选择省份') {
	        this.trueProvince = this.currentProvince
	      }
	  },
	  goToIndex(){
		if(!this.isInfoExpanded&&!this.isExpanded)
		  uni.reLaunch({
		  	url:'/pages/index/index?flag=true'
		  })
	  },
	selectImage() {
      // 在这里编写选择图片的逻辑
      uni.chooseImage({
        count: 1, // 最多可选择的图片数量
        success: (res) => {
          // 选择图片成功后的回调函数
          const tempFilePaths = res.tempFilePaths;
          if (tempFilePaths && tempFilePaths.length > 0) {
            this.profilePhoto = tempFilePaths[0]; // 更新当前图片的路径
          }
        },
        fail: (err) => {
          // 选择图片失败后的回调函数
          console.error(err);
        },
      });
    },
	}

};
</script>

<style scoped lang="less">


.background{
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100vw;
	height: calc(100vh - 12vh);
	position: relative;
	top: 0;
	left:0;
	background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
	.title{
		font-size: 16pt;
		font-weight: 400;
		letter-spacing: 0px;
		line-height: 32.93px;
		margin-top: 5vw;
		margin-bottom: 3vw;
	}
}
.img{
	width: 35vw;
	height: 35vw;
	border: 2px solid rgba(221, 183, 247, 1);
	border-radius: 50%;
    overflow: hidden;
	image{
		width: 100%;
		height: 100%;
	}
}

.name.expanded {
  width: 80vw;
  height: 7vh;
  transform: scale(1.2);
  background-color: rgba(255,255,255,1);
}

.name{
	width: 80vw;
	height: 7vh;
	border-radius: 20px;
	background: rgba(255, 255, 255, 0.45);
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	text-align: center;
	font-size: 18pt;
	font-weight: 400;
	color: rgba(0, 0, 0, 1);
	margin: 3vw;
	transition: all 0.3s ease;
}
.infoBox.infoExpanded{
	background-color: rgba(255,255,255,1);
	height: 40vh;
	width: 90vw;
}
.background-blur>*:not(.name):not(.confirm){
	filter: blur(5px);
}

.confirm{
	width: 80vw;
	height: 10vw;
	text-align: center;
}

.infoBox {
	display: flex;
	align-items: flex-start;
	box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.1);
	width: 80vw;
	height: 13vh;
	flex-direction: column;
	border-radius: 10px;
	background: rgba(255, 255, 255, 0.45);
	position: relative;
	padding-left: 5%;
	box-sizing: border-box;
	transition: all 0.3s ease;
}

.info {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-start;
	flex: 1;
}

.item {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
  flex:1
}

.label {
	font-size: 14pt;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 24.7px;
	color: rgba(187, 187, 199, 1);
}
.value{
	font-size: 14pt;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 24.7px;
	color: rgba(187, 187, 199, 1);
}
.icon {
    width: 8vw;
    height: 8vw;
    position: absolute;
    bottom: 10%;
	right: 2%;
}
.button{
	border-radius: 12px;
	background: rgba(224, 224, 224, 0.25);
	box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
	font-size: 18pt;
	font-weight: 400;
	color: rgba(179, 157, 194, 1);
	text-align: center;
	width: 80vw;
	height: 7vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	position: absolute;
	bottom: 10%;
	
}
.picker-text
{
	font-size: 14pt;
	font-weight: 400;
	letter-spacing: 0px;
	line-height: 24.7px;
	color: #bbbbc7;
}
.blur2>*:not(.infoBox){
	filter: blur(5px);
}
</style>