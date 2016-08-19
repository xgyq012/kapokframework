<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>基础数据</title>
<link rel="stylesheet" href="${ctx}/resources/css/base.css">
<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css" type="text/css">
<!--[if IE 7]> 
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css"> 
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
<link rel="stylesheet" href="${ctx}/resources/css/base.css">
<style type="text/css">

html,body{
   overflow-x:hidden;
   overflow-y:hidden;
   height: 100%;
}

</style>

</head>
<body>
<div class="container">
	<div class="left">
		<div class="easyui-accordion" data-options="border:false">
	        <div class="left-menu" title="指挥中心">
	            <ul></ul>
	        </div>
       </div>
	</div>
	<div class="right">
		<div id="contentTabs" class="easyui-tabs g-tabs2" style="width:100%; height:100%;"></div>
	</div>
</div>


<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sis.js"></script>
<script type="text/javascript">

$(function() {
	var resource = main.getResource('CC'),
		resourceId = resource.resourceId,
		$ul = $('.left-menu > ul');

	$.ajax({
		url: '${ctx}/resource/getMenuByModuleParent/' + resourceId
	}).done(function(data) {
		data.forEach(function(i) {
			var icon = i.imagePath || 'fa fa-file-text',
				id = i.resourceCode,
				label = i.resourceLabel,
				url = i.url;	
			$ul.append('<li><a href="#" data-id="'+id+'" data-label="'+label+'" data-url="'+url+'" data-icon="'+icon+'"><i class="'+icon+'"></i>'+label+'</a></li>');
		});
	});
	
    function _addTab(options) {
        if (options) {
            var id = options.id || '',
                title = options.title || '',
                url = options.url || '',
                closable = options.closable || (options.closable === undefined);
            if (id && title && url) {
                var content = '<iframe id="ifr' + id + '" scrolling="no" frameborder="0" src="${ctx}' + url + '"></iframe>';
                if ($('#contentTabs').tabs('exists', title)) {
                    var tab = $('#contentTabs').tabs('getTab', title);
                    $('#contentTabs').tabs('update',{
                        tab : tab,
                        options : {
                            content : content,
                        }
                    });
                    $('#contentTabs').tabs('select', title);
                } 
                else {
                    $('#contentTabs').tabs('add',{
                        title : title,
                        bodyCls : "tabs-body",
                        border : false,
                        content : content,
                        closable : closable
                    });
                }
				var tab = $('#ifr'+id)[0].contentWindow;
                tab.main = main;
            }
        }
    };

	$(document).on('click', '.left-menu a', function(e) {
		var data = this.dataset,
			id = data.id,
			title = '<i class="'+data.icon+'"></i>&nbsp;' + data.label,
			url = data.url;
		_addTab({
			id: id,
			title: title,
			url: url,
			closable: true
		});
	});
});
	

</script>
</body>
</html>