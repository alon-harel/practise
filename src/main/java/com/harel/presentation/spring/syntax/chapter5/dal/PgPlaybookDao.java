package com.harel.presentation.spring.syntax.chapter5.dal;

import com.harel.presentation.spring.Playbook;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("prod")
@Repository
public class PgPlaybookDao implements PlaybookDao {
    public Playbook readPlaybookById(long playbookId) {
        System.out.println("reading the playbook from PG");
        return new Playbook();
    }
}
