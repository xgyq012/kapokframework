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
	<a onclick="edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>查看详细</a>
	<a onclick="sendMessage();" id="sendMessage" class="easyui-linkbutton toolbar g-button"><i class="fa-envelope"></i>发送短信</a>
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
							<label class="form-label">事件状态</label>
		            			<input name="q_enrollStatus_EQ" 
		            					dictCode="enrollStatus" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 						</td>
 						<td class="form-cell-1 f-button">
									<a onclick="query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
									<a onclick="clearQueryForm('#queryForm');query();" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
						</td>
 						<td class="form-cell-1"></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td class="form-cell-1"> -->
<!-- 	            				<label class="form-label">登记人</label> -->
<!-- 	            				<input class="easyui-validatebox form-control" name="createByName" /> -->
<!-- 	            		</td> -->
<!-- 	            		<td class="form-cell-1"> -->
<!-- 			            		<label class="form-label">登记时间</label> -->
<%-- 		     					<input id="baseCreateTime" name="createTime" class="easyui-datetimebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/> --%>
<!-- 	          			</td> -->
						
<!-- 						<td class="form-cell-1"></td> -->
<!-- 					</tr> -->
				</table>
			</form>
		</div>
		
		<div class="list-area" style="top:50px;">
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
	            				<input class="easyui-validatebox form-control" name="enrollTitle"/>
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
		            			<input name="enrollType" 
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
		            			<input name="enrollCategory" 
		            					dictCode="eventType" 
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
		 	<div id="sub" style="margin:20px 0 20px 110px;height:85%; width:89%;">
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
		 
		 <!-- 行表区域 -->
     		<div id="sub" style="margin:20px 0 20px 110px;height:85%; width:89%; ">
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
			
		</form>
	</div>

</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>

<script type="text/javascript">

/**
 * 脚本文件，脚本入口 
 */
 $(function(){
	 tabInit();
	 enableButtons([ "add"]); // 初始加载时设置按钮可用
	 datagridInit(); // 初始化数据表格
	 orgLookup();
// 	 submitLookup();
	 publicAttr.initCombobox();
	 $("#mainTab").tabs("disableTab", 1); // 初始加载时详细标签不可用

 });
 
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
			field : "lon",
			title : "事件发生经度",
			align:'left',
			halign:'center',
			width : 160,
		},{
			field : "lat",
			title : "事件发生纬度",
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
			enableButtons([ "edit", "sendMessage" ]);
			$("#mainTab").tabs("enableTab", 1);
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
			enableButtons(["sendMessage" ]);
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
 * 修改事件登记
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	if(row){
		$("#dataForm").form("validate");
		$.ajax({
			url : "${ctx}/oversee/get/" + row.enrollId,
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
			$("#enrollStatus").combobox('disable');
			$("#enrollTime").attr("disabled", false);
			if(res.row.enroll.enrollStatus == 'transact'){
				$("#suggestion").attr("disabled", false);
			}else{
				$("#suggestion").attr("disabled", true);
			}
			$("#evaluateLast").attr("disabled", true);
			$("#dataForm").form("load", res.row.enroll);
			$("#unitsId").lookup("setTxt",publicAttr.getOrgName(res.row.enroll.unitsId));
			$("#dg_cisSwEventFile").datagrid("loadData", res.row.enroll.cisSwEventFile);
			$("#dg_cisSwEnrollRowTable").datagrid("loadData", res.row.rowTable);
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
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
			console.log(res.row.enroll.cisSwEventFile);
			$("#dg_cisSwEventFile").datagrid("loadData", res.row.enroll.cisSwEventFile);
			$("#dg_cisSwEnrollRowTable").datagrid("loadData", res.row.rowTable);
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
  
  function sendMessage(){
	  $.messager.alert('温馨提示', '余额不足，请先充值。');
  }
</script>

</body>
</html>