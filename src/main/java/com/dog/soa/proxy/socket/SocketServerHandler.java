/**
 * 
 */
package com.dog.soa.proxy.socket;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dog.soa.service.ProxyService;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author jianglong
 * @date 2017年7月12日 上午11:35:44
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<String> {
	
	private final static Logger log = LoggerFactory.getLogger(SocketServerHandler.class);
	private final static String ENDING = "\r\n";
	private ProxyService proxyService;
	
	public SocketServerHandler(ProxyService proxyService){
		this.proxyService = proxyService;
	}
	
	/**
	 * 接受到客户端的请求
	 * @param msg 收到的内容
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {		
		log.info("client send : "+msg);
		InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
		System.out.println(address.getHostString());
		String ip = address.getHostString();
		//以第一个#号前的内容做为接口标识ID，之后的为请求文本
		String serverLabel = msg.substring(0, msg.indexOf("#"));
		String queryString  = msg.substring(msg.indexOf("#") + 1);
		String result = proxyService.socketProxy(ip, serverLabel, queryString);
		log.info("server send : "+result);
		ctx.writeAndFlush( result + ENDING);
		//传递给下一个handler
//		ReferenceCountUtil.retain(msg);
		ctx.close();
	}

	/**
	 * 创建连接时执行该方法
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	log.info("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        super.channelActive(ctx);
    }
	
	/**
	 * 出现异常时打印
	 */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	cause.printStackTrace();
    	ctx.close();
    }
	
    /**
     * 超时事件
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    	// TODO Auto-generated method stub    	
    	log.info("触发事件");
    	if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                ctx.close();
                log.info("READER_IDLE is timeout");
            } else if (e.state() == IdleState.WRITER_IDLE) {
                ByteBuf buff = ctx.alloc().buffer();
                buff.writeBytes("WRITER_IDLE is timeout".getBytes());
                ctx.writeAndFlush(buff);
                log.info("WRITER_IDLE 写超时");
            } else {
            	log.info("其他超时");
            }
        }
    }
    
}
