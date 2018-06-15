package com.dog.soa.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dog.soa.entities.TblClientList;


/**
 * 客户端表DAO类
 * @author jianglong
 * @date 2017年7月5日 上午10:12:36
 */
public interface ClientListDao extends CrudRepository<TblClientList, String> {

	
	/**
	 * 查询指定的IP是否存在
	 * @param ip
	 * @return
	 */
	@Query("select t from TblClientList t where ip = :ip")
	public TblClientList queryIP(@Param("ip") String ip);
	
}
