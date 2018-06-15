/**
 * 
 */
package com.dog.soa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 服务端列表
 * @author jianglong
 * @date 2017年7月4日 下午5:51:28
 */
@Entity
@Table(name = "TBL_SERVER_LIST")
public class TblServerList implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false, name="ID")
	private String id;
	@Column(nullable = false, name="SERVER_NAME")
	private String serverName;
	@Column(nullable = false, name="SERVER_LABEL")
	private String serverLabel;
	@Column(nullable = false, name="SERVER_TYPE")
	private String serverType;
	@Column(nullable = false, name="SEND_TYPE")
	private String sendType;
	@Column(nullable = false, name="SYSTEM_TYPE")
	private String systemType;
	@Column(nullable = false, name="STATUS")
	private String status;
	@Column(nullable = false, name="SERVER_URL")
	private String serverUrl;	
	@Column(nullable = true, name="SERVER_INFO")
	private String serverInfo;
	@Column(nullable = false, name="CREATE_TIME")
	private String createTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}	
	public String getServerLabel() {
		return serverLabel;
	}
	public void setServerLabel(String serverLabel) {
		this.serverLabel = serverLabel;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
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
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getServerInfo() {
		return serverInfo;
	}
	public void setServerInfo(String serverInfo) {
		this.serverInfo = serverInfo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
