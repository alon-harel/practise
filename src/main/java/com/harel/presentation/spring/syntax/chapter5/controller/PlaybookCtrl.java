package com.harel.presentation.spring.syntax.chapter5.controller;

import com.harel.presentation.spring.syntax.chapter5.service.PlaybookScoreCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaybookCtrl {
    @Autowired
    private PlaybookScoreCalculator playbookScoreCalculator;

    public PlaybookCtrl() {
        System.out.println("After constructing Spring will scan the class and search for @Autowire annotations");
    }

//    public PlaybookCtrl(PlaybookScoreCalculator playbookScoreCalculator) {
//        this.playbookScoreCalculator = playbookScoreCalculator;
//        System.out.println("Spring will inject the calculator via this c'tr");
//    }

    @GetMapping(path = "calc")
    public Integer calc() {
        return playbookScoreCalculator.calc(10);
    }
}