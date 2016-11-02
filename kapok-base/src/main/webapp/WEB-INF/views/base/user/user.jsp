<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户管理</title>
	
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
		<shiro:hasPermission name="USER_ADD">
			<a id="add" class="easyui-linkbutton toolbar g-button" onclick="add()"><i class="fa fa-plus"></i>新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="USER_DEL">
			<a id="del" class="easyui-linkbutton toolbar g-button" onclick="del()"><i class="fa fa-trash-o"></i>删除</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="USER_EDIT">
			<a id="edit" class="easyui-linkbutton toolbar g-button" onclick="edit()"><i class="fa fa-edit"></i>修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="USER_SAVE">
			<a id="save" class="easyui-linkbutton toolbar g-button" onclick="save()"><i class="fa fa-floppy-o"></i>保存</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="USER_RESETPASSWORD">
			<a id="resetPassword" class="easyui-linkbutton toolbar g-button" onclick="resetPassword()"><i class="fa fa-reply"></i>重置密码</a>
		</shiro:hasPermission>
<!-- 			<a id="excelImport" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-import'">Excel导入</a> -->
<!-- 			<a id="excelExport" href="javascript:void(0);" class="easyui-splitbutton" onclick="excelExport('N');" data-options="menu:'#mm2',plain:true,iconCls:'icon-export'">Excel导出</a> -->
<!-- 			<a id="print" href="javascript:void(0);" class="easyui-splitbutton" onclick="print('N');" data-options="menu:'#mm3',plain:true,iconCls:'icon-print'">打印</a> -->
	</div>

	<!-- 内容区域 -->
	<div id="mainTabs" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
		<div title="列表" style="position:relative;">
			<div class="query-area">
				<form id="queryForm" method="post">
					<table class="g-form" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="form-cell-1">
				            		<label class="form-label">用户账号</label>
				            		<input name="q_userName_LIKE" class="easyui-validatebox form-control">
			          			</td>
			          			<td class="form-cell-1">
				            		<label class="form-label">姓名</label>
				            		<input name="q_realname_LIKE" class="easyui-validatebox form-control">
			          			</td>
			          			<td class="form-cell-1 f-button">
									<a onclick="query()" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
									<a onclick="clearQueryForm('#queryForm');query()" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
								</td>
								<td class="form-cell-1"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>

			<!-- 列表区域 -->
			<div class="list-area" style="top:50px;">
				<table id="listGrid" style="height:100%"></table>
			</div>
		</div>

		<!-- 实体详细 -->
		<div id="detailTab" title="详细">
			<form id="dataForm" style="height:100%">
				<input id="userId" name="userId" type="hidden">
				<input id="createrId" name="createrId" type="hidden">
				<input id="createTime" name="createTime" type="hidden">
				<table class="g-form" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="form-cell-1">
				            	<label class="form-label" title="用户账号">用户账号</label>
								<input id="userName" name="userName" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-1">
				            	<label class="form-label" title="姓名">姓名</label>
								<input id="realname" name="realname" class="easyui-validatebox form-control"
									data-options="required: true">
							</td>
							<td class="form-cell-1">
				            	<label class="form-label" title="入职日期">入职日期</label>
				            	<input id="indate" name="indate" class="easyui-datebox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px"
	     							data-options="editable:false">
							</td>
							<td class="form-cell-1">
				            	<label class="form-label" title="离职日期">离职日期</label>
				            	<input id="outdate" name="outdate" class="easyui-datebox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px"
	     							data-options="editable:false">
							</td>
						</tr>
						<tr>
							<td class="form-cell-1">
				            	<label class="form-label" title="员工编号">员工编号</label>
								<input id="empcode" name="empcode" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-1">
				            	<label class="form-label" title="职位">职位</label>
								<input id="position" name="position" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-1">
				            	<label class="form-label" title="用户类型">用户类型</label>
								<input id="userType" name="userType" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-1">
				            	<label class="form-label" title="状态">状态</label>
				            	<input  id="status" name="status" class="easyui-combobox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
										required:true,
								    	editable:false,
								    	panelHeight:'auto',
								    	valueField:'dictCode',
								    	textField:'dictName',
								    	url:'${ctx}/dict/getdict/UserStatus'">
							</td>
						</tr>
						<tr>
							<td class="form-cell-1">
				            	<label class="form-label" title="办公电话">办公电话</label>
								<input id="otel" name="otel" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-3" colspan="3">
				            	<label class="form-label" title="办公地址">办公地址</label>
								<input id="oaddress" name="oaddress" class="easyui-validatebox form-control">
							</td>
						</tr>
						<tr>
							<td class="form-cell-1">
				            	<label class="form-label" title="手机号码">手机号码</label>
								<input id="mobileno" name="mobileno" class="easyui-validatebox form-control"
									data-options="required: true">
							</td>
							<td class="form-cell-3" colspan="3">
				            	<label class="form-label" title="邮箱地址">邮箱地址</label>
								<input id="email" name="email" class="easyui-validatebox form-control">
							</td>
						</tr>
						<tr>
							<td class="form-cell-1">
				            	<label class="form-label" title="邮编">邮编</label>
								<input id="ozipcode" name="ozipcode" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-3" colspan="3">
				            	<label class="form-label" title="联系地址">联系地址</label>
								<input id="haddress" name="haddress" class="easyui-validatebox form-control">
							</td>
						</tr>
					</tbody>
				</table>
				
				<!-- 行表区域 -->
				<div id="subTabs" class="easyui-tabs g-tabs2" data-options="fit:true,border:false" style="height:100%;padding-bottom:170px">
					<div title="角色">
						<div id="buttonbar" class="g-toolbar">
							<a id="addRole" class="easyui-linkbutton g-button" onclick="addRole()">添加角色</a>
							<a id="delRole" class="easyui-linkbutton g-button" onclick="delRole()" >删除角色</a>
						</div>
						<table id="dg_userRoles" class="easyui-datagrid" style="height:100%"></table>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div id="roleDialog"></div>
<!-- 	<div id="mm2" style="width:100px;"> -->
<!--         <div data-options="iconCls:'icon-export'" onclick="excelExport('Y');">导出全部</div> -->
<!--     </div> -->
<!--     <div id="mm3" style="width:100px;"> -->
<!--         <div data-options="iconCls:'icon-print'" onclick="print('Y');">打印全部</div> -->
<!--     </div> -->
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/excelimport.js"></script>

<script type="text/javascript">

	var editable = false;
	$(function() {
		
		var dict = main.dict,
			dicts = dict.dicts,
			comboboxConfig = {
				valueField: 'dictCode',
			    textField: 'dictName',
			    editable: false,
				panelHeight: 'auto'
			};
		
		$("#excelImport").excelimport({
			url : "${ctx}/user/excelImport",
			preview : true
		});
		
		$("#userName").validatebox({
		    required : true,
		    validType : ["remoteCheckUnique['${ctx}/user/checkUserName', 'userId', 'userName']"]
		});
		
		// 初始化页面时，启用【新增】按钮，禁用【详细】Tab页
		enableButtons(["add"]);
		$("#mainTabs").tabs("disableTab", 1);
		
		$("#mainTabs").tabs({
			onSelect : function(title, index) {
				if (index == 0) {
					editable = false;
					if ($("#listGrid").datagrid("getSelections").length > 0) {
						enableButtons(["add", "edit", "resetPassword"]);
						$("#mainTabs").tabs("enableTab", 1);
					} else {
						enableButtons(["add"]);
						$("#mainTabs").tabs("disableTab", 1);
					}
				}
				if (index == 1) {
					if ($("#listGrid").datagrid("getSelections").length > 0) {
						enableButtons(["add", "save", "resetPassword"]);
						loadUserData();
					} else {
						enableButtons(["add", "save"]);
					}
				}
			}
		});
		
 		$("#listGrid").datagrid({
 			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			url : "${ctx}/user/search",
 			columns : [[
				{field : "userName", title : "用户账号", width : 100, halign : 'center'},
				{field : "realname", title : "实际名称", width : 100, halign : 'center'},
				{field : "mobileno", title : "手机", width : 100, halign : 'center'},
				{field : "email", title : "邮箱", width : 100, halign : 'center'},
				{field : "empcode", title : "员工编号", width : 100, halign : 'center'},
				{field : "statusName", title : "状态", width : 35, halign : 'center'},
				{field : "haddress", title : "联系地址", width : 200, halign : 'center'}
 			]],
			onSelect : function(rowIndex, rowData) {
				enableButtons(["add", "del", "edit", "resetPassword"]);
				$("#mainTabs").tabs("enableTab", 1);
				editable = false;
			},
			onUnselectAll : function(rowIndex, rowData) {
				var tab = $("#mainTabs").tabs("getSelected");
				var index = $("#mainTabs").tabs("getTabIndex", tab);
				if (index == 0) {
					enableButtons(["add"]);
					$("#mainTabs").tabs("disableTab", 1);
				}
				if (index == 1) {
					enableButtons(["add", "save"]);
				}
			},
			onLoadSuccess : function(data) {
				$("#listGrid").datagrid("unselectAll");
			}
 		});
 		
 		$('#dg_userRoles').datagrid({
 			idField: 'roleId',
			rownumbers: true,
			singleSelect: true,
			autoRowHeight: false,
			toolbar: '#buttonbar',
			onDblClickRow: _onUserRolesDblClickRow,
 			columns: [[
				{field: 'relaId', title: '主键', hidden: true},
				{field: 'userId', title: '用户ID', hidden: true},
				{field: 'roleId', title: '角色ID', hidden: true},
				{field: 'createrId', title: '创建人ID', hidden: true},
				{field: 'createTime', title: '创建时间', hidden: true},
				{field: 'roleCode', title: '角色编码', width: 100},
				{field: 'roleName', title: '角色名称', width: 100},
				{field: 'roleType', title: '角色类型', width: 100},
				{field: 'roleDesc', title: '角色描述', width: 100},
				{
					field: 'isGranted',
					title: '是否可授出',
					width: 80,
					formatter : function(value,row) {
						return value == 'Y' ? '是' : '否'
					},
					editor: {
						type: 'combobox',
						options: {
							valueField: 'dictCode',
							textField: 'dictName',
							panelHeight: 'auto',
							data: dicts.YesOrNo.list
						}
					}
				},
				{field: 'remark',  title: '备注', width: 100, editor:'text'}
 			]]
 		});	
 		
		$('#dataForm').form({
			onLoadSuccess : function (res) {
				var dd = []
				for (var i = 0; i < 30; i++) {
					dd.push({"roleCode":"super"+i,"roleName":"超级用户"+i});
				}
				if (res.userRoles) {
					$('#dg_userRoles').datagrid('loadData', res.userRoles);
				}
				_setFormEditable(editable);
			}
		});
		
		$("#mainTabs").click(function() {
			$("#dg_userRoles").datagrid("acceptChanges");
		});
		
	});

	// 启用按钮
	function enableButtons(buttons) {
		$(".easyui-linkbutton.toolbar").linkbutton("disable");
		for (var i = 0; i < buttons.length; i++) {
			$("#"+buttons[i]).linkbutton("enable");
		}
	}
	
	// 新增用户 
	function add() {
		editable = true;
		_setFormEditable(editable);
		$("#dataForm").form("clear");
		$("#listGrid").datagrid("unselectAll");
		$("#dg_userRoles").datagrid("loadData", {"total" : 0, "rows" : []});
		$("#mainTabs").tabs("enableTab", 1);
		$("#mainTabs").tabs("select", 1);
		$("#userName").focus();
	}

	// 删除用户
	function del() {
		var row = $("#listGrid").datagrid("getSelected");
		if (row) {
			$.messager.confirm("温馨提示", "确定删除用户【" + row.userName + "】?",
				function(r) {
					if (r) {
						$.messager.progress();
						$.ajax({
							type: "GET",
							url : "${ctx}/user/del/" + row.userId,
						}).done(function(res){
							$.messager.progress("close");
							if (res.resultCode == "0") {
								$("#dataForm").form("clear");
								$("#listGrid").datagrid("unselectAll");
								query(); // 刷新列表页
								$.messager.show({
									title: "温馨提示",
									msg: res.resultMsg,
									timeout: 2500,
									showType: "slide"
								});
							} else {
								$.messager.alert("温馨提示", res.resultMsg, "error");
							}
						}).fail(function(jqXHR, textStatus, errorThrown) {
							$.messager.progress("close");
							$.messager.alert("温馨提示", "删除出错！", "error");
						});
					}
				});
		}
	}

	// 修改用户
	function edit() {
		var tab = $("#mainTabs").tabs("getSelected");
		var index = $("#mainTabs").tabs("getTabIndex", tab);
		editable = true;
		if (index == 0) {
			$("#mainTabs").tabs("select", 1);
		}
		if (index == 1) {
			loadUserData();
		}
	}
	
	// 加载用户数据
	function loadUserData() {
		var row = $("#listGrid").datagrid("getSelected");
		if (row) {
			$.messager.progress();
			$.ajax({
				type: "GET",
				url: "${ctx}/user/get/" + row.userId,
			}).done(function(res){
				$.messager.progress("close");
				if (res.resultCode == "0") {
					$("#dataForm").form("load", res.row);
				} else {
					$.messager.alert("温馨提示", res.resultMsg, "error");
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				$.messager.alert("温馨提示", "加载出错！", "error");
			});
		}
	}
	
	// 保存用户
	function save() {
		if ($("#dataForm").form("validate")) {
	 		$.messager.progress();
	 		$("#dg_userRoles").datagrid("acceptChanges");
			$("#dg_userRoles").datagrid("unselectAll");
			var data = getFormData("#dataForm");
			$.ajax({
				type : "POST",
				url : "${ctx}/user/save",
				data : data
			}).done(function(res) {
				$.messager.progress("close");
				if (res.resultCode == "0") {
					if ($("#userId").val()) {
						$("#dataForm").form("load", res.row);
					} else {
						$("#dataForm").form("load", res.row);
						query();
					}
					$.messager.show({
						title: "温馨提示",
						msg: "操作成功",
						timeout: 2500,
						showType: "slide"
					});
				} else {
					$.messager.alert("温馨提示", res.resultMsg, "error");
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				$.messager.alert("温馨提示", "保存出错！", "error");
			});
		}
	}

	// 重置密码
	function resetPassword() {
		var row = $("#listGrid").datagrid("getSelected");
		if (row) {
			$.messager.confirm("温馨提示", "确定重置用户【" + row.userName + "】的密码吗?",
				function(r) {
					if (r) {
						$.messager.progress();
						$.ajax({
							type : "GET",
							url : "${ctx}/user/resetpassword/" + row.userId
						}).done(function(res) {
							$.messager.progress("close");
							if (res.resultCode == "0") {
								$.messager.show({
									title: "温馨提示",
									msg: "操作成功",
									timeout: 2500,
									showType: "slide"
								});
							} else {
								$.messager.alert("温馨提示", res.resultMsg, "error");
							}
						}).fail(function(jqXHR, textStatus, errorThrown) {
							$.messager.progress("close");
							$.messager.alert("温馨提示", "重置密码时系统出错！", "error");
						});
					}
				});
		}
	}
	
	function _setFormEditable(editable) {
		if (!editable) {
			enableButtons(["add", "edit", "resetPassword"]);
			$("#dataForm").form("disableValidation");
		}
		setFormItemDisabled("#dataForm", !editable);
		if (editable) {
			enableButtons(["add", "save", "resetPassword"]);
			$("#dataForm").form("enableValidation");
		}
		$("#addRole").linkbutton(editable?"enable":"disable");
		$("#delRole").linkbutton(editable?"enable":"disable");
	}
	
	function _onUserRolesDblClickRow(index) {
		if (editable) {
			$("#dg_userRoles").datagrid("acceptChanges");
			$("#dg_userRoles").datagrid("unselectAll");
			$("#dg_userRoles").datagrid("selectRow", index).datagrid("beginEdit", index);
		}
	}
	
	// 选择角色
	function addRole() {
		$("#roleDialog").dialog({
			title: "选择角色",
			width: 590,
			height: 417,
			closed: false,
			cache: false,
			content: '<iframe id="roleframe" scrolling="auto" frameborder="0" src="${ctx}/role/selectrole" style="width:100%;height:100%;"></iframe>',
			modal: true,
			onOpen : function() {
				$("#roleframe")[0].contentWindow.params = {
					singleSelect : false,
					checkbox : true
				};
				$("#roleframe")[0].contentWindow.actions = {
					confirm : function(data) {
						for(var i = 0; i < data.length; i++){
	            			var rowIndex = $("#dg_userRoles").datagrid("getRowIndex", data[i].roleId);
	            			if (rowIndex == -1) {
	            				data[i].userId = $("#userId").val();
	            				data[i].isGranted = "Y";
	            				data[i].createrId = "";
	            				data[i].createTime = "";
	            				$("#dg_userRoles").datagrid("insertRow", {index : 0, row : data[i]});
	            			}
	            		}
						$("#roleDialog").dialog("close");
					},
					cancel : function() {
						$("#roleDialog").dialog("close");
					}
				};
			}
		});
		$("#roleDialog").find(".panel-body").css("overflow", "hidden");
	}
	
	//删除角色
	function delRole() {
		var rows = $("#dg_userRoles").datagrid("getSelections");
		var copyRows = [];
		for (var i = 0; i < rows.length; i++) {
			copyRows.push(rows[i]);
		}
		for (var i = 0; i < copyRows.length; i++) {
			var rowIndex = $("#dg_userRoles").datagrid("getRowIndex", copyRows[i]);
			$("#dg_userRoles").datagrid("deleteRow", rowIndex);
		}
	}
	
	function query() {
 		$("#listGrid").datagrid("load", getFormData("#queryForm"));
	}
	
	// Excel导入
	function excelImport() {
		$("#aa").click();
	}
	
	// Excel导出
	function excelExport(isAll) {
		var pageOptions = $("#listGrid").datagrid("getPager").pagination("options");
		$("#queryForm").form("submit", {
			url : "${ctx}/user/excelExport",
			onSubmit: function(param) {
		        param.page = pageOptions.pageNumber;
		        param.rows = pageOptions.pageSize;
		        param.isAll = isAll;
		        return true;
		    }
		});
	}

	// 打印
	function print(isAll) {
		var queryParam = $.param(getFormData("#queryForm"));
		var url = "${ctx}/user/print?isAll=" + isAll + "&" + queryParam;
		window.open(url);
	}
	
	// 下载Excel模板
	function downloadTemplate() {
		$("#queryForm").form("submit", {
			url : "${ctx}/user/downloadTemplate/user"
		});
	}
	
</script>
</body>
</html>