package com.station.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.station.constant.Constant;
import com.station.service.JYChartDataService;
import com.station.service.JYSocketService;
import com.station.timer.HalfHourEvent;

public class SocketHandler {
	public SocketHandler(ServletContextEvent sce) {
		this.sce = sce;
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.sce.getServletContext());
		socketService = (JYSocketService) applicationContext
				.getBean("jySocketService");
		JYChartDataService chartDataService = (JYChartDataService) applicationContext
				.getBean("jyChartDataService");;
		checkThread = new LoopCheckThread(orderMap, socketService);
		checkThread.start();
		halfHourEvent = new HalfHourEvent(chartDataService);
		halfHourEvent.startTimer();

		SocketAction.setSocketHandler(this);
	}
	private HalfHourEvent halfHourEvent;
	private LoopCheckThread checkThread;
	private ServletContextEvent sce;
	public Map<String, Socket> clientMap = new HashMap<String, Socket>();
	public Map<String, Map<String, String>> orderMap = new HashMap<String, Map<String, String>>();
	private JYSocketService socketService;
	private List<String> realCabList;

	public String CheckString(String str, Socket client) {
		if (str == null || str.length() < 7
				|| !str.substring(str.length() - 2).equals("CR"))
			return "-2";
		String command[] = str.split("[|]");
		String cabNumber;
		if(command.length>=2){
			cabNumber = command[1].substring(0,5);
		}
		else
			return "-2";
		String orderStr = str.substring(0, 2);
		// loginMes = "0000000|0000000|000000CR";
		if (orderStr.equals("00")) {// 登陆
			return parseLogin(str, client);
		}
		if (orderStr.equals("40")) {// 心跳
			if (this.isLogined(cabNumber,client))
				return this.parseHeartBeat(str, client);
		}
		if (orderStr.equals("11")) {// 实时招测
			if (this.isLogined(cabNumber,client))
				return this.parseRealTempData(str, client);
		}
		if (orderStr.equals("20")) {// 温度
			if (this.isLogined(cabNumber,client))
				return this.parseTempData(str, client);
		}
		return "-2";
	}

	private String parseRealTempData(String str, Socket client) {
		// Mes =
		// "1100000|0000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432XXCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 4 && command[0].length() == 7
				&& command[1].length() == 7 && command[2].length() == 14) {
			String cabNumber = command[1].substring(0, 5);
			String dateStr = command[2];
			String tempData = command[3].substring(0, command[3].length() - 4);
			this.setTempValue(cabNumber, tempData, dateStr);
			if (realCabList==null)return "1";
			realCabList.add(cabNumber);
			//isCollecting = false;
		}
		return "1";
	}

	private String parseTempData(String str, Socket client) {
		// Mes =
		// "2000000|0000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432XXCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 4 && command[0].length() == 7
				&& command[1].length() == 7 && command[2].length() == 14) {
			String cabNumber = command[1].substring(0, 5);
			String dateStr = command[2];
			Map<String, String> order = orderMap.get(cabNumber);
			if (order == null) {
				return "-2";
			}
			order.put("reviceTemp", dateStr);
			orderMap.put(cabNumber, order);
			String tempData = command[3].substring(0, command[3].length() - 4);
			this.setTempValue(cabNumber, tempData, dateStr);
			return "2100000|" + command[1] + "|0XXCR";
		}
		return "-2";
	}

	private String parseHeartBeat(String str, Socket client) {
		// Mes = "4000000|0000000XXCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 2 && command[0].length() == 7) {
			storeHeartBertOrder(command[1].substring(0, 5));
			return "4100000" + command[1].substring(0, 7) + "|0XXCR";
		}
		return "-2";
	}

	private void storeHeartBertOrder(String cabNumber) {
		// Date date = new Date();
		Map<String, String> order = orderMap.get(cabNumber);
		if (order == null) {
			return;
		}
		order.put("heartBeat", Constant.getCurrentDateStr());
		orderMap.put(cabNumber, order);
		this.socketService.updateCabinetStatus(cabNumber);
	}

	private String parseLogin(String str, Socket client) {
		// loginMes = "0000000|0000000|000000CR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 3 && command[0].length() == 7
				&& command[1].length() == 7) {
			if (command[0].equals("0000000")) {
				String cabNumber = command[1].substring(0, 5);
				clientMap.put(cabNumber, client);
				createOrderMap(cabNumber);
				socketService.updateCabinetStatus(cabNumber);
				return "0000000|" + command[1] + "|000000CR";
			}
		}
		return "-2";
	}

	private void createOrderMap(String cabNumber) {
		Map<String, String> order = orderMap.get(cabNumber);
		if (order == null) {
			order = new HashMap<String, String>();
		}
		order.put("heartBeat", Constant.getCurrentDateStr());
		order.put("reviceTemp", Constant.getCurrentDateStr());
		orderMap.put(cabNumber, order);
	}

	public void removedSocket(Socket socket) {
		Iterator<Map.Entry<String, Socket>> iter = clientMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Socket> mEntry = (Map.Entry<String, Socket>) iter
					.next();
			Socket temp = (Socket) mEntry.getValue();
			String key = (String) mEntry.getKey();
			if (temp == socket) {
				clientMap.remove(key);
				Date d2 = new Date();
				this.socketService.saveAlarm(key, 1, d2, "离线");
				System.out.print("移除socket！");
				// ParseSocketData.deviceOffline(key);
			}
		}
	}

	public void sendCommandWithCabNumberList(String[] cabNumberList) {
		realCabList = new ArrayList<String>();
		// Mes = "1000000|0000000|XXCR"
		// ret =
		// "1100000|0000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432XXCR"
		for (int i = 0; i < cabNumberList.length; i++) {
			String cabNumber = cabNumberList[i];
			PrintWriter out = null;
			Socket client = clientMap.get(cabNumber);
			if (client != null) {
				try {
					out = new PrintWriter(client.getOutputStream());
					String queryStr = "1000000|" + cabNumber + "00|XXCR";
					out.print(queryStr);
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		int delay = 0;
		while(true){
			try {	
				Thread.sleep(500);
				if(realCabList.size()==cabNumberList.length)return;
				else if(delay==10)return;
				delay++;			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setTempValue(String cabNumber, String arg0, String dateStr) {
		// Mes =
		// "2000000|0000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432XXCR";
		Map<Integer, List<Float>> map = new HashMap<Integer, List<Float>>();
		String tempList[] = arg0.split("[*]");
		if (tempList != null && tempList.length > 0) {

			for (int i = 0; i < tempList.length; i++) {
				if (tempList[i].length() == 24) {
					List<Float> vL = new ArrayList<Float>();
					int positionNumber = Integer.valueOf(tempList[i].substring(
							0, 4));
					String v1 = tempList[i].substring(4, 9);
					String vt = v1.substring(4);
					v1 = v1.substring(0, 4) + "." + vt;
					String v2 = tempList[i].substring(9, 14);
					vt = v2.substring(4);
					v2 = v2.substring(0, 4) + "." + vt;

					String v3 = tempList[i].substring(14, 19);
					vt = v3.substring(4);
					v3 = v3.substring(0, 4) + "." + vt;

					String v4 = tempList[i].substring(19, 24);
					vt = v4.substring(4);
					v4 = v4.substring(0, 4) + "." + vt;

					System.out.print("v1" + v1 + ":v2" + v2 + ":v3" + v3
							+ ":v4" + v4);
					vL.add(Float.parseFloat(v1));
					vL.add(Float.parseFloat(v2));
					vL.add(Float.parseFloat(v3));
					vL.add(Float.parseFloat(v4));

					map.put(positionNumber, vL);
				}
			}
			socketService.saveDate(cabNumber, map, dateStr);
			this.socketService.updateCabinetStatus(cabNumber);
		}
	}

	public void stop() {
		this.checkThread.stopCheck();
		this.halfHourEvent.stopTimer();
	}
	private boolean isLogined(String cabNumber,Socket client){
		if(orderMap.get(cabNumber)!=null){
			clientMap.put(cabNumber, client);
			return true;
		}	
		else 
			return false;
	}
}
