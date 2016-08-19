<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>

<title>消息公告</title>
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
	    <a id="send" onclick="baseInfo.send();"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-level-up"></i>发送</a>
	    <a id="del" onclick="baseInfo.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	</div>
	
	<div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
	    <div title="列表" style="position:relative;">
		    <div class="query-area">
			    <form id="queryForm" method="post">
				    <table class="g-form" class="formCls" cellpadding="0" cellspacing="0">
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">标题</label>
	            				<input class="easyui-validatebox form-control" name="q_title_LIKE"  />
	            			</td>
		    				<td class="form-cell-1">
		    					<label class="form-label">公告类型</label>
		    					<input id="q_noticeClass_EQ" name="q_noticeClass_EQ"  class="easyui-combobox form-control"
		    							style="width:${comboboxWidth};height:${comboboxHeight}px"/>
		    				</td>
		    				<td class="form-cell-1">
		    					<label class="form-label">发送状态</label>
		    					<input id="q_sendStatus_EQ" name="q_sendStatus_EQ"  class="easyui-combobox form-control"
		    							style="width:${comboboxWidth};height:${comboboxHeight}px"/>
		    				</td>		    				
							<td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
							</td>
 							<td class="form-cell-1"></td>
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
	    		<!-- 隐藏属性 -->
	    		<input id="noticeId" name="noticeId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				<input id="delSign" name="delSign" type="hidden" />
	    		<table class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
	     				<td class="form-cell-1">
	     					<label class="form-label">公告类型</label>
	     					<input id="noticeClass" name="noticeClass"  class="easyui-combobox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	     				</td>
	     				<td class="form-cell-1">
	     					<label class="form-label">紧急程度</label>
	     					<input id="noticeLevel" name="noticeLevel"  class="easyui-combobox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	     				</td>	
	     				<td class="form-cell-1" colspan="1">
	     					<label class="form-label">发送状态</label>
	     					<input id="sendStatus" name="sendStatus"  class="easyui-combobox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	     				</td>	
	     				<td class="form-cell-1">
	     				   <label class="form-label">发送时间</label>
	     				   <input type="text" name="sendTime" disabled="disabled" data-options="required:false" class="easyui-datetimebox form-control" 
	     				   		data-options="formatter: formatterDate" style="width:${comboboxWidth};height:${comboboxHeight}px">
		     			</td>     							     	
	     			</tr>	    			
					<tr>
	     				<td class="form-cell-3" colspan="3">
	     					<label class="form-label">标题</label>
	     					<input class="easyui-validatebox form-control" name="title" data-options="required:true"  />
	     				</td>
	     			</tr>	    			
					<tr>
	     				<td class="form-cell-4" colspan="4" style="padding: 0px;">
	     					<label class="form-label" style="float: left;">公告内容</label>
	     					<div style="float: left;">
	     						<textarea name="body" id="body" class="easyui-validatebox form-control"
									style="height:360px;"></textarea>
	     					</div>
	     				</td>
	     			</tr>	     			
	     		</table>
	     		
	     		
	     		<div style="height:350px;margin:15px 15px 0 15px">
	   			<div id="buttonbar" class="infoBtn targetBut" style="padding: 4px 0;">
					<a id="addUserBut" onclick="targetTable.addUser();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>个人</a>
					<a id="addMeshBut" onclick="targetTable.addMesh();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>网格</a>
					<a id="addAllBut" onclick="targetTable.addAll();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>所有人</a>
					<a id="delTargetUserBut" onclick="targetTable.delRow();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
					<div id="addFile"></div>
		      	</div>
		       		<table id="dg_targetList" class="easyui-datagrid" title="接收对象表"
                                    data-options="
                                       	idField : 'targetId',
										rownumbers : true,
										singleSelect : true,
										autoRowHeight : false,
										toolbar : '#buttonbar'">
                                   <thead>
                                       <tr>
                                           <th data-options="field:'targetId',hidden:true">主键</th>
                                           <th data-options="field:'createrId',hidden:true">创建人ID</th>
										   <th data-options="field:'createTime',hidden:true">创建时间</th>
										   <th data-options="field:'noticeId',hidden:true">主表id</th>									
                                           <th data-options="field:'targetType',width:150">接收类型</th>
                                           <th data-options="field:'targetMeshId', hidden:true">网格id</th>
                                           <th data-options="field:'targetMeshName',width:240">网格名称</th>
                                           <th data-options="field:'targetUserId', hidden:true">用户id</th>
                                           <th data-options="field:'targetUserName',width:240">用户名称</th>
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
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${kindeditor }/kindeditor.js"></script>
<script type="text/javascript" >
var addNewRecord = false; //新增记录？
var editRecord = false;
var thisUiConfig = {
	main : window.parent.main,	
	ctx : "${ctx}",
	url : "notice",
	id : "#noticeId",    //表单中主键字段对应的控件的Id属性值
	idName:"noticeId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var comId = $(thisUiConfig.id).val();
		return comId;
	}
};
var keWidth = 
$(function (){
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	targetTable.init();
	initCombobox();
	editorInit();
	keWidth = $("#mainTab").width() - 40 - 90 - 8 - 14;
});
/**
 * 初始化编辑器
 */
 function editorInit() {
		KindEditor.ready(function(k) {
			bodyContentEditor = k.create("#body", {
				width: keWidth,
				minWidth: keWidth,
				maxWidth: keWidth,
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview',
						'print', 'cut', 'copy', 'paste', 'plainpaste',
						'wordpaste', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'justifyfull', 'insertorderedlist',
						'insertunorderedlist', 'indent', 'outdent',
						'subscript', 'superscript', 'clearhtml', 'quickformat',
						'selectall', '|', 'fullscreen', '/', 'formatblock',
						'fontname', 'fontsize', '|', 'forecolor',
						'hilitecolor', 'bold', 'italic', 'underline',
						'strikethrough', 'lineheight', 'removeformat', '|',
						'table', 'hr', 'emoticons', 'pagebreak', 'anchor',
						'link', 'unlink', '|' ]
			});
			bodyContentEditor.readonly(true);
		});
	}
	
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
						if (tableInfo.thisRow) {
							$(thisUiConfig.baseForm).form("load", tableInfo.thisRow);
							$("#dg_targetList").datagrid("loadData", tableInfo.thisRow.targetList);
							bodyContentEditor.html(tableInfo.thisRow.body);
						}
						enableButtons([ "add", "del", "edit"]);
					}
				}
			});
		}
};

//基础信息初始化
var baseInfo =  {
	saveUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/save",
	getUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/edit",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/softDelList",
	search : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/search",
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
	save : function (data){
		if(! data){
			if(! $(thisUiConfig.baseForm).form("validate")){
				return false;
			}
			
	        $(thisUiConfig.detailsTableId).datagrid("acceptChanges");
			$(thisUiConfig.detailsTableId).datagrid("unselectAll");
			
			bodyContentEditor.sync();
		    data = getFormData(thisUiConfig.baseForm);
		}
		
		$.messager.progress();
		$.ajax({
			type : "POST",
			url : baseInfo.saveUrl,
			data : data
		}).done(function(result) {
			$(thisUiConfig.baseForm).form("load",result.row);
			$.messager.progress("close");
			tableInfo.thisRow = result.row;
			tableInfo.query();       //刷新列表
			
			//新增记录时   选中第一行   将是否为新增记录标记设为false
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
		$("#dg_targetList").datagrid("loadData",{ total: 0, rows: [] });
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);	
		}
		setFormItemDisabled(thisUiConfig.baseForm, false, "#sendStatus");
		enableButtons(["save"]);
		$.ajax({
			type:"post",
			url:"${ctx}/coderule/generateCode/SZSS"
		}).done(function(number){
			$("#facilityCode").val(number);
		});
		$("input[name='sendStatus']").val(0);
		bodyContentEditor.html("");
		bodyContentEditor.readonly(false);
	},
	
	del:function (){
		if (! tableInfo.thisRow) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的公告记录?",
			function(r) {
				if (r) {
					$.ajax({
							url : baseInfo.delUrl,
							type: "POST",
							data:{
								ids:tableInfo.thisRow.noticeId
							}
					}).done(function(data) {
						$.messager.progress("close");
						if (data.resultCode == "0") {
							$(thisUiConfig.baseForm).form("clear");
							$(thisUiConfig.mainTab).tabs("select", 0);
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
							
							tableInfo.thisRow = "";
							tableInfo.thisRowIndex = -1;
							// 重新获取数据
							tableInfo.query();
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
		bodyContentEditor.readonly(false);
		setFormItemDisabled(thisUiConfig.baseForm, false, "#sendStatus");
	},
	send:function(){
		if(tabs.thisTabIndex == 0){
			$("#baseForm").form("load", tableInfo.thisRow);
		}
		$.messager.confirm("温馨提示！", "消息发送后将不能继续修改！确定要发送?", function(r){
			  if(r){
				  $("input[name='sendStatus']").val(1);
				  baseInfo.save(getFormData($("#baseForm")));
			  }
		  });
	}
};

//列表加载
var tableInfo =  {
	thisRow : "",      //选中行的内容
	thisRowIndex : 0,  //选中行的下标	
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			idField: "noticeId",
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			queryParams:  getFormData("#queryForm"),
			url : thisUiConfig.ctx + "/"+ thisUiConfig.url +"/search",
			columns : [ [ {
				field : "noticeId",
				hidden:true
			}, {
				field : "title",
				halign:'center',
				title : "标题",
				width : 300
			},{
				field:"noticeClass",
				title:"公告类型",
				halign:'center',
				width:180,
				formatter:function(value, row, index){
					if(value == 0){
						return "消息公告";
					}else if(value == 1){
						return "任务";
					}
				}
			},{
				 field:"noticeLevel",
				 title:"紧急程度",
				 halign:'center',
				 width:120,
				 formatter : function(value, row, index){
					 if(value == 1){
						 return "特急";
					 }else if(value == 2){
						 return "必办";
					 }else if(value == 3){
						 return "一般";
					 }
				 }
				 
			},{
				field:"sendTime",
				halign:'center',
				title:"发送时间",
				width:180
			},{
				 field:"sendStatus",
				 title:"发送状态",
				 halign:'center',
				 width:100,
				 formatter: function(value, row, index){
					 if(value == 0){
						 return "未发送";
					 }else if(value == 1){
						 return "已发送";
					 }
				 }
			}]],
			
			onSelect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onUnselect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onLoadSuccess : function(data) {
				$(thisUiConfig.tableList).datagrid("unselectAll");
				
				if(tableInfo.thisRow && addNewRecord){
					tableInfo.thisRowIndex = $(thisUiConfig.tableList).datagrid("getRowIndex", tableInfo.thisRow.noticeId);
				}
				
				//新增记录时   选中第一行   将是否为新增记录标记设为false
				if(addNewRecord){
					addNewRecord = false;
					$(thisUiConfig.tableList).datagrid("selectRow", tableInfo.thisRowIndex);
				}else{
					if(editRecord){
						enableButtons();
					}
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
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			enableButtons([ "add", "del", "edit"]);
			if(editRecord){
				enableButtons([ "add", "del", "save"]);
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
		var param = getFormData("#queryForm");
		enableButtons([ "add" ]);
		$(thisUiConfig.tableList).datagrid("load", param);
	}
};


function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	
	if(addNewRecord){
		$("#save").linkbutton("enable");
	}else if(editRecord){
		$("#save, #del").linkbutton("enable");
	}else{
		$("#add").linkbutton("enable");
		if(tableInfo.thisRow && tableInfo.thisRow.sendStatus == 0){
			$("#add, #edit, #del").linkbutton("enable");
		}
	}
	
	//设置发送按钮是否可用
	if(tableInfo.thisRow && tableInfo.thisRow.sendStatus == 0){	//修改记录时 可以选择接收人
		var targetRows = tableInfo.thisRow.targetList;

		//已经选择了接受人群，发送按钮可用
		if(targetRows && targetRows.length != 0){
			$("#send").linkbutton("enable");
		}
	}
	enableDetailsButtons();
}
function enableDetailsButtons(){
	if(editRecord){
		$("#addUserBut, #addMeshBut, #addAllBut").linkbutton("enable");
		var targetRows = tableInfo.thisRow.targetList;
		if(targetRows){
			for(var i = 0; i < targetRows.length; i ++){
				if(targetRows[i].targetType == "所有"){
					$("#addUserBut, #addMeshBut,#addAllBut").linkbutton("disable");
					break;
				}
			}
		}
		
		if(targetTable.thisRowIndex != -1){
			$("#delTargetUserBut").linkbutton("enable");
		}
	}
}


function initCombobox(){
	var dict = main.dict;
	var dicts = dict.dicts;
	var comboboxConfig = {
		valueField: 'dictCode',
	    textField: 'dictName',
	    editable: false,
		panelHeight: 'auto',
		disabled : false
	};
	comboboxConfig.data = dicts.noticeClass.list;
	$('#q_noticeClass_EQ').combobox(comboboxConfig);
	comboboxConfig.required=true;
	$('#noticeClass').combobox(comboboxConfig);	
	
	comboboxConfig.required=false;
	comboboxConfig.data = dicts.sendStatus.list;
	$('#q_sendStatus_EQ').combobox(comboboxConfig);
	
	comboboxConfig.disabled = true;
	$('#sendStatus').combobox(comboboxConfig);
	comboboxConfig.data = dicts.noticeLevel.list;
	
	comboboxConfig.required=true;
	$('#noticeLevel').combobox(comboboxConfig);
}

var targetTable = {
		thisRowIndex: -1,
		init: function(){
			$("#dg_targetList").datagrid({
				onSelect : function(rowIndex, rowData) {
					targetTable.onSelectOrUnselect(rowIndex, rowData);
				},
				onUnselect : function(rowIndex, rowData) {
					targetTable.onSelectOrUnselect(rowIndex, rowData);
				},
				onLoadSuccess : function(data) {
					targetTable.thisRowIndex = -1;
				}
				
			});
			
			// 设置分页显示形式
			$("#dg_targetList").datagrid("getPager").pagination({
				layout : defaultPageLayout
			});
		},
		onSelectOrUnselect : function(rowIndex, rowData){
			var len = $("#dg_targetList").datagrid("getSelections").length;
			if(len == 1){
				targetTable.thisRowIndex = rowIndex;
				enableButtons();
			}
		},
		addMesh: function(){
			main.dialog({
				title : "指定接受消息公告的机构",
				url : thisUiConfig.ctx + "/mesh/selectmesh",
				width : 350,
				height : 500,
				required : false,
				contentWindow: {
					params: {
						singleSelect : false,
						checkbox : true
					},
					actions: {
						confirm : function(data) {
							var rowIndex = $("#dg_targetList").datagrid('getRowIndex', data.meshId);
	            			var row = {};
	            			if (rowIndex == -1) {
	            				row.noticeId = thisUiConfig.getId();
	            				row.targetType = "网格";
	            				row.targetMeshId = data.meshId;
	            				row.targetMeshName = data.meshName;
	            				$("#dg_targetList").datagrid('insertRow',{index:0,row: row});
	            			}
							main.closeDialog();
						},
						cancel: function() {
							main.closeDialog();
						}
					}
				}
			});
			
		},
		addUser: function(){
			main.dialog({
				id: 'userframe',
				title: "选择用户",
				url: '${ctx}/user/selectuser',
				width: 610,
				height: 425,
				cache: false,
				modal: true,
				contentWindow: {
					params: {
						singleSelect : false,
						checkbox : true
					},
					actions: {
						confirm : function(data) {
							for(var i = 0; i < data.length; i++){
		            			var rowIndex = $("#dg_targetList").datagrid('getRowIndex', data[i].userId);
		            			var row = {};
		            			if (rowIndex == -1) {
		            				row.noticeId = thisUiConfig.getId();
		            				row.targetType = "个人";
		            				row.targetUserId = data[i].userId;
		            				row.targetUserName = data[i].realname;
		            				$("#dg_targetList").datagrid('insertRow',{index:0,row: row});
		            			}
		            		}
							main.closeDialog();
						},
						cancel: function() {
							main.closeDialog();
						}
					}
				}
			});
		},
		addAll: function(){
			var row = {};
			row.noticeId = thisUiConfig.getId();
			row.targetType = "所有";
			$("#dg_targetList").datagrid('insertRow',{index:0,row: row});
			enableButtons();
		},
		delRow: function(){
			var row = $("#dg_targetList").datagrid("getSelected");
			var rowIndex = $("#dg_targetList").datagrid('getRowIndex', row);
			$("#dg_targetList").datagrid('deleteRow', rowIndex);
		}
};

//日期格式化  
function  formatterDate (date){
	return date.formatDate("yyyy-MM-dd hh:mm:ss");
}

</script>

</body>
</html>