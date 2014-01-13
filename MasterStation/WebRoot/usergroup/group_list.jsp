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
		<link rel="stylesheet" type="text/css" href="css/alert-group-page.css" />
		<script type="text/javascript" src="js/user-group-page.js"></script>
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
	<form action="listUserGroups.action" method="post">
		<div class="toolbar">
			
				<span> 班组名称：
				<s:if test="groupName == \"%\"||groupName==null">
				<input name='groupName' type="text" /> 
				</s:if>
				<s:else>
				<input name='groupName' type="text" value="<s:property value="groupName"/>"/> 
				</s:else>
				</span>
				<span>班组负责人：
				<s:if test="leaderName == \"%\"||leaderName==null">
				<input name='leaderName' type="text" /> 
				</s:if>
				<s:else>
				<input name='leaderName' type="text" value="<s:property value="leaderName"/>"/> 
				</s:else>
				</span>
				<span><input type="submit" value="查询" class="toolbarButton"/> </span>
				<span><button id="btnShow" class="toolbarButton" onclick="return false;">添加班组</button></span>
		
		</div>
		<div class="center_table_div">
		<s:if test="ret==-1">
		<div><span class="errorMessage">班组名冲突！</span></div>
		</s:if>
		<s:if test="ret==-2">
		<div><span class="errorMessage">删除班组失败！请删除班组下面的用户、解除相关设备！</span></div>
		</s:if>
<div
		style="min-width:900px;width: 100%; height: 460px; margin-top: 0px;">
		<div class="datagrid-container datagrid-container-border"
			id="datagrid_89353"
			style="position: relative; overflow: hidden; width: 100%; height: 460px;">
	  <div style="background-color:#f5f5f5;overflow: hidden;">
	  <table id="table_th" class="gridtable">
	  			<tr>
				<th width="10%">
					<span>序号</span>
				</th>
				<th width="15%">
					<span>班组名称</span>
				</th>
				<th width="15%">
					<span>班组负责人</span>
				</th>
				<th width="15%">
					<span>备注</span>
				</th>
				<th width="15%">
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
					<td width="10%">
						<s:property value="#status.count+(pageList*(page-1))"/>
					</td>
					<td width="15%">
						<s:property value="groupName" />
					</td>
					<td width="15%">
						<s:property value="leaderName" />
					</td>
					<td width="15%">
						<s:property value="note" />
					</td>
					<td width="15%">
						<a
							href="#" onclick="getUserGroupDetails(${id}); return false;" />修改</a>
						<a
							href='deleteUserGroup.action?userGroup.id=${id}'
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
	</div>
</form>	
		<div id="BgDiv"></div>
			<div id="DialogDiv" style="display:none;width:300px;height:260px;">
				<h2>操作<a class="btnClose">关闭</a></h2>
				<div id="addUser_dialogDiv">
    	    		<jsp:include page="/usergroup/add_group.jsp"></jsp:include>
    	    	</div>
    	    	<div id="userDetails_dialogDiv">
    	    		<jsp:include page="/usergroup/group_details.jsp"></jsp:include>
    	    	</div>
			</div>
				
	</body>
</html>
