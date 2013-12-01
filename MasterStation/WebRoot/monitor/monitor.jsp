<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	//System.out.print("\n"+path+"\n"+basePath);
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
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
<html>
	<head>
		<base href="<%=basePath%>">

		<title>系统监控</title>
		<link rel="stylesheet" type="text/css" href="css/table/src/css/extgrid.v.1.2.5.all.css" />
		<link href="css/toolbar.css" rel="stylesheet" type="text/css">
		<link href="css/common.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="js/control.js" type="text/javascript"></script>
	</head>

	<body>
			       <div class="toolbar">
			       <s:form>
			       <span>线路：<input id='queryLine' type="text"/></span>
			       <span>柜体编号：<input id='queryNumber' type="text"/></span>
				   <span>柜体类型：<select id="queryType" class="selectbox">
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
						
						<span>管理者：<select id="queryUser" class="selectbox">
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
						<span><input class="toolbarButton" type="button" value="实时查询" onclick="queryDevice();"/></span>
		</s:form>
		</div>
		<div class="center_table_div">
		
		<div
		style="min-width:900px;width: 100%; height: 460px; margin-top: 0px;">
		<div class="datagrid-container datagrid-container-border"
			id="datagrid_89353"
			style="position: relative; overflow: hidden; width: 100%; height: 460px;">
	  <div style="background-color:#f5f5f5">
	  <table id="table_th" class="gridtable">
			
			<tr>
				<th width="4%">
				   <span>序号</span>
				</th>
				<th width="10%">
					<span>站房名称</span>
				</th>
				<th width="40%">
					<span>柜内设备数据</span>
				</th>
				<th width="10%">
					<span>采集时间</span>
				</th>
				<th width="16%">
					<span>报警信息</span>
				</th>
				<th width="10%">
					<span>管理者</span>
				</th>
				<th width="10%">
					<span>操作</span>
				</th>
			</tr>
		</table>	
		</div>
	 <div style="width:100%;height:400px;overflow: auto">
	  <table id="table_tr" class="gridtable">		
			<s:iterator value="pageBean.list" var="cabinet" status="status">
				<s:if test="#status.count%2==0">
					<tr bgcolor="#F2F2F2" onmouseout="javascript:this.bgColor='#F2F2F2'"
 onmouseover="javascript:this.bgColor='#f5fafe'" >
				</s:if>
				<s:else>
					<tr onmouseout="javascript:this.bgColor='#ffffff'"
 onmouseover="javascript:this.bgColor='#f5fafe'">
				</s:else>
					<td width="4%">
						<input type="checkbox"/>
						<s:property value="#status.count" /><br/>
					</td>
					<td width="10%">
						<s:property value="#cabinet.line.name" /><br/>
						<s:property value="#cabinet.cabNumber" /><s:property value="#cabinet.cabType.value" />
					</td>
					<td width="40%">
					<table>
						<s:iterator value="#cabinet.deviceList" var="device">
						
							<tr>
							<td>
							<b><s:property value="#device.name" /></b>
							</td>
								<s:iterator value="#device.detectorList" var="detector">
								<td>
									<s:property value="#detector.name" />:<s:property value="#detector.history.value" /><s:property value="#detector.unit" />
								</td>
								</s:iterator>
							</tr>	
						
						</s:iterator>
						</table>
					</td>
					<td width="10%">
						<s:date name="#cabinet.deviceList[0].detectorList[0].history.collectDate" format="yyyy-MM-dd" /><br/>
						<s:date name="#cabinet.deviceList[0].detectorList[0].history.collectTime" format="HH:mm:ss"/>
					</td>
					<td width="16%">
						<s:property value="#cabinet.alarm.alarmText" />
						<s:iterator value="#cabinet.deviceList" var="device">
							<br/>
							<s:property value="#device.alarm.alarmText" />
						</s:iterator>
					</td>
					<td width="10%">
						<s:property value="#cabinet.user.username" />
					</td>
					<td width="10%">
						<a href="listAlarm.action">历史报警</a><br/>
						<a href="listHistory.action?sqlDeviceHistoryColumn.identify=<s:property value="identify" />">历史温度</a>
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
								<select class="pagination-page-list" name="pageList" onchange="window.location='mainAction.action?pageList='+this.options[this.options.selectedIndex].value">
									<s:iterator value="pageNumberList" var="number">
									 
										<s:if test="#number==pageList">
										<option value="${number }" selected="selected">${number }</option>
										</s:if>
										<s:else>
										<option value="${number }">${number }</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
							    <s:if test="CurrentPage>1">
								<a href="mainAction.action?page=1"
									class="pagination-first-btn p-plain">
									<span class="pagination-first  p-btn">&nbsp;</span>
								</a>
								</s:if>	
								<s:else>
								<a href="javascript:void(0)"
									class="pagination-first-btn p-plain p-btn-disabled">
									<span class="pagination-first  p-btn">&nbsp;</span>
								</a>
								</s:else>
							</td>
							<td>
								<s:if test="CurrentPage>1">
								<a href="mainAction.action?page=${CurrentPage-1 }"
									class="pagination-prev-btn p-plain"><span
									class="pagination-prev  p-btn">&nbsp;</span>
								</a>
								</s:if>	
								<s:else>
								<a href="javascript:void(0)"
									class="pagination-prev-btn p-plain p-btn-disabled">
									<span class="pagination-prev  p-btn">&nbsp;</span>
								</a>
								</s:else>
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
								<span style="padding-right: 6px;">页  共${TotalPage}页</span>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<s:if test="%{CurrentPage< TotalPage}">
								<a href="mainAction.action?page=${CurrentPage+1 }"
									class="pagination-next-btn p-plain"><span
									class="pagination-next p-btn">&nbsp;</span>
								</a>
								</s:if>
								<s:else>
								<a href="javascript:viod(0)"
									class="pagination-next-btn p-plain p-btn-disabled"><span
									class="pagination-next p-btn">&nbsp;</span>
								</a>
								</s:else>
							</td>
							<td>
								<s:if test="%{(CurrentPage+1) > TotalPage}">
								<a href="javascript:void(0)"
									class="pagination-last-btn p-plain p-btn-disabled"><span
									class="pagination-last p-btn ">&nbsp;</span>
								</a>
								</s:if>
								<s:else>
								<a href="mainAction.action?page=${TotalPage}"
									class="pagination-last-btn p-plain"><span
									class="pagination-last p-btn ">&nbsp;</span>
								</a>
								</s:else>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="mainAction.action?page=${CurrentPage}" class="pagination-load-btn p-plain"><span
									class="pagination-load p-btn">&nbsp;</span>
								</a>
							</td>
							<td id="pagination-toolbar-datagrid_89353"></td>
						</tr>
					</tbody>
				</table>
				
				<div class="pagination-info">
					当前显示${(CurrentPage-1)*pageList+1 }到${CurrentPage*pageList }条，共${TotalCount}条
				</div>
				</s:iterator>
			</div>
				
		</div>
		<script>
			var table_tr = document.getElementById('table_tr');
			$('#table_th').width(table_tr.scrollWidth);
			$(window).resize(function() {
				var table_tr1 = document.getElementById('table_tr');
				$('#table_th').width(table_tr1.scrollWidth-1);
			});
							
		</script>
	</div></div>
			</body>
</html>
		