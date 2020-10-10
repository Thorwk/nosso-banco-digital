package com.zup.bootcamp.nossobancodigital;

import com.zup.bootcamp.nossobancodigital.properties.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileUploadProperties.class})
public class NossoBancoDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NossoBancoDigitalApplication.class, args);
	}

}
