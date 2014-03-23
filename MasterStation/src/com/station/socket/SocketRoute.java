package com.station.socket;

import java.net.Socket;
import java.util.List;

import javax.servlet.ServletContextEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.station.service.JYTimerTaskService;
import com.station.service.JYSocketService;
import com.station.service.impl.JYCabinetServiceImpl;
import com.station.system.action.MonitorTimeAction;
/**
 * 负责socket转发，不同的命令调用不同的函数
 */
public class SocketRoute {
	private JYSocketService socketService;
	private SocketHandler socketHandler;
	private SocketDTUHandle socketDTUHandle;
	/**
	 * 构成函数
	 * @param sce servletContextEvent
	 */
	public SocketRoute(ServletContextEvent sce) {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		socketService = (JYSocketService) applicationContext
				.getBean("jySocketService");
		JYTimerTaskService chartDataService = (JYTimerTaskService) applicationContext
				.getBean("jyTimerTaskService");;
		socketHandler = new SocketHandler(socketService,chartDataService);
		socketDTUHandle = new SocketDTUHandle();
		
		SocketAction.setSocketRoute(this);
		MonitorTimeAction.setSocketRoute(this);
		JYCabinetServiceImpl.setSocketRoute(this);
	}
	/**
	 * 获取实时温度
	 * @param cabIdList 柜体id数组
	 * @return 返回未收到数据的柜体id数组
	 */
	public List<String> sendCommandToGetTempWithCabIdList(String[] cabIdList){
		return this.socketHandler.sendCommandToGetTempWithCabIdList(cabIdList);
	}
	/**
	 * 设置某类柜体的温度上传间隔
	 * @param type 柜体类型
	 * @param value 上传时间间隔
	 */
	public void sendCommandToSetMonitorTime(String type,String value){
		this.socketHandler.sendCommandToSetMonitorTime(type,value);
	}
	/**
	 * 监测命令，分发给相关方法处理
	 * @param str 命令字符串
	 * @param client 当前socket
	 */
	public void CheckString(String str, Socket client) {
		// Mes = "0101CR";
		if (str.equals("010101CR")){
			socketDTUHandle.parseDTUHeart();
			return;
		}
		
		if (str == null || str.length() < 7
				|| !str.substring(str.length() - 2).equals("CR"))
			{
				String tempStr = SocketHandler.CODEERROR;
				this.socketHandler.sendCommand(tempStr, client);
				return;
			}
		String command[] = str.split("[|]");
		String cabId;
		if(command.length>=2){
			cabId = command[1].substring(1);
		}
		else{
			String tempStr = SocketHandler.CODEERROR;
			this.socketHandler.sendCommand(tempStr, client);
			return;
		}
			
		String orderStr = str.substring(0, 2);
		 
		// Mes = "0000000|#000000|18655087654|XCR";
		// Ret = "0100000|#000000|0XCR";
		if (orderStr.equals("00")) {// 登陆
			if (this.socketHandler.isExist(cabId, client)){
				socketHandler.parseLogin(str, client);
				return;
			}
			else
			{
				this.socketHandler.sendCommand(SocketHandler.DEVICENOTEXIST, client);
				return;
			}
		}
		// Mes = "1000000|#000000|XCR"
		// Ret = "1100000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|0XCR"
		if (orderStr.equals("11")) {// 实时招测
			if (this.socketHandler.isLogined(cabId)){
				socketHandler.parseRealTempData(str, client);
				return;
			}else{
				this.socketHandler.sendCommand(SocketHandler.UNLOGINED, client);
				return;
			}
		}
		// Mes = "2000000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|XCR";
		// Ret = "2100000|#000000|0XCR";
		if (orderStr.equals("20")) {// 温度
			if (this.socketHandler.isLogined(cabId)){
				socketHandler.parseTempData(str, client);
				return;
			}
			else
			{
				this.socketHandler.sendCommand(SocketHandler.UNLOGINED, client);
				return;
			}
		}
		
		if (orderStr.equals("22")){
			if (this.socketHandler.isLogined(cabId)){
				socketHandler.parseSetting(str, client);
				return;
			}
			else
			{
				this.socketHandler.sendCommand(SocketHandler.UNLOGINED, client);
				return;
			}
		}
		// Mes = "3000000|#000000|10XCR";
		// Ret = "3100000|#000000|0XCR";
		if (orderStr.equals("33")) {// 上传周期
			if (this.socketHandler.isLogined(cabId)){
				socketHandler.parseReturnMonitorTimeSetting(str, client);
				return;
			}
			else
			{
				this.socketHandler.sendCommand(SocketHandler.UNLOGINED, client);
				return;
			}
		}
		// Mes = "4000000|#000000|XCR";
		// Ret = "4100000|#000000|0XCR";
		/**
		 * 预留暂时不处理
		 */
		/*if (orderStr.equals("40")) {// 心跳
			if (this.socketHandler.isLogined(cabId)){
				socketHandler.parseHeartBeat(str, client);
				return;
			}
			else
			{
				this.socketHandler.sendCommand(Constant.UNLOGINED, client);
				return;
			}
		}*/
		// Mes = "5000000|#000000|XCR";
		// Ret = "5100000|#000000|0XCR";
		if (orderStr.equals("50")) {// 上传故障
			if (this.socketHandler.isLogined(cabId)){
				socketHandler.parseDeviceError(str, client);
				return;
			}
			else
			{
				this.socketHandler.sendCommand(SocketHandler.UNLOGINED, client);
				return;
			}
		}
		// Mes = "3000000|#000000|XCR";
		// Ret = "3100000|#000000|10|0XCR";
		if (orderStr.equals("30")) {
			if (this.socketHandler.isLogined(cabId)){
				this.socketHandler.parseMonitorTimeSetting(str,client);
				return;
			}
			else
			{
				this.socketHandler.sendCommand(SocketHandler.UNLOGINED, client);
				return;
			}
		}
		String tempStr = SocketHandler.CODEERROR;
		this.socketHandler.sendCommand(tempStr, client);
	}
	/**
	 * 删除内存中柜体信息
	 * @param cabId
	 */
	public void removedCabinet(String cabId){
		this.socketHandler.removeOrderMap(cabId);
	}
	/**
	 * 向内存中添加柜体信息
	 * @param cabId
	 */
	public void addCabinet(String cabId){
		this.socketHandler.addCabinet(cabId);
	}
	/**
	 * 终端掉线后删除内存中保存的socket记录
	 * @param socket
	 */
	public void removedSocket(Socket socket){
		this.socketHandler.removedSocket(socket);
	}
	
	public void stop() {
		this.socketHandler.stop();
	}
}
