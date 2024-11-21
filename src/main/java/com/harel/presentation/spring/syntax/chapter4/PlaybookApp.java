package com.harel.presentation.spring.syntax.chapter4;

import com.harel.presentation.spring.syntax.chapter4.controller.PlaybookCtrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = PlaybookCtrl.class)
public class PlaybookApp {
    public static void main(String[] args) {
        SpringApplication.run(PlaybookApp.class, args);
    }
}