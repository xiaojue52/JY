<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java"
	import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" import="com.station.data.DataList"%>
<%@ page language="java" import="com.station.po.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) request.getSession().getAttribute(
			"username");
	//System.out.print("\n" + path + "\n" + basePath);
	if (username == null)
		response.sendRedirect(basePath + "index.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>柜体类型管理</title>
		<link rel="stylesheet" type="text/css"
			href="css/table/src/css/extgrid.v.1.2.5.all.css" />
		<link href="css/toolbar.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/alert-cab-type-page.css" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="js/control.js" type="text/javascript"></script>
		<script src="js/datepicker.js" type="text/javascript"></script>
		<script src="js/cab-type.js" type="text/javascript"></script>
	</head> 

	<body>
	<div>
			<div class="toolbar">
			
				<table>
					<tr>
								<td>
									<span><input class="toolbarButton" type="button" style="width:100px" onClick='CabType.showPage(0);'
											value="新增"/> </span>
								</td>
							</tr>
				</table>			
		</div>
		<div class="center_table_div">
		<s:if test="code==-1">
		<div><span class="errorMessage">删除失败！请解除相关柜体！</span></div>
		</s:if>
		<div
			style="min-width: 900px; width: 100%; height: 430px; margin-top: 0px;">
			<div class="datagrid-container datagrid-container-border"
				id="datagrid_89353"
				style="position: relative; overflow: hidden; width: 300px; height: 430px;">
				<div style="background-color:#f5f5f5;overflow: hidden;">
	  			<table id="table_th" class="gridtable">
	  					<tr>
							<th width="50%">
								<span class="comSpan">柜体类型</span>
							</th>
							<th width="50%">
								<span class="comSpan">操作</span>
							</th>
						</tr>
	  			</table>
	  			</div>
				
				<div style="width: 300px; height: 370px; overflow: auto">
					<table id="table_tr" class="gridtable">

						
						<s:iterator value="list" var="cabType" status="status">
							<s:if test="#status.count%2==0">
					<tr bgcolor="#F2F2F2" onmouseout="javascript:this.bgColor='#F2F2F2'"
 onmouseover="javascript:this.bgColor='#f5fafe'" >
				</s:if>
				<s:else>
					<tr onmouseout="javascript:this.bgColor='#ffffff'"
 onmouseover="javascript:this.bgColor='#f5fafe'">
				</s:else>
								<td width="50%">
									<s:property value="#cabType.value" />
								</td>
								<td width="50%">
									<a
							href="#" onclick="CabType.showCabType(${id});return false;" />修改</a>
						<a
							href='deleteCabType.action?cabType.id=${id}'
							onclick="return del()">删除</a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
		</div>
		</div>
		</div>
		<script>
			var table_tr = document.getElementById('table_tr');
			$('#table_th').width(table_tr.scrollWidth);
			$(window).resize(function() {
				var table_tr1 = document.getElementById('table_tr');
				$('#table_th').width(table_tr1.scrollWidth-1);
			});
							
		</script>
		<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none;width:240px;height:120px;">
				<h2>操作<a class="btnClose" onclick="CabType.closePage();">关闭</a></h2>
				<div id="addCabType_dialogDiv">
    	    		<jsp:include page="/system/add_cab_type.jsp"></jsp:include>
    	    	</div>
    	    	<div id="userCabType_dialogDiv">
    	    		<jsp:include page="/system/show_cab_type.jsp"></jsp:include>
    	    	</div>
			</div>
		</div>
	</body>
</html>
