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

		<title>报警事项</title>
		<link href="css/frame.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="<%=path%>/js/jquery-1.9.1.js" type="text/javascript"></script>
		
	</head>

	<body>
					<div class="center_title_frame">
					<span class="center_title">
					<font size="5" face="楷体_GB2312"><strong>异常列表</strong></font>
					</span>
			       </div>
			       <div><s:form action="unhandledException.action">设备名称：<input type="text" name="sqlUnHandledExceptionColumn.name"/>所属网柜：<input type="text" name="sqlUnHandledExceptionColumn.deviceBox"/>开始日期：<input type="text" name="sqlUnHandledExceptionColumn.startDate" id="startDate" value="">结束日期：<input type="text" name="sqlUnHandledExceptionColumn.endDate" id="endDate" value=""><input type="submit" value="查询"/></s:form></div>
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
										<a href='deleteUnhandledException.action?unhandledException.id=<%=request.getAttribute("id")%>'>确认</a>
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
												<a href="unhandledException.action?page=1">第一页</a>
												<a
													href="unhandledException.action?page=<s:property value="%{CurrentPage-1}"/>">上一页</a>
											</s:else>

											<s:if test="%{CurrentPage!= TotalPage}">
												<a
													href="unhandledException.action?page=<s:property value="%{CurrentPage+1}"/>">下一页</a>
												<a
													href="unhandledException.action?page=<s:property value="TotalPage"/>">最后一页</a>
											</s:if>
											<s:else>
下一页 最后一页
</s:else>
										</td>
									</tr>
								</s:iterator>
							</table>

		<script src="<%=path%>/js/jquery-ui.js" type="text/javascript"></script>
		<script type="text/javascript">
                $(function(){
                        $('#startDate').datepicker();
                        $('#endDate').datepicker();
                });
        </script>
	</body>
</html>
