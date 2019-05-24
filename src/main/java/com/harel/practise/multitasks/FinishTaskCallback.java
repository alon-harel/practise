package com.harel.practise.multitasks;

public class FinishTaskCallback {

    private final int jobId;
    private TaskRepository taskRepository;

    public FinishTaskCallback(int jobId, TaskRepository taskRepository) {
        this.jobId = jobId;
        this.taskRepository = taskRepository;
    }

    public void call() {
        taskRepository.writeStatus(jobId, "DONE");
    }
}
