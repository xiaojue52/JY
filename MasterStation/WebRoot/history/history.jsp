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
		<link rel="stylesheet" type="text/css"
			href="css/table/src/css/extgrid.v.1.2.5.all.css" />
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
		<script src="js/datepicker.js" type="text/javascript"></script>
	</head> 

	<body>
		<div class="toolbar">
			<s:form>
				<table>
					<tr>
						<td>
							<span>线路：<input id='queryLine' type="text" /> </span>
						</td>
						<td>
							<span>柜体编号：<input id='queryNumber' type="text" /> </span>
						</td>
						<td>
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
										</select> </span>
							
						</td>
						</tr>
							<tr>
								<td>
									<span>开始日期： <input id='queryStartDate' type="text" onfocus="setday(this)"/> </span>
								</td>
								<td>
									<span>结束日期：<input id='queryEndDate' type="text" onfocus="setday(this)"/> </span>
								</td>
								<td>
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
								</select> </span>
								</td>
								<td>
									<span><input class="toolbarButton" type="button"
											value="查询" onclick="queryDevice();" /> </span>
								</td>
								<td>
									<span><input class="toolbarButton" type="button"
											value="系统监控" onclick="window.location='mainAction.action'" />
									</span>
								</td>
							</tr>
				</table>
			</s:form>
		</div>
		<br />

		<div
			style="min-width: 900px; width: 100%; height: 420px; margin-top: 0px;">
			<div class="datagrid-container datagrid-container-border"
				id="datagrid_89353"
				style="position: relative; overflow: hidden; width: 100%; height: 430px;">
				<div style="width: 100%; height: 400px; overflow: auto">
					<table class="gridtable">

						<tr>
							<th width="15%">
								<span>采集日期</span>
							</th>
							<th width="15%">
								<span>采集时间</span>
							</th>
							<th width="20%">
								<span>站房名称</span>
							</th>
							<th width="20%">
								<span>变送器名称</span>
							</th>
							<th width="10%">
								<span>采集器</span>
							</th>
							<th width="10%">
								<span>数据</span>
							</th>
							<th width="10%">
								<span>管理者</span>
							</th>
						</tr>
						<s:iterator value="pageBean.list" var="history" status="status">
							<s:if test="#status.count%2==0">
								<tr bgcolor="#F2F2F2">
							</s:if>
							<s:else>
								<tr>
							</s:else>
								<td width="15%">
									<s:date name="#history.collectDate" format="yyyy-MM-dd" />
								</td>
								<td width="15%">
									<s:date name="#history.CollectTime" format="HH:mm:ss" />
								</td>
								<td width="20%">
									<s:property value="detector.device.cabinet.line.name" />
									<br />
									<s:property value="detector.device.cabinet.cabNumber" />
									<s:property value="detector.device.cabinet.cabType.value" />
								</td>
								<td width="20%">
									<s:property value="detector.device.name" />
								</td>
								<td width="10%">
									<s:property value="detector.name" />
								</td>
								<td width="10%">
									<s:property value="value" />
									<s:property value="detector.unit" />
								</td>
								<td width="10%">
									<s:property value="detector.device.cabinet.user.username" />
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="datagrid-pager pagination" id="datagrid_89353_pager">
					<s:iterator value="pageBean">
						<table border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td>
										<select class="pagination-page-list">
											<option value="10">
												10
											</option>
											<option value="20">
												20
											</option>
											<option value="30">
												30
											</option>
											<option value="40">
												40
											</option>
											<option value="50">
												50
											</option>
										</select>
									</td>
									<td>
										<div class="pagination-btn-separator"></div>
									</td>
									<td>
										<a href="javascript:void(0)"
											class="pagination-first-btn p-plain p-btn-disabled"><span
											class="pagination-first  p-btn">&nbsp;</span> </a>
									</td>
									<td>
										<a href="javascript:void(0)"
											class="pagination-prev-btn p-plain p-btn-disabled"><span
											class="pagination-prev  p-btn">&nbsp;</span> </a>
									</td>
									<td>
										<div class="pagination-btn-separator"></div>
									</td>
									<td>
										<span style="padding-left: 6px;">第</span>
									</td>
									<td>
										<s:property value="CurrentPage" />
									</td>
									<td>
										<span style="padding-right: 6px;">页 共<s:property
												value="TotalPage" />页</span>
									</td>
									<td>
										<div class="pagination-btn-separator"></div>
									</td>
									<td>
										<a href="javascript:void(0)"
											class="pagination-next-btn p-plain p-btn-disabled"><span
											class="pagination-next p-btn">&nbsp;</span> </a>
									</td>
									<td>
										<a href="javascript:void(0)"
											class="pagination-last-btn p-plain p-btn-disabled"><span
											class="pagination-last p-btn ">&nbsp;</span> </a>
									</td>
									<td>
										<div class="pagination-btn-separator"></div>
									</td>
									<td>
										<a href="javascript:void(0)"
											class="pagination-load-btn p-plain"><span
											class="pagination-load p-btn">&nbsp;</span> </a>
									</td>
									<td id="pagination-toolbar-datagrid_89353"></td>
								</tr>
							</tbody>
						</table>

						<div class="pagination-info">
							当前显示1到10条，共
							<s:property value="TotalCount" />
							条
						</div>
					</s:iterator>
				</div>
			</div>
		</div>

	</body>
</html>
