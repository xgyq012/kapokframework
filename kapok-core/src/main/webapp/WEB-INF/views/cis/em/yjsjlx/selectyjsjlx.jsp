<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>选择用户</title>
	
    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/page.css">
</head>
<body>
     <div title="选择应急事件类型" style="padding:5px;height: 100%">
        <div id="list" style="overflow: auto; height: 355px;">
			<table id="listGrid" class="easyui-treegrid" style="width:100%;height:100%"></table>
		</div>
		<div id="toolbar" class="dialog-button">
			<a id="confirm" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="window.actions.confirm($('#listGrid').treegrid('getSelected'));" ><i class="fa fa-check"></i>确定</a>
	   		<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-times"></i>取消</a>		
	    </div>
     </div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >
$(document).ready(function(){
	tableInfo.init();
});
//列表加载
var tableInfo =  {
	//初始化列表
		//初始化列表
		init : function (){
			$("#listGrid").treegrid({
				rownumbers : true,
				idField: 'yjsjlxId',
				treeField: 'leixingName',
				url : "${ctx}/yjsjlx/search",
				columns : [ [ {
					field : "yjsjlxId",
					hidden:true
				}, {
					frozen:"true",
					field : "leixingName",
					title : "类型名称",
					width : 250
				},{
					field:"leixingPs",
					title:"类型描述",
					width:300
				}]],

				onSelect : function(rowIndex, rowData) {
				}
		});
	}
};
</script>
</body>
</html>