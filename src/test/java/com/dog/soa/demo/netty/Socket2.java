package com.dog.soa.demo.netty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * 
 * @author jianglong
 * @date 2017年7月17日 上午10:35:31
 */
public class Socket2 extends Thread{

	private Socket socket=null;
	
	public Socket2(Socket socket){
		this.socket=socket;
	}
	
	public void run(){
		//System.out.println("已获得客户端链接,链接地址："+socket.getInetAddress());
		this.netAnalyze();
		//System.out.println("结束客户端链接");
	}
	
	/**
	 * 解析网络数据
	 */
	public void netAnalyze(){
		char[] c = new char[1024];		
		String str;
		InputStream is=null;
		BufferedReader in=null;
		Writer out=null;
		OutputStream os=null;	
		try{
			String req="abc";
			//返回输入流
			is=socket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));			
			//获得输入流的总长度，无数据或到末位则为-1
			int i=in.read(c);			
			if (i>0){
				char[] s = new char[i];
				System.arraycopy(c, 0, s, 0, i);
				str=new String(s);
				
				//返回服务端的响应
				os=socket.getOutputStream();						
				out = new BufferedWriter(new OutputStreamWriter(os));
				
				System.out.println("服务端发送内容: "+req);
				out.write(req);						
			}else{ 
				str="null";
			}			
			//DataBean.add(str);			
			System.out.println("客户端发送内容："+str);			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			//关闭流
			try{								
				if (out!=null){out.flush();out.close();}
				if (os!=null){os.flush();os.close();}
				if (in!=null){in.close();}
				if (is!=null){is.close();}								
			}catch(IOException io){}
			this.close();
		}
		
	}	
	
	/**
	 * 关闭监听端口
	 */
	private void close(){
		try{
			socket.close();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
	
}
