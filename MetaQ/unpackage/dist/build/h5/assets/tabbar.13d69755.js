import{o as e,c as s,w as t,b as a,j as o,F as i,k as n,p as c,f as l,E as r,i as d,r as h,g as u,n as g,M as p,s as A,N as m,O as I,d as b,t as Q,l as C,a as k,G as E}from"./index-db74daea.js";import{d as S,t as T,h as f,a as y}from"./duoxuanAnswer.0b2ac52f.js";import v from"./components-answerQuestion-calendarSeleteAnswer-calendarSeleteAnswer.af3f5d9d.js";import{_ as N}from"./_plugin-vue_export-helper.1b428a4d.js";import{r as w}from"./uni-app.es.176bdeef.js";const q=N({components:{danxuanAnswer:S,tiankongAnswer:T,huadongtiaoAnswer:f,duoxuanAnswer:y,riqiAnswer:v},props:{questionNireProps:{type:Object,default:{}},isShow:{type:Boolean,default:!1}},mounted(){},data(){return{answer:[],mode:0,componentName:["danxuanAnswer","duoxuanAnswer","tiankongAnswer","huadongtiaoAnswer","","riqiAnswer"],title:"关于c10居住学生学校住宿感受的的调研",description:"请c10的同学填写，谢谢配合！",questionNire:this.questionNireProps}},methods:{autoChange(e){document.getElementById(e.currentTarget.id).style.height=`${e.detail.height}px`},chooseComponent(e){switch(this.questionNire.content[e].type){case"SINGLE":return this.componentName[0];case"MULTIPLE":return this.componentName[1];case"BLANK":return this.componentName[2];case"SLIDE":return this.componentName[3];case"PHONE":return this.componentName[4];case"DATE":return this.componentName[5]}},submitSuccess(){this.$refs.questionAnswerRef.forEach((e=>{this.answer.push(e.answer)})),console.log(this.answer)}}},[["render",function(h,u,g,p,A,m){const I=r,b=d;return e(),s(b,{class:l(["addContainer",{show:g.isShow}])},{default:t((()=>[a(b,{class:"questionnire_page"},{default:t((()=>[a(b,{class:"input_class2"},{default:t((()=>[a(b,{class:"textarea_border2"},{default:t((()=>[a(I,{maxlength:"900",id:"title2",placeholder:"请输入问卷标题",onLinechange:m.autoChange,modelValue:A.questionNire.title,"onUpdate:modelValue":u[0]||(u[0]=e=>A.questionNire.title=e),disabled:""},null,8,["onLinechange","modelValue"])])),_:1}),a(b,{class:"textarea_border2"},{default:t((()=>[a(I,{maxlength:"900",id:"descripition2",placeholder:"请输入问卷简介",onLinechange:m.autoChange,modelValue:A.questionNire.description,"onUpdate:modelValue":u[1]||(u[1]=e=>A.questionNire.description=e),disabled:""},null,8,["onLinechange","modelValue"])])),_:1})])),_:1}),(e(!0),o(i,null,n(A.questionNire.content,((a,o)=>(e(),s(b,{class:"question-all",key:o},{default:t((()=>[(e(),s(c(m.chooseComponent(o)),{num:(o+1).toString(),content:a},null,8,["num","content"]))])),_:2},1024)))),128))])),_:1})])),_:1},8,["class"])}],["__scopeId","data-v-f01c434c"]]),J="data:image/png;base64,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";const B=N({name:"save",data:()=>({b1:0,isQrcode:!1,checkImg:"/static/save/checkImg.png",qrcode:"/static/save/qrcode.png"}),computed:{button1Text(){return 0==this.b1?"分享填写链接":1==this.b1?"已复制到剪贴板":2==this.b1?"更换二维码背景":void 0},button2Text(){return this.isQrcode?"保存到相册":"更换二维码背景"}},watch:{isShow(e,s){!s&&e&&(this.b1=0,this.isQrcode=!1)}},props:{questionNirePorps:{type:Object},isShow:{type:Boolean,default:!1},Type:{type:Number,default:0},id:{type:Number,default:-1}},methods:{save(){this.questionNirePorps.commit=!1,console.log("save",this.questionNirePorps),console.log("this.questionNirePorps",this.questionNirePorps),-1==this.$store.state.qnid?(console.log("创建并保存问卷"),h({url:this.$store.state.host+"/create",method:"POST",header:{"Content-Type":"application/json",token:u("token")},data:this.questionNirePorps,success:e=>{console.log(e.data)},fail:()=>{},complete:()=>{}})):(console.log("保存已创建问卷"),this.questionNirePorps.id=this.$store.state.qnid,this.$store.state.qnid=-1,h({url:this.$store.state.host+"/save",method:"POST",header:{"Content-Type":"application/json",token:u("token")},data:this.questionNirePorps,success:e=>{console.log(e.data)},fail:()=>{},complete:()=>{}}),g({url:"/pages/myQ/myQ"}))},push(){console.log("push",this.questionNirePorps),this.questionNirePorps.commit=!0,console.log("this.questionNirePorps",this.questionNirePorps),-1==this.$store.state.qnid?(console.log("创建并发布问卷"),h({url:this.$store.state.host+"/create",method:"POST",header:{"Content-Type":"application/json",token:u("token")},data:this.questionNirePorps,success:e=>{console.log(e.data)},fail:()=>{},complete:()=>{}}),g({url:"/pages/myQ/myQ"})):(console.log("发布已创建问卷"),this.questionNirePorps.id=this.$store.state.qnid,this.$store.state.qnid=-1,h({url:this.$store.state.host+"/save",method:"POST",header:{"Content-Type":"application/json",token:u("token")},data:this.questionNirePorps,success:e=>{console.log(e.data)},fail:()=>{},complete:()=>{}}),g({url:"/pages/myQ/myQ"})),g({url:"/pages/myQ/myQ"})},goBack(){this.isQrcode?(this.isQrcode=!this.isQrcode,this.b1=0):this.$emit("updateSave")},copyText(){0==this.b1&&h({url:this.$store.state.host+"/share",method:"POST",header:{"Content-Type":"application/json",token:u("token")},data:{id:this.$store.state.qnid,flag:!1},success:e=>{const s=e.data;p({data:s,success:()=>{A({title:"已复制到剪贴板",icon:"success"}),this.b1=1},fail:e=>{console.error("Error copying text: ",e),A({title:"复制失败",icon:"none"}),this.b1=0}})},fail:()=>{},complete:()=>{}})},isMobileDevice:()=>!0,saveQRCode(){if(this.isQrcode)if(this.isMobileDevice())console.log(111),m({url:this.qrcode,success:e=>{200===e.statusCode&&I({filePath:e.tempFilePath,success:()=>{A({title:"二维码已保存",icon:"success"})},fail:e=>{console.error("Error saving image: ",e),A({title:"保存失败",icon:"none"})}})},fail:e=>{console.error("Error downloading image: ",e),A({title:"保存失败",icon:"none"})}});else{console.log(222);const e=document.createElement("a");e.href=this.qrcode,e.download="mataQ.png",e.style.display="none",document.body.appendChild(e),e.click(),document.body.removeChild(e)}else h({url:this.$store.state.host+"/share",method:"POST",header:{"Content-Type":"application/json",token:u("token")},data:{id:this.$store.state.qnid,flag:!0},success:e=>{console.log(e),console.log(e.data),this.qrcode=e.data},fail:()=>{},complete:()=>{}}),this.isQrcode=!this.isQrcode,this.b1=2}}},[["render",function(o,i,n,c,l,r){const h=C,u=d;return 1==n.Type?(e(),s(u,{key:0,class:"saveContainer"},{default:t((()=>[a(u,{class:"save"},{default:t((()=>[a(u,{class:"navBar"},{default:t((()=>[a(h,{src:J,mode:"aspectFit",class:"back",onClick:r.goBack},null,8,["onClick"])])),_:1}),a(h,{src:l.isQrcode?l.qrcode:l.checkImg,mode:"aspectFit",class:"checkImg"},null,8,["src"]),a(u,{class:"button",onClick:r.save},{default:t((()=>[b(" 保存 ")])),_:1},8,["onClick"]),a(u,{class:"button2",onClick:r.push},{default:t((()=>[b(" 发布 ")])),_:1},8,["onClick"])])),_:1})])),_:1})):(e(),s(u,{key:1,class:"saveContainer"},{default:t((()=>[a(u,{class:"save"},{default:t((()=>[a(u,{class:"navBar"},{default:t((()=>[a(h,{src:J,mode:"aspectFit",class:"back",onClick:r.goBack},null,8,["onClick"])])),_:1}),a(h,{src:l.isQrcode?l.qrcode:l.checkImg,mode:"aspectFit",class:"checkImg"},null,8,["src"]),a(u,{class:"button",onClick:r.copyText},{default:t((()=>[b(Q(r.button1Text),1)])),_:1},8,["onClick"]),a(u,{class:"button2",onClick:r.saveQRCode},{default:t((()=>[b(Q(r.button2Text),1)])),_:1},8,["onClick"])])),_:1})])),_:1}))}],["__scopeId","data-v-32d5c5b3"]]),P=""+new URL("add-febc0542.png",import.meta.url).href;const x=N({components:{add:q,save:B},data(){return{activeTab:this.tab,isAdd:!1,isSaveFlag:!1,questionNire:this.questionNireProps}},computed:{isSave(){return!(!this.isSaveFlag&&!this.outIsSave)&&(this.isSaveFlag=!0,console.log(this.isSaveFlag,this.outIsSave),!0)}},props:{questionNireProps:{type:Object,default:{}},outIsSave:{type:Boolean,default:!1},tab:{type:String,default:"question"},isExpanded:{type:Boolean,default:!1},Type:{type:Number,default:0}},methods:{changeAdd(){this.isAdd=!this.isAdd},changeSave(){this.$emit("changeSave"),this.isSaveFlag=!this.isSaveFlag,this.isSaveFlag||(this.activeTab="")},switchTab(e){if(!this.isExpanded)if("add"===e&&0==this.Type)g({url:"/pages/editQuestionnire/editQuestionnire"});else if("add"===e&&1==this.Type)this.isAdd=!this.isAdd;else switch(this.activeTab=e,e){case"question":k({url:`/pages/myQ/myQ?tab=${e}`});break;case"home":k({url:`/pages/home/home?tab=${e}`});break;case"setting":g({url:`/pages/setting/setting?tab=${e}`});break;case"save":this.isSaveFlag=!this.isSaveFlag,this.isSaveFlag||(this.activeTab="")}}}},[["render",function(o,i,n,r,h,u){const g=C,p=d,A=w(c("add"),q),m=w(c("save"),B);return e(),s(p,{class:l(["tabContainer",{blur:n.isExpanded}])},{default:t((()=>[a(p,{class:"tab-bar"},{default:t((()=>[0==n.Type?(e(),s(p,{key:0,class:"tab-item",onClick:i[0]||(i[0]=e=>u.switchTab("question"))},{default:t((()=>[a(g,{class:"imgGroup notebIcon leftIcon",src:"question"===h.activeTab?"/static/tabbar/Q2.png":"/static/tabbar/Q.png"},null,8,["src"]),a(p,{class:l(["dot",{active:"question"===h.activeTab}])},null,8,["class"])])),_:1})):E("",!0),1==n.Type?(e(),s(p,{key:1,class:"tab-item",onClick:i[1]||(i[1]=e=>u.switchTab("setting"))},{default:t((()=>[a(g,{class:"imgGroup notebIcon leftIcon",src:"setting"===h.activeTab?"/static/tabbar/setting2.png":"/static/tabbar/setting.png"},null,8,["src"]),a(p,{class:l(["dot",{active:"setting"===h.activeTab}])},null,8,["class"])])),_:1})):E("",!0),0==n.Type?(e(),s(p,{key:2,class:"tab-item add",onClick:i[2]||(i[2]=e=>u.switchTab("add"))},{default:t((()=>[a(g,{class:"imgGroup addImg",src:P})])),_:1})):E("",!0),1==n.Type?(e(),s(p,{key:3,class:"tab-item add",onClick:i[3]||(i[3]=e=>u.switchTab("add"))},{default:t((()=>[a(g,{class:"imgGroup addImg",src:P})])),_:1})):E("",!0),1==n.Type?(e(),s(p,{key:4,class:"tab-item",onClick:i[4]||(i[4]=e=>u.switchTab("save"))},{default:t((()=>[a(g,{class:"imgGroup notebIcon leftIcon",src:"save"===h.activeTab?"/static/tabbar/save2.png":"/static/tabbar/save.png"},null,8,["src"]),a(p,{class:l(["dot",{active:"save"===h.activeTab}])},null,8,["class"])])),_:1})):E("",!0),0==n.Type?(e(),s(p,{key:5,class:"tab-item",onClick:i[5]||(i[5]=e=>u.switchTab("home"))},{default:t((()=>[a(g,{class:"imgGroup",src:"home"===h.activeTab?"/static/tabbar/home2.png":"/static/tabbar/home.png"},null,8,["src"]),a(p,{class:l(["dot",{active:"home"===h.activeTab}])},null,8,["class"])])),_:1})):E("",!0)])),_:1}),a(A,{class:l({show:h.isAdd}),isShow:h.isAdd,onUpdateAdd:u.changeAdd,questionNireProps:h.questionNire},null,8,["class","isShow","onUpdateAdd","questionNireProps"]),1==n.Type?(e(),s(m,{key:0,class:l({show:u.isSave}),isShow:u.isSave,onUpdateSave:u.changeSave,Type:n.Type,questionNirePorps:h.questionNire},null,8,["class","isShow","onUpdateSave","Type","questionNirePorps"])):(e(),s(m,{key:1,class:l({show:u.isSave}),isShow:u.isSave,onUpdateSave:u.changeSave},null,8,["class","isShow","onUpdateSave"]))])),_:1},8,["class"])}],["__scopeId","data-v-c917eb92"]]);export{x as T};