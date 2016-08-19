<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>单位信息</title>
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
	            				<label class="form-label">单位名称</label>
	            				<input   name="q_dwmc_LIKE" class="easyui-validatebox form-control"   /></td>
							<td class="form-cell-1">
	            				<label class="form-label">法人代表</label>
	            				<input   name="q_frdb_LIKE" class="easyui-validatebox form-control"      /></td>
							<td class="form-cell-1">
	            				<label class="form-label">是否新经济组织</label>
	            				<input dictCode="YesOrNo" name="q_isXjjzz_EQ" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" /></td>
							<td class="form-cell-1">
	            				<label class="form-label">是否新社会组织</label>
								<input dictCode="YesOrNo" name="q_isXshzz_EQ" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
							</td>
							
						</tr>
						<tr>	
							<td class="form-cell-1" ></td>
							<td class="form-cell-1"></td>
							<td class="form-cell-1"></td>
							<td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
	         
     	 <div class="list-area" style="top:80px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
		
     </div>
     
     <div id="minute" data-options="disabled:true"  title="详细"   >
      
    		<form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="unitId" name="unitId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		
    		<table class="g-form" cellpadding="0" cellspacing="0" >
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">所属机构</label>
     					 <input id="org" name="org"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">单位名称</label>
     					<input required="required" class="easyui-validatebox form-control"  name="dwmc"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">单位性质</label>
     						<input dictCode="unitNature" name="dwxz" class="easyui-combobox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">是新经济组织</label>
     					<input dictCode="YesOrNo" name="isXjjzz" class="easyui-combobox form-control"
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
	            		<label class="form-label">是新社会组织</label>
     					<input dictCode="YesOrNo" name="isXshzz" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">占地面积(m2)</label>
     					<input name="zdmj" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">法人代表</label>
     					<input name="frdb" class="easyui-validatebox form-control"     />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">法人联系电话</label>
     					<input name="frdh" class="easyui-validatebox form-control"    />
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">党支部书记</label>
     					<input name="dzbsj" class="easyui-validatebox form-control"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">党支部书记联系电话</label>
     					<input name="dzbsjdh" class="easyui-validatebox form-control"   />
     				</td>
     				<td class="form-cell-1">
     					<label class="form-label">工会主席</label>
     					<input name="ghzx" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">工会主席联系电话</label>
     					<input name="ghzxdh" class="easyui-validatebox form-control"  />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">工会会员人数</label>
     					<input name="ghhyrs" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">安全负责人</label>
     					<input name="aqfzr" class="easyui-validatebox form-control"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">安全负责人电话</label>
     					<input name="aqfzrdh" class="easyui-validatebox form-control"    />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">员工人数</label>
     					<input name="ygrs" class="easyui-validatebox form-control"  />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">党员人数</label>
     					<input name="dyrs" class="easyui-validatebox form-control"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">防盗门安装情况</label>
     						<input dictCode="IsIntall" name="fdmaz" class="easyui-combobox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">电子监控安装情况</label>
     						<input dictCode="IsIntall" name="dzjkaz" class="easyui-combobox form-control"
										style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">电话报警安装情况</label> 
     						<input dictCode="IsIntall" name="dhbjaz" class="easyui-combobox form-control"
     									style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4" colspan="4">
	            		<label class="form-label">单位详细地址</label>
     					<input  name="dwxxdz"   class="easyui-validatebox form-control"    />
     				</td>
     			</tr>
     			
     			
     			<tr>
     		 		<td class="form-cell-4" colspan="4">
     					<label class="form-label">防火设备</label>
     					 <textarea name="fhsb"  class="easyui-validatebox form-control" rows="5" ></textarea>
     				</td>
     			</tr>
     			
     			<tr>
     		 		<td class="form-cell-4" colspan="4">
     					<label class="form-label">防盗设施</label>
     					 <textarea name="fhss"  class="easyui-validatebox form-control" rows="5"></textarea>
     				</td>
     			</tr>
     			
     			<tr>
     		 		<td class="form-cell-4" colspan="4">
     					<label class="form-label">文明单位情况</label>
     					 <textarea name="wmdwqk"  class="easyui-validatebox form-control" rows="5" ></textarea>
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
	url : "units",
	id : "#unitId",    //表单中主键字段对应的控件的Id属性值
	idName:"unitId",
	tableList : "#listGrid",
	getId : function (){
		var unitId = $(thisUiConfig.id).val();
		return unitId;
	}
}

$(function (){
	updateQueryParam();
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	orgLookup();
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
	var dwxz = $.getUrlParam("dwxz");
	if(dwxz){
		$("#queryForm").append("<input name='q_dwxz_EQ' value='"+ dwxz +"' type='hidden'>");
	}
}

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
						
					} else if (index == 1) { // 选中详细标签
						loadData();
						if(tabs.isEdit){
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
	
	getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/del/",
	
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
					var row = result.row ;
					$("#baseForm").form("load",row);
					$("#orgName").val(publicAttr.getOrgName(row.org));//添加名称
					tableInfo.query();
					enableButtons([ "add", "save" ]);
				},"show");
				
			}
		}else{
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
		
		$.messager.confirm("温馨提示", "确定删除单位名称为【"+row.dwmc+"】记录?",
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
							tableInfo.query();
							enableButtons([ "add" ]);
							
						}else {
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
	}
};


function  loadData(){
	var row = $(thisUiConfig.tableList).datagrid("getSelected");
	if(row){
		var id = row[thisUiConfig.idName];
		if(id){
			$.ajax({
				type : "POST",
				url : baseInfo.getUrl +  id,
			}).done(function(result) {
				$("#baseForm").form("load",result.row);
				$("#org").lookup("setTxt",publicAttr.getOrgName(row.org))
				setFormItemDisabled("#baseForm",true,null);
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
				field : "unitId",
				hidden:true,
			},{
				field : "org",
				title : "所属机构",
				width : 150,
				halign:'center',
				align:'left',
				formatter : function (value,row,index){
						
					return publicAttr.getOrgName(value);
				}
			},{
				field : "dwmc",
				title : "单位名称",
				width : 200,
				halign:'center',
				align:'left'
			},{
				field:"frdb",
				title:"法人代表",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"frdh",
				title:"联系电话",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"dzjkaz",
				title:"电子监控",
				width:100,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){

					return publicAttr.getDictText("IsIntall",value);
				}
			},{
				field:"fdmaz",
				title:"防盗门安装情况",
				width:150,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){

					return publicAttr.getDictText("IsIntall",value);
				}
			},{
				field:"dhbjaz",
				title:"电话报警安装情况",
				width:150,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){

					return publicAttr.getDictText("IsIntall",value);
				}
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

/**
 *  默认网格
 */
function defaultMesh(){
	var meshId = main.currentUserMesh.meshId;
	$("#org").lookup("setTxt", publicAttr.getOrgName(meshId));
	$("#org").lookup('setVal', meshId);
}
 

</script>

</body>
</html>