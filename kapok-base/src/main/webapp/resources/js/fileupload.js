(function($) {
	
	var _filetype = {
		unknow : "application/octet-stream",
		zip : "application/octet-stream",
		ZIP : "application/octet-stream",
		apk : "application/octet-stream",
		txt : "text/plain",
		doc : "application/msword",
		dot : "application/msword",
		docx : "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
		xls : "application/vnd.ms-excel",
		xlsx : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
		ppt : "application/vnd.ms-powerpoint",
		pptx : "application/vnd.openxmlformats-officedocument.presentationml.presentation",
		pdf : "application/pdf",
		jpg : "image/jpeg",
		jpeg : "image/jpeg",
		png : "image/png",
		gif : "image/gif",
		mp4 : "video/mp4",
		mkv : "video/mkv",
		mp3 : "audio/mp3"
	};

	function _createFileupload(target) {

		var state = $.data(target, "fileupload");
		var opts = state.options;
		var readonly = opts.readonly ? "readonly=\"readonly\"" : "";
		
		var fileupload = $("<span class=\"fileupload\"></span>").insertAfter(target);
		var txtbox = $("<input class=\"fileupload-text\" type=\"text\" " + readonly + "/>").appendTo(fileupload);
		var btn = $("<span><span class=\"fileupload-btn\">选择文件</span></span>").appendTo(fileupload);
		
		state.txtbox = txtbox;
		state.btn = btn;
		txtbox.validatebox({
			required : opts.required
		});
		
		var id = $(target).attr("id");
		if (id) {
			txtbox.attr("id", id);
		}
		var name = $(target).attr("name");
		if (name) {
			txtbox.attr("name", name);
		}
		$(target).removeAttr("id").removeAttr("name");
		var inputWidth =  $(target).width();
		var btnWidth = $(btn).find(".fileupload-btn").width();
		fileupload.css("width", inputWidth);
		txtbox.css("width", inputWidth - btnWidth - 4);

		if (opts.hidden) {
			$(fileupload).hide();
		}

		var _id = opts.upfile;
		var iframe = $('<iframe id="ifr_' + _id + '" name="ifr_' + _id + '" style="position:absolute;left:-999px;top:-999px;"></iframe>');
		var form = $('<form action="' + opts.url + '" enctype="multipart/form-data" method="post" target="ifr_' + _id + '" id="form_' + _id + '" style="position:absolute;left:-999px;top:-999px;"/>');
		for (var p in opts.params) {  
            $(form).append('<input type="hidden" name="' + p + '" value="' + opts.params[p] + '"/>');  
        }
		var file = $('<input id="' + _id + '" name="upfile" type="file"/>');
		var filetype = _getFileType(opts.filetype);
		var accept = "";
		for (var f in filetype) {
			if (f) {
				accept = "," + "." + f + accept;
			}
		}
		file.attr("accept", accept.substring(1));

        $(target).hide();  
        
        $("body").append(form);
        $("body").append(iframe);
        
        var isIframeFirstLoad = true;
        
        $(file).change(function(e) {
	    	if (!opts.onBeforeSelectFile()) {
	    		return;
	    	}
	        var filepath = $(this).val();
	        var index = filepath.lastIndexOf("\\");
	        var dotIndex = filepath.lastIndexOf(".");;
	        var filename = index != -1 ? filepath.substr(index + 1) : filepath;
	        var extension = filepath.substr(dotIndex + 1);
	        if (!filetype[extension]) {
	        	$.messager.alert("温馨提示", "不支持的文件类型[" + extension + "]。", "warning");
	        	return;
	        }
	        
	        $(txtbox).val(filename);
	        
	        $(form).append($(this));
	        isIframeFirstLoad = false;
	        $(form).submit();
	        $.messager.progress();
        });  
    
        $(iframe).load(function() {
        	if (!isIframeFirstLoad) {
        		$.messager.progress("close");
        		$(file).val("");
        		$(file).detach();
        		var contents = $(this).contents().get(0);
        		var data = $(contents).find("body").text();
        		$(txtbox).validatebox("validate");
        		try {
        			var result = eval("(" + data + ")")
    				opts.onUploadComplete(result);
        		}
        		catch(exception) {
        			opts.onUploadComplete(data);
        		}
        	}
        });
		
		state.btnOnClick = function() {
			if (opts.disabled) {
				return;
			}
			if (!opts.onBeforeSelectFile()) {
				return;
			}
			$(file).click();
		};
		
		btn.on("click", state.btnOnClick);
		
		_setDisabled(target, opts.disabled);
	}
	
	function _setDisabled(target, disabled){
		var state = $.data(target, "fileupload");
		var opts = state.options;
		if (disabled) {
			opts.disabled = true;
			state.txtbox.addClass("readonly");
			state.txtbox.validatebox({
				required : false
			});
		} else {
			opts.disabled = false;
			state.txtbox.removeClass("readonly");
			state.txtbox.validatebox({
				required : opts.required
			});
		}
	}
	
	function _getFileType(filetypeString) {
		if (filetypeString) {
			var filetype = [];
			var filetypes = filetypeString.split("|");
			for (var i = 0; i < filetypes.length; i++) {
				if (_filetype[filetypes[i]]) {
					filetype[filetypes[i]] = _filetype[filetypes[i]];
				}
			}
			return filetype;
		} else {
			return _filetype;
		}
	}
	
	$.fn.fileupload = function(options, param) {
		if (typeof options == "string") {
			return $.fn.fileupload.methods[options](this, param);
        }

		options = options || {};
		return this.each(function() {
			var state = $.data(this, "fileupload");
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, "fileupload", {
					options : $.extend(true, {}, $.fn.fileupload.defaults, options)
				});
			}
			_createFileupload(this);
		});
	};
	
	$.fn.fileupload.methods = {
        click : function(jq) {
        	return jq.each(function() {
        		var state = $.data(this, "fileupload");
                state.btnOnClick();
        	});
        },
        enable : function(jq){
			return jq.each(function() {
				_setDisabled(this, false);
			});
		},
		disable : function(jq){
			return jq.each(function() {
				_setDisabled(this, true);
			});
		}
	};

	$.fn.fileupload.defaults = {
		url : "",
		upfile : "upfile",
		filetype : "",
		params : {},
		required : false,
		disabled : false,
		readonly : true,
		hidden : false,
		
		onBeforeSelectFile : function() {return true;},
		onUploadComplete : function(result) {}
	};
	
})(jQuery);

