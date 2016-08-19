<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>房屋信息</title>
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

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false"  >

     <div title="列表"  style="position:relative;">
         
		<div id="query">
				<form id="queryForm" class="formCls" method="post">
					<table  class="g-form" cellpadding="0" cellspacing="0">
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">小区名称</label>
	            				<input name="q_cisBmCommunityMsg.communityName_LIKE" class="easyui-validatebox form-control"  /></td>
							<td class="form-cell-1">
	            				<label class="form-label">楼栋号</label>
	            				<input   name="q_cisBmBuildingMsg.ldCode_LIKE" class="easyui-validatebox form-control"  /> 
	            			</td>
							<td class="form-cell-1">
	            				<label class="form-label">房主姓名</label>
	            				<input name="q_houseUsername_EQ" class="easyui-validatebox form-control" />
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
			<table id="listGrid"  style="height:100%" ></table>
		</div>
         
     </div>
     <div id="minute"  title="详细"  data-options="disabled:true"  >
      
    		<form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="houseId" name="houseId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		
    		<table  class="g-form" cellpadding="0" cellspacing="0">
     			<tr>
     				<td class="form-cell-1">
	            		 <label class="form-label">所属机构</label>
     					 <input id="org" name="org"   />
     				</td>
     				<td class="form-cell-1">
	            		 <label class="form-label">小区名称</label>
     					<input  id="comId" name="comId"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">楼栋号</label>
     					<input id="buildId" name="buildId"  /> 
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">房屋号</label>
     					<input required="required" class="easyui-validatebox form-control" name="dyCode"  />
     				</td>
     				
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">房主姓名</label>
     					<input required="required" class="easyui-validatebox form-control" name="houseUsername"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">房主身份证号</label>
     					<input required="required" class="easyui-validatebox form-control" name="cardId" type="text"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">房主电话</label>
     					<input required="required" class="easyui-validatebox form-control" name="phone" type="text"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">共有人姓名</label>
     					<input class="easyui-validatebox form-control" name="gyrName"   />
     				</td>
     				
     				
     			</tr>
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">现居住人姓名</label>
     					<input class="easyui-validatebox form-control" name="xzName"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">现住人电话</label>
     					<input class="easyui-validatebox form-control" name="tel"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">居民人数</label>
     					<input class="easyui-validatebox form-control" name="jmrs"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">住房面积(m2)</label>
     					<input class="easyui-validatebox form-control" name="zfmj"    />
     				</td>
     				
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">房产证号码</label>
     					<input class="easyui-validatebox form-control" name="fczCode" type="text"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">房屋性质</label>
     					<input dictCode="HouseNature" name="fwxz" class="easyui-combobox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">是否自用或出租</label>
     					<input dictCode="userOrLease" name="isZj" class="easyui-combobox form-control"
     									style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				 
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">防盗门</label>
     					<input dictCode="IsIntall" name="fdm" class="easyui-combobox form-control"
     									style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				</td>
     			
     			</tr>
     		 
     		 	<tr>
     		 		<td class="form-cell-1">
	            		<label class="form-label">电子监控</label>
     					<input  dictCode="IsIntall" name="dzjk" class="easyui-combobox form-control"
     									style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">电话报警</label>
     					<input dictCode="IsIntall" name="dhbj" class="easyui-combobox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				</td>
     				
     				<td class="form-cell-1">
	            		<label class="form-label">是否一房多户</label>
     					<input dictCode="YesOrNo" name="isYfsh" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     			</tr>
     			 
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
	url : "house",
	id : "#houseId",    //表单中主键字段对应的控件的Id属性值
	idName:"houseId",
	tableList : "#listGrid",
	getId : function (){
		var houseId = $(thisUiConfig.id).val();
		return houseId;
	}
}

$(function (){
	tabs.init();
	tableInfo.init();
	baseInfo.init();
	enableButtons(['add']);
	orgLookup();
	communityInfoLookup();
	buildLookup();
	publicAttr.initCombobox();
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



//基础信息初始化
var baseInfo =  {
		
	saveUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",
	
	getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/softDel/",
	
	search : thisUiConfig.ctx + "/" + thisUiConfig.url + "/search ",
		
	init : function (){
		
		$("#minute").find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
			var id ="#"+$(this).attr("id");
			setFormItemDisabled(id,true,null);
		});
		
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
					var row= result.row;
					$("#baseForm").form("load",row);
					$("#org").lookup("setTxt",publicAttr.getOrgName(row.org));
					$("#buildId").lookup("setTxt",row.ldCode);
					$("#comId").lookup("setTxt",row.communityName);
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
			thisUiConfig.main.info("温馨提示", "请选择删除的信息！");
			return;
		}
		
		$.messager.confirm("温馨提示", "确定删除房屋【"+row.dyCode+"】?",
				
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
							enableButtons([ "add" ]);
							// 重新获取数据
							tableInfo.query();
							
						}else if(data.resultCode == "2"){
						
							thisUiConfig.main.info("温馨提示", data.resultMsg + "，因为人口信息下存在 房屋【"+row.dyCode+"】！");
						
							return ;
							
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
		var id = row[thisUiConfig.idName];
		if(id){
			publicFormControl.ajax({
				type : "POST",
				url : baseInfo.getUrl +  "/" + id,
			},function (result){
				var row = result.row ;
				$("#baseForm").form("load",row);
				$("#org").lookup("setTxt",publicAttr.getOrgName(row.org));
				$("#buildId").lookup("setTxt",row.ldCode);
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
				field : "houseId",
				hidden:true,
			},{
				field : "buildId",
				hidden:true,
			},{
				field : "comId",
				hidden:true,
			},{
				field : "org",
				title : "所属机构",
				width : 150,
				align:'left',
				halign:'center',
				formatter :  function (value,row,index){
					
					return  publicAttr.getOrgName(value);
				}
			},{
				field:"communityName",
				title:"小区名称",
				width:200,
				halign:'center',
				align:'left'
			}, {
				field : "ldCode",
				title : "楼栋号",
				width : 100,
				halign:'center',
				align:'left'
			}, {
				field : "dyCode",
				title : "单元号",
				width : 100,
				halign:'center',
				align:'left'
			},{
				field:"houseUsername",
				title:"房主姓名",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"xzName",
				title:"现住人",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"phone",
				title:"联系电话",
				width:150,
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

function  getComId(){
	
	return  $('#comId').lookup("getVal");
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
	});
	
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
			},
			actions : {
				confirm : function(data) {
					if(data){
						 $("#comId").lookup("setVal",data.comId);
						 $("#comId").lookup("setTxt",data.communityName);
						 $("#buildId").lookup("clear");
					}else{
						$("#comId").lookup("clear");
					}
					main.closeDialog();
				},
				cancel : function() {
					main.closeDialog();
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



var buildLookup;
/**
 * 选择楼栋弹出窗
 */
function buildLookup() {
	
	
	communityInfoLookup = $("#buildId").lookup({
		title : "选择楼栋",
		url : thisUiConfig.ctx + "/buildingmsg/selectBuild",
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
						comId:getComId()
					};
				}
			},
			actions : {
				confirm : function(data) {
						console.log(data);
					if(data){
						 if(data.comId){
							 $("#comId").lookup("setVal",data.comId);
							 $("#comId").lookup("setTxt",data.communityName);
						 }
						 $("#buildId").lookup("setVal",data.buildId);
						 $("#buildId").lookup("setTxt",data.ldCode);
					}else{
						$("#buildId").lookup("clear");
					}
					main.closeDialog();
				},
				cancel : function() {
					main.closeDialog();
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

function defaultMesh(){
	var meshId = main.currentUserMesh.meshId;
	$("#org").lookup("setTxt", publicAttr.getOrgName(meshId));
	$("#org").lookup('setVal', meshId);
}


</script>

</body>
</html>