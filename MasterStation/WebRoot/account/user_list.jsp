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
		<link rel="stylesheet" type="text/css" href="css/table/src/css/extgrid.v.1.2.5.all.css" />
		<link rel="stylesheet" type="text/css" href="css/toolbar.css" />
		<link rel="stylesheet" type="text/css" href="css/table.css" />
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
		<br/>
		<s:if test="ret==-1">
		<div><span class="errorMessage">用户名冲突，用户已存在请改名！</span></div>
		</s:if>
		<s:if test="ret==-2">
		<div><span class="errorMessage">删除用户失败！请解除用户拥有的柜体！</span></div>
		</s:if>
<div
		style="min-width:900px;width: 100%; height: 450px; margin-top: 0px;">
		<div class="datagrid-container datagrid-container-border"
			id="datagrid_89353"
			style="position: relative; overflow: hidden; width: 100%; height: 450px;">
	  <div style="width:100%;height:420px;overflow: auto">
	  <table class="gridtable">
			
			<tr>
				<th width="10%">
					<span>用户编号</span>
				</th>
				<th width="15%">
					<span>用户名</span>
				</th>
				<th width="15%">
					<span>手机号码</span>
				</th>
				<th width="15%">
					<span>所在单位</span>
				</th>
				<th width="15%">
					<span>职务级别</span>
				</th>
				<th width="15%">
					<span>系统角色</span>
				</th>
				<th width="15%">
					<span>操作</span>
				</th>
			</tr>
			<s:iterator value="pageBean.list" var="account">
				<tr>
					<td width="10%">
						<s:property value="userId" />
					</td>
					<td width="15%">
						<s:property value="username" />
					</td>
					<td width="15%">
						<s:property value="contact" />
					</td>
					<td width="15%">
						<s:property value="company" />
					</td>
					<td width="15%">
						<s:property value="jobLevel" />
					</td>
					<td width="15%">
						<s:if test="%{#account.userLevel == 'user'}">
							<span>普通用户</span>
						</s:if>
						<s:else>
							<span>普通管理员</span>
						</s:else>
					</td>
					<td width="15%">
						<a
							href="#" onclick="getUserDetails('<s:property value="userId" />');return false" />修改</a>
						<a
							href='<%=path%>/deleteUser.action?user.userId=<s:property value="userId" />'
							onclick="return del()">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table></div>
			<div class="datagrid-pager pagination" id="datagrid_89353_pager">
			<s:iterator value="pageBean">
				<table border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td>
								<select class="pagination-page-list">
									<option value="10">
										10
									</option>
									<option value="20">
										20
									</option>
									<option value="30">
										30
									</option>
									<option value="40">
										40
									</option>
									<option value="50">
										50
									</option>
								</select>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="javascript:void(0)"
									class="pagination-first-btn p-plain p-btn-disabled"><span
									class="pagination-first  p-btn">&nbsp;</span>
								</a>
							</td>
							<td>
								<a href="javascript:void(0)"
									class="pagination-prev-btn p-plain p-btn-disabled"><span
									class="pagination-prev  p-btn">&nbsp;</span>
								</a>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<span style="padding-left: 6px;">第</span>
							</td>
							<td>
								<s:property value="CurrentPage" />
							</td>
							<td>
								<span style="padding-right: 6px;">页  共<s:property value="TotalPage" />页</span>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="javascript:void(0)"
									class="pagination-next-btn p-plain p-btn-disabled"><span
									class="pagination-next p-btn">&nbsp;</span>
								</a>
							</td>
							<td>
								<a href="javascript:void(0)"
									class="pagination-last-btn p-plain p-btn-disabled"><span
									class="pagination-last p-btn ">&nbsp;</span>
								</a>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="javascript:void(0)" class="pagination-load-btn p-plain"><span
									class="pagination-load p-btn">&nbsp;</span>
								</a>
							</td>
							<td id="pagination-toolbar-datagrid_89353"></td>
						</tr>
					</tbody>
				</table>
				
				<div class="pagination-info">
					当前显示1到10条，共<s:property value="TotalCount" />条
				</div>
				</s:iterator>
			</div>
		</div>
	</div>		
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
