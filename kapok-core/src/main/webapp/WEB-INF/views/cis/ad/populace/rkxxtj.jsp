<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>人口信息统计</title>
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
	            				<input  id="meshId" name="q_h.ORG_ID_IN"  />
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
			queryParams: tableInfo.getQueryFormData("#queryForm"),
			url : thisUiConfig.ctx +"/basicData/rkxxtj",
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
				field : "rkzs",
				title : "人口总数",
				width : 100,
				align:'right',
				halign:'center'
			},{
				field:"czrk",
				title:"常住人口",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"ldrk",
				title:"流动人口",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"kgrk",
				title:"空挂人口",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"ssmz",
				title:"少数民族",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"dy",
				title:"党员",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"lnr",
				title:"老年人",
				width:100,
				align:'right',
				halign:'center'
			},{
				field:"lset",
				title:"留守儿童",
				width:100,
				align:'right',
				halign:'center'
			}]],
			
			onLoadSuccess : function(data) {
				/* var sum_rkzs = 0;
				var sum_czrk = 0;
				var sum_ldrk = 0;
				var sum_ssmz = 0;
				var sum_kgrk = 0;
				var sum_dy = 0;
				var sum_lnr = 0;
				var sum_lset = 0;
				
				var rows = data.rows;
				if(rows && rows.length){
					$.each(rows,function (i,n){
						sum_rkzs = sum_rkzs + n.rkzs;
						sum_czrk = sum_czrk + n.czrk;
						sum_ldrk = sum_ldrk + n.ldrk;
						sum_ssmz = sum_ssmz + n.ssmz;
						sum_kgrk = sum_kgrk + n.kgrk;
						sum_dy = sum_dy + n.dy;
						sum_lnr = sum_lnr + n.lnr;
						sum_lset = sum_lset + n.lset;
					});
				}
				$('#listGrid').datagrid('appendRow',{
					orgId: '总计：',
					rkzs:sum_rkzs,
					czrk:sum_czrk,
					ldrk:sum_ldrk,
					kgrk:sum_kgrk,
					ssmz:sum_ssmz,
					dy:sum_dy,
					lnr:sum_lnr,
					lset:sum_lset
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
			var q_mesh = data['q_h.ORG_ID_IN'];
			data['q_h.ORG_ID_IN'] = q_mesh || meshIds;
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