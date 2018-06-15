package com.dog.soa.proxy.http;

import com.dog.soa.service.ProxyService;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


/**
 * 代理》初始化解码与转码等配置，加载处理管道
 * @author jianglong
 * @date 2017年7月12日 上午11:39:51
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

	private ChannelPipeline pipeline = null;
	private ProxyService proxyService = null;
	
	public HttpServerInitializer(ProxyService proxyService){
		this.proxyService = proxyService;
	}
	
	/**
	 * 配置代理参数
	 */
	@Override	
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		pipeline = ch.pipeline();		
		//server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
//        pipeline.addLast("encoder",new HttpResponseEncoder());
		pipeline.addLast(new HttpResponseEncoder());
		// server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
//		pipeline.addLast("decoder",new HttpRequestDecoder());
		pipeline.addLast(new HttpRequestDecoder());
        //我们通常接收到的是一个http片段，如果要想完整接受一次请求的所有数据，我们需要绑定HttpObjectAggregator，然后我们就可以收到一个FullHttpRequest-是一个完整的请求信息。 
//        pipeline.addLast("aggegator",new HttpObjectAggregator(1024*1024*64));//定义缓冲数据量  
        pipeline.addLast(new HttpObjectAggregator(1024*1024*64));//定义缓冲数据量  
        //设置channel的读写超时，与ChannelFuture超时无关
//        pipeline.addLast(new IdleStateHandler(5*1000,7*1000,3*1000,TimeUnit.MILLISECONDS));
        // 自己的逻辑Handler
        pipeline.addLast("handler",new HttpServerHandler(proxyService));
        
	}

}
