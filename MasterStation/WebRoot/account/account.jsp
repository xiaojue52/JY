<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String username = (String)request.getSession().getAttribute("username");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<body>
		<div>
			<div><span class="errorMessage"> </span></div>
			<table id="form" style="margin-top:30px">
				<tr>
					<td align="right">
						<span class="comText">用户名:</span>
					</td>
					<td><input class="comInput" type="text" value="<%=username%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">
						<span class="comText">旧密码:</span>
					</td>
					<td>
						<input class="comInput" type="password" id="password"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="comText">新密码:</span>
					</td>
					<td>
						<input class="comInput" id="newPassword" type="password">
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="comText">确认密码:</span>
					</td>
					<td>
						<input class="comInput" id="verifyPassword" type="password">
					</td>
				</tr>
				<tr>
					<td align="center">
						<br/><br/>
						<input style="width:48px;" class="comButton" type="button" value="提交" onClick="verify();" />
					</td>
					<td align="center">
						<br/><br/>
						<input style="width:48px;" class="comButton" type="button" value="取消" onClick="closePage();"/>
					</td>
				</tr>
			</table>
		</div>

	</body>
</html>
