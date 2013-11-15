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

		<title>历史异常数据</title>
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
					<div class="center_title_frame">
					<span class="center_title">
					<font size="5" face="楷体_GB2312"><strong>历史异常数据</strong></font>
					</span>
			       </div>
			       <div><s:form action="listExceptionHistory.action">设备名称：<input type="text" name="sqlDeviceHistoryColumn.name"/>所属网柜：<input type="text" name="sqlDeviceHistoryColumn.deviceBox"/><input type="submit" value="查询"/></s:form></div>
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
												<a href="listExceptionHistory.action?page=1">第一页</a>
												<a
													href="listExceptionHistory.action?page=<s:property value="%{CurrentPage-1}"/>">上一页</a>
											</s:else>

											<s:if test="%{CurrentPage!= TotalPage}">
												<a
													href="listExceptionHistory.action?page=<s:property value="%{CurrentPage+1}"/>">下一页</a>
												<a
													href="listExceptionHistory.action?page=<s:property value="TotalPage"/>">最后一页</a>
											</s:if>
											<s:else>
下一页 最后一页
</s:else>
										</td>
									</tr>
								</s:iterator>
							</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
