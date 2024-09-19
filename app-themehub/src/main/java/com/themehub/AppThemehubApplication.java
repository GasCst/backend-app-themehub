package com.themehub;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AppThemehubApplication {
	public static void main(String[] args) {

		SpringApplication.run(AppThemehubApplication.class, args);
	}

	@Bean
	public OpenAPI info(){
		return new OpenAPI()
				.info(new Info()
				.title("app-themehub")
						.description("API_THEMEHUB_PROGETTO_PSW")
						.contact(new Contact().email("gascst1@gmail.com")));
	}

}
