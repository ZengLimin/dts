package com.zenglm.lambda;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LambdaTest {

	@Test
	public void test() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		numbers.forEach(System.out::println);
		int value = 1;
		Thread t = new Thread(() -> {
			System.out.println(value);
		});
		t.start();
	}

}
