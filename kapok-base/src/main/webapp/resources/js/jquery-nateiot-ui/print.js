$(function() {
	$(".print").on("click", function() {
		$(".print-button").hide();
		$("#printArea").removeClass("print-area");
		window.print();
		$(".print-button").show();
		$("#printArea").addClass("print-area");
	});
	
	$(".preview").on("click", function() {
		$(".print-button").hide();
		$("#printArea").removeClass("print-area");
		$.messager.show({
			title : "温馨提示",
			msg : "按“Esc”键退出预览",
			timeout : 2000,
			showType : "slide"
		});
	});
	
	$(document).keypress(function(event) {
		if (event.keyCode == 27) {
			$(".print-button").show();
			$("#printArea").addClass("print-area");
		}
	});
	
});