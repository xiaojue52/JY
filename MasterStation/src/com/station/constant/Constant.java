package com.station.constant;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;

public class Constant {
	public static final long heartBeatTime = 1*60*1000;
	public static final long loopTime = 1000;
	public static final long reciveTempTime = 1*60*1000;
	public static final String alarmType1Hql = "from JYConstant key where key.type = 'AlarmType' and key.key = '1000'";
	public static final String alarmType2Hql = "from JYConstant key where key.type = 'AlarmType' and key.key = '1001'";
	public static final String alarmType3Hql = "from JYConstant key where key.type = 'AlarmType' and key.key = '1002'";
	public static String getCurrentDateStr() {
		 Date date = new Date();
		 String str = null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		 str = df.format(date);
		 return str;
	}
	public static void flush(Map<String,Object> dataMap){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			Gson gson = new Gson();
			String jsonString = gson.toJson(dataMap);
			out.println(jsonString);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
