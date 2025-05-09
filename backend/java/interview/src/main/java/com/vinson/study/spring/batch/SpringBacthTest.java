package com.vinson.study.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBacthTest {

    public static void main(String[] args) {
        // Spring Java config
        System.out.println("Starting the batch job");
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
//        context.register(SpringConfig.class);
            context.register(SpringBatchConfig.class);
            context.refresh();
            
            JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
            Job job = (Job) context.getBean("firstBatchJob");
            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Job Status : " + execution.getStatus());
            System.out.println("Job completed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Job failed");
        }
    }
}
