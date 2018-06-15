/**
 * 
 */
package com.dog.soa.error;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常管理方法
 * @author jianglong
 * @date 2017年7月5日 上午10:34:36
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 全局异常客理类
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		log.error(req.getRequestURI()+" 请求操作异常!",e);
		return "error";		
	}
	
}
