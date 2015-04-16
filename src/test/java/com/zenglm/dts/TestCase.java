package com.zenglm.dts;

import static org.junit.Assert.assertNotNull;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zenglm.dts.domain.Account;
import com.zenglm.dts.model.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" })
public class TestCase {
	private final static Logger logger = Logger.getLogger(MybaitsDaoTestcase.class);
	@Autowired
	private UserService service;
	
	 public void testInsertAccount() {
		// 创建一个帐户
	  Account account = new Account();
	  // account.setAccountId(1);
	  account.setUsername("selina");
	  account.setPassword("123456");
		//account = service.findByName("j2ee");
	  assertNotNull(account);
		// account = service.findByName("j2ee");
		//将创建的帐户插入到数据库中
	 // service.insertAccount(account);
	 logger.debug("email: " + account.getEmail());
	  
		//从数据库获取刚才插入的帐户
	  //Account accountFromDb = service.getAccountById(account);
	  //assertNotNull(accountFromDb);
	  //assertEquals(account.getAccountId(), accountFromDb.getAccountId());
	 }

	@Test
	public void testbatch() throws IOException {
		//Map<String, Object> cond = new HashMap<String, Object>();
		//cond.put("username", "zlm");
		//service.updateBatch(cond);
		//System.out.println("1".charAt(0) % 2);

		FileWriter fw = new FileWriter("test");
		fw.write("AAAA");
		//fw.flush();
		fw.write("bbbb");
		fw.close();
	}
}
