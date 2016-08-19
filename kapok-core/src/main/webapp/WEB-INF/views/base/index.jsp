<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${system_name}</title>
	
    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">

	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	
	<link rel="stylesheet" href="${ctx}/resources/css/index.css">
</head>

<body style="background:url(${ctx}/resources/images/base/index_bg.gif) repeat center top; background-attachment: fixed;">
<div class="headtop">
	<div class="s-logo"></div>
	<div class="s-title">
		<img src="${ctx}/resources/images/logo_48.png">
		${system_name}
	</div>
	<div class="u-info">
		<ul>
			<li>
				<a>欢迎您：<shiro:principal/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</li>
			<li>
				<a href="${ctx}/logout"><i class="fa fa-sign-out"></i>安全退出</a>
			</li>
		</ul>
	</div>
</div>
<div class="container">
	<div class="mid"><div class="frame"><ul></ul></div></div>
</div>
<div class="tailbottom">
	<input id="versionNumber" type="hidden">
	${copyright}<a id="downloadApp" href="#">客户端下载</a>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function() {
	
	var colors = ['#0da5e1', '#6a6765', '#4bb123', '#455f97', '#bf227b', '#fac30e', '#ee8a05', '#69176b', '#d23330', '#3cccc9'];
	
	$.ajax({
		url: '${ctx}/resource/getResourceByResourceType',
		method: 'POST',
		data: {
			resourceType: 'module'
		}
	}).done(function(res) {
		if (res.resultCode == '0') {
			res.rows.sort(function(a, b) {
				return a.seq - b.seq;
			});
            res.rows.forEach(function(x, i) {
            	var li = ''
		            	+ ' <li class="c" data-resource-code="'+x.resourceCode+'" style="background:'+colors[(i%10)]+'">'
		            	+ '     <div class="shade">'+(x.resourceDesc?x.resourceDesc:x.resourceLabel)+'</div>'
		            	+ ' 	<div class="asome">'+x.resourceLabel+'</div>'
		            	+ ' 	<div class="menu"><i class="'+x.imagePath+'"></i></div>'
		            	+ ' </li>';
            	$('.frame>ul').append(li);
            });
        }
	}).fail(function(jqXHR, textStatus, errorThrown) {
		$.messager.alert("温馨提示", "加载出错！", "error");
	});
	
	$.ajax({
		url: "${ctx}/clientversion/getVersionNumber/200888",
	}).done(function(res){
		if (res.resultCode == "0") {
			$('#versionNumber').val(res.row.versionNumber);
			$("#downloadApp").text("客户端下载" + res.row.versionNumber);
		} else {
			$("#downloadApp").text("");
		}			
	}).fail(function(jqXHR, textStatus, errorThrown) {
		$.messager.alert("温馨提示", "加载出错！", "error");
	});
	
	$(document)
		.on('click', '.c', function(e) {
			var resourceCode = $(this).attr('data-resource-code');
			if (resourceCode) {
				window.location.href = '${ctx}/main#' + resourceCode;
			}
		})
		.on('click', '#downloadApp', function(e){
			e.preventDefault();
			window.location.href = "${ctx}/clientversion/download?fileNumber=200888&versionNumber=" + $('#versionNumber').val();
		});
});
</script>
</body>
</html>