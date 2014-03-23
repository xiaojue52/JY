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
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>报警事项</title>
		<link rel="stylesheet" type="text/css"
			href="css/table/src/css/extgrid.v.1.2.5.all.css" />
		<link href="css/toolbar.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<script src="<%=path%>/js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="js/datepicker.js" type="text/javascript"></script>
	</head>

	<body>
		<form action="listPageAlarm.action" method="post"> 
		<div
			style="min-width: 900px; width: 100%; height: 430px; margin-top: 0px;">
			<div class="datagrid-container datagrid-container-border"
				id="datagrid_89353"
				style="position: relative; overflow: hidden; width: 100%; height: 430px;">
				<div style="background-color:#f5f5f5;overflow: hidden;">
	  	<table id="table_th" class="gridtable">
	  	<tr>
							<th width="10%">
								<span>报警日期</span>
							</th>
							<th width="10%">
								<span>报警时间</span>
							</th>
							<th width="10%">
								<span>报警设备</span>
							</th>
							<th width="20%">
								<span>报警内容</span>
							</th>
							<th width="7%">
								<span>报警类型</span>
							</th>
							<th width="11%">
								<span>依据</span>
							</th>
							<th width="5%">
								<span>次数</span>
							</th>
							<th width="7%">
								<span>管理班组</span>
							</th>
							<th width="7%">
								<span>维修状态</span>
							</th>
							<th width="6%">
								<span>维修者</span>
							</th>

							<th width="8%">
								<span>维修备注</span>
							</th>
						</tr>
	  	</table>
	  	</div>
				<div style="width: 100%; height: 370px; overflow: auto">
					<table id="table_tr" class="gridtable">

						
						<s:iterator value="pageBean.list" var="alarm" status="status">
							<s:if test="#status.count%2==0">
					<tr bgcolor="#F2F2F2" onmouseout="javascript:this.bgColor='#F2F2F2'"
 onmouseover="javascript:this.bgColor='#f5fafe'" >
				</s:if>
				<s:else>
					<tr onmouseout="javascript:this.bgColor='#ffffff'"
 onmouseover="javascript:this.bgColor='#f5fafe'">
				</s:else>
								<td width="10%">
									<s:date name="#alarm.date" format="yyyy-MM-dd" />
								</td>
								<td width="10%">
									<s:date name="#alarm.date" format="HH:mm:ss" />
								</td>
								<td width="10%">
									<s:if test="#alarm.isCabinet == 0">
										<s:property value="#alarm.device.cabinet.line.name" /><br/>
										<s:property value="#alarm.device.cabinet.cabNumber" />
										<s:property value="#alarm.device.cabinet.cabType.value" /><br/>
										<s:property value="#alarm.device.name" />
									</s:if>
									<s:else>
										<s:property value="#alarm.device.cabinet.line.name" /><br/>
										<s:property value="#alarm.device.cabinet.cabNumber" />
										<s:property value="#alarm.device.cabinet.cabType.value" />
									</s:else>
								</td>

								<td width="20%">
									<s:property escape="false" value="alarmText" />
								</td>
								<td width="7%">
									<s:if test="#alarm.isCabinet == 0">
										温度异常
									</s:if>
									<s:else>
										设备故障
									</s:else>
								</td>
								<td width="11%">
									<s:property escape="false" value="condition"/>
								</td>
								<td width="5%">
									<s:property value="times"/>
								</td>
								<td width="7%">
										<s:if test="#alarm.device !=null">
											<s:property value="#alarm.device.cabinet.userGroup.groupName" />
										</s:if>
								</td>
								<td width="7%">
									<s:if test="status==0">
										未维修
									</s:if>
									<s:else>
										已维修
									</s:else>
								</td>
								<td width="5%">
									<s:property value="repairUser" />

								</td>
								<td width="8%">
									<s:property value="note" />

								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
				
			
		<%@ include file="/common/pagebean.jsp"%>		
		</div>		
		</div>	
		<input type="hidden" name="cabId" value="${cabId }">
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
