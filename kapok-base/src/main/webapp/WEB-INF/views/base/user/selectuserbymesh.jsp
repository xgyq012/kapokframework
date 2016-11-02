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
	<style type="text/css">
	.aaaa{
	padding: 0 10px;
	margin: 0;
	}
	</style>
</head>
<body>
<div class="g-layout">
	<div id="mainLayout" class="easyui-layout g-container" style="padding: 0;">
	    <div data-options="region:'west',split:true,minWidth:200" style="width:200px; overflow: hidden;">
       	   <ul id="orgTree" class="easyui-tree" checkbox="true"></ul>
	    </div>
     
	     <!-- data-options="disabled:true" -->
       <div data-options="region:'center'">
           <div id="query">
			    <form id="queryForm" class="formCls" method="post">
				  <table style="padding: 5px 0 6px 0;">
					<tr>	
						<td class="aaaa">
				            <label>用户账号</label>
				            <input id="q_userName_LIKE" name="q_userName_LIKE" style="height: 25px; margin: 0;">
				        </td>
						<td class="aaaa">
				            <label>姓名</label>
				            <input id="q_realname_LIKE" name="q_realname_LIKE" style="height: 25px; margin: 0;">
				        </td>			        
						<td class="aaaa">
							<a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
							<a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
						</td>				
					</tr>
				   </table>
			     </form>
		     </div>
		     <table title="用户列表" id="listGrid" style="height: 380px;"></table>
		     <div id="toolbar" class="dialog-button" style="border: 0;">
				<a id="confirm" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="confirm();" ><i class="fa fa-check"></i>确定</a>
			    <a id="cancel" href="javascript:void(0);" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-reply"></i>取消</a>
			 </div> 
       </div>
      
    </div>

</div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" >
var selectedUserList = new Array();
var selectAll = false;//選中列表中所有成員
var thisUiConfig = {
		ctx : "${ctx}",
		url : "mesh",
		tableList : "#listGrid",
		queryForm : "#queryForm",
		mainTab : "#mainTab",
		baseForm : "#baseForm",
	};
	
$(function (){
	$("#queryForm input").on("keyup", function(){
		    tableInfo.query();
	    }
	);
	tableInfo.init(0);
	initOrgTree();
	$("input[type='checkbox']").on("click", function(){
		return false;
	});
});


//列表加载
var tableInfo =  {
	//初始化列表
	init : function (){
		$(thisUiConfig.tableList).datagrid({
			rownumbers : true,
			singleSelect : false,
			autoRowHeight : false,
			checkbox: false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			queryParams:  getFormData("#queryForm"),
			url : thisUiConfig.ctx +"/user/search",
			columns : [[
						{field: '', checkbox: true},
						{field: 'relaId', title: '主键', halign: 'center', hidden: true},
						{field: 'meshId', title: '辖区ID', halign: 'center', hidden: true},
						{field: 'userId', title: '用户ID', halign: 'center', hidden: true},
						{field: 'createrId', title: '创建人ID', halign: 'center', hidden: true},
						{field: 'createTime', title: '创建时间', halign: 'center', hidden: true},
						{field: 'userName', title: '用户账号', halign: 'center', width: 100},
						{field: 'realname', title: '用户姓名', halign: 'center', width: 100},
						{field: 'statusName', title: '状态', halign: 'center', align: 'center', width: 50}
		 			]],
			onLoadSuccess : function(data) {
				if(selectAll){
					$(thisUiConfig.tableList).datagrid("checkAll");	
				}
			},
			onSelect: function(rowIndex, rowData){
				addOneUserId(rowData.userId);
				console.log(selectedUserList);
			},
			onUnselect: function(rowIndex, rowData){
				selectedUserList = remove(rowData.userId);
				console.log(selectedUserList);
			} 
		});
		
		// 设置分页显示形式
		$(thisUiConfig.tableList).datagrid("getPager").pagination({
			layout : defaultPageLayout
		});
	},
	query : function (){
		selectAll = false;
		tableInfo.init();
/* 		var param = getFormData("#queryForm");
		$(thisUiConfig.tableList).datagrid("load", param); */
	}
};

function confirm(){
	window.actions.confirm(selectedUserList);
}



function initOrgTree(){
	$("#orgTree").tree({
		url: '${ctx}/mesh/getMesh',
		cascadeCheck: false,
		formatter: function(node) {
			return node.meshName;
		},
		loadFilter: function(data, parent) {
			data.forEach(function(x) {
				x.id = x.meshId;
			});
			return data;
		},
		onCheck: function(node) {
			selectAll = true;
			if(! node.checked){
				$(thisUiConfig.tableList).datagrid({
					queryParams: {
						'q_mesh.meshId_EQ': node.meshId
					},
					url: '${ctx}/mesh/meshUserSearch',
				});
				
				findUserByMeshId(node.meshId, true);
			}else{
				findUserByMeshId(node.meshId, false);
				$(thisUiConfig.tableList).datagrid("unselectAll");
			}
		}
	});
}



//查找选中机构下的所有成员，并将其id保存到数组
function findUserByMeshId(meshId, checked){
	$.ajax({
		type: "post",
		data: {
			'q_mesh.meshId_EQ': meshId,
			'page': 1,
			'rows':900000
		},
		url:'${ctx}/mesh/meshUserSearch',
	}).done(function(data){
		if(checked){ //如果勾选某个机构
			appendUserId(data.rows);
		}else{
			removeUserId(data.rows);
		}
		console.log(selectedUserList);
	});
}

//将整个机构下的用户添加到选中用户列表
function appendUserId(rows){
	$.each(rows, function(key, value){
		addOneUserId(value.userId);
	});
};

//删除整个机构下的成员的id
function removeUserId(rows){
	$.each(rows, function(index, value){
		selectedUserList = remove(value.userId);
	});
}

function addOneUserId(userId){
	var baohan = isBaohan(userId);
	//alert(baohan);
	if(!baohan){
		selectedUserList[selectedUserList.length] = userId;
	}
}


function isBaohan(userId){
	var baohan = false;
	$.each(selectedUserList, function(index, value){
		if(value == userId){
			baohan =  true;
			return;
		}
	});
	return baohan;
}

//通过遍历删除给定的用户id  返回新的数组
function remove(userId){
	var newList = new Array();
	var i = 0;
	$.each(selectedUserList, function(index, value){
		if(value != userId){
			newList[i ++] = value;
		}
	});
	return newList;
}


</script>
</body>
</html>