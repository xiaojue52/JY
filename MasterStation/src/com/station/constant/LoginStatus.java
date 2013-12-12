package com.station.constant;

import java.io.File;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.station.po.JYUser;

public class LoginStatus {
	public static boolean isLogined(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		if (session.getAttribute("username")==null)
			return false;
		else 
			return true;
	}
	
	public static void initData(JYUser user){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		session.setAttribute("username", user.getUsername());
		session.setAttribute("userLevel", user.getUserLevel());
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("isFirstLogin", user.getIsFirstLogin());
		//session.setAttribute("password", user.getPassword());
		String path = ServletActionContext.getServletContext().getRealPath("/");
		File nameXml = new File(path+"files/NameConfig.xml");
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
			session.setAttribute("topContent", topContent.getTextContent());
			session.setAttribute("bottomContent", bottomContent.getTextContent());
			session.setAttribute("imagePath", imagePath.getTextContent());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void destroyData(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		session.setAttribute("username", null);
		session.setAttribute("userLevel", null);
		session.setAttribute("userId", null);
		session.setAttribute("isFirstLogin", null);
		//session.setAttribute("password", null);
	}
	public static int checkUserAccess(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userLevel = (String)session.getAttribute("userLevel");
		if (userLevel!=null&&userLevel.equals("super_admin")){
			return 1;
		}
		if (userLevel!=null&&userLevel.equals("user")){
			return 3;
		}
		if (userLevel!=null&&userLevel.equals("com_admin")){
			return 2;
		}
		return -1;
	}
	public static String getUsername(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		return (String) session.getAttribute("username");
	}
}
