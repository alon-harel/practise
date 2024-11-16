package com.harel.presentation.spring.dependencyinjection.chapter3;

import com.harel.presentation.spring.dependencyinjection.chapter3.service.PlaybookScoreCalculator;
import com.harel.presentation.spring.dependencyinjection.chapter3.dal.PlaybookDao;
import com.harel.presentation.spring.dependencyinjection.chapter3.dal.PlaybookDaoFactory;

public class PlaybookApp {
    public static void main(String[] args) {
        PlaybookDao playbookDao = PlaybookDaoFactory.provide(args[0]);
        PlaybookScoreCalculator playbookScoreCalculator = new PlaybookScoreCalculator(playbookDao);

        playbookScoreCalculator.calc(100);
    }
}
