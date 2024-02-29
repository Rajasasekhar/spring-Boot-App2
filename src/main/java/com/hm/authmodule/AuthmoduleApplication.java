package com.hm.authmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hm.suthmodule.pojo")
public class AuthmoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthmoduleApplication.class, args);
	}

}
