package com.station.constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.Gson;

public class Constant {
	public static String CODEERROR = "-2";
	public static String REALTEMPERROR = "-3";
	public static String OK = "1";
	public static long HEARTBEATTIME = 1*60*1000;
	public static final long LOOPCHECKTIME = 1000;
	public static final long RECIVETEMPTIME = 1*60*1000;
	public static final String ALARMTYPE1HQL = "from JYConstant key where key.type = 'AlarmType' and key.key = '1000'";
	public static final String ALARMTYPE2HQL = "from JYConstant key where key.type = 'AlarmType' and key.key = '1001'";
	public static final String ALARMTYPE3HQL = "from JYConstant key where key.type = 'AlarmType' and key.key = '1002'";
	public static String TOPNAME = "";
	public static String BOTTOMNAME = "";
	public static String LOGIMAGEPATH ="";
	public static String MESUSER = "";
	public static String MESDATE = "";
	public static String FUNCTIONNUM = "";
	public static String getCurrentDateStr() {
		 Date date = new Date();
		 String str = null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		 str = df.format(date);
		 return str;
	}
	public static void flush(Map<String,Object> dataMap){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			Gson gson = new Gson();
			String jsonString = gson.toJson(dataMap);
			out.println(jsonString);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean updateConfig(String path,String nodeName,String text){
		File nameXml = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(nameXml);
			doc.getDocumentElement().normalize();

			Element root = doc.getDocumentElement();
			Element element = (Element) root.getElementsByTagName(nodeName).item(0);
			element.setTextContent(text);
			TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer trans = tf.newTransformer();
	        OutputStream out = new FileOutputStream(path);
	        trans.transform(new DOMSource(doc), new StreamResult(out));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
	}
}
