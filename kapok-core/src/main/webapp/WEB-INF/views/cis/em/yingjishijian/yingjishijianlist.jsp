<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>避难场所信息</title>
	
    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/page.css">
</head>
<body>
<div class="g-layout">
	<div class="g-toolbar">
	      <a id="add" onclick="baseInfo.add();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
	      <a id="edit" onclick="baseInfo.edit();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
	      <a id="save" onclick="baseInfo.save();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
	      <a id="del" onclick="baseInfo.del();" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
	      <a onclick="baseInfo.submit();" id="submit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-level-up"></i>提交</a>
	      <a onclick="baseInfo.hecha();" id="hecha" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>核查</a>
	      <a onclick="baseInfo.shencha();" id="shencha" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>审查</a>
	</div>

	<div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
      <div title="列表" style="position:relative;">
	     <div class="query-area">
		    <form id="queryForm" method="post">
			  <table class="g-form" cellpadding="0" cellspacing="0">
			     <tbody>
					<tr>							
						 <td class="form-cell-1">
						     <label class="form-label">应急事件名称</label>
						     <input name="q_name_LIKE"   class="form-control"/>
						 </td>
	     			     <td class="form-cell-1">
	     				    <label class="form-label">事件状态</label>
	     				    <input id="shijianStatus_EQ" name="q_shijianStatus_EQ" class="easyui-combobox form-control"
		     						style="width:${comboboxWidth};height:${comboboxHeight}px">	     				   
		     			 </td>
						 <td class="form-cell-1">
						     <label class="form-label">事件类型</label>
						     <input id="q_yjsjlxId_EQ" name="q_yjsjlx.yjsjlxId_EQ" type="hidden"/>
						     <input id="queryFullName"   class="form-control"/>
						 </td>							 					
						 <td class="form-cell-1 f-button">
							<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
							<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
						 </td>
					</tr>
				 </tbody>
			  </table>
		    </form>
	     </div>
         <div class="list-area" style="top:50px;">
			<table id="listGrid" style="height:100%;"></table>
		 </div>
       </div>
	     
	     <!-- data-options="disabled:true" -->
	  <div id="minute" title="详细" data-options="disabled:true">
	     <form id="baseForm"  action=""> 
	     	<fieldset>
	    	    <legend>基础信息 </legend>
    			<input id="yingjiShijianId" name="yingjiShijianId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				<input id="yjsjlxId" name="yjsjlxId" type="hidden" />
				<input id="lururenId" name="lururenId" type="hidden" />
	    		<table class="g-form" cellpadding="0" cellspacing="0">
	     			<tr>
	     			    <td class="form-cell-2" colspan="2">
		     				   <label class="form-label">应急事件名称</label>
		     				   <input type="text" name="name" class="easyui-validatebox form-control"  data-options="required:true" >
		     			</td>
	     			    <td class="form-cell-1">
	     				   <label class="form-label">消息来源</label>
	     				   <input id="laiyuan" name="laiyuan" class="easyui-combobox form-control"
		     						style="width:${comboboxWidth};height:${comboboxHeight}px">
		     			</td>	
	     			    <td class="form-cell-1">
	     				   <label class="form-label">事件类型</label>
	     				   <input id="yjsjlxName" name="fullName" disabled="disabled" class="easyui-validatebox form-control">
		     			</td>		     				     			
	     			</tr>
	     		    <tr>
	     			    <td class="form-cell-3" colspan="3">
	     				   <label class="form-label">事件地点</label>
	     				   <input type="text" name="address" class="easyui-validatebox form-control"  data-options="required:true" >
		     			</td>	   
	     			    <td class="form-cell-1">
	     				   <label class="form-label">事件状态</label>
	     				   <input id="shijianStatus" name="shijianStatus" class="easyui-combobox form-control"
		     						style="width:${comboboxWidth};height:${comboboxHeight}px">	     				   
		     			</td>		     			  		    
	     			</tr>
	     			<tr>
		     			
	     			    <td class="form-cell-1">
	     				   <label class="form-label">来源人</label>
	     				   <input id="shangbaoren" name="shangbaoren" class="easyui-validatebox form-control" >
		     			</td>  
	     			    <td class="form-cell-1">
	     				   <label class="form-label">联系电话</label>
	     				   <input id="shangbaorenPhone" name="shangbaorenPhone" class="easyui-validatebox form-control" >
		     			</td>
	     			    <td class="form-cell-1">
	     				   <label class="form-label">来源时间</label>
	     				   <input name="shangbaoTime" class="easyui-datetimebox form-control" 
	     				       	data-options="formatter: formatterDate" style="width:${comboboxWidth};height:${comboboxHeight}px">
		     			</td>
	     			    <td class="form-cell-1">
	     				   <label class="form-label">经办人</label>
	     				   <input id="lururenName" name="lururenName" class="easyui-validatebox form-control">
		     			</td>		     			
	     			</tr>   
	     			  			
	     			<tr>
	     			    <td class="form-cell-4" colspan="4">
	     				   <label class="form-label">事件描述</label>
	     				   <textarea rows="4" name="shijianPs" class="easyui-validatebox form-control" ></textarea>
		     			</td>	     			
	     			</tr>
	     		 </table>
	      		     		 
	     		 <div style="height:350px;margin:15px 15px 10px 15px">
    				 <div id="buttonbar" class="infoBtn fujianBut" style="padding: 4px 0;">
					     <a id="upfile" onclick="fileTable.fileupload();" class="easyui-linkbutton add g-button"><i class="fa fa-upload"></i>上传</a>
					     <a onclick="fileTable.del();" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
					     <div id="addFile"></div>
		     	  	 </div>
		    		 <table id="dg_cisEmYjsjDocs" class="easyui-datagrid" style="height:100%;" title="附件列表"></table>
				 </div>
			</fieldset>
      	</form>
	     <form id="hechaInfo">
		      <fieldset>
		    	 <legend>核查信息</legend>
		    	 <div class="otherinfo">
	    			<input name="shijianHechaId" type="hidden" />
					<input name="createrId" type="hidden" />
					<input name="createTime" type="hidden" />
					<input name="hecharenId" type="hidden" />
		    		<table class="g-form" cellpadding="0" cellspacing="0">
		     			<tr>
		     			    <td class="form-cell-2" colspan="2">
		     				   <label class="form-label">核查人</label>
		     				   <input  name="hecharenName" disabled="disabled" class="easyui-validatebox form-control"  
		     				       data-options="required:true">
			     			</td>
			     			<td class="form-cell-1">
		     				   <label class="form-label">是否已上报</label>
		     				   <input id="shifouShangbao" name="shifouShangbao" class="easyui-combobox form-control"
			     						style="width:${comboboxWidth};height:${comboboxHeight}px">	     				   
			     			</td>
		     			    <td class="form-cell-1">
		     				   <label class="form-label">核查时间</label>
		     				   <input type="text" name="hechaTime" disabled="disabled" data-options="required:true" class="easyui-datetimebox form-control" 
		     				   		data-options="formatter: formatterDate" style="width:${comboboxWidth};height:${comboboxHeight}px">
			     			</td>			     					     			
		     			</tr>  
		     			<tr>
		     			    <td class="form-cell-4" colspan="4">
		     				   <label class="form-label">核查意见</label>
		     				   <textarea rows="4" name="hechaYijian" class="easyui-validatebox form-control" ></textarea>
			     			</td>		     			
		     			</tr>   			     			    			 			
		     		</table>
		     	 </div>
		     </fieldset>	
		  </form>
		  
	      <fieldset>
	    	 <legend>审查信息</legend>
	    	 <div  class="otherinfo">
	    		<form id="shenchaInfo" action="">
	    			<input name="shijianShenchaId" type="hidden" />
					<input type="hidden" />
					<input name="createTime" type="hidden" />
					<input name="shencharenId" type="hidden" />
		    		<table class="g-form" cellpadding="0" cellspacing="0">
		     			<tr>
		     			    <td class="form-cell-1">
		     				   <label class="form-label">审查人</label>
		     				   <input type="text" name="shencharenName" class="easyui-validatebox form-control">
			     			</td>	     			
		     			    <td class="form-cell-1">
		     				   <label class="form-label">是否已启动应急</label>
		     				   <input id="yingjiStatus" name="yingjiStatus" class="easyui-combobox form-control"
				     						style="width:${comboboxWidth};height:${comboboxHeight}px">	
			     			</td>
		     			    <td class="form-cell-1">
		     				   <label class="form-label">预案名称</label>
		     				   <input type="text" name="yingjiPlanName" class="easyui-validatebox form-control">
			     			</td>
		     			    <td class="form-cell-1">
		     				   <label class="form-label">审查时间</label>
		     				   <input type="text" name="shenchaTime" disabled="disabled" data-options="required:true" class="easyui-datetimebox form-control" 
		     				   		data-options="formatter: formatterDate" style="width:${comboboxWidth};height:${comboboxHeight}px">
			     			</td>		     						     			
		     			</tr>  
		     			<tr>
		     			    <td class="form-cell-4" colspan="4">
		     			    	<label class="form-label">审查意见</label>
		     			    	<textarea rows="4" name="shenchaYijian" class="easyui-validatebox form-control" ></textarea>
		     			    </td>
		     			</tr>   			     			    			 			
		     		</table>
	     		</form>
	     	 </div>
	     </fieldset>	
	     
		 <div style="height:350px;margin:15px 15px 5px 15px">
			<div class="infoBtn genjinBut">
				<a href="#" onclick="genjinTable.add();" class="easyui-linkbutton add g-button" ><i class="fa fa-plus"></i>新增</a>
				<a href="#" onclick="genjinTable.del();" class="easyui-linkbutton del g-button" ><i class="fa fa-trash-o"></i>删除</a>
		    </div>
			<table title="事件跟进明细" id="genjinList" style="height:100%;"></table>
	     </div>
	  </div>
    </div>	
</div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>


<script type="text/javascript" >
var uploadBtn;
var addNewRecord = false; //新增记录标记为true；
var editRecord = false;   //编辑记录时标记为true

var editRow = undefined; //附件表被编辑的行的下标
var thisUiConfig = {
	ctx : "${ctx}",
	url : "yingjishijian",
	id : "#yingjiShijianId",    //表单中主键字段对应的控件的Id属性值
	idName:"yingjiShijianId",
	tableList : "#listGrid",
	searchUrl : "search",
	queryForm : "#queryForm",
	mainTab : "#mainTab",
	getId : function (){
		var yingjiShijianId = $(thisUiConfig.id).val();
		return yingjiShijianId;
	}
};



$(function (){
	importDoc.init();	
	tabs.init();
	enableButtons(['add']);
	tableInfo.init();
	baseInfo.init();
	otherInfo.init();
	initCombobox();
	initSelectUserLookup();
	orgLookup();
});

var tabs = {
		thisTabIndex : 0,
		init : function (){
			$(thisUiConfig.mainTab).tabs({
				onSelect : function(title, index) {
					if (index == 0) { // 选中列表标签
						tabs.thisTabIndex = index;
						editRecord = false;
						setFormItemDisabled("#baseForm", true, null);
						
					    //XXX 清空已经填写的信息？
						$("#baseForm").form("clear");
					    
						if ($(thisUiConfig.tableList).datagrid("getSelections").length > 0) {
							$(thisUiConfig.mainTab).tabs("enableTab", 1);
						} else {
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
						}
						if (tableInfo.thisRow) {
							setButtonStatus(tableInfo.thisRow);
						}
						// 从新选中被操作的行 以此触发选中事件从而达到设置按钮状态的效果
						$(thisUiConfig.tableList).datagrid("selectRow", tableInfo.thisRowIndex);
						
					} else if (index == 1) { // 选中详细标签
						tabs.thisTabIndex = index;
						tabs.initMinxi();
						
						//设置页面按钮的状态
						setButtonStatus(tableInfo.thisRow);
					}
				}
			});
		},
		
		//初始化详情页信息
		initMinxi : function(){
			var row = tableInfo.thisRow;
			if (row) {
				$("#baseForm").form("load", row);
				$("#hechaInfo").form("load",  row);
				$("#shenchaInfo").form("load",  row);
				$("#lururenName").lookup("setTxt", row.lururenName);
				$("#genjinList").datagrid("loadData", row.cisEmYjsjClmxs);
				$("#dg_cisEmYjsjDocs").datagrid("loadData", row.cisEmYjsjDocs);
			}
		}
};

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
	getUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/get",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/del",
	search : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/search ",
	init : function (){
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
			var id ="#"+$(this).attr("id");
			setFormItemDisabled(id,true,null);
		});
	},
	
	//保存基础数据时调用此方法
	save : function (data){
		if(! data){
			if(! $("#baseForm").form("validate")){
				return false;
			}
	        $("#dg_cisEmYjsjDocs").datagrid("acceptChanges");
			$("#dg_cisEmYjsjDocs").datagrid("unselectAll");
		    data = getFormData("#baseForm");
		    console.log(data);
		}
		if(data){
			$.messager.progress();
			$.ajax({
				type : "POST",
				url : baseInfo.saveUrl,
				data : data
			}).done(function(result) {
				//$("#baseForm").form("load",result.row);
				tableInfo.refresh();
				$.messager.progress("close");
				$.messager.show({
					title : "温馨提示",
					msg : "操作成功",
					timeout : 2500,
					showType : "slide"
				});
				
				//关闭弹窗
				main.closeDialog();
			}).fail(function(jqXHR, textStatus, errorThrown) {
				alert(textStatus+"            "+errorThrown);
				$.messager.progress("close");
				$.messager.alert("温馨提示", "保存出错！", "error");
			});
		}
	},
	
	//点击新增基础数据时调用此方法
	add : function (){
		addNewRecord = true;
		editRecord = true;
		
		$(document).find("form").each(function (){
			$(this).form("enableValidation");
			$(this).form("clear");
		});
		$("#genjinList").datagrid("loadData",{ total: 0, rows: [] });
		$("#dg_cisEmYjsjDocs").datagrid("loadData", { total: 0, rows: [] });
		
		$("#listGrid").datagrid("unselectAll");
		tableInfo.thisRow = "";
		tableInfo.thisRowIndex = -1;
		
		enableButtons(["add", "save"]);
		if(tabs.thisTabIndex != 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);
		}else{
			tabs.initMinxi();
		}
		$("input[name='shijianStatus']").val(0);
		$("#lururenName").lookup("setTxt", userName);
		$("input[name='lururenName']").val(userName);
		$("#lururenId").val(userId);
		$("#shangbaoTime").datetimebox({disabled: false});
		setFormItemDisabled("#baseForm", false, "#shijianStatus,#yjsjlxName");
	},
	
	del:function (){
		var row = tableInfo.thisRow;
		if (! row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		$.messager.confirm("温馨提示", "确定删除选中【"+ row.name +"】的应急事件信息?",
			function(r) {
				if (r) {
					$.ajax({
							url : baseInfo.delUrl +"/"+ row.yingjiShijianId,
							type: "POST"
					}).done(function(data) {
						$.messager.progress("close");
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$(thisUiConfig.mainTab).tabs("select", 0);
							$(thisUiConfig.mainTab).tabs("disableTab", 1);
							enableButtons([ "add" ]);
									
							tableInfo.thisRow = "";
							tableInfo.thisRowIndex = -1;
							
							// 重新获取数据
							tableInfo.refresh();
							$.messager.show({
								title : "温馨提示",
								msg : "操作成功",
								timeout : 2500,
								showType : "slide"
							});
						} else {
							$.messager.show({
								title : "温馨提示",
								msg : data.resultMsg,
								timeout : 2500,
								showType : "slide"
							});
						}
						}).fail(function() {
							$.messager.progress("close");
							$.messager.alert("温馨提示", "删除出错！", "error");
						});
				 }
			});
		
	},
	
	edit:function (){
 		editRecord = true;
		if(tabs.thisTabIndex != 1){  //不在详情页时，切换到详情页标签
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			$(thisUiConfig.mainTab).tabs("select", 1);
		}else{
			setButtonStatus(tableInfo.thisRow);
		}
		$("#shangbaoTime").datetimebox({disabled: false});
		setFormItemDisabled("#baseForm",false, "#shijianStatus,#yjsjlxName"); 
	},
	submit:function(){
		var row = tableInfo.thisRow;
		$("#baseForm").form("load", row);
		
		$.messager.confirm("温馨提示！", "提交后将不能继续修改！确定要提交【"+ row.name +"】?", function(r){
			  if(r){
				  $("input[name='shijianStatus']").val(1);
				  editRecord = false;
				  addNewRecord = false;
				  baseInfo.save(getFormData($("#baseForm")));
				  setFormItemDisabled("#baseForm", true, null);
			  }
		  });
	},
	hecha: function(){
		openHechaPage();
	},
	shencha: function(){
		openShenchaPage();
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
				}
				$(this).next().toggle();
			});
			
			$("fieldset legend i").removeClass("fa-caret-up").addClass("fa-caret-down");
			
			//展开上传文件的信息列表
			$("#loadFileButton").removeClass("fa-caret-up").addClass("fa-caret-down");
			
			//展开上传文件的信息列表
			$("#loadGenjin").removeClass("fa-caret-up").addClass("fa-caret-down");
			
			fileTable.initFileList(0);
			genjinTable.init(0);
			
			$("div.infoBtn a").linkbutton("disable");
			
		},
		
		save : function (formObj){
			var fid = "#" + formObj.attr("id");
			var data = getFormData(fid);
			
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
			var url = formObj.attr("url");
			if(householderId){
				$.ajax({
					type : "POST",
					url : "${ctx}/yingjishijian"+ url +""+ householderId,
				}).done(function(result) {
					if(result){
						formObj.form("load", result.row);
						
						//为弹窗控件填值
						var formObj2 = $("#"+ formObj.attr("id") + "2");
						if(formObj2){
							formObj2.form("load", result.row);
						}
					}
				});
			}
		},
		
		upload :function(){
			
		}
		
};

//列表加载
var tableInfo =  {
	thisRow : "",
	thisRowIndex : 0,
	
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			queryParams:  getFormData(thisUiConfig.queryForm),
			url : baseInfo.search,
			columns : [ [ {
				field : "yingjiShijianId",
				hidden:true
			},{
				field : "name",
				title : "应急事件名称",
				halign:'center',
				align:'left',
				width : 300
			}, {
				 field:"fullName",
				 title:"事件类型",
				 halign:'center',
				 align:'left',
				 width:220,
			},{
				field:"address",
				halign:'center',
				align:'left',
				title:"事发地",
				width:300
			},{
				field:"shifouShangbao",
				title:"是否上报",
				halign:'center',
				align:'left',
				width:80,
				formatter:function(value, row, index){
					if(value == "N"){
						return "未上报";
					}else if(value == "Y"){
						return "已上报";
					}
				}
			},{
				 field:"yingjiStatus",
				 title:"是否启动应急",
				 halign:'center',
				 align:'left',
				 width:80,
				 formatter:function(value, row, index){
					 if(value == "N"){
						 return "未启动";
					 }else if(value == "Y"){
						 return "已启动";
					 }
				 }
			},{
				 field:"shijianStatus",
				 title:"事件处理状态",
				 halign:'center',
				 align:'left',
				 width:80,
				 formatter:function(value, row, index){
					 if(value == 0){
						 return "草稿";
					 }else if(value == 1){
						 return "已提交";
					 }else if(value == 2){
						 return "已核查";
					 }else if(value == 3){
						 return "已审查";
					 }else if(value == 4){
						 return "正在跟进";
					 }else if(value == 9){
						 return "已处理";
					 }
				 }
			}]],

			onSelect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			onUnselect : function(rowIndex, rowData) {
				tableInfo.onSelectOrUnselect(rowIndex, rowData);
			},
			
			//列表数据加载成功后调用
			onLoadSuccess : function(data) {
				//取消所有选中的行
				$("#listGrid").datagrid("unselectAll");
				
				//选中重新加载列表内容前所操作的记录
				if(tableInfo.thisRow){
					$(thisUiConfig.tableList).datagrid("selectRow", tableInfo.thisRowIndex);
					//tabs.initMinxi();
				}
				
				//新增记录时
				if(addNewRecord){
					//将是否为新增记录标记设为false
					addNewRecord = false;
					
					//选中列表中第一行记录
					$(thisUiConfig.tableList).datagrid("selectRow", 0);
				}
				
				if(! tableInfo.thisRow && tabs.thisTabIndex != 1){
					$(thisUiConfig.mainTab).tabs("disableTab", 1);
				}
				
				//调用需要做特殊处理的
				tableInfo.onLoadSuccessPlus();
			}
			
		});
		
		// 设置分页显示形式
		$("#listGrid").datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	query : function (){
		tableInfo.thisRow = "";
		tableInfo.thisRowIndex = -1;
		var param = getFormData("#queryForm");
		enableButtons([ "add" ]);
		$(thisUiConfig.tableList).datagrid("load", param);
		
	},
	refresh: function(){
		var param = getFormData("#queryForm");
		enableButtons([ "add" ]);
		$(thisUiConfig.tableList).datagrid("load", param);
	},
	
	//选中或取消一条记录时调用该方法
	onSelectOrUnselect : function(rowIndex, rowData){
		var len = $("#listGrid").datagrid("getSelections").length;
		//选中一行记录时
		if(len == 1){
			$(thisUiConfig.mainTab).tabs("enableTab", 1);
			tableInfo.thisRow = rowData;
			tableInfo.thisRowIndex = rowIndex;
			
			//当前已经在详情页时  重新加载最新行数据
			if(tabs.thisTabIndex == 1){
				tabs.initMinxi();
			}
			
			//设置主按钮的的状态
			setButtonStatus(tableInfo.thisRow);
			setOtherButtonStatus();
		}else if(len == 0){
			enableButtons([ "add"]);
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
		}else{
			enableButtons([ "add", "del"  ]);
			$(thisUiConfig.mainTab).tabs("disableTab", 1);
		}
	},
	//列表加载成功后执行 如果在列表加载成功后需要做一些特殊的动作 则在加载列表数据前重写该函数， 即：
    //tableInfo.onLoadSuccessPlus = function(){}　　　　　　　　
	//默认没有动作
	onLoadSuccessPlus : function(){}
};

//日期格式化  
function  formatterDate (date){
	return date.formatDate("yyyy-MM-dd hh:mm:ss");
}


var importDoc = {
	foromData : "",  //保存
	init : function (){
 		uploadBtn = $("#addFile").fileupload({
			url : thisUiConfig.ctx + "/doc/upload",
			upfile : "upfile",
			filetype : "mp3|mp4|mkv|jpg|jpeg|png",
			hidden : true,
			params : {
				directory : "photos"
			},
			onUploadComplete : function(result) {
				var row = {};
				row.docId = result.docId;
				row.docName = result.docShowname;
				row.createTime = new Date().formatDate("yyyy-MM-dd hh:mm:ss");
				row.downLoad = "";
				$("#dg_cisEmYjsjDocs").datagrid("insertRow", {index : 0, row: row});
			}
		});
	}
};

//应急事件附件列表
var fileTable = {
		editRow: false,
		editRowIndex : -1,
		selectRowIndex : 0,
		initFileList : function(){
			$("#dg_cisEmYjsjDocs").datagrid({
				rownumbers : true,
				singleSelect : true,
				autoRowHeight : false,
				border : true,
				toolbar:'#buttonbar',
				pagination:false,
				columns : [ [
				{
					field : "yjsjDocId",
					title : "主键",
					hidden: true
				},{
					field : "docId",
					title : "附件id",
					hidden: true
				},{
					field : "yingjiShijianId",
					title : "应急事件id",
					hidden: true
				},{
					field : "createrId",
					title : "上传人",
					hidden: true
				},{
					field : "docName",
					title : "文档名称",
					halign:'center',
					align:'left',
					width : 280
				},{
					field : "remark",
					title : "文档说明",
					halign:'center',
					align:'left',
					width : 380,
					editor:{
						type: 'text',
					}
				},{
					field:"createTime",
					halign:'center',
					align:'left',
					title:"上传时间",
					width:180
				},{
					field:"downLoad",
					halign:'center',
					align:'left',
					title:"下载附件",
					formatter:function(value, row, index){
						if(row.yjsjDocId){
							if(row.docId){
								return "<a href='#' onclick='downDoc("+ row.docId +");'>下载附件</a>";
							}else{
								return "未上传附件";
							}
						}else{
							return "保存后查看";
						}
					},
					width:80
				}]],

				onSelect : function(rowIndex, rowData) {
					fileTable.selectRowIndex = rowIndex;
					fileTable.setButtonStatus();
				},
				onUnselect : function(rowIndex, rowData) {
					fileTable.selectRowIndex = rowIndex;
					fileTable.setButtonStatus();
				},
				onLoadSuccess : function(data) {
					fileTable.editRow = false;
					fileTable.editRowIndex = -1;
					fileTable.selectRowIndex = -1;
					$("#dg_cisEmYjsjDocs").datagrid("unselectAll");
				},
				onDblClickRow :function(rowIndex, rowData){
					if(editRecord == false){
						return;
					}
					fileTable.editRowIndex = rowIndex;
					fileTable.editRow = true;
					$("#dg_cisEmYjsjDocs").datagrid("acceptChanges");
					$("#dg_cisEmYjsjDocs").datagrid("unselectAll");
					$("#dg_cisEmYjsjDocs").datagrid("selectRow", rowIndex).datagrid("beginEdit", rowIndex);
				}				
			});
		},
		setButtonStatus : function(){
			$("div.fujianBut a").linkbutton("disable");
			if(addNewRecord || editRecord){
				$("div.fujianBut a.add").linkbutton("enable");
				if(fileTable.selectRowIndex != -1){
					$("div.fujianBut a.del").linkbutton("enable");
				}
			}
		},
		del : function(){
			$("#dg_cisEmYjsjDocs").datagrid("deleteRow", fileTable.selectRowIndex);
		},
		fileupload : function(){
			importDoc.foromData = getFormData("#baseForm");
			uploadBtn.fileupload('click');
		}
}


//当前登入的用户的id
var userId = <shiro:principal property="userId"/>;
var userName = "<shiro:principal/>";
function openHechaPage(){
	main.dialog({
		title : "应急事件核查",
		url : "${ctx}/yingjishijian/hecha/page",
		width : 720,
		height : 460,
		contentWindow: {
			params: {
				singleSelect: true,
				checkbox: false,
				row : tableInfo.thisRow,
			},
			actions : {
				confirm : function(data) {
					baseInfo.save(data);
				},
				cancel : function() {
					main.closeDialog();
				}
			},
		}
	});
}

function openShenchaPage(){
	main.dialog({
		title : "应急事件审查",
		url : "${ctx}/yingjishijian/shencha/page",
		width : 720,
		height : 460,
		contentWindow: {
			params: {
				singleSelect: true,
				checkbox: false,
				//將選中行的數據傳到审查弹窗中
				row : tableInfo.thisRow,
			},
			actions : {
				confirm : function(data) {
					baseInfo.save(data);
				},
				cancel : function() {
					main.closeDialog();
				}
			}
		}
	});
}

function openGenjinPage(){
	main.dialog({
		title : "应急事件跟进",
		url : "${ctx}/yingjishijian/genjin/page",
		width : 720,
		height : 460,
		contentWindow:{
			params : {
				singleSelect : true,
				checkbox : false,
				yingjiShijianId: tableInfo.thisRow.yingjiShijianId
			},
			actions : {
				confirm : function(data) {
					tableInfo.refresh();
					main.closeDialog();
				},
				cancel : function() {
					main.closeDialog();
				}
			}	
		}
	});
}

var selectUser;
function initSelectUserLookup() {
	selectUser = $("#lururenName").lookup({
		title : "选择经办人",
 		url : "${ctx}/user/selectuser",
 		width : 650,
 		height : 435,
		contentWindow: {
			params: {
				singleSelect: true,
				checkbox: false
			},
			actions: {
				confirm: function(data) {
					if (data) {
						$("input[name='lururenId']").val(data[0].userId);
						$("#lururenName").lookup("setTxt", data[0].realname);
						$("input[name='lururenName']").val(data[0].realname);
						//$("input[name='lururenName']").prev().val(data.realname);
					}
					main.closeDialog();
				},
				cancel : function() {
					$("input[name='lururenId']").val(userId);						
					$("input[name='lururenName']").val(userName);
					$("#lururenName").lookup("setTxt", userName);
					main.closeDialog();
				}
			}
		}
	});
}


var orgLookup;
function orgLookup() {
	orgLookup = $("#queryFullName").lookup({
		title : "选择应急事件类型",
		url : thisUiConfig.ctx + "/yjsjlx/layerlist",
		width : 650,
		height : 440,
		contentWindow:{
			params : {
				singleSelect : true,
				checkbox : false
			},
			actions : {
				confirm : function(data) {
					$("#queryFullName").lookup("setTxt", data.fullName);
					$("#q_yjsjlxId_EQ").val(data.yjsjlxId);
					main.closeDialog();
				},
				cancel : function() {
					$("#q_yjsjlxId_EQ").val("");
					$("#yjsjlxName").lookup("setTxt", "");
					main.closeDialog();
				}
			}	
		}
		
	});
}


//根据应急事件信息id 设置按钮的可用性
function setButtonStatus(row){
	enableButtons(["add"]);
	
	//编辑记录的时候 按钮设置
	if(editRecord){
		enableButtons([ "add", "del", "save"]);
		
		if(addNewRecord){
			$("#del").linkbutton("disable");
		}
	}
	
	//选中的记录尚未提交
	if(row.shijianStatus == 0 && userId == row.lururenId && editRecord == false){
		enableButtons([ "add", "edit", "del", "submit"]);
		
		//当前已经在详情页  而且 处于编辑状态
		if(editRecord && tabs.thisTabIndex == 1){
			enableButtons([ "add", "save", "del", "submit"]);
		}
	}
	
	if(tabs.thisTabIndex == 1){
		if((row.shijianStatus == 1 && ! row.hechaTime)
				|| (! row.shenchaTime && row.shijianStatus == 2 && row.shifouShangbao == "Y")){
			$.ajax({
				type: 'post',
				url: '${ctx}/yingjishijian/ishechaorshencharen/'+ thisUiConfig.getId()
			}).done(function(result){
				//记录已经提交    && 记录尚未核查
				if(row.shijianStatus == 1 && ! row.hechaTime ){
					if(result){
						enableButtons([ "add", "hecha"]);
					}
				}
				
				//记录尚未审查  &&  记录已经核查   &&   选择上报
				else if(! row.shenchaTime && row.shijianStatus == 2 && row.shifouShangbao == "Y"){
					if(result){
						enableButtons([ "add", "shencha"]);					
					}
				}
			});
		}
		setOtherButtonStatus();	
	}else{
		$("div.infoBtn a").linkbutton("disable");
	}
	
	
	if(row.shijianStatus == 3 && row.shenchaTime  //记录已经审查
		|| row.shijianStatus == 2 && row.shifouShangbao == "N"){ //记录已经核查  但是没有上报
		enableButtons([ "add"]);
	}
}

function setOtherButtonStatus(){
	fileTable.setButtonStatus();
	genjinTable.setButtonStatus();
}

var genjinTable = {
		init : function(id){
			$("#genjinList").datagrid({
				rownumbers : true,
				singleSelect : false,
				autoRowHeight : false,
				pagination:false,
				border : true,
				toolbar:'.genjinBut',
				columns : [ [ {
					field : "yjsjClmxId",
					hidden:true
				}, {
					field : "title",
					title : "阶段",
					halign:'center',
					align:'left',
					width : 150
				},{
					field:"body",
					title:"事件进展",
					halign:'center',
					align:'left',
					width:280
				},{
					field:"genjinrenName",
					title:"跟进人",
					halign:'center',
					align:'left',
					width:180,
				},{
					field:"createTime",
					halign:'center',
					align:'left',
					title:"跟进时间",
					width:130
				},{
					field:"fileId",
					halign:'center',
					align:'left',
					title:"附件",
					width:200,
					formatter:function(value, row, index){
						if(value){
						    return "<a href='#' title='下载附件' onclick='downDoc("+ value +");'>"+ row.fileName +"</a>";
						}else{
							return "没有附件";
						}
					}
				}]],

				onSelect : function(rowIndex, rowData) {
					genjinTable.setButtonStatus();
				},
				onUnselect : function(rowIndex, rowData) {
					genjinTable.setButtonStatus();
				},
				onLoadSuccess : function(data) {
					$("#genjinList").datagrid("unselectAll");
				}
				
			});
		},
		setButtonStatus : function(){
			var row = tableInfo.thisRow;
			if(row){
				$("div.genjinBut a").linkbutton("disable");
				if(row.shijianStatus == 3 && row.shenchaTime && row.yingjiStatus == "Y"){
					var size = $("#genjinList").datagrid("getSelections").length;
					if(size != 0){
						$("div.genjinBut a.del").linkbutton("enable");
					}
					$("div.genjinBut a.add").linkbutton("enable");
				}		
			}else{
				$("div.genjinBut a").linkbutton("disable");
			}
		},
		add : function(){
			openGenjinPage();
		},
		del : function(){
			var row = $("#genjinList").datagrid('getSelections');
			if (!row) {
				$.messager.alert("温馨提示", "请选择删除的信息！", "info");
				return;
			}
			$.messager.confirm("温馨提示", "确定删除选中的资料信息?",
				function(r) {
					if (r) {
						var ids = "";
						$.each(row, function (i, n){
							ids = ids + n["yjsjClmxId"] + ",";
						});
						ids = ids.substring(0, ids.length - 1);
						$.ajax({
								url : "${ctx}/yingjishijian/genjin/del",
								type: "POST",
								data : {
									"ids" : ids
								}
						}).done(function(data) {
							$.messager.progress("close");
							if (data.resultCode == "0") {
								$.messager.show({
									title : "温馨提示",
									msg : "操作成功",
									timeout : 2500,
									showType : "slide"
								});
								tableInfo.refresh();
							} else {
								$.messager.show({
									title : "温馨提示",
									msg : data.resultMsg,
									timeout : 2500,
									showType : "slide"
								});
							}
							}).fail(function() {
								$.messager.progress("close");
								$.messager.alert("温馨提示", "删除出错！", "error");
							});
					 }
				});
		}
};

//下载附件
function downDoc(ids){
	var pictureId = "";
	var row = $("#listGrid").datagrid("getSelected");
	if(row == null){
		pictureId = $("#pictureId").val();
	}else{
		pictureId = row.pictureId;
	}
	console.log(pictureId);
	if (ids) {
		location.href = "${ctx}/doc/download/" + ids;
	} else {
		$.messager.show({
			title : "温馨提示",
			msg : "未上传文件",
			timeout : 2500,
			showType : "slide"
		});
	}
}

var dict = main.dict;
var dicts = dict.dicts;
var comboboxConfig = {
	valueField: 'dictCode',
    textField: 'dictName',
    editable: false,
	panelHeight: 'auto',
	disabled : true
};
function initCombobox(){
	comboboxConfig.data = dicts.YesOrNo.list;
	$('#shifouShangbao').combobox(comboboxConfig);
	$('#yingjiStatus').combobox(comboboxConfig);
	
	comboboxConfig.data = dicts.shijianStatus.list;
	$('#shijianStatus').combobox(comboboxConfig);
	comboboxConfig.disabled = false;
	$('#shijianStatus_EQ').combobox(comboboxConfig);
	
	comboboxConfig.data = dicts.shijianLaiyuan.list;
	comboboxConfig.required = true;
	$('#laiyuan').combobox(comboboxConfig);
}

//编辑
function edit(){
	 var editRow = undefined; 
	 //修改时要获取选择到的行
    var rows = $("#dg_cisEmYjsjDocs").datagrid("getSelections");
    //如果只选择了一行则可以进行修改，否则不操作
    if (rows.length == 1) {
        //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
        if (editRow != undefined) {
     	   $("#dg_cisEmYjsjDocs").datagrid("endEdit", editRow);
        }
        //当无编辑行时
        if (editRow == undefined) {
            //获取到当前选择行的下标
            var index = $("#dg_cisEmYjsjDocs").datagrid("getRowIndex", rows[0]);
            //开启编辑
            $("#dg_cisEmYjsjDocs").datagrid("beginEdit", index);
            //把当前开启编辑的行赋值给全局变量editRow
            editRow = index;
            //当开启了当前选择行的编辑状态之后，
            //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
            $("#dg_cisEmYjsjDocs").datagrid("unselectAll");
        }
    }
}

//保存
function save(){
	//保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
    $("#dg_cisEmYjsjDocs").datagrid("endEdit", editRow);
    
	  var row = $("#dg_cisEmYjsjDocs").datagrid("getSelected");
	  var index = $("#dg_cisEmYjsjDocs").datagrid("getRowIndex", row);
	  console.log(index);
	  
	  if(index != -1){
		  var saveUrl = "${ctx}yingjishijian/doc/save";
	       
	      $("#dg_cisEmYjsjDocs").datagrid('endEdit', index);
	      var row = $("#dg_cisEmYjsjDocs").datagrid("getRows")[index];
	      $.ajax({
	          url      : saveUrl, 
	          data     : row, 
	          type     : 'post', 
	          dataType : 'json', 
	          success  : function(data) {
	             $.messager.alert("提示" , "保存成功" , "info");
	          },
	          error:function(data){
	             $.messager.alert("提示" , "保存失败" , "error");
	          }
	      });
	  }
    
}
</script>


</body>
</html>