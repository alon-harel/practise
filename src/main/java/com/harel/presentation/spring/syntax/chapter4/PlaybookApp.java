package com.harel.presentation.spring.syntax.chapter4;

import com.harel.presentation.spring.syntax.chapter4.service.PlaybookScoreCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackageClasses = PlaybookScoreCalculator.Beans.class)
public class PlaybookApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PlaybookApp.class, args);

        PlaybookScoreCalculator calculator = context.getBean(PlaybookScoreCalculator.class);
        calculator.calc(666);
        System.out.println("calculated playbook score");
    }
}