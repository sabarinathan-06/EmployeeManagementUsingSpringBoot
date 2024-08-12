package com.ideas2it.employeeManagement.employee.employeeDTO;

import com.ideas2it.employeeManagement.model.Department;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <p>
 * This class is responsible for transferring Employee data as Data Transfer Object (DTO)
 * between layers.
 * This class is used to encapsulate the data related to an Employee and
 * transfer it between the client and the server. DTOs are typically used to
 * decouple the internal representation of data (employee entity class) from
 * the API, providing a simpler and more controlled way to expose data.
 * </p>
 */
@Data
@Builder
@Getter
@Setter
public class EmployeeDTO {


    private Long employeeId;
    private String employeeName;
    private String place;
    private LocalDate dateOfBirth;
    private int experience;
    private double salary;
    private String departmentName;

}
