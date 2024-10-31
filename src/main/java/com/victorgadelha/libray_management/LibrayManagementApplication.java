package com.victorgadelha.libray_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victorgadelha.libray_management.faker.FakeData;

@SpringBootApplication
public class LibrayManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrayManagementApplication.class, args);
	}


}
