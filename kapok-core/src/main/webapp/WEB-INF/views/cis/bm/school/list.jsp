<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>学校信息</title>
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
<div class="g-layout" style="padding-bottom:30px;">

<div class="g-toolbar">
	<a onclick="baseInfo.add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
</div>


<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

        <div title="列表" style="position:relative;">
         
		<div id="query">
				<form id="queryForm" class="formCls" method="post">
					<table  class="g-form" cellpadding="0" cellspacing="0" >
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">学校名称</label>
	            				<input   name="q_xxmc_LIKE" class="easyui-validatebox form-control"   /></td>
	            				<td class="form-cell-1">
	            				<label class="form-label">学校地址</label>
	            				<input   name="q_dz_LIKE" class="easyui-validatebox form-control"   /></td>
							<td class="form-cell-1 f-button">
								<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
								<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
							</td> 	
							<td class="form-cell-1"></td>
							<td class="form-cell-1"></td>
						</tr>
					</table>
				</form>
			</div>
	         
         <div class="list-area" style="top:50px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
         
     </div>
     
      <div id="minute" data-options="disabled:true"  title="详细"   >
      
    		<form id="baseForm"  action="">
	    		<!-- 隐藏属性 -->
	    		<input id="schoolId" name="schoolId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		<table>
     			<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">所属机构</label>
     					 <input id="ssjg" name="ssjg"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">学校名称</label>
     					<input name=xxmc class="easyui-validatebox form-control" required="required"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">学校班额</label>
     					<input name="xxbe" class="easyui-validatebox form-control"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">占地面积(m2)</label>
     					<input  name="xxzdmj" class="easyui-validatebox form-control"   />
     				</td>
     			</tr>
     			
    			<tr>
     				
     				<td class="form-cell-1">
	            		<label class="form-label">教师人数</label>
     					<input name="jsrs" class="easyui-validatebox form-control"  validType="checkNumber" />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">学生人数</label>
     					<input name="xsrs" class="easyui-validatebox form-control"   validType="checkNumber" />
     				</td>
     				
     				<!-- <td class="form-cell-3" colspan="2">
	            		<label class="form-label">地址</label>
     					<input name="dz" class="easyui-validatebox form-control"  />
     				</td> -->
     				<td class="form-cell-1">
	            		<label class="form-label">党支部书记</label>
     					<input name="dzbsj" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">书记联系电话</label>
     					<input name="dzbsjdh" class="easyui-validatebox form-control"   />
     				</td>
     			</tr>
     			 
     			<tr>
     				
     				<td class="form-cell-1">
	            		<label class="form-label">党员人数</label>
     					<input name="dyrs" class="easyui-validatebox form-control"  validType="checkNumber"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">工会主席</label>
     					<input name="ghzx" class="easyui-validatebox form-control"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">工会主席联系电话</label>
     					<input name="ghzxdh" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">工会会员人数</label>
     					<input name="ghhyrs" class="easyui-validatebox form-control"  validType="checkNumber"   />
     				</td>
     			</tr>
     			<tr>
     				
     				<td class="form-cell-1">
	            		<label class="form-label">校长姓名</label>
     					<input name="xzxm" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">校长电话</label>
     					<input name="xzdh" class="easyui-validatebox form-control"  />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">安全负责人姓名</label>
     		 			<input name="aqfzrxm" class="easyui-validatebox form-control"  />
     		 		</td>
     		 		<td class="form-cell-1">
	            		<label class="form-label">安全负责人电话</label>
     		 			<input name="aqfzrdh" class="easyui-validatebox form-control"  />
     		 		</td>
     			</tr>
     		 	<tr>
     				<td class="form-cell-1">
	            		<label class="form-label">值班室电话</label>
     					<input name="zbsdh" class="easyui-validatebox form-control"   />
     				</td>
     				<td class="form-cell-1">
	            		<label class="form-label">电子监控安装情况</label>
     						<input name="dzjkaz" class="easyui-combobox form-control"
     									style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName',
	 								    	url:'${ctx}/dict/getdict/IsIntall'">
     				</td>
     				<td class="form-cell-1"></td>
     				<td class="form-cell-1"></td>
     			</tr>
     			
     			<tr>
     				<td class="form-cell-4"  colspan="4">
	            		<label class="form-label">防火设备</label>
     					<textarea  name="fhsb" class="easyui-validatebox form-control"   ></textarea>
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4"  colspan="4">
	            		<label class="form-label">防盗设施</label>
     					<textarea name="fdss" class="easyui-validatebox form-control"  ></textarea>
     				</td>
     			</tr>
     			<tr>
     				<td class="form-cell-4" colspan="4">
	            		<label class="form-label">文明单位情况</label>
     					<textarea name="wmdwqk" class="easyui-validatebox form-control" rows="5" ></textarea>
     				</td>
     			</tr>
     			<tr>
     				<td  class="form-cell-2" colspan="2">
     					<label class="form-label">地址</label>
     					<input  class="easyui-validatebox form-control" id="dz"  name="dz"  />
     				</td>
     				<td  class="form-cell-1">
     					<label class="form-label">经度</label>
     					<input class="easyui-validatebox form-control" data-options="disabled:true" id="lon" name="lon" />
     				</td>     				
     				<td class="form-cell-1">
     					<label class="form-label">纬度</label>
     					<input class="easyui-validatebox form-control" data-options="disabled:true" id="lat" name="lat"   />     				
     				</td>
	     				
     			</tr>
     			<tr >
	     		 	<td colspan="4">
	     		 		<label class="form-label">标记设施位置</label>
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
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5E7AE4DB55fe730d75561f1988429709">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
<script type="text/javascript" >
var addNewRecord = false; //新增记录？
var editRecord = false;
var thisUiConfig = {
	main:window.main,
	ctx : "${ctx}",
	url : "school",
	id : "#schoolId",    //表单中主键字段对应的控件的Id属性值
	idName:"schoolId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var schoolId = $(thisUiConfig.id).val();
		return schoolId;
	}
}

$(function (){
	
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	
});


var tabs = {
		thisTabIndex : 0,
		isEdit : true ,
		
		init : function (){
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
			$("#mainTab").tabs({
				onSelect : function(title, index) {
					tabs.thisTabIndex = index;
					if (index == 0) { // 选中列表标签
						setFormItemDisabled(thisUiConfig.baseForm, true, null);
						setFormItemDisabled(thisUiConfig.queryForm, false, null);
						$("#baseForm").form("clear");
						if ($("#listGrid").datagrid("getSelections").length > 0) {
							$("#mainTab").tabs("enableTab", 1);
							enableButtons([ "add", "del", "edit" ]);
						} else {
							$("#mainTab").tabs("disableTab", 1);
							enableButtons([ "add" ]);
						}
						tabs.isEdit = false;
						setFormItemDisabled("#baseForm",true,null);
					} else if (index == 1) { // 选中详细标签
						loadData();
						if (tableInfo.thisRow) {
							$(thisUiConfig.baseForm).form("load", tableInfo.thisRow);
							$("#meshId").lookup("setTxt", publicAttr.getOrgName(tableInfo.thisRow.meshId));
						}
						if(tabs.isEdit){
							setFormItemDisabled("#baseForm",false,null);
							myMap.init();
							enableButtons([ "add", "del", "save" ]);
						}else{
							myMap.init();
							enableButtons([ "add", "del","edit"]);
						}
					}
				}
			});
		}
		
}

var	Msg = {
		
	 show : function (title,msg){
		 
		 	title = title || "温馨提示" ;
		 	
		 	msg = msg || "操作成功" ;
		 
			$.messager.show({
				title : "温馨提示",
				msg : "操作成功",
				timeout : 2500,
				showType : "slide"
			});
	 }
		
}


//基础信息初始化
var baseInfo =  {
		
	saveUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",
	
	getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/del/",
	
	search : thisUiConfig.ctx + "/" + thisUiConfig.url + "/search ",
		
	init : function (){
		$(thisUiConfig.mainTab).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
			var id ="#" + $(this).attr("id");
			setFormItemDisabled(id,true,null);
		});
		setFormItemDisabled(thisUiConfig.queryForm, false, null);
		//setFormItemDisabled("#baseForm",true,null);
		
	},
	
	save : function (){
		
		if($("#baseForm").form("validate")){
			var data = getFormData("#baseForm");
			if(data){
				publicFormControl.ajax({
					type : "POST",
					url : baseInfo.saveUrl,
					data : data
				},function (result){
					var row = result.row ;
					$("#baseForm").form("load",row);
					$("#ssjg").lookup('setTxt',publicAttr.getOrgName(row.ssjg));
					tableInfo.query();
					enableButtons([ "add", "save" ]);
					
				},"show");
			}
		}
		
	},
	
	add : function (){
		
		tabs.isEdit = false ;
		//clear data
		$("#listGrid").datagrid("unselectAll");
		$("#mainTab").tabs("enableTab", 1);
		$("#mainTab").tabs("select", 1);
		setFormItemDisabled("#baseForm",false,"#lon,#lat");
		$("#baseForm").form("enableValidation");
		enableButtons([ "add", "save" ]);
		$("#baseForm").form("clear");
		defaultMesh();         //默认网格
	},
	
	del:function (){
		
		var row = $("#listGrid").datagrid('getSelected');
		
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		
		$.messager.confirm("温馨提示", "确定删除学校【"+row.xxmc+"】记录?",
			function(r) {
				if (r) {
					publicFormControl.ajax({
						url : baseInfo.delUrl + row[thisUiConfig.idName],
						type: "POST",
					},function (data){
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$("#mainTab").tabs("select", 0);
							$("#mainTab").tabs("disableTab", 1);
							tableInfo.query();
						} 
					},"show");
				}
			});
		
	},
	
	edit:function (){
		tabs.isEdit = true ;
		var tab = $('#mainTab').tabs('getSelected');
		var  index = $('#mainTab').tabs('getTabIndex',tab);
		if(index!=1){
			$("#mainTab").tabs("enableTab", 1);
			$("#mainTab").tabs("select", 1);
		}else{
			enableButtons([ "add", "del" , "save" ]);
		}
		setFormItemDisabled("#baseForm",false,"#lon,#lat");
	}
};

function  loadData(){
	var row = $(thisUiConfig.tableList).datagrid("getSelected");
	if(row){
		var id = row[thisUiConfig.idName];
		if(id){
			publicFormControl.ajax({
				type : "POST",
				url : baseInfo.getUrl + id,
			},function (result){
				var row =  result.row;
				$("#baseForm").form("load",row);
				$("#ssjg").lookup('setTxt',publicAttr.getOrgName(row.ssjg));
			});
		}
	}
}
 

//列表加载
var tableInfo =  {
	
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
			queryParams:  tableInfo.getQueryFormData("#queryForm"),
			url : thisUiConfig.ctx +"/" + thisUiConfig.url + "/search",
			columns : [ [ {
				field : "schoolId",
				hidden:true,
			},{
				field : "ssjg",
				title : "所属机构",
				width : 150,
				halign:'center',
				align:'left',
				formatter : function (value,row,index){
						
					return publicAttr.getOrgName(value);
				}
			},{
				field : "xxmc",
				title : "学校名称",
				width : 150,
				halign:'center',
				align:'left'
			},{
				field:"xxzdmj",
				title:"占地面积",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"xzxm",
				title:"校长姓名",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"xzdh",
				title:"校长电话",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"aqfzrxm",
				title:"安全负责人",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"aqfzrdh",
				title:"安全负责人电话",
				width:100,
				halign:'center',
				align:'left'
			},{
				field:"dz",
				title:"学校地址",
				width:250,
				halign:'center',
				align:'left'
			}]],
			
			onSelect : function(rowIndex, rowData) {
				tabs.isEdit = false;
				var len = $("#listGrid").datagrid("getSelections").length;
				if(len==1){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit" ]);
				}else if(len==0){
					$("#mainTab").tabs("disableTab", 1);
					enableButtons([ "add"]);
				}else{
					enableButtons([ "add", "del"  ]);
					$("#mainTab").tabs("disableTab", 1);
				}
			},
			onUnselect : function(rowIndex, rowData) {
				var len = $("#listGrid").datagrid("getSelections").length;
				 
				if(len==1){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit" ]);
				}else if(len==0){
					enableButtons([ "add"]);
					$("#mainTab").tabs("disableTab", 1);
				}else{
					enableButtons([ "add", "del"  ]);
					$("#mainTab").tabs("disableTab", 1);
				}
			},
			onLoadSuccess : function(data) {
				$("#listGrid").datagrid("unselectAll");
			}
			
		});
		
		// 设置分页显示形式
		$("#listGrid").datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	
	query : function (){
		var param = tableInfo.getQueryFormData("#queryForm");
		$("#listGrid").datagrid("load", param);
		enableButtons([ "add" ]);
		$("#mainTab").tabs("disableTab", 1);
	},
	getQueryFormData : function (formId){
		var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
		var data =  getFormData(formId) || {};
		if(meshIds){
			data['q_ssjg_IN'] = meshIds;
		}
		return data;
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
				point = new BMap.Point(116.404, 39.915);  // 创建点坐标  
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
			     	$("#dz").val(address);
			    
			    	 //画图
					 var label = new BMap.Label(address, {offset:new BMap.Size(20,-10)});
				     marker.setLabel(label);
				 });
				 map.addOverlay(marker);    
				 marker.enableDragging();    
			});  

			map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
			map.panBy(0, 0);
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
/**
 * 机构弹出窗
 */
function orgLookup() {
	
	orgLookup = $('#ssjg').lookup({
		title : "选择所属网格",
		url : thisUiConfig.ctx + "/mesh/selectmesh",
		width : 350,
		height : 500,
		required : true,
		valueField: 'meshId',
		textField: 'meshName'
	});
	
}

/**
 *  默认网格
 */
function defaultMesh(){
	var meshId = main.currentUserMesh.meshId;
	$("#ssjg").lookup("setTxt", publicAttr.getOrgName(meshId));
	$("#ssjg").lookup('setVal', meshId);
}
 

</script>

</body>
</html>