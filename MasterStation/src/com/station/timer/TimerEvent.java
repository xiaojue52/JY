package com.station.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.station.service.JYTimerTaskService;

public class TimerEvent {
	private final static long JOB_INTERNAL = 1000 * 60 * 30;
	private final static long DAY_JOB_INTERNAL = 1000 * 60 * 60 * 24;
	private final static long CHECK_INTERNAL = 1000 * 60 * 15;
	private JYTimerTaskService timerTaskService;
	private Timer timer1; //每半小时读取一次历史数据放到报表数据库
	private Timer timer2; //每天读取一次历史数据放到月报表数据库
	private Timer timer3; //每15分钟判断一下温度是否异常
	private Timer timer4; //每25天清空一次前3个月的设备状态数据
	public TimerEvent(JYTimerTaskService timerTaskService){
		this.timerTaskService = timerTaskService;
	}

	public void startTimer() {
		
		timer1 = new Timer();
		timer2 = new Timer();
		timer3 = new Timer();
		timer4 = new Timer();
		Calendar currentTime1 = Calendar.getInstance();
		currentTime1.setTime(new Date());
		int currentHour = currentTime1.get(Calendar.HOUR);		
		
		
		currentTime1.set(Calendar.HOUR, currentHour + 1);
		currentTime1.set(Calendar.MINUTE, 0);
		currentTime1.set(Calendar.SECOND, 0);
		currentTime1.set(Calendar.MILLISECOND, 0);
		Date NextHour = currentTime1.getTime();
		
		Calendar currentTime2 = Calendar.getInstance();
		currentTime2.setTime(new Date());		
		currentTime2.set(Calendar.HOUR, 23);
		currentTime2.set(Calendar.MINUTE, 59);
		currentTime2.set(Calendar.SECOND, 59);
		Date NextDay = currentTime2.getTime();
		System.out.println(NextHour);
		
		Calendar currentTime3 = Calendar.getInstance();
		currentTime3.setTime(new Date());		
		int month = currentTime3.get(Calendar.MONTH);
		currentTime3.set(Calendar.MONTH, month+2);
		Date NextTwoMonth = currentTime3.getTime();
		
		/*Calendar testTime = Calendar.getInstance();
		testTime.setTime(new Date());
		int m = testTime.get(Calendar.MINUTE);
		testTime.set(Calendar.MINUTE, m+1);
		Date test = testTime.getTime();*/

		timer1.schedule(new GetHistoryTask(), NextHour, JOB_INTERNAL);
		timer2.scheduleAtFixedRate(new GetMaxAndMinHistoryTask(), NextDay, DAY_JOB_INTERNAL);
		timer3.schedule(new CheckTempTask(), 1000*60*1, CHECK_INTERNAL);
		timer4.schedule(new RemoveCabinetAlartTask(), NextTwoMonth);
	}
	class GetHistoryTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub

			timerTaskService.saveHistoryChartData();
		}
		
	}
	class GetMaxAndMinHistoryTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			timerTaskService.saveHistoryMonthChartData();
		}
		
	}
	class CheckTempTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			timerTaskService.saveCheckedTempAlarm();
		}
		
	}
	class RemoveCabinetAlartTask extends TimerTask{
		@Override
		public void run(){
			timerTaskService.removeCabinetStatusAlarmAtFixedRate();
			Calendar currentTime3 = Calendar.getInstance();
			currentTime3.setTime(new Date());		
			int month = currentTime3.get(Calendar.MONTH);
			currentTime3.set(Calendar.MONTH, month+2);
			Date NextTwoMonth = currentTime3.getTime();
			
			/*Calendar testTime = Calendar.getInstance();
			testTime.setTime(new Date());
			int m = testTime.get(Calendar.MINUTE);
			testTime.set(Calendar.MINUTE, m+1);
			Date test = testTime.getTime();*/
			timer4.schedule(new RemoveCabinetAlartTask(), NextTwoMonth);
		}
	}
	public void stopTimer(){
		this.timer1.cancel();
		this.timer2.cancel();
		this.timer3.cancel();
		this.timer4.cancel();
	}
}
