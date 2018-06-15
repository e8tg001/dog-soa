/**
 * 
 */
package com.dog.soa.proxy;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 网络客户端
 * @author jianglong
 * @date 2017年7月17日 上午10:33:14
 */
public class SocketProxyService {
	/**
	 * 链接端口
	 */
	private String host=null;
	private int prot=5050;
	private Socket socket=null;
	private Writer out=null;
	private OutputStream os=null;
	private InputStream is=null;
	private BufferedReader in=null;
	/**
	 * 主测试方法 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {		
		SocketProxyService sc = new SocketProxyService("172.16.0.102",5050);
		for (int i=0;i<100;i++){
			sc.send("test");
			Thread.sleep(20);
		}
	}
	
	public SocketProxyService(){
		
	}
	
	public SocketProxyService(String host,int prot){
		this.host = host;
		this.prot = prot;
	}
	
	/**
	 * 创建指定地址发送连接
	 * @throws IOException
	 */
	public void create() throws IOException{
		socket=new Socket();
		socket.setSoTimeout(10*1000);//超过10秒钟为获取到数据，则超时
		socket.connect(new InetSocketAddress(host,prot), 3*1000);
	}
	
	/**
	 * 发送请求
	 * @param msg
	 * @return
	 */
	public String send(String msg){
		String value = "";
		try{
			this.create();
			//判断连接是否打开
			if (socket.isClosed()){
				return value;
			}
			this.sendClientValue(socket, msg);		
			value = this.getServerValue(socket);
		}catch(IOException ioe){
			ioe.printStackTrace();			
		}finally{
			this.close();
		}
		return value;
	}
	
	/**
	 * 发送客户端的请求
	 * @param socket
	 * @param msg
	 * @throws IOException
	 */
	public void sendClientValue(Socket socket,String msg) throws IOException {
		//客户端输出字符流
		os=socket.getOutputStream();						
		out = new BufferedWriter(new OutputStreamWriter(os));
		out.write(msg);
		//需要刷新区域
		out.flush();
	}
	
	/**
	 * 获取服务端的响应值
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public String getServerValue(Socket socket) throws IOException{
		//接收服务端响应
		is =socket.getInputStream();		
		//字节模式
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] b2 = new byte[1024*1024];
		int i = bis.read(b2);
		bis.close();
		return new String(b2,0,i);
	}	

	/**
	 * 关闭Socket
	 */
	public void close(){
		//关闭流
		try{				
			if (out!=null){out.close();}
			if (os!=null){os.flush();os.close();}
			if (is!=null){is.close();}
			if (in!=null){in.close();}
			if (socket!=null){socket.close();}
		}catch(IOException io){}
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setProt(int prot) {
		this.prot = prot;
	}
	
}
