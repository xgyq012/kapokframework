<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>应急人员信息</title>
	
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
		    <div class="query-area">
			    <form id="queryForm" method="post">
				    <table class="g-form" class="formCls" cellpadding="0" cellspacing="0">
				        <tbody>
							<tr>	
								<td class="form-cell-1">
						            <label class="form-label">应急人员名称</label>
						            <input id="q_yingjirenName_LIKE" name="q_yingjirenName_LIKE" class="form-control">
						        </td>
								<td class="form-cell-1">
						            <label class="form-label">应急事件类型</label>
						            <input id="q_yjsjlxName_LIKE" name="q_yjsjlxName_LIKE" class="form-control">
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
	    		<input type="hidden" id="yingjiRenyuanId" name="yingjiRenyuanId">
	    		<input id="createrId" name="createrId" type="hidden" />
	    		<input name="createTime" type="hidden">
				<input type="hidden" id="yjsjlxId" name="yjsjlxId" />
	    		<table class="g-form" cellpadding="0" cellspacing="0">
	    		    <tbody>
		     			<tr>
		     			   <td class="form-cell-1">
		     				   <label class="form-label">应急人员名称</label>
		     				   <input id="yingjirenName" name="yingjirenName" class="easyui-validatebox form-control" 
		     				    data-options="validType:'length[2, 32]', required:true">
		     				</td>
		     				
		     			   <td class="form-cell-1">
		     				   <label class="form-label">性别</label>
		     				   <input id="sex" name="yingjirenSex" class="easyui-combobox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px">
		     				</td>		     				
		     			    <td class="form-cell-1">
		     				   <label class="form-label">职位</label>
		     				   <input id="zhiwei" name="zhiwei" class="easyui-combobox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px">
		     				</td>			 								    		     				
		     				<td rowspan="4" class="form-cell-1">
		     				    <label class="form-label">头像</label>
		     					<input id="photofileID" name="photofileID" type="hidden" >
		     					<input id="docShowname" name="docShowname" type="hidden" >
		     					<a onclick="uploadBtn.fileupload('click'); return false;" title="上传头像" id="upfile">
			     					<input type="image" class="easyui-validatebox form-control"
			     						src="${ctx}/resources/images/base/1.png"
			     						style="height:${rowSpanHeight*4-gutterHeight}px; width:120px;">
		     					</a>			     						
		     					<div style="display: none;" id="addPhoto">
		     					</div>			     					
		     				</td>
		     			</tr>
		     			<tr>
		     			    <td class="form-cell-1">
		     				   <label class="form-label">在职状态</label>
		     				   <input  id="zaizhiStatus" name="zaizhiStatus" class="easyui-combobox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px" data-options="required:true">
		     				</td>			     			
		     			   <td class="form-cell-1">
		     				   <label class="form-label">联系电话</label>
		     				   <input id="phone" name="phone"  type="text" class="easyui-validatebox form-control" 
		     				    data-options="validType:'phoneRex',required:true "/>
		     				</td>		     				     			
		     			   <td class="form-cell-1">
		     				   <label class="form-label">邮箱</label>
		     				   <input id="email" name="email"  type="text" class="easyui-validatebox form-control"
		     				    data-options="validType:'email'"/>
		     				</td>		     				     				    
		     			</tr>   
		     			<tr>
		     			   <td class="form-cell-2" colspan="2">
		     				   <label class="form-label">身份证号</label>
		     				   <input id="yingjirenIdcard" name="yingjirenIdcard" class="easyui-validatebox form-control" 
		     				    data-options="validType:'idcared'">
		     				</td>
		     			   <td class="form-cell-1">
		     				   <label class="form-label">应急事件类型</label>
		     				   <input id="yjsjlxName" name="yjsjlxName" class="easyui-validatebox form-control" >
		     				</td>			     				    
		     			</tr>       			  	
		     			<tr>
		     			    <td class="form-cell-3" colspan="3">
		     				   <label class="form-label">居住地址</label>
		     				   <input id="address" name="address" class="easyui-validatebox form-control"
		     				       data-options="validType:'length[4,40]'">
		     				</td>		     			
		     			</tr>
		     			<tr>
		     			    <td class="form-cell-4" colspan="4">
		     				   <label class="form-label">备注</label>
		     				   <textarea name="ps" rows="6" class="easyui-validatebox form-control" ></textarea>
		     				</td>		     			
		     			</tr>
		     		</tbody>
	     		</table>
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
var thisUiConfig = {
	ctx : "${ctx}",
	url : "yingjirenyuan",
	id : "#yingjiRenyuanId",    //表单中主键字段对应的控件的Id属性值
	idName:"yingjiRenyuanId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var householderId = $(thisUiConfig.id).val();
		return householderId;
	}
};

$(function (){
	importDoc.init();
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	initCombobox();
});

var tabs = {
		thisTabIndex: 0,
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
							enableButtons([ "add", "del", "edit" ]);
						} else {
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
						}
					} else if (index == 1) { // 选中详细标签
						var row = $(thisUiConfig.tableList).datagrid("getSelected");
						if (row) {
							$("#yjsjlxName").lookup("setTxt",row.yjsjlxName);
							$(thisUiConfig.baseForm).form("load",row);
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
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/softDelList",
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
				tableInfo.query();
				enableButtons([ "add", "del", "save"]);
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
		console.log("base add");
		//clear data
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$("#photofileID").val("");
		$("#docShowname").val("");
		baseInfo.showImg();
		$(thisUiConfig.tableList).datagrid("unselectAll");
		$(thisUiConfig.mainTab).tabs("enableTab", 1);
		$(thisUiConfig.mainTab).tabs("select", 1);
		setFormItemDisabled(thisUiConfig.baseForm,false,null);
		enableButtons(["save", "upfile"]);
	},
	
	del:function (){
		var row = $(thisUiConfig.tableList).datagrid('getSelections');
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的应急人员信息?",
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
							enableButtons([ "add" ]);
									
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
				field : "yingjiRenyuanId",
				hidden:true
			}, {
				field : "yingjirenName",
				halign:'center',
				align:'left',
				title : "应急人员名称",
				width : 120
			},{
				field:"yjsjlxName",
				halign:'center',
				align:'left',
				title:"应急事件类型",
				width:230
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
				var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
				if(len==1){
					$(thisUiConfig.mainTab).tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit" ]);
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
					$(thisUiConfig.mainTab).tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit", "clmx"]);
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
				if(tabs.thisTabIndex != 1){
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
		var param = getFormData("#queryForm");
		enableButtons([ "add" ]);
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
			params: {
				singleSelect : true,
				checkbox : false,
			},
			actions : {
				confirm : function(data) {
					$("#yjsjlxId").val(data.yjsjlxId);						
					$("input[name='yjsjlxName']").val(data.fullName);
					$("#yjsjlxName").lookup("setTxt", data.fullName);
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
				$("#photofileID").val(result.docId);
				$("#docShowname").val(result.docShowname);
				baseInfo.showImg();
			}
		});
	}
		
};
//日期格式化  
function  formatterDate (date){
	return date.formatDate("yyyy-MM-dd hh:mm:ss");
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
	
	comboboxConfig.data = dicts.yjryJob.list;
	comboboxConfig.required = true;
	$('#zhiwei').combobox(comboboxConfig);
}
</script>


</body>
</html>