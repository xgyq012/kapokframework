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
	    <a id="add" onclick="baseInfo.add();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	    <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	    <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	    <a id="del" onclick="baseInfo.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	</div>
	<div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
	     <div title="列表" style="position:relative;">
		     <div class="query">
			    <form id="queryForm" class="formCls" method="post">
					  <table class="g-form" cellpadding="0" cellspacing="0">
						 <tr>	
							 <td class="form-cell-1">
							     <label class="form-label">应急预案名称</label>
							     <input name="q_name_LIKE"  class="form-control"/>
							 </td>						 
							 <td class="form-cell-1">
							     <label class="form-label">应急事件类型</label>
							     <input name="q_yjsjlxName_LIKE"  class="form-control"/>
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
				<table id="listGrid" style="height:100%;"></table>
			 </div>
	     </div>
	     
	     
	     <!-- data-options="disabled:true" -->
	     <div id="minute" title="详细" data-options="disabled:true">
		     <form id="baseForm"  action="">
		     	<fieldset>
		     	<legend>基础信息 </legend>
    			<input id="yingjiPlanId" name="yingjiPlanId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				<input id="delSign" name="delSign" type="hidden" />
				<input type="hidden" id="yjsjlxId" name="yjsjlxId" />
    		    <table class="g-form" cellpadding="0" cellspacing="0" >
     			    <tr>
     			   	    <td class="form-cell-3" colspan="3">
	     				   <label class="form-label">应急预案名称</label>
	     				   <input id="name" name="name" class="easyui-validatebox form-control"
								data-options="validType:'length[4,64]', required:true">
	     				</td>
     			   	    <td class="form-cell-1">
	     				   <label class="form-label">应急事件类型</label>
	     				   <input id="yjsjlxName" name="yjsjlxName" class="easyui-validatebox form-control">
	     				</td>     				
     			    </tr>
     			    <tr>
     			        <td class="form-cell-4" colspan="4">
     			        	<label class="form-label">应急预案描述</label>
     				    	<textarea rows="8"  name="planBody" class="easyui-validatebox form-control" ></textarea>
    				    	</td>
     			    </tr>
     		    </table>
     			</fieldset>
     		 </form>
     		 
 		 	 <div style="height:350px;margin:15px 15px 0 15px">
    			<div id="buttonbar" class="infoBtn fujianBut" style="padding: 4px 0;">
					<a id="addPlanRenyuanBut" onclick="planRenyuanTable.add();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
					<a id="delPlanRenyuanBut" onclick="planRenyuanTable.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
					<div id="addFile"></div>
		      	</div>
		        <table id="planRenyuanTable" style="height:100%;" title="应急人员列表"></table>
			 </div>
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
var addNewRecord = false; //新增记录？
var editRecord = false;
var thisEditRecord;
var thisUiConfig = {
	ctx : "${ctx}",
	url : "yingjiplan",
	id : "#yingjiPlanId",    //表单中主键字段对应的控件的Id属性值
	idName:"yingjiPlanId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var yingjiShijianId = $(thisUiConfig.id).val();
		return yingjiShijianId;
	}
};

$(function (){
	tabs.init();
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	$(".infoBtn span.lookup").hide();
	planRenyuanTable.init();
});

var tabs = {
		thisTabIndex : 0,
		init : function (){
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
			$(thisUiConfig.mainTab).tabs({
				//切换标签时调用
				onSelect : function(title, index) {
					tabs.thisTabIndex = index;
					
					if (index == 0) { // 选中列表标签
						setFormItemDisabled(thisUiConfig.baseForm, true, null);
						setFormItemDisabled(thisUiConfig.queryForm, false, null);
						$(thisUiConfig.baseForm).form("clear");
						
						if ($(thisUiConfig.tableList).datagrid("getSelections").length > 0) {
							$(thisUiConfig.mainTab).tabs("enableTab", 1);
						} else {
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
						}
						editRecord = false;
						enableButtons();
					} else if (index == 1) { // 选中详细标签
						var row = $(thisUiConfig.tableList).datagrid("getSelected");
						if (row) {
							$(thisUiConfig.baseForm).form("load",row);
							$("#planRenyuanTable").datagrid("loadData", row.planRenyuanList);
							$("#yjsjlxName").lookup("setTxt", row.yjsjlxName);
						}
					}
					
				}
			});
		}
};

function enableButtons() {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	
	
	//选中了多少条记录？
	var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
	
	//只选中了一条记录
	if(len == 1){
		//编辑选中的记录
		if(editRecord){
			$("#add, #save,#del").linkbutton("enable");
		}else{
			$("#add,#del,#edit").linkbutton("enable");
		}
	}
	
	//没有选中记录
	else if(len == 0){
		$("#add").linkbutton("enable");
		if(editRecord){
			$("#save").linkbutton("enable");
		}
	}
	
	//选中了多条记录
	else{
		
	}
	
	//在编辑记录时，而且已经切换到详情页时  设置其它信息的按钮的可用性
	if(tabs.thisTabIndex == 1 && editRecord){
		$("#addPlanRenyuanBut").linkbutton("enable");
		len = $("#planRenyuanTable").datagrid("getSelections").length;
		if(len > 0){
			$("#delPlanRenyuanBut").linkbutton("enable");
		}
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
				
				var rows = $('#planRenyuanTable').datagrid("getRows");
				rows.forEach(function(x, i) {
					x.yingjiPlanId = result.row.yingjiPlanId;
					delete x.yingjirenName;
					delete x.yingjirenSex;
					delete x.yingjirenIdcard;
					delete x.zaizhiStatus;
					delete x.phone;
					delete x.email;
					delete x.ps;
					delete x.zhiwei;
					delete x.address;
					
					delete x.creater;
					delete x.createrId;
					delete x.lastUpdater;
					delete x.lastUpdaterId;
				});
				
				//保存应急人员和
				$.ajax({
					type: 'post',
					url: '${ctx}/yingjiplan/saveplanrenyuan',
					data:{
						planRenyuanJson: JSON.stringify(rows)
					}
				}).done(function(){
					tableInfo.refresh();
					$.messager.show({
						title : "温馨提示",
						msg : "操作成功",
						timeout : 2500,
						showType : "slide"
					});
				}).fail(function(){
					tableInfo.refresh();
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
		
		tableInfo.thisRow = "";
		tableInfo.thisRowIndex = -1;
		
		//clear data
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$("#planRenyuanTable").datagrid("loadData", { total: 0, rows: [] });
		$(thisUiConfig.tableList).datagrid("unselectAll");
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);	
		}
		setFormItemDisabled(thisUiConfig.baseForm, false, null);
		enableButtons();
	},
	
	del:function (){
		var row = $(thisUiConfig.tableList).datagrid('getSelections');
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的应急预案信息?",
			function(r) {
				if (r) {
					var ids = "";
					$.each(row, function (i, n){
						ids = ids + n[thisUiConfig.idName] + ",";
					});
					ids = ids.substring(0, ids.length - 1);
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
							$(thisUiConfig.mainTab).tabs("select", 0);
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
									
							tableInfo.thisRow = "";
							tableInfo.thisRowIndex = -1;
							
							// 重新获取数据
							tableInfo.refresh();
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
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);
		}
		enableButtons();
		setFormItemDisabled(thisUiConfig.baseForm, false, null);
	}
};


//列表加载
var tableInfo =  {
	thisRow : "",
	thisRowIndex : 0,
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
				field : "yingjiPlanId",
				hidden:true
			}, {
				field : "name",
				halign:'center',
				align:'left',
				title : "应急预案名称",
				width : 220
			},{
				field:"yjsjlxName",
				halign:'center',
				align:'left',
				title:"应急事件类型",
				width:210
			},{
				 field:"planBody",
				 title:"应急预案描述",
				 halign:'center',
				 align:'left',
				 width:420
			}]],

			onSelect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onUnselect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
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
				}else{
					enableButtons();
				}
				
				if(tabs.thisTabIndex == 0 && tableInfo.thisRow){
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
			}
			
		});
		
		// 设置分页显示形式
		$(thisUiConfig.tableList).datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	query : function (){
		tableInfo.thisRow = "";
		tableInfo.thisRowIndex = -1;
		var param = getFormData("#queryForm");
		$(thisUiConfig.tableList).datagrid("load", param);
	},
	onSelectOrUnselect : function(rowIndex, rowData){
		var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
		if(len == 1){
			tableInfo.thisRow = $(thisUiConfig.tableList).datagrid("getSelected");
			tableInfo.thisRowIndex = $(thisUiConfig.tableList).datagrid("getRowIndex", tableInfo.thisRow);
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			if(tabs.thisTabIndex == 1){
				$(thisUiConfig.baseForm).form("load",tableInfo.thisRow);
				$("#planRenyuanTable").datagrid("loadData", tableInfo.thisRow.planRenyuanList);
				$("#yjsjlxName").lookup("setTxt", tableInfo.thisRow.yjsjlxName);
			}
		}else{
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
		}
		
		enableButtons();
	},
	refresh: function(){
		var param = getFormData("#queryForm");
		$(thisUiConfig.tableList).datagrid("load", param);
	}
	
};

var orgLookup;
function orgLookup() {
	orgLookup = $("#yjsjlxName").lookup({
		title : "选择应急事件类型",
		url : thisUiConfig.ctx + "/yjsjlx/layerlist",
		width : 650,
		height : 440,
		required : true,
		contentWindow:{
			params : {
				singleSelect : true,
				checkbox : false
			},
			actions : {
				confirm : function(data) {
					$("#yjsjlxName").lookup("setTxt", data.fullName);
					$("#yjsjlxId").val(data.yjsjlxId);
					$("input[name='yjsjlxName']").val(data.fullName);
					
					main.closeDialog();
				},
				cancel : function() {
					$("#yjsjlxId").val("");
					$("input[name='yjsjlxName']").val("");
					$("#yjsjlxName").lookup("setTxt", "");
					main.closeDialog();
				}
			}	
		}
		
	});
}

var userId = <shiro:principal property="userId"/>;
var planRenyuanTable = {
	rows : "",
	isKon : true,
	init: function(){
		$("#planRenyuanTable").datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			//queryParams:  getFormData(thisUiConfig.queryForm),
			//url : baseInfo.search,
			idField: 'yingjiRenyuanId',
			columns : [ [ {
				field : "yingjiRenyuanId",
				hidden:true
			}, {
				field : "yingjirenName",
				halign:'center',
				align:'left',
				title : "应急人员",
				width : 120
			},{
				 field:"phone",
				 title:"联系电话",
				 halign:'center',
				 align:'left',
				 width:120
			},{
				 field:"email",
				 title:"联系邮箱",
				 halign:'center',
				 align:'left',
				 width:140
			},{
				field : "address",
				title : "常住地址",
				halign:'center',
				align:'left',
				width : 280
			}]],

			onSelect : function(rowIndex, rowData) {
				planRenyuanTable.onSelectAndUnselect();
			},
			onUnselect : function(rowIndex, rowData) {
				
			},
			onLoadSuccess : function(data) {
				$("#planRenyuanTable").datagrid("unselectAll");
				enableButtons();
			},
			
			
		});
		
		// 设置分页显示形式
		$("#planRenyuanTable").datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	onSelectAndUnselect: function(){
		var len = $("#planRenyuanTable").datagrid("getSelections").length;
		if(len == 1){
			enableButtons();
		}
	},
	
	add : function(){	
		planRenyuanTable.rows = clone($("#planRenyuanTable").datagrid("getRows"));
		
		main.dialog({
			title : "选择应急人员",
			url : "${ctx}/yingjirenyuan/selectyjry",
			width : 680,
			height : 410,
			contentWindow:{
				params : {
					singleSelect : false,
					checkbox : true
				},
				actions : {
					confirm : function(data) {
						planRenyuanTable.rows = clone($("#planRenyuanTable").datagrid("getRows"));
						main.closeDialog();
					},
					cancel : function() {
						console.log(planRenyuanTable.rows);
						$("#planRenyuanTable").datagrid("loadData", planRenyuanTable.rows);
						main.closeDialog();
					},
					add: function(data){
						var rowIndex = $('#planRenyuanTable').datagrid('getRowIndex', data.yingjiRenyuanId);
						if(rowIndex == -1){
							var row = planRenyuanTable.newRow(data);
							$('#planRenyuanTable').datagrid('insertRow',{index:0,row:row});
						}
					},
					del: function(data){
						var rowIndex = $('#planRenyuanTable').datagrid('getRowIndex', data.yingjiRenyuanId);
						if(rowIndex != -1){
							$('#planRenyuanTable').datagrid('deleteRow', rowIndex);	
						}
					},
					addRows: function(rows){
						planRenyuanTable.updateRows(rows, true);
					},
					delRows: function(rows){
						planRenyuanTable.updateRows(rows, false);
					}
				}	
			}
			
		});
	},
	del: function(){
		var row = $('#planRenyuanTable').datagrid('getSelected');
		var rowIndex = $('#planRenyuanTable').datagrid('getRowIndex', row);
		$('#planRenyuanTable').datagrid('deleteRow', rowIndex);
		enableButtons();
	},
	updateRows: function(data, isAdd){
		for(var i = 0; i < data.length; i++){
			var rowIndex = $('#planRenyuanTable').datagrid('getRowIndex', data[i].yingjiRenyuanId);
			if (rowIndex == -1 && isAdd) {
				var row = planRenyuanTable.newRow(data[i]);
				$('#planRenyuanTable').datagrid('insertRow',{index:0,row:row});
			}else if(rowIndex != -1 && ! isAdd){
				$('#planRenyuanTable').datagrid('deleteRow', rowIndex);
			}
		}
	},
	newRow: function(data){
		var row = {};
		row.yingjirenName = data.yingjirenName;
		row.yingjiPlanId = thisUiConfig.getId();
		row.createrId = userId;
		row.createTime = '';
		row.yingjiRenyuanId = data.yingjiRenyuanId;
		row.phone = data.phone;
		row.email = data.email;
		return row;
	}
};

//日期格式化  
function  formatterDate (date){
	return date.formatDate("yyyy-MM-dd ");
}


function clone(obj){  
    var o;  
    switch(typeof obj){  
    case 'undefined': break;  
    case 'string'   : o = obj + '';break;  
    case 'number'   : o = obj - 0;break;  
    case 'boolean'  : o = obj;break;  
    case 'object'   :  
        if(obj === null){  
            o = null;  
        }else{  
            if(obj instanceof Array){  
                o = [];  
                for(var i = 0, len = obj.length; i < len; i++){  
                    o.push(clone(obj[i]));  
                }  
            }else{  
                o = {};  
                for(var k in obj){  
                    o[k] = clone(obj[k]);  
                }  
            }  
        }  
        break;  
    default:          
        o = obj;break;  
    }  
    return o;     
}  
</script>


</body>
</html>