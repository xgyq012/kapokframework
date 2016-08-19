<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>编辑预案明细</title>
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
	width:240px;
	height:18px;
	line-height: 18px;
}

table tr td{
	text-align: left;
	padding:2px;
}

.infoBtn{
  		text-align: left;
  	}

</style>


</head>
<body>
<div class="easyui-panel" style="margin:5px 0 5px 0;">
      <a onclick="baseInfo.save();" id="save" href="#" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-edit'">保存</a>
      <a onclick="baseInfo.del();" id="del"  href="#" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-remove'">删除</a>
</div>
<div id="mainTab" class="easyui-tabs" style="width:100%;height:100%;padding-bottom: 110px;">
     <!-- data-options="disabled:true" -->
     <div  title="详细"  style="padding: 10px;height: 1000px;overflow: scroll;" >
         <fieldset style="width:95%;">
    		<legend>基础信息 </legend>
    		<form id="baseForm"  action="">
    			<input id="yingjiPlanId" name="yingjiPlanId" type="hidden" />
    			<input id="planMxId" name="planMxId" type="hidden" />
    			<input id="number" name="number" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
    		    <table>
     			    <tr>
     				    <td>动作名称</td>
     				    <td><input style="width: 100%" type="text" name="actionName" class="easyui-validatebox"  data-options="required:true"  /></td>
     				</tr>
     			    <tr>
     			        <td>应急预案描述</td>
     				    <td colspan="3"><textarea rows="4" style="width: 240px" name="actionPs" class="easyui-validatebox" ></textarea></td>
     			    </tr>     				
     				<tr>    
     				    <td>选择系统用户作为执行人</td>
     				    <td><input id="sysUserIds" name="sysUserIds" type="text" /></td>
     			    </tr>
     			    <tr>    
     				    <td>选择其他人员作为执行人</td>
     				    <td><input id="qitarenIds" name="qitarenIds" type="text" /></td>
     			    </tr>
     		    </table>
     		</form>
         </fieldset>
     </div>
</div>


<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >
$(function (){
	
	
	//如果是编辑而非新增
	if(parent.$("#isEditMx").val() == "true"){
		enableButtons(['save','del']);
		var planMxId = parent.$("#planMxId").val();
		$.ajax({
			type: "post",
			url:"${ctx}/planmx/getplanmx/"+ planMxId
		}).done(function(result){
			$(thisUiConfig.baseForm).form("load",result.row);
		});
	}else{
		enableButtons(['save']);
		$("#yingjiPlanId").val(parent.$("#yingjiPlanId").val());
	}
});

function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	// 设置按钮可用
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).linkbutton("enable");
	}
}

var thisUiConfig = {
		ctx : "${ctx}",
		url : "planmx",
		id : "#planMxId",    //表单中主键字段对应的控件的Id属性值
		baseForm : "#baseForm",
		getId : function (){
			var yingjiShijianId = $(thisUiConfig.id).val();
			return yingjiShijianId;
		}
	};
//基础信息初始化
var baseInfo =  {
	saveUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/save",
	delUrl : thisUiConfig.ctx +"/"+ thisUiConfig.url + "/del",	
	save : function (){

		if($(thisUiConfig.baseForm).form("validate")){
			var data = getFormData(thisUiConfig.baseForm);
			$.messager.progress();
			$.ajax({
				type : "POST",
				url : baseInfo.saveUrl,
				data : data
			}).done(function(result) {
				$(thisUiConfig.baseForm).form("load",result.row);
				$.messager.progress("close");
				enableButtons(['save','del']);
				
				//刷新父窗口
				parent.$("#isEditMx").trigger("click");
				parent.$.messager.show({
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
	
	del:function (){
		    $.messager.confirm("温馨提示", "确定删除当前的预案明细信息?",
			function(r) {
				if (r) {
					var ids = $("#planMxId").val();
					$.ajax({
							url : baseInfo.delUrl,
							type: "POST",
							data : {
								ids : ids
							}
					}).done(function(data) {
						$.messager.progress("close");
						if (data.resultCode == "0") {
							parent.$("#isEditMx").trigger("click");
							parent.$.messager.show({
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
		
	}
};
var orgLookup;
function orgLookup() {
	orgLookup = $("#yjsjlxName").lookup({
		title : "选择应急事件类型",
		url : thisUiConfig.ctx + "/yjsjlx/layerlist?id=yjsjlxId&name=yjsjlxName",
		width : 650,
		height : 440,
		required : true,
		params : {
			singleSelect : true,
			checkbox : false
		},
		actions : {
			confirm : function(data) {
				orgLookup.lookup("close");
			},
			cancel : function() {
				$("#yjsjlxId").val("");
				$("#yjsjlxName").val("");
				orgLookup.lookup("close");
			}
		}
	});
}

</script>


</body>
</html>