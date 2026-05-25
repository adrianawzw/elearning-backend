package com.elearning.platform_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.elearning.platform_backend.config.JwtConfig;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class PlatformBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformBackendApplication.class, args);
	}

}
