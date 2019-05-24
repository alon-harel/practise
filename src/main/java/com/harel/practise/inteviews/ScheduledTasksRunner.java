package com.harel.practise.inteviews;


import java.util.Map;
import java.util.TreeMap;

/*
Implement a scheduled tasks timer that has these methods:
add task
run the tasks.
 */
public class ScheduledTasksRunner implements Runnable
{
    private TreeMap<Long, Task> executionTimeToTasks = new TreeMap<>();

    public ScheduledTasksRunner() {
        runTasks();
    }

    public synchronized void addTask(Task task, long timeToRun) {
        // Put the new task and notify the runner to verify if it need to change its wait time:
        executionTimeToTasks.put(timeToRun, task);
        notify();
    }

    private void runTasks() {
        Thread runnerThread = new Thread(this);
        runnerThread.start();
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (executionTimeToTasks.isEmpty()) {
                // No scheduled Tasks. Wait for a second and look again
                try {
                    System.out.println("No Tasks to run");
                    wait(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                long nextTimeToRunTask = executionTimeToTasks.firstKey();
                try {
                    // Wait till the first task needs to be executed:
                    wait(nextTimeToRunTask - System.currentTimeMillis());

                    // Did we got here because its time to run the task (the current time
                    // is bigger then the task's scheduled time), or because a new task
                    // with a sooner schedule time arrived?
                    final Map.Entry<Long, Task> firstEntry = executionTimeToTasks.firstEntry();
                    if (firstEntry.getKey() <= System.currentTimeMillis()) {
                        firstEntry.getValue().execute();
                        executionTimeToTasks.remove(firstEntry.getKey());
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Task {
        int id;

        public Task(int id) {
            this.id = id;
        }

        void execute() {
            System.out.println(id + ": " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();
        ScheduledTasksRunner scheduledTasksRunner = new ScheduledTasksRunner();

        scheduledTasksRunner.addTask(new Task(1), currentTime + 4000);
        scheduledTasksRunner.addTask(new Task(2), currentTime + 3000);
        scheduledTasksRunner.addTask(new Task(5), currentTime + 1000);
        scheduledTasksRunner.addTask(new Task(4), currentTime + 2000);
        scheduledTasksRunner.addTask(new Task(3), currentTime + 5000);

        // Expected order is : 5, 4, 2, 1, 3

    }
}
