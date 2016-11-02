<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>资源管理</title>
	
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
		<a id="add" class="easyui-linkbutton toolbar g-button" onclick="add()"><i class="fa fa-plus"></i>新增同级</a>
		<a id="addNext" class="easyui-linkbutton toolbar g-button" onclick="addNext()" data-options="disabled:true"><i class="fa fa-plus"></i>新增下级</a>
		<a id="del" class="easyui-linkbutton toolbar g-button" onclick="del()" data-options="disabled:true"><i class="fa fa-trash-o"></i>删除</a>
		<a id="save" class="easyui-linkbutton toolbar g-button" onclick="save()" data-options="disabled:true"><i class="fa fa-floppy-o"></i>保存</a>
	</div>

	<!-- 实体区域 -->
	<div id="mainLayout" class="easyui-layout g-container">
		<div data-options="region:'west',split:true,minWidth:250" style="width:250px;">
			<ul id="resourceTree" class="easyui-tree " data-options="animate:true"></ul>
		</div>
		<div data-options="region:'center'">
			<form id="dataForm" method="post" style="visibility: hidden;">
				<input id="id" name="id" type="hidden">
				<input id="createrId" name="createrId" type="hidden">
				<input id="createTime" name="createTime" type="hidden">
				<input id="fullpath" name="fullpath" type="hidden">
				<input id="isBranch" name="isBranch" type="hidden">
				<input id="parentResourceId" name="parentResourceId" type="hidden">
				<table class="g-form" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="form-cell-4" colspan="4">
			            		<label class="form-label" title="上级资源">上级资源</label>
			            		<input id="parentResourceName" class="easyui-validatebox form-control" readonly>
			          		</td>
						</tr>
						<tr>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="资源编码">资源编码</label>
								<input id="resourceCode" name="resourceCode" class="easyui-validatebox form-control"
									data-options="required:true,validType:'stringCheck'">
							</td>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="资源标题">系统类型</label>
								<input id="systemType" name="systemType" class="easyui-combobox form-control"
									   style="width:${comboboxWidth};height:${comboboxHeight}px">
								<%--<input id="systemType" name="systemType" class="easyui-validatebox form-control">--%>
							</td>
							
						</tr>
						<tr>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="资源名称">资源名称</label>
								<input id="resourceName" name="resourceName" class="easyui-validatebox form-control"
									data-options="required:true,validType:'stringCheck'">
							</td>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="图标路径">图标路径</label>
								<input id="imagePath" name="imagePath" class="easyui-validatebox form-control">
							</td>
						</tr>
						<tr>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="资源标题">资源标题</label>
								<input id="resourceLabel" name="resourceLabel" class="easyui-validatebox form-control"
									data-options="required:true,validType:'stringCheck'">
							</td>
							<td class="form-cell-1">
			            		<label class="form-label" title="打开方式">打开方式</label>
								<input id="openMode" name="openMode" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-1">
			            		<label class="form-label" title="显示顺序">显示顺序</label>
								<input id="seq" name="seq" class="easyui-validatebox form-control">
							</td>
							
						</tr>
						<tr>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="URL地址">URL地址</label>
								<input id="url" name="url" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-1">
			            		<label class="form-label" title="是否有效">是否有效</label>
			            		<input id="enable" name="enable" class="easyui-combobox form-control"
					            	style="width:${comboboxWidth};height:${comboboxHeight}px">
							</td>
							<td class="form-cell-1">
								<label class="form-label" title="资源类型">资源类型</label>
								<input id="resourceType" name="resourceType" class="easyui-combobox form-control"
					            	style="width:${comboboxWidth};height:${comboboxHeight}px">
							</td>
						</tr>
						<tr>
							<td class="form-cell-4" colspan="4">
								<label class="form-label" title="资源描述">资源描述</label>
								<textarea id="resourceDesc" name="resourceDesc" class="easyui-validatebox form-control" style="height:100px"></textarea>
			          		</td>
						</tr>
						<tr>
							<td class="form-cell-4" colspan="4">
								<label class="form-label" title="备注">备注</label>
								<textarea id="remark" name="remark" class="easyui-validatebox form-control" style="height:100px"></textarea>
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
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">

	$(function() {
		var dict = main.dict,
			dicts = dict.dicts,
			comboboxConfig = {
				valueField: 'dictCode',
			    textField: 'dictName',
			    editable: false,
				panelHeight: 'auto'
			};

		comboboxConfig.data = dicts.ResourceSystemType.list;
		$('#systemType').combobox(comboboxConfig);

		comboboxConfig.data = dicts.YesOrNo.list;
		$('#enable').combobox(comboboxConfig);

		comboboxConfig.data = dicts.resourceType.list;
	 	$('#resourceType').combobox(comboboxConfig);

		$("#resourceTree").tree({
			url : "${ctx}/resource/getResource",
			formatter : function(node){
				return node.resourceLabel;
			},
			onSelect : function(node) {
				if ($("#dataForm").css("visibility") == "hidden") {
					$("#dataForm").css("visibility", "visible");
				}
				_loadData(node);
			}
		});
		
	});
	
	// 新增同级
	function add() {
		enableButtons(['add', 'addNext', 'save']);
		disableButtons(['del']);
		$('#dataForm').css('visibility', 'visible');
		$('#dataForm').form('clear');
		$("#enable").combobox("setValue", "Y");
		$('#resourceCode').focus();
		var node = $('#resourceTree').tree('getSelected');
		if (node) {
			var parentNode = $('#resourceTree').tree('getParent', node.target);
			$('#parentResourceId').val(parentNode != null ? parentNode.id : '');
			$('#parentResourceName').val(parentNode != null ? parentNode.resourceName : '');
		}
	}

	// 新增下级
	function addNext() {
		var node = $('#resourceTree').tree('getSelected');
		if (node) {
			enableButtons(['add', 'addNext', 'save']);
			disableButtons(['del']);
			if (node.state == 'closed') {
				$('#resourceTree').tree('expand', node.target);
			}
			$('#dataForm').css('visibility', 'visible');
			$('#dataForm').form('clear');
			$('#enable').combobox('setValue', 'Y');
			$('#parentResourceId').val(node.id);
			$('#parentResourceName').val(node.resourceLabel);
			$('#resourceCode').focus();
		} else {
			main.info('温馨提示', '请选择节点。');
		}
	}
	
	// 删除
	function del() {
		var node = $('#resourceTree').tree('getSelected');
		if (node) {
			main.confirm('温馨提示', '确定删除资源【' + node.resourceLabel + '】?', function(r) {
				if (r) {
					main.showWaiting();
					$.ajax({
						type : 'GET',
						url : '${ctx}/resource/del/' + node.id,
					}).done(function(res) {
						main.closeWaiting();
						if (res.resultCode == '0') {
							var selected = $('#resourceTree').tree('getSelected');
							var parent = $('#resourceTree').tree('getParent', selected.target);
							if (parent) {
								var children = $('#resourceTree').tree('getChildren', parent.target);
								if (children.length == 1) {
									$('#resourceTree').tree('update', {
										target : parent.target,
										isBranch : 'N'
									});
								}
							}
							$('#resourceTree').tree('remove', selected.target);
							enableButtons(['add']);
							disableButtons(['addNext', 'del', 'save']);
							$('#dataForm').css('visibility', 'hidden');
							$('#dataForm').form('clear');
							main.show();
						} else {
							main.error('温馨提示', res.resultMsg);
						}
					}).fail(function(jqXHR, textStatus, errorThrown) {
						main.closeWaiting();
						main.error('温馨提示', '删除时出错！');
					});
				}
			});
		}
	}

	// 保存
	function save() {
		if ($("#dataForm").form("validate")) {
			main.showWaiting();
			var data = getFormData("#dataForm");
			$.ajax({
				type: "POST",
				url: "${ctx}/resource/save",
				data: data
			}).done(function(res) {
				main.closeWaiting();
				if (res.resultCode == "0") {
					if ($("#id").val()) {
						$("#dataForm").form("load", res.row);
					} else {
						_refreshTree($("#resourceTree").tree("getSelected"), res.row);
					}
					main.show();
				} else {
					main.error('温馨提示', res.resultMsg);
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
				main.closeWaiting();
				main.error('温馨提示', '保存时出错！');
			});
		}
	}
	
	function _loadData(node) {
		main.showWaiting();
		$.ajax({
			type: 'GET',
			url: "${ctx}/resource/get/" + node.id,
		}).done(function(res){
			main.closeWaiting();
			if (res.resultCode == '0') {
				
				// 基本信息加载数据
				$('#dataForm').form('load', res.row);
				
				
				// 设置上级信息
				var parentNode = $('#resourceTree').tree('getParent', node.target);
				$('#parentResourceId').val(parentNode != null ? parentNode.id : '');
				$('#parentResourceName').val(parentNode != null ? parentNode.resourceName : '');
				
				// 控制按钮
				if (node.isBranch == 'N') {
					enableButtons(['add', 'addNext', 'del', 'save']);
				} else {
					enableButtons(['add', 'addNext', 'save']);
					disableButtons(['del']);
				}
			} else {
				main.error('温馨提示', res.resultMsg);
			}
		}).fail(function(jqXHR, textStatus, errorThrown) {
			main.closeWaiting();
			main.error('温馨提示', '加载时出错！');
		});
	}
	
	function _refreshTree(node, obj) {
		if (!node) {
			$("#resourceTree").tree("reload");
			return;
		}
		if (node.id != $("#parentResourceId").val()) {
			// 新增同级
			if (node) {
				$("#resourceTree").tree("insert", {
					after : node.target,
					data : obj
				});
			}
		} else {
			// 新增下级
			$("#resourceTree").tree("append", {
				parent : node.target,
				data : obj
			});
			if (node.isBranch == "N") {
				$("#resourceTree").tree("update", {
					target : node.target,
					isBranch : "Y"
				});
			}
		}		
		var newNode = $("#resourceTree").tree("find", obj.id);
		$("#resourceTree").tree("select", newNode.target);
	}

	
</script>
</body>
</html>