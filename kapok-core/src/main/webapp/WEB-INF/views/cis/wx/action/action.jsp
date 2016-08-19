<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>动作配置管理</title>
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
        <a onclick="baseInfo.edit();" id="edit" data-options="disabled:true" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
        <a onclick="baseInfo.save();" id="save" data-options="disabled:true" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
        <a onclick="baseInfo.del();" id="del" data-options="disabled:true" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
        <a onclick="verify();" id="verify" data-options="disabled:true" class="easyui-linkbutton toolbar g-button"><i class="fa fa-reply"></i>确认</a>
        <a onclick="executionTask();" id="execution" data-options="disabled:true" class="easyui-linkbutton toolbar g-button"><i class="fa fa-exchange"></i>发布</a>
    </div>

    <div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

        <div title="列表" style="position:relative;">

            <div id="query">
                <form id="queryForm" class="formCls" method="post">
                    <table  class="g-form" cellpadding="0" cellspacing="0" >
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">动作名称</label>
                                <input name="q_actionName_LIKE" class="easyui-validatebox form-control"  /></td>

                            <td class="form-cell-1">
                                <label class="form-label">动作类型</label>
                                <input  dictCode="WX_ACTION_TYPE" name="q_actionReturnType_EQ" class="easyui-combobox form-control"
                                        style="width:${comboboxWidth};height:${comboboxHeight}px"
                                        data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />

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
                <input id="actionId" name="actionId" type="hidden" />
                <input id="createrId" name="createrId" type="hidden" />
                <input id="createTime" name="createTime" type="hidden" />


                <table class="g-form" cellpadding="0" cellspacing="0" >
                    <tr>

                        <td class="form-cell-2"  colspan="2">
                            <label class="form-label">动作名称</label>
                            <input name="actionName" class="easyui-validatebox form-control" data-options="required:true" />
                        </td>

                        <td class="form-cell-1">
                            <label class="form-label">动作类型</label>
                            <input disabled id="actionReturnType" dictCode="WX_ACTION_TYPE" name="actionReturnType" class="easyui-combobox form-control"
                                   style="width:${comboboxWidth};height:${comboboxHeight}px"
                                   data-options="
                                        required:true,
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
                        </td>

                        <td class="form-cell-1">
                            <label class="form-label">状态</label>
                            <input  dictCode="WX_ACTION_STATUS" id="status" name="status" class="easyui-combobox form-control"
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
                            <label class="form-label">返回内容</label>
                            <textarea id="returnContent" name="returnContent" class="easyui-validatebox form-control" rows="3" ></textarea>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="4" class="form-cell-4">
                            <label class="form-label">备注</label>
                            <textarea name="remark" class="easyui-validatebox form-control" rows="5" ></textarea>
                        </td>
                    </tr>

                </table>
            </form>

            <div id="sub" style="height:500px;">
                <div id="subTabs" class="easyui-tabs" data-options="fit:true,border:false" style="height: 100%">
                    <div title="图文">
                        <div id="newsListToolbar" class="g-toolbar">
                            <a id="addNews" class="easyui-linkbutton g-button" onclick="addNews()" data-options="disabled:true"><i class="fa fa-plus"></i>添加</a>
                            <a id="delNews" class="easyui-linkbutton g-button" onclick="delNews()" data-options="disabled:true"><i class="fa fa-trash-o"></i>删除</a>
                        </div>
                        <table id="newsList" style="height: 100%" ></table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" >

    var exclude = "#status";

    var thisUiConfig = {
        main:window.main,
        ctx : "${ctx}",
        url : "action",
        id : "#actionId",    //表单中主键字段对应的控件的Id属性值
        idName:"actionId",
        tableList : "#listGrid",
        getId : function (){
            var actionId = $(thisUiConfig.id).val();
            return actionId;
        }
    }

    $(function (){
        tabs.init();
        tableInfo.init();
        baseInfo.init();
        publicAttr.initCombobox();
        initTw();
        $("#actionReturnType").combobox({
            onChange:function (newValue,oldValue){
                if(newValue=='tw'){
                    $("#sub").show();

                }else{
                    $("#sub").hide();
                }
            }
        });
    });


    var tabs = {

        isEdit : true ,

        init : function (){

            $("#mainTab").tabs({
                onSelect : function(title, index) {
                    if (index == 0) { // 选中列表标签
                        $("#baseForm").form("clear");
                        var fal = $("#listGrid").datagrid('getSelections').length > 0
                        if(!fal){
                            enableButtons(["add"]);
                            disableButtons([ "del", "save","edit"]);
                        }else{
                            enableButtons(["add","edit","del"]);
                            disableButtons([ "del", "save"]);
                        }
                        setFormItemDisabled("#baseForm",true,exclude);

                    } else if (index == 1) { // 选中详细标签
                        loadData();
                        setFormItemDisabled("#baseForm",true,exclude);
                        enableButtons([ "add", "del" , "edit"]);
                        disableButtons([ "save" , "addNews" ,"verify" ,"execution" ]);
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
            setFormItemDisabled("#baseForm",true,null);

        },

        save : function (){

            if($("#baseForm").form("validate")){

                var actionReturnType = $("#actionReturnType").combobox("getValue");

                var data = getFormData("#baseForm");

                //类型是图文的特殊处理
                if(actionReturnType=="tw"){
                    var rowData = $('#newsList').datagrid('getData').rows;
                    var dg = "wxActionLList";
                    if(rowData.length>0){
                        for (var k = 0; k < rowData.length; k++) {
                            data[dg + '[' + k + '].twId'] = rowData[k].twId ? rowData[k].twId : null;
                            data[dg + '[' + k + '].actionId'] = rowData[k].actionId ? rowData[k].actionId : null;
                            data[dg + '[' + k + '].title'] = rowData[k].title;
                            data[dg + '[' + k + '].author'] = rowData[k].author;
                            data[dg + '[' + k + '].newsId'] = rowData[k].newsId;
                        }
                    }
                }

               if(data){
                   //保存
                 publicFormControl.ajax({
                     type : "POST",
                     url : baseInfo.saveUrl,
                     data : data
                 },function (result){
                     var row = result.row;
                     $("#baseForm").form("load",row);

                     if(row.wxActionLList && row.wxActionLList.length>0){
                         $("#newsList").datagrid("loadData",row.wxActionLList);
                     }

                     tableInfo.query();
                     enableButtons([ "add", "save" , "del" ]);
                     disableButtons(["edit","verify"]);
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
            $("#baseForm").form("clear");
            $("#sub").hide();
            enableButtons([ "add", "save" , "addNews" ]);
            disableButtons(["edit","del","verify","execution"])
            $('#newsList').datagrid("loadData",[]);
        },

        del:function (){

            var row = $("#listGrid").datagrid('getSelected');
            var id = $(thisUiConfig.id).val();
            var fal =  row ? true : id ? true : false ;
            if (!fal) {
                $.messager.alert("温馨提示", "请选择删除的信息！", "info");
                return;
            }

            $.messager.confirm("温馨提示", "确定删除动作名称为【"+row.actionName+"】记录?",
                    function(r) {
                        if (r) {
                            if(row){
                                id = row.actionId;
                            }
                            publicFormControl.ajax({
                                url : baseInfo.delUrl + id,
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
            var tab = $('#mainTab').tabs('getSelected');
            var  index = $('#mainTab').tabs('getTabIndex',tab);
            if(index!=1){
                $("#mainTab").tabs("enableTab", 1);
                $("#mainTab").tabs("select", 1);
            }
            setFormItemDisabled("#baseForm",false,exclude);
            disableButtons([ "edit","verify","execution"]);
            enableButtons([ "add", "del" , "save" ,  "addNews"]);
        }
    };

    function  loadData(){
        var id = ""
        var row = $(thisUiConfig.tableList).datagrid("getSelections");
        if(row.length>0){
           id =  row[0][thisUiConfig.idName];
        }else{
            id= $(thisUiConfig.id).val();
        }

        if(id){
            publicFormControl.ajax({
                type : "POST",
                url : baseInfo.getUrl + id,
            },function (result){
                $('#newsList').datagrid("loadData",[]);
                var r =  result.row;
                $("#baseForm").form("load",r);
                var actionReturnType =  r.actionReturnType;
                if(actionReturnType && actionReturnType=='tw'){
                    if(r.wxActionLList  && r.wxActionLList.length>0){
                        $("#newsList").datagrid("loadData",r.wxActionLList);
                    }
                }
            });

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
                    field : "actionId",
                    hidden:true,
                },{
                    field : "actionName",
                    title : "动作名称",
                    width : 200,
                    halign:'center',
                    align:'left'
                },{
                    field:"actionReturnType",
                    title:"动作类别",
                    width:150,
                    halign:'center',
                    align:'left',
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("WX_ACTION_TYPE",value);
                    }
                },{
                    field:"status",
                    title:"状态",
                    width:150,
                    halign:'center',
                    align:'left',
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("WX_ACTION_STATUS",value);
                    }
                },{
                    field:"createTime",
                    title:"创建时间",
                    width:200,
                    halign:'center',
                    align:'left'
                } ]],

                onSelect : function(rowIndex, rowData) {
                    var len = $("#listGrid").datagrid("getSelections").length;
                    $("#mainTab").tabs("enableTab", 1);
                    enableButtons([ "add"  ,"edit" , "del"  ]);
                    disableButtons([  "save" ]);
                    if(rowData.status=='draft'){
                        enableButtons([ "verify" ]);
                        disableButtons([ "execution" ]);
                    }else if(rowData.status=='affirm'){
                        enableButtons([ "execution" ]);
                        disableButtons([ "verify" ]);
                    }else{
                        disableButtons([ "verify" , "execution" ]);
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
            $("#mainTab").tabs("disableTab", 1);
           /* enableButtons([ "add" ]);
            disableButtons([ "edit" , "save" , "del" ]);*/
        },
        getQueryFormData : function (formId){
            var data =  getFormData(formId) || {};
            return data;
        }
    }


    function initTw(){
        $('#newsList').datagrid({
            rownumbers : true,
            singleSelect : false,
            border : false,
            pageSize : defaultPageSize,
            pageList : defaultPageList,
            pagination : false,
            columns : [[
                {field: '', checkbox: true},
                {field: 'twId',  title: 'ID' ,hidden: true},
                {field: 'actionId', title: '动作ID', halign: 'center', hidden: true},
                {field: 'newsId', title: '图文ID', halign: 'center', hidden: true},
                {field: 'title', title: '标题', width : 200 , halign: 'center'},
                {field: 'author', title: '作者', width : 150 , halign: 'center'}
            ]],
            onCheck: function(row) {
                enableButtons(["addNews","delNews"]);
            },
            onUncheck: function(row) {
                var rows = $('#newsList').datagrid("getChecked");
                if(rows.length>0){
                    enableButtons([ "delNews"  ]);
                }else{
                    disableButtons([ "delNews"]);
                }
            },
            onCheckAll: function(rows) {
                enableButtons(["delNews"]);
            },
            onUncheckAll: function(rows) {
                disableButtons(["delNews"]);
            }
        });
    }


    // 获取当前列表的图文
    function  getnewsListRows(){
        var array ={};
        var news = $("#newsList").datagrid("getRows");
        for(var i in news){
            array[news[i].newId] = news[i];
        }
        return array ;
    }

    //添加图文
    function addNews() {

        var newsArray = getnewsListRows();

        main.dialog({
            id: 'newsframe',
            title: "选择图文",
            url: '${ctx}/news/select-news',
            width: 700,
            height: 425,
            cache: false,
            modal: true,
            contentWindow : {
                params: {
                    singleSelect : false,
                    checkbox : true,
                    newsArray:newsArray
                },
                actions: {
                    confirm : function(data) {

                        var actionId = $("#actionId").val();

                        var old_rows = $("#newsList").datagrid("getRows");
                        var obj = $("#newsList");

                        for(var j in old_rows){
                            var r =  old_rows[j];
                            var o = data[r.newsId];
                            if(!o){
                                var ind = $('#newsList').datagrid("getRowIndex",r);
                                $('#newsList').datagrid("deleteRow",ind);
                            }
                        }

                        for(var i in data){
                            var row = data[i];
                            var f = true;
                            for(var j in old_rows){
                                var orow =  old_rows[j];
                                if(orow.newsId == row.newsId){
                                    f=false;
                                    break;
                                }
                            }
                            if(f){
                                var d = {};
                                d['newsId'] = row.newsId;
                                d['actionId'] = actionId;
                                d['title'] = row.title;
                                d['author'] = row.author;
                                obj.datagrid("appendRow",d);
                            }
                        }

                        if($("#newsList").datagrid("getRows").length>10){
                            window.main.warn("图文信息不能超过十条！");
                        }

                        main.closeDialog();
                    },
                    cancel: function() {
                        main.closeDialog();
                    }
                }
            },
            onBeforeOpen : function() {

                return true;
            }
        });
    }

    //删除图文
    function delNews(){
        var rows = $('#newsList').datagrid("getSelections");
        if(rows && rows.length>0){
            main.confirm("温馨提示","确定删除选择的数据？",function (r){
                if(r){
                    var ids = "";
                    for(var j in rows){
                        var row = rows[j];
                        var twId = row.twId;
                        if(twId){
                            ids = ids + twId + ",";
                        }
                        var ind = $('#newsList').datagrid("getRowIndex",row);
                        $('#newsList').datagrid("deleteRow",ind);
                    }

                    if(ids){
                        ids = ids.substring(0,ids.length-1);
                        publicFormControl.ajax({
                            url : thisUiConfig.ctx + "/actionl/delList" ,
                            data : {
                                ids : ids
                            },
                            type: "POST"
                        },function (data){

                        },"show");
                    }

                }

            });

        }else{
            main.info("温馨提示","请选择删除的内容！");
        }
    }

    function verify (){
        var rows = $('#listGrid').datagrid("getSelected");
        if(rows && rows.actionId){
            $.messager.confirm("温馨提示", "确定修改这条数据的状态吗?",
                function(r) {
                    if (r) {
                        publicFormControl.ajax({
                            type : "POST",
                            url :thisUiConfig.ctx + "/action/updateActionStatus/" + rows.actionId
                        },function (result){
                            $("#listGrid").datagrid("reload");
                        },"show");
                    }
            });

        }else{
            thisUiConfig.main.warn("请选择需要更改状态数据！");
        }
    }

    //群发

    function executionTask(){
        var rows = $('#listGrid').datagrid("getSelected");
        if(rows && rows.actionId){
            $.messager.confirm("温馨提示", "确定要执行这个动作?",
                    function(r) {
                        if (r) {
                            publicFormControl.ajax({
                                type : "POST",
                                url :thisUiConfig.ctx + "/action/massMessageSend/" + rows.actionId
                            },function (result){
                                console.log(result);
                            },"show");
                        }
                    });

        }else{
            thisUiConfig.main.warn("请选择需要更改状态数据！");
        }
    }

</script>

</body>
</html>