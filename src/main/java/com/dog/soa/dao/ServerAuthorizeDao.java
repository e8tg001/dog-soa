/**
 * 
 */
package com.dog.soa.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dog.soa.entities.TblServerAuthorize;

/**
 * 服务端访客表
 * @author jianglong
 * @date 2017年7月5日 上午10:46:33
 */
public interface ServerAuthorizeDao extends CrudRepository<TblServerAuthorize, String> {

	/**
	 * 删除指定服务下的所有授权记录
	 * @param serverId
	 */
	@Transactional
	@Modifying
	@Query("delete from TblServerAuthorize where serverId=:serverId")
	public void deleteBatAuthorize(@Param("serverId") String serverId);
	
	/**
	 * 加载指定服务下的所有授权清单
	 * @param serverId
	 * @return
	 */
	@Query("select t from TblServerAuthorize t where serverId=:serverId")
	public List<TblServerAuthorize> getAuthorizeList(@Param("serverId") String serverId);
	
	/**
	 * 加载指定服务下的指定授权项
	 * @param serverId
	 * @param clientId
	 * @return
	 */
	@Query("select t from TblServerAuthorize t where t.status='1' and t.serverId=:serverId and t.clientId=:clientId")
	public TblServerAuthorize getAuthorize(@Param("serverId") String serverId,@Param("clientId") String clientId);
	
	/**
	 * 删除指定客户端的所有授权
	 * @param clientId
	 */
	@Transactional
	@Modifying
	@Query("delete from TblServerAuthorize t where t.clientId=:clientId")
	public void deleteAuthorizeClient(@Param("clientId") String clientId);
	
}
