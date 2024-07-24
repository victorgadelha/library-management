package com.victorgadelha.libray_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victorgadelha.libray_management.faker.FakerData;

@SpringBootApplication
public class LibrayManagementApplication implements CommandLineRunner {

	@Autowired
	FakerData fakerData;

	public static void main(String[] args) {
		SpringApplication.run(LibrayManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.fakerData.generateFakeData();
	}

}
