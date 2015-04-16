package com.zenglm.dts.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenglm.dts.dao.AbstractBatchDao;
import com.zenglm.dts.dao.UserDao;
import com.zenglm.dts.domain.User;

@Service("userService")
public class UserServiceImpl extends AbstractBatchDao implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void updateBatch(Map<String, Object> cond) {
		List<User> users = new ArrayList<User>();
		int id;
		long start = System.currentTimeMillis();
		SqlSession session = SqlSessionUtils.getSqlSession(sqlSessionFactory,
				ExecutorType.BATCH, null);
		for (int j = 0; j < 10; j++) {
			id = new Random().nextInt(1000000);
			User parameterObject = new User();
			//parameterObject.setId(id);
			//parameterObject.setName(cond.get("username").toString());
			users.add(parameterObject);
			session.insert("com.zenglm.dts.dao.AccountDao.insertTest",
					parameterObject);
		}
		session.commit();
		SqlSessionUtils.closeSqlSession(session, sqlSessionFactory);
		//updateBatch(users, "com.zenglm.dts.dao.AccountDao.insertTest");
		/*updateBatch(accountDao.getUserList(),
				"com.zenglm.dts.dao.AccountDao.updateTest");*/
		System.out.println(System.currentTimeMillis() - start
				+ "***********************");

	}

}
