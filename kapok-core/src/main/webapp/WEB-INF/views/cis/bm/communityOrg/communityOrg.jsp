<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>社区机构管理</title>
    
    <link href="${ctx}/resources/gxwlui/dist/libs/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/libs/font-Awesome-3.2.1/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/gxwlui.css" rel="stylesheet">
    <link href="${ctx}/resources/css/main.css" rel="stylesheet">
    
</head>
<body>

<div class="container-fluid wrapper">
	<div class="left-div">
		<div class="left-bottom-div">
            <ul class="tree" data-options="url:'${ctx}/resource/getMenuByModule/${resourceId}',idField:'id',labelField:'resourceLabel',parentIdField:'parentResourceId'"></ul>	
         </div>   
	</div>
	
	<div class="right-div">
		<div class="right-bottom-div">
			 <div style="height: 100%" id="tabs"></div>
		 <div>
   </div>
</div>
</div>
</div>

<script src="${ctx}/resources/gxwlui/dist/libs/jquery/jquery-1.11.3.min.js"></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/gxwlui.js"></script>
<script src="${ctx}/resources/gxwlui/dist/combobox.js"></script>
<script src="${ctx}/resources/gxwlui/dist/libs/layer/layer/layer.js"></script>

<script type="text/javascript">
	$(function(){
		$(".tree").tree({
			onClick : function(data){
				
			}
		});
	});
</script>

</body>
</html>