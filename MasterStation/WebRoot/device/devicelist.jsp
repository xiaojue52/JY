<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute(
			"username");
	//System.out.print("\n" + path + "\n" + basePath);
	if (username == null)
		response.sendRedirect(basePath+"index.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>系统管理</title>
		<link href="css/frame.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="<%=path%>/js/control.js" type="text/javascript"></script>
	</head>

	<body>
		<div>
			<jsp:include page="/frame/top.jsp"></jsp:include>
		</div>

		<div>
			<table class="main_frame">
				<tr>
					<td class="left_frame">
						<div>
							<jsp:include page="/frame/left.jsp"></jsp:include>
						</div>
					</td>
					<td class="center_frame">
	<div  style="overflow:scroll;height:400px">
					<div class="center_title_frame">
					<span class="center_title">
					<font size="5" face="楷体_GB2312"><strong>设备信息</strong></font>
					</span>
					<span class="center_button">

						</span>
						</div>
						
						<table class="gridtable">
							<tr>
								<th>
										设备名称
									</th>
									<th>
										当前温度（°）
									</th>
									<th>
										警戒温度（°）
									</th>
									<th>
										环境温度（°）
									</th>
									<th>
										所属柜子
									</th>
									<th>
										所属间隔
									</th>
									<th>
										管理者
									</th>
									
									<th>
										状态
									</th>
									<th>
										故障原因
									</th>
									<th>
										温度读取日期
									</th>
									<th>
										温度读取时间
									</th>
									<th>
										备注
									</th>
									
							</tr>
							<c:forEach items="${list}" var="x">
								<tr>
									<td>
										${x.name}
									</td>
									<td>
										${x.currentData }
									</td>
									
									<td>
										${x.warningTemperature }
									</td>
									<td>
										${x.enviTemp }
									</td>

									<td>
										${x.deviceBox}
									</td>
									<td>
										${x.subBox}
									</td>
									<td>
										${x.owner}
									</td>
									
									<td>
										${x.status }
									</td>
									<td>
										${x.reason }
									</td>
									<td>
										${x.createDate }
									</td>
									<td>
										${x.createTime }
									</td>
									<td>
										${x.note }
									</td>
								</tr>
							</c:forEach>
							
						</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
