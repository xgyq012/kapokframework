<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>信息管理</title>

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
        .ke-icon-multiselectimage {width:16px;height:16px;background-position: 0px -1232px}
        .ke-container-default{margin-left:85px !important}
    </style>
</head>

<body>
<div class="g-layout">
    <!-- 按钮区域 -->
    <div class="g-toolbar">
        <a onclick="baseInfo.add();" id="add"  class="easyui-linkbutton toolbar g-button"><i class="fa fa-plus"></i>新增</a>
        <a onclick="baseInfo.edit();" id="edit" class="easyui-linkbutton toolbar g-button"><i class="fa fa-edit"></i>编辑</a>
        <a onclick="baseInfo.save();" id="save" class="easyui-linkbutton toolbar g-button"><i class="fa fa-floppy-o"></i>保存</a>
        <a onclick="baseInfo.del();" id="del" class="easyui-linkbutton toolbar g-button"><i class="fa fa-trash-o"></i>删除</a>
    </div>

    <!-- 实体区域 -->
    <div id="mainTab" class="easyui-tabs  g-container g-tabs1" data-options="fit:true,border:false" >

        <div title="列表" style="position:relative;">

            <div id="query">

                <form id="queryForm" method="post">
                    <table class="g-form" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">类型</label>
                                <input dictCode="WX_NEWS_TYPE" name="q_newsType_EQ" class="easyui-combobox form-control"
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
                                <input dictCode="WX_NEWS_STATUS" name="q_status_EQ" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">标题</label>
                                <input name="q_title_LIKE" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1 f-button">
                                <a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
                                <a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>

            <!-- 列表区域 -->
            <div class="list-area" style="top:50px;">
                <table id="listGrid" style="height:100%"></table>
            </div>
        </div>

        <!-- 实体详细 -->
        <div title="详细">
            <form name="baseForm" id="baseForm" >
                <input id="createrId" name="createrId" type="hidden">
                <input id="createTime" name="createTime" type="hidden" >
                <input id="newsId" name="newsId" type="hidden">
                <input id="contentImgIds" name="contentImgIds" type="hidden">

                <table class="g-form" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td class="form-cell-2">
                            <label class="form-label">类型</label>
                            <input dictCode="WX_NEWS_TYPE" name="newsType" class="easyui-combobox form-control"
                                   style="width:${comboboxWidth};height:${comboboxHeight}px"
                                   data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
                        </td>
                        <td class="form-cell-2" rowspan="4">
                            <label class="form-label">封面</label>
                            <input id="thumbDocId" name="thumbDocId" type="hidden">
                            <input id="thumbMediaId" name="thumbMediaId" type="hidden">
                            <input id="thumbMediaUrl" name="thumbMediaUrl" type="hidden">
                            <div id="thumbImg" ></div>
                            <input id="image" type="image" class="easyui-validatebox form-control" src="${ctx}/resources/images/base/fengmian.png"
                                   onclick="return false" alt="封面" style="height:${rowSpanHeight*3+105-gutterHeight}px;padding:0">
                        </td>
                    </tr>
                    <tr>
                        <td class="form-cell-2">
                            <label class="form-label">标题</label>
                            <input name="title" class="easyui-validatebox form-control" data-options="required:true">
                        </td>
                    </tr>
                    <tr>
                        <td class="form-cell-2">
                            <label class="form-label">作者</label>
                            <input name="author" class="easyui-validatebox form-control">
                        </td>
                    </tr>
                    <tr>
                        <td class="form-cell-2">
                            <label class="form-label">摘要</label>
                            <textarea name="digest" class="easyui-validatebox form-control" data-options="validType:{length:[0,120]}" style="height:100px"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-cell-2">
                            <label class="form-label">原文链接</label>
                            <input id="contentSourceUrl" name="contentSourceUrl" class="easyui-validatebox form-control">
                        </td>
                        <td class="form-cell-2">
                            <label class="form-label"></label>
                            <input id="show_cover_pic" type="checkbox" style="vertical-align:middle;">&nbsp;&nbsp;封面图片显示在正文中
                        </td>
                    </tr>
                    <tr>
                        <td class="form-cell-4" colspan="2">
                            <label class="form-label" style="margin-right:-85px">正文</label>
                            <div  id="contentImg" ></div>
                            <textarea id="content" name="content"></textarea>
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
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${kindeditor}/kindeditor.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${kindeditor}/lang/zh_CN.js"></script>

<script type="text/javascript">

    var uploadBtn;

    var contentImg;

    var editorImg;

    KindEditor.lang({
        multiselectimage : "图片"
    });

    KindEditor.plugin("multiselectimage", function(K) {
        editorImg = this, name = "multiselectimage";
        editorImg.clickToolbar(name, function() {
            contentImg.fileupload('click');
        });
    });


    var thisUiConfig = {
        main:window.main,
        ctx : "${ctx}",
        url : "news",
        id : "#newsId",    //表单中主键字段对应的控件的Id属性值
        idName:"newsId",
        tableList : "#listGrid",
        getId : function (){
            var newsId = $(thisUiConfig.id).val();
            return newsId;
        }
    }

    $(function (){
        tabs.init();
        editor.init();
        tableInfo.init();
        baseInfo.init();
        importImg.init();
        publicAttr.initCombobox();
        enableButtons(['add']);
    });


    var contentEditor;

    var editor = {

        init : function () {
            KindEditor.ready(function(k) {
                contentEditor = k.create("#content", {
                    width: '100%',
                    height: '350px',
                    items: [
                        'fullscreen', '|',
                        'source', '|',
                        'undo', 'redo', '|',
                        'preview', 'print', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|',
                        'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', '|',
                        'insertorderedlist','insertunorderedlist', 'indent', 'outdent', '|',
                        'subscript', 'superscript', 'clearhtml', 'quickformat',
                        'selectall',  '/',
                        'formatblock', 'fontname', 'fontsize', '|',
                        'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
                        'table', 'hr', 'emoticons', 'pagebreak', 'anchor', 'link', 'unlink', '|', 'multiselectimage'
                    ]
                });
            });
        }

    }

    var importImg = {

        init : function (){

            uploadBtn = $("#thumbImg").fileupload({
                url : thisUiConfig.ctx + "/doc/upload",
                upfile : "upfile",
                filetype : "jpg|jpeg|png",
                hidden : true,
                params : {
                    directory : "news/cover"
                },
                onUploadComplete : function(result) {
                        $("#thumbDocId").val(result.docId);
                        $("#image").attr("src", thisUiConfig.ctx + "/doc/show/"+ result.docId);
                }

            });

            contentImg =  $("#contentImg").fileupload({
                url : thisUiConfig.ctx + "/doc/upload",
                upfile : "contentfile",
                filetype : "jpg|png",
                hidden : true,
                params : {
                    directory : "news/img"
                },
                onUploadComplete : function(result) {
                    if(editorImg && result.docId){
                        editorImg.insertHtml("<img id=\""+result.docId+"\" src=\""+thisUiConfig.ctx + "/doc/show/" + result.docId + "\"   /><br/>");
                    }
                }

            });

            $("#image").on("click",function (){
                uploadBtn.fileupload('click');
            });
        }
    }

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
                        setFormDisabled("#baseForm",true,null);
                    } else if (index == 1) { // 选中详细标签
                        contentEditor.readonly(true);
                        loadData();
                        if(tabs.isEdit){
                            setFormDisabled("#baseForm",false,null);
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

            setFormItemDisabled("#baseForm",true,null);

        },

        save : function (){

            if($("#baseForm").form("validate")){

                var data = getFormData("#baseForm");
                data['content'] =  contentEditor.html();

                var contents = contentEditor.html() ;
                var imgArray = contents.match(/<img\b[^>]+>/g);
                imgArray = imgArray ? imgArray : [] ;
                var imgIds="";
                $.each(imgArray,function (i,n){
                     imgIds = imgIds + $(n).attr("id")+",";
                });

                data['contentImgIds'] = imgIds;

                console.log(data);

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
            setFormDisabled("#baseForm",false,null);
            enableButtons([ "add", "save" ]);
            $("#baseForm").form("clear");
            $("#image").attr("src", thisUiConfig.ctx + "/resources/images/base/fengmian.png");
            contentEditor.html("");
        },

        del:function (){

            var row = $("#listGrid").datagrid('getSelected');

            if (row.length<=0) {
                $.messager.alert("温馨提示", "请选择删除的信息！", "info");
                return;
            }

            $.messager.confirm("温馨提示", "确定删除标题为【"+row.title+"】记录?",
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
            setFormDisabled("#baseForm",false,null);
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
                    $("#image").attr("src", thisUiConfig.ctx + "/doc/show/"+ row.thumbDocId);
                    $("#statusName").val(publicAttr.getDictText("WX_NEWS_TYPE",row.status));
                    contentEditor.html(row.content);
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
                    field : "newsId",
                    hidden:true,
                },{
                    field : "newsType",
                    title : "类型",
                    width : 200,
                    halign:'center',
                    align:'left',
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("WX_NEWS_TYPE",value);
                    }
                },{
                    field : "title",
                    title : "标题",
                    width : 300,
                    halign:'center',
                    align:'left'
                },{
                    field : "author",
                    title : "作者",
                    width : 100,
                    halign:'center',
                    align:'left'
                } ,{
                    field : "createTime",
                    title : "创建时间",
                    width : 200,
                    halign:'center',
                    align:'left'
                }]],

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

    function setFormDisabled(form, disabled, exclude){
        setFormItemDisabled(form,disabled,exclude);
        contentEditor.readonly(disabled);
    }
</script>
</body>
</html>