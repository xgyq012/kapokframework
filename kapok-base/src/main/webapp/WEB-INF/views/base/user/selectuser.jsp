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
			            <label>用户账号</label>
			            <input id="q_userName_LIKE" name="q_userName_LIKE" style="height: 25px; margin: 0;">
			        </td>
					<td class="aaaa">
			            <label>姓名</label>
			            <input id="q_realname_LIKE" name="q_realname_LIKE" style="height: 25px; margin: 0;">
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
	    <a id="cancel" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-reply"></i>取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >
var thisUiConfig = {
	ctx : "${ctx}",
	url : "user",
	id : "#userId",    //表单中主键字段对应的控件的Id属性值
	idName:"userId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var householderId = $(thisUiConfig.id).val();
		return householderId;
	}
};
var singleSelect = true;  //默认只能单选
$(function (){
	
	$("#queryForm input").on("keyup", function(){
		    tableInfo.query();
	    }
	);
	singleSelect = window.params.singleSelect;
	tableInfo.init();
});

//列表加载
var tableInfo =  {
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			rownumbers : true,
			singleSelect : singleSelect,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			//pagination : true,
			queryParams:  getFormData(thisUiConfig.queryForm),
			url : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/search",
			columns : [[
						{field : "userName", title : "用户账号", width: 100},
						{field : "realname", title : "实际名称", width: 100},
						{field : "mobileno", title : "手机", width: 100},
						{field : "empcode", title : "员工编号", width: 70},
						{field : "statusName", title : "状态", width: 50},
						{field : "haddress", title : "联系地址", width: 180}
		 			]],
			onLoadSuccess : function(data) {
				$(thisUiConfig.tableList).datagrid("unselectAll");
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
	if(singleSelect){
		window.actions.confirm($('#listGrid').datagrid('getSelected'));
	}else{
		window.actions.confirm($('#listGrid').datagrid('getSelections'));
	}
}


</script>
</body>
</html>