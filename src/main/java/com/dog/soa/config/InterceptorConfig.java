package com.dog.soa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dog.soa.intercept.IpAuthorityIntercept;

/**
 * 注册拦截器
 * @author jianglong
 * @date 2017年7月17日 下午2:51:15
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
		
	@Bean
	public IpAuthorityIntercept getIpAuthorityIntercept(){
		return new IpAuthorityIntercept();
	}	
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getIpAuthorityIntercept()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
