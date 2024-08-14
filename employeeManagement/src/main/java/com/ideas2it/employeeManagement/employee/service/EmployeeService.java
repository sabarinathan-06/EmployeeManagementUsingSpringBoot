package com.ideas2it.employeeManagement.employee.service;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
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

    /**
     * Creates a new employee.
     *
     * @param employeeDTO {@link EmployeeDTO} The DTO containing employee data.
     * @return The created employee DTO.
     */
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    /**
     * Retrieves an employee by ID.
     *
     * @param id {@link Long} The ID of the employee.
     * @return The employee DTO.
     * @throws RuntimeException if the employee is not found.
     */
    EmployeeDTO getEmployeeById(Long id);

    /**
     * Retrieves all employees.
     *
     * @return A list of employee DTOs.
     */
    List<EmployeeDTO> getAllEmployees();

    /**
     * Updates an existing employee.
     *
     * @param id          {@link Long} The ID of the employee to update.
     * @param employeeDTO {@link EmployeeDTO} The DTO containing updated employee data.
     * @return The updated employee DTO.
     * @throws RuntimeException if the employee is not found.
     */
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    /**
     * Deletes an employee by ID (soft deletion).
     *
     * @param id {@link Long} The ID of the employee to delete.
     * @throws RuntimeException if the employee is not found.
     */
    void deleteEmployee(Long id);

    /**
     * Assigns an employee to a project.
     *
     * @param employeeDTO {@link EmployeeDTO} The employee DTO.
     * @param projectDTO {@link ProjectDTO} The project DTO.
     * @return The updated employee DTO.
     */
    EmployeeDTO assignEmployeeToProject(EmployeeDTO employeeDTO, ProjectDTO projectDTO);

    /**
     * Assigns an employee to a department.
     *
     * @param employeeDTO {@link EmployeeDTO} The employee DTO.
     * @param departmentDTO {@link DepartmentDTO} The department DTO.
     * @return The updated employee DTO.
     */
    EmployeeDTO assignEmployeeToDepartment(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO);
}
