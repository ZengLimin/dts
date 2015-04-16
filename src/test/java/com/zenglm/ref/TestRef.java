package com.zenglm.ref;

import java.lang.reflect.Field;

import org.junit.Test;

public class TestRef {
	@Test
	public void testName() throws Exception {
		Man m = new Man();
		//Field age = m.getClass().getField("age");
		Field age = m.getClass().getDeclaredField("age");
		age.setAccessible(true);
		age.set(m, 20);
		//Field name = m.getClass().getField("name");
		Field name = m.getClass().getDeclaredField("name");
		name.setAccessible(true);
		name.set(m, "Dawn");
		System.out.println(m.getAge());
		System.out.println(m.getName());
	}
}
