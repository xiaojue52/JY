package com.station.group.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.pagebean.PageBean;
import com.station.po.JYUserGroup;
import com.station.service.JYUserGroupService;

@SuppressWarnings("serial")
public class UserGroupAction extends ActionSupport{
	private JYUserGroupService userGroupService;
	private JYUserGroup userGroup;
	private PageBean pageBean;
	private int page = 1;
	private int pageList = 10;
	private int ret = 0;
	private int code = 0;
	private List<Integer> pageNumberList = new ArrayList<Integer>();
	
	public List<Integer> getPageNumberList() {
		pageNumberList.clear();
		pageNumberList.add(10);
		pageNumberList.add(20);
		pageNumberList.add(30);
		pageNumberList.add(40);
		return pageNumberList;
	}
	public void setPageNumberList(List<Integer> pageNumberList) {
		this.pageNumberList = pageNumberList;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}

	private String groupName;
	private String leaderName;

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageList() {
		return pageList;
	}
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public JYUserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(JYUserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public void setUserGroupService(JYUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	public String addUserGroupAction(){
		String hql = "from JYUserGroup group where group.groupName = '"+this.userGroup.getGroupName()+"'";
		if (this.userGroupService.findJYUserGroupByHql(hql).size()>0){
			return ERROR;
		}
		else{
			this.userGroupService.saveJYUserGroup(this.userGroup);
			return SUCCESS;
		}
	}
	public String deleteUserGroupAction(){
		if(this.userGroupService.removeJYUserGroup(userGroup)==-1){
			return ERROR;
		}
		return SUCCESS;
	}
	public String updateUserGroupAction(){
		String hql = "from JYUserGroup group where group.groupName = '"+this.userGroup.getGroupName()+"'";
		
		if (this.userGroupService.findJYUserGroupByHql(hql).size()>0){
			JYUserGroup userGroup = this.userGroupService.findJYUserGroupByHql(hql).get(0);
			if (!userGroup.getId().equals(this.userGroup.getId()))
				return ERROR;
		}
		this.userGroupService.updateJYUserGroup(userGroup);
		return SUCCESS;
	}
	public void showUserGroupAction(){
		this.userGroup = this.userGroupService.findJYUserGroupById(this.userGroup.getId());
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("userGroup", this.userGroup);
		Constant.flush(dataMap);
	}
	public String listUserGroupsAction(){
		if (code == -1){
			ret = -1;
		}
		if (code == -2){
			ret = -2;
		}
		else
			ret = 0;
		final String hql = this.createSql();
		this.pageBean = this.userGroupService.getPerPage(pageList, page, hql);
		code = 0; 
		return SUCCESS;
	}
	
	private String createSql() {
		String hql = "from JYUserGroup userGroup where userGroup.groupName != '--' and ";
		if (groupName==null||groupName.length()==0)
			groupName = "%";
		if (leaderName==null||leaderName.length()==0)
			leaderName = "%";
		hql = hql+"userGroup.groupName like '%"+groupName+"%' "+"and userGroup.leaderName like '%"+leaderName+"%' ";		
		return hql;
	}
}
