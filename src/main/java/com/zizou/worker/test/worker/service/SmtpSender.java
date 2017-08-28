package com.zizou.worker.test.worker.service;

import com.zizou.worker.test.worker.thread.Workers;

/**
 * Created by zizou on 2017-08-24.
 */
public class SmtpSender {
    private Workers workers;
    private int limitedTime;
    private int limitedCount;

    public SmtpSender(){
        this.workers = new Workers();
        this.workers.initialize();
        this.limitedCount = 1;
        this.limitedTime = 60;
    }

    public void sendBulkMail(){
        for(int i = 0; i < this.limitedCount; i++){
            this.workers.execute(new SmtpSendJob());
        }
    }

    public boolean isWorkersRun(){
        return this.workers.isRun();
    }

    public boolean terminateWorkers() throws InterruptedException{
        return this.workers.terminate();
    }
}
