<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/menu.css"/>
<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
<script src="js/set-src.js" type="text/javascript"></script>
<div class="nav_title">功能菜单</div>

<div class="left_option"><a onclick="setFrameSrc('mainAction.action');">系统监控</a></div>
<%
	String userLevel = (String) session.getAttribute("userLevel");
	if (userLevel!=null&&userLevel.equals("com_admin")||userLevel.equals("super_admin")) { %>
	<div class="left_option"><a onclick="setFrameSrc(list);">系统管理</a></div>
	<div class="left_option child_menu"><a onclick="setFrameSrc('system/system_tree.jsp');">设备管理</a></div>
	<div class="left_option child_menu"><a onclick="setFrameSrc('system/conf.jsp');">系统参数设置</a></div>
<% }%>
<%
	if (userLevel!=null&&userLevel.equals("super_admin")){
 %>
<div class="left_option"><a onclick="setFrameSrc('listUsers.action');">用户管理</a></div>
<%} %>
<div class="left_option"><a>历史数据</a></div>
<div class="left_option child_menu"><a onclick="setFrameSrc('unhandledException.action');">报警记录</a></div>
	<div class="left_option child_menu"><a onclick="setFrameSrc('listHistory.action');">历史记录</a></div>
<div class="left_option"><a onclick="setFrameSrc('listHistory.action');">数据对比</a></div>
