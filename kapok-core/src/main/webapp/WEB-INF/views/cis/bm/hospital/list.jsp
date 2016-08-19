<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>医院信息</title>
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
	<a onclick="baseInfo.add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

     <div title="列表" style="position:relative;">
     
		<div id="query">
				<form id="queryForm" class="formCls" method="post">
					<table  class="g-form" cellpadding="0" cellspacing="0" >
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">医院名称</label>
	            				<input name="q_yymc_LIKE" class="easyui-validatebox form-control" /></td>
	            			<td class="form-cell-1">
	            				<label class="form-label">地址</label>
	            				<input name="q_dz_LIKE" class="easyui-validatebox form-control" /></td>
							<td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
							</td>
							<td class="form-cell-1"></td>
							<td class="form-cell-1"></td>
						</tr>
					</table>
				</form>
			</div>
	         
        <div class="list-area" style="top:50px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
		
     </div>
     
     <div id="minute" data-options="disabled:true"  title="详细"   >
      
    		<form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="hospitalId" name="hospitalId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		
    		<table>
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">所属机构</label>
     					 <input id="ssjg" name="ssjg"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">医院名称</label>
     					<input name=yymc class="easyui-validatebox form-control" required="required"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">医院等级</label>
     					<input name="yydj" class="easyui-validatebox form-control" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">医院床位</label>
     					<input  name="yycw" class="easyui-validatebox form-control" />
     				</td>
     			</tr>
     			
    			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">占地面积(m2)</label>
     					<input name="zdmj" class="easyui-validatebox form-control" />
     				</td>
     				
     				<td class="form-cell-1">
	            		<label class="form-label">法人</label>
     					<input name="fr" class="easyui-validatebox form-control" />
     				</td>
     				
     				<td class="form-cell-2" colspan="2">
	            		<label class="form-label">地址</label>
     					<input  name="dz" class="easyui-validatebox form-control" />
     				</td>
     			</tr>
     			 
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">法人联系电话</label>
     					<input name="frdh" class="easyui-validatebox form-control" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">党组织负责人</label>
     					<input name="dzzfzr" class="easyui-validatebox form-control" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">联系电话</label>
     					<input name="dzzfzrdh" class="easyui-validatebox form-control" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">党员人数</label>
     					<input name="dyrs" class="easyui-validatebox form-control"  />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">工会组织负责人</label>
     					<input name="ghzzfzr" class="easyui-validatebox form-control" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">工会负责人电话</label>
     					<input name="ghzzfzrdh" class="easyui-validatebox form-control" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">工会会员人数</label>
     					<input name="ghhyrs" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">保卫处负责人</label>
     					<input name="bwcfzr" class="easyui-validatebox form-control" />
     				</td>
     			</tr>
     		 
     		 	<tr>
     		 		<td class="form-cell-1">
	            		<label class="form-label">保卫处联系电话</label>
     		 			<input name="bwcdh" class="easyui-validatebox form-control" />
     		 		</td>
     		 		<td class="form-cell-1">
	            		<label class="form-label">医政处负责人</label>
     		 			<input name="yzcfzr" class="easyui-validatebox form-control" />
     		 		</td>
     		 		<td class="form-cell-1">
	            		<label class="form-label">医政处联系电话</label>
     					<input name="yzcfzrdh" class="easyui-validatebox form-control"   />
     				</td>
					<td class="form-cell-1">
	            		<label class="form-label">急诊室电话</label>
     					<input name="jzsdh" class="easyui-validatebox form-control" />
     				</td>
     		 	</tr>
     		 	<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">电子监控安装情况</label>
     						<input name="dzjkaz" class="easyui-combobox form-control"
     								style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName',
	 								    	url:'${ctx}/dict/getdict/IsIntall'">
     				</td>
     			</tr>
     		
     			
     			<tr>
     				<td class="form-cell-4" colspan="4">
	            		<label class="form-label">防火设备</label>
     					<textarea class="easyui-validatebox form-control" name="fhsb"  ></textarea>
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4" colspan="4">
     					<label class="form-label">防盗设施</label>
     					<textarea class="easyui-validatebox form-control" name="fdss"  ></textarea>
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4" colspan="4">
	            		<label class="form-label">文明单位情况</label>
     					<textarea name="wmdwqk" class="easyui-validatebox form-control" rows="5"></textarea>
     				</td>
     			</tr>
     		</table>
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
<script type="text/javascript" >


var thisUiConfig = {
	main:window.main,
	ctx : "${ctx}",
	url : "hospital",
	id : "#hospitalId",    //表单中主键字段对应的控件的Id属性值
	idName:"hospitalId",
	tableList : "#listGrid",
	getId : function (){
		var schoolId = $(thisUiConfig.id).val();
		return schoolId;
	}
}

$(function (){
	
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	
});

function showName(node){
	
	return  node.orgName;
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
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/del/",
	
	search : thisUiConfig.ctx + "/" + thisUiConfig.url + "/search ",
		
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
					var row = result.row ;
					$("#baseForm").form("load",row);
					$("#ssjg").lookup('setTxt',publicAttr.getOrgName(row.ssjg));
					tableInfo.query();
					enableButtons([ "add", "save" ]);
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
		
		$.messager.confirm("温馨提示", "确定删除医院名称为【"+row.yymc+"】记录?",
			function(r) {
				if (r) {
					 
					publicFormControl.ajax({
						url : baseInfo.delUrl + row[thisUiConfig.idName],
						type: "POST",
					},function (data){
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$("#mainTab").tabs("select", 0);
							$("#mainTab").tabs("disableTab", 1);
							tableInfo.query();
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
				url : baseInfo.getUrl + id
			},function (result){
				var row =  result.row;
				$("#baseForm").form("load",row);
				$("#ssjg").lookup('setTxt',publicAttr.getOrgName(row.ssjg));
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
				field : "hospitalId",
				hidden:true,
			},{
				field : "ssjg",
				title : "所属机构",
				width : 150,
				halign:'center',
				align:'left',
				formatter : function (value,row,index){
						
					return publicAttr.getOrgName(value);
				}
			},{
				field : "yymc",
				title : "医院名称",
				width : 200,
				halign:'center',
				align:'left'
			},{
				field:"yydj",
				title:"医院等级",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"fr",
				title:"法人",
				width:150,
				halign:'center',
				align:'left'
			},{
				field:"frdh",
				title:"联系电话",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"dz",
				title:"地址",
				width:250,
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
		$("#listGrid").datagrid("load", param);
		enableButtons([ "add" ]);
		$("#mainTab").tabs("disableTab", 1);
	},
	
	getQueryFormData : function (formId){
		var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
		var data =  getFormData(formId) || {};
		if(meshIds){
			data['q_ssjg_IN'] = meshIds;
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
	
	orgLookup = $('#ssjg').lookup({
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
	$("#ssjg").lookup("setTxt", publicAttr.getOrgName(meshId));
	$("#ssjg").lookup('setVal', meshId);
}

 

</script>

</body>
</html>