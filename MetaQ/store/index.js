import { createStore } from 'vuex';  
  
const store = createStore({  
  state: {  
    count: 0,  
  },  
  mutations: {  
    increment(state) {  
      state.count++;  
    },  
    decrement(state) {  
      state.count--;  
    },  
  },  
  actions: {  
    incrementAsync({ commit }) {  
      setTimeout(() => {  
        commit('increment');  
      }, 1000);  
    },  
  },  
  getters: {  
    doubleCount(state) {  
      return state.count * 2;  
    },  
  },  
});  
  
export default store;  
