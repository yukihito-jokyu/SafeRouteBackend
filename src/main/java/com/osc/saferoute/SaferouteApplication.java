package com.osc.saferoute;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.osc.saferoute.infrastructure.mybatis.mapper")
@SpringBootApplication
public class SaferouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaferouteApplication.class, args);
	}

}
