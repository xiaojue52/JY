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
		
		<link rel="stylesheet" type="text/css"
			href="js/ext/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css"
			href="css/common.css" />
		<link rel="stylesheet" type="text/css"
			href="css/main.css" />
		<script type="text/javascript" src="js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="js/ext/ext-all.js"></script>
		<script type="text/javascript" src="js/viewport.js"></script>
		<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="js/set-src.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/alert-page.css" />
		<script type="text/javascript" src="js/modify-password.js"></script>
		<title>监测主站</title>
	</head>

	<body>
		<div id="north-div">
			<jsp:include page="/frame/top.jsp"></jsp:include>
		</div>
		<div id="center-div">
				<iframe src="mainAction.action" class="center_frame"
					id="content_iframe" width=100% height=100% frameborder='0'></iframe>
		</div>
		<div id="south-div"> 
			c版权所有 翻版必究 
		</div>
		<div id="monitor_div" class="panelDiv">
			<input type="button" onclick="setFrameSrc('mainAction.action');" value="系统监控" class="itemDiv"/>
		</div>
		<div id="user_div" class="panelDiv">
			<input type="button" onclick="setFrameSrc('listUsers.action');" value="用户管理" class="itemDiv"/>
		</div>	
		<div id="system_div" class="panelDiv">
					<input type="button"  onclick="setFrameSrc('createTree.action');" class="itemDiv" value="设备管理"/>
				<input type="button"  onclick="setFrameSrc('system/conf.jsp');" class="itemDiv" value="系统参数设置"/>
		</div>
		<div id="history_div" class="panelDiv">
				<input type="button"  onclick="setFrameSrc('unhandledException.action');" class="itemDiv" value="报警记录"/>

				<input type="button"  onclick="setFrameSrc('listHistory.action');" class="itemDiv" value="历史记录"/>
		</div>
		<div id="data_div" class="panelDiv">
			<input type="button" onclick='setFrameSrc("listHistory.action");' class="itemDiv" value="数据对比"/>
		</div>
		<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none">
				<h2>操作<a id="btnClose" onclick="closePage();">关闭</a></h2>
				<div id="modifyPassword_dialogDiv">
    	    		<jsp:include page="/account/account.jsp"></jsp:include>
    	    	</div>
			</div>
	</body>
</html>
