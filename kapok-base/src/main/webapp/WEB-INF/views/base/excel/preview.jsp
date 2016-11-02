<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Excel导入预览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/sis.css" />
</head>
<body>
<div class="content">
	<!-- 列表区域 -->
	<div id="list">
		<table id="previewList" style="height: 326px"></table>
	</div>
	<div id="toolbar" class="dialog-button">
		<a id="import" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.actions.import();" data-options="plain:true,iconCls:'icon-import'">导入</a>
		<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.actions.cancel();" data-options="plain:true,iconCls:'icon-undo'">取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript">
	$(function() {
		var headers = window.params.headers;
		var cols = new Array();
		for (var i = 0; i < headers.length; i++) {
			var col = {field : headers[i][0], title : headers[i][1]};
			cols.push(col);
		}
 		$("#previewList").datagrid({
 			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
 			columns : [cols],
			data : window.params.data
 		});
	});
</script>
</body>
</html>