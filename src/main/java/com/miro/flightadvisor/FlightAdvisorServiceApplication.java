package com.miro.flightadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.concurrent.Executor;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableSwagger2
public class FlightAdvisorServiceApplication {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build()
                .apiInfo(new ApiInfo("Flight Advisor API'S",
                        "API's for finding cheapest flight", "1.0.0", null,
                        new Contact("Miroslav Trninic@", "https://antitask.com", "miroslav.trninic@gmail.com"),
                        null, null, new ArrayList()));
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightAdvisorServiceApplication.class, args);
    }

    /**
     * This executor is used to reduce load on memory and resources in general when lot (big file) of data is imported and parsed
     *
     * @return
     */
    @Bean(name = "importExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Importing data-");
        executor.initialize();
        return executor;
    }
}
