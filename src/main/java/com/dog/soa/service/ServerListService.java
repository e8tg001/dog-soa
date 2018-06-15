/**
 * 
 */
package com.dog.soa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dog.soa.bean.ServerAuthorizeBean;
import com.dog.soa.dao.ClientListDao;
import com.dog.soa.dao.ServerAuthorizeDao;
import com.dog.soa.dao.ServerListDao;
import com.dog.soa.entities.TblClientList;
import com.dog.soa.entities.TblServerAuthorize;
import com.dog.soa.entities.TblServerList;
import com.dog.soa.utils.Constant;

/**
 * 服务端管理业务类
 * @author jianglong
 * @date 2017年7月5日 上午10:42:30
 */
@CacheConfig(cacheNames = "serverEhcache")
@Service
public class ServerListService {
	
	@PersistenceContext 
	private EntityManager em; 
	@Autowired
	private ServerListDao serverListDao;
	@Autowired
	private ServerAuthorizeDao serverAuthorizeDao;
	@Autowired
	private ClientListDao clientListDao;
	
	/**
	 * 获取服务端信息
	 * @param id
	 * @return
	 */
	public TblServerList get(String id){
		return serverListDao.findOne(id);
	}		
	
	/**
	 * 获取服务端列表
	 * @param id
	 * @return
	 */
	public Iterable<TblServerList> getServerList(){
		return serverListDao.findAll();
	}
	
	/**
	 * 通过服务名称模糊查询
	 * @param serverName
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TblServerList> findServer(String serverName,String status){		
		String hql = "select t from TblServerList t where t.serverName like ?1";
		if (StringUtils.isNotBlank(status)){
			hql += " and t.status=?2";
		}
		Query query = em.createQuery(hql);
		query.setParameter(1, "%"+serverName+"%");
		if (StringUtils.isNotBlank(status)){
			query.setParameter(2, status);
		}
		return query.getResultList();
	}
	
	/**
	 * 添加服务类
	 * @param server
	 */
	public void save(TblServerList server){
		if (StringUtils.isBlank(server.getId())){
			server.setId(UUID.randomUUID().toString());			
		}
		server.setCreateTime(DateFormatUtils.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS));
		serverListDao.save(server);
	}
	
	/**
	 * 删除服务类
	 * @param id
	 */
	public void delete(String id){
		serverListDao.delete(id);
		serverAuthorizeDao.deleteBatAuthorize(id);
	}
	
	/**
	 * 设置服务端为关闭状态
	 * @param id
	 */
	public void setCloseStatus(String id){
		this.setStatus(id, Constant.CLOSE);
	}
	
	/**
	 * 设置服务端为打开状态
	 * @param id
	 */
	public void setOpenStatus(String id){
		this.setStatus(id, Constant.OPEN);
	}
	
	/**
	 * 设置服务端状态
	 * @param id
	 * @param status
	 */
	public void setStatus(String id, String status){
		TblServerList server = serverListDao.findOne(id);
		server.setStatus(status);
		serverListDao.save(server);
	}
	
	/**
	 * IP是否存在于客户端列表中（非列表清单中的IP，不可加入授权清单）
	 * @param ip
	 * @return
	 */
	public TblClientList validIp(String ip){
		return clientListDao.queryIP(ip);
	}	
	
	/**
	 * 返回集联查询结果
	 * @param serverId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<ServerAuthorizeBean> getAuthorizeList(String serverId){
		String hql = "select a.id,b.ip,a.status,a.lastModifyTime from TblServerAuthorize a, TblClientList b where a.clientId=b.id and a.serverId=?";
		Query query = em.createQuery(hql);
		query.setParameter(1, serverId);
		List list = query.getResultList();
		List<ServerAuthorizeBean> dataList = new ArrayList<>();
		if (list != null && list.size()>0){			
			ServerAuthorizeBean sab = null;
			Iterator iterator = list.iterator();
			while (iterator.hasNext()){
				Object[] objs =(Object[])iterator.next();
				sab =new ServerAuthorizeBean();
				sab.setId(String.valueOf(objs[0])); 
				sab.setIp(String.valueOf(objs[1]));
				sab.setStatus(String.valueOf(objs[2]));
				sab.setDataTime(String.valueOf(objs[3]));
				dataList.add(sab);
			}
			list.clear();
			list = null;
		}
		em.close();
		return dataList;
	}
	
	/**
	 * 增加授权客户端
	 * @param authorize
	 */
	public boolean saveAuthorize(String serverId,String ip){
		TblClientList client = this.validIp(ip);
		if (client!=null){
			TblServerAuthorize authorize = new TblServerAuthorize();
			authorize.setId(UUID.randomUUID().toString());
			authorize.setServerId(serverId);
			authorize.setClientId(client.getId());
			authorize.setStatus(Constant.CLOSE);
			authorize.setLastModifyTime(DateFormatUtils.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS));
			serverAuthorizeDao.save(authorize);			
			return true;
		}
		return false;
	}
	
	/**
	 * 增加授权客户端
	 * @param authorize
	 */
	public void saveAuthorize(TblServerAuthorize authorize){
		serverAuthorizeDao.save(authorize);	
	}
	
	/**
	 * 删除授权客户端
	 * @param id
	 */
	public void deleteAuthorize(String id){
		serverAuthorizeDao.delete(id);
	}
	
	/**
	 * 设置客户端授权为关闭状态
	 * @param id
	 */
	public void setAuthorizeCloseStatus(String id){
		this.setAuthorizeStatus(id, Constant.CLOSE);
	}
	
	/**
	 * 设置客户端授权为打开状态
	 * @param id
	 */
	public void setAuthorizeOpenStatus(String id){
		this.setAuthorizeStatus(id, Constant.OPEN);
	}
	
	/**
	 * 设置状态
	 * @param id
	 * @param status
	 */
	public void setAuthorizeStatus(String id ,String status){
		TblServerAuthorize authorize = serverAuthorizeDao.findOne(id);
		authorize.setStatus(status);
		serverAuthorizeDao.save(authorize);
	}
	
	/**
	 * 从缓存中加载服务清单
	 * @param dateKey
	 * @return
	 */
	@Cacheable(key="#dateKey")
	public Map<String,TblServerList> getServerList(String dateKey){
		Map<String,TblServerList> serverMap = new HashMap<>();
		Iterable<TblServerList> iterable = this.getServerList();
		Iterator<TblServerList> iterator = iterable.iterator();
		TblServerList server = null;
		while (iterator.hasNext()){
			server = iterator.next();
			serverMap.put(server.getServerLabel(), server);
		}		
		return serverMap;
	}
	
}
