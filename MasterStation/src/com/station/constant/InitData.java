package com.station.constant;

import com.station.md5.MD5;
import com.station.po.JYAlarmType;
import com.station.po.JYConstant;
import com.station.po.JYKeyGenerator;
import com.station.po.JYUser;
import com.station.service.JYAlarmTypeService;
import com.station.service.JYConstantService;
import com.station.service.JYKeyGeneratorService;
import com.station.service.JYUserService;

public class InitData {
	private JYUserService userService;
	private JYKeyGeneratorService keyGeneratorService;
	private JYConstantService constantService;
	private JYAlarmTypeService alarmTypeService;
	
	public void setAlarmTypeService(JYAlarmTypeService alarmTypeService) {
		this.alarmTypeService = alarmTypeService;
	}
	public void setConstantService(JYConstantService constantService) {
		this.constantService = constantService;
	}
	public void setKeyGeneratorService(JYKeyGeneratorService keyGeneratorService) {
		this.keyGeneratorService = keyGeneratorService;
	}
	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}
	public void initUserTable(){
		String hql = "from JYUser user where user.username = 'admin'";
		if (this.userService.findUserByHql(hql).size()==0){
			JYUser user = new JYUser();
			user.setUsername("admin");
			user.setPassword(MD5.CreateMD5String("admin"));
			user.setUserLevel("super_admin");
			this.userService.saveUser(user);
		}
	}
	public void initKeyGeneratorTable(){
		String hql = "from JYKeyGenerator key";
		if (this.keyGeneratorService.findJYKeyGeneratorByHql(hql).size()==0){
			JYKeyGenerator key = new JYKeyGenerator();
			key.setCabId(1);
			key.setDetectorId(1);
			key.setDeviceId(1);
			key.setLineId(1);
			key.setUserId(1);
			this.keyGeneratorService.saveJYKeyGenerator(key);
		}
	}
	public void initConstantTable(){
		String hql = "from JYConstant key";
		if (this.constantService.findJYConstantByHql(hql).size()==0){
			JYConstant key = new JYConstant();
			key.setType("CabType");
			key.setKey("1000");
			key.setValue("环网柜");
			this.constantService.saveJYConstant(key);
			key.setType("CabType");
			key.setKey("1001");
			key.setValue("分段柜");
			this.constantService.saveJYConstant(key);
			key.setType("CabType");
			key.setKey("1002");
			key.setValue("高分箱");
			this.constantService.saveJYConstant(key);
			key.setType("CabType");
			key.setKey("1003");
			key.setValue("变电柜");
			this.constantService.saveJYConstant(key);
			key.setType("CabType");
			key.setKey("1004");
			key.setValue("配电");
			this.constantService.saveJYConstant(key);
			
			key.setType("PowerLevel");
			key.setKey("1000");
			key.setValue("20KV");
			this.constantService.saveJYConstant(key);
			key.setType("PowerLevel");
			key.setKey("1001");
			key.setValue("110KV");
			this.constantService.saveJYConstant(key);
			key.setType("PowerLevel");
			key.setKey("1002");
			key.setValue("50KV");
			this.constantService.saveJYConstant(key);
			
			key.setType("AlarmType");
			key.setKey("1000");
			key.setValue("报警温度超出设定值（T1°）");
			this.constantService.saveJYConstant(key);
			key.setType("AlarmType");
			key.setKey("1001");
			key.setValue("三相之间温差超出设定值（T2°）");
			this.constantService.saveJYConstant(key);
			key.setType("AlarmType");
			key.setKey("1002");
			key.setValue("三相与环境温差超出设定值（T3°）");
			this.constantService.saveJYConstant(key);
		}
	}
	public void initAlarmTypeTable(){
		String hql = "from JYAlarmTypeTable key";
		if (this.alarmTypeService.findJYAlarmTypeByHql(hql).size()==0){
			JYAlarmType key = new JYAlarmType();
			//to-do
		}
	}

}
