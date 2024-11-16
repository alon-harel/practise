package com.harel.presentation.spring.dependencyinjection.chapter3.dal;

import com.harel.presentation.spring.dependencyinjection.Playbook;

public class PgDemoPlaybookDao implements PlaybookDao {
    public Playbook readPlaybookById(long playbookId) {
        System.out.println("reading the playbook from the demo PG");
        return new Playbook();
    }
}
