package com.station.socket;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import com.station.constant.Constant;

public class SocketLog {
	private ServletContextEvent sce;
	
	public SocketLog(ServletContextEvent sce){
		this.sce = sce;
	}
	public synchronized void saveLog(String str){
		String path = sce.getServletContext().getRealPath("/")+"files/logs";
		String name = Constant.getDateStr(new Date(), "yyyyMMdd")+".txt";
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdir();
		}
		File file = new File(path,name);
		try {
			FileWriter fw = new FileWriter(file,true);
			fw.write(Constant.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss")+" reviced:"+str+"\r\n");
			fw.flush();
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
