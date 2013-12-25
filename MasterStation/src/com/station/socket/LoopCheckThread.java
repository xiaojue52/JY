package com.station.socket;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.station.constant.Constant;
import com.station.po.JYAlarm;
import com.station.service.JYSocketService;

public class LoopCheckThread extends Thread{
	private Map<String, Map<String, String>> orderMap;
	private JYSocketService socketService;
	public boolean stop = false;
	@Override
	public void run(){
		while(!stop){
			try {
				Thread.sleep(Constant.LOOPCHECKTIME);
				Map<String, Map<String, String>> orderMap = this.orderMap;
				if (orderMap.isEmpty())continue;
				loopCheck(orderMap);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
		}
	}
	public LoopCheckThread(Map<String, Map<String, String>> orderMap,JYSocketService socketService){
		this.orderMap = orderMap;
		this.socketService = socketService;
	}
	private void loopCheck(Map<String, Map<String, String>> orderMap){
		Iterator<Map.Entry<String, Map<String, String>>> iter = orderMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Map<String, String>> mEntry = (Map.Entry<String, Map<String, String>>) iter.next();
			String cabNumber = (String) mEntry.getKey();
			Map<String, String> order = (Map<String, String>)mEntry.getValue();
			//System.out.print(cabNumber);
			try {
				checkHeartBeat(cabNumber,order.get("heartBeat"));
				checkReviceTemp(cabNumber,order.get("reviceTemp"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void checkHeartBeat(String cabNumber,String date) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d1 = df.parse(date);
		Date d2 = new Date();
		long diff = d2.getTime()-d1.getTime();
		if (diff>=Constant.HEARTBEATTIME*60*1000){
			this.socketService.saveAlarm(cabNumber, JYAlarm.HEARTBEATOFFLINE, d2, "离线");
		}
		//System.out.print("\n"+diff+"\n"+diff+"\n");
	}
	private void checkReviceTemp(String cabNumber,String date) throws ParseException{
		if (date==null||this.socketService.getCabinet(cabNumber)==null)return;
		long mTime = (Long.valueOf(this.socketService.getCabinet(cabNumber).getCabType().getSubValue())+1)*60*1000;
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d1 = df.parse(date);
		Date d2 = new Date();
		long diff = d2.getTime()-d1.getTime();
		if (diff>=mTime){
			this.socketService.saveAlarm(cabNumber, JYAlarm.DEVICEOFFLINE, d2, "离线");
		}
		//System.out.print("\n"+diff+"\n"+diff+"\n"+Constant.reciveTempTime);
	}
	public void stopCheck(){
		this.stop = true;
	}
}
