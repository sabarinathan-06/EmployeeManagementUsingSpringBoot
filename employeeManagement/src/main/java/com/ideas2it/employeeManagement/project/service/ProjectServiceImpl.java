package com.ideas2it.employeeManagement.project.service;

import com.ideas2it.employeeManagement.mapper.ProjectMapper;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;
import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.project.respository.ProjectRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of the {@link ProjectService} interface.
 *
 * <p>
 * This service class provides the business logic for managing Project entities. It handles
 * the conversion between Project entities and ProjectDTOs, interacts with the
 * ProjectRepository for CRUD operations, and performs any necessary data processing
 * before returning results to the controller layer.
 * </p>
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private static final Logger logger = LogManager.getLogger(ProjectServiceImpl.class);

    /**
     * Creates a new project.
     *
     * @param projectDTO {@link ProjectDTO} The DTO containing project data.
     * @return The created project DTO.
     */
    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        logger.debug("Request to create project with name: {}", projectDTO.getProjectName());
        Project project = ProjectMapper.convertToEntity(projectDTO);
        Project savedProject = projectRepository.save(project);
        ProjectDTO result = ProjectMapper.convertToDTO(savedProject);
        logger.info("Project created with ID: {}", result.getProjectId());
        return result;
    }

    /**
     * Retrieves a project by its unique ID.
     *
     * @param id The unique ID of the project.
     * @return The project DTO if found; otherwise, throw an exception.
     */
    @Override
    public ProjectDTO getProjectById(Long id) {
        logger.debug("Request to retrieve project with ID: {}", id);
        Project project = projectRepository.findByProjectIdAndIsDeletedFalse(id)
                .orElseThrow(() -> {
                    logger.error("Project not found for ID: {}", id);
                    return new NoSuchElementException("Project not found with Id: " + id);
                });
        return ProjectMapper.convertToDTO(project);
    }

    /**
     * Retrieves all projects.
     *
     * @return A list of all project DTOs.
     */
    @Override
    public List<ProjectDTO> getAllProjects() {
        logger.debug("Request to retrieve all projects");
        List<Project> projects = projectRepository.findByIsDeletedFalse();
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project project : projects) {
            projectDTOs.add(ProjectMapper.convertToDTO(project));
        }
        logger.info("Retrieved {} projects", projectDTOs.size());
        return projectDTOs;
    }

    /**
     * Updates an existing project.
     *
     * @param projectDTO {@link ProjectDTO} The updated project data.
     * @return The updated project DTO.
     */
    @Override
    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        logger.debug("Request to update project with ID: {}", projectDTO.getProjectId());
        Project project = ProjectMapper.convertToEntity(getProjectById(projectDTO.getProjectId()));
        project.setProjectName(projectDTO.getProjectName());
        Project updatedProject = projectRepository.save(project);
        ProjectDTO result = ProjectMapper.convertToDTO(updatedProject);
        logger.info("Project updated with ID: {}", result.getProjectId());
        return result;
    }

    /**
     * Deletes a project by its unique ID.
     *
     * @param id The unique ID of the project to delete.
     */
    @Override
    public boolean deleteProject(Long id) {
        logger.debug("Request to delete project with ID: {}", id);
        Project project = projectRepository.findByProjectIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found for ID: " + id));
        if (project == null) {
            logger.info("While deleting there is no Such department id : {}", id);
        }
        assert project != null;
        project.setDeleted(true);
        projectRepository.save(project);
        logger.info("Project with ID: {} marked as deleted", id);
        return true;
    }
}
