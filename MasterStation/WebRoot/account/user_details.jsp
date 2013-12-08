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
		<title>用户详情</title>
		<base href="<%=basePath%>">
	</head>

	<body>
		<div class="textCenter">
		<div><span class="errorMessage" id="user-details-page"></span></div>
			<s:form action="updateUser.action" method="post" id="form">
				<tr>
					<td align="right">
						<span>用户名：</span>
					</td>
					<td>
						<input type="text" id="user_username" name="user.username" value=""
							readonly="readonly" class="readonly" style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>手机号码：</span>
					</td>
					<td>
						<input class="checkInput-userdetailspage numberInput" maxlength="11" type="text"  id="user_contact" name="user.contact" value="" style="width:120px;"/><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>所在单位：</span>
					</td>
					<td>
						<input class="ecl-userdetailspage" type="text"  id="user_company" name="user.company" value="" style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>职位级别：</span>
					</td>
					<td>
						<input class="ecl-userdetailspage" type="text"  id="user_jobLevel" name="user.jobLevel" value="" style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>系统角色：</span>
					</td>
					<td>
						<select id="user_userLevel" name="user.userLevel" style="width:120px;">
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>重置密码：</span>
					</td>
					<td>
						<select name="resetPassword" style="width:120px;">
							<option value="0">
								否
							</option>
							<option value="1">
								是
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="hidden" id="user_userId" name="user.userId"
							value="${user.userId }" />
						<input type="submit" value="提交" class="comButton" onclick="return checkInput('user-details-page','checkInput-userdetailspage','ecl-userdetailspage');" style="margin-left:30px;"/>
					</td>
					<td align="center">	
						<input type="button"
							value="返回" class="btnClose comButton" style="margin-left:40px;"/>
					</td>
				</tr>
			</s:form>
		</div>
	</body>
</html>