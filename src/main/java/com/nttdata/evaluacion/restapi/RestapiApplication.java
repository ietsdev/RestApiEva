package com.nttdata.evaluacion.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.nttdata.evaluacion.restapi.*")
@ComponentScan(basePackages = { "com.nttdata.evaluacion.restapi.*" })
@EntityScan("com.nttdata.evaluacion.restapi.*")   
@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

}
