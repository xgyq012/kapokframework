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
	<style type="text/css">
	.aaaa{
	padding: 0 10px;
	margin: 0;
	}
	</style>
	
</head>
<body>
<div id="mainTab" class="easyui-panel" style="width:100%;height:100%; margin: 0; padding: 0;">
     <div title="列表" style="position:relative;">
         <div id="query">
		    <form id="queryForm" class="formCls" method="post">
			  <table style="padding: 5px 0 6px 0;">
				<tr>	
					<td class="aaaa">
			            <label>应急人员</label>
			            <input id="q_yingjirenName_LIKE" name="q_yingjirenName_LIKE" style="height: 25px; margin: 0;">
			        </td>
					<td class="aaaa">
			            <label>应急事件类型</label>
			            <input id="q_yjsjlxName_LIKE" name="q_yjsjlxName_LIKE" style="height: 25px; margin: 0;">
			        </td>			        
					<td class="aaaa">
						<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
						<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
					</td>				
				</tr>
			   </table>
		     </form>
	     </div>
		<table title="用户列表" id="listGrid" style="height: 280px;"></table>
	</div>
    <div id="toolbar" class="dialog-button" style="border: 0;">
		<a id="confirm" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="confirm();" ><i class="fa fa-check"></i>确定</a>
	    <a id="cancel" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-times"></i>取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >
var thisUiConfig = {
	ctx : "${ctx}",
	url : "yingjirenyuan",
	tableList : "#listGrid",
	queryForm : "#queryForm",
};
var col;

$(function (){
	$("#queryForm input").on("keyup", function(){
		    tableInfo.query();
	    }
	);
	initColumnsConfig();
	tableInfo.init();
});


//列表加载
var tableInfo =  {
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			rownumbers : true,
			singleSelect : window.params.singleSelect,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			url : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/search",
			columns : col,
			onSelect : function(rowIndex, rowData) {
				window.actions.add(rowData);
			},
			onUnselect : function(rowIndex, rowData) {
				window.actions.del(rowData);
			},
			onCheckAll: function(rows){
				window.actions.addRows(rows);
			},
			onUncheckAll: function(rows){
				window.actions.delRows(rows);
			},
			onLoadSuccess : function(data) {
				//$(thisUiConfig.tableList).datagrid("unselectAll");
			}
		});
		
		// 设置分页显示形式
		$(thisUiConfig.tableList).datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	query : function (){
		var param = getFormData("#queryForm");
		$(thisUiConfig.tableList).datagrid("load", param);
	}
};

function confirm(){
	window.actions.confirm($('#listGrid').datagrid('getSelections'));
}

function initColumnsConfig(){
	if(window.params.checkbox){
		col = [ [ 
		{
			field : "yingjiRenyuanId",
			//hidden:true,
			checkbox : true
		}, {
			field : "yingjirenName",
			halign:'center',
			align:'left',
			title : "应急人员",
			width : 120
		},{
			field:"yjsjlxName",
			halign:'center',
			align:'left',
			title:"应急事件类型",
			width:130
		},{
			 field:"phone",
			 title:"联系电话",
			 halign:'center',
			 align:'left',
			 width:120
		}]];
	}else{
		col = [ [ {
			field : "yingjiRenyuanId",
			hidden:true
		}, {
			field : "yingjirenName",
			halign:'center',
			align:'left',
			title : "应急人员",
			width : 120
		},{
			field:"yjsjlxName",
			halign:'center',
			align:'left',
			title:"应急事件类型",
			width:130
		},{
			 field:"phone",
			 title:"联系电话",
			 halign:'center',
			 align:'left',
			 width:120
		}]];
	}
	
}

</script>
</body>
</html>