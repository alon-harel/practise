package com.harel.practise.multitasks.task;

public class SomeTask implements Task {
    @Override
    public String execute(String input) {

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "SUCCESS";
    }
}
