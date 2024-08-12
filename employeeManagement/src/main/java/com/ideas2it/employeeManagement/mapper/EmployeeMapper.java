package com.ideas2it.employeeManagement.mapper;

import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.model.Employee;

/**
 * Utility class for converting between Employee entities and EmployeeDTOs.
 *
 * <p>This class provides static methods to convert between the entity and
 * data transfer object. This mapping facilitates the separation of concerns
 * between different layers of the application, ensuring that the data sent to
 * and from the client is properly structured and validated.
 * This class is designed to be stateless and thread-safe, with all methods
 * being static to allow easy usage without requiring instantiation.</p>
 */
public class EmployeeMapper {

    public static EmployeeDTO convertToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .place(employee.getPlace())
                .dateOfBirth(employee.getDateOfBirth())
                .experience(employee.getExperience())
                .salary(employee.getSalary())
                .departmentName(employee.getDepartment().getDepartmentName())
                .build();
    }

    public static Employee convertToEntity(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .employeeName(employeeDTO.getEmployeeName())
                .place(employeeDTO.getPlace())
                .dateOfBirth(employeeDTO.getDateOfBirth())
                .experience(employeeDTO.getExperience())
                .salary(employeeDTO.getSalary())
                .build();
    }
}
