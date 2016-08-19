<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>服刑人员</title>
	
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
		.nav-left {width:200px;position:fixed;top:63px;right:5px;padding-top:15px}
		.nav-left li{line-height:28px;font-size:14px}
		.nav-left li a{padding-left:15px;color:#767676}
		.nav-left li a:hover,.nav-left li a.active{padding-left:13px;color:#3aa5e6;text-decoration:none;border-left:2px solid #3aa5e6}
		.content-right{width:100%;padding:0 200px 15px 0}
		.zvxx_remove:hover{
			cursor: pointer;
		}
	</style>
</head>

<body>
<div class="g-layout">
	<!-- 按钮区域 -->
	<div class="g-toolbar">
      <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
      <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	</div>
	
	<!-- 内容区域 -->
	<div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
		<div title="列表" style="position:relative;">
			<div class="query-area">
				<form id="queryForm" method="post">
					<table class="g-form" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="form-cell-1">
				            		<label class="form-label">姓名</label>
				            		<input name="q_householderName_LIKE" class="easyui-validatebox form-control">
			          			</td>
			          			<td class="form-cell-1">
				            		<label class="form-label">身份号</label>
				            		<input name="q_cardCode_EQ" class="easyui-validatebox form-control">
			          			</td>
			          			<td class="form-cell-1">
				            		<label class="form-label">婚姻状况</label>
				            		<input dictCode="maritalStatus" name="q_maritalStatus_EQ"
				            			class="easyui-combobox form-control"
				            			style="width:${comboboxWidth};height:${comboboxHeight}px"
				            			data-options="
		 								    	editable:false,
		 								    	panelHeight:'auto',
		 								    	valueField:'dictCode',
		 								    	textField:'dictName'">
			          			</td>
			          			<td class="form-cell-1">
				            		<label class="form-label">性别</label>
				            		<input dictCode="Gender" name="q_sex_EQ" class="easyui-combobox form-control"
				            			style="width:${comboboxWidth};height:${comboboxHeight}px"
				            			data-options="
		 								    	editable:false,
		 								    	panelHeight:'auto',
		 								    	valueField:'dictCode',
		 								    	textField:'dictName'">
			          			</td>
			          			
							</tr>
							<tr>
								<td class="form-cell-1">
				            		<label class="form-label">年龄</label>
									<input name="q_age_GT" class="easyui-validatebox form-control">
								</td>
								<td class="form-cell-1">
				            		<label class="form-label">~</label>
				            		<input name="q_age_LT" class="easyui-validatebox form-control">
				            	</td>
								<td class="form-cell-1">
				            		<label class="form-label">户籍类别</label>
									<input dictCode="residentType" name="q_householdType_EQ" class="easyui-combobox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
								</td>
								<td class="form-cell-1">
			            			<label class="form-label">管理类型</label>
			            			<input dictCode="ManageType" name="q_gllx_EQ" class="easyui-combobox form-control"
			            				style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
		 						</td>
							</tr>
							<tr>
								<td class="form-cell-3" colspan="3"></td>
								<td class="form-cell-1 f-button">
									<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
									<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			
			<div class="list-area" style="top:104px;">
				<table id="listGrid" style="height:100%"></table>
			</div>
		</div>
		
		<div id="detial" title="详细" data-options="disabled:true">
			<%@ include file="/WEB-INF/views/cis/bm/rkxx/view.jsp" %>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript">

$('.nav-left a').on('click', function(e) {
	e.preventDefault();
	var top = $('#detial').scrollTop();
  	$('#detial').animate({  
        scrollTop: $(this.hash).offset().top + top - 78  
    }, 500);
 	$('.nav-left a').removeClass('active');
	$(this).addClass('active');
});

var thisUiConfig = {
		main : window.parent.main,
		meshIdName : 'q_h.org_Id_IN',
		ctx : '${ctx}',
		searchUrl : '${ctx}/fxry/search',
		url : "householder",
		id : "#householderId",    //表单中主键字段对应的控件的Id属性值
		idName:"householderId",
		tableList : "#listGrid",
		getId : function (){
			return  $(thisUiConfig.id).val();
		}
	}
</script>
<script type="text/javascript" src="${ctx}/resources/js/bm-householder.js"></script>

<script type="text/javascript">

$(document).ready(function (){
	
	$("#detial").on('scroll',function() {
		globalMethod.processing();
	});
	
	
});

</script>

</html>