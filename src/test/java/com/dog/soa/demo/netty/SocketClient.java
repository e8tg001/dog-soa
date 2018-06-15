/**
 * 
 */
package com.dog.soa.demo.netty;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 网络客户端
 * @author jianglong
 * @date 2017年7月17日 上午10:33:14
 */
public class SocketClient {
	/**
	 * 链接端口
	 */
	private static final int prot=7766;		
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {		
		SocketClient sc = new SocketClient();
		for (int i=0;i<1;i++){
			sc.sendStr("socket client test!");
			Thread.sleep(1000);
		}
	}

	public String sendStr(String msg){
		Socket socket=null;
		InetAddress address=null;
		Writer out=null;
		OutputStream os=null;
		InputStream is=null;
		BufferedReader in=null;
		long time=System.currentTimeMillis();
		//msg="Socket_test#test data time "+String.valueOf(time)+"\r\n";
		msg = "Solr_V8_Query#q=*%3A*&fq=id%3A3FAB0BB8BF4F4F60A60FB6DA0CD0AC2B&wt=json&indent=true"+"\r\n";
		String value = "";
		try{			
			//address=InetAddress.getByAddress("172.16.0.116");
			//向指定地址发送连接
			address=InetAddress.getLocalHost();
			System.out.println(address.getAddress());
			//socket=new Socket(address,prot);	
			//socket=new Socket("jianglong",prot);
			socket=new Socket("172.16.0.110",prot);
//			socket=new Socket();
//			socket.connect(new InetSocketAddress("localhost",8112));
			//判断连接是否打开
			if (socket.isClosed()){
				return value;
			}
			//System.out.println("服务端链接可用,HOST:"+address.getHostAddress()+"\t"+prot);
			//客户端输出字符流
			os=socket.getOutputStream();						
			out = new BufferedWriter(new OutputStreamWriter(os));
			System.out.println("SocketClient 客户端发送内容："+msg);
			out.write(msg);	
			//需要刷新区域
			out.flush();
			
//			try{
//				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));  
//				pw.write(str);  
//				pw.flush();
//				System.out.println("send:"+str);
//			}catch(IOException ioe){
//				ioe.printStackTrace();
//			}
			
			
			//接收服务端响应
			is=socket.getInputStream();
//			in = new BufferedReader(new InputStreamReader(is));
			//字符数组模式
//			char[] cbuf = new char[1024];
//			int i=in.read(cbuf);
			
			//字符串模式
//			String req=in.readLine();
//			System.out.println("服务端发送内容: "+req);
			
			//字节模式
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] b2 = new byte[1024*1024];
			int i = bis.read(b2);
			bis.close();
			value = new String(b2,0,i);
			if (i>0){
				//System.out.println("服务端发送内容: "+new String(cbuf));
				System.out.println("服务端发送内容: "+value);
			}else {
				System.out.println("无返回响应");
			}
		}catch(UnknownHostException uhe){
			uhe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();			
		}finally{
			//关闭流
			try{				
				if (out!=null){out.close();}
				if (os!=null){os.flush();os.close();}
				if (is!=null){is.close();}
				if (in!=null){in.close();}
				if (socket!=null){socket.close();}
			}catch(IOException io){}
		}
		return value;
	}
}
