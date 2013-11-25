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


public class SocketListener extends Thread {
	private ServerSocket server = null;
	/*private final int port = 10000;
	
	// @SuppressWarnings("unchecked")
	private Map<String, Socket> clientMap = new HashMap<String, Socket>();


	public SocketListener() {
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

		while (true) {
			Socket socket = null;
			try {
				socket = server.accept();
				//list.add(socket);

				invoke(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("socket accept失败！");
				e.printStackTrace();
			}
		}
	}

	private void invoke(final Socket client) throws IOException {
		new Thread(new Runnable() {

			public void run() {
				BufferedReader in = null;
				PrintWriter out = null;
				try {
					in = new BufferedReader(new InputStreamReader(client
							.getInputStream()));
					out = new PrintWriter(client.getOutputStream());
					out.println("wellcome!");
					out.flush();
					String loginMes = in.readLine();
					if(ParseSocketData.CheckDevice(loginMes))
						System.out.print("连接成功");
					else{
						in.close();
						out.close();
						removedSocket(client);
						return;
					}
						
					while (true) {
						String msg = in.readLine();
						if (msg == null) {
							System.out.print("socket 断开");
							in.close();
							out.close();
							removedSocket(client);
							break;
						}
						String cmd[] = msg.split(":");
						if (cmd != null && cmd.length > 1) {
							String identify = cmd[0];
							String currentData = cmd[1];
							clientMap.put(identify, client);
							ParseSocketData.updateDevice(identify, currentData);
						}
						else
							break;
						
						// System.out.println(msg);
						out.println("Server received " + msg);
						out.flush();
						if (msg.equals("bye")) {
							break;
						}
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
			}

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void sendCommand() {
		PrintWriter out = null;
		Iterator<?> iter = clientMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String,Socket> mEntry = (Map.Entry<String, Socket>) iter.next();
			Socket socket = (Socket) mEntry.getValue();
			try {
				out = new PrintWriter(socket.getOutputStream());
				out.print("give me temprature, please\n\t");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	public void removedSocket(Socket socket) {
		Iterator<?> iter = clientMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String,Socket> mEntry = (Map.Entry<String, Socket>) iter.next();
			Socket temp = (Socket) mEntry.getValue();
			String key = (String) mEntry.getKey();
			if (temp == socket){
				clientMap.remove(key);
				ParseSocketData.deviceOffline(key);
			}
		}
	}

	// @SuppressWarnings("unchecked")
	public void sendCommandWithIdentify(String identify) {
		PrintWriter out = null;
		Socket client = clientMap.get(identify);
		if (client != null) {
			try {
				out = new PrintWriter(client.getOutputStream());
				out.print("\nget " + identify + " temprature!");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
}
