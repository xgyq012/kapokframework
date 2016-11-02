<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${system_name}-${company_name}</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/sis.css" />
</head>

<body>
<div class="content">
	<!-- 按钮区域 -->
	<div id="toolbar" style="padding: 5px;">
		<a id="add" href="javascript:void(0);" class="easyui-linkbutton toolbar" onclick="add();" data-options="plain:true,iconCls:'icon-add'">新增</a>
		<a id="del" href="javascript:void(0);" class="easyui-linkbutton toolbar" onclick="del();" data-options="plain:true,iconCls:'icon-remove'">删除</a>
		<a id="edit" href="javascript:void(0);" class="easyui-linkbutton toolbar" onclick="edit();" data-options="plain:true,iconCls:'icon-edit'">修改</a>
		<a id="save" href="javascript:void(0);" class="easyui-linkbutton toolbar" onclick="save()" data-options="plain:true,iconCls:'icon-save'">保存</a>
	</div>

	<!-- 实体区域 -->
	<div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false">

		<!-- 实体列表 -->
		<div id="listTab" title="列表" class="tabCls">

			<!-- 查询区域 -->
			<div id="query">
				<form id="queryForm" class="formCls">
					<table>
						<tr>
							<td>角色编码</td>
							<td>
								<input id="q_roleCode_LIKE" name="q_roleCode_LIKE" type="text"/>
							</td>
							<td>角色名称</td>
							<td>
								<input id="q_roleName_LIKE" name="q_roleName_LIKE" type="text"/>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="clearQueryForm('#queryForm');query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<!-- 列表区域 -->
			<div id="list">
				<table id="listGrid" style="height: 432px;"></table>
			</div>
		</div>

		<!-- 实体详细 -->
		<div id="detailTab" title="详细" class="tabCls">
			<form id="dataForm" class="formCls" method="post">

				<!-- 表单区域 -->
				<div id="form">
					<input id="roleId" name="roleId" type="hidden"/>
					<input id="createrId" name="createrId" type="hidden" />
					<input id="createTime" name="createTime" type="hidden" />
					<table>
						<tr>
							<td>角色编码</td>
							<td>
								<input id="roleCode" name="roleCode" class="easyui-validatebox" type="text"
									data-options="required: true"/>
							</td>
							<td>角色名称</td>
							<td>
								<input id="roleName" name="roleName" class="easyui-validatebox" type="text"
									data-options="required:true"/>
							</td>
							<td>角色类型</td>
							<td>
								<input id="roleType" name="roleType" class="easyui-validatebox" type="text" />
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td>生效日期</td> -->
<!-- 							<td> -->
<!-- 								<input id="validDate" name="validDate" class="easyui-datebox" type="text" -->
<!-- 									data-options="required:true"/> -->
<!-- 							</td> -->
<!-- 							<td>失效日期</td> -->
<!-- 							<td> -->
<!-- 								<input id="invalidDate" name="invalidDate" class="easyui-datebox" type="text"/> -->
<!-- 							</td> -->
<!-- 							<td></td> -->
<!-- 							<td></td> -->
<!-- 						</tr> -->
						<tr>
							<td>角色描述</td>
							<td colspan="5">
								<textarea  id="roleDesc" name="roleDesc" class="easyui-validatebox"  rows="5" style="width: 490px;"></textarea>
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td>创建人</td> -->
<!-- 							<td><input id="creater" name="creater"  type="text" disabled="disabled" > </td> -->
<!-- 							<td>创建时间</td>	 -->
<!-- 							<td><input id="createTime" name="createTime"  type="text" disabled="disabled" > </td>					 -->
<!-- 							<td>最后修改人</td> -->
<!-- 							<td><input id="lastUpdater" name="lastUpdater"  type="text" disabled="disabled" > </td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>最后修改时间</td>	 -->
<!-- 							<td><input id="lastUpdateTime" name="lastUpdateTime"  type="text" disabled="disabled" > </td>					 -->
<!-- 						</tr> -->
					</table>
				</div>

				<!-- 行表区域 -->
				<div id="sub" style="height: 100%;">
					<div id="subTabs" class="easyui-tabs" data-options="fit:true,border:false" > 
						<div title="资源">
							<ul id="resourceTree"></ul>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript">

	var disabledItem = true;
	$(function() {
		
		// 初始化页面时，启用【新增】按钮，禁用【详细】Tab页
		enableButtons(["add"]);
		$("#mainTabs").tabs("disableTab", 1);
		
		$("#mainTabs").tabs({
			onSelect : function(title, index) {
				if (index == 0) {
					disabledItem = true;
					if ($("#listGrid").datagrid("getSelections").length > 0) {
						enableButtons(["add", "del", "edit"]);
						$("#mainTabs").tabs("enableTab", 1);
					} else {
						enableButtons(["add"]);
						$("#mainTabs").tabs("disableTab", 1);
					}
				}
				if (index == 1) {
					var detailTabOuterHeight = $("#detailTab").outerHeight(true);
					var formOuterHeight = $("#form").outerHeight(true);
					$("#sub").height(detailTabOuterHeight - formOuterHeight);
 					$("#subTabs").height(detailTabOuterHeight - formOuterHeight);
 					
 					var subTabsOuterHeight = $("#subTabs").outerHeight(true);
 					var subTabsHeaderOuterHeight = $("#subTabs").find(".tabs-header").outerHeight(true);
 					$("#subTabs").find(".tabs-panels").height(subTabsOuterHeight - subTabsHeaderOuterHeight);
 					$("#subTabs").find(".panel-body").height(subTabsOuterHeight - subTabsHeaderOuterHeight);
 					
					if ($("#listGrid").datagrid("getSelections").length > 0) {
						enableButtons(["add", "del", "save"]);
						loadRoleData();
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
			url : "${ctx}/role/search",
 			columns : [[
				{field : "roleCode", title : "角色编码"},
				{field : "roleName", title : "角色名称"},
				{field : "roleType", title : "角色类型"},
// 				{field : "validDate", title : "生效日期"},
// 				{field : "invalidDate", title : "失效日期"},
				{field : "roleDesc", title : "角色描述"}
 			]],
			onSelect : function(rowIndex, rowData) {
				enableButtons(["add", "del", "edit"]);
				$("#mainTabs").tabs("enableTab", 1);
				disabledItem = true;
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

		$("#listGrid").datagrid("getPager").pagination({
			layout : defaultPageLayout
		});		

		$("#resourceTree").tree({
			animate : true,
			checkbox : true,
			formatter : function(node) {
				return node.resourceLabel;
			}
		});
		
		$("#dataForm").form({
			onLoadSuccess : function (row) {
				loadResourceData(row.roleId);
				disableItem(disabledItem);
			}
		});		

		var bodyOuterHeight = $("body").outerHeight(true);
		var toolbarOuterHeight = $("#toolbar").outerHeight(true);
		$("#mainTabs").height(bodyOuterHeight - toolbarOuterHeight);

		var mainTabsOuterHeight = $("#mainTabs").outerHeight(true);
		var tabsHeaderOuterHeight = $("#mainTabs").find(".tabs-header").outerHeight(true);
		$("#mainTabs").find(".tabs-panels").height(mainTabsOuterHeight - tabsHeaderOuterHeight);
		$("#listTab").height(mainTabsOuterHeight - tabsHeaderOuterHeight);
		$("#detailTab").height(mainTabsOuterHeight - tabsHeaderOuterHeight);
		
		
	});

	// 启用按钮
	function enableButtons(buttons) {
		$(".easyui-linkbutton.toolbar").linkbutton("disable");
		for (var i = 0; i < buttons.length; i++) {
			$("#"+buttons[i]).linkbutton("enable");
		}
	}
	
	// 新增角色
	function add() {
		disabledItem = false;
		disableItem(disabledItem);
		$("#dataForm").form("clear");
		$("#listGrid").datagrid("unselectAll");
		$("#mainTabs").tabs("enableTab", 1);
		$("#mainTabs").tabs("select", 1);
		loadResourceData(0);
	}

	// 删除角色
	function del() {
		var row = $("#listGrid").datagrid('getSelected');
		if (row) {
			$.messager.confirm("温馨提示", "确定删除角色【" + row.roleName + "】?",
				function(r) {
					if (r) {
						$.messager.progress();
						$.ajax({
							type: "GET",
							url : "${ctx}/role/del/" + row.roleId,
						}).done(function(res){
							$.messager.progress("close");
							if (res.resultCode == "0") {
								$("#dataForm").form("clear");
								$("#listGrid").datagrid("unselectAll");
								$("#mainTabs").tabs("select", 0);
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
	
	// 修改角色
	function edit() {
		var tab = $("#mainTabs").tabs("getSelected");
		var index = $("#mainTabs").tabs("getTabIndex", tab);
		disabledItem = false;
		if (index == 0) {
			$("#mainTabs").tabs("select", 1);
		}
		if (index == 1) {
			loadRoleData();
		}
	}
	
	// 加载角色数据
	function loadRoleData() {
		var row = $("#listGrid").datagrid("getSelected");
		if (row) {
			$.messager.progress();
			$.ajax({
				type : "GET",
				url : "${ctx}/role/get/" + row.roleId,
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
	
	// 保存角色
	function save() {
		if ($("#dataForm").form("validate")) {
	 		$.messager.progress();
			var data = getFormData("#dataForm");
			var checkedResource = $("#resourceTree").tree("getChecked", ["checked","indeterminate"]);
			$.each(checkedResource, function(index, value){
				data["resources["+index+"].relaId"] = value.relaId;
				data["resources["+index+"].roleId"] = data.roleId;
				data["resources["+index+"].resourceId"] = value.resourceId;
				data["resources["+index+"].createrId"] = value.createrId;
				data["resources["+index+"].createTime"] = value.createTime;
			});
			$.ajax({
				type: "POST",
				url: "${ctx}/role/save",
				data: data
			}).done(function(res) {
				$.messager.progress("close");
				if (res.resultCode == "0") {
					if ($("#roleId").val()) {
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

	function disableItem(disable) {
		if (disable) {
			enableButtons(["add", "del", "edit"]);
			$("#dataForm").form("disableValidation");
		}
		setFormItemDisabled("#dataForm", disable);
		if (!disable) {
			enableButtons(["add", "del", "save"]);
			$("#dataForm").form("enableValidation");
		}
	}
	
	// 加载资源数据
	function loadResourceData(roleId) {
		$.messager.progress();
		$.ajax({
			url : "${ctx}/role/getResource/" + roleId
		}).done(function(res) {
			$.messager.progress("close");
			if (res.resultCode == "0") {
				$("#resourceTree").tree({
					onBeforeCheck : function(node, checked) {
						return true;
					}
				});
				var children = getChildrenByParentId(0, res.rows);
				convert(children, res.rows);
				$("#resourceTree").tree("loadData", children);
				$("#resourceTree").tree("collapseAll");
				$("#resourceTree").tree({
					onBeforeCheck : function(node, checked) {
						return !disabledItem;
					}
				});
			} else {
				$.messager.alert("温馨提示", res.resultMsg, "error");
			}
		}).fail(function(jqXHR, textStatus, errorThrown) {
			$.messager.progress("close");
			$.messager.alert("温馨提示", "加载资源数据出错！", "error");
		});
	}

	// 针对行表资源的特殊函数，迭代子资源数组，判断子资源内的元素是否拥有子资源
	// 如果拥有子资源，把子资源添加到对应用元素内，并继续查找子资源的子资源
	function convert(children, data) {
		$.each(children, function(index, value) {
			if (value.isBranch == "Y") {
				value.state = "closed";
				var child = getChildrenByParentId(value.resourceId, data);
				if (child) {
					value["children"] = child;
					convert(child, data);
				}
			}
		});
	}
	
	// 针对行表资源的特殊函数，从一个资源数组里，根据父资源ID找到对应用的子资源
	function getChildrenByParentId(parentId, data) {
		var result = [];
		$.each(data, function(index, value) {
			if (value.parentResourceId == parentId) {
				if (value.roleId && value.isBranch == "N") {
					value.checked = true;
				}
				result.push(value);
			}
		});
		return result.sort(sortBySeq);
	}
	
	// 针对行表资源的特殊函数，对资源数组的排序
	function sortBySeq(a, b){
		return a.seq - b.seq;
	}
	
	function query() {
 		$("#listGrid").datagrid("load", getFormData("#queryForm"));
	}
</script>
</body>
</html>