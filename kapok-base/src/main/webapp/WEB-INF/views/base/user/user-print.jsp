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
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/print.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/button.css" />
</head>
<body>
<div style="padding: 10px 20px;">
	<div class="print-button">
		<button class="preview button blue">打印预览</button>
		<button class="print button blue">打印</button>
	</div>
	<div id="printArea" class="print-area">
		<table class="print-title">
			<tr>
				<td>用户信息</td>
			</tr>
		</table>
		<table class="print-table">
			<tbody>
				<tr>
					<th></th>
					<th>用户账号</th>
					<th>姓名</th>
					<th>手机</th>
					<th>邮箱</th>
					<th>生效日期</th>
					<th>失效日期</th>
					<th>员工编号</th>
					<th>状态</th>
					<th>联系地址</th>
				</tr>
				<c:forEach varStatus="status" var="user" items="${users}" >
					<tr>
						<td>${status.index + 1}</td>
						<td>${user.userName}</td>
						<td>${user.realname}</td>
						<td>${user.mobileno}</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="print-button">
		<button class="preview button orange">打印预览</button>
		<button class="print button orange">打印</button>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-nateiot-ui/print.js"></script>
</body>
</html>