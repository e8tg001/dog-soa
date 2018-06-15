/**
 * 
 */
package com.dog.soa.proxy.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dog.soa.service.ProxyService;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 提供HTTP代理服务
 * @author jianglong
 * @date 2017年7月10日 上午11:48:51
 */
@Service
public class HttpServerManager {
	private final static Logger log = LoggerFactory.getLogger(HttpServerManager.class);
	
	@Value("${http.server.proxy.port:8866}")
	private String HTTP_SERVER_PROXY_PORT ;
	private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private ServerBootstrap server = new ServerBootstrap();
    @Autowired
	private ProxyService proxyService;
    /**
     * 测试入口
     * @param args
     */
    public static void main(String[] args) {
    	HttpServerManager psm = new HttpServerManager();
    	psm.HTTP_SERVER_PROXY_PORT = "8866";
    	psm.proxy();
	}
    
    /**
     * 代理入口
     */
    @Async
	public void proxy(){
		log.info("HTTP代理服务开始启动..");
		try{
			this.createServer();
			this.bind();
		}catch(Exception e){
			log.info("HTTP代理服务异常..",e);
		}finally{		
			this.close();
			log.info("HTTP代理服务已关闭..");
		}
	}
	
	/**
	 * 创建服务对象，设置服务通道配置
	 */
	public void createServer(){
		server.group(bossGroup, workerGroup);
		server.option(ChannelOption.SO_BACKLOG, 1024);// 配置TCP参数
		server.childOption(ChannelOption.SO_KEEPALIVE, true);
		server.channel(NioServerSocketChannel.class);//类似NIO中serverSocketChannel
		server.childHandler(new HttpServerInitializer(proxyService));//最后I/O事件的处理类
	}
	
	/**
	 * 绑定端口，并进行监听
	 * @throws Exception
	 */
	public void bind() throws Exception {
		//设置超时设置
		server.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3*1000);
		log.info("HTTP代理服务开始对【"+HTTP_SERVER_PROXY_PORT+"】端口进行监听..");
		//sync同步调用
		ChannelFuture cf =server.bind(Integer.parseInt(HTTP_SERVER_PROXY_PORT)).sync();
		cf.channel().closeFuture().sync();
		//awaitUninterruptibly异步调用
//		ChannelFuture cf =server.bind(portNumber).awaitUninterruptibly();
//		cf.channel().closeFuture().awaitUninterruptibly();
	}
	
	/**
	 * 关闭服务
	 */
	public void close(){
		if (bossGroup != null)
			bossGroup.shutdownGracefully();
		if (workerGroup != null)
			workerGroup.shutdownGracefully();
	}
	
	
	
}
