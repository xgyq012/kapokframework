<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>综合治理统计</title>
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


	 <div class="easyui-layout" style="width:100%;height:100%;position:relative;"  >
	 
	 		<div id="query">
				<form id="queryForm" class="formCls" method="post">
					<table  class="g-form" cellpadding="0" cellspacing="0">
						<tr>	
							<td class="form-cell-1">
	            				<label class="form-label">机构网格</label>
	            				<input  id="meshId" name="q_t.ORG_IN"  />
	            			</td>
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
			<table id="listGrid"  style="height:100%" ></table>
		</div>
	
	 </div>
</div>
 

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" >

var thisUiConfig = {
	main : window.parent.main,
	ctx : "${ctx}"
}

$(function (){
	
	orgLookup();
	tableInfo.init();
	
});

 
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
			url : thisUiConfig.ctx +"/basicData/zhzlSearch",
			columns : [[{
				field : "orgId",
				title : "所属机构",
				width : 150,
				align:'left',
				halign:'center',
				formatter : function (value,row,index){
						
					var path = row.fullPath;
					var str = [];
					str = path.split(".");
					var strVal = str.length-1;
					var v = (strVal == 1 ? 0 : (strVal-1) * 10); 
					return "<span style='margin-left: "+ v +"px;'>"+(publicAttr.getOrgName(value) || value)+"</span>";
				}
			},{
				field : "judicial",
				title : "司法",
				width : 100,
				align:'right',
				halign:'center'
			},{
				field : "petition",
				title : "信访",
				width : 100,
				align:'right',
				halign:'center'
			},{
				field:"details",
				title:"案发情况",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"patrol",
				title:"巡逻队",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"technical",
				title:"技防",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"duty",
				title:"值班室",
				width:100,
				align:'right',
				halign:'center'
			},{
				field : "fullPath",
				title : "路径",
				width : 100,
				align:'right',
				halign:'center',
				hidden: true
			}]],
			
			onSelect : function(rowIndex, rowData) {
				 
			},
			onUnselect : function(rowIndex, rowData) {
				 
			},
			onLoadSuccess : function(data) {
				
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
	},
	getQueryFormData : function (formId){
		var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
		var data =  getFormData(formId) || {};
		if(meshIds){
			var q_mesh = data['q_t.ORG_IN'];
			data['q_t.ORG_IN'] = q_mesh || meshIds;
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
		textField: 'meshName',
		contentWindow: {
			params: {
				singleSelect : true,
				checkbox : false,
				queryParams : function (){
					return {
						orgId:getOrgid()
					};
				}
			},
			actions: {
				confirm: function(data) {
					if (data) {
						var meshChildrenIds = data.meshChildrenIds;
						var meshName = data.meshName;
						$('#meshId').lookup('setTxt',meshName);
						$('#meshId').lookup('setVal',meshChildrenIds);
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