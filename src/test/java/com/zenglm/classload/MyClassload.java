package com.zenglm.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassload extends ClassLoader {
	private byte[] bs;
	private InputStream ins;
	private String rootDir;

	public MyClassload(String rootDir) {
		//super(null);
		this.rootDir = rootDir;
	}

	//	@Override
	//	public Class<?> loadClass(String name) throws ClassNotFoundException {
	//		Class<?> c = null;
	//		try {
	//			c = loadClass(name, false);
	//		} catch (Throwable e) {
	//
	//		}
	//		if (c == null) {
	//			c = ClassLoader.getSystemClassLoader().loadClass(name);
	//		}
	//		return c;
	//	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		bs = getClassData(classNameToPath(name));
		return defineClass(name, bs, 0, bs.length);
	}

	private String classNameToPath(String className) {
		return rootDir + File.separatorChar
				+ className.replace('.', File.separatorChar) + ".class";
	}

	private byte[] getClassData(String name) {
		try {
			ins = new FileInputStream(name);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
