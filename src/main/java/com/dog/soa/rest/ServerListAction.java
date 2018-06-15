/**
 * 
 */
package com.dog.soa.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dog.soa.entities.TblServerList;
import com.dog.soa.service.ServerListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 服务端管理控制器
 * @author jianglong
 * @date 2017年7月5日 上午10:41:56
 */
@RestController
public class ServerListAction extends BaiseAction {

	@Autowired
	private ServerListService serverListService;
	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	/**
	 * 获取服务端信息
	 * @param request
	 * @param response
	 * @param server
	 */
	@RequestMapping(value="/server/get", produces="application/json;charset=UTF-8", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String get(@RequestParam String id){
		try{
			return objectMapper.writeValueAsString(serverListService.get(id));
		}catch(JsonProcessingException je){
			je.printStackTrace();
		}
		return FAIL;
	}
	
	/**
	 * 获取服务端列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/server/getList", produces="application/json;charset=UTF-8", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String getList(){
		try{
			return objectMapper.writeValueAsString(serverListService.getServerList());
		}catch(JsonProcessingException je){
			je.printStackTrace();
		}
		return FAIL;
	}
	
	/**
	 * 通过服务名称模糊查询
	 * @param serverName
	 * @return
	 */
	@RequestMapping(value="/server/findServer", produces="application/json;charset=UTF-8", method = {RequestMethod.POST,RequestMethod.GET})
	public String findServer(@RequestParam String serverName,@RequestParam String status){
		if (StringUtils.isNotBlank(serverName) || StringUtils.isNotBlank(status)){
			try{
				return objectMapper.writeValueAsString(serverListService.findServer(serverName,status));
			}catch(JsonProcessingException je){
				je.printStackTrace();
			}
			return FAIL;
		}else {
			return getList();
		}
	}
	
	/**
	 * 获取服务端授权列表
	 * @param request
	 * @param response
	 * @param server
	 */
	@RequestMapping(value="/server/getAuthorizeList", produces="application/json;charset=UTF-8", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String getAuthorizeList(@RequestParam String id){
		try{
			return objectMapper.writeValueAsString(serverListService.getAuthorizeList(id));
		}catch(JsonProcessingException je){
			je.printStackTrace();
		}
		return FAIL;
	}
	
	/**
	 * 保存服务端
	 * @param request
	 * @param response
	 * @param server
	 */
	@RequestMapping(value="/server/save", method = {RequestMethod.POST,RequestMethod.GET})
	public String save(TblServerList server){
		serverListService.save(server);
		return SUCCESS;
	}
	
	/**
	 * 删除服务端
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value="/server/delete", method = {RequestMethod.POST,RequestMethod.GET})
	public String delete(@RequestParam String id){
		serverListService.delete(id);
		return SUCCESS;
	}
	
	/**
	 * 添加授权IP
	 * @param request
	 * @param response
	 * @param id
	 * @param ips
	 * @return
	 */
	@RequestMapping(value="/server/addAuthorize", method = {RequestMethod.POST,RequestMethod.GET})
	public String addAuthorize(@RequestParam String id,@RequestParam String ips){
		if (StringUtils.isBlank(ips)){
			return FAIL;
		}
		List<String> errorIpList = new ArrayList<>();
		String [] ipList =  ips.split(",");
		for (String ip : ipList){
			if (!serverListService.saveAuthorize(id,ip)){
				errorIpList.add(ip);
			}
		}
		if (errorIpList.size()>0){
			try{
				return objectMapper.writeValueAsString(errorIpList);
			}catch(JsonProcessingException je){
				je.printStackTrace();
				return ERROR;
			}
		}else {
			return SUCCESS;
		}
	}
	
	/**
	 * 删除服务端指定授权
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/server/deleteAuthorize", method = {RequestMethod.POST,RequestMethod.GET})
	public String deleteAuthorize(@RequestParam String id){
		serverListService.deleteAuthorize(id);
		return SUCCESS;
	}
	
	
	/**
	 * 设置服务端为关闭状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/server/setCloseStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public String setCloseStatus(@RequestParam String id){
		serverListService.setCloseStatus(id);
		return SUCCESS;
	}
	
	/**
	 * 设置服务端为开启状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/server/setOpenStauts", method = {RequestMethod.POST,RequestMethod.GET})
	public String setOpenStatus(@RequestParam String id){
		serverListService.setOpenStatus(id);
		return SUCCESS;
	}
	
	/**
	 * 设置服务端指定授权为关闭状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/server/setAuthorizeCloseStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public String setAuthorizeCloseStatus(@RequestParam String id){
		serverListService.setAuthorizeCloseStatus(id);
		return SUCCESS;
	}
	
	/**
	 * 设置服务端指定授权为开启状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/server/setAuthorizeOpenStatus", method = {RequestMethod.POST,RequestMethod.GET})
	public String setAuthorizeOpenStatus(@RequestParam String id){
		serverListService.setAuthorizeOpenStatus(id);
		return SUCCESS;
	}
}
