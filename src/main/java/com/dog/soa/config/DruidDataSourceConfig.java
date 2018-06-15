package com.dog.soa.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 注册数据源
 * 如果采用Druid，在application.properties配置完数据源参数后，一定需要注册一个bean，返回DataSource对象
 * 否则SQL监控,则无效
 * @author admin
 *
 */
@Configuration
public class DruidDataSourceConfig {
	
	private final static Logger log = LoggerFactory.getLogger(DruidDataSourceConfig.class);
	static {
		log.info("创建Druid连接池的DataSource对象...");
	}
	
	/**
	 * 注册Druid连接池的DataSource对象
	 * @return
	 */
	@Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
