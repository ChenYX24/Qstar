import{n as a,z as t,o as s,c as e,w as d,a as i,b as o,e as c,j as n,A as l,i as u,r,l as h}from"./index-6fe189e7.js";import{_ as m}from"./_plugin-vue_export-helper.1b428a4d.js";import{r as p}from"./uni-app.es.f2de9903.js";const g=m({name:"add",data:()=>({}),props:{isShow:{type:Boolean,default:!1}},methods:{IDCreate(){this.$emit("updateAdd"),a({url:"/pages/IDCreate/IDCreate/IDCreate"})},addNote(){t("myData",{item:["","","","",""],index:-1}),this.$emit("updateAdd"),a({url:"/pages/notebook/notebookForm"})},ActivityThoughtCreate(){this.$emit("updateAdd"),a({url:"/pages/activity/ActivityThoughtCreate"})}}},[["render",function(a,t,r,h,m,p){const g=n,b=l,f=u;return s(),e(f,{class:c(["addContainer",{show:r.isShow}])},{default:d((()=>[i(f,{class:"imgAndText"},{default:d((()=>[i(g,{src:"https://tuanpet-cyx.oss-cn-guangzhou.aliyuncs.com/static/add/addNotebook.png",mode:"widthFix",class:"addImg imgNote",onClick:p.addNote},null,8,["onClick"]),i(b,{class:"Text notbookT"},{default:d((()=>[o("新建备忘事件")])),_:1})])),_:1}),i(f,{class:"imgAndText"},{default:d((()=>[i(g,{src:"https://tuanpet-cyx.oss-cn-guangzhou.aliyuncs.com/static/add/addActivityNote.png",mode:"widthFix",class:"addImg",onClick:p.ActivityThoughtCreate},null,8,["onClick"]),i(b,{class:"Text notbookT"},{default:d((()=>[o("新建活动笔记")])),_:1})])),_:1}),i(f,{class:"imgAndText"},{default:d((()=>[i(g,{src:"https://tuanpet-cyx.oss-cn-guangzhou.aliyuncs.com/static/add/addPetIDCard.png",mode:"widthFix",class:"addImg",onClick:p.IDCreate},null,8,["onClick"]),i(b,{class:"Text"},{default:d((()=>[o("新建宠物身份证")])),_:1})])),_:1}),i(f,{class:"imgAndText"},{default:d((()=>[i(g,{src:"https://tuanpet-cyx.oss-cn-guangzhou.aliyuncs.com/static/add/addPetIcon.png",mode:"widthFix",class:"addImg face imgNote"}),i(b,{class:"Text notbookT"},{default:d((()=>[o("新建萌宠表情")])),_:1})])),_:1})])),_:1},8,["class"])}],["__scopeId","data-v-95bd8b9e"]]),b=""+new URL("add-febc0542.png",import.meta.url).href;const f=m({components:{add:g},data(){return{activeTab:this.tab,isAdd:!1}},props:{tab:{type:String,default:"question"},isExpanded:{type:Boolean,default:!1}},methods:{changeAdd(){this.isAdd=!this.isAdd},switchTab(a){if(!this.isExpanded)if("add"===a)this.isAdd=!this.isAdd;else switch(this.activeTab=a,a){case"question":r({url:`/pages/myQ/myQ?tab=${a}`});break;case"home":r({url:`/pages/home/home?tab=${a}`})}}}},[["render",function(a,t,o,l,r,m){const f=n,T=u,x=p(h("add"),g);return s(),e(T,{class:c(["tabContainer",{blur:o.isExpanded}])},{default:d((()=>[i(T,{class:"tab-bar"},{default:d((()=>[i(T,{class:"tab-item",onClick:t[0]||(t[0]=a=>m.switchTab("question"))},{default:d((()=>[i(f,{class:"imgGroup notebIcon leftIcon",src:"question"===r.activeTab?"/static/tabbar/Q2.png":"/static/tabbar/Q.png"},null,8,["src"]),i(T,{class:c(["dot",{active:"question"===r.activeTab}])},null,8,["class"])])),_:1}),i(T,{class:"tab-item add",onClick:t[1]||(t[1]=a=>m.switchTab("add"))},{default:d((()=>[i(f,{class:"imgGroup addImg",src:b})])),_:1}),i(T,{class:"tab-item",onClick:t[2]||(t[2]=a=>m.switchTab("home"))},{default:d((()=>[i(f,{class:"imgGroup",src:"home"===r.activeTab?"/static/tabbar/home2.png":"/static/tabbar/home.png"},null,8,["src"]),i(T,{class:c(["dot",{active:"home"===r.activeTab}])},null,8,["class"])])),_:1})])),_:1}),i(x,{class:c({show:r.isAdd}),isShow:r.isAdd,onUpdateAdd:m.changeAdd},null,8,["class","isShow","onUpdateAdd"])])),_:1},8,["class"])}],["__scopeId","data-v-99165041"]]);export{f as T};