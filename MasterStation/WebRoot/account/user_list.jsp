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
		<link rel="stylesheet" type="text/css" href="css/toolbar.css" />
		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		<link rel="stylesheet" type="text/css" href="css/alert-page.css" />
		<script type="text/javascript" src="js/alert-page.js"></script>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
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
		<div class="toolbar">
			<s:form action="listUsers.action">
				<span> 用户名：<input type="text" name="sqlUserColumn.username"/></span><span>所在单位：<input
					type="text" name="sqlUserColumn.company"/></span>
				<span><input type="submit" value="查询" class="toolbarButton"/> </span>
				<span><button id="btnShow" class="toolbarButton">添加用户</button></span>
			</s:form>	
		</div>
		<s:if test="ret==-1">
		<div><span class="errorMessage">用户名冲突，用户已存在请改名！</span></div>
		</s:if>
		<s:if test="ret==-2">
		<div><span class="errorMessage">删除用户失败！请解除用户拥有的柜体！</span></div>
		</s:if>
		<table class="gridtable">
			<tr>
				<th>
					<span>用户编号</span>
				</th>
				<th>
					<span>用户名</span>
				</th>
				<th>
					<span>手机号码</span>
				</th>
				<th>
					<span>所在单位</span>
				</th>
				<th>
					<span>职务级别</span>
				</th>
				<th>
					<span>系统角色</span>
				</th>
				<th>
					<span>操作</span>
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
							href="#" onclick="getUserDetails('<s:property value="userId" />');return false" />修改</a>
						<a
							href='<%=path%>/deleteUser.action?user.userId=<s:property value="userId" />'
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
		<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none">
				<h2>操作<a class="btnClose">关闭</a></h2>
				<div id="addUser_dialogDiv">
    	    		<jsp:include page="/account/add_user.jsp"></jsp:include>
    	    	</div>
    	    	<div id="userDetails_dialogDiv">
    	    		<jsp:include page="/account/user_details.jsp"></jsp:include>
    	    	</div>
			</div>
	</body>
</html>
