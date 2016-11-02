/**
 * Created by Will WM. Zhang on 2015-09-24 下午4:37.
 * ========================================================================
 * Gxwlui: lookup.js v0.0.1
 * ========================================================================
 */

!function ($, main) {

    "use strict";

    // LOOKUP CLASS DEFINITION
    // ======================

    var Lookup = function(domEle, options) {
        this.domEle = domEle;
        this.$domEle = $(domEle);
        this.options = $.extend(true, {}, Lookup.DEFAULTS, options);
    };
    
    Lookup.DEFAULTS = {
		title: '选择',
		url: '',
		valueField: '',
		textField: '',
		required: false,
		disabled: false,
		editable: false,
		innerDialog: false,
		contentWindow: {},
		onBeforeOpen : function() {
			return true;
		}
    };
    
    Lookup.prototype.click = function() {
        return _btnOnclick.call(this);
    };
    
    Lookup.prototype.enable = function() {
    	_setDisable.call(this, false);
    };
    
    Lookup.prototype.disable = function() {
    	_setDisable.call(this, true);
    };
    
    Lookup.prototype.setVal = function(val) {
    	return this.valbox.val(val);
    };
    
    Lookup.prototype.getVal = function() {
    	return this.valbox.val();
    };
    
    Lookup.prototype.setTxt = function(txt) {
    	return this.txtbox.val(txt);
    };
    
    Lookup.prototype.getTxt = function() {
    	return this.txtbox.val();
    };
    
    Lookup.prototype.clear = function() {
    	this.txtbox.val(null);
    	this.valbox.val(null);
    }
    
    // Lookup 内部方法
    // ===============

    function _init() {
    	_render.call(this);
    }

	function _render() {
		var lookup = this,
			opts = lookup.options,
			$domEle = lookup.$domEle,
			disabled = opts.disabled ? 'disabled' : '',
			editable = opts.editable ? '' : 'readonly',
			name = $domEle.attr('name');
		
		var lkp = $('<div class="g-lookup">').insertAfter($domEle),
			btn = $('<a class="' + disabled + '"><i class="fa fa-ellipsis-h"></i></a>').appendTo(lkp),
			txtbox = $('<input class="easyui-validatebox form-control" ' + disabled + ' ' + editable + '>').appendTo(lkp),
			valbox = $('<input class="textbox-value" type="hidden" name="' + name +'">').insertAfter(txtbox);
		
		txtbox.validatebox({
			required: opts.required
		});
		
		lookup.btn = btn;
		lookup.txtbox = txtbox;
		lookup.valbox = valbox;
		
		btn.on('click', lookup, function(e) {
			_btnOnclick.call(e.data);
		});
		
		$domEle.removeAttr("name");
		$domEle.hide();
	}
	
	function _btnOnclick() {
		var lookup = this,
			opts = lookup.options,
			$domEle = lookup.$domEle,
			id = $domEle.attr('id'),
			iframeid = 'iframe_' + id;
		
		var _contentWindow = {
			params: {
				singleSelect: true,
				checkbox: false
			},
			actions: {
				confirm: function(data) {
					if (data) {
						if (data[opts.textField]) lookup.txtbox.val(data[opts.textField]);
						if (data[opts.valueField]) lookup.valbox.val(data[opts.valueField]);
						lookup.txtbox.validatebox('validate');
						if (opts.innerDialog) {
							main.closeInnerDialog();
						}
						else {
							main.closeDialog();
						}
					}
				},
				cancel: function() {
					if (opts.innerDialog) {
						main.closeInnerDialog();
					}
					else {
						main.closeDialog();
					}
				}
			}
		};
		var _dialogOpts = {
			id: iframeid,
			title: opts.title,
			url: opts.url,
			width: opts.width,
			height: opts.height,
			cache: false,
			modal: true,
			contentWindow: $.extend(true, {}, _contentWindow, opts.contentWindow)
		};
		if (opts.disabled) return;
		if (!opts.onBeforeOpen()) return;
		if (opts.innerDialog) {
			main.innerDialog($.extend(true, {}, _dialogOpts));
		}
		else {
			main.dialog($.extend(true, {}, _dialogOpts));
		}
	}
	
	function _setDisable(disabled){
		var lookup = this,
			opts = lookup.options;
		if (disabled) {
			opts.disabled = true;
			lookup.btn.addClass('disabled');
			lookup.txtbox.attr('disabled', 'disabled');
		} else {
			opts.disabled = false;
			lookup.btn.removeClass('disabled');
			lookup.txtbox.removeAttr('disabled');
		}
	}
	
    function Plugin(options, params) {
        if (typeof options == 'string') {
            var $this = $(this),
            	lookup = $this.data('gxwl.lookup');
            if (lookup) return lookup[options].call(lookup, params);
        }
        return this.each(function () {
            var $this = $(this),
            	lookup = $this.data('gxwl.lookup');
            if (lookup) {
                $.extend(true, lookup.options, options);
            }
            else {
            	lookup = new Lookup(this, options);
                $this.data('gxwl.lookup', lookup);
                _init.call(lookup);
            }
        });
    }
    
    var old = $.fn.lookup;

    $.fn.lookup = Plugin;
    $.fn.lookup.Constructor = Lookup;

    // LOOKUP NO CONFLICT
    // ===============

    $.fn.lookup.noConflict = function () {
        $.fn.lookup = old;
        return this;
    };
	
}(window.jQuery, main);


