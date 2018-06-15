package com.dog.soa.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dog.soa.entities.TblServerList;

/**
 * 服务端管理DAO
 * @author jianglong
 * @date 2017年7月5日 上午10:43:16
 */
public interface ServerListDao extends CrudRepository<TblServerList, String>{

	/**
	 * 查询指定的接口
	 * @param serverLabel
	 * @return
	 */
	@Query("select t from TblServerList t where t.serverLabel = :serverLabel")
	public TblServerList findServer(@Param("serverLabel") String serverLabel);
}
