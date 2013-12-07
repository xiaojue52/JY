(function getCurrentData(){
	$.ajax({
		type:"post",
		url:"getUnhandledCount.action",
		contentType:"json", 
		success:function(returnData){
			
			showCurrentData(returnData);
			setTimeout(getCurrentData,5000);
		},
		error:function(){
			//alert("aa");
			setTimeout(getCurrentData,5000);
		}
	});	
})();
function showCurrentData(returnData){
	var data = eval("("+returnData+")");
	$("#unhandledCount").text(data.unhandledCount);
}
