package com.fast_report.changelogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.swagger.client.model.ProductVersionVM;

public class VersionVMCallback implements  VersionVMCallbackInterface {
    private final Lock lock;
    private final Condition condition;
    private List<ProductVersionVM> mVersions;
    private volatile boolean isReady;

    VersionVMCallback() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }


    @Override
    public void callback(List<ProductVersionVM> versions) {
        lock.lock();

        try {
            if (isReady)
                throw new IllegalStateException("This callback has already been performed");

            mVersions = new ArrayList<>(versions);
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
    public List<ProductVersionVM> getVersions() {
        return mVersions;
    }

    @Override
    public void error(Throwable what) {
        throw new RuntimeException(what);
    }
}