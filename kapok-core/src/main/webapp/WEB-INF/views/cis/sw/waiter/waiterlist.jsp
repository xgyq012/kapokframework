<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>服务团队队员管理</title>
<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css" type="text/css">
<!--[if IE 7]> 
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css"> 
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
<style type="text/css">
html,body{
   overflow-x:hidden;
   overflow-y:hidden;
   height: 100%;
}

input{
	width:130px;
	height:18px;
	line-height: 18px;
}

#baseForm input{
	width:300px;
	height:24px;
	line-height: 24px;
}
#baseForm textarea{
	width:600px;
	line-height: 18px;
}

div.otherinfo{
	display:none;
}

table tr td{
	text-align: right;
	padding:2px;
}

#baseForm .tdInput{
text-align: left;
}

.infoBtn{
  		text-align: left;
  	}
  	
#tanchuang input{
width: 20px;
}  	
</style>
</head>
<body>

<div class="easyui-panel" style="margin:5px 0 5px 0;">
      <a onclick="baseInfo.add();" id="add"  href="#" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-add'">新增</a>
      <a onclick="baseInfo.edit();" id="edit"  href="#" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
      <a onclick="baseInfo.save();" id="save" href="#" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-edit'">保存</a>
      <a onclick="baseInfo.del();" id="del"  href="#" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-remove'">删除</a>
</div>

<div id="p" class="easyui-panel" title="搜索区域"  style="width:100%;padding:10px;height:100px;"
        data-options="collapsible:true">
	<div id="query">
		<form id="queryForm" class="formCls" method="post">
			<table>
				<tr>	
					<td>队员名称</td>
					<td><input name="q_orgName_LIKE" type="text" /></td>
				    <td>手机</td>
					<td><input name="q_phone_LIKE" type="text" /></td>
					<td>在职状态</td>
					<td><input name="q_zaizhiStatus_EQ" class="easyui-combobox"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName',
 								    	url:'${ctx}/dict/getdict/zaizhiStatus'"></td>
					
					<td>
						<a href="javascript:void(0);" onclick="tableInfo.query();"
							class="easyui-linkbutton"
							data-options="plain:true,iconCls:'icon-search'">查询</a>
						<a href="javascript:void(0);" onclick="clearQueryForm('#queryForm');"
							class="easyui-linkbutton"
							data-options="plain:true,iconCls:'icon-undo'">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<div id="mainTab" class="easyui-tabs" style="width:100%;height:100%;padding-bottom: 110px;">

     <div title="列表" style="padding:5px;height: 100%">
         <div id="list" style="overflow: auto;height: 100%"">
			<table title="服务团队队员信息列表" id="listGrid" style="height:100%;"></table>
		</div>
     </div>
     
     <!-- data-options="disabled:true" -->
     <div  title="详细"  style="padding: 10px;height: 1000px;overflow: scroll;" >
     <fieldset style="width:95%;">
    		<legend>基础信息 </legend>
    		<form id="baseForm"  action="">
    		<input type="hidden" id="waiterId" name="waiterId">
    		<input id="createrId" name="createrId" type="hidden" />
			<input id="createTime" name="createTime" type="hidden" />
    		<table>
     			<tr>
     				<td>所属机构</td>
     				<td class="tdInput"><input type="text" name="jigouId" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>职务</td>
     				<td class="tdInput"><input type="text" name="job" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>服务区域</td>
     				<td class="tdInput"><input type="text" name="scope" class="easyui-validatebox" required="required"  /></td>
     			</tr>     			
     			<tr>
     				<td>队员名称</td>
     				<td class="tdInput"><input type="text" name="duiyuanName" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>头像</td>
     				<td class="tdInput"><input type="text" name="photo" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>电话</td>
     				<td class="tdInput"><input type="text" name="phone" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>在职状态</td>
     				<td class="tdInput"><input name="zaizhiStatus" class="easyui-combobox"
									data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName',
 								    	url:'${ctx}/dict/getdict/zaizhiStatus'"></td>
     			</tr>     			
     		</table>
     		</form>
     	</fieldset>
     </div>
</div>



<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script src="${ctx}/resources/gxwlui/dist/libs/layer/layer/layer.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >
var thisUiConfig = {
	ctx : "${ctx}",
	url : "waiter",
	id : "#waiterId",    //表单中主键字段对应的控件的Id属性值
	idName:"waiterId",
	tableList : "#listGrid",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	baseForm : "#baseForm",
	getId : function (){
		var householderId = $(thisUiConfig.id).val();
		return householderId;
	}
}

$(function (){
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	otherInfo.init();
});

var tabs = {
		init : function (){
			$(thisUiConfig.mainTab).tabs({
				
				//切换标签时调用
				onSelect : function(title, index) {
					if (index == 0) { // 选中列表标签
						//isEdit = false;
						$(thisUiConfig.baseForm).form("clear");
						if ($(thisUiConfig.tableList).datagrid("getSelections").length > 0) {
							$(thisUiConfig.mainTab).tabs("enableTab", 1);
							enableButtons([ "add", "del", "edit" ]);
						} else {
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
						}
					} else if (index == 1) { // 选中详细标签
						var row = $(thisUiConfig.tableList).datagrid("getSelected");
						enableButtons([ "add", "del", "edit", "save"]);
						if (row) {
							$(thisUiConfig.baseForm).form("load",row);
						}
					}
				}
			});
		}
}




function  loadData(){
	var row = $(thisUiConfig.tableList).datagrid("getSelected");
	if(row){
		$.messager.progress();
		var id = row.householderId;
		alert(id);
		if(id){
			$.ajax({
				type : "POST",
				url : baseInfo.getUrl +  "/" + id,
			}).done(function(result) {
				formObj.form("load",result.row);
				setFormItemDisabled(fid,true,null);
				$.messager.progress("close");
			});
		}
	}
}

function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	// 设置按钮可用
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).linkbutton("enable");
	}
}

//基础信息初始化
var baseInfo =  {
	saveUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/save",
	getUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/edit",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/softDel",
	search : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/search",
	init : function (){
		$(thisUiConfig.mainTab).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
			var id ="#" + $(this).attr("id");
			setFormItemDisabled(id,true,null);
		});
		
	},
	
	//保存基础数据时调用此方法
	save : function (){
		var data = getFormData(thisUiConfig.baseForm);
		if(data){
			alert(baseInfo.saveUrl);
			$.messager.progress();
			$.ajax({
				type : "POST",
				url : baseInfo.saveUrl,
				data : data
			}).done(function(result) {
				
				$(thisUiConfig.baseForm).form("load",result.row);
				
				$.messager.progress("close");
				
				$.messager.show({
					title : "温馨提示",
					msg : "操作成功",
					timeout : 2500,
					showType : "slide"
				});
				
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				alert(errorThrown+"           "+textStatus+"       "+jqXHR);
				$.messager.alert("温馨提示", "保存出错！", "error");
			});
		}
	},
	
	//点击新增基础数据时调用此方法
	add : function (){
		console.log("base add");
		//clear data
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$(thisUiConfig.tableList).datagrid("unselectAll");
		$(thisUiConfig.mainTab).tabs("enableTab", 1);
		$(thisUiConfig.mainTab).tabs("select", 1);
		setFormItemDisabled(thisUiConfig.baseForm,false,null);
		
	},
	
	del:function (){
		
		
	},
	
	edit:function (){
		var tab = $(thisUiConfig.mainTab).tabs('getSelected');
		var  index = $(thisUiConfig.mainTab).tabs('getTabIndex',tab);
		if(index!=1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);
		}
		setFormItemDisabled(thisUiConfig.baseForm,false,null);
	}
};

//other info control
var otherInfo = {
		delInfo :  thisUiConfig.ctx + thisUiConfig.url + "/delInfo " ,
		getInfoUrl : thisUiConfig.ctx + thisUiConfig.url + "/getInfo " ,
		init : function (){
			$("fieldset").find("legend").on("click",function (){
				var i = $(this).find("i");
				if(i.hasClass("fa-caret-up")){
					i.removeClass("fa-caret-up").addClass("fa-caret-down");
				}else{
					i.removeClass("fa-caret-down").addClass("fa-caret-up");
					var formObj= $(this).find("form");
					otherInfo.getInfo(formObj);//加载其他信息
				}
				$(this).next().toggle();
			});
			
			//初始化按钮
			$("div.infoBtn a").each(function (){
				if(!$(this).hasClass("edit")){
					$(this).linkbutton("disable");
				}
			});
			
			//绑定编辑按钮事件
			$("div.infoBtn a.edit").on('click',function (){
				 
				var parent = $(this).parent();
				parent.find("a.save,a.del").each(function (){
					 $(this).linkbutton("enable");
				 });
				var formObj = parent.parents("fieldset").find("form");
				var fid = "#" + formObj.attr("id");
				setFormItemDisabled(fid,false,null); //设置表达是否可用
				
				// $(this).linkbutton("disable");
			});
			 
			//绑定保存按钮事件
			$("div.infoBtn a.save").on('click',function (){
				var parent = $(this).parent();
				var formObj = parent.parents("fieldset").find("form");
				otherInfo.save(formObj);
			});
		},
		
		save : function (formObj){
			
			var fid = "#" + formObj.attr("id");
			var data = getFormData(fid);
			alert(fid);
			if(data){
				$.ajax({
					type : "POST",
					url :formObj.attr("action"),
					data : data
				}).done(function(result) {
					formObj.form("load", result.row);
				});
			}
			
		},
		
		del : function (formObj){
			
			var householderId = formObj.find("input[name='"+thisUiConfig.idName+"']").val();
			var infoName = formObj.attr("infoName");
			
			if(householderId){
				$.ajax({
					type : "POST",
					url : otherInfo.getInfoUrl,
					data : {
						'type':infoName,
						'householderId':householderId
					}
				}).done(function(result) {
					 
				});
			}else{
				$.messager.alert("温馨提示", "数据不存在", "info");
			}
			
		},
		
		getInfo : function (formObj){
			
			var householderId = thisUiConfig.getId();
			var infoName = formObj.attr("infoName");
			
			if(householderId){
				$.ajax({
					type : "POST",
					url : otherInfo.getInfoUrl,
					data : {
						'type':infoName,
						'householderId':householderId
					}
				}).done(function(result) {
					if(result){
						formObj.form("load",result);
					}
				});
			}
			
		}
}

//列表加载
var tableInfo =  {
	
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			rownumbers : true,
			singleSelect : false,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			queryParams:  getFormData(thisUiConfig.queryForm),
			url : baseInfo.search,
			columns : [ [ {
				field : "waiterId",
				hidden:true
			}, {
				field : "duiyuanName",
				title : "队员名称",
				width : 120
			},{
				field:"phone",
				title:"电话",
				width:130
			},{
				 field:"job",
				 title:"职务",
				 width:120
			},{
				field : "scope",
				title : "服务区域",
				width : 120
			},{
				field:"zaizhiStatus",
				title:"在职状态",
				width:80,
				formatter:function(value, row, index){
					if(value == 0){
						return "在职";
					}else if(value == 9){
						return "离职";
					}
				}
			}]],

			onSelect : function(rowIndex, rowData) {
				var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
				if(len==1){
					$(thisUiConfig.mainTab).tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit", "clmx" ]);
				}else if(len==0){
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
					enableButtons([ "add"]);
				}else{
					enableButtons([ "add", "del"  ]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
			},
			onUnselect : function(rowIndex, rowData) {
				var len = $(thisUiConfig.tableList).datagrid("getSelections").length;
				console.log(len);
				if(len==1){
					$(thisUiConfig.mainTab).tabs("enableTab", 1);
					enableButtons([ "add", "del", "edit", "clmx"]);
				}else if(len==0){
					enableButtons([ "add"]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}else{
					enableButtons([ "add", "del"  ]);
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
			},
			onLoadSuccess : function(data) {
				$(thisUiConfig.tableList).datagrid("unselectAll");
			}
			
		});
		
		// 设置分页显示形式
		$(thisUiConfig.tableList).datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	query : function (){
		var param = getFormData("#queryForm");
		enableButtons([ "add" ]);
		$(thisUiConfig.tableList).datagrid("load", param);
		
	}
	
}

//弹窗中生成列表的默认配置
var tableDefaultConfig = {
		rownumbers : true,
		singleSelect : false,
		autoRowHeight : false,
		border : false,
		pageSize : defaultPageSize,
		pageList : defaultPageList,
		pagination : true,	
}

var openLayer = {
		openListWindow : function(option){
			if(tableDefaultConfig.inputName){
				var layerOption = {
						  type: 1,
						  shade: false,
						  title: false, //不显示标题
						  area: ['666px', '400px']
				};
				$.extend(layerOption, layerOption, option);
				
				var finalOption = {
						  content: $('#tanchuang'), //捕获的元素
						  cancel: function(index){
							  var length = $("#tanchuangTable").datagrid("getSelections").length;
							  if(length> 1){
							      layer.msg('只能选择一条记录哟！', {time: 5000, icon:6});
							  }else if(length == 1){
							      var row = $("#tanchuangTable").datagrid("getSelected");
							      if(row){
							          $("input[name='"+ tableDefaultConfig.inputName +"']").val(row.waiterId);
							      }
							  };
						      layer.close(index);
						  }	
				};
				$.extend(layerOption, layerOption, finalOption);
				
				$("body").on("focus", "input[name='"+ tableDefaultConfig.inputName +"']", function(){
					layer.open(layerOption);
				});
				
			}
		},
		
		/*生成弹窗
		*option 设置弹窗中列表的生成规则
		*layerOption 设置弹窗的展示效果，包括弹窗大小
		*/
		createListWindow : function(option, layerOption){
			if($("#tanchuang").size() == 0){
			    $("body").append(
					"<div id='tanchuang'>"
				    +"<table id='tanchuangTable'></table>"
				    +"</div>");
			}
			tableDefaultConfig = $.extend(tableDefaultConfig, tableDefaultConfig, option);
			$("#tanchuangTable").datagrid(tableDefaultConfig);
			if(layerOption){
			    openLayer.openListWindow(layerOption);
			}
		}
}


$(document).ready(function(){
	var listOption = {
			inputName : "q_orgName_LIKE",   //控件的name值
			url : "${ctx}/waiter/search",
			columns : [ [ {
				field : "waiterId",
				hidden:true
			}, {
				field : "duiyuanName",
				title : "队员名称",
				width : 120
			},{
				field:"phone",
				title:"电话",
				width:130
			},{
				 field:"job",
				 title:"职务",
				 width:180
			}]],	
		};
	openLayer.createListWindow(listOption, {area:["666px", "400px"]});
});

</script>


</body>
</html>