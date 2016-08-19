<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>小区信息</title>
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

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

     <div title="列表" style="position:relative;">
         
		<div id="query" >
				<form id="queryForm" class="formCls" method="post">
					<table  class="g-form" cellpadding="0" cellspacing="0" >
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">小区名称</label>
	            				<input class="easyui-validatebox form-control" name="q_communityName_LIKE"  />
	            			</td>
							<td class="form-cell-1">
		            			<label class="form-label">治安乱点</label>
		            			<input dictCode="YesOrNo" name="q_zald_EQ" class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
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
	    		<input id="comId" name="comId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		
    		<table class="g-form" cellpadding="0" cellspacing="0" >
    			<tbody>
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">所属机构</label>
	            		<input id="org" name="org">
	          		</td>
     				<td class="form-cell-1">
     					<label class="form-label">小区名称</label>
     					<input class="easyui-validatebox form-control" required="required"  name="communityName"  />
     				</td>
     				<td class="form-cell-1">
     					<label class="form-label">住户数</label>
     					<input class="easyui-validatebox form-control"  name="zhs"  validType="checkNumber"  />
     				</td>
     				<td class="form-cell-1">
     					<label class="form-label">居民人数</label>
     					<input  class="easyui-validatebox form-control" name="jmrs" validType="checkNumber"  />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-1">
     					<label class="form-label">楼栋数</label>
     					<input class="easyui-validatebox form-control"  name="lds" type="text"  validType="checkNumber" />
     				</td>
     				<td class="form-cell-1">
     					<label class="form-label">车棚数</label>
     					<input class="easyui-validatebox form-control" name="cps" type="text" validType="checkNumber" />
     				</td>
     				<td class="form-cell-1">
     					<label title="文体活动场所" class="form-label">文体活动场所</label>
     					<input class="easyui-validatebox form-control"  name="wthdcs" type="text"    />
     				</td>
     				<td class="form-cell-1">
     					<label title="公共健身器材" class="form-label">公共健身器材</label>
     					<input class="easyui-validatebox form-control" name="ggjsqc" type="text"     />
     				</td>
     				
     			</tr>
     		 
     		 	<tr>
     				<td  class="form-cell-1">
     					<label class="form-label">清扫人员  </label>
     					<input class="easyui-validatebox form-control"  name="qsry"   />
     				</td>
     				<td  class="form-cell-1">
     					<label title="是否有社区警务室" class="form-label">是否有社区警务室</label>
     					<input dictCode="YesOrNo" name="sfysqjw"  class="easyui-combobox form-control"
     							style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
								    	editable:false,
								    	panelHeight:'auto',
								    	valueField:'dictCode',
								    	textField:'dictName'" />
     				</td>
     				<td  class="form-cell-1">
     					<label title="是否有值班室" class="form-label">是否有值班室 </label>
     					<input  dictCode="YesOrNo" name="sfyzbs" class="easyui-combobox form-control"
     							style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td  class="form-cell-1">
     					<label class="form-label">电子监控 </label>
     					<input dictCode="IsIntall" name="dzjk" class="easyui-combobox form-control"
     							style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     			</tr>
     			
     			<tr>
     				<td  class="form-cell-1">
     					<label class="form-label">封闭情况</label>
     					<input  dictCode="FBQK" name="fbqk" class="easyui-combobox form-control"
     							style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td  class="form-cell-1">
     					<label class="form-label">治安乱点</label>
     					<input  dictCode="YesOrNo" name="zald"  class="easyui-combobox form-control"
     							style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
     				</td>
     				<td class="form-cell-2" colspan="2" >
     					<label title="物业公司名称" class="form-label">物业公司名称</label>
     					<input class="easyui-validatebox form-control" name="wygsmc" type="text"  />
     				</td>
     				
     			</tr>
     			
     			<tr>
     				<td  class="form-cell-1">
     					<label class="form-label">物业负责人</label>
     					<input class="easyui-validatebox form-control" name="wyfzr" />
     				</td>
     				<td  class="form-cell-1">
     					<label class="form-label">负责人电话</label>
     					 <input class="easyui-validatebox form-control" name="fzrdh"   />
     				</td>
     				<td  class="form-cell-1">
     					<label class="form-label">保安人数</label>
     					 <input validType="checkNumber" class="easyui-validatebox form-control"  name="bars"  />
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-4" colspan="4">
     					<label class="form-label">防火设施</label>
     					<textarea class="easyui-validatebox form-control" name="fhss" ></textarea>   
     				</td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-4" colspan="4" >
     					<label class="form-label">防盗措施</label>
     					<textarea class="easyui-validatebox form-control" name="fdcs"  ></textarea>   	
     				</td>
     			</tr>
     		  	<tr>
     				<td  class="form-cell-4" colspan="4">
     					<label class="form-label">卫生设施情况</label>
     					<textarea name="wsssqk" class="easyui-validatebox form-control" ></textarea>
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4" colspan="4">
     					<label class="form-label">宣传设施情况</label>
     					<textarea name="xcssqk" class="easyui-validatebox form-control" ></textarea>
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
	main : window.main,	
	ctx : "${ctx}",
	url : "community",
	id : "#comId",    //表单中主键字段对应的控件的Id属性值
	idName:"comId",
	tableList : "#listGrid",
	getId : function (){
		var comId = $(thisUiConfig.id).val();
		return comId;
	}
}


$(function (){
	tabs.init();
	enableButtons(['add']);
	publicAttr.initCombobox();
	tableInfo.init();
	baseInfo.init();
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

//基础信息初始化
var baseInfo =  {
		
	saveUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",
	
	getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/softDel/",
	
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
				
				$.messager.confirm("温馨提示", "确定删除小区信息记录?",
					function(r) {
						if (r) {
							
							publicFormControl.ajax({
								url : baseInfo.delUrl + row.comId,
								type: "POST"
							},function(data){
								if (data.resultCode == "0") {
									$("#baseForm").form("clear");
									$("#mainTab").tabs("select", 0);
									$("#mainTab").tabs("disableTab", 1);
									enableButtons([ "add" ]);
									tableInfo.query();
								} else if(data.resultCode == "2"){
									thisUiConfig.main.info("温馨提示", data.resultMsg + " , 小区【"+row.communityName+"】下存在楼栋或者房屋信息！ ");
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
				url : baseInfo.getUrl + id,
			},function(result){
				var row =  result.row;
				$("#baseForm").form("load",row);
				$("#org").lookup("setTxt",publicAttr.getOrgName(row.org));
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
			url : "${ctx}/community/search",
			columns : [ [ {
				field : "comId",
				hidden:true,
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
				field:"zald",
				title:"治安乱点",
				width:100,
				halign:'center',
				align:'left',
				formatter : function (value,row,index){
					 
					return publicAttr.getDictText("YesOrNo",value);
				}
			},{
				field:"dzjk",
				title:"电子监控",
				width:100,
				halign:'center',
				align:'left',
				formatter : function (value,row,index){
					
					return publicAttr.getDictText("IsIntall",value);
				}
			},{
				 field:"wygsmc",
				 title:"物业公司名称",
				 width:150,
				 halign:'center',
				 align:'left'
			},{
				field:"wyfzr",
				title:"物业负责人",
				width:150,
				halign:'center',
				align:'left'
			},{
				field:"fzrdh",
				title:"联系电话",
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
		$("#mainTab").tabs("disableTab", 1);
		var param = tableInfo.getQueryFormData("#queryForm");
		$("#listGrid").datagrid("load", param);
		enableButtons([ "add" ]);
		
	},
	
	getQueryFormData : function (formId){
		console.log(thisUiConfig.main);
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

function defaultMesh(){
	var meshId = main.currentUserMesh.meshId;
	$("#org").lookup("setTxt", publicAttr.getOrgName(meshId));
	$("#org").lookup('setVal', meshId);
}

</script>

</body>
</html>