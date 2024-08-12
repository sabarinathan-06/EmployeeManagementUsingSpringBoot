package com.ideas2it.employeeManagement.employee.service;

import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.employee.respository.EmployeeRepository;
import com.ideas2it.employeeManagement.mapper.EmployeeMapper;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Service class that provides business logic related to Employee operations.
 * This class acts as an intermediary between the controller layer and the repository layer.
 * It contains methods for creating, retrieving, updating, and deleting Employee entities.
 * The service ensures that the necessary business rules are applied before data
 * is persisted or retrieved from the database.
 * </p>
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository
                .findByEmployeeIdAndIsDeletedFalse(id);
        return employeeOptional
                .orElseThrow(() -> new RuntimeException("Employee not found for ID: " + id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findByIsDeletedFalse();
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee excistingEmployee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for ID: " + id));
        excistingEmployee.setEmployeeName(employee.getEmployeeName());
        excistingEmployee.setPlace(employee.getPlace());
        excistingEmployee.setDateOfBirth(employee.getDateOfBirth());
        excistingEmployee.setSalary(employee.getSalary());
        excistingEmployee.setExperience(employee.getExperience());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDTO assignEmployeeToProject(Long employeeId, Employee employee, Project project) {
        Optional<Employee> retrievedEmployee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
        List<Project> projects = employee.getProjects();
        projects.add(project);
        employee.setProjects(projects);
        employeeRepository.save(employee);
        return EmployeeMapper.convertToDTO(employee);
    }
}

