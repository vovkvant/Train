package com.example.app;
import com.example.app.entity.Train;
import com.example.app.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = { "com.example.app.*" })
@EntityScan("com.example.app.entity.*")
@EnableJpaRepositories
public class TrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainApplication.class, args);
	}

	@Bean
    CommandLineRunner initDatabase(TrainRepository repository) {

        return args -> {
            repository.save(new Train(123L, "234", 43));
            repository.save(new Train(546L, "546", 56));
            repository.save(new Train(897L, "897", 69));
            repository.save(new Train(964L, "964", 87));
        };
    }
}
