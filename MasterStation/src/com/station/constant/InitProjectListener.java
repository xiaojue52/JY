package com.station.constant;

import javax.servlet.ServletContextEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.station.socket.SocketListener;


public class InitProjectListener extends ContextLoaderListener{
	private SocketListener socketListener;
	public void contextInitialized(ServletContextEvent event) {  
	    super.contextInitialized(event);  
	    ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());  
	    InitData initData = (InitData) applicationContext.getBean("InitData");
		initData.init(event);
		socketListener = new SocketListener(event);
		socketListener.start();
	}
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		// TODO Auto-generated method stub
		if(null!=socketListener&&!socketListener.isInterrupted())
		{
			//关闭线程
			socketListener.closeSocketServer();
			//中断线程
			socketListener.interrupt();
		}
	}
}
