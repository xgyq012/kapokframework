<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
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
<div class="easyui-panel" data-options="fit:true,border:false">
	<div style="padding:10px 60px 20px 60px">
		<form id="dataForm" class="formCls" method="post">
		    <table>
		        <tr>
		            <td>
		            	原密码
		            </td>
		            <td>
		            	<input id="oldPassword" name="oldPassword" type="password" />
		            </td>
		        </tr>
		        <tr>
		            <td>
		            	新密码
		            </td>
		            <td>
		            	<input id="newPassword" name="newPassword" type="password" />
		            </td>
		        </tr>
		        <tr>
		            <td>
		            	确认密码
		            </td>
		            <td>
		            	<input id="newPasswordAgain" type="password" />
		            </td>
		        </tr>
		        <tr>
		            <td colspan="2">
					    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true" onclick="submitForm()">提交</a>
					</td>
		        </tr>
		    </table>
		</form>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/jssha/sha.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">
	
	$.extend($.fn.validatebox.defaults.rules, {
		pwdCheck : {
			validator : function(value, param) {
				var reg = /^[0-9a-zA-Z!@#$%^&*()\-\=\\\_\+\|\[\]\{\}\;\'\:\"\,\.\/\<\>\?]{6,32}$/g;
				return reg.test(value);
			},
			message: "密码由6-32位的数字、大小写字母、非字母字符（如：! @ # $ % ^ & * ( ) < > ?）等组成。"
		},
		equals : {
	        validator : function(value, param) {
	            return value == $(param[0]).val();
	        },
	        message : "输入的新密码与确认密码不一致。"
	    },
	    notEquals : {
	        validator : function(value, param) {
	            return value != $(param[0]).val();
	        },
	        message : "输入的新密码不能与原密码一致。"
	    },
		remote : {
			validator : function(value, param) {
				var shaObj = new jsSHA(value, "TEXT");
				var params = {};
				params[param[1]] = shaObj.getHash("SHA-256", "HEX");
				var result = $.ajax({
					url : param[0],
					dataType : "json",
					data : params,
					async : false,
					cache : false,
					type : "post"
				}).responseJSON;
				return result.resultCode == "0";
			},
			message : "输入的原密码不正确。"
		}
	});
	
	$(function() {
		
		$("#oldPassword").focus();
		
		$("#oldPassword").validatebox({
		    required : true,
		    validType : ["pwdCheck", "remote['${ctx}/user/checkUserPassword','oldPassword']"]
		});
		
		$("#newPassword").validatebox({
		    required : true,
		    validType : ["pwdCheck", "notEquals['#oldPassword']"]
		});
		
		$("#newPasswordAgain").validatebox({
		    required : true,
		    validType : ["pwdCheck", "equals['#newPassword']"]
		});
		
	});

	function submitForm() {
		if ($("#dataForm").form("validate")) {
			$.messager.progress();
			var oldPasswordShaObj = new jsSHA($("#oldPassword").val(), "TEXT");
			var newPasswordShaObj = new jsSHA($("#newPassword").val(), "TEXT");
			$("#oldPassword").val(oldPasswordShaObj.getHash("SHA-256", "HEX"));
			$("#newPassword").val(newPasswordShaObj.getHash("SHA-256", "HEX"));
			var data = getFormData("#dataForm");
			$.ajax({
				type: "POST",
				url: "${ctx}/user/modifyUserPassword",
				data: data
			}).done(function(res) {
				$.messager.progress("close");
				$("#dataForm")[0].reset();
				if (res.resultCode == "0") {
					alert(res.resultMsg + ", 请重新登录！");
					top.window.location.href = "${ctx}/logout";
				} else {
					$.messager.alert("温馨提示", res.resultMsg, "error");
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				$("#dataForm")[0].reset();
				$.messager.alert("温馨提示", "修改密码时系统出错！", "error");
			});
		}
	}

</script>
</body>
</html>