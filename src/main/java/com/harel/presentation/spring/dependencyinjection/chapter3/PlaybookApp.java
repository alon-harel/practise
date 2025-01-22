package com.harel.presentation.spring.dependencyinjection.chapter3;

import com.harel.presentation.spring.dependencyinjection.chapter3.service.PlaybookScoreCalculator;
import com.harel.presentation.spring.dependencyinjection.chapter3.dal.PlaybookDao;
import com.harel.presentation.spring.dependencyinjection.chapter3.dal.PlaybookDaoContext;

public class PlaybookApp {
    public static void main(String[] args) {
        String profile = args[0];
        System.out.println("application running with profile=" + profile);
        PlaybookDao playbookDao = PlaybookDaoContext.getDaoBean(profile);
        PlaybookScoreCalculator playbookScoreCalculator = new PlaybookScoreCalculator(playbookDao);

        playbookScoreCalculator.calc(100);
    }
}
