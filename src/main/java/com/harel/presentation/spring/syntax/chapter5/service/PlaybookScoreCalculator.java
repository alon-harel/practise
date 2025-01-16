package com.harel.presentation.spring.syntax.chapter5.service;

import com.harel.presentation.spring.Playbook;
import com.harel.presentation.spring.syntax.chapter5.dal.PlaybookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaybookScoreCalculator {
    @Autowired
    private PlaybookDao playbookDao;

//    public PlaybookScoreCalculator() {
//        System.out.println("After constructing Spring will scan the class and search for @Autowire annotations");
//    }

//    public PlaybookScoreCalculator(PlaybookDao playbookDao) {
//        this.playbookDao = playbookDao;
//        System.out.println("Spring will inject the calculator via this c'tr");
//    }

    public int calc(long playbookId) {
        Playbook playbook = playbookDao.readPlaybookById(playbookId);
        return calc(playbook);
    }

    private int calc(Playbook playbook) {
        return 100;
    }
}
