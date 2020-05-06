package com.project.productservice;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableHystrix
@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
public class ProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

		@Bean
		public Docket productApi()
		{
			return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.project.productservice"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());

			
		}

		private ApiInfo apiInfo() {
			return new ApiInfo("ProductManagement", "description","version",
					"termsOfServiceUrl",
					new springfox.documentation.service.Contact("shan","url", "email.com"),
					"license","licenseUrl",Collections.emptyList());
		}
		
}
