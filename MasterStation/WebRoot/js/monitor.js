function showPage() {
	
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	document.documentElement.scrollTop = 0;
}
function closePage() {
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
}

function setPageFrameSrc(src){
	$('.page_iframe').attr('src',src);
	showPage();
}