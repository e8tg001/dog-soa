/**
 * 
 */
package com.dog.soa.intercept;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dog.soa.entities.TblClientList;
import com.dog.soa.service.ClientListService;
import com.dog.soa.utils.Constant;

/**
 * 所有HTTP请求会经过本拦截器
 * @author jianglong
 * @date 2017年7月4日 下午3:08:52
 */
public class IpAuthorityIntercept implements HandlerInterceptor {

	private final static Logger log = LoggerFactory.getLogger(IpAuthorityIntercept.class);
	@Autowired
	private ClientListService clientListService;
	
	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ip = request.getRemoteAddr();
		log.info(ip + " request...");
		//每一分钟加载一次所有IP到缓存中，每次请求均需要验证IP是否可以访问
		Map<String,TblClientList> clientMap = clientListService.getClientMap(DateFormatUtils.format(new Date(), "yyyyMMddHHmm"));
		if (clientMap!=null){			
			if (clientMap.get(ip)==null){
				this.out(response, Constant.HTTP_401);
				return false;
			}else if (Constant.CLOSE.equals(clientMap.get(ip).getStatus())) {
				this.out(response, Constant.HTTP_403);
				return false;
			}
		}
		//只有返回true才会继续向下执行，返回false取消当前请求
		return true;
	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 *  (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 输出响应内容
	 * @param response
	 * @param text
	 */
	public void out(HttpServletResponse response,String text){
		log.info(text);
		PrintWriter out = null;
		try{
			out = response.getWriter();
			out.write(text);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (out!=null){
				out.flush();
				out.close();
			}
		}
	}
	
	
}
