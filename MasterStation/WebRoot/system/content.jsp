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
	String username = (String) request.getSession().getAttribute("username");
	List<JYUser> userList = new ArrayList<JYUser>();
	List<JYConstant> powerLevelList = new ArrayList<JYConstant>();
	List<JYConstant> cabTypeList = new ArrayList<JYConstant>();
	
	if (username!=null)
	{
	WebApplicationContext wac = (WebApplicationContext) config
			.getServletContext()
			.getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	DataList dataList = (DataList) wac.getBean("DataList");
	userList = dataList.getUser();
	powerLevelList = dataList.getPowerLevelConstant();
	cabTypeList = dataList.getCabTpyeConstant();
	}
%>

<link rel="stylesheet" type="text/css" href="css/content.css">
<link rel="stylesheet" type="text/css" href="css/common.css">

<div>
	<div id="devicePage" class="page">
		<div class="devicePage">
			<div class="title">
				<span>变送器</span>
			</div>
			<s:form action="addDevice.action">
				<div>
					变送器编号：

					<input name="device.deviceNumber" id="deviceNumber" type="text"
						value="JY000001" />

					所属柜体：

					<input id="cabinet" type="text" value="" readonly="readonly"
						class="readonly" />
				</div>
				<div>
					变送器名称：

					<input name="device.name" id="deviceName" type="text"
						value="30861间隔" />

					管理者：

					<input id="deviceUser" type="text" value="张三" readonly="readonly" class="readonly" />

				</div>
				<div>
					状态：

					<input name="device.status" id="deviceStatus" type="text" value=""
						readonly="readonly" class="readonly" />

				</div>
				<div>
					备注：

					<textarea name="device.note" id="deviceNote" cols="30" rows="5"></textarea>
				</div>
				<div>
					<input class="comButton deviceButton" type="submit" value="确定" />
				</div>
				<input id="cabinetId" name="device.cabinet.cabId" type="hidden" />

			</s:form>
		</div>
	</div>

	<div id="detectorPage" class="page">
		<div class="detectorPage">
			<div class="title">
				<span>采集器</span>
			</div>
			<div>
				所属变送器编号：

				<input id="parentDeviceNumber" type="text" value="JY000001"
					readonly="readonly" class="readonly" />

				管理者：

				<input id="detectorUser" type="text" value="张三" readonly="readonly" class="readonly" />
			</div>
			<div>

				所属变送器名称：

				<input id="parentDeviceName" type="text" value="30861间隔"
					readonly="readonly" class="readonly" />
					所属柜体：

				<input id="parentCabinet" type="text" value="#3860环网柜"
					readonly="readonly" class="readonly" />
			</div>
		</div>
	</div>

	<div id="linePage" class="page">
		<div class="linePage">
			<div class="title">
				<span>添加线路</span>
			</div>
			<s:form action="addLine.action">
			线路名称：
			<input name="line.name" type="text" value="请输入线路名称" />
				<input class="comButton" type="submit" value="确定"
					style="margin-left: 10px" />
			</s:form>
		</div>
	</div>

	<div id="cabinetPage" class="page">
		<div class="cabinetPage">
			<div class="title">
				<span>柜体信息</span>
			</div>
			<s:form action="addCabinet.action">
				<div class="baseInfo">
					<div class="baseTitle">
						基本信息
					</div>
					<div>
						所属线路：

						<input id="line" value="" type="text" readonly="readonly"
							class="readonly" />

						柜体编号：

						<input name="cabinet.cabNumber" id="cabNumber" value="#3860"
							type="text" />
					</div>
					<div>
						柜体类型：

						<select name="cabinet.cabType.id" id="cabType">
							<%
								for (int i = 0; i < cabTypeList.size(); i++) {
							%>
							<option value='<%=cabTypeList.get(i).getId()%>'>
								<%=cabTypeList.get(i).getValue()%>
							</option>
							<%
								}
							%>
						</select>
						电压等级：

						<select name="cabinet.powerLevel.id" id="powerLevel">
							<% 
								for (int i = 0; i < powerLevelList.size(); i++) {
							%>
							<option value='<%=powerLevelList.get(i).getId()%>'>
								<%=powerLevelList.get(i).getValue()%>
							</option>
							<%
								}
							%>
						</select>

						管理者：

						<select id="user" name="cabinet.user.userId">
							<%
								for (int i = 0; i < userList.size(); i++) {
							%>
							<option value='<%=userList.get(i).getUserId()%>'>
								<%=userList.get(i).getUsername()%>
							</option>
							<%
								}
							%>
						</select>
					</div>
				</div>
				<div class="gprsInfo">
					<div>
						GPRS模块信息
					</div>
					<div>
						SIM卡号：

						<input name="cabinet.simNumber" id="simNumber" type="text"
							value="111111111" />
					</div>
					<div>
						SIM卡串号：

						<input name="cabinet.simSNumber" id="simSNumber" type="text"
							value="111111111" />
					</div>
				</div>
				<div class="alertInfo">
					<div>
						报警阀值设置
					</div>
					<div>
						<input type="checkbox" id="checkbox1" onclick="checkBoxSwitch('1')" />
						T1(°)：

						<input id="input1" name="cabinet.alarmTypeCollect.alarmType1.value" type="text" value="75" class="readonly" readonly="readonly"
							width="30px;" />
						<input id="enable1" type="hidden" name="cabinet.alarmTypeCollect.alarmType1.enable" value=""/>	
					</div>
					<div>
						<input type="checkbox" id="checkbox2" onclick="checkBoxSwitch('2')" />
						T2(°)：

						<input id="input2" name="cabinet.alarmTypeCollect.alarmType2.value"  type="text" value="15" class="readonly" readonly="readonly"
							width="30px;" />
							<input id="enable2" type="hidden" name="cabinet.alarmTypeCollect.alarmType2.enable" value=""/>	
					</div>
					<div>
						<input type="checkbox" id="checkbox3" onclick="checkBoxSwitch('3')" />
						T3(°)：

						<input id="input3" name="cabinet.alarmTypeCollect.alarmType3.value" type="text" value="15" class="readonly" readonly="readonly"
							width="30px;" />
							<input id="enable3" name="cabinet.alarmTypeCollect.alarmType3.enable" type="hidden" value=""/>	
					</div>
				</div>
				<div>
					<input class="comButton cabinetButton" type="submit" value="确定" />
				</div>

				<input id="lineId" name="cabinet.line.lineId" type="hidden" />
				<input id="userId" type="hidden" />

			</s:form>
		</div>
	</div>
</div>