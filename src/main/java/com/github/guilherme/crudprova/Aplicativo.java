package com.github.guilherme.crudprova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.github.guilherme.crudprova.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.github.guilherme.crudprova"})
public class Aplicativo {

	public static void main(String[] args) {
		SpringApplication.run(Aplicativo.class, args);
	}
}
