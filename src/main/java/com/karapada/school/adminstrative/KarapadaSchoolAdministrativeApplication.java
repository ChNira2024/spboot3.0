package com.karapada.school.adminstrative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KarapadaSchoolAdministrativeApplication {

	private static final Logger logger = LoggerFactory.getLogger(KarapadaSchoolAdministrativeApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(KarapadaSchoolAdministrativeApplication.class, args);
		logger.info("Karapada School Administrative Application Started..!");
	}

}
