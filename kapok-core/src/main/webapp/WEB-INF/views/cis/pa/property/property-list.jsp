<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>

<title>党组织财产信息</title>
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
		    					<label class="form-label">登记人</label>
		    					<input name="q_dengjiren.realname_LIKE"  class="easyui-validatebox form-control" />
		    				</td>
		    				<td class="form-cell-1">
		    					<label class="form-label">负责人</label>
		    					<input name="q_fuzeren_LIKE"  class="easyui-validatebox form-control" />
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
	    		<input id="propertyId" name="propertyId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
	    		<table class="g-form" cellpadding="0" cellspacing="0" >
	    			<tbody>
	     			<tr>
	     				<td class="form-cell-1">
		            		<label class="form-label">所属机构</label>
		            		<input id="meshId" name="meshId"  type="hidden" />
		          		</td>
	     				<td class="form-cell-1">
		            		<label class="form-label">编号</label>
		            		<input class="easyui-validatebox form-control" id="code" name="code"/>
		          		</td>		          		
	     				<td class="form-cell-2" colspan="2">
	     					<label class="form-label">党组织名称</label>
	     					<input id="partyId" name="partyId" type="hidden">
	     					<input id="partyOrg" type="text" />
	     				</td>
	     			</tr>
	     			
	     			<tr>
	     				<td class="form-cell-1">
	     					<label class="form-label">负责人</label>
	     					<input id="fuzeren" name="fuzeren"  class="easyui-validatebox form-control" />
	     				</td>
	     				<td class="form-cell-1">
	     					<label class="form-label">负责人电话</label>
	     					<input class="easyui-validatebox form-control" id="fuzerenPhone"  name="fuzerenPhone" />
	     				</td>   
	     				<td class="form-cell-1">
	     					<label class="form-label">登记人</label>
	     					<input id="dengjirenId" name="dengjirenId" type="hidden">
	     					<input id="dengjirenName" type="text"/>
	     				</td> 	     				  
	     				<td class="form-cell-1" colspan="1">
	     					<label class="form-label">登记日期</label>
	     					<input class="easyui-datebox form-control" id="dengjiDate"  name="dengjiDate" required="required"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	     				</td>   
	     			</tr>
	     			<tr>
	     				<td class="form-cell-4"  colspan="4">
	     					<label class="form-label">增减说明</label>
	     					<textarea rows="6" class="easyui-validatebox form-control"  name="zengjianRemark"></textarea>
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
					    <a id="btUpload" href="#" onclick="upload();" class="easyui-linkbutton toolbar g-button" ><i class="fa fa-arrow-circle-up"></i>上传图片</a>
			        </div>
				    <table id="dg_proDetList" class="easyui-datagrid" title="财产明细表"
                                    data-options="
                                       	idField : 'propertyDetailsId',
										rownumbers : true,
										singleSelect : true,
										autoRowHeight : false,
										toolbar : '.detailBut'">
                                   <thead>
                                       <tr>
                                       	<th data-options="field:'propertyDetailsId',hidden:true">主键</th>
                                       	<th data-options="field:'createrId',hidden:true">创建人ID</th>
										<th data-options="field:'createTime',hidden:true">创建时间</th>
										<th data-options="field:'propertyId',hidden:true">主表id</th>
                                       	<th data-options="field:'propertyCode',width:100 ,halign:'center',editor:{type:'validatebox',options:{required:true}}">财产编号</th>
										<th data-options="field:'propertyType',width:100,halign:'center',
										formatter : function(value,row){
                                                    return value == 'dc' ? '动产': value == 'bdc' ? '不动产' : value == 'zscq' ? '知识产权' : '';
                                        },
										editor : {
                                                   type : 'combobox',
                                                   options : {
                                                       valueField : 'dictCode',
                                                       textField : 'dictName',
                                                       url : '${ctx}/dict/getdict/propertyType',
                                                       panelHeight : 'auto'
                                                   }
                                               }
										">财产类型</th>
										
										<th data-options="field:'propertyName',width:180 ,halign:'center',editor:{type:'validatebox',options:{required:true}}">财产名称</th>
                                           <th data-options="field:'propertyNumber',width:60 ,halign:'center',editor:{type:'validatebox',options:{required:true}}" >数量</th>
                                           <th data-options="field:'propertyCost',width:80,halign:'center',editor:{type:'validatebox',options:{required:true}}" >价值</th>
                                           <th data-options="field:'caigouDate',width:200,halign:'center',editor:{type:'datebox'}">采购日期</th>
                                           <th data-options="field:'remark',width:200,halign:'center',editor:'text'">备注</th>
                                           <th data-options="field:'imageId',hidden:true,editor:{type:'textbox',options:{required:false}}">照片id</th>
                                           <th data-options="field:'upload',width:120,halign:'center',">图片</th>
                                       </tr>
                                   </thead>
                                </table>
		        </div>
     		</form>
     		
	  		<div style="display: none;" id="addPhoto"></div>
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
	url : "property",
	id : "#propertyId",    //表单中主键字段对应的控件的Id属性值
	idName:"property",
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
	enableButtons(['add']);
	publicAttr.initCombobox();
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	initPartOrgLookup();
	initDengjirenLookup();
	doc.init();
	detailsTable.resetTableConfig();
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
							enableButtons([ "add", "del", "edit" ]);
						} else {
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
						}
						editRecord = false;
					} else if (index == 1) { // 选中详细标签
				    	tabs.initDetailsTab();
				    	enableButtons([ "add", "del", "edit"]);
					}
				}
			});
		},
		initDetailsTab : function(){
			$("#dg_proDetList").datagrid("acceptChanges");
			$("#dg_proDetList").datagrid("unselectAll");
			if (tableInfo.thisRow) {
				$(thisUiConfig.baseForm).form("load", tableInfo.thisRow);
				console.log(main.currentUserMesh);
				$("#meshId").lookup("setTxt", publicAttr.getOrgName(tableInfo.thisRow.meshId));
				$("#partyOrg").lookup("setTxt", tableInfo.thisRow.partyOrganizationName);
				$("#dengjirenName").lookup("setTxt", tableInfo.thisRow.dengjirenName);
				if(tableInfo.thisRow.proDetList){
					$("#dg_proDetList").datagrid("loadData", tableInfo.thisRow.proDetList);
				}
			}else{
				if(addNewRecord){
					$("input[name='meshId']").val(main.currentUserMesh.meshId);
					$("#meshId").lookup("setTxt", main.currentUserMesh.meshName);	
					
					$("#dengjirenId").val(userId);						
					$("#dengjirenName").lookup("setTxt", userName);
					
					$("#dengjiDate").datebox("setValue", new Date().formatDate("yyyy-MM-dd"));
				}
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
	        $("#dg_proDetList").datagrid("acceptChanges");
			$("#dg_proDetList").datagrid("unselectAll");
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
		$("#dg_proDetList").datagrid("loadData",{ total: 0, rows: [] });
		$(thisUiConfig.tableList).datagrid("unselectAll");
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);	
		}else{
			tabs.initDetailsTab();
		}
		setFormItemDisabled(thisUiConfig.baseForm, false, "code");
		enableButtons(["save"]);
		$.ajax({
			type:"post",
			url:"${ctx}/coderule/generateCode/PAPR"
		}).done(function(number){
			$("#code").val(number);
		});
	},
	
	del:function (){
		if (! tableInfo.thisRow) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的财产记录?",
			function(r) {
				if (r) {
					$.ajax({
							url : baseInfo.delUrl,
							data:{
								ids: tableInfo.thisRow.propertyId
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
							
							enableButtons([ "add" ]);
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
		}
		enableButtons([ "add", "del", "save"]);
		setFormItemDisabled(thisUiConfig.baseForm, false, "#lon,#lat,#facilityCode");
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
				field : "propertyId",
				hidden:true,
			}, {
				field : "code",
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
				 field:"dengjirenName",
				 title:"登记人",
				 width:150,
				 halign:'center',
				 align:'left'
			},{
				field:"fuzeren",
				title:"负责人",
				width:180,
				halign:'center',
				align:'left'
			},{
				field:"dengjiDate",
				title:"登记日期",
				width:240,
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
				}
				
				//新增记录时   选中第一行   将是否为新增记录标记设为false
				if(addNewRecord){
					$(thisUiConfig.tableList).datagrid("selectRow", 0);
					addNewRecord = false;
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
			
			if(editRecord){
				enableButtons([ "add", "del", "save"]);
			}else{
				enableButtons([ "add", "del", "edit"]);
			}
		}else if(len == 0){
			enableButtons([ "add"]);
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
		}else{
			enableButtons([ "add", "del"  ]);
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
		}
	},
	query : function (){
		tableInfo.thisRow = "";
		tableInfo.thisRowIndex = -1;
		enableButtons([ "add" ]);
		$(thisUiConfig.mainTab).tabs("disableTab", 1);
		var param = getFormData("#queryForm");
		$(thisUiConfig.tableList).datagrid("load", param);
	},
	refresh: function(){
		enableButtons([ "add" ]);
		var param = getFormData("#queryForm");
		$(thisUiConfig.tableList).datagrid("load", param);
	}
};


function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	
	// 设置按钮可用
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).linkbutton("enable");
	}
	
	if(editRecord){
		$("#btAddDetails").linkbutton("enable");
		if(detailsTable.selectRowIndex != -1){
			$("#btDelDetails").linkbutton("enable");
		}
		if(detailsTable.editRowIndex != -1){
			$("#btUpload").linkbutton("enable");
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


function initDengjirenLookup() {
	$("#dengjirenName").lookup({
		title : "选择登记人",
		url: '${ctx}/user/selectuser',
		width: 610,
		height: 425,
		required : false,
		contentWindow: {
			params: {
				singleSelect : true,
				checkbox : false,
			},
			actions: {
				confirm: function(data) {
					if (data) {
						$("#dengjirenId").val(data[0].userId);						
						$("#dengjirenName").lookup("setTxt", data[0].realname);
					}
					main.closeDialog();
				},
				cancel : function() {
					$("#dengjirenId").val("");						
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
		selectRowIndex : 0,
		resetTableConfig: function(){
			$("#dg_proDetList").datagrid({
				onLoadSuccess : function(data) {
					detailsTable.editRow = false;
					detailsTable.editRowIndex = -1;
					detailsTable.selectRowIndex = -1;
					createUploadBut();
				},
				onDblClickRow :function(rowIndex, rowData){
					if(editRecord == false){
						return;
					}
					detailsTable.editRowIndex = rowIndex;
					detailsTable.editRow = true;
					$("#dg_proDetList").datagrid("acceptChanges");
					$("#dg_proDetList").datagrid("unselectAll");
					$("#dg_proDetList").datagrid("selectRow", rowIndex).datagrid("beginEdit", rowIndex);
					createUploadBut();
					enableButtons([ "add", "del", "save"]);
				},
				onSelect: function(rowIndex, rowData){
					detailsTable.selectRowIndex = rowIndex;
					enableButtons([ "add", "del", "save"]);
				}
			});
		},
		addRow : function(){
		    $("#dg_proDetList").datagrid('insertRow',{index : 0,  row: []}); 
		    createUploadBut();
		},
		
		removeRow: function(){
		    var rows = $("#dg_proDetList").datagrid("getSelections");
		    var copyRows = [];
		    for (var j= 0; j < rows.length; j++) {
				copyRows.push(rows[j]);
			}
		    for(var i =0;i<copyRows.length;i++) {    
		        var index = $("#dg_proDetList").datagrid("getRowIndex", copyRows[i]);
		        $("#dg_proDetList").datagrid("deleteRow", index); 
			}
		}
		
};



var uploadBtn;
var doc = {
		init : function (){
	 		uploadBtn = $("#addPhoto").fileupload({
				url : thisUiConfig.ctx + "/doc/upload",
				upfile : "upfile",
				filetype : "jpg|jpeg|png",
				hidden : true,
				params : {
					directory : "photos"
				},
				onUploadComplete : function(result) {
					if (result.errcode) {
						$.messager.alert("温馨提示", result.errmsg, "info");
						return ;
					}
					var ed = $('#dg_proDetList').datagrid('getEditor', {index:detailsTable.editRowIndex,field:'imageId'});
					$(ed.target).textbox('setValue', result.docId);
				}
			});
		},
		
		//下载附件
		downDoc : function(docId){
			if (docId) {
				location.href = "${ctx}/doc/download/" + docId;
				
				//XXX 先发请求判断请求的资源是否可用
				$.ajax({
					url:"${ctx}/doc/download/" + docId,
					type: "post"
				}).done(function(resule){
					
				});
				
			} else {
				$.messager.show({
					title : "温馨提示",
					msg : "未上传文件",
					timeout : 2500,
					showType : "slide"
				});
			}
		}
};

function upload(v){
	if(editRecord && detailsTable.editRowIndex != -1){
		uploadBtn.fileupload('click');
	}
}


function downDoc(){
	var row = $('#dg_proDetList').datagrid('getSelected');
	if(row.propertyId){
		doc.downDoc(row.imageId);
	}
}

function createUploadBut(){
    $.each($("td[field='upload']"), function(i){
    	if(i != 0){
    		//var html = "<a class='showImage' href='#' onclick='downDoc(this); return false;'>查看</a>&nbsp;&nbsp;&nbsp;"
    		   //+"<a href='#' onclick='upload(this); return false;'>上传</a>";
    		   
       		var html = "<a class='showImage' href='#' onclick='downDoc(this); return false;'>查看</a>";   
    		$(this).find("div").empty().append(html);
    	}
    });
}

</script>

</body>
</html>