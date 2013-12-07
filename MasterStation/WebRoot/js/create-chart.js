var createDayChart = function() {
	//alert($('#dayDevice').val());
	var startTime = $('#dayLineDate').val();
	if (startTime.length==0){alert("请选择日期！");return;}
	var endTime = startTime;
	$.ajax( {
		type : "post",
		url : "dayChart.action?queryDeviceId=" + $('#dayDevice').val()+"&queryStartDate="+startTime+"&queryEndDate="+endTime,
		contentType : "json",
		success : function(returnData) {

			var obj = eval("(" + returnData + ")");
			//alert(obj.B);
			$('#container').highcharts(
					{
						title : {
							text : '日温度曲线',
							x : -20
						// center
						},
						subtitle : {
							text : '温度对比',
							x : -20
						},
						xAxis : {
							categories : [ '0:00', '', '1:00', '',
									'2:00', '', '3:00', '', '4:00',
									'', '5:00', '', '6:00', '',
									'7:00', '', '8:00', '', '9:00',
									'', '10:00', '', '11:00', '',
									'12:00', '', '13:00', '',
									'14:00', '', '15:00', '',
									'16:00', '', '17:00', '',
									'18:00', '', '19:00', '',
									'20:00', '', '21:00', '',
									'22:00', '', '23:00', '' ]
						},
						yAxis : {
							title : {
								text : '温度 (°C)'
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#808080'
							} ]
						},
						tooltip : {
							valueSuffix : '°C'
						},
						legend : {
							layout : 'vertical',
							align : 'right',
							verticalAlign : 'middle',
							borderWidth : 0
						},
						series : [ {
							name : "A相",
							data : obj.A
						}, {
							name : 'B相',
							data : obj.B
						}, {
							name : 'C相',
							data : obj.C
						}, {
							name : '环境',
							data : obj.D
						} ],
						exporting : {
							enabled : false
						}
					});
		},
		error : function() {

		}
	});
}

var createMonthChart = function() {
	var startTime = $('#monthLineDate').val();
	if (startTime.length==0){alert("请选择日期！");return;}
	var endTime = startTime+"-31";
	startTime = startTime + "-01";
	//var temp = startTime.substring(5,7);
	//alert(endTime);
	$.ajax( {
		type : "post",
		url : "monthChart.action?queryDeviceId=" + $('#monthDevice').val()+"&queryStartDate="+startTime+"&queryEndDate="+endTime,
		contentType : "json",
		success : function(returnData) {

			var obj = eval("(" + returnData + ")");
			//alert(obj.A);
			$('#container').highcharts(
					{
						title : {
							text : '月温度曲线',
							x : -20
						// center
						},
						subtitle : {
							text : '温度对比',
							x : -20
						},
						xAxis : {
							categories : [ '1号', '2号', '3号', '4号', '5号', '6号',
									'7号', '8号', '9号', '10号', '11号', '12号',
									'13号', '14号', '15号', '16号', '17号', '18号',
									'19号', '20号', '21号', '22号', '23号', '24号',
									'25号', '26号', '27号', '28号', '29号', '30号',
									'31号' ]
						},
						yAxis : {
							title : {
								text : '温度 (°C)'
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#808080'
							} ]
						},
						tooltip : {
							valueSuffix : '°C'
						},
						legend : {
							layout : 'vertical',
							align : 'right',
							verticalAlign : 'middle',
							borderWidth : 0
						},
						series : [
								{
									name : 'A相Max',
									data : obj.AMax
								},
								{
									name : 'A相Min',
									data : obj.AMin
								},
								{
									name : 'B相Max',
									data : obj.BMax
								},
								{
									name : 'B相Min',
									data : obj.BMin
								} ,
								{
									name : 'C相Max',
									data : obj.CMax
								},
								{
									name : 'C相Min',
									data : obj.CMin
								},
								{
									name : '环境Max',
									data : obj.DMax
								},
								{
									name : '环境Min',
									data : obj.DMin
								}],
						exporting : {
							enabled : false
						}
					});
		},
		error : function() {

		}
	});
}

var createMoreChart = function() {
	var startTime = $('#moreLineStartDate').val();
	var endTime = $('#moreLineEndDate').val();
	if (startTime.length==0){alert("请选择日期！");return;}
	if (endTime.length==0){alert("请选择日期！");return;}

	var queryStrings=[];
	$("#moreDevice option").each(function(){
		queryStrings.push($(this).val());
	});
	//alert(queryStrings);
	$.ajax( {
		type : "post",
		url : "moreChart.action?queryStrings=" + queryStrings+"&queryStartDate="+startTime+"&queryEndDate="+endTime,
		contentType : "json",
		success : function(returnData) {

			var obj = eval("(" + returnData + ")");
			//alert(obj.A);
			$('#container').highcharts(
					{
						title : {
							text : '月温度曲线',
							x : -20
						// center
						},
						subtitle : {
							text : '温度对比',
							x : -20
						},
						xAxis : {
							categories : [ '1月', '2月', '3月', '4月', '5月', '6月',
									'7月', '8月', '9月', '10月', '11月', '12月' ]
						},
						yAxis : {
							title : {
								text : '温度 (°C)'
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#808080'
							} ]
						},
						tooltip : {
							valueSuffix : '°C'
						},
						legend : {
							layout : 'vertical',
							align : 'right',
							verticalAlign : 'middle',
							borderWidth : 0
						},
						series : [
								{
									name : 'A相',
									data : [ 7.0, 6.9, 9.5, 14.5, 18.2, 21.5,
											25.2, 26.5, 23.3, 18.3, 13.9, 9.6 ]
								},
								{
									name : 'B相',
									data : [ -0.2, 0.8, 5.7, 11.3, 17.0, 22.0,
											24.8, 24.1, 20.1, 14.1, 8.6, 2.5 ]
								},
								{
									name : 'C相',
									data : [ -0.9, 0.6, 3.5, 8.4, 13.5, 17.0,
											18.6, 17.9, 14.3, 9.0, 3.9, 1.0 ]
								},
								{
									name : '环境',
									data : [ 3.9, 4.2, 5.7, 8.5, 11.9, 15.2,
											17.0, 16.6, 14.2, 10.3, 6.6, 4.8 ]
								} ],
						exporting : {
							enabled : false
						}
					});
		},
		error : function() {

		}
	});
}

var Chart = {
	dayChart : createDayChart,
	monthChart : createMonthChart,
	moreChart : createMoreChart
}
//Chart.dayChart();
var showedTag = 0;
function dayBtnClick() {
	$('#dayMenu').show();
	$('#monthMenu').hide();
	$('#moreMenu').hide();
	$('#selectedDevice').html('');
	showedTag = 0;
}
function monthBtnClick() {
	$('#dayMenu').hide();
	$('#monthMenu').show();
	$('#moreMenu').hide();
	$('#selectedDevice').html('');
	showedTag = 1;
}
function moreBtnClick() {
	$('#dayMenu').hide();
	$('#monthMenu').hide();
	$('#moreMenu').show();
	$('#selectedDevice').html('');
	showedTag = 2;
}

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
	$('.page_iframe').attr('src', "");
}

function setPageFrameSrc(src) {
	$('.page_iframe').attr('src', src);
	showPage();
}
function selectedDevice(tag) {
	if ($(('#selectedDevice')).find('option').length == 0)
		return;
	// alert(tag);
	closePage();
	if (showedTag == 0) {
		$("#dayDevice").empty();
		$("#selectedDevice option").each(
				function() {
					var tempId = $(this).val();
					var text = $(this).text();
					$("#dayDevice").append(
							"<option value='" + tempId + "'>" + text
									+ "</option>");
				});
	}
	if (showedTag == 1) {
		$("#monthDevice").empty();
		$("#selectedDevice option").each(
				function() {
					var tempId = $(this).val();
					var text = $(this).text();
					$("#monthDevice").append(
							"<option value='" + tempId + "'>" + text
									+ "</option>");
				});
	}
	if (showedTag == 2) {
		$("#moreDevice").empty();
		$("#selectedDevice option").each(
				function() {
					var tempId = $(this).val();
					var text = $(this).text();
					$("#moreDevice").append(
							"<option value='" + tempId + "'>" + text
									+ "</option>");
				});
	}
}
