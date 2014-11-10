package com.visma.spring.controller;

import com.visma.spring.model.employee.Employee;
import com.visma.spring.model.employee.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@EnableTransactionManagement
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {
        return employeeDAO.persistEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public Employee deleteEmployee(Long id) {
        Employee emp = getEmployeeById(id);
        return employeeDAO.removeEmployee(emp);
    }

}
