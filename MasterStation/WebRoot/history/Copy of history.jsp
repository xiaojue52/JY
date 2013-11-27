<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java"
	import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" import="com.station.data.DataList"%>
<%@ page language="java" import="com.station.po.*"%>
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
		response.sendRedirect(basePath + "index.jsp");
	List<JYUser> userList = new ArrayList<JYUser>();
	List<JYConstant> cabTypeList = new ArrayList<JYConstant>();

	if (username != null) {
		WebApplicationContext wac = (WebApplicationContext) config
				.getServletContext()
				.getAttribute(
						WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		DataList dataList = (DataList) wac.getBean("DataList");
		userList = dataList.getUser();
		cabTypeList = dataList.getCabTpyeConstant();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>历史数据</title>
		<link href="css/toolbar.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<link rel="stylesheet" type="text/css" href="css/common.css" /> 
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="<%=path%>/js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="<%=path%>/js/control.js" type="text/javascript"></script>
	</head>

	<body>
			      <div class="toolbar">
			      <s:form>
			      <span>线路：<input id='queryLine' type="text"/></span>
			      <span>柜体编号：<input id='queryNumber' type="text"/></span>
			<span>柜体类型：<select id="queryType">
							<%
								for (int i = 0; i < cabTypeList.size(); i++) {
							%>
							<option value='<%=cabTypeList.get(i).getValue()%>'>
								<%=cabTypeList.get(i).getValue()%>
							</option>
							<%
								}
							%>
						</select></span>
						<br/>
						<span>开始日期： <input id='queryStartDate' type="text"/></span>
						<span>结束日期：<input id='queryEndDate' type="text"/></span>
						<span>管理者：<select id="queryUser">
							<%
								for (int i = 0; i < userList.size(); i++) {
							%>
							<option value='<%=userList.get(i).getUsername()%>'>
								<%=userList.get(i).getUsername()%>
							</option>
							<%
								}
							%>
						</select></span><span><input class="toolbarButton" type="button" value="查询" onclick="queryDevice();"/></span>
						<span><input class="toolbarButton" type="button" value="系统监控" onclick="window.location='mainAction.action'"/></span>
		</s:form>
		</div><br/>
						<table class="gridtable">
							<tr>
								<th>
										<span>采集日期</span>
									</th>
									<th>
										<span>采集时间</span>
									</th>
									<th>
										<span>站房名称</span>
									</th>
									<th>
										<span>设备数据</span>
									</th>
									<th>
										<span>管理者</span>
									</th>
								</tr>
								<s:iterator value="pageBean.list" var="history">
									<tr>
										<td>
											<s:date name="#history.collectDate" format="yyyy-MM-dd" />
										</td>
										<td>
											<s:date name="#history.CollectTime" format="HH:mm:ss" />
										</td>
										<td>
											<s:property value="detector.device.name" />
										</td>
										<td>
											<s:property value="value" />
										</td>
										<td>
											<s:property value="detector.device.cabinet.user.username" />
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
												<a href="listHistory.action?page=1">第一页</a>
												<a
													href="listHistory.action?page=<s:property value="%{CurrentPage-1}"/>">上一页</a>
											</s:else>

											<s:if test="%{CurrentPage!= TotalPage}">
												<a
													href="listHistory.action?page=<s:property value="%{CurrentPage+1}"/>">下一页</a>
												<a
													href="listHistory.action?page=<s:property value="TotalPage"/>">最后一页</a>
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
