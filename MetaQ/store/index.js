import { createStore } from 'vuex';  
  
const store = createStore({  
  state: {  
    now_operate: 100,
	
	questionNire:{
		title:'',
		description:'',
		content:[],
	},
	targetView:null,
	//一下是关于每个问题下面的操作栏的变量
	IsJump:false,
	//最后一位用来记录选择了那个操作
	otherOperate:false,
	index:-1,
	qnid:-1
  }, 
  mutations: {  
	    setNowOperate(state, id){
			state.now_operate = id; 
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
		},
		setIsJump(state){
			state.IsJump=!state.IsJump;
		},
		setOtherOperate(state,index){
			state.otherOperate=!state.otherOperate;
			state.index=index;
		},
		setIsCreate(state,T){
			state.qnid=T;
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
