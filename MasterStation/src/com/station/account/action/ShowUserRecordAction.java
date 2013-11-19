package com.station.account.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYUser;
import com.station.service.JYUserService;

@SuppressWarnings("serial")
public class ShowUserRecordAction extends ActionSupport {

	private JYUser user;
	private JYUserService userService;

	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}

	public JYUser getUser() {
		return user;
	}

	public void setUser(JYUser user) {
		this.user = user;
	}

	public void showUserAction() throws Exception {
		user = this.userService.findUserById(user.getUserId());
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("user", user);
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