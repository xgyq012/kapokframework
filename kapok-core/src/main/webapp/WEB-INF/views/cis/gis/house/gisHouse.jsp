<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>房屋定位</title>
<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css" type="text/css">
<!--[if IE 7]> 
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css"> 
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
<link rel="stylesheet" href="${ctx}/resources/css/page.css">
<style type="text/css">
  .c-label{
 	margin-left: 10px;
 	display: block;
 }
</style>
</head>
<body >
<div class="g-layout">

	 <div class="easyui-layout" style="width:100%;height:100%;">
	  	<div data-options="region:'north'" style="height:80px">
	  		 <div class="query-area">
			    <form id="queryForm" method="post">
				    <table class="g-form" class="formCls" cellpadding="0" cellspacing="0">
						 <tr>	
							<td class="form-cell-1">
	            				<label class="form-label">小区名称</label>
	            				<input name="q_c.community_Name_LIKE" class="easyui-validatebox form-control"  /></td>
							<td class="form-cell-1">
	            				<label class="form-label">楼栋号</label>
	            				<input   name="q_b.ld_Code_LIKE" class="easyui-validatebox form-control"  /> 
	            			</td>
	            			<td class="form-cell-1">
	            				<label class="form-label">房屋号</label>
	            				<input name="q_house.dy_Code_LIKE" class="easyui-validatebox form-control"  /> 
	            			</td>
							<td class="form-cell-1">
	            				<label class="form-label">姓名</label>
	            				<input name="q_h.HOUSEHOLDER_NAME_LIKE" class="easyui-validatebox form-control" />
	            			</td>
							
						</tr>
						<tr>
							<td class="form-cell-1"></td>
							<td class="form-cell-1"></td>
							<td class="form-cell-1"></td>
							<td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
	  	</div>
	  	
        <div id="queryResult" data-options="region:'east',hideCollapsedContent:true,collapsed:false" title="查询结果" style="width:230px">
				<table id="listGrid" style="height:100%"></table>
        </div>
        
        <div data-options="region:'center'" >
        	
        	<div id="Container" style="height: 100%;width: 100%"></div>
        	
        </div>
    </div>
	 
</div>
</body>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5E7AE4DB55fe730d75561f1988429709"></script>
<script type="text/javascript" src="${ctx}/resources/js/gis.js"></script>
<script type="text/javascript">

var thisUiConfig = {
	main:window.main,
	ctx : "${ctx}"
};

/**
 * 地图实例
 */
var Map;


$(function (){
	publicAttr.initCombobox();
	orgLookup();
	baiduMap.init();
	tableInfo.init();
});


var baiduMap = {
	
		init:function (){
			Map = MyMap.initMap("Container");
			MyMap.addNavigationControl({});
			MyMap.centerAndZoom(116.300, 39.915); 
			Map.enableScrollWheelZoom(true);
		}
		
}
	

var tableInfo  = {
		
		init : function (){
			var  ctx = thisUiConfig.ctx;
			var view = $.extend({}, $.fn.datagrid.defaults.view, {
				renderRow: function(target, fields, frozen, rowIndex, rowData){
					var cc = [];
						cc.push('<td colspan=' + fields.length + ' style="padding:5px 0px;width:230px;">');
						if (!frozen){
							cc.push('<img src="' + ctx + '/resources/images/gis/marker_red'+ (rowIndex + 1) +'.png' + '" style="float:left;margin-left:2px">');
			                cc.push('<div style="float:left;padding:5px 0;">');
			                for(var i=0; i<fields.length; i++){
			                    var copts = $(target).datagrid('getColumnOption', fields[i]);
			                    if(!copts.hidden){
			                    	var ldCode =rowData['ldCode'] || "";
									var communityName = rowData['communityName'] || "";
									var dyCode = rowData['dyCode']   || "";
									var value ="";
									if(dyCode){
										value = "小区：" + communityName + "<br> 楼栋：" + ldCode + " <br>单元号：" + dyCode;
									}
			                    	cc.push('<span class="c-label">' + value + '');
			                    }
			                }
			               	cc.push('</div>');
						}
						cc.push('</td>');
					return cc.join('');
				}
			});
			
			$("#listGrid").datagrid({
				rownumbers: false,
				singleSelect: true,
				autoRowHeight: false,
				pagination: false,
				showHeader:false,
				pageSize : 10,
				pageList : defaultPageList,
				pagination : true,
				queryParams: tableInfo.getQueryFormData("#queryForm"),
				url : "${ctx}/house/queryHouseByHolder",
				view:view,
				columns : [ [ {
					field : "houseId",
					hidden:true,
				},{
					field : "buildId",
					hidden:true,
				},{
					field : "comId",
					hidden:true,
				},{
					field : "ldCode",
					title : "楼栋号",
					width : 100,
					halign:'center',
					align:'left',
					hidden:true
				}, {
					field : "dyCode",
					title : "单元号",
					width : 100,
					halign:'center',
					align:'left'
					 
				},{
					field:"communityName",
					title:"小区名称",
					width:150,
					halign:'center',
					align:'left',
					hidden:true
				}]],
				
				onClickRow : function(rowIndex, rowData) {
					if(rowData.lon && rowData.lat){
						Map.setCenter(new BMap.Point(rowData.lon, rowData.lat));
					}else{
						$.messager.show({
							title : "温馨提示",
							msg : "所选房屋不存在经纬度。" ,
							timeout : 2500,
							showType : "slide"
						});
					}
					
				},
			 
				onLoadSuccess:function (data){
					Map.clearOverlays();
					var rows = data.rows;
					if(rows && rows.length>0){
						var  ctx = thisUiConfig.ctx;
						 $.each(rows,function (i,n){
							var seq = i+1;
							var url = ctx + '/resources/images/gis/marker_red'+ seq +'.png' ;
							MyMap.addMarker(n.lon,n.lat,n.dyCode,url,true)
						});
					}else{
						$("#listGrid").datagrid("loadData", {});
					}
				}
				
			});
			
			// 设置分页显示形式
			$("#listGrid").datagrid("getPager").pagination({
				layout: ['prev','manual','next']
			});
		},
		
		query : function (){
			var param = tableInfo.getQueryFormData("#queryForm");
			$("#listGrid").datagrid("load", param);
		},
		
		getQueryFormData : function (formId){
			var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
			var data =  getFormData(formId) || {};
			if(meshIds){
				data['q_house.org_IN'] = meshIds;
			}
			return data;
		}
		
		
}

var orgLookup;

function orgLookup() {
	orgLookup = $('#meshId').lookup({
		title : "选择所属网格",
		url : thisUiConfig.ctx + "/mesh/selectmesh",
		width : 350,
		height : 500,
		required : false,
		valueField: 'meshId',
		textField: 'meshName'
	});
}







</script>
</html>