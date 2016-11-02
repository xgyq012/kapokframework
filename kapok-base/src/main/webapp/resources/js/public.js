var publicAttr = {
		
	main : window.main,	
	//根据网格id获取网格名称
	getOrgName : function (orgId){
		if(orgId){
			return publicAttr.main.getMeshName(orgId);
		}
		return "";
	},
	//根据字典类型和值 获取字典text值
	getDictText : function (dictCode,dictValue){
		if (dictCode && dictValue) {
			var dicts = publicAttr.main.dict.dicts;
			return  dicts[dictCode] ? (dicts[dictCode][dictValue] ? dicts[dictCode][dictValue].dictName : '') : '';
		}
		return "";
	},
	//根据字典类型获取字典信息
	getDictData : function (dictCode){
		
		if(dictCode){
			return main.dict.dicts[dictCode].list ;
		}
		return [];
	},
	//初始化字典
	initCombobox : function (){
			$(".easyui-combobox").each(function (){
				var $this = $(this);
				var dictCode = $this.attr("dictCode");
				if(dictCode){
					var data = publicAttr.getDictData(dictCode);
					$this.combobox("loadData",data);
				}
			});
	},
	//设置默认当前的网格
	setDefaultsMesh : function (e) {
		var main = publicAttr.main;
		var meshName = main.currentUserMesh.meshName;
		var meshId = main.currentUserMesh.meshId;
		$(e).lookup("setTxt",meshName);
		$(e).lookup("setVal",meshId);
	}
	
	
}


var publicFormControl = {
		
		main : window.main,
		
		ajax : function (options,func,isMsg){
			
			publicFormControl.main.showWaiting();
			
			var ajax  = $.ajax(options).done(function(result) {
				func.call(this,result);
				publicFormControl.main.closeWaiting();
				!(isMsg=='show') || MsgInfo.show("",result.resultMsg);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				publicFormControl.main.closeWaiting();
				publicFormControl.main.error("温馨提示", "系统出错！");
			});
			
			return ajax ;
			
		}
		
}


var	MsgInfo = {
		
	 show : function (title,msg){
		 
		 	title = title || "温馨提示" ;
		 	
		 	msg = msg || "操作成功" ;
		 
			$.messager.show({
				title : "温馨提示",
				msg : msg ,
				timeout : 2500,
				showType : "slide"
			});
	 }
			
}
