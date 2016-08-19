<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>  
<head>  
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  


<title>Hello, World</title>  
<style type="text/css">  
html{height:100%}  
body{height:100%;margin:0px;padding:0px}  
#Container{height:100%}  
</style>  


<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5E7AE4DB55fe730d75561f1988429709">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
</head>  
 
<body>  
<div id="Container"></div> 
<script type="text/javascript"> 
var map = new BMap.Map("Container");          // 创建地图实例  
var point = new BMap.Point(116.300, 39.915);  // 创建点坐标  
map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别  

var marker = new BMap.Marker(point);  // 创建标注
map.addOverlay(marker);               // 将标注添加到地图中

map.addEventListener("click", function(e){  
     map.clearOverlays();
     var point = new BMap.Point(e.point.lng ,e.point.lat);//默认  
     // 创建标注对象并添加到地图    
     var marker = new BMap.Marker(point);    

     var gc = new BMap.Geocoder();
     //获取地址的数据地址
     var pt = point;
     gc.getLocation(pt, function(rs){
     var addComp = rs.addressComponents;
     address = addComp.province +  addComp.city + addComp.district + addComp.street + addComp.streetNumber;
     //画图
	 var label = new BMap.Label(address,{offset:new BMap.Size(20,-10)});
	    marker.setLabel(label);
	 });
	 map.addOverlay(marker);    
	 marker.enableDragging();    
});  

map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
map.panBy(403,200);


/*
map.addControl(new BMap.NavigationControl());        // 添加平移缩放控件
map.addControl(new BMap.ScaleControl());             // 添加比例尺控件
map.addControl(new BMap.OverviewMapControl());       //添加缩略地图控件
*/

</script>  
</body>  
</html>