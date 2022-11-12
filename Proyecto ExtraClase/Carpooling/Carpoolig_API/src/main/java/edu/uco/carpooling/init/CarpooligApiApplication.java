package edu.uco.carpooling.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.uco.carpooling"})
public class CarpooligApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarpooligApiApplication.class, args);
	}

}
