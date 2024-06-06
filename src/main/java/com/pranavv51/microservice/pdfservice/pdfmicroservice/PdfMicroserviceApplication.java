package com.pranavv51.microservice.pdfservice.pdfmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class PdfMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfMicroserviceApplication.class, args);
	}

}
