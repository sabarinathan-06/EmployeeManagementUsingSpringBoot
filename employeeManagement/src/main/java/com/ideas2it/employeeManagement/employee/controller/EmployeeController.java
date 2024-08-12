package com.ideas2it.employeeManagement.employee.controller;

import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.mapper.EmployeeMapper;
import com.ideas2it.employeeManagement.department.service.DepartmentService;
import com.ideas2it.employeeManagement.employee.service.EmployeeService;
import com.ideas2it.employeeManagement.project.service.ProjectService;
import com.ideas2it.employeeManagement.model.Department;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Employee-related operations.
 *
 * <p>
 * This controller handles HTTP requests and provides endpoints for
 * creating, retrieving, updating, and deleting Employee entities. The
 * controller maps client requests to the appropriate service methods
 * and returns responses in the form of JSON or other supported media types.
 * It is annotated with Spring MVC annotations to define the URL mappings
 * and request handling logic.
 * All responses are returned in a standardized format to ensure consistency across
 * the API.
 * </p>
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return EmployeeMapper.convertToDTO(employeeService.createEmployee(EmployeeMapper.convertToEntity(employeeDTO)));
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return EmployeeMapper.convertToDTO(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            employeeDTOs.add(EmployeeMapper.convertToDTO(employee));
        }
        return employeeDTOs;
    }

    @PutMapping
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return EmployeeMapper.convertToDTO(employeeService.updateEmployee(id, EmployeeMapper.convertToEntity(employeeDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{employeeId}/departments/{departmentId}")
    public EmployeeDTO assignEmployeeToDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Department department = departmentService.getDepartmentById(departmentId);
        employee.setDepartment(department);
        employeeService.updateEmployee(employeeId, employee);
        return EmployeeMapper.convertToDTO(employee);
    }

    @PutMapping("/{employeeId}/projects/{projectId}")
    public EmployeeDTO assignEmployeeToProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Project project = projectService.getProjectById(projectId);
        return employeeService.assignEmployeeToProject(employeeId, employee, project);
    }
}