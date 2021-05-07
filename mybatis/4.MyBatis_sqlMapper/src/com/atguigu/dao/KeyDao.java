package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Key;

public interface KeyDao {
	
	/**
	 * 将钥匙和锁子信息一起查出
	 * @param id
	 * @return
	 */
	public Key getKeyById(Integer id);
	
	public Key getKeyByIdSimple(Integer id);
	
	public List<Key> getKeysByLockId(Integer id);

}
