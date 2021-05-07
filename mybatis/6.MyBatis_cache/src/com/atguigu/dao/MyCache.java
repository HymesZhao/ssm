package com.atguigu.dao;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;

public class MyCache implements Cache {
	//redis = new Redis();
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putObject(Object key, Object value) {
		// TODO Auto-generated method stub
		// mybatis-ehcache-1.0.3.jar ehcache-core-2.6.8.jar
		/**
		 * 
		 */
		//redis.put();
	}

	@Override
	public Object getObject(Object key) {
		// TODO Auto-generated method stub
		//redis.get
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return null;
	}

}
