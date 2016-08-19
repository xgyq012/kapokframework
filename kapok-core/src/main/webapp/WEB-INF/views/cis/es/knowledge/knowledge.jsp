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
	<a onclick="upload();" id="upload" class="easyui-linkbutton toolbar g-button"><i class="fa fa-upload"></i>文件上传</a>
	<input type="hidden" id="aa" />
	<a onclick="downloads();" id="downloads" class="easyui-linkbutton toolbar g-button"><i class="fa fa-download"></i>文件下载</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query" >
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
						<td class="form-cell-1">
	            				<label class="form-label">编号</label>
	            				<input class="easyui-validatebox form-control" name="q_knowledgeCode_LIKE"/>
	            		</td>
	            		<td class="form-cell-1">
	            				<label class="form-label">标题</label>
	            				<input class="easyui-validatebox form-control" name="q_knowledgeTitle_LIKE"/>
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
	    		<input id="knowledgeId" name="knowledgeId" type="hidden" />
    			<input id="knowledgeDocId" name="knowledgeDocId" type="hidden" />
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
		     					<input id="knowledgeCode" name="knowledgeCode" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">标题</label>
		     					<input name="knowledgeTitle" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px" required="required"/>
	          				</td>
	          				<td class="form-cell-1">
		            			<label class="form-label">类型</label>
		            			<input id="knowledgeType" name="knowledgeType" 
		            					dictCode="knowledgeType" 
		            					class="easyui-combobox form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
 	 								    	editable:false, 
 	 								    	panelHeight:'auto', 
 	 								    	valueField:'dictCode', 
 	 								    	textField:'dictName'" /> 
 							</td>
 							<td class="form-cell-1">
		            			<label class="form-label">是否有效</label>
		            			<input name="enable" 
		            					dictCode="enable" 
		            					class="easyui-combobox  form-control"
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
			            		<label class="form-label">上传文件名</label>
		     					<input id="knowledgeDocName" name="knowledgeDocName" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-4" colspan="4">
								<label class="form-label" style="margin-right:-85px">内容</label>
								<textarea id="knowledgeContent" name="knowledgeContent" disabled="disabled"></textarea>
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


<!-- <div id="sendEmail" class="easyui-dialog" -->
<!-- 			data-options="title:'发送邮件', closed:true, width:300, height:150,  -->
<!--  				buttons: [{  -->
<!--  					text:'发送', iconCls:'icon-ok', handler: sendEmail -->
<!-- 				},{  -->
<!--  					text:'取消', iconCls:'icon-undo', handler:function(){  -->
<!--  						$('#sendEmail').dialog('close');  -->
<!--  					}  -->
<!--  				}]">  -->
<!-- 			<form id="sendForm" class="data-form" method="post"> -->
<!-- 				<table> -->
<!-- 					<tr> -->
<!-- 						<td>邮箱地址：<input id="emailAddress" class="easyui-validatebox" -->
<!-- 							data-options="required:true,validType:'email'" -->
<!-- 							style="width: 200px;margin-top:26px;"></td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</form> -->
<!-- 		</div> -->


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
// 	 setFormItemDisabled("#dataForm", true, exclude);
	 pageTypeInit();
	 $('#sendEmail').dialog({closed:true});  //dialog默认关闭
	 
	//文件上传初始化
		fileNameUpload = $("#aa").fileupload({
			url :"${ctx}/doc/upload",
			upfile : "upfile",
			hidden:true,
			params : {
				directory : "knowledge"
			},
			onUploadComplete : function(result) {
				console.log(result.docId);
				$("#knowledgeDocId").val(result.docId);
				$("#knowledgeDocName").val(result.docShowname);
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
		var knowledgeDocId = "";
		var row = $("#listGrid").datagrid('getSelected');	
		if(row==null){
			knowledgeDocId = $("#knowledgeDocId").val();
		}else{
			knowledgeDocId=row.knowledgeDocId;
		}
		console.log(knowledgeDocId);
		if (knowledgeDocId) {
			location.href = "${ctx}/doc/download/" + knowledgeDocId;
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
		url : "${ctx}/knowledge/search/" + pageType,
		columns : [[{
			field : "knowledgeId",
			title : "主键",
			hidden : true
		},{
			field : "knowledgeCode",
			title : "编号",
			width : 120
		},{
			field : "knowledgeTitle",
			title : "标题",
			width : 120
		},{
			field : "enable",
			title : "是否有效",
			width : 120,
			formatter : function(value, row, index){
				if(value == 'Y'){
					return '有效';
				}else if(value == 'N'){
					return '无效';
				}
			}
		}
// 		,{
// 			field : "knowledgeType",
// 			title : "类型",
// 			width : 120,
// 			formatter : function(value, row, index){
// 				 if(value == 'examine'){
// 					return '考核制度';
// 				}else if(value == 'knowledge'){
// 					return '知识库';
// 				}
// 			}
// 		}
		,{
			field : "knowledgeDocName",
			title : "上传文件名",
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
				knowledgeContentEditor.readonly(true);
				if(row){
					enableButtons(["add", "save", "del", "send", "edit"]);
					$.ajax({
						url : "${ctx}/knowledge/get/" + row.knowledgeId,
					}).done(function(res){
						$("#dataForm").form("load", res.row);
						knowledgeContentEditor.html(res.row.knowledgeContent);
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
 * 新增知识库
 */
 function add(){
	$("#listGrid").datagrid("unselectAll");
	$("#dataForm").form("clear");
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
	enableButtons(["add", "save", "upload"]);
	setFormItemDisabled("#dataForm", false);
	$('#q_knowledgeType_EQ').combobox('setValue', pageType);
	$('#knowledgeType').combobox('setValue', pageType);
	$('#q_knowledgeType_EQ').combobox('disable');
	$('#knowledgeType').combobox('disable');
	$("#knowledgeCode").attr("disabled", true);
	knowledgeContentEditor.html('');
	knowledgeContentEditor.readonly(false);
	
	//自动生成编号 
	$.ajax({
		url : "${ctx}/coderule/generateCode/ZSK",
		success : function(data) {
			$("#knowledgeCode").val(data);
		}
	});
}

/**
 * 删除知识库
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var knowledgeId = "";
	var knowledgeTitle = "";
	if(row == null){
		knowledgeId = $("#knowledgeId").val();
		knowledgeTitle = $("#knowledgeTitle").val();
	}else{
		knowledgeId = row.knowledgeId;
		knowledgeTitle = row.knowledgeTitle;
	}
	
	if(knowledgeId){
		$.messager.confirm("温馨提示", "确定删除知识库【" + knowledgeTitle + "】?",
				function(r){
			if(r){
				$.ajax({
					url : "${ctx}/knowledge/del/" + knowledgeId,
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
 * 修改知识库
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	if(row){
		$.ajax({
			url : "${ctx}/knowledge/get/" + row.knowledgeId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
			enableButtons([ "add","del","save","send", "upload", "downloads" ]);
			$("#dataForm").form("load", res.row);
			setFormItemDisabled("#dataForm", false);
			$('#q_knowledgeType_EQ').combobox('setValue', pageType);
			$('#knowledgeType').combobox('setValue', pageType);
			$('#q_knowledgeType_EQ').combobox('disable');
			$('#knowledgeType').combobox('disable');
			$("#knowledgeCode").attr("disabled", true);
			knowledgeContentEditor.readonly(false);
			knowledgeContentEditor.html(res.row.knowledgeContent);
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存知识库
	 */
	function save(){
	if($("#dataForm").form("validate")){
		knowledgeContentEditor.sync();
		var data = getFormData("#dataForm");
		console.log(data);
		$.ajax({
			type : "POST",
			url  : "${ctx}/knowledge/save",
			data : data
		}).done(function(res){
			$("#dataForm").form("load", res.row);
			enableButtons([ "save","send" ]);
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
		$.messager.progress();
		var row = $("#listGrid").datagrid("getSelected");
		$.ajax({
			type : "POST",
			url : "${ctx}/knowledge/send",
			data : {
				emailAddress : $("#emailAddress").val(),
				knowledgeId : row.knowledgeId
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
		if(pageType == 'examine'){//考核督办
			$('#q_knowledgeType_EQ').combobox('setValue', pageType);
			$('#knowledgeType').combobox('setValue', pageType);
			$('#q_knowledgeType_EQ').combobox('disable');
			$('#knowledgeType').combobox('disable');
		}else if(pageType == 'knowledge'){//knowledge
			$('#q_knowledgeType_EQ').combobox('setValue', pageType);
			$('#knowledgeType').combobox('setValue', pageType);
			$('#q_knowledgeType_EQ').combobox('disable');
			$('#knowledgeType').combobox('disable');
		}
	}

	   /**
		 * 初始化编辑器
		 */
		 function editorInit() {
				KindEditor.ready(function(k) {
					knowledgeContentEditor = k.create("#knowledgeContent", {
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
					knowledgeContentEditor.readonly(true);
				});
			}
	 
	 
</script>

</body>
</html>