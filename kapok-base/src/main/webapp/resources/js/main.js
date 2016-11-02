/**
 * Created by Will WM. Zhang on 2016-03-16 下午4:37.
 * ========================================================================
 * Gxwlui: main.js v0.0.1
 * ========================================================================
 */

!function(window, $) {

    "use strict";

    // Main CLASS DEFINITION
    // ======================

    var Main = function (options) {
        this.options = options;
        this.tabs = options.tabs;
        this.cache = options.cache;
        this.ctx = options.ctx;
    };

    Main.DEFAULTS = {
        tabs: {},
        cache: {}
    };

    Main.prototype.addTab = function(options) {
        if (options) {
            var main = this,
                id = options.id || '',
                title = options.title || '',
                url = options.url || '',
                $tabs = $('#mainTabs'),
                closable = options.closable || (options.closable === undefined);
            if (id && title && url) {
                var iframeId = 'tab_ifr_'+id,
                    content = '<iframe id="'+iframeId+'" scrolling="no" frameborder="0" src="'+url+'"></iframe>';
                if ($tabs.tabs('exists', title)) {
                    var tab = $tabs.tabs('getTab', title);
                    $tabs.tabs('update', {
                        tab: tab,
                        options: {
                            content: content,
                        }
                    });
                    $tabs.tabs('select', title);
                }
                else {
                    $tabs.tabs('add',{
                        title: title,
                        bodyCls: "tabs-body",
                        border: false,
                        content: content,
                        closable: closable
                    });
                }

                var tab = $('#'+iframeId)[0].contentWindow;
                main.tabs[id] = tab;
                tab.main = main;

                $('#'+iframeId).on('load', function(e) {
                    var c = e.target.contentDocument.body.innerHTML;
                    if (c.indexOf('SessionTimeout') != -1) {
                        e.target.contentDocument.body.innerHTML = '';
                        main.warn('温馨提示', '登录超时，请重新登录！', function() {
                            window.top.location.href = main.ctx+'/';
                        });
                    }
                });
            }
        }
    };

    Main.prototype.ready = function(callback, params, o) {
        var main = this,
            options = main.options,
            o = o || main;

        var resourceAjax = _createAjaxObject(options.cacheOptions.resource),
            dictAjax = _createAjaxObject(options.cacheOptions.dict);

        $.when(resourceAjax, dictAjax).done(function() {
            if (callback) callback.call(o, params);
        });

        function _createAjaxObject(options) {
            var url = options.url,
                method = options.method || 'GET',
                data = options.data,
                done = options.done;
            return url ? $.ajax({url:url,method:method,data:data}).done(done) : {};
        }

    };

    Main.prototype.getResourceId = function(resourceCode) {
        return this.resources[resourceCode].resourceId;
    };

    Main.prototype.getResource = function(resourceCode) {
        return this.resources[resourceCode];
    };

    Main.prototype.getMeshName = function(meshId) {
        return this.mesh[meshId] ? this.mesh[meshId].meshName : '';
    };

    Main.prototype.getMesh = function(meshId) {
        return this.mesh[meshId];
    };

    Main.prototype.show = function(options) {
        var options = options || {},
            defaultOptions = {
                title: "温馨提示",
                msg: "操作成功",
                timeout: 1000,
                showType: "fade",
                style: {
                    right: '',
                    bottom: ''
                }
            };
        $.messager.show($.extend(true, {}, defaultOptions, options));
    };

    Main.prototype.alert = function(title, msg, icon, fn) {
        $.messager.alert(title, msg, icon, fn);
    }

    Main.prototype.error = function(title, msg, fn) {
        Main.prototype.alert(title, msg, 'error', fn);
    }

    Main.prototype.info = function(title, msg, fn) {
        Main.prototype.alert(title, msg, 'info', fn);
    }

    Main.prototype.warn = function(title, msg, fn) {
        Main.prototype.alert(title, msg, 'warning', fn);
    }

    Main.prototype.confirm = function(title, msg, fn) {
        $.messager.confirm(title, msg, fn);
    }

    Main.prototype.showWaiting = function(options) {
        $.messager.progress(options);
    }

    Main.prototype.closeWaiting = function() {
        $.messager.progress('close');
    }

    Main.prototype.dialog = function(options, dialogId) {
        var iframdId = 'dialog_ifr_'+options.id,
            url = options.url,
            contentWindow = options.contentWindow,
            content = '<iframe id="' + iframdId + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>';

        delete options.id;
        delete options.url;
        delete options.data;

        options.content = content;

        dialogId = dialogId || 'dialog'
        $('#'+dialogId).dialog(options).dialog("open");

        $('#'+iframdId)[0].contentWindow.main = this;
        $('#'+iframdId)[0].contentWindow.params = contentWindow.params;
        $('#'+iframdId)[0].contentWindow.actions = contentWindow.actions;

        $('#'+dialogId).css('overflow', 'hidden');
    }

    Main.prototype.closeDialog = function() {
        $('#dialog').dialog("close");
    }

    Main.prototype.innerDialog = function(options) {
        this.dialog(options, 'innerDialog');
    }

    Main.prototype.closeInnerDialog = function() {
        $('#innerDialog').dialog("close");
    }

    Main.prototype.getIframeContentWindow = function(id) {
        return $(id)[0].contentWindow;
    }

    window.createMain = function(options) {
        options = $.extend(true, {}, Main.DEFAULTS, options || {});
        return new Main(options);
    };

}(window, window.jQuery);

