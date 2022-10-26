package com.lzc.mhl.service;

import com.lzc.mhl.dao.EmployeeDao;
import com.lzc.mhl.domain.Employee;

/**
 * @title: EmployeeService
 * @Author luozouchen
 * @Date: 2022/10/26 19:18
 * @Version 1.0
 * 该类完成对employee表各种操作，通过调用Employedao 对象完成
 */
public class EmployeeService {
    //定义一个EmployeeDao 属性
    private EmployeeDao employeeDao = new EmployeeDao();

    //方法 根据empId 和pwd返回一个employee对象
    //如果查询不到就返回null
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
        Employee employee = employeeDao.querySingle("select * from employee where empId=? and pwd=md5(?)", Employee.class, empId, pwd);

        return employee;
    }
}
