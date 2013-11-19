
function del() {
	if (confirm("确认删除记录？")) {
		return true;
	}
	return false;
}

function delLine(){
	if (confirm("确认删除记录？\n删除电缆会同时删除上面的设备！")) {
		return true;
	}
	return false;
}
function checkNumber(){
	var numberRegex = /^[+-]?\d+(\.\d+)?([eE][+-]?\d+)?$/;
	var str = $('#numberInput').val();
	if(numberRegex.test(str)) {
	   $('#numberForm').submit();
	}
	else
		alert("请输入数字！");
}
function getGprsById(id){
	$.ajax({
		type:"post",
		url:"getData",
		data:{"method":"getGprsById","id":id},
		contentType:"application/x-www-form-urlencoded;charset=utf-8", 
		success:function(returnData){
			if (returnData.length==3){
				$("#gprs .id").text("");
				$("#gprs .number").text("");
				$("#gprs .comNumber").text("");
				$("#gprs .deviceBox").text("");
				$("#gprs .owner").text("");
				$("#gprs .status").text("");
				$("#gprs .note").text("");
			}
			else{
			//alert(returnData);
				var data = eval("("+returnData+")");
				$("#gprs .id").text(data.id);
				$("#gprs .number").text(data.number);
				$("#gprs .comNumber").text(data.comNumber);
				$("#gprs .deviceBox").text(data.deviceBox);
				$("#gprs .owner").text(data.owner);
				$("#gprs .status").text(data.status);
				$("#gprs .note").text(data.note);
			}
		},
		error:function(){
			//alert("aa");
			$("#gprs .id").text("");
			$("#gprs .number").text("");
			$("#gprs .comNumber").text("");
			$("#gprs .deviceBox").text("");
			$("#gprs .owner").text("");
			$("#gprs .status").text("");
			$("#gprs .note").text("");
		}
	});	
	
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	$("#page1").hide();
	$("#page2").show();
	document.documentElement.scrollTop = 0;
}

function getDeviceBoxById(id){
	$.ajax({
		type:"post",
		url:"getData",
		data:{"method":"getDeviceBoxById","id":id},
		contentType:"application/x-www-form-urlencoded;charset=utf-8", 
		success:function(returnData){
			if (returnData.length==3){
				$("#deviceBox .id").text("");
				$("#deviceBox .name").text("");
				$("#deviceBox .identify").text("");
				$("#deviceBox .type").text("");
				$("#deviceBox .owner").text("");
				$("#deviceBox .deviceNumber").text("");
				$("#deviceBox .powerLevel").text("");
				$("#deviceBox .note").text("");
			}
			else{
				var data = eval("("+returnData+")");
				$("#deviceBox .id").text(data.id);
				$("#deviceBox .name").text(data.name);
				$("#deviceBox .identify").text(data.identify);
				$("#deviceBox .type").text(data.type);
				$("#deviceBox .owner").text(data.owner);
				$("#deviceBox .deviceNumber").text(data.deviceNumber);
				$("#deviceBox .powerLevel").text(data.powerLevel);
				$("#deviceBox .note").text(data.note);
			}
		},
		error:function(){
			//alert("aa");
			$("#deviceBox .id").text("");
			$("#deviceBox .name").text("");
			$("#deviceBox .identify").text("");
			$("#deviceBox .type").text("");
			$("#deviceBox .owner").text("");
			$("#deviceBox .deviceNumber").text("");
			$("#deviceBox .powerLevel").text("");
			$("#deviceBox .note").text("");
		}
	});	
	
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	$("#page1").show();
	$("#page2").hide();
	document.documentElement.scrollTop = 0;
}