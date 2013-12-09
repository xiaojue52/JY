package com.station.po;

public class JYUser {
	private String userId;
	private String username;
	private String password;
	private String contact;
	private String company;
	private String jobLevel;
	private String userLevel;
	private Integer isFirstLogin;
	

	public Integer getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Integer isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}