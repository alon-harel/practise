package com.harel.presentation.spring.syntax.chapter4.dal;

import com.harel.presentation.spring.Playbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class PgDemoPlaybookDao implements PlaybookDao {
    public Playbook readPlaybookById(long playbookId) {
        System.out.println("reading the playbook from the demo PG");
        return new Playbook();
    }

    @Profile("demo")
    @Configuration
    public static class Beans {
        @Bean
        public PgDemoPlaybookDao playbookDao() {
            return new PgDemoPlaybookDao();
        }
    }
}
