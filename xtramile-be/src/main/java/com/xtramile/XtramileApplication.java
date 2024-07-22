package com.xtramile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xtramile.config", "com.xtramile.repository", "com.xtramile.service","com.xtramile.model","com.xtramile.controller"})
public class XtramileApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtramileApplication.class, args);
	}

}
