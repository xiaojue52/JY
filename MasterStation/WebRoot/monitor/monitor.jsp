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
		<script src="js/monitor.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/monitor.css" />
		<script src="js/control.js" type="text/javascript"></script>
	</head>

	<body>
	  <form action="mainAction.action" method="post">
			       <div class="toolbar">
			     
			       
							<span>线路：
							<s:if test="queryLine == \"%\"||queryLine==null">
							<input name='queryLine' type="text" /> 
							</s:if>
							<s:else>
							<input name='queryLine' type="text" value="<s:property value="queryLine"/>"/> 
							</s:else>
							</span>
						
							<span>柜体编号：
							<s:if test="queryNumber == \"%\"||queryNumber==null">
							<input name='queryNumber' type="text" /> 
							</s:if>
							<s:else>
							<input name='queryNumber' type="text" value="<s:property value="queryNumber"/>"/> 
							</s:else>
							</span>
						
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
								
							</select> </span><span><input class="toolbarButton" type="submit" value="查询"/></span>
						<span><input class="toolbarButton" type="button" value="实时查询" onclick="Monitor.QueryDeviceTemp.getCabinetNumber();"/></span>
						<input type="hidden" name="orderColumn" value="cabinet.cabId"/>		
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
				<th width="8%">
					<span class="comSpan" onclick="Control.orderByColumn('mainAction.action','cabinet.line.name')">线路</span>
				</th>
				<th width="8%">
					<span class="comSpan" onclick="Control.orderByColumn('mainAction.action','cabinet.cabNumber')">柜体设备</span>
				</th>
				<th width="36%">
					<span>间隔采集器数据</span>
				</th>
				<th width="10%">
					<span>采集时间</span>
				</th>
				<th width="20%">
					<span>报警信息</span>
				</th>
				<th width="7%">
					<span class="comSpan" onclick="Control.orderByColumn('mainAction.action','cabinet.userGroup.groupName')">管理班组</span>
				</th>
				<th width="7%">
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
						<input class="monitor_checkbox" type="checkbox" value="${cabinet.cabId}" data="${cabinet.cabNumber}"/>
						<s:property value="#status.count+(pageList*(page-1))"/>
					</td>
					<td width="8%">
						<s:property value="#cabinet.line.name" /><br/>
						
					</td>
					<s:if test="#cabinet.alarm!=null||#cabinet.deviceList==null||#cabinet.deviceList.size()==0||#cabinet.detectTime==null">
					<td width="8%" style="color:#ff0000">
					</s:if>
					<s:else>
					<td width="8%">
					</s:else>
						<s:property value="#cabinet.cabNumber" /><s:property value="#cabinet.cabType.value" />
					</td>
					<td width="36%">
					<table width="100%">
						<s:if test="#cabinet.deviceList!=null&&#cabinet.deviceList.size()>0">
						<s:iterator value="#cabinet.deviceList" var="device">
						
							<tr id="${device.deviceId }" onMouseOver="$('#${device.deviceId } .tempValue').show();" onMouseOut="$('#${device.deviceId } .tempValue').hide();">
							<td width="60px">
							<b><s:property value="#device.name" /></b>(${device.detectorList[0].unit})
							</td>
								<s:iterator value="#device.detectorList" var="detector">
								<td>
									<span>${detector.name}:${detector.history.value}</span>
									<span class="tempValue" style="display:none">F(x):${a*detector.history.value}</span>
								</td>
								</s:iterator>
							</tr>	
						
						</s:iterator>
						</s:if>
						</table>
					</td>
					<s:if test="#cabinet.alarm!=null&&#cabinet.alarm.type==\"1\"">
					<td width="10%" style="color:#ff0000">
					</s:if>
					<s:else>
					<td width="10%">
					</s:else>
						<s:if test="#cabinet.deviceList!=null&&#cabinet.deviceList.size()>0&&#cabinet.deviceList[0].detectorList[0].history!=null">
						<s:date name="#cabinet.deviceList[0].detectorList[0].history.date" format="yyyy-MM-dd" /><br/>
						<s:date name="#cabinet.deviceList[0].detectorList[0].history.date" format="HH:mm:ss"/>
						</s:if>
					</td>
					<td width="20%">
						<s:if test="#cabinet.deviceList!=null&&#cabinet.deviceList.size()>0">
						<s:iterator value="#cabinet.deviceList" var="device">
							<s:if test="#device.alarm!=null">
								<s:property value="#device.name"/>:
								<s:property escape="false" value="#device.alarm.alarmText" />
							</s:if>
							<br/>
						</s:iterator>
						</s:if>
					</td>
					<td width="7%">
						<s:property value="#cabinet.userGroup.groupName" />
					</td>
					<td width="7%">
						<a href="javascript:void(0)" onClick="Monitor.setPageFrameSrc('listPageAlarm.action?cabId=<s:property value="#cabinet.cabId"/>');">历史报警</a><br/>
						<a href="javascript:void(0)" onClick="Monitor.setPageFrameSrc('listPageHistory.action?cabId=<s:property value="#cabinet.cabId"/>');">历史温度</a>
					</td>
				</tr>
			</s:iterator>
		</table></div>
			<%@ include file="/common/pagebean.jsp"%>
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
	
	<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none">
				<h2>操作<a id="btnClose" onclick="Monitor.closePage();">关闭</a></h2>
    	   		<iframe src="" class="page_iframe" width=100% height=100% frameborder='0'></iframe>
			</div>
	</form>
			</body>
</html>
		