package com.dog.soa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * cors跨域管理
 * @author jianglong
 * @date 2017年7月17日 下午2:51:15
 */
@Configuration
public class WebCorsConfig extends WebMvcConfigurerAdapter {			
	
	/**
	 * 过滤器
	 * @return
	 */
	@Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        // 对响应头进行CORS授权
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        
        // 注册CORS过滤器
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(configurationSource);
        return new FilterRegistrationBean(corsFilter);
	}
	
	/**
	 * 注册跨域
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {		
		registry.addMapping("/**")
			.allowedOrigins(CrossOrigin.DEFAULT_ORIGINS)
			.allowedHeaders(CrossOrigin.DEFAULT_ALLOWED_HEADERS)
			.allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name())
			.exposedHeaders(HttpHeaders.SET_COOKIE)
			.allowCredentials(CrossOrigin.DEFAULT_ALLOW_CREDENTIALS)
			.maxAge(CrossOrigin.DEFAULT_MAX_AGE);		
	}
	
	/**
	 * 添加资源路径映射
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
