package com.jeffrey.context.configuration;

import com.jeffrey.manager.OssManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description: Swagger配置，只在开发环境开启
 *
 * @author WQ
 * @date 2020/8/31 11:35 AM
 */
@Configuration
@EnableSwagger2
@Profile({"local", "dev"})
public class SwaggerConfiguration {

	@Autowired
	@Qualifier("ccbOssManager")
	private OssManager ccbOssManager;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jeffrey.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SpringBoot-MybatisPlus-Security-falsework API Doc")
				.description("This is a restful api document of SpringBoot-MybatisPlus-Security-falsework.").version("1.0").build();
	}
}