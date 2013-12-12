var RefreshPage = {
		url:"mainAction.action",
		load:function(){
			var stop = false;
			$(".monitor_checkbox").each(function(){
				//alert(this.checked);
				if (this.checked==true){
					//RefreshPage.loop();
					stop = true;
					return false;
				}
			});
			if(!$("#BgDiv").is(":hidden"))stop = true;
			if (stop==false)
				window.location = RefreshPage.url;
			else
				RefreshPage.loop();
		},
		time:1000*10,
		loop:function(){
			setTimeout(this.load,this.time);
		}
}
var QueryDeviceTemp = {
		cabinetList:[],
		getCabinetNumber:function(){
			QueryDeviceTemp.cabinetList.length=0;
			$(".monitor_checkbox").each(function(){
				if (this.checked==true){
					//alert()
					QueryDeviceTemp.cabinetList.push($(this).val());
				}
			});
			if (QueryDeviceTemp.cabinetList.length==0||QueryDeviceTemp.cabinetList[0].length==0){
				alert("请勾选所要查询的柜体！");
				return;
			}
			$.ajax({
				type:"post",
				url:"getTempDate.action?cabinetListStr="+QueryDeviceTemp.cabinetList,
				contentType:"json", 
				beforeSend:function(XMLHttpRequest){ $("body").append('<div id="load" style="z-index:10; position:absolute; width:99%;height:100%;top:0px;background-color: #e3e3e3;opacity: 0.5;filter: alpha(opacity = 50);-moz-opacity: 0.5;text-align:center"><img style="margin-top:250px;" src="images/loading.gif" /></div>'); },
				success:function(returnData){
					window.location = "mainAction.action";
				},
				error:function(){
					//alert("aa");
				}
			});
		}
}
var Monitor = {
	RefreshPage:RefreshPage,
	QueryDeviceTemp:QueryDeviceTemp
}
Monitor.RefreshPage.loop();

Monitor.showPage = function () {
	
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	document.documentElement.scrollTop = 0;
}
Monitor.closePage = function () {
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
	$('.page_iframe').attr('src',"");
}

Monitor.setPageFrameSrc = function(src){
	$('.page_iframe').attr('src',src);
	Monitor.showPage();
}