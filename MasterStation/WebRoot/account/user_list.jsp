<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		<script type="text/javascript">
</script>
		<script type="text/javascript">
   function del(){
    if(confirm("你真的想删除该记录吗")){
     return true;
    }
    return false;
   }
  </script>
	</head>

	<body>
		<div class="center_title_frame">
			<s:form action="listUsers.action">
				<span> 用户名：<input type="text" name="sqlUserColumn.username"/>所在单位：<input
					type="text" name="sqlUserColumn.company"/>
				<input type="submit" value="查询" /> </span>
			</s:form>	
				
			<span>
				<button
					onClick="window.location.href='<%=path%>/account/add_user.jsp'">
					添加用户
				</button> </span>
		</div>
		<table class="gridtable">
			<tr>
				<th>
					用户编号
				</th>
				<th>
					用户名
				</th>
				<th>
					手机号码
				</th>
				<th>
					所在单位
				</th>
				<th>
					职务级别
				</th>
				<th>
					系统角色
				</th>
				<th>
					操作
				</th>
			</tr>

			<s:iterator value="pageBean.list" var="account">
				<tr>
					<td>
						<s:property value="userId" />
					</td>
					<td>
						<s:property value="username" />
					</td>
					<td>
						<s:property value="contact" />
					</td>
					<td>
						<s:property value="company" />
					</td>
					<td>
						<s:property value="jobLevel" />
					</td>
					<td>
						<s:if test="%{#account.userLevel == 'user'}">
							<span>普通用户</span>
						</s:if>
						<s:else>
							<span>普通管理员</span>
						</s:else>
					</td>
					<td>
						<a
							href='<%=path%>/showUser.action?user.userId=<%=request.getAttribute("userId")%>'>修改</a>
						<a
							href='<%=path%>/deleteUser.action?user.userId=<%=request.getAttribute("userId")%>'
							onclick="return del()">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>


		<table width=100%>
			<s:iterator value="pageBean">
				<tr>
					<td colspan="6" align="center">
						共
						<s:property value="TotalCount" />
						条记录 共
						<s:property value="TotalPage" />
						页 当前第
						<s:property value="CurrentPage" />
						页
						<br>

						<s:if test="%{CurrentPage == 1}">
第一页 上一页
</s:if>
						<!-- CurrentPage为当前页 -->
						<s:else>
							<a href="listUsers.action?page=1">第一页</a>
							<a
								href="listUsers.action?page=<s:property value="%{CurrentPage-1}"/>">上一页</a>
						</s:else>

						<s:if test="%{CurrentPage!= TotalPage}">
							<a
								href="listUsers.action?page=<s:property value="%{CurrentPage+1}"/>">下一页</a>
							<a href="listUsers.action?page=<s:property value="TotalPage"/>">最后一页</a>
						</s:if>
						<s:else>
下一页 最后一页
</s:else>
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
