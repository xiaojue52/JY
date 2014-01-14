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
		<title>增加用户</title>
		<base href="<%=basePath%>">
		
	</head>

	<body>

		<div class="textCenter">
			<div><span class="errorMessage" id="add-user-page"></span></div>
			<s:form action="addUser.action" method="post" id="form" >
				<tr>
					<td align="right">
						<span>用户名：</span>
					</td>
					<td>
						<input maxLength=8 class="checkInput-adduserpage stopSpChars" maxlength="8" type="text" name="user.username"  style="width:120px;"/><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>初始密码：</span>
					</td>
					<td>
						<input type="text" name="user.password" value="000000" readonly="readonly"
							class="readonly"  style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>手机号码：</span>
					</td>
					<td>
						<input class="checkInput-adduserpage numberInput" maxlength="11" type="text" name="user.contact"  style="ime-mode:disabled;width:120px;" /><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>所在单位：</span>
					</td>
					<td>
						<input maxLength=20 class="ecl-adduserpage" type="text" name="user.company"  style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>职位级别：</span>
					</td>
					<td>
						<input maxLength=20 class="ecl-adduserpage" type="text" name="user.jobLevel"  style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>短信接收：</span>
					</td>
					<td>
						<select name="user.canRecMes"  style="width:120px;">
							<option value="1">
								是
							</option>
							<option value="0">
								否
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>系统角色：</span>
					</td>
					<td>
						<select name="user.userLevel"  style="width:120px;">
							<option value="com_admin">
								普通管理员
							</option>
							<option value="user">
								普通用户
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>所属班组：</span>
					</td>
					<td>
						<select name="user.userGroup.id"  style="width:120px;">
							<%
								for(int i=0;i<list.size();i++) {
							%>
							<option value='<%=list.get(i).getId()%>'>
								<%=list.get(i).getGroupName()%>
							</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit" value="提交" onclick="return Control.checkInput('add-user-page','checkInput-adduserpage','ecl-adduserpage');" class="comButton" style="margin-left:30px;"/>
					</td>
					<td align="center">		
						<input type="button"
							
							value="取消" class="btnClose comButton"  style="margin-left:40px;"/>
					</td>
				</tr>
			</s:form>
		</div>
	</body>
</html>