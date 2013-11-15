<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>用户详情</title>
    <base href="<%=basePath %>">
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
  </head>
 
  <body>
  <div class="center_frame">
   <s:form action="updateUser.action" method="post">
   <tr><td align="right">用户名：</td><td><input name="user.username" value="${ user.username}" readonly="readonly" class="readonly"/></td></tr> 
	<tr><td align="right">手机号码：</td><td><input name="user.contact" value="${user.contact }"/></td></tr>
	<tr><td align="right">所在单位：</td><td><input name="user.company" value="${user.company }"/></td></tr>
	<tr><td align="right">职位级别：</td><td><input name="user.jobLevel" value="${user.jobLevel }"/></td></tr>
	<tr><td align="right">系统角色：</td><td><select name="user.userLevel">
	<s:if test = "%{user.userLevel == 'com_admin'}">
	<option value="com_admin">普通管理员</option>
	<option value="user">普通用户</option>
	</s:if>
	<s:else>
	<option value="user">普通用户</option>
	<option value="com_admin">普通管理员</option>
	</s:else>
	</select></td></tr>
   <tr><td align="right">重置密码：</td><td><select name="resetPassword"><option value="0">否</option>
	<option value="1">是</option></select></td></tr>
   <tr><td align="right">
   <input type="hidden" name="user.userId" value="${user.userId }"/>
   <input type="submit" value="提交"/>
    <input type="button" onClick="window.location = '<%=path%>/listUsers.action';" value="返回">
   </td></tr>
    </s:form>

    </div>
  </body>
</html>