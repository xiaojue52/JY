/**
 * 负责监听socket连接，
 * 每次有连接进来，新建一个线程去处理
 */
package com.station.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContextEvent;

public class SocketListener extends Thread {
	//private SocketLog socketLog;
	private ServerSocket server = null;
	private final int port = 10000;
	private SocketRoute socketRoute;
	private Map<Socket, Socket> listMap = Collections
			.synchronizedMap(new HashMap<Socket, Socket>()); // 当前连入系统的所有socket的，包括非法的

	/**
	 * 构造函数
	 * 
	 * @param sce
	 */
	public SocketListener(ServletContextEvent sce) {
		socketRoute = new SocketRoute(sce);
		//socketLog = new SocketLog(sce);
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("创建socket失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 监听socket连接
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.print("进入run方法\n");
		while (true) {
			Socket client = null;
			try {
				client = server.accept();
				synchronized (this.listMap) {
					listMap.put(client, client);
				}
				invoke(client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				break;
				// e.printStackTrace();
			}
		}
	}

	/**
	 * 开启新线程，处理socket相关
	 * 
	 * @param client
	 */
	private void invoke(final Socket client) {
		new Thread(new Runnable() {

			public void run() {
				BufferedReader in = null;
				System.out.print("连接数:" + listMap.size() + "\n");
				try {
					in = new BufferedReader(new InputStreamReader(
							client.getInputStream()));
					while (client.isConnected()) {
						StringBuilder sb = new StringBuilder();
						int c;
						while ((c = in.read()) != -1) {
							sb.append((char) c);
							String str = sb.toString();
							System.out.print((char)c);
							//if (str.length()>0&&str.substring(str.length()-1).equals(" "))sb.setLength(0);
							if (str.length() > 7
									&& str.substring(str.length() - 2).equals(
											"CR")) {
								socketRoute.CheckString(str, client);
								sb.setLength(0);
								//socketLog.saveLog(str);
							}
							
						}
						removedClient(client, in);
						break;
					}
				} catch (IOException ex) {
					// ex.printStackTrace();
					removedClient(client, in);
				}
			}

		}).start();
	}

	/**
	 * 关闭服务器，断开所有连接
	 */
	public void closeSocketServer() {
		try {
			System.out.print("正在关闭socket\n");
			if (null != server && !server.isClosed()) {
				server.close();
				Iterator<Map.Entry<Socket, Socket>> iter = listMap.entrySet()
						.iterator();
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

	/**
	 * 终端断开连接，移除listMap内存中保存的相关信息
	 * 
	 * @param client
	 * @param in
	 */
	private void removedClient(Socket client, BufferedReader in) {
		socketRoute.removedSocket(client);
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			synchronized (this.listMap) {
				if (listMap.get(client) != null
						&& !listMap.get(client).isClosed())
					listMap.get(client).close();
				listMap.remove(client);
				System.out.print("\n连接数:" + listMap.size() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
