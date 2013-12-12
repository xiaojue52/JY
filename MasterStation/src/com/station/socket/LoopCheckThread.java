package com.station.socket;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.station.constant.Constant;
import com.station.service.JYSocketService;

public class LoopCheckThread extends Thread{
	private Map<String, Map<String, String>> orderMap;
	private JYSocketService socketService;
	public boolean stop = false;
	@Override
	public void run(){
		while(!stop){
			try {
				Thread.sleep(Constant.loopTime);
				if (this.orderMap.isEmpty())continue;
				loopCheck();
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
	private void loopCheck(){
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
		if (diff>=Constant.heartBeatTime){
			this.socketService.saveAlarm(cabNumber, 0, d2, "超过心跳时间");
		}
		//System.out.print("\n"+diff+"\n"+diff+"\n");
	}
	private void checkReviceTemp(String cabNumber,String date) throws ParseException{
		if (date==null)return;
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d1 = df.parse(date);
		Date d2 = new Date();
		long diff = d2.getTime()-d1.getTime();
		if (diff>=Constant.reciveTempTime){
			this.socketService.saveAlarm(cabNumber, 1, d2, "过长时间未收到数据");
		}
		//System.out.print("\n"+diff+"\n"+diff+"\n"+Constant.reciveTempTime);
	}
	public void stopCheck(){
		this.stop = true;
	}
}
