<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>医保信息</title>

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
        <a id="add" class="easyui-linkbutton g-button" onclick="add()" data-options=""><i class="fa fa-plus"></i>新增</a>
        <a id="edit" class="easyui-linkbutton g-button" onclick="edit()" data-options="disabled:true"><i class="fa fa-edit"></i>编辑</a>
        <a id="save" class="easyui-linkbutton g-button" onclick="save()" data-options="disabled:true"><i class="fa fa-floppy-o"></i>保存</a>
        <a id="del" class="easyui-linkbutton g-button" onclick="del()" data-options="disabled:true"><i class="fa fa-trash-o"></i>删除</a>
    </div>

    <!-- 内容区域 -->
    <div id="mainTab" class="easyui-tabs g-container g-tabs1" data-options="fit:true,border:false">
        <div title="列表" style="position:relative;">
            <div class="query-area">
                <form id="queryForm" method="post">
                    <table class="g-form" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">姓名</label>
                                <input name="q_h.householder_Name_LIKE" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">身份号</label>
                                <input name="q_h.card_Code_EQ" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">婚姻状况</label>
                                <input dictCode="maritalStatus" name="q_h.marital_Status_EQ"
                                       class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
		 								    	editable:false,
		 								    	panelHeight:'auto',
		 								    	valueField:'dictCode',
		 								    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">性别</label>
                                <input dictCode="Gender" name="q_h.sex_EQ" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
		 								    	editable:false,
		 								    	panelHeight:'auto',
		 								    	valueField:'dictCode',
		 								    	textField:'dictName'">
                            </td>

                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">年龄</label>
                                <input name="q_h.age_GTE" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">~</label>
                                <input name="q_h.age_LTE" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">户籍类别</label>
                                <input dictCode="residentType" name="q_h.household_Type_EQ" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">管理类型</label>
                                <input dictCode="ManageType" name="q_h.gllx_EQ" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
	 								    	editable:false,
	 								    	panelHeight:'auto',
	 								    	valueField:'dictCode',
	 								    	textField:'dictName'">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-3" colspan="3"></td>
                            <td class="form-cell-1 f-button">
                                <a onclick="tableInfo.query();" class="easyui-linkbutton g-button"><i class="fa fa-search"></i>查询</a>
                                <a onclick="clearQueryForm('#queryForm');" class="easyui-linkbutton g-button"><i class="fa fa-reply"></i>重置</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>

            <div class="list-area" style="top:104px;">
                <table id="listGrid" style="height:100%"></table>
            </div>
        </div>

        <div title="详细" data-options="disabled:true" >

            <form id="baseForm">
                <input id="householderId" name="householderId" type="hidden" value="${requestScope.householderId}">
                <input id="createrId" name="createrId" type="hidden">
                <input id="createTime" name="createTime" type="hidden">
                <fieldset>
                    <legend>基础信息</legend>
                    <table class="g-form" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td class="form-cell-3" colspan="3">
                                <label class="form-label">所属网格</label>
                                <input id="orgId" name="orgId">
                            </td>
                            <td class="form-cell-1" rowspan="6">
                                <label class="form-label">照片</label>
                                <input id="photofileID" name="photofileID" type="hidden">
                                <input id="docShowname" name="docShowname" type="hidden">
                                <input id="photofileUrl" type="image" class="easyui-validatebox form-control"
                                       src="${ctx}/resources/images/base/1.png" onclick="return false;"
                                       style="height:${rowSpanHeight*6-gutterHeight}px">
                                <div id="addPhoto"></div>
                            </td>

                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">小区</label>
                                <input id="comId" name="comId" type="hidden">
                                <input id="communityName" name="communityName" class="easyui-validatebox form-control" readonly>
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">楼栋</label>
                                <input id="buildId" name="buildId" type="hidden"/>
                                <input id="ldCode" name="ldCode" class="easyui-validatebox form-control" readonly>
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">房屋</label>
                                <input id="houseId" name="houseId"   />
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">姓名</label>
                                <input name="householderName" class="easyui-validatebox form-control" data-options="required:true">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">与户主关系</label>
                                <input dictCode="familytree" name="householderRelation"
                                       class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'" >
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">性别</label>
                                <input  dictCode="Gender" name="sex" class="easyui-combobox form-control"
                                        style="width:${comboboxWidth};height:${comboboxHeight}px"
                                        data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">身份证号</label>
                                <input onblur="addBirthDate(this);" name="cardCode" class="easyui-validatebox form-control" data-options="required:true" validType="checkIdCode">
                            </td>

                            <td class="form-cell-1">
                                <label class="form-label">出生日期</label>
                                <input id="birthDate" name="birthDate" class="easyui-datebox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="required:true,editable:false">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">民族</label>
                                <input dictCode="Nation"  name="nationality"
                                       class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">年龄</label>
                                <input id="age" name="age" class="easyui-validatebox form-control" readonly>
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">健康状况</label>
                                <input dictCode='healthStatus' name="healthStatus"
                                       class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">结婚时间</label>
                                <input name="maritalData" class="easyui-datebox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="editable:false">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">婚姻状况</label>
                                <input dictCode="maritalStatus" name="maritalStatus" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">职业</label>
                                <input dictCode="JobType" name="jobName" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">节育措施</label>
                                <input  dictCode="birthControl" name="zjxy" class="easyui-combobox form-control"
                                        style="width:${comboboxWidth};height:${comboboxHeight}px"
                                        data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">社会职务</label>
                                <input dictCode="socialFunction" name="socialJob" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">工作单位</label>
                                <input name="unit" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">职务</label>
                                <input name="dutyName" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">特长</label>
                                <input dictCode="SpecialityType" name="specialty" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">兵役状况</label>
                                <input  dictCode="veteranStatus" name="veteranStatus" class="easyui-combobox form-control"
                                        style="width:${comboboxWidth};height:${comboboxHeight}px"
                                        data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">户籍类别</label>
                                <input dictCode="residentType" name="householdType" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">户籍地</label>
                                <input name="householdAddress" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">籍贯</label>
                                <input name="nativePlace" class="easyui-validatebox form-control">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">身高</label>
                                <input name="sgHeight" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">住房面积</label>
                                <input name="housingArea" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">门牌号</label>
                                <input name="doorplate" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">管理类型</label>
                                <input dictCode="ManageType" name="gllx" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">文化程度</label>
                                <input dictCode="Education" name="eduLevel" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">血型</label>
                                <input dictCode="bloodType" name="bloodType" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">志愿者</label>
                                <input  dictCode="YesOrNo" name="isVolunteer" class="easyui-combobox form-control"
                                        style="width:${comboboxWidth};height:${comboboxHeight}px"
                                        data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">政治面貌</label>
                                <input dictCode="politicsStatus" name="politicsStatus" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                        </tr>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label">宗教信仰</label>
                                <input dictCode="Religion" name="religion" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label">联系电话</label>
                                <input name="callPhone" class="easyui-validatebox form-control">
                            </td>
                            <td class="form-cell-2" colspan="2">
                                <label class="form-label">现居地</label>
                                <input name="dwellAddress" class="easyui-validatebox form-control">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </fieldset>
            </form>

            <!-- 医保信息 -->
            <form id="ybxx" infoName="ybxx" action="${ctx}/healthinSuranceinfo/save">
                <input id="healthId" name="healthId" type="hidden">
                <input name="householderId" type="hidden">
                <input name="createrId" type="hidden">
                <input name="createTime" type="hidden">
                <fieldset>
                    <legend>医保信息</legend>
                    <table class="g-form" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td class="form-cell-1">
                                <label class="form-label" title="医保卡号">医保卡号</label>
                                <input name="healthCode" class="easyui-validatebox form-control" data-options="required:true">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label" title="缴纳医保类型">缴纳医保类型</label>
                                <input dictCode="healthType" name="healthType" class="easyui-combobox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
                            </td>
                            <td class="form-cell-1">
                                <label class="form-label" title="投保日期">投保日期</label>
                                <input name="insureDate" class="easyui-datebox form-control"
                                       style="width:${comboboxWidth};height:${comboboxHeight}px"
                                       data-options="editable:false">
                            </td>
                            <%--<td class="form-cell-1">
                                <label class="form-label" title="缴纳金额">缴纳金额</label>
                                <input name="paymentAmount"class="easyui-validatebox form-control">
                            </td>--%>
                        </tr>
                       <%-- <tr>
                            <td class="form-cell-1"></td>
                            <td class="form-cell-1"></td>
                            <td class="form-cell-1"></td>
                            <td class="form-cell-1 f-button">
                                <a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
                                <a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
                                <a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
                            </td>
                        </tr>--%>
                        </tbody>
                    </table>
                </fieldset>
            </form>

        </div>
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
        main : window.main,
        ctx : '${ctx}',
        searchUrl : '${ctx}/householder/searchHealthInsurance'
    }

    $(function (){
        tabs.init();
        tableInfo.init();
        publicAttr.initCombobox();
        orgLookup();
        houseLookupLookup();
        disableFormItem("#baseForm",null);
        disableFormItem("#ybxx",null);
    });

    var tabs = {

        isEdit : true ,

        init : function (){

            $("#mainTab").tabs({
                onSelect : function(title, index) {
                    if (index == 0) { // 选中列表标签
                        disableFormItem("#ybxx",null);
                        disableButtons(['save','del']);
                        if($("#listGrid").datagrid("getSelections").length>0){
                            enableButtons(['edit']);
                        }
                    } else if (index == 1) { // 选中详细标签
                        loadData();
                        enableButtons(['del'])
                    }
                }
            });
        }

    }


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
                queryParams:  tableInfo.getFormData("#queryForm"),
                url : thisUiConfig.searchUrl,
                columns : [ [ {
                    field : "householderId",
                    hidden:true
                }, {
                    field : "orgId",
                    title : "机构名称",
                    width : 150,
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
                    align:'left',
                },{
                    field:"nationality",
                    title:"民族",
                    halign:'center',
                    align:'left',
                    width:100,
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("Nation",value);
                    }
                },{
                    field:"maritalStatus",
                    title:"婚姻状况",
                    halign:'center',
                    align:'left',
                    width:100,
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("maritalStatus",value);
                    }
                },{
                    field:"householdType",
                    title:"户籍类别",
                    halign:'center',
                    align:'left',
                    width:100,
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("residentType",value);
                    }
                },{
                    field:"gllx",
                    title:"管理类型",
                    halign:'center',
                    align:'left',
                    width:100,
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("ManageType",value);
                    }
                },{
                    field:"householderRelation",
                    title:"是否户主",
                    halign:'center',
                    align:'left',
                    width:100,
                    formatter : function (value,row,index){

                        return publicAttr.getDictText("familytree",value);
                    }
                },{
                    field:"callPhone",
                    title:"联系电话",
                    halign:'center',
                    align:'left',
                    width:100
                }]],

                onSelect : function(rowIndex, rowData) {
                    var len = $("#listGrid").datagrid("getSelections").length;
                    if(len==1){
                        $("#mainTab").tabs("enableTab", 1);
                        enableButtons(['edit']);
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
            var param = tableInfo.getFormData("#queryForm");
            $("#listGrid").datagrid("load", param);
            enableButtons(['add']);
            $("#mainTab").tabs("enableTab", 1);
        },

        getFormData : function (formId){
            var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
            var data =  getFormData(formId) || {};
            if(meshIds){
                data['q_h.org_Id_IN'] = meshIds;
            }
            return data;
        }
    };





    //加载
    function  loadData(){
        var r = $("#listGrid").datagrid("getSelected");
        if(r){
            var id = r.householderId;
            loadHouseHolder(id);
            //加载医保信息
            socialinfo(id);
        }
    }


    function add(){
        holderLoop();
    }

    function edit(){
        var tab = $('#mainTab').tabs('getSelected');
        var index = $('#mainTab').tabs('getTabIndex',tab);
        if(index!=1){
            $("#mainTab").tabs("enableTab", 1);
            $("#mainTab").tabs("select", 1);
        }
        enableButtons([ "del" , "save" ]);
        disableButtons(['edit']);

        enableFormItem("#ybxx",null);
    }

    //删除
    function del(){
        var householderName =  $("input[name='householderName']").val();
        var householderId =  $("#householderId").val();
        $.messager.confirm("温馨提示", "确定删除【"+householderName+"】的医保信息?",
                function(r) {
                    if (r) {
                        publicFormControl.ajax({
                            url : thisUiConfig.ctx + "/healthinSuranceinfo/del/"+ householderId,
                            type: "POST"
                        },function (data){
                            if (data.resultCode == "0") {
                                $("#ybxx").form("clear");
                            }
                        },"show");
                    }
                });

    }


    function save(){

        if($("#ybxx").form("validate")) {
            var data = getFormData("#ybxx");
            data['householderId'] =  $("#householderId").val();
            publicFormControl.ajax({
                type: "POST",
                url : thisUiConfig.ctx + "/healthinSuranceinfo/save",
                data: data
            },function (result){
                $("#ybxx").form("load",result.row);

            },'show');
        }

    }

    function  loadHouseHolder(holderId){
        $("#baseForm").form("clear");
        if(holderId){
            var ajax = publicFormControl.ajax({
                type : "POST",
                url :thisUiConfig.ctx + "/householder/get/" + holderId
            },function (result){
                var row = result.row ;
                $("#baseForm").form("load",row);
                $("#houseId").lookup("setTxt",row.dyCode);
                $("#orgId").lookup('setTxt',publicAttr.getOrgName(row.orgId));
                $("#houseId").lookup("disable");
                $("#orgId").lookup("disable");
                // disableFormItem("#baseForm",null);
            });
        }
    }

    function socialinfo(holderId){
        $("#ybxx").form("clear");
        $.ajax({
            type : "POST",
            url : thisUiConfig.ctx + "/healthinSuranceinfo/getInfoByHouseHolder/"+holderId ,
        }).done(function(result){
            var row = result.row ;
            if(row){
                $("#ybxx").form("load",row);
            }
        });
    }

    function showImg(){
        var photofileID = $("#photofileID").val();
        var  imgUrl ="";
        if(photofileID && photofileID>0){
            imgUrl = thisUiConfig.ctx + "/doc/show/"+ photofileID
        }else{
            imgUrl = thisUiConfig.ctx + "/resources/images/base/1.png";
        }
        $("#photofileUrl").attr("src",imgUrl);
    }


    var orgLookup;
    function orgLookup() {
        orgLookup = $('#orgId').lookup({
            title : "选择所属网格",
            url : thisUiConfig.ctx + "/mesh/selectmesh",
            width : 350,
            height : 500,
            required : true,
            valueField: 'meshId',
            textField: 'meshName'
        });
    }

    function  getOrgid() {
        return $('#orgId').lookup('getVal');
    }

    var houseLookup;

    function houseLookupLookup() {
        houseLookup = $("#houseId").lookup({
            title: "选择房屋",
            url: thisUiConfig.ctx + "/house/select-house",
            width: 700,
            height: 440,
            contentWindow: {
                params: {
                    singleSelect : true,
                    checkbox : false,
                    queryParams : function (){
                        return {
                            orgId:getOrgid()
                        };
                    }
                },
                actions: {
                    confirm: function(data) {
                        if (data) {
                            var houseId = data.houseId;
                            var buildId = data.buildId;
                            var comId = data.comId;
                            var ldCode = data.ldCode;
                            var dyCode = data.dyCode;
                            var communityName = data.communityName;
                            $("#houseId").lookup("setVal",houseId);
                            $("#houseId").lookup("setTxt",dyCode);
                            $("#buildId").val(buildId);
                            $("#comId").val(comId);
                            $("#communityName").val(communityName);
                            $("#ldCode").val(ldCode);
                            $("#dyCode").val(dyCode);
                        }
                        main.closeDialog();
                    },
                    cancel : function() {
                        main.closeDialog();
                    }
                }
            },
            onBeforeOpen: function() {
                var orgId = getOrgid() ;
                if(orgId){
                    return true;
                }else{
                    main.alert("温馨提示","请选择机构！")
                    return false;
                }

            }
        });
    }


    function holderLoop(){
          main.dialog({
                title: "选择人员",
                url: thisUiConfig.ctx + "/householder/selectHolder",
                width: 700,
                height: 440,
                contentWindow: {
                    params: {
                        singleSelect : true,
                        checkbox : false
                    },
                    actions: {
                        confirm: function(data) {
                            if (data) {
                                edit();
                                loadHouseHolder(data.householderId);
                                socialinfo(data.householderId);
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

</html>