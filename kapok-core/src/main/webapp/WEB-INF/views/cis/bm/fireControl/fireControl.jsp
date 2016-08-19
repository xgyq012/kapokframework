<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>消防信息</title>
<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css" type="text/css">
<!--[if IE 7]> 
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css"> 
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
<link rel="stylesheet" href="${ctx}/resources/css/page.css">
<link rel="stylesheet" href="${ctx}/resources/js/lightbox/css/lightbox.min.css">

</head>
<body>

<div class="g-layout">

<div class="g-toolbar">
	<a onclick="baseInfo.add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a id="upfile" class="easyui-linkbutton toolbar g-button"><i class="fa fa-arrow-circle-up"></i>上传图片</a>
	<a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

     <div title="列表" style="position:relative;">
     
		<div id="query">
				<form id="queryForm" class="formCls" method="post">
					<table class="g-form" cellpadding="0" cellspacing="0" >
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">单位名称</label>
	            				<input name="q_unitsName_LIKE"  class="easyui-validatebox form-control" /></td>
	            			<td class="form-cell-1">
	            				<label class="form-label">检查人</label>
	            				<input name="q_rummager_LIKE"  class="easyui-validatebox form-control"  /></td>
	            			<td class="form-cell-1">
	            				<label class="form-label">检查日期</label>
	            				<input  name="q_edttime_EQ" class="easyui-datebox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="editable:false"></td>
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
     
      <div id="minute" data-options="disabled:true"  title="详细" >
      
       <fieldset>
			<legend>消防信息</legend>
			
    		<form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="cofireId" name="cofireId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		
    		<table class="g-form" cellpadding="0" cellspacing="0" >
    		
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">所属机构</label>
     					 <input id="orgId" name="orgId"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">单位名称</label>
     					<input name="unitsName" class="easyui-validatebox form-control"   required="required"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">检查人</label>
     					<input name="rummager" class="easyui-validatebox form-control"   required="required"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">电话</label>
     					<input name="rummagerPhone" class="easyui-validatebox form-control"  required="required"     />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label title="是否圈占、堵塞消防车通道" class="form-label">是否圈占、堵塞消防车通道</label>
     					<input dictCode="YesOrNo" name="congested" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
	            		<label title="是否在公共区域乱拉乱接电线，超负荷用电" class="form-label">是否在公共区域乱拉乱接电线，超负荷用电</label>
     					<input dictCode="YesOrNo" name="overload" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
	            		<label title="是否在住宅内、地下室储存易燃易爆物品" class="form-label">是否在住宅内、地下室储存易燃易爆物品</label>
     					<input dictCode="YesOrNo" name="explosives" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
	            		<label title="共用消防设施、器材是否丢失、损坏，是否有埋压、圈占、损坏消火栓等公共消防设施的现象" class="form-label">共用消防设施、器材是否丢失、损坏，是否有埋压、圈占、损坏消火栓等公共消防设施的现象</label>
     					<input dictCode="YesOrNo" name="damage" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label title="住宅楼内疏散楼梯是否堆放杂物" class="form-label">住宅楼内疏散楼梯是否堆放杂物：</label>
     					<input dictCode="YesOrNo" name="stairs" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">检查日期</label>
     					<input name="edttime" class="easyui-datebox form-control" 
										data-options="editable:false"
										style="width:${comboboxWidth};height:${comboboxHeight}px"    /></td>
     				<td class="form-cell-1">
	            		<label class="form-label">人数</label>
     					<input name="peoples" class="easyui-validatebox form-control"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">行业类型</label>
     					<input name="industryTypes" class="easyui-validatebox form-control"    />
     				</td>
     			</tr>
     			
     				<tr>
     				<td class="form-cell-1">
     					<label class="form-label">法人/负责人</label>
     					<input name="corp" class="easyui-validatebox form-control"     />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">联系电话</label>
     					<input name="corpPhone" class="easyui-validatebox form-control"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">消防负责人</label>
     					<input name="firePrincipal" class="easyui-validatebox form-control"    />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1" colspan="1" >
     					<label class="form-label">图片</label>
     					<input id="pictureId" name="pictureId" type="hidden">
     					<a class="example-image-link" href="${ctx}/resources/images/nophoto.jpg" data-lightbox="image-1">
     						<input id="photofileUrl" name="photofileUrl" type="image" class="easyui-validatebox form-control"
	     						src="${ctx}/resources/images/nophoto.jpg" onclick="return false;"
	     						style="height:${rowSpanHeight*6-gutterHeight}px">
     					</a>
	     				<div id="addPhoto"></div>
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4" colspan="4">
     					<label class="form-label">其他</label>
     					<textarea name="others" class="easyui-validatebox form-control"  rows="5"  ></textarea>
     				</td>
     			</tr>
     			
     		</table>
     		</form>
     		</fieldset>
     </div>
</div>
</div>
 
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lightbox/lightbox.min.js"></script>
<script type="text/javascript" >

var exclude = "#photofileUrl";

var thisUiConfig = {
	main:window.main,
	ctx : "${ctx}",
	url : "fireControl",
	id : "#cofireId",    //表单中主键字段对应的控件的Id属性值
	idName:"cofireId",
	tableList : "#listGrid",
	getId : function (){
		var id = $(thisUiConfig.id).val();
		return id;
	}
}

$(function (){
	updateQueryParam();
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	importDoc.init();
	publicAttr.initCombobox();
});

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
})(jQuery);

function updateQueryParam(){
	var congested = $.getUrlParam("congested");
	var overload = $.getUrlParam("overload");
	var damage = $.getUrlParam("damage");
	var stairs = $.getUrlParam("stairs");
	if(congested){
		$("#queryForm").append("<input name='q_congested_EQ' value='"+ congested +"' type='hidden'>");
	}else if(overload){
		$("#queryForm").append("<input name='q_overload_EQ' value='"+ overload +"' type='hidden'>");
	}else if(damage){
		$("#queryForm").append("<input name='q_damage_EQ' value='"+ damage +"' type='hidden'>");
	}else if(stairs){
		$("#queryForm").append("<input name='q_stairs_EQ' value='"+ stairs +"' type='hidden'>");
	}
}
 
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
						setFormItemDisabled("#baseForm",true,exclude);
					} else if (index == 1) { // 选中详细标签
						loadData();
						if(tabs.isEdit){
							setFormItemDisabled("#baseForm",false,exclude);
							enableButtons([ "add", "del", "save" ,"upfile"]);
						}else{
							enableButtons([ "add", "del","edit"]);
						}
					}
				}
			});
		}
		
}

var uploadBtn;

var importDoc = {
		
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
					$("#pictureId").val(result.docId);
					baseInfo.showImg();
				}
				
			});
	 		
	 		$("#upfile").on("click",function (){
				uploadBtn.fileupload('click');
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
		setFormItemDisabled("#baseForm",true,exclude);
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
					enableButtons([ "add", "save" , "upfile" ]);
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
		setFormItemDisabled("#baseForm",false,exclude);
		//$("#baseForm").form("enableValidation");
		enableButtons([ "add", "save" , "upfile" ]);
		$("#baseForm").form("clear");
		baseInfo.showImg();
		defaultMesh();         //默认网格
	},
	
	del:function (){
		
		var row = $("#listGrid").datagrid('getSelected');
		
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		
		$.messager.confirm("温馨提示", "确定删除单位名称为【"+row.unitsName+"】记录?",
			function(r) {
				if (r) {
					publicFormControl.ajax({
						url : baseInfo.delUrl+ row[thisUiConfig.idName],
						type: "POST",
					},function (data){
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$("#mainTab").tabs("select", 0);
							$("#mainTab").tabs("disableTab", 1);
							tableInfo.query();
							enableButtons([ "add" ]);
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
			enableButtons([ "add", "del" , "save" , "upfile" ]);
		}
		setFormItemDisabled("#baseForm",false,exclude);
	},
	
	showImg : function (){
		var pictureId = $("#pictureId").val();
		var  imgUrl ="";
		if(pictureId && pictureId>0){
			imgUrl = thisUiConfig.ctx + "/doc/show/"+ pictureId
		}else{
			imgUrl = thisUiConfig.ctx + "/resources/images/nophoto.jpg";
		}
		$("#photofileUrl").attr("src",imgUrl);
		$(".example-image-link").attr("href",imgUrl);
	},
	
};

function  loadData(){
	var row = $(thisUiConfig.tableList).datagrid("getSelected");
	if(row){
		var id = row[thisUiConfig.idName];
		if(id){
			publicFormControl.ajax({
				type : "POST",
				url : baseInfo.getUrl +  id,
			},function (result){
				var row =  result.row;
				$("#baseForm").form("load",row);
				$("#orgId").lookup('setTxt',publicAttr.getOrgName(row.orgId));//添加名称
				baseInfo.showImg();
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
				field : "cofireId",
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
				field : "unitsName",
				title : "单位名称",
				width : 200,
				halign:'center',
				align:'left'
			},{
				field : "corp",
				title : "负责人",
				width : 100,
				halign:'center',
				align:'left'
			},{
				field:"corpPhone",
				title:"联系电话",
				width:150,
				halign:'center',
				align:'left'
			},{
				field:"rummager",
				title:"检查人",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"rummagerPhone",
				title:"检查人电话",
				width:150,
				halign:'center',
				align:'left'
			},{
				field:"edttime",
				title:"检查日期",
				width:100,
				halign:'center',
				align:'left'
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