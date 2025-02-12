package com.harel.presentation.spring.syntax.chapter4.dal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PlaybookDaoConfig {

    @Profile("prod")
    @Bean
    public PgPlaybookDao pgPlaybookDao() {
        System.out.println("constructing PgPlaybookDao");
        return new PgPlaybookDao();
    }

    @Profile("demo")
    @Bean
    public PgDemoPlaybookDao pgDemoPlaybookDao() {
        System.out.println("constructing PgDemoPlaybookDao");
        return new PgDemoPlaybookDao();
    }

    @Profile("test")
    @Bean
    public H2PlaybookDao h2PlaybookDao() {
        System.out.println("constructing H2PlaybookDao");
        return new H2PlaybookDao();
    }
}
