<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>微信菜单管理</title>
	
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
		<a id="sync" class="easyui-linkbutton toolbar g-button" onclick="sync()"><i class="fa fa-floppy-o"></i>同步微信</a>
	</div>

	<!-- 实体区域 -->
	<div id="mainLayout" class="easyui-layout g-container">
		<div data-options="region:'west',split:true,minWidth:250" style="width:250px;">
			<ul id="wxMenuTree" class="easyui-tree " data-options="animate:true"></ul>
		</div>
		<div data-options="region:'center'">
			<form id="dataForm" method="post" style="visibility: hidden;">
				<input id="id" name="id" type="hidden">
				<input id="createrId" name="createrId" type="hidden">
				<input id="createTime" name="createTime" type="hidden">
				<input id="fullpath" name="fullpath" type="hidden">
				<input id="isBranch" name="isBranch" type="hidden">
				<input id="menuParentId" name="menuParentId" type="hidden">
				<table class="g-form" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="form-cell-4" colspan="4">
			            		<label class="form-label" title="上级资源">上级菜单</label>
			            		<input id="menuParentName" class="easyui-validatebox form-control" readonly>
			          		</td>
						</tr>
						<tr>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="菜单名称">菜单名称</label>
								<input id="menuName" name="menuName" class="easyui-validatebox form-control"
									data-options="required:true,validType:'stringCheck'">
							</td>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="菜单KEY">菜单KEY</label>
								<input id="menuKey" name="menuKey" class="easyui-validatebox form-control"
									data-options="required:true,validType:'stringCheck'">
							</td>
						</tr>
						<tr>
							<td class="form-cell-4" colspan="4">
								<label class="form-label" title="RUL">URL</label>
								<input id="menuUrl" name="menuUrl" class="easyui-validatebox form-control">
			          		</td>
						</tr>
						<tr>
							<td class="form-cell-2" colspan="2">
			            		<label class="form-label" title="执行动作">执行动作</label>
								<input id="actionId" name="actionId" class="easyui-validatebox form-control">
							</td>
							<td class="form-cell-1">
			            		<label class="form-label" title="排序">排序</label>
			            		<input id="menuSeq" name="menuSeq" class="easyui-validatebox form-control"
					            	style="width:${comboboxWidth};height:${comboboxHeight}px">
							</td>
							<td class="form-cell-1">
								<label class="form-label" title="菜单类型">菜单类型</label>
								<input id="menuType" name="menuType" class="easyui-combobox form-control"
					            	style="width:${comboboxWidth};height:${comboboxHeight}px">
							</td>
						</tr>
						<tr>
							<td class="form-cell-4" colspan="4">
								<label class="form-label" title="描述">描述</label>
								<textarea id="menuDescription" name="menuDescription" class="easyui-validatebox form-control" style="height:100px"></textarea>
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
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>


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
		
		comboboxConfig.data = dicts.WX_MENU_TYPE.list;
	 	$('#menuType').combobox(comboboxConfig);
	 	
		
		$("#wxMenuTree").tree({
			url : "${ctx}/wxmenu/getMenu",
			formatter : function(node){
				return node.menuName;
			},
			onSelect : function(node) {
				if ($("#dataForm").css("visibility") == "hidden") {
					$("#dataForm").css("visibility", "visible");
				}
				_loadData(node);
			}
		});
		actionLookupFun();
	});
	
	// 新增同级
	function add() {
		enableButtons(['add', 'addNext', 'save']);
		disableButtons(['del']);
		$('#dataForm').css('visibility', 'visible');
		$('#dataForm').form('clear');
		$("#enable").combobox("setValue", "Y");
		$('#menuName').focus();
		var node = $('#wxMenuTree').tree('getSelected');
		if (node) {
			var parentNode = $('#wxMenuTree').tree('getParent', node.target);
			$('#menuParentId').val(parentNode != null ? parentNode.id : '');
			$('#menuParentName').val(parentNode != null ? parentNode.menuName : '');
		}
	}

	// 新增下级
	function addNext() {
		var node = $('#wxMenuTree').tree('getSelected');
		if (node) {
			enableButtons(['add', 'addNext', 'save']);
			disableButtons(['del']);
			if (node.state == 'closed') {
				$('#wxMenuTree').tree('expand', node.target);
			}
			$('#dataForm').css('visibility', 'visible');
			$('#dataForm').form('clear');
			//$('#enable').combobox('setValue', 'Y');
			$('#menuParentId').val(node.id);
			$('#menuParentName').val(node.menuName);
			$('#menuName').focus();
		} else {
			main.info('温馨提示', '请选择节点。');
		}
	}
	
	// 删除
	function del() {
		var node = $('#wxMenuTree').tree('getSelected');
		if (node) {
			main.confirm('温馨提示', '确定删除资源【' + node.menuName + '】?', function(r) {
				if (r) {
					main.showWaiting();
					$.ajax({
						type : 'GET',
						url : '${ctx}/wxmenu/del/' + node.id,
					}).done(function(res) {
						main.closeWaiting();
						if (res.resultCode == '0') {
							var selected = $('#wxMenuTree').tree('getSelected');
							var parent = $('#wxMenuTree').tree('getParent', selected.target);
							if (parent) {
								var children = $('#wxMenuTree').tree('getChildren', parent.target);
								if (children.length == 1) {
									$('#wxMenuTree').tree('update', {
										target : parent.target,
										isBranch : 'N'
									});
								}
							}
							$('#wxMenuTree').tree('remove', selected.target);
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
				url: "${ctx}/wxmenu/save",
				data: data
			}).done(function(res) {
				main.closeWaiting();
				if (res.resultCode == "0") {
					if ($("#id").val()) {
						$("#dataForm").form("load", res.row);
						$("#wxMenuTree").tree("reload");
					} else {
						_refreshTree($("#wxMenuTree").tree("getSelected"), res.row);
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
	
	//将菜单信息同步致微信
	function sync(){
		main.confirm('温馨提示', '未保存的数据将不会同步致微信,是否继续?', function(r) {
			if (r) {
				main.showWaiting();
				$.ajax({
					type: "POST",
					url: "${ctx}/wxmenu/syncWxMenu"
				}).done(function(res) {
					main.closeWaiting();
					main.info('温馨提示', res.resultMsg);
				}).fail(function(jqXHR, textStatus, errorThrown) {
					main.closeWaiting();
					main.error('温馨提示', '同步时出错！');
				});
			}
		});
		
	}
	
	
	function _loadData(node) {
		main.showWaiting();
		$.ajax({
			type: 'GET',
			url: "${ctx}/wxmenu/get/" + node.id,
		}).done(function(res){
			main.closeWaiting();
			if (res.resultCode == '0') {
				
				// 基本信息加载数据
				$('#dataForm').form('load', res.row);
				$("#actionId").lookup("setVal",res.row.actionId);
                $("#actionId").lookup("setTxt",res.row.actionName);
				
				// 设置上级信息
				var parentNode = $('#wxMenuTree').tree('getParent', node.target);
				$('#menuParentId').val(parentNode != null ? parentNode.id : '');
				$('#menuParentName').val(parentNode != null ? parentNode.menuName : '');
				
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
			$("#wxMenuTree").tree("reload");
			return;
		}
		if (node.id != $("#menuParentId").val()) {
			// 新增同级
			if (node) {
				$("#wxMenuTree").tree("insert", {
					after : node.target,
					data : obj
				});
			}
		} else {
			// 新增下级
			$("#wxMenuTree").tree("append", {
				parent : node.target,
				data : obj
			});
			if (node.isBranch == "N") {
				$("#wxMenuTree").tree("update", {
					target : node.target,
					isBranch : "Y"
				});
			}
		}		
		var newNode = $("#wxMenuTree").tree("find", obj.id);
		$("#wxMenuTree").tree("select", newNode.target);
	}

	//获取当前动作id
    function getActionId(){

        return  $('#actionId').lookup('getVal');
    }
	var actionLookup;

    function actionLookupFun() {

        actionLookup = $("#actionId").lookup({
            title: "选择动作",
            url: "${ctx}/action/select-action",
            width: 700,
            height: 440,
            contentWindow: {
                params: {
                    singleSelect : true,
                    checkbox : false,
                    queryParams : function (){
                        return {
                            actionId:getActionId()
                        };
                    }
                },
                actions: {
                    confirm: function(data) {
                        if (data) {
                            $("#actionId").lookup("setVal",data.actionId);
                            $("#actionId").lookup("setTxt",data.actionName);
                        }
                        main.closeDialog();
                    },
                    cancel : function() {
                        main.closeDialog();
                    }
                }
            }
        });

    }
	
</script>
</body>
</html>