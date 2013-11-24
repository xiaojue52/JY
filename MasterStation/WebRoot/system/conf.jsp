<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute(
			"username");
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>参数设置</title>
		<base href="<%=basePath%>">
		<link href="<%=path%>/css/config.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/menu.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js"></script>
	</head>

	<body>
		<div class="config_page">
			<div class="config_time">
				<span><strong>监控时间设置</strong> </span><span>柜体类型：<select>
						<option>
							test
						</option>
					</select> </span><span>时间间隔：<select>
						<option>
							30分钟
						</option>
					</select> </span><span><input type="button" value="确定" /> </span>
			</div>
			<div class="config_calculate">
				<span><strong>温度计算</strong> </span>

				<table class="gridtable">
					<tr>
						<th>
							计算事项
						</th>
						<th>
							函数关系
						</th>
						<th>
							备注
						</th>
						<th>
							操作
						</th>
					</tr>
					<tr>
						<td>
							肘型头内部温度
						</td>
						<td>
							<div>
								<input type="radio" name="identify" />
								<input type="text" value="=f(x1,x2,x3,x4)=ax1x2x3x4" />
							</div>
							<div>
								<input type="radio" name="identify" />
								<input type="text" value="=f(x1,x2,x3,x4)=bx1x2x3x4" />
							</div>
						</td>
						<td>
							x1,x2,x3,x4分别
							<br />
							是ABC三相和环境的温度值
						</td>
						<td>
							<a>修改</a>
						</td>
					</tr>
				</table>
				<span> <br /> <input type="button" value="确定" /> </span>
			</div>
			<div class="config_type">
				<span><strong>报警条件设置</strong> </span>
				<table class="gridtable">
					<tr>
						<th>
							序号
						</th>
						<th>
							报警类型
						</th>
						<th>
							阀值设置
						</th>
					</tr>
					<tr>
						<td>
							1
							<input type="checkbox" />
						</td>
						<td>
							报警温度超出设定阀值（T1°）
						</td>
						<td>
							T1=
							<input type="text" value="75" />
						</td>
					</tr>
					<tr>
					<td>
							2
							<input type="checkbox" />
						</td>
						<td>
							三相之间的温度差查过设定值（T2°）
						</td>
						<td>
							T2=
							<input type="text" value="75" />
						</td></tr>
					<tr>
					<td>
							3
							<input type="checkbox" />
						</td>
						<td>
							三相与环境温度差超过设定值（T3°）
						</td>
						<td>
							T3=
							<input type="text" value="75" />
						</td></tr>

				</table>
				<span> <br /> <input type="button" value="确定"> </span>
			</div>
			<div class="config_cost">
				<span><strong>余额提醒设置</strong> </span><span>每月提醒日期：<select>
						<option>
							1号
						</option>
					</select> </span><span>短信接收人：<select>
						<option>
							张三
						</option>
					</select> </span>
					<span>
				<input type="button" value="确定" />
				</span>
			</div>
		</div>
	</body>
</html>