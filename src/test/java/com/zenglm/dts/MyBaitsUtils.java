package com.zenglm.dts;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBaitsUtils {
	private  final static SqlSessionFactory sqlSessionFactory; 
    static { 
       String resource = "test-mybatis-config.xml"; 
       Reader reader = null; 
       try { 
           reader = Resources.getResourceAsReader(resource); 
       } catch (IOException e) { 
           System.out.println(e.getMessage()); 
 
       } 
       sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
    } 
 
    public static SqlSessionFactory getSqlSessionFactory() { 
       return sqlSessionFactory; 
    } 
}
