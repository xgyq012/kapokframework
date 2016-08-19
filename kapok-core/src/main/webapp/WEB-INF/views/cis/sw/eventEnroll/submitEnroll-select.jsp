<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>提交</title>
	
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
		.edit-label{width:70px;height:25px;text-align:right;float:left;line-height:25px;padding: 0 5px 0 0;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;word-break:keep-all}
	</style>
</head>

<body>
<div class="g-layout">
	<!-- 列表区域 -->
	<form id="dataForm">
				<!-- 隐藏属性 -->
	    <input id="rowTableId" name="rowTableId" type="hidden" class="rowTable"/>
		<input id="suEnrollId" name="enrollId" type="hidden" class="rowTable"/>
<!-- 		<input id="backLogId" name="backLogId" type="hidden" class="rowTable"/> -->
		<input id="process" name="process" type="hidden" class="rowTable"/>
		<input id="operateId" name="operateId" type="hidden" class="rowTable"/>
		<input id="scoreDetail" name="scoreDetail" type="hidden" />
		<input id="createrId" name="createrId" type="hidden"/>
		<input id="createTime" name="createTime" type="hidden"/>
		
		<table class="g-form" cellpadding="0" cellspacing="0" style="min-width:0;margin-left:-20px;">
			<tbody>
				<tr>
					<td class="form-cell-4">
						<label id="abc" class="form-label"></label>
						<input id="process" name="process" class="radiobtn" type="radio" onclick="appearIn();"> 上报
						<input id="process" name="process" class="radiobtn" type="radio" onclick="assignyy();"> 交办
					</td>
				</tr>
				<tr>
					<td class="form-cell-4">
	            		<label class="form-label">所属机构</label>
						<input id="backLogId" name="backLogId" >
<!-- 						<input id="backLogOne" name="backLogOne" type="hidden"> -->
         			</td>
				</tr>
				<tr>
					<td class="form-cell-4">
						<label class="form-label">意见</label>
						<textarea name="remark" class="easyui-validatebox form-control" style="height:100px;"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	
	</form>

	<div id="toolbar" class="dialog-button">
		<a id="confirm" class="easyui-linkbutton g-button" onclick="sub();"><i class="fa fa-check"></i>确定</a>
		<a id="cancel" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-times"></i>取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript">

 /**
  * 脚本入口  
  */
  $(function(){
	  $("#suEnrollId").val(params.enrollId);
	  submitLookup();
  });
	
/**
 * 上报
 */
 function appearIn(){
	   $("#scoreDetail").val('upScore');
	   $("input[name='process']").val('appearIn');
	   $("#backLogId").lookup("setTxt", publicAttr.getOrgName(params.meshId));
	   $("#backLogId").lookup('setVal', params.meshId);
}

 var submitLookup;

/**
 * 交办
 */
 function assignyy(){
	   $("#scoreDetail").val('shuntScore');
	   submitLookup.lookup("click");
	   $("input[name='process']").val('assign');
}

  /**
	* 确定
	*/
	function saveSubmit(){
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
* 巡检员弹出窗
*/
function submitLookup() {
	submitLookup = $('#backLogId').lookup({
		title : "选择所属网格",
		url : "${ctx}/mesh/selectmesh",
		width : 350,
		height : 500,
		required : true,
		valueField: 'meshId',
		textField: 'meshName',
		innerDialog: true
	});
}

function sub(){
	saveSubmit();
}
</script>

</body>
</html>