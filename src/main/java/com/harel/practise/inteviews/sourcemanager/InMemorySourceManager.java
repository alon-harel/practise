package com.harel.practise.inteviews.sourcemanager;

import java.util.HashMap;
import java.util.Map;

public class InMemorySourceManager extends SourceManagerBase{

    private final static Map<String, String> database = new HashMap<>();

    @Override
    protected String doGet(String key) {
        return database.get(key);
    }

    @Override
    protected void doSet(String key, String value) {
        database.put(key, value);
    }

    @Override
    protected void doDelete(String key) {
        database.remove(key);
    }
}
