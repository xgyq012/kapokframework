<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>SW工作台</title>
	
    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/page.css">
	
	<style type="text/css">
		#outlineBorder{
			width : 100%;
			height : 100%;
			padding: 30px;
		}
		#outlineBorder #left{
			background : url(${ctx}/resources/images/service.png) no-repeat;
			width: 40%;
			height: 100%;
			float: left;
		}
		#outlineBorder #right{
			margin : 40px 0 0 0 ;
			width: 60%;
			height: 100%;
			float: left;
			}
		h2{
			fone-size : 14px;
			color : #2b2b2b;
			margin : 0 0 30px 0;
		}
		table{
		 height : 92%;
		}
	
	</style>
</head>

<body>
<!-- 	<div style="padding:20px"> -->
<!-- 		<h1>SW热烈欢迎您！</h1> -->
<!-- 	</div> -->
<div class="g-layout">
<div id="outlineBorder">
	<div id="left"></div>
	<div id="right">
		<h2>待办事件</h2>
		<table id="listGrid"></table>
	</div>
</div>
	
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript">
// 	console.log('home', main.getResourceId('BM'));

/**
 * 脚本入口
 */
 $(function(){
	 datagridInit(); // 初始化数据表格	 
 });
 
//初始化数据表格
 function datagridInit() {
 	$("#listGrid").datagrid({
 		rownumbers : true,
 		singleSelect : true,
 		autoRowHeight : false,
 		border : true,
 		fitColumns : true,
 		queryParams : getQueryFormData(),
 		url : "${ctx}/eventType/getData",
 		columns : [[{
 			field : "enrollCode",
 			title : "事件编号",
 			align:'left',
 			halign:'center',
 			width : 120
 		},{
 			field : "enrollTitle",
 			title : "事件标题",
 			align:'left',
 			halign:'center',
 			width : 120
 		},{
 			field : "enrollStatus",
 			title : "事件状态",
 			align:'left',
 			halign:'center',
 			width : 120,
 			formatter : function(value, row, index){
 				if(value == 'draft'){
					return '草稿';
				}else if(value == 'appearIn'){
					return '已上报';
				}else if(value == 'assign'){
					return '已交办';
				}else if(value == 'signFor'){
					return '已签收';
				}else if(value == 'sendBack'){
					return '退回';
				}else if(value == 'transact'){
					return '已处理';
				}else if(value == 'estimate'){
					return '已评价';
				}
 			}
 		},{
 			field : "enrollTime",
 			title : "事发时间  ",
 			align:'left',
 			halign:'center',
 			width : 120
 		}
 		]],
 	});
 }
 
 function getQueryFormData(){
		var meshIds = window.main.currentUserMesh.meshChildrenIds ;
		var data = [];
		if(meshIds){
			data['q_unitsId_IN'] = meshIds;
		}
		return data;
	}
</script>
</body>
</html>