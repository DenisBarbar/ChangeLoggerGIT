package com.fast_report.changelogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.swagger.client.model.ChangeVM;

public class ChangeVMCallback implements ChangeVMCallbackInterface {
    private final Lock lock;
    private final Condition condition;
    private List<ChangeVM> mChanges;
    private volatile boolean isReady;

    ChangeVMCallback() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    @Override
    public void callback(List<ChangeVM> changes) {
        lock.lock();

        try {
            if (isReady)
                throw new IllegalStateException("This callback has already been performed");

            mChanges = new ArrayList<>(changes);
            isReady = true;
            condition.signalAll();
        }

        finally {
            lock.unlock();
        }
    }

    @Override
    public void await() throws InterruptedException {
        lock.lock();

        try {
            while(!isReady)
                condition.await();
        }

        finally {
            lock.unlock();
        }
    }

    @Override
    public List<ChangeVM> getChanges() {
        return mChanges;
    }

    @Override
    public void error(Throwable what) {
        throw new RuntimeException(what);
    }
}
