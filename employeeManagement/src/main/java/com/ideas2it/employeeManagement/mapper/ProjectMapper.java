package com.ideas2it.employeeManagement.mapper;

import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.model.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for mapping between Project and ProjectDTO.
 *
 * <p>
 * This class provides static methods for converting between Project entities
 * and their corresponding Data Transfer Objects (DTOs). It facilitates the
 * conversion process needed for interacting with the service and controller layers
 * while keeping the domain model and DTOs separate.
 * </p>
 */
public class ProjectMapper {

    private static final Logger logger = LogManager.getLogger(ProjectMapper.class);

    /**
     * Converts a {@link Project} entity to a {@link ProjectDTO}.
     *
     * @param project {@link Project} The Project entity to be converted.
     * @return {@link ProjectDTO} The corresponding Project DTO.
     */
    public static ProjectDTO convertToDTO(Project project) {
        logger.debug("Converting Project entity with ID: {} to ProjectDTO", project.getProjectId());
        ProjectDTO projectDTO = ProjectDTO.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .build();
        logger.info("Converted Project entity to ProjectDTO with ID: {}", project.getProjectId());
        return projectDTO;
    }

    /**
     * Converts a {@link ProjectDTO} to a {@link Project} entity.
     *
     * @param projectDTO {@link ProjectDTO} The Project DTO to be converted.
     * @return {@link Project} The corresponding Project entity.
     */
    public static Project convertToEntity(ProjectDTO projectDTO) {
        logger.debug("Converting ProjectDTO with ID: {} to Project entity", projectDTO.getProjectId());
        Project project = Project.builder()
                .projectId(projectDTO.getProjectId())
                .projectName(projectDTO.getProjectName())
                .build();
        logger.info("Converted ProjectDTO to Project entity with ID: {}", projectDTO.getProjectId());
        return project;
    }
}
