<template>
	<view class="background">  
		<Time @getTime="getTime"></Time>
	  <view class="contentBox response">  
		<view class="select" @tap="toggleSelect(0)">  
		  <image :src="selected[0] ? '/static/setting/selected.png' : '/static/setting/noSelected.png'" class="icon" />  
		  <view class="text">记录用户昵称</view>  
		</view>  
		<view class="select" @tap="toggleSelect(1)">  
		  <image :src="selected[1] ? '/static/setting/selected.png' : '/static/setting/noSelected.png'" class="icon" />  
		  <view class="text">每个用户只能回答一次</view>  
		</view>  
		<view class="select" @tap="toggleSelect(2)">  
		  <image :src="selected[2] ? '/static/setting/selected.png' : '/static/setting/noSelected.png'" class="icon" />  
		  <view class="text">积分奖励（待开发）</view>  
		</view>  
	  </view> 
		<view class="contentBox authority">
			<view class="title">
				权限管理
			</view>
			<view class="scrollBox">
				<view class="peopleBox">
					<view class="infoBox">
						<view class="img">
							<image src="/static/home/profilePhoto.png" mode="aspectFill" ></image>
						</view>
						<view class="name">
							尘
						</view>
					</view>
					<view class="rightBox">
						<picker mode="selector" :range="roles" @change="roleChanged">
						  <view class="picker">{{roles[selectedRole]}}</view>  
						</picker> 
					</view>
				</view>
				<view class="peopleBox">
					<view class="infoBox">
						<view class="img">
							<image src="/static/home/profilePhoto.png" mode="aspectFill" ></image>
						</view>
						<view class="name">
							尘
						</view>
					</view>
					<view class="rightBox">
						<picker mode="selector" :range="roles" @change="roleChanged">
						  <view class="picker">{{roles[selectedRole]}}</view>  
						</picker> 
					</view>
				</view>
				<view class="peopleBox">
					<view class="infoBox">
						<view class="img">
							<image src="/static/home/profilePhoto.png" mode="aspectFill" ></image>
						</view>
						<view class="name">
							尘
						</view>
					</view>
					<view class="rightBox">
						<picker mode="selector" :range="roles" @change="roleChanged">
						  <view class="picker">{{roles[selectedRole]}}</view>  
						</picker> 
					</view>
				</view>
			</view>

		</view>
		<view class="button" @tap="goback">
			确认
		</view>
		<tab-bar :tab="tab" :Type="1"></tab-bar>
	</view>
</template>

<script>
	import TabBar from '/components/tabbar/tabbar.vue';
	import Time from '/components/chooseTime/chooseTime.vue'
	export default {
		components: {
			TabBar,
			Time
		},
		data() {  
			return { 
				tab: 'setting',
				startTime: "",  
				endTime: "",
				selected: [false, false, false], 
			     roles: ['管理者', '编辑者', '查看者'],  
			     selectedRole: 0
			};  
		},  
	  methods: {
		getTime(data){
			this.startTime=data.startTime
			this.endTime=data.endTime
		},
		goback(){
			uni.navigateTo({
				url:('/pages/editQuestionnire/editQuestionnire')
			})
		},
		toggleSelect(index) {  
		  this.selected.splice(index, 1, !this.selected[index]);  
		}, 
		roleChanged(e) {
		  this.selectedRole = e.target.value;  
		}
	  }, 
	}
</script>

<style lang="less">
	.background{
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 100vw;
		height: 100vh;
		position: relative;
		padding-top: 10%;
		gap: 5%;
		top: 0;
		left:0;
		background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
	}
	.response{
		align-items: flex-start!important;
		height: 18vh!important;
		gap:5%;
		.select{
			display: flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
			width: 100%;
			.icon{
				height: 8vw;
				width: 8vw;
			}
			.text{
				margin-left: 5%;
				width: 100%;
			}
		}
	}
	.contentBox{
		border-radius: 20px;
		background: rgba(255, 255, 255, 0.8);
		box-shadow: 2px 2px 20px 0px rgba(136, 63, 143, 0.15);
		width: 80vw;
		height: 12vh;
		box-sizing: border-box;
		padding: 5%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		.startTime,.endTime{
			width: 100%;
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			align-items: center;
			background: rgba(255, 255, 255, 0.8);
		}
		.endTime{ 
			border-width: 0 2px 2px 2px;
			border-style: solid;  
			border-color: #e1e1eb;  
		}
		.startTime{
			border-width: 2px 2px 2px 2px;
			border-style: solid;
			border-color: #e1e1eb;
		}
	}
	.button{
		position: absolute;
		bottom: 25vh;
		width: 80vw;
		height: 10vh;
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;
		border-radius: 12px;
		background: linear-gradient(90deg, rgba(255, 255, 255, 0.8) 0%, rgba(255, 242, 255, 0.8) 100%);
		box-shadow: 0px 2px 4px 0px rgba(136, 63, 143, 0.25);
		color:rgba(179, 157, 194, 1) ;
		font-size: 16pt;
	}
	.authority{
		justify-content: flex-start!important;
		    height: 18vh;
		.scrollBox{
			margin-top: 3%;
			width: 100%;
			display: flex;
			overflow-y: scroll;
			flex-direction: column;
			align-items: center;
			align-content: center;
			.peopleBox {
			  display: flex;  
			  justify-content: space-between;
			  width: 80%;
			}  
			.infoBox {
			  display: flex;  
			  align-items: center;
			  .name {
			  margin-left: 10px;  
			  }
			  .img{
			  	width: 18vw;
			  	height: 18vw;
			  	border: 2px solid rgba(221, 183, 247, 1);
			  	border-radius: 50%;
			      overflow: hidden;
			  	image{
			  		width: 100%;
			  		height: 100%;
			  	}
			  }
			}
			.rightBox {
			  display: flex;  
			  align-items: center;  
			  .picker {
				border: 1px solid #999;  
				padding: 5px;  
				border-radius: 5px;  
			  } 
			}
		}

	}
	
	  
	  
	  
  
	  
  
	  
 
</style>

