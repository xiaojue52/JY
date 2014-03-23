/**
 * 定义一些相关常量和通用方法
 */

package com.station.constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.google.gson.Gson;
import com.station.po.JYAlarm;
import com.station.po.JYCabinetHistory;
import com.station.po.JYDevice;
import com.station.po.JYHistory;

public class Constant {
	public static long HEARTBEATTIME = 1 * 60 * 1000;
	public static final long LOOPCHECKTIME = 10000;
	public static final String ALARMTYPE1HQL = "from JYConstant key where key.type = 'AlarmType' and key.key = '1000'";
	public static final String ALARMTYPE2HQL = "from JYConstant key where key.type = 'AlarmType' and key.key = '1001'";
	public static final String ALARMTYPE3HQL = "from JYConstant key where key.type = 'AlarmType' and key.key = '1002'";
	public static final String ALARMTYPE4HQL = "from JYConstant key where key.type = 'AlarmType' and key.key = '1003'";
	public static String TOPNAME = "";
	public static String BOTTOMNAME = "";
	public static String LOGIMAGEPATH = "";
	public static String MESUSER = "";
	public static String MESDATE = "";
	public static String FUNCTIONNUM = "";

	/**
	 * 转换日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateStr(Date date, String format) {
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		str = df.format(date);
		return str;
	}

	/**
	 * 输出string到页面
	 * 
	 * @param dataMap
	 *            json格式数据
	 */
	public static void flush(Map<String, Object> dataMap) {
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

	/**
	 * 更新文件配置信息
	 * 
	 * @param path
	 *            文件路径
	 * @param nodeName
	 *            节点名称
	 * @param text
	 *            节点内容
	 * @return 成功true，错误false
	 */
	public static boolean updateConfig(String path, String nodeName, String text) {
		File nameXml = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(nameXml);
			doc.getDocumentElement().normalize();

			Element root = doc.getDocumentElement();
			Element element = (Element) root.getElementsByTagName(nodeName)
					.item(0);
			element.setTextContent(text);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer();
			OutputStream out = new FileOutputStream(path);
			trans.transform(new DOMSource(doc), new StreamResult(out));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取session中存放的string
	 * 
	 * @param arg0
	 * @return
	 */
	public static String getSessionStringAttr(String arg0) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return (String) session.getAttribute(arg0);
	}

	/**
	 * 设置sqlquery查询参数
	 * 
	 * @param arg
	 *            query
	 * @param parameters
	 *            参数map
	 * @return
	 */
	public static Query setParameters(Query arg, Map<String, Object> parameters) {

		Query query = arg;
		if (parameters == null)
			return query;
		Iterator<Map.Entry<String, Object>> iter = parameters.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Object> mEntry = (Map.Entry<String, Object>) iter
					.next();
			Object obj = (Object) mEntry.getValue();
			String key = (String) mEntry.getKey();
			query.setParameter(key, obj);
		}
		return query;
	}

	/**
	 * 讲历史温度导出excel
	 * 
	 * @param fileName
	 * @throws WriteException
	 * @throws IOException
	 */
	public static void createExcel(List<JYCabinetHistory> list, File fileName)
			throws WriteException, IOException {
		// String path =
		// ServletActionContext.getServletContext().getRealPath("/")+"files/Config.xml";
		WritableFont bold = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD);//
		WritableCellFormat titleFormate = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
		titleFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(fileName);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		Label title0 = new Label(0, 0, "采集日期", titleFormate);
		sheet.addCell(title0);

		Label title1 = new Label(1, 0, "采集时间", titleFormate);
		sheet.addCell(title1);
		Label title2 = new Label(2, 0, "线路", titleFormate);
		sheet.addCell(title2);
		Label title3 = new Label(3, 0, "柜体设备", titleFormate);
		sheet.addCell(title3);
		Label title4 = new Label(4, 0, "间隔采集器历史数据", titleFormate);
		sheet.addCell(title4);
		Label title5 = new Label(5, 0, "管理班组", titleFormate);
		sheet.addCell(title5);

		sheet.setColumnView(0, 20);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 20);
		sheet.setColumnView(3, 20);
		sheet.setColumnView(4, 50);
		sheet.setColumnView(5, 20);

		int position = 0;
		for (int i = 0; i < list.size(); i++) {
			WritableFont bold2 = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD);//
			WritableCellFormat labelFormate = new WritableCellFormat(bold2);// 生成一个单元格样式控制对象
			labelFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
			labelFormate
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
			// System.out.println(list.get(i).list.size());
			JYCabinetHistory history = list.get(i);
			Map<JYDevice, List<JYHistory>> map = history.getMap();
			int count = map.size();

			sheet.mergeCells(0, position + 1, 0, position + count);
			Label label0 = new Label(0, position + 1, Constant.getDateStr(list
					.get(i).getDate(), "yyyy-MM-dd"), labelFormate);

			sheet.mergeCells(1, position + 1, 1, position + count);
			Label label1 = new Label(1, position + 1, Constant.getDateStr(list
					.get(i).getDate(), "HH:mm:ss"), labelFormate);

			sheet.mergeCells(2, position + 1, 2, position + count);
			Label label2 = new Label(2, position + 1, list.get(i).getCabinet()
					.getLine().getName(), labelFormate);

			sheet.mergeCells(3, position + 1, 3, position + count);
			Label label3 = new Label(3, position + 1, list.get(i).getCabinet()
					.getCabNumber()
					+ history.getCabinet().getCabType().getValue(),
					labelFormate);

			sheet.addCell(label0);
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			Iterator<Map.Entry<JYDevice, List<JYHistory>>> it = map.entrySet()
					.iterator();
			int j = 0;
			while (it.hasNext()) {

				Map.Entry<JYDevice, List<JYHistory>> ent = it.next();
				JYDevice device = ent.getKey();
				List<JYHistory> hlist = ent.getValue();
				String content = device.getName() + "--";
				for (int k = 0; k < hlist.size(); k++) {
					String value = "";
					if (hlist.get(k).getValue() != null) {
						value = String.valueOf(hlist.get(k).getValue());
					}
					content = content + hlist.get(k).getDetector().getName()
							+ ":" + value;
				}
				// content.replace("null", " ");
				// System.out.println(content);
				Label label = new Label(4, position + j + 1, content,
						labelFormate);
				sheet.addCell(label);
				j++;
			}
			sheet.mergeCells(5, position + 1, 5, position + count);
			Label label5 = new Label(5, position + 1, history.getCabinet()
					.getUserGroup().getGroupName(), labelFormate);
			sheet.addCell(label5);

			position = position + count;
		}
		workbook.write();
		workbook.close();
	}

	public static void createAlarmExcel(List<JYAlarm> list,
			File fileName) throws WriteException, IOException {
		// String path =
		// ServletActionContext.getServletContext().getRealPath("/")+"files/Config.xml";
		WritableFont bold = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD);//
		WritableCellFormat titleFormate = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
		titleFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(fileName);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		Label title0 = new Label(0, 0, "报警日期", titleFormate);
		sheet.addCell(title0);

		Label title1 = new Label(1, 0, "报警时间", titleFormate);
		sheet.addCell(title1);
		Label title2 = new Label(2, 0, "报警设备", titleFormate);
		sheet.addCell(title2);
		Label title3 = new Label(3, 0, "报警内容", titleFormate);
		sheet.addCell(title3);
		
		Label title4 = new Label(4, 0, "报警类型", titleFormate);
		sheet.addCell(title4);
		Label title5 = new Label(5, 0, "依据", titleFormate);
		sheet.addCell(title5);
		
		Label title6 = new Label(6, 0, "次数", titleFormate);
		sheet.addCell(title6);
		Label title7 = new Label(7, 0, "管理班组", titleFormate);
		sheet.addCell(title7);
		Label title8 = new Label(8, 0, "状态", titleFormate);
		sheet.addCell(title8);
		Label title9 = new Label(9, 0, "确认者", titleFormate);
		sheet.addCell(title9);
		Label title10 = new Label(10, 0, "维修备注", titleFormate);
		sheet.addCell(title10);

		sheet.setColumnView(0, 20);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 20);
		sheet.setColumnView(3, 20);
		sheet.setColumnView(4, 20);
		sheet.setColumnView(5, 20);
		sheet.setColumnView(6, 20);
		sheet.setColumnView(7, 20);
		sheet.setColumnView(8, 20);
		sheet.setColumnView(9, 20);
		sheet.setColumnView(10, 20);

		for (int i = 0; i < list.size(); i++) {
			WritableFont bold2 = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD);//
			WritableCellFormat labelFormate = new WritableCellFormat(bold2);// 生成一个单元格样式控制对象
			labelFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
			labelFormate
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
			// System.out.println(list.get(i).list.size());
			JYAlarm alarm = list.get(i);

			Label label0 = new Label(0, i + 1, Constant.getDateStr(list
					.get(i).getDate(), "yyyy-MM-dd"), labelFormate);

			Label label1 = new Label(1, i + 1, Constant.getDateStr(list
					.get(i).getDate(), "HH:mm:ss"), labelFormate);

			Label label2 = null;
			if (alarm.getIsCabinet().equals("0")){
				label2 = new Label(2, i + 1, alarm.getDevice().getCabinet().getLine().getName()+
						alarm.getDevice().getCabinet().getCabNumber()+
						alarm.getDevice().getCabinet().getCabType().getValue()+
						alarm.getDevice().getName(),
						labelFormate);
			}else{
				label2 = new Label(2, i + 1, alarm.getDevice().getCabinet().getLine().getName()+
						alarm.getDevice().getCabinet().getCabNumber()+
						alarm.getDevice().getCabinet().getCabType().getValue(),
						labelFormate);
			}
			Label label3 = new Label(3, i + 1, alarm.getAlarmText(), labelFormate);
			String type = "";
			if (alarm.getIsCabinet().equals("0")){
				type = "温度异常";
			}else
				type = "设备故障";
			Label label4 = new Label(4, i + 1, type, labelFormate);
			Label label5 = new Label(5, i + 1, alarm.getCondition(), labelFormate);
			
			
			Label label6 = new Label(6, i + 1, String.valueOf(alarm.getTimes()), labelFormate);
			Label label7 = new Label(7, i + 1, alarm.getDevice().getCabinet().getUserGroup().getGroupName(), labelFormate);
			Label label8 = new Label(8, i + 1, alarm.getStatus(), labelFormate);
			Label label9 = new Label(9, i + 1, alarm.getRepairUser(), labelFormate);
			Label label10 = new Label(10, i + 1, alarm.getNote(), labelFormate);
			sheet.addCell(label0);
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			sheet.addCell(label7);
			sheet.addCell(label8);
			sheet.addCell(label9);
			sheet.addCell(label10);

		}
		workbook.write();
		workbook.close();
	}
}
