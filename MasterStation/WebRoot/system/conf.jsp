<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String username = (String)request.getSession().getAttribute("username");
	if (username == null)
		response.sendRedirect(basePath+"index.jsp");
	String userTag = (String)request.getSession().getAttribute("Tag");	
	if (userTag != null&&userTag.equals("user"))
		response.sendRedirect(basePath+"index.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>增加电缆</title>
    <base href="<%=basePath %>">
    <link href="<%=path %>/css/frame.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=path %>/js/menu.js"></script>
     <script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js"></script>
  </head>
 
  <body>
  <div class="center_frame">
   <h1><font>系统参数</font></h1>
   <s:form action="getDataByTime.action" method="post">
   <table style="margin:auto">
<tr><td align="right">定时读取时间：</td><td><select name="timer">
  <option value ="3000">3秒</option>
  <option value ="6000">6秒</option>
</select></td></tr> 
<tr><td align="right">报警决策设定：</td><td><select name="decision">
  <option value ="1">春季</option>
  <option value ="2">夏季</option>
  <option value ="3">秋季</option>
  <option value ="4">东季</option>
</select><input type="button" value="查看决策" onClick="alert('decision');"/></td></tr> 
</table>
    <input type="reset" value="reset"><input type="submit" value="submit"/>
   </s:form>
   </div>
  </body>
</html>