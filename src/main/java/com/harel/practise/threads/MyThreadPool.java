package com.harel.practise.threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyThreadPool {
    private final Integer lock = 1;
    private List<Thread> threads = new ArrayList<>();
    private Queue<Runnable> tasks = new LinkedList<>();

    public MyThreadPool(int tasksNumber) {
        init(tasksNumber);
    }

    private void init(int tasksNumber) {
        for (int index = 0; index < tasksNumber; index++) {
            TaskRunner taskRunner = new TaskRunner();
            final Thread thread = new Thread(taskRunner);
            threads.add(thread);
            thread.start();
        }
    }

    private void shutDown() {
        // Kill all threads:
        threads.forEach(thread -> {
            thread.interrupt();
        });
    }

    public void submit(Runnable runnable) {
        synchronized (lock) {
            tasks.add(runnable);
        }
    }

    private class TaskRunner implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable task;
                synchronized (lock) {
                    task = tasks.poll();
                }
                if (task != null) {
                    task.run();
                }

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {

                }
            }
            System.out.println("Exiting...");
        }

    }

    private static class TaskToRun implements Runnable {

        private String taskName;

        public TaskToRun(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(taskName + " is " + "running in Thread " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(2);
        TaskToRun task1 = new TaskToRun("task1");
        TaskToRun task2 = new TaskToRun("task2");
        TaskToRun task3 = new TaskToRun("task3");

        // Two task will be run immediately (our thread pool has two threads)
        // The third one will be run once a thread is free to take task.
        myThreadPool.submit(task1);
        myThreadPool.submit(task2);
        myThreadPool.submit(task3);

        // TODO: NEED TO SEE HOW TO EXIT GRACEFULLY:
        //myThreadPool.shutDown();
    }


}
