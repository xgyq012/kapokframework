/**
 * Created by Will WM. Zhang on 2016-04-26 下午3:45.
 * ========================================================================
 * Gxwlui: base.js v0.0.1
 * ========================================================================
 */

!function ($, main) {

	"use strict";

	var Base = function(options) {};

	Base.DEFAULTS = {
		levelCount: 1
	};

	Base.init = function(options) {
		var opts = $.extend(true, {}, Base.DEFAULTS, options),
			resourceCode = opts.resourceCode,
			resource = main.getResource(resourceCode),
			resourceId = resource.resourceId,
			levelCount = opts.levelCount,
			workbenchUrl = opts.workbenchUrl,
			nav = $('#'+opts.navId);

		// 添加Accordion面板
		if (levelCount == 2) {
			$.ajax({
				url: main.ctx+'/resource/getMenuByModuleParent/' + resourceId
			}).done(function(data) {
				data.forEach(function(x) {
					var icon = x.imagePath || 'fa fa-file-text';
					x.resourceId = x.id;
					x.title = '<i class="'+icon+'"></i>&nbsp;&nbsp;&nbsp;&nbsp;'+x.resourceLabel;
				});
				_addPanel(nav, data);
			});
		}
		else {
			var title = '<i class="'+resource.imagePath+'"></i>&nbsp;&nbsp;&nbsp;&nbsp;'+resource.resourceLabel;
			_addPanel(nav, [{title:title,resourceId:resourceId}]);
		}

		// 添加工作台页面
		_addTab({
			id: resourceCode+'_WORKBENCH',
			title: '<i class="fa fa-home"></i>&nbsp;&nbsp;工作台',
			url: main.ctx+'/main/workbench?url='+workbenchUrl,
			closable: false
		});

		window.addTab = _addTab;
	};

	function _addPanel(nav, panels) {
		nav.accordion({
			onSelect: function (title,index) {
				var panel = nav.accordion('getPanel', index),
					$ul = panel.find('.left-menu>ul');
				if (!panel.hasClass('added')) {
					_addItem($ul, $ul.attr('data-resource-id'));
					panel.addClass('added');
				}
			}
		});
		panels.forEach(function(x, i) {
			nav.accordion('add', {
				title: x.title,
				content: '<div class="left-menu"><ul data-resource-id="'+x.resourceId+'"></ul></div>',
				selected: i == 0 ? true : false
			});
		});
	}

	function _addItem($ul, resourceId) {
		$.ajax({
			url: main.ctx+'/resource/getMenuByModuleParent/'+resourceId
		}).done(function(data) {
			if(data){
				data.forEach(function(x) {
					var icon = x.imagePath || 'fa fa-file-text',
						id = x.id,
						label = x.resourceLabel,
						url = x.url && main.ctx + x.url;
					$ul.append('<li><a href="#" data-resource-id="'+id+'" data-label="'+label+'" data-url="'+url+'" data-icon="'+icon+'"><i class="'+icon+'"></i>&nbsp;&nbsp;&nbsp;&nbsp;'+label+'</a></li>');
				});
			}
		});
	}

	function _addTab(options) {
		if (options) {
			var id = options.id || '',
				title = options.title || '',
				url = options.url || '',
				$tabs = $('#contentTabs'),
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

				$('#'+iframeId)[0].contentWindow.main = main;

				$('#'+iframeId).on('load', function(e) {
					var c = e.target.contentDocument.body.innerHTML;
					if (c.indexOf('SessionTimeout') != -1) {
						e.target.contentDocument.body.innerHTML = '';
						main.warn('温馨提示', '登录超时，请重新登录！', function() {
							window.parent.location.href = main.ctx+'/';
						});
					}
				});
			}
		}
	};

	$(document).on('click', '.left-menu a', function(e) {
		var data = this.dataset,
			id = data.resourceId,
			title = '<i class="'+data.icon+'"></i>&nbsp;&nbsp;' + data.label,
			url = data.url;
		_addTab({
			id: id,
			title: title,
			url: url,
			closable: true
		});
	});

	window.Base = Base;

}(window.jQuery, main);