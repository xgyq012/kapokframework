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
	.clheight{
		height:0px;
	}
</style>

</head>
<body>

<div class="g-toolbar">
	<a onclick="add();" id="add" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="edit();" id="edit" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a onclick="save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query" >
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
						<td class="form-cell-1">
		            			<label class="form-label">项目进度</label>
		            			<input name="q_proBuildPlan_LIKE" 
		            					dictCode="proInvestPlan" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
 							
 							<td class="form-cell-1">
		            			<label class="form-label">项目类型</label>
		            			<input name="q_proBuildType_EQ" 
		            					dictCode="proBuildType" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
 							
 							<td class="form-cell-1">
	            				<label class="form-label">项目名称</label>
	            				<input class="easyui-validatebox form-control" name="q_proBuildName_EQ"  />
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

	<div id="minute" data-options="disabled:true"  title="详细"   >
		<form id="dataForm"  action="">
				<!-- 隐藏属性 -->
	    		<input id="proBuildId" name="proBuildId" type="hidden"/>
   				<input id="pictureId" name="pictureId" type="hidden" />
   				<input id="pictureName" name="pictureName" type="hidden" />
	    		<input id="org" name="org"  type="hidden"/>
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				
			<fieldset>
				<legend>基础信息</legend>
				
				<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td class="form-cell-1">
			            		<label class="form-label">项目名称</label>
		     					<input id="proBuildName" name="proBuildName" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px" required="required"/>
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">所属机构</label>
		     					<input id="unitsId" name="unitsId">
<!-- 		     					<input id="units" name="units" type="hidden"/> -->
	          				</td>
 							<td class="form-cell-1">
			            		<label class="form-label">开始日期</label>
		     					<input name="startDate" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">结束日期</label>
		     					<input name="endDate" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-1">
			            		<label class="form-label">总投资</label>
		     					<input name="gi" class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">年计划投资</label>
		     					<input name="yearPlan" class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td class="form-cell-1">
			            		<label class="form-label">截止目前完成投资</label>
		     					<input name="etf" class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td class="form-cell-1">
		            			<label class="form-label">项目类型</label>
		            			<input name="proBuildType" 
		            					dictCode="proBuildType" 
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
		            			<label class="form-label">建设性质</label>
		            			<input name="proBuildNature" 
		            					dictCode="buildNature" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
							<td class="form-cell-1">
		            			<label class="form-label">项目进度</label>
		            			<input name="proBuildPlan" 
		            					dictCode="proInvestPlan" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
 							<td colspan="2" class="form-cell-2">
			            		<label class="form-label">负责单位</label>
		     					<input name="unit" class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label">建设规模及主要建设内容</label>
		     					<textarea name="remark" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
	          				</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			
			<fieldset>
				<legend>图片</legend>
				<table class="g-form" cellpadding="0" cellspacing="0" >
				<tbody>
					<tr>
							<td class="form-cell-1">
			            		<label class="form-label"></label>
		     					<input id="picName" name="picName" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
		          			<td class="form-cell-1 f-button">
									<a onclick="upload()" id="upload" name="upload" onclick="query();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-arrow-circle-up"></i>图片上传</a>
									<input type="hidden" id="uploadPicture" />
									<a onclick="downloads();" id="downloads" class="easyui-linkbutton toolbar g-button"><i class="fa fa-arrow-circle-down"></i>图片下载</a>
							</td>
							<td class="form-cell-1"></td>
							<td class="form-cell-1"></td>
					</tr>
					<tr>
							<td>
								<img alt="" src="" id="img" />
							</td>
					</tr>
				</tbody>
				</table>
			</fieldset>
				
		</form>
	</div>

</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>


<script type="text/javascript">
 var remarkContentEditor;	

/**
 * 脚本文件，脚本入口 
 */
 $(function(){
	 tabInit();
	 orgLookup();
	 enableButtons([ "add"]); // 初始加载时设置按钮可用
	 datagridInit(); // 初始化数据表格
// 	 editorInit();  // 初始化编辑器
	 publicAttr.initCombobox();
	 $("#mainTab").tabs("disableTab", 1); // 初始加载时详细标签不可用
// 	 setFormItemDisabled("#dataForm", true, exclude);
	 
	//文件上传初始化
		fileNameUpload = $("#uploadPicture").fileupload({
			url :"${ctx}/doc/upload",
			upfile : "upfile",
			filetype : "jpg|jpeg|png",
			hidden:true,
			params : {
				directory : "photos"
			},
			onUploadComplete : function(result) {
				console.log(result.docId);
				$("#pictureId").val(result.docId);
				$("#pictureName").val(result.docShowname);
				$("#picName").val(result.docShowname);
				showPicture();
			}
		}); 
 });
 
    /**
	 * 图片上传
	 */
	function upload() {
		fileNameUpload.fileupload("click");
 }
 
	/**
	 * 图片下载
	 */
	function downloads() {
		var pictureId = "";
		var row = $("#listGrid").datagrid("getSelected");
		if(row == null){
			pictureId = $("#pictureId").val();
		}else{
			pictureId = row.pictureId;
		}
		console.log(pictureId);
		if (pictureId) {
			location.href = "${ctx}/doc/download/" + pictureId;
		} else {
			$.messager.show({
				title : "温馨提示",
				msg : "未上传文件",
				timeout : 2500,
				showType : "slide"
			});
		}
	}
	
	//图片预览
	function showPicture(){
		var pictureId = "";
		var picture = "";
		
		var row = $("#listGrid").datagrid("getSelected");
		if(row != null && ($("#pictureId").val() == null)){
			pictureId = row.pictureId;
		}else{
			pictureId = $("#pictureId").val();
		}
		
		if(pictureId){
			picture = "${ctx}/doc/showImgAndIsTemp/" + pictureId;
		}
	
		if(picture.length>0){
			$("#img").attr("src", picture).attr('height', '150px');
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
		queryParams : getQueryFormData("#queryForm"),
		url : "${ctx}/proBuild/search",
		columns : [[{
			field : "proBuildId",
			title : "主键",
			align:'left',
			halign:'center'
			,hidden : true
		},{
			field : "pictureId",
			title : "图片id",
			align:'right',
			halign:'center'
			,hidden : true
		},{
			field : "proBuildName",
			title : "项目建设名称",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "unitsId",
			title : "所属机构",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, index){
				return publicAttr.getOrgName(value);
			}
		},{
			field : "gi",
			title : "总投资",
			align:'right',
			halign:'center',
			width : 120
		},{
			field : "yearPlan",
			title : "截止目前完成投资",
			align:'right',
			halign:'center',
			width : 120
		},{
			field : "proBuildType",
			title : "项目类型",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, index){
				if(value == 'Y'){
					return '城建重点项目';
				}else if(value == 'N'){
					return '工业项目';
				}else if(value == 'I'){
					return '服务业项目';
				}else if(value == 'U'){
					return '社会事业项目';
				}else if(value == 'O'){
					return '城中村项目';
				}
			}
		},{
			field : "proBuildPlan",
			title : "项目进度",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, Index){
					if(value == 'Y'){
						return '未开始';
					}else if(value == 'N'){
						return '进行中';
					}else if(value == 'I'){
						return '已完成';
					}
			}
		}
		]],
		onSelect : function(rowIndex, rowData) {
			enableButtons([ "add", "del", "edit" ]);
			$("#mainTab").tabs("enableTab", 1);
		},
		onUnselectAll : function(rowIndex, rowData) {
			var tab = $("#mainTab").tabs("getSelected");
			var index = $("#mainTab").tabs("getTabIndex", tab);
			if(index == 0){
				enableButtons(["add"]);
				$("#mainTab").tabs("disableTab", 1);
			}else if(index == 1){
				enableButtons(["add", "del", "save", "upload"]);
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
			enableButtons(["add", "del", "save", "upload", "downloads"]);
			$("#mainTab").tabs("select", 1);
// 			remarkContentEditor.readonly(false);
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
				$("#dataForm").form("clear");//清除表单
				$("#img").removeAttr("src").removeAttr('height');//清除图片内容
				if($("#listGrid").datagrid("getSelections").length > 0){
					$("#mainTab").tabs("enableTab", 1);
					enableButtons(["add", "del", "edit"]);
				}else{
					$("#mainTab").tabs("disableTab", 1);
					enableButtons(["add"]);
				}
			}else if(index == 1){
				setFormItemDisabled("#dataForm", true);
				var row = $("#listGrid").datagrid("getSelected");
				if(row){
					enableButtons(["add", "edit", "downloads"]);
					$.ajax({
						url : "${ctx}/proBuild/get/" + row.proBuildId,
					}).done(function(res){
						$("#dataForm").form("load", res.row);
						showPicture();
						$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
						$("#picName").val(res.row.pictureName);
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
 * 新增项目建设
 */
 function add(){
	$("#listGrid").datagrid("unselectAll");
	$("#dataForm").form("clear");
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
	enableButtons(["add", "save", "upload" ]);
// 	remarkContentEditor.readonly(false);
	setFormItemDisabled("#dataForm", false);
// 	remarkContentEditor.html('');
	$("#img").removeAttr('src').removeAttr('height');
	$("#picName").val('');
	defaultMesh();         //默认网格
}

/**
 * 删除项目建设
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var proBuildId = "";
	var proBuildName = "";
	if(row == null){
		proBuildId = $("#proBuildId").val();
		proBuildName = $("#proBuildName").val();
	}else{
		proBuildId = row.proBuildId;
		proBuildName = row.proBuildName;
	}
	
	if(proBuildId){
		$.messager.confirm("温馨提示", "确定删除项目建设【" + proBuildName + "】?",
				function(r){
			if(r){
				$.ajax({
					url : "${ctx}/proBuild/del/" + proBuildId,
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
 * 修改项目建设
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	if(row){
		$.ajax({
			url : "${ctx}/proBuild/get/" + row.proBuildId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
			enableButtons([ "add","del","save", "upload", "downloads"]);
			$("#dataForm").form("load", res.row);
			setFormItemDisabled("#dataForm", false);
			$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
			showPicture();
			$("#picName").val(res.row.pictureName);
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存项目建设
	 */
	function save(){
	if($("#dataForm").form("validate")){
// 		remarkContentEditor.sync();
		var data = getFormData("#dataForm");
		console.log(data);
		$.ajax({
			type : "POST",
			url : "${ctx}/proBuild/save",
			data : data
		}).done(function(res){
			$("#dataForm").form("load", res.row);
			$("#proBuildId").val(res.row.proBuildId);
			enableButtons([ "save" ]);
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
		var param = getQueryFormData("#queryForm");
		console.log(param);
		$("#listGrid").datagrid("load", param);
		enableButtons([ "add" ]);
// 	   $("#listGrid").datagrid("load", getFormData("#queryForm"));
	}
 
   /**
	 * 初始化编辑器
	 */
	function editorInit() {
		KindEditor.ready(function(k) {
			remarkContentEditor = k.create("#remark", {
				width : "920px",
				height : "350px",
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview',
						'print', 'cut', 'copy', 'paste', 'plainpaste',
						'wordpaste', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'justifyfull', 'insertorderedlist',
						'insertunorderedlist', 'indent', 'outdent',
						'subscript', 'superscript', 'clearhtml', 'quickformat',
						'selectall', '|', 'fullscreen', '/', 'formatblock',
						'fontname', 'fontsize', '|', 'forecolor',
						'hilitecolor', 'bold', 'italic', 'underline',
						'strikethrough', 'lineheight', 'removeformat', '|',
						'table', 'hr', 'emoticons', 'pagebreak', 'anchor',
						'link', 'unlink', '|' ]
			});
			remarkContentEditor.readonly(true);
		});
	}
   
	var orgLookup;
	/**
	 * 巡检员弹出窗
	 */
	function orgLookup() {
		orgLookup = $('#unitsId').lookup({
			title: '选择所属网格',
			url: '${ctx}/mesh/selectmesh',
			width: 350,
			height: 500,
			required: true,
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
								$("input[name='units']").val(data.meshName);
								$("input[name='unitsId']").val(data.meshId);
								$("input[name='unitsId']").prev().val(data.meshName);
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
	
	/**
	 *  默认网格
	 */
	function defaultMesh(){
		var meshId = main.currentUserMesh.meshId;
		$("#unitsId").lookup("setTxt", publicAttr.getOrgName(meshId));
		$("#unitsId").lookup('setVal', meshId);
	}
</script>
</body>
</html>