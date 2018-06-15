/**
 * 
 */
package com.dog.soa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 集成swagger2构建RESTful API文档说明
 * @author jianglong
 * @date 2017年7月24日 下午4:59:22
 */
@Configuration
public class Swagger2Config {

	/**
	 * 通过 createRestApi函数来构建一个DocketBean
	 */
	@Bean
	public Docket createRestApi() {
		// 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
				// 控制暴露出去的路径下的实例
				// 如果某个接口不想暴露,可以使用以下注解
				// @ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
				.apis(RequestHandlerSelectors.basePackage("com.dog.soa.rest"))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * 构建 api文档的详细信息函数
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				// 创建人
				// 版本号
				// 描述
				.title("DOA-SOA通用接口平台-接口说明：（Swagger2 构建RESTful API）")
				.contact(new Contact("jianglong","","jianglong@hollycrm.com"))
				.version("1.0")
				.description("API 接口描述")
				.build();
	}

}
