<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${system_name}-${company_name}</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-nateiot-ui/css/component.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/sis.css" />
</head>

<body>
<div class="content">
	<!-- 按钮区域 -->
	<div id="toolbar" style="padding: 5px;">
		<a id="add" href="javascript:void(0);" class="easyui-splitbutton toolbar" onclick="add();" data-options="menu:'#mm1',plain:true,iconCls:'icon-add'">新增同级节点</a>
		<a id="del" href="javascript:void(0);" class="easyui-linkbutton toolbar" onclick="del();" data-options="plain:true,iconCls:'icon-remove'">删除</a>
		<a id="edit" href="javascript:void(0);" class="easyui-linkbutton toolbar" onclick="edit();" data-options="plain:true,iconCls:'icon-edit'">修改</a>
		<a id="save" href="javascript:void(0);" class="easyui-linkbutton toolbar" onclick="save()" data-options="plain:true,iconCls:'icon-save'">保存</a>
	</div>

	<!-- 实体区域 -->
	<div id="mainLayout" class="easyui-layout"
		data-options="fit:true,border:false" style="width: 100%;height: 100%">
			<div data-options="region:'west',split:true,minWidth:200" style="width:200px;">
				<ul id="orgTree" class="easyui-tree" data-options="animate:true"></ul>
			</div>
			<div data-options="region:'center'">
				<form id="dataForm" class="formCls" method="post" style="visibility: hidden;">
					<input id="id" name="id" type="hidden" />
					<input id="createrId" name="createrId" type="hidden" />
					<input id="createTime" name="createTime" type="hidden" />
					<input id="parentOrgId" name="parentOrgId" type="hidden" />
					<input id="fullpath" name="fullpath" type="hidden" />
					<input id="isleaf" name="isleaf" type="hidden" />
					<table id="formTable">
						<tr>
							<td>组织编码</td>
							<td>
								<input id="orgCode" name="orgCode" class="easyui-validatebox" type="text"
									data-options="required:true,validType:'stringCheck'"/>
							</td>
							<td>组织名称</td>
							<td>
									<input id="orgName" name="orgName" class="easyui-validatebox" type="text"
									data-options="required:true,validType:'stringCheck'"/>
							</td>
						</tr>
						
						<tr>
							<td>组织全称</td>
							<td colspan="3">
								<input id="orgFullname" name="orgFullname" class="easyui-validatebox" type="text"
									data-options="required:true,validType:'stringCheck'" style="width: 100%;"/>
							</td>
						</tr>
						
						<tr>
							<td>组织地址</td>
							<td colspan="3">
								<input id="orgAddr" name="orgAddr" class="easyui-validatebox" type="text" style="width: 100%;" />
							</td>
						</tr>
						
						<tr>
							<td>邮箱地址</td>
							<td colspan="3">
								<input id="email" name="email" class="easyui-validatebox" type="text" style="width: 100%;" />
							</td>
						</tr>
						<tr>	
							<td>网址</td>
							<td colspan="3">
								<input id="weburl" name="weburl" class="easyui-validatebox" type="text" style="width: 100%;" />
							</td>
						</tr>
						
						<tr>
							<td>主管职位</td>
							<td>
								<input id="manaPosition" name="manaPosition" class="easyui-validatebox" type="text" />
							</td>
							<td>组织联系人</td>
							<td>
								<input id="linkmanId" name="linkmanId" type="hidden" />
								<input id="linkmanName" name="linkmanName" type="text"/>
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td>生效日期</td> -->
<!-- 							<td> -->
<!-- 								<input id="validDate" name="validDate" class="easyui-datebox" type="text" -->
<!-- 									data-options="required:true,editable:false"/> -->
<!-- 							</td> -->
<!-- 							<td>失效日期</td> -->
<!-- 							<td> -->
<!-- 								<input id="invalidDate" name="invalidDate" class="easyui-datebox" type="text"/> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						
						<tr>
							<td>备注</td>
							<td colspan="3">
								<input id="remark" name="remark" class="easyui-validatebox" type="text" style="width: 100%;height: 60px"/>
							</td>
						</tr>
						
<!-- 						<tr> -->
<!-- 							<td>创建人</td> -->
<!-- 							<td><input id="creater" name="creater"  type="text" disabled="disabled" > </td> -->
<!-- 							<td>创建时间</td>	 -->
<!-- 							<td><input id="createTime" name="createTime"  type="text" disabled="disabled" > </td>					 -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>最后修改人</td> -->
<!-- 							<td><input id="lastUpdater" name="lastUpdater"  type="text" disabled="disabled" > </td> -->
<!-- 							<td>最后修改时间</td>	 -->
<!-- 							<td><input id="lastUpdateTime" name="lastUpdateTime"  type="text" disabled="disabled" > </td>					 -->
<!-- 						</tr> -->
					</table>
					
					<div id="sub" style="height: 265px;">
						<div id="subTabs" class="easyui-tabs" data-options="fit:true,border:false">
						 	<div title="用户成员" style="overflow: hidden;">
								<div id="userbuttonbar">
									<a id="addUser" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addUser();">添加用户</a>
									<a id="delUser" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"  onclick="delUser();" >删除用户</a>
								</div>
								<table id="dg_orgUsers" class="easyui-datagrid"
									data-options="
										idField : 'userId',
										rownumbers : true,
										border : false,
										singleSelect : false,
										autoRowHeight : false,
										toolbar : '#userbuttonbar',
										onClickRow : onDg_orgUsersClickRow">
									<thead>
										<tr>
											<th data-options="field:'relaId',hidden:true">主键</th>
											<th data-options="field:'orgId',hidden:true">组织ID</th>
											<th data-options="field:'userId',hidden:true">用户ID</th>
											<th data-options="field:'createrId',hidden:true">创建人ID</th>
											<th data-options="field:'createTime',hidden:true">创建时间</th>
											<th data-options="field:'userName'">用户账号</th>
											<th data-options="field:'realname'">实际名称</th>
											<th data-options="field:'statusName'">状态</th>
											<th data-options="field:'isMain',
												formatter : function(value,row) {
													return value == 'Y' ? '是' : '否'
												},
												editor : {
													type : 'combobox',
													options : {
														valueField : 'dictCode',
														textField : 'dictName',
														editable : false,
														url : '${ctx}/dict/getdict/YesOrNo',
														panelHeight : 'auto'
													}
												}"
											>是否主组织</th>
											<th data-options="field:'isManage',
												formatter : function(value,row) {
													return value == 'Y' ? '是' : '否'
												},
												editor : {
													type : 'combobox',
													options : {
														valueField : 'dictCode',
														textField : 'dictName',
														editable : false,
														url : '${ctx}/dict/getdict/YesOrNo',
														panelHeight : 'auto'
													}
												}"
											>是否管理者</th>
										</tr>
									</thead>
								</table>
							</div>
							<div title="拥有角色" style="overflow: hidden;">
								<div id="rolebuttonbar">
									<a id="addRole" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addRole();">添加角色</a>
									<a id="delRole" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"  onclick="delRole();" >删除角色</a>
								</div>
								<table id="dg_orgRoles" class="easyui-datagrid"
									data-options="
										idField : 'roleId',
										rownumbers : true,
										border : false,
										singleSelect : false,
										autoRowHeight : false,
										toolbar : '#rolebuttonbar',
										onClickRow : onDg_orgRolesClickRow">
									<thead>
										<tr>
											<th data-options="field:'relaId',hidden:true">主键</th>
											<th data-options="field:'orgId',hidden:true">组织ID</th>
											<th data-options="field:'roleId',hidden:true">角色ID</th>
											<th data-options="field:'createrId',hidden:true">创建人ID</th>
											<th data-options="field:'createTime',hidden:true">创建时间</th>
											<th data-options="field:'roleCode'">角色编码</th>
											<th data-options="field:'roleName'">角色名称</th>
											<th data-options="field:'isGranted',
												formatter : function(value,row) {
													return value == 'Y' ? '是' : '否'
												},
												editor : {
													type : 'combobox',
													options : {
														valueField : 'dictCode',
														textField : 'dictName',
														editable : false,
														url : '${ctx}/dict/getdict/YesOrNo',
														panelHeight : 'auto'
													}
												}"
											>是否可授出</th>
											<th data-options="field:'isInherited',
												formatter : function(value,row) {
													return value == 'Y' ? '是' : '否'
												},
												editor : {
													type : 'combobox',
													options : {
														valueField : 'dictCode',
														textField : 'dictName',
														editable : false,
														url : '${ctx}/dict/getdict/YesOrNo',
														panelHeight : 'auto'
													}
												}"
											>是否可继承</th>
											<th data-options="field:'remark',editor:'text',width:150">备注</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</form>
			</div>
	</div>

	<div id="mm1" style="width:120px;">
        <div data-options="plain:true,iconCls:'icon-add'" onclick="addNext();">新增下级节点</div>
    </div>

	<div id="roleDialog"></div>
	<div id="userDialog"></div>

</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-nateiot-ui/component.js"></script>

<script type="text/javascript">

	var linkmanLookup;
	
	$(function() {
		
		linkmanLookup = $("#linkmanName").lookup({
			title : "选择联系人",
			url : "${ctx}/user/selectuser",
			width : 590,
			height : 435,
			params : {
				singleSelect : true,
				checkbox : false
			},
			clear : function() {
				$("#linkmanId").val(null);
				$("#linkmanName").val(null);
			},
			actions : {
				confirm : function(data) {
					$("#linkmanId").val(data[0].userId);
					$("#linkmanName").val(data[0].realname);
					linkmanLookup.lookup("close");
				},
				cancel : function() {
					linkmanLookup.lookup("close");
				}
			}
		});
		
		// 初始化页面时，启用【新增】按钮
		enableButtons([]);
		//$("#add").splitbutton("disable");
		
		$("#orgTree").tree({
			url: "${ctx}/org/getOrg",
			formatter: function(node) {
				return node.orgName;
			},
			onSelect: function(node) {
				if ($("#dataForm").css("visibility") == "hidden") {
					$("#dataForm").css("visibility", "visible");
				}
				
				$.ajax({
					type: "GET",
					url: "${ctx}/org/get/" + node.id,
				}).done(function(res){
					$.messager.progress("close");
					if (res.resultCode == "0") {
						$("#dataForm").form("load", res.row);
						disableItem(true);
					} else {
						$.messager.alert("温馨提示", res.resultMsg, "error");
					}
				}).fail(function(jqXHR, textStatus, errorThrown) {
					$.messager.progress("close");
					$.messager.alert("温馨提示", "加载出错！", "error");
				});
				
				if (node.isleaf == "Y") {
					enableButtons(["del", "edit"]);
					$("#add").splitbutton("enable");
				} else {
					enableButtons(["edit"]);
					$("#add").splitbutton("enable");
				}
			}
		});

		var bodyOuterHeight = $("body").outerHeight(true);
		var toolbarOuterHeight = $("#toolbar").outerHeight(true);
		$("#mainLayout").height(bodyOuterHeight - toolbarOuterHeight);
		$("#mainLayout").find(".panel-body").height(bodyOuterHeight - toolbarOuterHeight);
		
		$("#dataForm").form({
			onLoadSuccess : function (data) {
				loadSubData(data.id);
			}
		});		

		$("#mainLayout").click(function() {
			$("#dg_orgUsers").datagrid("acceptChanges");
			$("#dg_orgUsers").datagrid("unselectAll");
			$("#dg_orgRoles").datagrid("acceptChanges");
			$("#dg_orgRoles").datagrid("unselectAll");
		});
			
	});

	// 启用按钮
	function enableButtons(buttons) {
		$(".easyui-linkbutton.toolbar").linkbutton("disable");
		for (var i = 0; i < buttons.length; i++) {
			$("#"+buttons[i]).linkbutton("enable");
		}
	}
	
	// 新增同级
	function add() {
		enableButtons(["save"]);
		disableItem(false);
		$("#dataForm").css("visibility", "visible");
		$("#dataForm").form("clear");
		$("#dg_orgUsers").datagrid("loadData", {"total" : 0, "rows" : []});
		$("#dg_orgRoles").datagrid("loadData", {"total" : 0, "rows" : []});
		$("#orgCode").focus();
		
		var node = $("#orgTree").tree("getSelected");
		if (node) {
			$("#parentOrgId").val(node.parentOrgId);
		}
	}

	// 新增下级
	function addNext() {
		var node = $("#orgTree").tree("getSelected");
		if (node) {
			enableButtons(["save"]);
			disableItem(false);
			
			if (node.state == "closed") {
				$("#orgTree").tree("expand", node.target);
			}
			
			$("#dataForm").css("visibility", "visible");
			$("#dataForm").form("clear");
			$("#dg_orgUsers").datagrid("loadData", {"total" : 0, "rows" : []});
			$("#dg_orgRoles").datagrid("loadData", {"total" : 0, "rows" : []});
			$("#parentOrgId").val(node.id);
		} else {
			$.messager.alert("温馨提示", "请选择节点", "info");
		}		
	}
	
	// 删除
	function del() {
		var node = $("#orgTree").tree("getSelected");
		if (node) {
			$.messager.confirm("温馨提示", "确定删除组织【" + node.orgName + "】?",
				function(r) {
					if (r) {
						$.messager.progress();
						$.ajax({
							type : "GET",
							url : "${ctx}/org/del/" + node.id,
						}).done(function(res) {
							$.messager.progress("close");
							if (res.resultCode == "0") {
								var selected = $("#orgTree").tree("getSelected");
								var parent = $("#orgTree").tree("getParent", selected.target);
								if (parent) {
									var children = $("#orgTree").tree("getChildren", parent.target);
									if (children.length == 1) {
										$("#orgTree").tree("update", {
											target : parent.target,
											isleaf : "Y"
										});
									}
								}
								$("#orgTree").tree("remove", selected.target);
								hiddenForm();
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

	// 修改
	function edit() {
		disableItem(false);
	}

	// 保存
	function save() {
		if ($("#dataForm").form("validate")) {
	 		$.messager.progress();
	 		$("#dg_orgUsers").datagrid("acceptChanges");
			$("#dg_orgUsers").datagrid("unselectAll");
			$("#dg_orgRoles").datagrid("acceptChanges");
			$("#dg_orgRoles").datagrid("unselectAll");
			var data = getFormData("#dataForm");
			$.ajax({
				type: "POST",
				url: "${ctx}/org/save",
				data: data
			}).done(function(res) {
				$.messager.progress("close");
				if (res.resultCode == "0") {
					if ($("#id").val()) {
						$("#dataForm").form("load", res.row);
					} else {
						refreshTree($("#orgTree").tree("getSelected"), res.row);
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

	// 添加用户
	function addUser() {
		$("#userDialog").dialog({
			title: "选择角色",
			width: 590,
			height: 417,
			closed: false,
			cache: false,
			content: '<iframe id="userframe" scrolling="auto" frameborder="0" src="${ctx}/user/selectuser" style="width:100%;height:100%;"></iframe>',
			modal: true,
			onOpen : function() {
				$("#userframe")[0].contentWindow.params = {
					singleSelect : false,
					checkbox : true
				};
				$("#userframe")[0].contentWindow.actions = {
					confirm : function(data) {
						for(var i = 0; i < data.length; i++){
	            			var rowIndex = $("#dg_orgUsers").datagrid("getRowIndex", data[i].userId);
	            			if (rowIndex == -1) {
	            				data[i].orgId = $("#id").val();
	            				data[i].createrId = "";
	            				data[i].createTime = "";
	            				data[i].isMain = "N";
	            				data[i].isManage = "N";
	            				$("#dg_orgUsers").datagrid("insertRow", {index : 0, row : data[i]});
	            			}
	            		}
						$("#userDialog").dialog("close");
					},
					cancel : function() {
						$("#userDialog").dialog("close");
					}
				};
			}
		});
		$("#userDialog").find(".panel-body").css("overflow", "hidden");
	}	

	//删除用户
	function delUser() {
		var rows = $("#dg_orgUsers").datagrid("getSelections");
		var copyRows = [];
		for (var i = 0; i < rows.length; i++) {
			copyRows.push(rows[i]);
		}
		for (var i = 0; i < copyRows.length; i++) {
			var rowIndex = $("#dg_orgUsers").datagrid("getRowIndex", copyRows[i]);
			$("#dg_orgUsers").datagrid("deleteRow", rowIndex);
		}
	}	
	
	// 添加角色
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
	            			var rowIndex = $("#dg_orgRoles").datagrid("getRowIndex", data[i].roleId);
	            			if (rowIndex == -1) {
	            				data[i].orgId = $("#id").val();
	            				data[i].createrId = "";
	            				data[i].createTime = "";
	            				data[i].isGranted = "N";
	            				data[i].isInherited = "N";
	            				$("#dg_orgRoles").datagrid("insertRow", {index : 0, row : data[i]});
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
		var rows = $("#dg_orgRoles").datagrid("getSelections");
		var copyRows = [];
		for (var i = 0; i < rows.length; i++) {
			copyRows.push(rows[i]);
		}
		for (var i = 0; i < copyRows.length; i++) {
			var rowIndex = $("#dg_orgRoles").datagrid("getRowIndex", copyRows[i]);
			$("#dg_orgRoles").datagrid("deleteRow", rowIndex);
		}
	}	
	
	// 加载行表数据
	function loadSubData(orgId) {
		$.messager.progress();
		$.ajax({
			url : "${ctx}/org/getSubData/" + orgId
		}).done(function(res) {
			$.messager.progress("close");
			if (res.resultCode == "0") {
				$("#dg_orgRoles").datagrid("loadData", res.row.roles);
				$("#dg_orgUsers").datagrid("loadData", res.row.users);
			} else {
				$.messager.alert("温馨提示", res.resultMsg, "error");
			}
		}).fail(function(jqXHR, textStatus, errorThrown) {
			$.messager.progress("close");
			$.messager.alert("温馨提示", "加载行表数据出错！", "error");
		});
	}

	function disableItem(disable) {
		if (disable) {
			enableButtons(["add", "del", "edit"]);
			$("#dataForm").form("disableValidation");
		}
		setFormItemDisabled("#dataForm", disable);
		linkmanLookup.lookup(disable?"disable":"enable");
		if (!disable) {
			enableButtons(["add", "del", "save"]);
			$("#dataForm").form("enableValidation");
		}
		$("#addUser").linkbutton(disable?"disable":"enable");
		$("#delUser").linkbutton(disable?"disable":"enable");
		$("#dg_orgUsers").datagrid({
			onClickRow : disable?function(){}:onDg_orgUsersClickRow
		});
		$("#addRole").linkbutton(disable?"disable":"enable");
		$("#delRole").linkbutton(disable?"disable":"enable");
		$("#dg_orgRoles").datagrid({
			onClickRow : disable?function(){}:onDg_orgRolesClickRow
		});
	}	
	
	function onDg_orgUsersClickRow(index) {
		$("#dg_orgUsers").datagrid("acceptChanges");
		$("#dg_orgUsers").datagrid("unselectAll");
		$("#dg_orgUsers").datagrid("selectRow", index).datagrid("beginEdit", index);
	}
	
	function onDg_orgRolesClickRow(index) {
		$("#dg_orgRoles").datagrid("acceptChanges");
		$("#dg_orgRoles").datagrid("unselectAll");
		$("#dg_orgRoles").datagrid("selectRow", index).datagrid("beginEdit", index);
	}
	
	function hiddenForm() {
		if ($("#dataForm").css("visibility") == "visible") {
			$("#dataForm").css("visibility", "hidden");
			enableButtons([ "add" ]);
		}
		$("#dataForm").form("clear");
	}	
	
	function refreshTree(node, obj) {
		if (!node) {
			$("#orgTree").tree("reload");
			return;
		}
		if (node.id != $("#parentOrgId").val()) {
			// 新增同级
			if (node) {
				$("#orgTree").tree("insert", {
					after : node.target,
					data : obj
				});
			}
		} else {
			// 新增下级
			$("#orgTree").tree("append", {
				parent : node.target,
				data : obj
			});
			if (node.isleaf == "Y") {
				$("#orgTree").tree("update", {
					target : node.target,
					isleaf : "N"
				});
			}
		}		
		var newNode = $("#orgTree").tree("find", obj.id);
		$("#orgTree").tree("select", newNode.target);
	}	
	
</script>
</body>
</html>