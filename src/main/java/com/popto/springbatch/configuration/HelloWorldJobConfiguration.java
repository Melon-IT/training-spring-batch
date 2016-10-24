package com.popto.springbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.popto.springbatch.HelloWorldTasklet;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class HelloWorldJobConfiguration {
	
	/*
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }
    
    
    @Bean
    public JobRepository jobRepository() throws Exception {
        return new MapJobRepositoryFactoryBean(transactionManager()).getObject();
    }
	
    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        return jobLauncher;
    }
	*/
	
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;
  
    @Bean
    public Job job(
    		@Qualifier("step1") Step step1,
    		@Qualifier("step2") Step step2) {
    	
        return jobs
        		.get("myJob")
        		.start(step1)
        		.next(step2)
        		.build();
    }

    @Bean
    protected Step step1() {
    	
        return steps
        		.get("step1")
        		.tasklet(new HelloWorldTasklet())
        		.build();
    }

    @Bean
    protected Step step2() {

        return steps
        		.get("step2")
        		.tasklet(new HelloWorldTasklet())
        		.build();
    }
}
