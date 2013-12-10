
var createDayChart = function() {
	//alert($('#dayDevice').val());
	var startTime = $('#dayLineDate').val();
	if (startTime.length==0){alert("请选择日期！");return;}
	var endTime = startTime;
	var dateList = startTime.split("-");

	$.ajax( {
		type : "post",
		url : "dayChart.action?queryDeviceId=" + $('#dayDevice').val()+"&queryStartDate="+startTime+"&queryEndDate="+endTime,
		contentType : "json",
		success : function(returnData) {

			var obj = eval("(" + returnData + ")");
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
							type: 'datetime',
							minRange: 3600
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
						plotOptions: {
					        series: {
								pointInterval: 30*60 * 1000,
								pointStart: Date.UTC(dateList[0], (dateList[1]-1), dateList[2]),
					        }
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
	var dateList = startTime.split("-");
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
							type: 'datetime',
							minRange: 3600
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
						plotOptions: {
					        series: {
								pointInterval: 24*60*60 * 1000,
								pointStart: Date.UTC(dateList[0], (dateList[1]-1), dateList[2]),
					        }
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
	var dateList = startTime.split("-");
	$.ajax( {
		type : "post",
		url : "moreChart.action?queryStrings=" + queryStrings+"&queryStartDate="+startTime+"&queryEndDate="+endTime,
		contentType : "json",
		success : function(returnData) {

			var obj = eval("(" + returnData + ")");
			alert(obj.data[0]);
			//var date = eval("(["+obj.data+"])");
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
							type: 'datetime',
							minRange: 3600
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
						plotOptions: {
					        series: {
								pointInterval: 30*60 * 1000,
								pointStart: Date.UTC(dateList[0], (dateList[1]-1), dateList[2]),
					        }
					    },
						series : obj.data,
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
