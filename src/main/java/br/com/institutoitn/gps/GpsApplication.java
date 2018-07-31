package br.com.institutoitn.gps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableScheduling
@EnableAsync
@EnableSwagger2
@EnableAutoConfiguration
public class GpsApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(GpsApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GpsApplication.class);
	}

	@Bean
	public Integer port() {
		// HTTP PORT
		// TODO put it in the properties file next to HTTPS settings
		return 8989;

	}

}
