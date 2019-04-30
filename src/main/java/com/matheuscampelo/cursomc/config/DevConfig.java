package com.matheuscampelo.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matheuscampelo.cursomc.services.DBService;
import com.matheuscampelo.cursomc.services.EmailService;
import com.matheuscampelo.cursomc.services.SmtpEmailService;
@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBService dbService;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	
	@Bean
public boolean instantiateDatabase() throws ParseException {
		if("create".equals(strategy)) {
		dbService.instatiateDatabase();
		return true;
		}
		return false;
	}
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
