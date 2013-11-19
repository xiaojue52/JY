package com.station.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.station.constant.ParseStringToDecimal;
import com.station.service.UnhandledExceptionService;
import com.station.tree.TreeService;

@SuppressWarnings("serial")
public class GetDateByServlet extends HttpServlet {

	private static UnhandledExceptionService unhandledExceptionService;
	private List<com.station.po.UnhandledException> list;


	public static void setUnhandledExceptionService(
			UnhandledExceptionService unhandledExceptionService) {
		GetDateByServlet.unhandledExceptionService = unhandledExceptionService;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) req.getSession().getAttribute("username");
		String tag = (String) req.getSession().getAttribute("username");
		String method = req.getParameter("method");
		String id = req.getParameter("id");
		int identify = 0;
		if (ParseStringToDecimal.isInteger(id)) {
			identify = Integer.valueOf(id);
		}

		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		if (username != null) {
			if (method != null && method.equals("getCurrentData")) {
				PrintWriter out = resp.getWriter();
				out.println(getCurrentData(resp, tag, username));
				// System.out.print("qqqq");
				return;
			}
			if (id != null && method != null && method.equals("getGprsById")) {
				// int identify = Integer.valueOf(id);
				String data = "this.getGprsById(identify)";
				PrintWriter out = resp.getWriter();
				out.println(data);
				// System.out.print("qqqq");
				return;
			}
			if (id != null && method != null
					&& method.equals("getDeviceBoxById")) {
				// int identify = Integer.valueOf(id);
				String data = "this.getDeviceBoxById(identify)";

				PrintWriter out = resp.getWriter();
				out.println(data);
				// System.out.print("qqqq");
				return;
			}
			if (id != null && id.length() > 0 && method == null) {
				/*System.out.print("tree sevlet id = "+id+"\n");
				TreeService service = new TreeService();
				try {
					//resp.getWriter().write(service.getTreeNodeS(id));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;*/
			}
		}
		// super.doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	private int getCurrentData(HttpServletResponse resp, String tag,
			String username) {
		int count = 0;
		if (tag != null && tag.equals("admin"))
			list = unhandledExceptionService.findAllDevice();
		else if (tag != null && username != null) {
			list = unhandledExceptionService.findDevicesByOwner(username);
		}
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		count = list.size();
		return count;
	}

	/*private String getDeviceBoxById(int id) {
		DeviceBox deviceBox = deviceBoxService.findDeviceBoxById(id);
		if (deviceBox == null)
			return "0";
		String data = null;
		data = "{" + "'id':'" + deviceBox.getId() + "'," + "'name':'"
				+ deviceBox.getName() + "'," + "'identify':'"
				+ deviceBox.getIdentify() + "'," + "'type':'"
				+ deviceBox.getType() + "'," + "'owner':'"
				+ deviceBox.getOwner() + "'," + "'deviceNumber':'"
				+ deviceBox.getDeviceNumber() + "'," + "'powerLevel':'"
				+ deviceBox.getNote() + "'," + "'note':'" + deviceBox.getNote()
				+ "'}";
		return data;
	}*/

	/*private String getGprsById(int id) {
		Gprs gprs = gprsService.findGprsById(id);
		if (gprs == null)
			return "0";
		String data = null;
		data = "{" + "'id':'" + gprs.getId() + "'," + "'number':'"
				+ gprs.getNumber() + "'," + "'comNumber':'"
				+ gprs.getComNumber() + "'," + "'deviceBox':'"
				+ gprs.getDeviceBox() + "'," + "'owner':'" + gprs.getOwner()
				+ "'," + "'status':'" + gprs.getStatus() + "'," + "'note':'"
				+ gprs.getNote() + "'}";
		return data;
	}*/

}
