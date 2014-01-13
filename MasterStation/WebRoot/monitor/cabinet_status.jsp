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
		<script src="js/control.js" type="text/javascript"></script>
	</head>

	<body>
	  <form action="getCabinetStatus.action" method="post">
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
				<th width="25%">
					<span class="comSpan" onclick="Control.orderByColumn('getCabinetStatus.action','cabinet.line.name')">线路</span>
				</th>
				<th width="25%">
					<span class="comSpan" onclick="Control.orderByColumn('getCabinetStatus.action','cabinet.cabNumber')">柜体设备</span>
				</th>
				<th width="20%">
					<span class="comSpan" onclick="Control.orderByColumn('getCabinetStatus.action','cabinet.detectTime')">检测时间</span>
				</th>
				<th width="16%">
					<span>设备信息</span>
				</th>
				<th width="10%">
					<span class="comSpan" onclick="Control.orderByColumn('getCabinetStatus.action','cabinet.userGroup.groupName')">管理班组</span>
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
						<s:property value="#status.count+(pageList*(page-1))"/>
					</td>
					<td width="25%">
						<s:property value="#cabinet.line.name" /><br/>
					</td>
					<td width="25%">
						<s:property value="#cabinet.cabNumber" /><s:property value="#cabinet.cabType.value" />
					</td>
					<td width="20%">
						<s:date name="#cabinet.detectTime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td width="16%">
						<s:if test="#cabinet.status == -1">
							未启用
						</s:if>
						<s:elseif test="#cabinet.status == 0">
							 停用
						</s:elseif>
						<s:elseif test="#cabinet.deviceList==null||#cabinet.deviceList.size()==0">
							未添加采集设备
						</s:elseif>
						<s:elseif test="#cabinet.detectTime==null">
							离线
						</s:elseif>
						<s:elseif test="#cabinet.alarm!=null">
						<s:property value="#cabinet.alarm.alarmText" />
						</s:elseif>
						<s:elseif test="#cabinet.alarm==null">
							在线
						</s:elseif>
					</td>
					<td width="10%">
						<s:property value="#cabinet.userGroup.groupName" />
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
	</form>
</body>
</html>
		