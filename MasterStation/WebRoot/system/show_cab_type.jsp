<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java"
	import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" import="com.station.data.DataList" import="com.station.po.*"%>	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute("username");	
	
	List<JYUserGroup> list = new ArrayList<JYUserGroup>();
	if (username!=null)
		{
			WebApplicationContext wac = (WebApplicationContext) config
					.getServletContext()
					.getAttribute(
							WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			DataList dataList = (DataList) wac.getBean("DataList");
			list = dataList.getAllUserGroups();
		}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		
	</head>

	<body>

		<div class="textCenter">
			<div><span class="errorMessage" id="show-cabtype-page"></span></div>
			<s:form action="updateCabType.action" method="post" id="updateCabTypeForm" >
				<tr>
					<td align="right">
						<span>柜体类型：</span>
					</td>
					<td>
						<input id="cabType_value" class="checkInput-showcabtypepage stopSpChars" type="text" name="cabType.value"  style="width:120px;"/><span style="color:red">*</span>
					</td>
				</tr>
				
				<tr>
					<td align="center">
						<input type="submit" value="提交" onclick="return Control.checkInput('show-cabtype-page','checkInput-showcabtypepage','ecl-adduserpage');" class="comButton" style="margin-left:30px;"/>
					</td>
					<td align="center">		
						<input type="button"
							
							value="取消" class="btnClose comButton" onClick="CabType.closePage();" style="margin-left:40px;"/>
					</td>
				</tr>
				<input type="hidden" id="cabType_id" name="cabType.id"/>
			</s:form>
		</div>
	</body>
</html>