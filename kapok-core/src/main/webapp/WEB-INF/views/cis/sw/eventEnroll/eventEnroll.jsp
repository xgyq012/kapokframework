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

<div class="g-toolbar">
	<a onclick="add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	<a onclick="edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	<a onclick="save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	<a onclick="del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	<a onclick="submit();" id="submit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-pencil-square-o"></i>提交</a>
	<a onclick="signFor();" id="signFor" class="easyui-linkbutton toolbar g-button"><i class="fa fa-pencil-square-o"></i>签收</a>
	<a onclick="transact();" id="transact" class="easyui-linkbutton toolbar g-button"><i class="fa fa-pencil-square-o"></i>处理</a>
	<a onclick="estimate();" id="estimate" class="easyui-linkbutton toolbar g-button"><i class="fa fa-pencil-square-o"></i>评价</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query" >
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
	            		<td class="form-cell-1">
	            				<label class="form-label">事件标题</label>
	            				<input class="easyui-validatebox form-control" name="q_enrollTitle_EQ"  />
	            		</td>
						<td class="form-cell-1">
							<label class="form-label">事件类型</label>
		            		<input name="q_enrollType_EQ" 
	            					dictCode="eventType" 
	            					class="easyui-combobox  form-control"
	            					style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
 						</td>
 						<td class="form-cell-1">
		            			<label class="form-label">事件来源</label>
		            			<input name="q_enrollSource_EQ" 
		            					dictCode="enrollSource" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
<!--  						<td class="form-cell-1"> -->
<!-- 		            			<label class="form-label">事件状态</label> -->
<!-- 		            			<input name="q_enrollStatus_EQ" -->
<!-- 		            					dictCode="enrollStatus" -->
<!-- 		            					class="easyui-combobox  form-control" -->
<%-- 		            					style="width:${comboboxWidth};height:${comboboxHeight}px" --%>
<!-- 										data-options=" -->
<!-- 	 								    	editable:false, -->
<!-- 	 								    	panelHeight:'auto', -->
<!-- 	 								    	valueField:'dictCode', -->
<!-- 	 								    	textField:'dictName'" /> -->
<!--  						</td> -->
						<td class="form-cell-1">
	            				<label class="form-label">涉及人数</label>
	            				<input  class="easyui-validatebox form-control" name="involePeople"/>
	            			</td>
					</tr>
					<tr>
						<td class="form-cell-1">
	            				<label class="form-label">登记人</label>
	            				<input class="easyui-validatebox form-control" name="createByName" />
	            		</td>
	            		<td class="form-cell-1">
			            		<label class="form-label">登记时间</label>
		     					<input id="baseCreateTime" name="createTime" class="easyui-datetimebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          			</td>
						<td class="form-cell-1 f-button">
									<a onclick="query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
									<a onclick="clearQueryForm('#queryForm');query();" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
						</td>
						<td class="form-cell-1"></td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="list-area" style="top:80px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
		
	</div>

	<div id="minute" data-options="disabled:true" title="详细" >
		<form id="dataForm" action="">
				<!-- 隐藏属性 -->
	    		<input id="enrollId" name="enrollId" type="hidden" />
	    		<input id="delSign" name="delSign" type="hidden" />
				<input id="docId" name="docId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input name="createTime" type="hidden" />
				
		 <fieldset>
		 	<legend>基础数据</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td class="form-cell-1">
	            				<label class="form-label">事件编号</label>
	            				<input class="easyui-validatebox form-control" name="enrollCode" id="enrollCode" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	            			</td>
	            			<td colspan="2" class="form-cell-2">
	            				<label class="form-label">事件标题</label>
	            				<input class="easyui-validatebox form-control" id="enrollTitle" name="enrollTitle" required="required"/>
	            			</td>
	            			<td class="form-cell-1">
			            		<label class="form-label">所属机构</label>
		     					<input id="unitsId" name="unitsId">
<!-- 		     					<input id="units" name="units" type="hidden"/> -->
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-1">
		            			<label class="form-label">事件来源</label>
		            			<input name="enrollSource" 
		            					dictCode="enrollSource" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
 							<td colspan="2" class="form-cell-2">
	            				<label class="form-label">事发场所</label>
	            				<input class="easyui-validatebox form-control" name="enrollPlace"/>
	            			</td>
	            			<td class="form-cell-1">
			            		<label class="form-label">事发时间</label>
		     					<input id="enrollTime" name="enrollTime" class="easyui-datetimebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px" />
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-1">
	            				<label class="form-label">当事人</label>
	            				<input class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px" name="client"/>
	            			</td>
	            			<td class="form-cell-1">
	            				<label class="form-label">当事人电话</label>
	            				<input class="easyui-validatebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px" name="clientPhone"/>
	            			</td>
	            			<td class="form-cell-1">
		            			<label class="form-label">事件类型</label>
		            			<input id="enrollType" name="enrollType"
		            					dictCode="eventType" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
 	 								    	editable:false, 
	 								    	panelHeight:'auto', 
	 								    	valueField:'dictCode', 
	 								    	textField:'dictName'" />
 							</td>
 							<td class="form-cell-1">
		            			<label class="form-label">事件类别</label>
		            			<input id="enrollCategory" name="enrollCategory" 
		            					class="easyui-combobox form-control" 
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
		            					data-options="panelHeight:'auto',editable:false "
		            					/>
<!-- 		            					dictCode="eventType"  -->
<!-- 										data-options=" -->
<!--  	 								    	editable:false,  -->
<!--  	 								    	panelHeight:'auto',  -->
<!--  	 								    	valueField:'dictCode',  -->
<!--  	 								    	textField:'dictName'" />  -->
 							</td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label">事件描述</label>
		     					<textarea name="remark" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-1">
	            				<label class="form-label">登记人</label>
	            				<input class="easyui-validatebox form-control" id="createByName" name="createByName" />
	            			</td>
	            			<td class="form-cell-1">
			            		<label class="form-label">登记时间</label>
		     					<input id="baseCreateTime" name="createTime" class="easyui-datetimebox form-control" disabled="disable" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td class="form-cell-1">
	            				<label class="form-label">涉及人数</label>
	            				<input  class="easyui-validatebox form-control" name="involePeople"/>
	            			</td>
	            			<td class="form-cell-1">
		            			<label class="form-label">事件状态</label>
		            			<input id="enrollStatus" name="enrollStatus" 
		            					dictCode="enrollStatus" 
		            					class="easyui-combobox  form-control"
		            					disabled="disable"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
						</tr>
					</tbody>
			</table>
		 </fieldset>
		 <br/>
		 <fieldset>
		 <legend>处理意见</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0" >
		 		<tbody>
		 			<tr>
						<td colspan="4" class="form-cell-4">
		            		<label class="form-label"></label>
	     					<textarea id="suggestion" name="suggestion" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
          				</td>
					</tr>
		 		</tbody>
		 	</table>
		 </fieldset>
		 <br/>
		 <fieldset>
		 	<legend>事件最终评价</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0" >
		 		<tbody>
		 			<tr>
		 				<td id="estimateInput" style="margin:0 0 0 110px;text-align:center" colspan="4" class="form-cell-4">
		 					<input id="evaluate" name="evaluate" type="radio" value="excellent" style="margin:0 0 0 70px;" /><i>优秀</i>
		 					<input id="evaluate" name="evaluate" type="radio" value="qualified" style="margin:0 0 0 70px;" /><i>合格</i>
		 					<input id="evaluate" name="evaluate" type="radio" value="so" style="margin:0 0 0 70px;" /><i>一般</i>
		 					<input id="evaluate" name="evaluate" type="radio" value="bad" style="margin:0 0 0 70px;" /><i>差</i>
		 				</td>
<!-- 							<td > -->
<!-- 								<input id="evaluate" name="evaluate" type="radio" value="excellent" style="margin:0 0 0 70px;" /><i>优秀</i>  -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<input id="evaluate" name="evaluate" type="radio" value="qualified" style="margin:0 0 0 70px;" /><i>合格</i>  -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<input id="evaluate" name="evaluate" type="radio" value="so" style="margin:0 0 0 70px;" /><i>一般</i>  -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<input id="evaluate" name="evaluate" type="radio" value="bad" style="margin:0 0 0 70px;" /><i>差</i>  -->
<!-- 							</td> -->
		 			</tr>
		 			<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label"></label>
		     					<textarea id="evaluateLast" name="evaluateLast" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
	          				</td>
					</tr>
		 		</tbody>
		 	</table>
		 </fieldset>
		 <br/>
		 
		 <div id="tb" class="g-toolbar" style="margin:0px 0px 0px 0px">
					<a onclick="upload()" id="upload" name="upload" onclick="query();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-arrow-circle-up"></i>上传</a>
					<input type="hidden" id="fileInput" />
					<a onclick="downloads();" id="downloads" class="easyui-linkbutton toolbar g-button"><i class="fa fa-arrow-circle-down"></i>下载</a>
					<a onclick="delFiles();" id="delFiles" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
			</div>
		 	<div style="margin:20px 0 20px 110px;height:85%; width:89%;">
   				<table id="dg_cisSwEventFile" class="easyui-datagrid" 
     				title="图片/音频/视频" 
     				   data-options="rownumbers: true,
									singleSelect: true,
									fitColumns:true,
									toolbar:'#tb',
									autoHeight: true,
									height: 400, 
									onClickRow : function(rowIndex, rowData) {
											filesSelect(rowIndex,rowData);
										}
				                    ">
     				<thead>
     					<tr>
     						<th data-options="field:'createrId', hidden:true">创建人ID</th>
     						<th data-options="field:'fileId', hidden:true">文件主键</th>
     						<th data-options="field:'enrollId', hidden:true">事件登记主键</th>
     						<th data-options="field:'docId', hidden:true">文件ID</th>
     						<th data-options="field:'docName', width:100">文件名</th>
     						<th data-options="field:'createTime', width:70">操作时间</th>
     					</tr>
     				</thead>
   				</table>
     		</div>
     		
     		</form>
		 
		 <!-- 行表区域 -->
     		<div style="margin:20px 0 20px 110px;height:85%; width:89%; ">
     				<table id="dg_cisSwEnrollRowTable" class="easyui-datagrid" title="事件处理过程" 
     				   data-options="rownumbers: true,
									singleSelect: true,
									autoHeight: true,
									fitColumns:true,
									height: 300, 
									autoHeight: false">
     				<thead>
     					<tr>
     						<th data-options="field:'rowTableId', hidden:true">事件登记行表主键</th>
     						<th data-options="field:'enrollId', hidden:true">事件登记ID</th>
     						<th data-options="field:'createrId', hidden:true">创建人ID</th>
     						<th data-options="field:'process', width:150, 
     						         formatter : function(value, row, index){
     						         	if(value == 'appearIn'){
     						         		return '上报';
     						         	}else if(value == 'assign'){
     						         		return '交办';
     						         	}else if(value == 'signFor'){
     						         		return '签收';
     						         	}else if(value == 'sendBack'){
     						         		return '退回';
     						         	}else if(value == 'transact'){
     						         		return '处理';
     						         	}else if(value == 'estimate'){
     						         		return '评价';
     						         	}
     						         }
     						   ">处理过程</th>
     						<th data-options="field:'backLogId', width:200, 
     							formatter : function (value,row,index){
						            return publicAttr.getOrgName(value);
								}
     						">待办机构(人)</th>
     						<th data-options="field:'remark', width:365">意见</th>
     						<th data-options="field:'operate', width:120">操作人</th>
     						<th data-options="field:'createTime',width: 150">操作时间</th>
     					</tr>
     				</thead>
     				</table>
     		</div>
			
		
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

/**
 * 脚本文件，脚本入口 
 */
 $(function(){
	 tabInit();
	 enableButtons([ "add"]); // 初始加载时设置按钮可用
	 datagridInit(); // 初始化数据表格
	 orgLookup();
	 cateInit();
	 console.log(main);
	 publicAttr.initCombobox();
	 $("#mainTab").tabs("disableTab", 1); // 初始加载时详细标签不可用

	//图片上传初始化
	fileNameUpload = $("#fileInput").fileupload({
		url :"${ctx}/doc/upload",
		upfile : "upfile",
		hidden:true,
		params : {
			directory : "photos"
		},
		onUploadComplete : function(result) {
			var data = {
					fileId : $("#fileId").val(),
					docId : result.docId,
					docName : result.docShowname,
					createTime : FormatDate (new Date())
				};
				$("#dg_cisSwEventFile").datagrid('insertRow', {
					index : 0,
					row : data
				});
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
		var row = $("#dg_cisSwEventFile").datagrid("getSelected");
		if(row){
			docId = row.docId;
		}else{
			docId = $("#docId").val();
		}
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
	
	/**
	  * 时间格式化 
	  */
	 	function FormatDate (strTime) {
	 	    var date = new Date(strTime);
	 	    return date.getFullYear()+"-"
	 	    +((date.getMonth()+1) < 10 ? "0"+(date.getMonth()+1) : date.getMonth()+1)+"-"
	 	    +((date.getDate()) < 10 ? "0"+(date.getDate()) : date.getDate())+" "
	 	    +((date.getHours()) < 10 ? "0"+(date.getHours()) : date.getHours())+":"
	 	    +((date.getMinutes()) < 10 ? "0"+(date.getMinutes()) : date.getMinutes())+":"
	 	    +((date.getSeconds()) < 10 ? "0"+(date.getSeconds()) : date.getSeconds());
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
// 		queryParams : getFormData("#queryForm"),
		queryParams : getQueryFormData("#queryForm"),
		url : "${ctx}/eventEnroll/search",
		columns : [[{
			field : "enrollId",
			title : "事件登记主键",
			align:'left',
			halign:'center',
			hidden : true
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
			field : "enrollTitle",
			title : "事件标题",
			align:'left',
			halign:'center',
			width : 200
		},{
			field : "enrollType",
			title : "事件类型",
			align:'left',
			halign:'center',
			width : 160,
			formatter : function(value , row, index){
				if(value == 'service'){
					return '服务办事';
				}else if(value == 'contradiction'){
					return '矛盾调处';
				}else if(value == 'peril'){
					return '问题隐患';
				}else if(value == 'inspectors'){
					return '城管事件';
				}
			}
		},{
			field : "enrollCateGoryName",
			title : "事件类别",
			align:'left',
			halign:'center',
			width : 160
		},{
			field : "enrollStatus",
			title : "事件状态",
			align:'left',
			halign:'center',
			width : 160,
			formatter : function(value, row, index){
				if(value == 'draft'){
					return '草稿';
				}else if(value == 'appearIn'){
					return '已上报';
				}else if(value == 'assign'){
					return '已交办';
				}else if(value == 'signFor'){
					return '已签收';
				}else if(value == 'sendBack'){
					return '退回';
				}else if(value == 'transact'){
					return '已处理';
				}else if(value == 'estimate'){
					return '已评价';
				}
			}
		},{
			field : "createByName",
			title : "登记人",
			align:'left',
			halign:'center',
			width : 170
		},{
			field : "createTime",
			title : "登记时间",
			align:'left',
			halign:'center',
			width : 170
		}
		]],
		onSelect : function(rowIndex, rowData) {
// 			enableButtons([ "add", "del", "edit" ]);
			$("#mainTab").tabs("enableTab", 1);
			GetSizing();
		},
		onUnselectAll : function(rowIndex, rowData) {
			var tab = $("#mainTab").tabs("getSelected");
			var index = $("#mainTab").tabs("getTabIndex", tab);
			if(index == 0){
				enableButtons(["add"]);
				$("#mainTab").tabs("disableTab", 1);
			}else if(index == 1){
				enableButtons(["add", "del", "save" ]);
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
// 			enableButtons(["add", "del", "save" ]);
			GetSizing();
			if(rowData.enrollStatus == 'draft'){
				$("#mainTab").tabs("select", 1);
				setFormItemDisabled("#dataForm", false);
			}
			$("#mainTab").tabs("select", 1);
			$("#createByName").attr("disabled", true);
			$("#enrollStatus").combobox('disable');
			$("#evaluateLast").attr("disabled", true);
			$("#suggestion").attr("disabled", true);
			$("#enrollTime").attr("disabled", false);
			$("#enrollCode").attr("disabled", true);
			$("#enrollCategory").combobox('disable');
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
					enableButtons(["add" ]);
					$("#listGrid").datagrid("unselectAll");
				}else{
					$("#mainTab").tabs("disableTab", 1);
					enableButtons(["add"]);
				}
			}else if(index == 1){
				setFormItemDisabled("#dataForm", true);
				var row = $("#listGrid").datagrid("getSelected");
				if(row){
// 					enableButtons(["add", "edit", "downloads"]);
// 					$.ajax({
// 						url : "${ctx}/eventEnroll/get/" + row.enrollId,
// 					}).done(function(res){
// 						$("#dataForm").form("load", res.row.enroll);
// 						$('#unitsId').lookup('setTxt', res.row.enroll.units);
// 						$("#dg_cisSwEventFile").datagrid("loadData", res.row.enroll.cisSwEventFile);
// 						$("#dg_cisSwEnrollRowTable").datagrid("loadData", res.row.rowTable);
// 					}).fail(function(){
// 						$.messager.alert("温馨提示","获取数据出错！","error");
// 					});
					GetSizing();
					loadDetail();
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
 * 新增事件登记
 */
 function add(){
	$("#listGrid").datagrid("unselectAll");
	$("#dataForm").form("clear");
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
	enableButtons(["add", "save", "upload", "delFiles", "downloads" ]);
	setFormItemDisabled("#dataForm", false);
	$("#createByName").attr("disabled", true);
	$("#enrollStatus").combobox('disable');
	$("#evaluateLast").attr("disabled", true);
	$("#suggestion").attr("disabled", true);
	$("#enrollTime").attr("disabled", false);
	$("#enrollCode").attr("disabled", true);
	$("#enrollCategory").combobox('disable');
	$("#dg_cisSwEventFile").datagrid("loadData",{total:0,rows:[]});
	$("#dg_cisSwEnrollRowTable").datagrid("loadData",{total:0,rows:[]});
	$("#enrollCategory").combobox('loadData', []);
	
	//自动生成编号 
	$.ajax({
		url : "${ctx}/coderule/generateCode/SJDJ",
		success : function(data) {
			$("#enrollCode").val(data);
		}
	});
	defaultMesh();         //默认网格
}

/**
 * 删除事件登记
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var enrollId = "";
	var enrollTitle = "";
	if(row == null){
		enrollId = $("#enrollId").val();
		enrollTitle = $("#enrollTitle").val();
	}else{
		enrollId = row.enrollId;
		enrollTitle = row.enrollTitle;
	}
	
	if(enrollId){
		$.messager.confirm("温馨提示", "确定删除事件登记【" + enrollTitle + "】?",
				function(r){
			if(r){
				$.ajax({
					url : "${ctx}/eventEnroll/del/" + enrollId,
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
 * 修改事件登记
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	if(row){
		$("#dataForm").form("validate");
		$.ajax({
			url : "${ctx}/eventEnroll/get/" + row.enrollId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
			enableButtons([ "add", "del", "save", "upload", "submit", "delFiles", "downloads"]);
			$("#dataForm").form("load", res.row);
			setFormItemDisabled("#dataForm", false);
			$("#estimateInput").find(":input").each(function(){
				$(this).attr("disabled", true);
			});
			$("#createByName").attr("disabled", true);
			$("#createByName").attr("disabled", true);
			$("#enrollCategory").combobox('disable');
			$("#enrollStatus").combobox('disable');
			$("#enrollTime").attr("disabled", false);
			$("#enrollCode").attr("disabled", true);
			if(res.row.enroll.enrollStatus == 'transact'){
				$("#suggestion").attr("disabled", false);
			}else{
				$("#suggestion").attr("disabled", true);
			}
			$("#evaluateLast").attr("disabled", true);
			$("#dataForm").form("load", res.row.enroll);
			$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.enroll.unitsId));
			$("#dg_cisSwEventFile").datagrid("loadData", res.row.enroll.cisSwEventFile);
			$("#dg_cisSwEnrollRowTable").datagrid("loadData", res.row.rowTable);
			if(res.row.enroll.enrollType){
				cateComBox(res.row.enroll.enrollType, res.row.enroll.enrollCateGoryName, res.row.enroll.enrollCateGoryId);
			}
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存事件登记
	 */
	function save(){
// 		if($("#dataForm").form("validate")){
			$("#dg_cisSwEventFile").datagrid("acceptChanges");
			$("#dg_cisSwEventFile").datagrid("unselectAll");
			$("#delSign").val('N');
			var data = getFormData("#dataForm");
			$.ajax({
				type : "POST",
				url : "${ctx}/eventEnroll/save",
				data : data
			}).done(function(res){
// 				enableButtons([ "save", "add", "submit", "del", "upload", "downloads", "delFiles"]);
// 				console.log(res.row.enroll);
				$("#dataForm").form("load", res.row.enroll);
// 				$("#enrollId").val(res.row.enrollId);
				if(res.row.enroll.enrollCateGoryName != null){
					$("#enrollCategory").combobox('setValue', res.row.enroll.enrollCateGoryName);
					$("input[name='enrollCategory']").val(res.row.enroll.enrollCateGoryId);
				}
				if(res.row.enroll.cisSwEventFile != null){
					$("#dg_cisSwEventFile").datagrid("loadData",{total:0,rows:[]});
					$("#dg_cisSwEventFile").datagrid("loadData", res.row.enroll.cisSwEventFile);
					}
				if(res.row.rowTable != null){
					$("#dg_cisSwEnrollRowTable").datagrid("loadData",{total:0,rows:[]});
					$("#dg_cisSwEnrollRowTable").datagrid("loadData", res.row.rowTable);
				}
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
// }
 
   /**
	 * 查询按钮
	 */
	function query() {
		var param = getQueryFormData("#queryForm");
		$("#listGrid").datagrid("load", param);
		enableButtons([ "add" ]);
// 		$("#listGrid").datagrid("load", getFormData("#queryForm"));
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
	
	/**
	 * 删除上传文件
	 */
	function delFiles() {
		var rows = $("#dg_cisSwEventFile").datagrid("getSelections");
		var copyRows = [];
		for (var j = 0; j < rows.length; j++) {
			copyRows.push(rows[j]);
		}
		for (var i = 0; i < copyRows.length; i++) {
			var index = $("#dg_cisSwEventFile").datagrid("getRowIndex", copyRows[i]);
			$("#dg_cisSwEventFile").datagrid("deleteRow", index);
		}
	}
	
	/**
	 * 选择文件方法
	 * @param rowIndex
	 * @param rowDate
	 */
	function filesSelect(rowIndex,rowDate) {
// 		enableButtons(["add", "save", "upload", "downloads", "delFiles", "submit", "del"]);
			$("#dg_cisSwEventFile").datagrid("selectRow", rowIndex)
					.datagrid("beginEdit", rowIndex);
	}
	
 /**
  * 加载详细页
  */
  function loadDetail(){
	 var row = $("#listGrid").datagrid("getSelected");
	 var enrollId = "";
	 if(row != null){
		 enrollId = row.enrollId;
	 }else{
		 enrollId = $("#enrollId").val();
	 }
	 
	 $("#dataForm").form("validate");
	 $.ajax({
		 url : "${ctx}/eventEnroll/get/" + enrollId,
	 }).done(function(res){
		 if(res.resultCode == 0){
			$("#dataForm").form("load", res.row.enroll);
			$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.enroll.unitsId));
			$("#enrollCategory").combobox('setValue', res.row.enroll.enrollCateGoryName);
			$("input[name='enrollCategory']").val(res.row.enroll.enrollCateGoryId);
			$("#dg_cisSwEventFile").datagrid("loadData",{total:0,rows:[]});
			$("#dg_cisSwEventFile").datagrid("loadData", res.row.enroll.cisSwEventFile);
			$("#dg_cisSwEnrollRowTable").datagrid("loadData",{total:0,rows:[]});
			$("#dg_cisSwEnrollRowTable").datagrid("loadData", res.row.rowTable);
		 }
	 });
 }
 
  /** 
   * 判断状态
   */
   function GetSizing(){
	   var row = $("#listGrid").datagrid("getSelected");

	   if(row.enrollStatus == 'draft'){
			 enableButtons(["add", "edit", "del", "submit"]);
		 }else if(row.enrollStatus== 'assign'){
			 enableButtons(["add", "signFor"]);
		 }else if(row.enrollStatus== 'appearIn'){
			 enableButtons(["add", "signFor"]);
		 }else if(row.enrollStatus == 'signFor'){
			 enableButtons(["add", "transact"]);
		 }else if(row.enrollStatus == 'transact'){
			 enableButtons(["add", "estimate"]);
		 }else if(row.enrollStatus == 'estimate'){
			 enableButtons(["add"]);
		 }
		 
  }
  
   /**
    * 提交
    */
    function submit(){
    	 var row = $("#listGrid").datagrid("getSelected");
		 var enrollId = "";
		 if(row != null){
			 enrollId = row.enrollId;
		 }else{
			 enrollId = $("#enrollId").val();
		 }
		 
		 var meshId = main.currentUserMesh.meshId;
    	main.dialog({
			id: 'eventSubmitIframe',
			title: "提交",
			url: '${ctx}/eventEnroll/submitEnroll',
			width: 450,
			height: 257,
			cache: false,
			modal: true,
			contentWindow: {
				params: {
					enrollId : enrollId,
					meshId   : meshId
				},
				actions: {
					confirm : function(data) {
						main.closeDialog();
						query();
						$.messager.show({
							  title : "温馨提示",
							  msg   : "保存成功",
							  timeout:2500,
							  showType : "slide"
						  });
					},
					cancel: function() {
						main.closeDialog();
					}
				}
			}
			
		});
 }
  
  /**
   * 签收
   */
   function signFor(){
	   var row = $("#listGrid").datagrid("getSelected");
		 var enrollId = "";
		 if(row != null){
			 enrollId = row.enrollId;
		 }else{
			 enrollId = $("#enrollId").val();
		 }
		 
		 var meshId = main.currentUserMesh.meshId;
		 main.dialog({
				id: 'signForIframe',
				title: "签收",
				url: '${ctx}/eventEnroll/signForEnroll',
				width: 450,
				height: 200,
				cache: false,
				modal: true,
				contentWindow: {
					params: {
						enrollId : enrollId,
						meshId   : meshId
					},
					actions: {
						confirm : function(data) {
							main.closeDialog();
// 							$("#enrollStatus").comboBox("setValue", data.row.process);
							query();
							$.messager.show({
								  title : "温馨提示",
								  msg   : "保存成功",
								  timeout:2500,
								  showType : "slide"
							  });
						},
						cancel: function() {
							main.closeDialog();
							query();
						}
					}
				}
			});
		 query();
  }
  
  /**
  * 处理
  */
  function transact(){
	    $("#mainTab").tabs("enableTab", 1);
		$("#mainTab").tabs("select", 1);
		$("#suggestion").attr("disabled", false);
		enableButtons(["add", "save"]);
  }
  
  /**
   * 评价
   */
   function estimate(){
 	    enableButtons(["add", "save"]);
 	    $("#mainTab").tabs("enableTab", 1);
 		$("#mainTab").tabs("select", 1);
 		enableButtons(["add", "save"]);
  		$("#estimateInput").find(":input").each(function(){
  			$(this).attr("disabled",false);
  		});
 		$("#evaluateLast").attr("disabled", false);
   }
  
  function cateInit(){
	  $("#enrollType").combobox({
			 onSelect : function(record){
				 cateComBox(record.dictCode);
			 }
		 });
  }
  
 /**
  * 事件类别下拉框
  */
  function cateComBox(dictCode, dictName, dictId){
	  $.ajax({
		 url : "${ctx}/eventEnroll/comboBox/" + dictCode,
	 }).done(function(res){ 
		 $("#enrollCategory").combobox({
			 valueField: 'typeId',
		     textField: 'typeName',
		     panelHeight:'auto'
		 });
		 if(dictName){
			 $("#enrollCategory").combobox('select', dictName);
			 $("input[name='enrollCategory']").val(dictId);
		 }
		 $("#enrollCategory").combobox('loadData', res.rows);
	 });
 }
 
  function getQueryFormData(formId){
		var meshIds = window.main.currentUserMesh.meshChildrenIds || "" ;
		var data =  getFormData(formId) || {};
		if(meshIds){
			data['q_unitsId_IN'] = meshIds;
		}
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