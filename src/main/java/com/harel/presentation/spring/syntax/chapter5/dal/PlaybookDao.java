package com.harel.presentation.spring.syntax.chapter5.dal;

import com.harel.presentation.spring.Playbook;

public interface PlaybookDao {
    Playbook readPlaybookById(long playbookId);
}
