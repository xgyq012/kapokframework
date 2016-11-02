(function($) {

	$.fn.excelimport1 = function(options, param) {
		if (typeof options == "string") {
            var method = $.fn.excelimport1.methods[options];
            if (method) {
                return method(this, param);
            } else {
                return this.excelimport1(options, param);
            }
        }

		var defaultOpts = {
			title : "预览",
			width : 600,
			height : 400,
			url : "",
			templateUrl : "",
			preview : false,
			directory : "",
			importSuccess: function(){
			}
		};

		var opts = $.extend(true, defaultOpts, options);

		return this.each(function() {
			var state = $.data(this, "excelimport1");
			if (state) {
				$.extend(state.options, opts);
			} else {
				$.data(this, "excelimport1", {
					options : opts
				});
			}
			excelBtnCreater2(this);
		});
	};

	$.fn.excelimport1.methods = {
        close: function (jq) {
            return jq.each(function() {
                var state = $.data(this, "excelimport1");
                state.dialog.dialog("close");
            });
        }
	};

})(jQuery);

function excelBtnCreater2(target) {

	var state = $.data(target, "excelimport1");
	var fileForm = $("<form id=\"importDataForm1\" method=\"POST\" enctype=\"multipart/form-data\" target=\"frameFile1\"></form>").insertAfter($("body"));
	var fileInput = $("<input type=\"file\" id=\"upfile1\" name=\"upfile1\"/>").appendTo(fileForm);
	var previewInput = $("<input type=\"hidden\" name=\"preview1\" />").appendTo(fileForm);
	var directoryInput = $("<input type=\"hidden\" name=\"directory1\" id=\"directory1\"/>").appendTo(fileForm);
	var sub = $("<iframe id=\"frameFile1\" name=\"frameFile1\" ></iframe>").appendTo(fileForm);
	fileForm.hide();
	$(target).on("click", function() {
		previewInput.val(state.options.preview);
		directoryInput.val(state.options.directory);
		if (state.options.url) {
			fileForm.attr("action", state.options.url);
		}
		fileInput.click();
	});
}
