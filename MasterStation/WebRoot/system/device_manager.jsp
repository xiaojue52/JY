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
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
	List<JYUserGroup> userGroupList = new ArrayList<JYUserGroup>();
	List<JYConstant> cabTypeList = new ArrayList<JYConstant>();
	
	if (username!=null)
	{
	WebApplicationContext wac = (WebApplicationContext) config
			.getServletContext()
			.getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	DataList dataList = (DataList) wac.getBean("DataList");
	userGroupList = dataList.getAllUserGroups();
	cabTypeList = dataList.getCabTpyeConstant();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<link rel="stylesheet" type="text/css"
			href="js/ext/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="css/toolbar.css" />
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		<script src="js/ext/adapter/ext/ext-base.js" type="text/javascript"></script>
		<script src="js/ext/ext-all.js" type="text/javascript"></script>
		<script src="js/control.js" type="text/javascript"></script>
		<meta http-equiv="pragma" content="no-cache"> 
    	<meta http-equiv="cache-control" content="no-cache"> 
     	<meta http-equiv="expires" content="0">  	
	</head>

	<body>
		<script src="js/device-manager.js" type="text/javascript"></script>
		<div class="toolbar" id="toolbar">
			<s:form>
				<span>线路：<input id='queryLine' type="text" />
				</span>
				<span>柜体编号：<input id='queryNumber' type="text" />
				</span>
				<span>柜体类型：<select id="queryType">
						<option value='-1'>全部</option>
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
				<span>管理班组：
						<select id="queryUserGroup">
						<option value='-1'>全部</option>
							<%
								for (int i = 0; i < userGroupList.size(); i++) {
							%>
							<option value='<%=userGroupList.get(i).getId()%>'><%=userGroupList.get(i).getGroupName()%></option>
							<%
								}
							%>
						</select>	
				</span>
				<span><input style="width: 48px;" class="toolbarButton"
						type="button" value="查询" onclick="DeviceManager.queryDevice();" />
				</span>
			</s:form>
		</div>
		<div class="center_table_div" style="margin-top:9px;">
		<script type="text/javascript">
		Ext.BLANK_IMAGE_URL = 'js/ext/resources/images/default/tree/s.gif';
		var tag = '<s:property value="tag"/>'; 
		</script>
		<table cellpadding="0" cellspacing="0" id="treeTable" style="width: 100%">
			<tr>
				<td class="treeTd" width="170">
					<div id="tree_div"></div>
				</td>
				<td class="tree_td" valign="top" >
					<div style="height:480px;"><jsp:include page="/system/content.jsp"></jsp:include></div>
				</td>
			</tr>
		</table>
		</div>
	</body>
</html>
