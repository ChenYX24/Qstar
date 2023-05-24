import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  // 这里是Vuex store的配置信息
    state: {
	//记录的是当前正在操作的问题的id
      now_operate: '-1'
    },
	mutations: {
		setNowOperate(state, id){
	      state.now_operate = id 
	    }  
	} 
})

export default store

