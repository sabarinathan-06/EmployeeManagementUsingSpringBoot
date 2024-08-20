package com.ideas2it.employeeManagement.project.service;

import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;

import java.util.List;

/**
 * Service interface for managing Project entities.
 *
 * <p>
 * This interface defines the business operations related to Project entities.
 * It provides methods for creating, retrieving, updating, and deleting projects.
 * Implementations of this interface will handle the core logic of interacting
 * with project data and converting between Project entities and ProjectDTOs.
 * </p>
 */
public interface ProjectService {

    /**
     * Creates a new project.
     *
     * @param projectDTO {@link ProjectDTO} The DTO containing project data.
     * @return The created project DTO.
     */
    ProjectDTO createProject(ProjectDTO projectDTO);

    /**
     * Retrieves a project by its unique ID.
     *
     * @param id The unique ID of the project.
     * @return The project DTO if found; otherwise, throw an exception.
     */
    ProjectDTO getProjectById(Long id);

    /**
     * Retrieves all projects.
     *
     * @return A list of all project DTOs.
     */
    List<ProjectDTO> getAllProjects();

    /**
     * Updates an existing project.
     *
     * @param projectDTO {@link ProjectDTO} The updated project data.
     * @return The updated project DTO.
     */
    ProjectDTO updateProject(ProjectDTO projectDTO);

    /**
     * Deletes a project by its unique ID.
     *
     * @param id The unique ID of the project to delete.
     */
    boolean deleteProject(Long id);
}
