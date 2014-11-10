package com.visma.spring.controller;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.visma.spring.model.employee.Employee;

/**
 * Handles requests for the Employee service.
 */
@RestController
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") Long empId) {
        logger.info("Start getEmployee. ID="+empId);
         
        return employeeService.getEmployeeById(empId);
    }

    @RequestMapping(value = EmpRestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        logger.info("Start getAllEmployees.");
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = EmpRestURIConstants.CREATE_EMP, method = RequestMethod.POST)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
        logger.info("Start createEmployee.");
        if(emp.getId() != null) {
            return ResponseEntity.badRequest().body(null);
        }
        if(emp.getCreatedDate() != null) {
            return ResponseEntity.badRequest().body(null);
        }

        Employee employee = employeeService.addEmployee(emp);
        return ResponseEntity.ok(employee);
    }

    @RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.DELETE)
    public Employee deleteEmployee(@PathVariable("id") Long empId) {
        logger.info("Start deleteEmployee.");
        return employeeService.deleteEmployee(empId);
    }
     
}
