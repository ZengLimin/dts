package com.zenglm.dts;

public class B extends A {
	private String a;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public static void main(String[] args) {
		B b = new B();
		b.setA("123");
		if (b instanceof A) {
			A a = (A) b;
			System.out.println(a.getA());
		}
	}
}
