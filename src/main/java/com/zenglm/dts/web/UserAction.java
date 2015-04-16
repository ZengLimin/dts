package com.zenglm.dts.web;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.zenglm.dts.domain.Account;
import com.zenglm.dts.domain.User;
import com.zenglm.dts.model.UserService;

@Controller("userAction")
@Namespace("/user")
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UserAction.class);
	private Account account;
	private String username;
	private User user;
	@Autowired
	private UserService userService;
	//@Autowired
	private JmsTemplate jms;

	@Action(value = "login", results = { @Result(name = "success", location = "/index.jsp") })
	public String login(){
		logger.debug("username:" + username);
		return SUCCESS;
	}

	@Action(value = "register", results = { @Result(name = "success", location = "/index.jsp") })
	public String register() {
		logger.debug("user:" + user.getUsername());
		return SUCCESS;
	}

	@Action(value = "message", results = { @Result(name = "success", type = "json", params = {
			"ignoreHierarchy", "false" }) })
	public String message() {
		try {
			jms.convertAndSend("test .........");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
