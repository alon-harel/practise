package com.harel.practise.inteviews.sourcemanager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class SourceManagerBase implements SourceManager {

    private boolean inTx = false;
    private final Map<String, String> txModifedKesyMap = new HashMap<>();
    private final Set<String> txDeletedKeys = new HashSet<>();

    @Override
    public String get(String key) {
        String retVal = null;
        if (inTx) {
            // In transaction. Get from temp database:
            retVal = txModifedKesyMap.get(key);
            if (retVal == null && !txDeletedKeys.contains(key)) {
                // If the key was not found and was not marked as deleted, take it from real database:
                retVal = doGet(key);
            }
        }
        else {
            // Not in transaction look in database:
            retVal = doGet(key);
        }

        return retVal;
    }

    @Override
    public void set(String key, String value) {
        if (inTx) {
            txModifedKesyMap.put(key, value);
            // If this key was removed, we no longer need to remove it:
            txDeletedKeys.remove(key);
        }
        else {
            doSet(key, value);
        }
    }

    @Override
    public void delete(String key) {
        if (inTx) {
            txModifedKesyMap.remove(key);
            txDeletedKeys.add(key);
        }
        else {
            doDelete(key);
        }
    }

    @Override
    public void begin() {
        inTx = true;
    }

    @Override
    public void commit() {
        inTx = false;
        txModifedKesyMap.forEach(this::doSet);
        txDeletedKeys.forEach(this::doDelete);
        txDeletedKeys.clear();
        txModifedKesyMap.clear();
    }

    @Override
    public void rollback() {
        inTx = false;
        txDeletedKeys.clear();
        txModifedKesyMap.clear();
    }

    protected abstract String doGet(String key);
    protected abstract void doSet(String key, String value);
    protected abstract void doDelete(String key);
}
