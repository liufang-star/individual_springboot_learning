webpackJsonp([8],{"7Fmj":function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=i("BO1k"),n=i.n(a),s=i("msXN"),l=i("gyMJ"),o={name:"list-song-page",mixins:[s.a],data:function(){return{registerForm:{singerName:"",songName:""},tableData:[],tempDate:[],multipleSelection:[],centerDialogVisible:!1,editVisible:!1,delVisible:!1,select_word:"",idx:-1}},watch:{select_word:function(){if(""===this.select_word)this.tableData=this.tempDate;else{this.tableData=[];var e=!0,t=!1,i=void 0;try{for(var a,s=n()(this.tempDate);!(e=(a=s.next()).done);e=!0){var l=a.value;l.name.includes(this.select_word)&&this.tableData.push(l)}}catch(e){t=!0,i=e}finally{try{!e&&s.return&&s.return()}finally{if(t)throw i}}}}},created:function(){this.getData()},methods:{getData:function(){var e=this;this.tableData=[],this.tempDate=[],l.a.getListSongOfSongId(this.$route.query.id).then(function(t){console.log(t);var i=!0,a=!1,s=void 0;try{for(var l,o=n()(t);!(i=(l=o.next()).done);i=!0){var r=l.value;e.getSong(r.songId)}}catch(e){a=!0,s=e}finally{try{!i&&o.return&&o.return()}finally{if(a)throw s}}}).catch(function(e){console.log(e)})},getSong:function(e){var t=this;l.a.getSongOfId(e).then(function(e){t.tableData.push(e[0]),t.tempDate.push(e[0])}).catch(function(e){console.log(e)})},getSongId:function(){var e=this,t=this.registerForm.singerName+"-"+this.registerForm.songName;l.a.getSongOfSingerName(t).then(function(t){e.addSong(t[0].id)})},addSong:function(e){var t=this,i=new URLSearchParams;i.append("songId",e),i.append("songListId",this.$route.query.id),l.a.setListSong(i).then(function(e){1===e.code?(t.getData(),t.notify("添加成功","success")):t.notify("添加失败","error")}).catch(function(e){console.log(e)}),this.centerDialogVisible=!1},deleteRow:function(){var e=this;l.a.deleteListSong(this.idx).then(function(t){t?(e.getData(),e.notify("删除成功","success")):e.notify("删除失败","error")}).catch(function(e){console.log(e)}),this.delVisible=!1}}},r={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"table"},[i("div",{staticClass:"crumbs"},[i("el-breadcrumb",{attrs:{separator:"/"}},[i("el-breadcrumb-item",[i("i",{staticClass:"el-icon-tickets"}),e._v(" 歌单歌曲信息\n      ")])],1)],1),e._v(" "),i("div",{staticClass:"container"},[i("div",{staticClass:"handle-box"},[i("el-button",{staticClass:"handle-del mr10",attrs:{type:"primary",size:"mini"},on:{click:e.delAll}},[e._v("批量删除")]),e._v(" "),i("el-input",{staticClass:"handle-input mr10",attrs:{size:"mini",placeholder:"筛选关键词"},model:{value:e.select_word,callback:function(t){e.select_word=t},expression:"select_word"}}),e._v(" "),i("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){e.centerDialogVisible=!0}}},[e._v("添加歌曲")])],1),e._v(" "),i("el-table",{ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",size:"mini"},on:{"selection-change":e.handleSelectionChange}},[i("el-table-column",{attrs:{type:"selection",width:"50"}}),e._v(" "),i("el-table-column",{attrs:{prop:"name",label:"歌手-歌曲"}}),e._v(" "),i("el-table-column",{attrs:{label:"操作",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(i){return e.handleDelete(t.row.id)}}},[e._v("删除")])]}}])})],1)],1),e._v(" "),i("el-dialog",{attrs:{title:"添加歌曲",visible:e.centerDialogVisible,width:"400px",center:""},on:{"update:visible":function(t){e.centerDialogVisible=t}}},[i("el-form",{ref:"registerForm",staticClass:"demo-ruleForm",attrs:{model:e.registerForm,"status-icon":"","label-width":"80px"}},[i("el-form-item",{attrs:{prop:"singerName",label:"歌手名字",size:"mini"}},[i("el-input",{attrs:{placeholder:"歌手名字"},model:{value:e.registerForm.singerName,callback:function(t){e.$set(e.registerForm,"singerName",t)},expression:"registerForm.singerName"}})],1),e._v(" "),i("el-form-item",{attrs:{prop:"songName",label:"歌曲名字",size:"mini"}},[i("el-input",{attrs:{placeholder:"歌曲名字"},model:{value:e.registerForm.songName,callback:function(t){e.$set(e.registerForm,"songName",t)},expression:"registerForm.songName"}})],1)],1),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{size:"mini"},on:{click:function(t){e.centerDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),i("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.getSongId}},[e._v("确 定")])],1)],1),e._v(" "),i("el-dialog",{attrs:{title:"提示",visible:e.delVisible,width:"300px",center:""},on:{"update:visible":function(t){e.delVisible=t}}},[i("div",{staticClass:"del-dialog-cnt",attrs:{align:"center"}},[e._v("删除不可恢复，是否确定删除？")]),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{size:"mini"},on:{click:function(t){e.delVisible=!1}}},[e._v("取 消")]),e._v(" "),i("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.deleteRow}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]};var c=i("VU/8")(o,r,!1,function(e){i("Ii5R")},"data-v-450d525e",null);t.default=c.exports},Ii5R:function(e,t){}});
//# sourceMappingURL=8.20791f907bf5c9ccab28.js.map