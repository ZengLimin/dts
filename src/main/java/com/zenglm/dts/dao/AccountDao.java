package com.zenglm.dts.dao;

import java.util.List;
import java.util.Map;

import com.zenglm.dts.domain.Account;
import com.zenglm.dts.domain.User;
public interface AccountDao {

	Account getAccount(String username);
	
	Account getAccount(String username, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);
	
	void updateEmail(Map<String,Object> condition);
	
	void insertTest(User user);

	List<User> getUserList();

	void batchInsert(List<User> users);
}
