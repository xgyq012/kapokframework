<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>

<title>固定党日计划信息</title>
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
		    					<label class="form-label">年度</label>
		    					<input id="q_year_LIKE" name="q_year_LIKE"  class="easyui-validatebox form-control" 
		     						style="width:${comboboxWidth};height:${comboboxHeight}px"/>
		    				</td>
		    				<td class="form-cell-1">
		    					<label class="form-label">计划制定人</label>
		    					<input name="q_author_LIKE"  class="easyui-validatebox form-control" />
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
			    <fieldset>
	    	    <legend>基础信息 </legend>
	    		<input id="finalDangriId" name="finalDangriId" type="hidden" />
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
		     				<td class="form-cell-1">
			            		<label class="form-label">编号</label>
			            		<input class="easyui-validatebox form-control" id="planNumber" name="planNumber"/>
			          		</td>		          		
		     				<td class="form-cell-1" colspan="1">
		     					<label class="form-label">党组织名称</label>
		     					<input id="partyId" name="partyId" type="hidden">
		     					<input id="partyOrg" type="text" />
		     				</td>
		     				<td class="form-cell-1">
			            		<label class="form-label">年度</label>
			            		<input id="year" name="year" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px">
			          		</td>	
		     			</tr>
		     			
		     			<tr>
		     				<td class="form-cell-2" colspan="2">
		     					<label class="form-label">党日计划名称</label>
		     					<input id="planName" name="planName"  class="easyui-validatebox form-control" />
		     				</td>
		     				<td class="form-cell-1">
		     					<label class="form-label">计划制定人</label>
		     					<input class="easyui-validatebox form-control" id="author"  name="author" />
		     				</td>   	     				  
		     				<td class="form-cell-1" colspan="1">
		     					<label class="form-label">制定时间</label>
		     					<input class="easyui-datetimebox form-control" id="zhidinTime"  name="zhidinTime" required="required"
		     						data-options="formatter: formatterDate" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
		     				</td>   
		     			</tr>     			
		     			<tr>
		     				<td class="form-cell-4"  colspan="4">
		     					<label class="form-label">备注</label>
		     					<textarea rows="6" class="easyui-validatebox form-control"  name="remark"></textarea>
		     				</td>
		     			</tr>
	     			</tbody>
	     		</table>
     			</fieldset>
     			
     			
     			<div style="height:350px;margin:15px 15px 0 15px">
				    <div class="infoBtn detailBut" style="padding: 3px 0">
					    <a id="btAddDetails" href="#" onclick="detailsTable.addRow();" class="easyui-linkbutton toolbar g-button" ><i class="fa fa-plus"></i>新增</a>
					    <a id="btDelDetails" href="#" onclick="detailsTable.removeRow();" class="easyui-linkbutton toolbar g-button" ><i class="fa fa-trash-o"></i>删除</a>
			        </div>
				    <table id="dg_dangriMingxiList" class="easyui-datagrid" title="固定党日明细"
                                    data-options="
                                       	idField : 'dangriMingxiId',
										rownumbers : true,
										singleSelect : true,
										autoRowHeight : false,
										toolbar : '.detailBut'">
                                   <thead>
                                       <tr>
                                       	<th data-options="field:'dangriMingxiId',hidden:true">主键</th>
                                       	<th data-options="field:'createrId',hidden:true">创建人ID</th>
										<th data-options="field:'createTime',hidden:true">创建时间</th>
										<th data-options="field:'finalDangriId',hidden:true">主表id</th>
										<th data-options="field:'month',width:100,halign:'center',
											formatter : function(value,row){
	                                                   return value ? dicts.month[value].dictName : '';
	                                        },
											editor : {
	                                                   type : 'combobox',
	                                                   options : {
	                                                       valueField : 'dictCode',
	                                                       textField : 'dictName',
	                                                       url : '${ctx}/dict/getdict/month',
	                                                       panelHeight : 'auto',
	                                                       required:true
	                                                   }
	                                               }
											">月份</th>										
                                           <th data-options="field:'content',width:280 ,halign:'center',editor:{type:'validatebox',options:{required:true}}">党日内容</th>
                                           <th data-options="field:'canjiaren',width:240 ,halign:'center',editor:{type:'validatebox',options:{required:true}}" >参加人员</th>
										   <th data-options="field:'address',width:180 ,halign:'center',editor:{type:'validatebox',options:{required:true}}">地点</th>
                                           <th data-options="field:'zhuchiren',width:80,halign:'center',editor:{type:'validatebox',options:{required:true}}" >主持人</th>
                                           <th data-options="field:'remark',width:220,halign:'center',editor:'text'">备注</th>
                                       </tr>
                                   </thead>
                                </table>
		        </div>
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
//当前登入的用户的id
var userId = <shiro:principal property="userId"/>;
var userName = "<shiro:principal/>";

var thisUiConfig = {
	main : window.parent.main,	
	ctx : "${ctx}",
	url : "finaldangri",
	id : "#finaldangriId",    //表单中主键字段对应的控件的Id属性值
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	detailsTableId : "#dg_dangriMingxiList",
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
	detailsTable.resetTableConfig();
	initCombobox();
	setFormItemDisabled(thisUiConfig.baseForm, true, null);
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
						addNewRecord = false;
						enableButtons();
					} else if (index == 1) { // 选中详细标签
				    	tabs.initDetailsTab();
					}
				}
			});
		},
		initDetailsTab : function(){
			$(thisUiConfig.detailsTableId).datagrid("acceptChanges");
			$(thisUiConfig.detailsTableId).datagrid("unselectAll");
			if (tableInfo.thisRow) {
				$(thisUiConfig.baseForm).form("load", tableInfo.thisRow);
				$("#meshId").lookup("setTxt", publicAttr.getOrgName(tableInfo.thisRow.meshId));
				$("#partyOrg").lookup("setTxt", tableInfo.thisRow.partyOrganizationName);
				if(tableInfo.thisRow.dangriMingxiList){
					$(thisUiConfig.detailsTableId).datagrid("loadData", tableInfo.thisRow.dangriMingxiList);
				}
			}else{
				if(addNewRecord){
					$("input[name='meshId']").val(main.currentUserMesh.meshId);
					$("#meshId").lookup("setTxt", main.currentUserMesh.meshName);	
					
					$("#author").val(userName);
					$("#zhidinTime").datetimebox("setValue", new Date().formatDate("yyyy-MM-dd hh:mm"));
				}
			}
			enableButtons();
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
	        $(thisUiConfig.detailsTableId).datagrid("acceptChanges");
			$(thisUiConfig.detailsTableId).datagrid("unselectAll");
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
		detailsTable.editRowIndex = -1;
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$(thisUiConfig.detailsTableId).datagrid("loadData",{ total: 0, rows: [] });
		$(thisUiConfig.tableList).datagrid("unselectAll");
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);	
		}else{
			tabs.initDetailsTab();
		}
		setFormItemDisabled(thisUiConfig.baseForm, false, "planNumber");
		$.ajax({
			type:"post",
			url:"${ctx}/coderule/generateCode/PAFDN"
		}).done(function(number){
			$("#planNumber").val(number);
		});
	},
	
	del:function (){
		if (! tableInfo.thisRow) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的党组织固定党日计划?",
			function(r) {
				if (r) {
					$.ajax({
							url : baseInfo.delUrl,
							data:{
								ids: tableInfo.thisRow.finalDangriId
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
		setFormItemDisabled(thisUiConfig.baseForm, false, "planNumber");
	}
};

//列表加载
var tableInfo =  {
	thisRow : "",      //选中行的内容
	thisRowIndex : 0,  //选中行的下标	
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
			queryParams:  getFormData("#queryForm"),
			url : baseInfo.searchUrl,
			columns : [ [ {
				field : "finalDangriId",
				hidden:true,
			}, {
				field : "planNumber",
				title : "编号",
				width : 150,
				halign:'center',
				align:'left',
			},{
				field:"partyOrganizationName",
				title:"党组织名称",
				width:200,
				halign:'center',
				align:'left'
			},{
				 field:"year",
				 title:"年度",
				 width:150,
				 halign:'center',
				 align:'left'
			},{
				field:"planName",
				title:"党日计划名称",
				width:180,
				halign:'center',
				align:'left'
			},{
				field:"author",
				title:"计划制定人",
				width:120,
				halign:'center',
				align:'left'
			},{
				field:"zhidinTime",
				title:"制定时间",
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
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
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
	
	enableDetailsButton();
}
function enableDetailsButton(){
	if(editRecord){
		$("#btAddDetails").linkbutton("enable");
		if(detailsTable.selectRowIndex != -1){
			$("#btDelDetails").linkbutton("enable");
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

var detailsTable = {
		editRow: false,
		editRowIndex : -1,
		selectRowIndex : -1,
		resetTableConfig: function(){
			$(thisUiConfig.detailsTableId).datagrid({
				onLoadSuccess: function(){
					detailsTable.editRow = false;
					detailsTable.editRowIndex = -1;
					detailsTable.selectRowIndex = -1;
				},
				onDblClickRow :function(rowIndex, rowData){
					if(editRecord == false){
						return;
					}
					detailsTable.editRowIndex = rowIndex;
					detailsTable.editRow = true;
					$(thisUiConfig.detailsTableId).datagrid("acceptChanges");
					$(thisUiConfig.detailsTableId).datagrid("unselectAll");
					$(thisUiConfig.detailsTableId).datagrid("selectRow", rowIndex).datagrid("beginEdit", rowIndex);
					enableButtons();
				},
				onSelect: function(rowIndex, rowData){
					detailsTable.selectRowIndex = rowIndex;
					enableButtons();
				}
			});
		},
		addRow : function(){
		   $(thisUiConfig.detailsTableId).datagrid('insertRow',{index : 0,  row: []}); 
		},
		
		removeRow: function(){
		    var rows = $(thisUiConfig.detailsTableId).datagrid("getSelections");
		    var copyRows = [];
		    for (var j = 0; j < rows.length; j++) {
				copyRows.push(rows[j]);
			}
		    for(var i = 0;i < copyRows.length; i ++) {    
		        var index = $(thisUiConfig.detailsTableId).datagrid("getRowIndex", copyRows[i]);
		        $(thisUiConfig.detailsTableId).datagrid("deleteRow", index); 
			}
		}
		
};

//日期格式化  
function  formatterDate (date){
	return date.formatDate("yyyy-MM-dd hh:mm");
}

var dict = main.dict;
var dicts = dict.dicts;
var comboboxConfig = {
		valueField: 'dictCode',
	    textField: 'dictName',
	    editable: false,
		panelHeight: 'auto',
		disabled : true
	};
function initCombobox(){
	comboboxConfig.data = createComboboxData();
	console.log(comboboxConfig.data);
	$('#year').combobox(comboboxConfig);
	comboboxConfig.disabled = false;
	$('#q_year_LIKE').combobox(comboboxConfig);
}

function createComboboxData(){
	var mothArray = new Array();
	var moth = (new Date().formatDate("yyyy")) - 1;
	for(var i = moth; i < moth + 5; i ++){
		var mothData = new Object();
		mothData.dictCode = i;
		mothData.dictName = i;
		mothArray[mothArray.length] = mothData;
	}
	return mothArray;
}

</script>

</body>
</html>