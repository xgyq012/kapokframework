<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${system_name}-${company_name}</title>
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
	<a onclick="add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a onclick="save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query" >
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
						<td class="form-cell-1">
	            				<label class="form-label">所属网格</label>
	            				<input class="easyui-validatebox form-control" id="mpunitsId"  name="q_mp.UNITS_ID_LIKE"/>
	            		</td>
	            		<td class="form-cell-1">
		            		<label class="form-label">表格填写日期</label>
							<input id="timeGte" name="timeGte" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
						</td>
						<td class="form-cell-1">
<!-- 				            		<label class="form-label">~</label> -->
		            		~<input id="timeLte" name="timeLte" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px">
		            	</td>
						<td class="form-cell-1 f-button">
							<a onclick="query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
							<a onclick="clearQueryForm('#queryForm');query();" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="list-area" style="top:50px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
		
	</div>

	<div id="minute" data-options="disabled:true"  title="详细"   >
		<form id="dataForm"  action="">
				<!-- 隐藏属性 -->
	    		<input id="promiseId" name="promiseId" type="hidden" />
	    		<input id="delSign" name="delSign" type="hidden"/>
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				
			<fieldset>
				<legend>基础信息</legend>
				<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td class="form-cell-1">
			            		<label class="form-label">所属网格</label>
		     					<input id="unitsId" name="unitsId">
	          				</td>
							<td class="form-cell-1">
			            		<label class="form-label">乡(镇)</label>
		     					<input id="village" name="village" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px" required="required">
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">党支部</label>
		     					<input name="branch" class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px" required="required">
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">姓名</label>
		     					<input id="memberId" name="memberId" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px" >
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-1">
				            		<label class="form-label">性别</label>
				            		<input id="sex" name="sex" class="easyui-combobox form-control"
				            			style="width:${comboboxWidth};height:${comboboxHeight}px"
				            			dictCode="Gender" 
				            			data-options="
		 								    	editable:false,
		 								    	panelHeight:'auto',
		 								    	valueField:'dictCode',
		 								    	textField:'dictName'">
			          			</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">年龄</label>
		     					<input name="age" class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px" >
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">表格填写日期</label>
		     					<input id="writeDate" name="writeDate" class="easyui-datebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px">
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">评定日期</label>
		     					<input id="review" name="review" class="easyui-datebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px">
	          				</td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label">承诺事项</label>
		     					<textarea name="promiseItem" style="height:150px;" class="easyui-validatebox form-control"></textarea>
	          				</td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label">践诺情况</label>
		     					<textarea name="situation" style="height:150px;" class="easyui-validatebox form-control"></textarea>
	          				</td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label">支部评定意见</label>
		     					<textarea name="suggestion" style="height:150px;" class="easyui-validatebox form-control"></textarea>
	          				</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>

</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">
/**
 * 脚本文件，脚本入口 
 */
 $(function(){
	 tabInit();
	 orgLookup();
	 memberLookup();
	 listLookup();
	 enableButtons([ "add"]); // 初始加载时设置按钮可用
	 datagridInit(); // 初始化数据表格
	 publicAttr.initCombobox();
	 $("#mainTab").tabs("disableTab", 1); // 初始加载时详细标签不可用
	 
 });
 
//初始化数据表格
function datagridInit() {
	$("#listGrid").datagrid({
		rownumbers : true,
		singleSelect : true,
		autoRowHeight : false,
		border : false,
		pageSize : defaultPageSize,
		pageList : defaultPageList,
		pagination : true,
		fitColumns : true,
// 		queryParams : getFormData("#queryForm"),
		queryParams : getQueryFormData("#queryForm"),
		url : "${ctx}/memberPromise/searchBy",
		columns : [[{
			field : "promiseId",
			title : "主键",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "unitsId",
			title : "所属机构",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, index){
				return publicAttr.getOrgName(value);
			}
		},{
			field : "name",
			title : "党员",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "village",
			title : "乡(镇)",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "branch",
			title : "党支部",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "writeDate",
			title : "表格填写日期",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "promiseItem",
			title : "承诺事项",
			align:'left',
			halign:'center',
			width : 170
		}
		]],
		onSelect : function(rowIndex, rowData) {
			enableButtons([ "add", "del", "edit" ]);
			$("#mainTab").tabs("enableTab", 1);
		},
		onUnselectAll : function(rowIndex, rowData) {
			var tab = $("#mainTab").tabs("getSelected");
			var index = $("#mainTab").tabs("getTabIndex", tab);
			if(index == 0){
				enableButtons(["add"]);
				$("#mainTab").tabs("disableTab", 1);
			}else if(index == 1){
				enableButtons(["add", "del", "save" ]);
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
			enableButtons(["add", "del", "save" ]);
			$("#mainTab").tabs("select", 1);
			setFormItemDisabled("#dataForm", false);
		},
		onLoadSuccess : function(data) {
			$("#listGrid").datagrid("unselectAll");
		}
	});
	//设置分页形式
	$("#listGrid").datagrid("getPager").pagination({
		layout : defaultPageLayout
	});
}

/**
 * 初始化标签
 */
 function tabInit(){
	$("#mainTab").tabs({
		onSelect : function(title, index) {
			if(index == 0){
				$("#dataForm").form("clear");
				if($("#listGrid").datagrid("getSelections").length > 0){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons(["add", "del", "edit"]);
				}else{
					$("#mainTab").tabs("disableTab", 1);
					enableButtons(["add"]);
				}
			}else if(index == 1){
				setFormItemDisabled("#dataForm", true);
				var row = $("#listGrid").datagrid("getSelected");
				if(row){
					enableButtons(["add", "del", "edit"]);
					$.ajax({
						url : "${ctx}/memberPromise/get/" + row.promiseId,
					}).done(function(res){
						console.log(res.row);
						$("#dataForm").form("load", res.row);
						$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
						$("#memberId").lookup("setTxt", res.row.name);
						$("input[name='memberId']").val(res.row.memberId);
					}).fail(function(){
						$.messager.alert("温馨提示","获取数据出错！","error");
					});
				}
			}
		}
	});
}

/**
 * 设置按钮是否可用 
 */
function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	// 设置按钮可用
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).linkbutton("enable");
	}
}

/**
 * 新增党员公开承诺
 */
 function add(){
	$("#listGrid").datagrid("unselectAll");
	$("#dataForm").form("clear");
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
	enableButtons(["add", "save"]);
	setFormItemDisabled("#dataForm", false);
	$("input[name='age']").attr('disabled', true);
	$("#sex").combobox('disable');
	defaultMesh();         //默认网格
}

/**
 * 删除党员公开承诺
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var promiseId = "";
	var name = "";
	if(row == null){
		promiseId = $("#promiseId").val();
		name = $("#memberId").lookup('getTxt');
	}else{
		promiseId = row.promiseId;
		name = row.name;
	}
	
	if(promiseId){
		$.messager.confirm("温馨提示", "确定删除党员名为【" + name + "】的记录?",
				function(r){
			if(r){
				$.ajax({
					url : "${ctx}/memberPromise/softDel/" + promiseId,
				}).done(function(res){
					$.messager.progress("close");
					if(res.resultCode == 0){
						 $("#dataForm").form("clear");
						 $("#mainTab").tabs("select", 0);
						 $("#mainTab").tabs("disableTab", 1);
						 enableButtons([ "add" ]);
						 query();
					}
					$.messager.show({
						 title : "温馨提示",
						 msg : "操作成功",
						 timeout : 2500,
						 showType : "slide"
				     });
				});
			}
		});
	}
	
}

/**
 * 修改党员公开承诺
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	if(row){
		$.ajax({
			url : "${ctx}/memberPromise/get/" + row.promiseId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
			enableButtons([ "add", "del", "save" ]);
			$("#dataForm").form("load", res.row);
			setFormItemDisabled("#dataForm", false);
			$("input[name='age']").attr('disabled', true);
			$("#sex").combobox('disable');
// 			$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存党员公开承诺
	 */
	function save(){
	if($("#dataForm").form("validate")){
		$("#delSign").val('N');
		var data = getFormData("#dataForm");
		console.log(data);
		$.ajax({
			type : "POST",
			url  : "${ctx}/memberPromise/save",
			data : data
		}).done(function(res){
			$("#dataForm").form("load", res.row);
			enableButtons([ "save" ]);
			$("#promiseId").val(res.row.promiseId);
			$("#memberId").lookup("setTxt", res.row.name);
			$("input[name='memberId']").val(res.row.memberId);
			query();
			$.messager.show({
				title : "温馨提示",
				msg : "操作成功",
				timeout : 2500,
				showType : "slide"
			});
		}).fail(function(jqXHR, textStatus, errorThrown){
			$.messager.progress("close");
			$.messager.alert("温馨提示[" + textStatus + "]", "保存出错！", "error");
		});
	}
}
 
   /**
	 * 查询按钮
	 */
	function query() {
		formatterIntegalDate();
// 		var param = getFormData("#queryForm");
		var param = getQueryFormData("#queryForm");
		console.log(param);
		$("#listGrid").datagrid("load", param);
		enableButtons([ "add" ]);
	}
   
	var orgLookup;
	/**
	 * 巡检员弹出窗
	 */
	function orgLookup() {
		orgLookup = $('#unitsId').lookup({
			title: '选择所属网格',
			url: '${ctx}/mesh/selectmesh',
			width: 350,
			height: 500,
			required: true,
			valueField: 'meshId',
			textField: 'meshName',
				contentWindow: {
					params: {
						singleSelect : false,
						checkbox : true
					},
					actions: {
						confirm : function(data) {
							if(data){
								$("input[name='unitsId']").val(data.meshId);
								$("input[name='unitsId']").prev().val(data.meshName);
							}
							main.closeDialog();
						},
						cancel: function() {
							main.closeDialog();
						}
					}
				}
		});
	}
	
	function getQueryFormData(formId){
		console.log(window.main);
		var meshIds = window.main.currentUserMesh.meshChildrenIds || "" ;
		var data =  getFormData(formId) || {};
		if(meshIds){
			data['q_mp.units_Id_IN'] = meshIds;
		}
		console.log(data);
		return data;
	}
	
	var listLookup;
	/**
	 * 巡检员弹出窗
	 */
	function listLookup() {
		listLookup = $('#mpunitsId').lookup({
			title: '选择所属网格',
			url: '${ctx}/mesh/selectmesh',
			width: 350,
			height: 500,
			valueField: 'meshId',
			textField: 'meshName',
				contentWindow: {
					params: {
						singleSelect : false,
						checkbox : true
					},
					actions: {
						confirm : function(data) {
							if(data){
								$("#mpunitsId").lookup('setVal', data.meshId);
								$("#mpunitsId").lookup('setTxt', data.meshName);
							}
							main.closeDialog();
						},
						cancel: function() {
							main.closeDialog();
						}
					}
				}
		});
	}
	
	var memberLookup;
	/**
	 * 巡检员弹出窗
	 */
	function memberLookup() {
		memberLookup = $('#memberId').lookup({
			title: '选择党员',
			url: '${ctx}/memberPromise/member-select',
			width: 700,
			height: 427,
			required: true,
			cache: false,
			modal: true,
				contentWindow: {
					params: {
						
					},
					actions: {
						confirm : function(data) {
							if(data){
								console.log(data);
								$("#memberId").lookup('setTxt', data[0].houseHolderName);
								$("input[name='memberId']").val(data[0].houseHolderId);
								$("input[name='age']").val(data[0].age);
								$("#sex").combobox('setValue', data[0].sex);
// 								$("input[name='socialJob']").val(data[0].socialJob);
// 								$("input[name='eduLevel']").val(data[0].eduLevel);
							}
							main.closeDialog();
						},
						cancel: function() {
							main.closeDialog();
						}
					}
				}
		});
	}
	
	//日期格式转换
	function formatterDate(time) {
		var date = time;
		var y = "";
		var m  = "";
		var d = "";
		if(time == "") {
			date = new Date();
			y = date.getFullYear();  
		    m = date.getMonth()+1;  
		    d = date.getDate(); 
		} else {
			var ss = (date.split('-'));
	        y = parseInt(ss[0],10);
	        m = parseInt(ss[1],10);
	        d = parseInt(ss[2],10);
		}
		
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	}
	
	//日期查询
	function formatterIntegalDate() {
		var timeGte = $("#timeGte").datebox('getValue');
		var timeLte = $("#timeLte").datebox('getValue');
		if(timeGte == "") {
			if(timeLte == "") {
				integalDate =  formatterDate("");
			} else {
				integalDate =  formatterDate(timeLte);
			}
		} else {
			if(timeLte == "") {
				integalDate = formatterDate(timeGte) + formatterDate("");
			} else {
				integalDate = formatterDate(timeGte) + formatterDate(timeLte);
			}
		}
	}
	
	/**
	 *  默认网格
	 */
	function defaultMesh(){
		var meshId = main.currentUserMesh.meshId;
		$("#unitsId").lookup("setTxt", publicAttr.getOrgName(meshId));
		$("#unitsId").lookup('setVal', meshId);
	}
   
</script>

</body>
</html>