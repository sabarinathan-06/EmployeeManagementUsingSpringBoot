package com.ideas2it.employeeManagement.project.projectDTO;

import lombok.*;

/**
 * Data Transfer Object (DTO) for Project.
 *
 * <p>
 * This class represents the data structure used to transfer project information
 * between different layers of the application. It is used for both input and
 * output data in the API operations related to projects.
 * </p>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long projectId;
    private String projectName;
}
