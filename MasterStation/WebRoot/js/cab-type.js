var CabType = {};
CabType.showPage = function(tag) {
	if (tag==0){
		$("#addCabType_dialogDiv").show();
		$("#userCabType_dialogDiv").hide();
	}else{
		$("#addCabType_dialogDiv").hide();
		$("#userCabType_dialogDiv").show();
	}
	$("#BgDiv").css({
		display : "block",
		height : $(document).height()
	});
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	document.documentElement.scrollTop = 0;
};
CabType.closePage = function() {
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
};
CabType.showCabType = function(id){
	//alert(id);
	$.ajax( {
		type : "post",
		url : "showCabType.action",
		data:"cabType.id="+id,
		dataType:'text',
		success : function(returnData) {
			//alert(returnData);
			var obj = eval("("+returnData+")");
			var cabType = obj.data;
			$("#cabType_value").val(cabType.value);
			$("#cabType_id").val(cabType.id);
			CabType.showPage(1);
		},
		error:function(){
			alert("网络断开");
		}
	});
};