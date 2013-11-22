<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute(
			"username");
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>参数设置</title>
		<base href="<%=basePath%>">
		<link href="<%=path%>/css/frame.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/menu.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js"></script>
	</head>

	<body>
		<div>
			<div><span>监控时间设置</span><span>柜体类型：<select><option>test</option></select></span><span>时间间隔：<select><option>30分钟</option></select></span><input type="button" value="确定"/></div>
			<div><span>温度计算</span><table></table><input type="button" value="确定" /></div>
			<div><span>报警条件设置</span><table></table><input type="button" value="确定"></div>
			<div><span>余额提醒设置</span><span>每月提醒日期：<select><option>1号</option></select></span><span>短信接收人：<select><option>张三</option></select></span><input type="button" value="确定"/></div>
		</div>
	</body>
</html>