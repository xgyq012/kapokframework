<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>应急事件跟进弹窗</title>
	
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
    <!-- 事件核查弹窗 -->
    <div id="mainTab" class="easyui-panel" style="width:100%;height:100%; padding: 5px">
		<form id="hechaInfo"  action="">
		    <input id="yingjiShijianId" name="yingjiShijianId" type="hidden" />
			<input id="createrId" name="createrId" type="hidden" />
			<input id="createTime" name="createTime" type="hidden" />
			<input id="yjsjlxId" name="yjsjlxId" type="hidden" />
			<input id="yjsjlxName" name="yjsjlxName" type="hidden" />
			<input id="name" name="name" type="hidden" />
			<input id="address" name="address" type="hidden" />
			<input id="laiyuan" name="laiyuan" type="hidden" />
			<input id="jieduan" name="jieduan" type="hidden" />
			<input id="shijianStatus" name="shijianStatus" type="hidden" />
			<input id="shangbaoren" name="shangbaoren" type="hidden" />
			<input id="shangbaorenPhone" name="shangbaorenPhone" type="hidden" />
			<input id="shangbaoTime" name="shangbaoTime" type="hidden" />
			<input id="createTime" name="createTime" type="hidden" />
			<input id="shijianPs" name="shijianPs" type="hidden" />
			<input id="hecharenId" name="hecharenId" type="hidden" value="<shiro:principal property='userId'/>"/>
			<input id="hecharenName" name="hecharenName" type="hidden" value="<shiro:principal/>"/>
			<input id="hechaTime" name="hechaTime" type="hidden" />
			<input id="shencharenId" name="shencharenId" type="hidden" />
			<input id="shencharenName" name="shencharenName" type="hidden" />
			<input id="lururenId" name="lururenId" type="hidden" />
			<input id="lururenName" name="lururenName" type="hidden" />			
    		<table>
	     		<tr>
     			    <td class="form-cell-4" colspan="4">
     				   <label class="form-label">是否上报</label>
     				   <input id="shifouShangbao" name="shifouShangbao" class="easyui-combobox form-control" data-options="required:true"
		     						style="width:150px; height:${comboboxHeight}px">
	     			</td>		     					
     			</tr>
<!-- 	     		<tr>
     			    <td class="form-cell-4" colspan="4">
     				   <label class="form-label">选定审查人</label>
	     				   <input  type="text" id="shencharen" class="form-control" style="width: 150px"  data-options="required:true" >
	     			</td>		     					
     			</tr>   -->   				     		
     			<tr>
     			    <td class="form-cell-4" colspan="4">
	     				   <label class="form-label">核查意见</label>
	     				   <textarea  name="hechaYijian" class="easyui-validatebox form-control" style="width: 550px; height: 313px;"></textarea>
	     			</td>      			
     			</tr>   			     			    			 			
     		</table>
     	</form>	
     	<div id="toolbar" style="text-align: right; padding-right: 60px; ">
		    <a id="confirm" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="save();" ><i class="fa fa-check"></i>确定</a>
		    <a id="cancel" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-times"></i>取消</a>
	    </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >
var selectUser;
function selectUser2() {
	selectUser = $("#shencharen").lookup({
		title : "选择审查人",
		url : "${ctx}/user/showuserlist?id=shencharenId",
		width : 650,
		height : 400,
		innerDialog: true,
		contentWindow: {
			params: {
				singleSelect: true,
				checkbox: false
			},
			actions: {
				confirm: function(data) {
					if (data) {
						$("#shencharenId").val(data.userId);
						//$("#shencharenName").val(data.realname);
						$("#shencharen").lookup("setTxt", data.realname);
					}
					main.closeInnerDialog();
				},
				cancel : function() {
					$("#shencharenId").val("");
					//$("#shencharenName").val("");
					$("#shencharen").lookup("setTxt", "");
					main.closeInnerDialog();
				}
			}
		},
		
		onBeforeOpen : function (){
			//没有选择是否上报之前不能选择审查人
			if(! $("input[name='shifouShangbao']").val()){
				return false;
			}
			if($("input[name='shifouShangbao']").val() == "N"){
				main.info("温馨提示","不上报该事件时，无需选择审查人！",null);
				return false;
			}else{
				return true;
			}
		}
	});
}

$(function(){
	$("#hechaInfo").form("load", window.params.row);
	selectUser2();
	initCombobox();
});
//日期格式化  
function  formatterDate (date){
	return date.formatDate("yyyy-MM-dd hh:mm:ss");
}

//当前登入的用户的id
var userId = <shiro:principal property="userId"/>;
var userName = "<shiro:principal/>";
function save(data){
 	/* if($("input[name='shifouShangbao']").val() == "Y"){
 		if(! $("#shencharenId").val()){
 			main.info("温馨提示","请选择审查人！",null);
 			return false;
 		}
 		
 		//暂时指定审查人为超级用户
 		//$("#shencharenId").val(1);
 		//$("#shencharenName").val("超级用户");
	} */
	if($("#hechaInfo").form("validate")){
		$("input[name='shijianStatus']").val(2);
		$("#hecharenName").val(userName);
		$("input[name='hechaTime']").val(new Date().formatDate("yyyy-MM-dd hh:mm:ss"));
		var data = getFormData("#hechaInfo");
		window.actions.confirm(data);
	} 
	
}

var dict = main.dict;
var dicts = dict.dicts;
var comboboxConfig = {
	valueField: 'dictCode',
    textField: 'dictName',
    editable: false,
	panelHeight: 'auto',
};
function initCombobox(){
	comboboxConfig.data = dicts.YesOrNo.list;
	$('#shifouShangbao').combobox(comboboxConfig);
}
</script>


</body>
</html>