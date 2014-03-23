package com.station.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYConstant;
import com.station.service.JYConstantService;

public class CabTypeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JYConstantService constantService;
	private JYConstant cabType;
	private List<JYConstant> list;
	private int code = 0;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public JYConstantService getConstantService() {
		return constantService;
	}
	public void setConstantService(JYConstantService constantService) {
		this.constantService = constantService;
	}
	public JYConstant getCabType() {
		return cabType;
	}
	public void setCabType(JYConstant cabType) {
		this.cabType = cabType;
	}
	public List<JYConstant> getList() {
		return list;
	}
	public void setList(List<JYConstant> list) {
		this.list = list;
	}
	
	public String listCabTypesAction(){
		String hql = "from JYConstant cabType where cabType.type ='CabType' ORDER BY cabType.id DESC";
		this.list = this.constantService.findJYConstantByHql(hql);
		return SUCCESS;
	}
	public void showCabTypeAction(){
		this.cabType = this.constantService.findJYConstantById(this.cabType.getId());
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("data", this.cabType);
		Constant.flush(dataMap);
	}
	public String deleteCabTypeAction(){
		int tag = this.constantService.removeJYConstant(this.cabType);
		if (tag==1)
			return SUCCESS;
		else
			return ERROR;
	}
	public String addCabTypeAction(){
		this.constantService.saveJYConstant(this.cabType);
		return SUCCESS;
	}
	public String updateCabTypeAction(){
		String value = this.cabType.getValue();
		this.cabType = this.constantService.findJYConstantById(this.cabType.getId());
		this.cabType.setValue(value);
		this.constantService.updateJYConstant(this.cabType);
		return SUCCESS;
	}
}
