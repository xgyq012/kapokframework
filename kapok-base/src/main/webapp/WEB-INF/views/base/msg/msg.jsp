<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${system_name}-${company_name}</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/sis.css" />
</head>

<body>
<div class="content">
	<!-- 按钮区域 -->
	<div id="toolbar" style="padding: 5px;">
		<a id="add" href="javascript:void(0);" class="easyui-linkbutton toolbar" onclick="add();" data-options="plain:true,iconCls:'icon-add'">写信</a>
	</div>

	<!-- 实体区域 -->
	<div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false">

		<!-- 实体列表 -->
		<div id="sendListTab" title="发信箱" class="tabCls">
			<!-- 查询区域 -->
			<div id="sendQuery">
				<form id="sendQueryForm" class="formCls">
					<table>
						<tr>
							<td>信息标题</td>
							<td>
								<input id="q_msgTitle_LIKE" name="q_msgTitle_LIKE" type="text"/>
							</td>
							<td>信息内容</td>
							<td>
								<input id="q_msgBody_LIKE" name="q_msgBody_LIKE" type="text"/>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="sendQuery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="clearQueryForm('#sendQueryForm');" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<!-- 列表区域 -->
			<div id="sendList">
				<table id="sendListGrid" style="height: 432px;"></table>
			</div>
		</div>

		<!-- 实体详细 -->
		<div id="receiveListTab" title="收信箱" class="tabCls">
			<!-- 查询区域 -->
			<div id="receiveQuery">
				<form id="receiveQueryForm" class="formCls">
					<table>
						<tr>
							<td>信息标题</td>
							<td>
								<input id="q_msg.msgTitle_LIKE" name="q_msg.msgTitle_LIKE" type="text"/>
							</td>
							<td>发信人</td>
							<td>
								<input id="q_msg.sender_LIKE" name="q_msg.sender_LIKE" type="text"/>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="receiveQuery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="clearQueryForm('#receiveQueryForm');" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<!-- 列表区域 -->
			<div id="receiveList">
				<table id="receiveListGrid" style="height: 432px;"></table>
			</div>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog"
			data-options="closed:true,buttons:'#dlg-buttons'"
			style="width:460px;height:300px;padding:5px 10px">
        <form id="fm" method="post">
			<table>
				<tr>
					<td>信息标题：</td>
					<td>
						<input id="msgTitle" name="msgTitle" class="easyui-validatebox" type="text"
							style="width: 350px;" data-options="required: true"/>
					</td>
					
				</tr>
				<tr>
					<td>收信人：</td>
					<td>
						<input id="reciever" name="reciever"  class="easyui-validatebox" type="text"
							style="width: 350px;" data-options="required: true"/>
					</td>
				</tr>
				<tr>
					<td>内容：</td>
					<td>
						<textarea id="msgBody" name="msgBody" class="easyui-validatebox"
							style="width: 350px; height: 160px;" data-options="required: true">
						</textarea>
					</td>
				</tr>
			</table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="sendMsg();">发送</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close');">取消</a>
    </div>
	
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript">

	$(function() {
		
		$("#mainTabs").tabs({
			onSelect : function(title, index) {
				if (index == 0) {
				}
				if (index == 1) {
				}
			}
		});
		
		$("#sendListGrid").datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			url : "${ctx}/msg/getSendbyMsgList",
 			columns : [[
				{field : "msgTitle", title : "信息标题", width : 100},
				{field : "msgBody", title : "信息内容", width : 300},
				{field : "reciever", title : "收信人", width : 150}
 			]],
			onSelect : function(rowIndex, rowData) {
			},
			onUnselectAll : function(rowIndex, rowData) {
			},
			onDblClickRow : function(rowIndex, rowData) {
			}
		});

		$("#receiveListGrid").datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			url : "${ctx}/msg/getRecieverByMsgList",
 			columns : [[
				{field : "msgTitle", title : "信息标题", width : 100},
				{field : "msgBody", title : "信息内容", width : 300},
				{field : "sender", title : "发信人", width : 150}
 			]],
			onSelect : function(rowIndex, rowData) {
			},
			onUnselectAll : function(rowIndex, rowData) {
			},
			onDblClickRow : function(rowIndex, rowData) {
			}
		});		
		
		$("#sendListGrid").datagrid("getPager").pagination({
			layout : defaultPageLayout
		});	

		$("#receiveListGrid").datagrid("getPager").pagination({
			layout : defaultPageLayout
		});			
		
		var bodyOuterHeight = $("body").outerHeight(true);
		var toolbarOuterHeight = $("#toolbar").outerHeight(true);
		$("#mainTabs").height(bodyOuterHeight - toolbarOuterHeight);

		var mainTabsOuterHeight = $("#mainTabs").outerHeight(true);
		var tabsHeaderOuterHeight = $("#mainTabs").find(".tabs-header").outerHeight(true);
		$("#mainTabs").find(".tabs-panels").height(mainTabsOuterHeight - tabsHeaderOuterHeight);
		$("#sendListTab").height(mainTabsOuterHeight - tabsHeaderOuterHeight);
		$("#receiveListTab").height(mainTabsOuterHeight - tabsHeaderOuterHeight);
		
	});

	// 启用按钮
	function enableButtons(buttons) {
		$(".easyui-linkbutton.toolbar").linkbutton("disable");
		for (var i = 0; i < buttons.length; i++) {
			$("#"+buttons[i]).linkbutton("enable");
		}
	}
	
	// 写信
	function add() {
		$("#dlg").dialog("open").dialog("setTitle", "写信");
        $('#fm').form('clear');
	}
	
	// 发送
	function sendMsg() {
		if ($("#fm").form("validate")) {
	 		$.messager.progress();
	 		var data = {};
	 		data["sendBy"] = <shiro:principal property="userId"/>;
	 		data["msgTitle"] = $("#msgTitle").val();
	 		data["msgBody"] = $("#msgBody").val();
	 		var reciever = $("#reciever").val();
	 		var arr = reciever.split(",");
	 		for (var i = 0; i < arr.length; i++) {
 				data["recievers[" + i + "].receiver"] = arr[i];
	 		}
			$.ajax({
				type: "POST",
				url: "${ctx}/msg/send",
				data: data
			}).done(function(res) {
				$.messager.progress("close");
				if (res.resultCode == "0") {
					$("#dlg").dialog("close");
					sendQuery();
					$.messager.show({
						title: "温馨提示",
						msg: "发送成功",
						timeout: 2500,
						showType: "slide"
					});
				} else {
					$.messager.alert("温馨提示", res.resultMsg, "error");
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				$.messager.alert("温馨提示", "发送出错！", "error");
			});
 		}
	}	

	function sendQuery() {
 		$("#sendListGrid").datagrid("load", getFormData("#sendQueryForm"));
	}
	
	function receiveQuery() {
 		$("#receiveListGrid").datagrid("load", getFormData("#receiveQueryForm"));
	}
	
</script>
</body>
</html>