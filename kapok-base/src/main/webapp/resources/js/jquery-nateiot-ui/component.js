(function($) {

	function lookupCreater(target) {

		$(target).hide();

		var opts = $.data(target, "lookup").options;
		var state = $.data(target, "lookup");
		var readonly = opts.readonly ? "readonly=\"readonly\"" : "";
		
		var lookup = $("<span class=\"combo\"></span>").insertAfter(target);
		var txtbox = $("<input class=\"combo-text\" type=\"text\" " + readonly + "/>").appendTo(lookup);
		var btn = $("<span><span class=\"combo-arrow\"></span></span>").appendTo(lookup);

		state.txtbox = txtbox;
		state.btn = btn;
		
		var id = $(target).attr("id");
		if (!id) {
			id = "lookupId_" + new Date().getTime();
		}
		var name = $(target).attr("name");
		if (!name) {
			name = "lookupName_" + new Date().getTime();
		}
		txtbox.attr("id", id);
		txtbox.attr("name", name);
		txtbox.validatebox({
			required : opts.required
		});

		var inputWidth =  $(target).width();
		var btnWidth = $(lookup).find(".combo-arrow").width();
		lookup.addClass("lookup");
		lookup.css("width", inputWidth);
		txtbox.css("width", inputWidth - btnWidth - 4);

		$(target).removeAttr("id").removeAttr("name");


		var iframeid = "iframe_" + id;

		var _dialogOpts = {
			title : opts.title,
			width : opts.width,
			height : opts.height,
			modal : true,
			content : "<iframe id=" + iframeid
					+ " scrolling=\"no\" frameborder=\"0\" src=\""
					+ opts.url
					+ "\" style=\"width:100%;height:100%;\"></iframe>",
			closed : true,
			buttons : opts.buttons,
			onClose : function() {
				state.dialog.dialog("destroy");
				state.dialog = null;
			},
			onOpen : function() {
				$("#"+iframeid)[0].contentWindow.actions = opts.actions;
				$("#"+iframeid)[0].contentWindow.params = opts.params;
			}
		};

		btn.on("click", function() {
			if (opts.disabled) {
				return;
			}
			if (!opts.onBeforeOpen()) {
				return;
			}
			opts.params["queryParams"] = opts.queryParams();
			if (!state.dialog) {
				var did = "dialog_" + id;
				_dialogOpts = $.extend({}, opts.dialog, _dialogOpts);

				var _dialog = $("<div/>").attr("id", did).dialog(_dialogOpts);
				$(_dialog).find(".panel-body").css("overflow", "hidden");
				state.dialog = _dialog;
				state.txtbox = txtbox;
			}
			state.dialog.dialog("open");
		});
		
		txtbox.keypress(function(event) {
			if (opts.disabled) {
				return;
			}
			if (event.which == 8) {
				opts.clear();
			}
		});
		
		txtbox.on("change", opts.onChange);
		
		setDisabled(target, opts.disabled);
	}
	
	function setDisabled(target, disabled){
		var state = $.data(target, "lookup");
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
	
	$.fn.lookup = function(options, param) {
		if (typeof options == "string") {
			return $.fn.lookup.methods[options](this, param);
        }

		options = options || {};
		return this.each(function() {
			var state = $.data(this, "lookup");
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, "lookup", {
					options : $.extend(true, {}, $.fn.lookup.defaults, options)
				});
			}
			lookupCreater(this);
		});
	};
	
	$.fn.lookup.methods = {
        open : function() {},
        close : function (jq) {
            return jq.each(function() {
                var state = $.data(this, "lookup");
                state.dialog.dialog("close");
                state.txtbox.validatebox("validate");
            });
        },
        enable: function(jq){
			return jq.each(function(){
				setDisabled(this, false);
			});
		},
		disable: function(jq){
			return jq.each(function(){
				setDisabled(this, true);
			});
		}
	};

	$.fn.lookup.defaults = {
		title : "选择",
		width : 480,
		height : 320,
		url : "",
		required : false,
		disabled : false,
		readonly : true,
		clear : function(){},
		queryParams : function(){},
		onBeforeOpen : function() {
			return true;
		},
		onChange : function(){}
	};
	
})(jQuery);

