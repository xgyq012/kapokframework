/**
 * 地图实例
 */


var MyMap = {
		
		ObjectMap : null,
		
		initMap : function (id){
			
			MyMap.ObjectMap = new BMap.Map(id);          // 创建地图实例  
			
			return MyMap.ObjectMap;
		},
		
		/**
		 * 设置中心坐标
		 * @param lon
		 * @param lat
		 */
		centerAndZoom:function (lon,lat){
			var defaultPoint = new BMap.Point(lon,lat);  // 创建点坐标  
			MyMap.ObjectMap.centerAndZoom(defaultPoint, 15); 
		},
		
		/**
		 * 添加地图平移缩放控件
		 */
		 addNavigationControl : function (options){
		    var op = {
					anchor : BMAP_ANCHOR_TOP_LEFT,
					type : BMAP_NAVIGATION_CONTROL_LARGE
				};
		    $.extend(true,op,options || {});
			var navigator = new BMap.NavigationControl(op);
			MyMap.ObjectMap.addControl(navigator);
			return navigator;
		},

		/**
		 * 添加地图缩略控件
		 */
		 addOverviewMapControl : function (options){
			 var op = {
				anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
				isOpen : true
			 };
			$.extend(true,op,options || {});
			var overview = new BMap.OverviewMapControl(op);
			MyMap.ObjectMap.addControl(overview);
			return overview;
		},

		/**
		 * 添加地图比例尺控件
		 */
		 addScaleControl : function (options){
			var op = {
				anchor : BMAP_ANCHOR_BOTTOM_LEFT
			};
			$.extend(true,op,options || {});
			var scale = new BMap.ScaleControl(op);
			MyMap.ObjectMap.addControl(scale);
			return scale ;
		},
		
		/**
		 * 添加标注
		 * @param vlon 经度
		 * @param vlat 纬度
		 * @param vtitle 标题
		 * @param seq 标注点的序号
		 * @param isCenter 是否把该标注设置为中心
		 */
		 addMarker : function (vlon, vlat, vtitle, iconUrl, isCenter){
			if(vlon == "" || vlat == "") {
				return;
			}
			//自定义图标
			var icon = new BMap.Icon(iconUrl, new BMap.Size(18, 27), {
			    anchor: new BMap.Size(10, 30)
			});

		    var marker = new BMap.Marker(new BMap.Point(vlon, vlat), {icon: icon}); //创建标注
		    marker.setTitle(vtitle);
		    MyMap.ObjectMap.addOverlay(marker);              // 将标注添加到地图中
		    if(isCenter){
		    	MyMap.ObjectMap.setCenter(new BMap.Point(vlon, vlat));//设置地图的中心点
		    }
		    return marker;
		},
		
		//鼠标绘制点线面
		drawingManager : function (options){
			
			var op = {
			        isOpen: false, //是否开启绘制模式
			        enableDrawingTool: true, //是否显示工具栏
			        drawingToolOptions: {
			            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
			            offset: new BMap.Size(5, 5), //偏离值
			        }
			       // circleOptions: styleOptions, //圆的样式
			      // polylineOptions: styleOptions, //线的样式
			       // polygonOptions: styleOptions, //多边形的样式
			       // rectangleOptions: styleOptions //矩形的样式
			    };
			
			//实例化鼠标绘制工具
		    var drawingManager = new BMapLib.DrawingManager(MyMap.ObjectMap,options || op);  
		    
		    return drawingManager;
		}
		
}