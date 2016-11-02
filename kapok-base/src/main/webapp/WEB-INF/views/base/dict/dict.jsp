<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${system_name}-${company_name}</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui-1.3.5/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/sis.css" />
</head>

<body>
<div class="easyui-panel" data-options="fit:true,border:false">
    <!-- 按钮区域 -->
    <div id="toolbar" style="padding: 5px;" >
        <a id="add" href="javascript:void(0);" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-add'" onclick="add();" >新增</a>
        <a id="del" href="javascript:void(0);" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-remove'" onclick="del();">删除</a>
        <a id="edit" href="javascript:void(0);" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
        <a id="save" href="javascript:void(0);" class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-save'" onclick="save();">保存</a>
<!--         <a id="sync" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-sync'" onclick="syncDict();">同步数据字典</a> -->
    </div>

    <!-- 实体区域 -->
    <div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false">

        <!-- 实体列表 -->
        <div id="listTab" title="列表" class="tabCls">

            <!-- 查询区域 -->
            <div id="query">
                <form id="queryForm" class="formCls" method="post">
                    <table>
                        <tr>
                            <td>字典编码</td>
                            <td>
                                <input type="text" name="q_dictTypeCode_LIKE"/>
                            </td>
                            <td>字典名称</td>
                            <td>
                                <input type="text" name="q_dictTypeName_LIKE" />
                            </td>
                            <td>
                                <a href="javascript:void(0);" onclick="query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a> 
                                <a href="javascript:void(0);" onclick="$('#queryForm').form('clear');query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">重置</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

            <!-- 列表区域 -->
            <div id="list">
                <table id="listGrid" style="height: 432px;"></table>
            </div>
        </div>

        <!-- 实体详细 -->
        <div id="detailTab" title="详细" class="tabCls">
            <form id="dataForm" class="formCls" method="post">
                <!-- 表单区域 -->
                <div id="form">
                    <input id="dictTypeId" name="dictTypeId" type="hidden"/>
                    <input id="createrId" name="createrId" type="hidden" />
					<input id="createTime" name="createTime" type="hidden" />
                    <table>
                        <tr>
                            <td>字典编码</td>
                            <td><input id="dictTypeCode" name="dictTypeCode"type="text" class="easyui-validatebox"
                                data-options="required: true"
                                ></td>
                            <td>字典名称</td>
                            <td><input id="dictTypeName" name="dictTypeName" type="text" class="easyui-validatebox" data-options="required: true"></td>
                            <td>是否有效</td>
                            <td>
                                <input id="enable" name="enable" class="easyui-combobox"
                                    data-options="
                                        required:true,
                                        panelHeight:'auto',
                                        valueField:'dictCode',
                                        textField:'dictName',
                                        url:'${ctx}/dict/getdict/YesOrNo'"/>
                            </td>
                        </tr>
						<tr>
							<td>备注</td>
							<td colspan="5">
								<textarea  id="remark" name="remark" class="easyui-validatebox"  rows="5" style="width: 490px;"></textarea>
							</td>
						</tr>
                    </table>
                </div>
            
                <!-- 行表区域 -->
                <div id="sub" style="height:500px;">
                    <div  id="subTabs" class="easyui-tabs" data-options="fit:true,border:false"> 
                        <div title="数据字典项" style="overflow:auto;">
                            <div id="buttonbar">
                               <a id="addDict" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"  onclick="addDict();" >添加</a>
                               <a id="removeDict" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removeDict();">移除</a>
                            </div>
                           <table id="dg_gxwlSysDictLs" class="easyui-datagrid"
                                    data-options="
                                       	idField : 'dictId',
										rownumbers : true,
										singleSelect : false,
										autoRowHeight : false,
										toolbar : '#buttonbar',
										onClickRow : onDg_gxwlSysDictLsClickRow">
                                    <thead>
                                        <tr>
                                        	<th data-options="field:'dictId',hidden:true">主键</th>
                                        	<th data-options="field:'dictTypeId',hidden:true">数据字典类型id</th>
											<th data-options="field:'createrId',hidden:true">创建人ID</th>
											<th data-options="field:'createTime',hidden:true">创建时间</th>
                                            <th data-options="field:'dictCode',width:100 ,editor:{type:'validatebox',options:{required:true}}" >字典项编码</th>
                                            <th data-options="field:'dictName',width:100,editor:{type:'validatebox',options:{required:true}}" >字典项名称</th>
                                            <th data-options="field:'seq',width:100,editor:{type:'numberbox',options:{required:true}}">序号</th>
                                            <th  data-options="field:'isDisplay',width:80,
                                                formatter : function(value,row){
                                                    return value == 'Y'? '是':'否';
                                                },
                                                editor : {
                                                    type : 'combobox',
                                                    options : {
                                                        valueField : 'dictCode',
                                                        textField : 'dictName',
                                                        url : '${ctx}/dict/getdict/YesOrNo',
                                                        panelHeight : 'auto'
                                                    }
                                                }
                                                "
                                            >是否显示</th>
                                            <th data-options="field:'remark',width:150,editor:'text'">备注</th>
                                        </tr>
                                    </thead>
                                </table>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript">

	var disabledItem = true;
	$(function(){
		
		// 初始化页面时，启用【新增】按钮，禁用【详细】Tab页
	    enableButtons(["add"]);
	    $("#mainTabs").tabs("disableTab", 1);
	    
	    $("#mainTabs").tabs({
	        onSelect : function(title, index) {
	            if (index == 0) {
	            	disabledItem = true;
	                if ($("#listGrid").datagrid("getSelections").length > 0) {
	                    enableButtons(["add", "del", "edit"]);
	                    $("#mainTabs").tabs("enableTab", 1);
	                } else {
	                    enableButtons(["add"]);
	                    $("#mainTabs").tabs("disableTab", 1);
	                }
	            }
	            if (index == 1) {
	                resetDatagridHeight("#sub", "#subTabs");
	                
	                if ($("#listGrid").datagrid("getSelections").length > 0) {
	                    enableButtons(["add", "del", "save"]);
	                    loadDictData();
	                } else {
	                    enableButtons(["add", "save"]);
	                }
	              }
	            }
	    });
	    
	    //主列表
	    $("#listGrid").datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			border : false,
			pageSize : defaultPageSize,
			pageList : defaultPageList,
			pagination : true,
			url : "${ctx}/dict/search",
				columns : [[
					{field : "dictTypeId", title : "字典主表主键"},
					{field : "dictTypeCode", title : "字典编码"},
					{field : "dictTypeName", title : "字典名称"},
					{field : "enableName", title : "是否有效"}
			]],
	        onSelect: function(rowIndex, rowData) {
	            enableButtons(["add", "del", "edit"]);
	            $("#mainTabs").tabs("enableTab", 1);
	            disabledItem = true;
	        },
	        onUnselectAll: function(rowIndex, rowData) {
	            var tab = $("#mainTabs").tabs("getSelected");
	            var index = $("#mainTabs").tabs("getTabIndex", tab);
	            if (index == 0) {
	                enableButtons(["add"]);
	                $("#mainTabs").tabs("disableTab", 1);
	            }
	            if (index == 1) {
	                enableButtons(["add", "save"]);
	            }
	        },
			onLoadSuccess : function(data) {
				$("#listGrid").datagrid("unselectAll");
			}
	    }); 
	    
	    $("#listGrid").datagrid("getPager").pagination({
			layout : defaultPageLayout
		}); 
	    
	    $("#dataForm").form({
	        onLoadSuccess: function(data) {
	        	if (data.gxwlSysDictLs) {
		            $("#dg_gxwlSysDictLs").datagrid("loadData", data.gxwlSysDictLs);
	        	}
	        	disableItem(disabledItem);
	        }
	    });
	    
	    $("#detailTab").click(function(){
	    	$("#dg_gxwlSysDictLs").datagrid("acceptChanges");
			$("#dg_gxwlSysDictLs").datagrid("unselectAll");
		});
	    
	});

	// 设置按钮 
    function enableButtons(buttons){
        $(".easyui-linkbutton.toolbar").linkbutton("disable");
        for (var i = 0; i < buttons.length; i++) {
            $("#"+buttons[i]).linkbutton("enable");
        }
    }

	// 新增
    function add(){
    	disabledItem = false;
		disableItem(disabledItem);
        $("#dataForm").form("clear");
        $('#dg_gxwlSysDictLs').datagrid('loadData',{total:0,rows:[]});
        $("#listGrid").datagrid("unselectAll");
        $("#mainTabs").tabs("enableTab", 1);
        $("#mainTabs").tabs("select", 1);
        $("#dictTypeCode").focus();
    }
    
	// 删除
    function del() {
        var row = $("#listGrid").datagrid('getSelected');
        if (row) {
            $.messager.confirm("温馨提示", "确定删除数据字典【" + row.dictTypeName + "】?",
                function(r) {
                    if (r) {
                    	$.messager.progress();
                        $.ajax({
                            url : "${ctx}/dict/del/" + row.dictTypeId
                        }).done(function(res) {
                        	$.messager.progress("close");
                        	if (res.resultCode == "0") {
                       	 		$("#dataForm").form("clear");
	                            $("#listGrid").datagrid("unselectAll");
	                            $("#mainTabs").tabs("select", 0);
	                            query(); // 刷新列表页
	                            $.messager.show({
	                                title: "温馨提示",
	                                msg: "操作成功",
	                                timeout: 2500,
	                                showType: "slide"
	                            });
                        	}
                        }).fail(function(jqXHR, textStatus, errorThrown) {
            				$.messager.progress("close");
            				$.messager.alert("温馨提示", "保存出错！", "error");
            			});
                    }
                });
        }
    }
    
 	// 修改字典
    function edit() {
		var tab = $("#mainTabs").tabs("getSelected");
		var index = $("#mainTabs").tabs("getTabIndex", tab);
		disabledItem = false;
		if (index == 0) {
			$("#mainTabs").tabs("select", 1);
		}
		if (index == 1) {
			loadDictData();
		}
 	}
	
	// 加载字典
    function loadDictData() {
        var row = $("#listGrid").datagrid("getSelected");
        if (row) {
            $.messager.progress();
        	$.ajax({
    			type: "GET",
    			url: "${ctx}/dict/get/" + row.dictTypeId,
    		}).done(function(res){
    			$.messager.progress("close");
    			if (res.resultCode == "0") {
    				$("#dataForm").form("load", res.row);
		        	$("#mainTabs").tabs("select", 1);
    			} else {
    				$.messager.alert("温馨提示", res.resultMsg, "error");
    			}
    		}).fail(function(jqXHR, textStatus, errorThrown) {
    			$.messager.progress("close");
    			$.messager.alert("温馨提示", "加载出错！", "error");
    		});
        }
    }

	// 保存
    function save() {
    	if($("#dataForm").form("validate")){
	        $.messager.progress();
	        $("#dg_gxwlSysDictLs").datagrid("acceptChanges");
			$("#dg_gxwlSysDictLs").datagrid("unselectAll");
	        var data = getFormData("#dataForm");
	        $.ajax({
	        	url: "${ctx}/dict/save",
	        	type : "post",
	        	data : data
	        }).done(function(res){
	        	$.messager.progress("close");
				if (res.resultCode == "0") {
					if ($("#dictTypeId").val()) {
						$("#dataForm").form("load", res.row);
					} else {
						$("#dataForm").form("load", res.row);
						query();
					}
					$.messager.show({
						title: "温馨提示",
						msg: "操作成功",
						timeout: 2500,
						showType: "slide"
					});
				} else {
					$.messager.alert("温馨提示", res.resultMsg, "error");
				}
	        }).fail(function(jqXHR, textStatus, errorThrown) {
				$.messager.progress("close");
				$.messager.alert("温馨提示", "保存出错！", "error");
			});
    	}
    }
 
	function disableItem(disable) {
		if (disable) {
			enableButtons(["add", "del", "edit"]);
			$("#dataForm").form("disableValidation");
		}
		setFormItemDisabled("#dataForm", disable);
		if (!disable) {
			enableButtons(["add", "del", "save"]);
			$("#dataForm").form("enableValidation");
		}
		$("#addDict").linkbutton(disable?"disable":"enable");
		$("#removeDict").linkbutton(disable?"disable":"enable");
		$("#dg_gxwlSysDictLs").datagrid({
			onClickRow : disable?function(){}:onDg_gxwlSysDictLsClickRow
		});
	}
	
    function onDg_gxwlSysDictLsClickRow(index) {
    	$("#dg_gxwlSysDictLs").datagrid("acceptChanges");
		$("#dg_gxwlSysDictLs").datagrid("unselectAll");
    	$("#dg_gxwlSysDictLs").datagrid("selectRow", index).datagrid("beginEdit", index);
    }
	
    //添加数据字典项
	function addDict() {
        var data = {
			dictTypeId : $("#dictTypeId").val(),	
			isDisplay : "Y"
        };
        $("#dg_gxwlSysDictLs").datagrid('insertRow',{index : 0,  row: data}); 
	}
    
    // 移除数据字典项
    function removeDict() {
        var rows = $("#dg_gxwlSysDictLs").datagrid("getSelections");
        var copyRows = [];
        for (var j= 0; j < rows.length; j++) {
			copyRows.push(rows[j]);
		}
        for(var i =0;i<copyRows.length;i++) {    
            var index = $("#dg_gxwlSysDictLs").datagrid("getRowIndex", copyRows[i]);
            $("#dg_gxwlSysDictLs").datagrid("deleteRow", index); 
		}
	}
	
	// 查询
	function query(){
		$("#listGrid").datagrid("load", getFormData("#queryForm"));
	}
	
	// 同步数据字典
	function syncDict() {
        $.messager.progress();
        $.ajax({
        	url: "${ctx}/dict/syncDict",
        }).done(function(res){
        	$.messager.progress("close");
			if (res.resultCode == "0") {
				$.messager.show({
					title: "温馨提示",
					msg: "操作成功",
					timeout: 2500,
					showType: "slide"
				});
			} else {
				$.messager.alert("温馨提示", res.resultMsg, "error");
			}
        }).fail(function(jqXHR, textStatus, errorThrown) {
			$.messager.progress("close");
			$.messager.alert("温馨提示", "保存出错！", "error");
		});
	}
    
</script>
</body>
</html>