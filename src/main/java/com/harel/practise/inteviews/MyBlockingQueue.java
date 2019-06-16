package com.harel.practise.inteviews;

import java.util.LinkedList;
import java.util.Queue;

/*
A Queue that additionally supports operations that wait for the queue to become non-empty when retrieving an element,
and wait for space to become available in the queue when storing an element.
 */
public class MyBlockingQueue<T> {

    private final Integer queueSize;
    private final Queue<T> queue = new LinkedList<>();

    public MyBlockingQueue(int queueSize) {
        this.queueSize = queueSize;
    }

    public synchronized T poll() {
        while (true) {
            try {
                if (queue.isEmpty()) {
                    wait();
                }
                // In case some put is waiting because the queue is full:
                notify();
                return this.queue.poll();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void put(T element) {
        if (queue.size() == queueSize) {
            try {
                // Wait untill a get will be called and the queue will have space.
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.queue.add(element);
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(2);
        Consumer consumer = new Consumer(myBlockingQueue);
        Producer producer = new Producer(myBlockingQueue);

        new Thread(consumer).start();
        new Thread(producer).start();

        Thread.sleep(2000);
        System.out.println("-------------------------------------------");

        ProducerThatWillWait producerThatWillWait = new ProducerThatWillWait(myBlockingQueue);
        new Thread(producerThatWillWait).start();
        Thread.sleep(1000);

        System.out.println("Getting an element out to gain space in the queue");
        myBlockingQueue.poll();

    }

    static class ProducerThatWillWait implements Runnable {
        MyBlockingQueue<Integer> myBlockingQueue;

        public ProducerThatWillWait(MyBlockingQueue<Integer> myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            myBlockingQueue.put(1);
            myBlockingQueue.put(2);

            long beforeWaitingTime = System.currentTimeMillis();
            myBlockingQueue.put(3);
            System.out.println("ProducerThatWillWait waited for: " + (System.currentTimeMillis() - beforeWaitingTime) + " millis before adding the last element.");
        }
    }

    static class Consumer implements Runnable {
        MyBlockingQueue<Integer> myBlockingQueue;

        public Consumer(MyBlockingQueue<Integer> myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            long beforeWaitingTime = System.currentTimeMillis();
            System.out.println("Got from queue: " + myBlockingQueue.poll());
            System.out.println("Consumer waited for: " + (System.currentTimeMillis() - beforeWaitingTime)+ " millis.");

        }
    }

    static class Producer implements Runnable {
        MyBlockingQueue<Integer> myBlockingQueue;

        public Producer(MyBlockingQueue<Integer> myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            System.out.println("Waiting for a second before putting element in queue");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myBlockingQueue.put(1);
        }
    }
}
