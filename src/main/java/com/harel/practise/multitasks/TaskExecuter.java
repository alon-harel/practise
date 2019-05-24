package com.harel.practise.multitasks;

import com.harel.practise.multitasks.task.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskExecuter {

    int jobId = 0;
    Map<Integer, Future<String>> jobIdToSubmittedJobs = new HashMap<>();
    ExecutorService es = Executors.newFixedThreadPool(5);
    TaskRepository taskRepository;

    public TaskExecuter(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public int run(Task task, String taskInput) {
        final Future<String> submit = es.submit(new TaskCallable(task, taskInput, ++jobId, this));
        jobIdToSubmittedJobs.put(jobId, submit);

        return jobId;
    }

    public void cancel(Integer jobId) {
        if (jobIdToSubmittedJobs.containsKey(jobId)) {
            final Future<String> task = jobIdToSubmittedJobs.get(jobId);
            if (!task.isDone() && !task.isCancelled()) {
                task.cancel(true);
            }
        }
    }
}
