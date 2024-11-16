package com.harel.presentation.spring.dependencyinjection.chapter2;

import com.harel.presentation.spring.dependencyinjection.Playbook;

public class PlaybookScoreCalculator {

    public int calc(long playbookId) {
        Playbook playbook = readPlaybookById(playbookId);
        return calc(playbook);
    }

    private Playbook readPlaybookById(long playbookId) {
        PlaybookDao playbookDao = new PlaybookDao();
        return playbookDao.readPlaybookById(playbookId);
    }

    private int calc(Playbook playbook) {
        return 100;
    }
}
