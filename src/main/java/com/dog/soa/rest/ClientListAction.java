package com.dog.soa.rest;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dog.soa.entities.TblClientList;
import com.dog.soa.service.ClientListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 客户端管理控制器
 * @author jianglong
 * @date 2017年7月5日 上午10:20:09
 */
@RestController
//@CrossOrigin(origins="http://localhost:8081",maxAge=3600)
public class ClientListAction extends BaiseAction {

	@Autowired
	private ClientListService clientListService;
	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	/**
	 * 获取客户端信息【未使用】
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/clinet/get", produces="application/json;charset=UTF-8", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String get(@RequestParam String id){
		try{
			return objectMapper.writeValueAsString(clientListService.get(id));
		}catch(JsonProcessingException je){
			je.printStackTrace();
		}
		return FAIL;
	}
	
	/**
	 * 获取客户端信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/clinet/queryIP", produces="application/json;charset=UTF-8", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String queryIP(@RequestParam String ip){
		try{
			if (StringUtils.isNotBlank(ip))
				return objectMapper.writeValueAsString(Arrays.asList(clientListService.queryIP(ip)));
			else 
				return getList();
		}catch(JsonProcessingException je){
			je.printStackTrace();
		}
		return FAIL;
	}
	
	/**
	 * 获取客户端列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/clinet/getList", produces="application/json;charset=UTF-8", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String getList(){
		try{
			return objectMapper.writeValueAsString(clientListService.getClientList());
		}catch(JsonProcessingException je){
			je.printStackTrace();
		}
		return FAIL;
	}
	
	
	/**
	 * 保存客户端
	 * @param request
	 * @param response
	 * @param client
	 * @return
	 */
	@RequestMapping(value="/clinet/save", method = {RequestMethod.POST,RequestMethod.GET})
	public String save(HttpServletRequest request, HttpServletResponse response,TblClientList client){
		clientListService.save(client);
		return SUCCESS;
	}
	
	/**
	 * 删除客户端
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/clinet/delete", method = {RequestMethod.POST,RequestMethod.GET})
	public String delete(HttpServletRequest request, HttpServletResponse response,@RequestParam String id){
		clientListService.delete(id);		
		return SUCCESS;
	}
	
	
	/**
	 * 设置服务端为关闭状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/clinet/setCloseStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public String setCloseStatus(@RequestParam String id){
		clientListService.setCloseStatus(id);
		return SUCCESS;
	}
	
	/**
	 * 设置服务端为开启状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/clinet/setOpenStauts", method = {RequestMethod.POST,RequestMethod.GET})
	public String setOpenStatus(@RequestParam String id){
		clientListService.setOpenStatus(id);
		return SUCCESS;
	}
}
