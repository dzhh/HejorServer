package com.fly.task;

import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TaskOne extends QuartzJobBean {

	private int timeout;  
	
	public void setTimeout(int timeout) {  
        this.timeout = timeout;  
    }
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("-----定时任务执行-----");
	}
}
