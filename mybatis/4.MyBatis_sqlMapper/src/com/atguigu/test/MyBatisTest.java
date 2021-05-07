package com.atguigu.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.bean.Cat;
import com.atguigu.bean.Employee;
import com.atguigu.bean.Key;
import com.atguigu.bean.Lock;
import com.atguigu.dao.CatDao;
import com.atguigu.dao.EmployeeDao;
import com.atguigu.dao.KeyDao;
import com.atguigu.dao.LockDao;

public class MyBatisTest {

	// 工厂一个
	SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 一般我们在工作的时候；写成两个方法
	 * public Key getKeySimple(Integer id);
	 * 
	 * 推荐都来写连接查询
	 * public Key getKeyAssicate()
	 */
	@Test
	public void test08(){
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			
			LockDao mapper = openSession.getMapper(LockDao.class);
			Lock lock = mapper.getLockByIdByStep(3);
			System.out.println(lock.getLockName());
			
			List<Key> keys = lock.getKeys();
			for (Key key : keys) {
				System.out.println(key.getKeyName());
			}
			
			
		} finally {
			openSession.close();
		}
	}
	
	/*
	 * 分步查询：
	 * 0）、查询钥匙的时候顺便查出锁子；
	 * 1）、Key key = keyDao.getKeyById(1);
	 * 2）、Lock lock = lockDao.getLockById(1);
	 */
	@Test
	public void test07() throws InterruptedException{
		
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			KeyDao mapper = openSession.getMapper(KeyDao.class);
			
			Key key = mapper.getKeyByIdSimple(1);
			//严重性能；
			System.out.println(key.getKeyName());
			//按需加载；需要的时候再去查询；全局开启按需加载策略；
			//延迟加载：不着急加载（查询对象）
//			Thread.sleep(3000);
//			String lockName = key.getLock().getLockName();
//			System.out.println(lockName);
			
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test06(){
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LockDao mapper = openSession.getMapper(LockDao.class);
			Lock lock = mapper.getLockById(3);
			System.out.println(lock);
			System.out.println("所有锁子如下：");
			List<Key> keys = lock.getKeys();
			for (Key key : keys) {
				System.out.println(key);
			}
			
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 联合查询情况下
	 * 1、使用级联属性封装联合查询后的所有结果
	 */
	@Test
	public void test05() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			KeyDao mapper = openSession.getMapper(KeyDao.class);
			Key keyById = mapper.getKeyById(2);
			System.out.println(keyById);
			
		} finally {
			openSession.close();
		}
	}
	
	/**
	 * 默认mybatis自动封装结果集；
	 * 1）、按照列名和属性名一一对应的规则（不区分大小写）；
	 * 2）、如果不一一对应；
	 * 		1）、开启驼峰命名法（满足驼峰命名规则  aaa_bbb  aaaBbb）
	 * 		2）、起别名：
	 */
	@Test
	public void test04() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			CatDao mapper = openSession.getMapper(CatDao.class);
			Cat catById = mapper.getCatById(1);
			System.out.println(catById);
			
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test03() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeDao employeeDao = openSession.getMapper(EmployeeDao.class);
			
			//查询多条记录封装list
//			List<Employee> allEmps = employeeDao.getAllEmps();
//			for (Employee employee : allEmps) {
//				System.out.println(employee);
//			}
//			
//			System.out.println();
			
			//查询条记录封装map
//			Map<String, Object> map = employeeDao.getEmpByIdReturnMap(1);
//			System.out.println(map);
			
			//查询多条记录封装map
			Map<Integer, Employee> map = employeeDao.getAllEmpsReturnMap();
			System.out.println(map);
			Employee employee = map.get(1);
			System.out.println(employee.getEmpName());
			
		} finally {
			openSession.close();
		}
	}
	
	
	@Test
	public void test02() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeDao employeeDao = openSession.getMapper(EmployeeDao.class);
//			Employee employee = employeeDao.getEmpByIdAndEmpName(1, "admin");
//			System.out.println(employee);
//			
//			Employee empById = employeeDao.getEmpById(1);
//			System.out.println(empById);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("empName", "admin");
			map.put("tableName", "t_employee");
			Employee name = employeeDao.getEmployeeByIdAndEmpName(map);
			System.out.println(name);
			
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test01() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeDao employeeDao = openSession.getMapper(EmployeeDao.class);
//			Employee employee = new Employee(null, "haah", "aaa", 0);
//			int i = employeeDao.insertEmployee(employee);
//			System.out.println("--->"+i);
//			System.out.println("刚才插入的id："+employee.getId());
			Employee employee = new Employee(null, "haah", "aaa", 0);
			employeeDao.insertEmployee2(employee);
			
			System.out.println(employee.getId());
			openSession.commit();
		} finally {
			openSession.close();
		}
	}

	@Test
	public void test() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeDao employeeDao = openSession.getMapper(EmployeeDao.class);
			Employee empById = employeeDao.getEmpById(1);
			System.out.println(empById);
		} finally {
			openSession.close();
		}
	}

	@Before
	public void initSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

}
