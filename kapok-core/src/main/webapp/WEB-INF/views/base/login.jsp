<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
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

<body style="background:url(${ctx}/resources/images/base/login_bg.jpg) repeat-x #3aa5e6;">
<div class="loginframe">
	<div class="mid">
        <div class="title">
        	<img src="${ctx}/resources/images/logo_48.png">
        	${system_name}
        </div>
        <div class="top">账号登录</div>
        <form id="loginForm">
	        <div class="apln">
	            <div class="mt">ACCOUNT LOGIN</div>
	            <div class="miss"></div>
	            <div class="mc">
	            	<input id="username" name="username" type="text" class="itxt" placeholder="账号">
					<input id="password" name="password" type="password" class="itxt" placeholder="密码" style="margin-top:20px;">
	               	<div id="btn_submit" class="button">登录</div>
	            </div>
	        </div>
        </form>
       	<div class="info">
			<div class="info_frame">
				<div class="tu"><i class="fa fa-user fa-lg"></i></div>
              	<div class="zi">
              		<span style="font-size:12px;">联系电话</span><br>
              		<span style="font-size:16px;">0757-88888888</span>
              	</div>
			</div>
           	<div class="info_frame" style="margin-left:20px;">
				<div class="tu"><i class="fa fa-comment fa-lg"></i></div>
       		   	<div class="zi">
       		       <span style="font-size:12px;">服务QQ</span><br>
       		       <span style="font-size:16px;">88888888</span>
       		   	</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/jssha/sha.js"></script>

<script type="text/javascript">
!function($) {
	
	$("#username").focus();
	
	$(document).keypress(function(event) {
		if (event.which == 13) {
			submitForm();
		}
	});
	
	$('#btn_submit').on('click', submitForm);
	
	function validate() {
		var username = $('#username').val();
		var password = $('#password').val();
		if (!username) {
			$('#username').focus();
			return false;
		}
		if (!password) {
			$('#password').focus();
			return false;
		}
		return true;
	}
	
	function submitForm() {
		if (validate()) {
			var uValue = $("#username").val(),
				pValue = $("#password").val();
			var shaObj = new jsSHA(pValue, "TEXT"),
				pValue = shaObj.getHash("SHA-256", "HEX");

			$.ajax({
				type: "POST",
				url: "${ctx}/login",
				data: {
					username: uValue,
					password: pValue
				}
			}).done(function(res) {
				$("#loginForm")[0].reset();
				if (res.resultCode == "0") {
					window.location.href = "${ctx}/index";
				} else {
					$('.miss').text(res.resultMsg)
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$("#loginForm")[0].reset();
				$.messager.alert("温馨提示", "登录时系统出错！", "error");
			});
		}
	}
	
}(window.jQuery);
</script>
</body>
</html>
