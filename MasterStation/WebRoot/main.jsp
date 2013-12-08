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
		<link rel="stylesheet" type="text/css"
			href="css/common.css" />
		<script type="text/javascript" src="js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="js/ext/ext-all.js"></script>
		<script type="text/javascript" src="js/viewport.js"></script>
		<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="js/set-src.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/alert-page.css" />
		<script type="text/javascript" src="js/modify-password.js"></script>
		<script src="js/get-data.js" type="text/javascript"></script>
		<script src="js/control.js" type="text/javascript"></script>
		<title>监测主站</title>
	</head>

	<body>
		<div id="north-div">
			<jsp:include page="/frame/top.jsp"></jsp:include>
		</div>
		<div id="center-div" style="height:100%">
				<iframe src="" class="center_frame"
					id="content_iframe" width=100% height=100% frameborder='0'></iframe>
		</div>
		<div id="south-div"> 
			版权所有 翻版必究 
		</div>
		<div id="monitor_div" class="panelDiv">
			<button onclick="setFrameSrc('mainAction.action','系统监控');" class="itemDiv">系统监控</button>
			<button onclick="setFrameSrc('getCabinetStatus.action','设备状态');" class="itemDiv">设备状态</button>
		</div>
		<div id="user_div" class="panelDiv">
			<button onclick="setFrameSrc('listUser.action','用户管理');" class="itemDiv">用户管理</button>
		</div>	
		<div id="system_div" class="panelDiv">
					<button  onclick="setFrameSrc('createTree.action','设备管理');" class="itemDiv">设备管理</button>
				<button  onclick="setFrameSrc('showAlarmType.action','系统参数设置');" class="itemDiv">系统参数设置</button>
		</div>
		<div id="history_div" class="panelDiv">
				<button  onclick="setFrameSrc('listAlarm.action','报警记录');" class="itemDiv">报警记录</button>

				<button  onclick="setFrameSrc('cabinetHistory.action','历史记录');" class="itemDiv">历史记录</button>
		</div>
		<div id="data_div" class="panelDiv">
			<button onclick='setFrameSrc("history/chart.jsp","数据对比");' class="itemDiv" >数据对比</button>
		</div>
		<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none;width:300px;">
				<h2>操作<a id="btnClose" onclick="closePage();">关闭</a></h2>
				<div id="modifyPassword_dialogDiv">
    	    		<jsp:include page="/account/account.jsp"></jsp:include>
    	    	</div>
			</div>
		<script>
		
		</script>
		
	</body>
</html>
