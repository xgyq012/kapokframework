<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>下沉人员管理</title>
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
					<td>下沉人员名称</td>
					<td><input id="orgName_LIKE" name="" type="text" /></td>
					<td>作者</td>
					<td><input id="realname_LIKE" name="" type="text" /></td>
					<td>下沉单位</td>
					<td><input id="casenatureName_LIKE" name="" type="text" /></td>
					
					<td>
						<a href="javascript:void(0);" onclick="query();"
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
			<table title="下沉人员信息列表" id="listGrid" style="height:100%;"></table>
		</div>
     </div>
     
     <!-- data-options="disabled:true" -->
     <div  title="详细"  style="padding: 10px;height: 1000px;overflow: scroll;" >
     <fieldset style="width:95%;">
    		<legend>基础信息 </legend>
    		<form id="baseForm"  action="">
    		<input type="hidden" id="xiachenRenyuanId" name="xiachenRenyuanId">
    		<input id="createrId" name="createrId" type="hidden" />
			<input id="createTime" name="createTime" type="hidden" />
    		<table>
     			<tr>
     				<td>下沉人员id</td>
     				<td class="tdInput"><input type="text" name="duiyuanId" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>原所属组织</td>
     				<td class="tdInput"><input type="text" name="yuanZuzhi" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>原职务</td>
     				<td class="tdInput"><input type="text" name="yuanJob" class="easyui-validatebox" required="required"  /></td>
     			</tr>     			
     			<tr>
     				<td>下沉后组织名称</td>
     				<td class="tdInput"><input type="text" name="zuzhi" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>下沉后职务</td>
     				<td class="tdInput"><input type="text" name="job" class="easyui-validatebox" required="required"  /></td>
     			</tr>
     			<tr>
     				<td>下沉时间</td>
     				<td class="tdInput"><input name="xiachenTime" type="text" class="easyui-datebox"  data-options="editable:false,formatter:formatterDate"   /></td>
     			</tr>
     			
     		</table>
     		</form>
     	</fieldset>
     		
     </div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/easyui-lang-zh_CN.js"></script>
<script src="${ctx}/resources/gxwlui/dist/libs/layer/layer/layer.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >

var thisUiConfig = {
	ctx : "${ctx}",
	url : "xiachenrenyuan",
	id : "#xiachenRenyuanId",    //表单中主键字段对应的控件的Id属性值
	idName:"xiachenRenyuanId",
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
	var row = $(thisUiConfig.tableList).dataGrid("getSelected");
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

//日期格式化  
function  formatterDate (date){
	
	return date.formatDate("yyyy-MM-dd")
}
</script>

<!-- baseInfo对象的定义 -->
<script type="text/javascript">
//基础信息初始化
var baseInfo =  {
	saveUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/save",
	getUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/edit",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/softDel",
	search : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/search",
	init : function (){
		$(document).find("form").each(function (){
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

</script>

<!-- otherInfo对象的定义 -->
<script type="text/javascript">
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
</script>

<!-- tableInfo对象定义 -->
<script type="text/javascript">

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
				field : "xiachenRenyuanId",
				hidden:true
			}, {
				field : "duiyuanId",
				title : "队员名称",
				width : 120
			},{
				field:"yuanZuzhi",
				title:"原所属组织",
				width:130
			},{
				 field:"yuanJob",
				 title:"原职务",
				 width:120
			},{
				field : "zuzhi",
				title : "下沉后所属组织",
				width : 120
			},{
				field : "job",
				title : "下沉后职务",
				width : 120
			},{
				field : "xiachenTime",
				title : "下沉时间",
				width : 120
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
	}
	
}
</script>


</body>
</html>