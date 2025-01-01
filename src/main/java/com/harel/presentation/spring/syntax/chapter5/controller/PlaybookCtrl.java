package com.harel.presentation.spring.syntax.chapter5.controller;

import com.harel.presentation.spring.syntax.chapter5.service.PlaybookScoreCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaybookCtrl {
    @Autowired
    private PlaybookScoreCalculator playbookScoreCalculator;

    @GetMapping(path = "calc")
    public Integer calc() {
        return playbookScoreCalculator.calc(10);
    }
}