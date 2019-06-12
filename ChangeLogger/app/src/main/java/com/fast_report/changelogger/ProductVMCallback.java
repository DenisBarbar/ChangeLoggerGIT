package com.fast_report.changelogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.swagger.client.model.ProductVM;

public class ProductVMCallback implements  ProductVMCallbackInterface {
    private final Lock lock;
    private final Condition condition;
    private List<ProductVM> mProducts;
    private volatile boolean isReady;

    ProductVMCallback() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }


    @Override
    public void callback(List<ProductVM> products) {
        lock.lock();

        try {
            if (isReady)
                throw new IllegalStateException("This callback has already been performed");

            mProducts = new ArrayList<>(products);
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
    public List<ProductVM> getProducts() {
        return mProducts;
    }

    @Override
    public void error(Throwable what) {
        throw new RuntimeException(what);
    }
}
