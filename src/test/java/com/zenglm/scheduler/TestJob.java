package com.zenglm.scheduler;

import java.util.TimerTask;

public class TestJob extends TimerTask {
	private String jobName;

	public TestJob(String jobName) {
		super();
		this.jobName = jobName;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("execute " + jobName);
	}

}
