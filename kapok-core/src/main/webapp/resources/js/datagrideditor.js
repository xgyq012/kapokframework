(function($) {

	function _createDatagridEditor(target) {
		
		$(target).hide();
		
		var state = $.data(target, "datagrideditor");
		var opts = $.data(target, "datagrideditor").options;
		
		if (!opts.datagrid) {
			return;
		}
		
		opts.editable = true;
		opts.lasteditindex = -1;
		
		var _onClickRow = function(index, row) {
			if (opts.lasteditindex >= 0) {
				if ($(opts.datagrid).datagrid("validateRow", opts.lasteditindex)) {
					$(opts.datagrid).datagrid("acceptChanges");
					$(opts.datagrid).datagrid("unselectRow", opts.lasteditindex);
					opts.lasteditindex = -1;
				} else {
					$(opts.datagrid).datagrid("unselectAll");
					$(opts.datagrid).datagrid("selectRow", opts.lasteditindex);
				}
			}
		};
		
		var _onDblClickRow = function(index, row) {
			if (opts.editable) {
				if ($(opts.datagrid).datagrid("validateRow", opts.lasteditindex)) {
					$(opts.datagrid).datagrid("acceptChanges");
					$(opts.datagrid).datagrid("unselectAll");
					$(opts.datagrid).datagrid("selectRow", index).datagrid("beginEdit", index);
					opts.lasteditindex = index;
				}
			}
		};
		
		var options = $(opts.datagrid).datagrid("options");

		options.onClickRow = _onClickRow;
		options.onDblClickRow = _onDblClickRow;
	}
	
	$.fn.datagrideditor = function(options, param) {
		if (typeof options == "string") {
			return $.fn.datagrideditor.methods[options](this, param);
        }

		options = options || {};
		return this.each(function() {
			var state = $.data(this, "datagrideditor");
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, "datagrideditor", {
					options : $.extend(true, {}, $.fn.datagrideditor.defaults, options)
				});
			}
			_createDatagridEditor(this);
		});
	}
	
	$.fn.datagrideditor.methods = {
		addRow : function(jq, data) {
			return jq.each(function() {
				var opts = $.data(this, "datagrideditor").options;
			    if ($(opts.datagrid).datagrid("validateRow", opts.lasteditindex)) {
			    	$(opts.datagrid).datagrid("acceptChanges");
			    	$(opts.datagrid).datagrid("unselectAll");
			        $(opts.datagrid).datagrid('insertRow',{index : 0,  row: data});
			        $(opts.datagrid).datagrid("selectRow", 0).datagrid("beginEdit", 0);
			        opts.lasteditindex = 0;
			    }
        	});
		},
		delRow : function(jq) {
        	return jq.each(function() {
        		var opts = $.data(this, "datagrideditor").options;
        	    var rows = $(opts.datagrid).datagrid("getSelections");
        	    var copyRows = [];
        	    for (var j= 0; j < rows.length; j++) {
        			copyRows.push(rows[j]);
        		}
        	    for(var i = 0; i < copyRows.length; i++) {    
        	        var index = $(opts.datagrid).datagrid("getRowIndex", copyRows[i]);
        	        $(opts.datagrid).datagrid("deleteRow", index); 
        		}
        	    opts.lasteditindex = -1;
        	});
        },
        setEditable : function(jq, editable) {
            return jq.each(function() {
            	var opts = $.data(this, "datagrideditor").options;
            	opts.editable = editable;
            });
        },
        acceptChanges : function(jq) {
            return jq.each(function() {
            	var opts = $.data(this, "datagrideditor").options;
            	$(opts.datagrid).datagrid("acceptChanges");
        		$(opts.datagrid).datagrid("unselectAll");
            });
        },
        clear : function(jq) {
            return jq.each(function() {
            	var opts = $.data(this, "datagrideditor").options;
            	$(opts.datagrid).datagrid("loadData", {"total" : 0, "rows" : []});
            });
        }
	};

	$.fn.datagrideditor.defaults = {
		datagrid : {}
	};
	
})(jQuery);

