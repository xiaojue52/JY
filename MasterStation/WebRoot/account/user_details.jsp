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
			<s:form action="updateUser.action" method="post" id="form">
				<tr>
					<td align="right">
						用户名：
					</td>
					<td>
						<input type="text" id="user_username" name="user.username" value=""
							readonly="readonly" class="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right">
						手机号码：
					</td>
					<td>
						<input type="text"  id="user_contact" name="user.contact" value="" />
					</td>
				</tr>
				<tr>
					<td align="right">
						所在单位：
					</td>
					<td>
						<input type="text"  id="user_company" name="user.company" value="" />
					</td>
				</tr>
				<tr>
					<td align="right">
						职位级别：
					</td>
					<td>
						<input type="text"  id="user_jobLevel" name="user.jobLevel" value="" />
					</td>
				</tr>
				<tr>
					<td align="right">
						系统角色：
					</td>
					<td>
						<select id="user_userLevel" name="user.userLevel">
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						重置密码：
					</td>
					<td>
						<select name="resetPassword">
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
						<input type="submit" value="提交" class="comButton"/>
					</td>
					<td align="center">	
						<input type="button"
							value="返回" class="btnClose comButton"/>
					</td>
				</tr>
			</s:form>
		</div>
	</body>
</html>