package com.caiobruno.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.caiobruno.helpdesk.services.DBService;

import jakarta.validation.Valid;

@Configuration
@Profile("dev")
public class Devconfig2 {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;

	@Bean
	public boolean instaciaDB() {
		if (value.equals("create")) {
			this.dbService.instaciaDB();
		}
		return false;
	}
}
