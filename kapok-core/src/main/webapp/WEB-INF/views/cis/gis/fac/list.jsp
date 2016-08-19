<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>

<title>市政设施</title>
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
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">设施名称</label>
	            				<input class="easyui-validatebox form-control" name="q_facilityName_LIKE"  />
	            			</td>
		    				<td class="form-cell-1">
		    					<label class="form-label">设施类型</label>
		    					<input id="q_facilityTypeName_EQ" name="q_facilityTypeName_EQ"  class="easyui-combobox form-control"
		    							style="width:${comboboxWidth};height:${comboboxHeight}px"/>
		    				</td>
							<td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
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
	     
	     <!-- data-options="disabled:true" -->
	     <div id="minute" data-options="disabled:true"  title="详细"   >
	   		<form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="facilitiesId" name="facilitiesId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
	    		<table class="g-form" cellpadding="0" cellspacing="0" >
	    			<tbody>
	     			<tr>
	     				<td class="form-cell-1">
		            		<label class="form-label">所属机构</label>
		            		<input id="meshId" name="meshId"  type="hidden"   />
	     					<input id="meshName"  type="hidden"/>
		          		</td>
	     				<td class="form-cell-3" colspan="3">
	     					<label class="form-label">设施名称</label>
	     					<input class="easyui-validatebox form-control" name="facilityName" data-options="required:true"  />
	     				</td>
	     			</tr>
	     			
	     			<tr>
	     				<td class="form-cell-1">
	     					<label class="form-label">设施类型</label>
	     					<input id="facilityTypeName" name="facilityTypeName"  class="easyui-combobox form-control"
	     							style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	     				</td>
	     				<td class="form-cell-1">
	     					<label class="form-label">设施编码</label>
	     					<input class="easyui-validatebox form-control" id="facilityCode"  name="facilityCode" />
	     				</td>     
	     				<td class="form-cell-2" colspan="2">
	     					<label class="form-label">置办时间</label>
	     					<input class="easyui-datebox form-control"  name="anzhuangTime"  data-options="required:true"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	     				</td>   
	     			</tr>
	     			<tr>
	     				<td class="form-cell-4"  colspan="4">
	     					<label class="form-label">备注</label>
	     					<textarea rows="3" class="easyui-validatebox form-control"  name="remarks"></textarea>
	     				</td>
	     			</tr>
	     			<tr>
	     				<td  class="form-cell-2" colspan="2">
	     					<label class="form-label">所在位置</label>
	     					<input class="easyui-validatebox form-control" id="address" name="address"   />
	     				</td>
	     				<td  class="form-cell-1">
	     					<label class="form-label">X坐标</label>
	     					<input class="easyui-validatebox form-control" data-options="disabled:true" id="lon" name="lon" />
	     				</td>     				
	     				<td class="form-cell-1">
	     					<label class="form-label">Y坐标</label>
	     					<input class="easyui-validatebox form-control" data-options="disabled:true" id="lat" name="lat"   />     				
	     				</td>
	     				
	     			</tr>
	     			<tr >
		     		 	<td colspan="4">
		     		 		<label class="form-label">标记设施位置</label>
		     				<div id="Container" class="form-control" style="width:92%;height:380px;margin-left:85px;padding-bottom:34px"></div> 
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
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5E7AE4DB55fe730d75561f1988429709">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>

<script type="text/javascript" >
var addNewRecord = false; //新增记录？
var editRecord = false;
var thisUiConfig = {
	main : window.parent.main,	
	ctx : "${ctx}",
	url : "fac",
	id : "#facilitiesId",    //表单中主键字段对应的控件的Id属性值
	idName:"facilitiesId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var comId = $(thisUiConfig.id).val();
		return comId;
	}
};

$(function (){
	tabs.init();
	enableButtons(['add']);
	publicAttr.initCombobox();
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	initCombobox();
});

var tabs = {
		thisTabIndex : 0,
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
						editRecord = false;
					} else if (index == 1) { // 选中详细标签
						if (tableInfo.thisRow) {
							$(thisUiConfig.baseForm).form("load", tableInfo.thisRow);
							$("#meshId").lookup("setTxt", publicAttr.getOrgName(tableInfo.thisRow.meshId));
						}
						myMap.init();
						enableButtons([ "add", "del", "edit"]);
					}
				}
			});
		}
};

var	Msg = {
	 show : function (title, msg){
		 	title = title || "温馨提示" ;
		 	msg = msg || "操作成功" ;
			$.messager.show({
				title : "温馨提示",
				msg : "操作成功",
				timeout : 2500,
				showType : "slide"
			});
	 }
};

//基础信息初始化
var baseInfo =  {
	saveUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/save",
	getUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/edit",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/del",
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
				tableInfo.query();       //刷新列表
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
		addNewRecord = true;
		editRecord = true;
		tableInfo.thisRow = "";
		tableInfo.thisRowIndex = -1;
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$(thisUiConfig.tableList).datagrid("unselectAll");
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);	
		}
		setFormItemDisabled(thisUiConfig.baseForm, false, "#lon,#lat,#facilityCode");
		enableButtons(["save"]);
		$.ajax({
			type:"post",
			url:"${ctx}//coderule/generateCode/SZSS"
		}).done(function(number){
			$("#facilityCode").val(number);
		});
		defaultMesh();         //默认网格
	},
	
	del:function (){
		if (! tableInfo.thisRow) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的设施记录?",
			function(r) {
				if (r) {
					$.ajax({
							url : baseInfo.delUrl +"/"+ tableInfo.thisRow.facilitiesId,
							type: "POST"
					}).done(function(data) {
						$.messager.progress("close");
						if (data.resultCode == "0") {
							$(thisUiConfig.baseForm).form("clear");
							$(thisUiConfig.mainTab).tabs("select", 0);
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
							
							tableInfo.thisRow = "";
							tableInfo.thisRowIndex = -1;
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
		editRecord = true;
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);
		}
		enableButtons([ "add", "del", "save"]);
		setFormItemDisabled(thisUiConfig.baseForm, false, "#lon,#lat,#facilityCode");
	}
};

//列表加载
var tableInfo =  {
	thisRow : "",      //选中行的内容
	thisRowIndex : 0,  //选中行的下标	
	//初始化列表
	init : function (){
	
		$("#listGrid").datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			queryParams:  getFormData("#queryForm"),
			url : "${ctx}/fac/search",
			columns : [ [ {
				field : "facilitiesId",
				hidden:true,
			}, {
				field : "meshId",
				title : "所属机构",
				width : 150,
				halign:'center',
				formatter: function(value, row){
					return publicAttr.getOrgName(value);
				},
				align:'left',
			},{
				field:"facilityTypeName",
				title:"设施类型",
				width:100,
				halign:'center',
				align:'left',
				formatter : function (value,row,index){
					return publicAttr.getDictText("facilityType",value);
				}
			},{
				 field:"facilityCode",
				 title:"设施编码",
				 width:150,
				 halign:'center',
				 align:'left'
			},{
				field:"facilityName",
				title:"设施名称",
				width:180,
				halign:'center',
				align:'left'
			},{
				field:"remarks",
				title:"备注",
				width:240,
				halign:'center',
				align:'left'
			},{
				field:"address",
				title:"所在位置",
				width:200,
				halign:'center',
				align:'left'
			}]],
			
			onSelect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onUnselect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onLoadSuccess : function(data) {
				$(thisUiConfig.tableList).datagrid("unselectAll");
				if(tableInfo.thisRow){
					$(thisUiConfig.tableList).datagrid("selectRow", tableInfo.thisRowIndex);
				}
				
				//新增记录时   选中第一行   将是否为新增记录标记设为false
				if(addNewRecord){
					$(thisUiConfig.tableList).datagrid("selectRow", 0);
					addNewRecord = false;
				}
			}
			
		});
		
		// 设置分页显示形式
		$(thisUiConfig.tableList).datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	onSelectOrUnselect : function(rowIndex, rowData){
		var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
		if(len == 1){
			tableInfo.thisRow = $(thisUiConfig.tableList).datagrid("getSelected");
			tableInfo.thisRowIndex = $(thisUiConfig.tableList).datagrid("getRowIndex", tableInfo.thisRow);
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			enableButtons([ "add", "del", "edit"]);
			if(editRecord){
				enableButtons([ "add", "del", "save"]);
			}
		}else if(len == 0){
			enableButtons([ "add"]);
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
		}else{
			enableButtons([ "add", "del"  ]);
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
		}
	},
	query : function (){
		var param = getFormData("#queryForm");
		enableButtons([ "add" ]);
		$(thisUiConfig.tableList).datagrid("load", param);
		
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



var orgLookup;
function orgLookup() {
	orgLookup = $("#meshId").lookup({
		title : "选择社区机构",
		url : thisUiConfig.ctx + "/mesh/selectmesh",
		width : 350,
		height : 500,
		required : true,
		valueField: 'meshId',
		textField: 'meshName',
		contentWindow: {
			params: {
				singleSelect : true,
				checkbox : false,
			},
			actions: {
				confirm: function(data) {
					if (data) {
						$("input[name='meshId']").val(data.meshId);						
						$("#meshName").val(data.meshName);
						$("input[name='meshId']").prev().val(data.meshName);
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


var myMap = {
		init : function(){
			var map = new BMap.Map("Container");          // 创建地图实例  
			var point;
			if(tableInfo.thisRow){
				if(tableInfo.thisRow.lon && tableInfo.thisRow.lat){
					point = new BMap.Point(tableInfo.thisRow.lon, tableInfo.thisRow.lat);
				}else{
					point = new BMap.Point(116.300, 39.915);  // 创建点坐标  
				}
			}else{
				point = new BMap.Point(116.300, 39.915);  // 创建点坐标  
			}
			map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别  
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);               // 将标注添加到地图中
			
			map.addEventListener("click", function(e){  
			     map.clearOverlays();
			     
			     var point = new BMap.Point(e.point.lng ,e.point.lat);//默认  
			     // 创建标注对象并添加到地图    
			     var marker = new BMap.Marker(point);    

			     $("#lon").val(e.point.lng);
			     $("#lat").val(e.point.lat);
			     
			     
			     var gc = new BMap.Geocoder();
			     //获取地址的数据地址
			     var pt = point;
			     gc.getLocation(pt, function(rs){
			     	var addComp = rs.addressComponents;
			     	address = addComp.province +  addComp.city + addComp.district + addComp.street + addComp.streetNumber;
			     	$("#address").val(address);
			    
			    	 //画图
					 var label = new BMap.Label(address, {offset:new BMap.Size(20,-10)});
				     marker.setLabel(label);
				 });
				 map.addOverlay(marker);    
				 marker.enableDragging();    
			});  

			map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
			map.panBy(403,200);
		}
};


function initCombobox(){
	var dict = main.dict;
	var dicts = dict.dicts;
	var comboboxConfig = {
		valueField: 'dictCode',
	    textField: 'dictName',
	    editable: false,
		panelHeight: 'auto',
		disabled : false
	};
	comboboxConfig.data = dicts.facilityType.list;
	$('#q_facilityTypeName_EQ').combobox(comboboxConfig);
	comboboxConfig.disabled = true;
	$('#facilityTypeName').combobox(comboboxConfig);
}

/**
 *  默认网格
 */
function defaultMesh(){
	var meshId = main.currentUserMesh.meshId;
	$("#meshId").lookup("setTxt", publicAttr.getOrgName(meshId));
	$("#meshId").lookup('setVal', meshId);
}

</script>

</body>
</html>