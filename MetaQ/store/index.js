import { createStore } from 'vuex';  
  
const store = createStore({  
  state: {  
    now_operate: 100,
	IsJump:false,
	questionNire:{
		title:'',
		description:'',
		content:[],
	},
	targetView:null,
	
	
  }, 
  mutations: {  
		setNowOperate(state, id){
			state.now_operate = id; 
		  },
		setIsJump(state){
			state.IsJump=!state.IsJump;
		},
		setContent(state,content){
			state.questionNire.content=content;
		},
		setTargetView(state,targetView){
			state.targetView=targetView;
		},
		setEditHeight(state,editHeight){
			state.editHeight=editHeight;
		},
		setQuestionNire(state,questionNire){
			state.questionNire=questionNire;
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
