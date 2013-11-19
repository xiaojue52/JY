<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
String username = (String)request.getSession().getAttribute("username");
 %>


<div class="top_frame">
	    <span><a>首页 |</a> </span><span>欢迎<%=username %>登陆 | </span><span><a>未读短信息(0) |</a> </span><span><a onclick="showPage();">修改密码 </a>| </span><a onclick="window.location.href='logout.action'">退出</a><br>
</div>