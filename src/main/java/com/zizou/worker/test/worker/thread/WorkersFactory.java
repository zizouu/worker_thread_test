package com.zizou.worker.test.worker.thread;

import java.util.concurrent.ThreadFactory;

/**
 * Created by zizou on 2017-08-23.
 */
public class WorkersFactory implements ThreadFactory{
    private int count = 0;
    private String name;

    public WorkersFactory(String name){
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, this.name + " - " + ++this.count);
        return thread;
    }
}
