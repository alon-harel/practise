package com.harel.presentation.spring.syntax.chapter4.dal;

import com.harel.presentation.spring.Playbook;

public class H2PlaybookDao implements PlaybookDao {
    @Override
    public Playbook readPlaybookById(long playbookId) {
        System.out.println("reading playbook from in memory database (H2)");
        return new Playbook();
    }
}
