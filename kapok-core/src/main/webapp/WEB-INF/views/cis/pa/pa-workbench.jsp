<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PA工作台</title>
	
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
<!-- 	<div style="padding:20px"> -->
<!-- 		<h1>PA热烈欢迎您！</h1> -->
<!-- 	</div> -->
<!-- <div class="g-layout"> -->
<%-- 		<img src="${ctx}/resources/images/er.jpg"> --%>
<!-- </div> -->
<div style="width:100%;height:100%;padding:20px;overflow:scroll;" id="partyRegime">
		</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript">
// 	console.log('home', main.getResourceId('BM'));
/**
 * 脚本入口
 */
 $(function(){
	 getData();
 });
 
 function getData(){
	 $.ajax({
		 url : "${ctx}/partyRegime/getData"
	 }).done(function(res){
		 $("#partyRegime").html(res.row);
	 });
 }
</script>
</body>
</html>