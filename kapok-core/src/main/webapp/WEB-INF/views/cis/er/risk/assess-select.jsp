<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>初评</title>
	
    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/page.css">
	<style>
		.radiobtn{margin-top:8px;margin-left:8px}
		.edit-label{width:50px;height:25px;text-align:right;float:left;line-height:25px;padding: 0 5px 0 0;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;word-break:keep-all}
	</style>
</head>

<body>
<div class="g-layout">
	<!-- 列表区域 -->
	<form id="dataForm">
				<!-- 隐藏属性 -->
	    <input id="riskId" name="riskId" type="hidden"/>
		<input id="riskStatus" name="riskStatus" type="hidden"/>
		<input id="delSign" name="delSign" type="hidden"/>
		<input id="statusResult" name="statusResult" type="hidden"/>
		<!-- 操作人 -->
		<input id="operator" name="operator" type="hidden"/>
		<input id="createrId" name="createrId" type="hidden"/>
		<input id="createTime" name="createTime" type="hidden"/>
		
		<table class="g-form" cellpadding="0" cellspacing="0" style="min-width:0;margin-left:-18px;">
			<tbody>
				<tr>
					<td class="form-cell-2">
	            		<label class="form-label">参与人</label>
     					<input id="player" name="player" class="easyui-validatebox form-control" type="text"/>
         			</td>
				</tr>
				<tr>
					<td class="form-cell-2">
						<label class="form-label">填写报告</label>
						<textarea name="remark" class="easyui-validatebox form-control" style="height:100px;"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	
	</form>

	<div id="toolbar" class="dialog-button">
		<a id="confirm" class="easyui-linkbutton g-button" onclick="saveAssess();"><i class="fa fa-check"></i>通过</a>
		<a id="cancel" class="easyui-linkbutton g-button" onclick="window.actions.cancel(cancelAssess());"><i class="fa fa-times"></i>不通过</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript">

 /**
  * 脚本入口  
  */
  $(function(){
	  $("#riskId").val(params.riskId);
// 	  $("#riskStatus").val(params.riskStatus);
  });
	
  /**
	* 确定
	*/
	function saveAssess(){
	  $("input[name='riskStatus']").val('assess');
	  $("input[name='statusResult']").val('Y');
	  $('#operator').val('<shiro:principal/>');
	  var data = getFormData("#dataForm");
	  console.log(data);
	  $.ajax({
		  type : "post",
		  url : "${ctx}/riskStatus/save",
		  data : data
	  }).done(function(res){
		  window.actions.confirm(res);
	  });
}
  
	/**
	* 取消
	*/
	function cancelAssess(){
	  $("input[name='riskStatus']").val('assess');
	  $('#operator').val('<shiro:principal/>');
	  $("input[name='statusResult']").val('N');
	  $('#player').val('<shiro:principal/>');
	  var data = getFormData("#dataForm");
	  console.log(data);
	  $.ajax({
		  type : "post",
		  url : "${ctx}/riskStatus/noPass",
		  data : data
	  }).done(function(res){
		  window.actions.confirm(res);
	  });
}
	
</script>

</body>
</html>