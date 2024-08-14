package com.ideas2it.employeeManagement.department.service;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;

import java.util.List;

/**
 * Service interface for handling business logic related to Department operations.
 *
 * <p>
 * This interface defines the contract for department-related operations, including
 * creating, retrieving, updating, and deleting departments. It also provides a method
 * for retrieving employees associated with a department. Implementations of this
 * interface should provide the necessary business logic and interact with the data
 * access layer.
 * </p>
 */
public interface DepartmentService {

    /**
     * Creates a new department.
     *
     * @param departmentDTO {@link DepartmentDTO} The DTO containing department data.
     * @return The created department DTO with HTTP status 201 CREATED.
     */
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    /**
     * Retrieves a department by its unique ID.
     *
     * @param id The unique identifier of the department.
     * @return The department DTO if found with HTTP status 200 OK.
     */
    DepartmentDTO getDepartmentById(Long id);

    /**
     * Retrieves all departments.
     *
     * @return A list of all department DTOs.
     */
    List<DepartmentDTO> getAllDepartments();

    /**
     * Updates an existing department's details by its unique ID.
     *
     * @param id The unique identifier of the department to be updated.
     * @param departmentDTO {@link DepartmentDTO} The DTO containing updated department data.
     * @return The updated department DTO with HTTP status 200 OK.
     */
    DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO);

    /**
     * Deletes a department by its unique ID. This method performs a soft delete,
     * marking the department as deleted rather than physically removing it from the database.
     *
     * @param id The unique identifier of the department to be deleted.
     */
    void deleteDepartment(Long id);

    /**
     * Retrieves all employees associated with a specific department.
     *
     * @param id The unique identifier of the department.
     * @return A list of employee DTOs associated with the department.
     */
    List<EmployeeDTO> getEmployeesByDepartment(Long id);
}
