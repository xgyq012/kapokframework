<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>指令管理</title>

    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
    <link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
    <![endif]-->
    <link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
    <link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
    <link rel="stylesheet" href="${ctx}/resources/css/page.css">
</head>

<body>
<div class="g-layout">
    <!-- 按钮区域 -->
    <div class="g-toolbar">
        <a id="add" class="easyui-linkbutton toolbar g-button" onclick="add()"><i class="fa fa-plus"></i>新增同级</a>
        <a id="addNext" class="easyui-linkbutton toolbar g-button" onclick="addNext()" data-options="disabled:true"><i class="fa fa-plus"></i>新增下级</a>
        <a id="del" class="easyui-linkbutton toolbar g-button" onclick="del()" data-options="disabled:true"><i class="fa fa-trash-o"></i>删除</a>
        <a id="save" class="easyui-linkbutton toolbar g-button" onclick="save()" data-options="disabled:true"><i class="fa fa-floppy-o"></i>保存</a>
    </div>

    <!-- 实体区域 -->
    <div id="mainLayout" class="easyui-layout g-container">
        <div data-options="region:'west',split:true,minWidth:250" style="width:250px;">
            <ul id="resourceTree" class="easyui-tree " data-options="animate:true"></ul>
        </div>
        <div data-options="region:'center'">
            <form id="dataForm" method="post" style="visibility: hidden;">
                <input id="id" name="id" type="hidden">
                <input id="createrId" name="createrId" type="hidden">
                <input id="createTime" name="createTime" type="hidden">
                <input id="fullpath" name="fullpath" type="hidden">
                <input id="isBranch" name="isBranch" type="hidden">
                <input id="parentCommandId" name="parentCommandId" type="hidden">

                <table class="g-form" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td class="form-cell-4" colspan="4">
                            <label class="form-label" title="上级资源">上级资源</label>
                            <input id="parentCommandName" class="easyui-validatebox form-control" readonly>
                        </td>
                    </tr>

                    <tr>
                        <td class="form-cell-2" colspan="2">
                            <label class="form-label" title="资源名称">资源名称</label>
                            <input id="commandName" name="commandName" class="easyui-validatebox form-control"
                                   data-options="required:true,validType:'stringCheck'">
                        </td>

                        <td class="form-cell-1">
                            <label class="form-label" title="是否有效">是否有效</label>
                            <input dictCode="YesOrNo" name="enable" class="easyui-combobox form-control"
                                   style="width:${comboboxWidth};height:${comboboxHeight}px"
                                   data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
                        </td>

                        <td class="form-cell-1">
                            <label class="form-label" title="显示顺序">显示顺序</label>
                            <input  name="seq" class="easyui-validatebox form-control">
                        </td>
                    </tr>
                    <tr>
                        <td class="form-cell-2" colspan="2" >
                            <label class="form-label" title="Key">指令代码</label>
                            <input name="commandKey" class="easyui-validatebox form-control">
                        </td>
                       <%-- <td class="form-cell-1">
                            <label class="form-label" title="Key">返回类型</label>
                            <input dictCode="WX_ACTION_TYPE" name="returnType" class="easyui-combobox form-control"
                                   style="width:${comboboxWidth};height:${comboboxHeight}px"
                                   data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
                        </td>--%>
                        <td class="form-cell-2" colspan="2">
                            <label class="form-label">执行动作</label>
                            <input id="actionId" name="actionId" class="easyui-validatebox form-control" />
                        </td>
                    </tr>
                    <tr>
                        <td class="form-cell-4" colspan="4">
                            <label class="form-label" title="节点内容说明">节点内容说明</label>
                            <textarea id="nodeReturnContent" name="nodeReturnContent" class="easyui-validatebox form-control" style="height:100px"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-cell-4" colspan="4">
                            <label class="form-label" title="备注">备注</label>
                            <textarea id="remark" name="remark" class="easyui-validatebox form-control" style="height:100px"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript">

    $(function() {

        publicAttr.initCombobox();

        actionLookupFun();

        $("#resourceTree").tree({
            url : "${ctx}/command/getResource",
            formatter : function(node){
                return node.commandName;
            },
            onSelect : function(node) {
                if ($("#dataForm").css("visibility") == "hidden") {
                    $("#dataForm").css("visibility", "visible");
                }
                _loadData(node);
            }
        });

    });

    // 新增同级
    function add() {
        enableButtons(['add', 'addNext', 'save']);
        disableButtons(['del']);
        $('#dataForm').css('visibility', 'visible');
        $('#dataForm').form('clear');
        $("#enable").combobox("setValue", "Y");
        var node = $('#resourceTree').tree('getSelected');
        if (node) {
            var parentNode = $('#resourceTree').tree('getParent', node.target);
            $('#parentCommandId').val(parentNode != null ? parentNode.id : '');
            $('#parentCommandName').val(parentNode != null ? parentNode.commandName : '');
        }
    }

    // 新增下级
    function addNext() {
        var node = $('#resourceTree').tree('getSelected');
        if (node) {
            enableButtons(['add', 'addNext', 'save']);
            disableButtons(['del']);
            if (node.state == 'closed') {
                $('#resourceTree').tree('expand', node.target);
            }
            $('#dataForm').css('visibility', 'visible');
            $('#dataForm').form('clear');
            $('#enable').combobox('setValue', 'Y');
            $('#parentCommandId').val(node.id);
            $('#parentCommandName').val(node.commandName);
        } else {
            main.info('温馨提示', '请选择节点。');
        }
    }

    // 删除
    function del() {
        var node = $('#resourceTree').tree('getSelected');
        if (node) {
            main.confirm('温馨提示', '确定删除资源【' + node.commandName + '】?', function(r) {
                if (r) {
                    main.showWaiting();
                    $.ajax({
                        type : 'GET',
                        url : '${ctx}/command/del/' + node.id,
                    }).done(function(res) {
                        main.closeWaiting();
                        if (res.resultCode == '0') {
                            var selected = $('#resourceTree').tree('getSelected');
                            var parent = $('#resourceTree').tree('getParent', selected.target);
                            if (parent) {
                                var children = $('#resourceTree').tree('getChildren', parent.target);
                                if (children.length == 1) {
                                    $('#resourceTree').tree('update', {
                                        target : parent.target,
                                        isBranch : 'N'
                                    });
                                }
                            }
                            $('#resourceTree').tree('remove', selected.target);
                            enableButtons(['add']);
                            disableButtons(['addNext', 'del', 'save']);
                            $('#dataForm').css('visibility', 'hidden');
                            $('#dataForm').form('clear');
                            main.show();
                        } else {
                            main.error('温馨提示', res.resultMsg);
                        }
                    }).fail(function(jqXHR, textStatus, errorThrown) {
                        main.closeWaiting();
                        main.error('温馨提示', '删除时出错！');
                    });
                }
            });
        }
    }

    // 保存
    function save() {
        if ($("#dataForm").form("validate")) {
            main.showWaiting();
            var data = getFormData("#dataForm");
            $.ajax({
                type: "POST",
                url: "${ctx}/command/save",
                data: data
            }).done(function(res) {
                main.closeWaiting();
                if (res.resultCode == "0") {
                    if ($("#id").val()) {
                        $("#dataForm").form("load", res.row);
                    } else {
                        _refreshTree($("#resourceTree").tree("getSelected"), res.row);
                    }
                    main.show();
                } else {
                    main.error('温馨提示', res.resultMsg);
                }
            }).fail(function(jqXHR, textStatus, errorThrown) {
                main.closeWaiting();
                main.error('温馨提示', '保存时出错！');
            });
        }
    }

    function _loadData(node) {
        main.showWaiting();
        $.ajax({
            type: 'GET',
            url: "${ctx}/command/get/" + node.id,
        }).done(function(res){
            main.closeWaiting();
            if (res.resultCode == '0') {

                // 基本信息加载数据
                $('#dataForm').form('load', res.row);

                $("#actionId").lookup("setVal",res.row.actionId);
                $("#actionId").lookup("setTxt",res.row.actionName);

                // 设置上级信息
                var parentNode = $('#resourceTree').tree('getParent', node.target);
                $('#parentCommandId').val(parentNode != null ? parentNode.id : '');
                $('#parentCommandName').val(parentNode != null ? parentNode.commandName : '');

                // 控制按钮
                if (node.isBranch == 'N') {
                    enableButtons(['add', 'addNext', 'del', 'save']);
                } else {
                    enableButtons(['add', 'addNext', 'save']);
                    disableButtons(['del']);
                }
            } else {
                main.error('温馨提示', res.resultMsg);
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
            main.closeWaiting();
            main.error('温馨提示', '加载时出错！');
        });
    }

    function _refreshTree(node, obj) {
        if (!node) {
            $("#resourceTree").tree("reload");
            return;
        }
        if (node.id != $("#parentCommandId").val()) {
            // 新增同级
            if (node) {
                $("#resourceTree").tree("insert", {
                    after : node.target,
                    data : obj
                });
            }
        } else {
            // 新增下级
            $("#resourceTree").tree("append", {
                parent : node.target,
                data : obj
            });
            if (node.isBranch == "N") {
                $("#resourceTree").tree("update", {
                    target : node.target,
                    isBranch : "Y"
                });
            }
        }
        var newNode = $("#resourceTree").tree("find", obj.id);
        $("#resourceTree").tree("select", newNode.target);
    }

    //获取当前动作id
    function getActionId(){

        return  $('#actionId').lookup('getVal');
    }

    var actionLookup;

    function actionLookupFun() {

        actionLookup = $("#actionId").lookup({
            title: "选择动作",
            url:  "${ctx}/action/select-action",
            width: 700,
            height: 440,
            contentWindow: {
                params: {
                    singleSelect : true,
                    checkbox : false,
                    queryParams : function (){
                        return {
                            actionId:getActionId()
                        };
                    }
                },
                actions: {
                    confirm: function(data) {
                        if (data) {
                            $("#actionId").lookup("setVal",data.actionId);
                            $("#actionId").lookup("setTxt",data.actionName);
                        }
                        main.closeDialog();
                    },
                    cancel : function() {
                        main.closeDialog();
                    }
                }
            }
        });

    }


</script>
</body>
</html>