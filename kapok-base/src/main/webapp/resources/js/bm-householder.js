
var uploadBtn ;

$(document).ready(function (){
	updateQueryParam();
	importDoc.init();
	tabs.init();
	tableInfo.init();
	baseInfo.init();
	orgLookup();
	houseLookupLookup();
	publicAttr.initCombobox();
	enableButtons(['add']);
});

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
})(jQuery);

function updateQueryParam(){
	var gllx = $.getUrlParam("q_gllx_EQ"); 
	var eduLevel = $.getUrlParam("q_eduLevel_EQ");
	var age = $.getUrlParam("q_age_GTE");
	var ageLT = $.getUrlParam("q_age_LT");
	var jzfs = $.getUrlParam("q_jzfs_EQ");
	var politicsStatus = $.getUrlParam("q_politicsStatus_EQ");
	var sex = $.getUrlParam("q_sex_EQ");
	var householdType = $.getUrlParam("q_householdType_EQ");
	if(gllx){
		$("input[name='q_gllx_EQ']").val(gllx);
	}else if(eduLevel){
		$("#queryForm").append("<input type='hidden' name='q_eduLevel_EQ' value='"+ eduLevel +"'>");
	}else if(age){
		$("input[name='q_age_GTE']").val(age);
	}else if(ageLT){
		$("#queryForm").append("<input type='hidden' name='q_age_LT' value='"+ ageLT +"'>");
	}else if(jzfs){
		$("#queryForm").append("<input type='hidden' name='q_jzfs_EQ' value='"+ jzfs +"'>");
	}else if(politicsStatus){
		$("#queryForm").append("<input type='hidden' name='q_politicsStatus_EQ' value='"+ politicsStatus +"'>");
	}else if(sex){
		$("input[name='q_sex_EQ']").val(sex);
	}else if(householdType){
		$("input[name='q_householdType_EQ']").val(householdType);
	}
}

var importDoc = {
		
	init : function (){
			
 		uploadBtn = $("#addPhoto").fileupload({
			url : thisUiConfig.ctx + "/doc/upload",
			upfile : "upfile",
			filetype : "jpg|jpeg|png",
			hidden : true,
			params : {
				directory : "photos"
			},
			onUploadComplete : function(result) {
				if (result.errcode) {
					$.messager.alert("温馨提示", result.errmsg, "info");
					return ;
				}
				$("#photofileID").val(result.docId);
				$("#docShowname").val(result.docShowname);
				baseInfo.showImg();
			}
			
		});
 		
 		$("#photofileUrl").on("click",function (){
			uploadBtn.fileupload('click');
		});
	}
		
}



var tabs = {
		
		isEdit : false ,
		
		init : function (){
			
			$("#mainTab").tabs({
				onSelect : function(title, index) {
					
					if (index == 0) { // 选中列表标签
						
						if ($("#listGrid").datagrid("getSelections").length > 0) {
							$("#mainTab").tabs("enableTab", 1);
							enableButtons([ "add", "del", "edit" ]);
						} else {
							$("#mainTab").tabs("disableTab", 1);
							enableButtons([ "add" ]);
						}
						tabs.isEdit = false;
					} else if (index == 1) { // 选中详细标签
						globalMethod.reset();
						loadData();
						if(tabs.isEdit){
							enableButtons([ "add", "del", "save" , "upfile" ]);
						}else{
							enableButtons([ "add", "del","edit"]);
						}
					}
				}
			});
		}
		
}


var globalMethod = {
		reset : function (){
			$("form:not(#queryForm)").each(function (){
				var id ="#"+$(this).attr("id");
				setFormItemDisabled(id,true,null);
				$(this).form("enableValidation");
				$(this).form("clear");
				$(this).removeClass("alreadyLoad");
			});
			$("#lnxx").find("tr.zvxx:gt(0)").remove();
			var imgUrl = thisUiConfig.ctx + "/resources/images/base/1.png";
			$("#photofileUrl").attr("src",imgUrl);
		},
		
		processing  : function (id){
			 var h = $("#detial").height()-50;
			 $("form:not(#queryForm,#baseForm,form.alreadyLoad)").each(function (){
				 var $form = $(this) ;
				 var top = $form.offset().top;
				 if( top<h && top>0 ){
					 //console.log($form.attr("id")+" top : "+top +" h : "+h );
					 $form.addClass("alreadyLoad");
					 otherInfo.getInfo($form);
				 }
			 });
		}
}


//基础信息初始化
var baseInfo =  {
		
	saveUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/save",
	
	getUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/get/",
	
	delUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/softDel/",
	
	search : thisUiConfig.ctx + "/" + thisUiConfig.url + "/search ",
		
	init : function (){
		
		
		
	},
	
	save : function (){
		
		if($("#baseForm").form("validate")){
			
			thisUiConfig.main.showWaiting();
			
			var data = getFormData("#baseForm");
			
			publicFormControl.ajax({
				type : "POST",
				url : baseInfo.saveUrl,
				data : data
			},function (result){
				
				var row = result.row;
				$("#baseForm").form("load",row);
				$("#houseId").lookup("setTxt",row.dyCode);
				$('#orgId').lookup('setTxt',publicAttr.getOrgName(row.orgId));
				baseInfo.showImg();
				otherInfo.linkBtnStatus();
				tableInfo.query();
				enableButtons([ "add","save","upfile"]);
				 
			},"show");
			
		}
	},
	
	add : function (){
		tabs.isEdit = false ;
		$("#listGrid").datagrid("unselectAll");
		$("#mainTab").tabs("enableTab", 1);
		$("#mainTab").tabs("select", 1);
		otherInfo.linkBtnStatus();
		globalMethod.reset();
		setFormItemDisabled("#baseForm",false,null);
		publicAttr.setDefaultsMesh("#orgId");
		enableButtons([ "add", "save","upfile" ]);
	},
	
	
	del:function (){
		
		var row = $("#listGrid").datagrid('getSelected');
		
		if (!row) {
			$.messager.alert("温馨提示", "请选择删除的信息！", "info");
			return;
		}
		
		$.messager.confirm("温馨提示", "确定删除人员【"+row.householderName+"】记录?",
			function(r) {
				if (r) {
					
					publicFormControl.ajax({
						url : baseInfo.delUrl + row[thisUiConfig.idName],
						type: "POST",
					},function (data){
						
						if (data.resultCode == "0") {
							$("#baseForm").form("clear");
							$("#mainTab").tabs("select", 0);
							$("#mainTab").tabs("disableTab", 1);
							enableButtons([ "add" ]);
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
			enableButtons([ "add", "del" , "save" , "upfile" ]);
		}
		setFormItemDisabled("#baseForm",false,null);
	},
	
	showImg : function (){
		var photofileID = $("#photofileID").val();
		var  imgUrl ="";
		if(photofileID && photofileID>0){
			imgUrl = thisUiConfig.ctx + "/doc/show/"+ photofileID
		}else{
			imgUrl = thisUiConfig.ctx + "/resources/images/base/1.png";
		}
		$("#photofileUrl").attr("src",imgUrl);
	},
	//初始化年龄
	initAge : function (){
		var birthDate = $("#birthDate").val();
		if(birthDate){
			var myDate=new Date();
			var age = Util.daysBetween(myDate.formatDate("yyyy-MM-dd"),birthDate)-1;
			$("#age").val(age);
		}
	}
};

function  loadData(){
	var row = $(thisUiConfig.tableList).datagrid("getSelected");
	if(row){
		var id = row[thisUiConfig.idName];
		
		if(id){
			var ajax = publicFormControl.ajax({
				type : "POST",
				url : baseInfo.getUrl +  id,
			},function (result){
				var row = result.row ;
				$("#baseForm").form("load",row);
				$("#houseId").lookup("setTxt",row.dyCode);
				baseInfo.initAge();
				baseInfo.showImg();
				otherInfo.init();
				otherInfo.linkBtnStatus();
				globalMethod.processing();
				$("#orgId").lookup('setTxt',publicAttr.getOrgName(row.orgId));
			});
			
		}
	}
}

//other info control
var otherInfo = {
		
		delInfo :  thisUiConfig.ctx + "/" + thisUiConfig.url + "/delInfo " ,
		
		getInfoUrl : thisUiConfig.ctx + "/" + thisUiConfig.url + "/getInfo " ,
		
		linkBtnStatus : function (){
			
			var isExist_hid = thisUiConfig.getId() || false ;
			
			//初始化按钮
			  $("form:not(#baseForm,#queryForm) .easyui-linkbutton").each(function (){
				if(isExist_hid){
					if($(this).hasClass("edit")){
						$(this).linkbutton("enable");
					}else{
						$(this).linkbutton("disable");
					}
				}else{
					$(this).linkbutton("disable");
				}
			}); 
		},
		
		init : function (){
			
			$("span.zvxx_remove").on('click',function (){
				var disable = $(this).parents("table").find("a.edit").linkbutton("options").disabled;
				if(disable){
					if($("tr.zvxx").length>1){
						$(this).parents("tr").remove();
					}else{
						thisUiConfig.main.info("温馨提示","无法删除，至少保留一个子女信息！");
					}
				}
			});
			
		},
		
		editBtn : function (obj){
			var parent = $(obj).parent();
			parent.find("a.save,a.del").each(function (){
				 $(this).linkbutton("enable");
			 });
			var formObj = parent.parents("form");
			var fid = "#" + formObj.attr("id");
			setFormItemDisabled(fid,false,null); //设置表达是否可用
			$(obj).linkbutton("disable");
		},
		
		saveBtn : function (obj){
			var parent = $(obj).parent();
			var formObj = parent.parents("form");
			otherInfo.save(formObj);
		},
		
		delBtn : function (obj){
			var parent = $(obj).parent();
			var formObj = parent.parents("form");
			otherInfo.del(formObj);
		},
		
		save : function (formObj){
			var fid = "#" + formObj.attr("id");
			var isSubmit = $(fid).form("validate");
			if(isSubmit){
				var data = getFormData(fid);
				data[thisUiConfig.idName] = thisUiConfig.getId();
				publicFormControl.ajax({
					type : "POST",
					url :formObj.attr("action"),
					data : data
				},function (result){
					
					formObj.form("load", result.row);
					
				},"show");
				
			}
			
		},
		
		saveLnxx : function (obj){
			
			var data = {};
			var parent = $(obj).parent();
			var formObj = parent.parents("form");
			var fid = "#" + formObj.attr("id");
			
			if($(fid).form("validate")){
				data[thisUiConfig.idName] = thisUiConfig.getId();
				formObj.find("tr.lnxx").each(function (){
					
					var checkboxFields = $(this).find(":input:checkbox:checked");
					var fields = $(this).find(":input:not(:radio, :checkbox)");
					
					checkboxFields.each(function(index, element) {
						var fieldName = $(element).attr("name");
						if (fieldName) {
							var fieldValue = $(element).val();
							data[fieldName] = fieldValue;
						}
					});
					
					fields.each(function(index, element) {
						var fieldName = $(element).attr("name");
						if (fieldName) {
							var fieldValue = $(element).val();
							data[fieldName] = fieldValue;
						}
					});
					
				});
				
				var dg = "cisBmOldPeopleL";
				formObj.find("tr.zvxx").each(function (index,e){
					$(e).find(":input:not(:radio,:checkbox)").each(function(i, element) {
						var fieldName = $(element).attr("name");
						if (fieldName) {
							var fieldValue = $(element).val();
							data[dg + "[" + index + "]." + fieldName ] =fieldValue;
						}
					});
				});
				
				publicFormControl.ajax({
					type : "POST",
					url :formObj.attr("action"),
					data : data
				},function (result){
					
					formObj.form("load", result.row);
					
				},"show");
				
			}
		
		},
		
		del : function (formObj){
			
			var householderId = formObj.find("input[name='"+thisUiConfig.idName+"']").val();
			var infoName = formObj.attr("infoName");
			
			if(householderId){
				
				publicFormControl.ajax({
					type : "POST",
					url : otherInfo.delInfo,
					data : {
						'type':infoName,
						'householderId':householderId
					}
				},function (result){
					formObj.form("enableValidation");
					formObj.form("clear");
				});
				
			}else{
				$.messager.alert("温馨提示", "数据不存在", "info");
			}
			
		},
		
		getInfo : function (formObj){
			var row = $(thisUiConfig.tableList).datagrid("getSelected");
			var householderId = thisUiConfig.getId() ||  (row ?  row[thisUiConfig.idName] : "") ;
			var infoName = formObj.attr("infoName");
			if(householderId){
				$.ajax({
					url : otherInfo.getInfoUrl,
					data : {
						'type':infoName,
						'householderId':householderId
					}
				}).done(function(result) {
					if(result){
						if(infoName=='lnxx'){
							formObj.form("load",result);
							var cisBmOldPeopleL = result.cisBmOldPeopleL;
							if(cisBmOldPeopleL){
								if(cisBmOldPeopleL.length>1){
									var tr = formObj.find("tr.zvxx:eq(0)");
									for (var i = 0; i < cisBmOldPeopleL.length-1; i++) {
										var a = tr.clone(true);
										tr.before(a);
										a.find("input").val("");
										a.find("input").each(function (){
											$(this).validatebox({});
										});
									}
								}
								formObj.find("tr.zvxx").each(function (index){
									var n = cisBmOldPeopleL[index];
									$(this).find("input").each(function (){
										var $e = $(this);
										var name = $e.attr("name")
										$e.val(n[name]);
									});
								});
							}
						}else{
							formObj.form("load",result);
						}
					}
				}).fail(function(jqXHR, textStatus, errorThrown) {
					thisUiConfig.main.error("温馨提示", "系统出错！");
				});
			}
		},
		
		addChildInfo : function (obj){
			var beforeTr = $(obj).parents("tr").before();
			var tr = $(obj).parents("tr").prev();
			var a = tr.clone(true);
			beforeTr.before(a);
			a.find("input").val("");
			a.find("input").each(function (){
				$(this).validatebox({});
			});
		}
}


//列表加载



function enableButtons(ids) {
	// 把所有按钮设置为不可用
	$(".easyui-linkbutton.toolbar").linkbutton("disable");
	$(".easyui-splitbutton.toolbar").linkbutton("disable");
	// 设置按钮可用
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).linkbutton("enable");
	}
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
				queryParams:tableInfo.getFormData("#queryForm"),
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
						if(value){
							return publicAttr.getOrgName(value);
						}
						return "";
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
					title:"与户主关系",
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
			var param = tableInfo.getFormData("#queryForm");
			$("#listGrid").datagrid("load", param);
			enableButtons([ "add" ]);
			$("#mainTab").tabs("disableTab", 1);
		},
		
		getFormData : function (formId){
			var meshIds = thisUiConfig.main.currentUserMesh.meshChildrenIds || "" ;
			var data =  getFormData(formId) || {};
			if(meshIds){
				data[thisUiConfig.meshIdName] = meshIds;
			}
			return data;
		}
	}


var Util = {
		
	//根据身份证获取生日日期
	getBirthdayFromIdCard : function(idCard) {
	  	var birthday = "";
		if(idCard != null && idCard != ""){
			if(idCard.length == 15){
				birthday = "19"+idCard.substr(6,6);
			} else if(idCard.length == 18){
				birthday = idCard.substr(6,8);
			}
		
			birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");
		}
		
		return birthday;
	  },
	  

	/** 计算两个日期间的年龄  */ 
	daysBetween : function (DateOne,DateTwo){   
	    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
	    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
	    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
	  
	    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
	    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
	    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
	  
	    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000/365);   
	   	var age = Math.abs(cha)+1+""; 
	    return age.split(".")[0]; 
	} 
}

//身份证计算出生日期
function addBirthDate (obj){
	var idCard = $(obj).val();
	var birthDate = Util.getBirthdayFromIdCard(idCard);
	$("#birthDate").datebox("setValue",birthDate);
	var myDate=new Date();
	var age = Util.daysBetween(myDate.formatDate("yyyy-MM-dd"),birthDate)-1;
	age = $.isNumeric(age) ? age : "";
	$("#age").val(age);
}
