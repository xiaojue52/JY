package com.station.constant;

import javax.servlet.ServletContextEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class InitProjectListener extends ContextLoaderListener{

	public void contextInitialized(ServletContextEvent event) {  
	    super.contextInitialized(event);  
	    ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());  
	    InitData initData = (InitData) applicationContext.getBean("InitData");
		initData.initUserTable();
		initData.initKeyGeneratorTable();
		initData.initConstantTable();
	}
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		// TODO Auto-generated method stub
	}
}
