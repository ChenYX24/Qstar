import { createStore } from 'vuex';  
  
const store = createStore({  
  state: {  
    now_operate: -1,
	IsJump:false
  },  
  mutations: {  
		setNowOperate(state, id){
			state.now_operate = id; 
		  },
		setIsJump(state){
			state.IsJump=!state.IsJump;
		}  
			  
  },
//   actions: {  
//     incrementAsync({ commit }) {  
//       setTimeout(() => {  
//         commit('increment');  
//       }, 1000);  
//     },  
//   },  
//   getters: {  
//     doubleCount(state) {  
//       return state.count * 2;  
//     },  
//   },  
});  
  
export default store;  
