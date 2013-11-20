<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute(
			"username");
	//System.out.print("\n"+path+"\n"+basePath);
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
	Integer methodCode = (Integer) request.getAttribute("methodCode");
	String detect_sort = null;
	if (methodCode!=null){
		switch (methodCode){
			case 0:
				detect_sort = "实时";
				break;
			case 1:
				detect_sort = "定时";
				break;
		}
	}
%>
<html>
	<head>
		<base href="<%=basePath%>">

		<title>系统监控</title>
		<link href="css/frame.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="<%=path%>/js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="<%=path%>/js/control.js" type="text/javascript"></script>
	</head>

	<body>
<div class="center_title_frame">
					<span class="center_title">
					<font size="5" face="楷体_GB2312"><strong><%=detect_sort %>监控</strong></font>
					</span><span id="sort_discript"></span>
			       </div>
			       <div><s:form action="mainAction.action">设备名称：<input type="text" name="sqlDeviceColumn.name"/>所属网柜：<input type="text" name="sqlDeviceColumn.deviceBox"/><input type="submit" value="查询"/></s:form></div>
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
									<th>
										操作
									</th>
								</tr>
								<s:iterator value="pageBean.list" var="device">
									<tr>
										<td>
											<s:property value="name" />
										</td>
										<td>
											<s:property value="currentData" />
										</td>
										<td>
											<s:property value="warningTemperature" />
										</td>
										<td>
											<s:property value="enviTemp" />
										</td>
										<td>
											<s:property value="deviceBox" />
										</td>
										<td>
											<s:property value="subBox" />
										</td>
										<td>
											<s:property value="owner" />
										</td>
										
										<td>
											<s:property value="status" />
										</td>
										<td>
											<s:property value="reason" />
										</td>
										<td>
									<s:date name="#device.createDate" format="yyyy-MM-dd" />
										
									</td>
									<td>
									<s:date name="#device.createTime" format="HH:mm:ss" />
										
									</td>
										<td>
											<s:property value="note" />
										</td>
										<td>
											<a href="history/chart.jsp">查看趋势图</a>
											<a href="listExceptionHistory.action?sqlDeviceHistoryColumn.identify=<s:property value="identify" />">历史报警</a>
											<a href="listHistory.action?sqlDeviceHistoryColumn.identify=<s:property value="identify" />">历史温度</a>
										</td>
									</tr>
								</s:iterator>
								
							</table>
							<table width=100%>
								<s:iterator value="pageBean">
									<tr>
										<td colspan="6" align="center">
											共
											<s:property value="TotalCount" />
											条记录 共
											<s:property value="TotalPage" />
											页 当前第
											<s:property value="CurrentPage" />
											页
											<br>

											<s:if test="%{CurrentPage == 1}">
第一页 上一页
</s:if>
											<!-- CurrentPage为当前页 -->
											<s:else>
												<a href="mainAction.action?page=1">第一页</a>
												<a
													href="mainAction.action?page=<s:property value="%{CurrentPage-1}"/>">上一页</a>
											</s:else>

											<s:if test="%{CurrentPage!= TotalPage}">
												<a
													href="mainAction.action?page=<s:property value="%{CurrentPage+1}"/>">下一页</a>
												<a
													href="mainAction.action?page=<s:property value="TotalPage"/>">最后一页</a>
											</s:if>
											<s:else>
下一页 最后一页
</s:else>
										</td>
									</tr>
								</s:iterator>
							</table>
			</body>
</html>
		