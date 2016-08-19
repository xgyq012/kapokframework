<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>户主关系</title>
	
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
	<!-- 按钮区域 -->
	<div class="g-toolbar">
		<a id="edit" class="easyui-linkbutton g-button" onclick="editUser()" data-options="disabled:true"><i class="fa fa-edit"></i>编辑</a>
		<a id="saveUser" class="easyui-linkbutton g-button" onclick="saveUser()" data-options="disabled:true"><i class="fa fa-floppy-o"></i>保存</a>
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
									<input name="q_age_GTE" class="easyui-validatebox form-control">
								</td>
								<td class="form-cell-1">
				            		<label class="form-label">~</label>
				            		<input name="q_age_LTE" class="easyui-validatebox form-control">
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
		
		<div title="家庭成员" data-options="disabled:true" >
		
			<form id="dataForm" class="formCls" method="post">
			
				<div id="form" style="height: 352px;">
				
					<table class="g-form" cellpadding="0" cellspacing="0">
		    			<tbody>
		    				<tr>
			    				<td class="form-cell-3" colspan="3">
				            		<label class="form-label">所属网格</label>
				            		<input id="orgId" name="orgId">
			          			</td>
			    				<td class="form-cell-1" rowspan="6">
			    					<label class="form-label">照片</label>
			    					<input id="photofileID" name="photofileID" type="hidden">
			     					<input id="docShowname" name="docShowname" type="hidden">
			     					<input id="photofileUrl" type="image" class="easyui-validatebox form-control"
			     						src="${ctx}/resources/images/base/1.png" onclick="return false;"
			     						style="height:${rowSpanHeight*6-gutterHeight}px">
			     						<div id="addPhoto"></div>
			    				</td>
			    				<%-- <td colspan="2" rowspan="5" align="center">
			     					<div style="width: 100%;text-align: center;">
				     					<img  id="photofileUrl" style="width:100px;height:100px" alt="" src="${ctx}/resources/images/base/1.png">
			     					</div>
			     					<div style="width: 100%;text-align: center;">
			     						<a onclick="uploadBtn.fileupload('click');" id="upfile"  href="#" 
			     							class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-remove'">上传</a>
										<div id="addPhoto"></div>
			     					</div>
			     				</td> --%>
			    			</tr>
			    			<tr>
			    				<td class="form-cell-1">
			    					<label class="form-label">小区</label>
			     					<input id="comId" name="comId" type="hidden">
			     					<input id="communityName" name="communityName" class="easyui-validatebox form-control" readonly>
			     				</td>
			     				<td class="form-cell-1">
			     					<label class="form-label">楼栋</label>
			     					<input id="buildId" name="buildId" type="hidden"/> 
			     					<input id="ldCode" name="ldCode" class="easyui-validatebox form-control" readonly>
			     				</td>
			     				<td class="form-cell-1">
			     					<label class="form-label">房屋</label>
			     					<input id="houseId" name="houseId"   /> 
			     				</td>
			    			</tr>
			     			<tr>
			     				<td class="form-cell-1">
			    					<label class="form-label">姓名</label>
			     					<input name="householderName" class="easyui-validatebox form-control" data-options="required:true">
			     				</td>
			     				<td class="form-cell-1">
			     					<label class="form-label">与户主关系</label>
			     					<input dictCode="familytree" name="householderRelation"
										class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'" >
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">性别</label>
			    					<input  dictCode="Gender" name="sex" class="easyui-combobox form-control"
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
			    					<label class="form-label">身份证号</label>
			     					<input onblur="addBirthDate(this);" name="cardCode" class="easyui-validatebox form-control" data-options="required:true" validType="checkIdCode">
			     				</td>
			     			
			     				<td class="form-cell-1">
			    					<label class="form-label">出生日期</label>
			     					<input id="birthDate" name="birthDate" class="easyui-datebox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
			     						data-options="required:true,editable:false">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">民族</label>
			     					<input dictCode="Nation"  name="nationality"
			     						class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	valueField:'dictCode',
									    	textField:'dictName'" >
			     				</td>
			     			</tr>
			     			<tr>
			     			 	<td class="form-cell-1">
									<label class="form-label">年龄</label>
									<input id="age" name="age" class="easyui-validatebox form-control" readonly>
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">健康状况</label>
			     					<input dictCode='healthStatus' name="healthStatus"
										class="easyui-combobox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">结婚时间</label>
			     					<input name="maritalData" class="easyui-datebox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
			     						data-options="editable:false">
			     				</td>
			     			</tr>
			     			<tr>
			     				<td class="form-cell-1">
			    					<label class="form-label">婚姻状况</label>
			     					<input dictCode="maritalStatus" name="maritalStatus" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">职业</label>
			     					<input dictCode="JobType" name="jobName" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">节育措施</label>
			     					<input  dictCode="birthControl" name="zjxy" class="easyui-combobox form-control"
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
			    					<label class="form-label">社会职务</label>
			     					<input dictCode="socialFunction" name="socialJob" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">工作单位</label>
			     					<input name="unit" class="easyui-validatebox form-control">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">职务</label>
			     					<input name="dutyName" class="easyui-validatebox form-control">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">特长</label>
									<input dictCode="SpecialityType" name="specialty" class="easyui-combobox form-control"
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
			    					<label class="form-label">兵役状况</label>
			     					<input  dictCode="veteranStatus" name="veteranStatus" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">户籍类别</label>
			     					<input dictCode="residentType" name="householdType" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">户籍地</label>
			     					<input name="householdAddress" class="easyui-validatebox form-control">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">籍贯</label>
			     					<input name="nativePlace" class="easyui-validatebox form-control">
			     				</td>
			     			</tr>
			     			<tr>
			     				<td class="form-cell-1">
			    					<label class="form-label">身高</label>
			     					<input name="sgHeight" class="easyui-validatebox form-control">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">住房面积</label>
			     					<input name="housingArea" class="easyui-validatebox form-control">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">门牌号</label>
			     					<input name="doorplate" class="easyui-validatebox form-control">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">管理类型</label>
			     					<input dictCode="ManageType" name="gllx" class="easyui-combobox form-control"
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
			    					<label class="form-label">文化程度</label>
			     					<input dictCode="Education" name="eduLevel" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">血型</label>
			     					<input dictCode="bloodType" name="bloodType" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">志愿者</label>
			     					<input  dictCode="YesOrNo" name="isVolunteer" class="easyui-combobox form-control"
			     					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			    					<label class="form-label">政治面貌</label>
			     					<input dictCode="politicsStatus" name="politicsStatus" class="easyui-combobox form-control"
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
			    					<label class="form-label">宗教信仰</label>
			     					<input dictCode="Religion" name="religion" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
									    	editable:false,
									    	panelHeight:'auto',
									    	valueField:'dictCode',
									    	textField:'dictName'">
			     				</td>
			     				<td class="form-cell-1">
			     					<label class="form-label">联系电话</label>
									<input name="callPhone" class="easyui-validatebox form-control">
			     				</td>  
			     				<td class="form-cell-2" colspan="2">
			    					<label class="form-label">现居地</label>
			     					<input name="dwellAddress" class="easyui-validatebox form-control">
			     				</td>
							</tr>
						</tbody>
					</table>
				
				</div>
		
			</form>
			<div id="sub" style="height: 100%;">
				<div id="subTabs" class="easyui-tabs" data-options="fit:true,border:false" > 
					<div title="成员">
							<div id="userListToolbar" class="g-toolbar">
								<a id="addUser" class="easyui-linkbutton g-button" onclick="addUser()" data-options="disabled:true"><i class="fa fa-plus"></i>添加</a>
								<a id="delUser" class="easyui-linkbutton g-button" onclick="delUser()" data-options="disabled:true"><i class="fa fa-trash-o"></i>删除</a>
					      	</div>
						 <table id="holderList"  ></table>
					</div>
				</div>
			</div>
			
			<!-- <div style="height:100%">
		       <table id="holderList" style="height:100%;"></table>
		    </div> -->
			
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript">

var holderId;

var thisUiConfig = {
		main : window.main,
		ctx : '${ctx}',
		searchUrl : '${ctx}/householder/searchHousehold'
	}

$(function (){
	tabs.init();
	tableInfo.init();
	publicAttr.initCombobox();
	$(".g-layout").click(function() {
		$("#holderList").datagrid("acceptChanges");
	});
	orgLookup();
	houseLookupLookup();
});

var tabs = {
		
		isEdit : true ,
		
		init : function (){
			
			$("#mainTab").tabs({
				onSelect : function(title, index) {
					if (index == 0) { // 选中列表标签
						enableButtons([ "edit"  ]);
						disableButtons([ "addUser","saveUser","delUser"]);
					} else if (index == 1) { // 选中详细标签
						disableButtons([ "edit"  ]);
						enableButtons([ "addUser","saveUser"]);
						IsDisableForm(false); 
						loadData();
					}
				}
			});
		}
		
}


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
				queryParams:  tableInfo.getFormData("#queryForm"),
				url : thisUiConfig.searchUrl,
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
					title:"是否户主",
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
					var len = $("#listGrid").datagrid("getSelections").length;
					if(len==1){
						$("#mainTab").tabs("enableTab", 1);
						enableButtons([ "edit"  ]);
					}
				},
				onUnselect : function(rowIndex, rowData) {
					var len = $("#listGrid").datagrid("getSelections").length;
					if(len==1){
						$("#mainTab").tabs("enableTab", 1);
					}else if(len==0){
						disableButtons([ "edit"  ]);
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
			
			var dict = main.dict,
			dicts = dict.dicts;
			
			$('#holderList').datagrid({
				rownumbers : true,
				singleSelect : false,
				border : false,
				pageSize : defaultPageSize,
				pageList : defaultPageList,
				pagination : false,
				columns : [[
					{field: '', checkbox: true},
					{field: 'relationId',  title: 'ID' ,hidden: true},
					{field: 'householderId', title: '人员ID', halign: 'center', hidden: true},
					{field: 'followId', title: '关联ID', halign: 'center', hidden: true},
					{field: 'householderName', title: '姓名', width : 100 , halign: 'center'},
					{field: 'cardCode', title: '身份证', width : 150 , halign: 'center'},
					{
						field: 'holderRelationship', 
						title: '与房主关系', 
						halign: 'center', 
						width:100,
						formatter: function(value,row,index) {

							return publicAttr.getDictText("familytree",value);
						},
						editor: {
							type: 'combobox',
							options: {
								valueField: 'dictCode',
								textField: 'dictName',
								editable: false,
								panelHeight: 'auto',
								data: dicts.familytree.list
							}
						}
					}
				]],
				onCheck: function(row) {
					enableButtons(["addUser","delUser"]);
				},
				onUncheck: function(row) {
					var rows = $('#holderList').datagrid("getSelections");
					if(rows.length>0){
						enableButtons([ "delUser"  ]);
					}else{
						disableButtons([ "delUser" ]);
					}
				},
				onCheckAll: function(rows) {
					enableButtons([ "delUser"  ]);
				},
				onUncheckAll: function(rows) {
					disableButtons([ "delUser" ]);
				},
				onDblClickCell : function (index,field,value){
					$('#holderList').datagrid('acceptChanges');
					$('#holderList').datagrid('beginEdit', index);
				},
				onLoadSuccess : function (data){
				/* 	var rows = data.rows;
					if(!rows  || rows.length==0 ){
					 	var r = $("#listGrid").datagrid("getSelected");
					 	r['holderRelationship'] = r.householderRelation;
					 	r['followId'] = r.householderId;
				 	 	$("#holderList").datagrid("appendRow",r);
				   } */
				}
			});
		},
		
		query : function (){
			var param = tableInfo.getFormData("#queryForm");
			$("#listGrid").datagrid("load", param);
			disableButtons(["edit"]);
			$("#mainTab").tabs("disableTab", 1);
		},
		
		getFormData : function (formId){
			var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
			var data =  getFormData(formId) || {};
			if(meshIds){
				data['q_orgId_IN'] = meshIds;
			}
			return data;
		}
	}


function  gethouseholderIds(){
	var array ={};
	var holder = $("#holderList").datagrid("getRows");
	for(var i in holder){
		array[holder[i].householderId] = holder[i];
	}
	return array ;
}

function  loadData(){
	var r = $("#listGrid").datagrid("getSelected");
	if(r){
		var id = r.householderId;
		loadHouseHolder(id);
		$("#holderList").datagrid({
			queryParams: {
				'q_followId_EQ': id
			},
			url: thisUiConfig.ctx + "/hoRelationShip/search"
		});
	}
}

//添加用户
function addUser() {
	
	var householderArray = gethouseholderIds();
	
	main.dialog({
		id: 'userframe',
		title: "选择用户",
		url: '${ctx}/householder/holderList',
		width: 700,
		height: 425,
		cache: false,
		modal: true,
		contentWindow : {
			params: {
				singleSelect : false,
				checkbox : true,
				householderArray:householderArray
			},
			actions: {
				confirm : function(data) {
					
					var h = $("#listGrid").datagrid("getSelected"); 
					var old_rows = $("#holderList").datagrid("getRows");
					var householderId = h.householderId;
					var obj = $("#holderList");
					
					for(var j in old_rows){
						var r =  old_rows[j];
						var o = data[r.householderId];
						if(!o){
							 var ind = $('#holderList').datagrid("getRowIndex",r);
							 $('#holderList').datagrid("deleteRow",ind);
						}
					}
					
					for(var i in data){
            			var row = data[i];
            			var f = true;
            			for(var j in old_rows){
            				var orow =  old_rows[j];
            				if(orow.householderId == row.householderId){
            					f=false;
            					break;
            				}
            			}
            			if(f){
            				var d = {};
            				d['householderId'] = row.householderId;
            				d['followId'] = row.householderId;
            				d['holderRelationship'] = row.holderRelationship;
            				d['householderName'] = row.householderName;
            				d['cardCode'] = row.cardCode;
                			obj.datagrid("appendRow",d);
            			}
            		}
					
					enableButtons([ "saveUser"]);
					
					main.closeDialog();
				},
				cancel: function() {
					main.closeDialog();
				}
			}
		},
		onBeforeOpen : function() {
		
			return true;
		}
	});
}

function editUser(){
	var tab = $('#mainTab').tabs('getSelected');
	var  index = $('#mainTab').tabs('getTabIndex',tab);
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
}

function delUser(){
	var rows = $('#holderList').datagrid("getSelections");
	if(rows && rows.length>0){
		main.confirm("温馨提示","确定删除选择的数据？",function (r){
			if(r){
				var ids = "";
				for(var j in rows){
					 var row = rows[j];
					 var relationId = row.relationId;
					 if(relationId){
						 ids = ids + relationId + ",";
					 }
					var ind = $('#holderList').datagrid("getRowIndex",row);
					 $('#holderList').datagrid("deleteRow",ind);
				}
				
				if(ids){
					 ids = ids.substring(0,ids.length-1);
					 publicFormControl.ajax({
						url : thisUiConfig.ctx + "/hoRelationShip/delList" ,
						data : {
							ids : ids
						},
						type: "POST"
					},function (data){
						
					},"show");
				}
				 
			}
			
		});
		
	}else{
		main.info("温馨提示","请选择删除的内容！");
	}
}

function saveUser(){
	
	$("#holderList").datagrid("acceptChanges");
	var hid = $("#listGrid").datagrid("getSelected").householderId ;
	var rows = $('#holderList').datagrid("getRows");
	var data = {};
	var dataList = [];
	for(var i in rows){
		var d = {};
		var row = rows[i];
		d['relationId'] = row.relationId;
		d['householderId'] = row.householderId;
		d['followId'] = hid ;
		d['holderRelationship'] = row.holderRelationship;
		dataList.push(d);
	}
 
	data['dataList'] =  JSON.stringify(dataList);
	data['id'] =  hid;
	
	publicFormControl.ajax({
		data: data,
		type: "POST",
		url : thisUiConfig.ctx + "/hoRelationShip/save"
	},function (result){
		
		$('#holderList').datagrid("loadData",result.rows);
		
	},'show');
	
}

function  loadHouseHolder(holderId){
	if(holderId){
		var ajax = publicFormControl.ajax({
			type : "POST",
			url :thisUiConfig.ctx + "/householder/get/" + holderId
		},function (result){
			var row = result.row ;
			$("#dataForm").form("load",row);
			$("#houseId").lookup("setTxt",row.dyCode);
			$("#orgId").lookup('setTxt',publicAttr.getOrgName(row.orgId));
			$("#houseId").lookup("disable");
			$("#orgId").lookup("disable");
			IsDisableForm(true);
		});
	}
}

//设置表单是否可编辑
function IsDisableForm(fal){
	if(fal){
		$("#dataForm").form("disableValidation");
		disableFormItem("#dataForm",null);
	}else{
		enableFormItem("#dataForm",null);
	}
}

function showImg(){
	var photofileID = $("#photofileID").val();
	var  imgUrl ="";
	if(photofileID && photofileID>0){
		imgUrl = thisUiConfig.ctx + "/doc/show/"+ photofileID
	}else{
		imgUrl = thisUiConfig.ctx + "/resources/images/base/1.png";
	}
	$("#photofileUrl").attr("src",imgUrl);
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

function  getOrgid() {
	return $('#orgId').lookup('getVal');
}

var houseLookup;
function houseLookupLookup() {
	houseLookup = $("#houseId").lookup({
		title: "选择房屋",
		url: thisUiConfig.ctx + "/house/select-house",
		width: 700,
		height: 440,
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
			actions: {
				confirm: function(data) {
					if (data) {
						var houseId = data.houseId;
						var buildId = data.buildId;
						var comId = data.comId;
						var ldCode = data.ldCode;
						var dyCode = data.dyCode;
						var communityName = data.communityName;
						$("#houseId").lookup("setVal",houseId);
						$("#houseId").lookup("setTxt",dyCode);
						$("#buildId").val(buildId);
						$("#comId").val(comId);
						$("#communityName").val(communityName);
						$("#ldCode").val(ldCode);
						$("#dyCode").val(dyCode);
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

</script>

</html>