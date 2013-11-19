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
	WebApplicationContext wac = (WebApplicationContext) config
			.getServletContext()
			.getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	DataList dataList = (DataList) wac.getBean("DataList");
	List<JYUser> userList = dataList.getUser();
	//System.out.print(list.size());
%>

<link rel="stylesheet" type="text/css" href="css/content.css">
<link rel="stylesheet" type="text/css" href="css/common.css">

<div>
	<div id="devicePage" class="page">
		<s:form action="addDevice.action">
			<tr>
				<td>
					变送器
				</td>
			</tr>

			<tr>
				<td>
					变送器编号：
				</td>
				<td>
					<input name="device.deviceNumber" id="deviceNumber" type="text"
						value="JY000001" />
				</td>
				<td>
					变送器名称：
				</td>
				<td>
					<input name="device.name" id="deviceName" type="text"
						value="30861间隔" />
				</td>
			</tr>
			<tr>
				<td>
					所属柜体：
				</td>
				<td>
					<input id="cabinet" type="text" value="" readonly="readonly"
						class="readonly" />
				</td>
			</tr>
			<tr>
				<td>
					状态：
				</td>
				<td>
					<input id="deviceStatus" type="text" value="" readonly="readonly"
						class="readonly" />
				</td>
				<td>
					管理者：
				</td>
				<td>
					<input type="text" value="张三" readonly="readonly" class="readonly" />
				</td>
			</tr>
			<tr>
				<td>
					备注：
				</td>
				<td>
					<textarea name="device.note" id="deviceNote" cols="30" rows="5"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="确定" />
				</td>
			</tr>
			<tr>
				<td>
					<input id="cabinetId" name="device.cabinet.cabId" type="hidden" />
				</td>
			</tr>
		</s:form>
	</div>

	<div id="detectorPage" class="page">
		<div class="detectorPage">
			<div class="title">
				<span>采集器</span>
			</div>
			<table id="gridtable">
				<tr>
					<td>
						所属变送器编号：
					</td>
					<td>
						<input id="parentDeviceNumber" type="text" value="JY000001"
							readonly="readonly" class="readonly" />
					</td>
					<td>
						管理者：
					</td>
					<td>
						<input type="text" value="张三" readonly="readonly" class="readonly" />
					</td>
				</tr>
				<tr>
					<td>
						所属柜体：
					</td>
					<td>
						<input id="parentCabinet" type="text" value="#3860环网柜"
							readonly="readonly" class="readonly" />
					</td>
					<td>
						所属间隔：
					</td>
					<td>
						<input id="parentDeviceName" type="text" value="30861间隔"
							readonly="readonly" class="readonly" />
					</td>
				</tr>
			</table>
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
				<div>
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

					<select name="cabinet.cabType">
						<option value="环网柜">
							环网柜
						</option>
						<option value="变电柜">
							变电柜
						</option>
					</select>
					电压等级：

					<select name="cabinet.powerLevel">
						<option value="10kv">
							10kv
						</option>
						<option value="20kv">
							20kv
						</option>
					</select>

					管理者：

					<select id="userSelection" name="cabinet.user.userId">
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

									<input type="checkbox" />
									启用全局
				</div>
				<div>
									T1(°)：

									<input type="text" value="75" />
				</div>
				<div>
									T2(°)：

									<input type="text" value="15" />
				</div>
				<div>
									T3(°)：

									<input type="text" value="15" />
</div>
</div>
				<div><input type="submit" value="确定" /></div>

				<input id="lineId" name="cabinet.line.lineId" type="hidden" />
				<input id="userId" type="hidden" />

			</s:form>
		</div>
	</div>
</div>