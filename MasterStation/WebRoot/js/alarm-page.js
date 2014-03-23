var Alarm = {};
Alarm.showPage = function (id,text) 
{
	$("#form").attr({"action":"updateAlarm.action?alarmTemp.id="+id});
	$("#textarea").text(text);
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	//var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	document.documentElement.scrollTop = 0;
};
Alarm.closePage = function () {
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
};

Alarm.updateMultiple = {
	list : [],
	updateMultipleAlarm:function(){
		Alarm.updateMultiple.list.length = 0;
		$(".history_checkbox").each(function(){
			if(this.checked){
				Alarm.updateMultiple.list.push($(this).val());
			}
		});
		if (Alarm.updateMultiple.list.length==0||Alarm.updateMultiple.list[0].length==0){
			alert("请勾选相应条目！");
			return;
		}
		$.ajax({
			type : "post",
			url : "updateMultipleAlarm.action",
			data:"alarmIdListStr="+Alarm.updateMultiple.list,
			dataType:'text',
			beforeSend:function(XMLHttpRequest){ $("body").append('<div id="load" style="z-index:10; position:absolute; width:99%;height:100%;top:0px;background-color: #e3e3e3;opacity: 0.5;filter: alpha(opacity = 50);-moz-opacity: 0.5;text-align:center"><img style="margin-top:250px;" src="images/loading.gif" /></div>'); },
			success : function(returnData) {
				//var obj = eval("("+returnData+")");
				window.location = "listAlarm.action";
			},
			error:function(){
				alert("更新失败");
				window.location = "listAlarm.action";
			}
		});
	}
};
Alarm.selectAll = function (){
	var select_all_alarm = document.getElementById("select_all_alarm");
	if (select_all_alarm.checked){
		//alert("checked");
		$(".history_checkbox").each(function(){
			this.checked = true;
		});
	}else{
		$(".history_checkbox").each(function(){
			this.checked = false;
		});
	}
};