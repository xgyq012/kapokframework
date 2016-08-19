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
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
</head>

<body>
<div class="g-layout">

	<div class="g-toolbar">
		<!-- <a id="createPolygon" class="easyui-linkbutton g-button" onclick="getBoundary()"><i class="fa fa-plus"></i>生成多边形</a> -->
	</div>

	<!-- 实体区域 -->
	<div id="mainLayout" class="easyui-layout g-container">
	
		<div data-options="region:'west',split:true,minWidth:250" style="width:250px;">
			<ul id="meshTree" class="easyui-tree " data-options="animate:true"></ul>
		</div>
		
		<div data-options="region:'center'">
			<div id="baiduMapContainer" style="height: 100%;width: 100%"></div>
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


	var thisUiConfig = {
		ctx : "${ctx}"
	};

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
	
	var orArray = [];	
	
	var baiduMap = {
			
			init : function (){
				Map = MyMap.initMap("baiduMapContainer");
				MyMap.addNavigationControl({
					anchor : BMAP_ANCHOR_TOP_LEFT,
					type : BMAP_NAVIGATION_CONTROL_LARGE
				});
				var point = new BMap.Point(116.300, 39.915);  // 创建点坐标
				Map.centerAndZoom(point, 12);
				Map.enableScrollWheelZoom(true);
			/* 	Map.addEventListener("click",function(e){    
					var v = e.point.lng+","+e.point.lat;
					fw.push(v)
			    }); */
				
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
				      //$("#fanWeiZuoBiao").val(fw.join(";"));
				}); 
			},
			
			openDrawer : function (){
				 
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
	
	 

	$(function() {
		
		baiduMap.init();
		
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
				baiduMap.drawing(node.fanWeiZuoBiao);
			}
		});
	});
	
	
</script>

</body>
</html></html>