<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
String username = (String)request.getSession().getAttribute("username");
 %>
<link rel="stylesheet" type="text/css" href="css/menu.css"/>
<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
<script src="js/set-src.js" type="text/javascript"></script>
<div class="top_frame">
	    <span>首页 | </span><span>欢迎<%=username %>登陆 | </span><span>未读短信息(0) | </span><span><a onclick="setFrameSrc('account/account.jsp');">修改密码 </a>| </span><a href='logout.action'>退出</a><br>
</div>