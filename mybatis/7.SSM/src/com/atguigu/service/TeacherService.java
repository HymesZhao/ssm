package com.atguigu.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Teacher;
import com.atguigu.dao.TeacherDao;

@Service
public class TeacherService {
	
//	@Autowired
//	private SqlSessionFactory factory;
	
	@Autowired
	private TeacherDao teacherDao;
	

	public Teacher getTeacher(Integer id) {
		// TODO Auto-generated method stub
		return teacherDao.getTeacherById(id);
	}


	public List<Teacher> getAll() {


		List<Teacher> teachers = teacherDao.getTeachers();
		return teachers;
	}
	
}
