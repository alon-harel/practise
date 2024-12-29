package com.harel.presentation.spring.syntax.chapter4.dal;

import com.harel.presentation.spring.Playbook;

public class PgPlaybookDao implements PlaybookDao {
    public Playbook readPlaybookById(long playbookId) {
        System.out.println("reading the playbook from PG");
        return new Playbook();
    }
}
