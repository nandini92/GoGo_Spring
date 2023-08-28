package com.gogo_ecommerce.gogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class GogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GogoApplication.class, args);
	}

}
