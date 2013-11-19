package com.station.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.station.dao.impl.DeviceDAOImpl;
import com.station.po.Device;

@SuppressWarnings("serial")
public class CurrentData extends HttpServlet {
	private static DeviceDAOImpl dao;
	private List<Device> list;

	public static void setDao(DeviceDAOImpl daoI) {
		dao = daoI;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		list = dao.findAllDevice();
		
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out=response.getWriter();
		//String old= request.getParameter("name");
		
		String data = null;
		for (int i=0;i<list.size();i++){
			String divId = list.get(i).getName()+list.get(i).getDeviceBox();
			double divValue = list.get(i).getCurrentData();
			String status = list.get(i).getStatus();
			if (data == null){
				data = "{'name':'"+divId+"','value':'"+divValue+"','status':'"+status+"'}";
			}
			else
				data =data+ ",{'name':'"+divId+"','value':'"+divValue+"','status':'"+status+"'}";
		}
		data = "["+data+"]";
		System.out.print("\n"+data);
		out.println(data);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
}
