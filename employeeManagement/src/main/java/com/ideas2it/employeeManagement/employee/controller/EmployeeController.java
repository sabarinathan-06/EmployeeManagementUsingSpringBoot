package com.ideas2it.employeeManagement.employee.controller;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.department.service.DepartmentService;
import com.ideas2it.employeeManagement.employee.service.EmployeeService;
import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.project.service.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    /**
     * Creates a new employee.
     *
     * @param employeeDTO {@link EmployeeDTO} The DTO containing employee data.
     * @return The created employee DTO with HTTP status 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        logger.debug("Request to create employee with employee name: {}", employeeDTO.getEmployeeName());
        EmployeeDTO createdEmployeeDto = employeeService.createEmployee(employeeDTO);
        logger.info("Employee created with ID: {}", createdEmployeeDto.getEmployeeId());
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }

    /**
     * Retrieves an employee by their unique employee ID.
     *
     * @param id the unique employee ID
     * @return the employee DTO if found with HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        logger.debug("Request to retrieve employee with ID: {}", id);
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        logger.info("Retrieved employee with ID: {}", id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return the list of all employees as employee DTOs with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        logger.debug("Request to retrieve all employees");
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployees();
        logger.info("Retrieved {} employees", employeeDTOs.size());
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    /**
     * Updates an existing employee's details by their unique employee ID.
     *
     * @param id the unique employee ID
     * @param employeeDTO {@link EmployeeDTO} The DTO containing updated employee data.
     * @return the updated employee DTO with HTTP status 202 ACCEPTED.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        logger.debug("Request to update employee with ID: {} with data: {}", id, employeeDTO);
        EmployeeDTO updatedEmployeeDto = employeeService.updateEmployee(employeeDTO);
        logger.info("Updated employee with ID: {}", id);
        return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.ACCEPTED);
    }

    /**
     * Deletes an employee by their unique employee ID.
     *
     * @param id the unique employee ID
     * @return HTTP status 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id) {
        logger.debug("Request to delete employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        logger.info("Deleted employee with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Assigns an employee to a department.
     *
     * @param employeeId the unique employee ID
     * @param departmentId the unique department ID
     * @return the updated employee DTO with HTTP status 200 OK.
     */
    @PutMapping("/{employeeId}/departments/{departmentId}")
    public ResponseEntity<EmployeeDTO> assignEmployeeToDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId) {
        logger.debug("Request to assign employee with ID: {} to department with ID: {}", employeeId, departmentId);
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(departmentId);
        if (employeeDTO != null && departmentDTO != null) {
            EmployeeDTO updatedEmployeeDto = employeeService.assignEmployeeToDepartment(employeeDTO, departmentDTO);
            logger.info("Assigned employee with ID: {} to department with ID: {}", employeeId, departmentId);
            return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.OK);
        } else {
            logger.warn("Employee with ID: {} or department with ID: {} not found", employeeId, departmentId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Assigns an employee to a project.
     *
     * @param employeeId the unique employee ID
     * @param projectId the unique project ID
     * @return the updated employee DTO with HTTP status 200 OK.
     */
    @PutMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<EmployeeDTO> assignEmployeeToProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        logger.debug("Request to assign employee with ID: {} to project with ID: {}", employeeId, projectId);
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        ProjectDTO projectDTO = projectService.getProjectById(projectId);
        if (employeeDTO != null && projectDTO != null) {
            EmployeeDTO updatedEmployeeDto = employeeService.assignEmployeeToProject(employeeDTO, projectDTO);
            logger.info("Assigned employee with ID: {} to project with ID: {}", employeeId, projectId);
            return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.OK);
        } else {
            logger.warn("Employee with ID: {} or Project with ID: {} not found", employeeId, projectId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
