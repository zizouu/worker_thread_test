package com.zizou.worker.test.worker;

import com.zizou.worker.test.worker.service.SmtpSendJob;
import com.zizou.worker.test.worker.thread.Workers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);
		Workers workers = new Workers();
		workers.initialize();
		long start = System.currentTimeMillis();
		send(workers);
		while(workers.isRun()){
			try{
				Thread.sleep(1000L);
			}catch (InterruptedException e){
				e.printStackTrace();
			}

		}
		long end = System.currentTimeMillis();
		System.out.println("Process Time : " + (start - end) / 1000 + " sec");
		try {
			workers.terminate();
		}catch (InterruptedException e){
			e.printStackTrace();
		}

	}

	public static void send(Workers workers){

		for(int i = 0; i < 100; i++){
			workers.execute(new SmtpSendJob());
		}

	}
}
