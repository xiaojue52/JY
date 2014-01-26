/**
 * 处理业务相关命令,
 * 心跳数据目前保留,但不处理
 * 
 * 内存中永远存放着启用的柜体信息（对应变量orderMap），每个终端连接上来都会到内存中查看是否存在该终端对应的柜体，
 * 如果存在，就会在内存中保留连接信息（对应变量clientMap）
 * 
 * 当在主站中删除或者不启用柜体，相应的内存中会删除柜体信息和连接信息
 */
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
import com.station.constant.Constant;
import com.station.po.JYAlarm;
import com.station.po.JYCabinet;
import com.station.service.JYTimerTaskService;
import com.station.service.JYSocketService;
import com.station.timer.TimerEvent;

/**
 * 处理socket相关命令，负责解析
 * @author Administrator
 *
 */
public class SocketHandler {
	public static final String NOCABINET = "-4"; 
	public static final String CODEERROR = "-2";
	public static final String REALTEMPERROR = "-3";
	public static final String MONITORTIMEERROR = "-5";
	public static final String DEVICENOTEXIST = "-6";
	public static final String UNLOGINED = "-7";
	public static final String OK = "1";
	
	
	private JYSocketService socketService;
	private Map<String,List<String>> realCabListMap = new HashMap<String,List<String>>();
	private Map<String, Socket> clientMap = new HashMap<String, Socket>();//当前连接的设备
	private Map<String, Map<String, String>> orderMap = new HashMap<String, Map<String, String>>();//主站启用的设备
	private TimerEvent halfHourEvent;
	private LoopCheckThread checkThread;
	/**
	 * 专门用于解析socket通信
	 * @param socketService
	 * @param chartDataService
	 */
	public SocketHandler(JYSocketService socketService,JYTimerTaskService chartDataService){
		this.socketService = socketService;
		
		String hql = "from JYCabinet cabinet where cabinet.tag = 1 and cabinet.status = 1";
		List<JYCabinet> list = this.socketService.findCabinetsByHql(hql);
		for (int i =0;i<list.size();i++){
			this.addCabinet(list.get(i).getCabId());
		}
		checkThread = new LoopCheckThread(orderMap, socketService);
		checkThread.start();
		halfHourEvent = new TimerEvent(chartDataService);
		halfHourEvent.startTimer();
	}
	
	/**
	 * 解析登陆
	 * @param str
	 * @param client
	 * @return
	 */
	public void parseLogin(String str, Socket client) {
		// Mes = "0000000|#000000|1395476582|XCR";
		// Ret = "0100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 4 && command[0].length() == 7) {
			if (command[0].equals("0000000")) {
				String cabId = command[1];
				String phoneNumber = command[2];
				
				this.init(cabId, client,phoneNumber);
				String tempStr = "0100000|" + command[1] + "|0XCR";
				this.sendCommand(tempStr, client);
				return;
			}
		}
		String tempStr = "0100000|" + command[1] + "|1XCR";
		this.sendCommand(tempStr, client);
	}
	/**
	 * 初始化设备信息
	 * @param cabId
	 * @param client
	 * @param phoneNumber
	 */
	private void init(String cabId,Socket client,String phoneNumber){
		Map<String, String> order = orderMap.get(cabId);
		if (order == null) {
			return;
		}
		String prePhoneNumber = order.get("phoneNumber");
		order.put("heartBeat", Constant.getDateStr(new Date(),"yyyyMMddHHmmss"));

		order.put("isLogined", "1");
		order.put("monitorTimeOK", "0");
		order.put("phoneNumber",phoneNumber);
		orderMap.put(cabId, order);
		if(prePhoneNumber!=null&&!prePhoneNumber.equals(phoneNumber)){
			//出现重复的终端
			this.socketService.saveAlarm(cabId, JYAlarm.TERMINALREPEAT, new Date(), "终端重复（号码："+prePhoneNumber+"："+phoneNumber+"）");
		}
		else
			socketService.updateCabinetStatus(cabId);
	}
	
	/**
	 * 解析上传温度
	 * @param str
	 * @param client
	 * @return
	 */
	public void parseTempData(String str, Socket client) {
		// 温度不为数字时候报传感器异常、有数据不管对错都不算离线
		// Mes = "2000000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|XCR";
		// Ret = "2100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 5 && command[0].length() == 7
				 && command[2].length() == 14) {
			String cabId = command[1];
			//String dateStr = command[2];
			Map<String, String> order = orderMap.get(cabId);
			String dateStr = Constant.getDateStr(new Date(), "yyyyMMddHHmmss");
			order.put("reviceTemp", dateStr);
			orderMap.put(cabId, order);
			String tempData = command[3];
			this.setTempValue(cabId, tempData,dateStr);
			String tempStr = "2100000|" + command[1] + "|0XCR";

			this.sendCommand(tempStr, client);
			return;
		}
		String tempStr = "2100000|" + command[1] + "|1XCR";
		this.sendCommand(tempStr, client);
	}
	
	
	/**
	 * 解析温度上传时间
	 * @param str
	 * @param client
	 * @return
	 */
	public void parseMonitorTimeSetting(String str,Socket client){
		// Mes = "3000000|#000000|XCR";
		// Ret = "3100000|#000000|10|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 3 && command[0].length() == 7) {
			//this.storeHeartBertOrder(command[1].substring(0, 5));
			String cabId = command[1];
			String mTime = this.getMonitorTime(cabId);
			if (mTime!=null){
				String queryStr = "3100000|" + cabId + "|"+mTime+"|0XCR";
				this.sendCommand(queryStr, client);
			}
		}	
		else
		{
			String tempStr = "3100000|" + command[1] + "|00|1XCR";
			this.sendCommand(tempStr, client);
		}
	}
	
	/**
	 * 解析心跳命令，保存收到此次命令的时间，以便监测是否超时
	 * @param str
	 * @param client
	 * @return
	 */
	/*public void parseHeartBeat(String str, Socket client) {
		// Mes = "4000000|#000000|XCR";
		// Ret = "4100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 3 && command[0].length() == 7) {
			this.storeHeartBertOrder(command[1].substring(0, 5));
			String tempStr = "4100000|" + command[1] + "|0XCR";
			this.sendCommand(tempStr, client);
			return;
		}
		String tempStr = "4100000|" + command[1] + "|1XCR";
		this.sendCommand(tempStr, client);
	}*/
	
	/**
	 * 解析设备故障
	 * @param str
	 * @param client
	 * @return
	 */
	public void parseDeviceError(String str, Socket client) {
		// Mes = "5000000|#000000|XCR";
		// Ret = "5100000|#000000|0XCR";
		String command[] = str.split("[|]");
		if (command != null && command.length == 3 && command[0].length() == 7) {
			//this.storeHeartBertOrder(command[1].substring(0, 5));
			String tempStr = "5100000|" + command[1] + "|0XCR";
			String cabId = command[1];
			Date date = new Date();
			this.socketService.saveAlarm(cabId, JYAlarm.DEVICEREEOR, date, "故障");
			this.sendCommand(tempStr, client);
			return;
		}
		String tempStr = "5100000|" + command[1] + "|1XCR";
		this.sendCommand(tempStr, client);
	}

	/**
	 * 更新最新心跳时间
	 * @param cabId
	 */
	/*private void storeHeartBertOrder(String cabId) {

		Map<String, String> order = orderMap.get(cabId);
		order.put("heartBeat", Constant.getDateStr(new Date(),"yyyyMMddHHmmss"));
		orderMap.put(cabId, order);
		this.socketService.updateCabinetStatus(cabId);
	}*/
	/**
	 * 设置上传温度数据周期
	 * @param type 柜体类型
	 * @param value 时间周期
	 */
	public void sendCommandToSetMonitorTime(String type,String value){
		// Mes = "3200000|#000000|10|XCR";
		// Ret = "3300000|#000000|0XCR";
		
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

			String tempStr = "3200000|" + cabId + "|"+mTime+"|XCR";
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.sendCommand(tempStr, client);
		}
	}
	
	/**
	 * 解析设置上传时间返回命令
	 * @param str
	 * @param client
	 * @return
	 */
	public void parseReturnMonitorTimeSetting(String str,Socket client){
		// Mes = "3200000|#000000|10|XCR";
		// Ret = "3300000|#000000|0XCR";
		String command[] = str.split("[|]");
		String cabId = command[1];
		if (command != null && command.length == 3 && command[0].length() == 7&&command[2].equals("0XCR")) {	
			Map<String, String> order = this.orderMap.get(cabId);
			if (order!=null){
				order.put("monitorTimeOK", "1");
				return;
			}
		}else{
			String mTime = this.getMonitorTime(cabId);
			if (mTime!=null){
				String queryStr = "3200000|" + cabId + "|"+mTime+"|XCR";
				this.sendCommand(queryStr, client);
			}else{
				String tempStr = SocketHandler.MONITORTIMEERROR;
				this.sendCommand(tempStr, client);
			}
		}
	}
	
	/**
	 * 发送实时查询温度命令，返回超时的设备id数组
	 * @param cabIdList 柜体id数组
	 * @return
	 */
	public List<String> sendCommandToGetTempWithCabIdList(String[] cabIdList) {
		// Mes = "1000000|#000000|XCR"
		// Ret = "1100000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|0XCR"
		Date date = new Date();
		String str = String.valueOf(date.getTime());
		String taskNo = str.substring(str.length()-5);
		List<String> realCabList = new ArrayList<String>();
		realCabListMap.put(taskNo, realCabList);
		List<String> list =  new ArrayList<String>();
		for (int i=0;i<cabIdList.length;i++){
			list.add(cabIdList[i]);
		}

		for (int i = 0; i < list.size(); i++) {
			String cabId = list.get(i);
			Socket client = clientMap.get(cabId);
			String queryStr = "10"+taskNo+"|" + cabId + "|XCR";
			this.sendCommand(queryStr, client);
		}
		int delay = 0;
		while(true){
			try {	
				Thread.sleep(500);
				if(realCabList.size()==list.size()) 
				{
					realCabListMap.remove(taskNo);	
					return null;
				}
				else if(delay==15){
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
						//Date date = new Date();
						this.socketService.saveAlarm(list.get(i), JYAlarm.DEVICEOFFLINE, date, "离线");
						
					}
					realCabListMap.remove(taskNo);
					return list;
				}
				delay++;			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 解析实时温度
	 * @param str
	 * @param client
	 * @return
	 */
	public void parseRealTempData(String str, Socket client) {
		// Mes = "1000000|#000000|XCR"
		// Ret = "1100000|#000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432|0XCR"
		// 此命令中的时间预留，系统以收到数据的时间为准
		String command[] = str.split("[|]");
		if (command != null && command.length == 5 && command[0].length() == 7
				&& command[2].length() == 14) {
			String cabId = command[1];
			//String dateStr = command[2];
			String tempData = command[3];
			String taskNo = command[0].substring(2,7);
			List<String> realCabList = this.realCabListMap.get(taskNo);
			if (realCabList==null){
				this.sendCommand(SocketHandler.REALTEMPERROR, client);
				return;
			}
			realCabList.add(cabId);
			String dateStr = Constant.getDateStr(new Date(), "yyyyMMddHHmmss");
			this.setTempValue(cabId, tempData,dateStr);
			return;
		}
		this.sendCommand(SocketHandler.REALTEMPERROR, client);
	}
	
	/**
	 * 解析温度
	 * @param cabId
	 * @param arg0 包含温度的字符串
	 * @param dateStr 读取温度时间
	 */
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

					try{
						vL.add(Float.parseFloat(v1));
					}catch(Exception e){
						vL.add(null);
					}
					try{
						vL.add(Float.parseFloat(v2));
					}catch(Exception e){
						vL.add(null);
					}
					try{
						vL.add(Float.parseFloat(v3));
					}catch(Exception e){
						vL.add(null);
					}
					try{
						vL.add(Float.parseFloat(v4));
					}catch(Exception e){
						vL.add(null);
					}

					map.put(positionNumber, vL);
				}
			}
			
			socketService.saveDate(cabId, map, dateStr);
			this.socketService.updateCabinetStatus(cabId);
		}
	}
	/**
	 * 终端掉线后从内存移除
	 * @param socket
	 */
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
				this.Logout(key);
			}
		}
	}

	/**
	 * 将设备从内存移除
	 * @param cabId
	 */
	public void removeOrderMap(String cabId) {
		orderMap.remove(cabId);
		clientMap.remove(cabId);		
	}
	/**
	 * 监测内存中是否加载了设备，（取决于主站是否添加且启用）
	 * @param cabId
	 * @param client
	 * @return
	 */
	public boolean isExist(String cabId,Socket client){
		if(orderMap.get(cabId)!=null){
			clientMap.put(cabId, client);//如有则替换
			return true;
		}	
		else 
			return false;
	}
	/**
	 * 停止线程
	 */
	public void stop(){
		this.checkThread.stopCheck();
		this.halfHourEvent.stopTimer();
	}
	/**
	 * 发送命令
	 * @param str
	 * @param client
	 */
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
	/**
	 * 讲设备载入内存
	 * @param cabId
	 */
	public void addCabinet(String cabId){
		Map<String, String> order = new HashMap<String, String>();
		JYCabinet cabinet = this.socketService.getCabinet(cabId);
		Date detectTime = cabinet.getDetectTime();
		String heartDateStr = "";
		if(detectTime!=null){
			//SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			heartDateStr = Constant.getDateStr(detectTime,"yyyyMMddHHmmss");
		}
		else
			heartDateStr = Constant.getDateStr(new Date(),"yyyyMMddHHmmss");
		order.put("heartBeat", heartDateStr);
		String dateStr = this.socketService.getHistoryDateString(cabId);
		if (dateStr!=null)
			order.put("reviceTemp", dateStr);
		order.put("isLogined", "0");
		order.put("monitorTimeOK", "0");
		orderMap.put(cabId, order);
	}
	/**
	 * 判断终端是否登陆
	 * @param cabId
	 * @return
	 */
	public boolean isLogined(String cabId){
		Map<String, String> order = orderMap.get(cabId);
		if (order!=null){
			String isLogined = order.get("isLogined");
			if(isLogined.equals("1"))
				return true;
		}
		return false;
	}
	/**
	 * 登出
	 * @param cabId
	 */
	private void Logout(String cabId){
		Map<String, String> order = orderMap.get(cabId);
		if (order!=null){
			order.put("isLogined", "0");
			orderMap.put(cabId, order);
		}
	}
	/**
	 * 获取柜体上传时间
	 * @param cabId
	 * @return
	 */
	private String getMonitorTime(String cabId){
		JYCabinet cabinet = this.socketService.getCabinet(cabId);
		if (cabinet==null)return null;
		String mTime = cabinet.getCabType().getSubValue();
		if (mTime.length()==1){
			mTime ="0"+mTime;
		}
		return mTime;
	}
}
