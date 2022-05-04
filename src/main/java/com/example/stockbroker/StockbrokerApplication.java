package com.example.stockbroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.stockbroker"})
public class StockbrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockbrokerApplication.class, args);
	}

}
