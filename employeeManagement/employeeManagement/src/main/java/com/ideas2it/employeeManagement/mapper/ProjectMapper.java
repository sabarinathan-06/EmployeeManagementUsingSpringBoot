package com.ideas2it.employeeManagement.mapper;

import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.model.Project;

public class ProjectMapper {

    public static ProjectDTO convertToDTO(Project project) {
        return ProjectDTO.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .build();
    }

    public static Project convertToEntity(ProjectDTO projectDTO) {
        return Project.builder()
                .projectId(projectDTO.getProjectId())
                .projectName(projectDTO.getProjectName())
                .build();
    }
}
