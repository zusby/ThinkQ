package it.zusby.ThinkQ;

import it.zusby.ThinkQ.Auth2.UserEntity;
import it.zusby.ThinkQ.Auth2.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ThinkQApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThinkQApplication.class, args);
	}

}
