package com.harel.practise.multitasks;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository {
    private Map<Integer, String> jobIdToStatuses = new HashMap<>();

    public void writeStatus(int jobId, String status) {
        this.jobIdToStatuses.put(jobId, status);
    }

    public Map<Integer, String> getStatuses() {
        return jobIdToStatuses;
    }
}
