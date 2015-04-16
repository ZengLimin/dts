package com.zenglm.dts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.log4j.Logger;
import org.junit.Before;

import com.zenglm.dts.dao.AccountDao;
import com.zenglm.dts.domain.Account;


public class MybaitsDaoTestcase {
	private final static Logger logger = Logger.getLogger(MybaitsDaoTestcase.class);
	private SqlSessionFactory factory;
	@Before
	public void init() throws FileNotFoundException, IOException {
		 factory = MyBaitsUtils.getSqlSessionFactory();
		 logger.debug(factory);
	}

	//@Test
	public void test1() throws SQLException{
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		SqlSession session = factory.openSession();
		Connection connection = session.getConnection();
		connection.setAutoCommit(false);
		AccountDao accountDao = session.getMapper(AccountDao.class);
		Transaction transaction = transactionFactory.newTransaction(connection);
		Account account =  accountDao.getAccount("j2ee");
		account.setEmail(account.getEmail()+ ".cn");
		accountDao.updateAccount(account);
		System.out.println(account.getEmail());
		transaction.rollback();
		session.close();
	}
}
