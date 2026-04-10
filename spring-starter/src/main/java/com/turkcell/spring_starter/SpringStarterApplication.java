package com.turkcell.spring_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Annotation: bulunduğu class,fonk,değişken'e özellik kazandıran yapıdır. 
public class SpringStarterApplication {

	//entrypoint
	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplication.class, args);
	}

}
