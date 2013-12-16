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
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
	List<JYConstant> cabTypeList = new ArrayList<JYConstant>();
	List<JYUser> userList = new ArrayList<JYUser>();
	String functionNum ="";
	String mesUser = "";
	String mesDate = "";
	long heartBeatTime = 1;
	if (username!=null)
	{
	WebApplicationContext wac = (WebApplicationContext) config
			.getServletContext()
			.getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	DataList dataList = (DataList) wac.getBean("DataList");
	cabTypeList = dataList.getCabTpyeConstant();
	userList = dataList.getAllUser();
	functionNum = dataList.getFunctionNum();
	mesUser = dataList.getMesUser();
	mesDate = dataList.getMesDate();
	heartBeatTime = dataList.getHeartBeatTime();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>参数设置</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<base href="<%=basePath%>">
		<link href="<%=path%>/css/config.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=path%>/js/control.js"></script>
	</head>

	<body style="padding:0;overflow:hidden;">
		<div class="config_page">
			<s:form>
			<div class="config_time">
				<input type="hidden" id="cabinetType"/>
				<span><strong>采集时间周期设置</strong> </span><span>柜体类型：<select onChange="Config.getUpTime();" id="cabType">
							<%
								for (int i = 0; i < cabTypeList.size(); i++) {
							%>
							<option value='<%=cabTypeList.get(i).getSubValue()%>'><%=cabTypeList.get(i).getValue()%></option>
							<%
								}
							%>
						</select> </span><span>间隔时间： </span><span><input class="numberInput" id="upTime" size=4 maxLength=4 type="text" style="ime-mode:disabled;width:40px;"/>分钟 </span><span><input type="button" onclick="Config.upDateMonitorTime();" value="确定" /> </span>
			</div>
			<div class="heartBeat_time">
				<span><strong>离线判定时间设置</strong> </span><span>间隔时间： </span><span><input id="heartBeatTime" class="numberInput" size=4 maxLength=4 type="text" style="ime-mode:disabled;width:40px;" value="<%=heartBeatTime %>"/>分钟 </span><span><input type="button" onclick="Config.setHeartBeat();" value="确定" /> </span>
			</div>
			</s:form>
			<div class="config_calculate">
				<span><strong>温度计算</strong> </span>

				<table class="gridtable">
					<tr>
						<th>
							计算事项
						</th>
						<th>
							函数关系
						</th>
						<th>
							备注
						</th>
					</tr>
					<tr>
						<td>
							肘型头内部温度
						</td>
						<td><table style="width:100%">
						<tr><td>
							<div>
								<input value=0 type="radio" name="functionNum" checked="checked">
								<input type="text" value="F(x)=x" readonly="readonly"/>
								<span>内部温度为采集温度</span>
							</div></td></tr>
							<tr><td><div>
								<input value=1 type="radio" name="functionNum"/>
								<input type="text" value="F(x)=1.2x" readonly="readonly"/>
								<span>内部温度为计算温度</span>
							</div></td></tr>
							</table>
						</td>
						<td>
							x是ABC三相和环境的温度值
						</td>
					</tr>
				</table>
				<span> <br /> <input type="button" value="确定" onclick="Config.upDateMonitorFunction();"/> </span>
			</div>
			<div class="config_type">
				<span><strong>报警条件设置</strong> </span>
				<table class="gridtable">
					<tr>
						<th>
							序号
						</th>
						<th>
							报警类型
						</th>
						<th>
							阀值设置
						</th>
					</tr>
					<tr>
						<td>
							1
							<input id="alarmTypeEnable1" type="checkbox" onclick="Config.setEnable(1)"/>
							<input id="enable1" type="hidden" value="<s:property value="alarmType1.enable"/>" />
						</td>
						<td>
							报警温度超出设定阀值（T1°）
						</td>
						<td>
							T1=
							<input style="ime-mode:disabled;" class="floatNumber" id="value1" type="text" value="<s:property value="alarmType1.value"/>"/>
						</td>
					</tr>
					<tr>
					<td>
							2
							<input id="alarmTypeEnable2" type="checkbox" onclick="Config.setEnable(2)"/>
							<input id="enable2" type="hidden" value="<s:property value="alarmType2.enable"/>"/>
						</td>
						<td>
							三相之间的温度差超过设定值（T2°）
						</td>
						<td>
							T2=
							<input style="ime-mode:disabled;" class="floatNumber" id="value2" type="text" value="<s:property value="alarmType2.value"/>" />
						</td></tr>
					<tr>
					<td>
							3
							<input id="alarmTypeEnable3" type="checkbox" onclick="Config.setEnable(3)"/>
							<input type="hidden" id="enable3" value="<s:property value="alarmType3.enable"/>"/>
						</td>
						<td>
							三相与环境温度差超过设定值（T3°）
						</td>
						<td>
							T3=
							<input style="ime-mode:disabled;" class="floatNumber" id="value3" type="text" value="<s:property value="alarmType3.value"/>" />
						</td></tr>

				</table>
				<span> <br /> <input type="button" value="确定" onclick="Config.updateAlarmType();"> </span>
			</div>
			<div class="config_cost">
				<span><strong>余额提醒设置</strong> </span><span>每月提醒日期：<select id="mesDate">
						
						
					</select> </span><span>短信接收人：<select id="mesUser">
							<%
								for (int i = 0; i < userList.size(); i++) {
							%>
							<option value='<%=userList.get(i).getUsername()%>'>
								<%=userList.get(i).getUsername()%>
							</option>
							<% }%>
						</select> </span>
					<span>
				<input type="button" value="确定" onclick="Config.upDateMessage();"/>
				</span>
			</div>
		</div>
		<input id="tempMesDate" type="hidden" value="<%=mesDate %>"/>
		<input id="tempFunctionNum" type="hidden" value="<%=functionNum %>"/>
		<input id="tempMesUser" type="hidden" value="<%=mesUser %>"/>
		<script type="text/javascript" src="<%=path%>/js/config.js"></script>
	</body>
</html>