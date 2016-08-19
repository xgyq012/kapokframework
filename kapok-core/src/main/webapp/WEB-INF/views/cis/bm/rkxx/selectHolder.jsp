<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>人口信息</title>

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
                        <label>姓名</label>
                    </td>
                    <td>
                        <input name="q_h.householder_Name_LIKE" class="easyui-validatebox form-control" style="width:150px;margin:0 5px;">
                    </td>
                    <td>
                        <label>身份号</label>
                    </td>
                    <td>
                        <input name="q_h.card_Code_EQ" class="easyui-validatebox form-control" style="width:150px;margin:0 5px;">
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



    var thisUiConfig = {
        main : window.parent.main,
        ctx : '${ctx}',
        searchUrl : '${ctx}/householder/search',
        url : "householder",
        id : "#householderId",    //表单中主键字段对应的控件的Id属性值
        idName:"householderId",
        tableList : "#listGrid",
        getId : function (){
            return  $(thisUiConfig.id).val();
        }
    }


    $(function (){


        var firstInit = true ;

        $("#listGrid").datagrid({
            rownumbers : true,
            singleSelect : true,
            autoRowHeight : false,
            border : false,
            pageSize : 10 ,
            pageList : defaultPageList,
            pagination : true,
            queryParams:  getFormData("#queryForm"),
            url : thisUiConfig.searchUrl,
            columns : [[{
                field : "householderId",
                hidden:true
            },{
                field : "orgId",
                title : "机构名称",
                width : 100,
                halign:'center',
                align:'left',
                formatter : function (value,row,index){

                    return publicAttr.getOrgName(value);
                }
            },{
                field : "householderName",
                title : "姓名",
                halign:'center',
                align:'left',
                width : 100,
            },{
                field:"age",
                title:"年龄",
                width:50,
                halign:'center',
                align:'left'
            },{
                field:"sex",
                title:"性别",
                halign:'center',
                align:'left',
                width:50,
                formatter : function (value,row,index){

                    return publicAttr.getDictText("Gender",value);
                }
            },{
                field:"cardCode",
                title:"身份证号码",
                width:150,
                halign:'center',
                align:'left'
            }]],
            onLoadSuccess : function (data){

            },
            onSelect:function (index,row){
                conl
            }
        });

        // 设置分页显示形式
        $("#listGrid").datagrid("getPager").pagination({
            layout : defaultPageLayout
        });
    });

    function  sub(){
        var row = $("#listGrid").datagrid("getSelected");
        window.actions.confirm(row);
    }

    function query(){
        var param = getFormData("#queryForm");
        $("#listGrid").datagrid("load", param);
    }

</script>

</html>