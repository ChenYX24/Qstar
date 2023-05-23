<template>
  <view class="background">
    <view class="input_class">
      <view class="textarea_border">
        <textarea maxlength="900"
                  ref="textareaDom"
                  :style="{ height: h1 }"
                  name="title"
                  placeholder="请输入问卷标题"
                  @input="test"></textarea>
      </view>
      <view class="textarea_border">
        <textarea name="description"
                  placeholder="请输入问卷简介"></textarea>
      </view>
    </view>
    <view class="add_question" @click="test">
      <p>+</p>
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
  data() {
    return {
      tab: '',
      textareaDom: null,
      h1: null
    };
  },
  mounted() {
    this.textareaDom = this.$refs.textareaDom;
  },
  methods: {
    test() {
      const textarea = this.$refs.textareaDom;
      const lines = textarea.value.split(/\r\n|\r|\n/).length;
      const lineHeight = parseInt(getComputedStyle(textarea).lineHeight, 10);
      this.h1 = `${lines * lineHeight}px`;
    }
  }
};
</script>

<style scoped lang="less">
.background {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  height: 100vh;
  position: relative;
  top: 0;
  left: 0;
  background: linear-gradient(225deg, rgba(245, 224, 230, 1) 0%, rgba(228, 218, 241, 1) 38.89%, rgba(237, 248, 255, 1) 67.78%, rgba(230, 224, 250, 1) 100%);
}

.input_class {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.textarea_border {
  border: 2px dashed #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;

  textarea {
	  border: 2px solid red;
    border: none;
    display: flex;
    overflow-wrap: break-word;
    white-space: normal;
    text-align: center;
    overflow-y: auto;
    resize: none;
    font-size: 16px;
    font-family: inherit;
    line-height: 1.5;
  }
}

.textarea_border:first-child {
  margin-top: 4vh;
  height: 12vh;
  width: 80vw;
}

.textarea_border:last-child {
  margin-top: 2vh;
  height: 8vh;
  width: 80vw;
}

textarea[name="title"] {
  height: 3vh;
  width: 100%;
}

textarea[name="description"] {
  height: 3vh;
  width: 100%;
}

.add_question {
  margin: 2vh;
  height: 15vh;
  width: 80vw;
  border: 2px solid #fff;
  border-radius: 5vw;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
}

.add_question p {
  margin: 0;
  text-align: center;
  font-size: 15vw;
  color: #E4C2FF;
}
</style>