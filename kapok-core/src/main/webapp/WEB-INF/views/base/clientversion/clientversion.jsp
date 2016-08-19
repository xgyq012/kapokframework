<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>版本管理</title>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css" type="text/css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/page.css">
</head>

<body>

<div class="g-layout">


	<div class="g-toolbar">
		<a id="add" href="javascript:void(0);" class="easyui-linkbutton toolbar  g-button"   onclick="add();" ><i class="fa fa-plus"></i>新增</a>
		<a id="del" href="javascript:void(0);" class="easyui-linkbutton toolbar  g-button"  onclick="del();"><i class="fa fa-trash-o"></i>删除</a>
		<a id="edit" href="javascript:void(0);" class="easyui-linkbutton toolbar  g-button" onclick="edit();"><i class="fa fa-edit"></i>修改</a>
		<a id="save" href="javascript:void(0);" class="easyui-linkbutton toolbar  g-button"  onclick="save();"><i class="fa fa-floppy-o"></i>保存</a>
		<a id="upgrade" href="javascript:void(0);" class="easyui-linkbutton toolbar  g-button"  onclick="upgrade();"><i class="fa fa-edit"></i>升级</a>
	</div>

	<div id="mainTabs"  class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false">

		<div title="列表"  style="position:relative;">

            <!-- 查询区域 -->
            <div id="query">
                <form id="queryForm" class="formCls" method="post">
					<table class="g-form" cellpadding="0" cellspacing="0" >
                        <tr>

							<td class="form-cell-1">
								<label class="form-label">文件号</label>
                                <input   class="easyui-validatebox form-control"  name="q_fileNumber_LIKE"/>
                            </td>

							<td class="form-cell-1">
								<label class="form-label">文件名</label>
                                <input   class="easyui-validatebox form-control"  name="q_fileName_LIKE" />
                            </td>

							<td class="form-cell-1">
								<label class="form-label">客户端类型</label>
                                <input name="q_clientType_EQ" class="easyui-datebox form-control"
									   style="width:${comboboxWidth};height:${comboboxHeight}px"
									   data-options="
                                        panelHeight:'auto',
                                        editable:false,
                                        valueField:'dictCode',
                                        textField:'dictName',
                                        url:'${ctx}/dict/getdict/SysClientType'"/>
                            </td>

                            <td  class="form-cell-1 f-button">
                                <a href="javascript:void(0);" onclick="query();"  class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
                                <a href="javascript:void(0);" onclick="$('#queryForm').form('clear');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
                            </td>

                        </tr>
                    </table>
                </form>
            </div>

            <!-- 列表区域 -->
			<div class="list-area" style="top:50px;">
				<table id="listGrid" style="height:100%"></table>
			</div>
        </div>

        <!-- 实体详细 -->
		<div id="minute" data-options="disabled:true"  title="详细"   >
           <form id="dataForm" class="formCls" method="post">
                <!-- 表单区域 -->
                <div id="form">
                    <input id="versionId" name="versionId" type="hidden"/>
                    <input id="createrId" name="createrId" type="hidden"/>
					<input id="createTime" name="createTime" type="hidden"/>
					<table class="g-form" cellpadding="0" cellspacing="0" >

						<tr>
							<td class="form-cell-1">
								<label class="form-label">文件号</label>
									<input id="fileNumber" name="fileNumber" class="easyui-validatebox form-control"  data-options="required:true"/>
								</td>
							<td class="form-cell-1">
								<label class="form-label">版本号</label>
								<input id="versionNumber" name="versionNumber"  class="easyui-validatebox form-control" data-options="required:true"/>
							</td>
							<td class="form-cell-1">
								<label class="form-label">客户端类型</label>
								<input id="clientType" name="clientType" class="easyui-combobox form-control"
									   style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
										required:true,
										editable:false,
										panelHeight:'auto',
										valueField:'dictCode',
										textField:'dictName',
										url:'${ctx}/dict/getdict/SysClientType'"/>
							</td>
							<td class="form-cell-1">
								<label class="form-label">是否当前版本</label>
								<input id="isLast" name="isLast" class="easyui-combobox form-control"
									   style="width:${comboboxWidth};height:${comboboxHeight}px"
									   data-options="
	                                        required:true,
	                                        panelHeight:'auto',
	                                        valueField:'dictCode',
	                                        textField:'dictName',
	                                        url:'${ctx}/dict/getdict/YesOrNo'"/>
							</td>
                        </tr>

                        <tr>
							<td class="form-cell-1">
								<label class="form-label">文件</label>
								<input id="clientUrl" name="clientUrl" type="hidden"/>
								<input id="fileName" name="fileName"  class="easyui-validatebox form-control"  />
							</td>
						</tr>

                        <tr>
							<td class="form-cell-4" colspan="4">
								<label class="form-label">更新说明</label>
								<textarea  id="versionDesc" name="versionDesc"  class="easyui-validatebox form-control"   rows="5" ></textarea>
							</td>
						</tr>
						<tr>
							<td class="form-cell-4" colspan="4">
								<label class="form-label">备注</label>
								<textarea  id="remark" name="remark" class="easyui-validatebox form-control"   rows="5" ></textarea>
							</td>
						</tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>

<script type="text/javascript">

	var disabledItem = true;
	var fileNameUpload;
	
	$(document).ready(function() {
		
		$("#testfile").on("mouseover", function(){
			$("#testbtn").trigger("mouseover");
		})
		
		fileNameUpload = $("#fileName").fileupload({
			url : "${ctx}/doc/upload",
			upfile : "upfile",
			filetype : "apk",
			required : true,
			params : {
				directory : "ClientVersionFile"
			},
			onUploadComplete : function(result) {
				$("#clientUrl").val(result.docId);
			}
		});
		
		// 初始化页面时，启用【新增】按钮，禁用【详细】Tab页
	    enableButtons(["add"]);
	    $("#mainTabs").tabs("disableTab", 1);
	    
	    $("#mainTabs").tabs({
	        onSelect : function(title, index) {
	            if (index == 0) {
	            	disabledItem = true;
	                if ($("#listGrid").datagrid("getSelections").length > 0) {
						enableButtons(["add", "del", "edit", "upgrade"]);							
	                    $("#mainTabs").tabs("enableTab", 1);
	                } else {
	                    $("#mainTabs").tabs("disableTab", 1);
	                }
	            }
	            if (index == 1) {
	                if ($("#listGrid").datagrid("getSelections").length > 0) {
	                    loadData();
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
			url : "${ctx}/clientversion/search",
				columns : [[
					{field : "fileNumber", title : "文件号"},
					{field : "clientType", title : "客户端类型"},
					{field : "fileName", title : "文件名"},
					{field : "versionNumber", title : "版本号"},
					{field : "isLastName", title : "最新版本", align : "center"}
			]],
	        onSelect : function(rowIndex, rowData) {
	            enableButtons(["add", "del", "edit", "upgrade"]);
	            $("#mainTabs").tabs("enableTab", 1);
	            disabledItem = true;
	        },
	        onDblClickRow : function(rowIndex, rowData) {
	            enableButtons(["add", "del", "edit", "upgrade"]);
	            $("#mainTabs").tabs("select", 1);
	            disabledItem = true;
	        },
	        onUnselectAll : function(rowIndex, rowData) {
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
	        	disableItem(disabledItem);
	        }
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
    function add() {
    	disabledItem = false;
		disableItem(disabledItem);
        $("#dataForm").form("clear");
        $("#listGrid").datagrid("unselectAll");
        $("#mainTabs").tabs("enableTab", 1);
        $("#mainTabs").tabs("select", 1);
        $("#fileNumber").focus();
        
        $("#clientType").combobox("setValue", "Android");
        $("#isLast").combobox("setValue", "Y");
    }
    
	// 删除
    function del() {
        var row = $("#listGrid").datagrid("getSelected");
        if (row) {
        	if (row.isLast == 'Y') {
        		$.messager.alert("温馨提示", "最新版本不能删除！", "warning");
        		return;
        	}
            $.messager.confirm("温馨提示", "确定删除数据【" + row.fileNumber + "】?",
                function(r) {
                    if (r) {
                    	$.messager.progress();
                        $.ajax({
                            url : "${ctx}/clientversion/del/" + row.versionId
                        }).done(function(res) {
                        	$.messager.progress("close");
                        	if (res.resultCode == "0") {
                       	 		$("#dataForm").form("clear");
	                            $("#listGrid").datagrid("unselectAll");
	                            $("#mainTabs").tabs("select", 0);
	                            $("#listGrid").datagrid("reload", getFormData("#queryForm"));
	                            $.messager.show({
	                                title: "温馨提示",
	                                msg: "操作成功",
	                                timeout: 2500,
	                                showType: "slide"
	                            });
                        	}
                        }).fail(function(jqXHR, textStatus, errorThrown) {
            				$.messager.progress("close");
            				$.messager.alert("温馨提示", "删除出错！", "error");
            			});
                    }
                });
        }
    }
    
 	// 修改
    function edit() {
		var tab = $("#mainTabs").tabs("getSelected");
		var index = $("#mainTabs").tabs("getTabIndex", tab);
		disabledItem = false;
		if (index == 0) {
			$("#mainTabs").tabs("select", 1);
		}
		if (index == 1) {
			loadData();
		}
 	}
	
	// 加载
    function loadData(versionId) {
    	var row = $("#listGrid").datagrid("getSelected");
    	versionId = versionId?versionId:row?row.versionId:null;
        if (versionId) {
            $.messager.progress();
        	$.ajax({
    			type: "GET",
    			url: "${ctx}/clientversion/get/" + versionId,
    		}).done(function(res){
    			$.messager.progress("close");
    			if (res.resultCode == "0") {
    				$("#dataForm").form("load", res.row);
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
    	if($("#dataForm").form("validate")) {
	        $.messager.progress();
	        var data = getFormData("#dataForm");
	        $.ajax({
	        	url: "${ctx}/clientversion/save",
	        	type : "post",
	        	data : data
	        }).done(function(res){
	        	$.messager.progress("close");
				if (res.resultCode == "0") {
// 					loadData(res.row.versionId);
// 					$("#listGrid").datagrid("reload", getFormData("#queryForm"));
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
	
	// 升级
	function upgrade() {
		var row = $("#listGrid").datagrid("getSelected");
		add();
        $("#fileNumber").val(row.fileNumber);
        var index = row.versionNumber.lastIndexOf(".");
        var frontVersionNum = row.versionNumber.substr(0, index+1);
        var backVersionNum = row.versionNumber.substr(index+1);
        $("#versionNumber").val(frontVersionNum + (parseInt(backVersionNum)+1));
        $("#clientType").combobox("setValue", row.clientType);
        $("#isLast").combobox("setValue", "Y");
        $("#dataForm").form("validate");
	}
	
	function disableItem(disable) {
		if (disable) {
			enableButtons(["add", "del", "edit", "upgrade"]);
			$("#dataForm").form("disableValidation");
		}
		setFormItemDisabled("#dataForm", disable);
		if (!disable) {
			enableButtons(["add", "del", "save", "upgrade"]);
			$("#dataForm").form("enableValidation");
		}
		fileNameUpload.fileupload(disable?"disable":"enable");
	}
	
	// 查询
	function query(){
		$("#listGrid").datagrid("load", getFormData("#queryForm"));
	}

</script>
</body>
</html>