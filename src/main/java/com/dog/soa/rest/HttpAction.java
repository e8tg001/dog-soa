/**
 * 
 */
package com.dog.soa.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dog.soa.service.ProxyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author jianglong
 * @date 2017年7月4日 上午11:44:34
 */
//@Api(value="接口使用说明")  
@RestController
public class HttpAction extends BaiseAction {
	
	
	@Autowired
	private ProxyService proxyService;
	
	@RequestMapping(value="/testserver", method = {RequestMethod.POST,RequestMethod.GET})
	public String server(HttpServletRequest request, HttpServletResponse response){		
		return "Ok_"+System.currentTimeMillis();
	}
	
	
	/**
	 * 对外统一HTTP代理转发服务入口
	 * @param request
	 * @param response
	 * @param serverLabel
	 * @return
	 */
	@ApiOperation(value = "对外统一HTTP代理转发服务入口",notes="注意事项:服务标准请求模式为http://127.0.0.1:8081/dog/rest/server/{serverLabel}")
	@ApiImplicitParam(dataType = "String",name = "serverLabel",value = "服务标识",required = true,paramType = "body")
	@RequestMapping(value="/server/{serverLabel}", method = {RequestMethod.POST,RequestMethod.GET})
	public String server(HttpServletRequest request, HttpServletResponse response,@PathVariable String serverLabel){		
		return this.httpProxy(request, serverLabel);
	}
	
	/**
	 * HTTP代理服务
	 * @param request
	 * @param serverLabel
	 * @return
	 */
	private String httpProxy(HttpServletRequest request,String serverLabel){
		String ip = request.getRemoteAddr();
		return proxyService.httpProxy(ip, serverLabel, request.getParameterMap());
	}
	
}
