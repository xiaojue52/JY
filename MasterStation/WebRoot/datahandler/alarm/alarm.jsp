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
		<link href="css/alert-page.css" rel="stylesheet" type="text/css">
		<script src="js/alarm-page.js" type="text/javascript"></script>
		<script src="js/control.js" type="text/javascript"></script>
	</head>

	<body>
	<form action="listAlarm.action" method="post">
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
							<span>报警类型：
							<select name='queryAlarmType'>
										<option value="">全部</option>	
										<s:if test="queryAlarmType==\"1\"">
										<option value="1" selected="selected">设备故障</option>
										<option value="0">温度异常</option>
										</s:if>
										<s:elseif test="queryAlarmType==\"0\"">
										<option value="1">设备故障</option>
										<option value="0" selected="selected">温度异常</option>
										</s:elseif>
										<s:else>
										<option value="1">设备故障</option>
										<option value="0">温度异常</option>
										</s:else>
										
									</select>
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
									<span>维修状态：
									<select name='queryRepairStatus'>
										<option value="">全部</option>	
										<s:if test="queryRepairStatus==\"1\"">
										<option value="1" selected="selected">已维修</option>
										<option value="0">未维修</option>
										</s:if>
										<s:elseif test="queryRepairStatus==\"0\"">
										<option value="1">已维修</option>
										<option value="0" selected="selected">未维修</option>
										</s:elseif>
										<s:else>
										<option value="1">已维修</option>
										<option value="0">未维修</option>
										</s:else>
										
									</select>
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
								<s:if test="#session.userLevel!=\"user\"">
								<td>
									<span><input class="toolbarButton" type="button"
											value="批量确认" onclick="Alarm.updateMultiple.updateMultipleAlarm();"/> </span>
								</td>
								</s:if>
								<td>
									<span><input class="toolbarButton" type="button"
											value="导出excel" onclick="javascrtpt:window.location.href='files/alarm.xls'"/> </span>
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
	  						<th width="5%">
	  							<span><input type="checkbox" id="select_all_alarm" onclick="Alarm.selectAll();"/>序号</span>
	  						</th>
							<th width="8%">
								<span class="comSpan" onclick="Control.orderByColumn('listAlarm.action','alarm.date')">报警日期</span>
							</th>
							<th width="8%">
								<span class="comSpan" onclick="Control.orderByColumn('listAlarm.action','alarm.date')">报警时间</span>
							</th>
							<th width="10%">
								<span class="comSpan" onclick="Control.orderByColumn('listAlarm.action','alarm.device.cabinet.cabNumber')">报警设备</span>
							</th>
							<th width="15%">
								<span>报警内容</span>
							</th>
							<th width="7%">
								<span>报警类型</span>
							</th>
							<th width="7%">
								<span>依据</span>
							</th>
							<th width="5%">
								<span>次数</span>
							</th>
							<th width="7%">
								<span class="comSpan" onclick="Control.orderByColumn('listAlarm.action','alarm.device.cabinet.userGroup.groupName')">管理班组</span>
							</th>
							<th width="7%">
								<span class="comSpan" onclick="Control.orderByColumn('listAlarm.action','alarm.status')">状态</span>
							</th>
							<th width="5%">
								<span class="comSpan" onclick="Control.orderByColumn('listAlarm.action','alarm.repairUser')">确认者</span>
							</th>

							<th width="8%">
								<span>维修备注</span>
							</th>
							<s:if test="#session.userLevel!=\"user\"">
							<th width="8%">
								<span>操作</span>
							</th>
							</s:if>
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
								<td width="5%">
								<s:if test="status==0">
										<input class="history_checkbox" type="checkbox" value="${alarm.id}"/>
									</s:if>
						
						<s:property value="#status.count+(pageList*(page-1))"/>
					</td>
								<td width="8%">
									<s:date name="#alarm.date" format="yyyy-MM-dd" />
								</td>
								<td width="8%">
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

								<td width="15%">
									<s:property escape="false" value="alarmText"/>
								</td>
								<td width="7%">
									<s:if test="#alarm.isCabinet == 0">
										温度异常
									</s:if>
									<s:else>
										设备故障
									</s:else>
								</td>
								<td width="7%">
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
								<s:if test="#session.userLevel!=\"user\"">
								<td width="8%">
								
								
								<s:if test="status==0">
									<a href="javascript:void(0);" onclick="Alarm.showPage('<s:property value="#alarm.id" />','<s:property value="#alarm.alarmText" />')">维修确认</a>
								</s:if>
								<s:else>
									<a>维修确认</a>
								</s:else>
								</td>
								</s:if>
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
		<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none;width:300px;height:310px;">
				<h2>操作<a onClick="Alarm.closePage()">关闭</a></h2>
				<div>
    	    		<s:form id="form">
    	    		<div><span style="margin-left:8px;">报警信息：</span><textarea id="textarea" style="margin-left:30px;margin-top:8px;" readonly="readonly" cols="30" rows="5"></textarea></div>
    	    		
    	    		<div style="margin-top:8px;"><span style="margin-left:8px;">维修备注：</span><textarea onkeyup="this.value = this.value.substring(0, 200)" name="alarmTemp.note" style="margin-left:30px;margin-top:8px;" cols="30" rows="5"></textarea></div>
    	    		
    	    		<div style="margin-top:12px;width:100%">
    	    		<table style="width:100%">
    	    		<tr>
    	    		<td align="center">
    	    		<input type="submit" value="确定" />
    	    		</td>
    	    		<td align="center">
    	    		<input type="button" value="取消" onclick="Alarm.closePage()"/>
    	    		</td>
    	    		</tr>
    	    		</table>
    	    		</div>
    	    		</s:form>
    	    		
    	    	</div>
			</div>

	</body>
</html>
