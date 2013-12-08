<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>系统登录</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<script>
		if (window.parent.document.getElementById("content_iframe")!=null){
			window.parent.location = "index.jsp";
		}
	function checkInput() {
	
		var username = document.getElementById("username");
		var pass = document.getElementById("password");
		if (username.value == "") {
			alert("请输入用户名");
			username.focus();
			return false;
		}
		if (pass.value == "") {
			alert("请输入密码");
			return false;
		}
		return true;
	}
	function verify(){
	
		if(checkInput()){
			document.getElementById("loginForm").submit();
		}
	}
	
</script>
<style>
#imgDiv{
		background-image: url('images/login.jpg');
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
	<body>
 <form id="loginForm" action="login.action" method="post">
    	<div id="imgDiv" style="height: 455px;width: 600px;position: absolute;">
    		
    		<div class="wordDiv" style="margin-top:160px;margin-left: 130px;">请输入你的姓名:</div>
			<div style="margin-left: 130px;margin-top: 5px;">
				<input id="username" type="text" name="username" style="width:350px;"
					/>
			</div>
			<div class="wordDiv" style="margin-top:20px;margin-left: 130px;">请输入你的登录口令:</div>
			<div style="margin-left: 130px;margin-top: 5px;">
				<input id="password" type="password" name="password" style="width:350px;"
					/>
			</div>
			<div style="margin-top:20px;margin-right: 130px;" align="right">
				<input type="submit" value="登  录" onclick="verify();"/>
			</div>
			<s:if test="ret==-1">
    		<div class="wordDiv" style="margin-top:0px;" align="center"><span class="errorMessage">用户名密码错误！</span></div>
    		</s:if>
			<div class="wordDiv" style="margin-top:40px;" align="center">如果您没有注册账号或已经忘记密码，请联系管理员!</div>
		</div>
    </form>
	</body>
</html>