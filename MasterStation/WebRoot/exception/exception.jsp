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
		<link rel="stylesheet" type="text/css" href="css/table/src/css/extgrid.v.1.2.5.all.css" />
		<link href="css/toolbar.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<link rel="stylesheet" type="text/css" href="css/common.css" /> 
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
						<span><input class="toolbarButton" type="button" value="系统监控" onclick="window.location='mainAction.action'"/></span>
		</s:form>
		</div><br/>
		
			<div
		style="min-width:900px;width: 100%; height: 420px; margin-top: 0px;">
		<div class="datagrid-container datagrid-container-border"
			id="datagrid_89353"
			style="position: relative; overflow: hidden; width: 100%; height: 430px;">
	  <div style="width:100%;height:400px;overflow: auto">
	  <table class="gridtable">
			
			<tr>
								<th width="10%">
										<span>报警日期</span>
									</th>
									<th width="10%">
										<span>报警时间</span>
									</th>
									<th width="20%">
										<span>报警设备</span>
									</th>
									<th width="20%">
										<span >报警内容</span>
									</th>
									<th width="10%">
										<span>管理者</span>
									</th>
									<th width="10%">
										<span>维修者</span>
									</th>
									
									<th width="10%">
										<span>维修备注</span>
									</th>
									<th width="10%">
										<span>操作</span>
									</th>
								</tr>
								<s:iterator value="pageBean.list" var="device">
									<tr>
										<td width="10%">
											<s:property value="enviTemp" />
										</td>
										<td width="10%">
											<s:property value="deviceBox" />
										</td>
										<td width="10%">
											<s:property value="owner" />
										</td>
										
										<td width="10%">
											<s:property value="status" />
										</td>
										<td width="10%">
											<s:property value="reason" />
										</td>
										<td width="10%">
									<s:date name="#device.createDate" format="yyyy-MM-dd" />
										
									</td>
									<td>
									<s:date name="#device.createTime" format="HH:mm:ss" />
										
									</td>
									<td width="10%">
										<a>维修确认</a>
									</td>
								</tr>
							</s:iterator>
		</table></div>
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
									class="pagination-first  p-btn">&nbsp;</span>
								</a>
							</td>
							<td>
								<a href="javascript:void(0)"
									class="pagination-prev-btn p-plain p-btn-disabled"><span
									class="pagination-prev  p-btn">&nbsp;</span>
								</a>
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
								<span style="padding-right: 6px;">页  共<s:property value="TotalPage" />页</span>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="javascript:void(0)"
									class="pagination-next-btn p-plain p-btn-disabled"><span
									class="pagination-next p-btn">&nbsp;</span>
								</a>
							</td>
							<td>
								<a href="javascript:void(0)"
									class="pagination-last-btn p-plain p-btn-disabled"><span
									class="pagination-last p-btn ">&nbsp;</span>
								</a>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="javascript:void(0)" class="pagination-load-btn p-plain"><span
									class="pagination-load p-btn">&nbsp;</span>
								</a>
							</td>
							<td id="pagination-toolbar-datagrid_89353"></td>
						</tr>
					</tbody>
				</table>
				
				<div class="pagination-info">
					当前显示1到10条，共<s:property value="TotalCount" />条
				</div>
				</s:iterator>
			</div>
		</div>
	</div>

		<script src="<%=path%>/js/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>
		<script type="text/javascript">
                $(function(){
                        $('#queryStartDate').datepicker();
                        $('#queryEndDate').datepicker();
                });
        </script>
	</body>
</html>