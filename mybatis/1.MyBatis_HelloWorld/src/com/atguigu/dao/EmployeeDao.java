package com.atguigu.dao;

import com.atguigu.bean.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeDao {
    // 按照员工ID查询员工
    Employee getEmpById(Integer id);

    int updateEmployee(@Param("employee") Employee employee);

    boolean deleteEmployee(Integer id);

    int insertEmployee(@Param("employee") Employee employee);

}
