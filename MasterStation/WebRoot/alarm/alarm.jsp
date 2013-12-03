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

	</head>

	<body>
		<div class="toolbar">
			<s:form action="listAlarm">
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
							<span>变送器：
							<s:if test="queryDevice == \"%\"||queryDevice==null">
							<input name='queryDevice' type="text" /> 
							</s:if>
							<s:else>
							<input name='queryDevice' type="text" value="<s:property value="queryDevice"/>"/> 
							</s:else>
							 </span>
						</td>
						<td>
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
								
							</select> </span>
							
						</td>
						</tr>
							<tr>
								<td>
									<span>开始日期：
									<s:if test="queryStartDate == \"1000-01-01\"||queryStartDate==null">
									<input name='queryStartDate' type="text" onfocus="setday(this)"/> 
									</s:if>
									<s:else>
									<input name='queryStartDate' type="text" value="<s:property value="queryStartDate"/>" onfocus="setday(this)"/> 
									</s:else>
									</span>
								</td>
								<td>
									<span>结束日期：
									<s:if test="queryEndDate == \"9999-12-12\"||queryEndDate==null">
									<input name='queryEndDate' type="text" onfocus="setday(this)"/> 
									</s:if>
									<s:else>
									<input name='queryEndDate' type="text" value="<s:property value="queryEndDate"/>" onfocus="setday(this)"/> 
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
									<s:date name="#alarm.createDate" format="yyyy-MM-dd" />
								</td>
								<td width="10%">
									<s:date name="#alarm.createTime" format="HH:mm:ss" />
								</td>
								<td width="20%">
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

								<td width="10%">
									<s:property value="alarmText" />
								</td>
								<td width="10%">
										<s:if test="#alarm.device !=null">
									   		<s:property value="#alarm.device.cabinet.user.username" />
										</s:if>
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
								<s:if test="status==0">
									<a href="javascript:void(0);" onclick="showPage('<s:property value="#alarm.id" />','<s:property value="#alarm.alarmText" />')">维修确认</a>
								</s:if>
								<s:else>
									<a>维修确认</a>
								</s:else>
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
		<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none;width:300px;height:310px;">
				<h2>操作<a onClick="closePage()">关闭</a></h2>
				<div>
    	    		<s:form id="form">
    	    		<div><span style="margin-left:8px;">报警信息：</span><textarea id="textarea" style="margin-left:30px;margin-top:8px;" readonly="readonly" cols="30" rows="5"></textarea></div>
    	    		
    	    		<div style="margin-top:8px;"><span style="margin-left:8px;">维修备注：</span><textarea name="alarmTemp.note" style="margin-left:30px;margin-top:8px;" cols="30" rows="5"></textarea></div>
    	    		
    	    		<div style="margin-top:12px;width:100%">
    	    		<table style="width:100%">
    	    		<tr>
    	    		<td align="center">
    	    		<input type="submit" value="确定" />
    	    		</td>
    	    		<td align="center">
    	    		<input type="button" value="取消" onclick="closePage()"/>
    	    		</td>
    	    		</tr>
    	    		</table>
    	    		</div>
    	    		</s:form>
    	    		
    	    	</div>
			</div>
	</body>
</html>
