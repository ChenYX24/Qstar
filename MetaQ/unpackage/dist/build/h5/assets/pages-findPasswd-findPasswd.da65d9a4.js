import{n as a,o as e,c as t,w as i,i as s,a as l,b as o,s as r,e as c,d as n,t as d,u as m,I as u,j as h,v as p,x as f}from"./index-6fe189e7.js";import{a as g}from"./axios.5ae1da96.js";import{_ as v}from"./_plugin-vue_export-helper.1b428a4d.js";const b=v({data:()=>({formData:{email:"",password:"",confirm:"",verification:""},active:!0,getCodeText:"获取验证码",trueEmail:!0,code:"123456",success:"",check:!1,visible1:!1,visible2:!1}),computed:{isFill(){return this.trueEmail&&""!==this.formData.username&&""!==this.formData.password&&""!==this.formData.confirm&&""!==this.formData.verification},enableGetcode(){return this.trueEmail&&this.active},isSame(){return this.formData.password==this.formData.confirm}},methods:{changepasswd(){g.post("https://metaq.scutbot.icu/checkcode",{email:this.formData.email,code:this.formData.verification}).then((e=>{1==e.data?g.post("https://metaq.scutbot.icu/changepasswd",{email:this.formData.email,password:this.formData.password}).then((e=>{console.log("响应值",e.data),1==e.data?(alert("修改密码成功！"),a({url:"/pages/login/login"})):alert("修改密码失败！")})).catch((a=>{console.log(a)})):alert("验证码错误!")})).catch((a=>{console.log(a)}))},Login(){a({url:"/pages/login/login"})},startTime(){if(this.enableGetcode&&this.check){g.post("https://metaq.scutbot.icu/sendcode",{email:this.formData.email}).then((a=>{0==a.data&&alert("获取验证码失败!")})).catch((a=>{console.log(a)}));var a=30;this.active=!1,this.getCodeText=a;const e=setInterval((()=>{0===--a?(clearInterval(e),this.active=!0,this.getCodeText="获取验证码"):this.getCodeText=a}),1e3)}},changeView1(){this.visible1=!this.visible1},changeView2(){this.visible2=!this.visible2},checkEmail(){this.check=!0;this.trueEmail=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.formData.email)}}},[["render",function(a,g,v,b,w,D){const k=s,_=m,x=u,V=h,C=p,I=f;return e(),t(k,null,{default:i((()=>[l(k,{class:"bg"},{default:i((()=>[l(k,{class:"title"},{default:i((()=>[o(" FindPasswd ")])),_:1}),l(I,{class:"Form"},{default:i((()=>[r("div",{class:c({"Input-margin":w.trueEmail,errorWrite:!w.trueEmail})},[l(_,{class:"label"},{default:i((()=>[o("邮箱：")])),_:1}),l(x,{type:"email",placeholder:"请输入邮箱",maxlength:"20",class:"Input",modelValue:w.formData.email,"onUpdate:modelValue":g[0]||(g[0]=a=>w.formData.email=a),onInput:D.checkEmail,required:""},null,8,["modelValue","onInput"])],2),r("div",{class:c({"Input-margin":D.isSame,errorWrite:!D.isSame})},[l(_,{class:"label"},{default:i((()=>[o("密码：")])),_:1}),l(x,{type:this.visible1?"text":"password",maxlength:"16",placeholder:"请输入密码",class:"Input",modelValue:w.formData.password,"onUpdate:modelValue":g[1]||(g[1]=a=>w.formData.password=a),required:""},null,8,["type","modelValue"]),l(V,{class:"img",src:this.visible1?"../../static/login/visible.png":"../../static/login/invisible.png",onClick:D.changeView1},null,8,["src","onClick"])],2),r("div",{class:c({"Input-margin":D.isSame,errorWrite:!D.isSame})},[l(_,{class:"confirm_label"},{default:i((()=>[o("确认密码：")])),_:1}),l(x,{class:"confirm_input",type:this.visible2?"text":"password",maxlength:"16",placeholder:"请再次输入密码",modelValue:w.formData.confirm,"onUpdate:modelValue":g[2]||(g[2]=a=>w.formData.confirm=a),required:""},null,8,["type","modelValue"]),l(V,{class:"img",src:this.visible2?"../../static/login/visible.png":"../../static/login/invisible.png",onClick:D.changeView2},null,8,["src","onClick"])],2),r("div",{id:"gbutton"},[r("div",{id:"getcode"},[l(_,{class:"label"},{default:i((()=>[o("验证码：")])),_:1}),l(x,{type:"text",placeholder:"请输入验证码",class:"Input",modelValue:w.formData.verification,"onUpdate:modelValue":g[3]||(g[3]=a=>w.formData.verification=a),required:""},null,8,["modelValue"])]),l(k,{style:n({"background-color":w.active?"rgba(255,255,255,0.8)":"rgba(208, 202, 217, 1)"}),onClick:D.startTime},{default:i((()=>[o(d(w.getCodeText),1)])),_:1},8,["style","onClick"])]),r("div",{class:"buttonGroup"},[l(C,{class:c({active:D.isFill,inactive:!D.isFill}),disabled:!D.isFill,type:"submit",onClick:D.changepasswd},{default:i((()=>[o("修改密码")])),_:1},8,["class","disabled","onClick"]),l(C,{onClick:D.Login,class:"link"},{default:i((()=>[o("立即登录")])),_:1},8,["onClick"])])])),_:1})])),_:1})])),_:1})}],["__scopeId","data-v-f7c65b34"]]);export{b as default};
