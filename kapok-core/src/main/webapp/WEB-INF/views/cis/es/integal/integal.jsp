<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${system_name}</title>
	
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
<!-- 		<a onclick="add()" class="easyui-linkbutton g-button"><i class="fa fa-plus"></i>添加</a> -->
		<a onclick="edit()" class="easyui-linkbutton g-button"><i class="fa fa-edit"></i>编辑</a>
		<a onclick="save()" class="easyui-linkbutton g-button"><i class="fa fa-floppy-o"></i>保存</a>
		<a onclick="cancel()" class="easyui-linkbutton g-button"><i class="fa fa-reply-all"></i>取消编辑</a>
	</div>
	
	<div id="list" class="g-container">
		<table id="listGrid" style="height:100%;"></table>
	</div>
</div>
	
</body>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>

<script type="text/javascript">
var datagrid; //定义全局变量datagrid
var editRow = undefined; 

/**
 * 脚本入口
 */
 $(function(){
	 datagridInit();
// 	 $('#listGrid').datagrid('getColumnFields').attr("disabled", true);
// 	 $('#listGrid').datagrid('getColumnOption', fields[1]).attr("disabled", true);
 });
 
  /**
   * 初始化数据表格
   */
  function datagridInit(){
          
// 	  	   var editRow = undefined; //定义全局变量：当前编辑的行
          
          $("#listGrid").datagrid({
			  rownumbers : true,
			  singleSelect : true,
			  autoRowHeight : false,
			  border : false,
			  fitColumns : true,
			  pageSize : defaultPageSize,
			  pageList : defaultPageList,
			  pagination : true,
              url : "${ctx}/integal/search",
              columns : [[{
        			field : "integalId",
	     			title : "主键id",
	     			align :'left',
	     			halign:'center',
	    			hidden: true
        		},{
        			field : "integalCode",
	      			title : "积分编码 ",
	      			align:'left',
	      			halign:'center',
	      			width : 120
// 					editor:{type:'validatebox',options:{required:true,editable:false}}
        		},{
        			field : "integalName",
	      			title : "积分名称",
	      			align:'left',
	      			halign:'center',
	      			width : 120,
					editor:{type:'validatebox',options:{required:true}}
        		},{
        			field : "score",
	      			title : "分值",
	      			align:'left',
	      			halign:'center',
	      			width : 120,
					editor:{type:'validatebox',options:{required:true}}
        		},{
        			field : "remark",
	      			title : "描述",
	      			align:'left',
	      			halign:'center',
	      			width : 200,
					editor:{type:'validatebox',options:{required:true}}
        		}
        		]],
        		onSelect : function(rowIndex, rowData) {
        			
        		},
        		onAfterEdit: function (rowIndex, rowData, changes) {
                  //endEdit该方法触发此事件
                  console.info(rowData);
                  editRow = undefined;
                },
                onDblClickRow: function (rowIndex, rowData) {
                //双击开启编辑行
                  if (editRow != undefined) {
                	  $("#listGrid").datagrid("endEdit", editRow);
                  }
                  if (editRow == undefined) {
                	  $("#listGrid").datagrid("beginEdit", rowIndex);
                      editRow = rowIndex;
                  }
              }
          });
	  }
  
  //添加
  function add(){
	 //如果只选择了一行则可以进行修改，否则不操作
	  var rows = $("#listGrid").datagrid("getSelections");
	  
      alert(rows.length);
      
      if (rows.length == 0) {
		//添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
	      if (editRow != undefined) {
	    	  $("#listGrid").datagrid("endEdit", editRow);
	      }
	      //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
	      if (editRow == undefined) {
	    	  $("#listGrid").datagrid("insertRow", {
	              index: 0, // index start with 0
	              row: {

	              }
	          });
	          //将新插入的那一行开户编辑状态
	          $("#listGrid").datagrid("beginEdit", 0);
	          //给当前编辑的行赋值
	          editRow = 0;
	      }
	  }
      
  }
  
  //保存
  function save(){
	//保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
      $("#listGrid").datagrid("endEdit", editRow);
      
	  var number = $("#listGrid").datagrid("getSelected");
	  var index = $("#listGrid").datagrid("getRowIndex", number);
	  console.log(index);
	  
	  if(index != -1){
		  var saveUrl = "${ctx}/integal/save";
	       
	      $("#listGrid").datagrid('endEdit', index);
	      var row = $("#listGrid").datagrid("getRows")[index];
	      $.ajax({
	          url      : saveUrl, 
	          data     : row, 
	          type     : 'post', 
	          dataType : 'json', 
	          success  : function(data) {
	             $.messager.alert("提示" , "保存成功" , "info");
	             
	          },
	          error:function(data){
	             $.messager.alert("提示" , "保存失败" , "error");
	          }
	      });
	  }
      
  }
	
    //取消编辑
	function cancel(){
		 //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
        editRow = undefined;
        $("#listGrid").datagrid("rejectChanges");
        $("#listGrid").datagrid("unselectAll");
  }
    
   //编辑
   function edit(){
	 var editRow = undefined; 
	 //修改时要获取选择到的行
       var rows = $("#listGrid").datagrid("getSelections");
       //如果只选择了一行则可以进行修改，否则不操作
       if (rows.length == 1) {
           //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
           if (editRow != undefined) {
        	   $("#listGrid").datagrid("endEdit", editRow);
           }
           //当无编辑行时
           if (editRow == undefined) {
               //获取到当前选择行的下标
               var index = $("#listGrid").datagrid("getRowIndex", rows[0]);
               //开启编辑
               $("#listGrid").datagrid("beginEdit", index);
               //把当前开启编辑的行赋值给全局变量editRow
               editRow = index;
               //当开启了当前选择行的编辑状态之后，
               //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
               $("#listGrid").datagrid("unselectAll");
           }
       }
   }
   
</script>
</html>