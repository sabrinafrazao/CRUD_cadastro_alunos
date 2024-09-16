package com.api.api_aluno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "com.api.api_aluno")
@EnableJpaRepositories(basePackages = "com.api.api_aluno.domain.repository")
public class ApiAlunoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAlunoApplication.class, args);
	}

}
