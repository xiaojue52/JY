<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute("username");		
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<link rel="stylesheet" type="text/css" href="js/ext/resources/css/ext-all.css" /> 
		<link rel="stylesheet" type="text/css" href="css/common.css" /> 
		<link rel="stylesheet" type="text/css" href="css/toolbar.css" /> 
		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		<script src="js/ext/adapter/ext/ext-base.js" type="text/javascript"></script>
		<script src="js/ext/ext-all.js" type="text/javascript"></script>

	<script src="js/create-tree.js" type="text/javascript"></script>
	</head>

	<body>
		<script type="text/javascript">
		Ext.BLANK_IMAGE_URL = 'js/ext/resources/images/default/tree/s.gif';
		var tag = '<s:property value="tag"/>'; 
		</script>
		<div class="toolbar">
			<span>线路：<input id='queryLine' type="text"/>柜体类型：<input id='queryType' type="text"/>编号：<input id='queryNumber' type="text"/>管理者：<input id='queryUser' type="text"/><input style="margin-left:10px" class="comButton" type="button" value="查询" onclick="queryDevice();"/></span>
		</div>
		<table><tr><td width="170px"><div id="tree_div"></div> </td><td><div><jsp:include page="/system/content.jsp"></jsp:include></div></td></tr></table>
	</body>
</html>
