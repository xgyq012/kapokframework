<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>房屋信息统计</title>
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
			url : thisUiConfig.ctx +"/basicData/fwgltj",
			columns : [[{
				field : "orgId",
				title : "所属机构",
				width : 150,
				align:'left',
				halign:'center',
				formatter : function (value,row,index){
						
					return publicAttr.getOrgName(value) || value;
				}
			},{
				field : "community",
				title : "小区总数",
				width : 100,
				align:'right',
				halign:'center'
			},{
				field:"building",
				title:"楼栋总数",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"house",
				title:"房屋总数",
				width:100,
				align:'right',
				halign:'center'
			}]],
			
			onSelect : function(rowIndex, rowData) {
				 
			},
			onUnselect : function(rowIndex, rowData) {
				 
			},
			onLoadSuccess : function(data) {
				/* var sum_community = 0;
				var sum_building = 0;
				var sum_house = 0;
				var rows = data.rows;
				if(rows){
					$.each(rows,function (i,n){
						sum_community = sum_community + parseInt(n.community);
						sum_building = sum_building + parseInt(n.building);
						sum_house = sum_house + parseInt(n.house);
					});
				}
				$('#listGrid').datagrid('appendRow',{
					orgId: '总计：',
					community: sum_community,
					building: sum_building,
					house: sum_house
				}); */
				
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