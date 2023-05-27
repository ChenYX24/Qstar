import { createStore } from 'vuex';  
  
const store = createStore({  
  state: {  
    now_operate: -1,
	IsJump:false,
	questionNire:{
		nireTitle:'',
		nireIntroduction:'',
		all_content:[]
	},
	offsetHeight:0
  }, 
  mutations: {  
		setNowOperate(state, id){
			state.now_operate = id; 
		  },
		setIsJump(state){
			state.IsJump=!state.IsJump;
		},
		set_all_content(state,all_content){
			state.questionNire.all_content=all_content;
		},
		setOffsetHeight(state,height){
			state.offsetHeight=height;
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
