package utry.task.utils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/****
 * Mongo 数据库操作管理
 * 
 * @author dingxinfa
 * @date 2014-07-31
 * 
 */
@Component
public class MongoManage {

	private static Mongo mongo = null;
	private static DB dbConn;

	public MongoManage() {
	}

	public MongoManage(String ip, Integer port, String name) {
		try {
			mongo = new Mongo(ip, port);
			dbConn = mongo.getDB(name);
		} catch (Exception e) {
			if (null != mongo) {
				mongo.close();
			}
			e.printStackTrace();
		}
	}

	public void doMongo(String ip, Integer port) {
		try {
			mongo = new Mongo(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	public void doDB(String name) {
		dbConn = mongo.getDB(name);
	}

	public void close() {
		if (null != mongo) {
			mongo.close();
		}

	}

	/**
	 * 获取集合（表）
	 * 
	 * @param collection
	 */
	private static DBCollection getCollection(String collection) {
		return dbConn.getCollection(collection);
	}

	/***
	 * map 转成 DBObject
	 * 
	 * @param map
	 * @return
	 */
	private static DBObject mapToDBObject(Map<String, Object> map) {
		DBObject dbObject = new BasicDBObject();
		dbObject.putAll(map);
		return dbObject;
	}

	/***
	 * 添加保存数据
	 * 
	 * @param collection
	 *            表
	 * @param map
	 *            数据
	 */
	public void save(String collection, Map<String, Object> map) {
		getCollection(collection).save(mapToDBObject(map));
	}
	
	/**
	 * 插入
	 * 
	 * @param collection
	 *            表
	 * @param map
	 *            数据
	 */
	public void insert(String collection, Map<String, Object> map) {
		getCollection(collection).insert(mapToDBObject(map));

	}

	/**
	 * 批量插入
	 * 
	 * @param collection
	 *            表
	 * @param list
	 *            插入数据集合
	 */
	public void insertBatch(String collection, List<Map<String, Object>> list) {
		if (list == null || list.isEmpty()) {
			return;
		}

		List<DBObject> listDB = new ArrayList<DBObject>();
		for (int i = 0; i < list.size(); i++) {
			listDB.add(mapToDBObject(list.get(i)));
		}
		getCollection(collection).insert(listDB);

	}

	/**
	 * 删除
	 * 
	 * @param collection
	 *            表
	 * @param map
	 *            条件数据
	 */
	public void delete(String collection, Map<String, Object> map) {
		getCollection(collection).remove(mapToDBObject(map));
	}

	/**
	 * 删除全部
	 * 
	 * @param collection
	 * @param map
	 */
	public void deleteAll(String collection) {
		List<DBObject> rs = getCollection(collection).find().toArray();
		if (rs != null && !rs.isEmpty()) {
			for (int i = 0; i < rs.size(); i++) {
				getCollection(collection).remove(rs.get(i));
			}
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param collection
	 *            表
	 * @param list
	 *            条件数据
	 */
	public void deleteBatch(String collection, List<Map<String, Object>> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			getCollection(collection).remove(mapToDBObject(list.get(i)));
		}
	}

	/**
	 * 计算满足条件条数
	 * 
	 * @param collection
	 *            表
	 * @param map
	 */
	public long count(String collection, Map<String, Object> map) {
		if(null!=map){
			return getCollection(collection).getCount(mapToDBObject(map));
		}else{
			return getCollection(collection).getCount();
		}
	}

	/**
	 * 更新
	 * 
	 * @param collection
	 *            表
	 * @param setFields
	 *            更新字段数据
	 * @param whereFields
	 *            条件字段数据
	 */
	public void update(String collection, Map<String, Object> setFields,
			Map<String, Object> whereFields) {
		DBObject obj1 = mapToDBObject(setFields);
		DBObject obj2 = mapToDBObject(whereFields);
		getCollection(collection).updateMulti(obj1, obj2);
	}

	/**
	 * 查找对象（根据主键_id）
	 * 
	 * @param collection
	 * @param _id
	 */
	
	public DBObject findById(String collection, String _id) {
		DBCollection coll = getCollection(collection);

		DBObject obj = new BasicDBObject();
		obj.put("_id", ObjectId.massageToObjectId(_id));
		return coll.findOne(obj);
	}

	/**
	 * 查找（返回一个对象）
	 * 
	 * @param map
	 *            查询条件字段数据
	 * @param collection
	 *            查询表
	 */
	public DBObject findOne(String collection, Map<String, Object> map) {
		DBCollection coll = getCollection(collection);
		//
		if(null!=map){
			return coll.findOne(mapToDBObject(map));
		}else{
			return coll.findOne();
		}
	}

	/**
	 * 查找（返回一个List<DBObject>）
	 * 
	 * @param <DBObject>
	 * @param map
	 *            查询条件
	 * @param collection
	 *            查询表
	 */
	public List<DBObject> find(String collection, Map<String, Object> map){
		DBCollection coll = getCollection(collection);

		DBCursor c=null;
		if(null!=map){
			c = coll.find(mapToDBObject(map));
		}else{
			c =coll.find();
		}
		
		if (c != null){
			return c.toArray();
		}else{
			return null;
		}
	}

	/****
	 * 查找（返回一个List<DBObject>）
	 * 
	 * @param collection
	 *            表
	 * @param map
	 *            查询条件
	 * @param startRow
	 *            开始行
	 * @param results
	 *            查询返回数据
	 * @return
	 */
	public List<DBObject> findPaged(String collection, Map<String, Object> map,
			Integer startRow, Integer results) {
		DBCollection coll = getCollection(collection);

		DBCursor c=null;
		if(null!=map){
			c = coll.find(mapToDBObject(map)).skip(startRow).limit(results);
		}else{
			c = coll.find().skip(startRow).limit(results);
		}
		if (c != null){
			return c.toArray();
		}else{
			return null;
		}
	}

	
	public static void main(String[] args){
		
		//MongoManage mongoManage=new MongoManage("10.0.2.40", 27017, "autoTaskDb");
		
		MongoManage mongoManage=new MongoManage();
		mongoManage.doMongo("10.0.2.40", 27017);
		mongoManage.doDB("autoTaskDB");
		
		System.out.println(mongoManage.count("autoTaskLog", null));
		List<DBObject>  list=mongoManage.findPaged("autoTaskLog", null,0,10);
		
		
		for(DBObject d:  list){
			System.out.println(d.toMap().toString());
		}
		
		mongoManage.close();

	}
}
