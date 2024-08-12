package com.ideas2it.employeeManagement.employee.service;

import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Interface for EmployeeService, defining the business operations related to Employees.
 * This interface is implemented by EmployeeServiceImpl and defines the contract for
 * managing Employee entities.
 * </p>
 */
@Service
public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    EmployeeDTO assignEmployeeToProject(Long employeeId, Employee employee, Project project);
}
