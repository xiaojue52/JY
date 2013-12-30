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
		<title>班组详情</title>
		<base href="<%=basePath%>">
	</head>

	<body>
		<div class="textCenter">
		<div><span class="errorMessage" id="group-details-page"></span></div>
			<s:form action="updateUserGroup.action" method="post" id="form">
				<tr>
					<td align="right">
						<span>班组名称：</span>
					</td>
					<td>
						<input maxLength=20 class="checkInput-groupdetailspage" type="text"  id="groupName" name="userGroup.groupName" value="" style="width:120px;"/><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span>班组负责人：</span>
					</td>
					<td>
						<input maxLength=20 class="ecl-userdetailspage" type="text"  id="leaderName" name="userGroup.leaderName" value="" style="width:120px;"/>
					</td>
				</tr>
					<tr>
					<td align="right">
						<span>备注：</span>
					</td>
					<td>
						<textarea id="groupNote" name="userGroup.note" style="width:120px;" cols="30" rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="hidden" id="groupId" name="userGroup.id"
							value="${userGroup.id}" />
						<input type="submit" value="提交" class="comButton" onclick="return Control.checkInput('group-details-page','checkInput-groupdetailspage','ecl-groupdetailspage');" style="margin-left:30px;"/>
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