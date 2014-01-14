package com.station.data;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYConstant;
import com.station.po.JYUser;
import com.station.po.JYUserGroup;
import com.station.service.JYConstantService;
import com.station.service.JYUserGroupService;
import com.station.service.JYUserService;

@SuppressWarnings("serial")
public class DataList extends ActionSupport {

	private JYUserService userService;
	private JYConstantService constantService;
	private JYUserGroupService userGroupService;

	public void setUserGroupService(JYUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	public void setConstantService(JYConstantService constantService) {
		this.constantService = constantService;
	}
	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}
	public List<JYUser> getUser(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userLevel =  (String) session.getAttribute("userLevel");
		String userId = (String)session.getAttribute("userId");
		if (userLevel!=null&&(userLevel.equals("super_admin")||userLevel.endsWith("user"))){
			String hql = "from JYUser user where user.userLevel = 'com_admin'";
			List<JYUser> list = this.userService.findUserByHql(hql);
			//list.add(0, user);
			return list;
		}
		else
		{
			JYUser user = this.userService.findUserById(userId);
			JYUser user1 = this.userService.findUserByHql("from JYUser user where user.username = '--'").get(0);
			List<JYUser> list = new ArrayList<JYUser>();
			list.add(user);
			list.add(user1);
			return list;
		}
		
	}
	public List<JYUserGroup> getAllUserGroups(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userLevel =  (String) session.getAttribute("userLevel");
		String userId = (String)session.getAttribute("userId");
		if (userLevel!=null&&(userLevel.equals("super_admin")||userLevel.endsWith("user"))){
			String hql = "from JYUserGroup userGroup";
			return this.userGroupService.findJYUserGroupByHql(hql,null);
		}else{
			String hql = "from JYUserGroup userGroup where userGroup.groupName = '--'";
			JYUserGroup userGroup = this.userGroupService.findJYUserGroupByHql(hql,null).get(0);
			JYUser user = this.userService.findUserById(userId);
			JYUserGroup userGroup1 = user.getUserGroup();
			List<JYUserGroup> list = new ArrayList<JYUserGroup>();
			if (userGroup1.getId()==userGroup.getId()){
				list.add(userGroup);
			}else
			{
				list.add(userGroup);
				list.add(userGroup1);
			}
			return list;
		}
	}
	public List<JYUser> getAllUser(){
		String hql = "from JYUser user";
		List<JYUser> list = this.userService.findUserByHql(hql);
		return list;
	}
	public List<JYConstant> getCabTpyeConstant(){
		String hql = "from JYConstant constant where constant.type = 'CabType'";
		List<JYConstant> list = this.constantService.findJYConstantByHql(hql);
		return list;
	}
	public List<JYConstant> getPowerLevelConstant(){
		String hql = "from JYConstant constant where constant.type = 'PowerLevel'";
		List<JYConstant> list = this.constantService.findJYConstantByHql(hql);
		return list;
	}
	public String getFunctionNum(){
		return Constant.FUNCTIONNUM;
	}
	public String getMesDate(){
		return Constant.MESDATE;
	}
	public String getMesUser(){
		return Constant.MESUSER;
	}
	public long getHeartBeatTime(){
		return Constant.HEARTBEATTIME;
	}
}
