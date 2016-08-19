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
	            				<label class="form-label">团队编码</label>
	            				<input class="easyui-validatebox form-control" name="q_teamCode_EQ"  />
	            		</td>
						<td class="form-cell-1">
	            				<label class="form-label">团队名称</label>
	            				<input class="easyui-validatebox form-control" name="q_teamName_EQ"  />
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
	    		<input id="teamId" name="teamId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
		 <fieldset>
		 	<legend>基础数据</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td class="form-cell-1">
	            				<label class="form-label">团队编码</label>
	            				<input class="easyui-validatebox form-control" id="teamCode" name="teamCode"  />
	            			</td>
	            			<td class="form-cell-1">
	            				<label class="form-label">团队名称</label>
	            				<input class="easyui-validatebox form-control" name="teamName" required="required" />
	            			</td>
	            			<td class="form-cell-1">
			            		<label class="form-label">所属机构</label>
		     					<input id="unitsId" name="unitsId">
	          				</td>
	          				<td class="form-cell-1">
	            				<label class="form-label">负责人</label>
	            				<input class="easyui-validatebox form-control" name="principal" required="required" />
	            			</td>
						</tr>
						<tr>
							<td class="form-cell-1">
	            				<label class="form-label">负责区域</label>
	            				<input class="easyui-validatebox form-control" name="region"  />
	            			</td>
							<td class="form-cell-1">
	            				<label class="form-label">联系方式</label>
	            				<input class="easyui-validatebox form-control" name="phone"  />
	            			</td>
	            			<td class="form-cell-1">
		            			<label class="form-label">在职状态</label>
		            			<input name="jobStatus" 
		            					dictCode="jobStatus" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
 							<td class="form-cell-1"></td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
	            				<label class="form-label">职责</label>
	            				<textarea name="responsible" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
	            			</td>
						</tr>
					</tbody>
			</table>
		 </fieldset>
		 </br>
		 <!-- 团队成员行表 -->
		 	<div id="ta" class="g-toolbar" style="margin:0 0 0px 0px;">
				<a onclick="addMember();" id="addMember" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
				<a onclick="delMember();" id="delMember" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
			</div>
		 	<div id="sub" style="margin:20px 0 20px 110px;height:85%; width:89%;">
     				<table id="dg_cisSwTeamMember" class="easyui-datagrid" title="团队成员" 
     				   data-options="rownumbers: true,
									singleSelect: true,
									autoHeight: false,
									fitColumns:true,
									height: 300, 
									toolbar:'#ta',
									onClickRow : onDb_cisSwTeamMember">
     				<thead>
     					<tr>
     						<th data-options="field:'memberId', hidden:true">事件登记行表主键</th>
     						<th data-options="field:'createrId', hidden:true">创建人ID</th>
							<th data-options="field:'memberCode', width:120, editor:{type:'validatebox',options:{required:true}}">成员编号</th>
     						<th data-options="field:'memberName', width:120, editor:{type:'validatebox',options:{required:true}}">成员名称</th>
     						<th data-options="field:'memberGender', width:120, 
                                                 formatter : function(value, row){ 
                                                      if(value == 'male'){
                                                      	  return '男';
                                                      }else if(value == 'female'){
                                                      	  return '女';
                                                      }
                                                 }, 
                                                editor : {
                                                    type : 'combobox',
                                                    options : {
                                                        valueField : 'dictCode',
                                                        textField : 'dictName',
                                                        url : '${ctx}/dict/getdict/Gender',
                                                        panelHeight : 'auto'
                                                    }
                                                }
                                                ">成员性别</th>
     						<th data-options="field:'phone',width: 150, editor:{type:'validatebox',options:{required:true}}">成员联系电话</th>
     						<th data-options="field:'age', width: 150, editor:{type:'validatebox',options:{required:true}}">成员年龄</th>
     						<th data-options="field:'address', width: 320, editor:{type:'validatebox',options:{required:true}}">成员住址</th>
<!--      						<th data-options="field:'contactsName', width: 150, editor:{type:'validatebox',options:{required:true}}">紧急联系人名称</th> -->
<!--      						<th data-options="field:'contactsPhone', width: 150, editor:{type:'validatebox',options:{required:true}}">紧急联系人电话</th> -->
<!--      						<th data-options="field:'createTime', width:140">操作时间</th> -->
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
		url : "${ctx}/serviceTeam/search",
		columns : [[{
			field : "teamId",
			title : "主键",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "teamCode",
			title : "团队编码",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "teamName",
			title : "团队名称",
			align:'left',
			halign:'center',
			width : 120
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
			field : "jobStatus",
			title : "在职状态",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, index){
				if(value == 'inJob'){
					return '在职';
				}else if(value == 'leaveOffice'){
					return '离职';
				}
			}
		},{
			field : "phone",
			title : "联系方式",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "region",
			title : "负责区域",
			align:'left',
			halign:'center',
			width : 230
		},{
			field : "responsible",
			title : "职责",
			align:'left',
			halign:'center',
			width : 230
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
			enableButtons(["add", "del", "save", "addMember", "delMember"]);
			$("#mainTab").tabs("select", 1);
			setFormItemDisabled("#dataForm", false);
			$("#teamCode").attr('disabled', true);
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
						url : "${ctx}/serviceTeam/get/" + row.teamId,
					}).done(function(res){
						$("#dataForm").form("load", res.row);
						$("#dg_cisSwTeamMember").datagrid("loadData", res.row.cisSwTeamMember);
						$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
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
 * 新增服务团队
 */
 function add(){
	$("#listGrid").datagrid("unselectAll");
	$("#dataForm").form("clear");
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
	enableButtons(["add", "save", "addMember", "delMember" ]);
	setFormItemDisabled("#dataForm", false);
	$("#dg_cisSwTeamMember").datagrid("loadData",{total:0,rows:[]});
	$("#teamCode").attr('disabled', true);
	
	//自动生成编号 
	$.ajax({
		url : "${ctx}/coderule/generateCode/FWTD",
		success : function(data) {
			$("#teamCode").val(data);
		}
	});
	defaultMesh();         //默认网格
}

/**
 * 删除服务团队
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var teamId = "";
	var teamName = "";
	if(row == null){
		teamId = $("#teamId").val();
		teamName = $("#teamName").val();
	}else{
		teamId = row.teamId;
		teamName = row.teamName;
	}
	
	if(teamId){
		$.messager.confirm("温馨提示", "确定删除服务团队【" + teamName + "】?",
				function(r){
			if(r){
				$.ajax({
					url : "${ctx}/serviceTeam/del/" + teamId,
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
 * 修改服务团队
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	$("#dataForm").form("validate");
	if(row){
		$.ajax({
			url : "${ctx}/serviceTeam/get/" + row.teamId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
			enableButtons([ "add","del","save", "delMember", "addMember"]);
			$("#dataForm").form("load", res.row);
			$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
			setFormItemDisabled("#dataForm", false);
			$("#teamCode").attr('disabled', true);
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存服务团队
	 */
	function save(){
	if($("#dataForm").form("validate")){
		$("#dg_cisSwTeamMember").datagrid("acceptChanges");
		$("#dg_cisSwTeamMember").datagrid("unselectAll");
		var data = getFormData("#dataForm");
		console.log(data);
		$.ajax({
			type : "POST",
			url : "${ctx}/serviceTeam/save",
			data : data
		}).done(function(res){
			$("#dataForm").form("load", res.row);
			if(res.row.cisSwTeamMember != null){
				$("#dg_cisSwTeamMember").datagrid("loadData",{total:0,rows:[]});
				$("#dg_cisSwTeamMember").datagrid("loadData", res.row.cisSwTeamMember);
			}
			query();
			enableButtons([ "add", "save", "del", "addMember", "delMember"]);
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
	function addMember(){
		$("#dg_cisSwTeamMember").datagrid('insertRow', {
			index : 0,
			row : []
		});
		$("#listGrid").datagrid("beginEdit", 0);
    }
	
	/**
	 * 删除成员
	 */
	function delMember() {
		var rows = $("#dg_cisSwTeamMember").datagrid("getSelections");
		var copyRows = [];
		for (var j = 0; j < rows.length; j++) {
			copyRows.push(rows[j]);
		}
		for (var i = 0; i < copyRows.length; i++) {
			var index = $("#dg_cisSwTeamMember").datagrid("getRowIndex", copyRows[i]);
			$("#dg_cisSwTeamMember").datagrid("deleteRow", index);
		}
	}
	
   /**
	* 行表选择行
	*/
	function onDb_cisSwTeamMember(index){
	$("#dg_cisSwTeamMember").datagrid("acceptChanges");
	$("#dg_cisSwTeamMember").datagrid("unselectAll");
	$("#dg_cisSwTeamMember").datagrid("selectRow", index).datagrid("beginEdit", index);
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