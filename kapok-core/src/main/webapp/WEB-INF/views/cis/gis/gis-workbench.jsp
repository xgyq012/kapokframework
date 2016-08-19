<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>GIS工作台</title>
	
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
	<div style="height: 100%;width: 100%">
		 <div id="Container" style="height: 100%;width: 100%"></div>
	</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
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

var drawingManager ;

var styleOptions = {
   strokeColor:"red",    //边线颜色。
   fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
   strokeWeight: 1,       //边线的宽度，以像素为单位。
   strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
   fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
   strokeStyle: 'solid' //边线的样式，solid或dashed。
};


$(function (){
	baiduMap.init();
	initMesh();
});

function initMesh(){
	publicFormControl.ajax({
		url : thisUiConfig.ctx+'/mesh/findAll',
		type: "POST",
	},function (data){
		baiduMap.clear();
		if(data.rows){
			$.each(data.rows,function (i,n){
				var fanWeiZuoBiao = n.fanWeiZuoBiao;
				baiduMap.drawing(fanWeiZuoBiao);
			});
		}
		
	});
}

var baiduMap = {
		
		init:function (){
			Map = MyMap.initMap("Container");
			MyMap.addNavigationControl({});
			MyMap.centerAndZoom(116.300, 39.915); 
			Map.enableScrollWheelZoom(true);
		},
		
		clear : function (){
			Map.clearOverlays();
		},
		
		drawing : function (zb){
			if(zb){
				var pointArray = [];
				var oval = new BMap.Polygon(zb,styleOptions);
				Map.addOverlay(oval);
				pointArray = pointArray.concat(oval.getPath());
				//Map.setViewport(pointArray);    //调整视野   
			}
		}
		
}
</script>
</body>
</html>