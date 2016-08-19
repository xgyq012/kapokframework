<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
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
	<style type="text/css">
	.form-label{
		font-size: 12px;
	}
	</style>
</head>

<body>
<div class="g-layout">
    <!-- 事件核查弹窗 -->
    <div id="mainTab" class="easyui-panel" style="width:100%;height:100%; padding: 5px">
		<form id="genjin"  action="">
		    <input id="yjsjClmxId" name="yjsjClmxId" type="hidden" />
			<input id="createrId" name="createrId" type="hidden" />
			<input id="createTime" name="createTime" type="hidden" />
			<input id="yingjiShijianId" name="yingjiShijianId" type="hidden" />
			<input id="genjinrenId" name="genjinrenId" value="<shiro:principal property='userId'/>" type="hidden"/>
			<input id="genjinrenName" name="genjinrenName" value="<shiro:principal/>" type="hidden"/>
			<input id="genjinTime" name="genjinTime" type="hidden" />
			<input id="fileId" name="fileId" type="hidden" />
    		<table  cellpadding="0" cellspacing="0">
     			<tr>
     			    <td class="form-cell-4" colspan="4">
	     				   <label class="form-label">阶段</label>
	     				   <input id="title" name="title" class="easyui-validatebox form-control"  data-options="required:true" >
	     			</td>     			
     			</tr>  
  
     			<tr>   			
     			    <td class="form-cell-4" colspan="4">
	     				   <label class="form-label">事件进展</label>
	     				   <textarea rows="9" name="body" class="easyui-validatebox form-control" data-options="required:true" style="height: 313px;"></textarea>
	     			</td>      			
     			</tr>   
     			<tr>
     			    <td class="form-cell-4" colspan="4">
	     				   <label class="form-label">附件名称</label>
     					   <div id="addFile"></div>
	     				   <input id="docName" name="fileName" class="easyui-validatebox form-control" readonly="readonly" style="width: 510px">
	     			</td>      			
     			</tr>      						     			    			 			
     		</table>
     	</form>	
     	
        <div id="toolbar" style="text-align: right; padding-right: 100px; ">
            <a id="upfile" onclick="uploadBtn.fileupload('click');" class="easyui-linkbutton add g-button"><i class="fa fa-upload"></i>上传附件</a>
		    <a id="confirm" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="save();" ><i class="fa fa-check"></i>确定</a>
		    <a id="cancel" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-times"></i>取消</a>
	    </div>     		
    </div>
</div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript" >
var yingjiShijianId = 0;
var uploadBtn;

$(function(){
	yingjiShijianId = window.params.yingjiShijianId;
	importDoc.init();
});

function save(data){
	if($("#genjin").form("validate")){
		$("#yingjiShijianId").val(yingjiShijianId);
		$("input[name='genjinTime']").val(new Date().formatDate("yyyy-MM-dd hh:mm:ss"));
		$.ajax({
			type:"post",
			url:"${ctx}/yjsjclmx/save",
			data:getFormData("#genjin")
		}).done(function(jieguo){
			window.actions.confirm();
		});
		
	}
	
}

//日期格式化  
function  formatterDate (date){
	return date.formatDate("yyyy-MM-dd hh:mm:ss");
}

var importDoc = {
		init : function (){
	 		uploadBtn = $("#addFile").fileupload({
				url : "${ctx}/doc/upload",
				upfile : "upfile",
				filetype : "mp3|mp4|mkv|jpg|jpeg|png",
				hidden : true,
				params : {
					directory : "photos"
				},
				onUploadComplete : function(result) {
					//修改应急事件附件信息id列表
					$("#fileId").val(result.docId);
					$("#docName").val(result.docShowname);
				}
			});
		}
	};
</script>


</body>
</html>