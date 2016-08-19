<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>EM工作台</title>
	
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
	#ronqi{
	width:100%;
	height:100%;
	padding: 30px;
	}
	#ronqi #left{
	background:url(${ctx}/resources/images/base/emergency.png) no-repeat;
	width: 30%;
	height: 100%;
	float: left;
	}
	#ronqi #right{
	width: 70%;
	height: 100%;
	float: right;
	}
	</style>
</head>

<body>
<div class="g-layout">
	<div id="ronqi">
		<div id="left">
		<form id="queryForm" method="post">
			<input id="shijianStatus_NEQ" name="q_shijianStatus_NEQ" value="0" type="hidden">
		</form>
		</div>
		<div id="right">
			<h3 style="margin: 13px 0;">最新应急事件一览表</h3>
			<table id="listGrid" style="height:277px;"></table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript">
// 	console.log('home', main.getResourceId('BM'));
$(function(){
	tableInfo.init();
});


var thisUiConfig = {
	ctx : "${ctx}",
	url : "yingjishijian",
	tableList : "#listGrid",
	searchUrl : "search",
	queryForm: "#queryForm",
};

//列表加载
var tableInfo =  {
	
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : true,
			queryParams:  getFormData(thisUiConfig.queryForm),
			url : thisUiConfig.ctx +"/"+thisUiConfig.url +"/search",
			columns : [ [ {
				field : "yingjiShijianId",
				hidden:true
			}, {
				field : "name",
				title : "应急事件名称",
				halign:'center',
				align:'left',
				width : 200
			},{
				field:"address",
				halign:'center',
				align:'left',
				title:"事发地",
				width:200
			},{
				field:"shijianPs",
				title:"事件描述",
				halign:'center',
				align:'left',
				width:355
			}]],

			onSelect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onUnselect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onDblClickRow: function(rowIndex, rowData){
				title = '<i class="fa fa-tasks"></i>&nbsp;&nbsp;应急事件管理';
				parent.addTab({
					id: 'yjsjgl_01',
					title: title,
					url: "${ctx}/yingjishijian/list"
				});
			},
			
			//列表数据加载成功后调用
			onLoadSuccess : function(data) {

			}
		});

	}
};
</script>
</body>
</html>