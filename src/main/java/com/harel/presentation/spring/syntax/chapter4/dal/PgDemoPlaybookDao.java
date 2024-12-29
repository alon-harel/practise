package com.harel.presentation.spring.syntax.chapter4.dal;

import com.harel.presentation.spring.Playbook;

public class PgDemoPlaybookDao implements PlaybookDao {
    public Playbook readPlaybookById(long playbookId) {
        System.out.println("reading the playbook from the demo PG");
        return new Playbook();
    }
}
