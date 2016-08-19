<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>避难场所信息</title>
	
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
	<div class="g-toolbar">
	    <a onclick="baseInfo.add(1);" id="add1" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增同级类型</a>
	    <a onclick="baseInfo.add(2);" id="add2"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增子类型</a>
	    <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	    <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	    <a id="del" onclick="baseInfo.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	</div>
	<div id="mainLayout" class="easyui-layout g-container">
	    <div data-options="region:'west',split:true,minWidth:250" style="width:250px; overflow: hidden;">
	        <table id="listGrid" class="easyui-treegrid" style="width:100%;height:100%"></table>
	    </div>
     
	     <!-- data-options="disabled:true" -->
        <div data-options="region:'center'">
	         <form id="baseForm"  action="">
	     	    <input type="hidden" id="yjsjlxId" name="yjsjlxId">
    		    <input id="createrId" name="createrId" type="hidden" />
			    <input id="createTime" name="createTime" type="hidden" />
			    <input type="hidden" name="parentId" id="parentId" />
	    		<table class="g-form" cellpadding="0" cellspacing="0">
	    		    <tr>
	    		        <td class="form-cell-1">
	     				   <label class="form-label">上级类型名称</label>
	     				   <input disabled="disabled" id="parentLeixingName" class="easyui-validatebox form-control"/>
	     				</td>
	     				<td class="form-cell-2" colspan="2">
	     				   <label class="form-label">类型名称</label>
	     				   <input name="leixingName" class="easyui-validatebox form-control" data-options="validType:'length[2,64]', required:'required'"  />
	     				</td>
	     				<td class="form-cell-1">
	     				   <label class="form-label">代表色</label>
	     				   <input id="daibiaoColor" name="color" class="easyui-combobox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px">
	     				</td>		     				
	     			</tr>     			
	     			<tr>
	     				<td class="form-cell-4" colspan="4">
	     				    <label class="form-label">应急事件类型描述</label>
	     				    <textarea name="leixingPs" rows="6" class="easyui-validatebox form-control"></textarea>
	     			</tr>
	     		</table>
   		     </form>
        </div>
     
    </div>
</div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" >
var thisUiConfig = {
	ctx : "${ctx}",
	url : "yjsjlx",
	id : "#yjsjlxId",    //表单中主键字段对应的控件的Id属性值
	idName:"yjsjlxId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	baseForm : "#baseForm",
	mainTab : "#mainTab",
	getId : function (){
		var householderId = $(thisUiConfig.id).val();
		return householderId;
	}
};
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
 	comboboxConfig.data = dicts.color.list;
	$("#daibiaoColor").combobox(comboboxConfig); 
}
$(function (){
	enableButtons(['add0']);
	initCombobox();
	tableInfo.init();
	$(thisUiConfig.tableList).datagrid("fitColumns");
	baseInfo.init();
	var bodyOuterHeight = $("body").outerHeight(true);
	var toolbarOuterHeight = $("#toolbar").outerHeight(true);
	$("#mainLayout").height(bodyOuterHeight - toolbarOuterHeight);
	$("#mainLayout").find(".panel-body").height(bodyOuterHeight - toolbarOuterHeight);
});

function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	// 设置按钮可用
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).linkbutton("enable");
	}
}

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
		
	},
	
	//保存基础数据时调用此方法
	save : function (){
		var data = getFormData("#baseForm");
		if($("#baseForm").form("validate")){
			$.messager.progress();
			$.ajax({
				type : "POST",
				url : baseInfo.saveUrl,
				data : data
			}).done(function(result) {
				$("#baseForm").form("load",result.row);
				$.messager.progress("close");
				enableButtons([ "add","save", "del"]);
				
				$(thisUiConfig.tableList).treegrid("load",{});
				$.messager.show({
					title : "温馨提示",
					msg : "操作成功",
					timeout : 2500,
					showType : "slide"
				});
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				$.messager.alert("温馨提示",errorThrown+ "保存出错！"+textStatus, "error");
			});
		}
	},
	
	//点击新增基础数据时调用此方法
	add : function (level){
		console.log("base add");
		//clear data
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		setParentId(level);
		setFormItemDisabled("#baseForm",false,null);
		$("#parentLeixingName").attr("disabled", "disabled");
		enableButtons(["save"]);
	},
	
	del:function (){
		var row = $(thisUiConfig.tableList).treegrid("getSelected");
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		
		$.messager.confirm("温馨提示", "确定删除选中的【"+ row.leixingName +"】记录?",
			function(r) {
				if (r) {
					$.ajax({
						url : baseInfo.delUrl,
						data: {
							ids : row.yjsjlxId
						},
						type: "POST"
					}).done(function(data) {
						$.messager.progress("close");
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$("#mainTab").tabs("select", 0);
							$("#mainTab").tabs("disableTab", 1);
							
							// 重新获取数据
							enableButtons([ "add" ]);
				            $(thisUiConfig.tableList).treegrid("load",{});
				            
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
		enableButtons([ "add1","add2", "del", "save"]);
		setFormItemDisabled("#baseForm", false, null);
		$("#parentLeixingName").attr("disabled", "disabled");
	}
};

//列表加载
var tableInfo =  {
	
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).treegrid({
			rownumbers : false,
			idField: 'yjsjlxId',
			treeField: 'leixingName',
			queryParams:  getFormData(thisUiConfig.queryForm),
			url : baseInfo.search,
			columns : [ [ {
				field : "yjsjlxId",
				hidden:true
			}, {
				frozen:"true",
				field : "leixingName",
				title : "应急事件列表",
				width : 1000
			}]],

			onSelect : function(rowIndex, rowData) {
				var row = $(thisUiConfig.tableList).treegrid("getSelected");
				setFormItemDisabled(thisUiConfig.baseForm, true, null);
				if(row.parentId != 0){
					var root = $(thisUiConfig.tableList).treegrid("find", row.parentId);
					$("#parentLeixingName").val(root.leixingName);
					$("#baseForm").form("load",row);
				}else{
					$("#baseForm").form("load",row);
					$("#parentLeixingName").val("");
				}
				enableButtons(["add1","add2", "del", "edit"]);
			},
			onLoadSuccess : function(){
				$("td").css("border", "0");
			}
		});
	}
};

function setParentId(level){
/* 	if(level == 0){   //新增顶级节点
		$("#parentId").val(0);
		return;
	} */
	var row = $(thisUiConfig.tableList).treegrid("getSelected");
	if(row && row.parentId != 0){
		var root = $(thisUiConfig.tableList).treegrid("find", row.parentId);
	    $("#parentLeixingName").val(root.leixingName);
	}
	if(level == 1){   //新增同级节点
		$("#parentId").val(row.parentId);
	}else if(level == 2){  //新增子节点
		var root = $(thisUiConfig.tableList).treegrid("find", row.yjsjlxId);
	    $("#parentLeixingName").val(root.leixingName);
		$("#parentId").val(row.yjsjlxId);
	};
};

</script>
</body>
</html>