package com.geekcap.javaworld;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
    	
    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("jobs/file-import-job.xml");
    	JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        Job job = ctx.getBean("simpleFileImportJob", Job.class);
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("inputFile","sample.csv");
        JobExecution jobExecution = jobLauncher.run(job, jobParametersBuilder.toJobParameters());
        BatchStatus batchStatus = jobExecution.getStatus();
		
    }
}
