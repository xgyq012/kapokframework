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
            				<label class="form-label">编码</label>
            				<input class="easyui-validatebox form-control" name="q_laborCode_LIKE"  />
	            		</td>
	            		<td class="form-cell-1">
				            		<label class="form-label">类型</label>
				            		<input name="q_laborType_LIKE" class="easyui-combobox form-control"
				            			style="width:${comboboxWidth};height:${comboboxHeight}px"
				            			dictCode="division" 
				            			data-options="
		 								    	editable:false,
		 								    	panelHeight:'auto',
		 								    	valueField:'dictCode',
		 								    	textField:'dictName'">
		          			</td>
	            		<td class="form-cell-1 f-button">
							<a onclick="query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
							<a onclick="clearQueryForm('#queryForm');query();" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
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

	<div id="minute" data-options="disabled:true"  title="详细"   >
		<form id="dataForm"  action="">
				<!-- 隐藏属性 -->
	    		<input id="laborId" name="laborId" type="hidden" />
	    		<input id="delSign" name="delSign" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
		 <fieldset>
		 	<legend>基础数据</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td class="form-cell-1">
			            		<label class="form-label">所属机构</label>
		     					<input id="unitsId" name="unitsId">
	          				</td>
	            			<td class="form-cell-1">
	            				<label class="form-label">编码</label>
	            				<input class="easyui-validatebox form-control" id="laborCode" name="laborCode"/>
	            			</td>
	          				<td class="form-cell-1">
	            				<label class="form-label">编制人</label>
	            				<input class="easyui-validatebox form-control" id="compileId" name="compileId" />
	            			</td>
	            			<td class="form-cell-1">
	            				<label class="form-label" title="社区/居委会名称">社区名称</label>
	            				<input class="easyui-validatebox form-control" id="communityName" name="communityName" required="required"  />
	            			</td>
						</tr>
						<tr>
<!-- 							<td class="form-cell-1"> -->
<!-- 	            				<label class="form-label">编号</label> -->
<!-- 	            				<input class="easyui-validatebox form-control" name="region"  /> -->
<!-- 	            			</td> -->
	            			<td class="form-cell-1">
				            		<label class="form-label">类型</label>
				            		<input id="laborType" name="laborType" class="easyui-combobox form-control"
				            			style="width:${comboboxWidth};height:${comboboxHeight}px"
				            			dictCode="division" 
				            			data-options="
		 								    	editable:false,
		 								    	panelHeight:'auto',
		 								    	valueField:'dictCode',
		 								    	textField:'dictName'">
		          			</td>
	            			<td class="form-cell-1">
	            				<label class="form-label">编制日期</label>
	            				<input name="compileDate" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px" />
	            			</td>
 							<td class="form-cell-1"></td>
 							<td class="form-cell-1"></td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
	            				<label class="form-label">备注</label>
	            				<textarea name="remark" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
	            			</td>
						</tr>
					</tbody>
			</table>
		 </fieldset>
		 </br>
		 <!-- 两委分工行表 -->
		 	<div id="ta" class="g-toolbar" style="margin:0 0 0px 0px;">
				<a onclick="addJob();" id="addJob" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
				<a onclick="delJob();" id="delJob" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
			</div>
		 	<div id="sub" style="margin:0px 0 20px 11px;height:85%; width:98%;">
     				<table id="dg_cisPaDivisionRowtable" class="easyui-datagrid" title="两委分工职务" 
     				   data-options="rownumbers: true,
									singleSelect: true,
									autoHeight: false,
									fitColumns:true,
									height: 300, 
									toolbar:'#ta',
									onClickRow : onDb_cisPaDivisionRowtable">
     				<thead>
     					<tr>
     						<th data-options="field:'tableId', hidden:true, halign:'center'">两委分工行表主键</th>
     						<th data-options="field:'createrId', hidden:true, halign:'center'">创建人ID</th>
							<th data-options="field:'job', width:120, editor:{type:'validatebox',options:{required:true}}, halign:'center'">职务</th>
     						<th data-options="field:'divisionWork', width:120, editor:{type:'validatebox',options:{required:true}}, halign:'center'">分管工作</th>
     						<th data-options="field:'remark', width: 320, editor:{type:'validatebox',options:{required:true}}, halign:'center'">备注</th>
     					</tr>
     				</thead>
     				</table>
     		</div>
		</form>
	</div>

</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">

/**
 * 脚本文件，脚本入口 
 */
 $(function(){
	 tabInit();
	 enableButtons([ "add"]); // 初始加载时设置按钮可用
	 datagridInit(); // 初始化数据表格
	 publicAttr.initCombobox();
	 orgLookup();
	 memberLookup();
	 $("#mainTab").tabs("disableTab", 1); // 初始加载时详细标签不可用
// 	 setFormItemDisabled("#dataForm", true, exclude);
	 
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
// 		queryParams : getFormData("#queryForm"),
		queryParams : getQueryFormData("#queryForm"),
		url : "${ctx}/division/search",
		columns : [[{
			field : "laborId",
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
			field : "laborCode",
			title : "编码",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "compileName",
			title : "编制人",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "communityName",
			title : "社区名称",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "compileDate",
			title : "编制日期",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "remark",
			title : "备注",
			align:'left',
			halign:'center',
			width : 260
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
// 				enableButtons(["add", "del", "save"]);
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
			enableButtons(["add", "del", "save", "addJob", "delJob"]);
			$("#mainTab").tabs("select", 1);
			setFormItemDisabled("#dataForm", false);
			$("#laborCode").attr('disabled', true);
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
				$("#dataForm").form("validate");
				var row = $("#listGrid").datagrid("getSelected");
				if(row){
					enableButtons(["add", "edit", "downloads"]);
					$.ajax({
						url : "${ctx}/division/get/" + row.laborId,
					}).done(function(res){
						console.log(res.row);
						$("#dataForm").form("load", res.row);
						$("#dg_cisPaDivisionRowtable").datagrid("loadData", res.row.cisPaDivisionRowtable);
						$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
						$("#compileId").lookup('setTxt', res.row.compileName);
						$("input[name='compileId']").val(res.row.compileId);
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
 * 新增两委分工
 */
 function add(){
	$("#listGrid").datagrid("unselectAll");
	$("#dataForm").form("clear");
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
	enableButtons(["add", "save", "addJob", "delJob" ]);
	setFormItemDisabled("#dataForm", false);
	$("#dg_cisPaDivisionRowtable").datagrid("loadData",{total:0,rows:[]});
	$("#laborCode").attr('disabled', true);
// 	$("#compileId").lookup('setTxt', '<shiro:principal/>');
// 	$("input[name='compileId']").val('<shiro:principal property="userId"/>');
	
	//自动生成编号 
	$.ajax({
		url : "${ctx}/coderule/generateCode/FWTD",
		success : function(data) {
			$("#laborCode").val(data);
		}
	});
	defaultMesh();         //默认网格
}

/**
 * 删除两委分工
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var laborId = "";
	var compileName = "";
	if(row == null){
		laborId = $("#laborId").val();
		compileName = $("#compileId").lookup('getVal');
	}else{
		laborId = row.laborId;
		compileName = row.compileName;
	}
	
	if(laborId){
		$.messager.confirm("温馨提示", "确定删除编制人为【" + compileName + "】的记录?",
				function(r){
			if(r){
				$.ajax({
					url : "${ctx}/division/del/" + laborId,
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
 * 修改两委分工
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	$("#dataForm").form("validate");
	if(row){
		$.ajax({
			url : "${ctx}/division/get/" + row.laborId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
			enableButtons([ "add","del","save", "delJob", "addJob"]);
			$("#dataForm").form("load", res.row);
			$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
			setFormItemDisabled("#dataForm", false);
			$("#laborCode").attr('disabled', true);
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存v
	 */
	function save(){
// 	if($("#dataForm").form("validate")){
		$("#dg_cisPaDivisionRowtable").datagrid("acceptChanges");
		$("#dg_cisPaDivisionRowtable").datagrid("unselectAll");
		$("#delSign").val('N');
		var data = getFormData("#dataForm");
		console.log(data);
		$.ajax({
			type : "POST",
			url : "${ctx}/division/saveEn",
			data : data
		}).done(function(res){
			$("#dataForm").form("load", res.row);
			if(res.row.cisPaDivisionRowtable != null){
				$("#dg_cisPaDivisionRowtable").datagrid("loadData",{total:0,rows:[]});
				$("#dg_cisPaDivisionRowtable").datagrid("loadData", res.row.cisPaDivisionRowtable);
			}
			query();
			enableButtons([ "add", "save", "del", "addJob", "delJob"]);
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
// 	}
}
 
   /**
	 * 查询按钮
	 */
	function query() {
		var param = getQueryFormData("#queryForm");
		console.log(param);
		$("#listGrid").datagrid("load", param);
		enableButtons([ "add" ]);
// 		$("#listGrid").datagrid("load", getFormData("#queryForm"));
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
			textField: 'meshName'
		});
	}
	
   /**
	* 新增成员
	*/
	function addJob(){
		$("#dg_cisPaDivisionRowtable").datagrid('insertRow', {
			index : 0,
			row : []
		});
		$("#listGrid").datagrid("beginEdit", 0);
    }
	
	/**
	 * 删除成员
	 */
	function delJob() {
		var rows = $("#dg_cisPaDivisionRowtable").datagrid("getSelections");
		var copyRows = [];
		for (var j = 0; j < rows.length; j++) {
			copyRows.push(rows[j]);
		}
		for (var i = 0; i < copyRows.length; i++) {
			var index = $("#dg_cisPaDivisionRowtable").datagrid("getRowIndex", copyRows[i]);
			$("#dg_cisPaDivisionRowtable").datagrid("deleteRow", index);
		}
	}
	
   /**
	* 行表选择行
	*/
	function onDb_cisPaDivisionRowtable(index){
	$("#dg_cisPaDivisionRowtable").datagrid("acceptChanges");
	$("#dg_cisPaDivisionRowtable").datagrid("unselectAll");
	$("#dg_cisPaDivisionRowtable").datagrid("selectRow", index).datagrid("beginEdit", index);
   }

   
	function getQueryFormData(formId){
		console.log(window.main);
		var meshIds = window.main.currentUserMesh.meshChildrenIds || "" ;
		var data =  getFormData(formId) || {};
		if(meshIds){
			data['q_unitsId_IN'] = meshIds;
		}
		console.log(data);
		return data;
	}
	
	
	var memberLookup;
	/**
	 * 巡检员弹出窗
	 */
	function memberLookup() {
		memberLookup = $('#compileId').lookup({
			title: '选择党员',
			url: '${ctx}/division/division-select',
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
								$("#compileId").lookup('setTxt', data[0].houseHolderName);
								$("input[name='compileId']").val(data[0].houseHolderId);
// 								$("input[name='age']").val(data[0].age);
// 								$("#sex").combobox('setValue', data[0].sex);
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