<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${ctx}/resources/gxwlui/dist/libs/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/libs/font-Awesome-3.2.1/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${ctx}/resources/gxwlui/dist/gxwlui.css" rel="stylesheet">    
    <title>Insert title here</title>
    
    <style type="text/css">
    	body, html {
    		overflow:hidden;
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }
   
    	.search-toolbar{
    		margin-top: 10px;
    		margin-bottom: 10px;
    		margin-left: 10px;
    	}
    	
    	.cc{
    		height: 100% ;
    		padding: 0;
    	}
    	
    	.infoBtn{
    		text-align: right;
    	}
    	
    	.hide-otherInfo{
    		display: none;
    	}
    </style>    
</head>
<body>
<div class= "container-fluid cc">
	<div id="buttonLink" class="buttonLink">
	</div>
	
	<div style="height:100%;padding-top: 36px;margin-top: -36px" id="listTab" class="gxwl-tab" data-option='' >
		<div  class="active" title="列表" >
		    <div class="search-toolbar">
			   	<form id="search" class="form"  action="" > 
			        <div class="form-row">
						<div class="form-cell-xs-1">
						    <label class="form-label">姓名</label>
						    <input  name="q_householderName_LIKE" type="text" class="form-control" />
						</div>
					    <div class="form-cell-xs-1">
						    <label class="form-label">身份证号</label>
						    <input name="Q_cardCode_EQ" type="text" class="form-control" />
						</div>
						<div class="form-cell-xs-1">
						    <button onclick="process.query();" type="button" class="btn btn-primary btn-sm">搜索</button>
						</div>
					</div>
				</form>
			</div>
			<div id="datalist"></div>
		</div>
		<div disabled="disabled" title="详细">
			
			<form id="baseForm" class="form validator" action="">
				<input id="householderId" name="householderId" type="hidden" />
				<input id="createrId" name="createrId" type="hidden" />
				<input id="createTime" name="createTime" type="hidden" />
				<div class="form-row">
					<div class="form-cell-xs-1">
					    <label class="form-label">姓名</label>
					    <input validator="required" name="householderName" type="text" class="form-control" />
				    </div>
					<div class="form-cell-xs-1">
					    <label class="form-label">与户主关系</label>
					    <input name="" type="text" class="form-control" />
					</div>
				 	<div class="form-cell-xs-1">
					    <label class="form-label">身份证号</label>
					    <input name="cardCode" type="text" class="form-control" />
					</div> 
				</div>
			</form>
		</div>
	
	</div>
</div>

 
<script src="${ctx}/resources/gxwlui/dist/libs/jquery/jquery-1.11.3.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-3.3.5/js/bootstrap.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/libs/layer/layer/layer.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/combobox.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/validator.js" ></script>
<script src="${ctx}/resources/gxwlui/dist/gxwlui.js" ></script>

<script type="text/javascript">

var ctx = '${ctx}';

$(function(){
	
	//初始化按钮组
	$("#buttonLink").btntoolbar({
			btns:[
			      {
			    	id : 'add0',
			    	name :'add0',
			    	value : '新增',
			    	disable : false ,
			    	evens :[
				    	       {
				    	    	  eveName:'click',//事件名称
				    	    	  fncName : function (){//方法名称
				    	    		  process.add();
				    	    	  } 
				    	       },
			    	      ]
			      },
			      {
			    	id : 'edit0',
			    	name :'edit0',
			    	value : '编辑',
			    	disable : true ,
			    	evens :[
				    	       {
				    	    	  eveName:'click',//事件名称
				    	    	  fncName : function (){//方法名称
				    	    		  process.edit();
				    	    	  } 
				    	       },
			    	      ]
			      } ,
			      {
			    	id : 'save0',
			    	name :'save0',
			    	value : '保存',
			    	disable : true ,
			    	evens:[
			    	       {
			    	    	   eveName:'click',
			    	    	   fncName : 'save'
			    	       }
			    	 ]
			      },
			      {
			    	id : 'del0',
			    	name :'del0',
			    	value : '删除',
			    	disable : true,
			    	evens:[
			    	       {
			    	    	   eveName:'click',
			    	    	   fncName : function (){
			    	    		   process.del();
			    	    	   }
			    	       }
			    	 ]
			      } 
		      ]
	}); 
	
	//初始化tab
	$("#listTab").gtabs({
		
		onselect :function (param,index){
			
			if(index==1){
				loadData();
				$("#buttonLink").btntoolbar("setDisable",{
		 			"save0" : false 
		 		}); 
			}else{
				$("#buttonLink").btntoolbar("setDisable",{
		 			"save0" : true 
		 		});
			}
			
		}
	
	});
	
	init();
	
	$("#baseForm").form("setDisable",{disable:true});
	$("#sbxx").form("setDisable",{disable:true});
	$("#ybxx").form("setDisable",{disable:true});
	$("#yfxx").form("setDisable",{disable:true});
	$("#dbxx").form("setDisable",{disable:true});
	$("#wbxx").form("setDisable",{disable:true});
	$("#swxx").form("setDisable",{disable:true});
	$("#crxx").form("setDisable",{disable:true});
	$("#crxx").form("setDisable",{disable:true});
	$("#grxx").form("setDisable",{disable:true});
	$("#cjxx").form("setDisable",{disable:true});
	$("#xsjjxx").form("setDisable",{disable:true});
	$("#wfqsn").form("setDisable",{disable:true});
	$("#xjry").form("setDisable",{disable:true});
	$("#cqsfss").form("setDisable",{disable:true});
	$("#fyry").form("setDisable",{disable:true});
	$("#lnxx").form("setDisable",{disable:true});
});

$(function (){
	
	//dtagrid 数据加载
	$("#datalist").dataGrid({
		pageSize :8,
		setting:{
	 		multiSelect:true
	 	},
	 	columns : [[
	 	       {field:"householderId",title:"主键",hidden:true},
 	           {field:"householderName",title:"姓名",width:200},
 	           {field:"cardCode",title:"身份证号码",width:200},
 	           {field:"sex",title:"性别",width:100},
 	           {field:"nationality",title:"民族"},
 	           {field:"maritalStatus",title:"婚姻状况",width:70},
 	           {field:"callPhone",title:"联系电话"},
	 	     ]],
	 	url : "${ctx}/householder/search",
	 	onSelect : function(index,row){
	 		var len = $("#datalist").dataGrid("getSelections").length;
	 		
	 		 if(len>1){
	 			$("#listTab").gtabs("disable",1);
		 		$("#buttonLink").btntoolbar("setDisable",{
		 			"edit0" : true ,
		 			"del0" : false
		 		});
	 		}else if(len==1){
	 			$("#listTab").gtabs("enable",1);
		 		$("#buttonLink").btntoolbar("setDisable",{
		 			"edit0" : false ,
		 			"del0" : false
		 		});
	 		}
	 	},
	 	onUnselect : function(index,row){
	 		var len = $("#datalist").dataGrid("getSelections").length;
	 		if(len==1){
	 			$("#listTab").gtabs("enable",1);
	 			$("#buttonLink").btntoolbar("setDisable",{
		 			"edit0" : false ,
		 			"del0" : false
		 		});
	 		}else if(len>1){
	 			$("#listTab").gtabs("disable",1);
	 			$("#buttonLink").btntoolbar("setDisable",{
		 			"edit0" : true ,
		 			"del0" : false
		 		});
	 		}else {
	 			$("#buttonLink").btntoolbar("setDisable",{
		 			"edit0" : true ,
		 			"del0" : true
		 		});
	 			$("#listTab").gtabs("disable",1);
	 		}
	 	} 
	});
	
});

//清除所有表单信息
function clearAll (){
	$(document).find("form:not('#search')").each(function (){
		$(this).form("clear");
	});
}

//基础信息处理方法
var process = {
		
		add : function(){
			$("#baseForm").form("setDisable",{disable:false});
			$("#datalist").dataGrid("clearSelections");
			clearAll();
			$("#listTab").gtabs("select",1);
		},
		
		edit : function (){
			$("#listTab").gtabs("select",1);
			loadData();
		},
		
		del:function (){
			var rows = $("#datalist").dataGrid("getSelections");
			if(rows.length>0){
				var ids = "";
				$.each(rows,function (i,n){
					ids = ids +  n.householderId + ",";
				});
				$.ajax({
					type : "POST",
					url : ctx + "/householder/softDelList",
					data : {
						householderIds : ids
					}
				}).done(function(result) {
					if(result.resultCode==0){
						$("#datalist").dataGrid("load");
						console.log("datagrid load");
						layer.msg(result.resultMsg, {icon : 1});
					}else{
						layer.msg(result.resultMsg, {icon : 2});
					}
				});
			}else{
				layer.msg("请选择需要删除的数据！", {icon : 2});
			}
		},
		
		query : function (){
			var param = $("#search").form("getData");
			$("#datalist").dataGrid("load",param);
		}
		
};

//加载基础信息数据
function  loadData(){
	var row = $("#datalist").dataGrid("getSelected");
	if(row){
		var householderId = row.householderId;
		$.ajax({
			type : "POST",
			url : ctx + "/householder/get/"+householderId,
		}).done(function(result) {
			  $("#baseForm").form("setData",result.row);
			  $("#baseForm").form("setDisable",{disable:false});
		});
	}
	
}

//保存基础信息
function save(){
		
	var fal = $("#baseForm").validator("validate");
	
	if(fal){
		layer.confirm('是否确认保存？', {
		    btn: ['保存','取消'] //按钮
		}, function(){
			var da = $("#baseForm").form("getData");
			
			$.ajax({
				type : "POST",
				url : ctx + "/householder/save",
				data : da
			}).done(function(result) {
				$("#datalist").dataGrid("load");
				layer.msg('保存成功', {icon : 1});
			});
			
		});
	}	
}

//附加信息初始化
function init(){
	
	$("#otherInfo").find("div.panel-heading").bind("click",function (){
		var attr = $(this).attr("toggle");
		var infoName = $(this).attr("infoName");//对应的信息类型
		var formId = $(this).attr("formId");//对应的表单id
		var oinfo = $("#otherInfo").find("div.panel-body[toggle='"+attr+"']");
		if(oinfo.is(":hidden")){
			oinfo.show(); 
			otherInfo.getInfo(infoName,"#"+formId);
		}else{
			oinfo.hide(); 
		}
	});
	
}

var otherInfo  = {
		
		//保存附加信息
		saveOtherInfo : function (formId,infoName){
			
			if($(formId).validator("validate")){
				
				var householderId = $("#householderId").val();
				
				if(!householderId){
					layer.msg('请保存基础信息后再保存其他数据！', {icon : 2});
					return false ;
				}
				
				layer.confirm('是否确认保存？', {
				    btn: ['保存','取消'] //按钮
				}, function(){
					var data  = $(formId).form("getData");
					data["householderId"] = householderId;
					$.ajax({
						type : "POST",
						url : $(formId).attr("action"),
						data : data
					}).done(function(result) {
						layer.msg('保存成功', {icon : 1});
					});
					 
					
				});
			}
		},
		//删除附加信息
		delOtherInfo :function (type,formId){
			var householderId = $("#householderId").val();
			if(!householderId){
				layer.msg('基础信息不存在，无法执行删除操作！', {icon : 2});
				return false ;
			}
			layer.confirm('确认要删除信息？', {
			    btn: ['确认','取消'] //按钮
			}, function(){
				
				$.ajax({
					type : "POST",
					url : "${ctx}/householder/delInfo",
					data : {
						'type':type,
						'householderId':householderId
					}
				}).done(function(result) {
					if(result.resultCode==0){
						$(formId).form("clear");	
						layer.msg(result.resultMsg, {icon : 1});
					}else{
						layer.msg(result.resultMsg, {icon : 2});
					}
				});
				 
				
			});
			
		},
		//编辑附加信息
		editOtherInfo : function (formId,infoName){
			$(formId+"_btn_save").removeClass("disabled");
			$(formId+"_btn_edit").addClass("disabled");
			$(formId).form("setDisable",{disable:false});
		},
		//获取附加信息
		getInfo:function (type,formId){
			var householderId = $("#householderId").val();
			if(householderId){
				$.ajax({
					type : "POST",
					url : ctx + "/householder/getInfo/",
					data : {
						'type':type,
						'householderId':householderId
					}
				}).done(function(result) {
					if(result){
					  $(formId).form("setData",result);
					}
				});
			}
		}
}

 
	
</script>

</body>
</html>