package com.unir.eureka_servir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaServirApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServirApplication.class, args);
	}

}
