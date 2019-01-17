package com.dli.todolist;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.dli.todolist.repository") 
@EntityScan(basePackages = {"com.dli.todolist.model", "com.dli.todolist.authorization"})
public class Application extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

/*
 * public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

 */