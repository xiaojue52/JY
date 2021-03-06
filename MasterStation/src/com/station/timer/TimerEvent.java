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
	private Timer timer4; //每2个月清空一次前3个月的设备状态数据
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
		int currentHour = currentTime1.get(Calendar.HOUR_OF_DAY);		
		currentTime1.set(Calendar.HOUR_OF_DAY, currentHour + 1);
		currentTime1.set(Calendar.MINUTE, 0);
		currentTime1.set(Calendar.SECOND, 0);
		currentTime1.set(Calendar.MILLISECOND, 0);
		Date NextHour = currentTime1.getTime();
		
		Calendar currentTime2 = Calendar.getInstance();
		currentTime2.setTime(new Date());
		int day = currentTime2.get(Calendar.DAY_OF_MONTH);
		currentTime2.set(Calendar.DAY_OF_MONTH, day+1);
		currentTime2.set(Calendar.HOUR_OF_DAY, 0);
		currentTime2.set(Calendar.MINUTE, 10);
		currentTime2.set(Calendar.SECOND, 00);
		Date NextDay = currentTime2.getTime();
		
		System.out.print(new Date()+"\n");
		System.out.println(NextDay);
		
		Calendar currentTime3 = Calendar.getInstance();
		currentTime3.setTime(new Date());		
		int month = currentTime3.get(Calendar.MONTH);
		currentTime3.set(Calendar.MONTH, month+2);
		Date NextTwoMonth = currentTime3.getTime();
		
		/*Calendar testTime = Calendar.getInstance();
		testTime.setTime(new Date());
		int m = testTime.get(Calendar.MINUTE);
		testTime.set(Calendar.MINUTE, m+2);
		Date test = testTime.getTime();*/

		//System.out.print(NextDay);
		timer1.schedule(new GetHistoryTask(), NextHour, JOB_INTERNAL);
		timer2.scheduleAtFixedRate(new GetMaxAndMinHistoryTask(), NextDay, DAY_JOB_INTERNAL);
		timer3.schedule(new CheckTempTask(), 1000*60*1, CHECK_INTERNAL);
		timer4.schedule(new RemoveCabinetAlartTask(), NextTwoMonth);
	}
	/**
	 * 定时任务，每隔半小时（整点半小时）取温度数据存入jy_history_chart表
	 * 用于检测单元数据对比
	 * @author Administrator
	 *
	 */
	class GetHistoryTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub

			timerTaskService.saveHistoryChartData();
		}
		
	}
	/**
	 * 隔天取前一天的温度最大值、最小值存入jy_history_month_chart表，
	 * 用于月温度对比
	 * @author Administrator
	 *
	 */
	class GetMaxAndMinHistoryTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			timerTaskService.saveHistoryMonthChartData();
		}
		
	}
	/**
	 * 监测设定时间内温度的最大值、最小值是否超出设定阀值
	 * @author Administrator
	 *
	 */
	class CheckTempTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			timerTaskService.saveCheckedTempAlarm();
		}
		
	}
	/**
	 * 删除3个月前的报警记录信息
	 * @author Administrator
	 *
	 */
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
	/**
	 * 取消定时任务
	 */
	public void stopTimer(){
		this.timer1.cancel();
		this.timer2.cancel();
		this.timer3.cancel();
		this.timer4.cancel();
	}
}
