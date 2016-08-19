<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>查找党员</title>
	
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
<div class="g-layout">
		<!-- 查询区域 -->
	<div style="width:100%;padding:10px 10px;">
		<form id="queryForm" method="post">
			<table cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td><label>姓名</label></td>
						<td>
		            		<input id="q_hh.HOUSEHOLDER_NAME_LIKE" name="q_hh.HOUSEHOLDER_NAME_LIKE" class="easyui-validatebox form-control"
		            			style="width:150px;margin:0 5px;">
	          			</td>
	          			<td><label>社会职务</label></td>
	          			<td>
<!-- 		            		<input id="q_hh.SOCIAL_JOB_EQ" name="q_hh.SOCIAL_JOB_EQ" class="easyui-validatebox form-control"> -->
	     					<input name="q_hh.SOCIAL_JOB_EQ" class="easyui-combobox form-control"
	     						style="width:230px;margin:0 20px;"
	     						dictCode="socialFunction"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	          			</td>
	          			<td>
	          				<a class="easyui-linkbutton g-button" onclick="query()"><i class="fa fa-search"></i>查询</a>
	          				<a class="easyui-linkbutton g-button" onclick="clearQueryForm('#queryForm');query();"><i class="fa fa-reply"></i>重置</a>
	          			</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
	<!-- 列表区域 -->
	<div id="list">
		<table id="userList" style="height:310px;"></table>
	</div>
	<div id="toolbar" class="dialog-button">
		<a id="confirm" class="easyui-linkbutton g-button" onclick="window.actions.confirm($('#userList').datagrid('getSelections'));"><i class="fa fa-check"></i>确定</a>
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
$(function() {
	publicAttr.initCombobox();
	
	var col;
	
		col = [[
// 			{field: '', checkbox: true},
			{field : "houseHolderId", title : "主键", align:'left', halign:'center', hidden : true},
			{field : "houseHolderName", title : "姓名", align:'left', halign:'center', width : 120},
			{field : "age", title : "年龄", align:'left', halign:'center', width : 120},
			{field : "sex", title : "性别", align:'left', halign:'center', width : 120,
					formatter : function(value, row, index){
						if(value == 'male'){
							return '男';
						}else if(value == 'female'){
							return '女';
						}
					}	
			},
			{field : "eduLevel", title : "文化程度", align:'left', halign:'center', width : 120,
					formatter : function(value, row, index){
						if(value == 'zhongzhuan'){
							return '中专';
						}else if(value == 'gaozhong'){
							return '高中';
						}else if(value == 'chuzhong'){
							return '初中';
						}else if(value == 'xiaoxue'){
							return '小学';
						}else if(value == 'youeryuan'){
							return '幼儿园';
						}else if(value == 'dazhuan'){
							return '大专';
						}else if(value == 'boshihou'){
							return '博士后';
						}else if(value == 'boshi'){
							return '博士';
						}else if(value == 'shuoshi'){
							return '硕士';
						}else if(value == 'benke'){
							return '本科';
						}else{
							return '';
						}
					}	
			},
			{field : "socialJob", title : "社会职务", align:'left', halign:'center', width : 120,
				formatter : function(value, row, index){
					if(value == '0'){
						return '其他';
					}else if(value == '9'){
						return '老党员';
					}else if(value == '8'){
						return '老干部';
					}else if(value == '7'){
						return '老教师';
					}else if(value == '6'){
						return '监督员';
					}else if(value == '5'){
						return '社会法官';
					}else if(value == '4'){
						return '政协委员';
					}else if(value == '3'){
						return '人大代表';
					}else if(value == '2'){
						return '居民组长';
					}else if(value == '1'){
						return '楼组长';
					}
				}
			}
		]];
	
	$("#userList").datagrid({
		rownumbers: true,
		singleSelect: window.params.singleSelect,
		autoRowHeight: false,
		border: false,
		pageSize: 10,
		pageList: defaultPageList,
		pagination: true,
		url: "${ctx}/postDuty/findMember",
		columns: col
	});
	
	$("#userList").datagrid("getPager").pagination({
		layout : defaultPageLayout
	}); 
		
});
	/**
	 * 查询按钮
	 */
	 function query() {
	 		$("#userList").datagrid("load", getFormData("#queryForm"));
		}
	
</script>

</body>
</html>