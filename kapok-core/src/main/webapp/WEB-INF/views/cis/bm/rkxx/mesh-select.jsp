<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>选择网格</title>
	
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
	<!-- 列表区域 -->
	<div style="height:428px;overflow: auto;">
		<ul id="meshTree" class="easyui-tree " data-options="animate:true"></ul>
	</div>
	<div id="toolbar" class="dialog-button">
		<a id="confirm" class="easyui-linkbutton g-button" onclick="confirm()"><i class="fa fa-check"></i>确定</a>
		<a id="cancel" class="easyui-linkbutton g-button" onclick="window.actions.cancel()"><i class="fa fa-times"></i>取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">
	$(function () {
		var userMesh = main.userMesh;
		$('#meshTree').tree({
			url: '${ctx}/mesh/getMesh',
			formatter: function(node) {
				if (userMesh[node.meshId]) {
					return node.meshName;
				}
				else {
					return '<span style="color:#aaa">'+node.meshName+'（不可选）</span>';
				}
			},
			onBeforeSelect: function(node) {
				if (userMesh[node.meshId]) return true; return false;
			},
			loadFilter: function(data, parent) {
				data.forEach(function(x) {
					x.id = x.meshId;
				});
				return data;
			}
		});
	});
	
	function confirm() {
		var selectedMesh = $('#meshTree').tree('getSelected');
		if (selectedMesh) {
			main.showWaiting();
			$.ajax({
				url: '${ctx}/mesh/getMeshChildren/'+selectedMesh.meshId
			}).done(function(res) {
				main.closeWaiting();
				if (res.resultCode == '0') {
					var meshChildrenIds = '';
					res.rows.forEach(function(x) {
						meshChildrenIds = meshChildrenIds+','+x.meshId;
					});
					selectedMesh.meshChildrenIds = meshChildrenIds.substring(1);
					window.actions.confirm(selectedMesh);
				} else {
					main.error('温馨提示', res.resultMsg);
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
				main.closeWaiting();
				main.error('温馨提示', '加载时出错!');
			});
		}
	}
	
</script>
</body>
</html>