package com.bshhr.docgenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bshhr.docgenerator.mapper")
public class DocGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocGeneratorApplication.class, args);
	}

}
