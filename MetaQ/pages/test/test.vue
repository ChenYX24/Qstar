<template>  
  <view class="contentBox">  
<input type="radio" name="radios" value="1" v-model="param"><label>one</label>
<br>
<input type="radio" name="radios" value="2" v-model="param"><label>two</label>
<br>
<input type="radio" name="radios" value="3" v-model="param"><label>three</label>
  </view>  
</template>  
  
<script>  
export default {  
  data() {  
    return {  
      selected: [false, false, false],  
	   param:'1' 
    };  
  },  
  methods: {  
    toggleSelect(index) {  
      this.selected.splice(index, 1, !this.selected[index]);  
    },  
  },  
};  
</script>  
  
<style>  
.contentBox {  
  display: flex;  
  flex-direction: column;  
  width: 100%;  
}  
.select {  
  display: flex;  
  align-items: center;  
  margin-bottom: 10px;  
}  
.text {  
  margin-left: 10px;  
}  
</style>  
