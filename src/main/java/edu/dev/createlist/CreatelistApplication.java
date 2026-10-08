package edu.dev.createlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaRepositories(basePackages = {
		"edu.dev.createlist.infrastructure.repository.postgres",
		"edu.dev.createlist.infrastructure.repository.h2",
})
@EnableMongoRepositories(basePackages = "edu.dev.createlist.infrastructure.repository.mongo")
@SpringBootApplication
public class CreatelistApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreatelistApplication.class, args);
	}

}
