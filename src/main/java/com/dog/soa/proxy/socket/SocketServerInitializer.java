package com.dog.soa.proxy.socket;

import com.dog.soa.service.ProxyService;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * 代理》初始化解码与准码等配置，加载处理管道
 * @author jianglong
 * @date 2017年7月12日 上午11:39:51
 */
public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {

	private ChannelPipeline pipeline = null;
	private ProxyService proxyService;
	
	public SocketServerInitializer(ProxyService proxyService){
		this.proxyService = proxyService;
	}
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		pipeline = ch.pipeline();
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 字符串解码 和 编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        //我们通常接收到的是一个http片段，如果要想完整接受一次请求的所有数据，我们需要绑定HttpObjectAggregator，然后我们就可以收到一个FullHttpRequest-是一个完整的请求信息。 
        pipeline.addLast("aggegator",new HttpObjectAggregator(1024*1024*64));//定义缓冲数据量  
        //设置channel的读写超时，与ChannelFuture超时无关
//        pipeline.addLast("idleStateHandler",new IdleStateHandler(5*1000,7*1000,3*1000,TimeUnit.MILLISECONDS));
        // 自己的逻辑Handler
        pipeline.addLast("handler", new SocketServerHandler(proxyService));
	}	

}
