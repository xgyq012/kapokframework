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

<style type="text/css">
	#hidden-layout{width:100%;height:100%;overflow:hidden;display:none;}
	.ke-container-default{margin-left:85px !important}
</style>

</head>
<body>

<div class="g-toolbar">
	<a onclick="add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a onclick="save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	<a onclick="send();" id="send" class="easyui-linkbutton toolbar g-button"><i class="fa fa-send"></i>发送邮件</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query" >
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
						<td class="form-cell-1">
	            				<label class="form-label">编号</label>
	            				<input class="easyui-validatebox form-control" name="q_regimeCode_LIKE"/>
	            		</td>
	            		<td class="form-cell-1">
	            				<label class="form-label">标题</label>
	            				<input class="easyui-validatebox form-control" name="q_regimeTitle_LIKE"/>
	            		</td>
	            		<td class="form-cell-1 f-button">
									<a onclick="query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
									<a onclick="clearQueryForm('#queryForm');query();" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
						</td>
	            		<td class="form-cell-1">
<!-- 		            			<label class="form-label">类型</label> -->
<!-- 		            			<input id="q_knowledgeType_EQ" name="q_knowledgeType_EQ"  -->
<!-- 		            					dictCode="knowledgeType" -->
<!-- 		            					class="easyui-combobox  form-control" -->
<%-- 		            					style="width:${comboboxWidth};height:${comboboxHeight}px;" --%>
<!-- 										data-options=" -->
<!-- 	 								    	editable:false, -->
<!-- 	 								    	panelHeight:'auto', -->
<!-- 	 								    	valueField:'dictCode', -->
<!-- 	 								    	textField:'dictName'" /> -->
 							</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="list-area" style="top:50px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
		
	</div>

	<div id="minute" data-options="disabled:true"  title="详细"   >
		<form id="dataForm"  action="">
				<!-- 隐藏属性 -->
	    		<input id="regimeId" name="regimeId" type="hidden" />
    			<input id="docId" name="docId" type="hidden" />
    			<input id="pageType" value="${pageType}" type="hidden"/>
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				
			<fieldset>
				<legend>基础信息</legend>
				<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td class="form-cell-1">
			            		<label class="form-label">编码</label>
		     					<input id="regimeCode" name="regimeCode" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td colspan="2" class="form-cell-2">
			            		<label class="form-label">标题</label>
		     					<input id="regimeTitle" name="regimeTitle" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px" required="required"/>
	          				</td>
	          				<td class="form-cell-1">
		            			<label class="form-label">类型</label>
		            			<input id="regimeType" name="regimeType" 
		            					dictCode="regimeType" 
		            					class="easyui-combobox form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
 	 								    	editable:false, 
 	 								    	panelHeight:'auto', 
 	 								    	valueField:'dictCode', 
 	 								    	textField:'dictName'" /> 
 							</td>
						</tr>
						<tr>
							<td class="form-cell-1">
		            			<label class="form-label">是否有效</label>
		            			<input name="isEnable" 
		            					dictCode="enable" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
							<td colspan="2" class="form-cell-2">
			            		<label class="form-label">上传文件名</label>
		     					<input id="docName" name="docName" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td class="form-cell-1 f-button">
								<a onclick="upload();" id="upload" class="easyui-linkbutton toolbar g-button"><i class="fa fa-upload"></i>文件上传</a>
								<input type="hidden" id="aa" />
								<a onclick="downloads();" id="downloads" class="easyui-linkbutton toolbar g-button"><i class="fa fa-download"></i>文件下载</a>
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-4" colspan="4">
								<label class="form-label" style="margin-right:-85px">内容</label>
								<textarea id="remark" name="remark" disabled="disabled"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>	
				
		</form>
	</div>
</div>

<div id="hidden-layout">
	<div id="sendEmail" class="easyui-dialog" title="发送邮件" style="width:400px;height:130px;">
		<form id="dataForm">
				<!-- 隐藏属性 -->
		<table class="g-form" cellpadding="0" cellspacing="0" style="min-width:0">
			<tbody>
				<tr>
					<td class="form-cell-4">
						<label class="form-label">邮箱地址</label>
						<input id="emailAddress" class="easyui-validatebox form-control" data-options="required:true,validType:'email'">
					</td>
				</tr>
			</tbody>
		</table>
	
	</form>

	<div id="toolbar" class="dialog-button">
		<a id="confirm" class="easyui-linkbutton g-button" onclick="sendEmail();"><i class="fa fa-check"></i>确定</a>
		<a id="cancel" class="easyui-linkbutton g-button" onclick="$('#sendEmail').dialog('close');"><i class="fa fa-times"></i>取消</a>
	</div>
	</div>
	
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/kindeditor-4.1.10/kindeditor.js"></script>

<script type="text/javascript">
var pageType = $("#pageType").val();

/**
 * 脚本文件，脚本入口 
 */
 $(function(){
	 tabInit();
	 enableButtons([ "add"]); // 初始加载时设置按钮可用
	 datagridInit(); // 初始化数据表格
	 editorInit();  // 初始化编辑器
	 publicAttr.initCombobox();
	 $("#mainTab").tabs("disableTab", 1); // 初始加载时详细标签不可用
	 pageTypeInit();
	 $('#sendEmail').dialog({closed:true});  
	 
	//文件上传初始化
		fileNameUpload = $("#aa").fileupload({
			url :"${ctx}/doc/upload",
			upfile : "upfile",
			hidden:true,
			params : {
				directory : "partyRegime"
			},
			onUploadComplete : function(result) {
				console.log(result.docId);
				$("#docId").val(result.docId);
				$("#docName").val(result.docShowname);
			}
		}); 
 });
 
    /**
	 * 文件上传
	 */
	function upload() {
		fileNameUpload.fileupload("click");
 }
 
	/**
	 * 文件下载
	 */
	function downloads() {
		var docId = "";
		var row = $("#listGrid").datagrid('getSelected');	
		if(row==null){
			docId = $("#docId").val();
		}else{
			docId=row.docId;
		}
		console.log(docId);
		if (docId) {
			location.href = "${ctx}/doc/download/" + docId;
		} else {
			$.messager.show({
				title : "温馨提示",
				msg : "未上传文件",
				timeout : 2500,
				showType : "slide"
			});
		}
	}

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
		fitColumns : true,
		queryParams : getFormData("#queryForm"),
		url : "${ctx}/partyRegime/search/" + pageType,
		columns : [[{
			field : "regimeId",
			title : "主键",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "docId",
			title : "文件Id",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "regimeCode",
			title : "编号",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "regimeTitle",
			title : "标题",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "isEnable",
			title : "是否有效",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, index){
				if(value == 'Y'){
					return '有效';
				}else if(value == 'N'){
					return '无效';
				}
			}
		},{
			field : "docName",
			title : "上传文件名",
			align:'left',
			halign:'center',
			width : 200
		}
		]],
		onSelect : function(rowIndex, rowData) {
			enableButtons([ "add", "del", "edit", "send", "downloads"]);
			$("#mainTab").tabs("enableTab", 1);
		},
		onUnselectAll : function(rowIndex, rowData) {
			var tab = $("#mainTab").tabs("getSelected");
			var index = $("#mainTab").tabs("getTabIndex", tab);
			if(index == 0){
				enableButtons(["add"]);
				$("#mainTab").tabs("disableTab", 1);
			}else if(index == 1){
				enableButtons(["add", "del", "save", "send", "upload", "downloads"]);
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
			enableButtons(["add", "del", "save", "send", "upload"]);
			$("#mainTab").tabs("select", 1);
			setFormItemDisabled("#dataForm", false);
		},
		onLoadSuccess : function(data) {
			$("#listGrid").datagrid("unselectAll");
		}
	});
	//设置分页形式
	$("#listGrid").datagrid("getPager").pagination({
		layout : defaultPageLayout
	});
}

/**
 * 初始化标签
 */
 function tabInit(){
	$("#mainTab").tabs({
		onSelect : function(title, index) {
			if(index == 0){
				$("#dataForm").form("clear");
				if($("#listGrid").datagrid("getSelections").length > 0){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons(["add", "del", "edit", "send"]);
				}else{
					$("#mainTab").tabs("disableTab", 1);
					enableButtons(["add"]);
				}
			}else if(index == 1){
				setFormItemDisabled("#dataForm", true);
				var row = $("#listGrid").datagrid("getSelected");
				remarkContentEditor.readonly(true);
				if(row){
					enableButtons(["add", "del", "send", "edit", "downloads"]);
					$.ajax({
						url : "${ctx}/partyRegime/get/" + row.regimeId,
					}).done(function(res){
						console.log(res.row);
						$("#dataForm").form("load", res.row);
						remarkContentEditor.html(res.row.remark);
					}).fail(function(){
						$.messager.alert("温馨提示","获取数据出错！","error");
					});
				}
			}
		}
	});
}

/**
 * 设置按钮是否可用 
 */
function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	// 设置按钮可用
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).linkbutton("enable");
	}
}

/**
 * 新增党务制度
 */
 function add(){
	$("#listGrid").datagrid("unselectAll");
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
	$("#dataForm").form("clear");
	enableButtons(["add", "save", "upload"]);
	setFormItemDisabled("#dataForm", false);
	$('#regimeType').combobox('setValue', pageType);
	$('#regimeType').combobox('disable');
	$("#regimeCode").attr("disabled", true);
	remarkContentEditor.html('');
	remarkContentEditor.readonly(false);
	
	//自动生成编号 
	$.ajax({
		url : "${ctx}/coderule/generateCode/ZSK",
		success : function(data) {
			$("#regimeCode").val(data);
		}
	});
}

/**
 * 删除党务制度
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var regimeId = "";
	var regimeTitle = "";
	if(row == null){
		regimeId = $("#regimeId").val();
		regimeTitle = $("#regimeTitle").val();
	}else{
		regimeId = row.regimeId;
		regimeTitle = row.regimeTitle;
	}
	
	if(regimeId){
		$.messager.confirm("温馨提示", "确定删除党务制度【" + regimeTitle + "】?",
				function(r){
			if(r){
				$.ajax({
					url : "${ctx}/partyRegime/del/" + regimeId,
				}).done(function(res){
					$.messager.progress("close");
					if(res.resultCode == 0){
						 $("#dataForm").form("clear");
						 $("#mainTab").tabs("select", 0);
						 $("#mainTab").tabs("disableTab", 1);
						 enableButtons([ "add" ]);
						 query();
					}
					$.messager.show({
						 title : "温馨提示",
						 msg : "操作成功",
						 timeout : 2500,
						 showType : "slide"
				     });
				});
			}
		});
	}
	
}

/**
 * 修改党务制度
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	if(row){
		$.ajax({
			url : "${ctx}/partyRegime/get/" + row.regimeId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
			enableButtons([ "add","del","save","send", "upload", "downloads" ]);
			$("#dataForm").form("load", res.row);
			setFormItemDisabled("#dataForm", false);
			$('#regimeType').combobox('setValue', pageType);
// 			$('#q_regimeType_EQ').combobox('disable');
			$('#regimeType').combobox('disable');
			$("#regimeCode").attr("disabled", true);
			remarkContentEditor.readonly(false);
			remarkContentEditor.html(res.row.remark);
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存党务制度
	 */
	function save(){
	if($("#dataForm").form("validate")){
		remarkContentEditor.sync();
		var data = getFormData("#dataForm");
		console.log(data);
		$.ajax({
			type : "POST",
			url  : "${ctx}/partyRegime/save",
			data : data
		}).done(function(res){
			$("#dataForm").form("load", res.row);
			enableButtons([ "save","send" ]);
// 			$("#regimeId").val(res.row.regimeId);
			query();
			$.messager.show({
				title : "温馨提示",
				msg : "操作成功",
				timeout : 2500,
				showType : "slide"
			});
		}).fail(function(jqXHR, textStatus, errorThrown){
			$.messager.progress("close");
			$.messager.alert("温馨提示[" + textStatus + "]", "保存出错！", "error");
		});
	}
}
 
   /**
	 * 查询按钮
	 */
	function query() {
		$("#listGrid").datagrid("load", getFormData("#queryForm"));
	}
 
	function send() {
		$("#emailAddress").val("");
		$("#sendEmail").dialog("open");
	}
	
  /**
	* 发送邮件
	*/
	function sendEmail() {
		if (!$("#sendForm").form("validate")) {
			return;
		}
		
		var row = $("#listGrid").datagrid('getSelected');
		var regimeId = '';
		if(row){
			regimeId = row.regimeId;
		}else{
			regimeId = $('#regimeId').val();
		}
		
		$.messager.progress();
		$.ajax({
			type : "POST",
			url : "${ctx}/partyRegime/send",
			data : {
				emailAddress : $("#emailAddress").val(),
				regimeId : regimeId
			}
		}).done(function(result) {
			$.messager.progress("close");
			$("#sendEmail").dialog("close");
			$.messager.show({
				title : "温馨提示",
				msg : "发送成功",
				timeout : 2500,
				showType : "slide"
			});
		}).fail(function(jqXHR, textStatus, errorThrown) {
			$.messager.progress("close");
			$("#sendEmail").dialog("close");
			$.messager.alert("温馨提示", "系统出错！", "error");
		});
	}
	
  /**
	* 判断页面类型
	*/
	function pageTypeInit(){
		if(pageType == 'puSystem'){//党务公开制度
// 			$('#q_regimeType_EQ').combobox('setValue', pageType);
			$('#regimeType').combobox('setValue', pageType);
// 			$('#q_regimeType_EQ').combobox('disable');
			$('#regimeType').combobox('disable');
		}else if(pageType == 'Wobranch'){//党支部工作职责
// 			$('#q_regimeType_EQ').combobox('setValue', pageType);
			$('#regimeType').combobox('setValue', pageType);
// 			$('#q_regimeType_EQ').combobox('disable');
			$('#regimeType').combobox('disable');
		}else if(pageType == 'joinParty'){//入党程序
// 			$('#q_regimeType_EQ').combobox('setValue', pageType);
			$('#regimeType').combobox('setValue', pageType);
// 			$('#q_regimeType_EQ').combobox('disable');
			$('#regimeType').combobox('disable');
		}else if(pageType == 'course'){//三会一课
// 			$('#q_regimeType_EQ').combobox('setValue', pageType);
			$('#regimeType').combobox('setValue', pageType);
// 			$('#q_regimeType_EQ').combobox('disable');
			$('#regimeType').combobox('disable');
		}
	}

	   /**
		 * 初始化编辑器
		 */
		 function editorInit() {
				KindEditor.ready(function(k) {
					remarkContentEditor = k.create("#remark", {
						width: '100%',
						height: '350px',
						items: [
								'fullscreen', '|',
								'source', '|',
								'undo', 'redo', '|',
								'preview', 'print', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|',
								'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', '|',
								'insertorderedlist','insertunorderedlist', 'indent', 'outdent', '|',
								'subscript', 'superscript', 'clearhtml', 'quickformat',
								'selectall',  '/',
								'formatblock', 'fontname', 'fontsize', '|',
								'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
								'table', 'hr', 'emoticons', 'pagebreak', 'anchor', 'link', 'unlink', '|', 'multiselectimage'
							]
					});
					remarkContentEditor.readonly(true);
				});
			}
	 
</script>

</body>
</html>