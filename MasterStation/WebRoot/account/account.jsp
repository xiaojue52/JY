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
			<div><span class="errorMessage" id="account-page"> </span></div>
			<table id="form" style="margin-top:20px">
				<tr>
					<td align="right">
						<span>用户名:</span>
					</td>
					<td><input class="comInput" type="text" value="<%=username%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">
						<span>旧密码:</span>
					</td>
					<td>
						<input style="ime-mode:disabled;" maxlength="16" class="comInput checkInputLength" type="password" id="password"/><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>新密码:</span>
					</td>
					<td>
						<input style="ime-mode:disabled;" maxlength="16" class="comInput checkInputLength" id="newPassword" type="password"><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>确认密码:</span>
					</td>
					<td>
						<input style="ime-mode:disabled;" maxlength="16" class="comInput checkInputLength" id="verifyPassword" type="password"><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td align="center">
						<br/><br/>
						<input style="width:48px;" class="comButton" type="button" value="提交" onClick="if(Control.checkInputLength('account-page','checkInputLength',5))ModifyPassword.verify();" />
					</td>
					<td align="center">
						<br/><br/>
						<input style="width:48px;" class="comButton" type="button" value="取消" onClick="ModifyPassword.closePage();"/>
					</td>
				</tr>
			</table>
		</div>

	</body>
</html>
