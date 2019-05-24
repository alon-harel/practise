package com.harel.practise.multitasks;

import java.util.Map;

public class TaskAdmin {

    private TaskRepository taskRepository;
    private final TaskExecuter taskExecuter;

    public TaskAdmin(TaskRepository taskRepository,
                     TaskExecuter taskExecuter) {
        this.taskRepository = taskRepository;
        this.taskExecuter = taskExecuter;
    }

    public Map<Integer, String> getJobStatus() {
        return taskRepository.getStatuses();
    }

    public void cancel(Integer jobId) {
        taskExecuter.cancel(jobId);
        taskRepository.writeStatus(jobId, "CANCEL");
    }
}
