package com.zenglm.mongodb;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MongoDBTest {

	@Test
	public void test() throws UnknownHostException, ParseException {

		//获得数据库服务
		Mongo m = new MongoClient("localhost", 27017);

		//得到数据库mytest
		DB db = m.getDB("test");

		//得到mytest数据库下所有表名
		Set<String> colls = db.getCollectionNames();
		for (String s : colls) {
			System.out.println(s);
		}

		//得到testCollection表
		DBCollection coll = db.getCollection("testCollection");

		//new 一个BasicDBObject对象doc
		BasicDBObject doc = new BasicDBObject();
		Date date = new Date();
		//赋值
		doc.put("name", "MongoDB");
		doc.put("type", "database");
		doc.put("count", 1);
		doc.put("date", date);

		//又new 一个BasicDBObject对象info
		BasicDBObject info = new BasicDBObject();
		info.put("x", 204);
		info.put("y", 102);

		//把info放入doc
		doc.put("info", info);
		doc.put("array", new int[] { 1, 2, 3 });

		//向testCollection表中插入一条数据
		coll.insert(doc);

		//查询一条数据
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);

		SimpleDateFormat sf = new SimpleDateFormat("yyyyhh");
		date = sf.parse("201412");

		DBObject cond = new BasicDBObject();
		//cond.put("name", true);
		cond.put("info", info);
		cond.put("date", new BasicDBObject("$gte", date));
		DBObject keys = new BasicDBObject();
		keys.put("name", true);
		DBCursor cursor = coll.find(cond, keys);
		cursor = coll.find(cond);
		//System.out.println(coll.count(cond));
		while (cursor.hasNext()) {
			myDoc = cursor.next();
			System.out.println((Date)myDoc.get("date"));
			System.out.println((Date) myDoc.get("date"));
		}
		//cursor.toArray();
	}

}
