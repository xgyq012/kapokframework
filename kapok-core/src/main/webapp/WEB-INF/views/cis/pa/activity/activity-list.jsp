<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>

<title>党组织开展活动情况信息</title>
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
	    <a id="add" onclick="baseInfo.add();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	    <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	    <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	    <a id="del" onclick="baseInfo.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	</div>
	
	<div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
	    <div title="列表" style="position:relative;">
		    <div class="query-area">
			    <form id="queryForm" method="post">
				    <table class="g-form" class="formCls" cellpadding="0" cellspacing="0">
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">党组织名称</label>
	            				<input class="easyui-validatebox form-control" name="q_partyOrganization.organizationName_LIKE"  />
	            			</td>
		    				<td class="form-cell-1">
		    					<label class="form-label">活动名称</label>
		    					<input name="q_activityName_LIKE"  class="easyui-validatebox form-control" />
		    				</td>
		    				<td class="form-cell-1">
		    					<label class="form-label">活动地点</label>
		    					<input name="q_address_LIKE"  class="easyui-validatebox form-control" />
		    				</td>		    				
							<td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			<div class="list-area" style="top:50px;">
				<table id="listGrid" style="height:100%"></table>
			</div>
         
     	</div>
	     
	     <!-- data-options="disabled:true" -->
	     <div id="minute" data-options="disabled:true"  title="详细"   >
	   		<form id="baseForm"  action="">
	    		<input id="activityId" name="activityId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				<input id="delSign" name="delSign" type="hidden" />
	    		<table class="g-form" cellpadding="0" cellspacing="0" >
	    			<tbody>
	     			<tr>
	     				<td class="form-cell-1">
		            		<label class="form-label">所属机构</label>
		            		<input id="meshId" name="meshId"  type="hidden" />
		          		</td>
	     				<td class="form-cell-2" colspan="2">
	     					<label class="form-label">党组织名称</label>
	     					<input id="partyId" name="partyId" type="hidden">
	     					<input id="partyOrg" type="text" />
	     				</td>
	     				<td class="form-cell-1" colspan="1">
	     					<label class="form-label">活动时间</label>
	     					<input class="easyui-datetimebox form-control" id="activityTime" name="activityTime" required="required"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	     				</td>	     				
	     			</tr>
	     			
	     			<tr>
	     				<td class="form-cell-3" colspan="3">
	     					<label class="form-label">活动名称</label>
	     					<input id="activityName" name="activityName"  class="easyui-validatebox form-control" data-options="required:true"/>
	     				</td>
	     				<td class="form-cell-1">
	     					<label class="form-label">主持人</label>
	     					<input class="easyui-validatebox form-control" id="zhuchiren"  name="zhuchiren" />
	     				</td>    
	     			</tr>
	     			<tr>
	     				<td class="form-cell-4"  colspan="4">
	     					<label class="form-label">活动地点</label>
	     					<input id="address" name="address"  class="easyui-validatebox form-control"/>
	     				</td>
	     			</tr>	     			
	     			<tr>
	     				<td class="form-cell-4"  colspan="4">
	     					<label class="form-label">参加人</label>
	     					<textarea rows="3" class="easyui-validatebox form-control"  name="canjiaren"></textarea>
	     				</td>
	     			</tr>
	     			<tr>
	     				<td class="form-cell-4"  colspan="4">
	     					<label class="form-label">活动内容</label>
	     					<textarea rows="6" class="easyui-validatebox form-control"  name="activityBody"></textarea>
	     				</td>
	     			</tr>	
	     			<tr>
	     				<td class="form-cell-4"  colspan="4">
	     					<label class="form-label">摘要</label>
	     					<textarea rows="4" class="easyui-validatebox form-control"  name="remark"></textarea>
	     				</td>
	     			</tr>	     			     			
	     			</tbody>
	     		</table>
     		</form>
     		
	     </div>
</div>

</div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>

<script type="text/javascript" >
var addNewRecord = false; //新增记录？
var editRecord = false;   //编辑记录？？

var thisUiConfig = {
	main : window.parent.main,	
	ctx : "${ctx}",
	url : "activity",
	id : "#activityId",    //表单中主键字段对应的控件的Id属性值
	idName:"activity",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		return $(thisUiConfig.id).val();
	}
};

$(function (){
	tabs.init();
	publicAttr.initCombobox();
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	initPartOrgLookup();
});

var tabs = {
		thisTabIndex : 0,
		init : function (){
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
			$(thisUiConfig.mainTab).tabs({
				//切换标签时调用
				onSelect : function(title, index) {
					tabs.thisTabIndex = index;
					if (index == 0) { // 选中列表标签
						setFormItemDisabled(thisUiConfig.baseForm, true, null);
						setFormItemDisabled(thisUiConfig.queryForm, false, null);
						$(thisUiConfig.baseForm).form("clear");
						
						if ($(thisUiConfig.tableList).datagrid("getSelections").length > 0) {
							$(thisUiConfig.mainTab).tabs("enableTab", 1);
						} else {
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
						}
						editRecord = false;
						enableButtons();
					} else if (index == 1) { // 选中详细标签
				    	tabs.initDetailsTab();
					}
				}
			});
		},
		initDetailsTab : function(){
			if (tableInfo.thisRow) {
				$(thisUiConfig.baseForm).form("load", tableInfo.thisRow);
				$("#meshId").lookup("setTxt", publicAttr.getOrgName(tableInfo.thisRow.meshId));
				$("#partyOrg").lookup("setTxt", tableInfo.thisRow.partyOrganizationName);
			}else{
				if(addNewRecord){
					$("input[name='meshId']").val(main.currentUserMesh.meshId);
					$("#meshId").lookup("setTxt", main.currentUserMesh.meshName);	
				}
			}
			
			if(! editRecord){
				setFormItemDisabled(thisUiConfig.baseForm, true, null);
			}else{
				setFormItemDisabled(thisUiConfig.baseForm, false, null);
				enableButtons();
			}
		}
};


//基础信息初始化
var baseInfo =  {
	saveUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/save",
	getUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/edit",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/softDelList",
	searchUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/search",
	init : function (){
		$(thisUiConfig.mainTab).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
			var id ="#" + $(this).attr("id");
			setFormItemDisabled(id,true,null);
		});
		setFormItemDisabled(thisUiConfig.queryForm, false, null);
	},
	
	//保存基础数据时调用此方法
	save : function (){
		if($(thisUiConfig.baseForm).form("validate")){
			var data = getFormData(thisUiConfig.baseForm);
 			$.messager.progress();
			$.ajax({
				type : "POST",
				url : baseInfo.saveUrl,
				data : data
			}).done(function(result) {
				$(thisUiConfig.baseForm).form("load",result.row);
				$.messager.progress("close");
				tableInfo.refresh();       //刷新列表
				$.messager.show({
					title : "温馨提示",
					msg : "操作成功",
					timeout : 2500,
					showType : "slide"
				});
				
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				alert(errorThrown+"           "+textStatus+"       "+jqXHR);
				$.messager.alert("温馨提示", "保存出错！", "error");
			}); 
		}
	},
	
	//点击新增基础数据时调用此方法
	add : function (){
		addNewRecord = true;
		editRecord = true;
		tableInfo.thisRow = "";
		tableInfo.thisRowIndex = -1;
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$(thisUiConfig.tableList).datagrid("unselectAll");
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);	
		}else{
			tabs.initDetailsTab();
		}
		setFormItemDisabled(thisUiConfig.baseForm, false, "code");
	},
	
	del:function (){
		if (! tableInfo.thisRow) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的活动记录?",
			function(r) {
				if (r) {
					$.ajax({
							url : baseInfo.delUrl,
							data:{
								ids: tableInfo.thisRow.activityId
							},
							type: "POST"
					}).done(function(data) {
						$.messager.progress("close");
						if (data.resultCode == "0") {
							$(thisUiConfig.baseForm).form("clear");
							if(tabs.thisTabIndex == 1){
								$(thisUiConfig.mainTab).tabs("select", 0);
							}
							$(thisUiConfig.mainTab).tabs("disableTab", 1);	
							tableInfo.thisRow = "";
							tableInfo.thisRowIndex = -1;
							
							// 重新获取数据
							tableInfo.refresh();
							$.messager.show({
								title : "温馨提示",
								msg : "操作成功",
								timeout : 2500,
								showType : "slide"
							});
						} else {
							$.messager.show({
								title : "温馨提示",
								msg : data.resultMsg,
								timeout : 2500,
								showType : "slide"
							});
						}
						}).fail(function() {
							$.messager.progress("close");
							$.messager.alert("温馨提示", "删除出错！", "error");
						});
				 }
			});
		
	},
	
	edit:function (){
		editRecord = true;
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);
		}else{
			tabs.initDetailsTab();
		}
	}
};

//列表加载
var tableInfo =  {
	thisRow : "",      //选中行的内容
	thisRowIndex : 0,  //选中行的下标	
	//初始化列表
	init : function (){
	
		$(thisUiConfig.tableList).datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			queryParams:  getFormData("#queryForm"),
			url : baseInfo.searchUrl,
			columns : [ [ {
				field : "activityId",
				hidden:true,
			},{
				field:"partyOrganizationName",
				title:"党组织名称",
				width:200,
				halign:'center',
				align:'left'
			},{
				 field:"activityName",
				 title:"活动名称",
				 width:150,
				 halign:'center',
				 align:'left'
			},{
				field:"address",
				title:"活动地点",
				width:240,
				halign:'center',
				align:'left'
			},{
				field:"activityTime",
				title:"活动时间",
				width:140,
				halign:'center',
				align:'left'
			},{
				field:"zhuchiren",
				title:"主持人",
				width:140,
				halign:'center',
				align:'left'
			}]],
			
			onSelect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onUnselect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onLoadSuccess : function(data) {
				$(thisUiConfig.tableList).datagrid("unselectAll");
				if(tableInfo.thisRow){
					$(thisUiConfig.tableList).datagrid("selectRow", tableInfo.thisRowIndex);
				}else{
					enableButtons();
				}
				
				//新增记录时   选中第一行   将是否为新增记录标记设为false
				if(addNewRecord){
					addNewRecord = false;
					$(thisUiConfig.tableList).datagrid("selectRow", 0);
				}
			}
			
		});
		
		// 设置分页显示形式
		$(thisUiConfig.tableList).datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	onSelectOrUnselect : function(rowIndex, rowData){
		var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
		if(len == 1){
			tableInfo.thisRow = $(thisUiConfig.tableList).datagrid("getSelected");
			tableInfo.thisRowIndex = $(thisUiConfig.tableList).datagrid("getRowIndex", tableInfo.thisRow);
			
			if(tabs.thisTabIndex == 1){
				tabs.initDetailsTab();
			}else{
				$(thisUiConfig.mainTab).tabs("enableTab", 1);
			}
		}
		enableButtons();
	},
	query : function (){
		tableInfo.thisRow = "";
		tableInfo.thisRowIndex = -1;
		$(thisUiConfig.mainTab).tabs("disableTab", 1);
		var param = getFormData("#queryForm");
		$(thisUiConfig.tableList).datagrid("load", param);
	},
	refresh: function(){
		var param = getFormData("#queryForm");
		$(thisUiConfig.tableList).datagrid("load", param);
	}
};


function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	
	//新增记录时 
	if(addNewRecord){
		$("#save").linkbutton("enable");
	}else{
		
		//编辑记录时
		if(editRecord){
			$("#add, #save, #del").linkbutton("enable");
		}
		
		//其他时候  多数是选中表格中一行记录时
		else if(tableInfo.thisRow){
			$("#add, #edit, #del").linkbutton("enable");
		}
		else{
			$("#add").linkbutton("enable");
		}
	}
}



var orgLookup;
function orgLookup() {
	orgLookup = $("#meshId").lookup({
		title : "选择党组织",
		url : thisUiConfig.ctx + "/mesh/selectmesh",
		width : 350,
		height : 500,
		required : true,
		valueField: 'meshId',
		textField: 'meshName',
		contentWindow: {
			params: {
				singleSelect : true,
				checkbox : false,
			},
			actions: {
				confirm: function(data) {
					if (data) {
						$("input[name='meshId']").val(data.meshId);						
						$("#meshId").lookup("setTxt", data.meshName);
					}
					main.closeDialog();
				},
				cancel : function() {
					main.closeDialog();
				}
			}
		}
	});
}

var partOrgLookup;
function initPartOrgLookup() {
	partOrgLookup = $("#partyOrg").lookup({
		title : "选择党组织",
		url : thisUiConfig.ctx + "/partyorg/selectpartorg",
		width : 680,
		height : 435,
		required : true,
		contentWindow: {
			params: {
				singleSelect : true,
				checkbox : false,
			},
			actions: {
				confirm: function(data) {
					if (data) {
						$("#partyId").val(data[0].organizationId);						
						$("#partyOrg").lookup("setTxt", data[0].organizationName);
					}
					main.closeDialog();
				},
				cancel : function() {
					$("#partyId").val("");						
					$("#partyOrg").lookup("setTxt", "");
					main.closeDialog();
				}
			}
		}
	});
}
</script>

</body>
</html>