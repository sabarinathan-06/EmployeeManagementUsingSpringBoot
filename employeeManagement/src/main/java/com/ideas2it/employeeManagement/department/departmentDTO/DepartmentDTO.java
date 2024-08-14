package com.ideas2it.employeeManagement.department.departmentDTO;

import lombok.*;

/**
 * Data Transfer Object (DTO) for Department.
 *
 * <p>
 * This class represents the data structure used to transfer department data
 * between different layers of the application, specifically between the
 * service and controller layers. It includes basic information about a department,
 * such as its ID and name.
 * </p>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long departmentId;

    private String departmentName;

}
