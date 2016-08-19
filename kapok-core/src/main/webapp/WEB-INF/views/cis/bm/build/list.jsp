<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>楼栋信息</title>
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

<div class="g-toolbar">
	<a onclick="baseInfo.add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false">

     <div title="列表" style="position:relative;">
     
		<div id="query">
				<form id="queryForm" class="formCls" method="post">
					<table class="g-form" cellpadding="0" cellspacing="0">
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">小区名称</label>
	            				<input class="easyui-validatebox form-control"  name="q_cisBmCommunityMsg.communityName_LIKE"  /></td>
							<td class="form-cell-1">
	            				<label class="form-label">楼栋号</label>
	            				<input class="easyui-validatebox form-control" name="Q_ldCode_EQ" />
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
     
     <div id="minute" data-options="disabled:true"  title="详细"  >
      
    		<form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="buildId" name="buildId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		
    		<table class="g-form" cellpadding="0" cellspacing="0" >
    			<tbody>
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">所属机构</label>
     					 <input id="org" name="org"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">小区名称</label>
     					<input  id="comId" name="comId"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">楼栋号</label>
     					<input class="easyui-validatebox form-control"   required="required"  name="ldCode"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">住户数</label>
     					<input   validType="checkNumber"  class="easyui-validatebox form-control"  name="zhs" />
     				</td>
     				
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">居民数</label>
     					<input  validType="checkNumber"  class="easyui-validatebox form-control"  name="jms"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">防火措施</label>
     					<input class="easyui-validatebox form-control" name="fhcs"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">防盗措施</label>
     					<input class="easyui-validatebox form-control" name="fdcs"  />
     				</td>
     				 <td class="form-cell-1">
	            		<label class="form-label">清扫人员</label>
     					<input class="easyui-validatebox form-control"  name="qsry"   />
     				</td>
     				
     			</tr>
     			
     			<tr>
	     			<td class="form-cell-4" colspan="4">
	            		<label class="form-label">卫生设施情况</label>
     					<textarea name="wsssqk" class="easyui-validatebox form-control" ></textarea>
     				</td>
     			</tr>
	     		 
     		 	<tr>
     				 <td class="form-cell-1">
	            		<label class="form-label">电话报警</label>
     					<input dictCode="YesOrNo" name="dhbj" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">电子监控</label>
     					<input dictCode="YesOrNo" name="dzjk" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">封闭情况</label>
     					<input dictCode="YesOrNo" name="fbqk" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">楼栋负责人</label>
     					<input class="easyui-validatebox form-control" name="ldName"    />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">负责人电话</label>
     					<input class="easyui-validatebox form-control" name="phone"  />
     				</td>
     			</tr>
     			
     			</tbody>
     		</table>
     		</form>
     </div>
</div>


<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" >

var thisUiConfig = {
	main : window.parent.main,
	ctx : "${ctx}",
	url : "buildingmsg",
	id : "#buildId",    //表单中主键字段对应的控件的Id属性值
	idName:"buildId",
	tableList : "#listGrid",
	getId : function (){
		var buildId = $(thisUiConfig.id).val();
		return buildId;
	}
}


$(function (){
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	publicAttr.initCombobox();
	communityInfoLookup();
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
							setFormItemDisabled("#baseForm",false,null);
							enableButtons([ "add", "del", "save" ]);
						}else{
							enableButtons([ "add", "del","edit"]);
						}
					}
				}
			});
		}
		
}

var	Msg = {
		
	 show : function (title,msg){
		 
		 	title = title || "温馨提示" ;
		 	
		 	msg = msg || "操作成功" ;
		 
			$.messager.show({
				title : "温馨提示",
				msg : "操作成功",
				timeout : 2500,
				showType : "slide"
			});
	 }
		
}


//基础信息初始化
var baseInfo =  {
		
	ajaxExistsHouse : null ,
		
	saveUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",
	
	getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/softDel/",
	
	search : thisUiConfig.ctx + "/" + thisUiConfig.url + "/search ",
	
	existsHouseUrl : thisUiConfig.ctx + "/house/existsHouse/" , 
		
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
					$("#org").lookup("setTxt",publicAttr.getOrgName(row.org));
					tableInfo.query();
					enableButtons([ "add" , "save" ]);
					
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
		
		$.messager.confirm("温馨提示", "确定删除楼栋【"+row.ldCode+"】?",
			function(r) {
				if (r) {
					
					publicFormControl.ajax({
						url : baseInfo.delUrl + row.buildId,
						type: "POST" 
					},function (data){
						
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$("#mainTab").tabs("select", 0);
							$("#mainTab").tabs("disableTab", 1);
							enableButtons([ "add" ]);
							// 重新获取数据
							tableInfo.query();
						}else if(data.resultCode == "-2"){
							$.messager.alert("温馨提示", "楼栋号【"+row.ldCode+"】下存在房屋信息，无法删除！", "info");
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
	},
	
	existsHouse : function (buildId){
		
		baseInfo.ajaxExistsHouse = $.ajax({
			url: baseInfo.existsHouseUrl + buildId
		});
		
	}
};

function  loadData(){
	var row = $(thisUiConfig.tableList).datagrid("getSelected");
	if(row){
		var id = row[thisUiConfig.idName];
		if(id){
			publicFormControl.ajax({
				type : "POST",
				url : baseInfo.getUrl + id,
			},function (result){
				var row = result.row;
				$("#baseForm").form("load",row);
				$("#org").lookup("setTxt",publicAttr.getOrgName(row.org));
				$("#comId").lookup("setTxt",row.communityName);
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
				field : "buildId",
				hidden:true,
			}, {
				field : "ldCode",
				title : "楼栋号",
				width : 150,
				halign:'center',
				align:'left'
			}, {
				field : "org",
				title : "所属机构",
				width : 150,
				halign:'center',
				align:'left',
				formatter : function (value,row,index){
					
						return publicAttr.getOrgName(value);
				}
			},{
				field:"communityName",
				title:"小区名称",
				width:150,
				halign:'center',
				align:'left'
			},{
				field:"zhs",
				title:"住户数",
				width:100,
				halign:'center',
				align:'right'
			},{
				field:"jms",
				title:"居民数",
				width:100,
				halign:'center',
				align:'right'
			},{
				field:"ldName",
				title:"楼栋负责人",
				width:150,
				halign:'center',
				align:'left'
			},{
				field:"phone",
				title:"负责人电话",
				 width:100,
				 halign:'center',
				 align:'left'
			}]],
			
			onSelect : function(rowIndex, rowData) {
				tabs.isEdit = false ;
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
				console.log(len);
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
		var param = tableInfo.getQueryFormData("#queryForm");
		enableButtons([ "add" ]);
		$("#listGrid").datagrid("load", param);
		$("#mainTab").tabs("disableTab", 1);
	},
	
	getQueryFormData : function (formId){
		var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
		var data =  getFormData(formId) || {};
		if(meshIds){
			data['q_org_IN'] = meshIds;
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


function  getOrgid(){
	
	return $('#org').lookup("getVal");
}

var communityInfoLookup;
/**
 * 小区弹出窗
 */
function communityInfoLookup() {
	
	communityInfoLookup = $("#comId").lookup({
		title : "选择小区",
		url : thisUiConfig.ctx + "/community/selectCommunity",
		width: 700,
		height: 440,
		valueField: 'comId',
		textField: 'communityName',
		contentWindow: {
			params: {
				singleSelect : true,
				checkbox : false,
				queryParams : function (){
					return {
						orgId:getOrgid()
					};
				}
			}
		},
		onBeforeOpen: function() {
			var orgId = getOrgid() ;
			if(orgId){
				return true;
			}else{
				main.alert("温馨提示","请选择机构！")
				return false;
			}
			
		}
	});
	
	 
}


var orgLookup;
/**
 * 机构弹出窗
 */
function orgLookup() {
	
	orgLookup = $('#org').lookup({
		title : "选择所属网格",
		url : thisUiConfig.ctx + "/mesh/selectmesh",
		width : 350,
		height : 500,
		required : true,
		valueField: 'meshId',
		textField: 'meshName'
		/* contentWindow: {
			params: {},
			actions: {
				confirm : function(data) {
					if (data[opts.textField]) lookup.txtbox.val(data[opts.textField]);
					if (data[opts.valueField]) lookup.valbox.val(data[opts.valueField]);
					lookup.txtbox.validatebox('validate');
					console.log(data);
					main.closeDialog();
				}
			}
		} */
	});
}

function defaultMesh(){
	var meshId = main.currentUserMesh.meshId;
	$("#org").lookup("setTxt", publicAttr.getOrgName(meshId));
	$("#org").lookup('setVal', meshId);
}

</script>

</body>
</html>