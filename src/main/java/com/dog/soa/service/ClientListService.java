/**
 * 
 */
package com.dog.soa.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dog.soa.dao.ClientListDao;
import com.dog.soa.dao.ServerAuthorizeDao;
import com.dog.soa.entities.TblClientList;
import com.dog.soa.utils.Constant;

/**
 * 客户端管理业务类
 * @author jianglong
 * @date 2017年7月5日 上午10:14:17
 */
@CacheConfig(cacheNames = "clientEhcache")
@Service
public class ClientListService {

	@Autowired
	private ClientListDao clientListDao;
	@Autowired
	private ServerAuthorizeDao serverAuthorizeDao;
	
	/**
	 * 获取客户端信息
	 * @param id
	 * @return
	 */
	public TblClientList get(String id){
		return clientListDao.findOne(id);
	}
	
	/**
	 * 查询所有客户端列表
	 * @return
	 */
	public Iterable<TblClientList> getClientList(){
		return clientListDao.findAll();
	}
	
	/**
	 * 查询指定IP
	 * @param ip
	 * @return
	 */
	public TblClientList queryIP(String ip){
		return clientListDao.queryIP(ip);
	}
	
	/**
	 * 保存客户端
	 * @param client
	 */
	public void save(TblClientList client){
		if (client!=null && StringUtils.isBlank(client.getId())){
			client.setId(UUID.randomUUID().toString());
			client.setClientInfo("test");
			client.setCreateTime(DateFormatUtils.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS));
		}
		clientListDao.save(client);
	}
	
	/**
	 * 通过ID删除客户端
	 * @param id
	 */
	public void delete(String id){
		clientListDao.delete(id);
		serverAuthorizeDao.deleteAuthorizeClient(id);
	}
	
	/**
	 * 设置客户端为关闭状态
	 * @param id
	 */
	public void setCloseStatus(String id){
		this.setStatus(id, Constant.CLOSE);
	}
	
	/**
	 * 设置客户端为打开状态
	 * @param id
	 */
	public void setOpenStatus(String id){
		this.setStatus(id, Constant.OPEN);
	}
	
	/**
	 * 设置客户端状态
	 * @param id
	 * @param status
	 */
	public void setStatus(String id, String status){
		TblClientList client = clientListDao.findOne(id);
		client.setStatus(status);
		clientListDao.save(client);
	}
	
	/**
	 * 从缓存中加载客户端列表
	 * @param dateKey
	 * @return
	 */
	@Cacheable(key="#dateKey")
	public Map<String,TblClientList> getClientMap(String dateKey){
		Map<String,TblClientList> clientMap = new HashMap<>();
		Iterable<TblClientList> iterable = clientListDao.findAll();
		Iterator<TblClientList> iterator = iterable.iterator();
		TblClientList client = null;
		while (iterator.hasNext()){
			client = iterator.next();
			clientMap.put(client.getIp(), client);
		}		
		return clientMap;
	}
}
