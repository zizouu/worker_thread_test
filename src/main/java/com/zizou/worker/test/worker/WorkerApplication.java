package com.zizou.worker.test.worker;

import com.zizou.worker.test.worker.service.SmtpSendJob;
import com.zizou.worker.test.worker.service.SmtpSender;
import com.zizou.worker.test.worker.thread.Workers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);

		SmtpSender sender = new SmtpSender();
		sender.sendBulkMail();

		long start = System.currentTimeMillis();

		while(sender.isWorkersRun()){
			try{
				Thread.sleep(1000L);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("Process Time : " + (end - start) / 1000 + " sec");

		try {
			while (!sender.terminateWorkers()){
				Thread.sleep(1000L);
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}

	}
}
