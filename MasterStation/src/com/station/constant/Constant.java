package com.station.constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constant {
	public static final long heartBeatTime = 1*60*1000;
	public static final long loopTime = 1000;
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
}
