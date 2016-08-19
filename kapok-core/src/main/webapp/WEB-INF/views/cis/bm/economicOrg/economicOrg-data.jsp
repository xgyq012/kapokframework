<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>新经济组织</title>
    
    <link href="${ctx}/resources/gxwlui/dist/libs/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/libs/font-Awesome-3.2.1/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/gxwlui.css" rel="stylesheet">
    
</head>
<body>

<div class="container-fluid">
	 <form class="form" id="dataForm" >
        <input id="userId" name="userId" type="hidden"/>
       	<input id="createrId" name="createrId" type="hidden" />
	    <input id="createTime" name="createTime" type="hidden" />
	 	<div class="form-row">
			         <div class="form-cell-xs-1">
			              <label class="form-label">所属机构</label>
                          <input class="form-control" type="text" id="ssjg" name="ssjg"/> 
                     </div>
        </div>             
        <div class="form-row">
                     <div class="form-cell-xs-1">
			              <label class="form-label">单位名称</label>
                          <input class="form-control" type="text" id="dwmc" name="dwmc"/> 
                     </div>
        </div>             
        <div class="form-row">
                     <div class="form-cell-xs-1">
			              <label class="form-label">单位性质</label>
                          <input class="form-control" type="text" id="dwxz" name="dwxz"/> 
                     </div>
        </div>             
        <div class="form-row">
                     <div class="form-cell-xs-1">
			              <label class="form-label">员工人数</label>
                          <input class="form-control" type="text" id="ygrs" name="ygrs"/> 
                     </div>
                     <div class="form-cell-xs-1">
			              <label class="form-label">占地面积</label>
                          <input class="form-control" type="text" id="zdmj" name="zdmj"/> 
                     </div>
                     <div class="form-cell-xs-1">
			              <label class="form-label">法人代表</label>
                          <input class="form-control" type="text" id="frdb" name="frdb"/> 
                     </div>
        </div>
         <div class="form-row">
                     <div class="form-cell-xs-1">
			              <label class="form-label">法人联系电话</label>
                          <input class="form-control" type="text" id="frdh" name="frdh"/> 
                     </div>
                     <div class="form-cell-xs-1">
			              <label class="form-label">党支部书记</label>
                          <input class="form-control" type="text" id="dzbsj" name="dzbsj"/> 
                     </div>
                     <div class="form-cell-xs-1">
			              <label class="form-label">党支部书记联系电话</label>
                          <input class="form-control" type="text" id="dzbsjdh" name="dzbsjdh"/> 
                     </div>
        </div>
        <div class="form-row">
                     <div class="form-cell-xs-1">
			              <label class="form-label">工会主席</label>
                          <input class="form-control" type="text" id="ghzx" name="ghzx"/> 
                     </div>
                     <div class="form-cell-xs-1">
			              <label class="form-label">工会主席联系电话</label>
                          <input class="form-control" type="text" id="ghzxdh" name="ghzxdh"/> 
                     </div>
                     <div class="form-cell-xs-1">
			              <label class="form-label">工会会员人数</label>
                          <input class="form-control" type="text" id="ghhyrs" name="ghhyrs"/> 
                     </div>
        </div>
        <div class="form-row">
                     <div class="form-cell-xs-1">
			              <label class="form-label">安全负责人</label>
                          <input class="form-control" type="text" id="aqfzr" name="aqfzr"/> 
                     </div>
                     <div class="form-cell-xs-1">
			              <label class="form-label">安全负责人电话</label>
                          <input class="form-control" type="text" id="aqfzrdh" name="aqfzrdh"/> 
                     </div>
                     <div class="form-cell-xs-1">
			              <label class="form-label">党员人数</label>
                          <input class="form-control" type="text" id="dyrs" name="dyrs"/> 
                     </div>
                     
        </div>
        <div class="form-row">
                     
        </div>
        <div class="form-row">
        			<div class="form-cell-xs-3">
			              <label class="form-label">文明单位情况</label>
                          <input class="form-control" type="text" id="wmdwqk" name="wmdwqk"/> 
                     </div>
        </div>
        <div class="form-row">
			         <div class="form-cell-xs-3">
			              <label class="form-label">防火设备</label>
                          <input class="form-control" type="text" id="fhsb" name="fhsb"/> 
                     </div>
        </div>
        <div class="form-row">
			         <div class="form-cell-xs-3">
			              <label class="form-label">防盗设施</label>
                          <input class="form-control" type="text" id="fdss" name="fdss"/> 
                     </div>
        </div>
        <div class="form-row">
			         <div class="form-cell-xs-1">
			              <label class="form-label">防盗门安装情况</label>
                          <input class="form-control" type="radio" id="fdmaz" name="fdmaz"/>
                          <input class="form-control" type="radio" id="fdmaz" name="fdmaz"/> 
                     </div>
        </div>
        <div class="form-row">
			         <div class="form-cell-xs-1">
			              <label class="form-label">电子监控安装情况</label>
                          <input class="form-control" type="radio" id="dzjkaz" name="dzjkaz"/> 
                          <input class="form-control" type="radio" id="dzjkaz" name="dzjkaz"/> 
                     </div>
        </div>
        <div class="form-row">
			         <div class="form-cell-xs-1">
			              <label class="form-label">电话报警安装情况</label>
                          <input class="form-control" type="radio" id="dhbjaz" name="dhbjaz"/> 
                          <input class="form-control" type="radio" id="dhbjaz" name="dhbjaz"/> 
                     </div>
        </div>
        <div class="form-row">
			         <div class="form-cell-xs-3">
			              <label class="form-label">单位详细地址</label>
                          <input class="form-control" type="text" id="dwxxdz" name="dwxxdz"/> 
                     </div>
        </div>
        
        <button class="btn btn-primary btn-sm" type="button" onclick="ensure();">确定</button>
	    <button class="btn btn-primary btn-sm" type="button" onclick="canel();">取消</button>
	 </form>
</div>

<script src="${ctx}/resources/gxwlui/dist/libs/jquery/jquery-1.11.3.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-3.3.5/js/bootstrap.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/gxwlui.js" ></script>

<script src="${ctx}/resources/gxwlui/dist/libs/layer/layer/layer.js"></script>
	
<script type="text/javascript">
	//点击确定
	function ensure(){
		var data = $("#dataForm").form('getData');
		console.log(data);
		
		$.ajax({
			type : "post",
			url : "${ctx}/economicOrg/save",
			data : data
		}).done(function(res){
			if(res.resultCode == 0){
				$("#dataForm").form('setData', res.row);
			}
		}).fail(function(jqXHR, textStatus, errorThrown){
			
		});
			
	}
</script>

</body>
</html>