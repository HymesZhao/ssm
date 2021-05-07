package com.atguigu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.Employee;


public interface EmployeeDao {
	
	
	//
	/**
	 *     id  empname       gender  email           login_account  
			------  ------------  ------  --------------  ---------------
     		1  admin              0  admin@qq.com    a  
	 * @param id
	 * @return
	 * 
	 * 列名作为key，值作为value
	 */
	public Map<String, Object> getEmpByIdReturnMap(Integer id);
	
	public List<Employee> getAllEmps();
	/**
	 * key就是这个记录的主键；value就是这条记录封装好的对象；
	 * @return
	 * 
	 * 把查询的记录的id的值作为key封装这个map
	 * @MapKey("id")
	 */
	@MapKey("id")
	public Map<Integer, Employee> getAllEmpsReturnMap();
	
	public Employee getEmpById(Integer id);
	public Employee getEmpByIdAndEmpName(@Param("id")Integer id,@Param("empName")String empName);
	public Employee getEmployeeByIdAndEmpName(Map<String, Object> map);
	
	public int updateEmployee(Employee employee);
	public boolean deleteEmployee(Integer id);
	public int insertEmployee(Employee employee);
	public int insertEmployee2(Employee employee);

}
