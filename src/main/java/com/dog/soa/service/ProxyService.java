/**
 * 
 */
package com.dog.soa.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.dog.soa.dao.ServerAuthorizeDao;
import com.dog.soa.entities.TblClientList;
import com.dog.soa.entities.TblServerAuthorize;
import com.dog.soa.entities.TblServerList;
import com.dog.soa.proxy.HttpProxyService;
import com.dog.soa.proxy.SocketProxyService;
import com.dog.soa.utils.Constant;
import com.dog.soa.utils.HttpUtils;

/**
 * 代理服务验证
 * @author jianglong
 * @date 2017年7月7日 上午10:54:07
 */
@Service
public class ProxyService {

	@Autowired
	private ServerListService serverListService;
	
	@Autowired
	private ClientListService clientListService;
	
	@Autowired
	private ServerAuthorizeDao serverAuthorizeDao;
	
	/**
	 * 查询指定服务接口对象
	 * @param serverLabel
	 * @return
	 */
	public TblServerList findServer(String serverLabel){
		String dateKey = DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
		Map<String,TblServerList> serverMap = serverListService.getServerList(dateKey);
		return serverMap.get(serverLabel);
	}
	
	/**
	 * 查询指定客户端对象
	 * @param ip
	 * @return
	 */
	public TblClientList findClient(String ip){
		String dateKey = DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
		Map<String,TblClientList> clientMap = clientListService.getClientMap(dateKey);
		return clientMap.get(ip);
	} 
	
	/**
	 * 加载指定服务下的指定授权项
	 * @param serverId
	 * @param clientId
	 */
	public TblServerAuthorize getAuthorize(String serverId,String clientId){
		return serverAuthorizeDao.getAuthorize(serverId, clientId);
	}
	
	/**
	 * HTTP代理服务
	 * @param request
	 * @param serverLabel
	 * @return
	 */
	public String httpProxy(final String ip,String serverLabel,Map<String,String[]> parameterMap){
		TblServerList server = findServer(serverLabel);
		String authorize = this.proxyAuthorize(server, ip);
		if (authorize!=null){
			return authorize;
		} else {		
			return this.proxySend(server, parameterMap);
		}
	}
	
	/**
	 * HTTP代理服务
	 * @param request
	 * @param serverLabel
	 * @return
	 */
	public String httpProxy(final String ip,final String serverLabel,String parameters){
		return this.proxy(ip, serverLabel, parameters);
	}
	
	/**
	 * SOCKET代理服务
	 * @param request
	 * @param serverLabel
	 * @return
	 */
	public String socketProxy(final String ip,final String serverLabel,String parameters){
		return this.proxy(ip, serverLabel, parameters);
	}
	
	/**
	 * 代理服务
	 * @param request
	 * @param serverLabel
	 * @return
	 */
	public String proxy(final String ip,final String serverLabel,String parameters){
		TblServerList server = findServer(serverLabel);
		String authorize = this.proxyAuthorize(server, ip);
		if (authorize!=null){
			return authorize;
		} else {		
			return this.proxySend(server, parameters);
		}
	}
	
	/**
	 * 权限鉴证
	 * @param server
	 * @param ip
	 * @return
	 */
	public String proxyAuthorize(TblServerList server,String ip){
		//未找到服务端
		if (server == null){
			return Constant.HTTP_404;
		}
		//未授权的客户端
		TblClientList client = findClient(ip);
		if (client == null || Constant.CLOSE.equals(client.getStatus())){
			return Constant.HTTP_401;
		}
		//禁止访问
		TblServerAuthorize authorize = getAuthorize(server.getId(), client.getId());
		if (authorize == null){
			return Constant.HTTP_403;
		}
		return null;
	}
	
	/**
	 * 通过字符串参数发送请求
	 * @param server
	 * @param parameters
	 * @return
	 */
	private String proxySend(TblServerList server, String parameters){
		String value = "";
		if (Constant.SOCKET.equals(server.getServerType())){
			String url = server.getServerUrl();
			String [] hosts = url.split(":");
			//代理转发
			SocketProxyService sc = new SocketProxyService(hosts[0],Integer.parseInt(hosts[1]));
			value = sc.send(parameters);
		}else if (Constant.HTTP.equals(server.getServerType())) {
			HttpProxyService httpProxyService = new HttpProxyService();
			//代理转发
			if (server.getSendType().equals(HttpMethod.POST.name()))
				value = httpProxyService.doPost(server.getServerUrl(), HttpUtils.paramePairs(parameters));
			else 
				value = httpProxyService.doGet(server.getServerUrl(), parameters);
		}
		return StringUtils.isNotBlank(value)? value: "";
	}
	
	/**
	 * 通过字Map参数发送请求
	 * @param server
	 * @param parameterMap
	 * @return
	 */
	private String proxySend(TblServerList server, Map<String,String[]> parameterMap){
		String value = "";
		if (Constant.SOCKET.equals(server.getServerType())){
			String url = server.getServerUrl();
			String [] hosts = url.split(":");
			//代理转发
			SocketProxyService sc = new SocketProxyService(hosts[0],Integer.parseInt(hosts[1]));
			value = sc.send(HttpUtils.parameStrPairs(parameterMap));
		}else if (Constant.HTTP.equals(server.getServerType())) {
			HttpProxyService httpProxyService = new HttpProxyService();
			//代理转发
			if (server.getSendType().equals(HttpMethod.POST.name()))
				value = httpProxyService.doPost(server.getServerUrl(),parameterMap);
			else 
				value = httpProxyService.doGet(server.getServerUrl(),parameterMap);
		}else {
			value = "server type is error";
		}
		return StringUtils.isNotBlank(value)? value: "";
	}
	
}
