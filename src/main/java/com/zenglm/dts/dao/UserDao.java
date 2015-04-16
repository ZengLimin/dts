package com.zenglm.dts.dao;

import com.zenglm.dts.domain.User;

public interface UserDao {

	User findUser(String username);

	User findUser(String username, String password);

}
