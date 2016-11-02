<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${system_name}-${company_name}</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-nateiot-ui/css/component.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/sis.css" />
</head>

<body>
<div class="easyui-panel" data-options="fit:true,border:false">
	<!-- 按钮区域 -->
	<div id="toolbar" style="padding: 5px;">
		<a id="excelExport" href="javascript:void(0);" class="easyui-splitbutton" onclick="excelExport('N');" data-options="menu:'#mm2',plain:true,iconCls:'icon-export'">Excel导出</a>
		<a id="del" href="javascript:void(0);" class="easyui-linkbutton" onclick="del();" data-options="plain:true,iconCls:'icon-remove'">清除</a>
	</div>

	<!-- 实体区域 -->
	<div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false">

		<!-- 实体列表 -->
		<div id="listTab" title="列表" class="tabCls">

			<!-- 查询区域 -->
			<div id="query">
				<form id="queryForm" class="formCls">
					<table>
						<tr>
							<td>操作人</td>
							<td>
								<input id="q_operator.realname_LIKE" name="q_operator.realname_LIKE" type="text"/>
							</td>
							<td>操作类型</td>
							<td>
								<input id="q_operateType_LIKE" name="q_operateType_LIKE" type="text"/>
							</td>
							<td>操作说明</td>
							<td>
								<input id="q_logDesc_LIKE" name="q_logDesc_LIKE" type="text"/>
							</td>
						</tr>
						<tr>
							<td>操作时间起</td>
							<td>
								<input id="q_operateTime_GTE_Date" name="q_operateTime_GTE_Date" class="easyui-datebox" type="text"/>
							</td>	
							<td>止</td>
							<td>
								<input id="q_operateTime_LTE_Date" name="q_operateTime_LTE_Date" class="easyui-datebox" type="text"/>
							</td>
							<td colspan="2" style="text-align: right;">
								<a href="javascript:void(0);" onclick="query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
								<a href="javascript:void(0);" onclick="clearQueryForm('#queryForm');query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<!-- 列表区域 -->
			<div id="list">
				<table id="listGrid" style="height: 405px;"></table>
			</div>
		</div>

	</div>
	
	<div id="mm1" style="width:100px;">
        <div data-options="iconCls:'icon-download'" onclick="">下载模板</div>
    </div>
	<div id="mm2" style="width:100px;">
        <div data-options="iconCls:'icon-export'" onclick="excelExport('Y');">导出全部</div>
    </div>
	
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-nateiot-ui/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript">

	$(function() {
		
		$("#del").tooltip({
		    position : "bottom",
		    content : "清除六个月前的日志",
		    onShow : function(){
		        $(this).tooltip("tip").css({
		            borderColor : "#f00"
		        });
		    }
		});
		
 		$("#listGrid").datagrid({
 			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			url : "${ctx}/operatelog/search",
 			columns : [[
				{field : "realname", title : "操作人"},
				{field : "operateType", title : "操作类型"},
				{field : "logDesc", title : "操作说明"},
				{field : "operateTime", title : "操作时间"}
 			]]
 		});

		$("#listGrid").datagrid("getPager").pagination({
			layout : defaultPageLayout
		}); 		
		
	});

	// Excel导出
	function excelExport(isAll) {
		var pageOptions = $("#listGrid").datagrid("getPager").pagination("options");
		$("#queryForm").form("submit", {
			url : "${ctx}/operatelog/excelExport",
			onSubmit: function(param) {
		        param.page = pageOptions.pageNumber;
		        param.rows = pageOptions.pageSize;
		        param.isAll = isAll;
		        return true;
		    }
		});
	}
	
	// 清除六个月前的日志
	function del() {
		$.messager.confirm(
			"温馨提示",
			"确定清除六个月前的日志吗？",
			function(r) {
				if (r) {
					$.messager.progress();
					$.ajax({
						url : "${ctx}/operatelog/clear"
					}).done(function(res){
						$.messager.progress("close");
						if (res.resultCode == "0") {
							$("#listGrid").datagrid("unselectAll");
							query(); // 刷新列表页
							$.messager.show({
								title: "温馨提示",
								msg: res.resultMsg,
								timeout: 2500,
								showType: "slide"
							});
						} else {
							$.messager.alert("温馨提示", res.resultMsg, "error");
						}
					}).fail(function(jqXHR, textStatus, errorThrown) {
						$.messager.progress("close");
						$.messager.alert("温馨提示", "删除出错！", "error");
					});
				}
			}
		);
	}
	
	function query() {
 		$("#listGrid").datagrid("load", getFormData("#queryForm"));
	}
</script>
</body>
</html>