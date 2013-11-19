<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String username = (String)request.getSession().getAttribute("username");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="js/set-src.js" type="text/javascript"></script>
		<title>账户管理</title>
		<script type="text/javascript">
   		function verify(){
		if (document.getElementById("newPassword").value == document.getElementById("verifyPassword").value)
		{	
			if(confirm("确定修改？")){
     			document.getElementById("updateAccount").submit();
    		}
   			 return false;
  		 }
  		 else
  		 	alert("密码不一致");		 	
}  
  </script>
	</head>

	<body>
		<div>
			<s:form id="updateAccount"
				action="updatePassword.action" method="post">
				<tr>
					<td align="right">
						用户名
					</td>
					<td><input type="text" value="<%=username%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">
						旧密码
					</td>
					<td>
						<input type="password" name="password" value="000000"/>
						<s:if test="%{ret ==-1}">
						<span>密码错误</span>
						</s:if>
					</td>
				</tr>
				<tr>
					<td align="right">
						新密码
					</td>
					<td>
						<input id="newPassword" type="password">
					</td>
				</tr>
				<tr>
					<td align="right">
						确认密码
					</td>
					<td>
						<input id="verifyPassword" name="newPassword" type="password">
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="button" value="提交" onClick="verify();" />
						<input type="button" value="取消" onClick="setFrameSrc('mainAction.action');"/>
					</td>
				</tr>
			</s:form>
		</div>

	</body>
</html>
