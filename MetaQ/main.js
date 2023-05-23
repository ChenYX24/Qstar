import App from './App'

// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'

try {
  function isPromise(obj) {
    return (
      !!obj &&
      (typeof obj === "object" || typeof obj === "function") &&
      typeof obj.then === "function"
    );
  }

  // 统一 vue2 API Promise 化返回格式与 vue3 保持一致
  uni.addInterceptor({
    returnValue(res) {
      if (!isPromise(res)) {
        return res;
      }
      return new Promise((resolve, reject) => {
        res.then((res) => {
          if (res[0]) {
            reject(res[0]);
          } else {
            resolve(res[1]);
          }
        });
      });
    },
  });
  //自动登录
  const token = localStorage.getItem('token');
  console.log(token);
  if (token) {
    // 发送请求进行自动登录
    axios.post(/*'https://metaq.scutbot.icu/login'*/
    			'http://localhost:8080/login', {token})
        .then(response => {
          var response = response.data;
    	  if(response == " ")
    	  {
    		  alert("登录失败");
    	  }
    	  console.log(response);
    	  localStorage.setItem('token', JSON.stringify(response));
    	  console.log(localStorage.getItem('token'));
		  console.log("自动登录成功！");
		  uni.navigateTo({
		  	url:"/pages/myQ/myQ"
		  })
        })
        .catch(error => {
          console.log(error);
        });
  }
} catch (error) { }

const app = new Vue({
  ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif