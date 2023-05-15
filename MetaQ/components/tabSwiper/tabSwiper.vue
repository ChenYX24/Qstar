<template>
  <view class="custom-tab">
    <view
      class="tab-item"
      :class="{ 'tab-active': currentTab === 0 }"
      @tap="switchTab(0)"
    >
		{{text1}}
      <view v-if="currentTab === 0" class="active-dot"></view>
	  <view v-else class="dot"></view>
    </view>
    <view
      class="tab-item"
      :class="{ 'tab-active': currentTab === 1 }"
      @tap="switchTab(1)"
    >
      	{{text2}}
      <view v-if="currentTab === 1" class="active-dot" ></view>
	  <view v-else class="dot"></view>
    </view>
  </view>
</template>

<script>
	export default {
	  props: {
	    currentTab: {
	      type: Number,
	      default: 0
	    },
		text1:{
			type:String,
			default:''
		},
		text2:{
			type:String,
			default:''
		}
	  },
	  methods: {
	    switchTab(index) {
			this.$emit("tab-change", index);
	    }
	  }
	};
</script>

<style lang="less">
.custom-tab {
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 50px;
}

.tab-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 20px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 27.44px;
  color: rgba(0, 0, 0, 0.36);
}

.tab-active {
  color: rgba(204, 139, 247, 1);
}

.active-dot {
  border-radius: 50%;
  width: 12px;
  height: 12px;
  opacity: 1;
  background: rgba(221, 183, 247, 1);
  filter: blur(0.6px);
  position: relative;
  top: 3px;
}
.dot {
  border-radius: 50%;
  width: 12px;
  height: 12px;
  opacity: 1;
  background: rgba(221, 183, 247, 0);
  filter: blur(0.6px);
  position: relative;
  top: 3px;
}
</style>