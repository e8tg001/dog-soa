/**
 * 
 */
package com.dog.soa.demo.netty;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 网络服务端
 * <p>Title: SocketService.java</p>
 * <p>Description: 安全生产一体化管理手册应用平台</p>
 * @author JIANGLONG
 * @version 1.0 创建时间：2013-9-16 下午04:17:26
 */

public class SocketService {
	
	private static final int port=5050; 
	
	public static void main(String [] arge){
		ServerSocket ss=null;
		InetAddress address=null;
		try{
			//获取本地服务端地址
			address=InetAddress.getLocalHost();
			System.out.println(address.getHostAddress());
			System.out.println(address.getHostName());
			System.out.println(address.getAddress().toString());
			//绑定主机服务端口、限定链接数、链接地址
			ss=new ServerSocket(port,100,address);
			System.out.println("已启动服务端监听程序");
			//循环监听指定端口，如果获得响应，则建立一个线程处理事务
			while (true){
				Socket socket=ss.accept();
				Socket2 s1=new Socket2(socket);
				new Thread(s1).start();
				Thread.sleep(100);
			}						
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}finally{
			try{
				if (ss!=null) ss.close();
			}catch(IOException ioex){}
			System.out.println("已结束服务端监听程序");
		}						
		
	}
	
}
