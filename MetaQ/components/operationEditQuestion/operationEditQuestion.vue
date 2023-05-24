<template>
	<view>
		<view class="operation">
			<view class="outbox">
				<view class="box">
					<view class="opration-concrete">
						<img src="/static/operationEditQuestion/edit.png" alt="">
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
	export default {
		name:'operationEditQuestion',
		data() {
			return {
				
			};
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
	width: 90vw;
	height: 10vh;
	border: 1px dashed green;
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
