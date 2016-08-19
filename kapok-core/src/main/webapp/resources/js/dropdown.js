/**
 * Created by Will WM. Zhang on 2016-03-22 下午4:37.
 * ========================================================================
 * Gxwlui: dropdown.js v0.0.1
 * ========================================================================
 */

!function($) {

    "use strict";

    // Dropdown CLASS DEFINITION
    // ======================

    var Dropdown = function(domEle, options) {
    	this.domEle = domEle;
        this.$domEle = $(domEle);
        this.$dropdownMenu = this.$domEle.find('.dropdown-menu');
        this.$toggle = this.$domEle.find('.toggle');
        this.options = $.extend(true, {}, Dropdown.DEFAULTS, options);

        this.$toggle.on('click.gxwl.dropdown', this.toggle);
    };

    Dropdown.DEFAULTS = {};

    Dropdown.prototype.toggle = function(e) {
	    var $toggle = $(this),
	    	$parent = $toggle.parent(),
	    	isActive = $parent.hasClass('open');

		_clearMenus();

	    if (!isActive) {
			$toggle.trigger('focus').attr('aria-expanded', 'true');
      		$parent.toggleClass('open');

            var $dropdownMenu = $parent.find('.dropdown-menu'),
                $toggle = $parent.find('.toggle'),
                width = $parent.outerWidth(true),
                height = $parent.outerHeight(true),
                left = $parent.offset().left,
                top = $parent.offset().top,
                dropdownMenuWidth = $dropdownMenu.outerWidth(true),
                dropdownMenuHeight = $dropdownMenu.outerHeight(true),
                dropdownMenuLeft = $dropdownMenu.offset().left,
                dropdownMenuTop = $dropdownMenu.offset().top;

            // console.log('width', width);
            // console.log('height', height);
            // console.log('left', left);
            // console.log('top', top);
            // console.log('dropdownMenuWidth', dropdownMenuWidth);
            // console.log('dropdownMenuHeight', dropdownMenuHeight);
            // console.log('dropdownMenuLeft', dropdownMenuLeft);
            // console.log('dropdownMenuTop', dropdownMenuTop);

            $dropdownMenu.offset({left:(left-(dropdownMenuWidth-width)), top:(height+top+8)});
	    }
	    return false;
    };

    function _clearMenus(e) {
    	$('.toggle').each(function() {
    		var $toggle = $(this),
    			$parent = $toggle.parent();
    		if (!$parent.hasClass('open')) return;
    		$toggle.attr('aria-expanded', 'false');
      		$parent.removeClass('open');
    	});
    }

    function _init() {
    }

    function Plugin(options, params) {
        if (typeof options == 'string') {
            var $this = $(this),
                dropdown = $this.data('gxwl.dropdown');
            if (dropdown) return dropdown[options].call(dropdown, params);
        }
        return this.each(function () {
            var $this = $(this),
                dropdown = $this.data('gxwl.dropdown');
            if (dropdown) {
                var oldOpts = $.extend(true, {}, dropdown.options);
                $.extend(true, dropdown.options, options);
            }
            else {
                dropdown = new Dropdown(this, options);
                $this.data('gxwl.dropdown', dropdown);
                _init.call(dropdown);
            }
        });
    }

    $(document)
    	.on('click.gxwl.dropdown.data-api', _clearMenus)
    	.on('click.gxwl.dropdown.data-api', '.toggle', Dropdown.prototype.toggle);

	$(function () {
        $(".dropdown").each(function(i) {
            var $this = $(this);
            Plugin.call($this, {});
        });
    });

}(window.jQuery);