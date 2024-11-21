package com.harel.presentation.spring.dependencyinjection.chapter3.service;

import com.harel.presentation.spring.Playbook;
import com.harel.presentation.spring.dependencyinjection.chapter3.dal.PlaybookDao;

public class PlaybookScoreCalculator {
    private final PlaybookDao playbookDao;

    public PlaybookScoreCalculator(PlaybookDao playbookDao) {
        this.playbookDao = playbookDao;
    }

    public int calc(long playbookId) {
        Playbook playbook = playbookDao.readPlaybookById(playbookId);
        return calc(playbook);
    }

    private int calc(Playbook playbook) {
        return 100;
    }
}
