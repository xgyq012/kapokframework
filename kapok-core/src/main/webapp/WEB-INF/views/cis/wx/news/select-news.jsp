<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>选择图文信息</title>

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
    <div style="width:100%;padding:10px 10px;">
        <form id="queryForm" method="post">
            <table  cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>
                        <label>标题</label>
                    </td>
                    <td>
                        <input name="q_title_LIKE" class="easyui-validatebox form-control" style="width:150px;margin:0 5px;">
                    </td>
                    <td>
                        <label>类型&nbsp;</label>
                    </td>
                    <td style="width: 150px;">
                        <input dictCode="WX_NEWS_TYPE" name="q_newsType_EQ" class="easyui-combobox form-control"
                               style="width:${comboboxWidth};height:${comboboxHeight}px"
                               data-options="
                                        required:true,
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'" />
                    </td>
                    <td>
                        <a onclick="query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
                        <a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>

    <div id="list">
        <table id="listGrid"  style="height:310px;"></table>
    </div>

    <div id="toolbar" class="dialog-button">
        <a id="confirm" class="easyui-linkbutton g-button" onclick="sub();"><i class="fa fa-check" aria-hidden="true"></i>确定</a>
        <a id="cancel" class="easyui-linkbutton g-button" onclick="window.actions.cancel();"><i class="fa fa-times" aria-hidden="true"></i>取消</a>
    </div>

</div>
</body>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/lookup.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript">


    var newsList = {};

    var thisUiConfig = {
        main : window.parent.main,
        ctx : '${ctx}',
        searchUrl : '${ctx}/news/search',
        url : "news",
        id : "#newsId",    //表单中主键字段对应的控件的Id属性值
        idName:"newsId",
        tableList : "#listGrid",
        getId : function (){
            return  $(thisUiConfig.id).val();
        }
    }


    $(function (){

        publicAttr.initCombobox();

        var firstInit = true ;

        $("#listGrid").datagrid({
            rownumbers : true,
            singleSelect : false,
            autoRowHeight : false,
            border : false,
            pageSize : 10 ,
            pageList : defaultPageList,
            pagination : true,
            queryParams:  getFormData("#queryForm"),
            url : thisUiConfig.searchUrl,
            columns : [ [ {
                field : "newsId",
                checkbox:true
            },{
                field : "newsType",
                title : "类型",
                width : 100,
                halign:'center',
                align:'left',
                formatter : function (value,row,index){

                    return publicAttr.getDictText("WX_NEWS_TYPE",value);
                }
            },{
                field : "title",
                title : "标题",
                width : 200,
                halign:'center',
                align:'left'
            },{
                field : "title",
                title : "作者",
                width : 100,
                halign:'center',
                align:'left'
            },{
                field:"status",
                title:"状态",
                width:80,
                halign:'center',
                align:'left',
                formatter : function (value,row,index){

                    return publicAttr.getDictText("WX_NEWS_STATUS",value);
                }
            }]],

            onLoadSuccess : function (data){
                var existsArray =  window.params.newsArray ;
                $.extend(true,newsList,existsArray);
                if(data){
                    var listGrid = $("#listGrid");
                    $.each(data.rows,function (index,n){
                        $.each(newsList,function (j,r){
                            if(r.newsId==n.newsId){
                                var ind = listGrid.datagrid("getRowIndex",n);
                                listGrid.datagrid("checkRow",ind);
                                return false;
                            }
                        });
                    });

                }
            },
            onCheck:function (index,row){

                newsList[row.newsId] = row  ;

            },
            onUncheck:function (index,row){
                delete newsList[row.newsId];
            },
            onCheckAll: function (rows){
                for(var i in rows){
                    var row = rows[i];
                    newsList[row.newsId] = row ;
                }
            },
            onUncheckAll : function (rows){
                for(var i in rows){
                    var row = rows[i];
                    delete newsList[row.newsId];
                }
            }
        });

        // 设置分页显示形式
        $("#listGrid").datagrid("getPager").pagination({
            layout : defaultPageLayout
        });
    });

    function  sub(){
        window.actions.confirm(newsList);
    }

    function query(){
        var param = getFormData("#queryForm");
        $("#listGrid").datagrid("load", param);
    }

</script>

</html>