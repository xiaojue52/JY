<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="monitor_div" class="panelDiv">
	<input type="button" onclick="setSrc('mainAction.action');"
		value="系统监控" class="itemDiv" />
</div>
<div id="user_div" class="panelDiv">
	<input type="button" onclick="setSrc('listUsers.action');"
		value="用户管理" class="itemDiv" />
</div>
<div id="system_div" class="panelDiv">
	<input type="button" onclick="setSrc('system/system_tree.jsp');"
		class="itemDiv" value="设备管理" />
	<input type="button" onclick="setSrc('system/conf.jsp');"
		class="itemDiv" value="系统参数设置" />
</div>
<div id="history_div" class="panelDiv">
	<input type="button"
		onclick="setSrc('unhandledException.action');" class="itemDiv"
		value="报警记录" />

	<input type="button" onclick="setSrc('listHistory.action');"
		class="itemDiv" value="历史记录" />
</div>
<div id="data_div" class="panelDiv">
	<input type="button" onclick='setSrc("listHistory.action");'
		class="itemDiv" value="数据对比" />
</div>