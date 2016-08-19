<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${system_name}-${company_name}</title>
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

<!-- <div class="g-toolbar"> -->
<!-- 	<a onclick="baseInfo.add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a> -->
<!-- 	<a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a> -->
<!-- 	<a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a> -->
<!-- 	<a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a> -->
<!-- </div> -->

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query" >
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
						<td class="form-cell-1">
            				<label class="form-label">所属网格</label>
            				<input id="unitsId" name="q_unitsId_EQ"  />
            			</td>
<!--             			<td class="form-cell-1"></td> -->
            			<td class="form-cell-1">
		            		<label class="form-label">日期</label>
							<input id="timeGte" name="timeGte" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
						</td>
						<td class="form-cell-1">
<!-- 				            		<label class="form-label">~</label> -->
		            		~<input id="timeLte" name="timeLte" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px">
		            	</td>
						<td class="form-cell-1 f-button">
							<a onclick="query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
							<a onclick="clearQueryForm('#queryForm');query();" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="list-area" style="top:50px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
		
	</div>

</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>

<script type="text/javascript">
/**
 * 脚本入口
 */
$(function(){
	orgLookup();
	datagridInit(); // 初始化数据表格
	console.log(main);
	//publicAttr.initCombobox();
});

//初始化数据表格
function datagridInit() {
	$("#listGrid").datagrid({
		rownumbers : true,
		singleSelect : true,
		autoRowHeight : false,
		border : false,
		pageSize : defaultPageSize,
		pageList : defaultPageList,
		pagination : true,
// 		queryParams : getFormData("#queryForm"),
		queryParams : getQueryFormData("#queryForm"),
		url : "${ctx}/reportStatistics/diarySearch",
		columns : [[{
			field : "unitsId",
			title : "所属机构",
			width : 150,
			align:'left',
			halign:'center',
			formatter : function (value,row,index){
				return publicAttr.getOrgName(value) || value;
			}
		},{
			field : "writer",
			title : "编写人员",
			align:'left',
			halign:'center',
			width : 120,
			hidden : true
		},{
			field : "realName",
			title : "用户名称",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "LEVELA",
			title : "优",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "LEVELB",
			title : "良",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "LEVELC",
			title : "中",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "LEVELD",
			title : "差",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "allscore",
			title : "合计",
			align:'left',
			halign:'center',
			width : 120
		}
		]],
// 		onLoadSuccess : function(data) {
// 			console.log(data);
// 			var sum_writer = 0;
// 			var sum_realName = 0;
// 			var sum_LEVELA = 0;
// 			var sum_LEVELB = 0;
// 			var sum_LEVELC = 0;
// 			var sum_LEVELD = 0;
// 			var sum_allscore = 0;
			
// 			var rows = data.rows;
// 			if(rows && rows.length){
// 				$.each(rows,function (i,n){
// 					sum_writer += (i == null ? 0 : 1);
// 					sum_realName += (i == null ? 0 : 1);
// 					sum_LEVELA += n.LEVELA;
// 					sum_LEVELB += n.LEVELB;
// 					sum_LEVELC += n.LEVELC;
// 					sum_LEVELD += n.LEVELD;
// 					sum_allscore += n.allscore;
// 				});
// 			}
// 			$('#listGrid').datagrid('appendRow',{
// 				unitsId : '总计：',
// 				writer : sum_writer,
// 				realName : sum_realName,
// 				LEVELA:sum_LEVELA,
// 				LEVELB:sum_LEVELB,
// 				LEVELC:sum_LEVELC,
// 				LEVELD:sum_LEVELD,
// 				allscore:sum_allscore
// 			});
			
// 		}
	});
	//设置分页形式
	$("#listGrid").datagrid("getPager").pagination({
		layout : defaultPageLayout
	});
}

function query(){
	formatterIntegalDate();
	var param = getQueryFormData("#queryForm");
	console.log(param);
	$("#listGrid").datagrid("load", param);
	enableButtons([ "add" ]);
// 	var param = getFormData("#queryForm");
// 	$("#listGrid").datagrid("load", param);
}

//日期查询
function formatterIntegalDate() {
	var timeGte = $("#timeGte").datebox('getValue');
	var timeLte = $("#timeLte").datebox('getValue');
	if(timeGte == "") {
		if(timeLte == "") {
			integalDate =  formatterDate("");
		} else {
			integalDate =  formatterDate(timeLte);
		}
	} else {
		if(timeLte == "") {
			integalDate = formatterDate(timeGte) + formatterDate("");
		} else {
			integalDate = formatterDate(timeGte) + formatterDate(timeLte);
		}
	}
}

//日期格式转换
function formatterDate(time) {
	var date = time;
	var y = "";
	var m  = "";
	var d = "";
	if(time == "") {
		date = new Date();
		y = date.getFullYear();  
	    m = date.getMonth()+1;  
	    d = date.getDate(); 
	} else {
		var ss = (date.split('-'));
        y = parseInt(ss[0],10);
        m = parseInt(ss[1],10);
        d = parseInt(ss[2],10);
	}
	
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}

//选择所属网格
function orgLookup() {
	orgLookup = $('#unitsId').lookup({
		title: '选择所属网格',
		url: '${ctx}/mesh/selectmesh',
		width: 350,
		height: 500,
// 		required: true,
		valueField: 'meshId',
		textField: 'meshName',
			contentWindow: {
				params: {
					singleSelect : false,
					checkbox : true
				},
				actions: {
					confirm : function(data) {
						if(data){
							$("input[name='q_unitsId_EQ']").val(data.meshName);
							$("input[name='q_unitsId_EQ']").val(data.meshId);
							$("input[name='q_unitsId_EQ']").prev().val(data.meshName);
						}
						main.closeDialog();
					},
					cancel: function() {
						main.closeDialog();
					}
				}
			}
	});
}

function getQueryFormData(formId){
	console.log(window.main);
	var meshIds = window.main.currentUserMesh.meshChildrenIds || "" ;
	var data =  getFormData(formId) || {};
	if(meshIds){
		data['q_unitsId_IN'] = meshIds;
	}
	console.log(data);
	return data;
}

</script>

</body>
</html>