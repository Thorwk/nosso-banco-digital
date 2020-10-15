package com.zup.bootcamp.nossobancodigital;

import com.zup.bootcamp.nossobancodigital.properties.FileUploadProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableRetry
@EnableAsync
@EnableConfigurationProperties({FileUploadProperties.class})
public class NossoBancoDigitalApplication {

	private static Logger logger = LoggerFactory.getLogger(NossoBancoDigitalApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando a api Nosso-Banco-Digital");
		SpringApplication.run(NossoBancoDigitalApplication.class, args);
		logger.info("Api iniciada e pronta para receber requisições");
	}

}
