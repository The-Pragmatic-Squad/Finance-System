package io.github.thepragmaticsquad.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"io.github.thepragmaticsquad.fs.repository"})
public class FinanceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceSystemApplication.class, args);
	}

}
