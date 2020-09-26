package com.options.tracker.optionstracker;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class OptionsTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptionsTrackerApplication.class, args);	
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("lets inspect the beans provided by Spring Boot");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);

			for(String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

}
