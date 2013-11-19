
(function getCurrentData(){
	$.ajax({
		type:"post",
		url:"getData",
		data:{"method":"getCurrentData"},
		contentType:"application/x-www-form-urlencoded;charset=utf-8", 
		success:function(returnData){
			//alert(returnData);
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
	//var data = eval(returnData);
	$("#exceptionCount").text("("+returnData+")");
	if (returnData>0){
		//alert("");
		$("#alert_palyer").get(0).play();
	}
	else
		$("#alert_palyer").get(0).pause();
	/*for(var i=0;i< data.length;i=i+1){
		//alert("."+data[i].status);
		var resultObj = $("#"+data[i].name);
		var resultObjStatus = $("#"+data[i].name+"status");
		resultObj.html(data[i].value);
		resultObjStatus.html(data[i].status);
	}*/
}
