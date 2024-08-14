package com.ideas2it.employeeManagement.mapper;

import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.util.DisplayProjects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for mapping between Employee and EmployeeDTO.
 *
 * <p>
 * This class provides static methods for converting between Employee entities
 * and their corresponding Data Transfer Objects (DTOs). It facilitates the
 * conversion process needed for interacting with the service and controller layers
 * while keeping the domain model and DTOs separate.
 * </p>
 */
public class EmployeeMapper {

    private static final Logger logger = LogManager.getLogger(EmployeeMapper.class);

    /**
     * Converts an {@link Employee} entity to an {@link EmployeeDTO}.
     *
     * @param employee {@link Employee} The Employee entity to be converted.
     * @return {@link EmployeeDTO} The corresponding Employee DTO.
     */
    public static EmployeeDTO convertToDTO(Employee employee) {
        logger.debug("Converting Employee entity with ID: {} to EmployeeDTO", employee.getEmployeeId());
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .place(employee.getPlace())
                .dateOfBirth(employee.getDateOfBirth())
                .experience(employee.getExperience())
                .salary(employee.getSalary())
                .departmentName(employee.getDepartment() == null ?
                        "Not Assigned" : employee.getDepartment().getDepartmentName())
                .projectName(employee.getProjects().isEmpty() ?
                        "Not Assigned" : DisplayProjects.displayProject(employee))
                .build();
        logger.info("Converted Employee entity to EmployeeDTO with ID: {}", employee.getEmployeeId());
        return employeeDTO;
    }

    /**
     * Converts an {@link EmployeeDTO} to an {@link Employee} entity.
     *
     * @param employeeDTO {@link EmployeeDTO} The Employee DTO to be converted.
     * @return {@link Employee} The corresponding Employee entity.
     */
    public static Employee convertToEntity(EmployeeDTO employeeDTO) {
        logger.debug("Converting EmployeeDTO with ID: {} to Employee entity", employeeDTO.getEmployeeId());
        Employee employee = Employee.builder()
                .employeeName(employeeDTO.getEmployeeName())
                .place(employeeDTO.getPlace())
                .dateOfBirth(employeeDTO.getDateOfBirth())
                .experience(employeeDTO.getExperience())
                .salary(employeeDTO.getSalary())
                .build();
        logger.info("Converted EmployeeDTO to Employee entity with ID: {}", employeeDTO.getEmployeeId());
        return employee;
    }
}
