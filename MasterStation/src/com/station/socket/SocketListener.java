package com.station.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContextEvent;

public class SocketListener extends Thread {
	private ServerSocket server = null;
	private final int port = 10000;
	private SocketRoute socketRoute;
	private Map<Socket, Socket> listMap = new HashMap<Socket, Socket>(); //当前连入系统的所有socket的，包括非法的

	public SocketListener(ServletContextEvent sce) {
		//this.sce = sce;
		socketRoute = new SocketRoute(sce);
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
				try {
					in = new BufferedReader(new InputStreamReader(client
							.getInputStream()));
					while (client.isConnected()) {
						String str = "";
						char[] cbuf = new char[1024];
						int ret = in.read(cbuf, 0, 1024);
						if (ret < 0) {
							in.close();
							listMap.get(client).close();
							socketRoute.removedSocket(client);
							break;
						}
						if (ret >= 1024)
							cbuf[1023] = '\0';
						else
							cbuf[ret] = '\0';
						str = String.valueOf(cbuf, 0, ret);
						socketRoute.CheckString(str,client);
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
					client.close();	
				}
				socketRoute.stop();
			}

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
}
