<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>积分明细记录</title>
	
    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/page.css">
	<style type="text/css">
	input{
 		width:130px;
/* 		height:18px; */
/* 		line-height: 18px; */
	} 
	
	div.otherinfo{
		display:none;
	}
	
	
	table.infoTable tr td{
		text-align: right;
		padding:3px;
	}
	
	</style>


</head>
<body>
<div class="g-layout">
	<!-- 按钮区域 -->
	<div class="g-toolbar">
<!--       <a id="add" onclick="baseInfo.add();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a> -->
<!--       <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>编辑</a> -->
<!--       <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>保存</a> -->
<!--       <a id="del" onclick="baseInfo.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>删除</a> -->
	</div>
	
	<!-- 内容区域 -->
	<div id="mainLayout" class="easyui-layout g-container" data-options="fit:true,border:false">
<!-- 		<div title="列表" style="position:relative;"> -->
			<div class="query-area">
				<form id="queryForm" method="post">
					<table class="g-form" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="form-cell-1">
				            		<label class="form-label">用户名称</label>
				            		<input name="q_a.REAL_NAME_LIKE" class="easyui-validatebox form-control">
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
						</tbody>
					</table>
				</form>
			</div>
			
			<div class="list-area" style="top:50px;">
				<table id="listGrid" style="height:100%"></table>
			</div>
<!-- 		</div> -->
		
	</div>
</div>
</body>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript">

$(function(){
	
	datagridInit(); // 初始化数据表格
});

//初始化数据表格
function datagridInit() {
	$("#listGrid").datagrid({
		rownumbers : true,
		singleSelect : true,
		autoRowHeight : false,
		border : false,
		fitColumns : true,
		pageSize : defaultPageSize,
		pageList : defaultPageList,
		pagination : true,
		queryParams : getFormData("#queryForm"),
		url : "${ctx}/integalDetail/search",
		columns : [[{
			field : "detailId",
			title : "主键",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "userId",
			title : "用户ID",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "userName",
			title : "用户账号",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "realName",
			title : "用户名称",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "detailType",
			title : "积分类型",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, index){
				if(value == 'diaryScoreA'){
					return '日志积分（优）';
				}else if(value == 'diaryScoreB'){
					return '日志积分（良）';
			    }else if(value == 'diaryScoreC'){
					return '日志积分（中）';
			    }else if(value == 'diaryScoreD'){
					return '日志积分（差）';
			    }else if(value == 'upScore'){
					return '上报积分';
				}else if(value == 'shuntScore'){
					return '分流积分';
				}else if(value == 'endScore'){
					return '办结积分';
				}else if(value == 'diaryScore'){
					return '日志积分';
				}else if(value == 'loginScore'){
					return '登录积分';
				}
			}
		},{
			field : "score",
			title : "分值",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "remark",
			title : "描述",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "detailTime",
			title : "计分时间",
			align:'left',
			halign:'center',
			width : 120
		}
		]],
	});
// 	//设置分页形式
// 	$("#listGrid").datagrid("getPager").pagination({
// 		layout : defaultPageLayout
// 	});
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


</script>
</html>