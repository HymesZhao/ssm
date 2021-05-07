package com.atguigu.dao;

import com.atguigu.bean.Lock;

public interface LockDao {
	
	//查锁子的时候将所有钥匙也查出来
	public Lock getLockById(Integer id);
	
	public Lock getLockByIdSimple(Integer id);
	
	public Lock getLockByIdByStep(Integer id);

}
