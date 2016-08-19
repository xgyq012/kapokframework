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

<div class=container>
     <button class="btn btn-primary btn-sm" type="button" onclick="check();">查看</button>
	 <button class="btn btn-primary btn-sm" type="button" onclick="print();">打印</button>
	 <button class="btn btn-primary btn-sm" type="button" onclick="download();">下载</button>
	 
	 <form class="form" id="checkForm" >
			      <div class="form-row">
			         <div class="form-cell-xs-1">
			              <label class="form-label">安全负责人</label>
                          <input class="form-control input-sm" id="Qaqfzr" /> 
                     </div>
                     <div class="form-cell-xs-1">
                          <label class="form-label">单位名称</label>
                          <input class="form-control input-sm" id="Qdwmc" />
                     </div>
                     <div class="form-cell-xs-1">
                          <label class="form-label">防盗门</label>
                          <input class="form-control input-sm" id="Qfdmaz" />
                     </div>
                     <div class="form-cell-xs-1">
                          <label class="form-label">电子监控 </label>
                          <input class="form-control input-sm" id="Qdzjkaz" />
                     </div>
                     <div class="form-cell-xs-1">
                          <label class="form-label">电话报警</label>
                          <input class="form-control input-sm" id="Qdhbjaz" />
                     </div>
			      
                     <button class="btn btn-primary" type="button" onclick="query();">查询</button>
	                 <button class="btn btn-primary" type="button" onclick="reset();">重置</button>  
			      </div>
	 </form>
	 
	 <div id="listGrid"></div>

</div>

<script src="${ctx}/resources/gxwlui/dist/libs/jquery/jquery-1.11.3.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-3.3.5/js/bootstrap.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/gxwlui.js" ></script>

<script src="${ctx}/resources/gxwlui/dist/libs/layer/layer/layer.js"></script>

<script type="text/javascript">
	 var ctx = "${ctx}";
</script>

<script type="text/javascript">

	var ctx = '${ctx}';
	
	$(function(){
		$("#listGrid").dataGrid({
			pageSize : 10,
			setting:{
		 		multiSelect:true
		 	},
		 	columns : [[
		 	       {field:"economicOrgId",title:"主键",hidden:true},
	 	           {field:"ssjg",title:"所属机构",width:200},
	 	           {field:"qyspmc",title:"单位名称",width:200},
	 	           {field:"hylb",title:"党支部书记",width:100},
	 	           {field:"dz",title:"工会主席"},
	 	           {field:"fzrxm",title:"电子监控",width:70},
	 	          {field:"fzrxm",title:"安全负责人",width:70},
	 	           {field:"fzrdh",title:"负责人联系电话",width:70}
		 	           ]],
		 	url : "${ctx}/economicOrg/search",
		 	onSelect : function(index,row){
		 		
		 	},
		 	onUnSelect : function(index,row){
		 		
		 	},
		 	onDbClickRow : function(index,row){
		 		
		 	},
		 	onClickRow : function(index,row){
		 		
		 	}
		});
		
	});
	
	var url = ctx + "/economicOrg/list2";
	
	//查看
	function check(){
		top.layer.open({
			type : 2,
			area : ['900px', '650px'],
			fix: false, //不固定
			maxmin: true,
			title : '编辑组织',
			shade : 0.6,
			moveType : 1,
			shift : 1,
			content : url,
			btn : ['取消'],
			btn1 : function(){
				layer.close();
			}
		});
		
	}

	//加载数据
	function loadSocialOrgData(){
		
	}
	
	
	function query(){
// 		var param = getFormData("#checkForm");
// 		var value1 = $("Qaqfzr").val();
// 		var value2 = $("Qdwmc").val();
// 		var value3 = $("Qfdmaz").val();
// 		var value4 = $("Qdzjkaz").val();
// 		var value5 = $("Qdhbjaz").val();
		
// 		if(value1 != ""){
// 			param["q_aqfzr_like"] = value1;
// 		}
// 		if(value2 != ""){
// 			param["q_dwmc_like"] = value2;
// 		}
// 		if(value3 != ""){
// 			param["q_fdmaz_like"] = value3;
// 		}
// 		if(value4 != ""){
// 			param["q_dzjkaz_like"] = value4;
// 		}
// 		if(value5 != ""){
// 			param["q_dhbjaz_like"] = value5;
// 		}
		
// 		try{
// 			$("#checkForm").dataGrid("load", param);
// 		}catch(err){
			
// 		}
		
	}
	

</script>

</body>
</html>