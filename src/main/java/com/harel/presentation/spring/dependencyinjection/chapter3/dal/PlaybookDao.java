package com.harel.presentation.spring.dependencyinjection.chapter3.dal;

import com.harel.presentation.spring.Playbook;

public interface PlaybookDao {
    Playbook readPlaybookById(long playbookId);
}
