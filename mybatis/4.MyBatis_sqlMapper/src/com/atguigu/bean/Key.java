package com.atguigu.bean;

/**
 * 钥匙表
 * @author lfy
 *
 */
public class Key {
	
	private Integer id;//钥匙的id
	private String keyName;//钥匙的名
	
	private Lock lock;//当前钥匙能开哪个锁；
	
	
	
	/**
	 * @return the lock
	 */
	public Lock getLock() {
		return lock;
	}
	/**
	 * @param lock the lock to set
	 */
	public void setLock(Lock lock) {
		this.lock = lock;
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
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}
	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Key [id=" + id + ", keyName=" + keyName + ", lock=" + lock
				+ "]";
	}
	
	

}
