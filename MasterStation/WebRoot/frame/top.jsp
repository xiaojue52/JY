<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
String username = (String)request.getSession().getAttribute("username");
 %>


<div class="top_frame">
	    <span class="comSpan" onclick="setFrameSrc('mainAction.action','系统监控');"><a>首页 |</a> </span><span>欢迎<%=username %>登陆 | </span><span class="comSpan" onclick="setFrameSrc('listAlarm.action?unhandledTag=1','报警记录');"><img style="width:16px;height:16px;" src="images/message.png"/>未处理报警(<span id="unhandledCount" style="margin-left:0px;">1</span>) |</span> <span class="comSpan" onclick="showPage();"><img style="width:14px;height:14px;" src="images/modify.png"/>修改密码 |</span> <span class="comSpan" onclick="window.location.href='logout.action'"><img style="width:14px;height:14px;" src="images/quit.png"/>退出系统</span>
</div>
<div class="logDiv"><img src="images/sg.png"><span>电缆接头温度在线监测主站系统</span></div>