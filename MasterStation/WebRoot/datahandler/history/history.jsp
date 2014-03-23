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
	<form action="cabinetHistory.action" method="post">
		<div class="toolbar">
			
				<table>
					<tr>
						<td>
							<span>线路：
							<s:if test="queryLine == \"%\"||queryLine==null">
							<input name='queryLine' type="text" /> 
							</s:if>
							<s:else>
							<input name='queryLine' type="text" value="<s:property value="queryLine"/>"/> 
							</s:else>
							</span>
						</td>
						<td>
							<span>柜体编号：
							<s:if test="queryNumber == \"%\"||queryNumber==null">
							<input name='queryNumber' type="text" /> 
							</s:if>
							<s:else>
							<input name='queryNumber' type="text" value="<s:property value="queryNumber"/>"/> 
							</s:else>
							</span>
						</td>
					
						<td>
						<span>管理班组：<select name="queryUserGroup">
						 	<option value="">全部</option>	
							<s:iterator value="userGroupList" var="userGroup" status="status">
							 <s:if test="queryUserGroup==#userGroup.groupName">
							 	<option value="<s:property value="#userGroup.groupName"/>" selected="selected"><s:property value="#userGroup.groupName"/></option>
							 </s:if>
							 <s:else>
								<option value="<s:property value="#userGroup.groupName"/>"><s:property value="#userGroup.groupName"/></option>
							</s:else>
							</s:iterator>					
								
							</select> </span>
							
						</td>
						</tr>
							<tr>
							
								<td>
									<span>开始日期：
									<s:if test="queryStartDate == \"1000-01-01\"||queryStartDate==null">
									<input name='queryStartDate' type="text" onfocus="DatePicker.setday(this)"/> 
									</s:if>
									<s:else>
									<input name='queryStartDate' type="text" value="<s:property value="queryStartDate"/>" onfocus="DatePicker.setday(this)"/> 
									</s:else>
									</span>
								</td>
								<td>
									<span>结束日期：
									<s:if test="queryEndDate == \"9999-12-12\"||queryEndDate==null">
									<input name='queryEndDate' type="text" onfocus="DatePicker.setday(this)"/> 
									</s:if>
									<s:else>
									<input name='queryEndDate' type="text" value="<s:property value="queryEndDate"/>" onfocus="DatePicker.setday(this)"/> 
									</s:else>
									</span>
								</td>
								<td>
									<span>柜体类型：<select name="queryType">
									<option value="">全部</option>	
									<s:iterator value="cabTypeList" var="cabType" status="status">
							 			<s:if test="queryType==#cabType.value">
							 		<option value="<s:property value="#cabType.value"/>" selected="selected"><s:property value="#cabType.value"/></option>
									 </s:if>
									 <s:else>
										<option value="<s:property value="#cabType.value"/>"><s:property value="#cabType.value"/></option>
									</s:else>
									</s:iterator>
								</select> </span>
								</td>
								<td>
									<span><input class="toolbarButton" type="submit"
											value="查询"/> </span>
								</td>
								<td>
									<span><input type="button" class="toolbarButton"
											value="导出excel" onclick="javascrtpt:window.location.href='files/history.xls'"/></span>
								</td>
							</tr>
				</table>			
		</div>
		<div class="center_table_div">
		
		<div
			style="min-width: 900px; width: 100%; height: 430px; margin-top: 0px;">
			<div class="datagrid-container datagrid-container-border"
				id="datagrid_89353"
				style="position: relative; overflow: hidden; width: 100%; height: 430px;">
				<div style="background-color:#f5f5f5;overflow: hidden;">
	  			<table id="table_th" class="gridtable">
	  					<tr>
							<th width="15%">
								<span class="comSpan" onclick="Control.orderByColumn('cabinetHistory.action','cabinetHistory.date')">采集日期</span>
							</th>
							<th width="15%">
								<span class="comSpan" onclick="Control.orderByColumn('cabinetHistory.action','cabinetHistory.date')">采集时间</span>
							</th>
							<th width="10%">
								<span class="comSpan" onclick="Control.orderByColumn('cabinetHistory.action','cabinetHistory.cabinet.line.name')">线路</span>
							</th>
							<th width="10%">
								<span class="comSpan" onclick="Control.orderByColumn('cabinetHistory.action','cabinetHistory.cabinet.cabNumber')">柜体设备</span>
							</th>
							<th width="40%">
								<span>间隔采集器历史数据</span>
							</th>
							<th width="10%">
								<span class="comSpan" onclick="Control.orderByColumn('cabinetHistory.action','cabinetHistory.cabinet.userGroup.groupName')">管理班组</span>
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
								<td width="10%">
									<s:property value="#history.cabinet.line.name" />
								</td>
								<td width="10%">
									<s:property value="#history.cabinet.cabNumber" />
									<s:property value="#history.cabinet.cabType.value" />
								</td>
								<td width="40%">
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
		</div>
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
