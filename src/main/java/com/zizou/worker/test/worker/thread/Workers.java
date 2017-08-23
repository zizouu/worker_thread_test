package com.zizou.worker.test.worker.thread;

import java.util.concurrent.*;

/**
 * Created by zizou on 2017-08-23.
 */
public class Workers {
    private final int CPU_CORE_CNT = Runtime.getRuntime().availableProcessors();
    private final int MAX_POOL_SIZE = CPU_CORE_CNT * 2;
    private final RejectedExecutionHandler rejectedExecutionHandler = new BlockPolicy();
    private ThreadPoolExecutor workerPoolExecutor = null;
    private BlockingQueue<Runnable> workQueue;
    private int corePoolSize;
    private int maximumPoolsize;
    private int terminateTimeout;
    private int keepAliveTime;
    private TimeUnit timeUnit;

    public Workers(){
        this.corePoolSize = CPU_CORE_CNT / 4;
        this.maximumPoolsize = MAX_POOL_SIZE;
        this.terminateTimeout = 3;
        this.keepAliveTime = 1;
        this.timeUnit = TimeUnit.SECONDS;
    }

    public void initialize(){
        if(this.workerPoolExecutor != null){
            throw new IllegalStateException("woker pool alread initialized");
        }else{
            ThreadFactory threadFactory = new WorkersFactory("TestWoker");
            this.workQueue = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
            this.workerPoolExecutor = new ThreadPoolExecutor(this.corePoolSize, this.maximumPoolsize, (long)this.keepAliveTime, this.timeUnit, this.workQueue, threadFactory, this.rejectedExecutionHandler);
        }
    }

    public void execute(Job job){
        this.workerPoolExecutor.execute(job);
    }

    public void terminate() throws InterruptedException{
        if(this.workerPoolExecutor != null){
            this.workerPoolExecutor.shutdown();
            this.workerPoolExecutor.awaitTermination((long)this.terminateTimeout, TimeUnit.SECONDS);
        }
    }

    public boolean isRun() {
        if (this.workerPoolExecutor == null) {
            return false;
        } else if (this.workQueue.isEmpty()) {
            return this.workerPoolExecutor.getActiveCount() > 0;
        } else {
            return true;
        }
    }
}
