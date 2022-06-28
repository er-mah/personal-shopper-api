package com.mah.personalshopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PersonalShopperApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalShopperApplication.class, args);
	}

}
