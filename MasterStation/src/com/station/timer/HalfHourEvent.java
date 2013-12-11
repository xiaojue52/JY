package com.station.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.station.service.JYChartDataService;

public class HalfHourEvent {
	private final static long JOB_INTERNAL = 1000 * 60 * 30;
	private final static long DAY_JOB_INTERNAL = 1000 * 60 * 60*24;
	private JYChartDataService chartDataService;
	private Timer timer1;
	private Timer timer2;
	public HalfHourEvent(JYChartDataService chartDataService){
		this.chartDataService = chartDataService;
	}

	public void startTimer() {
		
		timer1 = new Timer();
		timer2 = new Timer();
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

		timer1.schedule(new GetHistoryTask(), NextHour, JOB_INTERNAL);
		timer2.scheduleAtFixedRate(new GetMaxAndMinHistoryTask(), NextDay, DAY_JOB_INTERNAL);
		//timer1.
	}
	class GetHistoryTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(5000);
				chartDataService.saveHistoryChartData();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	class GetMaxAndMinHistoryTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(5000);
				chartDataService.saveHistoryMonthChartData();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void stopTimer(){
		this.timer1.cancel();
		this.timer2.cancel();
	}
}
