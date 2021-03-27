package br.com.viavarejo.simulator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
   
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("br.com.viavarejo.simulator"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(getApiInfo());
    }
	
	private ApiInfo getApiInfo() {
	    return new ApiInfoBuilder()
	    		.title("Simulação de compra de produto.")
	    		.description("Simula a compra de um produto e calcula valor do acréscimo da parcela se parcela > 6.")
	    		.version("1.0.0")
	    		.build();
	}
}
