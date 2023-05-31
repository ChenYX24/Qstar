<template>
	<view>
		<view class="operation">
			<view class="outbox">
				<view class="box">
					<view class="opration-concrete" @click="toEdit">
						<img src="/static/operationEditQuestion/edit.png" alt="" >
						编辑
					</view>
					<view class="opration-concrete">
						<img src="/static/operationEditQuestion/copy.png" alt="">
						复制
					</view>
					<view class="opration-concrete">
						<img src="/static/operationEditQuestion/up.png" alt="">
						上移
					</view>
					<view class="opration-concrete">
						<img src="/static/operationEditQuestion/down.png" alt="">
						下移
					</view>
					<view class="opration-concrete">
						<img src="/static/operationEditQuestion/delete.png" alt="">
						删除
					</view>
				</view>
			</view>

		</view>

		
		
	</view>
</template>

<script>
	import store from '/store/index.js'
	export default {
		name:'operationEditQuestion',
		data() {
			return {
				
			};
		},
		props:{
			num:{
				type:String,
				default:'-1'
			}
		},
		methods:{
			toEdit(e){
				let editHeight=this.$el.clientHeight
				console.log(this.$el.clientHeight)
				this.$store.commit('setEditHeight',editHeight);
				//修改当前正在操作的值
				this.$store.commit('setNowOperate',this.num-1);
				this.$store.commit('setIsJump');
				this.$emit('clickSon')
			}
		}
	}
</script>

<style lang="less">
.operation{
	
	width: 100vw;
	display: flex;
	// justify-content: center;

}
.outbox{
    box-sizing: border-box;
	width: 90vw;
	height: 10vh;
	display: flex;
	align-items: center;
	justify-content: center;
	border-bottom-left-radius:10px;
	border-bottom-right-radius: 10px;
}
.box{
	width: 85vw;
	height: 10vh;
	// border: 1px solid red;
	display: flex;
	align-items: center;
    justify-content: center;
	justify-content: space-between;
}

.opration-concrete{
	display: flex;
	align-items: center;
	flex-direction: column;
	
}

.opration-concrete img{
	height: 5vh;
}
</style>
