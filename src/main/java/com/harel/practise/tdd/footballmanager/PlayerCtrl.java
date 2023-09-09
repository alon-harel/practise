package com.harel.practise.tdd.footballmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerCtrl {
    private final PlayerService playerService;

    public PlayerCtrl(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public String get() {
        return playerService.fetch();
    }
}
