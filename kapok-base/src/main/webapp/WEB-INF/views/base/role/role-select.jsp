<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${system_name}-${company_name}</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/sis.css" />
</head>

<body>
<div class="content">
	<!-- 查询区域 -->
	<div id="query">
		<form id="queryForm" class="formCls">
			<table>
				<tr>
					<td>角色编码</td>
					<td>
						<input id="q_roleCode_LIKE" name="q_roleCode_LIKE" type="text"/>
					</td>
					<td>角色名称</td>
					<td>
						<input id="q_roleName_LIKE" name="q_roleName_LIKE" type="text"/>
					</td>
					<td>
						<a href="javascript:void(0);" onclick="query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
					</td>
					<td>
						<a href="javascript:void(0);" onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 列表区域 -->
	<div id="list">
		<table id="roleList" style="height: 310px"></table>
	</div>
	<div id="toolbar" class="dialog-button">
		<a id="confirm" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.actions.confirm($('#roleList').datagrid('getSelections'));" data-options="plain:true,iconCls:'icon-ok'">确定</a>
		<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.actions.cancel();" data-options="plain:true,iconCls:'icon-undo'">取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript">
	$(function() {
		
		var col;
		
		if (window.params.checkbox) {
			col = [[
				{checkbox : true},
				{field : "roleCode", title : "角色编码"},
				{field : "roleName", title : "角色名称"},
				{field : "roleType", title : "角色类型"},
				{field : "roleDesc", title : "角色描述"}
			]];
		} else {
			col = [[
				{field : "roleCode", title : "角色编码"},
				{field : "roleName", title : "角色名称"},
				{field : "roleType", title : "角色类型"},
				{field : "roleDesc", title : "角色描述"}
			]];
		}
		
		$("#roleList").datagrid({
			rownumbers : true,
			singleSelect : window.params.singleSelect,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			url : "${ctx}/role/search",
			columns : col
		});
		
		$("#roleList").datagrid("getPager").pagination({
			layout : defaultPageLayout
		}); 
			
	});
	
	function query() {
		$("#roleList").datagrid("load", getFormData("#queryForm"));
	}
</script>

</body>
</html>