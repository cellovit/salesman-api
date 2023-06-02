package com.aiv.crud.salesmanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SalesmanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesmanApiApplication.class, args);
	}

}
