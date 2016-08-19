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
<!-- 						<td class="form-cell-1"> -->
<!--             				<label class="form-label">所属网格</label> -->
<!--             				<input  id="unitsId" name="q_unitsId_EQ"  /> -->
<!--             			</td> -->
            			<td class="form-cell-1">
            				<label class="form-label">用户名称</label>
            				<input id="realName" name="q_realName_EQ" class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
            			</td>
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
							<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
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
// 	orgLookup();
	datagridInit(); // 初始化数据表格
// 	alert(main.currentUserMesh.meshChildrenIds);
// 	meshAjax();
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
		queryParams : getFormData("#queryForm"),
		url : "${ctx}/reportStatistics/scoreSearch" ,
		columns : [[{
			field : "userId",
			title : "用户ID",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "meshId",
			title : "网格ID",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "realName",
			title : "用户名称",
			width : 150,
			align:'left',
			halign:'center'
		},{
			field : "meshName",
			title : "网格名称",
			width : 150,
			align:'left',
			halign:'center'
		},{
			field : "meshFullName",
			title : "网格全称",
			width : 150,
			align:'left',
			halign:'center'
		},{
			field : "score",
			title : "分值",
			align:'left',
			halign:'center',
			width : 120
		}
		]],
		onLoadSuccess : function(data) {
			var sum_realName = 0;
			var sum_otel = 0;
			var sum_htel = 0;
			var sum_score = 0;
			var sum_meshName = 0;
			var sum_meshFullName = 0;
			
			var rows = data.rows;
			if(rows && rows.length){
				$.each(rows,function (i,n){
					sum_realName += (n.realName == null ? 0 : 1);
					sum_otel += (n.otel == null ? 0 : 1);
					sum_htel += (n.htel == null ? 0 : 1);
					sum_score += (n.score == null ? 0 : n.score);
					sum_meshName += (n.meshName == null ? 0 : 1);
					sum_meshFullName += (n.meshFullName == null ? 0 : 1);
				});
			}
			$('#listGrid').datagrid('appendRow',{
				realName: '总计：',
// 				realName:sum_realName,
				meshName:sum_meshName,
				score:sum_score,
				otel:sum_otel,
				htel:sum_htel,
				meshFullName:sum_meshFullName
			});
		}
	});
	//设置分页形式
	$("#listGrid").datagrid("getPager").pagination({
		layout : defaultPageLayout
	});
}

function query(){
	formatterIntegalDate();
	var param = getFormData("#queryForm");
	$("#listGrid").datagrid("load", param);
	
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

</script>

</body>
</html>