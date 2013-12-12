<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute("username");
	//System.out.print("\n"+path+"\n"+basePath);
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
	Integer isFirstLogin = (Integer)request.getSession().getAttribute("isFirstLogin");
	String topContent = (String) request.getSession().getAttribute("topContent");
	String bottomContent = (String) request.getSession().getAttribute("bottomContent");
	String imagePath = (String) request.getSession().getAttribute("imagePath");
	String userLevel = (String) request.getSession().getAttribute("userLevel");
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
		<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="js/ext/ext-all.js"></script>
		
		
		<script src="js/set-src.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/alert-page.css" />
		<script type="text/javascript" src="js/modify-password.js"></script>
		<script src="js/get-data.js" type="text/javascript"></script>
		<script src="js/control.js" type="text/javascript"></script>
		<title>监测主站</title>
	</head>

	<body>
		
		<div id="north-div">
			<div class="top_frame">
	    		<span class="comSpan" onclick="setFrameSrc('mainAction.action','系统监控');"><a>首页 |</a> </span><span>欢迎<%=username %>登陆 | </span><span class="comSpan" onclick="setFrameSrc('listAlarm.action?unhandledTag=1','报警记录');"><img style="width:16px;height:16px;" src="images/message.png"/>未处理报警(<span id="unhandledCount" style="margin-left:0px;">0</span>) |</span> <span class="comSpan" onclick="ModifyPassword.showPage();"><img style="width:14px;height:14px;" src="images/modify.png"/>修改密码 |</span> <span class="comSpan" onclick="window.location.href='logout.action'"><img style="width:14px;height:14px;" src="images/quit.png"/>退出系统</span>
			</div>
			<div class="logDiv" id="top-content"><img src="<%=imagePath %>"><span><%=topContent %></span></div>
		</div>
		<div id="center-div" style="height:100%">
				<iframe src="" class="center_frame"
					id="content_iframe" width=100% height=100% frameborder='0'></iframe>
		</div>
		<div id="south-div"> 
			<div id="bottom-content" style="text-align:center; width:100%">
			<%=bottomContent %>
			</div>
		</div>
		<div id="monitor_div" class="panelDiv">
			<button onclick="setFrameSrc('mainAction.action','温度监控');" class="itemDiv">温度监控</button>
			<button onclick="setFrameSrc('getCabinetStatus.action','设备状态');" class="itemDiv">设备状态</button>
		</div>
		<div id="user_div" class="panelDiv">
			<button onclick="setFrameSrc('listUser.action','用户管理');" class="itemDiv">用户管理</button>
		</div>	
		<div id="system_div" class="panelDiv">
					<button  onclick="setFrameSrc('createTree.action','设备管理');" class="itemDiv">设备管理</button>
				<button id="sys_config"  onclick="setFrameSrc('showAlarmType.action','系统参数设置');" class="itemDiv">系统参数设置</button>
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
				<h2>操作<a id="btnClose" onclick="ModifyPassword.closePage();">关闭</a></h2>
				<div id="modifyPassword_dialogDiv">
    	    		<jsp:include page="/account/account.jsp"></jsp:include>
    	    	</div>
			</div>
		<input id="isFirstLogin" type="hidden" value="<%=isFirstLogin %>"/>
		<input id="userLevel" type="hidden" value="<%=userLevel %>"/>
		<script>
		if($('#isFirstLogin').val()==1){
			$(".errorMessage").html("首次登陆请更改密码！");
			showPage();
		}
		if($("#userLevel").val()!="super_admin")
			$("#sys_config").hide();
		</script>
		<script type="text/javascript" src="js/viewport.js"></script>
	</body>
</html>
