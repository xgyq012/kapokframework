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
			preview : false,
			directory : "",
			importSuccess: function(){
			}
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
	var fileForm = $("<form id=\"importDataForm\" method=\"POST\" enctype=\"multipart/form-data\" target=\"frameFile\"></form>").insertAfter($("body"));
	var fileInput = $("<input type=\"file\" id=\"upfile\" name=\"upfile\"/>").appendTo(fileForm);
	var previewInput = $("<input type=\"hidden\" name=\"preview\" />").appendTo(fileForm);
	var directoryInput = $("<input type=\"hidden\" name=\"directory\" id=\"directory\"/>").appendTo(fileForm);
	var sub = $("<iframe id=\"frameFile\" name=\"frameFile\" ></iframe>").appendTo(fileForm);
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
