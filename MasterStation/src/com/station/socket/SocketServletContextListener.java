package com.station.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.station.main.action.MainAction;

public class SocketServletContextListener implements ServletContextListener{
	private SocketListener socketListener;
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		/*if(null!=socketListener&&!socketListener.isInterrupted())
		{
			//关闭线程
			socketListener.closeSocketServer();
			//中断线程
			socketListener.interrupt();
		}*/
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		/*SocketListener socketListener = new SocketListener();
		socketListener.start();
		MainAction.setSocketListener(socketListener);*/
	}
}
