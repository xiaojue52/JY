<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute("username");
	//System.out.print("\n"+path+"\n"+basePath);
	if (username != null)
		response.sendRedirect(basePath + "main.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>系统登录</title>
		<base href="<%=basePath%>">
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="js/login.js" type="text/javascript"></script>
<style>
#imgDiv{
		background-image: url('images/login.png');
		margin-top: -227px;
		margin-left: -300px;
		left:50%;
		top:50%;
	}
	.wordDiv{
		color: #9B9B9B;
	}
</style>
	</head>
	<body style="width:100%;height:100%;background-image: url('images/indexbg.png');overflow:hidden">
    	<div id="imgDiv" style="height: 455px;width: 600px;position: absolute;">
    		
    		<div class="wordDiv" style="margin-top:160px;margin-left: 130px;">请输入你的姓名:</div>
			<div style="margin-left: 130px;margin-top: 5px;">
				<input maxLength=8 id="username" type="text" name="username" style="width:350px;"
					/>
			</div>
			<div class="wordDiv" style="margin-top:20px;margin-left: 130px;">请输入你的登录口令:</div>
			<div style="margin-left: 130px;margin-top: 5px;">
				<input maxLength=16 id="password" type="password" name="password" style="width:350px;"
					/>
			</div>
			<div style="margin-top:20px;margin-right: 130px;" align="right">
				<input type="button" value="登  录" onclick="Login.verify();"/>
			</div>
    		<div class="wordDiv" style="margin-top:0px;" align="center"><span class="errorMessage" id="error"></span></div>
			<div class="wordDiv" style="margin-top:40px;" align="center">如果您没有注册账号或已经忘记密码，请联系管理员!</div>
		</div>
	</body>
</html>