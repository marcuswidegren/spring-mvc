package com.visma.spring.model.employee;

import java.util.List;

public interface EmployeeDAO {

    public Employee persistEmployee(Employee employee);

    public Employee getEmployee(Long id);

    public List<Employee> getAllEmployees();

    public Employee removeEmployee(Employee employee);
}
