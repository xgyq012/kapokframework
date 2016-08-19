<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>社区网格</title>
	
    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/page.css">
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
</head>

<body>
<div class="g-layout">
	<!-- 按钮区域 -->
	<div class="g-toolbar">
		<a id="add" class="easyui-linkbutton g-button" onclick="add()"><i class="fa fa-plus"></i>新增同级</a>
		<a id="addNext" class="easyui-linkbutton g-button" onclick="addNext()" data-options="disabled:true"><i class="fa fa-plus"></i>新增下级</a>
		<a id="del" class="easyui-linkbutton g-button" onclick="del()" data-options="disabled:true"><i class="fa fa-trash-o"></i>删除</a>
<!-- 		<a id="edit" class="easyui-linkbutton g-button" onclick="edit()" data-options="disabled:true"><i class="fa fa-edit"></i>编辑</a> -->
		<a id="save" class="easyui-linkbutton g-button" onclick="save()" data-options="disabled:true"><i class="fa fa-floppy-o"></i>保存</a>
	</div>

	<!-- 实体区域 -->
	<div id="mainLayout" class="easyui-layout g-container">
		<div data-options="region:'west',split:true,minWidth:250" style="width:250px;">
			<ul id="meshTree" class="easyui-tree " data-options="animate:true"></ul>
		</div>
		<div data-options="region:'center'">
			<div id="mainTab" class="easyui-tabs g-tabs1" style="width:100%;height:100%" data-options="fit:true,border:false">
				<div title="基本信息">
					<form id="mainForm" style="visibility:hidden">
						<input id="meshId" name="meshId" type="hidden">
						<input name="createrId" type="hidden">
						<input name="createTime" type="hidden">
						<input id="parentMeshId" name="parentMeshId" type="hidden">
						<input name="fullPath" type="hidden">
						<input id="isLeaf" name="isLeaf" type="hidden">
						<input id="delSign" name="delSign" type="hidden">
				      	<table class="g-form" cellpadding="0" cellspacing="0">
				      		<tbody>
				      			<tr>
					          		<td class="form-cell-2" colspan="2">
					            		<label class="form-label" title="上级网格">上级网格</label>
					            		<input id="parentMeshName" class="easyui-validatebox form-control" readonly>
					          		</td>
					          		<td class="form-cell-1">
					            		<label class="form-label" title="网格编码">网格编码</label>
					            		<input id="meshCode" name="meshCode" class="easyui-validatebox form-control">
					          		</td>
					          		<td class="form-cell-1">
					            		<label class="form-label" title="网格类型">网格类型</label>
					            		<input id="meshType" name="meshType" class="easyui-combobox form-control"
					            			style="width:${comboboxWidth};height:${comboboxHeight}px">
					          		</td>
					        	</tr>
					        	<tr>
					          		<td class="form-cell-4" colspan="4">
					                    <label class="form-label" title="网格名称">网格名称</label>
					                    <input name="meshName" class="easyui-validatebox form-control" data-options="required:true">
					          		</td>
					        	</tr>
					        	<tr>
					          		<td class="form-cell-4" colspan="4">
					                    <label class="form-label" title="网格全称">网格全称</label>
					                    <input name="meshFullName" class="easyui-validatebox form-control">
					          		</td>
					        	</tr>
					        	<tr>
					          		<td class="form-cell-4" colspan="4">
										<label class="form-label">网格概况</label>
										<textarea name="meshDesc" class="easyui-validatebox form-control" rows="5"></textarea>
					          		</td>
					        	</tr>
					        	<tr>
					        		<td class="form-cell-2" colspan="2">
					                    <label class="form-label" title="经度坐标">经度坐标</label>
					                    <input name="lon" class="easyui-validatebox form-control" readonly>
					          		</td>
					          		<td class="form-cell-2" colspan="2">
					                    <label class="form-label" title="纬度坐标">纬度坐标</label>
					                    <input name="lat" class="easyui-validatebox form-control" readonly >
					          		</td>
					          	</tr>
					        	<tr>
					          		<td class="form-cell-4" colspan="4">
										<label class="form-label">范围坐标</label>
										<textarea id="fanWeiZuoBiao" name="fanWeiZuoBiao" class="easyui-validatebox form-control" rows="3" readonly></textarea>
					          		</td>
					        	</tr>
					        	<tr>
					          		<td class="form-cell-4" colspan="4">
										<label class="form-label">地理信息</label>
										<div style="width:100%;height:380px;margin-left:85px;padding-bottom:34px">
											<div class="g-toolbar">
												<a id="setLonLat" class="easyui-linkbutton g-button"><i class="fa fa-anchor"></i>设置经纬度坐标</a>
												<a onclick="baiduMap.openDrawer();" id="setRang" class="easyui-linkbutton g-button"><i class="fa fa-object-group"></i>设置范围坐标</a>
											</div>
											<div id="baiduMapContainer" style="width:100%;height:100%;border: 1px #c3d9e0 solid"></div>
										</div>
					          		</td>
					        	</tr>
				        	</tbody>
				      	</table>
					</form>
				</div>
				<div title="网格成员" data-options="disabled:true">
					<div style="height:100%">
			    			<div id="userListToolbar" class="g-toolbar">
								<a id="addUser" class="easyui-linkbutton g-button" onclick="addUser()"><i class="fa fa-plus"></i>添加</a>
								<a id="delUser" class="easyui-linkbutton g-button" onclick="delUser()" data-options="disabled:true"><i class="fa fa-trash-o"></i>删除</a>
					      	</div>
					       <table id="userList" style="height:100%;"></table>
					 </div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${baiduKey}"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gis.js"></script>

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
		baiduMap.init();
	//	var map = new BMap.Map('');  // 创建地图实例
	//	var point = new BMap.Point(116.300, 39.915);  // 创建点坐标
	//	map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别
		
		comboboxConfig.data = dicts.MeshType.list;
	 	$('#meshType').combobox(comboboxConfig);
		
		$('#meshTree').tree({
			url: '${ctx}/mesh/getMesh',
			formatter: function(node) {
				return node.meshName;
			},
			loadFilter: function(data, parent) {
				data.forEach(function(x) {
					x.id = x.meshId;
				});
				return data;
			},
			onSelect: function(node) {
				if ($('#mainForm').css('visibility') == 'hidden') {
					$('#mainForm').css('visibility', 'visible');
				}
				_loadData(node);
				// 启用网格成员标签页
				$('#mainTab').tabs('enableTab', 1);
			}
		});
		
		 $('#mainTab').tabs({
			 onSelect: function(title, index) {
			 }
		 });
		
 		$('#userList').datagrid({
 			rownumbers : true,
			singleSelect : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			toolbar: '#userListToolbar',
			onDblClickRow: _onUserListlDblClickRow,
			idField: 'userId',
 			columns : [[
				{field: '', checkbox: true},
				{field: 'relaId', title: '主键', halign: 'center', hidden: true},
				{field: 'meshId', title: '辖区ID', halign: 'center', hidden: true},
				{field: 'userId', title: '用户ID', halign: 'center', hidden: true},
				{field: 'createrId', title: '创建人ID', halign: 'center', hidden: true},
				{field: 'createTime', title: '创建时间', halign: 'center', hidden: true},
				{field: 'userName', title: '用户账号', halign: 'center', width: 100},
				{field: 'realname', title: '用户姓名', halign: 'center', width: 100},
				{field: 'statusName', title: '状态', halign: 'center', align: 'center', width: 50},
				{
					field: 'isManager',
					title: '是否负责人',
					halign: 'center',
					align: 'center',
					width: 80,
					formatter: function(value,row,index) {
						if ('Y' == value){
							return '<i class="fa fa-check-square"></i>';
						} else {
							return '';
						}
					},
					editor: {
						type: 'combobox',
						options: {
							valueField: 'dictCode',
							textField: 'dictName',
							editable: false,
							panelHeight: 'auto',
							data: dicts.YesOrNo.list
						}
					}
				}
 			]],
			onSelect: function(rowIndex, rowData) {
				enableButtons(['delUser']);
			},
			onSelectAll: function(rowIndex, rowData) {
				enableButtons(['delUser']);
			},
			onUnselect: function(rowIndex, rowData) {
				var rows = $('#userList').datagrid('getSelections');
				if (rows.length == 0) {
					disableButtons(['delUser']);
				}
			},
			onUnselectAll: function(rowIndex, rowData) {
				disableButtons(['delUser']);
			}
 		});
 		
 		$("#mainLayout").click(function() {
			$("#userList").datagrid("acceptChanges");
		});
	
	});
	
	// 新增同级
	function add() {
		enableButtons(['add', 'addNext', 'save']);
		disableButtons(['del']);
		$('#mainForm').css('visibility', 'visible');
		$('#mainForm').form('clear');
		$('#delSign').val('N');
		$('#isLeaf').val('Y');
		$('#meshCode').focus();
		var node = $('#meshTree').tree('getSelected');
		if (node) {
			var parentNode = $('#meshTree').tree('getParent', node.target);
			$('#parentMeshId').val(parentNode != null ? parentNode.meshId : '');
			$('#parentMeshName').val(parentNode != null ? parentNode.meshName : '');
		}
		$('#mainTab').tabs('select', 0);
		$('#mainTab').tabs('disableTab', 1);
		$('#userList').datagrid("loadData", {"total" : 0, "rows" : []});
	}
	
	// 新增下级
	function addNext() {
		var node = $('#meshTree').tree('getSelected');
		if (node) {
			enableButtons(['add', 'addNext', 'save']);
			disableButtons(['del']);
			if (node.state == 'closed') {
				$('#meshTree').tree('expand', node.target);
			}
			$('#mainForm').css('visibility', 'visible');
			$('#mainForm').form('clear');
			$('#parentMeshId').val(node.meshId);
			$('#parentMeshName').val(node.meshName);
			$('#delSign').val('N');
			$('#isLeaf').val('Y');
			$('#meshCode').focus();
			$('#mainTab').tabs('select', 0);
			$('#mainTab').tabs('disableTab', 1);
			$('#userList').datagrid("loadData", {"total" : 0, "rows" : []});
		} else {
			main.info('温馨提示', '请选择节点。');
		}		
	}
	
	// 删除
	function del() {
		var node = $('#meshTree').tree('getSelected');
		if (node) {
			main.confirm('温馨提示', '确定删除网格【' + node.meshName + '】?', function(r) {
				if (r) {
					main.showWaiting();
					$.ajax({
						type : 'GET',
						url : '${ctx}/mesh/del/' + node.meshId,
					}).done(function(res) {
						main.closeWaiting();
						if (res.resultCode == '0') {
							var selected = $('#meshTree').tree('getSelected');
							var parent = $('#meshTree').tree('getParent', selected.target);
							if (parent) {
								var children = $('#meshTree').tree('getChildren', parent.target);
								if (children.length == 1) {
									$('#meshTree').tree('update', {
										target : parent.target,
										isLeaf : 'Y'
									});
								}
							}
							$('#meshTree').tree('remove', selected.target);
							enableButtons(['add']);
							disableButtons(['addNext', 'del', 'save']);
							$('#mainForm').css('visibility', 'hidden');
							$('#mainForm').form('clear');
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
		var tab = $('#mainTab').tabs('getSelected'),
			index = $('#mainTab').tabs('getTabIndex',tab);
		if (index == 0) {
			if ($('#mainForm').form('validate')) {
		 		main.showWaiting();
				var data = getFormData('#mainForm');
				$.ajax({
					type: 'POST',
					url: '${ctx}/mesh/save',
					data: data
				}).done(function(res) {
					main.closeWaiting();
					if (res.resultCode == '0') {
						if ($('#meshId').val()) {
							$('#mainForm').form('load', res.row);
						} else {
							_refreshTree($('#meshTree').tree('getSelected'), res.row);
						}
						$.messager.show({
							title: '温馨提示',
							msg: '操作成功',
							timeout: 2500,
							showType: 'slide'
						});
					} else {
						main.error('温馨提示', res.resultMsg);
					}
				}).fail(function(jqXHR, textStatus, errorThrown) {
					main.closeWaiting();
					main.error('温馨提示', '保存时出错!');
				});
			}			
		}
		else {
			var rows = $('#userList').datagrid('getData').rows
			rows.forEach(function(x, i) {
				delete x.creater;
				delete x.lastUpdater;
				delete x.realname;
				delete x.statusName;
				delete x.userName;
				delete x.meshName;
			});
			_saveMeshUser({
				meshUser: JSON.stringify(rows)
			});
		}
	}
	
	// 添加用户
	function addUser() {
		main.dialog({
			id: 'userframe',
			title: "选择用户",
			url: '${ctx}/user/selectuser',
			width: 610,
			height: 425,
			cache: false,
			modal: true,
			contentWindow: {
				params: {
					singleSelect : false,
					checkbox : true
				},
				actions: {
					confirm : function(data) {
						for (var i = 0; i < data.length; i++) {
	            			var rowIndex = $('#userList').datagrid('getRowIndex', data[i].userId);
	            			var row = {};
	            			if (rowIndex == -1) {
	            				row.meshId = $('#meshId').val();
	            				row.userId = data[i].userId;
	            				row.createrId = '';
	            				row.createTime = '';
	            				row.userName = data[i].userName;
	            				row.realname = data[i].realname;
	            				row.statusName = data[i].statusName;
	            				row.isManager = 'N';
	            				$('#userList').datagrid('insertRow',{index:0,row:row});
	            			}
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
	
	// 删除用户
	function delUser() {
		main.confirm('温馨提示', '确定删除这些网格成员?', function(r) {
			if (r) {
				var rows = $('#userList').datagrid("getSelections");
				rows.forEach(function(x, i) {
					delete x.creater;
					delete x.lastUpdater;
					delete x.realname;
					delete x.statusName;
					delete x.userName;
					delete x.meshName;
				});
				_saveMeshUser({
					isDel: 'Y',
					meshUser: JSON.stringify(rows)
				});
			}
		});
	}	
	
	function _saveMeshUser(data) {
		main.showWaiting();
		$.ajax({
			type: 'POST',
			url: '${ctx}/mesh/saveMeshUser',
			data: data
		}).done(function(res) {
			main.closeWaiting();
			if (res.resultCode == '0') {
				var node = $('#meshTree').tree('getSelected');
				$("#userList").datagrid({
					queryParams: {
						'q_mesh.meshId_EQ': node.meshId
					},
					url: '${ctx}/mesh/meshUserSearch',
				});
				$("#userList").datagrid('unselectAll');
				$.messager.show({
					title: '温馨提示',
					msg: '操作成功',
					timeout: 2500,
					showType: 'slide'
				});
			} else {
				main.error('温馨提示', res.resultMsg);
			}
		}).fail(function(jqXHR, textStatus, errorThrown) {
			main.closeWaiting();
			main.error('温馨提示', '保存时出错!');
		});
	}
	
	function _loadData(node) {
		main.showWaiting();
		$.ajax({
			type: 'GET',
			url: '${ctx}/mesh/get/' + node.meshId,
		}).done(function(res){
			main.closeWaiting();
			if (res.resultCode == '0') {
				
				// 基本信息加载数据
				$('#mainForm').form('load', res.row);
				
				baiduMap.drawing(res.row.fanWeiZuoBiao);//设置地图
				
				// 设置上级辖区信息
				var parentNode = $('#meshTree').tree('getParent', node.target);
				$('#parentMeshId').val(parentNode != null ? parentNode.meshId : '');
				$('#parentMeshName').val(parentNode != null ? parentNode.meshName : '');
				
				// 辖区成员加载数据
				$("#userList").datagrid({
					queryParams: {
						'q_mesh.meshId_EQ': res.row.meshId
					},
					url: '${ctx}/mesh/meshUserSearch',
				});
				
				// 控制按钮
				if (node.isLeaf == 'Y') {
					enableButtons(['add', 'addNext', 'del', 'edit', 'save']);
				} else {
					enableButtons(['add', 'addNext', 'edit', 'save']);
					disableButtons(['del']);
				}
				disableButtons(['delUser']);
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
			$('#meshTree').tree('reload');
			return;
		}
		if (node.meshId != $('#parentMeshId').val()) {
			// 新增同级
			if (node) {
				$('#meshTree').tree('insert', {
					after : node.target,
					data : obj
				});
			}
		} else {
			// 新增下级
			$('#meshTree').tree('append', {
				parent : node.target,
				data : obj
			});
			if (node.isLeaf == 'Y') {
				$('#meshTree').tree('update', {
					target : node.target,
					isleaf : 'N'
				});
			}
		}		
		var newNode = $('#meshTree').tree('find', obj.id);
		$('#meshTree').tree('select', newNode.target);
	}
	
	function _onUserListlDblClickRow(index) {
		$('#userList').datagrid('acceptChanges');
		$('#userList').datagrid('unselectAll');
		$('#userList').datagrid('selectRow', index).datagrid('beginEdit', index);
	}
	
	
	 var fw=[];
	
	 var Map;
	 
	 var drawingManager ;
	
	 var styleOptions = {
	        strokeColor:"red",    //边线颜色。
	        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
	        strokeWeight: 1,       //边线的宽度，以像素为单位。
	        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
	        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
	        strokeStyle: 'solid' //边线的样式，solid或dashed。
	    };
	
	var baiduMap = {
			
		init : function (){
			Map = MyMap.initMap("baiduMapContainer");
			MyMap.addNavigationControl({
				anchor : BMAP_ANCHOR_TOP_LEFT,
				type : BMAP_NAVIGATION_CONTROL_LARGE
			});
			drawingManager = MyMap.drawingManager({
				isOpen: false, //是否开启绘制模式
		        enableDrawingTool: false, //是否显示工具栏
		        drawingToolOptions: {
		            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
		            offset: new BMap.Size(5, 5), //偏离值
		        },
		        polygonOptions: styleOptions  //多边形的样式
			});
			drawingManager.addEventListener('overlaycomplete', function (e){
			      $("#fanWeiZuoBiao").val(fw.join(";"));
			});
			
			var point = new BMap.Point(116.300, 39.915);  // 创建点坐标
			Map.centerAndZoom(point, 15);
		//	Map.enableScrollWheelZoom(true);
			Map.addEventListener("click",function(e){   //单击地图，形成折线覆盖物
				var v = e.point.lng+","+e.point.lat;
				fw.push(v)
		    });
			
		},
		
		openDrawer : function (){
			baiduMap.clear();
			fw.length=0;
			drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
			drawingManager.open();
		},
		closeDrawer : function (){
			
		},
		clear : function (){
			Map.clearOverlays();
			fw.length=0;
		},
		
		drawing : function (zb){
			baiduMap.clear();
			if(zb){
				var pointArray = [];
				var oval = new BMap.Polygon(zb,styleOptions);
				Map.addOverlay(oval);
				pointArray = pointArray.concat(oval.getPath());
				Map.setViewport(pointArray);    //调整视野   
			}
		}
		
	}
	
	
</script>

</body>
</html>