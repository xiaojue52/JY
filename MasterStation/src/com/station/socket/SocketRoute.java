package com.station.socket;

import java.net.Socket;
import javax.servlet.ServletContextEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.station.constant.Constant;
import com.station.service.JYChartDataService;
import com.station.service.JYSocketService;
import com.station.service.impl.JYCabinetServiceImpl;
import com.station.system.action.MonitorTimeAction;

public class SocketRoute {
	private JYSocketService socketService;
	private SocketHandler socketHandler;
	public SocketRoute(ServletContextEvent sce) {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		socketService = (JYSocketService) applicationContext
				.getBean("jySocketService");
		JYChartDataService chartDataService = (JYChartDataService) applicationContext
				.getBean("jyChartDataService");;
		socketHandler = new SocketHandler(socketService,chartDataService);
		SocketAction.setSocketRoute(this);
		MonitorTimeAction.setSocketRoute(this);
		JYCabinetServiceImpl.setSocketRoute(this);
	}

	public void sendCommandToGetTempWithCabNumberList(String[] cabNumberList){
		this.socketHandler.sendCommandToGetTempWithCabNumberList(cabNumberList);
	}
	public void sendCommandToSetMonitorTime(){
		this.socketHandler.sendCommandToSetMonitorTime();
	}
	public String CheckString(String str, Socket client) {
		if (str == null || str.length() < 7
				|| !str.substring(str.length() - 2).equals("CR"))
			return Constant.CODEERROR;
		String command[] = str.split("[|]");
		String cabNumber;
		if(command.length>=2){
			cabNumber = command[1].substring(0,5);
		}
		else
			return Constant.CODEERROR;
		String orderStr = str.substring(0, 2);
		// Mes = "0000000|0000000|00000XXCR";
		// Ret = "0100000|0000000|0XXCR";
		if (orderStr.equals("00")) {// 登陆
			return socketHandler.parseLogin(str, client);
		}
		// Mes = "1000000|0000000|XXCR"
		// Ret = "1100000|0000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|XXCR"
		if (orderStr.equals("11")) {// 实时招测
			if (this.socketHandler.isLogined(cabNumber,client))
				return socketHandler.parseRealTempData(str, client);
		}
		// Mes = "2000000|0000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|XXCR";
		// Ret = "2100000|0000000|0XXCR";
		if (orderStr.equals("20")) {// 温度
			if (this.socketHandler.isLogined(cabNumber,client))
				return socketHandler.parseTempData(str, client);
		}
		// Mes = "3000000|0000000|10XXCR";
		// Ret = "3100000|0000000|0XXCR";
		if (orderStr.equals("31")) {// 上传周期
			if (this.socketHandler.isLogined(cabNumber,client))
				return socketHandler.parseMonitorTime(str, client);
		}
		// Mes = "4000000|0000000|XXCR";
		// Ret = "4100000|0000000|0XXCR";
		if (orderStr.equals("40")) {// 心跳
			if (this.socketHandler.isLogined(cabNumber,client))
				return socketHandler.parseHeartBeat(str, client);
		}
		
		return Constant.CODEERROR;
	}
	public void removedCabinet(String cabNumber){
		this.socketHandler.removeOrderMap(cabNumber);
	}
	public void removedSocket(Socket socket){
		this.socketHandler.removedSocket(socket);
	}
	
	public void stop() {
		this.socketHandler.stop();
	}
}
