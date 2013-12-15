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
			       <div class="toolbar">
			       <s:form action="mainAction.action">
			       
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
						
						<span>管理者：<select name="queryUser">
						 	<option value="">全部</option>	
							<s:iterator value="userList" var="user" status="status">
							 <s:if test="queryUser==#user.username">
							 	<option value="<s:property value="#user.username"/>" selected="selected"><s:property value="#user.username"/></option>
							 </s:if>
							 <s:else>
								<option value="<s:property value="#user.username"/>"><s:property value="#user.username"/></option>
							</s:else>
							</s:iterator>				
								
							</select> </span><span><input class="toolbarButton" type="submit" value="查询"/></span>
						<span><input class="toolbarButton" type="button" value="实时查询" onclick="Monitor.QueryDeviceTemp.getCabinetNumber();"/></span>
						<input type="hidden" name="orderColumn" value="cabinet.cabId"/>
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
				<th width="22%">
					<span>报警信息</span>
				</th>
				<th width="5%">
					<span class="comSpan" onclick="Control.orderByColumn('mainAction.action','cabinet.user.username')">管理者</span>
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
						<input class="monitor_checkbox" type="checkbox" value="${cabinet.cabNumber}"/>
						<s:property value="#status.count+(pageList*(page-1))"/>
					</td>
					<td width="8%">
						<s:property value="#cabinet.line.name" /><br/>
						
					</td>
					<td width="8%">
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
					<s:if test="#cabinet.alarm!=null">
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
					<td width="22%">
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
					<td width="5%">
						<s:property value="#cabinet.user.username" />
					</td>
					<td width="7%">
						<a href="javascript:void(0)" onClick="Monitor.setPageFrameSrc('listPageAlarm.action?cabId=<s:property value="#cabinet.cabId"/>');">历史报警</a><br/>
						<a href="javascript:void(0)" onClick="Monitor.setPageFrameSrc('listPageHistory.action?cabId=<s:property value="#cabinet.cabId"/>');">历史温度</a>
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
	
	<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none">
				<h2>操作<a id="btnClose" onclick="Monitor.closePage();">关闭</a></h2>
    	   		<iframe src="" class="page_iframe" width=100% height=100% frameborder='0'></iframe>
			</div>
	
			</body>
</html>
		