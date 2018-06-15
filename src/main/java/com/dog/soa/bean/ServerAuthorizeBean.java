/**
 * 
 */
package com.dog.soa.bean;

/**
 * @author jianglong
 * @date 2017年7月5日 下午3:14:58
 */
public class ServerAuthorizeBean {

	private String id;
	private String ip;
	private String dataTime;
	private String status;
	
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
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
