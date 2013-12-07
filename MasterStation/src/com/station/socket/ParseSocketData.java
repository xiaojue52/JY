package com.station.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.station.constant.InitData;
import com.station.service.JYSocketService;

public class ParseSocketData {
	public ParseSocketData(ServletContextEvent sce){
		this.sce = sce;
	}
	private ServletContextEvent sce;
	public Map<String, Socket> clientMap = new HashMap<String, Socket>();
	public Map<String, String> order = new HashMap<String, String>();
	public Map<String, Map<String, String>> orderMap = new HashMap<String, Map<String, String>>();

	public void updateDevice(String identify, String currentData){
		/*double temp = 0;
		if (ParseStringToDecimal.IsDouble(currentData)){
			temp = Double.valueOf(currentData);
		}
		Device device = deviceService
		.findDeviceByIdentify(identify);
		if (device != null) {
			device.setCurrentData(temp);
			Date date = new Date();
			device.setCreateDate(new java.sql.Date(date.getTime()));
			device.setCreateTime(new java.sql.Time(date.getTime()));
			double wTemp = device.getWarningTemperature();
			if (temp>=wTemp){
				device.setReason(hTemp);
				device.setStatus(exception);
				UnhandledException unhandledException = unhandledExceptionService.getUnhandledException(device);
				unhandledExceptionService.saveDevice(unhandledException);
			}
			else{
				device.setReason(dNormal);
				device.setStatus(normal);

			}
			deviceService.updateDevice(device);
	
			DeviceHistory deviceHistory = deviceHistoryService.getDeviceHistory(device);
			deviceHistoryService.saveDevice(deviceHistory);
		}*/
		
	}
	public void deviceOffline(String identify){
		/*Device device = deviceService
		.findDeviceByIdentify(identify);
		if (device != null) {
			device.setStatus(exception);
			device.setReason(offLine);
			deviceService.updateDevice(device);
			UnhandledException unhandledException = unhandledExceptionService.getUnhandledException(device);
			unhandledExceptionService.saveDevice(unhandledException);
		}*/
	}
	public String CheckString(String str,Socket client){
		if (str==null||str.length()<7)return "-2";
		String orderStr = str.substring(0,2);
		System.out.print(orderStr);
		//loginMes = "0000000|0000000|000000CR";
		if (orderStr.equals("00")){
			return parseLogin(str,client);
		}
		if (orderStr.equals("40")){
			return this.parseHeartBeat(str, client);
		}
		if (orderStr.equals("20")){
			return this.parseTempData(str, client);
		}
		return "-2";
	}
	private String parseTempData(String str,Socket client){
		//Mes = "2000000|0000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432XXCR";
		if(!str.substring(str.length()-2).equals("CR"))return "-2";
		String command[] = str.split("[|]");
		if (command!=null&&command.length==4&&command[0].length()==7&&command[1].length()==7&&command[2].length()==14){
			String tempData = command[3].substring(0,command[3].length()-4);
			//if (tempData)
			this.setTempValue(command[1].substring(0,5),tempData,command[2]);
			return "2100000|"+command[1]+"|0XXCR";
		}
		return "-2";
	}
	
	private String parseHeartBeat(String str,Socket client){
		//Mes = "4000000|0000000XXCR";
		if (str.length()!=19||!str.substring(str.length()-2).equals("CR"))return "-2";
		String command[] = str.split("[|]");
		if (command!=null&&command.length==2
				&&command[0].length()==7){
			return "4100000"+command[1].substring(0,7)+"|0XXCR";
		}
		return "-2";
	}
	private String parseLogin(String str,Socket client){
		//loginMes = "0000000|0000000|000000CR";
		if (str.length()!=24||!str.substring(str.length()-2).equals("CR"))
			return "-2";
		String command[] = str.split("[|]");
		System.out.println(str+":::"+str.length()+"::"+command[1]+"::::"+command.length+"::"+str.substring(22));
		
		if (command!=null&&command.length==3&&command[0].length()==7&&command[1].length()==7){
			if(command[0].equals("0000000")){			
				clientMap.put(command[1],client);
				return "0000000|"+command[1]+"|000000CR";
			}
			
		}
		return "-2";
	}
	
	
	public void sendCommand() {
		PrintWriter out = null;
		Iterator<Map.Entry<String, Socket>> iter = clientMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Socket> mEntry = (Map.Entry<String, Socket>) iter
					.next();
			Socket socket = (Socket) mEntry.getValue();
			try {
				out = new PrintWriter(socket.getOutputStream());
				out.print("give me temprature, please\n\t");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void removedSocket(Socket socket) {
		Iterator<Map.Entry<String, Socket>> iter = clientMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Socket> mEntry = (Map.Entry<String, Socket>) iter
					.next();
			Socket temp = (Socket) mEntry.getValue();
			String key = (String) mEntry.getKey();
			if (temp == socket) {
				clientMap.remove(key);
				System.out.print("移除socket！");
				// ParseSocketData.deviceOffline(key);
			}
		}
	}

	// @SuppressWarnings("unchecked")
	public void sendCommandWithIdentify(String identify) {
		PrintWriter out = null;
		Socket client = clientMap.get(identify);
		if (client != null) {
			try {
				out = new PrintWriter(client.getOutputStream());
				out.print("\nget " + identify + " temprature!");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void setTempValue(String cabNumber,String arg0,String dateStr){
		//Mes = "2000000|0000000|20131206124730|0001+1235+0135+1240+0103*0002+2356+1111+0104+1432XXCR";
		Map<Integer,List<Float>> map = new HashMap<Integer,List<Float>>();
		String tempList[] = arg0.split("[*]");
		if (tempList!=null&&tempList.length>0){
			ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.sce.getServletContext());  
			JYSocketService socketService = (JYSocketService) applicationContext.getBean("JYSocketService");
			for(int i=0;i<tempList.length;i++){
				if (tempList[i].length()==24){
					List<Float> vL = new ArrayList<Float>();
					int positionNumber = Integer.valueOf(tempList[i].substring(0,4));
					String v1 = tempList[i].substring(4,9);
					String vt = v1.substring(4);
					v1 = v1.substring(0,4)+"."+vt;
					String v2 = tempList[i].substring(9,14);
					vt = v2.substring(4);
					v2 = v2.substring(0,4)+"."+vt;
					
					String v3 = tempList[i].substring(14,19);
					vt = v3.substring(4);
					v3 = v3.substring(0,4)+"."+vt;
					
					String v4 = tempList[i].substring(19,24);
					vt = v4.substring(4);
					v4 = v4.substring(0,4)+"."+vt;
					
					System.out.print("v1"+v1+":v2"+v2+":v3"+v3+":v4"+v4);
					vL.add(Float.parseFloat(v1));
					vL.add(Float.parseFloat(v2));
					vL.add(Float.parseFloat(v3));
					vL.add(Float.parseFloat(v4));
					
					map.put(positionNumber, vL);
				}	
			}
			socketService.saveDate(cabNumber,map, dateStr);
		}
	}
}
