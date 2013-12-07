package com.station.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContextEvent;

public class SocketListener extends Thread {
	private ServerSocket server = null;
	private final int port = 10000;
	private ParseSocketData parseSocketData;
	//private ServletContextEvent sce;

	private Map<Socket, Socket> listMap = new HashMap<Socket, Socket>();
	//private Map<String, String> order = new HashMap<String, String>();
	//private Map<String, Map<String, String>> orderMap = new HashMap<String, Map<String, String>>();

	public SocketListener(ServletContextEvent sce) {
		//this.sce = sce;
		parseSocketData = new ParseSocketData(sce);
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("创建socket失败！");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.print("进入run方法\n");
		while (true) {
			Socket client = null;
			try {
				client = server.accept();
				listMap.put(client,client);
				invoke(client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				break;
				//e.printStackTrace();
			}
		}
	}

	private void invoke(final Socket client) {
		new Thread(new Runnable() {
			
			public void run() {
				BufferedReader in = null;
				PrintWriter out = null;
				try {
					in = new BufferedReader(new InputStreamReader(client
							.getInputStream()));
					out = new PrintWriter(client.getOutputStream());
					while (client.isConnected()) {
						String str = "";
						char[] cbuf = new char[1024];
						int ret = in.read(cbuf, 0, 1024);
						if (ret < 0) {
							in.close();
							out.close();
							listMap.get(client).close();
							parseSocketData.removedSocket(client);
							break;
						}
						if (ret >= 1024)
							cbuf[1023] = '\0';
						else
							cbuf[ret] = '\0';
						str = String.valueOf(cbuf, 0, ret);
						String retStr = parseSocketData.CheckString(str,client);
						out.println(retStr);
						out.flush();
					}
				} catch (IOException ex) {
					System.out.print("socket 读取 、输出失败！");
					// ex.printStackTrace();
				} finally {
					try {
						in.close();
					} catch (Exception e) {
						System.out.print("socket 读取 close失败！");
					}
					try {
						out.close();
					} catch (Exception e) {
						System.out.print("socket 输出 close失败！");
					}
					try {
						client.close();
					} catch (Exception e) {
						System.out.print("socket 关闭失败！");
					}
				}
			}

		}).start();
	}

	public void closeSocketServer() {
		try {
			if (null != server && !server.isClosed()) {
				server.close();
				Iterator<Map.Entry<Socket, Socket>> iter = listMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<Socket, Socket> mEntry = (Map.Entry<Socket, Socket>) iter
							.next();
					Socket client = (Socket) mEntry.getValue();
					//Socket key = (Socket) mEntry.getKey();
					client.close();	
				}
			}

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
}
