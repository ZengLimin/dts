package com.zenglm.mongodb;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class TestMorphia {
	
	private static Datastore ds;

	@Before
	public void init() throws UnknownHostException {
		MongoClient mc = new MongoClient("localhost", 27017);
		Morphia morphia = new Morphia();
		morphia.map(HouseholdStaff.class);
		ds = morphia.createDatastore(mc, "test");
	}

	@Test
	public void test() throws CloneNotSupportedException {
		long start = System.currentTimeMillis();
		HouseholdStaff baseInfo = new HouseholdStaff(1l, "test", 22, "888888",
				10l, 1l, ".1.");
		HouseholdStaff temp;
		for (long i = 0; i < 10l; i++) {
			temp = baseInfo.clone();
			temp.setId(300000 + i);
			ds.save(temp, WriteConcern.UNACKNOWLEDGED);
		}
		System.out.println(System.currentTimeMillis() - start + "ms");
		//ds.save(new BaseInfo(1l, "test11", 22, "888888"));

		

		/*
		System.out.println(baseInfo.getOid());
		BaseInfo b = ds.find(BaseInfo.class).field("age").lessThanOrEq(22)
				.get();
		System.out.println(b.getName());*/
		/*Account user = new Account();
		user.setId(1l);
		user.setUsername("aaaa");
		ds.save(user);
		user = ds.find(Account.class).get();
		System.out.println(user.getId());*/

	}
	
	@Test
	public void  testPage(){
		Query<HouseholdStaff> query = ds.find(HouseholdStaff.class)
				.field("age").lessThanOrEq(22);
		long start = System.currentTimeMillis();
		DBObject keys = new BasicDBObject();
		keys.put("_id", true);
		long count = ds.getCollection(HouseholdStaff.class)
				.find(query.getQueryObject(), keys).count();
		//System.out.println(query);
		//System.out.println(query.explain());
		//System.out.println(System.currentTimeMillis() - start + "ms");
		System.out.println(count);
		start = System.currentTimeMillis();
		count = ds.find(HouseholdStaff.class).field("age").lessThanOrEq(22)
				.countAll();
		System.out.println(ds.find(HouseholdStaff.class).get().getName());
		System.out.println(System.currentTimeMillis() - start + "ms");
		//System.out.println(count);
	}

}
