package com.station.data;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYUser;
import com.station.service.JYUserService;

@SuppressWarnings("serial")
public class DataList extends ActionSupport {

	private JYUserService userService;

	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}
	public List<JYUser> getUser(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		//String username = (String) session.getAttribute("username");
		String userLevel =  (String) session.getAttribute("userLevel");
		String userId = (String)session.getAttribute("userId");
		if (userLevel!=null&&userLevel.equals("super_admin")){
			String hql = "from JYUser user where user.userLevel = 'com_admin'";
			List<JYUser> list = this.userService.findUserByHql(hql);
			//list.add(0, user);
			return list;
		}
		else
		{
			JYUser user = this.userService.findUserById(userId);
			List<JYUser> list = new ArrayList<JYUser>();
			list.add(user);
			return list;
		}
	}
}
