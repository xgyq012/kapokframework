// 设置默认PageLayout
var defaultPageLayout = [ 'list', 'sep', 'first', 'prev', 'sep', 'manual', 'sep', 'next', 'last' ];

// 设置默认PageSize
var defaultPageSize = 20;

// 设置默认PageList
var defaultPageList = [ 10, 20, 30, 40, 50 ];

// 设置全局的ajax请求
$.ajaxSetup({cache : false});

$(document).ajaxComplete(function(event, xhr, settings) {
	var res = xhr.responseJSON;
	var url = '/' + settings.url.split('/')[1];
	if (res && res.errorCode === 'SessionTimeout') {
		alert('登录超时，请重新登录！');
		window.parent.location.href = url;
	}
});

function enableButtons(buttons) {
	buttons.forEach(function(x, i) {
		$('#'+x).linkbutton('enable');
	});
}

function disableButtons(buttons) {
	buttons.forEach(function(x, i) {
		$('#'+x).linkbutton('disable');
	});
}

function enableFormItem(form, exclude) {
	setFormItemDisabled(form, false, exclude);
}

function disableFormItem(form, exclude) {
	setFormItemDisabled(form, true, exclude);
}

function setFormItemDisabled(form, disabled, exclude) {
	var allInput = $(form).find(':input');
	allInput.each(function(index, element) {
		var name = $(element).attr('name');
		if (exclude && exclude.indexOf(name) != -1) {
			return;
		}
		var clazz = $(element).attr('class');
		if (clazz) {
			if (clazz.indexOf('easyui-validatebox') != -1) {
				$(element).attr('disabled', disabled);
			}
			if (clazz.indexOf('easyui-combobox') != -1) {
				$(element).combobox(disabled?'disable':'enable');
			}
			if (clazz.indexOf('easyui-datebox') != -1) {
				$(element).datebox(disabled?'disable':'enable');
			}
			if (clazz.indexOf('easyui-datetimebox') != -1) {
				$(element).datetimebox(disabled?'disable':'enable');
			}
		} else {
			$(element).attr('disabled', disabled);
		}
	});
}

// 清空查询表单
function clearQueryForm(queryForm) {
	$(queryForm).form('clear');
}

// 自定表单校验类型
$.extend($.fn.validatebox.defaults.rules, {
	//检测是否为正整数
	checkNumber : {
		validator : function(value, param) {
			var reg =/^[0-9]*$/;
			return reg.test(value);
		},
		message : '该输入正整数'
	},
    
	//日期验证
	 TimeCheck:{
	     validator:function(value,param){     
	      var s = $("input[name="+param[0]+"]").val();
	      //因为日期是统一格式的所以可以直接比较字符串 否则需要Date.parse(_date)转换
	      return value >= s;
	     },
	     message:'非法数据'
	    },

	//验证身份证号码
	checkIdCode : {
		validator : function(value, param) {
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			return reg.test(value);
		},
		message : '该输入正确的身份证号码'
	},
	
	//验证金额
	money : {
		validator : function(value) {
			
			var reg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?(\u5143)|(\u4e07)|(\u4ebf)$/;
			return reg.test(value);
		},
		message : '请输入正确的金额格式'
	},
	
	stringCheck : {
		validator : function(value, param) {
			var reg = /^[\u0391-\uFFE5\w]+$/gi;
			return reg.test(value);
		},
		message : '该输入项只能包括中文字、英文字母、数字和下划线'
	},
	remoteCheckUnique : {
		validator : function(value, param) {
			var params = {};
			params[param[1]] = $('#'+param[1]).val();
			params[param[2]] = value;
			var result = $.ajax({
				url : param[0],
				dataType : 'json',
				data : params,
				async : false,
				cache : false,
				type : 'post'
			}).responseJSON;
			return result.resultCode == '0';
		},
		message : '输入的值已经存在。'
	}
});

// 获取表单数据
function getFormData(dataForm, dataGrid) {
	var data = {};
	var fields = $(dataForm).find(':input:not(:radio, :checkbox)');
	var radioFields = $(dataForm).find(':input:radio:checked');
	// var checkboxFields = $(dataForm).find(':input:checkbox:checked');
	fields.each(function(index, element) {
		var fieldName = $(element).attr('name');
		var fieldValue = $(element).val();
		if (fieldName) {
			data[fieldName] = fieldValue;
		}
	});
	radioFields.each(function(index, element) {
		var fieldName = $(element).attr('name');
		var fieldValue = $(element).val();
		if (fieldName) {
			data[fieldName] = fieldValue;
		}
	});
	// checkboxFields.each(function(index, element) {
	// var fieldName = $(element).attr('name');
	// var fieldValue = $(element).val();
	// data[fieldName] = fieldValue;
	// });
	if (dataGrid) {
		if (typeof dataGrid == 'string') {
			var rowData = $(dataGrid).datagrid('getData').rows;
			var columnFields = $(dataGrid).datagrid('getColumnFields').concat(
					$(dataGrid).datagrid('getColumnFields', true));
			for (var i = 0; i < rowData.length; i++) {
				for (var j = 0; j < columnFields.length; j++) {
					data[dataGrid + '[' + i + '].' + columnFields[j]] = rowData[i][columnFields[j]];
				}
			}
		} else {
			for (var i = 0; i < dataGrid.length; i++) {
				var rowData = $(dataGrid[i]).datagrid('getData').rows;
				var columnFields = $(dataGrid[i]).datagrid('getColumnFields')
						.concat(
								$(dataGrid[i])
										.datagrid('getColumnFields', true));
				for (var j = 0; j < rowData.length; j++) {
					for (var k = 0; k < columnFields.length; k++) {
						data[dataGrid[i] + '[' + j + '].' + columnFields[k]] = rowData[j][columnFields[k]];
					}
				}
			}
		}
	} else {
		var subTable = $(dataForm).find('.easyui-datagrid');
		for (var j = 0; j < subTable.length; j++) {
			var subTableId = $(subTable[j]).attr('id');
			if (subTableId) {
				var rowData = $('#' + subTableId).datagrid('getData').rows;
				var columnFields = $('#' + subTableId).datagrid(
						'getColumnFields').concat(
						$('#' + subTableId).datagrid('getColumnFields', true));
				var dg = subTableId.substring(3);
				for (var k = 0; k < rowData.length; k++) {
					for (var l = 0; l < columnFields.length; l++) {
						data[dg + '[' + k + '].' + columnFields[l]] = rowData[k][columnFields[l]];
					}
				}
			}
		}
	}
	return data;
}

// 列表的名称列过长的就显示提示文字
function showTip(value) {
	return '<span title="' + value + '">' + value + '</span>';
}
// 转化列表的名称列，名称过长的样式
function changeCellStyle(value) {
	var maxLength = 30;
	if (value.replace(/[^\x00-\xff]/g, 'xx').length <= maxLength) {
		return value;
	}

	var str = new String();
	var len = 0;
	var strChar;
	for (var i = 0; strChar = value.charAt(i); i++) {
		str += strChar;
		len += (strChar.match(/[^\x00-\xff]/) != null ? 2 : 1);
		if (len >= maxLength) {
			break;
		}
	}
	return 'background-image:url(../resources/images/icons/tip.png);	background-repeat:no-repeat; background-position:right; z-index: 100;';
	// return str + '...';
}

//为date类添加一个format方法
//yyyy 年
//MM 月
//dd 日
//hh 小时
//mm 分
//ss 秒
//qq 季度
//S  毫秒
Date.prototype.formatDate = function (format){
	var o = {
	    "M+": this.getMonth() + 1, //month
	    "d+": this.getDate(),    //day
	    "h+": this.getHours(),   //hour
	    "m+": this.getMinutes(), //minute
	    "s+": this.getSeconds(), //second
	    "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
	    "S": this.getMilliseconds() //millisecond
	}
	if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
	(this.getFullYear() + '').substr(4 - RegExp.$1.length));
	for (var k in o) if (new RegExp('(' + k + ')').test(format))
	    format = format.replace(RegExp.$1,
	  RegExp.$1.length == 1 ? o[k] :
	    ('00' + o[k]).substr(('' + o[k]).length));
	return format;
}
