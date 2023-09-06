package com.br.ciasc;

import com.br.ciasc.Filters.AuthFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CiascApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiascApplication.class, args);
	}

	@Autowired
	private AuthFilter autFilter;


	/**
	 * adicionamos o filtro de login na navegação
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(autFilter);
		// aplica-se apenas ao endpoint tarefa
		registrationBean.addUrlPatterns("/tarefa/*");
		// define a ordem de precedencia do filtro
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("REST API BLOG")
						.version("1.0.0")
						.contact(new Contact().email("teste@mail.com")
								.name("REST API"))
						.description("REST API BLOG"))
				.addSecurityItem(new SecurityRequirement().addList("Auth JWT"))
				.components(new Components()
						.addSecuritySchemes("Auth JWT",
								new SecurityScheme()
										.name("Auth JWT")
										.type(SecurityScheme.Type.HTTP)
										.scheme("Bearer")
										.bearerFormat("JWT")));


	}
}
