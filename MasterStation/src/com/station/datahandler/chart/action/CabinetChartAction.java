package com.station.datahandler.chart.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.service.JYAlarmService;

@SuppressWarnings("serial")
public class CabinetChartAction extends ActionSupport {
	private JYAlarmService alarmService;
	private String queryStartDate;
	private String queryCabId;

	public void setAlarmService(JYAlarmService alarmService) {
		this.alarmService = alarmService;
	}
	
	public String getQueryCabId() {
		return queryCabId;
	}

	public void setQueryCabId(String queryCabId) {
		this.queryCabId = queryCabId;
	}

	public String getQueryStartDate() {
		return queryStartDate;
	}

	public void setQueryStartDate(String queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	class ChartData{
		public String name;
		public List<List<Long>> data = new ArrayList<List<Long>>();
	}
	public void listAlarmAction() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(queryStartDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, day-3);
		String startStr = df.format(calendar.getTime());
		
		calendar.set(Calendar.DAY_OF_MONTH, day+3);
		String endStr = df.format(calendar.getTime());
		
		List<ChartData> listValue = new ArrayList<ChartData>();
		ChartData data1 = this.getChartData("离线", startStr,endStr);
		ChartData data2 = this.getChartData("故障", startStr,endStr);
		listValue.add(data1);
		listValue.add(data2);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", listValue);
		Constant.flush(dataMap);
	}
	
	public class ComResult{
		public String alarmText;
		public String dateStr;
		public String times;
	}
	@SuppressWarnings("unchecked")
	private ChartData getChartData(String arg0,String arg1,String arg2){
		ChartData chartData = new ChartData(); 
		String hql = "select t.alarm_text,to_char(t.c_date,'yyyy-MM-dd'),sum(t.times) from jy_alarm t,jy_device d where d.cab_id = '"+queryCabId+"' and t.alarm_text = '"+arg0+"' and t.c_date>=to_date('"+arg1+"','yyyy-MM-dd') and t.c_date<=to_date('"+arg2+"','yyyy-MM-dd') group by t.alarm_text,to_char(t.c_date,'yyyy-MM-dd')";
		List<ComResult> comResults = (List<ComResult>) this.alarmService.findCostomizeObjHql(hql);
		chartData.name = arg0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (int i=0;i<comResults.size();i++){
			List<Long> list = new ArrayList<Long>();
			try {
				Object obj = comResults.get(i);
				ComResult test = new ComResult();
				test.alarmText = "1";
				test.dateStr = "2";
				test.times = "3";
				list.add(df.parse(comResults.get(i).dateStr).getTime()+8*60*60*1000);
				list.add(Long.valueOf(comResults.get(i).times));
				chartData.data.add(list);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		//todo
		return chartData;
	}
}
