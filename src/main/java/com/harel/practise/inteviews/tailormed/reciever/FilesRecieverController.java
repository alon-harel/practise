package com.harel.practise.inteviews.tailormed.reciever;


import com.harel.practise.inteviews.tailormed.queue.FilesQueue;

import java.io.File;

// This represnts the end point we expose. The hospitals will invoke it.
public class FilesRecieverController {

    private final FilesQueue filesQueue;

    public FilesRecieverController(FilesQueue filesQueue) {
        this.filesQueue = filesQueue;
    }


    public void recievePatient(File file) {
        // In rabbit I can distinguish between patient and treatment with a routing key.
        filesQueue.push(FileData.createPatient(file));

        // Return 200:
    }

    public void recieveTreatmenatData(File file) {
        // In rabbit I can distinguish between patient and treatment with a routing key.
        filesQueue.push(FileData.createTreatment(file));

        // Return 200:
    }

}
