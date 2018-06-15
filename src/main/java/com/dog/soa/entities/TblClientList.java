package com.dog.soa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户端列表
 * @author jianglong
 * @date 2017年7月4日 下午6:13:55
 */
@Entity
@Table(name = "TBL_CLIENT_LIST")
public class TblClientList implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false, name="ID")
	private String id;
	@Column(nullable = false, name="IP")
	private String ip;
	@Column(nullable = false, name="SYSTEM_TYPE")
	private String systemType;
	@Column(nullable = false, name="STATUS")
	private String status;
	@Column(nullable = true, name="CLIENT_INFO")
	private String clientInfo;
	@Column(nullable = false, name="CREATE_TIME")
	private String createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClientInfo() {
		return clientInfo;
	}
	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
