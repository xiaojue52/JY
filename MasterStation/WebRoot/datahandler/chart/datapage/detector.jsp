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
	<form action="listDetectorComAction.action" method="post">
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
									<span><input class="toolbarButton" type="button"
											value="确定" onclick="parent.Chart.selectedDevice(1);return false;"/> </span>
								</td>
							</tr>
				</table>			
		</div>
		<div class="center_table_div">
		
		<div
			style="min-width: 900px; width: 100%; height: 360px; margin-top: 0px;">
			<div class="datagrid-container datagrid-container-border"
				id="datagrid_89353"
				style="position: relative; overflow: hidden; width: 100%; height: 360px;">
				<div style="background-color:#f5f5f5;overflow: hidden;">
	  			<table id="table_th" class="gridtable">
	  					<tr>
							<th width="5%">
								<span>序号</span>
							</th>
							<th width="15%">
								<span>线路</span>
							</th>
							<th width="15%">
								<span>柜体</span>
							</th>
							<th width="15%">
								<span>变送器</span>
							</th>
							<th width="15%">
								<span>采集器</span>
							</th>
							<th width="15%">
								<span>管理班组</span>
							</th>
							<th width="20%">
								<span>安装时间</span>
							</th>
						</tr>
	  			</table>
	  			</div>
				
				<div style="width: 100%; height: 300px; overflow: auto">
					<table id="table_tr" class="gridtable">

						
						<s:iterator value="pageBean.list" var="detector" status="status">
							<s:if test="#status.count%2==0">
					<tr bgcolor="#F2F2F2" onmouseout="javascript:this.bgColor='#F2F2F2'"
 onmouseover="javascript:this.bgColor='#f5fafe'" >
				</s:if>
				<s:else>
					<tr onmouseout="javascript:this.bgColor='#ffffff'"
 onmouseover="javascript:this.bgColor='#f5fafe'">
				</s:else>
								<td width="5%">
									<input value="<s:property value="#detector.detectorId" />" class="checkbox" id="checkbox<s:property value="#detector.detectorId" />" data="<s:property value="#detector.device.cabinet.line.name"/><s:property value="#detector.device.cabinet.cabNumber" /><s:property value="#detector.device.cabinet.cabType.value" /><s:property value="#detector.device.name" /><s:property value="#detector.name" />" type="checkbox" onClick="checkBoxSwitch('<s:property value="#detector.detectorId" />')"/>
									<s:property value="#status.count+(pageList*(page-1))"/>
								</td>
								<td width="15%">
									<s:property value="#detector.device.cabinet.line.name"/>
								</td>
								<td width="15%">
					
									<s:property value="#detector.device.cabinet.cabNumber" />
									<s:property value="#detector.device.cabinet.cabType.value" />
								</td>
								<td width="15%">
									<s:property value="#detector.device.name" />
								</td>
								<td width="15%">
									<s:property value="#detector.name" />
								</td>
								<td width="15%">
											<s:property value="#detector.device.cabinet.userGroup.groupName" />
								</td>
								<td width="20%">
									<s:date name="#detector.device.createTime" format="yyyy-MM-dd" />
									<s:date name="#detector.device.createTime" format="HH:mm:ss" />
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
			function selectDevice(){
				var value = $('input[name="device"]:checked').val();
				var data = $('input[name="device"]:checked').attr("data");
				$(('#selectedDevice'),parent.document).html("<option value='"+value+"'>"+data+"</option>");
			}	
			$('input[name="device"]').each(function(){
				var deviceId = $(('#selectedDevice'),parent.document).val();
				if(deviceId==$(this).val()){
					$(this).attr("checked","checked");
				}
			});	
			function checkBoxSwitch(id){
				if (document.getElementById("checkbox"+id).checked==true){
					if($(('#selectedDevice'),parent.document).find('option').length>=10)
					{
						alert("最多选取10个");
						document.getElementById("checkbox"+id).checked=false;
						return;
					}
					$(('#selectedDevice'),parent.document).append("<option value='"+id+"' selected='selected'>"+$(document.getElementById("checkbox"+id)).attr('data')+"</option>");
				}
				else
				{
					$(("#selectedDevice option[value='"+id+"']"),parent.document).remove(); 
				}
			}
			$(("#selectedDevice option"),parent.document).each(function(){
				var detectorId = $(this).val();
				$(".checkbox").each(function(){
					//alert($(this).attr('value'));	
					if (detectorId==$(this).attr('value')){
						document.getElementById("checkbox"+detectorId).checked = true;	
					}
				});
			});						
		</script>
		</form>
	</body>
</html>
