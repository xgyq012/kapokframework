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
	<a onclick="assess();" id="assess" class="easyui-linkbutton toolbar g-button"><i class="fa fa-pencil-square-o"></i>初评</a>
	<a onclick="review();" id="review" class="easyui-linkbutton toolbar g-button"><i class="fa fa-pencil-square-o"></i>复评</a>
	<a onclick="audit();" id="audit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-pencil-square-o"></i>审核</a>
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query" >
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0" >
					<tr>
						<td class="form-cell-1">
	            				<label class="form-label">项目名称</label>
	            				<input class="easyui-validatebox form-control" name="q_riskName_LIKE"  />
	            		</td>
	            		<td class="form-cell-1">
		            			<label class="form-label">项目进度</label>
		            			<input name="q_riskType_EQ" 
		            					dictCode="proBuildType" 
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
	    		<input id="riskId" name="riskId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
			
			<fieldset>
				<legend>基础信息 </legend>
				<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td colspan="2" class="form-cell-2">
			            		<label class="form-label">项目名称</label>
		     					<input id="riskName" name="riskName" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px" required="required"/>
	          				</td>
	          				<td colspan="2" class="form-cell-2">
			            		<label class="form-label">实施单位</label>
		     					<input name="carryOut" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
						</tr>
						<tr>
							<td class="form-cell-1">
			            		<label class="form-label">所属机构</label>
<!-- 			            		<input id="org" name="org"  type="hidden"   /> -->
		     					<input id="unitsId" name="unitsId" />
	          				</td>
	          				<td class="form-cell-1">
		            			<label class="form-label">项目类型</label>
		            			<input name="riskType" 
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
			            		<label class="form-label">评估负责人</label>
		     					<input name="principalName" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px"/>
	          				</td>
	          				<td class="form-cell-1">
		            			<label class="form-label">状态</label>
		            			<input id="riskStatus" name="riskStatus" 
		            					dictCode="examineStatus" 
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
			            		<label class="form-label">项目简介</label>
		     					<textarea name="remark" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
	          				</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<br/>
			
     	<!-- 行表区域 -->
     		<div id="sub" style="margin:20px 0 20px 110px;height:85%; width:89%;">
     				<table id="dg_cisErRiskStatus" class="easyui-datagrid" title="审核信息"
     				   data-options="rownumbers: true, 
									singleSelect: true,
									fitColumns:true,
									height: 300, 
									autoHeight: false">
     				<thead>
     					<tr>
     						<th data-options="field:'statusId', hidden:true">风险项目状态主键</th>
     						<th data-options="field:'riskId', hidden:true">风险项目主键</th>
     						<th data-options="field:'createrId', hidden:true">创建人ID</th>
<!--      						<th data-options="field:'createTime', hidden:true">创建时间</th> -->
     						<th data-options="field:'riskStatus', width:80, 
     							formatter : function(value, row, index){
     								if(value == 'submit'){
     									return '提交';
     								}else if(value == 'assess'){
     									return '初评';
     								}else if(value == 'review'){
     									return '复评';
     								}else if(value == 'audit'){
     									return '审核';
     								}
     							}
     						">步骤</th>
     						<th data-options="field:'statusResult', width:80, 
     						    formatter : function(value , row, index){
     						    	if(value == 'Y'){
     						    		return '通过';
     						    	}else if(value == 'N'){
     						    		return '不通过';
     						    	}
     						    }
     						">结果</th>
     						<th data-options="field:'player', width:80">参与人</th>
     						<th data-options="field:'remark', width:370">审核意见</th>
     						<th data-options="field:'operator', width:80">操作人</th>
     						<th data-options="field:'createTime'">审核时间</th>
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
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
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
	 publicAttr.initCombobox();
	 $("#mainTab").tabs("disableTab", 1); // 初始加载时详细标签不可用
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
		fitColumns : true,
// 		queryParams : getFormData("#queryForm"),
		queryParams : getQueryFormData("#queryForm"),
		url : "${ctx}/risk/search",
		columns : [[{
			field : "riskId",
			title : "主键id",
			align:'left',
			halign:'center',
			hidden: true
		},{
			field : "riskName",
			title : "项目名称",
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
			field : "riskType",
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
			field : "carryOut",
			title : "实施单位",
			align:'left',
			halign:'center',
			width : 120
		},{
			field : "riskStatus",
			title : "项目评估状态",
			align:'left',
			halign:'center',
			width : 120,
			formatter : function(value, row, index){
				if(value == 'draft'){
					return '草稿';
				}else if(value == 'submit'){
					return '已提交';
				}else if(value == 'assess'){
					return '已初评';
				}else if(value == 'review'){
					return '已复评';
				}else if(value == 'audit'){
					return '已审核';
				}
			}
		},{
			field : "principalName",
			title : "评估负责人",
			align:'left',
			halign:'center',
			width : 120
		}
		]],
		onSelect : function(rowIndex, rowData) {
// 			enableButtons([ "add", "del", "edit", "review", "assess", "audit" ]);
			  GetSizing();
			  $("#mainTab").tabs("enableTab", 1);
		},
		onUnselectAll : function(rowIndex, rowData) {
			var tab = $("#mainTab").tabs("getSelected");
			var index = $("#mainTab").tabs("getTabIndex", tab);
			if(index == 0){
				enableButtons(["add"]);
				$("#mainTab").tabs("disableTab", 1);
			}else if(index == 1){
// 				enableButtons(["add", "del", "save", "submit"]);
				GetSizing();
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
			enableButtons(["add", "del", "save"]);
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
// 					enableButtons(["add", "del", "edit", "review", "assess", "audit"]);
					GetSizing();
				}else{
					$("#mainTab").tabs("disableTab", 1);
					enableButtons(["add"]);
				}
			}else if(index == 1){
				setFormItemDisabled("#dataForm", true);
				$('#riskStatus').combobox('disable');
				var rows = $("#listGrid").datagrid("getSelected");
				if(rows){
// 					enableButtons([ "add", "del", "save" ]);
					$.ajax({
						url : "${ctx}/risk/get/" + rows.riskId,
					}).done(function(res){
						console.log(res.row.riskStatus);
						$("#dataForm").form('load', res.row.risk);
						$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.risk.unitsId));
						$("#dg_cisErRiskStatus").datagrid('loadData', res.row.riskStatus);
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
	enableButtons(["add", "save"]);
	setFormItemDisabled("#dataForm", false);
	$("#dg_cisErRiskStatus").datagrid('loadData', []);
	$('#riskStatus').combobox('disable');
	defaultMesh();         //默认网格
}

/**
 * 删除项目建设
 */
 function del(){
	var row = $("#listGrid").datagrid("getSelected");
	var riskId = "";
	var riskName = "";
	if(row == null){
		riskId = $("#riskId").val();
		riskName = $("#riskName").val();
	}else{
		riskId = row.riskId;
		riskName = row.riskName;
	}
	
	if(riskId){
		$.messager.confirm("温馨提示", "确定删除风险项目【" + riskName + "】?",
		function(r){
			if(r){
				$.ajax({
					url : "${ctx}/risk/softDel/" + riskId,
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
			url : "${ctx}/risk/get/" + row.riskId,
		}).done(function(res){
			$("#mainTab").tabs("select", 1);
// 			enableButtons([ "add","del","save"]);
			$("#dataForm").form("load", res.row.risk);
			setFormItemDisabled("#dataForm", false);
			enableButtons(["add", "save", "del"]);
			$('#riskStatus').combobox('disable');
		}).fail(function(){
			$.messager.alert("温馨提示", "获取数据出错！", "error");
		});
	}
}

	/**
	 * 保存风险项目
	 */
	function save(){
	if($("#dataForm").form("validate")){
		var data = getFormData("#dataForm");
		console.log(data);
		$.ajax({
			type : "POST",
			url : "${ctx}/risk/save",
			data : data
		}).done(function(res){
			$("#dataForm").form("load", res.row);
// 			$("#mainTab").tabs("select", 0);
			query();
// 			enableButtons(["add", "save", "del", "submit"]);
			$.messager.show({
				title : "温馨提示",
				msg : "操作成功",
				timeout : 2500,
				showType : "slide"
			});
		}).fail(function(jqXHR, textStatus, errorThrown){
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
// 		$("#listGrid").datagrid("load", getFormData("#queryForm"));
	}
 
	 /**
	   * 提交
	   */
	   function submit(){
			  var riskId = "";
			  var riskName = "";
			  var row = $("#listGrid").datagrid("getSelected");
			  if(row == null){
				  riskId = $("#riskId").val();
				  riskName = $("#riskName").val();
			  }else{
				  riskId = row.riskId;
				  riskName = row.riskName;
			  }
			  
		  $.ajax({
			  url : "${ctx}/riskStatus/distinguish/" + riskId,
			  success : function(data){
				  if(data.resultCode == 0){
					  if(data.row == 'draft'){
						  $.messager.confirm("温馨提示", "确定要提交【"+ riskName +"】?", function(r){
							  if(r){
								  $("#riskId").val(riskId);
								  saveSubmit();
							  }
						  });
					  }
				  }
			  }
		  });
	  }
	 
	   /**
	   	* 提交保存
	   	*/
		function saveSubmit(){
		   $("riskStatus").combobox('setValue', 'submit');
		   var data = getFormData("#dataForm");
		   console.log(data);
		   $.ajax({
			   type : "POST",
			   url  : "${ctx}/riskStatus/save",
			   data : data
		   }).done(function(res){
			   if(res.resultCode == 0){
				   $.messager.show({
						  title : "温馨提示",
						  msg   : "保存成功",
						  timeout:2500,
						  showType : "slide"
					  });
				   $("input[name='riskId']").val(res.row.riskId);
				   query();
				   loadDetail();
				   GetSizing();
			   }
		   });
	   }
	   
  /**
   * 初评
   */
   function assess(){
	  var row = $("#listGrid").datagrid("getSelected");
	  var riskId = "";
	  if(row == null){
		  riskId = $("#riskId").val();
	  }else{
		  riskId = row.riskId;
	  }
// 	  $.ajax({
// 		  url : "${ctx}/riskStatus/distinguish/" + riskId,
// 		  success : function(data){
// 			  if(data.resultCode == 0){
// 				  if(data.row == 'submit'){
// 					  $("#asForm").form("clear");
// 					  $("#as").dialog('open');
// 				  }
// 			  }
// 		  }
// 	  });
		  var meshId = main.currentUserMesh.meshId;
			 main.dialog({
					id: 'assessIframe',
					title: "初评",
					url: '${ctx}/risk/assess',
					width: 500,
					height: 226,
					cache: false,
					modal: true,
					contentWindow: {
						params: {
							riskId : riskId,
							meshId : meshId
						},
						actions: {
							confirm : function(data) {
								console.log(data);
								$("input[name='riskId']").val(data.row.riskId);
								main.closeDialog();
								query();
								loadDetail();
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
								loadDetail();
							}
						}
					}
				});
  }
   
  /**
   * 复评
   */
   function review(){
	   var riskId = "";
		  var row = $("#listGrid").datagrid("getSelected");
		  if(row == null){
			  riskId = $("#riskId").val();
		  }else{
			  riskId = row.riskId;
		  }
// 		  $.ajax({
// 			  url : "${ctx}/riskStatus/distinguish/" + riskId,
// 			  success : function(data){
// 				  if(data.resultCode == 0){
// 					  if(data.row == 'assess'){
// 						  $("#reForm").form("clear");
// 						  $("#re").dialog('open');
// 					  }
// 				  }
// 			  }
// 		  });

		  var meshId = main.currentUserMesh.meshId;
			 main.dialog({
					id: 'reviewIframe',
					title: "复评",
					url: '${ctx}/risk/review',
					width: 500,
					height: 226,
					cache: false,
					modal: true,
					contentWindow: {
						params: {
							riskId : riskId,
							meshId : meshId
						},
						actions: {
							confirm : function(data) {
								main.closeDialog();
								$("input[name='riskId']").val(data.row.riskId);
								query();
								loadDetail();
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
								loadDetail();
							}
						}
					}
				});
  }
 
   /**
    * 审核
    */
    function audit(){
 	  var riskId = "";
 	  var row = $("#listGrid").datagrid("getSelected");
	  if(row == null){
		  riskId = $("#riskId").val();
	  }else{
		  riskId = row.riskId;
	  }
 	  
//  	  $.ajax({
//  		  url : "${ctx}/riskStatus/distinguish/" + riskId,
//  		  success : function(data){
//  			  if(data.resultCode == 0){
//  				  if(data.row == 'review'){
//  					  $("#auForm").form("clear");
//  					  $("#au").dialog("open");
//  				  }
//  			  }
//  		  }
//  	  });

	  var meshId = main.currentUserMesh.meshId;
		 main.dialog({
				id: 'auditIframe',
				title: "审核",
				url: '${ctx}/risk/audit',
				width: 500,
				height: 226,
				cache: false,
				modal: true,
				contentWindow: {
					params: {
						riskId : riskId,
						meshId : meshId
					},
					actions: {
						confirm : function(data) {
							main.closeDialog();
							$("input[name='riskId']").val(data.row.riskId);
							query();
							loadDetail();
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
							loadDetail();
						}
					}
				}
			});
   }
  
  
 /**
  * 判断状态
  */
  function GetSizing(){
	  var riskId = "";
	  var row = $("#listGrid").datagrid("getSelected");
	  if(row == null){
		  riskId = $("#riskId").val();
	  }else{
		  riskId = row.riskId;
	  }
	  
	  var tab = $("#mainTab").tabs("getSelected");
	  var index = $("#mainTab").tabs("getTabIndex", tab);
	  
	  $.ajax({
		  url : "${ctx}/riskStatus/distinguish/" + riskId,
		  success : function(data){
			  if(data.resultCode == 0){
				  if(index == 0){
					  if(data.row == 'draft'){
						  enableButtons([ "add", "del", "edit", "submit" ]);
					  }else if(data.row == 'submit'){
						  enableButtons([ "add", "assess" ]);
					  }else if(data.row == 'assess'){
						  enableButtons([ "add", "review" ]);
					  }else if(data.row == 'review'){
						  enableButtons([ "add", "audit" ]);
					  }else if(data.row == 'audit'){
						  enableButtons([ "add" ]);
					  }
				  }else if(index == 1){
					  	  if(data.row == ''){
					  		enableButtons([ "add", "save" ]);
					  	  }else if(data.row == 'draft'){
							  enableButtons([ "add", "del", "save", "submit" ]);
						  }else if(data.row == 'submit'){
							  enableButtons([ "add", "assess" ]);
						  }else if(data.row == 'assess'){
							  enableButtons([ "add", "review" ]);
						  }else if(data.row == 'review'){
							  enableButtons([ "add", "audit" ]);
						  }else if(data.row == 'audit'){
							  enableButtons([ "add" ]);
						  }
				  }
				  
			  }
		  }
	  });
 }
 
  function noPass(data){
	  
	  console.log(data);
	  
	  $.ajax({
		  type : "POST",
		  url  : "${ctx}/riskStatus/noPass",
		  data : data
	  }).done(function(res){
		  if(res.resultCode === 0){
			  
		  }
	  });
  }
  
/**
 * 加载详细页信息
 */
 function loadDetail(){
	var riskId = "";
	var row = $("#listGrid").datagrid("getSelected");
	if(row){
		riskId = row.riskId;
	}else{
		riskId = $("#riskId").val();
	}
	
	$.ajax({
		url : "${ctx}/risk/get/" + riskId,
	}).done(function(res){
		if(res.resultCode == 0){
			$("#dataForm").form("load", res.row.risk);
			$("#unitsId").lookup("setTxt", publicAttr.getOrgName(res.row.risk.unitsId));
			$("#dg_cisErRiskStatus").datagrid("loadData", res.row.riskStatus);
		}
	}).fail(function(){
		$.messager.alert("温馨提示","获取数据出错！","error");
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
			textField: 'meshName'
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