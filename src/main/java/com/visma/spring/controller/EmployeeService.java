package com.visma.spring.controller;

import com.visma.spring.model.employee.Employee;

import java.util.*;

public interface EmployeeService {

    public Employee addEmployee(Employee employee);

    public Employee getEmployeeById(Long id);

    public List<Employee> getAllEmployees();

    public Employee deleteEmployee(Long id);

}
