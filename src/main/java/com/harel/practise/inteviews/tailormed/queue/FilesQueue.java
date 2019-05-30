package com.harel.practise.inteviews.tailormed.queue;


import com.harel.practise.inteviews.tailormed.reciever.FileData;

import java.util.LinkedList;
import java.util.Queue;

// I will use a queue for moving the files onward to another service.
// In Reality it might be rabbit mq or other queue.
// In my case it is the "shared" resource between the reciever and the consumer.
public class FilesQueue {

    private Queue<FileData> queue = new LinkedList<>();

    public void push(FileData file) {
        queue.add(file);
    }

    public FileData pull() {
        return queue.poll();
    }
}
