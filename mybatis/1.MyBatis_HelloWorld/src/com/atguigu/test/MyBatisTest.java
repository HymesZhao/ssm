package com.atguigu.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.bean.Employee;
import com.atguigu.dao.EmployeeDao;


public class MyBatisTest {

    // 工厂一个
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() throws IOException {
        //1、根据全局配置文件创建出一个SqlSessionFactory
        //SqlSessionFactory：是SqlSession工厂，负责创建SqlSession对象；
        //SqlSession:sql会话（代表和数据库的一次会话）;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testQuery() throws IOException {
        //4、调用之前的方法；
        Employee employee;
        try (SqlSession openSession = sqlSessionFactory.openSession(true)) {
            //2、获取和数据库的一次会话；getConnection()；
            //3、使用SqlSession操作数据库，获取到dao接口的实现
            EmployeeDao employeeDao = openSession.getMapper(EmployeeDao.class);
            employee = employeeDao.getEmpById(1);
            System.out.println(employee);
        }
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setEmail("liuwenhui@qq.com");
        employee.setEmpName("liuwenhui");
        employee.setGender(1);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            int i = mapper.insertEmployee(employee);
            System.out.println(i);
        }
        //			sqlSession.commit(); //openSession(true)
    }

    @Test
    public void testUpdate() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee("lisi", "lisi@qq.com", 0);
            employee.setId(3);
            int i = mapper.updateEmployee(employee);
            System.out.println(i);
        }
    }

    @Test
    public void testDelete() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            boolean b = mapper.deleteEmployee(3);
            System.out.println(b);
        }
    }
}
