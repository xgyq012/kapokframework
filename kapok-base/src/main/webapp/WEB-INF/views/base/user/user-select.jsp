<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
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
<div class="g-layout">
	<!-- 查询区域 -->
	<div style="width:100%;padding:10px 10px;">
		<form id="queryForm" method="post">
			<table cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td><label>用户账号</label></td>
						<td>
		            		<input id="q_userName_LIKE" name="q_userName_LIKE" class="easyui-validatebox form-control"
		            			style="width:150px;margin:0 5px;">
	          			</td>
	          			<td><label>实际名称</label></td>
	          			<td>
		            		<input id="q_realname_LIKE" name="q_realname_LIKE" class="easyui-validatebox form-control"
		            			style="width:150px;margin:0 5px;">
	          			</td>
	          			<td>
	          				<a class="easyui-linkbutton g-button" onclick="query()"><i class="fa fa-search"></i>查询</a>
	          				<a class="easyui-linkbutton g-button" onclick="clearQueryForm('#queryForm');"><i class="fa fa-reply"></i>重置</a>
	          			</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<!-- 列表区域 -->
	<div id="list">
		<table id="userList" style="height:310px;"></table>
	</div>
	<div id="toolbar" class="dialog-button">
		<a id="confirm" class="easyui-linkbutton g-button" onclick="window.actions.confirm($('#userList').datagrid('getSelections'));"><i class="fa fa-check"></i>确定</a>
		<a id="cancel" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-times"></i>取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">
	$(function() {
		
		var col;
		
		if (window.params.checkbox) {
			col = [[
				{field: '', checkbox: true},
				{field: "userName", title: "用户账号"},
				{field: "realname", title: "实际名称"},
				{field: "mobileno", title: "手机"},
				{field: "email", title: "邮箱"},
				{field: "statusName", title: "状态"},
				{field: "haddress", title: "联系地址"}
			]];
		} else {
			col = [[
				{field: "userName", title: "用户账号"},
				{field: "realname", title: "实际名称"},
				{field: "mobileno", title: "手机"},
				{field: "email", title: "邮箱"},
				{field: "statusName", title: "状态"},
				{field: "haddress", title: "联系地址"}
			]];
		}
		
		$("#userList").datagrid({
			rownumbers: true,
			singleSelect: window.params.singleSelect,
			autoRowHeight: false,
			border: false,
			pageSize: 10,
			pageList: defaultPageList,
			pagination: true,
			url: "${ctx}/user/search",
			columns: col
		});
		
		$("#userList").datagrid("getPager").pagination({
			layout : defaultPageLayout
		}); 
			
	});

	function query() {
 		$("#userList").datagrid("load", getFormData("#queryForm"));
	}
</script>

</body>
</html>