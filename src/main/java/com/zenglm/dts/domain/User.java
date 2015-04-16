package com.zenglm.dts.domain;

import org.apache.ibatis.type.Alias;

/**
 * 
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年11月26日
 */
@Alias("user")
public class User {

	private Long id;
	private String username;
	private String nickname;
	private String password;
	private String email;
	private Integer level;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
