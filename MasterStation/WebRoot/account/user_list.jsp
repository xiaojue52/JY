<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<base href="<%=basePath%>">
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="css/table/src/css/extgrid.v.1.2.5.all.css" />
		<link rel="stylesheet" type="text/css" href="css/toolbar.css" />
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		<link rel="stylesheet" type="text/css" href="css/alert-page.css" />
		<script type="text/javascript" src="js/userpage.js"></script>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<script type="text/javascript" src="js/control.js"></script>
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
	<form action="listUser.action" method="post">
		<div class="toolbar">
				<span> 用户名：
				<s:if test="username == \"%\"||username==null">
				<input name='username' type="text" /> 
				</s:if>
				<s:else>
				<input name='username' type="text" value="<s:property value="username"/>"/> 
				</s:else>
				</span>
				<span>所在单位：
				<s:if test="company == \"%\"||company==null">
				<input name='company' type="text" /> 
				</s:if>
				<s:else>
				<input name='company' type="text" value="<s:property value="company"/>"/> 
				</s:else>
				</span>
				<span><input type="submit" value="查询" class="toolbarButton"/> </span>
				<span><button id="btnShow" class="toolbarButton" onclick="return false;">添加用户</button></span>
			
		</div>
		<div class="center_table_div">
		<s:if test="ret==-1">
		<div><span class="errorMessage">用户名冲突，用户已存在请改名！</span></div>
		</s:if>
		<s:if test="ret==-2">
		<div><span class="errorMessage">删除用户失败！请解除用户拥有的柜体！</span></div>
		</s:if>
		<div
		style="min-width:900px;width: 100%; height: 460px; margin-top: 0px;">
		
		<div class="datagrid-container datagrid-container-border"
			id="datagrid_89353"
			style="position: relative; overflow: hidden; width: 100%; height: 460px;">
	  <div style="background-color:#f5f5f5;overflow: hidden;">
	  <table id="table_th" class="gridtable">
	  			<tr>
				<th width="5%">
					<span class="comSpan" onclick="Control.orderByColumn('listUser.action','user.userId')">编号</span>
				</th>
				<th width="10%">
					<span class="comSpan" onclick="Control.orderByColumn('listUser.action','user.username')">用户名</span>
				</th>
				<th width="15%">
					<span class="comSpan" onclick="Control.orderByColumn('listUser.action','user.contact')">手机号码</span>
				</th>
				<th width="15%">
					<span class="comSpan" onclick="Control.orderByColumn('listUser.action','user.company')">所在单位</span>
				</th>
				<th width="10%">
					<span class="comSpan" onclick="Control.orderByColumn('listUser.action','user.userGroup')">所属班组</span>
				</th>
				<th width="10%">
					<span class="comSpan" onclick="Control.orderByColumn('listUser.action','user.userLevel')">系统角色</span>
				</th>
				<th width="15%">
					<span class="comSpan" onclick="Control.orderByColumn('listUser.action','user.jobLevel')">职务级别</span>
				</th>
				<th width="10%">
					<span>短信接收</span>
				</th>
				<th width="10%">
					<span>操作</span>
				</th>
			</tr>
	  </table>
	  </div>
	  <div id='table_div' style="width:100%;height:400px;overflow: auto;">
	  <table id="table_tr" class="gridtable">
			<s:iterator value="pageBean.list" var="account" status="status">
				<s:if test="#status.count%2==0">
					<tr bgcolor="#F2F2F2" onmouseout="javascript:this.bgColor='#F2F2F2'"
 onmouseover="javascript:this.bgColor='#f5fafe'" >
				</s:if>
				<s:else>
					<tr onmouseout="javascript:this.bgColor='#ffffff'"
 onmouseover="javascript:this.bgColor='#f5fafe'">
				</s:else>
					<td width="5%">
						<s:property value="userId" />
					</td>
					<td width="10%">
						<s:property value="username" />
					</td>
					<td width="15%">
						<s:property value="contact" />
					</td>
					<td width="15%">
						<s:property value="company" />
					</td>
					<td width="10%">
						<s:property value="userGroup.groupName" />
					</td>
					<td width="10%">
						<s:if test="%{#account.userLevel == 'user'}">
							<span>普通用户</span>
						</s:if>
						<s:else>
							<span>普通管理员</span>
						</s:else>
					</td>
					<td width="15%">
						<s:property value="jobLevel" />
					</td>
					<td width="10%">
						<s:if test="canRecMes == 1">
							<span>是</span>
						</s:if>
						<s:else>
							<span>否</span>
						</s:else>
					</td>
					<td width="10%">
						<a
							href="#" onclick="getUserDetails('<s:property value="userId" />');return false" />修改</a>
						<a
							href='deleteUser.action?user.userId=<s:property value="userId" />'
							onclick="return del()">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table></div>
			<%@ include file="/common/pagebean.jsp"%>
			</div>
		
		<script>
			var table_tr = document.getElementById('table_tr');
			$('#table_th').width(table_tr.scrollWidth);
			$(window).resize(function() {
				var table_tr1 = document.getElementById('table_tr');
				$('#table_th').width(table_tr1.scrollWidth-1);
			});
							
		</script>
		
	</div>		

		<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none;width:300px;height:410px;">
				<h2>操作<a class="btnClose">关闭</a></h2>
				<div id="addUser_dialogDiv">
    	    		<jsp:include page="/account/add_user.jsp"></jsp:include>
    	    	</div>
    	    	<div id="userDetails_dialogDiv">
    	    		<jsp:include page="/account/user_details.jsp"></jsp:include>
    	    	</div>
			</div>
	</div>
	</form>	
	</body>
</html>
