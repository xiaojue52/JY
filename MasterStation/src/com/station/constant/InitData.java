/**
 * 启动服务时候会将初始化数据
 */
package com.station.constant;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.station.md5.MD5;
import com.station.po.JYAlarmType;
import com.station.po.JYAlarmTypeCollect;
import com.station.po.JYConstant;
import com.station.po.JYKeyGenerator;
import com.station.po.JYUser;
import com.station.po.JYUserGroup;
import com.station.service.JYAlarmTypeCollectService;
import com.station.service.JYAlarmTypeService;
import com.station.service.JYConstantService;
import com.station.service.JYKeyGeneratorService;
import com.station.service.JYUserGroupService;
import com.station.service.JYUserService;

public class InitData {
	private JYUserService userService;
	private JYKeyGeneratorService keyGeneratorService;
	private JYConstantService constantService;
	private JYAlarmTypeService alarmTypeService;
	private JYAlarmTypeCollectService alarmTypeCollectService;
	private JYUserGroupService userGroupService;
	
	public void setUserGroupService(JYUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	public void setAlarmTypeCollectService(
			JYAlarmTypeCollectService alarmTypeCollectService) {
		this.alarmTypeCollectService = alarmTypeCollectService;
	}
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
	private void initUserGroupTable(){
		String hql = "from JYUserGroup userGroup where userGroup.groupName = '--'";
		if (this.userGroupService.findJYUserGroupByHql(hql,null).size()==0){
			JYUserGroup userGroup = new JYUserGroup();
			userGroup.setGroupName("--");
			userGroup.setLeaderName("--");
			this.userGroupService.saveJYUserGroup(userGroup);
		}
	}
	private void initUserTable(){
		String hql = "from JYUser user where user.username = 'admin'";
		if (this.userService.findUserByHql(hql,null).size()==0){
			JYUser user = new JYUser();
			user.setUsername("admin");
			user.setIsFirstLogin(1);
			user.setPassword(MD5.CreateMD5String("admin"));
			user.setUserLevel("super_admin");
			this.userService.saveUser(user);
			user.setUsername("--");
			user.setPassword(MD5.CreateMD5String("--------"));
			user.setUserLevel("com_admin");
			String hql0 = "from JYUserGroup userGroup where userGroup.groupName = '--'";
			user.setUserGroup(this.userGroupService.findJYUserGroupByHql(hql0,null).get(0));
			this.userService.saveUser(user);
		}
	}
	private void initKeyGeneratorTable(){
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
	private void initConstantTable(){
		String hql = "from JYConstant key";
		if (this.constantService.findJYConstantByHql(hql).size()==0){
			JYConstant key = new JYConstant();
			key.setType("CabType");
			key.setKey("1000");
			key.setValue("环网柜");
			key.setSubValue("15");
			this.constantService.saveJYConstant(key);
			key.setType("CabType");
			key.setKey("1001");
			key.setValue("分段柜");
			key.setSubValue("15");
			this.constantService.saveJYConstant(key);
			key.setType("CabType");
			key.setKey("1002");
			key.setValue("高分箱");
			key.setSubValue("15");
			this.constantService.saveJYConstant(key);
			key.setType("CabType");
			key.setKey("1003");
			key.setValue("变电柜");
			key.setSubValue("15");
			this.constantService.saveJYConstant(key);
			key.setType("CabType");
			key.setKey("1004");
			key.setValue("配电");
			key.setSubValue("15");
			this.constantService.saveJYConstant(key);
			
			key.setType("PowerLevel");
			key.setKey("1002");
			key.setValue("10KV");
			this.constantService.saveJYConstant(key);
			
			key.setType("PowerLevel");
			key.setKey("1002");
			key.setValue("0.4KV");
			this.constantService.saveJYConstant(key);
			key.setType("PowerLevel");
			key.setKey("1003");
			key.setValue("35KV");
			this.constantService.saveJYConstant(key);
			key.setType("PowerLevel");
			key.setKey("1004");
			key.setValue("66KV");
			this.constantService.saveJYConstant(key);
			key.setType("PowerLevel");
			key.setKey("1005");
			key.setValue("110KV");
			this.constantService.saveJYConstant(key);
			key.setType("PowerLevel");
			key.setKey("1006");
			key.setValue("220KV");
			this.constantService.saveJYConstant(key);
			key.setType("PowerLevel");
			key.setKey("1007");
			key.setValue("其他");
			this.constantService.saveJYConstant(key);
			
			key.setType("AlarmType");
			key.setKey("1000");
			key.setValue("报警温度超出设定值（T1℃）");
			this.constantService.saveJYConstant(key);
			key.setType("AlarmType");
			key.setKey("1001");
			key.setValue("三相之间温差超出设定值（T2℃）");
			this.constantService.saveJYConstant(key);
			key.setType("AlarmType");
			key.setKey("1002");
			key.setValue("三相与环境温差超出设定值（T3℃）");
			this.constantService.saveJYConstant(key);
			key.setType("AlarmType");
			key.setKey("1003");
			key.setValue("特定间隔（T4m）时间内温度变化超过设定值（T5℃）");
			this.constantService.saveJYConstant(key);
		}
	}
	private void initAlarmTypeTable(){
		String hql = "from JYAlarmType key";
		if (this.alarmTypeService.findJYAlarmTypeByHql(hql).size()==0){
			JYAlarmType key = new JYAlarmType();
			JYConstant type = null;
			type = this.constantService.findJYConstantByHql(Constant.ALARMTYPE1HQL).get(0);
			key.setId("-11000");
			key.setEnable(1);
			key.setValue(15.0f);
			key.setType(type);
			this.alarmTypeService.saveJYAlarmType(key);
			
			type = this.constantService.findJYConstantByHql(Constant.ALARMTYPE2HQL).get(0);
			key.setId("-11001");
			key.setEnable(1);
			key.setValue(25.0f);
			key.setType(type);
			this.alarmTypeService.saveJYAlarmType(key);
			
			type = this.constantService.findJYConstantByHql(Constant.ALARMTYPE3HQL).get(0);
			key.setId("-11002");
			key.setEnable(1);
			key.setValue(35.0f);
			key.setType(type);
			this.alarmTypeService.saveJYAlarmType(key);
			
			type = this.constantService.findJYConstantByHql(Constant.ALARMTYPE4HQL).get(0);
			key.setId("-11003");
			key.setEnable(1);
			key.setValue(10.0f);
			key.setSubValue(15);
			key.setType(type);
			this.alarmTypeService.saveJYAlarmType(key);
		}
	}
	private void initAlarmTypeCollectTable(){
		String hql = "from JYAlarmTypeCollect key";
		if (this.alarmTypeCollectService.findJYAlarmTypeCollectByHql(hql).size()==0){
			JYAlarmTypeCollect key = new JYAlarmTypeCollect();
			JYAlarmType type1 = this.alarmTypeService.findJYAlarmTypeById("-11000");
			JYAlarmType type2 = this.alarmTypeService.findJYAlarmTypeById("-11001");
			JYAlarmType type3 = this.alarmTypeService.findJYAlarmTypeById("-11002");
			JYAlarmType type4 = this.alarmTypeService.findJYAlarmTypeById("-11003");
			
			key.setId("-1");
			key.setAlarmType1(type1);
			key.setAlarmType2(type2);
			key.setAlarmType3(type3);
			key.setAlarmType4(type4);
			this.alarmTypeCollectService.saveJYAlarmTypeCollect(key);
		}
	}
	public void init(ServletContextEvent event){
		this.initTable();
		this.readCondig(event);
	}
	private void initTable(){
		initUserGroupTable();
		initConstantTable();
		initKeyGeneratorTable();
		initUserTable();
		initAlarmTypeTable();
		initAlarmTypeCollectTable();
	}
	private void readCondig(ServletContextEvent event){
		String path = event.getServletContext().getRealPath("/");
		File nameXml = new File(path+"files/Config.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(nameXml);
			doc.getDocumentElement().normalize();

			Element root = doc.getDocumentElement();
			Element topContent = (Element) root.getElementsByTagName("topContent").item(0);
			Element bottomContent = (Element) root.getElementsByTagName("bottomContent").item(0);
			Element imagePath = (Element) root.getElementsByTagName("imagePath").item(0);
			Element mesDate = (Element) root.getElementsByTagName("mesDate").item(0);
			Element mesUser = (Element) root.getElementsByTagName("mesUser").item(0);
			Element functionNum = (Element) root.getElementsByTagName("functionNum").item(0);
			Element heartBeatTime = (Element)root.getElementsByTagName("heartBeatTime").item(0);
			Constant.TOPNAME =  topContent.getTextContent();
			Constant.BOTTOMNAME = bottomContent.getTextContent();
			Constant.LOGIMAGEPATH = imagePath.getTextContent();
			Constant.MESUSER =  mesUser.getTextContent();
			Constant.MESDATE = mesDate.getTextContent();
			Constant.FUNCTIONNUM = functionNum.getTextContent();
			Constant.HEARTBEATTIME = Long.valueOf(heartBeatTime.getTextContent());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
