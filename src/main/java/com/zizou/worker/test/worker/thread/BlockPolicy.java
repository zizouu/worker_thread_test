package com.zizou.worker.test.worker.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zizou on 2017-08-23.
 */
public class BlockPolicy implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(!executor.isShutdown()){
            try{
                executor.getQueue().put(r);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
