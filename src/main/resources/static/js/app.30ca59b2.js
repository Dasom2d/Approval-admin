(function(t){function e(e){for(var n,i,o=e[0],l=e[1],c=e[2],u=0,p=[];u<o.length;u++)i=o[u],Object.prototype.hasOwnProperty.call(r,i)&&r[i]&&p.push(r[i][0]),r[i]=0;for(n in l)Object.prototype.hasOwnProperty.call(l,n)&&(t[n]=l[n]);d&&d(e);while(p.length)p.shift()();return s.push.apply(s,c||[]),a()}function a(){for(var t,e=0;e<s.length;e++){for(var a=s[e],n=!0,o=1;o<a.length;o++){var l=a[o];0!==r[l]&&(n=!1)}n&&(s.splice(e--,1),t=i(i.s=a[0]))}return t}var n={},r={app:0},s=[];function i(e){if(n[e])return n[e].exports;var a=n[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,i),a.l=!0,a.exports}i.m=t,i.c=n,i.d=function(t,e,a){i.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},i.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},i.t=function(t,e){if(1&e&&(t=i(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(i.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var n in t)i.d(a,n,function(e){return t[e]}.bind(null,n));return a},i.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return i.d(e,"a",e),e},i.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},i.p="/";var o=window["webpackJsonp"]=window["webpackJsonp"]||[],l=o.push.bind(o);o.push=e,o=o.slice();for(var c=0;c<o.length;c++)e(o[c]);var d=l;s.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"034f":function(t,e,a){"use strict";a("5a56")},"09c9":function(t,e,a){"use strict";a("1db7")},"0c3a":function(t,e,a){},"1db7":function(t,e,a){},"2c86":function(t,e,a){"use strict";a("a928")},"37b9":function(t,e,a){},"477b":function(t,e,a){"use strict";a("ffa6")},"56d7":function(t,e,a){"use strict";a.r(e);a("e260"),a("e6cf"),a("cca6"),a("a79d");var n=a("2b0e"),r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"app"}},[a("div",[a("Header"),a("router-view")],1)])},s=[],i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"header"}},[a("div",{staticClass:"section_header",attrs:{id:"section_header"}},[a("div",{staticClass:"left"},[t._v("Lion Company")]),a("div",{staticClass:"right"},[t._v(t._s(this.$store.state.loginMember.member.name)),a("span",{staticClass:"nim"},[t._v(" 님")])])])])},o=[],l={name:"Header",mounted:function(){},data:function(){return{}}},c=l,d=(a("477b"),a("0c7c")),u=Object(d["a"])(c,i,o,!1,null,null,null),p=u.exports,m={name:"App",components:{Header:p},computed:{}},v=m,f=(a("034f"),Object(d["a"])(v,r,s,!1,null,null,null)),h=f.exports,_=a("8c4f"),b=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"login"},t._l(t.memberList,(function(t,e){return a("login-box",{key:e,attrs:{account:t}})})),1)},g=[],M=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"loginbox"},[a("div",[a("div",{staticStyle:{"padding-top":"100px"},on:{click:function(e){return t.login(t.$attrs.account)}}},[a("a",{staticStyle:{cursor:"pointer"}},[a("span",{staticStyle:{"font-size":"20px","font-wigth":"bold"}},[t._v(" "+t._s(this.$attrs.account.name))]),a("span",{staticStyle:{"font-size":"15px"}},[t._v(" "+t._s(this.$attrs.account.gradeName))])])])])])},I=[],C={name:"LoginBox",components:{},methods:{login:function(t){this.$store.commit("loginMember/LOGIN",t),this.$router.push({path:"/main"})}}},y=C,E=(a("b697"),Object(d["a"])(y,M,I,!1,null,null,null)),w=E.exports,S={name:"App",components:{LoginBox:w},mounted:function(){this.$store.commit("loginMember/LOGOUT")},data:function(){return{memberList:[{memberId:1,name:"라이언",gradeId:1,gradeName:"회장"},{memberId:2,name:"튜브",gradeId:2,gradeName:"과장"},{memberId:3,name:"네오",gradeId:3,gradeName:"대리"},{memberId:4,name:"무지",gradeId:4,gradeName:"사원"}]}}},A=S,P=(a("09c9"),Object(d["a"])(A,b,g,!1,null,null,null)),x=P.exports,L=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"container"}},[a("div",{staticStyle:{"padding-top":"20px"}},[a("span",{staticClass:"name"},[t._v(t._s(this.$store.state.loginMember.member.name))]),a("span",{staticClass:"nim"},[t._v("님의 문서")]),a("span",{staticStyle:{"padding-left":"10px"}},[a("button",{on:{click:t.logout}},[t._v("로그아웃")])])]),a("div",{staticClass:"contents_btm",staticStyle:{"padding-top":"50px"}},[a("div",{staticClass:"snb"},[a("SubNav")],1),a("div",{attrs:{id:"content"}},[a("Contents")],1)])])},O=[],T=a("53ca"),$=(a("b64b"),function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"snb_nav"},[a("div",{staticClass:"nav_lst frst"},[a("h3",{directives:[{name:"show",rawName:"v-show",value:1!=t.loginedMemberInfo.memberId,expression:"loginedMemberInfo.memberId != 1"}]},[a("div",{staticClass:"big_menu"},[a("router-link",{attrs:{to:"/register"}},[t._v("기안 신청하기")])],1)])]),a("div",{staticClass:"nav_lst"},[a("h3",[a("a",{staticClass:"big_menu",on:{click:function(e){return t.goRecentList()}}},[t._v(t._s(t.APPROVAL_TYPE.RECENT))])]),a("h3",[a("a",{staticClass:"big_menu",on:{click:function(e){return t.setSearchParam("WAIT",null,t.loginedMemberInfo.memberId,t.loginedMemberInfo.memberId,"WAIT")}}},[t._v(t._s(t.APPROVAL_TYPE.WAIT))])]),a("ul",[a("li",[a("a",{staticClass:"small_menu",on:{click:function(e){return t.setSearchParam("WAIT",null,t.loginedMemberInfo.memberId,null,"WAIT_APPROVE")}}},[t._v(t._s(t.APPROVAL_TYPE.WAIT_REQUEST))])]),a("li",[a("a",{staticClass:"small_menu",on:{click:function(e){return t.setSearchParam("WAIT",null,null,t.loginedMemberInfo.memberId,"WAIT_REQUEST")}}},[t._v(t._s(t.APPROVAL_TYPE.WAIT_APPROVE))])])]),a("h3",[a("a",{staticClass:"big_menu",on:{click:function(e){return t.setSearchParam("COMPLETE",null,t.loginedMemberInfo.memberId,t.loginedMemberInfo.memberId,"COMPLETE")}}},[t._v(t._s(t.APPROVAL_TYPE.COMPLETE))])]),a("ul",[a("li",[a("a",{staticClass:"small_menu",on:{click:function(e){return t.setSearchParam("COMPLETE","APPROVE",t.loginedMemberInfo.memberId,t.loginedMemberInfo.memberId,"COMPLETE_APPROVE")}}},[t._v(t._s(t.APPROVAL_TYPE.COMPLETE_APPROVE))])]),a("li",[a("a",{staticClass:"small_menu",on:{click:function(e){return t.setSearchParam("COMPLETE","RETURN",t.loginedMemberInfo.memberId,t.loginedMemberInfo.memberId,"COMPLETE_RETURN")}}},[t._v(t._s(t.APPROVAL_TYPE.COMPLETE_RETURN))])])])])])}),R=[];function N(){var t="/Users/dasom/study/approval-admin/frontend/src/js/eventBus.js",e="b82949058cf448201c37a92c1f3c9ca1fcde6468",a=new Function("return this")(),n="__coverage__",r={path:"/Users/dasom/study/approval-admin/frontend/src/js/eventBus.js",statementMap:{0:{start:{line:3,column:17},end:{line:3,column:26}}},fnMap:{},branchMap:{},s:{0:0},f:{},b:{},_coverageSchema:"1a1c01bbd47fc00a2c39e90264f33305004495a9",hash:"b82949058cf448201c37a92c1f3c9ca1fcde6468"},s=a[n]||(a[n]={});s[t]&&s[t].hash===e||(s[t]=r);var i=s[t];return N=function(){return i},i}N();var j=(N().s[0]++,new n["a"]),q=j,k={name:"SubNav",methods:{goRecentList:function(){q.$emit("showDefault",!0)},setSearchParam:function(t,e,a,n,r){var s=this.APPROVAL_TYPE[r],i={requestStatusCode:t,approvalStatusCode:e,requestMemberId:a,approveMemberId:n};q.$emit("deliverSearchParam",i,s),q.$emit("changeContentState",!1)}},data:function(){return{loginedMemberInfo:this.$store.state.loginMember.member,approvalList:[],APPROVAL_TYPE:{RECENT:"최근 결재 문서",WAIT:"진행중인 결재 문서",WAIT_APPROVE:"결재할 문서",WAIT_REQUEST:"상신/결재한 문서",COMPLETE:"완료 문서",COMPLETE_APPROVE:"승인 문서",COMPLETE_RETURN:"반려 문서"}}}},U=k,D=Object(d["a"])(U,$,R,!1,null,null,null),V=D.exports,W=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[t.loading?n("div",{staticClass:"loader"},[n("img",{attrs:{src:a("cf1c")}})]):t._e(),n("div",[n("Default",{directives:[{name:"show",rawName:"v-show",value:t.isDefaultShow,expression:"isDefaultShow"}]}),n("List",{directives:[{name:"show",rawName:"v-show",value:!t.isDefaultShow,expression:"!isDefaultShow"}]})],1)])},G=[],Y=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"default"}},[t.loading?n("div",{staticClass:"loader"},[n("img",{attrs:{src:a("cf1c")}})]):t._e(),n("table",{staticClass:"mainContentTable"},[t._m(0),n("tbody",[n("tr",[n("td",{staticClass:"mainContentTableTd1"},[t._m(1),n("div",{staticClass:"section_main v2"},[t.approvalList.length>0?n("ul",{staticClass:"lst_type"},t._l(t.approvalList,(function(e,a){return n("li",{key:a},[n("em",{staticClass:"situ"},[t._v(t._s(e.approvalType))]),n("p",{staticClass:"tit"},[n("a",[n("router-link",{attrs:{to:"view/"+e.approvalId}},[t._v(" "+t._s(e.title))])],1)]),n("span",{staticClass:"rgt",staticStyle:{width:"300px","margin-right":"5px"}},[n("a",[t._v(t._s(e.requestMemberName))]),n("span",{staticClass:"date"},[t._v(t._s(e.registerDate))])])])})),0):t._e(),0==t.approvalList.length?n("ul",{staticClass:"lst_type"},[n("li",[t._v(" 문서가 없습니다. ")])]):t._e()])])])])])])},F=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("colgroup",[a("col",{attrs:{width:"*"}}),a("col",{attrs:{width:"248px"}}),a("col",{attrs:{width:"248px"}}),a("col",{attrs:{width:"248px"}})])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"main_content"}},[a("div",{staticClass:"section_tit first main"},[a("h3",[t._v("최근 결재문서")])])])}],Q=(a("159b"),a("bc3a")),B=a.n(Q),H={name:"Default",mounted:function(){var t={requestMemberId:this.loginedMemberInfo.memberId,approveMemberId:this.loginedMemberInfo.memberId};this.getRecentApprovalList(t)},methods:{getRecentApprovalList:function(t){var e=this;q.$emit("changeLoading",!0),B.a.get("/api/approval/getApprovalList",{params:t}).then((function(t){e.loading=!1,0===t.data.code&&(t.data.body.forEach((function(t){"COMPLETE"===t.requestStatusCode?t.approvalType="완료문서":t.approvalType="대기문서"})),e.approvalList=t.data.body,q.$emit("changeLoading",!1))})).catch((function(t){alert("조회에 실패하였습니다."),console.log(t),q.$emit("changeLoading",!1)}))}},data:function(){return{loading:!1,loginedMemberInfo:this.$store.state.loginMember.member,approvalList:[]}}},z=H,J=Object(d["a"])(z,Y,F,!1,null,null,null),K=J.exports,X=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"list"}},[a("div",{attrs:{id:"content"}},[a("div",{staticClass:"section_tit frst"},[a("h3",{staticStyle:{"padding-bottom":"15px"}},[t._v(t._s(t.typeName)),a("span",{staticClass:"tit_dsc"},[t._v(" 총 "+t._s(t.approvalList.length)+" 건")])]),a("table",{staticClass:"tb_lst"},[t._m(0),t._m(1),t.approvalList.length>0?a("tbody",t._l(t.approvalList,(function(e,n){return a("tr",{key:n,staticClass:"noline context",staticStyle:{"background-color":"rgb(255, 255, 255)"}},[a("td",[t._v(t._s(e.approvalId))]),a("td",[a("a",[a("router-link",{attrs:{to:"view/"+e.approvalId}},[t._v(" "+t._s(e.title))])],1)]),a("td",[t._v(t._s(e.requestMemberName))]),a("td",[t._v(t._s(e.approveMemberName))]),a("td",[t._v(t._s(e.requestApprovalType))]),a("td",[t._v(t._s(e.registerDate))]),a("td",[t._v(t._s(e.approveDate))])])})),0):t._e(),0==t.approvalList.length?a("tbody",[t._m(2)]):t._e()])])])])},Z=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("colgroup",[a("col",{staticStyle:{width:"67px"}}),a("col"),a("col",{staticStyle:{width:"93px"}}),a("col",{staticStyle:{width:"65px"}}),a("col",{staticStyle:{width:"65px"}}),a("col",{staticStyle:{width:"107px"}}),a("col",{staticStyle:{width:"107px"}})])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("thead",[a("tr",[a("th",{staticClass:"bg_lft",attrs:{scope:"col"}},[t._v("문서번호")]),a("th",{attrs:{scope:"col"}},[t._v("제목")]),a("th",{attrs:{scope:"col"}},[t._v("기안자")]),a("th",{attrs:{scope:"col"}},[t._v("승인자")]),a("th",{attrs:{scope:"col"}},[t._v("승인상태")]),a("th",{attrs:{scope:"col"}},[t._v("기안일")]),a("th",{staticClass:"bg_rgt",attrs:{scope:"col"}},[t._v("완료일")])])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("tr",[a("td",{attrs:{colspan:"7"}},[t._v("문서가 없습니다.")])])}],tt={REQUEST_WAIT:"요청중",APPROVE_COMPLETE:"승인 완료",RETURN_COMPLETE:"반려 완료"},et={name:"List",created:function(){var t=this;q.$on("deliverSearchParam",(function(e,a){t.typeName=a,t.searchParam=e,t.getApprovalList()}))},methods:{getApprovalList:function(){var t=this;q.$emit("changeLoading",!0),B.a.get("/api/approval/getApprovalList",{params:this.searchParam}).then((function(e){0===e.data.code&&(q.$emit("changeLoading",!1),e.data.body.forEach((function(t){t.requestApprovalType=tt[t.approvalStatusCode+"_"+t.requestStatusCode]})),t.approvalList=e.data.body)})).catch((function(t){alert("조회에 실패하였습니다."),console.log(t),q.$emit("changeLoading",!1)}))}},data:function(){return{searchParam:{},approvalList:[],typeName:""}}},at=et,nt=Object(d["a"])(at,X,Z,!1,null,null,null),rt=nt.exports,st={name:"Contents",components:{Default:K,List:rt},created:function(){var t=this;q.$on("changeContentState",(function(e){t.isDefaultShow=e})),q.$on("changeLoading",(function(e){t.loading=e})),q.$on("showDefault",(function(e){t.isDefaultShow=e}))},methods:{},data:function(){return{loading:!1,state:!1,isDefaultShow:!0}}},it=st,ot=(a("2c86"),Object(d["a"])(it,W,G,!1,null,null,null)),lt=ot.exports,ct={name:"Main",components:{SubNav:V,Contents:lt},mounted:function(){this.isNull(this.loginedMemberInfo)&&(alert("로그인해주세요."),this.$router.push({path:"/"}))},methods:{isNull:function(t){return""==t||null==t||void 0==t||null!=t&&"object"==Object(T["a"])(t)&&!Object.keys(t).length},logout:function(){this.$store.commit("loginMember/LOGOUT"),this.$router.push({path:"/"})}},data:function(){return{loginedMemberInfo:this.$store.state.loginMember.member}}},dt=ct,ut=(a("bf0b"),Object(d["a"])(dt,L,O,!1,null,null,null)),pt=ut.exports,mt=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"content"},[t.loading?n("div",{staticClass:"loader"},[n("img",{attrs:{src:a("cf1c")}})]):t._e(),n("div",{staticClass:"section_title2"},[n("h2",{staticClass:"fl h_tx"},[t._v("기안신청서")]),n("p",{staticClass:"fr p_tx"},[t._v(t._s(t.approvalState))])]),n("div",[n("ModalView",{directives:[{name:"show",rawName:"v-show",value:t.showApproveModal,expression:"showApproveModal"}],on:{close:function(e){t.showApproveModal=!1}}},[n("SelectApproveMember",{attrs:{modal:t.showApproveModal},on:{approveMember:t.approveMember}})],1),n("div",{staticStyle:{"padding-top":"15px"}},["register"===t.type||"edit"===t.type?n("button",{on:{click:function(e){t.showApproveModal=!0}}},[t._v("승인자 선택")]):t._e()]),n("div",{staticClass:"group_lst",staticStyle:{"padding-top":"35px"},attrs:{id:"lineview_group_lst"}},[n("div",{attrs:{id:"apms_aprvLineArea"}},[n("div",{staticClass:"lst_drafter_wrap",attrs:{id:"apms_aprvLine_hori"}},[n("ul",{staticClass:"lst_drafter",staticStyle:{"margin-left":"0px"}},[n("li",{staticClass:"off dim",attrs:{id:"0"}},[t._m(0),n("div",{staticClass:"name"},[n("a",[t._v(t._s(t.requestMemberInfo.name))])]),n("div",{staticClass:"date"},[t._v(" "+t._s(t.registerDate)+" ")])]),n("li",{staticClass:"off dim",attrs:{id:"1"}},[t._m(1),n("div",{staticClass:"name"},[n("a",{staticClass:"test",attrs:{id:"apms_aprvrNm_TL10051",href:"#null",empcd:"TL10051",title:"오정운"}},[t._v(t._s(t.approveMemberInfo.name))])]),n("div",{staticClass:"date"},[t._v(" "+t._s(t.approveDate)+" ")])])])])])])],1),n("div",{staticStyle:{width:"100%","text-align":"center",height:"100%"}},[n("div",{staticClass:"tbl_type2",staticStyle:{"padding-top":"35px",width:"300px",height:"300px",margin:"0 auto"}},[n("table",[t._m(2),n("tbody",[n("tr",[t._m(3),n("td",[n("div",{staticClass:"td"},[n("input",{directives:[{name:"model",rawName:"v-model",value:t.title,expression:"title"}],staticClass:"ipt_txt",staticStyle:{width:"200px"},attrs:{type:"text",disabled:"view"===t.type,title:"제목 입력란",placeholder:""},domProps:{value:t.title},on:{input:function(e){e.target.composing||(t.title=e.target.value)}}})])])]),n("tr",[t._m(4),n("td",[n("div",{staticClass:"td"},[n("textarea",{directives:[{name:"model",rawName:"v-model",value:t.content,expression:"content"}],staticClass:"ipt_txt",staticStyle:{width:"100%",height:"56px"},attrs:{disabled:"view"===t.type,name:"내용 입력란",cols:"20",rows:"3"},domProps:{value:t.content},on:{input:function(e){e.target.composing||(t.content=e.target.value)}}})])])])])])])]),n("div",{staticStyle:{"padding-top":"10px"}},[n("button",{on:{click:function(e){return t.$router.push({path:"/main"})}}},[t._v("목록으로")]),t._v(" "),"register"===t.type?n("button",{on:{click:function(e){return t.registApproval()}}},[t._v("등록하기")]):t._e(),t._v(" "),t.isAvailEdit&&"edit"!=t.type?n("button",{on:{click:function(e){return t.goEdit()}}},[t._v("편집")]):t._e(),t._v(" "),t.isAvailEdit&&"edit"!=t.type?n("button",{on:{click:function(e){return t.deleteApproval()}}},[t._v("삭제")]):t._e(),t._v(" "),"edit"===t.type?n("button",{on:{click:function(e){return t.updateApproval()}}},[t._v("저장")]):t._e(),t._v(" "),t.isAvailUpdate?n("button",{on:{click:function(e){return t.processApproval("APPROVE")}}},[t._v("승인")]):t._e(),t._v(" "),t.isAvailUpdate?n("button",{on:{click:function(e){return t.processApproval("RETURN")}}},[t._v("반려")]):t._e()])])},vt=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"type"},[a("span",[t._v("일반결재")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"type"},[a("span",[t._v(" 일반결재")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("colgroup",[a("col",{staticStyle:{width:"130px"}}),a("col")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("th",{attrs:{scope:"col"}},[a("div",{staticClass:"th"},[t._v("제목")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("th",{attrs:{scope:"col"}},[a("div",{staticClass:"th"},[t._v("내용")])])}],ft=a("1da1"),ht=(a("96cf"),a("b0c0"),function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"content",staticStyle:{width:"380px",position:"relative",right:"28px",height:"400px"}},[t.loading?n("div",{staticClass:"loader"},[n("img",{attrs:{src:a("cf1c")}})]):t._e(),t._m(0),n("div",{staticStyle:{"padding-top":"20px"}},[n("table",{staticClass:"tb_lst",staticStyle:{width:"438px"}},[t._m(1),n("tbody",t._l(t.memberList,(function(e,a){return n("tr",{key:a},[n("td",[t._v(t._s(e.memberId))]),n("td",[n("a",{staticStyle:{cursor:"pointer"},on:{click:function(a){return t.deliverMemberInfo(e)}}},[t._v(t._s(e.name))])]),n("td",[t._v(t._s(e.gradeName))])])})),0)])])])}),_t=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"section_title2",staticStyle:{width:"438px"}},[a("h2",{staticClass:"fl h_tx"},[t._v("승인자 선택")]),a("p",{staticClass:"fr p_tx"})])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("thead",[a("th",[t._v("사번")]),a("th",[t._v("이름")]),a("th",[t._v("직급")])])}],bt={name:"SelectApproveMember",props:["modal"],watch:{modal:function(){this.modal&&this.getMemberList()}},methods:{getMemberList:function(){var t=this;this.loading=!0,B.a.get("/api/member/getMemberList?memberId="+this.$store.state.loginMember.member.memberId).then((function(e){200===e.status&&(t.loading=!1,t.memberList=e.data)})).catch((function(t){alert("조회에 실패하였습니다."),console.log(t)}))},deliverMemberInfo:function(t){this.approveMember=t,this.$emit("approveMember",t,!1)}},data:function(){return{loading:!1,approveMember:{},memberList:[]}}},gt=bt,Mt=Object(d["a"])(gt,ht,_t,!1,null,null,null),It=Mt.exports,Ct=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"modal"},[a("div",{staticClass:"overlay",on:{click:function(e){return t.$emit("close")}}}),a("div",{staticClass:"modal-card"},[t._t("default"),a("div",{staticStyle:{"padding-top":"20px"}},[a("button",{on:{click:function(e){return t.$emit("close")}}},[t._v("닫기")])])],2)])},yt=[],Et={},wt=Et,St=(a("acbf"),Object(d["a"])(wt,Ct,yt,!1,null,null,null)),At=St.exports,Pt={REQUEST:"요청중",APPROVE:"승인문서",RETURN:"반려문서"},xt={name:"Detail",components:{SelectApproveMember:It,ModalView:At},mounted:function(){var t=this.$store.state.loginMember;if(this.type=this.$route.name,this.isVaildMember())if("view"===this.type||"update"===this.type){var e=this.$route.params.id;this.getApproval(e)}else"register"===this.type&&(this.requestMemberInfo=t.member)},methods:{rediect:function(t,e){var a;alert(e),"login"===t?a="/":"main"===t&&(a="/main"),this.$router.push({path:a})},goEdit:function(){this.type="edit"},isNull:function(t){return""==t||null==t||void 0==t||null!=t&&"object"==Object(T["a"])(t)&&!Object.keys(t).length},getApproval:function(t){var e=this;return Object(ft["a"])(regeneratorRuntime.mark((function a(){var n,r;return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:return e.loading=!0,n={approvalId:t},a.next=4,B.a.get("/api/approval/getApproval",{params:n});case 4:r=a.sent,e.isAuthMember(r)&&e.setData(r.data.body);case 6:case"end":return a.stop()}}),a)})))()},isVaildMember:function(){var t=this.loginedMemberInfo.memberId;return this.isNull(t)?(this.rediect("login","로그인해주세요."),!1):"register"!==this.type||1!==t||(this.rediect("main","권한이 없습니다."),!1)},isAuthMember:function(t){var e=this.loginedMemberInfo.memberId;if(0!==t.data.code)return alert("조회에 실패하였습니다."),!1;this.loading=!1;var a=t.data.body;return this.isNull(a)?(this.rediect("main","존재하지않는 문서입니다."),!1):e==a.approveMemberId||e==a.requestMemberId||(this.rediect("main","권한이 없습니다."),!1)},setData:function(t){this.approvalId=t.approvalId,this.approveMemberInfo={memberId:t.approveMemberId,name:t.approveMemberName,gradeId:t.approveMemberGradeId},this.requestMemberInfo={memberId:t.requestMemberId,name:t.requestMemberName,gradeId:t.requestMemberGradeId},this.requestStatusCode=t.requestStatusCode,this.approvalStatusCode=t.approvalStatusCode,this.approvalState=Pt[t.approvalStatusCode],this.title=t.title,this.content=t.content,this.registerDate=t.registerDate,this.approveDate=t.approveDate,this.isAvailEdit="view"===this.type&&"WAIT"===this.requestStatusCode&&this.loginedMemberInfo.memberId===t.requestMemberId,this.isAvailUpdate="view"===this.type&&"WAIT"===this.requestStatusCode&&this.loginedMemberInfo.memberId===t.approveMemberId},approveMember:function(t,e){this.approveMemberInfo=t,this.showApproveModal=e},setParams:function(){var t={title:this.title,content:this.content,approveMemberId:this.approveMemberInfo.memberId,requestMemberId:this.requestMemberInfo.memberId,registerMemberId:this.loginedMemberInfo.memberId,approveMemberGradeId:this.approveMemberInfo.gradeId,requestMemberGradeId:this.requestMemberInfo.gradeId};return t},validate:function(){if(this.isAvailEdit&&"WAIT"===!this.requestStatusCode)throw"요청상태의 문서만 수정가능합니다.";if(this.isNull(this.title))throw"제목을 입력해주세요.";if(this.title.length>45)throw"제목의 길이는 45자를 넘을 수 없습니다.";if(this.isNull(this.content))throw"내용을 입력해주세요.";if(this.isNull(this.approveMemberInfo))throw"승인자가 지정되지 않았습니다.";if(this.content.length>3e3)throw"내용은 3000자를 넘을 수 없습니다.";if(this.approveMemberInfo.gradeId==this.requestMemberInfo.gradeId)throw"승인자는 요청자와 같은 직급일 수 없습니다.";if(this.approveMemberInfo.gradeId>this.requestMemberInfo.gradeId)throw"승인자는 요청자보다 직급이 높아야합니다.";return!0},registApproval:function(){try{if(this.validate()){var t=this.setParams();B.a.post("/api/approval",t).then((function(t){0===t.data.code&&(alert("기안을 상신하였습니다."),window.location.href="/view/"+t.data.approvalId)})).catch((function(t){console.log(t.response.data)}))}}catch(e){alert(e)}},goUpdate:function(t,e,a){var n=this;B.a.put(t,e).then((function(t){0===t.data.code&&alert(a+"하였습니다."),window.location.href="/view/"+n.approvalId})).catch((function(t){n.isNull(t.response.data)?alert(t.response.data.error):alert(t.response.data.message)}))},updateApproval:function(){try{if(this.validate()){var t=this.setParams();t.approvalId=this.approvalId,t.requestStatusCode=this.requestStatusCode,t.approvalStatusCode=this.approvalStatusCode,this.goUpdate("/api/approval/update",t,"수정")}}catch(e){alert(e)}},processApproval:function(t){try{if(this.validate()){var e,a={approvalId:this.approvalId,requestStatusCode:this.requestStatusCode,approvalStatusCode:t};e="APPROVE"===t?"승인":"반려",this.goUpdate("/api/approval/process",a,e)}}catch(n){alert(n)}},deleteApproval:function(){if(confirm("삭제하시겠습니까?"))if(this.isAvailEdit){var t={approvalId:this.approvalId,approvalStatusCode:this.approvalStatusCode,requestStatusCode:this.requestStatusCode};B.a.delete("/api/approval",{data:t}).then((function(t){0===t.data.code&&alert("기안을 삭제하였습니다."),window.location.href="/main"})).catch((function(t){alert(t.response.data.message)}))}else alert("요청상태의 문서만 수정 가능합니다.")}},data:function(){return{loading:!1,loginedMemberInfo:this.$store.state.loginMember.member,isAvailEdit:!1,isAvailUpdate:!1,type:"register",approveMemberInfo:{},requestMemberInfo:{},showApproveModal:!1,approvalId:"",requestStatusCode:"",approvalState:"",approvalStatusCode:"",title:"",content:"",registerDate:"",approveDate:""}}},Lt=xt,Ot=(a("fd9e"),Object(d["a"])(Lt,mt,vt,!1,null,null,null)),Tt=Ot.exports,$t=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[t._v(" 에러페이지~! ")])},Rt=[],Nt={name:"Error",mounted:function(){},data:function(){return{}}},jt=Nt,qt=Object(d["a"])(jt,$t,Rt,!1,null,null,null),kt=qt.exports;function Ut(){var t="/Users/dasom/study/approval-admin/frontend/src/router/index.js",e="211800e3e496574697002370b2a5cd6a90528667",a=new Function("return this")(),n="__coverage__",r={path:"/Users/dasom/study/approval-admin/frontend/src/router/index.js",statementMap:{0:{start:{line:9,column:0},end:{line:9,column:15}}},fnMap:{},branchMap:{},s:{0:0},f:{},b:{},_coverageSchema:"1a1c01bbd47fc00a2c39e90264f33305004495a9",hash:"211800e3e496574697002370b2a5cd6a90528667"},s=a[n]||(a[n]={});s[t]&&s[t].hash===e||(s[t]=r);var i=s[t];return Ut=function(){return i},i}Ut(),Ut().s[0]++,n["a"].use(_["a"]);var Dt=new _["a"]({mode:"history",routes:[{path:"/",component:x},{path:"/main",component:pt},{name:"register",path:"/register",component:Tt,props:!0},{name:"view",path:"/view/:id",component:Tt},{name:"error",path:"/error",component:kt}]}),Vt=a("2f62"),Wt=a("0e44"),Gt=a("5530");function Yt(){var t="/Users/dasom/study/approval-admin/frontend/src/store/loginMemberInfo.js",e="311a10526c755b887029f168bbca693f5d7fa2ca",a=new Function("return this")(),n="__coverage__",r={path:"/Users/dasom/study/approval-admin/frontend/src/store/loginMemberInfo.js",statementMap:{0:{start:{line:1,column:21},end:{line:3,column:1}},1:{start:{line:5,column:20},end:{line:12,column:3}},2:{start:{line:7,column:6},end:{line:7,column:27}},3:{start:{line:10,column:6},end:{line:10,column:24}}},fnMap:{0:{name:"(anonymous_0)",decl:{start:{line:6,column:4},end:{line:6,column:5}},loc:{start:{line:6,column:24},end:{line:8,column:5}},line:6},1:{name:"(anonymous_1)",decl:{start:{line:9,column:4},end:{line:9,column:5}},loc:{start:{line:9,column:18},end:{line:11,column:5}},line:9}},branchMap:{},s:{0:0,1:0,2:0,3:0},f:{0:0,1:0},b:{},_coverageSchema:"1a1c01bbd47fc00a2c39e90264f33305004495a9",hash:"311a10526c755b887029f168bbca693f5d7fa2ca"},s=a[n]||(a[n]={});s[t]&&s[t].hash===e||(s[t]=r);var i=s[t];return Yt=function(){return i},i}Yt();var Ft=(Yt().s[0]++,{member:{}}),Qt=(Yt().s[1]++,{LOGIN:function(t,e){Yt().f[0]++,Yt().s[2]++,t.member=e},LOGOUT:function(t){Yt().f[1]++,Yt().s[3]++,t.member={}}}),Bt={strict:!1,namespaced:!0,state:Object(Gt["a"])({},Ft),mutations:Qt};function Ht(){var t="/Users/dasom/study/approval-admin/frontend/src/store/index.js",e="d7c3f6c88ccff8ca4f3514447011c3e031fe1776",a=new Function("return this")(),n="__coverage__",r={path:"/Users/dasom/study/approval-admin/frontend/src/store/index.js",statementMap:{0:{start:{line:8,column:16},end:{line:10,column:1}},1:{start:{line:12,column:16},end:{line:18,column:1}},2:{start:{line:20,column:0},end:{line:20,column:14}}},fnMap:{},branchMap:{},s:{0:0,1:0,2:0},f:{},b:{},_coverageSchema:"1a1c01bbd47fc00a2c39e90264f33305004495a9",hash:"d7c3f6c88ccff8ca4f3514447011c3e031fe1776"},s=a[n]||(a[n]={});s[t]&&s[t].hash===e||(s[t]=r);var i=s[t];return Ht=function(){return i},i}Ht();var zt=(Ht().s[0]++,{loginMember:Bt}),Jt=(Ht().s[1]++,[Object(Wt["a"])({paths:["loginMember"]})]);Ht().s[2]++,n["a"].use(Vt["a"]);var Kt=new Vt["a"].Store({modules:zt,plugins:Jt});function Xt(){var t="/Users/dasom/study/approval-admin/frontend/src/main.js",e="f0b070513258bd775000024191f76f649af74785",a=new Function("return this")(),n="__coverage__",r={path:"/Users/dasom/study/approval-admin/frontend/src/main.js",statementMap:{0:{start:{line:7,column:0},end:{line:7,column:32}},1:{start:{line:8,column:0},end:{line:8,column:28}},2:{start:{line:10,column:0},end:{line:14,column:17}},3:{start:{line:13,column:15},end:{line:13,column:21}}},fnMap:{0:{name:"(anonymous_0)",decl:{start:{line:13,column:10},end:{line:13,column:11}},loc:{start:{line:13,column:15},end:{line:13,column:21}},line:13}},branchMap:{},s:{0:0,1:0,2:0,3:0},f:{0:0},b:{},_coverageSchema:"1a1c01bbd47fc00a2c39e90264f33305004495a9",hash:"f0b070513258bd775000024191f76f649af74785"},s=a[n]||(a[n]={});s[t]&&s[t].hash===e||(s[t]=r);var i=s[t];return Xt=function(){return i},i}Xt(),Xt().s[0]++,n["a"].config.productionTip=!1,Xt().s[1]++,n["a"].prototype.$axios=B.a,Xt().s[2]++,new n["a"]({router:Dt,store:Kt,render:function(t){return Xt().f[0]++,Xt().s[3]++,t(h)}}).$mount("#app")},"5a56":function(t,e,a){},"5dd9":function(t,e,a){},a928:function(t,e,a){},acbf:function(t,e,a){"use strict";a("0c3a")},b697:function(t,e,a){"use strict";a("5dd9")},bf0b:function(t,e,a){"use strict";a("e8da")},cf1c:function(t,e,a){t.exports=a.p+"img/loading.525834b9.gif"},e8da:function(t,e,a){},fd9e:function(t,e,a){"use strict";a("37b9")},ffa6:function(t,e,a){}});
//# sourceMappingURL=app.30ca59b2.js.map