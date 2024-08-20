package com.ideas2it.employeeManagement.employee.employeeDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+([ ][a-zA-Z]+)*$", message = "Enter the valid Employee name.")
    private String employeeName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+([ ][a-zA-Z]+)*$", message = "Enter the valid place name.")
    private String place;

    @NotBlank
    private LocalDate dateOfBirth;

    @NotBlank
    private int experience;
    private double salary;
    private String departmentName;
    private String projectName;

}
