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
	List<JYUser> userList = new ArrayList<JYUser>();
	List<JYConstant> cabTypeList = new ArrayList<JYConstant>();
	
	if (username!=null)
	{
	WebApplicationContext wac = (WebApplicationContext) config
			.getServletContext()
			.getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	DataList dataList = (DataList) wac.getBean("DataList");
	userList = dataList.getUser();
	cabTypeList = dataList.getCabTpyeConstant();
	}
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
		<div class="toolbar">
			<s:form>
				<table>
					<tr>
						<td>
							<span>线路：<input id='queryLine' type="text" />
							</span>
						</td>
						<td>
							<span>柜体编号：<input id='queryNumber' type="text" />
							</span>
						</td>
						<td>
							<span>柜体类型：<select id="queryType">
									<%
								for (int i = 0; i < cabTypeList.size(); i++) {
							%>
									<option value='<%=cabTypeList.get(i).getValue()%>'>
										<%=cabTypeList.get(i).getValue()%>
									</option>
									<%
								}
							%>
								</select>
							</span>
						</td>

					</tr>
					<tr>
						<td>
							<span>开始日期： <input id='queryStartDate' type="text" onfocus="setday(this)"/>
							</span>
						</td>
						<td>
							<span>结束日期：<input id='queryEndDate' type="text" onfocus="setday(this)"/>
							</span>
						</td>
						<td>
							<span>维修状态：<select id='queryStatus'>
									<option value="0">
										未维修
									</option>
									<option value="1">
										已维修
									</option>
								</select>
							</span>

							<span>管理者：<select id="queryUser">
									<%
								for (int i = 0; i < userList.size(); i++) {
							%>
									<option value='<%=userList.get(i).getUsername()%>'>
										<%=userList.get(i).getUsername()%>
									</option>
									<%
								}
							%>
								</select>
							</span>
						</td>
						<td>
							<span><input class="toolbarButton" type="button"
									value="查询" onclick="queryDevice();"/>
							</span>
						</td>
						<td>
							<span><input class="toolbarButton" type="button"
									value="系统监控" onclick="window.location = 'mainAction.action';"/>
							</span>
						</td>
					</tr>
				</table>
			</s:form>
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
							<th width="10%">
								<span>报警日期</span>
							</th>
							<th width="10%">
								<span>报警时间</span>
							</th>
							<th width="20%">
								<span>报警设备</span>
							</th>
							<th width="10%">
								<span>报警内容</span>
							</th>
							<th width="10%">
								<span>管理者</span>
							</th>
							<th width="10%">
								<span>维修状态</span>
							</th>
							<th width="10%">
								<span>维修者</span>
							</th>

							<th width="10%">
								<span>维修备注</span>
							</th>
							<th width="10%">
								<span>操作</span>
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
									<s:date name="#alarm.alarmDatetime" format="yyyy-MM-dd" />
								</td>
								<td width="10%">
									<s:date name="#alarm.alarmDatetime" format="HH:mm:ss" />
								</td>
								<td width="20%">
									<s:if test="#alarm.cabinet !=null">
										<s:property value="#alarm.cabinet.line.name" /><br/>
										<s:property value="#alarm.cabinet.cabNumber" />
										<s:property value="#alarm.cabinet.cabType.value" />
									</s:if>
									<s:elseif test="#alarm.device !=null">
										<s:property value="#alarm.device.cabinet.line.name" /><br/>
										<s:property value="#alarm.device.cabinet.cabNumber" />
										<s:property value="#alarm.device.cabinet.cabType.value" /><br/>
										<s:property value="#alarm.device.name" />
									</s:elseif>
								</td>

								<td width="10%">
									<s:property value="alarmText" />
								</td>
								<td width="10%">
										<s:if test="#alarm.cabinet !=null">
											<s:property value="#alarm.cabinet.user.username" /><br/>
										</s:if>
										<s:elseif test="#alarm.device !=null">
									
									   		<s:property value="#alarm.device.cabinet.user.username" />
										
										</s:elseif>
								</td>
								<td width="10%">
									<s:if test="status==0">
										未维修
									</s:if>
									<s:else>
										已维修
									</s:else>
								</td>
								<td width="10%">
									<s:property value="repairUser" />

								</td>
								<td width="10%">
									<s:property value="note" />

								</td>
								<td width="10%">
									<a>维修确认</a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
				
				<div class="datagrid-pager pagination" id="datagrid_89353_pager">
			<s:iterator value="pageBean">
				<table border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td>
								<select class="pagination-page-list" name="pageList" onchange="window.location='listAlarm.action?pageList='+this.options[this.options.selectedIndex].value">
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
								<a href="listAlarm.action?page=1"
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
								<a href="listAlarm.action?page=${CurrentPage-1 }"
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
								<a href="listAlarm.action?page=${CurrentPage+1 }"
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
								<a href="listAlarm.action?page=${TotalPage}"
									class="pagination-last-btn p-plain"><span
									class="pagination-last p-btn ">&nbsp;</span>
								</a>
								</s:else>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="listAlarm.action?page=${CurrentPage}" class="pagination-load-btn p-plain"><span
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
		</div></div>
		<script>
			var table_tr = document.getElementById('table_tr');
			$('#table_th').width(table_tr.scrollWidth);
			$(window).resize(function() {
				var table_tr1 = document.getElementById('table_tr');
				$('#table_th').width(table_tr1.scrollWidth-1);
			});
							
		</script>
	</body>
</html>
