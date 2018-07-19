package br.com.institutoitn.gps;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Executor;

@SpringBootApplication
//@EnableScheduling
@EnableAsync
//@EnableSwagger2
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
/*	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("WSocket GPS Sender-");

		executor.initialize();
		return executor;
	}*/

}
