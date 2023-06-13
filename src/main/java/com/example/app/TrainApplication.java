package com.example.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.example.app.*" })
@EntityScan("com.example.app.entity")
@EnableJpaRepositories
public class TrainApplication {

	public static void main(String[] args) {

		SpringApplication.run(TrainApplication.class, args);
	}


}
