<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${system_name}-${company_name}</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/icon.css" />
</head>

<body>
<div class="content">
	<!-- 查询区域 -->
	<!-- 
	<div id="query">
		<form id="queryForm" class="formCls" method="post">
			<table>
				<tr>
					<td>角色编码</td>
					<td>
						<input type="text" />
					</td>
					<td>角色名称</td>
					<td>
						<input type="text" />
					</td>
					<td>
						<a href="javascript:void(0);" onclick="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
					</td>
					<td>
						<a href="javascript:void(0);" onclick="clear();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	 -->

	<!-- 列表区域 -->
	<div style="height:350px;overflow: auto;">
	   <ul id="orgTree" class="easyui-tree"
    		checkbox="true">
        </ul>
	</div>
	<div id="toolbar" class="dialog-button">
		<a id="confirm" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.actions.confirm($('#orgTree').tree('getSelected'));" data-options="plain:true,iconCls:'icon-save'">确定</a>
		<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.actions.cancel();" data-options="plain:true,iconCls:'icon-undo'">取消</a>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	$(function (){
		$("#orgTree").tree({
			url: "${ctx}/org/getOrg",
			cascadeCheck: false,
			formatter: function(node) {
				return node.orgName;
			},onCheck:function(){
				var ids = "";
				var nodes = $('#orgTree').tree('getChecked');
				for(var i = 0; i < nodes.length; i++){
					if(ids == ""){
						ids = "" + nodes[i].id;
					}else{
						ids += "," + nodes[i].id;
					}
				}
				parent.$("#targetJigous").val(ids);
			}
		});
		
	})
	
</script>

</body>
</html>