package com.zizou.worker.test.worker.thread;

/**
 * Created by zizou on 2017-08-23.
 */
public interface Job extends Runnable{
    public void execute() throws Throwable;
}
