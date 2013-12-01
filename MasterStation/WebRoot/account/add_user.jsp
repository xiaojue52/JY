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
		<title>增加用户</title>
		<base href="<%=basePath%>">
		
	</head>

	<body>

		<div class="textCenter">
			<s:form action="addUser.action" method="post" id="form">
				<tr>
					<td align="right">
						<span>用户名：</span>
					</td>
					<td>
						<input type="text" name="user.username" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>初始密码：</span>
					</td>
					<td>
						<input type="text" name="user.password" value="000000" readonly="readonly"
							class="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>手机号码：</span>
					</td>
					<td>
						<input type="text" name="user.contact" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>所在单位：</span>
					</td>
					<td>
						<input type="text" name="user.company" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>职位级别：</span>
					</td>
					<td>
						<input type="text" name="user.jobLevel" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>系统角色：</span>
					</td>
					<td>
						<select name="user.userLevel">
							<option value="com_admin">
								普通管理员
							</option>
							<option value="user">
								普通用户
							</option>
						</select>
					</td>
				</tr>

				<tr>
					<td align="center">
						<input type="submit" value="提交" class="comButton"/>
					</td>
					<td align="center">		
						<input type="button"
							
							value="取消" class="btnClose comButton"/>
					</td>
				</tr>
			</s:form>
		</div>
	</body>
</html>