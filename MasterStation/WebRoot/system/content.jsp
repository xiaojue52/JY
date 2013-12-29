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


<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/content.css">

<div class="container">
	<div class="page" id="devicePage" style="background-color: #FFFFFF;display: none;">
		<div style="margin-top:0px;margin-left:0px;width:100%;height: 478px;" align="center">
			<div class="titleDiv" id="deviceTitle">变送器</div>
			<div><span class="errorMessage" id="device-page"> </span></div>
			<table class="tableStyle" style="width: 98%;margin-bottom: 5px;">
				<tr>
					<td class="td_left" width="100">变送器编号：</td>
					<td width="270">
						<input maxlength="20" class="checkInput-devicepage" name="device.deviceNumber" id="deviceNumber" type="text"
						value="" style="ime-mode:disabled;height: 22px;width:260px;"/><span style="color:red">*</span>
					</td>
				</tr>
				
				<tr>
					<td class="td_left" width="100">变送器名称：</td>
					<td width="270">
						<input maxlength="20" class="checkInput-devicepage" name="device.name" id="deviceName" type="text"
						value="" style="height: 22px;width:260px;"/><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td class="td_left" width="100">间隔序号：</td>
					<td width="270">
						<input maxlength="2" class="checkInput-devicepage numberInput" name="device.positionNumber" id="devicePositionNumber" type="text"
						value="" style="ime-mode:disabled;height: 22px;width:260px;"/><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td class="td_left" width="100">所属柜体：</td>
					<td width="270">
						<input id="cabinet" type="text" value="" readonly="readonly"
						class="readonly" style="height: 22px;width:260px;"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" width="100">添加时间：</td>
					<td width="270">
						<input id="deviceTime" type="text" value="自动生成" readonly="readonly" class="readonly" style="height: 22px;width:260px;"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" width="100">备注：</td>
					<td width="270">
						<textarea onkeyup="this.value = this.value.substring(0, 200)" class="ecl-devicepage" name="device.note" id="deviceNote" cols="30" rows="5" style="width:260px;"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input id="deviceUpdateBtn" type="button" value="更新" onclick=" if(Control.checkInput('device-page','checkInput-devicepage')){DeviceManager.Device.device(1);};" style="width: 60px;display:none">
						<input id="deviceAddBtn" type="button" value="增加" onclick=" if(Control.checkInput('device-page','checkInput-devicepage')){DeviceManager.Device.device(0);};" style="width: 60px;display:none">
					</td>
				</tr>
				
			</table>
			<input id="deviceCabinetId" name="device.cabinet.cabId" type="hidden" />
			<input id="deviceId" name="device.deviceId" type="hidden" />
		</div>
	</div>

	

	<div class="page" id="detectorPage" style="background-color: #FFFFFF;display: none;">
		<div style="margin-top:0px;margin-left:0px;width:100%;height: 478px;" align="center">
			<div class="titleDiv">采集器</div>
			<table class="tableStyle" style="width: 98%;margin-bottom: 5px;">
				<tr>
					<td class="td_left" width="100">所属变送器编号</td>
					<td width="270">
						<input id="parentDeviceNumber" type="text" value="" readonly="readonly" class="readonly" style="height: 22px;width:260px;"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">管理者</td>
					<td>
						<input id="detectorUser" type="text" value="" readonly="readonly" class="readonly" style="height: 22px;width:260px;"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">所属变送器名称</td>
					<td>
						<input id="parentDeviceName" type="text" value="" readonly="readonly" class="readonly" style="height: 22px;width:260px;"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">所属柜体</td>
					<td>
						<input id="parentCabinet" type="text" value="" readonly="readonly" class="readonly" style="height: 22px;width:260px;"/>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="page" id="linePage" style="background-color: #FFFFFF;display: none;">
		<div style="margin-top:0px;margin-left:0px;width:100%;height: 100%" align="center">
			<div class="titleDiv">添加线路</div>
			<div><span class="errorMessage" id="line-page"> </span></div>
			<div style="margin-top:100px;" >
			线路名称：
			<input maxLength=20 id="lineName" class="checkInput-linepage" name="line.name" type="text" value="请输入线路名称" /><span style="color:red">*</span>
				<input id="lineAddBtn" type="button" value="确定"
					style="margin-left: 10px;width:60px;" onclick=" if(Control.checkInput('line-page','checkInput-linepage')){DeviceManager.Line.line(0);};"/>
			
			<br/>
			<br/>
			<span>添加设备说明：添加线路->在所选线路上右击添加柜体<br/><br/>->在所选柜体上右击添加变送器->完成</span>
			</div>
		</div>
		</div>
	
	
	<div class="page" id="cabinetPage" style="background-color: #FFFFFF;display: none;">
		<div style="margin-top:0px;margin-left:0px;width:100%;height: 478px;" align="center">
			<div class="titleDiv" id="cabinetTitle">柜体信息</div>
			<div><span class="errorMessage" id="cabinet-page"> </span></div>
			<s:form action="addCabinet.action">
			<table class="tableStyle" style="width: 98%;margin-bottom: 5px;">
				<tr>
					<td colspan="2">
					<table class="tableStyle" style="width: 100%;margin-bottom: 5px;margin-top: 5px;">
					<tr>
					<td colspan="4" align="center">基本信息</td>
					</tr>
					<tr>
					<td class="td_left" width="200px">终端编号：</td>
					<td>
						<input id="terminal" value="自动生成，跟终端匹配" type="text" readonly="readonly"
							class="readonly"  style="height: 22px;width:200px;"/>
					</td>
					
					<td class="td_left" width="200px">所属线路：</td>
					<td>
						<input id="line" value="" type="text" readonly="readonly"
							class="readonly"  style="height: 22px;width:200px;"/>
					</td>
					</tr>
					<tr>
					<td class="td_left" width="100">柜体编号：</td>
					<td>
						<input maxlength=5 class="checkInput-cabinetpage" name="cabinet.cabNumber" id="cabNumber" value=""
							type="text" style="ime-mode:disabled;height: 22px;width:200px;"/><span style="color:red">*</span>
					</td>
					<td class="td_left" width="200px">柜体类型：</td>
					<td>
						<select name="cabinet.cabType.id" id="cabType" style="height: 22px;width:200px;">
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
					</td>
					
					
					</tr>
					<tr>
					<td class="td_left" width="100">电压等级：</td>
					<td>
						<select name="cabinet.powerLevel.id" id="powerLevel" style="height: 22px;width:200px;">
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
					</td>
					
					<td class="td_left" width="100">管理者：</td>
					<td>
						<select id="user" name="cabinet.user.userId" style="height: 22px;width:200px;">
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
					</td>
					</tr>
					<tr>
					<td class="td_left" width="100">添加时间：</td>
					<td width="270">
						<input id="cabinetTime" type="text" value="自动生成" readonly="readonly" class="readonly" style="height: 22px;width:200px;"/>
					</td>
					<td class="td_left" width="100">管理班组：</td>
					<td>
						<select id="user" name="cabinet.user.userId" style="height: 22px;width:200px;">
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
					</td>
					</tr>
					<tr>
						<td class="td_left" width="100"><input type="checkbox" id="cabinetStatusCheckBox" onclick="DeviceManager.switchCabinetStatus(this);"/><input name="cabinet.status" id="cabinetStatus" type="hidden" value="-1" /></td>
						<td width="270" colspan="3">
						<input type="text" value="启用" readonly="readonly" class="readonly" style="height: 22px;width:200px;"/>
					</td>
					</tr>
					<tr>
					<td class="td_left" width="100">备注：</td>
					<td width="270" colspan="3">
						<textarea onkeyup="this.value = this.value.substring(0, 200)"  name="cabinet.note" id="cabinetNote" cols="30" rows="5" style="width:200px;"></textarea>
					</td>
				</tr>
				<tr>
				<td colspan="2" align="center"><table class="tableStyle" style="margin-bottom: 5px;margin-top: 5px;">
				<tr>
					<td colspan="2" align="center">GPRS模块信息</td>
					</tr>
					<tr>
					<td class="td_left" width="200px">SIM卡号：</td>
					<td>
						<input class="numberInput" maxlength=11 name="cabinet.simNumber" id="simNumber" type="text"
							value="" style="ime-mode:disabled;height: 22px;width:200px;"/>
					</td>
					</tr>
					<tr>
					<td class="td_left" width="100">SIM卡串号：</td>
					<td>
						<input maxlength=20 name="cabinet.simSNumber" id="simSNumber" type="text"
							value="" style="ime-mode:disabled;height: 22px;width:200px;"/>
					</td>
					</tr>
				
				
				</table ></td>
				<td colspan="2" align="center"><table class="tableStyle" style="margin-bottom: 5px;margin-top: 5px;">
				<tr>
				<td colspan="2" align="center">报警阀值设置</td>
					</tr>
					<tr>
					<td class="td_left" width="200px"><input type="checkbox" id="checkbox1" onclick="DeviceManager.checkBoxSwitch('1')" />
						T1(°)：</td>
					<td>
						<input class="floatNumber" id="input1" name="cabinet.alarmTypeCollect.alarmType1.value" type="text" value="75" class="readonly" readonly="readonly"
							width="30px;" style="ime-mode:disabled;height: 22px;width:200px;"/>
						<input id="enable1" type="hidden" name="cabinet.alarmTypeCollect.alarmType1.enable" value=""/>	
					</td>
					</tr>
					<tr>
					<td class="td_left" width="100"><input type="checkbox" id="checkbox2" onclick="DeviceManager.checkBoxSwitch('2')" />
						T2(°)：</td>
					<td>
						<input class="floatNumber" id="input2" name="cabinet.alarmTypeCollect.alarmType2.value"  type="text" value="15" class="readonly" readonly="readonly"
							width="30px;" style="ime-mode:disabled;height: 22px;width:200px;"/>
							<input id="enable2" type="hidden" name="cabinet.alarmTypeCollect.alarmType2.enable" value=""/>	
					</td>
					</tr>
					<tr>
					<td class="td_left" width="100"><input type="checkbox" id="checkbox3" onclick="DeviceManager.checkBoxSwitch('3')" />
						T3(°)：</td>
					<td>
						<input class="floatNumber" id="input3" name="cabinet.alarmTypeCollect.alarmType3.value" type="text" value="15" class="readonly" readonly="readonly"
							width="30px;" style="ime-mode:disabled;height: 22px;width:200px;"/>
							<input id="enable3" name="cabinet.alarmTypeCollect.alarmType3.enable" type="hidden" value=""/>	
					</td>
					</tr>
				
				</table></td>
				</tr>
				</table>
				</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
					<input id="cabinetUpdateBtn" style="width: 60px;display:none" type="button" value="更新" onclick="DeviceManager.checkValue(); if(Control.checkInput('cabinet-page','checkInput-cabinetpage')&&Control.checkInputFixedLength('cabinet-page','checkInput-cabinetpage',5)){DeviceManager.Cabinet.cabinet(1)};"/>
					<input id="cabinetAddBtn" style="width: 60px;display:none" type="button" value="增加" onclick="DeviceManager.checkValue(); if(Control.checkInput('cabinet-page','checkInput-cabinetpage')&&Control.checkInputFixedLength('cabinet-page','checkInput-cabinetpage',5)){DeviceManager.Cabinet.cabinet(0)};"/>
					</td>
				</tr>	
				</table>
				<input id="lineId" name="cabinet.line.lineId" type="hidden" />
				<input id="cabinetId" name="cabinet.cabId" type="hidden" />

			</s:form>
		</div>
	</div>
</div>
<div class="page" id="firstPage" style="background-color: #FFFFFF;">
	<div style="margin-top:0px;margin-left:0px;width:100%;height: 478px;" align="center">
		<div class="titleDiv">说明</div>
		<div style="margin-top:100px;" >
		<span>点击左边树目录查看设备信息！如第一次使用，右击树根目录添加线路！</span>
		</div>
	</div>
</div>
<div class="page" id="deletePage" style="background-color: #FFFFFF;">
	<div style="margin-top:0px;margin-left:0px;width:100%;height: 478px;" align="center">
		<div class="titleDiv">删除信息</div>
		<div style="margin-top:100px;" >
		<span>设备删除成功！</span>
		</div>
	</div>
</div>
<script>
$(".page").hide();
$("#firstPage").show();
$(".page").width($("#toolbar").width()-195);
$(window).resize(function() {
	$(".page").width($("#toolbar").width()-195);
});
</script>