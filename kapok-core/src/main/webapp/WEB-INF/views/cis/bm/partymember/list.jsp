<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>党员信息</title>
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
	<!-- 按钮区域 -->
	<div class="g-toolbar">
      <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
      <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	</div>
	
	<!-- 内容区域 -->
	<div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
		<div title="列表" style="position:relative;">
			<div class="query-area">
				<form id="queryForm" method="post">
					<table class="g-form" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="form-cell-1">
				            		<label class="form-label">姓名</label>
				            		<input name="q_householderName_LIKE" class="easyui-validatebox form-control">
			          			</td>
			          			<td class="form-cell-1">
				            		<label class="form-label">身份号</label>
				            		<input name="q_cardCode_EQ" class="easyui-validatebox form-control">
			          			</td>
			          			<td class="form-cell-1">
				            		<label class="form-label">婚姻状况</label>
				            		<input dictCode="maritalStatus" name="q_maritalStatus_EQ"
				            			class="easyui-combobox form-control"
				            			style="width:${comboboxWidth};height:${comboboxHeight}px"
				            			data-options="
		 								    	editable:false,
		 								    	panelHeight:'auto',
		 								    	valueField:'dictCode',
		 								    	textField:'dictName'">
			          			</td>
			          			<td class="form-cell-1">
				            		<label class="form-label">性别</label>
				            		<input dictCode="Gender" name="q_sex_EQ" class="easyui-combobox form-control"
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
				            		<label class="form-label">年龄</label>
									<input name="q_age_GT" class="easyui-validatebox form-control">
								</td>
								<td class="form-cell-1">
				            		<label class="form-label">~</label>
				            		<input name="q_age_LT" class="easyui-validatebox form-control">
				            	</td>
								<td class="form-cell-1">
				            		<label class="form-label">户籍类别</label>
									<input dictCode="residentType" name="q_householdType_EQ" class="easyui-combobox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
								</td>
								<td class="form-cell-1">
			            			<label class="form-label">管理类型</label>
			            			<input dictCode="ManageType" name="q_gllx_EQ" class="easyui-combobox form-control"
			            				style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
		 						</td>
							</tr>
							<tr>
								<td class="form-cell-3" colspan="3"></td>
								<td class="form-cell-1 f-button">
									<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
									<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			
			<div class="list-area" style="top:104px;">
				<table id="listGrid" style="height:100%"></table>
			</div>
			
		</div>
		
		<div id="minute"  title="详细"  data-options="disabled:true"  >
			 <form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="partyId" name="partyId" type="hidden" />
	    		<input id="householderId" name="householderId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		
    		<table class="g-form" cellpadding="0" cellspacing="0">
     			<tr>
     				<td class="form-cell-1">
				         <label class="form-label">所属机构</label>
     					 <input id="orgId" name="orgId"  />
     				</td>
     				<td class="form-cell-1">
				         <label class="form-label">姓名</label>
     					<input id="householderName" name="householderName" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">性别</label>
     					<input dictCode='Gender' id="sex" name="sex" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">出生日期</label>
     					<input id="birthDate" name="birthDate" type="text"  class="easyui-datebox form-control"   data-options="editable:false"  style="width:${comboboxWidth};height:${comboboxHeight}px" />
     				</td>
     			</tr>
     			
    			<tr>
     				<td class="form-cell-1">
				        <label class="form-label">民族</label>
 							<input  dictCode='Nation' id="nationality" name="nationality" class="easyui-combobox form-control"
 								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
								    	editable:false,
								    	panelHeight:'auto',
								    	valueField:'dictCode',
								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">文化程度</label>
     					<input dictCode="Education" id="eduLevel" name="eduLevel" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">政治面貌</label>
     					<input dictCode="politicsStatus"  id="politicsStatus" name="politicsStatus" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false,
								    	panelHeight:'auto',
								    	valueField:'dictCode',
								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">入党时间</label>
     					<input name="inPartyTime" type="text" class="easyui-datebox form-control"  data-options="editable:false" style="width:${comboboxWidth};height:${comboboxHeight}px"  />
     				</td>
     			</tr>
     			 
     			<tr>
     				<td class="form-cell-1">
				        <label title="党组织关系是否在社区" class="form-label">党组织关系是否在社区</label>
     					<input dictCode="YesOrNo"  name="isIncommunity" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">婚姻状况</label>
     					<input  dictCode="ManageType"  id="maritalStatus" name="maritalStatus" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">身份证号码</label>
     					<input id="cardCode" name="cardCode" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">社会职务</label>
     					 <input dictCode='socialFunction' id="" name="socialJob" class="easyui-combobox form-control"
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
				        <label title="工作单位" class="form-label">工作单位</label>
     					<input id="dutyName" name="gzdw" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">联系电话</label>
     					<input id="callPhone"  name="callPhone" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">户籍地</label>
     					<input id="householdAddress" name="householdAddress" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">现居地</label>
     					<input id="dwellAddress" name="dwellAddress" class="easyui-validatebox form-control"  />
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-1">
				        <label class="form-label">籍贯</label>
     					<input id="nativePlace" name="nativePlace" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">户口类型</label>
     					<input dictCode='residentType' id="householdType" name="householdType" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">党组织类别</label>
     					<input dictCode="organizationType" name="organizationType" class="easyui-combobox form-control"
     							style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">直管党员</label>
     					<input dictCode="straightParty" name="straightPartyMember" class="easyui-combobox form-control"
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
				        <label class="form-label">流动党员</label>
     					<input dictCode="floatingCommunists" name="floatingPartyMembers" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
				        <label class="form-label">困难党员</label>
     					<input dictCode="difficultMembers" name="difficultPartyMembers" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1" >
				        <label class="form-label">建国前老党员</label>
     					<input name="oldPartyMembers"  class="easyui-validatebox form-control"    />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-4" colspan="4">
				        <label class="form-label">民主评议结果</label>
     					<textarea name="appraisalResult"  class="easyui-validatebox form-control" rows="5" ></textarea>
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4" colspan="4">
				        <label class="form-label">奖惩情况</label>
     					<textarea name="rewardsResult"  class="easyui-validatebox form-control"  rows="8"  ></textarea>
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4" colspan="4">
				        <label class="form-label">培训情况</label>
     					<textarea name="trainResult"  class="easyui-validatebox form-control"  rows="8"  ></textarea>
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-4" colspan="4">
				        <label class="form-label">关系接转</label>
     					<textarea name="relationshipTransfer"  class="easyui-validatebox form-control" rows="8"   ></textarea>
     				</td>
     			</tr>
     		</table>
     		</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" >


var exclude = "#householderName,#sex,#birthDate,#nationality,"+
	"#eduLevel,#politicsStatus,#politicsStatus,#maritalStatus,#cardCode,"+
	"#socialJob,#dutyName,#callPhone,#dwellAddress,#householdType,#householdAddress,#nativePlace";

var thisUiConfig = {
	main:main,
	ctx : "${ctx}",
	url : "partymember",
	id : "#partyId",    //表单中主键字段对应的控件的Id属性值
	idName:"partyId",
	tableList : "#listGrid",
	getId : function (){
		var schoolId = $(thisUiConfig.id).val();
		return schoolId;
	}
}

$(function (){
	enableButtons([ ]);
	tabs.init();
	tableInfo.init();
	baseInfo.init();
	publicAttr.initCombobox();
	orgLookup();
	 $('#orgId').lookup("disable")
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
							setFormItemDisabled("#baseForm",false,exclude);
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
		
	saveUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",
	
	getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/findByHid/",
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/delList",
	
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
					var row = result.row;
					$("#baseForm").form("load",row);
					$.messager.progress("close");
					tableInfo.query();
					enableButtons(["save"]);
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
		enableButtons([   "save" ]);
		$("#baseForm").form("clear");
	},
	
	del:function (){
		
		var row = $("#listGrid").datagrid('getSelections');
		
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		
		$.messager.confirm("温馨提示", "确定删除学校记录?",
			function(r) {
				if (r) {
					var ids = "";
					$.each(row,function (i,n){
						ids = ids + n[thisUiConfig.idName] + ","
					});
					ids = ids.substring(0,ids.length-1);
					
					$.ajax({
						url : baseInfo.delUrl,
						type: "POST",
						data : {
							ids : ids
						}
					}).done(function(data) {
						$.messager.progress("close");
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$("#mainTab").tabs("select", 0);
							$("#mainTab").tabs("disableTab", 1);
							enableButtons([ ]);
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
		tabs.isEdit = true ;
		var tab = $('#mainTab').tabs('getSelected');
		var  index = $('#mainTab').tabs('getTabIndex',tab);
		if(index!=1){
			$("#mainTab").tabs("enableTab", 1);
			$("#mainTab").tabs("select", 1);
		}else{
			enableButtons([ "add", "del" , "save" ]);
		}
		setFormItemDisabled("#baseForm",false,exclude);
	}
	
	
};

 
 

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
			url : thisUiConfig.ctx +"/" + "householder/partymember",
			columns : [ [ {
				field : "householderId",
				hidden:true
			}, {
				field : "orgId",
				title : "机构名称",
				width : 150,
				halign:'center',
				align:'left',
				formatter : function (value,row,index){
					if(value){
						return publicAttr.getOrgName(value);
					}
					return "";
				}
			},{
				field : "householderName",
				title : "姓名",
				halign:'center',
				align:'left',
				width : 100,
			},{
				field:"age",
				title:"年龄",
				width:50,
				halign:'center',
				align:'left'
			},{
				field:"sex",
				title:"性别",
				halign:'center',
				align:'left',
				width:50,
				formatter : function (value,row,index){
					
					return publicAttr.getDictText("Gender",value);
				}
			},{
				field:"cardCode",
				title:"身份证号码",
				width:150,
				halign:'center',
				align:'left',
			},{
				 field:"nationality",
				 title:"民族",
				 halign:'center',
				 align:'left',
				 width:100,
				 formatter : function (value,row,index){
						
					return publicAttr.getDictText("Nation",value);
				 }
			},{
				field:"maritalStatus",
				title:"婚姻状况",
				halign:'center',
				align:'left',
				width:100,
				formatter : function (value,row,index){
					
					return publicAttr.getDictText("maritalStatus",value);
				 }
			},{
				field:"householdType",
				title:"户籍类别",
				halign:'center',
				align:'left',
				width:100,
				formatter : function (value,row,index){
					
					return publicAttr.getDictText("residentType",value);
				 }
			},{
				field:"gllx",
				title:"管理类型",
				halign:'center',
				align:'left',
				width:100,
				formatter : function (value,row,index){
					
					return publicAttr.getDictText("ManageType",value);
				 }
			},{
				field:"householderRelation",
				title:"与户主关系",
				halign:'center',
				align:'left',
				width:100,
				formatter : function (value,row,index){
					
					return publicAttr.getDictText("familytree",value);
				 }
			},{
				field:"callPhone",
				title:"联系电话",
				halign:'center',
				align:'left',
				width:100
			}]],
			
			onSelect : function(rowIndex, rowData) {
				tabs.isEdit = false;
				var len = $("#listGrid").datagrid("getSelections").length;
				if(len==1){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons([   "del", "edit" ]);
				}else if(len==0){
					$("#mainTab").tabs("disableTab", 1);
					enableButtons([  ]);
				}else{
					enableButtons([   "del"  ]);
					$("#mainTab").tabs("disableTab", 1);
				}
			},
			onUnselect : function(rowIndex, rowData) {
				var len = $("#listGrid").datagrid("getSelections").length;
				 
				if(len==1){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons([   "del", "edit" ]);
				}else if(len==0){
					enableButtons([ ]);
					$("#mainTab").tabs("disableTab", 1);
				}else{
					enableButtons([  "del"  ]);
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

 

function  loadData(){
	var row = $(thisUiConfig.tableList).datagrid("getSelected");
	if(row){
		var ajax = publicFormControl.ajax({
			type : "POST",
			url : baseInfo.getUrl + row.householderId,
		},function (result){
			var r = result.row ;
			if(r){
				$("#baseForm").form("load",r);
				$("#orgId").lookup('setTxt',publicAttr.getOrgName(r.orgId));
			}
			$("#baseForm").form("load",row);
			$("#orgId").lookup('setTxt',publicAttr.getOrgName(row.orgId));
		});
	}
}


var orgLookup;

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

//年龄计算
function  ages(str){   
      var  r =   str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
      if(r==null)return   false;     
      var d=new Date(r[1],   r[3]-1,   r[4]);     
      if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]){   
            var   Y   =   new   Date().getFullYear();   
            return((Y-r[1])   +"周岁");   
      }   
      return "";   
}  

</script>

</html>