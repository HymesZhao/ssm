package com.atguigu.bean;

import java.util.List;

/**
 * 锁子表
 * @author lfy
 *
 */
public class Lock {
	private Integer id;
	private String lockName;
	//查询锁子的时候把所有的钥匙也查出来
	private List<Key> keys;
	//1-1关联   1-n关联  n-n关联
	// 一个key开一把lock； 1-1
	// 从lock来看key；1-n；
	// 从key表看lock：n-1；
	// n-n；
	// student表   teacher表；
	// 1-n；n-1；n-n；外键应该放在哪个表？
	//结论：
	//一对n；外键一定放在n的一端；
	//n-n：中间表存储对应关系；
	
	
	
	
	/**
	 * @return the keys
	 */
	public List<Key> getKeys() {
		return keys;
	}
	/**
	 * @param keys the keys to set
	 */
	public void setKeys(List<Key> keys) {
		this.keys = keys;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the lockName
	 */
	public String getLockName() {
		return lockName;
	}
	/**
	 * @param lockName the lockName to set
	 */
	public void setLockName(String lockName) {
		this.lockName = lockName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "Lock [id=" + id + ", lockName=" + lockName + "]";
	}
	
	
	

}
