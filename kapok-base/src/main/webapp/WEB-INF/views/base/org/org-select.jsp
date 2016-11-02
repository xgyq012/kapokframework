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
	<!-- 
	<div id="query">
		<form id="queryForm" class="formCls" method="post">
			<table>
				<tr>
					<td>角色编码</td>
					<td>
						<input type="text" />
					</td>
					<td>角色名称</td>
					<td>
						<input type="text" />
					</td>
					<td>
						<a href="javascript:void(0);" onclick="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
					</td>
					<td>
						<a href="javascript:void(0);" onclick="clear();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	 -->

	<!-- 列表区域 -->
	<div id="list">
		<table id="userList" class="easyui-datagrid" style="height: 306px"
			data-options="
				rownumbers: true,
				singleSelect: window.params.singleSelect,
				autoRowHeight: true,
				pagination: true,
				pageSize: 10,
				border:false,
				url: '${ctx}/org/search',
				method: 'get'">
			<thead>
				<tr>
					<th data-options="field:'orgCode'">组织编码</th>
					<th data-options="field:'orgName'">组织名称</th>
					<th data-options="field:'orgFullname'">组织全称</th>
					<th data-options="field:'orgAddr'">组织地址</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="toolbar" class="dialog-button">
		<a id="confirm" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.actions.confirm($('#userList').datagrid('getSelections'));" data-options="plain:true,iconCls:'icon-save'">确定</a>
		<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.actions.cancel();" data-options="plain:true,iconCls:'icon-undo'">取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

</script>

</body>
</html>