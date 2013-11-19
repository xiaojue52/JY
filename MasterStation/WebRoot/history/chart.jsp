<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute(
			"username");
	String password = (String) request.getSession().getAttribute(
			"password");
	Integer userId = (Integer) request.getSession().getAttribute(
			"user_id");
	String userTag = (String) request.getSession().getAttribute("Tag");
	//System.out.print("\n" + password);
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
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<title>Line Chart</title>
		<script src="<%=path%>/js/jscharts.js"></script>
		<link href="css/frame.css" rel="stylesheet" type="text/css">
		<meta name="viewport" content="initial-scale = 1, user-scalable = no">
		<style>
canvas {
	
}
</style>
	</head>
	<body>
		<div>
			<jsp:include page="/frame/top.jsp"></jsp:include>
		</div>

		<div>
			<table>
				<tr>
					<td>
						<div>
							<jsp:include page="/frame/left.jsp"></jsp:include>
						</div>
					</td>
					<td>

						<div id="graph">
							Loading...
						</div>


						<script type="text/javascript">
	var myChart = new JSChart('graph', 'line');
	myChart.setDataArray( [ [ 1, 80 ], [ 2, 40 ], [ 3, 60 ], [ 4, 65 ],
			[ 5, 50 ], [ 6, 50 ], [ 7, 60 ], [ 8,

			80 ], [ 9, 150 ], [ 10, 100 ] ], 'blue');
	myChart.setDataArray( [ [ 1, 100 ], [ 2, 55 ], [ 3, 80 ], [ 4, 115 ],
			[ 5, 80 ], [ 6, 70 ], [ 7, 30 ],

			[ 8, 130 ], [ 9, 160 ], [ 10, 170 ] ], 'green');
	myChart.setDataArray( [ [ 1, 150 ], [ 2, 25 ], [ 3, 100 ], [ 4, 80 ],
			[ 5, 20 ], [ 6, 65 ], [ 7, 0 ],

			[ 8, 155 ], [ 9, 190 ], [ 10, 200 ] ], 'gray');
	myChart.setSize(550, 300);
	myChart.setAxisValuesNumberY(5);
	myChart.setIntervalStartY(0);
	myChart.setIntervalEndY(200);
	myChart.setLabelX( [ 2, 'p1' ]);
	myChart.setLabelX( [ 4, 'p2' ]);
	myChart.setLabelX( [ 6, 'p3' ]);
	myChart.setLabelX( [ 8, 'p4' ]);
	myChart.setLabelX( [ 10, 'p5' ]);
	myChart.setAxisValuesNumberX(5);
	myChart.setShowXValues(false);
	myChart.setTitleColor('#454545');
	myChart.setAxisValuesColor('#454545');
	myChart.setLineColor('#A4D314', 'green');
	myChart.setLineColor('#BBBBBB', 'gray');
	myChart.setTooltip( [ 1, ' ' ]);
	myChart.setTooltip( [ 2, ' ' ]);
	myChart.setTooltip( [ 3, ' ' ]);
	myChart.setTooltip( [ 4, ' ' ]);
	myChart.setTooltip( [ 5, ' ' ]);
	myChart.setTooltip( [ 6, ' ' ]);
	myChart.setTooltip( [ 7, ' ' ]);
	myChart.setTooltip( [ 8, ' ' ]);
	myChart.setTooltip( [ 9, ' ' ]);
	myChart.setTooltip( [ 10, ' ' ]);
	myChart.setFlagColor('#9D16FC');
	myChart.setFlagRadius(4);
	myChart.setAxisPaddingRight(100);
	myChart.setLegendShow(true);
	myChart.setLegendPosition(490, 80);
	myChart.setLegendForLine('blue', 'Click me');
	myChart.setLegendForLine('green', 'Click me');
	myChart.setLegendForLine('gray', 'Click me');
	myChart.draw();
</script>
					</td>
				</tr>
			</table>
		</div>
		<div>
        <img src="JFreeChartAction.action"/>
        </div>
	</body>
</html>
