<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>签收</title>
	
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
	    <input id="rowTableId" name="rowTableId" type="hidden" class="rowTable"/>
		<input id="suEnrollId" name="enrollId" type="hidden" class="rowTable"/>
		<input id="process" name="process" type="hidden" class="rowTable"/>
		<input id="operateId" name="operateId" type="hidden" class="rowTable"/>
		<input id="backLogId" name="backLogId" type="hidden" class="rowTable"/>
		<input id="createrId" name="createrId" type="hidden"/>
		<input id="createTime" name="createTime" type="hidden"/>
		
		<table class="g-form" cellpadding="0" cellspacing="0" style="min-width:0">
			<tbody>
				<tr>
					<td class="form-cell-4">
						<label class="edit-label">意见</label>
						<textarea name="remark" class="easyui-validatebox form-control" style="height:100px;"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	
	</form>

	<div id="toolbar" class="dialog-button">
		<a id="confirm" class="easyui-linkbutton g-button" onclick="signFor();"><i class="fa fa-check"></i>签收</a>
		<a id="cancel" class="easyui-linkbutton g-button" onclick="window.actions.cancel(cancelSignFor());"><i class="fa fa-times"></i>拒签</a>
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
	  $("#suEnrollId").val(params.enrollId);
	  $("#backLogId").val(params.meshId);
// 	  submitLookup();
  });
	
  /**
	* 确定
	*/
	function saveSignFor(){
	  $("input[name='process']").val('signFor');
	  $('#operateId').val('<shiro:principal property="userId"/>');
	  var data = getFormData("#dataForm");
	  console.log(data);
	  $.ajax({
		  type : "post",
		  url : "${ctx}/enrollRowTable/save",
		  data : data
	  }).done(function(res){
		  window.actions.confirm(res);
	  });
}
  
	/**
	* 取消
	*/
	function cancelSignFor(){
	  $("input[name='process']").val('sendBack');
	  $('#operateId').val('<shiro:principal property="userId"/>');
	  var data = getFormData("#dataForm");
	  console.log(data);
	  $.ajax({
		  type : "post",
		  url : "${ctx}/enrollRowTable/save",
		  data : data
	  }).done(function(res){
		  window.actions.confirm(res);
	  });
}
	
	function signFor(){
		saveSignFor();
	}

</script>

</body>
</html>