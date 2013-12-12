<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute(
			"username");
	if (username == null)
		response.sendRedirect(basePath+"index.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<title>温度对比</title>
		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		</head>
		<link rel="stylesheet" type="text/css" href="css/toolbar.css" />
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/datepicker.css" />
		<script src="js/datepicker.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/chart.css" />
	<body>	
	<div class="toolbar">
		<span>
			<input type="button" value="日温度对比" onclick="Chart.dayBtnClick()"/>
		</span>
		<span>	
			<input type="button" value="月温度对比" onclick="Chart.monthBtnClick()"/>
		</span>
		<span>
			<input type="button" value="监测单元温度对比" onclick="Chart.moreBtnClick()"/>
		</span>	
		<br/><br/>
		<div id="dayMenu">
		<span><input type="button" value="变送器" onclick="Chart.setPageFrameSrc('listDeviceComAction.action')"/><select id="dayDevice" style="width:200px;height:20px;"/></select></span>
		<span>选择日期：</span><span><input id="dayLineDate" type="text" readonly='readonly' onfocus="DatePicker.setday(this)"/></span>
		<span><input type="button" value="生成曲线" onclick="Chart.dayChart()"/></span>
		</div>
		<div id="monthMenu" style="display:none">
		<span><input type="button" value="变送器" onclick="Chart.setPageFrameSrc('listDeviceComAction.action')"/><select id="monthDevice" style="width:200px;height:20px;"/></select></span>
		<span>选择月份：</span><span><input id="monthLineDate" type="text" readonly='readonly' onfocus="DatePicker.setmonth(this)"/></span>
		<span><input type="button" value="生成曲线" onclick="Chart.monthChart()"/></span>
		</div>
		<div id="moreMenu" style="display:none">
		<span><input type="button" value="监测单元" onclick="Chart.setPageFrameSrc('listDetectorComAction.action')"/><select id="moreDevice" style="width:200px;height:20px;"/></select></span>
		<span>开始日期：</span><span><input id="moreLineStartDate" type="text" readonly='readonly' onfocus="DatePicker.setday(this)"/></span>
		<span>结束日期：</span><span><input id="moreLineEndDate" type="text" readonly='readonly' onfocus="DatePicker.setday(this)"/></span>
		<span><input type="button" value="生成曲线" onclick="Chart.moreChart()"/></span>
		</div>
		<br/>
	</div>
	<br/>
	<script src="js/Highcharts-3.0.7/js/highcharts.js"></script>
	<script src="js/Highcharts-3.0.7/js/modules/exporting.js"></script>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<script src="js/create-chart.js"></script>
	<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none">
				<h2>操作<a id="btnClose" onclick="Chart.closePage();">关闭</a></h2>
				<div style="height:12px;position:absolute;top:26px;left:13px;"><span>已选择：</span><select id="selectedDevice" style="width:200px;height:16px;"></select></div>
    	   		<iframe src="" class="page_iframe" width=100% height=100% frameborder='0'></iframe>
			</div>
	</body>
</html>

