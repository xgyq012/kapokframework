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
	<a onclick="estimate();" id="estimate" class="easyui-linkbutton toolbar g-button"><i class="fa fa-pencil-square-o"></i>评价</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query" >
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
						<td class="form-cell-1">
	            				<label class="form-label">日记标题</label>
	            				<input class="easyui-validatebox form-control" name="q_diaryTitle_EQ"  />
	            		</td>
	            		<td class="form-cell-1">
	            				<label class="form-label">编写人员</label>
	            				<input class="easyui-validatebox form-control" name="q_writer_EQ"  />
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
		
		<div class="list-area" style="top:50px;">
			<table id="listGrid" style="height:100%"></table>
		</div>
		
	</div>

	<div id="minute" data-options="disabled:true"  title="详细"   >
		<form id="dataForm"  action="">
				<!-- 隐藏属性 -->
	    		<input id="diaryId" name="diaryId" type="hidden" />
<!-- 	    		<input id="hiDiaryStatus" name="diaryStatus" type="hidden" /> -->
				<input id="createrId" name="createrId" type="hidden" />
<!-- 				<input id="createBy" name="createBy" type="hidden" /> -->
				<input id="createTime" name="createTime" type="hidden" />
				<input id="delSign" name="delSign" type="hidden" />
				
		 <fieldset>
		 	<legend>基础数据</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td colspan="1" class="form-cell-1">
	            				<label class="form-label">日记标题</label>
	            				<input class="easyui-validatebox form-control" id="diaryTitle" name="diaryTitle" required="required" />
	            			</td>
	            			<td colspan="1" class="form-cell-1">
	            				<label class="form-label">所属网格</label>
	            				<input class="easyui-validatebox form-control" id="unitsId" name="unitsId" />
	            			</td>
	            			<td class="form-cell-1">
	            				<label class="form-label">编写人员</label>
	            				<input class="easyui-validatebox form-control" id="writer" name="writer"/>
	            			</td>
	            			<td class="form-cell-1">
	            				<label class="form-label">评价人</label>
	            				<input class="easyui-validatebox form-control" id="estimatePerson" name="estimatePerson"/>
	            			</td>
						</tr>
						<tr>
							<td class="form-cell-1">
		            			<label class="form-label">状态</label>
		            			<input id="diaryStatus" name="diaryStatus" 
		            					dictCode="diaryStatus"
		            					disable="disable"
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
	          				<td class="form-cell-1">
		            			<label class="form-label">评定等级</label>
		            			<input id="estimateLevel" name="estimateLevel" 
		            					dictCode="estimateLevel" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
 							</td>
 							<td class="form-cell-1">
			            		<label class="form-label">提交时间</label>
		     					<input id="submitTime" name="submitTime" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
 							<td class="form-cell-1">
			            		<label class="form-label">评价时间</label>
		     					<input id="estimateTime" name="estimateTime" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-1">
			            		<label class="form-label">日记日期</label>
		     					<input id="diaryDate" name="diaryDate" class="easyui-datebox form-control" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label">日记内容</label>
		     					<textarea name="diaryContent" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
	          				</td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label">评价内容</label>
		     					<textarea id="estimateContent" name="estimateContent" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
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
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">
/**
 * 脚本入口
 */
$(function(){
	 tabInit();
	 enableButtons([ "add"]); // 初始加载时设置按钮可用
	 datagridInit(); // 初始化数据表格
	 $("#mainTab").tabs("disableTab", 1); // 初始加载时详细标签不可用
	 publicAttr.initCombobox();
	 estimatePersonLookup();
	 writerLookup();
	 orgLookup();
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
// 		fitColumns : true,
		queryParams : getQueryFormData("#queryForm"),
		url : "${ctx}/conditionsDiary/search",
		columns : [[{
			field : "diaryId",
			title : "主键",
			align:'left',
			halign:'center',
			hidden : true
		},{
			field : "diaryTitle",
			title : "日记标题",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "writerName",
			title : "编写人员",
			align:'left',
			halign:'center',
			width : 120
// 			formatter : function(value, row, index){
// 				return publicAttr.getUserName(value);
// 			}
		},{
			field : "diaryDate",
			title : "日记日期",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "diaryStatus",
			title : "状态",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, Index){
					if(value == 'draft'){
						return '草稿';
					}else if(value == 'submit'){
						return '提交';
					}else if(value == 'estimate'){
						return '评价';
					}
			}
		},{
			field : "estimatePersonName",
			title : "评价人",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "estimateLevel",
			title : "评定等级",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, index){
				if(value == 'diaryScoreA'){
					return '优';
				}else if(value == 'diaryScoreB'){
					return '良';
				}else if(value == 'diaryScoreD'){
					return '中';
				}else if(value == 'diaryScoreC'){
					return '差';
				}
			}
		},{
			field : "estimateTime",
			title : "评价时间",
			align:'left',
			halign:'center',
			width : 120
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
// 				enableButtons(["add", "del", "save"]);
// 				GetSizing();
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
// 			enableButtons(["add", "del", "save"]);
			GetSizing();
			$("#mainTab").tabs("select", 1);
			setFormItemDisabled("#dataForm", false);
			$("#diaryStatus").combobox('disable');
			$("#estimateContent").attr("disabled", true);
			$("#estimateLevel").combobox('disable');
			$("#writer").lookup('disable');
			$("#submitTime").datebox('disable');
			$("#estimatePerson").lookup('disable');
			$("#estimateTime").datebox('disable');
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
					$("#listGrid").datagrid("unselectAll");
					enableButtons(["add"]);
				}else{
					$("#mainTab").tabs("disableTab", 1);
					enableButtons(["add"]);
				}
			}else if(index == 1){
				setFormItemDisabled("#dataForm", true);
				var row = $("#listGrid").datagrid("getSelected");
				if(row){
					GetSizing();
// 					enableButtons(["add", "edit", "submit"]);
					$.ajax({
						url : "${ctx}/conditionsDiary/get/" + row.diaryId,
					}).done(function(res){
						$("#dataForm").form("load", res.row);
						$("#writer").lookup("setTxt", res.row.writerName);
						$("input[name='writer']").val(res.row.writer);
						$("#estimatePerson").lookup("setTxt", res.row.estimatePersonName);
						$("input[name='estimatePerson']").val(res.row.estimatePerson);
						$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
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
 * 新增民情日记
 */
 function add(){
	$("#listGrid").datagrid("unselectAll");
	$("#dataForm").form("clear");
	$("#mainTab").tabs("enableTab", 1);
	$("#mainTab").tabs("select", 1);
	enableButtons(["add", "save" ]);
	setFormItemDisabled("#dataForm", false);
	$("#diaryStatus").combobox('disable');
	$("#estimateContent").attr("disabled", true);
	$("#estimateLevel").combobox('disable');
	$("#writer").lookup('disable');
	$("#submitTime").datebox('disable');
	$("#estimatePerson").lookup('disable');
	$("#estimateTime").datebox('disable');
	defaultMesh();         //默认网格
}

/**
 * 删除民情日记
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var diaryId = "";
	var diaryTitle = "";
	if(row == null){
		diaryId = $("#diaryId").val();
		diaryTitle = $("#diaryTitle").val();
	}else{
		diaryId = row.diaryId;
		diaryTitle = row.diaryTitle;
	}
	
	if(diaryId){
		$.messager.confirm("温馨提示", "确定删除民情日记【" + diaryTitle + "】?",
				function(r){
			if(r){
				$.ajax({
					url : "${ctx}/conditionsDiary/del/" + diaryId,
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
 * 修改民情日记
 */
 function edit(){
	var row = $("#listGrid").datagrid("getSelected");
	if(row){
		$.ajax({
			url : "${ctx}/conditionsDiary/get/" + row.diaryId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
			setFormItemDisabled("#dataForm", false);
			$("#diaryStatus").combobox('disable');
			$("#estimateContent").attr("disabled", true);
			$("#estimateLevel").combobox('disable');
			enableButtons([ "add", "del", "save"]);
			$("#dataForm").form("load", res.row);
			$("#writer").lookup("setTxt", res.row.writerName);
			$("#estimatePerson").lookup("setTxt", res.row.estimatePersonName);
			$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.unitsId));
			$("#writer").lookup('disable');
			$("#submitTime").datebox('disable');
			$("#estimatePerson").lookup('disable');
			$("#estimateTime").datebox('disable');
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存民情日记
	 */
	function save(){
// 	if($("#dataForm").form("validate")){
		currentUser();
		$("#delSign").val('N');
		var data = getFormData("#dataForm");
		console.log(data);
		$.ajax({
			type : "POST",
			url : "${ctx}/conditionsDiary/save",
			data : data 
		}).done(function(res){
			$("#dataForm").form("load", res.row);
			$("#diaryId").val(res.row.diaryId);
// 			$("#writer").val(res.row.writer);
// 			$("#writer").lookup('setTxt', res.row.writerName);
// 			$("#estimatePerson").val(res.row.estimatePerson);
// 			$("#estimatePerson").lookup('setTxt', res.row.estimatePersonName);
			
			query();
			if(res.row.diaryStatus == 'draft'){
				$.messager.show({
					title : "温馨提示",
					msg : "操作成功",
					timeout : 2500,
					showType : "slide"
				});
			}
			if(res.row.estimateContent){
				setFormItemDisabled("#dataForm", true);
				$.messager.show({
					title : "温馨提示",
					msg : "评价保存成功",
					timeout : 2500,
					showType : "slide"
				});
			}
			
			var tab = $("#mainTab").tabs("getSelected");
			  var index = $("#mainTab").tabs("getTabIndex", tab);
			  if(index==1){
				  if(res.row.diaryStatus=='draft'){
					  enableButtons(["add", "save", "del", "submit"]);
				  }else if(res.row.diaryStatus=='estimate'){
					  enableButtons(["add"]);
				  }
			  }
		}).fail(function(jqXHR, textStatus, errorThrown){
			$.messager.progress("close");
			$.messager.alert("温馨提示[" + textStatus + "]", "保存出错！", "error");
		});
		  
			  
		  }
// 	}
 
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
   
   /**
   * 提交
   */
   function submit(){
	   var row = $("#listGrid").datagrid("getSelected");
		var diaryId = "";
		var diaryTitle = "";
		if(row == null){
			diaryId = $("#diaryId").val();
			diaryTitle = $("#diaryTitle").val();
		}else{
			diaryId = row.diaryId;
			diaryTitle = row.diaryTitle;
		}
		
		if(diaryId){
			$.messager.confirm("温馨提示", "确定要提交【"+ diaryTitle +"】?", function(r){
				  if(r){
					  $.ajax({
						  url : "${ctx}/conditionsDiary/submit/" +  diaryId
					  }).done(function(res){
						  $("#dataForm").form("load", res.row);
						  setFormItemDisabled("#dataForm", true);
						  query();
						  enableButtons(["add", "estimate"]);
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
    * 判断状态
    */
    function GetSizing(){
	   var row = $("#listGrid").datagrid("getSelected");
	   var diaryStatus = "";
	   if(row){
		   diaryStatus = row.diaryStatus;
	   }else{
		   diaryStatus = $("#diaryStatus").val();
	   }
	   
	   if(diaryStatus != null){
		    if(row.diaryStatus == 'draft'){
			   enableButtons(["add", "edit", "del", "submit"]);
		   }else if(row.diaryStatus == 'submit'){
		   		enableButtons(["add", "estimate"]);
		   }else if(row.diaryStatus == 'estimate"'){
			   enableButtons(["add"]);
		   }
	 }
   }
   
   /**
    * 评价
    */
    function estimate(){
    	$("#mainTab").tabs("select", 1);
    	enableButtons(["add", "save"]);
    	$("#estimateContent").attr('disabled', false);
    	$("#estimateLevel").combobox('enable');
   }
   
    var selectUser;
    function writerLookup() {
    	selectUser = $("#writer").lookup({
    		title : "选择编写人员",
     		url : "${ctx}/user/showuserlist",
     		width : 650,
     		height : 400,
    		contentWindow: {
    			params: {
    				singleSelect: true,
    				checkbox: false
    			},
    			actions: {
    				confirm: function(data) {
    					if (data) {
    						$("input[name='writer']").val(data.userId);
    						$("#writer").lookup("setTxt", data.realname);
    					}
    					main.closeDialog();
    				},
    				cancel : function() {
    					main.closeDialog();
    				}
    			}
    		}
    	});
    }
    
    function estimatePersonLookup() {
    	selectUser = $("#estimatePerson").lookup({
    		title : "选择编写人员",
     		url : "${ctx}/user/showuserlist",
     		width : 650,
     		height : 400,
    		contentWindow: {
    			params: {
    				singleSelect: true,
    				checkbox: false
    			},
    			actions: {
    				confirm: function(data) {
    					if (data) {
    						$("input[name='estimatePerson']").val(data.userId);
    						$("#estimatePerson").lookup("setTxt", data.realname);
    					}
    					main.closeDialog();
    				},
    				cancel : function() {
    					main.closeDialog();
    				}
    			}
    		}
    	});
    }
   
    
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
    
    function currentUser(){
    	var userId = '<shiro:principal property="userId"/>';
		var userName = '<shiro:principal/>';
		var row = getFormData("#dataForm");
		if(row.writer == ''){
			$("#writer").lookup('setTxt', userName);
			$("input[name='writer']").val(userId);
		}else if(row.estimatePerson == ''){
			$("#estimatePerson").lookup('setTxt', userName);
			$("input[name='estimatePerson']").val(userId);
		}
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