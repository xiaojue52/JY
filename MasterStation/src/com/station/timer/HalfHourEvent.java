package com.station.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HalfHourEvent {
	private final static long JOB_INTERNAL = 1000 * 60 * 30;

	public void test() {
		Timer timer = new Timer();
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTime(new Date());
		int currentHour = currentTime.get(Calendar.HOUR);
		currentTime.set(Calendar.HOUR, currentHour + 1);
		currentTime.set(Calendar.MINUTE, 0);
		currentTime.set(Calendar.SECOND, 0);
		currentTime.set(Calendar.MILLISECOND, 0);
		Date NextHour = currentTime.getTime();
		System.out.println(NextHour);
		timer.scheduleAtFixedRate(new MyTask(), NextHour, JOB_INTERNAL);
	}
	class MyTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("\nmytask： " + new Date());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
