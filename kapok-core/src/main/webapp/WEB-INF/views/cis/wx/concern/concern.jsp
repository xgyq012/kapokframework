<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>欢迎词配置</title>
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
        <a onclick="baseInfo.add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
        <a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
        <a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
        <a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
    </div>

    <div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

        <div title="列表" style="position:relative;">

            <div id="query">
                <form id="queryForm" class="formCls" method="post">
                    <table  class="g-form" cellpadding="0" cellspacing="0" >
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">回复内容</label>
                                <input name="q_replyContent_LIKE" class="easyui-validatebox form-control"  /></td>
                            <td class="form-cell-1 f-button">
                                <a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
                                <a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
                            </td>
                            <td class="form-cell-1"></td>
                            <td class="form-cell-1"></td>
                        </tr>
                    </table>
                </form>
            </div>

            <div class="list-area" style="top:50px;">
                <table id="listGrid" style="height:100%"></table>
            </div>

        </div>

        <div id="minute" data-options="disabled:true"  title="详细"   >

            <form id="baseForm"  action="">
                <!-- 隐藏属性 -->
                <input id="replyId" name="replyId" type="hidden" />
                <input id="createrId" name="createrId" type="hidden" />
                <input id="createTime" name="createTime" type="hidden" />

                <table class="g-form" cellpadding="0" cellspacing="0" >
                    <tr>
                        <td class="form-cell-2" colspan="2">
                            <label class="form-label">回复类型</label>
                            <input dictCode="WX_REPLY_TYPE" name="replyType" class="easyui-combobox form-control"
                                   style="width:${comboboxWidth};height:${comboboxHeight}px"
                                   data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
                        </td>
                        <td class="form-cell-2" colspan="2">
                            <label class="form-label">是否可用</label>
                            <input dictCode="enable" name="isUsed" class="easyui-combobox form-control"
                                   style="width:${comboboxWidth};height:${comboboxHeight}px"
                                   data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
                        </td>
                    </tr>

                    <tr>
                        <td colspan="4" class="form-cell-4">
                            <label class="form-label">回复内容</label>
                            <textarea name="replyContent" class="easyui-validatebox form-control" rows="5" ></textarea>
                        </td>
                    </tr>

					<tr>
                        <td class="form-cell-1">
                            <label class="form-label">创建人</label>
                            <input id="creater" name="creater"  class="easyui-validatebox form-control" disabled="disabled"/>
                        </td>
                        <td class="form-cell-1">
                            <label class="form-label">创建时间</label>
                            <input id="createTime" type="text" name="createTime" disabled="disabled"
                                   class="easyui-validatebox form-control" />
                        </td>
                        <td class="form-cell-1">
                            <label class="form-label">最后修改人</label>
                            <input id="lastUpdater" name="lastUpdater"   disabled="disabled"  class="easyui-validatebox form-control" />
                        </td>
                        <td class="form-cell-1">
                            <label class="form-label">最后修改时间</label>
                            <input id="lastUpdateTime" name="lastUpdateTime" type="text" disabled="disabled"
                                   class="easyui-validatebox form-control" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" >

	var exclude = "#creater,#createTime,#lastUpdater,#lastUpdateTime,#sendTime,#sendStatus";
	
    var thisUiConfig = {
        main:window.main,
        ctx : "${ctx}",
        url : "concern",
        id : "#replyId",    //表单中主键字段对应的控件的Id属性值
        idName:"replyId",
        tableList : "#listGrid",
        getId : function (){
            var actionId = $(thisUiConfig.id).val();
            return actionId;
        }
    }

    $(function (){
        tabs.init();
        enableButtons(['add']);
        tableInfo.init();
        baseInfo.init();
        publicAttr.initCombobox();
    });


    var tabs = {

        isEdit : true ,

        init : function (){

            $("#mainTab").tabs({
                onSelect : function(title, index) {
                    if (index == 0) { // 选中列表标签
                        $("#baseForm").form("clear");
                        if ($("#listGrid").datagrid("getSelections").length > 0) {
                            $("#mainTab").tabs("enableTab", 1);
                            enableButtons([ "add", "del", "edit" ]);
                        } else {
                            $("#mainTab").tabs("disableTab", 1);
                            enableButtons([ "add" ]);
                        }
                        tabs.isEdit = false;
                        setFormItemDisabled("#baseForm",true,exclude);
                    } else if (index == 1) { // 选中详细标签
                        loadData();
                        if(tabs.isEdit){
                            setFormItemDisabled("#baseForm",false,exclude);
                            enableButtons([ "add", "del", "save" ]);
                        }else{
                            enableButtons([ "add", "del","edit"]);
                        }
                    }
                }
            });
        }

    }



    //基础信息初始化
    var baseInfo =  {

        saveUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",

        getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",

        delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/delSign/",

        search : thisUiConfig.ctx + "/" + thisUiConfig.url + "/search ",

        init : function (){

            setFormItemDisabled("#baseForm",true,exclude);

        },

        save : function (){

            if($("#baseForm").form("validate")){
                var data = getFormData("#baseForm");
                if(data){
                    publicFormControl.ajax({
                        type : "POST",
                        url : baseInfo.saveUrl,
                        data : data
                    },function (result){
                        var row = result.row;
                        $("#baseForm").form("load",row);
                        tableInfo.query();
                        enableButtons([ "add", "save" ]);
                    },"show");


                }
            }

        },

        add : function (){

            tabs.isEdit = false ;
            //clear data
            $("#listGrid").datagrid("unselectAll");
            $("#mainTab").tabs("enableTab", 1);
            $("#mainTab").tabs("select", 1);
            setFormItemDisabled("#baseForm",false,exclude);
            enableButtons([ "add", "save" ]);
            $("#baseForm").form("clear");
        },

        del:function (){

            var row = $("#listGrid").datagrid('getSelected');

            if (row.length<=0) {
                $.messager.alert("温馨提示", "请选择删除的信息！", "info");
                return;
            }

            $.messager.confirm("温馨提示", "确定删除动该记录?",
                    function(r) {
                        if (r) {
                            publicFormControl.ajax({
                                url : baseInfo.delUrl+row[thisUiConfig.idName],
                                type: "POST",
                            },function (data){
                                if (data.resultCode == "0") {
                                    $("#baseForm").form("clear");
                                    $("#mainTab").tabs("select", 0);
                                    $("#mainTab").tabs("disableTab", 1);
                                    tableInfo.query();
                                }
                            },"show");
                        }
                    });

        },

        edit:function (){
            tabs.isEdit = true ;
            var tab = $('#mainTab').tabs('getSelected');
            var  index = $('#mainTab').tabs('getTabIndex',tab);
            if(index!=1){
                $("#mainTab").tabs("enableTab", 1);
                $("#mainTab").tabs("select", 1);
            }else{
                enableButtons([ "add", "del" , "save" ]);
            }
            setFormItemDisabled("#baseForm",false,exclude);
        }
    };

    function  loadData(){
        var row = $(thisUiConfig.tableList).datagrid("getSelected");
        if(row){
            var id = row[thisUiConfig.idName];
            if(id){
                publicFormControl.ajax({
                    type : "POST",
                    url : baseInfo.getUrl + id,
                },function (result){
                    var row =  result.row;
                    $("#baseForm").form("load",row);
                });
            }
        }
    }


    //列表加载
    var tableInfo =  {

        //初始化列表
        init : function (){

            $("#listGrid").datagrid({
                rownumbers : true,
                singleSelect : true,
                autoRowHeight : false,
                border : false,
                pageSize : defaultPageSize,
                pageList : defaultPageList,
                pagination : true,
                queryParams:  tableInfo.getQueryFormData("#queryForm"),
                url : thisUiConfig.ctx +"/" + thisUiConfig.url + "/search",
                columns : [ [ {
                    field : "replyId",
                    hidden:true
                },{
                    field:"replyType",
                    title:"回复类型",
                    width:150,
                    halign:'center',
                    align:'left',
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("WX_REPLY_TYPE",value);
                    }
                },{
                    field : "replyContent",
                    title : "回复内容",
                    width : 200,
                    halign:'center',
                    align:'left'
                },{
                    field:"createTime",
                    title:"创建时间",
                    width:200,
                    halign:'center',
                    align:'left'
                } ]],

                onSelect : function(rowIndex, rowData) {
                    tabs.isEdit = false;
                    var len = $("#listGrid").datagrid("getSelections").length;
                    if(len==1){
                        $("#mainTab").tabs("enableTab", 1);
                        enableButtons([ "add", "del", "edit" ]);
                    }else if(len==0){
                        $("#mainTab").tabs("disableTab", 1);
                        enableButtons([ "add"]);
                    }else{
                        enableButtons([ "add", "del"  ]);
                        $("#mainTab").tabs("disableTab", 1);
                    }
                },
                onUnselect : function(rowIndex, rowData) {
                    var len = $("#listGrid").datagrid("getSelections").length;

                    if(len==1){
                        $("#mainTab").tabs("enableTab", 1);
                        enableButtons([ "add", "del", "edit" ]);
                    }else if(len==0){
                        enableButtons([ "add"]);
                        $("#mainTab").tabs("disableTab", 1);
                    }else{
                        enableButtons([ "add", "del"  ]);
                        $("#mainTab").tabs("disableTab", 1);
                    }
                },
                onLoadSuccess : function(data) {
                    $("#listGrid").datagrid("unselectAll");
                }

            });


            // 设置分页显示形式
            $("#listGrid").datagrid("getPager").pagination({
                layout : defaultPageLayout
            });
        },

        query : function (){
            var param = tableInfo.getQueryFormData("#queryForm");
            $("#listGrid").datagrid("load", param);
            enableButtons([ "add" ]);
            $("#mainTab").tabs("disableTab", 1);
        },
        getQueryFormData : function (formId){
            var data =  getFormData(formId) || {};
            return data;
        }
    }


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