package com.popto.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class SpringBatchTrainingApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringBatchTrainingApplication.class, args);

		ApplicationContext context = new ClassPathXmlApplicationContext("hello-world-job.xml");
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("job");

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			
			System.out.println("Exit Status : " + execution.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
