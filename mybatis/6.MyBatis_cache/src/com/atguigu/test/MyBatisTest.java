package com.atguigu.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.bean.Teacher;
import com.atguigu.dao.TeacherDao;


public class MyBatisTest {

	// 工厂一个
	SqlSessionFactory sqlSessionFactory;
	
	
	
	/**
	 * 1、不会出现一级缓存和二级缓存中有同一个数据。
	 * 		二级缓存中：一级缓存关闭了就有了；
	 * 		一级缓存中：二级缓存中没有此数据，就会看一级缓存，一级缓存没有去查数据库；
	 * 				数据库的查询后的结果放在一级缓存中了；
	 * 2、任何时候都是先看二级缓存、再看一级缓存，如果大家都没有就去查询数据库；
	 * 	二 == 一 == 库
	 */
	@Test
	public void test05(){
		SqlSession openSession = sqlSessionFactory.openSession();
		TeacherDao teacherDao = openSession.getMapper(TeacherDao.class);
		Teacher teacher = teacherDao.getTeacherById(1);
		System.out.println(teacher);
		openSession.close();
		System.out.println("=================");
		
		
		
		SqlSession openSession2 = sqlSessionFactory.openSession();
		TeacherDao teacherDao2 = openSession2.getMapper(TeacherDao.class);
		Teacher teacherById = teacherDao2.getTeacherById(1);
		System.out.println(teacherById);
		
		Teacher teacherById2 = teacherDao2.getTeacherById(1);
		System.out.println(teacherById2);
		openSession2.close();
		
	}
	/**
	 * 二级缓存：
	 */
	@Test
	public void test04(){
		SqlSession openSession = sqlSessionFactory.openSession();
		SqlSession openSession2 = sqlSessionFactory.openSession();
		
		TeacherDao teacherDao1 = openSession.getMapper(TeacherDao.class);
		TeacherDao teacherDao2 = openSession2.getMapper(TeacherDao.class);
		
		//1、第一个dao查询1号teacher
		Teacher teacher = teacherDao1.getTeacherById(1);
		System.out.println(teacher);
		openSession.close();
		
		//2、第二个dao查询1号teacher
		Teacher teacher2 = teacherDao2.getTeacherById(1);
		System.out.println(teacher2);
		openSession2.close();
	}
	
	/**
	 * 一级缓存失效的几种情况；
	 * 一级缓存是SqlSession级别的缓存；
	 * 1、不同的sqlSession，使用不同的一级缓存； 
	 * 		只有在同一个sqlSession期间查询到的数据会保存在这个sqlSession的缓存中；
	 * 		下次使用这个sqlSession查询会从缓存中拿
	 * 2、同一个方法，不同的参数，由于可能之前没查询过，所有还会发新的sql；
	 * 3、在这个sqlSession期间执行上任何一次增删改操作，增删改操作会把缓存清空；
	 * 4、手动清空了缓存；
	 * 
	 * 每次查询，先看一级缓存中有没有，如果没有就去发送新的sql；每个sqlSession拥有自己的一级缓存
	 * 
	 * 
	 */
	@Test
	public void test03() {
		//1、第一个会话
		SqlSession openSession = sqlSessionFactory.openSession();
		TeacherDao teacherDao = openSession.getMapper(TeacherDao.class);
		Teacher teacher1 = teacherDao.getTeacherById(1);
		System.out.println(teacher1);
		System.out.println("========================");
		//执行任何一个增删改操作
//		Teacher teacher = new Teacher();
//		teacher.setId(3);
//		teacher.setName("33333");
//		teacherDao.updateTeacher(teacher);
		System.out.println("手动清空缓存");
		//清空当前sqlSession的一级缓存
	//	openSession.clearCache();
		
		System.out.println("========================");
		Teacher teacher2 = teacherDao.getTeacherById(1);
		System.out.println(teacher2);
		
		
		openSession.commit();
		openSession.close();
		
//		//2、第二个会话
//		SqlSession openSession2 = sqlSessionFactory.openSession();
//		TeacherDao teacherDao2 = openSession2.getMapper(TeacherDao.class);
//		Teacher teacher2 = teacherDao2.getTeacherById(1);
//		System.out.println(teacher2);
		//openSession2.close();
		
	}

	
	/**
	 * 体会一级缓存的存在
	 */
	@Test
	public void test02() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			
			TeacherDao mapper = openSession.getMapper(TeacherDao.class);
			Teacher teacher = mapper.getTeacherById(1);
			System.out.println(teacher);
			System.out.println("====================");
			Teacher teacher2 = mapper.getTeacherById(1);
			System.out.println(teacher2);
			System.out.println(teacher2 == teacher);
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
