package com.library_management_system.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
		System.err.println("---------------------------------");
		System.err.println("--- System is up and running! ---");
		System.err.println("---------------------------------");
	}

}
