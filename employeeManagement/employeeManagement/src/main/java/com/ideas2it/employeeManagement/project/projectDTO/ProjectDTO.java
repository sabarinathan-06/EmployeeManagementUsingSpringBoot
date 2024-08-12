package com.ideas2it.employeeManagement.project.projectDTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long projectId;
    private String projectName;
}
