/**
 * 
 */
package com.dog.soa;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import com.dog.soa.proxy.http.HttpServerManager;
import com.dog.soa.proxy.socket.SocketServerManager;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 主启动类
 * @author jianglong
 * @date 2017年7月4日 上午11:37:43
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableSwagger2
public class DogSoaApplication {

	private static final Logger log = LoggerFactory.getLogger(DogSoaApplication.class);
	@Autowired
	private HttpServerManager httpServerManager;
	@Autowired
	private SocketServerManager socketServerManager;
	
	/**
	 * 主启动方法
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("启动服务中...");
		SpringApplication.run(DogSoaApplication.class, args);
		log.info("服务已启动...");
	}
	
	/**
	 * 启动后端服务
	 */
	@PostConstruct
	public void proxy(){
		httpServerManager.proxy();
		socketServerManager.proxy();
		log.info("代理服务已准备完毕，正在监听中...");
	}
	
	/**
	 * 类被注销后执行
	 */
	@PreDestroy
	public void destroy(){
		log.info("线程已退出，类被注销...");
	}
	
}
