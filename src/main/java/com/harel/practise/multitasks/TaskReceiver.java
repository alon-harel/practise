package com.harel.practise.multitasks;

import com.harel.practise.multitasks.task.SomeTask;
import com.harel.practise.multitasks.task.Task;

public class TaskReceiver {
    private TaskExecuter taskExecuter;
    private TaskRepository taskRepository;

    public TaskReceiver(TaskExecuter taskExecuter, TaskRepository taskRepository) {
        this.taskExecuter = taskExecuter;
        this.taskRepository = taskRepository;
    }

    public int receive(int taskId, String taskInput) {
        Task task = createTask(taskId);
        int jobId = taskExecuter.run(task, taskInput);
        taskRepository.writeStatus(jobId, "STARTING");

        return jobId;
    }

    private Task createTask(int taskId) {
        // Use Factory:
        return new SomeTask();
    }
}
