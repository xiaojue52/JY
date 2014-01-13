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
		<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="js/control.js" type="text/javascript"></script>
		<script src="js/datepicker.js" type="text/javascript"></script>
	</head> 

	<body>		
		<form action="listPageHistory.action" method="post"> 
		<div
			style="min-width: 900px; width: 100%; height: 430px; margin-top: 0px;">
			<div class="datagrid-container datagrid-container-border"
				id="datagrid_89353"
				style="position: relative; overflow: hidden; width: 100%; height: 430px;">
				<div style="background-color:#f5f5f5;overflow: hidden;">
	  			<table id="table_th" class="gridtable">
	  					<tr>
							<th width="15%">
								<span>采集日期</span>
							</th>
							<th width="15%">
								<span>采集时间</span>
							</th>
							<th width="15%">
								<span>站房名称</span>
							</th>
							<th width="45%">
								<span>间隔采集器历史数据</span>
							</th>
							<th width="10%">
								<span>管理班组</span>
							</th>
						</tr>
	  			</table>
	  			</div>
				
				<div style="width: 100%; height: 370px; overflow: auto">
					<table id="table_tr" class="gridtable">

						
						<s:iterator value="pageBean.list" var="history" status="status">
							<s:if test="#status.count%2==0">
					<tr bgcolor="#F2F2F2" onmouseout="javascript:this.bgColor='#F2F2F2'"
 onmouseover="javascript:this.bgColor='#f5fafe'" >
				</s:if>
				<s:else>
					<tr onmouseout="javascript:this.bgColor='#ffffff'"
 onmouseover="javascript:this.bgColor='#f5fafe'">
				</s:else>
								<td width="15%">
									<s:date name="#history.date" format="yyyy-MM-dd" />
								</td>
								<td width="15%">
									<s:date name="#history.date" format="HH:mm:ss" />
								</td>
								<td width="15%">
									<s:property value="#history.cabinet.line.name" />
									<br />
									<s:property value="#history.cabinet.cabNumber" />
									<s:property value="#history.cabinet.cabType.value" />
								</td>
								<td width="45%">
									<table>
									<s:iterator value="#history.map" var="map" status="st">
										<tr>
										<td>
										<s:property value='key.name'/> 
										</td>
										<td>
											<s:iterator value="value">
												<s:property value="detector.name"/>:<s:property value="value"/>
											</s:iterator>
										</td>
										</tr>
									</s:iterator>
									</table>
								</td>
								<td width="10%">
									<s:property value="#history.cabinet.userGroup.groupName" />
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<%@ include file="/common/pagebean.jsp"%>
				<input type="hidden" name="cabId" value="${cabId }">
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
		</form>
	</body>
</html>
