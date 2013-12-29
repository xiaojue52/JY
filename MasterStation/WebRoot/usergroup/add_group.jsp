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
		<title>增加班组</title>
		<base href="<%=basePath%>">
		
	</head>

	<body>

		<div class="textCenter">
			<div><span class="errorMessage" id="add-group-page"></span></div>
			<s:form action="addUserGroup.action" method="post" id="form" >
				<tr>
					<td align="right">
						<span>班组名称：</span>
					</td>
					<td>
						<input maxLength=8 class="checkInput-addgrouppage" maxlength="20" type="text" name="userGroup.groupName"  style="width:120px;"/><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>班组负责人：</span>
					</td>
					<td>
						<input class="ecl-addgrouppage" type="text" name="userGroup.leaderName" value="" style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>备注：</span>
					</td>
					<td>
						<textarea name="userGroup.note" style="width:120px;" cols="30" rows="5"></textarea>
					</td>
				</tr>

				<tr>
					<td align="center">
						<input type="submit" value="提交" onclick="return Control.checkInput('add-group-page','checkInput-addgrouppage','ecl-addgrouppage');" class="comButton" style="margin-left:30px;"/>
					</td>
					<td align="center">		
						<input type="button"
							
							value="取消" class="btnClose comButton"  style="margin-left:40px;"/>
					</td>
				</tr>
			</s:form>
		</div>
	</body>
</html>