<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>图标管理</title>
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
        <a onclick="baseInfo.add();" id="add" class="easyui-linkbutton toolbar g-button"><i
                class="fa fa-plus"></i>新增</a>
        <a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
        <a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i
                class="fa fa-floppy-o"></i>保存</a>
        <a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
    </div>


    <div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false">

        <div title="列表" style="position:relative;">
            <div id="query">
                <form id="queryForm" class="formCls" method="post">
                    <table class="g-form" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">图标名称</label>
                                <input name="q_iconName_LIKE" class="easyui-validatebox form-control"/>
                            </td>
                            <td class="form-cell-1"></td>
                            <td class="form-cell-1"></td>
                            <td class="form-cell-1 f-button">
                                <a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i
                                        class="fa fa-search"></i>查询</a>
                                <a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i
                                        class="fa fa-reply"></i>重置</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>

            <div class="list-area" style="top:50px;">
                <table id="listGrid" style="height:100%"></table>
            </div>

        </div>

        <div id="minute" data-options="disabled:true" title="详细">

            <form id="baseForm" action="">
            	<fieldset>
                <legend>图标管理</legend>

                    <!-- 隐藏属性 -->
                    <input id="iconId" name="iconId" type="hidden"/>
                    <input id="createrId" name="createrId" type="hidden"/>
                    <input id="createTime" name="createTime" type="hidden"/>

                    <table class="g-form" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">图标路径</label>
                                <input id="iconUrl" name="iconUrl" class="easyui-validatebox form-control" disabled="disabled" />
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">图标大小</label>
                                <input class="easyui-validatebox form-control"
                                       name="picSize"/>
                            </td>
                            <td class="form-cell-1">
                            	
                            </td>
                            <td class="form-cell-1"></td>
                        </tr>

                        <tr>
                            <td class="form-cell-4" colspan="4">
                                <label class="form-label">描述</label>
                                <textarea name="picDescribe" class="easyui-validatebox form-control"
                                          rows="4"></textarea>
                            </td>      
                        </tr>
                        
                    </table>
            	</fieldset>
            	<fieldset>
					<legend>图标上传</legend>
					<table class="g-form" cellpadding="0" cellspacing="0" >
					<tbody>
						<tr>
							
							<td class="form-cell-1" colspan="1">
		     				    <label class="form-label">图标名称</label>
		     				    <input id="iconName" name="iconName" class="easyui-validatebox form-control" type="text" style="width:${comboboxWidth};height:${comboboxHeight}px"/>    					
		     				</td>
		     				<td class="form-cell-1">
		     					<input id="photofileID" name="imgId" type="hidden" >
		     					<input id="docShowname" name="docShowname" type="hidden" >
		     					<a onclick="uploadBtn.fileupload('click'); return false;" title="上传图片" id="upfile" class="easyui-linkbutton toolbar g-button"><i class="fa fa-arrow-circle-up"></i>图片上传</a>			     						
		     					<div style="display: none;" id="addPhoto"></div>
		     				</td>
							<td class="form-cell-1"></td>
							<td class="form-cell-1"></td>
						</tr>
						<tr>
							<td class="form-cell-4" colspan="4">
								<img alt="" src="" id="img" style="margin-left: 86px;"/>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gx-validate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript">
	var uploadBtn;
    var thisUiConfig = {
        main: window.main,
        ctx: "${ctx}",
        url: "iconManage",
        id: "#iconId",    //表单中主键字段对应的控件的Id属性值
        idName: "iconId",
        tableList: "#listGrid",
        getId: function () {
            var id = $(thisUiConfig.id).val();
            return id;
        }
    };

    $(function () {
    	importDoc.init();
        tabs.init();
        enableButtons(['add']);
        tableInfo.init();
        baseInfo.init();
        //orgLookup();
        publicAttr.initCombobox();
    });
	
	var importDoc = {
				
		init : function (){
			
			
			uploadBtn = $("#addPhoto").fileupload({
				url : thisUiConfig.ctx + "/doc/upload",
				upfile : "upfile",
				filetype : "jpg|jpeg|png|gif",
				hidden : true,
				params : {
					directory :"gis"
				},				
				onUploadComplete : function(result) {
					if (result.errcode) {
						$.messager.alert("温馨提示", result.errmsg, "info");
						return ;						
					}
					var picRealWidth, picRealHeight;
					$("#img").load(function() {
				        picRealWidth = this.width;
				        picRealHeight = this.height;
				        if(picRealWidth > 30 && picRealHeight > 30){
				        	$("#img").css("width","30px").css("height","30px");  
			            }else{
			            	$("#img").css("width",picRealWidth+'px').css("height",picRealHeight+'px');  
			            }
			            	
					});
		 			$("input[name='imgId']").val(result.docId);
					$("#iconName").val(result.docShowname);
					$("#iconUrl").val(result.docUri);
					baseInfo.showImg();	
				}
			});
		}			
	};
	
    var tabs = {

        isEdit: true,

        init: function () {

            $("#mainTab").tabs({
                onSelect: function (title, index) {
                    if (index == 0) { // 选中列表标签
                        $("#baseForm").form("clear");
                        if ($("#listGrid").datagrid("getSelections").length > 0) {
                            $("#mainTab").tabs("enableTab", 1);
                            enableButtons(["add", "del", "edit"]);
                        } else {
                            $("#mainTab").tabs("disableTab", 1);
                            enableButtons(["add"]);
                        }
                        tabs.isEdit = false;
                        setFormItemDisabled("#baseForm", true, null);
                    } else if (index == 1) { // 选中详细标签
                        loadData();
                        if (tabs.isEdit) {
                            setFormItemDisabled("#baseForm", false, null);
                            enableButtons(["add", "del", "save","upfile"]);
                        } else {
                            enableButtons(["add", "del", "edit"]);
                        }
                    }
                }
            });
        }

    };


    //基础信息初始化
    var baseInfo = {

        saveUrl: thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",

        getUrl: thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",

        delUrl: thisUiConfig.ctx + "/" + thisUiConfig.url + "/del/",

        search: thisUiConfig.ctx + "/" + thisUiConfig.url + "/search ",

        init: function () {
            setFormItemDisabled("#baseForm", true, null);
        },

        save: function () {

            if ($("#baseForm").form("validate")) {
                var data = getFormData("#baseForm");
                if (data) {
                    publicFormControl.ajax({
                        type: "POST",
                        url: baseInfo.saveUrl,
                        data: data
                    }, function (result) {
                        var row = result.row;
                        $("#baseForm").form("load", row);
                        tableInfo.query();
                        enableButtons(["add", "save","del"]);
                    }, "show");

                }
            }

        },

        add: function () {
            tabs.isEdit = false;
            $("#listGrid").datagrid("unselectAll");
            $("#mainTab").tabs("enableTab", 1);
            $("#mainTab").tabs("select", 1);
            setFormItemDisabled("#baseForm", false, null);
            enableButtons(["add", "save","upfile"]);
            $("#baseForm").form("clear");
            $("#photofileID").val("");
    		$("#docShowname").val("");
        },

        del: function () {
            var row = $("#listGrid").datagrid('getSelected');
            var id = $(thisUiConfig.id).val();
            if (!row) {
                if(!id){
                    $.messager.alert("温馨提示", "请选择删除的信息！", "info");
                    return;
                }
            }
            $.messager.confirm("温馨提示", "确定删除图标为【" + ($("input[name='iconName']").val() || row.iconName) + "】的记录?",
                    function (r) {
                        if (r) {
                            id = id || row[thisUiConfig.idName] ;
                            publicFormControl.ajax({
                                url: baseInfo.delUrl + id,
                                type: "POST"
                            }, function (data) {
                                if (data.resultCode == "0") {
                                    $("#baseForm").form("clear");
                                    $("#mainTab").tabs("select", 0);
                                    $("#mainTab").tabs("disableTab", 1);
                                    enableButtons(["add"]);
                                    tableInfo.query();
                                }
                            }, "show");
                        }
                    });

        },

        edit: function () {
            tabs.isEdit = true;
            var tab = $('#mainTab').tabs('getSelected');
            var index = $('#mainTab').tabs('getTabIndex', tab);
            if (index != 1) {
                $("#mainTab").tabs("enableTab", 1);
                $("#mainTab").tabs("select", 1);
            } else {
                enableButtons(["add", "del", "save", "upfile"]);
            }
            setFormItemDisabled("#baseForm", false, null);
        },
		showImg : function (){
    		
    		var pictureId = "";
    		var picture = "";
    		
    		var row = $("#listGrid").datagrid("getSelected");
    		if(row != null && ($("input[name='imgId']").val() == null)){
    			pictureId = row.imgId;
    		}else{
    			pictureId = $("input[name='imgId']").val();
    		}
    		
    		if(pictureId > 0){
    			picture = "${ctx}/doc/show/" + pictureId;
    		}
    		
    		$("#img").attr("src", picture);
    		
    	} 
    };

    function loadData() {
        var row = $(thisUiConfig.tableList).datagrid("getSelected");
        if (row) {
            var id = row[thisUiConfig.idName];
            if (id) {
                publicFormControl.ajax({
                    type: "POST",
                    url: baseInfo.getUrl + id,
                }, function (result) {
                    var row = result.row;
                    $("#baseForm").form("load", row);
                    baseInfo.showImg();
                });

            }
        }
    }


    //列表加载
    var tableInfo = {

        //初始化列表
        init: function () {

            $("#listGrid").datagrid({
                rownumbers: true,
                singleSelect: true,
                autoRowHeight: false,
                border: false,
                pageSize: defaultPageSize,
                pageList: defaultPageList,
                pagination: true,
                queryParams: tableInfo.getQueryFormData("#queryForm"),
                url: thisUiConfig.ctx + "/" + thisUiConfig.url + "/search",
                columns: [[{
                    field: "iconId",
                    hidden: true,
                }, {
                    field: "iconName",
                    title: "图标名称",
                    width: 150,
                    align: 'left',
                    halign: 'center'
                }, {
                    field: "picDescribe",
                    title: "描述",
                    width: 180,
                    align: 'left',
                    halign: 'center'
                }]],

                onSelect: function (rowIndex, rowData) {
                    tabs.isEdit = false;
                    var len = $("#listGrid").datagrid("getSelections").length;
                    if (len == 1) {
                        $("#mainTab").tabs("enableTab", 1);
                        enableButtons(["add", "del", "edit"]);
                    } else if (len == 0) {
                        $("#mainTab").tabs("disableTab", 1);
                        enableButtons(["add"]);
                    } else {
                        enableButtons(["add", "del"]);
                        $("#mainTab").tabs("disableTab", 1);
                    }
                },
                onUnselect: function (rowIndex, rowData) {
                    var len = $("#listGrid").datagrid("getSelections").length;

                    if (len == 1) {
                        $("#mainTab").tabs("enableTab", 1);
                        enableButtons(["add", "del", "edit", "upfile"]);
                    } else if (len == 0) {
                        enableButtons(["add"]);
                        $("#mainTab").tabs("disableTab", 1);
                    } else {
                        enableButtons(["add", "del"]);
                        $("#mainTab").tabs("disableTab", 1);
                    }
                },
                onDblClickRow:function (index,row) {
                    $("#mainTab").tabs("select",1);
                },
                onLoadSuccess: function (data) {
                    $("#listGrid").datagrid("unselectAll");
                }

            });

            // 设置分页显示形式
            $("#listGrid").datagrid("getPager").pagination({
                layout: defaultPageLayout
            });
        },

        query: function () {
            var param = tableInfo.getQueryFormData("#queryForm");
            enableButtons(["add"]);
            $("#listGrid").datagrid("load", param);
        },
        getQueryFormData: function (formId) {
            var data = getFormData(formId) || {};
            return data;
        }

    };


    function enableButtons(ids) {
        // 把所有按钮设置为不可用
        $(".easyui-linkbutton.toolbar").linkbutton("disable");
        $(".easyui-splitbutton.toolbar").linkbutton("disable");
        // 设置按钮可用
        for (var i = 0; i < ids.length; i++) {
            $("#" + ids[i]).linkbutton("enable");
        }
    }

</script>

</body>
</html>