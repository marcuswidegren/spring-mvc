package com.visma.spring.model.employee;

import org.hibernate.SessionFactory;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Employee persistEmployee(Employee employee) {
        employee.setCreatedDate(LocalDateTime.now());
        sessionFactory.getCurrentSession().save(employee);
        return employee;
    }

    @Override
    public Employee getEmployee(Long id) {
        return (Employee)sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        @SuppressWarnings("unchecked")
        List<Employee> allEmployees =sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
        return allEmployees;
    }

    @Override
    public Employee removeEmployee(Employee employee) {
        sessionFactory.getCurrentSession().delete(employee);
        employee.nullId();
        return employee;
    }

}
