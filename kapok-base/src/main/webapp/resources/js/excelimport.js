(function($) {

	$.fn.excelimport = function(options, param) {
		if (typeof options == "string") {
            var method = $.fn.excelimport.methods[options];
            if (method) {
                return method(this, param);
            } else {
                return this.excelimport(options, param);
            }
        }

		var defaultOpts = {
			title : "预览",
			width : 600,
			height : 400,
			url : "",
			templateUrl : "",
			preview : false
		};

		var opts = $.extend(true, defaultOpts, options);

		return this.each(function() {
			var state = $.data(this, "excelimport");
			if (state) {
				$.extend(state.options, opts);
			} else {
				$.data(this, "excelimport", {
					options : opts
				});
			}
			excelBtnCreater(this);
		});
	};

	$.fn.excelimport.methods = {
        close: function (jq) {
            return jq.each(function() {
                var state = $.data(this, "excelimport");
                state.dialog.dialog("close");
            });
        }
	};

})(jQuery);

function excelBtnCreater(target) {

	var state = $.data(target, "excelimport");
	
	var fileForm = $("<form method=\"POST\" enctype=\"multipart/form-data\"></form>").insertAfter(target);
	var fileInput = $("<input type=\"file\" name=\"file\"/>").appendTo(fileForm);
	var previewInput = $("<input type=\"hidden\" name=\"preview\" />").appendTo(fileForm);
	fileForm.hide();

	if (state.options.templateUrl) {
//		$(target).attr("class", "easyui-splitbutton");
//		var mm1 = $("<div id=\"mm1\" style=\"width:100px;\"></div>").insertAfter(target);
//		var btn1 = $("<div data-options=\"iconCls:'icon-download'\">下载模板</div>").appendTo(mm1);
//		btn1.on("click", function() {
//			fileForm.form("submit", {
//				url : state.options.templateUrl
//			});
//		});
	}
	
	$(target).on("click", function() {
		fileInput.click();
	});
	
	fileInput.on("change", function() {
		previewInput.val(state.options.preview);
		fileFormSubmit(state.options.preview);
	});
	
	var fileFormSubmit = function(preview) {
		$.messager.progress();
		fileForm.form("submit", {
			url : state.options.url,
			success: function(data) {
				$.messager.progress("close");
				var res = eval('(' + data + ')'); 
				if (res.resultCode == 0) {
					if (preview) {
						if (!state.dialog) {
							var did = "dialog_" + new Date().getTime();
							var iframeid = "iframe_" + new Date().getTime();
							var previewUrl = state.options.url.substring(0, state.options.url.lastIndexOf("/")) + "/preview";
							var _dialogOpts = {
									title : state.options.title,
									width : state.options.width,
									height : state.options.height,
									modal : true,
									content : "<iframe id=" + iframeid
									+ " scrolling=\"no\" frameborder=\"0\" src=\""
									+ previewUrl
									+ "\" style=\"width:100%;height:100%;\"></iframe>",
									closed : true,
									buttons : state.options.buttons,
									onClose : function() {
										state.dialog.dialog("destroy");
										state.dialog = null;
									},
									onOpen : function() {
										$("#"+iframeid)[0].contentWindow.actions = {
											import : function() {
												state.dialog.dialog("close");
												previewInput.val(false);
												fileFormSubmit(false);
											},
											cancel : function() {
												state.dialog.dialog("close");
											}
										};
										$("#"+iframeid)[0].contentWindow.params = {
											headers : res.headers,
											data : res.rows
										};
									}
							};
							_dialogOpts = $.extend({}, state.options.dialog, _dialogOpts);

							var _dialog = $("<div/>").attr("id", did).dialog(_dialogOpts);
							$(_dialog).find(".panel-body").css("overflow", "hidden");
							state.dialog = _dialog;
						}
						state.dialog.dialog("open");
					} else {
						$.messager.show({
							title: "温馨提示",
							msg: res.resultMsg,
							timeout: 2500,
							showType: "slide"
						});
					}
				} else {
					$.messager.alert("温馨提示", res.resultMsg, "error");
				}
			}
		});
	};
	
}