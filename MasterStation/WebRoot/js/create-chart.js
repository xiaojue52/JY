
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
			//alert(obj.A.startDate+":::"+Date.UTC(2013,11,26));
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
								pointStart: Date.UTC(dateList[0], (dateList[1]-1), dateList[2])
					        }
					    },
						series : [ {
							name : "A相",	
							pointStart:obj.A.startDate,
							//pointStart:Date.UTC(2013,11,26),
							data : obj.A.data
						}, {
							name : 'B相',
							pointStart:obj.B.startDate,
							data : obj.B.data
						}, {
							name : 'C相',
							pointStart:obj.C.startDate,
							data : obj.C.data
						}, {
							name : '环境',
							pointStart:obj.D.startDate,
							data : obj.D.data
						} ],
						exporting : {
							enabled : false
						}
					});
		},
		error : function() {

		}
	});
};

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
								pointStart: Date.UTC(dateList[0], (dateList[1]-1), dateList[2])
					        }
					    },
						series : [
								{
									name : 'A相Max',
									pointStart:obj.AMax.startDate,
									data : obj.AMax.data
								},
								{
									name : 'A相Min',
									pointStart:obj.AMin.startDate,
									data : obj.AMin.data
								},
								{
									name : 'B相Max',
									pointStart:obj.BMax.startDate,
									data : obj.BMax.data
								},
								{
									name : 'B相Min',
									pointStart:obj.BMin.startDate,
									data : obj.BMin.data
								} ,
								{
									name : 'C相Max',
									pointStart:obj.CMax.startDate,
									data : obj.CMax.data
								},
								{
									name : 'C相Min',
									pointStart:obj.CMin.startDate,
									data : obj.CMin.data
								},
								{
									name : '环境Max',
									pointStart:obj.DMax.startDate,
									data : obj.DMax.data
								},
								{
									name : '环境Min',
									pointStart:obj.DMin.startDate,
									data : obj.DMin.data
								}],
						exporting : {
							enabled : false
						}
					});
		},
		error : function() {

		}
	});
};

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
			//var date = eval("(["+obj.data+"])");
			$('#container').highcharts(
					{
						title : {
							text : '温度曲线',
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
								pointStart: Date.UTC(dateList[0], (dateList[1]-1), dateList[2])
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
};

var Chart = {
	dayChart : createDayChart,
	monthChart : createMonthChart,
	moreChart : createMoreChart
};
//Chart.dayChart();
Chart.showedTag = 0;
//var showedTag = 0;
Chart.dayBtnClick = function() {
	$('#dayMenu').show();
	$('#monthMenu').hide();
	$('#moreMenu').hide();
	$('#selectedDevice').html('');
	Chart.showedTag = 0;
};
Chart.monthBtnClick = function() {
	$('#dayMenu').hide();
	$('#monthMenu').show();
	$('#moreMenu').hide();
	$('#selectedDevice').html('');
	Chart.showedTag = 1;
};
Chart.moreBtnClick = function() {
	$('#dayMenu').hide();
	$('#monthMenu').hide();
	$('#moreMenu').show();
	$('#selectedDevice').html('');
	Chart.showedTag = 2;
};

Chart.showPage = function() {
	$("#start_page").hide();
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	//var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	document.documentElement.scrollTop = 0;
};
Chart.closePage = function() {
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
	$('.page_iframe').attr('src', "");
};

Chart.setPageFrameSrc = function(src) {
	$('.page_iframe').attr('src', src);
	Chart.showPage();
};
Chart.selectedDevice = function(tag) {
	if ($(('#selectedDevice')).find('option').length == 0)
		return;
	// alert(tag);
	Chart.closePage();
	if (Chart.showedTag == 0) {
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
	if (Chart.showedTag == 1) {
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
	if (Chart.showedTag == 2) {
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
};
