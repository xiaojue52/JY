package com.station.query.column;

import java.util.ArrayList;
import java.util.List;

public class SqlQueryColumn {

	// 查询字段
	private String name;
	private String deviceBox;
	private String identify;
	private String startDate;
	private String endDate;
	private String number;
	private String username;
	private String company;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeviceBox() {
		return deviceBox;
	}

	public void setDeviceBox(String deviceBox) {
		this.deviceBox = deviceBox;
	}

	//@SuppressWarnings("unchecked")
	

	private class Column {
		public String column;
		public String value;
	}
	private List<Column> getList() {
		List<Column> list = new ArrayList<Column>();
		Column column;
		column = new Column();
		column.column = "name";
		column.value = this.name;
		list.add(column);
		column = new Column();
		column.column = "deviceBox";
		column.value = this.deviceBox;
		list.add(column);
		
		column = new Column();
		column.column = "number";
		column.value = this.number;
		list.add(column);
		
		column = new Column();
		column.column = "company";
		column.value = this.company;
		list.add(column);
		
		column = new Column();
		column.column = "username";
		column.value = this.username;
		list.add(column);
		return list;
	}
	private class IdColumn {
		public String idColumn;
		public String value;
	}

	//@SuppressWarnings("unchecked")
	private List<IdColumn> getIdList() {
		List<IdColumn> list = new ArrayList<IdColumn>();
		IdColumn column;
		column = new IdColumn();
		column.idColumn = "identify";
		column.value = this.identify;
		list.add(column);
		return list;
	}

	private class DateColumn{
		public String startDate;
		public String endDate;
	}
	private DateColumn getDateColumn() {
		DateColumn dateColumn;
		dateColumn = new DateColumn();
		dateColumn.startDate = this.startDate;
		dateColumn.endDate = this.endDate;
		return dateColumn;
	}
	//@SuppressWarnings("unchecked")
	public String getQueryString() {
		String query = null;
		List<Column> list = this.getList();
		List<IdColumn> idList = this.getIdList();
		
		int tag = 0;
		String temp = null;
		for (int i = 0; i < list.size(); i++) {
			Column column = (Column) list.get(i);
			if (column.value != null && column.value.length() > 0) {
				tag = 1;
				if (temp == null) {
					temp = column.column + " like '%" + column.value + "%'";
				} else
					temp = temp + " and " + column.column + " like '%"
							+ column.value + "%'";
			}
		}

		int tag2 = 0;
		String temp2 = null;
		for (int i = 0; i < idList.size(); i++) {
			IdColumn column = (IdColumn) idList.get(i);
			if (column.value != null && column.value.length() > 0) {
				tag2 = 1;
				if (temp2 == null) {
					temp2 = column.idColumn + " = '" + column.value + "'";
				} else
					temp2 = temp2 + " and " + column.idColumn + " = '"
							+ column.value + "'";
			}
		}
		
		String temp3 = null;
		DateColumn dateColumn = this.getDateColumn();
		if (dateColumn!=null){
			//TO_DATE('"+startDate+"','YYYY-MM-DD')
			if (dateColumn.startDate != null && dateColumn.startDate.length() > 0&&dateColumn.endDate != null && dateColumn.endDate.length() > 0) {
				temp3 =" create_date >= TO_DATE('" + dateColumn.startDate + "','YYYY-MM-DD') and "+"create_date <= TO_DATE('"+dateColumn.endDate+"','YYYY-MM-DD')";
			}
			else if (dateColumn.startDate != null && dateColumn.startDate.length() > 0) {
				temp3 =" create_date >= TO_DATE('" + dateColumn.startDate + "','YYYY-MM-DD')";
			}
			else if (dateColumn.endDate != null && dateColumn.endDate.length() > 0) {
				temp3 =" create_date <= TO_DATE('" + dateColumn.endDate + "','YYYY-MM-DD')";
			}
		}
		
		if (tag == 1 && tag2 == 1)
			query = "where " + temp + " and " + temp2;
		else if (tag == 1)
			query = "where " + temp;
		else if (tag2 == 1)
			query = "where " + temp2;
		tag = 0;
		tag2 = 0;
		if (temp3 !=null&&query!=null)
			query = query + temp3;
		else if (temp3 !=null&&query ==null)
			query = "where " + temp3;
		return query;
	}
}
