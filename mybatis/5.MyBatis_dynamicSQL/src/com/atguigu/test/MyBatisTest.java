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
	
	@Test
	public void test02() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			
			TeacherDao mapper = openSession.getMapper(TeacherDao.class);
			
			Teacher teacher = new Teacher();
			teacher.setId(1);
			teacher.setName("hahaha");
			mapper.updateTeacher(teacher);
			
			openSession.commit();
		} finally {
			openSession.close();
		}
	}

	
	@Test
	public void test() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			
			TeacherDao mapper = openSession.getMapper(TeacherDao.class);
			
			//teacher2.setBirth(new Date());
			/*	List<Teacher> list = mapper.getTeacherByCondition(teacher2);*/
			
			
			//List<Teacher> list = mapper.getTeacherByIdIn(Arrays.asList(1,2,3,4,5));
			
			
			Teacher teacher2 = new Teacher();
			//teacher2.setId(1);
			//teacher2.setName("admin");
			List<Teacher> list = mapper.getTeacherByConditionChoose(teacher2);
			
			System.out.println(list);
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
