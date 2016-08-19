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
         <form id="shenchaInfo">
    		<input id="shencharenId" name="shencharenId" type="hidden" value="<shiro:principal property='userId'/>"/>
			<input id="shencharenName" name="shencharenName" type="hidden"/>
			<input id="yingjiShijianId" name="yingjiShijianId" type="hidden" />
			<input id="createrId" name="createrId" type="hidden" />
			<input id="createTime" name="createTime" type="hidden" />
			<input id="yjsjlxId" name="yjsjlxId" type="hidden" />
			<input id="yjsjlxName" name="yjsjlxName" type="hidden" />
			<input id="name" name="name" type="hidden" />
			<input id="address" name="address" type="hidden" />
			<input id="laiyuan" name="laiyuan" type="hidden" />
			<input id="shijianStatus" name="shijianStatus" type="hidden" />
			<input id="shangbaoren" name="shangbaoren" type="hidden" />
			<input id="shangbaorenPhone" name="shangbaorenPhone" type="hidden" />
			<input id="shangbaoTime" name="shangbaoTime" type="hidden" />
			<input id="shijianPs" name="shijianPs" type="hidden" />
			<input id="hecharenId" name="hecharenId" type="hidden" />
			<input id="hecharenName" name="hecharenName" type="hidden" />
			<input id="hechaTime" name="hechaTime" type="hidden" />
			<input id="shifouShangbao" name="shifouShangbao" type="hidden" />
			<input id="hechaYijian" name="hechaYijian" type="hidden" />
			<input id="yingjiPlanId" name="yingjiPlanId" type="hidden" />
			<input id="shenchaTime" name="shenchaTime" type="hidden" />
			<input id="lururenId" name="lururenId" type="hidden" />
			<input id="lururenName" name="lururenName" type="hidden" />
    		<table>
	     		<tr>
     			    <td class="form-cell-4" colspan="4">
		     			<label class="form-label">是否启动应急</label>
	   				    <input id="yingjiStatus" name="yingjiStatus" class="easyui-combobox form-control"
	     						style="width:150px; height:${comboboxHeight}px">
	     			</td>		     					
     			</tr>     	
	     		<tr>
     			    <td class="form-cell-4" colspan="4">
     				    <label class="form-label">选择应急预案</label>
	     				<input type="text" id="yingjiPlanName" name="yingjiPlanName" class="easyui-validatebox form-control" />
	     			</td>		     					
     			</tr> 	     					
     			<tr>
     			    <td class="form-cell-4" colspan="4">
	     				   <label class="form-label">审查意见</label>
	     				   <textarea rows="13" name="shenchaYijian" class="easyui-validatebox form-control" style="width: 550px; height: 310px;"></textarea>
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
var userName = "<shiro:principal/>";
function selectUser() {
	selectUser = $("#yingjiPlanName").lookup({
		title : "选择应急预案",
		url : "${ctx}/yingjiplan/selectplan",
		width : 700,
		height : 420,
		innerDialog: true,
		required : false,
		contentWindow:{
			params : {
				singleSelect : true,
				checkbox : false
			},
			actions : {
				confirm : function(data) {
					if(data){
						$("#yingjiPlanName").lookup("setTxt", data.name);
						$("#yingjiPlanId").val(data.yingjiPlanId);
						$("input[name='yingjiPlanName']").val(data.name);
						$("#yjsjlxId").val(data.yjsjlxId);
						$("#yjsjlxName").val(data.yjsjlxName);
					}
					main.closeInnerDialog();
				},
				cancel : function() {
					$("#yingjiPlanName").lookup("setTxt", "");
					$("#yingjiPlanId").val("");
					$("input[name='yingjiPlanName']").val("");
					$("#yjsjlxId").val("");
					$("#yjsjlxName").val("");
					main.closeInnerDialog();
				}
			}
		},
		
		onBeforeOpen : function (){
			var v = $("input[name='yingjiStatus']").val();
			if(! v){//没有选择是否启动应急之前不能选择应急预案
				return false;
			}else{
				if(v == "N"){
					main.alert("温馨提示:", "不启动应急的情况下，无需选择应急预案！");
					return false;
				}else{
					return true;
				}
			}
		}
	});
}
	
$(function(){
	//獲取應急事件頁面傳過來的選中行的數據
	$("#shenchaInfo").form("load", window.params.row);
	selectUser();
	initCombobox();
});

function save(){
	if($("#shenchaInfo").form("validate")){
		var v = $("input[name='yingjiStatus']").val();
		if(v == "Y" && ! $("#yingjiPlanId").val()){
			main.alert("温馨提示:", "启动应急的情况下，需要选择应急预案！");
			return false;
		}else if(v == "N" && $("#yingjiPlanId").val()){
			$("#yingjiPlanId").val("");
			$("#yingjiPlanName").val("");
			main.alert("温馨提示:", "如不启动应急，无需选择应急预案！");
			return false;
		}
		$("input[name='shijianStatus']").val(3);
		$("#shencharenName").val(userName);
		$("input[name='shenchaTime']").val(new Date().formatDate("yyyy-MM-dd hh:mm:ss"));
		var data = getFormData("#shenchaInfo");
		window.actions.confirm(data);
	}
	
}
var dict = main.dict;
var dicts = dict.dicts;
var comboboxConfig = {
	valueField: 'dictCode',
    textField: 'dictName',
    editable: true,
	panelHeight: 'auto',
};
function initCombobox(){
	comboboxConfig.data = dicts.YesOrNo.list;
	comboboxConfig.required = true;
	$('#yingjiStatus').combobox(comboboxConfig);
}
</script>


</body>
</html>