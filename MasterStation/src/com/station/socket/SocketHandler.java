package com.station.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.station.constant.Constant;
import com.station.po.JYAlarm;
import com.station.po.JYCabinet;
import com.station.service.JYTimerTaskService;
import com.station.service.JYSocketService;
import com.station.timer.TimerEvent;
/*
 * 处理socket相关命令，负责解析
 */
public class SocketHandler {
	private JYSocketService socketService;
	private List<String> realCabList;
	private Map<String, Socket> clientMap = new HashMap<String, Socket>();//当前连接的设备
	private Map<String, Map<String, String>> orderMap = new HashMap<String, Map<String, String>>();//主站注册的设备
	private TimerEvent halfHourEvent;
	private LoopCheckThread checkThread;
	/*
	 * 构造函数
	 * 实例化的时候将所有的柜体加入到内存中，以便一一对应
	 */
	public SocketHandler(JYSocketService socketService,JYTimerTaskService chartDataService){
		this.socketService = socketService;
		
		String hql = "from JYCabinet cabinet where cabinet.tag = 1 and cabinet.status = 1";
		List<JYCabinet> list = this.socketService.findCabinetsByHql(hql);
		for (int i =0;i<list.size();i++){
			Map<String, String> order = new HashMap<String, String>();
			order.put("heartBeat", Constant.getCurrentDateStr());
			//order.put("reviceTemp", Constant.getCurrentDateStr());
			order.put("monitorTimeOK", "0");
			orderMap.put(list.get(i).getCabId(), order);
		}
		checkThread = new LoopCheckThread(orderMap, socketService);
		checkThread.start();
		halfHourEvent = new TimerEvent(chartDataService);
		halfHourEvent.startTimer();
	}
	/*
	 * 保存socket连接，保存柜体编号
	 */
	public String parseLogin(String str, Socket client) {
		// Mes = "0000000|#000000|1395476582|XCR";
		// Ret = "0100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 4 && command[0].length() == 7) {
			if (command[0].equals("0000000")) {
				String cabId = command[1];
				
				if(!socketService.cabinetIsExist(cabId)){
					this.sendCommand(Constant.NOCABINET, client);
					return null;
				}
				this.init(cabId, client);
				String tempStr = "0100000|" + command[1] + "|0XCR";
				this.sendCommand(tempStr, client);
				return null;
			}
		}
		String tempStr = "0100000|" + command[1] + "|1XCR";
		this.sendCommand(tempStr, client);
		return null;
	}
	/*
	 * 解析实时温度
	 */
	public String parseRealTempData(String str, Socket client) {
		// Mes = "1000000|#000000|XCR"
		// Ret = "1100000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|0XCR"
		String command[] = str.split("[|]");
		if (command != null && command.length == 5 && command[0].length() == 7
				&& command[2].length() == 14) {
			String cabId = command[1];
			String dateStr = command[2];
			String tempData = command[3];
			
			if (realCabList==null){
				this.sendCommand(Constant.REALTEMPERROR, client);
				return null;
			}
			realCabList.add(cabId);
			this.setTempValue(cabId, tempData, dateStr);
			return null;
		}
		this.sendCommand(Constant.REALTEMPERROR, client);
		return  null;
	}
	/*
	 * 解析上传温度
	 */
	public String parseTempData(String str, Socket client) {
		// Mes = "2000000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|XCR";
		// Ret = "2100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 5 && command[0].length() == 7
				 && command[2].length() == 14) {
			String cabId = command[1];
			String dateStr = command[2];
			Map<String, String> order = orderMap.get(cabId);
			order.put("reviceTemp", dateStr);
			orderMap.put(cabId, order);
			String tempData = command[3];
			this.setTempValue(cabId, tempData, dateStr);
			String tempStr = "2100000|" + command[1] + "|0XCR";

			this.sendCommand(tempStr, client);
			return null;
		}
		String tempStr = "2100000|" + command[1] + "|1XCR";
		this.sendCommand(tempStr, client);
		return null;
	}
	/*
	 * 解析上传时间间隔
	 */
	public String parseMonitorTime(String str,Socket client){
		// Mes = "3000000|#000000|10XCR";
		// Ret = "3100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 3 && command[0].length() == 7) {
			String cabId = command[1];
			Map<String, String> order = this.orderMap.get(cabId);
			if (order!=null){
				order.put("monitorTimeOK", "1");
				return null;
			}
			
		}
		String tempStr = Constant.MONITORTIMEERROR;
		this.sendCommand(tempStr, client);
		return null;
	}
	/*
	 * 解析心跳时间
	 * 将收到指令时间存储到orderMap,以便于循环和当前时间对比，看是否超过设定超时时间
	 */
	public String parseHeartBeat(String str, Socket client) {
		// Mes = "4000000|#000000|XCR";
		// Ret = "4100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 3 && command[0].length() == 7) {
			this.storeHeartBertOrder(command[1].substring(0, 5));
			String tempStr = "4100000|" + command[1] + "|0XCR";
			this.sendCommand(tempStr, client);
			return null;
		}
		String tempStr = "4100000|" + command[1] + "|1XCR";
		this.sendCommand(tempStr, client);
		return null;
	}
	
	public String parseDeviceError(String str, Socket client) {
		// Mes = "5000000|#000000|XCR";
		// Ret = "5100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 3 && command[0].length() == 7) {
			this.storeHeartBertOrder(command[1].substring(0, 5));
			String tempStr = "5100000|" + command[1] + "|0XCR";
			String cabId = command[1];
			Date date = new Date();
			this.socketService.saveAlarm(cabId, JYAlarm.DEVICEREEOR, date, "故障");
			this.sendCommand(tempStr, client);
			return null;
		}
		String tempStr = "5100000|" + command[1] + "|1XCR";
		this.sendCommand(tempStr, client);
		return null;
	}

	private void storeHeartBertOrder(String cabId) {

		Map<String, String> order = orderMap.get(cabId);
		order.put("heartBeat", Constant.getCurrentDateStr());
		orderMap.put(cabId, order);
		this.socketService.updateCabinetStatus(cabId);
	}
	/*
	 * 设置上传时间
	 */
	public void sendCommandToSetMonitorTime(String type,String value){
		// Mes = "3000000|#000000|10XCR";
		// Ret = "3100000|#000000|0XCR";
		
		Iterator<Map.Entry<String, Map<String, String>>> iter = orderMap.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, Map<String, String>> mEntry = (Map.Entry<String, Map<String, String>>)iter.next();
			Map<String, String> order = (Map<String, String>)mEntry.getValue();
			String cabId = (String)mEntry.getKey();
			String mTime = value;
			if (mTime.length()==1){
				mTime ="0"+mTime;
			}
			Socket client = (Socket)clientMap.get(cabId);
			if (client==null)continue;
			order.put("monitorTimeOK", "0");

			String tempStr = "3000000|" + cabId + "|"+mTime+"XCR";
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.sendCommand(tempStr, client);
		}
	}
	/*
	 * 读取实时温度
	 */
	public List<String> sendCommandToGetTempWithCabIdList(String[] cabIdList) {
		// Mes = "1000000|#000000|XCR"
		// Ret = "1100000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|0XCR"
		realCabList = new ArrayList<String>();
		List<String> list =  Arrays.asList(cabIdList);

		for (int i = 0; i < list.size(); i++) {
			String cabId = list.get(i);
			Socket client = clientMap.get(cabId);
			String queryStr = "1000000|" + cabId + "|XCR";
			this.sendCommand(queryStr, client);
		}
		int delay = 0;
		while(true){
			try {	
				Thread.sleep(500);
				if(realCabList.size()==list.size())return null;
				else if(delay==10){
					for (int i=0;i<list.size();i++){
						int j=0;
						while(realCabList.size()>0){
							if (list.get(i).equals(realCabList.get(j))){
								list.remove(i);
								realCabList.remove(j);
								j--;
								i--;
							}
							j++;
						}
					}
					for (int i=0;i<list.size();i++){
						Date date = new Date();
						this.socketService.saveAlarm(list.get(i), JYAlarm.DEVICEREEOR, date, "离线");
						
					}
					return list;
				}
				delay++;			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void init(String cabId,Socket client){
		clientMap.put(cabId, client);
		this.addOrderMap(cabId,client);
		socketService.updateCabinetStatus(cabId);
	}
	public void checkInit(String cabId,Socket client){
		Map<String, String> order = orderMap.get(cabId);
		if (order == null) 
			return;
		else{
			String monitorTimeOK = order.get("monitorTimeOK");
			if (monitorTimeOK.equals("0")){
				JYCabinet cabinet = this.socketService.getCabinet(cabId);
				if (cabinet==null)return;
				String mTime = cabinet.getCabType().getSubValue();
				if (mTime.length()==1){
					mTime ="0"+mTime;
				}
				String queryStr = "3000000|" + cabId + "|"+mTime+"XCR";
				this.sendCommand(queryStr, client);
			}
		}
			
	}
	private void setTempValue(String cabId, String arg0, String dateStr) {

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

					vL.add(Float.parseFloat(v1));
					vL.add(Float.parseFloat(v2));
					vL.add(Float.parseFloat(v3));
					vL.add(Float.parseFloat(v4));

					map.put(positionNumber, vL);
				}
			}
			socketService.saveDate(cabId, map, dateStr);
			this.socketService.updateCabinetStatus(cabId);
		}
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
				//Date d2 = new Date();
				//this.socketService.saveAlarm(key, 1, d2, "离线");
				System.out.print("移除socket！");
				// ParseSocketData.deviceOffline(key);
			}
		}
	}
	private void addOrderMap(String cabId,Socket client) {
		Map<String, String> order = orderMap.get(cabId);
		if (order == null) {
			return;
		}
		order.put("heartBeat", Constant.getCurrentDateStr());
		//order.put("reviceTemp", Constant.getCurrentDateStr());
		order.put("monitorTimeOK", "0");
		JYCabinet cabinet = this.socketService.getCabinet(cabId);
		if (cabinet==null)return;
		String mTime = cabinet.getCabType().getSubValue();
		if (mTime.length()==1){
			mTime = "0"+mTime;
		}
		String queryStr = "3000000|" + cabId + "|"+mTime+"XCR";
		this.sendCommand(queryStr, client);
		orderMap.put(cabId, order);
	}
	public void removeOrderMap(String cabId) {
		orderMap.remove(cabId);
		clientMap.remove(cabId);
	}
	public boolean isLogined(String cabId,Socket client){
		if(orderMap.get(cabId)!=null){
			clientMap.put(cabId, client);//如有则替换
			return true;
		}	
		else 
			return false;
	}
	
	public void stop(){
		this.checkThread.stopCheck();
		this.halfHourEvent.stopTimer();
	}
	public void sendCommand(String str,Socket client){
		if (client==null)return;
		PrintWriter out = null;
		try {
			out = new PrintWriter(client.getOutputStream());
			out.print(str);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addCabinet(String cabId){
		Map<String, String> order = new HashMap<String, String>();
		order.put("heartBeat", Constant.getCurrentDateStr());
		//order.put("reviceTemp", Constant.getCurrentDateStr());
		order.put("monitorTimeOK", "0");
		orderMap.put(cabId, order);
	}
}
