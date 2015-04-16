package com.zenglm.mongodb;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.Property;

import com.mongodb.DBObject;

@Entity(noClassnameStored = true)
public class HouseholdStaff extends BaseInfo implements Cloneable {

	@Id
	private Long id;
	private Long orgId;
	@NotSaved
	private String orgCode;
	@Property("orgCode")
	private String temp;

	public HouseholdStaff() {
	}
	public HouseholdStaff(Long baseInfoId, String name, Integer age,
			String telephone, Long id, Long orgId, String orgCode) {
		super(baseInfoId, name, age, telephone);
		this.id = id;
		this.orgId = orgId;
		setOrgCode(orgCode);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		temp = orgCode + "*****";
		this.orgCode = orgCode;
	}

	@Override
	public HouseholdStaff clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (HouseholdStaff) super.clone();
	}

	@PostLoad
	public void postLoad(DBObject dbObj) {
		super.setAge((Integer) dbObj.get("age") + 2);
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

}
