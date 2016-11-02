<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>配置管理</title>
	
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
	<!-- 按钮区域 -->
	<div class="g-toolbar">
		<a id="refresh" class="easyui-linkbutton g-button" onclick="refresh()"><i class="fa fa-refresh"></i>刷新</a>
		<a id="save" class="easyui-linkbutton g-button" onclick="save()"><i class="fa fa-floppy-o"></i>保存</a>
	</div>
	<div style="height:100%;padding-bottom:34px">
		<table id="configGrid" style="height:100%"></table>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">

	$(function() {
		
		var dict = main.dict,
			dicts = dict.dicts,
			comboboxConfig = {
				valueField: 'dictCode',
			    textField: 'dictName',
			    editable: false,
				panelHeight: 'auto'
			};
		
 		$('#configGrid').datagrid({
 			rownumbers : true,
			singleSelect: true,
			border: false,
			pagination: true,
			pageSize: defaultPageSize,
			pageList: defaultPageList,
			url: '${ctx}/config/search',
			onDblClickRow: _onDblClickRow,
 			columns: [[
				{field: 'createrId', title: '创建人ID', hidden: true},
				{field: 'createTime', title: '创建时间', hidden: true},
				{field: 'configId', title: '用户ID', hidden: true},
				{field: 'configType', title: '配置类型', hidden: true},
				{field: 'configCode', title: '配置编码', hidden: true},
				{field: 'configName', title: '配置名称', halign: 'center', width: 150},
				{field: 'configValue', editor:'text', title: '配置值', halign: 'center', width: 250},
				{
					field: 'enable',
					title: '是否有效',
					halign: 'center',
					align: 'center',
					width: 80,
					formatter: function(value,row,index) {
						if ('Y' == value) {
							return '<i class="fa fa-check-square"></i>';
						} else {
							return '';
						}
					},
					editor: {
						type: 'combobox',
						options: {
							valueField: 'dictCode',
							textField: 'dictName',
							editable: false,
							panelHeight: 'auto',
							data: dicts.YesOrNo.list
						}
					}
				},
				{field: 'remark', editor:'text', title: '备注', width: 300}
 			]]
 		});
 		
 		$('.g-layout').click(function() {
			$('#configGrid').datagrid('acceptChanges');
		});
	});
	
	function save() {
		var rows = $('#configGrid').datagrid('getData').rows;
		rows.forEach(function(x, i) {
			delete x.creater;
			delete x.lastUpdaterId;
			delete x.lastUpdater;
			delete x.lastUpdateTime;
		});
		$('#configGrid').datagrid('acceptChanges');
		main.showWaiting();
		$.ajax({
			type: 'POST',
			url: '${ctx}/config/save',
			data: {
				config: JSON.stringify(rows)
			}
		}).done(function(res) {
			main.closeWaiting();
			if (res.resultCode == '0') {
				main.show();
				refresh();
			} else {
				main.error('温馨提示', res.resultMsg);
			}
		}).fail(function(jqXHR, textStatus, errorThrown) {
			main.closeWaiting();
			main.error('温馨提示', '保存时出错!');
		});
	}
	
	function refresh() {
		$('#configGrid').datagrid('reload');
	}
	
	function _onDblClickRow(index) {
		$('#configGrid').datagrid('acceptChanges');
		$('#configGrid').datagrid('unselectAll');
		$('#configGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
	}
	
</script>

</body>
</html>