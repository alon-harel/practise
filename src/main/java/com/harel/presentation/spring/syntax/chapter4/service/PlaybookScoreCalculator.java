package com.harel.presentation.spring.syntax.chapter4.service;

import com.harel.presentation.spring.Playbook;
import com.harel.presentation.spring.syntax.chapter4.dal.H2PlaybookDao;
import com.harel.presentation.spring.syntax.chapter4.dal.PgDemoPlaybookDao;
import com.harel.presentation.spring.syntax.chapter4.dal.PgPlaybookDao;
import com.harel.presentation.spring.syntax.chapter4.dal.PlaybookDao;
import com.harel.presentation.spring.syntax.chapter4.dal.PlaybookDaoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


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

    @Configuration
    @Import({PlaybookDaoConfig.class})
    public static class Beans {
        @Bean
        public PlaybookScoreCalculator playbookScoreCalculator(PlaybookDao playbookDao) {
            return new PlaybookScoreCalculator(playbookDao);
        }
    }
}
