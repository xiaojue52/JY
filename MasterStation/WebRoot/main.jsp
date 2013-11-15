<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute(
			"username");
	//System.out.print("\n"+path+"\n"+basePath);
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<title>监测主站</title>
	</head>

	<body>
		<div class="top_menu">
			<jsp:include page="/frame/top.jsp"></jsp:include>
		</div>

		<div class="main_layout">
			<table>
				<tr>
					<td class="left_td">
						<div class="left_frame">
							<jsp:include page="/frame/left.jsp"></jsp:include>
						</div>
					</td>
					<td class="center_td">
					<iframe src="mainAction.action" class="center_frame" id="content_iframe"></iframe>
					</td>
				</tr>
			</table>
		</div>
		
	</body>
</html>
