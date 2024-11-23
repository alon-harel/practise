package com.harel.presentation.spring.syntax.chapter5.service;

import com.harel.presentation.spring.Playbook;
import com.harel.presentation.spring.syntax.chapter5.dal.PlaybookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaybookScoreCalculator {
    @Autowired
    private PlaybookDao playbookDao;
    

    public int calc(long playbookId) {
        Playbook playbook = playbookDao.readPlaybookById(playbookId);
        return calc(playbook);
    }

    private int calc(Playbook playbook) {
        return 100;
    }
}
