<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>服务团队队员管理</title>
<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css" type="text/css">
<!--[if IE 7]> 
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css"> 
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
<style type="text/css">
html,body{
   overflow-x:hidden;
   overflow-y:hidden;
   height: 100%;
}

.infoBtn{
  		text-align: left;
  	}
  	
</style>
</head>
<body>

<div id="mainTab" class="easyui-tabs" style="width:340px;height:2105px;padding-bottom: 110px;">
     <div title="你有新的消息" style="padding:5px;height: 100%">
         <div id="list" style="overflow: auto;height: 100%">
			<table id="listGrid" style="height:100%;"></table>
		</div>
     </div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >
var thisUiConfig = {
	ctx : "${ctx}",
	url : "notice",
	tableList : "#listGrid",
	mainTab : "#mainTab",
	getId : function (){
		var householderId = $(thisUiConfig.id).val();
		return householderId;
	}
}

//列表加载
var tableInfo =  {
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			url : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/search",
			columns : [[
						{
							field : "noticeId",
							hidden:true
						}, {
							field : "title",
							title : "标题",
							width : 100
						},{
							field:"body",
							title:"消息内容",
							width:240
						}
		 			]],
		 			onSelect : function(rowIndex, rowData) {
		 				main.addTab({
		 					id: 'NOTICE',
		 					title: '消息公告',
		 					url: thisUiConfig.ctx + '/notice/list',
		 					closable: true
		 				});
		 				parent.layer.closeAll('iframe');
					},
		 			
		});
	}

}
$(document).ready(function(){
	tableInfo.init();
});

</script>
</body>
</html>