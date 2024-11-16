package com.harel.presentation.spring.dependencyinjection.chapter3.dal;

import com.harel.presentation.spring.dependencyinjection.Playbook;

public class H2PlaybookDao implements PlaybookDao {
    @Override
    public Playbook readPlaybookById(long playbookId) {
        System.out.println("reading playbook from in memory database (H2)");
        return new Playbook();
    }
}
