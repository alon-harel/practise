package com.harel.practise.multitasks;

import com.harel.practise.multitasks.task.Task;

import java.util.concurrent.Callable;

public class TaskCallable implements Callable<String> {
    private Task task;
    private String input;
    private final int jobId;
    private final TaskExecuter taskExecuter;


    public TaskCallable(Task task, String input, int jobId, TaskExecuter taskExecuter) {
        this.task = task;
        this.input = input;
        this.jobId = jobId;
        this.taskExecuter = taskExecuter;
    }

    @Override
    public String call()     {
        String result = task.execute(input);
       // taskExecuter.done();
        return result;
    }
}
