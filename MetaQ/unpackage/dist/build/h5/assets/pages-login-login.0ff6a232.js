import{r as s,s as a,v as e,g as l,x as t,a as i,n as o,o as n,c as r,w as c,i as d,b as m,d as u,y as p,z as h,f,A as g,I as k,l as b,B as w,C as v}from"./index-db74daea.js";import{_}from"./_plugin-vue_export-helper.1b428a4d.js";const C=_({onLoad(s){this.flag=s.flag},data:()=>({formData:{email:"",password:""},token:"",trueEmail:!0,check:!1,visible:!1,flag:!1}),computed:{isFill(){return!1!==this.trueEmail&&""!==this.formData.password&&this.check}},methods:{submitForm(){console.log(this.$store.state.host+"/login"),s({url:this.$store.state.host+"/login",method:"POST",header:{"Content-Type":"application/json"},data:{email:this.formData.email,password:this.formData.password},success:s=>{if(console.log(s),this.token=s.data," "==this.token)a({title:"登录失败",icon:"none"});else{const s=JSON.stringify(this.token);console.log("token",s),e("token",s),console.log("存在缓存中的:",l("token")),this.flag&&t(),i({url:"/pages/myQ/myQ"})}},fail:()=>{},complete:()=>{}})},checkEmail(){this.check=!0;this.trueEmail=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.formData.email)},changeView(){this.visible=!this.visible},Register(){o({url:"/pages/register/register"})},findPasswd(){o({url:"/pages/findPasswd/findPasswd"})}}},[["render",function(s,a,e,l,t,i){const o=d,_=g,C=k,y=b,D=w,F=v;return n(),r(o,null,{default:c((()=>[m(o,{class:"bg"},{default:c((()=>[m(o,{class:"title"},{default:c((()=>[u(" Login ")])),_:1}),m(F,{onSubmit:p(i.submitForm,["prevent"]),class:"Form"},{default:c((()=>[h("div",{class:f({"Input-margin":t.trueEmail,errorWrite:!t.trueEmail})},[m(_,{class:"label"},{default:c((()=>[u("邮箱：")])),_:1}),m(C,{type:"email",placeholder:"请输入邮箱",class:"Input",maxlength:"20",modelValue:t.formData.email,"onUpdate:modelValue":a[0]||(a[0]=s=>t.formData.email=s),onInput:i.checkEmail,required:""},null,8,["modelValue","onInput"])],2),h("div",{class:"Input-margin"},[m(_,{class:"label"},{default:c((()=>[u("密码：")])),_:1}),m(C,{type:this.visible?"text":"password",maxlength:"16",placeholder:"请输入密码",class:"Input",modelValue:t.formData.password,"onUpdate:modelValue":a[1]||(a[1]=s=>t.formData.password=s),required:""},null,8,["type","modelValue"]),m(y,{class:"img",src:this.visible?"../../static/login/avisible.png":"../../static/login/invisible.png",onClick:i.changeView},null,8,["src","onClick"])]),h("div",{class:"buttonGroup"},[m(o,{class:f({active:i.isFill,inactive:!i.isFill}),disabled:!i.isFill,type:"submit",onClick:i.submitForm},{default:c((()=>[u("登录")])),_:1},8,["class","disabled","onClick"]),m(D,{onClick:i.Register,class:"link"},{default:c((()=>[u("没有账户？点此注册")])),_:1},8,["onClick"]),m(D,{onClick:i.findPasswd,class:"link"},{default:c((()=>[u("忘记密码？点此找回")])),_:1},8,["onClick"])])])),_:1},8,["onSubmit"])])),_:1})])),_:1})}],["__scopeId","data-v-f658db23"]]);export{C as default};