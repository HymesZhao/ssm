package com.atguigu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.Teacher;

public interface TeacherDao {
	
	public Teacher getTeacherById(Integer id);
	
	public List<Teacher> getTeacherByCondition(Teacher teacher);
	
	public List<Teacher> getTeacherByIdIn(@Param("ids")List<Integer> ids);
	
	public List<Teacher> getTeacherByConditionChoose(Teacher teacher);
	
	public int updateTeacher(Teacher teacher);

}
