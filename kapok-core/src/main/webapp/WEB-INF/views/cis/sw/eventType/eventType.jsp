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
</div>

<div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

	<div title="列表" style="position:relative;">
		
		<div id="query">
			<form id="queryForm" class="formCls" method="post">
				<table  class="g-form" cellpadding="0" cellspacing="0">
					<tr>
						<td class="form-cell-1">
		            			<label class="form-label">事件类型</label>
		            			<input name="q_eventType_EQ" 
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
           					<input class="easyui-validatebox form-control" name="q_eventType_EQ"  />
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

	<div id="minute" data-options="disabled:true"  title="详细">
		<form id="dataForm"  action="">
				<!-- 隐藏属性 -->
	    		<input id="typeId" name="typeId" type="hidden" />
	    		<input id="slaveId" name="slaveId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				
		 <fieldset>
		 	<legend>基础数据</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							<td class="form-cell-1">
			            		<label class="form-label">事件类别</label>
		     					<input name="typeName" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px" required="required"/>
	          				</td>
	          				<td colspan="1" class="form-cell-1">
		            			<label class="form-label">事件类型</label>
		            			<input name="eventType" 
		            					dictCode="eventType" 
		            					class="easyui-combobox  form-control"
		            					style="width:${comboboxWidth};height:${comboboxHeight}px"
										data-options="
	 								    	editable:false,
	 								    	required:true,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'" />
							</td>
							<td class="form-cell-1"></td>
							<td class="form-cell-1"></td>
						</tr>
						<tr>
							<td colspan="4" class="form-cell-4">
			            		<label class="form-label">备注</label>
		     					<textarea name="remark" style="height:150px;" class="easyui-validatebox form-control" ></textarea>
	          				</td>
						</tr>
					</tbody>
			</table>
		 </fieldset>
		 </br>
<!-- 		 <fieldset> -->
<!--      	<legend>红黄牌信息</legend> -->
     	<!-- 行表区域 -->
     		<div id="tp" class="g-toolbar" style="margin:0 0 0 0;">
				<a onclick="addSlave();" id="addSlave" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
				<a onclick="delSlave();" id="delSlave" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
			</div>
     		<div id="sub" style="margin:20px 0 20px 110px;height:85%; width:89%;; overflow: scroll;">
     		
     				<table id="dg_cisSwEventSlave" class="easyui-datagrid" title="红黄牌信息"
     				   data-options="rownumbers: true,
									singleSelect: true,
									fitColumns:true,
									autoHeight: false,
									toolbar:'#tp',
									height: 250, 
									onClickRow : onDc_cisSwEventSlave">
     				<thead>
     					<tr>
     						<th data-options="field:'slaveId', hidden:true">行表主键</th>
     						<th data-options="field:'typeId', hidden:true">事件类型管理主键 </th>
     						<th data-options="field:'createrId', hidden:true">创建人ID</th>
     						<th data-options="field:'createrTime', hidden:true">创建人ID</th>
     						<th data-options="field:'slaveName', width:270,  formatter : function(value,row){
                                                    if(value == 'signFor'){
                                                    	return '签收';
                                                    }else if(value == 'deal'){
                                                    	return '处理';
                                                    }else if(value == 'clear'){
                                                    	return '办结';
                                                    }
                                                },
                                                editor : {
                                                    type : 'combobox',
                                                    options : {
                                                        valueField : 'dictCode',
                                                        textField : 'dictName',
                                                        url : '${ctx}/dict/getdict/eventSchedule',
                                                        panelHeight : 'auto'
                                                    }
                                                }">事件进度</th>
                            <th data-options="field:'redYellowCard', width:221, formatter : function(value,row){
                                                    if(value == 'redCard'){
                                                    	return '红牌';
                                                    }else if(value == 'yellowCard'){
                                                    	return '黄牌';
                                                    	}
                                                },
                                                editor : {
                                                    type : 'combobox',
                                                    options : {
                                                        valueField : 'dictCode',
                                                        textField : 'dictName',
                                                        url : '${ctx}/dict/getdict/redYellowCard',
                                                        panelHeight : 'auto'
                                                    }} ">红黄牌</th>
     						<th data-options="field:'timeOut', width:221, editor:{type:'validatebox', options:{required:true}}">超时(h)</th>
     					</tr>
     				</thead>
     				</table>
     		</div>
<!--      </fieldset> -->
		</form>
	</div>

</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>

<script type="text/javascript">
var isEdit = false;

/**
 * 脚本文件，脚本入口 
 */
 $(function(){
	 tabInit();
	 enableButtons([ "add"]); // 初始加载时设置按钮可用
	 datagridInit(); // 初始化数据表格
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
		queryParams : getFormData("#queryForm"),
		url : "${ctx}/eventType/search",
		columns : [[{
			field : "typeId",
			title : "主键id",
			align:'left',
			halign:'center',
			hidden: true
		},{
			field : "eventType",
			title : "事件类型",
			align:'left',
			halign:'center',
			width : 200,
			formatter : function(value, row, index){
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
			field : "typeName",
			title : "事件类别 ",
			align:'left',
			halign:'center',
			width : 200
		},{
			field : "remark",
			title : "备注",
			align:'left',
			halign:'center',
			width : 270
		}
		]],
		onSelect : function(rowIndex, rowData) {
			  $("#mainTab").tabs("enableTab", 1);
			  enableButtons(["add", "del", "edit"]);
		},
		onUnselectAll : function(rowIndex, rowData) {
			var tab = $("#mainTab").tabs("getSelected");
			var index = $("#mainTab").tabs("getTabIndex", tab);
			if(index == 0){
				enableButtons(["add"]);
				$("#mainTab").tabs("disableTab", 1);
			}else if(index == 1){
				enableButtons(["add", "del", "save", "addSlave", "delSlave"]);
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
			enableButtons(["add", "del", "save",  "addSlave", "delSlave"]);
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
				enableButtons(["add"]);
				$("#dataForm").form("clear");
				if($("#listGrid").datagrid("getSelections").length > 0){
					$("#mainTab").tabs("enableTab", 1);
					$("#listGrid").datagrid("unselectAll");
				}else{
					$("#mainTab").tabs("disableTab", 1);
					enableButtons(["add"]);
				}
			}else if(index == 1){
				setFormItemDisabled("#dataForm", true);
// 				isEdit = true;
// 				$('#riskStatus').combobox('disable');
				var rows = $("#listGrid").datagrid("getSelected");
				if(rows){
// 					$.ajax({
// 						url : "${ctx}/eventType/get/" + rows.typeId,
// 					}).done(function(res){
// 						console.log(res.row);
// // 						$("#dataForm").form('load', res.row.risk);
// // 						$("#dg_cisErRiskStatus").datagrid('loadData',
// // 								res.row.riskStatus);
// 					}).fail(function(){
// 						$.messager.alert("温馨提示","获取数据出错！","error");
// 					});
					loadDetialData(rows.typeId);
					console.log(rows.typeId);
				}else{
					loadDetialData(0);
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
  * 新增事件类别
  */
  function add(){
 	$("#listGrid").datagrid("unselectAll");
 	$("#dataForm").form("clear");
 	$("#mainTab").tabs("enableTab", 1);
 	$("#mainTab").tabs("select", 1);
 	enableButtons(["add", "save", "addSlave", "delSlave"]);
 	setFormItemDisabled("#dataForm", false);
 	$("#dg_cisSwEventSlave").datagrid('loadData', []);
 }
 
  /**
   * 删除事件类别
   */
   function del(){
  	var row = $("#listGrid").datagrid("getSelected");
  	var typeId = "";
  	if(row == null){
  		typeId = $("#typeId").val();
  	}else{
  		typeId = row.typeId;
  	}
  	
  	if(typeId){
  		$.messager.confirm("温馨提示", "确定删除事件类别【" + row.typeName + "】?",
  		function(r){
  			if(r){
  				$.ajax({
  					url : "${ctx}/eventType/del/" + typeId,
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
   * 修改事件类别
   */
   function edit(){
  	var row = $("#listGrid").datagrid("getSelected");
  	isEdit = true;
  	if(row){
  		$.ajax({
  			url : "${ctx}/eventType/get/" + row.typeId,
  		}).done(function(res){
  			console.log(res.row);
  			$("#mainTab").tabs("select", 1);
  			$("#dataForm").form("load", res.row);
  			if(res.row.cisSwEventSlave != null){
  				$("#dg_cisSwEventSlave").datagrid("loadData",{total:0,rows:[]});
  				$("#dg_cisSwEventSlave").datagrid("loadData", res.row.cisSwEventSlave);
  			}
  			setFormItemDisabled("#dataForm", false);
  			enableButtons(["add", "save", "del", "addSlave", "delSlave"]);
  		}).fail(function(){
  			$.messager.alert("温馨提示", "获取数据出错！", "error");
  		});
  	}
  }

  	/**
  	 * 保存事件类别
  	 */
  	function save(){
  	if($("#dataForm").form("validate")){
  		$("#dg_cisSwEventSlave").datagrid("acceptChanges");
		$("#dg_cisSwEventSlave").datagrid("unselectAll");
  		var data = getFormData("#dataForm");
  		console.log(data);
  		$.ajax({
  			type : "POST",
  			url : "${ctx}/eventType/save",
  			data : data
  		}).done(function(res){
  			console.log(res.type);
  			$("#dataForm").form("load", res.row);
  			if(res.row.cisSwEventSlave != null){
  				$("#dg_cisSwEventSlave").datagrid("loadData",{total:0,rows:[]});
  				$("#dg_cisSwEventSlave").datagrid("loadData", res.row.cisSwEventSlave);
  			}
  			query();
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
  		$("#listGrid").datagrid("load", getFormData("#queryForm"));
  	}
     
    /**
     * 添加行表信息
     */
     function addSlave(){
    	$("#dg_cisSwEventSlave").datagrid("insertRow", {index : 0, row : []});
    }
     
     /**
      * 删除行表信息
      */
      function delSlave(){
    	 var rows = $("#dg_cisSwEventSlave").datagrid("getSelections");
    	 var copyRows = [];
    	 for(var i=0; i<rows.length; i++){
    		 copyRows.push(rows[i]);
    	 }
    	 
    	 for(var i=0; i<rows.length; i++){
    		 var rowIndex = $("#dg_cisSwEventSlave").datagrid("getRowIndex", copyRows[i]);
    		 $("#dg_cisSwEventSlave").datagrid("deleteRow", rowIndex);
    	 }
     }
     
    /**
     * 行表双击触发事件
     */
     function onDc_cisSwEventSlave(index){
    	$("#dg_cisSwEventSlave").datagrid("acceptChanges");
 		$("#dg_cisSwEventSlave").datagrid("unselectAll");
     	$("#dg_cisSwEventSlave").datagrid("selectRow", index).datagrid("beginEdit", index);
     }
    
     /**
      * 获取详细数据
      * 
      * @param row
      */
     function loadDetialData(typeId) {
     	$.ajax({
     		url : "${ctx}/eventType/get/" + typeId,
     	}).done(
     			function(req) {
     				if (req.resultCode == 0) {
     					if (isEdit) {
     						enableButtons([ "add", "edit", "del" ]);
     						$("#dataForm").form("load", req.row);
     						$("#dg_cisSwEventSlave").datagrid("loadData",req.row.cisSwEventSlave);
//      						setFormItemDisabled("#dataForm", false);
     						$("#dataForm").form("enableValidation");
     					}else if(req.row == null){//新增
     						setFormItemDisabled("#dataForm", false);
     						$("#dg_cisSwEventSlave").datagrid("loadData",{total:0,rows:[]});
     						$("#dg_cisSwEventSlave").datagrid("loadData",[]);
     					}else {
//      						enableButtons([ "add", "del", "edit","resp","downloadPhotos" ]);
     						$("#dataForm").form("disableValidation");
     						setFormItemDisabled("#dataForm", true);
     						$("#dataForm").form("load", req.row);
     						if(req.row.cisSwEventSlave != null){
     							$("#dg_cisSwEventSlave").datagrid("loadData",req.row.cisSwEventSlave);
     						}
     					}
     				} else {
     					$.messager.alert("温馨提示", "获取数据出错！", "error");
     				}
     			}).fail(function() {
     		$.messager.alert("温馨提示", "获取数据出错！", "error");
     	});
     }
 
</script>

</body>
</html>