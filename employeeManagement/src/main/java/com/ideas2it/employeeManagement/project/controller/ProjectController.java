package com.ideas2it.employeeManagement.project.controller;

import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.project.service.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing project-related operations.
 *
 * <p>
 * This controller handles HTTP requests related to Project entities, including
 * creating, retrieving, updating, and deleting projects. It maps client requests
 * to the appropriate service methods and returns responses in the form of JSON.
 * </p>
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private static final Logger logger = LogManager.getLogger(ProjectController.class);

    /**
     * Creates a new project.
     *
     * @param projectDTO {@link ProjectDTO} The DTO containing project data.
     * @return The created project DTO with HTTP status 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        logger.debug("Request to create project with name: {}", projectDTO.getProjectName());
        ProjectDTO createdProjectDTO = projectService.createProject(projectDTO);
        logger.info("Project created with ID: {}", createdProjectDTO.getProjectId());
        return new ResponseEntity<>(createdProjectDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieves a project by its unique project ID.
     *
     * @param id the unique project ID
     * @return the project DTO with HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        logger.debug("Request to retrieve project with ID: {}", id);
        ProjectDTO projectDTO = projectService.getProjectById(id);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    /**
     * Retrieves all projects.
     *
     * @return the list of all project DTOs with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        logger.debug("Request to retrieve all projects");
        List<ProjectDTO> projectDTOs = projectService.getAllProjects();
        return new ResponseEntity<>(projectDTOs, HttpStatus.OK);
    }

    /**
     * Updates an existing project's details by its unique project ID.
     *
     * @param id the unique project ID
     * @param projectDTO {@link ProjectDTO} The DTO containing updated project data.
     * @return the updated project DTO with HTTP status 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        logger.debug("Request to update project with ID: {}", id);
        ProjectDTO updatedProjectDTO = projectService.updateProject(projectDTO);
        return new ResponseEntity<>(updatedProjectDTO, HttpStatus.OK);
    }

    /**
     * Deletes a project by its unique project ID.
     *
     * @param id the unique project ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable Long id) {
        logger.debug("Request to delete project with ID: {}", id);
        boolean result = projectService.deleteProject(id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
}
