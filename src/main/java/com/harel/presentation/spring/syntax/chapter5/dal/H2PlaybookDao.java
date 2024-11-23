package com.harel.presentation.spring.syntax.chapter5.dal;

import com.harel.presentation.spring.Playbook;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("test")
@Repository
public class H2PlaybookDao implements PlaybookDao {
    @Override
    public Playbook readPlaybookById(long playbookId) {
        System.out.println("reading playbook from in memory database (H2)");
        return new Playbook();
    }
}
