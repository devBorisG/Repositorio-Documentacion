package edu.uco.budget.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.uco.budget"})
public class BudgetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetApiApplication.class, args);
	}

}
