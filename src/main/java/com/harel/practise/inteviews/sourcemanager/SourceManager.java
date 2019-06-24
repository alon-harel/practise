package com.harel.practise.inteviews.sourcemanager;

/*
"Design a source manager with the following API: GET, SET, DELETE, BEGIN_TX, COMMIT_TX,
 ROLLBACK_TX"
 */
public interface SourceManager {
    String get(String key);
    void set(String key, String value);
    void delete(String key);
    void begin();
    void commit();
    void rollback();
}
