
var setEnable = function(id) {
	if (document.getElementById("alarmTypeEnable" + id).checked == true)
		$("#enable" + id).val(1);
	else
		$("#enable" + id).val(0);
};
var getUpTime = function() {
	
	$.ajax( {
		type : "post",
		url : "getMonitorTime.action",
		data:"constant.value="+$("#cabType").find("option:selected").text(),
		dataType:'text',
		success : function(returnData) {
			var obj = eval("("+returnData+")");
			if(obj.data=="0")
				alert("获取远端数据失败");
			else{
				$("#upTime").val(obj.data);	
			}
		},
		error:function(){
			alert("网络断开");
		}
	});
};
var upDateMonitorTime = function(){
	if (Config.setCabinetType()){
		$.ajax( {
			type : "post",
			url : "updateMonitorTime.action",
			data:"constant.subValue=" + $("#upTime").val()+"&constant.value="+$("#cabinetType").val(),
			dataType:'text',
			success : function(returnData) {
				var obj = eval("("+returnData+")");
				if(obj.data==1)
					alert("更新成功");
				else
					alert("更新失败");
			},
			error:function(){
				alert("更新失败");
			}
		});
	}
};
var upDateMonitorFunction = function(){
	$.ajax( {
		type : "post",
		url : "updateMonitorFunction.action",
		data:"functionNum=" + $("input:radio[name=functionNum]:checked").val(),
		dataType:'text',
		success : function(returnData) {
			var obj = eval("("+returnData+")");
			if(obj.data==1)
				alert("更新成功");
			else
				alert("更新失败");
		},
		error:function(){
			alert("更新失败");
		}
	});
};
var upDateMessage = function(){
	$.ajax( {
		type : "post",
		url : "updateMessageRevicer.action",
		data:"dateNumber=" + $("#mesDate").val()+"&username="+$("#mesUser").val(),
		dataType:'text',
		success : function(returnData) {
			var obj = eval("("+returnData+")");
			if(obj.data==1)
				alert("更新成功");
			else
				alert("更新失败");
		},
		error:function(){
			alert("更新失败");
		}
	});
};
var updateAlarmType = function(){
	if ($("#value1").val().length==0)
		$("#value1").val(0);
	if ($("#value2").val().length==0)
		$("#value2").val(0);
	if ($("#value3").val().length==0)
		$("#value3").val(0);
	if ($("#value4").val().length==0)
		$("#value4").val(0);
	if ($("#value5").val().length==0)
		$("#value5").val(0);
	$.ajax( {
		type : "post",
		url : "updateAlarmType.action",
		data:{
				"alarmType1.enable" : $("#enable1").val(),
				"alarmType1.value":$("#value1").val(),
				"alarmType2.enable":$("#enable2").val(),
				"alarmType2.value":$("#value2").val(),
				"alarmType3.enable":$("#enable3").val(),
				"alarmType3.value":$("#value3").val(),
				"alarmType4.enable":$("#enable4").val(),
				"alarmType4.subValue":$("#value4").val(),
				"alarmType4.value":$("#value5").val()
				},
		dataType:'json',
		success : function(obj) {
			if(obj.data==1)
				alert("更新成功");
			else
				alert("更新失败");
		},
		error:function(){
			alert("更新失败");
		}
	});
};
var setCabinetType = function() {
	var cabType = $("#cabType").find("option:selected").text();
	$("#cabinetType").val(cabType);
	if ($("#upTime").val() == null || $("#upTime").val().length == 0) {
		alert("请输入时间");
		return false;

	}
	return true;
};
var init = function() {
	$("#upTime").val($("#cabType").val());
	var enable1 = $("#enable1").val();
	var enable2 = $("#enable2").val();
	var enable3 = $("#enable3").val();
	var enable4 = $("#enable4").val();
	if (enable1 == 1)
		document.getElementById("alarmTypeEnable1").checked = true;
	else
		document.getElementById("alarmTypeEnable1").checked = false;
	if (enable2 == 1)
		document.getElementById("alarmTypeEnable2").checked = true;
	else
		document.getElementById("alarmTypeEnable2").checked = false;
	if (enable3 == 1)
		document.getElementById("alarmTypeEnable3").checked = true;
	else
		document.getElementById("alarmTypeEnable3").checked = false;
	if (enable4 == 1)
		document.getElementById("alarmTypeEnable4").checked = true;
	else
		document.getElementById("alarmTypeEnable4").checked = false;

	for (var i=1;i<29;i++){
		var option;
		if ($("#tempMesDate").val()==i)
			option = "<option value='"+i+"' selected='selected'>"+i+"号</option>";
		else
			option = "<option value='"+i+"'>"+i+"号</option>";
		$("#mesDate").append(option);
	}
	$("#mesUser").val($("#tempMesUser").val());
	$("input:radio[name=functionNum]").each(function(){
		var functionNum = $("#tempFunctionNum").val();
		if ($(this).val()==functionNum){
			this.checked = true;
			return false;
		}
	});
};
var Config = {
	init : init,
	setEnable : setEnable,
	getUpTime : getUpTime,
	setCabinetType : setCabinetType,
	upDateMonitorTime:upDateMonitorTime,
	upDateMessage:upDateMessage,
	upDateMonitorFunction:upDateMonitorFunction,
	updateAlarmType:updateAlarmType
};
Config.setHeartBeat = function(){
	var time = $('#heartBeatTime').val();
	if (time==null||time.length==0){
		alert("请输入时间");
	}else{
		$.ajax( {
			type : "post",
			url : "updateHeartBeatTime.action",
			data:"heartBeatTime=" + time,
			dataType:'text',
			success : function(returnData) {
				var obj = eval("("+returnData+")");
				if(obj.data==1)
					alert("更新成功");
				else
					alert("更新失败");
			},
			error:function(){
				alert("更新失败");
			}
		});
	}
};
Config.init();