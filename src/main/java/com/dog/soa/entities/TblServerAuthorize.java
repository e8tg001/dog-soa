/**
 * 
 */
package com.dog.soa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 服务端授权表
 * @author jianglong
 * @date 2017年7月4日 下午6:17:36
 */
@Entity
@Table(name = "TBL_SERVER_AUTHORIZE")
public class TblServerAuthorize implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false, name="ID")
	private String id;
	@Column(nullable = false, name="SERVER_ID")
	private String serverId;
	@Column(nullable = false, name="CLIENT_ID")
	private String clientId;
	@Column(nullable = false, name="STATUS")
	private String status;
	@Column(nullable = false, name="LAST_MODIFY_TIME")
	private String lastModifyTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
}
