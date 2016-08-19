<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>志愿者服务队伍信息</title>
	
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
	    <a id="add" onclick="baseInfo.add();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	    <a id="del" onclick="baseInfo.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	    <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	    <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	</div>
	
	
	
	<div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
	    <div title="列表" style="position:relative;">
		    <div class="query-area">
			    <form id="queryForm" method="post">
				    <table class="g-form" cellpadding="0" cellspacing="0">
				        <tbody>
							<tr>	
								<td class="form-cell-1">
						            <label class="form-label">服务团队名称</label>
						            <input id="q_teamName_LIKE" name="q_teamName_LIKE" class="form-control">
						        </td>
								<td class="form-cell-1">
						            <label class="form-label">负责人</label>
						            <input id="q_lower_LIKE" name="q_lower_LIKE" class="form-control">
						        </td>						        
								<td class="form-cell-1 f-button">
									<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
									<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
								</td>
								<td class="form-cell-1">
								</td>
							</tr>
						</tbody>
				     </table>
			     </form>
	         </div>
	         <div class="list-area" style="top:50px;">
				<table id="listGrid" style="height:100%;"></table>
			 </div>
	     </div>
	     
	     <!-- data-options="disabled:true" -->
	     <div id="minute" title="详细" data-options="disabled:true">
		     <form id="baseForm"  action="">
		         <fieldset>
	    		    <legend>基础信息 </legend>
		    		<input type="hidden" id="serviceId" name="serviceId">
		    		<input id="createrId" name="createrId" type="hidden" />
		    		<input name="createTime" type="hidden">
		    		<table class="g-form" cellpadding="0" cellspacing="0">
		    		    <tbody>
			     			<tr>
			     			   <td class="form-cell-3" colspan="3">
			     				   <label class="form-label">队伍名称</label>
			     				   <input id="teamName" name="teamName" class="easyui-validatebox form-control" data-options="validType:'length[2, 64]', required:true">
			     				</td>	
			     			   <td class="form-cell-1">
			     				   <label class="form-label">所属机构</label>
			     				   <input id="orgId" name="orgId"/>
			     				</td>
		     				</tr>
		     				<tr>			     					     				
			     			   <td class="form-cell-1">
			     				   <label class="form-label">志愿者人数</label>
			     				   <input  id="voluntNum" name="voluntNum" class="easyui-validatebox form-control" data-options="validType:'onlyNumber',required:true">
			     				</td>			 								    		     				
			     				<td class="form-cell-1">
			     				    <label class="form-label">负责人</label>
			     					<input id="lower" name="lower" class="easyui-validatebox form-control" data-options="required:true">
			     				</td>
			     			    <td class="form-cell-1">
			     				   <label class="form-label">联系电话</label>
			     				   <input id="phone" name="phone" class="easyui-validatebox form-control" 
			     				   		 data-options="validType:'phoneRex',required:true "/>
			     				 </td>		
			     			     <td class="form-cell-1">
			     				   <label class="form-label">服务时间</label>
			     				   <input name="serTime" class="easyui-datebox form-control"
		     				    		style="width:${comboboxWidth};height:${comboboxHeight}px">
			     				 </td>			     				 		     				
			     			</tr>
			     			<tr>
			     				<td class="form-cell-4" colspan="4">
			     				    <label class="form-label">服务地点</label>
			     					<input id="serAddress" name="serAddress" class="easyui-validatebox form-control" data-options="validType:'length[2, 64]', required:true">
			     				</td>	     				     			
			     			</tr>   
			     			<tr>
			     			    <td class="form-cell-4" colspan="4">
			     				   <label class="form-label">服务内容</label>
			     				   <textarea name="serContent" rows="6" class="easyui-validatebox form-control" ></textarea>
			     				</td>		     			
			     			</tr>
			     			<tr>
			     			    <td class="form-cell-4" colspan="4">
			     				   <label class="form-label">服务效果</label>
			     				   <textarea name="serEffect" rows="6" class="easyui-validatebox form-control" ></textarea>
			     				</td>		     			
			     			</tr>
			     		</tbody>
		     		</table>
		    	</fieldset>
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
<script type="text/javascript" src="${ctx}/resources/js/gx-validate.js"></script>

<script type="text/javascript" >
var uploadBtn;
var addNewRecord = false; //新增记录？
var editRecord = false;
var thisUiConfig = {
	ctx : "${ctx}",
	url : "volunteer",
	id : "#serviceId",    //表单中主键字段对应的控件的Id属性值
	idName:"serviceId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var id = $(thisUiConfig.id).val();
		return id;
	}
};

$(function (){
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	initCombobox();
	publicAttr.initCombobox();
});

var tabs = {
		init : function (){
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
			$(thisUiConfig.mainTab).tabs({
				//切换标签时调用
				onSelect : function(title, index) {
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
						var row = $(thisUiConfig.tableList).datagrid("getSelected");
						if (row) {
							$(thisUiConfig.baseForm).form("load",row);
							$("#orgId").lookup("setTxt",publicAttr.getOrgName(tableInfo.thisRow.orgId));
						}
						enableButtons([ "add", "del", "edit"]);
						baseInfo.showImg();
					}
				}
			});
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
}

//基础信息初始化
var baseInfo =  {
	saveUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/save",
	getUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/edit",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/del",
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
				tableInfo.query();       //刷新列表
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
		console.log("base add");
		//clear data
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$(thisUiConfig.tableList).datagrid("unselectAll");
		$(thisUiConfig.mainTab).tabs("enableTab", 1);
		$(thisUiConfig.mainTab).tabs("select", 1);
		setFormItemDisabled(thisUiConfig.baseForm,false,null);
		enableButtons(["save", "upfile"]);
		defaultMesh();         //默认网格
	},
	
	del:function (){
		var row = $(thisUiConfig.tableList).datagrid('getSelected');
		if (! row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的志愿服务记录?",
			function(r) {
				if (r) {
					$.ajax({
							url : baseInfo.delUrl +"/"+ row.serviceId,
							type: "POST"
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
		var tab = $(thisUiConfig.mainTab).tabs('getSelected');
		var index = $(thisUiConfig.mainTab).tabs('getTabIndex',tab);
		if(index != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);
		}
		enableButtons([ "add", "del", "save"]);
		setFormItemDisabled(thisUiConfig.baseForm, false, null);
	},
	showImg : function (){
		var photofileID = $("#photofileID").val();
		var imgUrl = thisUiConfig.ctx + "/resources/images/base/1.png";
		if(photofileID && photofileID > 0){
			imgUrl = thisUiConfig.ctx + "/doc/show/"+ photofileID;
		}
		$("input[type='image']").attr("src", imgUrl);
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
			queryParams:  getFormData(thisUiConfig.queryForm),
			url : baseInfo.search,
			columns : [ [ {
				field : "serviceId",
				hidden:true
			}, {
				field : "orgId",
				halign:'center',
				align:'left',
				formatter: function(value, row){
					return publicAttr.getOrgName(value);
				},
				title : "所属机构",
				width : 160
			},{
				field:"teamName",
				halign:'center',
				align:'left',
				title:"队伍名称",
				width:180
			},{
				 field:"lower",
				 title:"负责人",
				 halign:'center',
				 align:'left',
				 width:150
			},{
				 field:"phone",
				 title:"联系电话",
				 halign:'center',
				 align:'left',
				 width:120
			},{
				field : "serAddress",
				title : "服务地点",
				halign:'center',
				align:'left',
				width : 200
			},{
				field : "serTime",
				title : "服务时间",
				halign:'center',
				align:'left',
				width : 120
			}]],

			onSelect : function(rowIndex, rowData) {
				var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
				if(len==1){
					tableInfo.thisRow = $(thisUiConfig.tableList).datagrid("getSelected");
					$(thisUiConfig.mainTab).tabs("enableTab", 1);
					tableInfo.thisRowIndex = $(thisUiConfig.tableList).datagrid("getRowIndex", tableInfo.thisRow);
					enableButtons([ "add", "del", "edit"]);
					if(editRecord){
						enableButtons([ "add", "del", "save"]);
					}
				}else if(len==0){
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
					enableButtons([ "add"]);
				}else{
					enableButtons([ "add", "del"  ]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
			},
			onUnselect : function(rowIndex, rowData) {
				var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
				console.log(len);
				if(len==1){
					tableInfo.thisRow = $(thisUiConfig.tableList).datagrid("getSelected");
					tableInfo.thisRowIndex = $(thisUiConfig.tableList).datagrid("getRowIndex", tableInfo.thisRow);
					$(thisUiConfig.mainTab).tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit"]);
					if(editRecord){
						enableButtons([ "add", "del", "save"]);
					}
				}else if(len==0){
					enableButtons([ "add"]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}else{
					enableButtons([ "add", "del"  ]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
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
	query : function (){
		var param = getFormData("#queryForm");
		enableButtons([ "add" ]);
		$(thisUiConfig.tableList).datagrid("load", param);
		$(thisUiConfig.mainTab).tabs("disableTab", 1);
	}
	
};

function orgLookup() {
	$("#orgId").lookup({
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
						$("input[name='orgId']").val(data.meshId);						
						$("#orgId").lookup("setTxt", data.meshName);
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
	comboboxConfig.data = dicts.Gender.list;
	$('#sex').combobox(comboboxConfig);

	comboboxConfig.data = dicts.zaizhiStatus.list;
	$('#zaizhiStatus').combobox(comboboxConfig);
}

function dateformatter(date){
	return date.formatDate("yyyy-MM-dd hh:mm:ss");
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