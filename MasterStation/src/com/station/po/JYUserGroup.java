package com.station.po;

import java.util.List;

public class JYUserGroup {
	private Integer id;
	private String leaderName;
	private String groupName;
	private String note;
	private List<JYUser> userList;

	public List<JYUser> getUserList() {
		return userList;
	}
	public void setUserList(List<JYUser> userList) {
		this.userList = userList;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}