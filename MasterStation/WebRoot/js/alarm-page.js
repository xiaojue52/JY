var Alarm = {}
Alarm.showPage = function (id,text) 
{
	$("#form").attr({"action":"updateAlarm.action?alarmTemp.id="+id});
	$("#textarea").text(text);
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	document.documentElement.scrollTop = 0;
}
Alarm.closePage = function () {
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
}

