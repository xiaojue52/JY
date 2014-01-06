package com.station.socket;

import java.net.Socket;
import java.util.List;

import javax.servlet.ServletContextEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.station.constant.Constant;
import com.station.service.JYTimerTaskService;
import com.station.service.JYSocketService;
import com.station.service.impl.JYCabinetServiceImpl;
import com.station.system.action.MonitorTimeAction;
/*
 * 负责socket转发，不同的命令调用不同的函数
 */
public class SocketRoute {
	private JYSocketService socketService;
	private SocketHandler socketHandler;
	/*
	 * 构造函数
	 * 将socket实例给需要的类
	 */
	public SocketRoute(ServletContextEvent sce) {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		socketService = (JYSocketService) applicationContext
				.getBean("jySocketService");
		JYTimerTaskService chartDataService = (JYTimerTaskService) applicationContext
				.getBean("jyTimerTaskService");;
		socketHandler = new SocketHandler(socketService,chartDataService);
		
		SocketAction.setSocketRoute(this);
		MonitorTimeAction.setSocketRoute(this);
		JYCabinetServiceImpl.setSocketRoute(this);
	}
	/*
	 * 获取选择柜体的温度
	 */
	public List<String> sendCommandToGetTempWithCabIdList(String[] cabIdList){
		return this.socketHandler.sendCommandToGetTempWithCabIdList(cabIdList);
	}
	/*
	 * 设置温度上传时间间隔（按类型）
	 */
	public void sendCommandToSetMonitorTime(String type,String value){
		this.socketHandler.sendCommandToSetMonitorTime(type,value);
	}
	/*
	 * 分发命令去解析
	 */
	public String CheckString(String str, Socket client) {
		if (str == null || str.length() < 7
				|| !str.substring(str.length() - 2).equals("CR"))
			{
				String tempStr = Constant.CODEERROR;
				this.socketHandler.sendCommand(tempStr, client);
				return null;
			}
		String command[] = str.split("[|]");
		String cabId;
		if(command.length>=2){
			cabId = command[1];
		}
		else{
			String tempStr = Constant.CODEERROR;
			this.socketHandler.sendCommand(tempStr, client);
			return null;
		}
			
		String orderStr = str.substring(0, 2);
		
		
		// Mes = "0000000|#000000|18655087654XCR";
		// Ret = "0100000|#000000|0XCR";
		if (orderStr.equals("00")) {// 登陆
			return socketHandler.parseLogin(str, client);
		}
		// Mes = "1000000|#000000|XCR"
		// Ret = "1100000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|0XCR"
		if (orderStr.equals("11")) {// 实时招测
			if (this.socketHandler.isExist(cabId,client))
				return socketHandler.parseRealTempData(str, client);
		}
		// Mes = "2000000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|XCR";
		// Ret = "2100000|#000000|0XCR";
		if (orderStr.equals("20")) {// 温度
			if (this.socketHandler.isExist(cabId,client))
				return socketHandler.parseTempData(str, client);
		}
		// Mes = "3000000|#000000|10XCR";
		// Ret = "3100000|#000000|0XCR";
		if (orderStr.equals("31")) {// 上传周期
			if (this.socketHandler.isExist(cabId,client))
				return socketHandler.parseMonitorTime(str, client);
		}
		// Mes = "4000000|#000000|XCR";
		// Ret = "4100000|#000000|0XCR";
		if (orderStr.equals("40")) {// 心跳
			if (this.socketHandler.isExist(cabId,client))
				return socketHandler.parseHeartBeat(str, client);
		}
		// Mes = "5000000|#000000|XCR";
		// Ret = "5100000|#000000|0XCR";
		if (orderStr.equals("50")) {// 上传故障
			if (this.socketHandler.isExist(cabId,client))
				return socketHandler.parseDeviceError(str, client);
		}
		// Mes = "3000000|#000000|XCR";
		// Ret = "3100000|#000000|10|0XCR";
		if (orderStr.equals("30")) {
			this.socketHandler.parseMonitorTimeSetting(str,client);
		}
		String tempStr = Constant.CODEERROR;
		this.socketHandler.sendCommand(tempStr, client);
		return null;
	}
	public void removedCabinet(String cabId){
		this.socketHandler.removeOrderMap(cabId);
	}
	public void addCabinet(String cabId){
		this.socketHandler.addCabinet(cabId);
	}
	public void removedSocket(Socket socket){
		this.socketHandler.removedSocket(socket);
	}
	
	public void stop() {
		this.socketHandler.stop();
	}
}
