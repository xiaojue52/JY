package com.station.po;

public class JYAlarmType {
	private String id;
	private JYConstant type;
	private Float value;
	private Integer enable;
	private Integer isDefault;
	private Integer subValue;

	public Integer getSubValue() {
		return subValue;
	}
	public void setSubValue(Integer subValue) {
		this.subValue = subValue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public JYConstant getType() {
		return type;
	}
	public void setType(JYConstant type) {
		this.type = type;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}