package com.trabalho.api.autores_livros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableScheduling
@SpringBootApplication
@EnableAsync
public class AutoresLivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoresLivrosApplication.class, args);
	}

}



