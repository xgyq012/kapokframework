<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>避难场所信息</title>
	
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
	<div class="g-toolbar">
	    <a id="add" onclick="baseInfo.add();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	    <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	    <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	    <a id="del" onclick="baseInfo.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	</div>
	
	<div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
	     <div title="列表" style="position:relative;">
		     <div class="query-area">
			    <form id="queryForm" class="formCls" method="post">
				  <table class="g-form" cellpadding="0" cellspacing="0">
				     <tbody>
						 <tr>	
							 <td class="form-cell-1">
							     <label class="form-label">避难场所名称</label>
							     <input name="q_emePlaceName_LIKE"  class="form-control"/>
							 </td>
							 <td class="form-cell-1">
							     <label class="form-label">地址</label>
							     <input name="q_addrs_LIKE" class="form-control"   />
							 </td>
							 <td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
							 </td>
							 <td class="form-cell-1"></td>
						 </tr>
					 </tbody>
				  </table>
			    </form>
		     </div>
	         <div class="list-area" style="top:50px;">
				<table id="listGrid" style="height:100%;"></table>
			 </div>
	     </div>
	     
	     <!-- data-options="disabled:true" -->
	     <div id="minute" title="详细" data-options="disabled:true">
		     <form id="baseForm"  action="">
	    		<input type="hidden" name="emePlaceId">
	    		<input id="createrId" name="createrId" type="hidden">
	    		<table class="g-form" cellpadding="0" cellspacing="0">
	     			<tr>
	     				<td class="form-cell-2" colspan="2">
	     				   <label class="form-label">避难场所名称</label>
	     				   <input  name="emePlaceName" class="easyui-validatebox form-control"
								data-options="validType:'length[4,64]', required:'required'">
	     				</td>
	     				<td class="form-cell-1">
	     				    <label class="form-label">面积（平米）</label>
	     				    <input   name="area" class="easyui-validatebox form-control" data-options="required:true">
	     				</td>
	     				<td class="form-cell-1">
	     				    <label class="form-label">可容纳人数</label>
	     				    <input onkeydown="onlyNum();"  name="gallery" class="easyui-validatebox form-control" data-options="validType:'onlyNumber',required:true">
	     				</td>
	     			</tr>
	     			<tr>
	     				<td class="form-cell-1">
	     				    <label class="form-label">负责人</label>
	     				    <input  name="fuzeren" class="easyui-validatebox form-control" data-options="validType:'length[2,18]'">
	     				</td>
	     				<td class="form-cell-1">
	     				    <label class="form-label">负责人电话</label>
	     				    <input  name="phone" class="easyui-validatebox form-control" data-options="validType:'phoneRex'"/>
	     				</td>
	     				<td class="form-cell-2" colspan="2">
	     				    <label class="form-label">录入时间</label>
	     				    <input name="createTime" disabled="disabled" class="easyui-datetimebox form-control" data-options="formatter:dateformatter"
	     				    	style="width:${comboboxWidth};height:${comboboxHeight}px">
	     				</td>
	     			</tr> 
	     			<tr>
	     				<td class="form-cell-4" colspan="4">
	     				    <label class="form-label">备注</label>
	     				    <textarea name="remark" rows="4"  class="easyui-validatebox form-control"></textarea>
	     				</td>
	     			</tr>
					<tr>
	     				<td  class="form-cell-2" colspan="2">
	     					<label class="form-label">地址</label>
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
		     		 		<label class="form-label">标记避难场所位置</label>
		     				<div id="Container" class="form-control" style="width:92%;height:380px;margin-left:85px;padding-bottom:34px"></div> 
		     		    </td>		
	     			</tr>	     			
	     		</table>
	     	 </form>
	     </div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gx-validate.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5E7AE4DB55fe730d75561f1988429709">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
<script type="text/javascript" >

var thisUiConfig = {
	ctx : "${ctx}",
	url : "emeplace",
	id : "#emePlaceId",    //表单中主键字段对应的控件的Id属性值
	idName:"emePlaceId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var householderId = $(thisUiConfig.id).val();
		return householderId;
	}
};

$(function (){
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	
});

var tabs = {
		thisTabIndex: 0,
		init : function (){
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
			$(thisUiConfig.mainTab).tabs({
				onSelect : function(title, index) {    //切换列表与明细标签时调用
					tabs.thisTabIndex = index;
					if (index == 0) { // 选中列表标签
						$(thisUiConfig.baseForm).form("clear");
					    setFormItemDisabled(thisUiConfig.baseForm, true, null);
						if ($(thisUiConfig.tableList).datagrid("getSelections").length > 0) {
							$(thisUiConfig.mainTab).tabs("enableTab", 1);
							enableButtons([ "add", "del", "edit" ]);
						} else {
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
						}
					} else if (index == 1) { // 选中详细标签
						var row = $(thisUiConfig.tableList).datagrid("getSelected");
						if (row) {
							$(thisUiConfig.baseForm).form("load",row);
						}
						myMap.init();
						enableButtons([ "add", "del", "edit"]);
					}
				}
			});
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

//基础信息初始化
var baseInfo =  {
	saveUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/save",
	getUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/edit",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/softDelList",
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
				tableInfo.query();
				enableButtons([ "add","save","del" ]);
				$.messager.show({
					title : "温馨提示",
					msg : "操作成功",
					timeout : 2500,
					showType : "slide"
				});
				
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				$.messager.alert("温馨提示", "保存出错！", "error");
			});
		}
	},
	
	//点击新增基础数据时调用此方法
	add : function (){
		console.log("base add");
		//clear data
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$("#listGrid").datagrid("unselectAll");
		$(thisUiConfig.mainTab).tabs("enableTab", 1);
		$(thisUiConfig.mainTab).tabs("select", 1);
		setFormItemDisabled(thisUiConfig.baseForm,false,"#lon,#lat");
		enableButtons(["save"]);
	},
	
	del:function (){
		var row = $(thisUiConfig.tableList).datagrid('getSelections');
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中的避难场所信息?",
			function(r) {
				if (r) {
					var ids = "";
					$.each(row, function (i, n){
						ids = ids + n[thisUiConfig.idName] + ",";
					});
					ids = ids.substring(0, ids.length - 1);
					$.ajax({
							url : baseInfo.delUrl,
							type: "POST",
							data : {
								ids : ids
							}
					}).done(function(data) {
						$.messager.progress("close");
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$(thisUiConfig.mainTab).tabs("select", 0);
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
									
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
		var tab = $(thisUiConfig.mainTab).tabs('getSelected');
		var index = $(thisUiConfig.mainTab).tabs('getTabIndex',tab);
		if(index != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);
		}
		enableButtons([ "add", "del", "save"]);
		setFormItemDisabled(thisUiConfig.baseForm,false,"#lon,#lat");
	}
};

//列表加载
var tableInfo =  {
	
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			queryParams:  getFormData(thisUiConfig.queryForm),
			url : baseInfo.search,
			columns : [ [ {
				field : "emePlaceId",
				hidden:true
			}, {
				field : "emePlaceName",
				title : "避难场所名称",
				halign:'center',
				align:'left',
				width : 150
			},{
				field:"address",
				title:"地址",
				halign:'center',
				align:'left',
				width:200
			},{
				field:"area",
				title:"场所面积(平米)",
				halign:'center',
				align:'right',
				width:110,
			},{
				 field:"gallery",
				 title:"可容纳人数",
				 halign:'center',
				 align:'right',
				 width:80
			},{
				 field:"remark",
				 title:"备注",
				 halign:'center',
				 align:'left',
				 width:300
			}]],

			onSelect : function(rowIndex, rowData) {
				var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
				if(len==1){
					$(thisUiConfig.mainTab).tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit", "clmx" ]);
				}else if(len==0){
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
					enableButtons([ "add"]);
				}else{
					enableButtons([ "add", "del"  ]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
			},
			onUnselect : function(rowIndex, rowData) {
				var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
				console.log(len);
				if(len==1){
					$(thisUiConfig.mainTab).tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit", "clmx"]);
				}else if(len==0){
					enableButtons([ "add"]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}else{
					enableButtons([ "add", "del"  ]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
			},
			onLoadSuccess : function(data) {
				$(thisUiConfig.tableList).datagrid("unselectAll");
				if(tabs.thisTabIndex == 0){
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
			}
			
		});
		
		// 设置分页显示形式
		$(thisUiConfig.tableList).datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	query : function (){
		var param = getFormData("#queryForm");
		enableButtons([ "add" ]);
		$(thisUiConfig.tableList).datagrid("load", param);
		
	}
	
}


var myMap = {
		init : function(){
			var map = new BMap.Map("Container");          // 创建地图实例  
			var point;
			var row = $(thisUiConfig.tableList).datagrid("getSelected");
			if(row){
				point = new BMap.Point(row.lon, row.lat);
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

function dateformatter(date){
	return date.formatDate("yyyy-MM-dd hh:mm:ss");
}
</script>


</body>
</html>