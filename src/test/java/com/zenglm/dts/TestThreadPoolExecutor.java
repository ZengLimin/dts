package com.zenglm.dts;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestThreadPoolExecutor {
	@Test
	public void testName() throws Exception {
		ThreadPoolExecutor scheduler = new ThreadPoolExecutor(1, 3, 60,
				TimeUnit.SECONDS,
				// ActiveObjectPattern.ActivationQueue
				new LinkedBlockingQueue<Runnable>(200), new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r, "AsyncRequestPersistence");
						return t;
					}

				});
		scheduler
				.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

	}
}
