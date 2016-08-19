<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>案发情况</title>
<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css" type="text/css">
<!--[if IE 7]> 
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css"> 
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
<link rel="stylesheet" href="${ctx}/resources/css/page.css">
</head>
<body>

<div class="g-layout">

<div class="g-toolbar">
	<a onclick="baseInfo.add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
</div>

<div id="mainTab"  class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false">

      <div title="列表"  style="position:relative;">
         <div id="query">
				<form id="queryForm" class="formCls" method="post">
				
					<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>	
							<td class="form-cell-1">
		            				<label class="form-label">案发类型</label>
		            				<input dictCode="caseType" name="q_caseType_EQ" class="easyui-combobox form-control"
			            				style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'"></td>
							<td class="form-cell-1">
		            				<label class="form-label">案发日期</label>
		            				<input  name="q_caseTime_EQ" class="easyui-datebox form-control" 
		            						data-options="editable:false"
		            						style="width:${comboboxWidth};height:${comboboxHeight}px" /></td>
							<td class="form-cell-1">
		            				<label class="form-label">案发地点</label>
		            				<input  class="easyui-validatebox form-control"   name="caseAddress"  /></td>
							<td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
							</td>
						</tr>
					</tbody>
					</table>
				</form>
			</div>
	         	
         <div class="list-area" style="top:50px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
         
     </div>
 
	<div id="minute" data-options="disabled:true"  title="详细"   >
      
    		<form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="caseId" name="caseId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		
    		<table class="g-form" cellpadding="0" cellspacing="0" >
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">所属机构</label>
     					 <input id="orgId" name="orgId"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">案发时间</label>
     					<input  data-options="editable:false" name="caseTime" style="width:${comboboxWidth};height:${comboboxHeight}px" class="easyui-datebox form-control"  />
     				</td>
     				 <td class="form-cell-1">
	            		<label class="form-label">案件类型</label>
     					<input dictCode="caseType" name="caseType" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1"></td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-4" colspan="4">
	            		<label class="form-label">案发地点</label>
     					<input class="easyui-validatebox form-control"  required="required"  name="caseAddress"    />
     				</td>
     			</tr>
     			 <tr>
     				<td class="form-cell-4" colspan="4">
     					<label class="form-label">案件概述</label>
     					<textarea name="caseDesc" required="required" class="easyui-validatebox form-control" rows="8" ></textarea>
     				</td>
     			</tr>
     			
     			 <tr>
     				<td class="form-cell-4" colspan="4">
     					<label class="form-label">案件处理情况</label>
     					<textarea name="caseHandling"  class="easyui-validatebox form-control"   rows="8"   ></textarea>
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-4" colspan="4">
     					<label class="form-label">案件回访情况</label>
     					<textarea name="caseReturnVisit" class="easyui-validatebox form-control"  rows="8"  ></textarea>
     				</td>
     			</tr>
     			 
     		</table>
     		</form>
     </div>
</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" >

var thisUiConfig = {
	main : window.main,
	ctx : "${ctx}",
	url : "case",
	id : "#caseId",    //表单中主键字段对应的控件的Id属性值
	idName:"caseId",
	tableList : "#listGrid",
	getId : function (){
		var id = $(thisUiConfig.id).val();
		return id;
	}
}

$(function (){
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	publicAttr.initCombobox();
	orgLookup();
});


var tabs = {
		
		isEdit : true ,
		
		init : function (){
			
			$("#mainTab").tabs({
				onSelect : function(title, index) {
					if (index == 0) { // 选中列表标签
						$("#baseForm").form("clear");
						if ($("#listGrid").datagrid("getSelections").length > 0) {
							$("#mainTab").tabs("enableTab", 1);
							enableButtons([ "add", "del", "edit" ]);
						} else {
							$("#mainTab").tabs("disableTab", 1);
							enableButtons([ "add" ]);
						}
						tabs.isEdit = false;
						setFormItemDisabled("#baseForm",true,null);
					} else if (index == 1) { // 选中详细标签
						loadData();
						if(tabs.isEdit){
							enableButtons([ "add", "del", "save" ]);
							setFormItemDisabled("#baseForm",false,null);
						}else{
							enableButtons([ "add", "del","edit"]);
						}
					}
				}
			});
		}
		
}



//基础信息初始化
var baseInfo =  {
		
	saveUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",
	
	getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/del/",
	
	search : thisUiConfig.ctx + "/" + thisUiConfig.url + "/search ",
		
	init : function (){
		
			setFormItemDisabled("#baseForm",true,null);
		
	},
	
	save : function (){
		
		if($("#baseForm").form("validate")){
			var data = getFormData("#baseForm");
			if(data){
				publicFormControl.ajax({
					type : "POST",
					url : baseInfo.saveUrl,
					data : data
				},function (result){
					var row = result.row;
					$("#baseForm").form("load",row);
					$("#orgId").lookup('setTxt',publicAttr.getOrgName(row.orgId));//添加名称
					tableInfo.query();
					enableButtons([ "add", "save" ]);
					
				},"show");
			}
		}
		
	},
	
	add : function (){
		
		tabs.isEdit = false ;
		//clear data
		$("#listGrid").datagrid("unselectAll");
		$("#mainTab").tabs("enableTab", 1);
		$("#mainTab").tabs("select", 1);
		setFormItemDisabled("#baseForm",false,null);
		//$("#baseForm").form("enableValidation");
		enableButtons([ "add", "save" ]);
		$("#baseForm").form("clear");
		defaultMesh();         //默认网格
	},
	
	del:function (){
		
		var row = $("#listGrid").datagrid('getSelected');
		
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		
		$.messager.confirm("温馨提示", "确定删除案发地点为【"+row.caseAddress+"】记录?",
				
			function(r) {
				if (r) {
					publicFormControl.ajax({
						url : baseInfo.delUrl + row[thisUiConfig.idName],
						type: "POST" 
					},function (data){
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$("#mainTab").tabs("select", 0);
							$("#mainTab").tabs("disableTab", 1);
							tableInfo.query();
						}
					},"show");
				}
			});
		
	},
	
	edit:function (){
		tabs.isEdit = true ;
		var tab = $('#mainTab').tabs('getSelected');
		var  index = $('#mainTab').tabs('getTabIndex',tab);
		if(index!=1){
			$("#mainTab").tabs("enableTab", 1);
			$("#mainTab").tabs("select", 1);
		}else{
			enableButtons([ "add", "del" , "save" ]);
		}
		setFormItemDisabled("#baseForm",false,null);
	}
};

function  loadData(){
	var row = $(thisUiConfig.tableList).datagrid("getSelected");
	if(row){
		thisUiConfig.main.showWaiting({});
		var id = row[thisUiConfig.idName];
		if(id){
			publicFormControl.ajax({
				type : "POST",
				url : baseInfo.getUrl +  id,
			},function (result){
				var row = result.row ;
				$("#baseForm").form("load",row);
				$("#orgId").lookup('setTxt',publicAttr.getOrgName(row.orgId));//添加名称
			});
		}
	}
}
 

//列表加载
var tableInfo =  {
	
	//初始化列表
	init : function (){
		
		$("#listGrid").datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			queryParams:  tableInfo.getQueryFormData("#queryForm"),
			url : thisUiConfig.ctx +"/" + thisUiConfig.url + "/search",
			columns : [ [ {
				field : "caseId",
				hidden:true,
			},{
				field : "orgId",
				title : "所属机构",
				width : 150,
				align:'left',
				halign:'center',
				formatter : function (value,row,index){
						
					return publicAttr.getOrgName(value);
				}
			},{
				field : "caseTime",
				title : "案发时间",
				width : 100,
				align:'left',
				halign:'center'
			},{
				field:"caseAddress",
				title:"案发地点",
				width:200,
				align:'left',
				halign:'center'
			},{
				field:"caseType",
				title:"案件类型",
				width:150,
				align:'left',
				halign:'center',
				formatter : function (value,row,index){
					
					return publicAttr.getDictText("caseType",value);
				}
			}]],
			
			onSelect : function(rowIndex, rowData) {
				tabs.isEdit = false;
				var len = $("#listGrid").datagrid("getSelections").length;
				if(len==1){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit" ]);
				}else if(len==0){
					$("#mainTab").tabs("disableTab", 1);
					enableButtons([ "add"]);
				}else{
					enableButtons([ "add", "del"  ]);
					$("#mainTab").tabs("disableTab", 1);
				}
			},
			onUnselect : function(rowIndex, rowData) {
				var len = $("#listGrid").datagrid("getSelections").length;
				 
				if(len==1){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit" ]);
				}else if(len==0){
					enableButtons([ "add"]);
					$("#mainTab").tabs("disableTab", 1);
				}else{
					enableButtons([ "add", "del"  ]);
					$("#mainTab").tabs("disableTab", 1);
				}
			},
			onLoadSuccess : function(data) {
				$("#listGrid").datagrid("unselectAll");
			}
			
		});
		
		// 设置分页显示形式
		$("#listGrid").datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	
	query : function (){
		var param =tableInfo.getQueryFormData("#queryForm");
		$("#listGrid").datagrid("load", param);
		enableButtons([ "add" ]);
		$("#mainTab").tabs("disableTab", 1);
	},
	getQueryFormData : function (formId){
		var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
		var data =  getFormData(formId) || {};
		if(meshIds){
			data['q_orgId_IN'] = meshIds;
		}
		return data;
	}
	
}


function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	// 设置按钮可用
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).linkbutton("enable");
	}
}



var orgLookup;
/**
 * 机构弹出窗
 */
function orgLookup() {
	orgLookup = $('#orgId').lookup({
		title : "选择所属网格",
		url : thisUiConfig.ctx + "/mesh/selectmesh",
		width : 350,
		height : 500,
		required : true,
		valueField: 'meshId',
		textField: 'meshName'
	});
}

/**
 *  默认网格
 */
function defaultMesh(){
	var meshId = main.currentUserMesh.meshId;
	$("#orgId").lookup("setTxt", publicAttr.getOrgName(meshId));
	$("#orgId").lookup('setVal', meshId);
}

</script>

</body>
</html>