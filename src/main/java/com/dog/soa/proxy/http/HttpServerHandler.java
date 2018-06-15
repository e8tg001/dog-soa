/**
 * 
 */
package com.dog.soa.proxy.http;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dog.soa.service.ProxyService;
import com.dog.soa.utils.HttpUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 代理服务处理类
 * @author jianglong
 * @date 2017年7月12日 上午11:35:44
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<Object> {
	/**
	 * HTTP转发类
	 */
	private ProxyService proxyService;
	
	
	public HttpServerHandler(ProxyService proxyService){
		this.proxyService = proxyService;
	} 
	
	private final static Logger log = LoggerFactory.getLogger(HttpServerHandler.class);
	/**
	 * 接受到客户端的请求
	 * @param msg 收到的内容
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("client http headers :\n" + msg);
		HttpRequest request = null;
		if (msg instanceof HttpRequest){
			request = (HttpRequest) msg;
		} else{
			ctx.flush();
    		ctx.close();
        	return ; 
		}
		//所有后缀非.do.action为空的URL通过，非以上则返回地址不存在
        String uri = request.uri();
        int l = uri.lastIndexOf("/");
        if (uri.substring(l).indexOf(".")!=-1){
        	if (uri.lastIndexOf(".do")==-1 || uri.lastIndexOf(".action")==-1){
        		FullHttpResponse response = new DefaultFullHttpResponse(
        				HttpVersion.HTTP_1_1, 
        				HttpResponseStatus.NOT_FOUND);
        		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        		ctx.close();
            	return ; 
            }
        }

        if (request.method() != HttpMethod.GET && request.method() != HttpMethod.POST) {
            throw new IllegalStateException("请求不是GET或POST请求.");
        } 
        //获取客户端请求地址
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
		String ip = address.getHostName();
		String queryString = HttpUtils.getQueryString(uri);
		String serverLabel = HttpUtils.getServerLabel(uri);
		String result = proxyService.httpProxy(ip, serverLabel, queryString);
		log.info("server send : "+result);
		//包装一个HTTP Response响应对象
		this.response(ctx, request, result);
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
    	InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
		String ip = socket.getHostName();
    	log.info("RamoteAddress : " + ip + " active !");
//        ctx.flush();
    	super.channelActive(ctx);        
    }
	
	/**
	 * 出现异常时打印
	 */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	cause.printStackTrace();
    	ctx.flush();
    	ctx.close();
    }
	
    /**
     * 超时处理事件
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
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
    
    /**
     * 封装返回响应的response
     * @param ctx
     * @param request
     * @param result
     * @throws UnsupportedEncodingException
     */
    private void response(ChannelHandlerContext ctx, HttpRequest request,String result) throws UnsupportedEncodingException{
    	FullHttpResponse response = new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1, 
				HttpResponseStatus.OK, 
				Unpooled.wrappedBuffer(result.getBytes("UTF-8")));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN+";charset=UTF-8");//text/plain||"text/html;charset=UTF-8"        
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        if (!HttpUtil.isKeepAlive(request)) {
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
//        	response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.writeAndFlush(response);
        }
       
    }
}
