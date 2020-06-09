package com.thoughtworks.dddpractice.representation.restful;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SPRING_WEB)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage(this.getClass().getPackage().getName()))
      .paths(PathSelectors.any())
      .build()
      .enable(true);
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("ThoughtWorks DDD Sample")
      .description("A sample project for ddd implementation")
      .version("1.0.0-SNAPSHOT")
      .contact(new Contact("N/A", "N/A", "nobody@gmail.com"))
      .build();
  }
}
