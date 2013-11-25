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
		response.sendRedirect(basePath+"index.jsp");
	List<JYUser> userList = new ArrayList<JYUser>();
	List<JYConstant> cabTypeList = new ArrayList<JYConstant>();
	
	if (username!=null)
	{
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

		<title>报警事项</title>
		<link href="css/toolbar.css" rel="stylesheet" type="text/css">
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
			        <div class="toolbar">
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
						<span>柜体编号：<input id='queryNumber' type="text"/></span>
						<span>开始日期： <input id='queryStartDate' type="text"/></span>
						<span>结束日期：<input id='queryEndDate' type="text"/></span>
						<span>维修状态：<select id='queryStatus'>
						<option value="0">未维修</option>
						<option value="1">已维修</option>
						</select></span>
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
		</div>
						<table class="gridtable">
							<tr>
								<th>
										<span>报警日期</span>
									</th>
									<th>
										<span>报警时间</span>
									</th>
									<th>
										<span>报警设备</span>
									</th>
									<th>
										<span>报警内容</span>
									</th>
									<th>
										<span>管理者</span>
									</th>
									<th>
										<span>维修者</span>
									</th>
									
									<th>
										<span>维修备注</span>
									</th>
									<th>
										<span>操作</span>
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
										<a>维修确认</a>
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
												<a href="listException.action?page=1">第一页</a>
												<a
													href="listException.action?page=<s:property value="%{CurrentPage-1}"/>">上一页</a>
											</s:else>

											<s:if test="%{CurrentPage!= TotalPage}">
												<a
													href="listException.action?page=<s:property value="%{CurrentPage+1}"/>">下一页</a>
												<a
													href="listException.action?page=<s:property value="TotalPage"/>">最后一页</a>
											</s:if>
											<s:else>
下一页 最后一页
</s:else>
										</td>
									</tr>
								</s:iterator>
							</table>

		<script src="<%=path%>/js/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>
		<script type="text/javascript">
                $(function(){
                        $('#queryStartDate').datepicker();
                        $('#queryEndDate').datepicker();
                });
        </script>
	</body>
</html>
