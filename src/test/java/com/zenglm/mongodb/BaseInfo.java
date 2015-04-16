package com.zenglm.mongodb;



public class BaseInfo {
	//@Property("bid")
	private Long baseInfoId;
	private String name;
	private Integer age;
	//@Property("tel")
	private String telephone;

	public BaseInfo() {
	}

	public BaseInfo(Long baseInfoId, String name, Integer age, String telephone) {
		super();
		this.baseInfoId = baseInfoId;
		this.name = name;
		this.age = age;
		this.telephone = telephone;
	}

	public Long getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(Long baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
