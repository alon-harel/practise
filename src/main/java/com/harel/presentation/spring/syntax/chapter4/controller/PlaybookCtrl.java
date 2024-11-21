package com.harel.presentation.spring.syntax.chapter4.controller;


import com.harel.presentation.spring.syntax.chapter4.service.PlaybookScoreCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaybookCtrl {
    private final PlaybookScoreCalculator playbookScoreCalculator;

    public PlaybookCtrl(PlaybookScoreCalculator playbookScoreCalculator) {
        this.playbookScoreCalculator = playbookScoreCalculator;
    }

    @GetMapping(path = "calc")
    public Integer calc() {
        return playbookScoreCalculator.calc(10);
    }

    @Configuration
    @Import(PlaybookScoreCalculator.Beans.class)
    public static class Beans {
    }
}