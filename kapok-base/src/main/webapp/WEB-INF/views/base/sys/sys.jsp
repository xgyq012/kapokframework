<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统管理</title>
	
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/base.css">
</head>

<body>
<div class="container">
	<div class="left">
		<div id="nav" class="easyui-accordion" data-options="border:false"></div> 
	</div>
	<div class="right">
		<div id="contentTabs" class="easyui-tabs g-tabs2" style="width:100%; height:100%;"></div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/base.js"></script>

<script type="text/javascript">
$(function() {
	
	Base.init({resourceCode:'SYS',navId:'nav',workbenchUrl:'base/sys/sys-workbench'});
	
});
</script>
</body>
</html>