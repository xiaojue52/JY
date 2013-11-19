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
		<title>系统登录</title>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<LINK href="css/index.css" type=text/css rel=STYLESHEET>
		<style type="text/css">
<!--
.STYLE1 {
	color: #000000;
	font-weight: bold;
}
-->
</style>
<script>
		if (window.parent.document.getElementById("content_iframe")!=null){
			window.parent.location = "index.jsp";
		}
	function checkInput() {
	
		var username = document.getElementById("username");
		var pass = document.getElementById("password");
		if (username.value == "") {
			alert("请输入用户名");
			username.focus();
			return false;
		}
		if (pass.value == "") {
			alert("请输入密码");
			return false;
		}
		return true;
	}
	function verify(){
	
		if(checkInput()){
			document.getElementById("loginForm").submit();
		}
	}
</script>
		<script src="<%=path %>/js/menu.js" type="text/javascript"></script>
	</head>
	<body bgColor=#ffffff leftMargin=0 topMargin=0 rightMargin=0
		marginheight="0" marginwidth="0" onload="startTime()">
		<center>

			<div>
				<jsp:include page="/frame/index_top.jsp"></jsp:include>
			</div>

			<DIV id=content>
				<DIV class=module_darkgray>
					<DIV class=topleft_darkgray></DIV>
					<DIV class=topright_darkgray></DIV>
					<DIV class=moduleborder>
						<DIV class=module_inset_darkgray>
							<DIV class=bottomedge_inset_darkgray>
								<DIV class=topleft_inset_darkgray></DIV>
								<DIV class=topright_inset_darkgray></DIV>
								<div>
									<table>
										<tr>
											<td style="color: #00688B; width: 400px;" align="center"
												width="50%">
												<div align="center">
													<strong><font size="4">用户登录</font>
													</strong>
												</div>
												<div>
													<form action="login.action" method="post" id="loginForm">
														<table width="100%" style="color: #00688B; width: 300px;">
															<tr>
																<td align="right">
																	<font size="2"><strong><font face="宋体">用户名</font>
																	</strong>
																	</font>
																	<br>
																</td>
																<td>
																	<input name="username" id="username" />
																</td>
															</tr>
															<tr>
																<td align="right">
																	<font face="宋体" size="2"><strong>密&nbsp;
																			码</strong>
																	</font>
																</td>
																<td>
																	<input name="password" id="password" type="password" />
																</td>
															</tr>
															<tr>
																<td align="center" colspan="2">
																	<input type="button" value="登录" onClick="verify()" />
																	<input type="reset" value="重置" />
																</td>
															</tr>
														</table>
													</form>
												</div>
											</td>
											<td style="padding-top: 0px; position: static;" align="left">
												<IMG height="200" src="images/index_cable.jpg" width="450">
											</td>
										</tr>
									</table>
								</div>
							</DIV>
						</div>
					</div>
				</DIV>
			</div>
			<DIV>
				<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>
					<TBODY>
						<TR>
							<TD vAlign=top align=center width=776>
								电缆接头温度在线监测项目
							</TD>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>

		</center>
	</body>
</html>