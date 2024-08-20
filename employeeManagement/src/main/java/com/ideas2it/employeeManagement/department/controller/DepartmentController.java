package com.ideas2it.employeeManagement.department.controller;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.department.service.DepartmentService;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Department-related operations.
 *
 * <p>
 * This controller handles HTTP requests and provides endpoints for
 * creating, retrieving, updating, and deleting Department entities. It
 * maps client requests to the appropriate service methods and returns
 * responses in the form of JSON or other supported media types. The
 * controller is annotated with Spring MVC annotations to define the URL
 * mappings and request handling logic.
 * </p>
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private static final Logger logger = LogManager.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * Creates a new department.
     *
     * <p>
     * This method handles POST requests to create a new department. It takes
     * a {@link DepartmentDTO} as input, passes it to the service layer, and
     * returns the created department DTO with HTTP status 201 CREATED.
     * </p>
     *
     * @param departmentDTO {@link DepartmentDTO} The DTO containing department data.
     * @return The created department DTO with HTTP status 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        logger.debug("Request to create department with name: {}", departmentDTO.getDepartmentName());
        DepartmentDTO createdDepartmentDto = departmentService.createDepartment(departmentDTO);
        logger.info("Department created with ID: {}", createdDepartmentDto.getDepartmentId());
        return new ResponseEntity<>(createdDepartmentDto, HttpStatus.CREATED);
    }

    /**
     * Retrieves a department by its unique ID.
     *
     * <p>
     * This method handles GET requests to retrieve a department by its ID. It
     * returns the department DTO with HTTP status 200 OK if found, or HTTP status
     * 404 NOT FOUND if the department does not exist.
     * </p>
     *
     * @param id the unique department ID
     * @return the department DTO with HTTP status 200 OK or HTTP status 404 NOT FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        logger.debug("Request to retrieve department with ID: {}", id);
        try {
            DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
            logger.info("Department retrieved with ID: {}", id);
            return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Department not found with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all departments.
     *
     * <p>
     * This method handles GET requests to retrieve a list of all departments.
     * It returns a list of department DTOs with HTTP status 200 OK.
     * </p>
     *
     * @return a list of all department DTOs with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        logger.debug("Request to retrieve all departments.");
        List<DepartmentDTO> departmentDTOs = departmentService.getAllDepartments();
        logger.info("Retrieved all departments.");
        return new ResponseEntity<>(departmentDTOs, HttpStatus.OK);
    }

    /**
     * Updates an existing department's details by its unique ID.
     *
     * <p>
     * This method handles PUT requests to update a department. It takes the
     * department ID and a {@link DepartmentDTO} with updated data, passes them
     * to the service layer, and returns the updated department DTO with HTTP status 200 OK.
     * </p>
     *
     * @param id            the unique department ID
     * @param departmentDTO {@link DepartmentDTO} The DTO containing updated department data.
     * @return the updated department DTO with HTTP status 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        logger.debug("Request to update department with ID: {}", id);
        DepartmentDTO updatedDepartmentDto = departmentService.updateDepartment(departmentDTO);
        logger.info("Department updated with ID: {}", id);
        return new ResponseEntity<>(updatedDepartmentDto, HttpStatus.OK);
    }

    /**
     * Deletes a department by its unique ID.
     *
     * <p>
     * This method handles DELETE requests to delete a department. It takes the
     * department ID, passes it to the service layer to mark it as deleted.
     * </p>
     *
     * @param id the unique department ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long id) {
        logger.debug("Request to delete department with ID: {}", id);
        boolean deleteDepartment = departmentService.deleteDepartment(id);
        logger.info("Department marked as deleted with ID: {}", id);
        return new ResponseEntity<>(deleteDepartment, HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves employees associated with a specific department.
     *
     * <p>
     * This method handles GET requests to retrieve employees by their department ID.
     * It returns a list of employee DTOs with HTTP status 200 OK.
     * </p>
     *
     * @param id the unique department ID
     * @return a list of employee DTOs associated with the department with HTTP status 200 OK.
     */
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable Long id) {
        logger.debug("Request to retrieve employees for department with ID: {}", id);
        List<EmployeeDTO> employeeDTOs = departmentService.getEmployeesByDepartment(id);
        logger.info("Employees retrieved for department with ID: {}", id);
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }
}
