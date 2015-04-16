package com.zenglm.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class TestQueue {
	//@Test
	public void testName() throws Exception {
		final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(
				10);
		Thread t = new Thread() {
			public void run() {
				try {
					int i = 0;
					while (true) {
						queue.put(i++);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		};
		t.start();
		while (true) {
			System.out.println(queue.take() + " " + queue.size());
		}
	}
}
